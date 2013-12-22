package com.redv.fxbtc.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info extends AbstractObject {

	private static final long serialVersionUID = 2013122201L;

	private Funds funds;

	private Status status;

	public Funds getFunds() {
		return funds;
	}

	public Status getStatus() {
		return status;
	}

	public static class Funds extends AbstractObject {

		private static final long serialVersionUID = 2013122201L;

		private Fund free;

		private Fund locked;

		public Fund getFree() {
			return free;
		}

		public Fund getLocked() {
			return locked;
		}

		public static class Fund extends AbstractObject {

			private static final long serialVersionUID = 2013122201L;

			private BigDecimal cny;

			private BigDecimal btc;

			private BigDecimal ltc;

			public BigDecimal getCny() {
				return cny;
			}

			public BigDecimal getBtc() {
				return btc;
			}

			public BigDecimal getLtc() {
				return ltc;
			}

		}

	}

	public static class Status extends AbstractObject {

		private static final long serialVersionUID = 2013122201L;

		private boolean trade;

		private Date serverTime;

		public Status(@JsonProperty("trade") boolean trade,
				@JsonProperty("server_time") long serverTime) {
			this.trade = trade;

			Calendar cal = Calendar.getInstance(new Locale("UTC"));
			cal.setTimeInMillis(serverTime * 1000);
			this.serverTime = cal.getTime();
		}

		public boolean isTrade() {
			return trade;
		}

		public Date getServerTime() {
			return serverTime;
		}

	}
}
