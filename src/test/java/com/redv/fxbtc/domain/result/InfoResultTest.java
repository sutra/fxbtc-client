package com.redv.fxbtc.domain.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redv.fxbtc.domain.Info;
import com.redv.fxbtc.domain.Info.Funds.Fund;
import com.redv.fxbtc.domain.Info.Status;
import com.redv.fxbtc.valuereader.JsonValueReader;

public class InfoResultTest {

	private final Logger log = LoggerFactory.getLogger(InfoResultTest.class);

	@Test
	public void test() throws IOException {
		JsonValueReader<InfoResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), InfoResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"info.json")) {
			InfoResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());

			Info info = result.getInfo();

			Fund free = info.getFunds().getFree();
			assertEquals(new BigDecimal("166.14273500"), free.getCny());
			assertEquals(new BigDecimal("5.45168404"), free.getBtc());
			assertEquals(new BigDecimal("23.74322960"), free.getLtc());

			Fund locked = info.getFunds().getLocked();
			assertEquals(new BigDecimal("500.83818499"), locked.getCny());
			assertEquals(new BigDecimal("1.22457200"), locked.getBtc());
			assertEquals(new BigDecimal("11.85418799"), locked.getLtc());

			Status status = info.getStatus();
			assertTrue(status.isTrade());
			log.debug("Server time: {}", status.getServerTime().getTime());
			assertEquals(1371549835L * 1000L, status.getServerTime().getTime());
		}

	}

}
