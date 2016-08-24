package com.alex.console.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.alex.base.dao.BaseDao;
import com.alex.console.dao.IUserDao;
import com.alex.console.model.User;

@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

	/**
	 * 根据用户id查询用户
	 */
	public User getUser(int id) {  

	    String hql = "from com.alex.console.model.User u where u.id=?";  
	    Query query = getCurrentSession().createQuery(hql);  
	    query.setInteger(0, id);  

	    return (User)query.uniqueResult();  
	}  

	/**
	 * 查询所有用户
	 */
	public List<User> getAllUser() {  

	    String hql = "from com.alex.console.model.User";  
	    Query query = getCurrentSession().createQuery(hql); 
	    
//	    String sql = "select * from t_ichat_user";
//	    Query query = getCurrentSession().createSQLQuery(sql);
	    return query.list();  
	}  

	/**
	 * 添加用户
	 */
	public int addUser(User user) {  
	    Serializable ser = getCurrentSession().save(user);  
	    return (Integer) ser;
	}  

	/**
	 * 根据用户id删除用户
	 */
	public boolean delUser(String id) {  

	    String hql = "delete com.alex.console.model.User u where u.id = ?";  
	    Query query = getCurrentSession().createQuery(hql);  
	    query.setString(0, id);  

	    return (query.executeUpdate() > 0);  
	}  

	/**
	 * 编辑用户
	 */
	public boolean updateUser(User user) {  

	    String hql = "update com.alex.console.model.User u set u.userName = ?,u.password=? where u.id = ?";  
	    Query query = getCurrentSession().createQuery(hql);  
	    query.setString(0, user.getUserName());  
	    query.setString(1, user.getPassword());  
	    query.setInteger(2, user.getId());  

	    return (query.executeUpdate() > 0);  
	}

	public User getUserByName(String userName) {
		String hql = "from com.alex.console.model.User u where u.userName=?";  
	    Query query = getCurrentSession().createQuery(hql);  
	    query.setString(0, userName);  

	    return (User)query.uniqueResult(); 
	}  


}
