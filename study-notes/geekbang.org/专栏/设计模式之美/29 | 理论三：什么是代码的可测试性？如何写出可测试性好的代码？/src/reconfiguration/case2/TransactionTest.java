package reconfiguration.case2;

import javax.transaction.InvalidTransactionException;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    public void testExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, String.valueOf(orderId));
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
    }
}
