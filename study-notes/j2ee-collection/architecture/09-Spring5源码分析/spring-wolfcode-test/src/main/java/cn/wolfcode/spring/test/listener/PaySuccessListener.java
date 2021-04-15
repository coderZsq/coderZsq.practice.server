package cn.wolfcode.spring.test.listener;

import cn.wolfcode.spring.test.event.PaySuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Leon
 * @date 2021/3/25
 */
@Component
public class PaySuccessListener implements ApplicationListener<PaySuccessEvent> {

	@Override
	public void onApplicationEvent(PaySuccessEvent event) {
		System.out.println("【收到支付成功事件】：订单=" + event.getOrderNo() + ", 交易订单=" + event.getTransNo() + ", 支付金额=" + event.getAmount());
	}
}
