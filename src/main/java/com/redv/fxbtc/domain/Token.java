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
		return getTimeout().compareTo(new Date()) < 0;
	}

}
