package com.redv.fxbtc.domain.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.domain.Symbol;
import com.redv.fxbtc.domain.Trade;
import com.redv.fxbtc.domain.Type;
import com.redv.fxbtc.domain.result.TradesResult;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class TradesResultTest {

	private final Logger log = LoggerFactory.getLogger(TradesResultTest.class);

	@Test
	public void testLastTrades() throws IOException {
		JsonValueReader<TradesResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), TradesResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"lastTrades.json")) {
			TradesResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
			List<Trade> trades = result.getTrades();
			for (Trade trade : trades) {
				log.debug("{}", trade);
			}

			Trade trade = trades.get(0);
			assertEquals(1387612146000L, trade.getDate().getTime());
			assertEquals(new BigDecimal("3668.62"), trade.getRate());
			assertEquals(new BigDecimal("0.3787"), trade.getVol());
			assertEquals("2", trade.getOrder());
			assertEquals(Type.ASK, trade.getType());
			assertEquals("662170", trade.getTicket());

			assertEquals(Symbol.BTC_CNY, result.getParams().getSymbol());
		}
	}

	@Test
	public void testHistoryTrades() throws IOException {
		JsonValueReader<TradesResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), TradesResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"historyTrades.json")) {
			TradesResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
			List<Trade> trades = result.getTrades();
			for (Trade trade : trades) {
				log.debug("{}", trade);
			}

			Trade trade = trades.get(0);
			assertEquals("1357574517000000", trade.getTid());
			assertEquals(1357574517000L, trade.getDate().getTime());
			assertEquals("ask", trade.getOrder());
			assertEquals(new BigDecimal("80.0000"), trade.getRate());
			assertEquals(new BigDecimal("0.09980000"), trade.getVol());

			assertEquals(Symbol.BTC_CNY, result.getParams().getSymbol());
		}
	}
}
