package com.redv.fxbtc.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelOrderResult extends DataResult<Long> {

	private static final long serialVersionUID = 2013122201L;

	public CancelOrderResult(@JsonProperty("result") boolean result,
			@JsonProperty("id") Long data,
			@JsonProperty("error") Error error) {
		super(result, data, error);
	}

	public Long getId() {
		return getData();
	}

}
