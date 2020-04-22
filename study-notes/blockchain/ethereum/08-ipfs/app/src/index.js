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
  node.append("<img src='http://localhost:8080/ipfs/" + product[3] + "' width='150px' />");
  node.append("<div>" + product[1] + "</div>");
  node.append("<div>" + product[2] + "</div>");
  node.append("<div>" + product[5] + "</div>");
  node.append("<div>" + product[6] + "</div>");
  node.append("<div> Ether " + product[7] + "</div>");
  return node;
}

window.addEventListener('load', function () {
  if (typeof web3 !== 'undefined') {
    window.web3 = new Web3(web3.currentProvider);
  } else {
    window.web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
  }
  App.start();
});