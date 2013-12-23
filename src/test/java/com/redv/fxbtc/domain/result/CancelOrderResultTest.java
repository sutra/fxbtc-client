package com.redv.fxbtc.domain.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class CancelOrderResultTest {

	private final Logger log = LoggerFactory.getLogger(CancelOrderResultTest.class);

	@Test
	public void test() throws IOException {
		JsonValueReader<CancelOrderResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), CancelOrderResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"cancelOrder.json")) {
			CancelOrderResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
			assertEquals(1357571743002L, result.getId().longValue());
		}

	}

}
