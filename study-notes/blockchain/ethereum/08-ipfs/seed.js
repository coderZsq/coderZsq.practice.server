EcommerceStore = artifacts.require('./EcommerceStore.sol');

module.exports = function () {
  amt_1 = web3.toWei(1, 'ether');
  current_time = Math.round(new Date() / 1000);
  EcommerceStore.deployed().then(function (i) { i.addProductToStore('iphone 5', 'Cell Phones & Accessories', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', current_time, current_time + 200, 2 * amt_1, 0).then(function (f) { console.log(f) }) });
  EcommerceStore.deployed().then(function (i) { i.addProductToStore('iphone 5s', 'Cell Phones & Accessories', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', current_time, current_time + 400, 3 * amt_1, 1).then(function (f) { console.log(f) }) });
  EcommerceStore.deployed().then(function (i) { i.addProductToStore('iphone 6', 'Cell Phones & Accessories', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', current_time, current_time + 14, amt_1, 0).then(function (f) { console.log(f) }) });
  EcommerceStore.deployed().then(function (i) { i.addProductToStore('iphone 6s', 'Cell Phones & Accessories', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', current_time, current_time + 86400, 4 * amt_1, 1).then(function (f) { console.log(f) }) });
  EcommerceStore.deployed().then(function (i) { i.addProductToStore('iphone 7', 'Cell Phones & Accessories', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', current_time, current_time + 86400, 5 * amt_1, 1).then(function (f) { console.log(f) }) });
  EcommerceStore.deployed().then(function (i) { i.addProductToStore('Jeans', 'Clothing, Shoes & Accessories', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', 'QmXFDHuwDobmgXZQ8PfkJoTPHm31jZns9FLjpZrA1sBcqR', current_time, current_time + 86400 + 86400 + 86400, 5 * amt_1, 1).then(function (f) { console.log(f) }) });
  EcommerceStore.deployed().then(function (i) { i.productIndex.call().then(function (f) { console.log(f) }) });
}