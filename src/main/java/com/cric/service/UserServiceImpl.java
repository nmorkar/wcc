package com.cric.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cric.dao.UserDao;
import com.cric.dao.UserSelectionDao;
import com.cric.domain.User;
import com.cric.domain.UserSelection;
import com.cric.model.CricModel;
import com.cric.model.Player;
import com.cric.model.PlayerSelection;
import com.cric.util.CricUtil;
import com.cric.util.EMailUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserSelectionDao userSelectionDao;
	
	@Autowired
	EMailUtil email;
	
	@Override
	public User getUser(String username) {
		return userDao.find(username);
	}
	
	@Override
	public void saveUserMatchSelection(CricModel model){
		List<UserSelection> lus = null;
		if(model !=null && model.getPlayers() != null && !model.getPlayers().isEmpty()){
			//check if exists then delete
			lus = userSelectionDao.find(model.getMatch());
			if(lus != null && !lus.isEmpty()){
				userSelectionDao.delete(lus);
			}
			//Add
			lus = new ArrayList<>();
			for (Player p : model.getPlayers()) {
				UserSelection us = new UserSelection();		
				us.setMatchName(model.getMatch());
				us.setSelection(p.getSelectedPlayers().toString());
				us.setSelectionDate(new Date());
				us.setUser(new User(com.cric.model.User.valueOf(p.getName().toUpperCase()).getId()));
				us.setJsonData(CricUtil.toJson(model));
				us.setStatus(1l);
				
				lus.add(us);
			}
			//Add
			userSelectionDao.saveUpdate(lus);
		}
	}
	
	@Override
	public boolean matchNameExists(String matchname){
		List<UserSelection> l = userSelectionDao.find(matchname);
		if(l != null && !l.isEmpty()){
			return true;
		}
		return false;
	}
	
	@Override
	public void archiveMatchSelection(String matchname){
		userSelectionDao.archive(matchname);
	}

	@Override
	public void deleteMatchSelection(String matchname){
		userSelectionDao.delete(matchname);
	}
	@Override
	public void sendEmail(){
		email.sendEmail("nmorkar@gmail.com", "nmorkar@gmail.com", "test email");
	}
	
	@Override
	public Map<String,List<PlayerSelection>> getMatchPlayerList(){
		Map<String,List<PlayerSelection>> map = new HashMap<>();
		List<UserSelection> list = userSelectionDao.findAll();
		if(list != null & !list.isEmpty()){
			List<PlayerSelection> pl = null;
			for (UserSelection userSelection : list) {
				 pl = map.get(userSelection.getMatchName());
				 if(pl != null && !pl.isEmpty()){
					  pl.add(buildPlayerSelection(userSelection));
				 }else{
					 pl = new ArrayList<>();
					 pl.add(buildPlayerSelection(userSelection));
					 map.put(userSelection.getMatchName(), pl);
				 }
			}
		}
		
		return map;
	}

	private PlayerSelection buildPlayerSelection(UserSelection userSelection){
		PlayerSelection p = new PlayerSelection();
		 p.setName(userSelection.getUser().getUserName());
		 p.setSelectedPlayers(userSelection.getSelection());
		 p.setIntial(com.cric.model.User.valueOf(userSelection.getUser().getUserName().toUpperCase()).getInitial());
		return p;
	}
}
