package CosapiSoft.SARAWebManager;


import java.sql.Connection;
import java.sql.ResultSetMetaData;

import pe.cosapi.common.ConnectionPool;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;
import CosapiSoft.SARAWebBanking.JDatabase;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBanking.JQuery;
import CosapiSoft.SARAWebBanking.InicioSesion;

public class AfiliacionesCliente {
	//
	private InicioSesion login;
	public java.util.Vector afi = null;
	private String modulo="manager";
	private String desser = "";
	private String datpro = "";
	private String horpro = "";
	private String numprdsrc = "";
	private String error = "";
	private String msgerror = "";
	private String cuenta = "";
	private String operacion = "";
	private String codofi = "";
	private String oficina = "";
	private String codcaje = "";
	
public String getDatpro() {
	return datpro.trim();
}

public String getError() {
	return error;
}

public String getHorpro() {
	return horpro.trim();
}

public InicioSesion getLogin() {
	return this.login;
}

public String getNumprdsrc() {
	return numprdsrc;
}

public String getMsgError() {
	return msgerror;
}


public String getUrlLogDeOperaciones() {
	if (modulo.toUpperCase().equals("MANAGER"))
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
	else
		return JspServlet.DIARIO_ELECTRONICO_SERVLET + "?BtnDia=Buscar&Modulo=Manager&ChkLog=";
}


public void AfiporCliente() throws Exception {
	ConnectionPool cnx= new ConnectionPool();
	Connection vc= cnx.getConnection();
	JQuery db = new JQuery(vc);
	int i=0;
	try {
		afi = new java.util.Vector();
		String query="Select  A.F22_NTARJETA, A.F22_TOPERACION, A.F22_CCUENTA, A.F22_FPROCESO, A.F22_HPROCESO, A.F22_COFICINA, B.F01_AOFICINA, F22_CUSUARIO " + //
		"From NACION.BNATF22 A inner join NACION.BNMAF01 B on A.F22_COFICINA =B.F01_COFICINA " + //
		"where A.F22_NTARJETA='"+getNumprdsrc()+"' AND B.F01_CAREA=0 AND B.F01_CSUBX=0 AND B.F01_BOFICINA=0";
		
		db.setQuery(query);
		db.executeQuery();
		System.out.println(query);
		while (db.getResultSet().next()) {
			java.util.Vector row = new java.util.Vector();
			row.addElement(db.stringValue(1));
			row.addElement(db.stringValue(2));
			row.addElement(db.stringValue(3));
			row.addElement(ObjectUtil.getYYYYMMDDFormateada(db.stringValue(4)));
			row.addElement(ObjectUtil.getHHMMSSformateado(db.stringValue(5)));
			row.addElement(db.stringValue(6));
			if(db.stringValue(6).trim().equals("0")){
				row.addElement("INTERNET");
			}else{
				row.addElement(db.stringValue(7).equals("")?"DESCONOCIDO":db.stringValue(7).equals(null)?"DESCONOCIDO":db.stringValue(7).equals("null")?"DESCONOCIDO":db.stringValue(7));
			}
			row.addElement(db.stringValue(8));
			afi.addElement(row);
			
		}
	} catch (Exception e) {
		throw new Exception("\nLoadComboUsr() - Log de Operaciones \n" + e.getMessage());
	} finally {
		db.close();
		cnx.free(vc);
	}
}


public void nextafi(int i) {
	numprdsrc = ((java.util.Vector) getAfi().elementAt(i)).elementAt(0).toString();
	operacion = ((java.util.Vector) getAfi().elementAt(i)).elementAt(1).toString();
	cuenta = ((java.util.Vector) getAfi().elementAt(i)).elementAt(2).toString();
	datpro = ((java.util.Vector) getAfi().elementAt(i)).elementAt(3).toString();
	horpro = ((java.util.Vector) getAfi().elementAt(i)).elementAt(4).toString();
	codofi = ((java.util.Vector) getAfi().elementAt(i)).elementAt(5).toString();
	oficina = ((java.util.Vector) getAfi().elementAt(i)).elementAt(6).toString();
	codcaje = ((java.util.Vector) getAfi().elementAt(i)).elementAt(7).toString();
	
}


public void setNumprdsrc(String newValue) {
	this.numprdsrc = newValue;
}


public void setMsgerror(String newValue) {
	this.msgerror = newValue;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */

public void setError(String newValue) {
	this.error = newValue;
}


public void setHorpro(String newValue) {
	if(horpro!=null || !horpro.trim().equals(""))
	this.horpro = newValue;
	else
		this.horpro=" ";
}

public void setLogin(InicioSesion newValue) {
	this.login = newValue;
}




public java.util.Vector getAfi() {
		return afi;
}

public void setAfi(java.util.Vector afi) {
		this.afi = afi;
	}

public String getDesser() {
		return desser;
	}
	/**
	 * @param desser El desser a establecer.
	 */
public void setDesser(String newValue) {
	if(desser!=null || !desser.trim().equals(""))
		this.desser = newValue;
		else
			this.desser=" ";
	}
public void setDatpro(String newValue) {
	if(datpro!=null || !datpro.trim().equals(""))
		this.datpro = newValue;
		else
			this.datpro=" ";
	}

	/**
	 * @return Returns the codofi.
	 */
	public String getCodofi() {
		return codofi;
	}
	/**
	 * @param codofi The codofi to set.
	 */
	public void setCodofi(String codofi) {
		this.codofi = codofi;
	}
	/**
	 * @return Returns the cuenta.
	 */
	public String getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta The cuenta to set.
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Returns the oficina.
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina The oficina to set.
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return Returns the operacion.
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion The operacion to set.
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * @return Returns the codcaje.
	 */
	public String getCodcaje() {
		return codcaje;
	}
	/**
	 * @param codcaje The codcaje to set.
	 */
	public void setCodcaje(String codcaje) {
		this.codcaje = codcaje;
	}
}


