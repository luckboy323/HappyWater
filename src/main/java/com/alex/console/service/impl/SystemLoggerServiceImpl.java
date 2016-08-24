package com.alex.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.console.dao.ISystemLogDao;
import com.alex.console.model.SystemLog;
import com.alex.console.service.ISystemLoggerService;

@Service
public class SystemLoggerServiceImpl implements ISystemLoggerService {

	@Autowired
	private ISystemLogDao systemLogDao;
	
	public int addSystemLog(SystemLog systemLog) {
		return systemLogDao.addSystemLog(systemLog);
	}

}
