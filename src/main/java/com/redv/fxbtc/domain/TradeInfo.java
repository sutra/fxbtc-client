package com.redv.fxbtc.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeInfo extends AbstractObject {

	private static final long serialVersionUID = 2013122201L;

	@JsonProperty("traded_orders")
	private List<TradedOrder> tradedOrders;

	@JsonProperty("limit_orders")
	private List<LimitOrder> limitOrders;

	public List<TradedOrder> getTradedOrders() {
		return tradedOrders;
	}

	public List<LimitOrder> getLimitOrders() {
		return limitOrders;
	}

	public static class TradedOrder extends AbstractObject {

		/**
		 *
		 */
		private static final long serialVersionUID = 2013122201L;

		private BigDecimal rate;

		private BigDecimal vol;

		public BigDecimal getRate() {
			return rate;
		}

		public void setRate(BigDecimal rate) {
			this.rate = rate;
		}

		public BigDecimal getVol() {
			return vol;
		}

		public void setVol(BigDecimal vol) {
			this.vol = vol;
		}

	}

	public static class LimitOrder extends AbstractObject {

		private static final long serialVersionUID = 2013122201L;

		private Long id;

		private BigDecimal rate;

		private BigDecimal vol;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public BigDecimal getRate() {
			return rate;
		}

		public void setRate(BigDecimal rate) {
			this.rate = rate;
		}

		public BigDecimal getVol() {
			return vol;
		}

		public void setVol(BigDecimal vol) {
			this.vol = vol;
		}

	}

}
