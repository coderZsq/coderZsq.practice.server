import { default as Web3 } from 'web3'
import { default as contract } from 'truffle-contract'
import ecommerce_store_artifacts from '../../build/contracts/EcommerceStore.json'

var EcommerceStore = contract(ecommerce_store_artifacts);

var ipfsAPI = require('ipfs-api');

var ipfs = ipfsAPI({ host: 'localhost', port: '5001', protocol: 'http' });

window.App = {
  start: function () {
    var self = this;
    EcommerceStore.setProvider(web3.currentProvider);
    renderStore();

    var reader;
    $("#product-image").change(event => {
      const file = event.target.files[0];
      reader = new window.FileReader();
      reader.readAsArrayBuffer(file);
    });

    $("#add-item-to-store").submit(function (event) {
      const req = $("#add-item-to-store").serialize();
      console.log("req: ", req);
      let params = JSON.parse('{"' + req.replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
      console.log("params: ", params);
      let decodedParams = {}
      Object.keys(params).forEach(function (v) {
        decodedParams[v] = decodeURIComponent(decodeURI(params[v]));
      });
      saveProduct(reader, decodedParams);
      event.preventDefault();
    });

    if ($("#product-details").length > 0) {
      //This is product details page
      let productId = new URLSearchParams(window.location.search).get('id');
      $("#bidding, #revealing").hide();
      renderProductDetails(productId);
    }

    $("#bidding").submit(function (event) {
      $("#msg").hide();
      let amount = $("#bid-amount").val();
      let sendAmount = $("#bid-send-amount").val();
      let secretText = $("#secret-text").val();
      let sealedBid = '0x' + web3.sha3(web3.toWei(amount, 'ether') + secretText).toString('hex');
      let productId = $("#product-id").val();
      console.log(sealedBid + " for " + productId);
      EcommerceStore.deployed().then(function (i) {
        i.bid(parseInt(productId), sealedBid, { value: web3.toWei(sendAmount), from: web3.eth.accounts[0], gas: 440000 }).then(
          function (f) {
            $("#msg").html("Your bid has been successfully submitted!");
            $("#msg").show();
            console.log(f)
          }
        )
      });
      event.preventDefault();
    });

    $("#revealing").submit(function (event) {
      $("#msg").hide();
      let amount = $("#actual-amount").val();
      let secretText = $("#reveal-secret-text").val();
      let productId = $("#product-id").val();
      EcommerceStore.deployed().then(function (i) {
        i.revealBid(parseInt(productId), web3.toWei(amount).toString(), secretText, { from: web3.eth.accounts[0], gas: 440000 }).then(
          function (f) {
            $("#msg").show();
            $("#msg").html("Your bid has been successfully revealed!");
            console.log(f)
          }
        )
      });
      event.preventDefault();
    });

    $("#finalize-auction").submit(function (event) {
      $("#msg").hide();
      let productId = $("#product-id").val();
      EcommerceStore.deployed().then(function (i) {
        i.finalizeAuction(parseInt(productId), { from: web3.eth.accounts[0], gas: 4400000 }).then(
          function (f) {
            $("#msg").show();
            $("#msg").html("The auction has been finalized and winner declared.");
            console.log(f)
            location.reload();
          }
        ).catch(function (e) {
          console.log(e);
          $("#msg").show();
          $("#msg").html("The auction can not be finalized by the buyer or seller, only a third party aribiter can finalize it");
        })
      });
      event.preventDefault();
    });

    $("#release-funds").click(function () {
      let productId = new URLSearchParams(window.location.search).get('id');
      EcommerceStore.deployed().then(function (f) {
        $("#msg").html("Your transaction has been submitted. Please wait for few seconds for the confirmation").show();
        console.log(productId);
        f.releaseAmountToSeller(productId, { from: web3.eth.accounts[0], gas: 440000 }).then(function (f) {
          console.log(f);
          location.reload();
        }).catch(function (e) {
          console.log(e);
        })
      });
    });

    $("#refund-funds").click(function () {
      let productId = new URLSearchParams(window.location.search).get('id');
      EcommerceStore.deployed().then(function (f) {
        $("#msg").html("Your transaction has been submitted. Please wait for few seconds for the confirmation").show();
        f.refundAmountToBuyer(productId, { from: web3.eth.accounts[0], gas: 440000 }).then(function (f) {
          console.log(f);
          location.reload();
        }).catch(function (e) {
          console.log(e);
        })
      });

      alert("refund the funds!");
    });

    function renderProductDetails(productId) {
      EcommerceStore.deployed().then(function (i) {
        i.getProduct.call(productId).then(function (p) {
          console.log(p);
          let content = "";
          ipfs.cat(p[4]).then(function (stream) {
            stream.on('data', function (chunk) {
              // do stuff with this chunk of data
              content += chunk.toString();
              $("#product-desc").append("<div>" + content + "</div>");
            })
          });

          $("#product-image").append("<img src='http://localhost:8080/ipfs/" + p[3] + "' width='250px' />");
          $("#product-price").html(displayPrice(p[7]));
          $("#product-name").html(p[1]);
          $("#product-auction-end").html(displayEndHours(p[6]));
          $("#product-id").val(p[0]);
          $("#revealing, #bidding, #finalize-auction, #escrow-info").hide();
          let currentTime = getCurrentTimeInSeconds();
          if (parseInt(p[8]) == 1) {
            EcommerceStore.deployed().then(function (i) {
              $("#escrow-info").show();
              i.highestBidderInfo.call(productId).then(function (f) {
                if (f[2].toLocaleString() == '0') {
                  $("#product-status").html("Auction has ended. No bids were revealed");
                } else {
                  $("#product-status").html("Auction has ended. Product sold to " + f[0] + " for " + displayPrice(f[2]) +
                    "The money is in the escrow. Two of the three participants (Buyer, Seller and Arbiter) have to " +
                    "either release the funds to seller or refund the money to the buyer");
                }
              })
              i.escrowInfo.call(productId).then(function (f) {
                $("#buyer").html('Buyer: ' + f[0]);
                $("#seller").html('Seller: ' + f[1]);
                $("#arbiter").html('Arbiter: ' + f[2]);
                if (f[3] == true) {
                  $("#release-count").html("Amount from the escrow has been released");
                } else {
                  $("#release-count").html(f[4] + " of 3 participants have agreed to release funds");
                  $("#refund-count").html(f[5] + " of 3 participants have agreed to refund the buyer");
                }
              })
            })
          } else if (parseInt(p[8]) == 2) {
            $("#product-status").html("Product was not sold");
          } else if (currentTime < parseInt(p[6])) {
            $("#bidding").show();
          } else if (currentTime < (parseInt(p[6]) + 600)) {
            $("#revealing").show();
          } else {
            $("#finalize-auction").show();
          }
        })
      })
    }
  },
};

function renderStore() {
  EcommerceStore.deployed().then(i => {
    i.getProduct(1).then(p => {
      $("#product-list").append(buildProduct(p));
    });
    i.getProduct(2).then(p => {
      $("#product-list").append(buildProduct(p));
    });
  });
}

function buildProduct(product) {
  let node = $("<div/>");
  node.addClass("col-sm-3 text-center col-margin-bottom-1");
  node.append("<a href='product.html?id=" + product[0] + "'><img src='http://localhost:8080/ipfs/" + product[3] + "' width='150px' /></a>");
  node.append("<div>" + product[1] + "</div>");
  node.append("<div>" + product[2] + "</div>");
  node.append("<div>" + product[5] + "</div>");
  node.append("<div>" + product[6] + "</div>");
  node.append("<div> Ether " + product[7] + "</div>");
  return node;
}

function saveProduct(reader, decodedParams) {
  let imageId, descId;
  saveImageOnIpfs(reader).then(function (id) {
    imageId = id;
    saveTextBlobOnIpfs(decodedParams["product-description"]).then(function (id) {
      descId = id;
      saveProductToBlockchain(decodedParams, imageId, descId);
    })
  })
}

function saveImageOnIpfs(reader) {
  return new Promise(function (resolve, reject) {
    const buffer = Buffer.from(reader.result);
    ipfs.add(buffer)
      .then((response) => {
        console.log(response)
        resolve(response[0].hash);
      }).catch((err) => {
        console.error(err)
        reject(err);
      })
  })
}

function saveTextBlobOnIpfs(blob) {
  return new Promise(function (resolve, reject) {
    const descBuffer = Buffer.from(blob, 'utf-8');
    ipfs.add(descBuffer)
      .then((response) => {
        console.log(response)
        resolve(response[0].hash);
      }).catch((err) => {
        console.error(err)
        reject(err);
      })
  })
}

function saveProductToBlockchain(params, imageId, descId) {
  console.log(params);
  let auctionStartTime = Date.parse(params["product-auction-start"]) / 1000;
  let auctionEndTime = auctionStartTime + parseInt(params["product-auction-end"]) * 24 * 60 * 60

  EcommerceStore.deployed().then(function (i) {
    i.addProductToStore(params["product-name"], params["product-category"], imageId, descId, auctionStartTime,
      auctionEndTime, web3.toWei(params["product-price"], 'ether'), parseInt(params["product-condition"]), { from: web3.eth.accounts[0], gas: 440000 }).then(function (f) {
        console.log(f);
        $("#msg").show();
        $("#msg").html("Your product was successfully added to your store!");
      })
  });
}

function renderProductDetails(productId) {
  EcommerceStore.deployed().then(function (i) {
    i.getProduct.call(productId).then(function (p) {
      console.log(p);
      let content = "";
      ipfs.cat(p[4]).then(function (file) {
        content = file.toString();
        $("#product-desc").append("<div>" + content + "</div>");
      });

      $("#product-image").append("<img src='http://localhost:8080/ipfs/" + p[3] + "' width='250px' />");
      $("#product-price").html(displayPrice(p[7]));
      $("#product-name").html(p[1]);
      $("#product-auction-end").html(displayEndHours(p[6]));
      $("#product-id").val(p[0]);
      $("#revealing, #bidding").hide();
      let currentTime = getCurrentTimeInSeconds();
      if (currentTime < p[6]) {
        $("#bidding").show();
      } else if (currentTime - (60) < p[6]) {
        $("#revealing").show();
      }
    })
  })
}

function getCurrentTimeInSeconds() {
  return Math.round(new Date() / 1000);
}

function displayPrice(amt) {
  return 'Ξ' + web3.fromWei(amt, 'ether');
}


function displayEndHours(seconds) {
  let current_time = getCurrentTimeInSeconds()
  let remaining_seconds = seconds - current_time;

  if (remaining_seconds <= 0) {
    return "Auction has ended";
  }

  let days = Math.trunc(remaining_seconds / (24 * 60 * 60));
  remaining_seconds -= days * 24 * 60 * 60;

  let hours = Math.trunc(remaining_seconds / (60 * 60));
  remaining_seconds -= hours * 60 * 60;

  let minutes = Math.trunc(remaining_seconds / 60);
  remaining_seconds -= minutes * 60;

  if (days > 0) {
    return "Auction ends in " + days + " days, " + hours + ", hours, " + minutes + " minutes";
  } else if (hours > 0) {
    return "Auction ends in " + hours + " hours, " + minutes + " minutes ";
  } else if (minutes > 0) {
    return "Auction ends in " + minutes + " minutes ";
  } else {
    return "Auction ends in " + remaining_seconds + " seconds";
  }
}

window.addEventListener('load', function () {
  if (typeof web3 !== 'undefined') {
    window.web3 = new Web3(web3.currentProvider);
  } else {
    window.web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
  }
  App.start();
});