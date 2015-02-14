package com.cric.domain;

public class MatchInfo {
	private Long matchInfoId;
	private String matchGroup="wcc";
	private String matchName;
	private String performance;
	private Long status;
	
	
	
	public MatchInfo(String matchName, Long status) {
		super();
		this.matchName = matchName;
		this.status = status;
	}
	public MatchInfo(Long matchInfoId, String matchName, String performance,
			Long status) {
		super();
		this.matchInfoId = matchInfoId;
		this.matchName = matchName;
		this.performance = performance;
		this.status = status;
	}
	public MatchInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getMatchInfoId() {
		return matchInfoId;
	}
	public void setMatchInfoId(Long matchInfoId) {
		this.matchInfoId = matchInfoId;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getMatchGroup() {
		return matchGroup;
	}
	public void setMatchGroup(String matchGroup) {
		this.matchGroup = matchGroup;
	}


	
}
