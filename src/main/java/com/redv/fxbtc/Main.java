package com.redv.fxbtc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redv.fxbtc.domain.Depth;
import com.redv.fxbtc.domain.Info;
import com.redv.fxbtc.domain.Order;
import com.redv.fxbtc.domain.Symbol;
import com.redv.fxbtc.domain.Ticker;
import com.redv.fxbtc.domain.Trade;
import com.redv.fxbtc.domain.TradeInfo;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		String username = args[0];
		String password = args[1];

		FXBTCClient client = new FXBTCClient(username, password);

		// Ticker
		Ticker ticker = client.getTicker(Symbol.BTC_CNY);
		log.debug("Ticker: {}", ticker);

		// Depth
		Depth depth = client.getDepth(Symbol.BTC_CNY);
		log.debug("Depth: {}", depth);

		// Last trades
		List<Trade> lastTrades = client.getLastTrades(Symbol.BTC_CNY, 100);
		log.debug("Last trades: {}", lastTrades);

		// History trades
		List<Trade> historyTrades = client.getHistoryTrades(Symbol.BTC_CNY, 0);
		log.debug("History trades: {}", historyTrades);

		// Info
		Info info = client.getInfo();
		log.debug("Info: {}", info);

		// Orders
		List<Order> orders = client.getOrders(Symbol.BTC_CNY);
		for (Order order : orders) {
			log.debug("Order: {}", order);
		}

		TradeInfo tradeInfo = client.bid(Symbol.BTC_CNY, new BigDecimal("0.01"), new BigDecimal("0.001"));
		System.out.println(tradeInfo);
	}

}
