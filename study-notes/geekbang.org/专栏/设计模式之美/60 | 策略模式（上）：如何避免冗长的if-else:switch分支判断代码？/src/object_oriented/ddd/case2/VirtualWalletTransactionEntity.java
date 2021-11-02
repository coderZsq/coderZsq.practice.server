package object_oriented.ddd.case2;

import java.math.BigDecimal;

public class VirtualWalletTransactionEntity {
    private BigDecimal amount;
    private Long createTime;
    private TransactionType type;
    private Long fromWalletId;
    private Long toWalletId;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreateTime(long currentTimeMillis) {
        this.createTime = currentTimeMillis;
    }

    public void setType(TransactionType debit) {
        this.type = debit;
    }

    public void setFromWalletId(Long walletId) {
        this.fromWalletId = walletId;
    }

    public void setToWalletId(Long toWalletId) {
        this.toWalletId = toWalletId;
    }
}
