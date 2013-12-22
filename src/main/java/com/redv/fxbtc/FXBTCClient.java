package com.redv.fxbtc;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redv.fxbtc.domain.Depth;
import com.redv.fxbtc.domain.Info;
import com.redv.fxbtc.domain.Order;
import com.redv.fxbtc.domain.Symbol;
import com.redv.fxbtc.domain.Ticker;
import com.redv.fxbtc.domain.Token;
import com.redv.fxbtc.domain.Trade;
import com.redv.fxbtc.domain.TradeInfo;
import com.redv.fxbtc.domain.Type;
import com.redv.fxbtc.domain.result.CancelOrderResult;
import com.redv.fxbtc.domain.result.CheckTokenResult;
import com.redv.fxbtc.domain.result.DepthResult;
import com.redv.fxbtc.domain.result.InfoResult;
import com.redv.fxbtc.domain.result.OrdersResult;
import com.redv.fxbtc.domain.result.TickerResult;
import com.redv.fxbtc.domain.result.TokenResult;
import com.redv.fxbtc.domain.result.TradeInfoResult;
import com.redv.fxbtc.domain.result.TradesResult;

public class FXBTCClient {

	private static final URI MARKET_API = URI.create("https://data.fxbtc.com/api");
	private static final URI TRADE_API = URI.create("https://trade.fxbtc.com/api");

	private final Logger log = LoggerFactory.getLogger(FXBTCClient.class);

	private final HttpClient httpClient;

	private String username;
	private String password;

	private volatile Token token;

	public FXBTCClient() {
		httpClient = new HttpClient();
	}

	public FXBTCClient(String username, String password) {
		this();

		this.username = username;
		this.password = password;
	}

	public Ticker getTicker(Symbol symbol) throws IOException {
		URI uri = buildMarketURI("query_ticker", symbol);
		return httpClient.get(uri, TickerResult.class).getTicker();
	}

	public Depth getDepth(Symbol symbol) throws IOException {
		URI uri = buildMarketURI("query_depth", symbol);
		return httpClient.get(uri, DepthResult.class).getDepth();
	}

	public List<Trade> getLastTrades(Symbol symbol, int count)
			throws IOException {
		URI uri = buildMarketURI("query_last_trades", symbol,
				new BasicNameValuePair("count", Integer.toString(count)));
		return httpClient.get(uri, TradesResult.class).getTrades();
	}

	public List<Trade> getHistoryTrades(Symbol symbol, long since)
			throws IOException {
		URI uri = buildMarketURI("query_history_trades", symbol,
				new BasicNameValuePair("since", Long.toString(since)));
		return httpClient.get(uri, TradesResult.class).getTrades();
	}

	public Token getToken() throws IOException {
		List<NameValuePair> list = new ArrayList<>(3);
		list.add(new BasicNameValuePair("op", "get_token"));
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("password", password));
		return httpClient.post(TRADE_API, TokenResult.class, list).getToken();
	}

	public boolean checkToken(String token) throws IOException {
		List<NameValuePair> list = new ArrayList<>(2);
		list.add(new BasicNameValuePair("token", token));
		list.add(new BasicNameValuePair("op", "check_token"));
		return httpClient.post(TRADE_API, CheckTokenResult.class, list).isValid();
	}

	public Info getInfo() throws IOException {
		return tradePost(InfoResult.class, "get_info").getInfo();
	}

	public List<Order> getOrders(Symbol symbol) throws IOException {
		return tradePost(OrdersResult.class, "get_orders", symbol).getOrders();
	}

	public Long cancelOrder(Symbol symbol, long id) throws IOException {
		return tradePost(CancelOrderResult.class, "cancel_order", symbol,
				new BasicNameValuePair("id", Long.toString(id))).getId();
	}

	public TradeInfo bid(Symbol symbol, BigDecimal rate, BigDecimal vol)
			throws IOException {
		return trade(symbol, Type.BID, rate, vol);
	}

	public TradeInfo ask(Symbol symbol, BigDecimal rate, BigDecimal vol)
			throws IOException {
		return trade(symbol, Type.ASK, rate, vol);
	}

	public TradeInfo trade(Symbol symbol, Type type, BigDecimal rate,
			BigDecimal vol) throws IOException {
		return tradePost(TradeInfoResult.class, "trade", symbol,
				new BasicNameValuePair("type", type.getTradeType()),
				new BasicNameValuePair("rate", rate.toString()),
				new BasicNameValuePair("vol", vol.toString())).getTradeInfo();
	}

	private URI buildMarketURI(String op, Symbol symbol,
			NameValuePair... params) {
		List<NameValuePair> list = new ArrayList<>(params.length + 2);
		list.add(new BasicNameValuePair("op", op));
		list.add(new BasicNameValuePair("symbol", symbol.toString()));
		Collections.addAll(list, params);
		return buildURI(MARKET_API, list);
	}

	private URI buildURI(URI uri, List<NameValuePair> params) {
		NameValuePair[] array = new NameValuePair[params.size()];
		params.toArray(array);
		return buildURI(uri, array);
	}

	private URI buildURI(URI uri, NameValuePair... params) {
		URIBuilder builder = new URIBuilder(uri);
		for (NameValuePair param : params) {
			builder.addParameter(param.getName(), param.getValue());
		}
		try {
			return builder.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private <T> T tradePost(Class<T> valueType, String op,
			Symbol symbol, NameValuePair... params) throws IOException {
		NameValuePair symbolParam = new BasicNameValuePair("symbol", symbol.toString());
		return tradePost(valueType, op, ArrayUtils.add(params, symbolParam));
	}

	private <T> T tradePost(Class<T> valueType, String op,
			NameValuePair... params) throws IOException {
		refreshToken();

		List<NameValuePair> list = new ArrayList<>(params.length + 2);
		list.add(new BasicNameValuePair("token", token.getToken()));
		list.add(new BasicNameValuePair("op", op));
		Collections.addAll(list, params);
		return httpClient.post(TRADE_API, valueType, list);
	}

	private void refreshToken() throws IOException {
		if (token == null || token.isTimeout()) {
			token = getToken();
			log.debug("Token: {}", token);
		}
	}

}
