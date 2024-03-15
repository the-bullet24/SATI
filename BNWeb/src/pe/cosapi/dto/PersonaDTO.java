/*
 * Fecha 21/03/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.dto;

import java.io.Serializable;

import pe.cosapi.common.ComboUtil;


public class PersonaDTO implements Serializable{
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tipoDocumento;
	private String nomTipoDocumento;
	private String numeroDocumento;
	private String sexo;
	
	private ComboUtil objBanco;
	private ComboUtil objTipoTarjeta;
	private String codTipoTarjeta;
	private String desTipoTarjeta;
	
	public  PersonaDTO(){
	    this.objBanco = new ComboUtil();
	    this.objTipoTarjeta = new ComboUtil();
	}
	
    
    public ComboUtil getObjBanco() {
        return objBanco;
    }
    public void setObjBanco(ComboUtil objBanco) {
        this.objBanco = objBanco;
    }
    public ComboUtil getObjTipoTarjeta() {
        return objTipoTarjeta;
    }
    public void setObjTipoTarjeta(ComboUtil objTipoTarjeta) {
        this.objTipoTarjeta = objTipoTarjeta;
    }
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Returns the codTipoTarjeta.
	 */
	public String getCodTipoTarjeta() {
		return codTipoTarjeta;
	}
	/**
	 * @param codTipoTarjeta The codTipoTarjeta to set.
	 */
	public void setCodTipoTarjeta(String codTipoTarjeta) {
		this.codTipoTarjeta = codTipoTarjeta;
	}
	/**
	 * @return Returns the desTipoTarjeta.
	 */
	public String getDesTipoTarjeta() {
		return desTipoTarjeta;
	}
	/**
	 * @param desTipoTarjeta The desTipoTarjeta to set.
	 */
	public void setDesTipoTarjeta(String desTipoTarjeta) {
		this.desTipoTarjeta = desTipoTarjeta;
	}
	/**
	 * @return Devuelve nomTipoDocumento.
	 */
	public String getNomTipoDocumento() {
		return nomTipoDocumento;
	}
	/**
	 * @param nomTipoDocumento El nomTipoDocumento a establecer.
	 */
	public void setNomTipoDocumento(String nomTipoDocumento) {
		this.nomTipoDocumento = nomTipoDocumento;
	}
}
