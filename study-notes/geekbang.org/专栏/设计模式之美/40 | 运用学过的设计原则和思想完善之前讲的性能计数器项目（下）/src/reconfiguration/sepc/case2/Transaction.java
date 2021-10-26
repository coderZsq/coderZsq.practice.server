package reconfiguration.sepc.case2;

import java.util.Date;
import java.util.List;

public class Transaction {

    public List<Transaction> selectTransactions(Long userId, Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            // 查询两个时间区间的transactions
        }
        if (startDate != null && endDate == null) {
            // 查询startDate之后的所有transactions
        }
        if (startDate == null && endDate != null) {
            // 查询endDate之前的所有transactions
        }
        if (startDate == null && endDate == null) {
            // 查询所有的transactions
        }
        return null;
    }

    // 拆分成多个public函数，更加清晰、易用
    public List<Transaction> selectTransactionsBetween(Long userId, Date startDate, Date endDate) {
        return selectTransactions2(userId, startDate, endDate);
    }

    public List<Transaction> selectTransactionsStartWith(Long userId, Date startDate) {
        return selectTransactions2(userId, startDate, null);
    }

    public List<Transaction> selectTransactionsEndWith(Long userId, Date endDate) {
        return selectTransactions2(userId, null, endDate);
    }

    public List<Transaction> selectAllTransactions(Long userId) {
        return selectTransactions2(userId, null, null);
    }

    private List<Transaction> selectTransactions2(Long userId, Date startDate, Date endDate) {
        // ...
        return null;
    }
}
