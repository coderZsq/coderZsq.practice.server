pragma solidity >=0.4.21 <0.7.0;


contract Escrow {
    uint256 public productId;
    address public buyer;
    address public seller;
    address public arbiter;
    uint256 public amount;
    bool public fundsDisbursed;
    mapping(address => bool) releaseAmount;
    uint256 public releaseCount;
    mapping(address => bool) refundAmount;
    uint256 public refundCount;

    event CreateEscrow(
        uint256 _productId,
        address _buyer,
        address _seller,
        address _arbiter
    );
    event UnlockAmount(
        uint256 _productId,
        string _operation,
        address _operator
    );
    event DisburseAmount(
        uint256 _productId,
        uint256 _amount,
        address _beneficiary
    );

    constructor(
        uint256 _productId,
        address _buyer,
        address _seller,
        address _arbiter
    ) public payable {
        productId = _productId;
        buyer = _buyer;
        seller = _seller;
        arbiter = _arbiter;
        amount = msg.value;
        fundsDisbursed = false;
        emit CreateEscrow(_productId, _buyer, _seller, _arbiter);
    }

    function escrowInfo()
        public
        view
        returns (address, address, address, bool, uint256, uint256)
    {
        return (
            buyer,
            seller,
            arbiter,
            fundsDisbursed,
            releaseCount,
            refundCount
        );
    }

    function releaseAmountToSeller(address caller) public {
        require(!fundsDisbursed, "");
        if (
            (caller == buyer || caller == seller || caller == arbiter) &&
            releaseAmount[caller] != true
        ) {
            releaseAmount[caller] = true;
            releaseCount += 1;
            emit UnlockAmount(productId, "release", caller);
        }

        if (releaseCount == 2) {
            seller.transfer(amount);
            fundsDisbursed = true;
            emit DisburseAmount(productId, amount, seller);
        }
    }

    function refundAmountToBuyer(address caller) public {
        require(!fundsDisbursed, "");
        if (
            (caller == buyer || caller == seller || caller == arbiter) &&
            refundAmount[caller] != true
        ) {
            refundAmount[caller] = true;
            refundCount += 1;
            emit UnlockAmount(productId, "refund", caller);
        }

        if (refundCount == 2) {
            buyer.transfer(amount);
            fundsDisbursed = true;
            emit DisburseAmount(productId, amount, buyer);
        }
    }
}
