/*
 * Created on 09/03/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author cosapi_sac_01
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogginResult implements Serializable{

	private String state;
	private String message;
	private String flagPasswordChange;
	private String nameUser;
	private ArrayList profiles;
	private ArrayList activities;
	private ArrayList limits;
	private ArrayList groupsProfiles;
	private ArrayList transactionsProfiles;
	
	public LogginResult() {
		state ="0";
		message = "";
		flagPasswordChange = "0";
		nameUser = "";
		this.profiles = new ArrayList();
		this.activities = new ArrayList();
		this.limits = new ArrayList();
		this.groupsProfiles = new ArrayList();
		this.transactionsProfiles = new ArrayList();
	}
	
	
	/**
	 * @return Returns the groupsProfiles.
	 */
	public ArrayList getGroupsProfiles() {
		return groupsProfiles;
	}
	/**
	 * @param groupsProfiles The groupsProfiles to set.
	 */
	public void setGroupsProfiles(ArrayList groupsProfiles) {
		this.groupsProfiles = groupsProfiles;
	}
	/**
	 * @return Returns the transactionsProfiles.
	 */
	public ArrayList getTransactionsProfiles() {
		return transactionsProfiles;
	}
	/**
	 * @param transactionsProfiles The transactionsProfiles to set.
	 */
	public void setTransactionsProfiles(ArrayList transactionsProfiles) {
		this.transactionsProfiles = transactionsProfiles;
	}
	/**
	 * @return Returns the activities.
	 */
	public ArrayList getActivities() {
		return activities;
	}
	/**
	 * @param activities The activities to set.
	 */
	public void setActivities(ArrayList activities) {
		this.activities = activities;
	}
	/**
	 * @return Returns the flagPasswordChange.
	 */
	public String getFlagPasswordChange() {
		return flagPasswordChange;
	}
	/**
	 * @param flagPasswordChange The flagPasswordChange to set.
	 */
	public void setFlagPasswordChange(String flagPasswordChange) {
		this.flagPasswordChange = flagPasswordChange;
	}
	/**
	 * @return Returns the limits.
	 */
	public ArrayList getLimits() {
		return limits;
	}
	/**
	 * @param limits The limits to set.
	 */
	public void setLimits(ArrayList limits) {
		this.limits = limits;
	}
	
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList getProfiles() {
		return profiles;
	}
	/**
	 * @param profiles The profiles to set.
	 */
	public void setProfiles(ArrayList profiles) {
		this.profiles = profiles;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
}


