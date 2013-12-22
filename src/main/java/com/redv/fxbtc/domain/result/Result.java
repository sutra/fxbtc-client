package com.redv.fxbtc.domain.result;

import com.redv.fxbtc.domain.AbstractObject;

public abstract class Result extends AbstractObject {

	private static final long serialVersionUID = 2012122201L;

	private boolean result;

	private Error error;

	public Result(boolean result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return result;
	}

	public Error getError() {
		return error;
	}

	public static class Error extends AbstractObject {

		private static final long serialVersionUID = 2013122201L;

		private int code;

		private String msg;

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

	}

}
