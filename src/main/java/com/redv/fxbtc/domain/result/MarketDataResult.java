package com.redv.fxbtc.domain.result;

import com.redv.fxbtc.domain.Params;

public abstract class MarketDataResult<T> extends DataResult<T> {

	private static final long serialVersionUID = 2013122201L;

	private Params params;

	public MarketDataResult(boolean result, T data, Params params) {
		super(result, data);
		this.params = params;
	}

	public Params getParams() {
		return params;
	}

}
