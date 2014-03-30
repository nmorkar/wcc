package com.cric.domain;

import java.util.Date;

public class UserSelection {

	private Long selectionId;
	private User user;
	private String matchName;
	private String selection;
	private Date selectionDate;
	private String jsonData;
	private Long status;
	
	public Long getSelectionId() {
		return selectionId;
	}
	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public Date getSelectionDate() {
		return selectionDate;
	}
	public void setSelectionDate(Date selectionDate) {
		this.selectionDate = selectionDate;
	}
	
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matchName == null) ? 0 : matchName.hashCode());
		result = prime * result
				+ ((selection == null) ? 0 : selection.hashCode());
		result = prime * result
				+ ((selectionDate == null) ? 0 : selectionDate.hashCode());
		result = prime * result
				+ ((selectionId == null) ? 0 : selectionId.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSelection other = (UserSelection) obj;
		if (matchName == null) {
			if (other.matchName != null)
				return false;
		} else if (!matchName.equals(other.matchName))
			return false;
		if (selection == null) {
			if (other.selection != null)
				return false;
		} else if (!selection.equals(other.selection))
			return false;
		if (selectionDate == null) {
			if (other.selectionDate != null)
				return false;
		} else if (!selectionDate.equals(other.selectionDate))
			return false;
		if (selectionId == null) {
			if (other.selectionId != null)
				return false;
		} else if (!selectionId.equals(other.selectionId))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
