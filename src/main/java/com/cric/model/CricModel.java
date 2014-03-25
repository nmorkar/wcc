package com.cric.model;

import java.util.Collection;

public class CricModel {

	private String match;
	private int playerCount;
	private Collection<Player> players;
	private String message;
	private String nextUser;
	
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
	
	
	
}
