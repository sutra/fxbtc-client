package com.redv.fxbtc.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.domain.result.TradeInfoResult;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class TradeInfoResultTest {

	private final Logger log = LoggerFactory.getLogger(TradeInfoResultTest.class);

	@Test
	public void testDepth() throws IOException {
		JsonValueReader<TradeInfoResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), TradeInfoResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"trade.json")) {
			TradeInfoResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertEquals(Symbol.BTC_CNY, result.getSymbol());
			assertEquals(Type.BID, result.getType());
			TradeInfo.TradedOrder to = result.getTradeInfo().getTradedOrders().get(0);
			assertEquals(new BigDecimal(100), to.getRate());
			assertEquals(new BigDecimal(5), to.getVol());
			TradeInfo.LimitOrder lo = result.getTradeInfo().getLimitOrders().get(0);
			assertEquals(1357571743002L, lo.getId().longValue());
			assertEquals(new BigDecimal(101), lo.getRate());
			assertEquals(new BigDecimal("6.66"), lo.getVol());
		}

	}

}
