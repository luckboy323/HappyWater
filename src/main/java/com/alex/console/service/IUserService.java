package com.alex.console.service;

import java.util.List;

import com.alex.console.model.User;

/**
 * 用户相关操作的业务层
 * @author hadoop
 *
 */
public interface IUserService {
	/**
	 * 根据用户id获取用户信息
	 * @param id
	 * @return
	 */
	public User getUser(int id);  

	/**
	 * 获取所有用户的信息列表
	 * @return
	 */
	public List<User> getAllUser();  

	public int addUser(User user);  

	public boolean delUser(String id);  

	public boolean updateUser(User user);
	
	public User getUserByName(String userName);
}
