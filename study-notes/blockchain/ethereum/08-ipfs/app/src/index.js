import { default as Web3 } from 'web3'
import { default as contract } from 'truffle-contract'
import ecommerce_store_artifacts from '../../build/contracts/EcommerceStore.json'

var EcommerceStore = contract(ecommerce_store_artifacts);

var ipfsAPI = require('ipfs-api');

var ipfs = ipfsAPI({ host: 'localhost', port: '5001', protocol: 'http' });

window.App = {
  start: function () {
    var self = this;
  },
};

window.addEventListener('load', function () {
  if (typeof web3 !== undefined) {
    window.web3 = new Web3(web3.currentProvider);
  } else {
    window.web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'));
  }
  App.start();
});