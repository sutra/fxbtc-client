package com.redv.fxbtc.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Params;
import com.redv.fxbtc.domain.Ticker;

public class TickerResult extends MarketDataResult<Ticker> {

	private static final long serialVersionUID = 2013122101L;

	public TickerResult(@JsonProperty("result") boolean result,
			@JsonProperty("ticker") Ticker ticker,
			@JsonProperty("params") Params params) {
		super(result, ticker, params);
	}

	public Ticker getTicker() {
		return getData();
	}

}
