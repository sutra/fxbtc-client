package com.redv.fxbtc.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Depth;
import com.redv.fxbtc.domain.Params;
import com.redv.fxbtc.domain.Symbol;

public class DepthResult extends MarketDataResult<Depth> {

	private static final long serialVersionUID = 2013122101L;

	private Symbol symbol;

	public DepthResult(@JsonProperty("result") boolean result,
			@JsonProperty("depth") Depth data,
			@JsonProperty("params") Params params,
			@JsonProperty("symbol") String symbol) {
		super(result, data, params);
		this.symbol = Symbol.valueOfSymbol(symbol);
	}

	public Depth getDepth() {
		return getData();
	}

	public Symbol getSymbol() {
		return symbol;
	}

}
