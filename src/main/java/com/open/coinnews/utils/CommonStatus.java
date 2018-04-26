package com.open.coinnews.utils;

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

	public static final String  USER_CHAR_CODE = "000007";
	public static final String  USER_CHAR_MESSAGE = "不存在或已停用";

	public static final String  USER_PSD_CODE = "000008";
	public static final String  USER_PSD_MESSAGE = "密码输入不正确";

	public static final String  LOGIN_IS_CODE = "000009";
	public static final String  LOGIN_IS_MESSAGE = "登录成功";

	public static final String  LOGIN_FLASE_CODE = "000010";
	public static final String  LOGIN_FLASE_MESSAGE = "登录异常";

	public static final String  USER_ISL_CODE = "000011";
	public static final String  USER_ISL_MESSAGE = "已登录";

	public static final String  USER_OVER_CODE = "000012";
	public static final String  USER_OVER_MESSAGE = "登录失效";
	/*
	 * 用户业务级别  2000001-4000000 可自定义
	 * */
	/**缺少参数**/
	public static final String LOSEPARAM_EROR_CODE = "200001";
	public static final String LOSEPARAM_EROR_MESSAGE = "缺少参数";

	public static final String VersionIs_EROR_CODE = "200002";
	public static final String VersionIs_EROR_MESSAGE = "版本已存在";
	
	/**userTicket 系统错误**/
	public static final String  SYS_ERROR_CODE = "0003";
	public static final String  SYS_ERROR_MESSAGE = "系统错误";
	
	
	/**userTicket 非法请求**/
	public static final String  ILLEGAL_REQUEST_CODE = "0004";
	public static final String  ILLEGAL_REQUEST_MESSAGE = "非法请求";
	


	

};


