package com.alex.business.wepay.vo;

import org.springframework.jmx.export.naming.SelfNaming;

public class PayResult {
	//结果类型：0-需通过微信支付缴费，获取payURL返回给用户，1-缴费成功，2-支付失败
	private int resultType;
	//支付业务类型：1-月卡用户免费，2-用户账户缴费，3-微信支付缴费
	private int payType;
	//微信支付链接
	private String payUrl;
	//结果信息
	private String resultInfo;
	
	private String payFee;
	
	private String openId;
	
	private int orderInfoId;
	
	public PayResult() {
		this.payFee="0";
		this.resultType=0;
		this.orderInfoId=0;
		this.payFee="0";
		this.payUrl="";
		}
	
	public int getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(int orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public String getPayFee() {
		return payFee;
	}
	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getResultType() {
		return resultType;
	}
	public void setResultType(int resultType) {
		this.resultType = resultType;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public String getResultInfo() {
		return resultInfo;
	}
	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	
}
