package com.alex.console.dao;

import java.util.List;

import com.alex.console.model.User;

/**
 * 用户相关操作的持久层
 * @author hadoop
 *
 */
public interface IUserDao {
	public User getUser(int id);  

	public List<User> getAllUser();  

	public int addUser(User user);  

	public boolean delUser(String id);  

	public boolean updateUser(User user);  
	
	public User getUserByName(String userName);
}
