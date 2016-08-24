package com.alex.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 日志记录uri的响应时间
 * @author hadoop
 *
 */
public class MonitorInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOGGER = LogFactory.getLog(MonitorInterceptor.class);
	
	private NamedThreadLocal<Long>  startTimeThreadLocal = new NamedThreadLocal<Long>("Monitor-StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Long begintime = System.currentTimeMillis();
		startTimeThreadLocal.set(begintime);
		String remoteAddr = request.getRemoteAddr();
		//LOGGER.debug(String.format("MonitorInterceptor---%s request uri %s",remoteAddr, request.getRequestURI()));
		//LOGGER.debug("MonitorInterceptor---begintime=" + begintime);
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Long endTime = System.currentTimeMillis();
		Long beginTime = startTimeThreadLocal.get();
		Long consumeTime = endTime - beginTime;
		LOGGER.info(String.format("[MonitorInterceptor]ip:%s-uri:%s consume %s millis", request.getRemoteAddr(),request.getRequestURI(),consumeTime));
	}
}
