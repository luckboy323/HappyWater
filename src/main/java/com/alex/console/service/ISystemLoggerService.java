package com.alex.console.service;

import com.alex.console.model.SystemLog;

/**
 * 记录系统访问日志
 * @author hadoop
 *
 */
public interface ISystemLoggerService {
	public int addSystemLog(SystemLog systemLog);
}
