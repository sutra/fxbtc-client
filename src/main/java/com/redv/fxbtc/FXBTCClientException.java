package com.redv.fxbtc;

import java.io.IOException;

public class FXBTCClientException extends IOException {

	private static final long serialVersionUID = 2013122401L;

	private com.redv.fxbtc.domain.result.Result.Error error;

	public FXBTCClientException(com.redv.fxbtc.domain.result.Result.Error error) {
		super(error.getMsg());
		this.error = error;
	}

	public int getErrorCode() {
		return error.getCode();
	}

}
