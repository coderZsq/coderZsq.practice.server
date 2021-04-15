package cn.wolfcode.spring.test.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 支付失败事件
 *
 * @author Leon
 * @date 2021/3/25
 */
public class PayFailedEvent extends ApplicationContextEvent {

	private static final long serialVersionUID = 0L;

	/** 订单编号 */
	private String orderNo;
	/** 支付订单号 */
	private String transNo;
	/** 失败编码 */
	private String code;
	/** 失败原因 */
	private String msg;

	/**
	 * Create a new ContextStartedEvent.
	 *
	 * @param source the {@code ApplicationContext} that the event is raised for
	 *               (must not be {@code null})
	 */
	public PayFailedEvent(ApplicationContext source, String orderNo, String transNo, String code, String msg) {
		super(source);
		this.orderNo = orderNo;
		this.transNo = transNo;
		this.code = code;
		this.msg = msg;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getTransNo() {
		return transNo;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
