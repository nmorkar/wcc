package com.cric.service;

import java.util.List;
import java.util.Map;

import com.cric.domain.User;
import com.cric.model.CricModel;
import com.cric.model.PlayerSelection;

public interface UserService {

	User getUser(String username);
	void saveUserMatchSelection(CricModel model);
	Map<String, List<PlayerSelection>> getMatchPlayerList();
	void deleteMatchSelection(String matchname);
	void archiveMatchSelection(String matchname);
	boolean matchNameExists(String matchname);
	void sendEmail();
}
