/*
 * Created on 02/04/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cosapisoft.sarawebbranch.common.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author David
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityBranch implements Serializable{

	/**
	 * 
	 */
	private ArrayList perfiles;
	private ArrayList limites;
	
	public ArrayList getLimites() {
		return limites;
	}
	public void setLimites(ArrayList limites) {
		this.limites = limites;
	}
	public ArrayList getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(ArrayList perfiles) {
		this.perfiles = perfiles;
	}
}
