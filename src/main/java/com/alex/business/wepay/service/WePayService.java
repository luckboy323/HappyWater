package com.alex.business.wepay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.business.order.po.OrderInfo;
import com.alex.business.wepay.vo.PayResult;


public interface WePayService {

	public PayResult getWeChatPay(OrderInfo orderInfo,HttpServletRequest request,
			HttpServletResponse response);
}
