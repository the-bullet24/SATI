/*
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Miguel
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DBAccessRequest implements Serializable{
	/**
	 * 
	 */
	private ArrayList Querys;

	public DBAccessRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList getQuerys() {
		return Querys;
	}
	public void setQuerys(ArrayList Querys) {
		this.Querys = Querys;
	}
}
