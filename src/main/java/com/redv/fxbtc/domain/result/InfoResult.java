package com.redv.fxbtc.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Info;

public class InfoResult extends DataResult<Info> {

	private static final long serialVersionUID = 2013122201L;

	public InfoResult(@JsonProperty("result") boolean result,
			@JsonProperty("info") Info data) {
		super(result, data);
	}

	public Info getInfo() {
		return getData();
	}

}
