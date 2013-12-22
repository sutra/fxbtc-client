package com.redv.fxbtc.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticker extends AbstractObject {

	private static final long serialVersionUID = 2013122101L;

	private BigDecimal high;

	private BigDecimal low;

	private BigDecimal vol;

	private BigDecimal lastRate;

	private BigDecimal ask;

	private BigDecimal bid;

	public Ticker(@JsonProperty("high") BigDecimal high,
			@JsonProperty("low") BigDecimal low,
			@JsonProperty("vol") BigDecimal vol,
			@JsonProperty("last_rate") BigDecimal lastRate,
			@JsonProperty("ask") BigDecimal ask,
			@JsonProperty("bid") BigDecimal bid) {
		this.high = high;
		this.low = low;
		this.vol = vol;
		this.lastRate = lastRate;
		this.ask = ask;
		this.bid = bid;
	}

	/**
	 * @return the high
	 */
	public BigDecimal getHigh() {
		return high;
	}

	/**
	 * @return the low
	 */
	public BigDecimal getLow() {
		return low;
	}

	/**
	 * @return the vol
	 */
	public BigDecimal getVol() {
		return vol;
	}

	/**
	 * @return the lastRate
	 */
	public BigDecimal getLastRate() {
		return lastRate;
	}

	/**
	 * @return the ask
	 */
	public BigDecimal getAsk() {
		return ask;
	}

	/**
	 * @return the bid
	 */
	public BigDecimal getBid() {
		return bid;
	}

}
