package com.redv.fxbtc.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckTokenResult extends DataResult<Void> {

	private static final long serialVersionUID = 2013122201L;

	public CheckTokenResult(@JsonProperty("result") boolean result,
			@JsonProperty("error") Error error) {
		super(result, null, error);
	}

	public boolean isValid() {
		return isSuccess();
	}

}
