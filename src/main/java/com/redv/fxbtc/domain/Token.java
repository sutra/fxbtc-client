package com.redv.fxbtc.domain;

import java.util.Date;

public class Token extends AbstractObject {

	private static final long serialVersionUID = 2013122101L;

	private String token;

	private Date timeout;

	public Token(String token, Date timeout) {
		this.token = token;
		this.timeout = timeout;
	}

	public String getToken() {
		return token;
	}

	public Date getTimeout() {
		return timeout;
	}

	public boolean isTimeout() {
		return isTimeout(System.currentTimeMillis());
	}

	/**
	 * Returns if the token is timeout after given seconds.
	 */
	public boolean isTimeoutAfter(long seconds) {
		return isTimeout(System.currentTimeMillis() + seconds * 1000);
	}

	public boolean isTimeout(long timeMillis) {
		return getTimeout().compareTo(new Date(timeMillis)) < 0;
	}

}
