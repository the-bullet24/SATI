package com.cosapisoft.sarawebbanking.admin;


import java.io.*;
import java.net.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.soap.SOAPException;

import pe.cosapi.common.*;
import pe.cosapi.system.services.*;
import CosapiSoft.SARAWebManager.*;
import com.cosapisoft.sarawebbranch.common.beans.*;
import com.cosapisoft.sarawebbranch.server.*;
import com.ibm.bn.InterfazServicios_PortTypeProxy;

public class EjecucionDeProcesoServlet extends HttpServlet implements Servlet,Serializable{
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public EjecucionDeProcesoServlet() {
		super();
	}	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}
	
	public synchronized static boolean esEjecutable(){
		//log.warn("Entro a la validacion");
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	     	    
	    try{	    	
				SimpleDateFormat formatter;
				Date data;
				Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
				//Seteamos la fecha
				//log.info("Fecha actual en el sistema: " + fechaActual);
				formatter = new SimpleDateFormat("HH:mm:ss");
				data = new Date(fechaActual.getTime());
				String hora = formatter.format(data);
				//log.info("Hora actual en el sistema formateado: " + hora);
			    conn = DataSourceConnector.getInstance().getConnection();
				sqlstmt = "select 'X'" +
						" from pv_swb.tbrainf" +
						" where houini =  '"+hora+"'";
				
				pstmt = conn.prepareStatement(sqlstmt);
				ResultSet result = pstmt.executeQuery();
				if (result.next()){
					//log.warn("RETORNÓ TRUE EN JOB INICIO DE DIA");
					conn.close();
					return true;
				}
				else{
					//log.warn("RETORNÓ FALSE EN JOB INICIO DE DIA");
					conn.close();
					return false;
				}
	    	
	    }catch(NamingException e){
	        System.out.println(""+e);
	    }catch (SQLException e){
	        System.out.println(""+e);
	    }finally{
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(""+e);
	    		System.out.println(e.getMessage());
	    	}	
	 }	
		
		return false;
	}
	
	
	private void performTask(HttpServletRequest req, HttpServletResponse res){
		String processID = "";
		String paginaDestino = "";
		String msgError = "";
		try {
			HttpSession session = req.getSession(false);
			if (session == null){
				session = req.getSession(true);
			}
			if (session.getAttribute("currentUser") == null){
				NavigationBean navigationBean = new NavigationBean();
				navigationBean.setMessageError("Ha ocurrido un error inesperado");
				session.setAttribute("navigationBean", navigationBean);
				paginaDestino = NavigationConstant.PAG_ERROR;
			}else{
				((NavigationBean) req.getSession().getAttribute("navigationBean")).setMessageError("");
				String action = req.getParameter("actioncommand");
				if (action.equals(NavigationConstant.ID_ACEPTAR)){
					processID = ((ProcessBean)req.getSession().getAttribute("processBean")).getProcessID();
					if (processID.equals(NavigationConstant.ID_INICIO_DIA)){
						msgError = ejecutarInicioDeDia();
						if (msgError.equals("")){
							((ProcessBean) req.getSession().getAttribute("processBean")).setMessage("La Ejecución del Proceso de Inicio de Día ha finalizado con Exito");
							((NavigationBean) req.getSession().getAttribute("navigationBean")).setTargetPage(NavigationConstant.PAG_INICIO_DIA);
							paginaDestino = NavigationConstant.PAG_EXITO;
						}else{
							cambiarEstadoBloqueoAgencia(false);//Desbloquear
							((NavigationBean) req.getSession().getAttribute("navigationBean")).setMessageError(msgError);
							paginaDestino = NavigationConstant.PAG_EJECUCION;
						}
					}else if (processID.equals(NavigationConstant.ID_RE_INICIO_DIA)){
						msgError = ejecutarReInicioDeDia();
						if (msgError.equals("")){
							((ProcessBean) req.getSession().getAttribute("processBean")).setMessage("La Ejecución del Proceso de Re-Inicio de Día ha finalizado con Exito");
							((NavigationBean) req.getSession().getAttribute("navigationBean")).setTargetPage(NavigationConstant.PAG_INICIO_DIA);
							paginaDestino = NavigationConstant.PAG_EXITO;
						}else{
							cambiarEstadoBloqueoAgencia(false);//Desbloquear
							((NavigationBean) req.getSession().getAttribute("navigationBean")).setMessageError(msgError);
							paginaDestino = NavigationConstant.PAG_EJECUCION;
						}
					}
					/*
					else if (processID.equals(NavigationConstant.ID_FIN_DIA)){
						msgError = ejecutarFinDeDia(req);
						if (msgError.equals("")){
							((ProcessBean) req.getSession().getAttribute("processBean")).setMessage("La Ejecución del Proceso de Fin de Día ha finalizado con Exito");
							((NavigationBean) req.getSession().getAttribute("navigationBean")).setTargetPage(NavigationConstant.PAG_INICIO_DIA);
							paginaDestino = NavigationConstant.PAG_EXITO;
						}else{
							((NavigationBean) req.getSession().getAttribute("navigationBean")).setMessageError(msgError);
							paginaDestino = NavigationConstant.PAG_EJECUCION;
						}
					}*/
					else{
						msgError = "El Proceso Inválido";
						((NavigationBean) req.getSession().getAttribute("navigationBean")).setMessageError(msgError);
						paginaDestino = NavigationConstant.PAG_EJECUCION;
					}
				}else if (action.equals(NavigationConstant.ID_CANCELAR)){
					// paginaDestino = NavigationConstant.PAG_INICIO_DIA;
					paginaDestino = "/sarawebbanking/SARAWebBanking/body.jsp";
				}					
			}
			callPage(req, res, paginaDestino);
		}catch(IOException e){
		    System.out.println(""+e);
			// System.out.println(e.getMessage());
		}catch (ServletException e){
		    System.out.println(""+e);
			// System.out.println(e.getMessage());
		}
	}
	
	private void callPage(HttpServletRequest req, HttpServletResponse res, String destino) throws ServletException, IOException{
		//RequestDispatcher rd = this.getServletConfig().getServletContext().getRequestDispatcher(destino);
		//rd.forward(req, res);
		res.sendRedirect(destino);
	}
	/*
	private String actualizarSistema(){
		try{
			System.out.println("Actualizacion del Sistema...");
			InitialParametersInfo pi = new InitialParametersInfo();
			InitialParametersServlet.loadAgenciaParameters(pi);
			
			String nombreCarpetaDistribucion = pi.getPathDistribucion();
			String nombreArchivoDistribucion = nombreCarpetaDistribucion +"//swb.lst";
			System.out.println("Leyendo "+ nombreArchivoDistribucion);
			File archivoDistribucion = new File(nombreArchivoDistribucion);
			if (archivoDistribucion.exists()){
				java.io.BufferedReader input = null;
	
				input = new BufferedReader( new FileReader(archivoDistribucion));
				String line = null; 
				while (( line = input.readLine()) != null){
					String fileInfo[] =line.split(",");
					String archivo = fileInfo[0].trim();
					String carpetaDestino = fileInfo[1].trim();
					if (carpetaDestino.substring(0,1).equals(".")){ //Se considerá relativo al webcontent
						carpetaDestino = getServletContext().getRealPath(carpetaDestino.substring(1));
					}
					File archivoOrigen = new File(nombreCarpetaDistribucion,archivo);
					File archivoDestino = new File(carpetaDestino,archivo);
					copyFile(archivoOrigen,archivoDestino);
				}
			}else{
				System.out.println("actualizarSistema: no hay archivos por distribuir");
			}
			System.out.println("Actualizacion del Sistema finalizada");
			return "";
		}catch (Exception e){
			System.out.println("actualizarSistema: "+e.getMessage());
			return "Ocurrió un error inesperado este proceso";
		}
	}
	
	public void copyFile(File in, File out) throws FileNotFoundException, IOException{
		try {
			FileInputStream fis  = new FileInputStream(in);
			FileOutputStream fos = new FileOutputStream(out);
			byte[] buf = new byte[1024];
			int i = 0;
			while((i=fis.read(buf))!=-1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
		}catch (FileNotFoundException e){
			System.out.println("copyFile ("+in.getName()+ "): "+e.getMessage());
			throw e;
		}catch (IOException e){
			System.out.println("copyFile ("+in.getName()+ "): "+e.getMessage());
			throw e;
		}
	}
	*/
	final static boolean obtenerEstadoBloqueoAgencia() throws Exception{
		boolean estadoBloqueo = false;
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;	     	    
	    try{
	    	// System.out.println("Verificacion del estado de bloqueo de la Agencia...");
		    conn = DataSourceConnector.getInstance().getConnection();
			sqlstmt = "select flgblkbra from pv_swb.tbrainf";
			pstmt = conn.prepareStatement(sqlstmt);
			ResultSet result = pstmt.executeQuery();
			if (result.next()){
				if (result.getString("flgblkbra").equals("1")){
					estadoBloqueo = true;
				}else{
					estadoBloqueo = false;
				}
			}
			// System.out.println("Verificacion del estado de bloqueo de la Agencia finalizada");
			return estadoBloqueo;
	    }catch(NamingException e){
	    	// System.out.println("obtenerEstadoBloqueoAgencia: " + e.getMessage());
	        System.out.println(""+e);
	    	throw new Exception("No se pudo obtener el estado de Bloqueo de la Agencia");
	    }catch (SQLException e){
	    	// System.out.println("obtenerEstadoBloqueoAgencia: " + e.getMessage());
	        System.out.println(""+e);
	    	throw new Exception("No se pudo obtener el estado de Bloqueo de la Agencia");
	    }finally{
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(""+e);
	    		// System.out.println(e.getMessage());
	    	}	
	    }	
	}
	
	private String actualizarFechaProcesoDesdeHost(){
		try{
			int posFechaProceso = 3;
			Connection conn = null;		
		    String sqlstmt = "";
		    PreparedStatement pstmt = null;
		    
			String trama ="ABC30042007DEF......."; //Para Pruebas
			String fechaProceso = trama.substring(posFechaProceso, posFechaProceso+8);
			fechaProceso = fechaProceso.substring(4,8) + fechaProceso.substring(2,4) + fechaProceso.substring(0,2);
		    try{	    	
			    conn = DataSourceConnector.getInstance().getConnection();
				sqlstmt = "update pv_swb.tbrainf set datpro = ?";
				pstmt = conn.prepareStatement(sqlstmt);
		    	pstmt.setString(1,fechaProceso);
		    	pstmt.executeUpdate();
				return "";
		    }catch(NamingException e){
		        System.out.println(""+e);
		    	// System.out.println("actualizarFechaProceso: " + e.getMessage());
		    	return "Error al actualizar la Fecha de Proceso";
		    }catch (SQLException e){
		        System.out.println(""+e);
		    	// System.out.println("actualizarFechaProceso: " + e.getMessage());
		    	return "Error al actualizar la Fecha de Proceso";
		    }finally{
		    	try {	    		 
		    		conn.close();
		    	}catch (SQLException e){
		    	    System.out.println(""+e);
		    		// System.out.println(e.getMessage());
		    	}	
		    }
		}catch (Exception e){
		    System.out.println(""+e);
			// System.out.println("actualizarFechaProceso: "+e.getMessage());
			return "Error en la actualización de la Fecha de Proceso";
		}
	}
	
	private static String actualizarDatosAgenciaDesdeHost(){
		try{
			 System.out.println("Actualización de Datos de Agencia Virtual...");
			String msgError = "";
			InitialParametersInfo pi = new InitialParametersInfo();			

			InitialParametersServlet.loadAgenciaParameters(pi);
			HostServer.configTrxClaves = pi.getConfiguracionTrxClaves();
			byte[] trama = HostServer.prepareHeaderAscii("0013", "0000", "0000");
			
			String cuerpo = TramaUtil.getStringDecimalEbcdic(new String(trama));
			Map mapResp = null;
			String tramaCodificada = null;
			String codResp = null;
			
			try{				
				
				 javax.xml.rpc.holders.StringHolder stringHolder = new StringHolder();
			       javax.xml.rpc.holders.IntHolder intHolder = new IntHolder();
			       String value = "";
			        Map output = new HashMap();     
				
				   InterfazServicios_PortTypeProxy proxy = new InterfazServicios_PortTypeProxy();
		        
		            String codigoWEBSERVICE = ObjectUtil.getCodigoTRX();
		          

		            proxy.enviarTramaConsulta(codigoWEBSERVICE, cuerpo, stringHolder, intHolder);
		            value = stringHolder.value;
		            
		            if (intHolder.value != 0)
		            {
//		                
		            	System.out.println("Entro exception");
//		                throw new SOAPException(String.valueOf(intHolder.value), String.valueOf(intHolder.value));
		            }
		            
		          
		            //output.put("tramaRespuesta", value);
		            //output.put("codResp", String.valueOf(intHolder.value));
		            output.put("tramaRespuesta", value);
		            output.put("codResp", ""+intHolder.value);
				
			
				mapResp = output;
				
				//mapResp = UtilWebServices.getInstance().callNewSendHost(cuerpo);
				tramaCodificada  = (String)mapResp.get("tramaRespuesta");
				codResp 			= (String)mapResp.get("codResp");
				if(Constante.VER_LOG) System.out.println("mapResp: "+mapResp);
			}
			catch(Exception e){
				cambiarEstadoBloqueoAgencia(false);//Desbloquear
			    System.out.println(""+e);
				msgError = "Error al recibir trama de claves " + e;
				return msgError;
			}
			if(Constante.VER_LOG) System.out.println("HostServer.claveDeClaves: "+HostServer.claveDeClaves);
			
			byte[] asciiEncriptado		= TramaUtil.getBytesTrama(tramaCodificada);
			
			
			
			String cabecera = TramaUtil.decodificarDecimal2Ebcdic(tramaCodificada);	
			if(Constante.VER_LOG) System.out.println("cabecera: "+cabecera);
			if (false){
				msgError = "Error al recibir trama de claves ";
			}
			else{
				GeneralParameters gp = new GeneralParameters();
				
				ByteArrayOutputStream bClave = new ByteArrayOutputStream(30);
				bClave.write(asciiEncriptado, 2226, 30);
				byte[] bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
				String output = new String(Crypto.EbcdicToAscii(bTrama));						
				if(Constante.VER_LOG) System.out.println("GeneralParameters.keyPinPad: "+output);
				gp.setKeyPinPad(output); 
					
				bClave = new ByteArrayOutputStream(30);
				bClave.write(asciiEncriptado, 2226+30, 30);
				bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
				output = new String(Crypto.EbcdicToAscii(bTrama));
				if(Constante.VER_LOG) System.out.println("GeneralParameters.keyMAC: "+output);
				gp.setKeyMAC(output);
				
				bClave = new ByteArrayOutputStream(30);
				bClave.write(asciiEncriptado, 2226+60, 30);
				bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
				output = new String(Crypto.EbcdicToAscii(bTrama));
				if(Constante.VER_LOG) System.out.println("GeneralParameters.keyDES_A: "+output);
				gp.setKeyDES_A(output);
				
				bClave = new ByteArrayOutputStream(30);
				bClave.write(asciiEncriptado, 2226+90, 30);
				bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
				output = new String(Crypto.EbcdicToAscii(bTrama));
				if(Constante.VER_LOG) System.out.println("GeneralParameters.keyDES_B: "+output);			
				gp.setKeyDES_B(output);
				
				gp.setTipoEncripcion(pi.getTipoEncripcion());
				
				/** **/
				setKeysOnDB(gp.getKeyPinPad(),gp.getKeyMAC(),gp.getKeyDES_A(),gp.getKeyDES_B());
				/** **/
				FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"/"+"media.obj");
				ObjectOutputStream salida=new ObjectOutputStream(fos);
				salida.writeObject(gp);
				salida.close();
				
			}
			//InitialParametersServlet.loadLUName(sNombreServidor, pi);
			/*
			HostServer.configTrxClaves = pi.getConfiguracionTrxClaves();
			byte[] trama = HostServer.prepareHeaderAscii("0013", codUsuario, codUsuario);
			
			if (!HostServer.initSNASessionBySocket(pi.getCommServer(), Integer.parseInt(pi.getCommPort()), pi.getLUName())){
				msgError = "Error al establecer sesión de comunicaciones";
				System.out.println(msgError);
			}else {
				HostServer.mySocket.setMessageToHost(trama);
				System.out.println("Enviando a Host...");
				if (HostServer.mySocket.sendTxn()){
					String sRespuestaHost = new String(Crypto.EbcdicToAscii(HostServer.mySocket.getMessageFromHost()).getBytes());
					//Se verifica que no exista error en la transaccion.
					if (!sRespuestaHost.substring(85, 87).equalsIgnoreCase("00")){
						msgError = "Error al recibir trama de claves: " + sRespuestaHost.substring(106, 186);
						System.err.println("Error al recibir trama de claves: " + sRespuestaHost.substring(106, 186));
					}else{
						System.err.println("Trama de claves recibida Ok...");
						ByteArrayOutputStream bTramaDesdeHost = new ByteArrayOutputStream(HostServer.mySocket.getMessageFromHost().length);
						bTramaDesdeHost.write(HostServer.mySocket.getMessageFromHost());
						
						ByteArrayOutputStream bClave = new ByteArrayOutputStream(30);
						bClave.write(HostServer.mySocket.getMessageFromHost(), 1050, 30);
						GeneralParameters.keyPinPad = Crypto.EbcdicToAscii(Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves));
 								
						bClave.reset();
						bClave.write(HostServer.mySocket.getMessageFromHost(), 1080, 30);
						GeneralParameters.keyMAC = Crypto.EbcdicToAscii(Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves));
						
						bClave.reset();
						bClave.write(HostServer.mySocket.getMessageFromHost(), 1110, 30);
						GeneralParameters.keyDES_A = Crypto.EbcdicToAscii(Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves));
						
						bClave.reset();
						bClave.write(HostServer.mySocket.getMessageFromHost(), 1140, 30);
						GeneralParameters.keyDES_B = Crypto.EbcdicToAscii(Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves));
					}
				}else{
					msgError = "Error al recibir trama de claves";
					System.out.println(msgError);
				}
			}*/
			// System.out.println("Actualización de Datos de Agencia Virtual desde Host finalizada");
			return msgError;
		}catch (Exception e){
			cambiarEstadoBloqueoAgencia(false);//Desbloquear
		    System.out.println("Error al actualizar Datos de Agencia desde el Computador Central: "+e);
		    if(Constante.VER_LOG) System.out.println("actualizarDatosAgenciaDesdeHost: "+e.getMessage());
			return "Error al actualizar Datos de Agencia desde el Computador Central:  " + e ;
		}
		/*
		finally{
			HostServer.mySocket.CloseSession();
		}
		*/
	}
	private static String setKeysOnDB(String keypindPad, String KeyMac,String keyDesA,String keyDesB){
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;
	    
	    try{
	    	
	    	System.out.println("Inicio - Guardar claves en la base de datos...");
		    conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false);
		    sqlstmt = "Update pv_swb.tbrainf set " +
		    			"KEYPINPAD='" + keypindPad +"', " + 
						"KEYMAC='" + KeyMac +"', " +
						"KEYDES_A='" + keyDesA +"', " +
						"KEYDES_B='" + keyDesB +"' where CODBRA='0001'";
		    		
		    if(Constante.VER_LOG) System.out.println("Query - Guardar claves en la base de datos..." + sqlstmt);
		    pstmt = conn.prepareStatement(sqlstmt);
		    pstmt.executeUpdate();
		    conn.commit();
		    System.out.println("Fin - Guardar claves en la base de datos...");
			return "";
	    }catch(Exception e){
	    	cambiarEstadoBloqueoAgencia(false);//Desbloquear
	        
	    	try{
	    		System.out.println("namingException-rollback");
	    		conn.rollback();
	    	}catch(SQLException e1){
	    		 System.out.println(e.getMessage());
	    	}	    	
	    	return "Ocurrió un error inesperado este proceso " + e;
	    }finally{
	    	
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(e);
	    	}	
	    }	
	}
	private static String moverDiarioElectronico(){
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;
	    
	    try{
	    	//tjoutra		: diario electronico
	    	//tjoudet		: detalle del diario electrónico
	    	//tjoutratim	: refrendos
	    	//tjoutrawin	: wincha electrónica
	    	//tjoutrawin	: tramas (ya no se registran)
	    	System.out.println("Inicio - Pase de Diario Electrónico al Histórico...");
		    conn = DataSourceConnector.getInstance("jdbc/dbJT").getConnection();
		    conn.setAutoCommit(false);
		    System.out.println("Inicio Perfilado...");
		    sqlstmt = "Delete From bn_swb_log.tjoutra t Where t.numlog in (Select h.numlog From bn_swb_log.hjoutra h)";
			pstmt = conn.prepareStatement(sqlstmt);
			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(false);
			System.out.println("Fin Perfilado...");
			System.out.println("Inicio bloqueo de tabla...");
		    sqlstmt = "LOCK TABLE bn_swb_log.hjoutra IN EXCLUSIVE MODE";
			pstmt = conn.prepareStatement(sqlstmt);
			pstmt.executeQuery();
			System.out.println("Fin bloqueo de tabla...");
		    sqlstmt = "insert into bn_swb_log.hjoutra" +
		    		" (numlog," +
		    		" datpro," +
		    		" horpro," +
		    		" coddoc," +
		    		" numdoc," +
		    		" cipadr," +
		    		" codtra," +
		    		" codtrahst," +
		    		" tipprdsrc," +
		    		" numprdsrc," +
		    		" tipprdtar," +
		    		" numprdtar," +
		    		" amotra," +
		    		" codcur," +
		    		" numref," +
		    		" codent," +
		    		" msghst," +
		    		" idetrapro," +
		    		" idetracom," +
		    		" amotxn," +
		    		" baltra," +
		    		" msgerror," +
		    		" typper," +
		    		" clobcons," +
		    		" numope," +
		    		" flgcha," +
		    		" nrocta," +
		    		" flgcom," +
		    		" typtar," +
		    		" flgerror," +
		    		" nomben)" +
		    		" select numlog," +
		    		" datpro," +
		    		" horpro," +
		    		" coddoc," +
		    		" numdoc," +
		    		" cipadr," +
		    		" codtra," +
		    		" codtrahst," +
		    		" tipprdsrc," +
		    		" numprdsrc," +
		    		" tipprdtar," +
		    		" numprdtar," +
		    		" amotra," +
		    		" codcur," +
		    		" numref," +
		    		" codent," +
		    		" msghst," +
		    		" idetrapro," +
		    		" idetracom," +
		    		" amotxn," +
		    		" baltra," +
		    		" msgerror," +
		    		" typper," +
		    		" clobcons," +
		    		" numope," +
		    		" flgcha," +
		    		" nrocta," +
		    		" flgcom," +
		    		" typtar," +
		    		" flgerror," +
		    		" nomben" +
		    		" from bn_swb_log.tjoutra";
		    
		    pstmt = conn.prepareStatement(sqlstmt);
		    pstmt.executeUpdate();
		    conn.commit();
		    System.out.println("Fin - Pase de Diario Electrónico al Histórico...");
		    System.out.println("Inicio - Truncate table tjoutra(limpia el diario electronico)");
		    sqlstmt = "truncate table bn_swb_log.tjoutra";
			pstmt = conn.prepareStatement(sqlstmt);
			pstmt.executeUpdate();
			
			conn.commit();
			System.out.println("Pase de Diario Electrónico al Histórico finalizado");
			return "";
	    }catch(NamingException e){
	    	cambiarEstadoBloqueoAgencia(false);//Desbloquear
	        System.out.println(""+e);
	    	try{
	    		System.out.println("namingException-rollback");
	    		conn.rollback();
	    	}catch(SQLException e1){
	    		// System.out.println(e.getMessage());
	    	}	    	
	    	// System.out.println("moverDiarioElectronico: " + e.getMessage());
	    	return "Ocurrió un error inesperado este proceso " + e;
	    }catch (SQLException e){
	    	cambiarEstadoBloqueoAgencia(false);//Desbloquear
	        System.out.println(""+e);
	    	try{
	    		System.out.println("sqlEexception-rollback");
	    		conn.rollback();
	    	}catch(SQLException e1){
	    		// System.out.println(e.getMessage());
	    	}	    	
	    	// System.out.println("moverDiarioElectronico: " + e.getMessage());
	    	return "Ocurrió un error inesperado este proceso " + e;
	    }finally{
	    	
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(""+e);
	    		// System.out.println(e.getMessage());
	    	}	
	    }	
	}
	
	private String depurarDiarioHistorico(){
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;
	    Statement stmt = null;
	    String msgError = "";
	    
	    try{
	    	//tjoutra		: diario electronico
	    	//tjoudet		: detalle del diario electrónico
	    	//tjoutratim	: refrendos
	    	//tjoutrawin	: wincha electrónica
	    	//tjoutrawin	: tramas (ya no se registran)
	    	// System.out.println("Depuración del Diario Histórico...");
	    	String fechaProceso = "";
	    	int diasHistorico = 0; 
	    	conn = DataSourceConnector.getInstance().getConnection();
			sqlstmt = "select datpro, numhistoricomax from pv_swb.tbrainf";
			stmt = conn.createStatement();
			    		    	
	    	ResultSet result = stmt.executeQuery(sqlstmt);
	    	if (result.next()){
	    		fechaProceso = result.getString("datpro");
	    		diasHistorico = result.getInt("numhistoricomax");
	    	}else{
	    		msgError = "Error al obtener la Configuración de Agencia";
	    	}
	    	
	    	if (msgError.equals("")){
		    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
		    	Calendar c1 = Calendar.getInstance(); 
		    	int yyyy = Integer.parseInt(fechaProceso.substring(0,4));
		    	int mm = Integer.parseInt(fechaProceso.substring(4,6));
		    	int dd = Integer.parseInt(fechaProceso.substring(6,8));

		    	c1.set(yyyy, mm-1, dd); //(-1: El mes empieza en 0)
		    	c1.add(Calendar.DATE,-diasHistorico);
		    	String fechaHistoricoMax = sdf.format(c1.getTime());
		    	
			    conn = DataSourceConnector.getInstance().getConnection();
			    conn.setAutoCommit(false);
			    
			    sqlstmt = "delete from bn_swb_log.hjoudet where exists ( ";
			    sqlstmt = sqlstmt + "select hjoutra.numlog, hjoutra.codusr from bn_swb_log.hjoutra ";
			    sqlstmt = sqlstmt + "where ";
			    sqlstmt = sqlstmt + "hjoutra.numlog = hjoudet.numlog and ";
			    sqlstmt = sqlstmt + "hjoutra.codusr = hjoudet.codusr and ";
			    sqlstmt = sqlstmt + "hjoutra.datpro < ?)";	
			    pstmt = conn.prepareStatement(sqlstmt);
			    pstmt.setString(1,fechaHistoricoMax);
			    pstmt.executeUpdate();
			    
			    sqlstmt = "delete from bn_swb_log.hjoutratim where exists ( ";
			    sqlstmt = sqlstmt + "select hjoutra.numlog, hjoutra.codusr from bn_swb_log.hjoutra ";
			    sqlstmt = sqlstmt + "where ";
			    sqlstmt = sqlstmt + "hjoutra.numlog = hjoutratim.numlog and ";
			    sqlstmt = sqlstmt + "hjoutra.codusr = hjoutratim.codusr and ";
			    sqlstmt = sqlstmt + "hjoutra.datpro < ?)";	
			    pstmt = conn.prepareStatement(sqlstmt);
			    pstmt.setString(1,fechaHistoricoMax);
			    pstmt.executeUpdate();
			    
			    sqlstmt = "delete from bn_swb_log.hjoutrawin where exists ( ";
			    sqlstmt = sqlstmt + "select hjoutra.numlog, hjoutra.codusr from bn_swb_log.hjoutra ";
			    sqlstmt = sqlstmt + "where ";
			    sqlstmt = sqlstmt + "hjoutra.numlog = hjoutrawin.numlog and ";
			    sqlstmt = sqlstmt + "hjoutra.codusr = hjoutrawin.codusr and ";
			    sqlstmt = sqlstmt + "hjoutra.datpro < ?)";	
			    pstmt = conn.prepareStatement(sqlstmt);
			    pstmt.setString(1,fechaHistoricoMax);
			    pstmt.executeUpdate();			    
			    
			    sqlstmt = "delete from bn_swb_log.hjoutra where datpro < ?";
				pstmt = conn.prepareStatement(sqlstmt);
				pstmt.setString(1,fechaHistoricoMax);
				pstmt.executeUpdate();
			    
				conn.commit();
	    	}
	    	// System.out.println("Depuración del Diario Histórico finalizada");
			return msgError;
	    }catch(NamingException e){
	        System.out.println(""+e);
	    	try{
	    		conn.rollback();
	    	}catch(SQLException e1){
	    	    System.out.println(""+e1);
	    		// System.out.println(e.getMessage());
	    	}	    	
	    	// System.out.println("depurarDiarioHistorico: " + e.getMessage());
	    	return "Ocurrió un error inesperado este proceso";
	    }catch (SQLException e){
	    	try{
	    		conn.rollback();
	    	}catch(SQLException e1){
	    	    System.out.println(""+e1);
	    		// System.out.println(e.getMessage());
	    	}	    	
	    	// System.out.println("depurarDiarioHistorico: " + e.getMessage());
	    	return "Ocurrió un error inesperado este proceso";
	    }finally{
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(""+e);
	    		// System.out.println(e.getMessage());
	    	}	
	    }	
	}
	final static String enviarReporteInicioDia(String txtBloq, String txtClaves, String txtJournal, String txtDesBloq){
		Main bean=null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String dirIP = "";
		
		try {
			dirIP=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		String texto="<html>" +
					"<body>" +
					"<b>INFORME DE EJECUCION DE PROCESO DE INICIO DE DIA AGENCIA VIRTUAL</b><BR><BR>" +
					"<table border=0 width=800>" +
						"<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Servidor: </b>"+dirIP +
							"</td>" +" <br>"+
						"</tr>" +
						"<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Sub Proceso:</b> Bloqueo de Agencia Virtual" +
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Estado: </b>"+txtBloq +
							"</td>" +" <br>"+
						"</tr>" +
						"<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Sub Proceso:</b> Obtener claves de criptografía" +
							"</td>" +
					    "</tr>" +
					    "<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Estado: </b>"+txtClaves +
							"</td>" + "<br>"+
					    "</tr>" +			
					    "<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Sub Proceso:</b> Mantenimiento de Journal" +
							"</td>" + 
						"</tr>" +
						"<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Estado: </b>"+txtJournal +
							"</td>" + "<br>"+
						"</tr>" +
						"<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Sub Proceso:</b> Desbloqueo de Agencia Virtual" +
							"</td>" +
					    "</tr>" +
					    "<tr>" +
							"<td nowrap>" +
							"<b>"+sdf.format(cal.getTime())+" " + "Estado:</b> "+txtDesBloq +
							"</td>" +
						"</tr>" +			
				"</table>" +
				"<body>" +
				"</html>";
		bean = new Main();	
			bean.setM_text(ObjectUtil.replaceIlegalCharacterToSendMail(texto));
			if(Constante.CONSTANTE_OS.equals("3")){
				bean.setM_to("acaro@bn.com.pe");
				bean.setCc_m_to("cgrande@bn.com.pe, cosapi_ati01@bn.com.pe");	
			}else{
				bean.setM_to("cosapi_ati01@bn.com.pe");
				bean.setCc_m_to("cosapi_ati04@bn.com.pe");
			}
			
			//bean.setM_subject("INFORME DE EJECUCION DE PROCESO DE INICIO DE DIA AGENCIA VIRTUAL");
			ConstanteSesion.MAIL_SUBJECT = "INFORME DE EJECUCION DE PROCESO DE INICIO DE DIA AGENCIA VIRTUAL";
			bean.Enviarcorreo();		
		return "";
	}
	
	public final static String cambiarEstadoBloqueoAgencia(boolean estadoBloqueo){
		Connection conn = null;		
	    String sqlstmt = "";
	    PreparedStatement pstmt = null;
	    String flgBloqueo;
	    
	    try{	    	
		    // System.out.println("Cambio de estado de Bloqueo de la Agencia...");
	    	conn = DataSourceConnector.getInstance().getConnection();
		    conn.setAutoCommit(false);
		    if (estadoBloqueo){
		    	flgBloqueo = "1";
		    }else{
		    	flgBloqueo = "0";
		    }
			sqlstmt = "update pv_swb.tbrainf set flgblkbra = ?";
			pstmt = conn.prepareStatement(sqlstmt);
			pstmt.setString(1,flgBloqueo);
			pstmt.executeUpdate();
			conn.commit();
			// System.out.println("Cambio de estado de Bloqueo de la Agencia finalizado");
			return "";
	    }catch(NamingException e){
	        System.out.println(""+e);
	    	// System.out.println("cambiarEstadoBloqueoAgencia: " + e.getMessage());
	    	try{
	    		conn.rollback();
	    	}catch(SQLException e1){
	    		// System.out.println(e.getMessage());
	    	}
	    	return "Ocurrió un error inesperado en este proceso " + e;
	    }catch (SQLException e){
	        System.out.println(""+e);
	    	try{
	    		conn.rollback();
	    	}catch(SQLException e1){
	    	    System.out.println(""+e1);
	    		// System.out.println(e.getMessage());
	    	}	    	
	    	// System.out.println("cambiarEstadoBloqueoAgencia: " + e.getMessage());
	    	return "Ocurrió un error inesperado en este proceso " + e;
	    }finally{
	    	try {	    		 
	    		conn.close();
	    	}catch (SQLException e){
	    	    System.out.println(""+e);
	    		// System.out.println(e.getMessage());
	    	}	
	    }		
	}
	
	public synchronized static String ejecutarInicioDeDia(){
		System.out.println("INICIO PROCESO DE INICIO DE DIA");
		//AQUI ES LA NUEZ!!!!
		String msgError = "";
		try {
			String IP=InetAddress.getLocalHost().getHostAddress();
			System.out.println("IP SERVER: " + IP);
		} catch (UnknownHostException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		} 
		try {
			//agenciaBloqueada = obtenerEstadoBloqueoAgencia();			
			
			msgError = cambiarEstadoBloqueoAgencia(true);	
		}catch (Exception e){
			enviarReporteInicioDia("Error \n "+msgError,"No se ejecut&oacute;","No se ejecut&oacute;","OK");
			//msgError = cambiarEstadoBloqueoAgencia(false);//Desbloquear
		    System.out.println(""+e);
			msgError = e.getMessage();			
		}
		
		if (msgError.equals("")){
			if (true){
				//msgError = actualizarSistema();
				if (msgError.equals("")){
					//msgError = actualizarFechaProcesoDesdeHost();
					if (msgError.equals("")){
						msgError = actualizarDatosAgenciaDesdeHost();
						if (msgError.equals("")){
							//msgError = moverDiarioElectronico();
							if (msgError.equals("")){
								//msgError = depurarDiarioHistorico();
								if (msgError.equals("")){
									msgError = cambiarEstadoBloqueoAgencia(false);//Desbloquear
									if (msgError.equals("")){
										//Ok
										enviarReporteInicioDia("OK","OK","OK","OK");
									}else{
										enviarReporteInicioDia("OK","OK","OK","Error \n "+msgError);
										msgError = ProcessBean.PROC_DESBLOQUEO_AGENCIA + ": " + msgError;
									}
								}else{
									msgError = ProcessBean.PROC_DEPURACION_HISTORICO + ": "+msgError;
								}
							}else{
								enviarReporteInicioDia("OK","OK","Error \n "+msgError,"OK");
								msgError = ProcessBean.PROC_PASE_DIARIO + ": "+ msgError;
							}
						}else{
							enviarReporteInicioDia("OK","Error \n "+msgError,"No se Ejecut&oacute;","OK");
							msgError = ProcessBean.PROC_ACTUALIZACION_AGENCIA + ": " + msgError;
						}
					}else{
						msgError = ProcessBean.PROC_ACTUALIZACION_FECHA + ": "+msgError;
					}
				}else{
					msgError = ProcessBean.PROC_ACTUALIZACION_SISTEMA + ": "+ msgError;
				}
			}else{ //Si no está bloqueada,  Inicio de Dia ya fue ejecutado (Inicio de Día desbloquea la Agencia)
				msgError = "La Agencia no está Bloqueada, el Proceso de Inicio de Día ya fue ejecutado";
			}
		}
		
		if(!msgError.equals("")){
			cambiarEstadoBloqueoAgencia(false);
		}
		//System.out.println("FINALIZA PROCESO DE INICIO DE DIA");
		return msgError;
	}
	
	public static String ejecutarReInicioDeDia(){
		//System.out.println("INICIO PROCESO DE RE-INICIO DE DIA");
		String msgError = "";
		boolean agenciaBloqueada = false;
		try {
			//agenciaBloqueada = obtenerEstadoBloqueoAgencia();			
			//agenciaBloqueada = true;
			msgError = cambiarEstadoBloqueoAgencia(true);
		}catch (Exception e){
		    System.out.println(""+e);
			msgError = e.getMessage();
			return msgError; 
		}
		
		if (msgError.equals("")){
			if (true){
				//msgError = actualizarSistema();
				if (msgError.equals("")){
					//msgError = actualizarFechaProcesoDesdeHost();
					if (msgError.equals("")){
						msgError = actualizarDatosAgenciaDesdeHost();
						if (msgError.equals("")){
							msgError = cambiarEstadoBloqueoAgencia(false);
						}else{
							msgError = ProcessBean.PROC_ACTUALIZACION_AGENCIA +": "+ msgError;
						}
					}else{
						msgError = ProcessBean.PROC_ACTUALIZACION_FECHA + ": "+msgError;
					}
				}else{
					msgError = ProcessBean.PROC_ACTUALIZACION_SISTEMA +": "+msgError;
				}
			}else{
				msgError = "La Agencia está Bloqueada. Debe Ejecutar Inicio de Día";
				//paginaDestino = NavigationConstant.PAG_EJECUCION;							
			}						
		}
		
		if(!msgError.equals("")){
			cambiarEstadoBloqueoAgencia(false);
		}
		//System.out.println("FINALIZA PROCESO DE RE-INICIO DE DIA");
		return msgError;
	}
	
	private String ejecutarFinDeDia(HttpServletRequest req){
		String msgError = "";
		boolean agenciaBloqueada = false;
		try {
			agenciaBloqueada = obtenerEstadoBloqueoAgencia();
		}catch (Exception e){
			msgError = e.getMessage();
		}
		if (msgError.equals("")){
			if (!agenciaBloqueada){
				//msgError = generarArchivoReporteEstado();
				if (msgError.equals("")){
					msgError = cambiarEstadoBloqueoAgencia(true);//bloquear
					if (msgError.equals("")){
						//Ok
					}else{
						msgError = ProcessBean.PROC_BLOQUEO_AGENCIA + ": "+msgError;
					}
				}else{
					msgError = ProcessBean.PROC_GENERACION_ARCHIVO_RPT_ESTADO + ": "+msgError;
				}
			}else{
				msgError = "La Agencia ya está Bloqueada";
			}
		}
		return msgError;
	}
	
}