package ddd.case4;

import ddd.case3.InsufficientBalanceException;
import oop.encapsulation.InvalidAmountException;

import java.math.BigDecimal;

public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    ;
    private BigDecimal balance = BigDecimal.ZERO;
    private boolean isAllowedOverdraft = true;
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) {
    }

    public void unfreeze(BigDecimal amount) {
    }

    public void increaseOverdraftAmount(BigDecimal amount) {
    }

    public void decreaseOverdraftAmount(BigDecimal amount) {
    }

    public void closeOverdraft() {
    }

    public void openOverdraft() {
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public BigDecimal getAvaliableBalance() {
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvaliableBalance.add(this.overdraftAmount);
        }
        return totalAvaliableBalance;
    }

    public void debit(BigDecimal amount) {
        BigDecimal totoalAvaliableBalance = getAvaliableBalance();
        if (totoalAvaliableBalance.compareTo(amount) < 0) {
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
