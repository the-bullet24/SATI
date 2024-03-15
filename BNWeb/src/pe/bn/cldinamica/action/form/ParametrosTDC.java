package pe.bn.cldinamica.action.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Mily Dolores Bustamante
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ParametrosTDC implements Serializable {
	
	private String binMedioAutent;
	private String binMedioAutentVirtual;
	private String codMedioAutent;
	private String codElementoSeguridad;
	private String codElementoSeguridadSec;
	private String nomCodMedioAutent;
	private String nomCodMedioAutentVirt;
	private String estCodMedioAutent;
	private String estCodMedioAutentVirt;
	private String tipoElementoSeguridad;
	private Integer codVerifExistMA;
	private Integer codObtenerBinesMA;
	private Integer codObtenerListaESRelac;
	private String codEstObtenerListaESRelac;
	private String tipoBloqueo;
	private  List elemetosRel;
	private String codEstObtenerListaESRelacFinal;
	private String tipoElementoSeguridadFinal;
	
	
	public String getCodEstObtenerListaESRelacFinal() {
		return codEstObtenerListaESRelacFinal;
	}
	public void setCodEstObtenerListaESRelacFinal(
			String codEstObtenerListaESRelacFinal) {
		this.codEstObtenerListaESRelacFinal = codEstObtenerListaESRelacFinal;
	}
	public String getTipoElementoSeguridadFinal() {
		return tipoElementoSeguridadFinal;
	}
	public void setTipoElementoSeguridadFinal(String tipoElementoSeguridadFinal) {
		this.tipoElementoSeguridadFinal = tipoElementoSeguridadFinal;
	}
	public String getCodEstObtenerListaESRelac() {
		return codEstObtenerListaESRelac;
	}
	public void setCodEstObtenerListaESRelac(String codEstObtenerListaESRelac) {
		this.codEstObtenerListaESRelac = codEstObtenerListaESRelac;
	}
	public String getBinMedioAutent() {
		return binMedioAutent;
	}
	public void setBinMedioAutent(String binMedioAutent) {
		this.binMedioAutent = binMedioAutent;
	}
	public String getBinMedioAutentVirtual() {
		return binMedioAutentVirtual;
	}
	public void setBinMedioAutentVirtual(String binMedioAutentVirtual) {
		this.binMedioAutentVirtual = binMedioAutentVirtual;
	}
	public String getCodElementoSeguridad() {
		return codElementoSeguridad;
	}
	public void setCodElementoSeguridad(String codElementoSeguridad) {
		this.codElementoSeguridad = codElementoSeguridad;
	}
	public String getCodMedioAutent() {
		return codMedioAutent;
	}
	public void setCodMedioAutent(String codMedioAutent) {
		this.codMedioAutent = codMedioAutent;
	}
	public Integer getCodObtenerBinesMA() {
		return codObtenerBinesMA;
	}
	public void setCodObtenerBinesMA(Integer codObtenerBinesMA) {
		this.codObtenerBinesMA = codObtenerBinesMA;
	}
	public Integer getCodObtenerListaESRelac() {
		return codObtenerListaESRelac;
	}
	public void setCodObtenerListaESRelac(Integer codObtenerListaESRelac) {
		this.codObtenerListaESRelac = codObtenerListaESRelac;
	}
	public Integer getCodVerifExistMA() {
		return codVerifExistMA;
	}
	public void setCodVerifExistMA(Integer codVerifExistMA) {
		this.codVerifExistMA = codVerifExistMA;
	}
	public String getEstCodMedioAutent() {
		return estCodMedioAutent;
	}
	public void setEstCodMedioAutent(String estCodMedioAutent) {
		this.estCodMedioAutent = estCodMedioAutent;
	}
	public String getEstCodMedioAutentVirt() {
		return estCodMedioAutentVirt;
	}
	public void setEstCodMedioAutentVirt(String estCodMedioAutentVirt) {
		this.estCodMedioAutentVirt = estCodMedioAutentVirt;
	}
	public String getNomCodMedioAutent() {
		return nomCodMedioAutent;
	}
	public void setNomCodMedioAutent(String nomCodMedioAutent) {
		this.nomCodMedioAutent = nomCodMedioAutent;
	}
	public String getTipoElementoSeguridad() {
		return tipoElementoSeguridad;
	}
	public void setTipoElementoSeguridad(String tipoElementoSeguridad) {
		this.tipoElementoSeguridad = tipoElementoSeguridad;
	}
	public String getNomCodMedioAutentVirt() {
		return nomCodMedioAutentVirt;
	}
	public void setNomCodMedioAutentVirt(String nomCodMedioAutentVirt) {
		this.nomCodMedioAutentVirt = nomCodMedioAutentVirt;
	}
	public String getCodElementoSeguridadSec() {
		return codElementoSeguridadSec;
	}
	public void setCodElementoSeguridadSec(String codElementoSeguridadSec) {
		this.codElementoSeguridadSec = codElementoSeguridadSec;
	}
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}
	public List getElemetosRel() {
		return elemetosRel;
	}
	public void setElemetosRel(List elemetosRel) {
		this.elemetosRel = elemetosRel;
	}
	
}
