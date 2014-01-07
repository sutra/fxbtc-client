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
import com.redv.fxbtc.domain.result.Result;
import com.redv.fxbtc.domain.result.Result.Error;
import com.redv.fxbtc.domain.result.TickerResult;
import com.redv.fxbtc.domain.result.TokenResult;
import com.redv.fxbtc.domain.result.TradeInfoResult;
import com.redv.fxbtc.domain.result.TradesResult;

public class FXBTCClient {

	public static final String ENCODING = "UTF-8";

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
		return callMarket(TickerResult.class, "query_ticker", symbol).getTicker();
	}

	public Depth getDepth(Symbol symbol) throws IOException {
		return callMarket(DepthResult.class, "query_depth", symbol).getDepth();
	}

	public List<Trade> getLastTrades(Symbol symbol, int count)
			throws IOException {
		return callMarket(TradesResult.class, "query_last_trades", symbol,
				new BasicNameValuePair("count", String.valueOf(count)))
			.getTrades();
	}

	public List<Trade> getHistoryTrades(Symbol symbol, long since)
			throws IOException {
		return callMarket(TradesResult.class, "query_history_trades", symbol,
				new BasicNameValuePair("since", String.valueOf(since)))
			.getTrades();
	}

	public Token getToken() throws IOException {
		List<NameValuePair> list = new ArrayList<>(3);
		list.add(new BasicNameValuePair("op", "get_token"));
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("password", password));
		return post(TRADE_API, TokenResult.class, list).getToken();
	}

	public boolean checkToken(String token) throws IOException {
		List<NameValuePair> list = new ArrayList<>(2);
		list.add(new BasicNameValuePair("token", token));
		list.add(new BasicNameValuePair("op", "check_token"));
		return httpClient.post(TRADE_API, CheckTokenResult.class, list).isValid();
	}

	public Info getInfo() throws IOException {
		return callTrade(InfoResult.class, "get_info").getInfo();
	}

	public List<Order> getOrders(Symbol symbol) throws IOException {
		return callTrade(OrdersResult.class, "get_orders", symbol).getOrders();
	}

	public Long cancelOrder(Symbol symbol, long id) throws IOException {
		return callTrade(CancelOrderResult.class, "cancel_order", symbol,
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
		return callTrade(TradeInfoResult.class, "trade", symbol,
				new BasicNameValuePair("type", type.getTradeType()),
				new BasicNameValuePair("rate", rate.toString()),
				new BasicNameValuePair("vol", vol.toString())).getTradeInfo();
	}

	private Token getFreshToken() throws IOException {
		refreshToken(false);

		return token;
	}

	private void refreshToken(boolean force) throws IOException {
		if (force || token == null || token.isTimeoutAfter(5)) {
			token = getToken();
			log.debug("Token: {}", token);
		}
	}

	private <T extends Result> T callMarket(
			Class<T> resultType,
			String op,
			Symbol symbol,
			NameValuePair... params) throws IOException {
		final URIBuilder builder = new URIBuilder(MARKET_API)
			.setParameter("op", op)
			.setParameter("symbol", symbol.toString());
		for (NameValuePair param : params) {
			builder.addParameter(param.getName(), param.getValue());
		}
		final URI uri;
		try {
			uri = builder.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
		return get(uri, resultType);
	}

	private <T extends Result> T callTrade(
			Class<T> valueType,
			String op,
			Symbol symbol,
			NameValuePair... params) throws IOException {
		NameValuePair sp = new BasicNameValuePair("symbol", symbol.toString());
		return callTrade(valueType, op, ArrayUtils.add(params, sp));
	}

	/**
	 * Call trade API, retry once if got token timeout exception.
	 *
	 * @param valueType the return value type.
	 * @param op the operation.
	 * @param params the parameters
	 * @return the response from trade API.
	 * @throws IOException indicates I/O exception.
	 */
	private <T extends Result> T callTrade(
			Class<T> valueType,
			String op,
			NameValuePair... params) throws IOException {
		try {
			return callTradeHelper(valueType, op, params);
		} catch (TokenTimeoutException e) {
			refreshToken(true);

			return callTradeHelper(valueType, op, params);
		}
	}

	private <T extends Result> T callTradeHelper(
			Class<T> valueType,
			String op,
			NameValuePair... params) throws IOException {
		List<NameValuePair> list = new ArrayList<>(params.length + 2);

		list.add(new BasicNameValuePair("token", getFreshToken().getToken()));
		list.add(new BasicNameValuePair("op", op));

		Collections.addAll(list, params);

		T value = post(TRADE_API, valueType, list);

		log.debug("Trade post result: {}", value);
		return value;
	}

	private <T extends Result> T get(URI uri, Class<T> valueType)
			throws FXBTCClientException, IOException {
		T result = httpClient.get(uri, valueType);
		if (result.isSuccess()) {
			return result;
		} else {
			throw convertException(result.getError());
		}
	}

	private <T extends Result> T post(URI uri, Class<T> valueType,
			List<NameValuePair> params) throws FXBTCClientException,
			IOException {
		T result = httpClient.post(uri, valueType, params);
		if (result.isSuccess()) {
			return result;
		} else {
			log.debug("result: {}", result);
			throw convertException(result.getError());
		}
	}

	private FXBTCClientException convertException(Error error) {
		final FXBTCClientException e;
		if (error.getCode() == -202) {
			e = new TokenTimeoutException(error);
		} else {
			e = new FXBTCClientException(error);
		}
		return e;
	}

}
