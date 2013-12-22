package com.redv.fxbtc.domain;

public enum Type {

	BID("bid", "buy"), ASK("ask", "sell");

	public static Type valueOfType(String typeString) {
		if (typeString == null) {
			return null;
		}

		for (Type type : Type.values()) {
			if (type.type.equals(typeString)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unexpected type: " + typeString);
	}

	public static Type valueOfTradeType(String tradeTypeString) {
		if (tradeTypeString == null) {
			return null;
		}

		for (Type type : Type.values()) {
			if (type.tradeType.equals(tradeTypeString)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unexpected trade type: "
				+ tradeTypeString);
	}

	private String type;

	private String tradeType;

	Type(String type, String tradeType) {
		this.type = type;
		this.tradeType = tradeType;
	}

	public String getType() {
		return type;
	}

	public String getTradeType() {
		return tradeType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return type;
	}

}
