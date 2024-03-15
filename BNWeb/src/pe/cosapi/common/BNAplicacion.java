/*
 * Creado el 08/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.naming.NamingException;

import com.cosapisoft.sarawebbranch.server.GeneralParameters;

import pe.bn.telegiro.action.TelegiroAction;
import pe.cosapi.domain.*;
import pe.cosapi.sarabank.bean.DataSourceConnector;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class BNAplicacion implements Serializable{
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
	
	private Branch branch;
	private Map mapEstilo;
	
	private String rutaClasspath;

	 
	private String tipoEncriptacion;
	private String claveDESA;
	private String claveDESB;

	
	
	public BNAplicacion(){
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
	 * @return Devuelve control.
	 */
	public Vector getControl() throws Exception{
		this.control = new Vector();
				
		List lstControl = DAOFactory.getGeneraDAO().getControlTransaccion();
		for (int i = 0; i < lstControl.size(); i++) {
			ControlTransaccion controlTransaccion = (ControlTransaccion)lstControl.get(i);
			Vector temp = new Vector();
			temp.addElement(controlTransaccion.getCodTra());
			temp.addElement(controlTransaccion.getCodDic());
			temp.addElement(controlTransaccion.getCodChn());
			temp.addElement(controlTransaccion.getTypCtr());
			temp.addElement(controlTransaccion.getTxtCtr());
			temp.addElement(controlTransaccion.getOrdSnd());			
			this.control.addElement(temp);	
		}		
		return control;
	}
	/**
	 * @param control El control a establecer.
	 */
	public void setControl(Vector control) {
		this.control = control;
	}
	
	public Vector getCont() {
		return this.control;
	}
	/**
	 * @return Devuelve diccionario.
	 */
	public Vector getDiccionario() throws Exception{
		this.diccionario = new Vector();
		this.mapDiccionario = new HashMap();
		List lstDiccionario = DAOFactory.getGeneraDAO().getDiccionario();
		for (int i = 0; i < lstDiccionario.size(); i++) {
			Diccionario diccionario = (Diccionario)lstDiccionario.get(i);
			Vector temp = new Vector();
			temp.addElement(diccionario.getCodDic());
			temp.addElement(diccionario.getTxtDes());
			temp.addElement(diccionario.getNumLon());
			temp.addElement(diccionario.getTxtFor());
			temp.addElement(diccionario.getIdeDic());
			this.diccionario.addElement(temp);
			this.mapDiccionario.put(diccionario.getCodDic(),temp);
		}		
		return diccionario;
	}
	/**
	 * @param diccionario El diccionario a establecer.
	 */
	public void setDiccionario(Vector diccionario) {
		this.diccionario = diccionario;
	}
	
	public Vector getDicc() {
		return this.diccionario;
	}
	
	/**
	 * @return Devuelve esquema.
	 */
	public Vector getEsquema() throws Exception{
		this.esquema = new Vector();		
		
		List lstEsquema = DAOFactory.getGeneraDAO().getEsquema();
		for(int i = 0 ; i < lstEsquema.size(); i++){
			Esquema esquema = (Esquema)lstEsquema.get(i);
			Vector temp = new Vector();
			temp.addElement(esquema.getCodTra());
			temp.addElement(esquema.getTxtTra());
			temp.addElement("");
			temp.addElement(esquema.getCodFas());
			temp.addElement(esquema.getCodAlt());
			temp.addElement(esquema.getCodClaAlt());
			temp.addElement(esquema.getCodMetAlt());
			temp.addElement(esquema.getNumSeq());
			temp.addElement(esquema.getCodCla());
			temp.addElement(esquema.getCodMet());
			temp.addElement(esquema.getTxtCla());
			temp.addElement(esquema.getTxtMet());
			temp.addElement(esquema.getNumSeqArg());
			temp.addElement(esquema.getTxtArg());
			this.esquema.addElement(temp);
		}		
		return esquema;
	}
	/**
	 * @param esquema El esquema a establecer.
	 */
	public void setEsquema(Vector esquema) {
		this.esquema = esquema;
	}
	
	public Vector getEsq() {
		return this.esquema;
	}
	/**
	 * @return Devuelve mensajes.
	 */
	public Vector getMensajes() throws Exception{
		this.mensajes = new Vector();
		this.mapMensajes = new HashMap();
		this.mapMensajeCompuesto = new HashMap();
		List lstMensaje = DAOFactory.getGeneraDAO().getMensajeria();
		for (int i = 0; i < lstMensaje.size(); i++) {		
			Mensajeria mensaje = (Mensajeria)lstMensaje.get(i);
			Vector temp = new Vector();
			temp.addElement(mensaje.getCodGrp());
			temp.addElement(mensaje.getCodMsg());
			temp.addElement(mensaje.getDesMsg());
			temp.addElement(mensaje.getIdeMsg());
			this.mensajes.addElement(temp);
			this.mapMensajes.put(mensaje.getCodMsg().trim(),temp);
			this.mapMensajeCompuesto.put(mensaje.getCodGrp().trim()+"-"+mensaje.getCodMsg().trim(),temp);
		}
		//**********************************************
		List lstMensajeApp = DAOFactory.getGeneraDAO().getMensajeriaApp();
		for (int i = 0; i < lstMensajeApp.size(); i++) {
			Mensajeria mensajeApp = (Mensajeria)lstMensajeApp.get(i);
			Vector tempApp = new Vector();
			tempApp.addElement(mensajeApp.getCodGrp());
			tempApp.addElement(mensajeApp.getCodMsg());
			tempApp.addElement(mensajeApp.getDesMsg());
			tempApp.addElement(mensajeApp.getIdeMsg());
			this.mensajes.addElement(tempApp);
			this.mapMensajes.put(mensajeApp.getCodMsg().trim(),tempApp);
			this.mapMensajeCompuesto.put(mensajeApp.getCodGrp().trim()+"-"+mensajeApp.getCodMsg().trim(),tempApp);
		}
		//**********************************************
		List lstMensajesHst = DAOFactory.getGeneraDAO().getMensajesHost();
		for (int i = 0; i < lstMensajesHst.size(); i++) {
			Mensajeria mensajeHst = (Mensajeria)lstMensajesHst.get(i);
			Vector temphst = new Vector();
			temphst.addElement(mensajeHst.getCodGrp());
			temphst.addElement(mensajeHst.getCodMsg());
			temphst.addElement(mensajeHst.getDesMsg());
			temphst.addElement(mensajeHst.getIdeMsg());
			this.mensajes.addElement(temphst);
			this.mapMensajes.put(mensajeHst.getCodMsg().trim(),temphst);
			this.mapMensajeCompuesto.put(mensajeHst.getCodGrp().trim()+"-"+mensajeHst.getCodMsg().trim(),temphst);
		}
		/**
		*Iterator it = mapMensajeCompuesto.entrySet().iterator();
		*while (it.hasNext()) {
		*    Map.Entry e = (Map.Entry)it.next();
		*    System.out.println("LISTA MAPA COMPUESTO:"+e.getKey() + "-" + e.getValue());
		*}
		*/
		
		return mensajes;
	}
	
	
	/**
	 * @param mensajes El mensajes a establecer.
	 */
	public void setMensajes(Vector mensajes) {
		this.mensajes = mensajes;
	}
	/**
	 * @return Devuelve parametros.
	 */
	public Vector getParametros() throws Exception{

		this.parametros = new Vector();
		
		List lstParametro = DAOFactory.getGeneraDAO().getParametro();
		for (int i = 0; i < lstParametro.size(); i++) {
			Esquema esquema = (Esquema) lstParametro.get(i);
			Vector temp = new Vector();
			temp.addElement(esquema.getCodTra());
			temp.addElement(esquema.getCodMetAlt());
			temp.addElement(esquema.getCodClaAlt());
			temp.addElement(esquema.getTxtCla());
			temp.addElement(esquema.getTxtMet());
			temp.addElement(esquema.getNumSeqArg());
			temp.addElement(esquema.getTxtArg());
			this.parametros.addElement(temp);
		}		
		return parametros;
	}
	/**
	 * @param parametros El parametros a establecer.
	 */
	public void setParametros(Vector parametros) {
		this.parametros = parametros;
	}
	
	public Vector getParam() {
		return this.parametros;
	}

	public void inicioDia() throws Exception{
		//System.out.println("SYSTEM USER HOME: "+ System.getProperty("user.home"));
		/**ObjectInputStream entrada=null;**/
		GeneralParameters gp= new GeneralParameters();
		//System.out.println("Antes de traer claves");
		gp=DAOFactory.getGeneraDAO().getClaves();
		//System.out.println("Despues de traer claves");
		/**GeneralParameters gp=null;**/
		/*
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;
	    
	    try{
	    	
	    	System.out.println("Inicio - Leyendo claves en la base de datos...");
		    conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false);
		    sqlstmt = "select KEYPINPAD,KEYMAC,KEYDES_A,KEYDES_B,typencripcion from pv_swb.tbrainf where CODBRA='0001'";
		    pstmt = conn.prepareStatement(sqlstmt);
		    ResultSet result = pstmt.executeQuery();
		    
		    
		    while(result.next()){
		    	gp.setKeyPinPad(result.getString(1));
		    	gp.setKeyMAC(result.getString(2));
		    	gp.setKeyDES_A(result.getString(3));
		    	gp.setKeyDES_B(result.getString(4));
		    	gp.setTipoEncripcion(result.getString(5));
		    }
		    
		    
	    }catch(Exception e){
	    		 System.out.println(e.getMessage());
	    }finally{
	    	try {
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(e);
	    	}	
	    }	
	    */
		/**
		try {
			entrada = new ObjectInputStream(new FileInputStream(System.getProperty("user.home")+"/"+"media.obj"));
			gp = (GeneralParameters)entrada.readObject();
		} catch (FileNotFoundException e) {
			logger.warn("EXCEPTION: FileNotFoundException ...El archivo media.obj no existe \n" + e);
		} catch (IOException e) {
			logger.warn("EXCEPTION: FileNotFoundException ...Error de lectura al archivo media.obj \n" + e);
		} catch (ClassNotFoundException e) {
			logger.warn("EXCEPTION: FileNotFoundException ...No se encontró la clase \n" + e);
		}catch (Exception e) {
			logger.warn("EXCEPTION: GENERICO: " + e);
		}
		
		try {
			String IP=InetAddress.getLocalHost().getHostAddress();
			System.out.println("IP HOST: " + IP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} **/
		this.setTipoEncriptacion(gp.getTipoEncripcion());
		//System.out.println("Leyendo Tipo de encriptacion desde DB: "+gp.getTipoEncripcion());
		this.setClaveDESA(gp.getKeyDES_A().trim());
		//if(Constante.VER_LOG) System.out.println("Leyendo getKeyDES_A desde DB: "+gp.getKeyDES_A());
		this.setClaveDESB(gp.getKeyDES_B().trim());
		//if(Constante.VER_LOG) System.out.println("Leyendo getKeyDES_B desde DB: "+gp.getKeyDES_B());
	}
	
	public void cargar() throws Exception{
		this.branch = DAOFactory.getGeneraDAO().getBranch(Constante.COD_BRANCH);
		//System.out.println("this.branch:"+this.branch);
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
	
	public Vector getDiccionarioPorCodigo(String codigo) throws Exception{
		
		if(!mapDiccionario.containsKey(codigo.trim())){
			int  cod=Integer.parseInt(codigo.trim());
			if(cod>=500 && cod<600){// 500's
				//System.out.println("Error de Diccionario " + codigo);
				Vector temp = new Vector();
				temp.addElement(codigo);
				temp.addElement("numerico1");
				temp.addElement("15");
				temp.addElement("");
				temp.addElement("N");
				return (temp);
			}else if(cod>=600 && cod<700){// 600's
				//System.out.println("Error de Diccionario " + codigo);
				Vector temp = new Vector();
				temp.addElement(codigo);
				temp.addElement("cadena1");
				temp.addElement("30");
				temp.addElement("");
				temp.addElement("A");
				return (temp);
			}else if (cod>= 700 && cod <800){// 700's
				//System.out.println("Error de Diccionario " + codigo);
				Vector temp = new Vector();
				temp.addElement(codigo);
				temp.addElement("numerico2");
				temp.addElement("15");
				temp.addElement("");
				temp.addElement("N");
				return (temp);
			}else if(cod >=800){// 800's
				//System.out.println("Error de Diccionario " + codigo);
				Vector temp = new Vector();
				temp.addElement(codigo);
				temp.addElement("cadena2");
				temp.addElement("100");
				temp.addElement("");
				temp.addElement("A");
				return (temp);
			}
		   
		}
		return (Vector) mapDiccionario.get(codigo);		
	}


	public Vector getMensajePorCodigo(String codigoGrupo,String codigoMensaje) throws Exception{
	    
		if(!mapMensajeCompuesto.containsKey(codigoGrupo+"-"+codigoMensaje)){
		    Vector temp = new Vector();
		    temp.addElement("");
		    temp.addElement("");
		    temp.addElement("");
		    temp.addElement("");
		    return (Vector) temp;
	 	} else {
		    return (Vector) mapMensajeCompuesto.get(codigoGrupo+"-"+codigoMensaje);
		}
	}
	public Vector getMsjesHost(String codigoGrupo,String codigoMensaje) throws Exception{
		if(!mapMensajeCompuesto.containsKey(codigoGrupo+"-"+codigoMensaje)){
			codigoGrupo="**";
			codigoMensaje="****";
		}
					
		return (Vector) mapMensajeCompuesto.get(codigoGrupo+"-"+codigoMensaje);		
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
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
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