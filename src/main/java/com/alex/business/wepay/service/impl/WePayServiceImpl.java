package com.alex.business.wepay.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alex.business.order.po.OrderInfo;
import com.alex.business.wepay.service.WePayService;
import com.alex.business.wepay.utils.GetWxOrderno;
import com.alex.business.wepay.utils.RequestHandler;
import com.alex.business.wepay.utils.Sha1Util;
import com.alex.business.wepay.utils.TenpayUtil;
import com.alex.business.wepay.utils.WxPayConfigure;
import com.alex.business.wepay.vo.PayResult;
import com.alex.utils.GlobelConstant;
import com.alibaba.fastjson.JSONObject;

public class WePayServiceImpl implements WePayService {
	private final Logger logger = Logger.getLogger(WePayServiceImpl.class);
	
	public PayResult getWeChatPay(OrderInfo orderInfo,
			HttpServletRequest request, HttpServletResponse response) {
		
		PayResult payResult = new PayResult();
		/**
		 * 3、 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		 */
		String appid = WxPayConfigure.getAppID();
		String appsecret = WxPayConfigure.getAppSecret();
		String partnerkey = WxPayConfigure.getPartnerKey();
		String mch_id = WxPayConfigure.getPartner();

		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		String body = orderInfo.getOrderDesc();
		// 附加数据
		String attach = ""+orderInfo.getOrderType();
		// 商户订单号
		String out_trade_no = orderInfo.getOrderNo();
		int intMoney = Integer.parseInt(orderInfo.getOrderFee());
		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = "";
		// 订 单 生 成 时 间 非必输
		// String time_start ="";
		// 订单失效时间 非必输
		// String time_expire = "";
		// 商品标记 非必输
		// String goods_tag = "";
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = GlobelConstant.PAY_URL + "admin/wp_notice.action";
		String trade_type = "JSAPI";
		String openid = orderInfo.getOpenID();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", "1");
		 //packageParams.put("total_fee", money);

		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" + "<attach>" + attach
				+ "</attach>" + "<out_trade_no>"
				+ out_trade_no
				+ "</out_trade_no>"
				+ "<attach>"
				+ attach
				+ "</attach>"
				// 金额，这里写的1 分到时修改
				+ "<total_fee>" + 1 + "</total_fee>"
				//+ "<total_fee>"+money+"</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openid + "</openid>"
				+ "</xml>";

		logger.info(xml);
		System.out.println("*************money=" + orderInfo.getOrderFee());
		// String allParameters = "";
		// try {
		// allParameters = reqHandler.genPackage(packageParams);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		// Map<String, Object> dataMap2 = new HashMap<String, Object>();
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (prepay_id.equals("")) {
				logger.info("************ErrorMsg:统一支付接口获取预支付订单出错*******");
				payResult.setResultType(GlobelConstant.PAY_FAILED);
				payResult.setResultInfo("统一支付接口获取预支付订单出错!");
				return payResult;//ErrorMsg:统一支付接口获取预支付订单出错";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String appid2 = appid;
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr2 = nonce_str;
		String prepay_id2 = "prepay_id=" + prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid2);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		String finalsign = reqHandler.createSign(finalpackage);

		/*String payUrl = GlobelConstant.PAY_URL+"public/pay.jsp?appid=" + appid2
					+ "&timeStamp=" + timestamp + "&nonceStr=" + nonceStr2
					+ "&package=" + packages + "&sign=" + finalsign;*/
		String payParam = getJson(appid2, timestamp, nonceStr2, packages, finalsign);
		logger.info("************payUrl = " + payParam);
		
		payResult.setResultType(GlobelConstant.PAY_NOTHING);
		payResult.setPayType(GlobelConstant.PAY_TYPE_WXPAY);
		payResult.setPayUrl(payParam);
		payResult.setResultInfo("生成微信支付链接");
		payResult.setOrderInfoId(orderInfo.getId());
		payResult.setPayFee(orderInfo.getOrderFee());
		
		return payResult;
	}

	private String getJson(String appid2,String timestamp, String nonceStr2,String packages, String finalsign) {
		Map<String, Object> payMap = new HashMap<String,Object>();
		payMap.put("appid", appid2);
		payMap.put("timeStamp", timestamp);
		payMap.put("nonceStr", nonceStr2);
		payMap.put("package", packages);
		payMap.put("sign", finalsign);
		JSONObject payJson = new JSONObject(payMap);
		return payJson.toJSONString();
	}
}
