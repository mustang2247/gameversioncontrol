package com.mybitop.gameversioncontrol.enums;

import com.alibaba.fastjson.annotation.JSONField;

//wangfandebaopi
public class ResultObject {
	private String code;
	
	private Object data;
	 
	private String message;
	/**
	 * @param code
	 * @param data
	 * @param message
	 */
	public ResultObject(String code, Object data, String message) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}
	/**
	 * 
	 */
	public ResultObject() {
		super();
		code = "000000";
		data = "";
		message = "执行成功";
	} 
	
	
	public static ResultObject getSuccess() {
		return new ResultObject();
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
//	public Object getNum() {
//		return num;
//	}
//	/**
//	 * @param
//	 */
//	public void setNum(Object num) {
//		this.num = num;
//	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
