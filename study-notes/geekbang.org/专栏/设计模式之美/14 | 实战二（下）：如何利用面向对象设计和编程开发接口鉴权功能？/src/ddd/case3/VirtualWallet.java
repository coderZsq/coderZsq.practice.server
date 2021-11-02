package ddd.case3;

import oop.encapsulation.InvalidAmountException;

import java.math.BigDecimal;

public class VirtualWallet { // Domain领域模型(充血模型)
    private Long id;
    private Long createTime = System.currentTimeMillis();;
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            try {
                throw new InsufficientBalanceException(null);
            } catch (InsufficientBalanceException e) {
                e.printStackTrace();
            }
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            try {
                throw new InvalidAmountException(null);
            } catch (InvalidAmountException e) {
                e.printStackTrace();
            }
        }
        this.balance = this.balance.add(amount);
    }
}

