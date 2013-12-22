package com.redv.fxbtc.domain.result;

import java.util.Calendar;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redv.fxbtc.domain.Token;

public class TokenResult extends DataResult<String> {

	private static final long serialVersionUID = 2013122101L;

	private Token token;

	public TokenResult(@JsonProperty("result") boolean result,
			@JsonProperty("token") String data,
			@JsonProperty("timeout") long timeout) {
		super(result, data);

		Calendar cal = Calendar.getInstance(new Locale("UTC"));
		cal.setTimeInMillis(timeout * 1000);

		token = new Token(data, cal.getTime());
	}

	public Token getToken() {
		return token;
	}

}
