package com.alex.base.interceptor;

import java.io.PrintWriter;  
import java.util.Iterator;  
import java.util.Map;  
import javax.annotation.Resource;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  

import com.alex.console.controller.AuthController;
import com.alex.console.model.SystemLog;
import com.alex.console.model.User;
import com.alex.console.service.ISystemLoggerService;
import com.alex.utils.AppConstant;
  
/**
 * 指定目录访问的权限验证和访问记录写数据库
 * @author hadoop
 *
 */
@Repository  
public class SystemInterceptor extends HandlerInterceptorAdapter {  
  
	@Autowired
    private ISystemLoggerService systemLoggerService;  
  
	private static final Log LOGGER = LogFactory.getLog(SystemInterceptor.class);
    /* 
     * (non-Javadoc) 
     *  
     * @see 
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle 
     * (javax.servlet.http.HttpServletRequest, 
     * javax.servlet.http.HttpServletResponse, java.lang.Object) 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
  
        // 后台session控制  
        String[] noFilters = new String[] { "login.html", "veriCode.html",  
                "index.html", "logout.html" };  
        String uri = request.getRequestURI();  
        //LOGGER.info("uri="+uri);
        if (uri.indexOf("background") != -1) {  
            boolean beFilter = true;  
            for (String s : noFilters) {  
                if (uri.indexOf(s) != -1) {  
                    beFilter = false;  
                    break;  
                }  
            }  
            if (beFilter) {  
                Object obj = request.getSession().getAttribute(AppConstant.LOGINED);  
                if (null == obj) {  
                    // 未登录  
                    PrintWriter out = response.getWriter();  
                    StringBuilder builder = new StringBuilder();  
                    builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
                    builder.append("alert(\"页面过期，请重新登录\");");  
                    builder.append("window.top.location.href=\"");  
                    builder.append(AppConstant.BASEPATH);  
                    builder.append("/background/index.html\";</script>");  
                    out.print(builder.toString());  
                    out.close();  
                    return false;  
                } else {  
                    // 添加日志  
                    String operateContent = AppConstant.operateContent(uri);  
                    if (null != operateContent) {  
                        String url = uri.substring(uri.indexOf("background"));  
                        String ip = request.getRemoteAddr(); 
                        Integer userId = ((User) obj).getId();  
                        SystemLog systemLog = new SystemLog();  
                        systemLog.setUserId(userId);  
                        systemLog.setClientIP(ip);  
                        systemLog.setUri(uri);
                        systemLog.setCreateTime(System.currentTimeMillis());
                        this.systemLoggerService.addSystemLog(systemLog);  
                    }  
                }  
            }  
        }  
  
        Map paramsMap = request.getParameterMap();  
  
        for (Iterator<Map.Entry> it = paramsMap.entrySet().iterator(); it  
                .hasNext();) {  
            Map.Entry entry = it.next();  
            Object[] values = (Object[]) entry.getValue();  
            for (Object obj : values) {  
//                if (!DataUtil.isValueSuccessed(obj)) {  
//                    throw new RuntimeException("有非法字符：" + obj);  
//                }  
            }  
        }  
  
        return super.preHandle(request, response, handler);  
    }  
  
}  