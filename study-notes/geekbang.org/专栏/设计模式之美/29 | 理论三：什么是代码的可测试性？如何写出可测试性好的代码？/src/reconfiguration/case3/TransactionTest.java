package reconfiguration.case3;

import javax.transaction.InvalidTransactionException;

import static org.junit.Assert.*;

public class TransactionTest {
    public void testExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;

        TransactionLock mockLock = new TransactionLock() {
            @Override
            public boolean lock(String id) {
                return true;
            }

            @Override
            public void unlock() {}
        };

        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, String.valueOf(orderId));
        transaction.setWalletRpcService(new MockWalletRpcServiceOne());
        transaction.setTransactionLock(mockLock);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }

    public void testExecute_with_TransactionIsExpired() throws InvalidTransactionException {
        // Long buyerId = 123L;
        // Long sellerId = 234L;
        // Long productId = 345L;
        // Long orderId = 456L;
        // Transaction transaction = new Transaction(null, buyerId, sellerId, productId, String.valueOf(orderId));
        // transaction.setCreateTimestamp(System.currentTimeMillis() - 14/*days*/);
        // boolean actualResult = transaction.execute();
        // assertFalse(actualResult);
        // assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }


    public void testExecute_with_TransactionIsExpired2() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, String.valueOf(orderId)) {
            @Override
            protected boolean isExpired() {
                return true;
            }
        };
        boolean actualResult = transaction.execute();
        assertFalse(actualResult);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }
}
