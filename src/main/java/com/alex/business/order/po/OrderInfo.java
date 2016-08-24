package com.alex.business.order.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_order_info")
public class OrderInfo implements Serializable {


	public OrderInfo(){
		this.payResult=0;
		this.id=0;
	}
	
	@Id
	@Column(name = "id")
	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "openID")
	private String openID;  

	@Column(name = "orderFee")
	private String orderFee;  
	
	@Column(name = "orderNo")
	private String orderNo;  

	@Column(name = "orderType")
	private Integer orderType; 
	
	@Column(name = "startTime")
	private Date startTime; 
	
	@Column(name = "endTime")
	private Date endTime; 
	
	@Column(name = "orderDesc")
	private String orderDesc;
	
	@Column(name = "createTime")
	private Date createTime; 
	
	@Column(name = "payResult")
	private Integer payResult;  
	
	@Column(name = "errorMsg")
	private String errorMsg;  
	
	@Column(name = "payBank")
	private String payBank;  
	
	/**
	 * 微信的支付订单号
	 */
	@Column(name = "wxOrderID")
	private String wxOrderID;
	
	/**
	 * 订单消费所在的停车场ID
	 */
	@Column(name = "parkId")
	private int parkId;
	
	/**
	 * 支付类型：0-现金支付，1-微信支付，2-用户账户支付，3-月卡用户支付
	 */
	@Column(name = "payType")
	private int payType;
	
	@Column(name = "carCode")
	private String carCode;
	
	@Column(name = "carType")
	private String carType;
	
	@Column(name = "isOutOrder")
	private int isOutOrder;
	
	@Column(name = "payParam")
	private String payParam;
	
	public int getIsOutOrder() {
		return isOutOrder;
	}

	public void setIsOutOrder(int isOutOrder) {
		this.isOutOrder = isOutOrder;
	}

	public String getPayParam() {
		return payParam;
	}

	public void setPayParam(String payParam) {
		this.payParam = payParam;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	/**
	 * 停车场记录ID
	 */
	@Column(name = "stopRecordId")
	private int stopRecordId;
	
	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public int getStopRecordId() {
		return stopRecordId;
	}

	public void setStopRecordId(int stopRecordId) {
		this.stopRecordId = stopRecordId;
	}

	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(String orderFee) {
		this.orderFee = orderFee;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Integer getPayResult() {
		return payResult;
	}

	public void setPayResult(Integer payResult) {
		this.payResult = payResult;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getWxOrderID() {
		return wxOrderID;
	}

	public void setWxOrderID(String wxOrderID) {
		this.wxOrderID = wxOrderID;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}  
	
	
	
	
	
	
	
 
	
}
