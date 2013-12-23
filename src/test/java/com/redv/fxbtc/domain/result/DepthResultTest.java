package com.redv.fxbtc.domain.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.domain.Depth.Data;
import com.redv.fxbtc.domain.Symbol;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class DepthResultTest {

	private final Logger log = LoggerFactory.getLogger(DepthResultTest.class);

	@Test
	public void testDepth() throws IOException {
		JsonValueReader<DepthResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), DepthResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"depth.json")) {
			DepthResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
			List<Data> asks = result.getDepth().getAsks();
			for (Data ask : asks) {
				log.debug("Ask: {}", ask);
			}
			List<Data> bids = result.getDepth().getBids();
			for (Data bid : bids) {
				log.debug("Bid: {}", bid);
			}
			assertEquals(Symbol.BTC_CNY, result.getSymbol());
			assertEquals(Symbol.BTC_CNY, result.getParams().getSymbol());
		}

	}

}
