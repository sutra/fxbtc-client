package com.redv.fxbtc.domain.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Symbol;
import com.redv.fxbtc.domain.TradeInfo;
import com.redv.fxbtc.domain.Type;

public class TradeInfoResult extends DataResult<TradeInfo> {

	private static final long serialVersionUID = 2013122201L;

	private Symbol symbol;

	private Type type;

	public TradeInfoResult(@JsonProperty("result") boolean result,
			@JsonProperty("symbol") String symbol,
			@JsonProperty("type") String type,
			@JsonProperty("trade_info") TradeInfo data) {
		super(result, data);
		this.symbol = Symbol.valueOfSymbol(symbol);
		this.type = Type.valueOfTradeType(type);
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public Type getType() {
		return type;
	}

	public TradeInfo getTradeInfo() {
		return getData();
	}

}
