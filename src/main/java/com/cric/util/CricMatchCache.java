package com.cric.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cric.model.Player;
import com.cric.model.User;

public class CricMatchCache {
	
	private static List<String> availableList = new ArrayList<>();
	private static String matchId;
	private static Map<User, Player> userPls = new HashMap<>();
	private static int playerCount;
	
	public static Collection<Player> selectRandom(User u){
		Player p = userPls.get(u);
		if(p == null ){
			userPls.put(u, buildPlayer(u));
		}else{
			List<String> pl = p.getSelectedPlayers();
			String s = selectOne();
			System.out.println(s);
			p.add(s);
			//pl.add(s);
		}
		
		return userPls.values();
	}
	
	public static Player buildPlayer(User u){
		Player p = new Player();
		p.setIntial(u.getInitial());
		p.setName(u.getName());
		p.add(selectOne());
		//p.setSelectedPlayers(Arrays.asList(new String[]{selectOne()}));
		return p;
	}
	
	public static String getMatchId(){
		return matchId;
	}
	public static int getPlayerCount(){
		return playerCount;
	}
	public static Collection<Player> getPlayers(){
		return userPls.values();
	}
	
	public static String selectOne(){
		Collections.shuffle(availableList);
		Collections.shuffle(availableList);
		Collections.shuffle(availableList);
		playerCount--;
		return availableList.remove(0);
	}
	
	public static void reset(){
		matchId = "";
		playerCount = 22;
		availableList = new ArrayList<>(getList());
		//userPls = new HashMap<>();
		userPls.values().clear();
	}
	
	public static void startNew(String mId){
		reset();
		matchId = mId;
	}
	
	private static Set<String> getList(){
		Set<String> s = new HashSet<>();
		for (int i=0; i<12;i++) {
			s.add("A" + i);
		}
		for (int i=0; i<12;i++) {
			s.add("B" + i);
		}
		return s;
	}
	
	
}
