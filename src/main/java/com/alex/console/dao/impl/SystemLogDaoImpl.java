package com.alex.console.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.alex.base.dao.BaseDao;
import com.alex.console.dao.ISystemLogDao;
import com.alex.console.model.SystemLog;

@Repository
public class SystemLogDaoImpl extends BaseDao implements ISystemLogDao {

	public int addSystemLog(SystemLog systemLog) {
		Serializable ser = getCurrentSession().save(systemLog);
		return (Integer) ser;
	}

}
