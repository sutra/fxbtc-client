package com.redv.fxbtc.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order extends AbstractObject {

	private static final long serialVersionUID = 2013122201L;

	private final Long id;

	private final Type type;

	private final BigDecimal rate;

	private final BigDecimal vol;

	private final Date date;

	public Order(@JsonProperty("id") Long id,
			@JsonProperty("type") String type,
			@JsonProperty("rate") BigDecimal rate,
			@JsonProperty("vol") BigDecimal vol, @JsonProperty("date") long date) {
		this.id = id;
		this.type = Type.valueOfType(type);
		this.rate = rate;
		this.vol = vol;
		this.date = new Date(date * 1000);
	}

	public Long getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getVol() {
		return vol;
	}

	public Date getDate() {
		return date;
	}

}
