package com.redv.fxbtc.domain;

public class Params extends AbstractObject {

	private static final long serialVersionUID = 2013122101L;

	private Symbol symbol;

	private Integer count;

	private String since;

	public void setSymbol(String symbol) {
		this.symbol = Symbol.valueOfSymbol(symbol);
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public Integer getCount() {
		return count;
	}

	public String getSince() {
		return since;
	}

}
