package com.cric.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.cric.domain.UserSelection;

@Component
public class UserSelectionDao extends HibernateDaoSupport {

	/*@Autowired
	SessionFactory sessionFactory;*/
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	
	public void saveUpdate(List<UserSelection> c){
		getHibernateTemplate().saveOrUpdateAll(c);
		//getHibernateTemplate().flush();
	}
	
	public void save(UserSelection user){
		getHibernateTemplate().save(user);
	}
 
	public void update(UserSelection user){
		getHibernateTemplate().update(user);
	}
 
	public void delete(UserSelection user){
		getHibernateTemplate().delete(user);
	}
	public void delete(List<UserSelection> user){
		getHibernateTemplate().deleteAll(user);
	}
	
	public void delete(String deleteMatch){
		getHibernateTemplate().deleteAll(getHibernateTemplate().find(" from UserSelection u where u.matchName = ? ", new Object[]{deleteMatch}) );
	}
	
	public void archive(String match){
		List<UserSelection> u = find(match);
		for (UserSelection userSelection : u) {
			userSelection.setStatus(0l);
		}
		getHibernateTemplate().saveOrUpdateAll(u);
	}
	
	public void archive(UserSelection user){
		user.setStatus(0l);
		getHibernateTemplate().update(user);
	}
	
	public UserSelection get(Long selectionId){
		return (UserSelection) getHibernateTemplate().get(UserSelection.class, selectionId);
	}
	
	public UserSelection load(Long selectionId){
		return (UserSelection) getHibernateTemplate().load(UserSelection.class, selectionId);
	}

	@SuppressWarnings("unchecked")
	public List<UserSelection> findAll(){
		return  getHibernateTemplate().find(" from UserSelection u where  status = 1");
	}
	
	@SuppressWarnings("unchecked")
	public List<UserSelection> find(Long userId){
		return  getHibernateTemplate().find(" from UserSelection u where u.userId = ?  and status = 1 ", new Object[]{userId});
	}
	
	@SuppressWarnings("unchecked")
	public List<UserSelection> find(Long userId, String matchname){
		return  getHibernateTemplate().find(" from UserSelection u where u.userId = ? and u.matchName = ?  and status = 1 ", new Object[]{userId,matchname});
	}
	
	@SuppressWarnings("unchecked")
	public List<UserSelection> find(String matchname){
		return  getHibernateTemplate().find(" from UserSelection u where u.matchName = ? and status = 1", new Object[]{matchname});
	}
	
	
	
}
