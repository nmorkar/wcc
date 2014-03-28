package com.cric.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
	private static List<User> selectionOrder = new ArrayList<>();
	
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
		selectionOrder.clear();
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
	
	public static void addSelectionOrder(User u){
		if(!selectionOrder.contains(u)){
			selectionOrder.add(u);
		}
	}
	
	public static String nextSelectionOrder(User u){
		Iterator<User> ite = selectionOrder.iterator();
		while (ite.hasNext()) {
			User user = (User) ite.next();
			if(user.name().equals(u.name())){
				if(ite.hasNext()){
					return ite.next().getName();
				}else{
					if(selectionOrder.size() > 1)
						return selectionOrder.get(0).getName();
				}
			}
		}
		return null;
		
	}
	
}
