package com.redv.fxbtc.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Trade extends AbstractObject {

	private static final long serialVersionUID = 2013122101L;

	private String tid;

	private Date date;

	private BigDecimal rate;

	private BigDecimal vol;

	private String order;

	private Type type;

	private String ticket;

	public String getTid() {
		return tid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = new Date(date * 1000);
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getVol() {
		return vol;
	}

	public String getOrder() {
		return order;
	}

	public Type getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Type.valueOfType(type);
	}

	public String getTicket() {
		return ticket;
	}

}
