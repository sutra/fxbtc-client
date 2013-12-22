package com.redv.fxbtc.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Depth extends AbstractObject {

	private static final long serialVersionUID = 2013122101L;

	private List<Data> asks;

	private List<Data> bids;

	public Depth(@JsonProperty("asks") List<Data> asks,
			@JsonProperty("bids") List<Data> bids) {
		this.asks = asks;
		this.bids = bids;
	}

	/**
	 * @return the asks
	 */
	public List<Data> getAsks() {
		return asks;
	}


	/**
	 * @return the bids
	 */
	public List<Data> getBids() {
		return bids;
	}

	public static class Data extends AbstractObject implements Comparable<Data> {

		private static final long serialVersionUID = 2013122101L;

		private int count;

		private BigDecimal rate;

		private BigDecimal vol;

		public Data(@JsonProperty("count") int count,
				@JsonProperty("rate") BigDecimal rate,
				@JsonProperty("vol") BigDecimal vol) {
			this.count = count;
			this.rate = rate;
			this.vol = vol;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @return the rate
		 */
		public BigDecimal getRate() {
			return rate;
		}

		/**
		 * @return the vol
		 */
		public BigDecimal getVol() {
			return vol;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compareTo(Data o) {
			return rate.compareTo(o.rate);
		}

	}

}
