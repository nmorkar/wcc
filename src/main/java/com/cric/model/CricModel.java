package com.cric.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CricModel {

	private String match;
	private int playerCount;
	private Collection<Player> players;
	private String message;
	private String nextUser;
	private List<User> selectionOrder;
	private boolean refresh=true;
 	
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public Collection<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNextUser() {
		return nextUser;
	}
	public void setNextUser(String next) {
		this.nextUser = next;
	}
	
	public List<User> getSelectionOrder() {
		return selectionOrder;
	}
	public void setSelectionOrder(List<User> selectionOrder) {
		this.selectionOrder = selectionOrder;
	}
	public boolean isRefresh() {
		return refresh;
	}
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
	
	
	
}
