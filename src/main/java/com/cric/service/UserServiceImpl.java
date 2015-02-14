package com.cric.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cric.dao.MatchInfoDao;
import com.cric.dao.UserDao;
import com.cric.dao.UserSelectionDao;
import com.cric.domain.MatchInfo;
import com.cric.domain.User;
import com.cric.domain.UserSelection;
import com.cric.model.CricModel;
import com.cric.model.Performance;
import com.cric.model.Player;
import com.cric.model.PlayerSelection;
import com.cric.util.CricUtil;
import com.cric.util.EMailUtil;
import com.cric.util.OrderByTotal;

@Transactional(readOnly = false)
@Service
public class UserServiceImpl implements UserService {

	private final String playerScoreSeperator=":";
	private final String playerSeperator=",";
	private final int wicketMultiplier = 20;
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MatchInfoDao matchInfoDao;
	
	@Autowired
	private UserSelectionDao userSelectionDao;
	
	@Autowired
	private EMailUtil email;
	
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
				us.setUserName(p.getName());
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
			
			matchInfoDao.save(new MatchInfo(model.getMatch(), 1L));
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
		email.sendEmail("nmorkar@gmail.com", "nmorkar@yahoo.com", "test email");
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
		p.setName(userSelection.getUserName());
		 //p.setName(userSelection.getUser().getUserName());
		 p.setSelectedPlayers(userSelection.getSelection());
		 //p.setIntial(com.cric.model.User.valueOf(userSelection.getUser().getUserName().toUpperCase()).getInitial());
		 p.setIntial(com.cric.model.User.valueOf(userSelection.getUserName().toUpperCase()).getInitial());
		return p;
	}
	
	public List<Performance> getPerformance(){
		List<Performance> list = new ArrayList<>();
		List<MatchInfo> minfo = matchInfoDao.findAll();
		if(minfo != null && !minfo.isEmpty()){
			 Map<String,List<PlayerSelection>> map = getMatchPlayerList();
			 Map<String,String> plMap = buildPlayerPerfMap(minfo);
			for (MatchInfo matchInfo : minfo) {
				if(matchInfo.getPerformance() != null && !matchInfo.getPerformance().isEmpty()){
					Performance p = new Performance();
					p.setMatchName(matchInfo.getMatchName());
					List<PlayerSelection> plSelection = map.get(matchInfo.getMatchName());
					if(plSelection != null && !plSelection.isEmpty()){
						for (PlayerSelection playerSelection : plSelection) {
							//playerSelection.setPlayers(buildPlayerPerformance(playerSelection, plMap));
							buildPlayerPerformance(playerSelection, plMap);
						}
					}
					p.setPlayers(plSelection);
					p.setPerformance(calculatePlayerPerformance(plSelection));
					list.add(p);
				}
				
			}
		}
			
		
		return list;
	}
	
	public List<String> calculatePlayerPerformance(List<PlayerSelection> plSelection){
		List<String> list = new ArrayList<>();
		if(plSelection != null && !plSelection.isEmpty()){
			String textValue = "";
			list.add("<tr><th colspan='5'>Total Performance</th></tr>");
			list.add("<tr><td colspan='5'>&nbsp;</td></tr>");
			Collections.sort(plSelection, new OrderByTotal());
			for (int i = 0; i < plSelection.size(); i++) {
				PlayerSelection ps = plSelection.get(i);
				for (int j = i+1; j < plSelection.size(); j++) {
					PlayerSelection ps1 = plSelection.get(j);
					textValue = "<tr><td>"+ps.getIntial() +"</td><td> -> </td><td>"+ ps1.getIntial() + "</td><td> = </td><td>" + (ps.getTotalPerformance() - ps1.getTotalPerformance()) +"</td></tr>";
					list.add(textValue);
				}
			}
		}
		return list;
	}
	
	public PlayerSelection buildPlayerPerformance(PlayerSelection selection, Map<String,String> map){
		List<String> list = new ArrayList<>();
		if(selection != null && selection.getSelectedPlayers() != null 
				&& !selection.getSelectedPlayers().isEmpty()){
			String[] plArr = selection.getSelectedPlayers().split(playerSeperator);
			if(plArr != null){
				int rTotal=0;
				int cTotal=0;
				long total=0;
				String textValue="";
				textValue = "<tr><th>PL</th><th>R</th><th>W</th></tr>";
				list.add(textValue);
				for (String pl : plArr) {
					pl = pl.replaceAll("\\[", "");
					pl = pl.replaceAll("]", "");
					String pl1 = map.get(pl.trim());
					
					if(pl1 != null && pl1.length() > 0){
						int r=0,w=0;
						String[] pl1Arr = pl1.split(playerScoreSeperator);
						if(pl1Arr.length >= 2){
							r = Integer.valueOf(pl1Arr[1].trim());
							rTotal += r;
						}if(pl1Arr.length >= 3){
							w = (Integer.valueOf(pl1Arr[2].trim()) * wicketMultiplier);
							cTotal += w;
						}
						textValue = "<tr><td>"+pl1Arr[0]+"</td><td>"+r+"</td><td>"+w+"</td></tr>";
						list.add(textValue);
					}
				}
				textValue = "<tr><td>&nbsp;</td><td>"+rTotal+"</td><td>"+cTotal+"</td></tr>";
				list.add(textValue);
				total = rTotal+cTotal;
				textValue = "<tr><td colspan='2'>Total</td><td>"+total+"</td></tr>";
				list.add(textValue);
				selection.setPlayersPerformance(list);
				selection.setTotalPerformance(total);
			}
		}
		
		return selection;
	}
	
	
	public Map<String,String> buildPlayerPerfMap(List<MatchInfo> minfo){
		Map<String,String> map = new HashMap<>();
		if(minfo != null && !minfo.isEmpty()){
			for (MatchInfo matchInfo : minfo) {
				if(matchInfo.getPerformance() != null && !matchInfo.getPerformance().isEmpty()){
					String[] perf = matchInfo.getPerformance().replaceAll("\\n", "").split(playerSeperator);
					for (String p : perf) {
						String[] p1 = p.split(playerScoreSeperator);
						map.put(p1[0].trim(), p);
					}
				}
			}
		}
		return map;
	}

	@Override
	public void savePerformance(String matchName, String performance) {
		MatchInfo matchInfo = matchInfoDao.find(matchName);	
		if(matchInfo == null){
			matchInfo = new MatchInfo(matchName, 1L);
		}
		matchInfo.setPerformance(performance);
		
		matchInfoDao.saveOrUpdate(matchInfo);
	}
	
}
