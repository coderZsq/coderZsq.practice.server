package cn.wolfcode.di;

import org.springframework.beans.factory.annotation.Value;

public class ValueBean {

	@Value("${server.port}")
	private int port;

	public String toString() {
		return "ValueBean [port=" + port + "]";
	}
}
