package com.cric.util;

import java.util.Comparator;

import com.cric.model.PlayerSelection;

public class OrderByTotal implements Comparator<PlayerSelection> {

	@Override
	public int compare(PlayerSelection o1, PlayerSelection o2) {
		if(o1 == null || o2 == null) return 0;
		if(o1.getTotalPerformance() > o2.getTotalPerformance()){
			return -1;
		}
		if(o1.getTotalPerformance() < o2.getTotalPerformance()){
			return 1;
		}
		return 0;
	}

}
