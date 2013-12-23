package com.redv.fxbtc.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != getClass()) {
				return false;
			}
			Data rhs = (Data) obj;
			return new EqualsBuilder()
				.append(rate, rhs.rate)
				.isEquals();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			return new HashCodeBuilder(17, 37)
				.append(rate)
				.toHashCode();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compareTo(Data o) {
			return new CompareToBuilder()
				.append(rate, o.rate)
				.toComparison();
		}

	}

}
