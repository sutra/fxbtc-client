package com.redv.fxbtc.domain.result;

import com.redv.fxbtc.domain.AbstractObject;

public abstract class Result extends AbstractObject {

	private static final long serialVersionUID = 2012122201L;

	private final boolean result;

	private final Error error;

	public Result(boolean result, Error error) {
		this.result = result;
		this.error = error;
	}

	public boolean isSuccess() {
		return result;
	}

	public Error getError() {
		return error;
	}

	public static class Error extends AbstractObject {

		private static final long serialVersionUID = 2013122201L;

		private final int code;

		private final String msg;

		public Error(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}

	}

}
