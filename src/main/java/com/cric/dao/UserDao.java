package com.cric.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.cric.domain.User;

@Component
public class UserDao extends HibernateDaoSupport {

	
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	public void save(User user){
		getHibernateTemplate().save(user);
	}
 
	public void update(User user){
		getHibernateTemplate().update(user);
	}
 
	public void delete(User user){
		getHibernateTemplate().delete(user);
	}
	
	public User find(Long userId){
		return (User) getHibernateTemplate().get(User.class, userId);
	}
	
	public User find(String username){
		@SuppressWarnings("unchecked")
		List<User> l = getHibernateTemplate().find(" from User u where upper(u.userName) = ? ", new String[]{username.toUpperCase()});
		if(l != null && !l.isEmpty()){
			return l.get(0);
		}
		return null;
	}
	
	
	
}
