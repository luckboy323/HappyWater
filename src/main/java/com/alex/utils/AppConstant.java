package com.alex.utils;

/**
 * 系统常量
 * @author hadoop
 *
 */
public class AppConstant {
	public final static String LOGINED="userInfo";
	public final static String BASEPATH="";
	public static String operateContent(String uri){
		String logContent="您当前访问："+uri;
		return logContent;
	}
}
