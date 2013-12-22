package com.redv.fxbtc.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.domain.result.TickerResult;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class TickerResultTest {

	private final Logger log = LoggerFactory.getLogger(TickerResultTest.class);

	@Test
	public void testTicker() throws IOException {
		JsonValueReader<TickerResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), TickerResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"ticker.json")) {
			TickerResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
			assertEquals(new BigDecimal("4233.66"), result.getTicker().getHigh());
			assertEquals(new BigDecimal("3599.99"), result.getTicker().getLow());
			assertEquals(new BigDecimal("25226.09583433"), result.getTicker().getVol());
			assertEquals(new BigDecimal("3655.94"), result.getTicker().getLastRate());
			assertEquals(new BigDecimal("3655.94"), result.getTicker().getAsk());
			assertEquals(new BigDecimal("3639.04"), result.getTicker().getBid());
			assertEquals(Symbol.BTC_CNY, result.getParams().getSymbol());
		}
	}

}
