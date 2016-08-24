package com.alex.utils;

import java.util.UUID;

import com.alex.business.wepay.utils.TenpayUtil;

/**
 * Author xiong
 * 
 * @author Administrator
 */
public class GlobelConstant {

	public static final String P_URL = "http://localhost:8080/p";

	public static final String port = "8080";

	/* token 失效时间 2 小时 */
	public static final Long addExpireTime = 2 * 60 * 60 * 1000L;

	// public static final String PAY_URL = "http://www.wetingche.cn/weipark/";
	public static final String PAY_URL = "http://www.aliyunparking.com/weipark/";

	// 支付结果：0-未支付；1-成功支付； 2-支付失败；
	public static final int PAY_NOTHING = 0;
	public static final int PAY_SUCCESS = 1;
	public static final int PAY_FAILED = 2;
	

	// 订单类型：0-停车缴费(提前自主停车缴费)；1-出场时的停车缴费；2-用户账户充值；3-月卡办理
	/**
	 * 0-停车缴费(提前自主停车缴费)
	 */
	public static final int ORDER_PARK_AUTO = 0;
	/**
	 * 1-出场时的停车缴费
	 */
	public static final int ORDER_PARK_OUT = 1;
	/**
	 * 2-用户账户充值
	 */
	public static final int ORDER_ACCOUNT = 2;
	/**
	 * 3-月卡办理
	 */
	public static final int ORDER_MONTH = 3;

	// 支付方式：0-未知方式，4-现金支付，1-月卡用户免费，2-用户账户缴费，3-微信支付缴费,5-提前交费且未超时
	/**
	 * 0-未知方式
	 */
	public static final int PAY_TYPE_NONE = 0;
	/**
	 * 1-月卡用户免费
	 */
	public static final int PAY_TYPE_MONTH_CARD = 1;
	/**
	 * 2-用户账户缴费
	 */
	public static final int PAY_TYPE_ACCOUNT = 2;
	/**
	 * 3-微信支付缴费
	 */
	public static final int PAY_TYPE_WXPAY = 3;
	/**
	 * 4-现金支付
	 */
	public static final int PAY_TYPE_CASH = 4;
	/**
	 * 5-提前交费且未超时
	 */
	public static final int PAY_TYPE_AUTO = 5;
	/**
	 * 6-免费通行车辆（军警车等）
	 */
	public static final int PAY_TYPE_FREE = 6;

	public static String getOrderNo() {
		return "HappyWater" + System.currentTimeMillis()
				+ TenpayUtil.buildRandom(6);
	}

	public static String createToken() {
		UUID token = UUID.randomUUID();
		return token.toString();
	}

}
