package com.redv.fxbtc;

import com.redv.fxbtc.domain.result.Result.Error;

public class TokenTimeoutException extends FXBTCClientException {

	private static final long serialVersionUID = 2014010701L;

	public TokenTimeoutException(Error error) {
		super(error);
	}

}
