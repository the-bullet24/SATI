/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.cosapisoft.sarawebbranch.server.GeneralParameters;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class BNAplicacion {
	private static BNAplicacion unico = null;
	private Vector esquema;
	private Vector diccionario;
	private Vector control;
	private Vector parametros;
	private Vector mensajes;
	
	private Map mapEsquema;
	private Map mapDiccionario;
	private Map mapControl;
	private Map mapParametros;
	private Map mapMensajes;
	private Map mapMensajeCompuesto;
	private Map mapEstilo;
	
	private String rutaClasspath;

	 
	private String tipoEncriptacion;
	private String claveDESA;
	private String claveDESB;

	
	
	private BNAplicacion(){
		esquema				= new Vector();
		diccionario			= new Vector();
		control				= new Vector();
		parametros			= new Vector();
		mensajes			= new Vector();		

		mapEsquema			= new HashMap();
		mapDiccionario		= new HashMap();
		mapControl			= new HashMap();
		mapParametros		= new HashMap();
		mapMensajes			= new HashMap();
		mapMensajeCompuesto = new HashMap();
		mapEstilo			= new HashMap();
	}
	
	public static BNAplicacion getInstance(){
		if(unico == null)	
			unico = new BNAplicacion();
		return unico;
	}
	

	/**
	 * @param control El control a establecer.
	 */
	public void setControl(Vector control) {
		this.control = control;
	}

	/**
	 * @param diccionario El diccionario a establecer.
	 */
	public void setDiccionario(Vector diccionario) {
		this.diccionario = diccionario;
	}
	/**
	 * @return Devuelve esquema.
	 */

	/**
	 * @param esquema El esquema a establecer.
	 */
	public void setEsquema(Vector esquema) {
		this.esquema = esquema;
	}
	/**
	 * @return Devuelve mensajes.
	 */

	/**
	 * @param mensajes El mensajes a establecer.
	 */
	public void setMensajes(Vector mensajes) {
		this.mensajes = mensajes;
	}
	/**
	 * @return Devuelve parametros.
	 */

	/**
	 * @param parametros El parametros a establecer.
	 */
	public void setParametros(Vector parametros) {
		this.parametros = parametros;
	}

	public void inicioDia() throws Exception{
		ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(System.getProperty("user.home")+"/"+"media.obj"));
		GeneralParameters gp = (GeneralParameters)entrada.readObject();
		this.setTipoEncriptacion(gp.getTipoEncripcion());
		this.setClaveDESA(gp.getKeyDES_A().trim());
		this.setClaveDESB(gp.getKeyDES_B().trim());
	}


	/**
	 * @return Devuelve mapControl.
	 */
	public Map getMapControl() {
		return mapControl;
	}
	/**
	 * @param mapControl El mapControl a establecer.
	 */
	public void setMapControl(Map mapControl) {
		this.mapControl = mapControl;
	}
	/**
	 * @return Devuelve mapDiccionario.
	 */
	public Map getMapDiccionario() {
		return mapDiccionario;
	}
	/**
	 * @param mapDiccionario El mapDiccionario a establecer.
	 */
	public void setMapDiccionario(Map mapDiccionario) {
		this.mapDiccionario = mapDiccionario;
	}
	/**
	 * @return Devuelve mapEsquema.
	 */
	public Map getMapEsquema(){
		
		return mapEsquema;
	}
	/**
	 * @param mapEsquema El mapEsquema a establecer.
	 */
	public void setMapEsquema(Map mapEsquema) {
		this.mapEsquema = mapEsquema;
	}
	/**
	 * @return Devuelve mapMensajes.
	 */
	public Map getMapMensajes() {
		return mapMensajes;
	}
	/**
	 * @param mapMensajes El mapMensajes a establecer.
	 */
	public void setMapMensajes(Map mapMensajes) {
		this.mapMensajes = mapMensajes;
	}
	/**
	 * @return Devuelve mapParametros.
	 */
	public Map getMapParametros() {
		return mapParametros;
	}
	/**
	 * @param mapParametros El mapParametros a establecer.
	 */
	public void setMapParametros(Map mapParametros) {
		this.mapParametros = mapParametros;
	}
	

	/**
	 * @return Devuelve mapMensajeCompuesto.
	 */
	public Map getMapMensajeCompuesto() {
		return mapMensajeCompuesto;
	}
	/**
	 * @param mapMensajeCompuesto El mapMensajeCompuesto a establecer.
	 */
	public void setMapMensajeCompuesto(Map mapMensajeCompuesto) {
		this.mapMensajeCompuesto = mapMensajeCompuesto;
	}

	
	/**
	 * @return Devuelve mapEstilo.
	 */
	public Map getMapEstilo() {
		return mapEstilo;
	}
	/**
	 * @param mapEstilo El mapEstilo a establecer.
	 */
	public void setMapEstilo(Map mapEstilo) {
		this.mapEstilo = mapEstilo;
	}

	public String getRutaClasspath() {
		return rutaClasspath;
	}

	public void setRutaClasspath(String rutaClasspath) {
		this.rutaClasspath = rutaClasspath;
	}
	
	
	/**
	 * @return Devuelve tipoEncriptacion.
	 */
	public String getTipoEncriptacion() {
		return tipoEncriptacion;
	}
	/**
	 * @param tipoEncriptacion El tipoEncriptacion a establecer.
	 */
	public void setTipoEncriptacion(String tipoEncriptacion) {
		this.tipoEncriptacion = tipoEncriptacion;
	}
	/**
	 * @return Devuelve claveDESA.
	 */
	public String getClaveDESA() {
		return claveDESA;
	}
	/**
	 * @param claveDESA El claveDESA a establecer.
	 */
	public void setClaveDESA(String claveDESA) {
		this.claveDESA = claveDESA;
	}
	/**
	 * @return Devuelve claveDESB.
	 */
	public String getClaveDESB() {
		return claveDESB;
	}
	/**
	 * @param claveDESB El claveDESB a establecer.
	 */
	public void setClaveDESB(String claveDESB) {
		this.claveDESB = claveDESB;
	}
}