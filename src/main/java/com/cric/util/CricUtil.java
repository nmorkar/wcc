package com.cric.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.cric.model.CricModel;
import com.cric.model.User;

public final class CricUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String getModelJSON() {
		try {
			return mapper.writeValueAsString(getModel());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toJson(CricModel model){
		try {
			return mapper.writeValueAsString(model);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}

	public static CricModel getModel() {
		CricModel model = new CricModel();

		model.setMatch(CricMatchCache.getMatchId());
		model.setPlayers(CricMatchCache.getPlayers());
		model.setPlayerCount(CricMatchCache.getPlayerCount());

		return model;
	}

	public static CricModel selectPlayer(String username) {
		CricModel model = new CricModel();
		User u = User.valueOf(username.toUpperCase());
		
		CricMatchCache.addSelectionOrder(u); //add user for selection order
		
		model.setPlayers(CricMatchCache.selectRandom(u));

		model.setMatch(CricMatchCache.getMatchId());
		model.setPlayerCount(CricMatchCache.getPlayerCount());
		// model.setUsername(u.getName());
		
		model.setNextUser(CricMatchCache.nextSelectionOrder(u)); //set next user to select
		
		CricMatchCache.setNextUser(model.getNextUser());
		
		model.setSelectionOrder(CricMatchCache.getSelectionOrder());
		
		return model;
	}

	public static CricModel getCricModel(){
		CricModel m = new CricModel();
		m.setRefresh(false);
		m.setSelectionOrder(CricMatchCache.getSelectionOrder());
		m.setNextUser(CricMatchCache.getNextUser());
		return m;
	} 
	
	public static CricModel addLoggedInUser(String username){
		User u = User.valueOf(username.toUpperCase());
		CricMatchCache.addSelectionOrder(u);
		
		return getCricModel();
	}
	public static CricModel removeLoggedInUser(String username){
		User u = User.valueOf(username.toUpperCase());
		CricMatchCache.removeSelectionOrder(u);
		
		return getCricModel();
	}

	public static CricModel addSelectionOrder(String names){
		if(names != null && !names.isEmpty()){
			String[] nameArr = names.split(",");
			if(nameArr != null && nameArr.length > 0){
				CricMatchCache.clearSelectionOrder();
				for (String name : nameArr) {
					User u = User.valueOf(name.toUpperCase());
					CricMatchCache.addSelectionOrder(u);	
				}
			}
		}else{
			CricMatchCache.clearSelectionOrder();
		}
		
		return getCricModel();
	}
	
	public static String nextUser(String username){
		User u = User.valueOf(username.toUpperCase());
		return CricMatchCache.nextSelectionOrder(u);
	}
	
	
	public static CricModel rest() {
		CricMatchCache.reset();
		CricModel model = new CricModel();
		model.setPlayers(CricMatchCache.getPlayers());
	
		return new CricModel();
	}

	public static CricModel startNew(String matchId) {
		CricMatchCache.startNew(matchId);
		CricModel model = new CricModel();
		model.setMatch(matchId);
		model.setPlayers(CricMatchCache.getPlayers());
		model.setPlayerCount(CricMatchCache.getPlayerCount());
		model.setSelectionOrder(CricMatchCache.getSelectionOrder());
		model.setNextUser(CricMatchCache.getNextUser());
		return model;

	}

	public static String convertToJSON(CricModel model) {
		try {
			return mapper.writeValueAsString(model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
