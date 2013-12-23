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
import com.redv.fxbtc.domain.Order;
import com.redv.fxbtc.domain.Symbol;
import com.redv.fxbtc.domain.Type;
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
			assertEquals(Symbol.BTC_CNY, result.getSymbol());
			List<Order> orders = result.getOrders();
			assertEquals(2, orders.size());

			Order order0 = orders.get(0);
			assertEquals(1357571743002L, order0.getId().longValue());
			assertEquals(Type.BID, order0.getType());
			assertEquals(new BigDecimal("80"), order0.getRate());
			assertEquals(new BigDecimal("0.30339321"), order0.getVol());
			assertEquals(1357574443L * 1000L, order0.getDate().getTime());

			Order order1 = orders.get(1);
			assertEquals(1357571743004L, order1.getId().longValue());
			assertEquals(Type.ASK, order1.getType());
			assertEquals(new BigDecimal(9999), order1.getRate());
			assertEquals(new BigDecimal("1"), order1.getVol());
			assertEquals(1357574556L * 1000L, order1.getDate().getTime());
		}

	}

}
