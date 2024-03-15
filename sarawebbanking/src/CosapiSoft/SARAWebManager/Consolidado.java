/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package CosapiSoft.SARAWebManager;

import java.text.DecimalFormat;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.Mensajes;
import pe.cosapi.common.*;


/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Consolidado {
	private InicioSesion login;
	private String fcarga="";
	private String ntarjservnosolic="";
	private String ntarjservsolic="";
	private String ntarjservactivado="";
	private String ntarjservanulado="";
	private String ntarjact="";
	private String fultactualizacion="";
	private String ntarservrealizado="";
	public String error="";
/**
 * @return Devuelve afirea.
 */


public boolean mostrar() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = mostrar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean mostrar(JQuery db) throws Exception {
	try {
		DecimalFormat myFormatter = new DecimalFormat("###,###.000");
		String query = //
		"Select fcarga,ntarjservnosolic,ntarjservsolic,ntarjservactivado,ntarjservanulado,ntarjact,fultactualizacion,CONCAT('0', (ntarjservsolic+ntarjservactivado)) As ntarservrealizado " + //
		"From "+Constante.ESQUEMA2+".BNSERV_ESTADISTICO "+
		"Where fcarga in (select max(t.fcarga) " +
						 "from "+Constante.ESQUEMA2+".BNSERV_ESTADISTICO t)";
		db.setQuery(query);
		db.executeQuery();
		if (!db.getResultSet().next()) {
			error = Mensajes.getMessage(Mensajes.NO_EXISTE_REGISTROS);
			return false;
		}
		fcarga = db.stringValue(1);
		System.out.println("***--- Consolidado 1:"+fcarga);
		ntarjservnosolic = db.stringValue(2);
		String a= myFormatter.format(new Integer(ntarjservnosolic)).toString();
		int io =a.length() - 4;
		ntarjservnosolic = a.substring(0,io).replace('.',',');
		System.out.println("***--- Consolidado 2:"+ntarjservnosolic);
		
		ntarjservsolic = db.stringValue(3);
		String e= myFormatter.format(new Integer(ntarjservsolic)).toString();
		int eo =e.length() - 4;
		ntarjservsolic = e.substring(0,eo).replace('.',',');
		System.out.println("***--- Consolidado 3:"+ntarjservsolic);
		
		ntarjservactivado = db.stringValue(4);
		String o= myFormatter.format(new Integer(ntarjservactivado)).toString();
		int oo =o.length() - 4;
		ntarjservactivado = o.substring(0,oo).replace('.',',');
		System.out.println("***--- Consolidado 4:"+ntarjservactivado);
		
		ntarjservanulado = db.stringValue(5);
		String u= myFormatter.format(new Integer(ntarjservanulado)).toString();
		int uo =u.length() - 4;
		ntarjservanulado = u.substring(0,uo).replace('.',',');
		System.out.println("***--- Consolidado 5:"+ntarjservanulado);
		
		ntarjact=db.stringValue(6);
		String j= myFormatter.format(new Integer(ntarjact)).toString();
		int jo =j.length() - 4;
		ntarjact = j.substring(0,jo).replace('.',',');
		System.out.println("***--- Consolidado 6:"+ntarjact);
		
		fultactualizacion = db.stringValue(7);
		System.out.println("***--- Consolidado 7:"+fultactualizacion);
		
		ntarservrealizado=db.stringValue(8);
		//@DPS String k= myFormatter.format(new Integer(ntarjact)).toString();
		String k= myFormatter.format(new Integer(ntarservrealizado)).toString();
		int ko =k.length() - 4;
		ntarservrealizado = k.substring(0,ko).replace('.',',');
		System.out.println("***--- Consolidado 8:"+ntarservrealizado);
		
		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}



	/**
	 * @return Devuelve error.
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error El error a establecer.
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return Devuelve fcarga.
	 */
	public String getFcarga() {
		return fcarga;
	}
	/**
	 * @param fcarga El fcarga a establecer.
	 */
	public void setFcarga(String fcarga) {
		this.fcarga = fcarga;
	}
	/**
	 * @return Devuelve fultactualizacion.
	 */
	public String getFultactualizacion() {
		return fultactualizacion;
	}
	/**
	 * @param fultactualizacion El fultactualizacion a establecer.
	 */
	public void setFultactualizacion(String fultactualizacion) {
		this.fultactualizacion = fultactualizacion;
	}
	/**
	 * @return Devuelve login.
	 */
	public InicioSesion getLogin() {
		return login;
	}
	/**
	 * @param login El login a establecer.
	 */
	public void setLogin(InicioSesion login) {
		this.login = login;
	}
	/**
	 * @return Devuelve ntarjact.
	 */
	public String getNtarjact() {
		return ntarjact;
	}
	/**
	 * @param ntarjact El ntarjact a establecer.
	 */
	public void setNtarjact(String ntarjact) {
		this.ntarjact = ntarjact;
	}
	/**
	 * @return Devuelve ntarjservactivado.
	 */
	public String getNtarjservactivado() {
		return ntarjservactivado;
	}
	/**
	 * @param ntarjservactivado El ntarjservactivado a establecer.
	 */
	public void setNtarjservactivado(String ntarjservactivado) {
		this.ntarjservactivado = ntarjservactivado;
	}
	/**
	 * @return Devuelve ntarjservanulado.
	 */
	public String getNtarjservanulado() {
		return ntarjservanulado;
	}
	/**
	 * @param ntarjservanulado El ntarjservanulado a establecer.
	 */
	public void setNtarjservanulado(String ntarjservanulado) {
		this.ntarjservanulado = ntarjservanulado;
	}
	/**
	 * @return Devuelve ntarjservnosolic.
	 */
	public String getNtarjservnosolic() {
		return ntarjservnosolic;
	}
	/**
	 * @param ntarjservnosolic El ntarjservnosolic a establecer.
	 */
	public void setNtarjservnosolic(String ntarjservnosolic) {
		this.ntarjservnosolic = ntarjservnosolic;
	}
	/**
	 * @return Devuelve ntarjservsolic.
	 */
	public String getNtarjservsolic() {
		return ntarjservsolic;
	}
	/**
	 * @param ntarjservsolic El ntarjservsolic a establecer.
	 */
	public void setNtarjservsolic(String ntarjservsolic) {
		this.ntarjservsolic = ntarjservsolic;
	}
	/**
	 * @return Devuelve ntarservrealizado.
	 */
	public String getNtarservrealizado() {
		return ntarservrealizado;
	}
	/**
	 * @param ntarservrealizado El ntarservrealizado a establecer.
	 */
	public void setNtarservrealizado(String ntarservrealizado) {
		this.ntarservrealizado = ntarservrealizado;
	}
}
