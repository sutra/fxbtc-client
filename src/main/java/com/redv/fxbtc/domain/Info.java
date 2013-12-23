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

	public void setFunds(Funds funds) {
		this.funds = funds;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static class Funds extends AbstractObject {

		private static final long serialVersionUID = 2013122201L;

		private Fund free;

		private Fund locked;

		public Fund getFree() {
			return free;
		}

		public void setFree(Fund free) {
			this.free = free;
		}

		public Fund getLocked() {
			return locked;
		}

		public void setLocked(Fund locked) {
			this.locked = locked;
		}

		public static class Fund extends AbstractObject {

			private static final long serialVersionUID = 2013122201L;

			private BigDecimal cny;

			private BigDecimal btc;

			private BigDecimal ltc;

			public BigDecimal getCny() {
				return cny;
			}

			public void setCny(BigDecimal cny) {
				this.cny = cny;
			}

			public BigDecimal getBtc() {
				return btc;
			}

			public void setBtc(BigDecimal btc) {
				this.btc = btc;
			}

			public BigDecimal getLtc() {
				return ltc;
			}

			public void setLtc(BigDecimal ltc) {
				this.ltc = ltc;
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
