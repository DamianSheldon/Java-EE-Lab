package com.smart.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class User extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7471521913490203758L;

	private int userId;
	
	private String userName;
	
	private String password;
	
	private long userType;
	
	private long locked;
	
	private int credit;
	
	private Date lastVisit;
	
	private String lastTip;
	
	private Set manBoards;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserType() {
		return userType;
	}

	public void setUserType(long userType) {
		this.userType = userType;
	}

	public long getLocked() {
		return locked;
	}

	public void setLocked(long locked) {
		this.locked = locked;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getLastTip() {
		return lastTip;
	}

	public void setLastTip(String lastTip) {
		this.lastTip = lastTip;
	}

	public Set getManBoards() {
		return manBoards;
	}

	public void setManBoards(Set manBoards) {
		this.manBoards = manBoards;
	}
}
