/*
 * Created on 06/10/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.login.domain.impl;

import java.io.Serializable;
import java.util.Date;

import pe.bn.login.domain.ImagenTarjeta;
import pe.bn.login.domain.IngresoTarjeta;


/**
 * @author 2424012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IngresoTarjetaImpl implements IngresoTarjeta, Serializable{
	private String fechaIng;
	private Date fechaAnt;
	private String diaAnt;
	private String horaAnt;
	private String numSec;
	private String numTar;
	private String ipIng;
	private String ipAnt;
	
	

	public String getDiaAnt() {
		return diaAnt;
	}
	public void setDiaAnt(String diaAnt) {
		this.diaAnt = diaAnt;
	}
	public String getHoraAnt() {
		return horaAnt;
	}
	public void setHoraAnt(String horaAnt) {
		this.horaAnt = horaAnt;
	}
	public Date getFechaAnt() {
		return fechaAnt;
	}
	public void setFechaAnt(Date fechaAnt) {
		this.fechaAnt = fechaAnt;
	}
	public String getFechaIng() {
		return fechaIng;
	}
	public void setFechaIng(String fechaIng) {
		this.fechaIng = fechaIng;
	}
	public String getIpAnt() {
		return ipAnt;
	}
	public void setIpAnt(String ipAnt) {
		this.ipAnt = ipAnt;
	}
	public String getIpIng() {
		return ipIng;
	}
	public void setIpIng(String ipIng) {
		this.ipIng = ipIng;
	}
	public String getNumSec() {
		return numSec;
	}
	public void setNumSec(String numSec) {
		this.numSec = numSec;
	}
	public String getNumTar() {
		return numTar;
	}
	public void setNumTar(String numTar) {
		this.numTar = numTar;
	}


}
