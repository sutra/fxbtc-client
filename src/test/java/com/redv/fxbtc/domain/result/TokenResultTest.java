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

public class TokenResultTest {

	private final Logger log = LoggerFactory.getLogger(TokenResultTest.class);

	@Test
	public void test() throws IOException {
		JsonValueReader<TokenResult> jsonValueReader = new JsonValueReader<>(
				new ObjectMapper(), TokenResult.class);
		try (InputStream inputStream = getClass().getResourceAsStream(
				"token.json")) {
			TokenResult result = jsonValueReader.read(inputStream);
			log.debug("{}", result);
			assertTrue(result.isSuccess());
			assertEquals("PU8JXNRHC452L8NBZ67BSLE9WK2B8H69JABRDBWR98SRHYA457J5GUGWNJXT5D9V",
					result.getToken().getToken());
		}

	}

}
