package com.redv.fxbtc.domain;

public enum Symbol {

	BTC_CNY("btc_cny"),LTC_CNY("ltc_cny"),LTC_BTC("ltc_btc");

	private String symbol;

	private Symbol(String symbol) {
		this.symbol = symbol;
	}

	public static Symbol valueOfSymbol(String symbolString) {
		if (symbolString == null) {
			return null;
		}

		for (Symbol symbol : Symbol.values()) {
			if (symbol.symbol.equals(symbolString)) {
				return symbol;
			}
		}

		throw new IllegalArgumentException("Unexpected symbol: " + symbolString);

	}

	public String getSymbol() {
		return symbol;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return symbol;
	}

}
