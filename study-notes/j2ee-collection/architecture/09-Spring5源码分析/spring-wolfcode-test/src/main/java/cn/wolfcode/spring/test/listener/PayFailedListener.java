package cn.wolfcode.spring.test.listener;

import cn.wolfcode.spring.test.event.PayFailedEvent;
import cn.wolfcode.spring.test.event.PaySuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Leon
 * @date 2021/3/25
 */
@Component
public class PayFailedListener {

	@EventListener(PayFailedEvent.class)
	public void onApplicationEvent(PayFailedEvent event) {
		System.out.println("【收到支付失败事件】：订单=" + event.getOrderNo() + ", 交易订单=" + event.getTransNo() + ", 失败编码=" + event.getCode() + ", 失败原因=" + event.getMsg());
	}

	@EventListener(PaySuccessEvent.class)
	public void onApplicationEvent(PaySuccessEvent event) {
		System.out.println("【收到支付成功事件】：订单=" + event.getOrderNo() + ", 交易订单=" + event.getTransNo() + ", 支付金额=" + event.getAmount());
	}
}
