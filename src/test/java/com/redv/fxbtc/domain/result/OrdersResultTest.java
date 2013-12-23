package com.redv.fxbtc.domain.result;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class OrdersResultTest {

	private final Logger log = LoggerFactory.getLogger(OrdersResultTest.class);

	@Test
	public void test() throws IOException {
		JsonValueReader<OrdersResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), OrdersResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"orders.json")) {
			OrdersResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
		}

	}

}
