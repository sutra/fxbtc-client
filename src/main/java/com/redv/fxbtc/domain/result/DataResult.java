package com.redv.fxbtc.domain.result;

public abstract class DataResult<T> extends Result {

	private static final long serialVersionUID = 2013122101L;

	private T data;

	public DataResult(boolean result, T data) {
		super(result);
		this.data = data;
	}

	protected T getData() {
		return data;
	}

}
