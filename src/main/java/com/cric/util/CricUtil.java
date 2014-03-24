package com.cric.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.cric.model.CricModel;
import com.cric.model.User;

public final class CricUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static CricModel selectPlayer(String username) {
		CricModel model = new CricModel();
		User u = User.valueOf(username);

		model.setPlayers(CricMatchCache.selectRandom(u));

		model.setMatch(CricMatchCache.getMatchId());
		model.setPlayerCount(CricMatchCache.getPlayerCount());
		// model.setUsername(u.getName());

		return model;
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
