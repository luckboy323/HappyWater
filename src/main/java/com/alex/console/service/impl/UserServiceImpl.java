package com.alex.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.console.dao.IUserDao;
import com.alex.console.model.User;
import com.alex.console.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;  

	public User getUser(int id) {  
	    return userDao.getUser(id);  
	}  

	public List<User> getAllUser() {  
	    return userDao.getAllUser();  
	}  

	public int addUser(User user) {  
		if(userDao.getUserByName(user.getUserName())==null){
			return userDao.addUser(user);
		}
	    return -1;  
	}  

	public boolean delUser(String id) {  

	    return userDao.delUser(id);  
	}  

	public boolean updateUser(User user) {  
	    return userDao.updateUser(user);  
	}

	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

}
