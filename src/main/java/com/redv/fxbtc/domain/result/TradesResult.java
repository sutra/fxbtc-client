package com.redv.fxbtc.domain.result;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Params;
import com.redv.fxbtc.domain.Trade;

public class TradesResult extends MarketDataResult<List<Trade>> {

	private static final long serialVersionUID = 2013122101L;

	public TradesResult(@JsonProperty("result") boolean result,
			@JsonProperty("datas") List<Trade> data,
			@JsonProperty("params") Params params,
			@JsonProperty("error") Error error) {
		super(result, data, params, error);
	}

	public List<Trade> getTrades() {
		return getData() == null ? Collections.<Trade>emptyList() : getData();
	}

}
