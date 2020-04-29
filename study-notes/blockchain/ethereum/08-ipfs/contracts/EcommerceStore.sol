pragma solidity >=0.4.21 <0.7.0;

import "contracts/Escrow.sol";


contract EcommerceStore {
    enum ProductStatus {Open, Sold, Unsold}
    enum ProductCondition {New, Used}

    uint256 public productIndex;
    mapping(uint256 => address) productIdInStore;
    mapping(address => mapping(uint256 => Product)) stores;

    mapping(uint256 => address) productEscrow;

    struct Product {
        uint256 id;
        string name;
        string category;
        string imageLink;
        string descLink;
        uint256 auctionStartTime;
        uint256 auctionEndTime;
        uint256 startPrice;
        address highestBidder;
        uint256 highestBid;
        uint256 secondHighestBid;
        uint256 totalBids;
        ProductStatus status;
        ProductCondition condition;
        mapping(address => mapping(bytes32 => Bid)) bids;
    }

    struct Bid {
        address bidder;
        uint256 productId;
        uint256 value;
        bool revealed;
    }

    constructor() public {
        productIndex = 0;
    }

    function addProductToStore(
        string memory _name,
        string memory _category,
        string memory _imageLink,
        string memory _descLink,
        uint256 _auctionStartTime,
        uint256 _auctionEndTime,
        uint256 _startPrice,
        uint256 _productCondition
    ) public {
        require(
            _auctionStartTime < _auctionEndTime,
            "Auction start time should be earlier than end time."
        );
        productIndex += 1;
        Product memory product = Product(
            productIndex,
            _name,
            _category,
            _imageLink,
            _descLink,
            _auctionStartTime,
            _auctionEndTime,
            _startPrice,
            address(0),
            0,
            0,
            0,
            ProductStatus.Open,
            ProductCondition(_productCondition)
        );
        stores[msg.sender][productIndex] = product;
        productIdInStore[productIndex] = msg.sender;
    }

    function getProduct(uint256 _productId)
        public
        view
        returns (
            uint256,
            string memory,
            string memory,
            string memory,
            string memory,
            uint256,
            uint256,
            uint256,
            ProductStatus,
            ProductCondition
        )
    {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return (
            product.id,
            product.name,
            product.category,
            product.imageLink,
            product.descLink,
            product.auctionStartTime,
            product.auctionEndTime,
            product.startPrice,
            product.status,
            product.condition
        );
    }

    function bid(uint256 _productId, bytes32 _bid)
        public
        payable
        returns (bool)
    {
        Product storage product = stores[productIdInStore[_productId]][_productId];
        require(
            now >= product.auctionStartTime,
            "Current time should be later than auction start time"
        );
        require(
            now <= product.auctionEndTime,
            "Current time should be earlier than auction end time"
        );
        require(
            msg.value > product.startPrice,
            "Value should be larger than start price"
        );
        require(
            product.bids[msg.sender][_bid].bidder == address(0),
            "Bidder should be null"
        );
        product.bids[msg.sender][_bid] = Bid(
            msg.sender,
            _productId,
            msg.value,
            false
        );
        product.totalBids += 1;
        return true;
    }

    function revealBid(
        uint256 _productId,
        string memory _amount,
        string memory _secret
    ) public {
        Product storage product = stores[productIdInStore[_productId]][_productId];
        require(now >= product.auctionEndTime, "");
        bytes32 sealedBid = sha3(_amount, _secret);
        Bid memory bidInfo = product.bids[msg.sender][sealedBid];
        require(bidInfo.bidder > address(0), "Bidder should exist");
        require(bidInfo.revealed == false, "Bid should not be revealed");
        uint256 refund;
        uint256 amount = stringToUint(_amount);
        if (bidInfo.value < amount) {
            refund = bidInfo.value;
        } else {
            if (address(product.highestBidder) == 0) {
                product.highestBidder = msg.sender;
                product.highestBid = amount;
                product.secondHighestBid = product.startPrice;
                refund = bidInfo.value - amount;
            } else {
                if (amount > product.highestBid) {
                    product.secondHighestBid = product.highestBid;
                    product.highestBidder.transfer(product.highestBid);
                    product.highestBid = amount;
                    product.highestBidder = msg.sender;
                    refund = bidInfo.value - amount;
                } else if (amount > product.secondHighestBid) {
                    product.secondHighestBid = amount;
                    refund = bidInfo.value;
                } else {
                    refund = bidInfo.value;
                }
            }
        }
        product.bids[msg.sender][sealedBid].revealed = true;

        if (refund > 0) {
            msg.sender.transfer(refund);
        }
    }

    function stringToUint(string memory s) private pure returns (uint256) {
        bytes memory b = bytes(s);
        uint256 result = 0;
        for (uint256 i = 0; i < b.length; i++) {
            if (b[i] >= 48 && b[i] <= 57) {
                result = result * 10 + (uint256(b[i]) - 48);
            }
        }
        return result;
    }

    function highestBidderInfo(uint256 _productId)
        public
        view
        returns (address, uint256, uint256)
    {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return (
            product.highestBidder,
            product.highestBid,
            product.secondHighestBid
        );
    }

    function totalBids(uint256 _productId) public view returns (uint256) {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return product.totalBids;
    }

    function finalizeAuction(uint256 _productId) public {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        // 48 hours to reveal the bid
        require(now > product.auctionEndTime);
        require(product.status == ProductStatus.Open);
        require(product.highestBidder != msg.sender);
        require(productIdInStore[_productId] != msg.sender);

        if (product.highestBidder == 0) {
            product.status = ProductStatus.Unsold;
        } else {
            // Whoever finalizes the auction is the arbiter
            Escrow escrow = (new Escrow).value(product.secondHighestBid)(
                _productId,
                product.highestBidder,
                productIdInStore[_productId],
                msg.sender
            );
            productEscrow[_productId] = address(escrow);
            product.status = ProductStatus.Sold;
            // The bidder only pays the amount equivalent to second highest bidder
            // Refund the difference
            uint256 refund = product.highestBid - product.secondHighestBid;
            product.highestBidder.transfer(refund);
        }
        stores[productIdInStore[_productId]][_productId] = product;
    }

    function escrowAddressForProduct(uint256 _productId)
        public
        view
        returns (address)
    {
        return productEscrow[_productId];
    }

    function escrowInfo(uint256 _productId)
        public
        view
        returns (address, address, address, bool, uint256, uint256)
    {
        return Escrow(productEscrow[_productId]).escrowInfo();
    }
}
