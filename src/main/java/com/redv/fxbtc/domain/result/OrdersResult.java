package com.redv.fxbtc.domain.result;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Order;
import com.redv.fxbtc.domain.Symbol;

public class OrdersResult extends DataResult<List<Order>> {

	private static final long serialVersionUID = 2013122201L;

	private Symbol symbol;

	public OrdersResult(@JsonProperty("result") boolean result,
			@JsonProperty("symbol") String symbol,
			@JsonProperty("orders") List<Order> data) {
		super(result, data);
		this.symbol = Symbol.valueOfSymbol(symbol);
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public List<Order> getOrders() {
		return getData() == null ? Collections.<Order>emptyList() : getData();
	}

}
