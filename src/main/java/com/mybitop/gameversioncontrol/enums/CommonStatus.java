package com.mybitop.gameversioncontrol.enums;

public class CommonStatus {
	
	/*
	 * 系统级别   0-2000000 可自定义
	 * */
	/**成功**/
	public static final String SUCCESS_CODE = "000000";
	public static final String SUCCESS_MESSAGE = "执行成功";
	
	/**执行失败**/
	public static final String EXIT_FAILURE_CODE = "000004";
	public static final String EXIT_FAILURE_MESSAGE = "执行失败";
	
	/**执行失败**/
	public static final String PARAM_RORROR_CODE = "000005";
	public static final String PARAM_RORROR_MSG = "参数错误";
	
	/**非法字符**/
	public static final String  ILLRGAL_CHAR_CODE = "000006";
	public static final String ILLRGAL_CHAR_MESSAGE = "非法字符";
	
	
	/*
	 * 用户业务级别  2000001-4000000 可自定义
	 * */
	/**缺少参数**/
	public static final String LOSEPARAM_EROR_CODE = "200001";
	public static final String LOSEPARAM_EROR_MESSAGE = "缺少参数";

	/*验证码失败*/
	public static final String CODE_EROR_CODE = "200007";
	public static final String CODE_EROR_MESSAGE = "验证码错误";

	/**userTicket 未找到相应结果**/
	public static final String  SUCCESS_RESULT_CODE = "0001";
	public static final String  SUCCESS_RESULT_MESSAGE = "验证成功";
	
	/**userTicket 系统错误**/
	public static final String  SYS_ERROR_CODE = "0003";
	public static final String  SYS_ERROR_MESSAGE = "系统错误";
	
	
	/**userTicket 非法请求**/
	public static final String  ILLEGAL_REQUEST_CODE = "0004";
	public static final String  ILLEGAL_REQUEST_MESSAGE = "非法请求";
	

	

};


