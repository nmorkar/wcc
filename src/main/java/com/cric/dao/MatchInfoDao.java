package com.cric.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.cric.domain.MatchInfo;

//@Transactional(readOnly = false)
@Component
public class MatchInfoDao extends HibernateDaoSupport {

	/*@Autowired
	SessionFactory sessionFactory;*/
	
	/*@Autowired
	private HibernateTemplate  hibernateTemplate;*/
	
	@Autowired
	public void init(HibernateTemplate hibernateTemplate) {
	    //setSessionFactory(factory);
		//this.hibernateTemplate = hibernateTemplate;
		setHibernateTemplate(hibernateTemplate);
	}
	
	
	public void saveUpdate(List<MatchInfo> c){
		//getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		for (MatchInfo MatchInfo : c) {
			save(MatchInfo);
		}
		
	}
	
	public void saveOrUpdate(MatchInfo matchInfo){
		getHibernateTemplate().saveOrUpdate(matchInfo);
	}
	
	public void save(MatchInfo matchInfo){
		getHibernateTemplate().save(matchInfo);
		//getHibernateTemplate().flush();
	}
 
	public void update(MatchInfo matchInfo){
		getHibernateTemplate().update(matchInfo);
	}
 
	public void delete(MatchInfo matchInfo){
		getHibernateTemplate().delete(matchInfo);
	}
	public void delete(List<MatchInfo> matchInfo){
		getHibernateTemplate().deleteAll(matchInfo);
	}
	
	public void delete(String deleteMatch){
		getHibernateTemplate().deleteAll(getHibernateTemplate().find(" from MatchInfo u where u.matchName = ? ", new Object[]{deleteMatch}) );
	}
	
	public void archive(String match){
		archive(find(match));
	}
	
	public void archive(MatchInfo matchInfo){
		matchInfo.setStatus(0l);
		getHibernateTemplate().update(matchInfo);
	}
	
	public MatchInfo get(Long selectionId){
		return (MatchInfo) getHibernateTemplate().get(MatchInfo.class, selectionId);
	}
	
	public MatchInfo load(Long selectionId){
		return (MatchInfo) getHibernateTemplate().load(MatchInfo.class, selectionId);
	}

	@SuppressWarnings("unchecked")
	public List<MatchInfo> findAll(){
		return  (List<MatchInfo>) getHibernateTemplate().find(" from MatchInfo u where  status = 1");
	}
	
	
	@SuppressWarnings("unchecked")
	public MatchInfo find(String matchname){
		List<MatchInfo> l =  (List<MatchInfo>) getHibernateTemplate().find(" from MatchInfo u where u.matchName = ? and status = 1", new Object[]{matchname});
		if(l != null && !l.isEmpty()){
			return l.get(0);
		}
		return null;
	}
	
	
	
}
