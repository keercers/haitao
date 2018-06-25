package com.thinvent.zhhd.common.exception;

/**
 * 思创华信基础异常定义
 * */
public class ThinventBaseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String msg;
	
	public ThinventBaseException(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
