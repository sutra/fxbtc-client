package com.redv.fxbtc.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TokenTest {

	@Test
	public void testIsTimeout() throws InterruptedException {
		long now = System.currentTimeMillis();
		Token token = new Token("hello", new Date(now));
		assertFalse(token.isTimeout(now - 1));
		assertTrue(token.isTimeout(now + 1));
	}

}
