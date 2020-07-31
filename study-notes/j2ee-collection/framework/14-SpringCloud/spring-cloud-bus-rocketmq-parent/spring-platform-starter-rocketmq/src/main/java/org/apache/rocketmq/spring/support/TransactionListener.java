package org.apache.rocketmq.spring.support;

import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.TransactionCheckListener;

public abstract class TransactionListener implements TransactionCheckListener, LocalTransactionExecuter {

}
