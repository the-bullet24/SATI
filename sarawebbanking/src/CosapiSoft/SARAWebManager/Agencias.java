/*
 * Creado el 19/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package CosapiSoft.SARAWebManager;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.ibm.math.BigDecimal;

import CosapiSoft.SARAWebBanking.*;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JDatabasen;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.Mensajes;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;
/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Agencias {
	private InicioSesion login;
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
	 * @return Devuelve f01_agencia.
	 */
	public String getF01_agencia() {
		return f01_agencia;
	}
	/**
	 * @param f01_agencia El f01_agencia a establecer.
	 */
	public void setF01_agencia(String f01_agencia) {
		this.f01_agencia = f01_agencia;
	}
	/**
	 * @return Devuelve f01_cantidad.
	 */
	public String getF01_cantidad() {
		return f01_cantidad;
	}
	/**
	 * @param f01_cantidad El f01_cantidad a establecer.
	 */
	public void setF01_cantidad(String f01_cantidad) {
		this.f01_cantidad = f01_cantidad;
	}
	/**
	 * @return Devuelve f01_fproceso.
	 */
	public String getF01_fproceso() {
		return f01_fproceso;
	}
	/**
	 * @param f01_fproceso El f01_fproceso a establecer.
	 */
	public void setF01_fproceso(String f01_fproceso) {
		this.f01_fproceso = f01_fproceso;
	}
	/**
	 * @return Devuelve f01_fultactualizacion.
	 */
	public String getF01_fultactualizacion() {
		return f01_fultactualizacion;
	}
	/**
	 * @param f01_fultactualizacion El f01_fultactualizacion a establecer.
	 */
	public void setF01_fultactualizacion(String f01_fultactualizacion) {
		this.f01_fultactualizacion = f01_fultactualizacion;
	}
	/**
	 * @return Devuelve f01_ncodagencia.
	 */
	public String getF01_ncodagencia() {
		return f01_ncodagencia;
	}
	/**
	 * @param f01_ncodagencia El f01_ncodagencia a establecer.
	 */
	public void setF01_ncodagencia(String f01_ncodagencia) {
		this.f01_ncodagencia = f01_ncodagencia;
	}
	/**
	 * @return Devuelve f01_ndesafiliaciones.
	 */
	public String getF01_ndesafiliaciones() {
		return f01_ndesafiliaciones;
	}
	/**
	 * @param f01_ndesafiliaciones El f01_ndesafiliaciones a establecer.
	 */
	public void setF01_ndesafiliaciones(String f01_ndesafiliaciones) {
		this.f01_ndesafiliaciones = f01_ndesafiliaciones;
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
	private java.util.Vector grid = null;
	public String error="";
	private String f01_fproceso="";
	private String f01_ncodagencia="";
	private String f01_agencia=""; 
	private String f01_cantidad="";
	private String f01_ndesafiliaciones="";
	private String f01_fultactualizacion="";
	private String total=""; 
	public String fecha="";
	
/**
 * @return Devuelve afirea.
 */


public boolean buscar() throws Exception {
	JDatabasen jd = new JDatabasen(login.driver, login.url, login.userid, login.password);
	JQuery db = new JQuery(jd.getConnection());
	try {
		error = "";
		boolean sw = buscar(db);
		return sw;
	} catch (Exception e) {
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		jd.close();
	}
}
private boolean buscar(JQuery db) throws Exception {
	try {
		
		//String query = //
		//"Select f01_ncodagencia, f01_fproceso, f01_agencia, f01_cantidad,  f01_ndesafiliaciones,  f01_fultactualizacion " + //
		//"From "+Constante.ESQUEMA2+".BNSERV_AFILAGENCIA "+
		//"Where f01_fproceso <= '"+ObjectUtil.fechaToTramaYYYYMMDD(getFecha())+"' "+
		//"order by f01_fproceso desc";
		
	    System.out.println("***--- Fecha de Consulta:"+ObjectUtil.fechaToTramaYYYYMMDD(getFecha()));
		String query = //
		"Select f01_ncodagencia, f01_agencia, Sum(f01_cantidad) As TotalAfil, Sum(f01_ndesafiliaciones) As TotalDesa " + //
		"From "+Constante.ESQUEMA2+".BNSERV_AFILAGENCIA "+ 
	    "   Where f01_fproceso <= '"+ObjectUtil.fechaToTramaYYYYMMDD(getFecha())+"' "+
	    "   Group By f01_ncodagencia, f01_agencia "+
	    "   order by f01_agencia";
	
		System.out.println("query............."+query);
		
		db.setQuery(query);
		db.executeQuery();
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			/**
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(db.stringValue(4));
			row.addElement(db.stringValue(5));
			row.addElement(db.stringValue(6));
			*/
			System.out.println("***--- Resultado AGENCIAS - Valor 1:"+db.stringValue(1));
			row.addElement(db.stringValue(1));
			System.out.println("***--- Resultado AGENCIAS - Valor 2:"+db.stringValue(2));
			row.addElement(db.stringValue(2));
			System.out.println("***--- Resultado AGENCIAS - Valor 3:"+db.stringValue(3));
			row.addElement(db.stringValue(3));
			System.out.println("***--- Resultado AGENCIAS - Valor 4:"+db.stringValue(4));
			row.addElement(db.stringValue(4));
			getGrid().addElement(row);
		}
		query = //
			"Select sum(f01_cantidad) " + //
			"From "+Constante.ESQUEMA2+".BNSERV_AFILAGENCIA "+
			"Where f01_fproceso <='"+ObjectUtil.fechaToTramaYYYYMMDD(getFecha())+"'"+
			"order by f01_fultactualizacion desc";
		
			System.out.println("query............."+query);
			
			db.setQuery(query);
			db.executeQuery();
			if (db.getResultSet().next()) {
				total = db.stringValue(1);
			}
			
		return true;
	} 
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\nBuscar - Log de Operaciones \n" + e.getMessage());
	}
}
public void next(int i) {
	f01_ncodagencia = ((java.util.Vector) getGrid().elementAt(i)).elementAt(0).toString();
	f01_agencia= ((java.util.Vector) getGrid().elementAt(i)).elementAt(1).toString();
	f01_cantidad = ((java.util.Vector) getGrid().elementAt(i)).elementAt(2).toString();
	f01_fproceso = ((java.util.Vector) getGrid().elementAt(i)).elementAt(3).toString();
	DecimalFormat myFormatter = new DecimalFormat("###,###.000");
	String a= myFormatter.format(new Integer(f01_cantidad)).toString();
	System.out.println("decimal....."+a);
	int io =a.length() - 4;
	f01_cantidad = a.substring(0,io).replace('.',',');
	
	//f01_ndesafiliaciones = ((java.util.Vector) getGrid().elementAt(i)).elementAt(4).toString();
	//f01_fultactualizacion = ((java.util.Vector) getGrid().elementAt(i)).elementAt(5).toString();
}
	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Devuelve grid.
	 */
	public java.util.Vector getGrid() {
		if (grid == null) {
			grid = new java.util.Vector(100, 100);
		}
		return grid;
	}
	/**
	 * @param grid El grid a establecer.
	 */
	public void setGrid(java.util.Vector grid) {
		this.grid = grid;
	}
	/**
	 * @return Returns the total.
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total The total to set.
	 */
	public void setTotal(String total) {
		this.total = total;
	}
}

