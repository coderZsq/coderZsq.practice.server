package cn.wolfcode.spring.test.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.math.BigDecimal;

/**
 * 支付成功事件
 *
 * @author Leon
 * @date 2021/3/25
 */
public class PaySuccessEvent extends ApplicationContextEvent {

	private static final long serialVersionUID = 0L;

	/** 订单编号 */
	private String orderNo;
	/** 支付订单号 */
	private String transNo;
	/** 支付金额 */
	private BigDecimal amount;

	/**
	 * Create a new ContextStartedEvent.
	 *
	 * @param source the {@code ApplicationContext} that the event is raised for
	 *               (must not be {@code null})
	 */
	public PaySuccessEvent(ApplicationContext source, String orderNo, String transNo, BigDecimal amount) {
		super(source);
		this.orderNo = orderNo;
		this.transNo = transNo;
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getTransNo() {
		return transNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}
