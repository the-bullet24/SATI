package com.cosapisoft.sarawebbanking.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.output.XMLOutputter;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;

import CosapiSoft.SARAWebBanking.InicioSesion;
import CosapiSoft.SARAWebBanking.JspServlet;

import com.app.sarawebmanager.ws.bean.CustomerAuthenticationProxy;
import com.app.sarawebmanager.ws.bean.client.ClienteWS;

import com.app.sarawebmanager.ws.bean.client.vo.ActivityVO;
import com.app.sarawebmanager.ws.bean.client.vo.ClientResultVO;
import com.app.sarawebmanager.ws.bean.client.vo.LogginResultVO;
import com.app.sarawebmanager.ws.bean.client.HelperClientFrames;
import com.cosapisoft.sarawebbranch.common.beans.InitialParametersInfo;
import com.cosapisoft.sarawebbranch.common.beans.LogginResult;
import com.cosapisoft.sarawebbranch.common.beans.LoginRequest;
import com.cosapisoft.sarawebbranch.common.beans.PerfilesUsuario;
import com.cosapisoft.sarawebmanager.security.beans.Actividad;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;



public class InicioDeSesionServlet extends HttpServlet implements Servlet, Serializable{
	Vector acciones = null;
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InicioDeSesionServlet() {
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

	private void performTask(HttpServletRequest req, HttpServletResponse res){
		String paginaDestino = "";
		paginaDestino = NavigationConstant.PAG_INICIO_SESION;
		Constante.SESSION_CADUCA="1";
		try {
			HttpSession session = req.getSession(false);
			if (session == null){
				session = req.getSession(true);
			}
			
			Constante.CONST_MODULO = "11";
			//System.out.println("Modulo Constante:"+Constante.CONST_MODULO);
			
			if (session.getAttribute("navigationBean") == null){
				NavigationBean navigationBean = new NavigationBean();
				navigationBean.setMessageError("Ha ocurrido un error inesperado");
				session.setAttribute("navigationBean", navigationBean);
				paginaDestino = NavigationConstant.PAG_ERROR;
			}else{
				((NavigationBean) session.getAttribute("navigationBean")).setMessageError("");
				String action = req.getParameter("actioncommand");
				
				InicioSesion bean = new InicioSesion();
				String modulo = "Inicio de Dia";
				bean.setNameProducto(modulo);
				req.getSession(true).setAttribute("login", bean);
				
				if (action.equals(NavigationConstant.ID_ACEPTAR)){
					String messageResultTra = "";
					try {
						String codUsuario = req.getParameter("codusuario");
						String clave = req.getParameter("clave");
						//String codUsuario = "1212122";
						//String clave = "111111";
				    	String codAgencia = "8888";
				    	String codModulo = "11";
				    	Constante.CONST_MODULO = codModulo;
				    	//System.out.println("Modulo Constante:"+Constante.CONST_MODULO);
				    	
				    	LogginResult logginResult = new LogginResult();
				    	
				    	// Conectandose al WebService

				    	//ClienteWS clienteWS = new ClienteWS();	       
				        ClientResultVO cliRVO = new ClientResultVO();
				       
				    	//cliRVO = clienteWS.usersLoggin(codModulo, codAgencia, codUsuario, clave);
				    
				    	//LogginResultVO logRVO = (LogginResultVO) cliRVO.getObject();
				        System.out.println("Inicio de Dia");
				    
				    	CustomerAuthenticationProxy autenticacion = new CustomerAuthenticationProxy();
				    	String trama = autenticacion.autenticarUsuarios(HelperClientFrames.generateUsersLoggin(codModulo, codAgencia, codUsuario, clave));
				    	
				    	LogginResultVO logRVO = HelperClientFrames.parseUserTeller(trama);
				    	cliRVO.setCode("1");
				    	cliRVO.setObject(logRVO);
				    	
				    	messageResultTra =cliRVO.getMessage();
				    	
				    	((NavigationBean) session.getAttribute("navigationBean")).setMessageError(logRVO.getMessage());
				    	
				    	if (cliRVO.getCode().equals("1")){
				    	//88if ("1".equals("1")){
						    logginResult.setState(logRVO.getState());
						    logginResult.setMessage(logRVO.getMessage());
						    logginResult.setNameUser(logRVO.getName());
						    logginResult.setFlagPasswordChange(logRVO.getFlagPasswordChange());
						    
						    acciones = new java.util.Vector(50);
						    ArrayList all = new ArrayList();
						    
						    Constante.INICIO_101 = "";
						    Constante.INICIO_102 = "";
						     
						    
						    if (logginResult.getState().equals("1")){ //1: Login Exitoso    
						    //if ("1".equals("1")){ //1: Login Exitoso
						    	int listAct = 0;	    	
							    listAct  = logRVO.getActivities().size();			    
						    	//listAct  = all.size();
							    for(int i=0; i<=listAct-1; i++){			    
							    	ActivityVO aVO = (ActivityVO) logRVO.getActivities().get(i);
							    	Actividad act = new Actividad();
							    	act.setCodModulo(codModulo);
							    	act.setCodActividad(aVO.getCode());
							    	act.setNomActividad(aVO.getName());
							    	logginResult.getActivities().add(act);
							    	
							    	if (act.getCodActividad().trim().equals("101")) {
							    	    Constante.INICIO_101 = act.getCodActividad().trim();
							    	}
							    	
							    	if (act.getCodActividad().trim().equals("102")) {
							    	    Constante.INICIO_102 = act.getCodActividad().trim();
							    	}
							    								    	
							    	acciones.addElement(((Actividad)logginResult.getActivities().get(i)).getCodActividad());
							    	
							    	//System.out.println("AVO-->"+aVO.getCode() +"+" +aVO.getName());
							    	
							    }

							    req.getSession().setAttribute("acciones", acciones);
							    
							    int rows = acciones.size();
							    
							    //for (int i = 0; i < rows; i++) {
								//	String cod_act = acciones.elementAt(i).toString();
								//	System.out.println("cod_act INICIO DIA:"+cod_act);
								//}
							    
							    // REGISTRAR EN BD
								PerfilesUsuario perfilesUsuario = new PerfilesUsuario();
								perfilesUsuario.setCodUsuario(codUsuario);
								perfilesUsuario.setPerfiles(logginResult.getProfiles());
								
								session.setAttribute("currentUser",codUsuario);
								
								paginaDestino = JspServlet.ADMIN_HTML;
						    }
				    	}else{
				    	    if (codUsuario.trim().length() == 0) {
				    	        ((NavigationBean) session.getAttribute("navigationBean")).setMessageError("Es necesario ingresar el código del usuario");
				    	    } else if (clave.trim().length() == 0) {
				    	        ((NavigationBean) session.getAttribute("navigationBean")).setMessageError("Es necesario ingresar la clave");
				    	    }
				    	    
				    		paginaDestino = NavigationConstant.PAG_INICIO_SESION;
				    	}
				    	/**
					    res.setContentType("java-internal/" + LogginResult.class.getName());
						OutputStream out = res.getOutputStream();	    	
						ObjectOutputStream oos = new ObjectOutputStream(out);
						oos.writeObject(logginResult);
						oos.flush();
						oos.close();
						*/
				    	
			    	}catch(Exception e){
			    		// System.out.println("InicioDeSesionServlet: "+e.getMessage());;
			    		((NavigationBean) session.getAttribute("navigationBean")).setMessageError("Ha ocurrido un error inesperado");
			    		paginaDestino = NavigationConstant.PAG_INICIO_SESION;		    		
			    	}
				} else if (action.equals(NavigationConstant.ID_CANCELAR)){
					//paginaDestino = NavigationConstant.PAG_INICIO;
				    
					res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
					return;
				} else if (action.equals(NavigationConstant.ID_CAMBIAR)){
				    String codUsuarioValida = req.getParameter("codusuario");
				    
				    
				    if (codUsuarioValida.trim().length() == 0) {
		    	        ((NavigationBean) session.getAttribute("navigationBean")).setMessageError("Es necesario ingresar el código del usuario");
		    	        paginaDestino = NavigationConstant.PAG_INICIO_SESION;
		    	        callPage(req, res, paginaDestino);
		    	    }
		    	    
					bean.setUsuario(req.getParameter("codusuario"));
					bean.setClave(req.getParameter("clave"));
					
					res.sendRedirect(JspServlet.MODIFICAR_CLAVE_SERVLET + "?TxtCodusr=" + req.getParameter("codusuario") + "&BtnClave=Otro");
					return;
				}				
			}
			callPage(req, res, paginaDestino);
		}catch(IOException e){
			// System.out.println(e.getMessage());
		}catch (ServletException e){
			// System.out.println(e.getMessage());
		}
	}
	
	private void callPage(HttpServletRequest req, HttpServletResponse res, String destino) throws ServletException, IOException{
		//RequestDispatcher rd = this.getServletConfig().getServletContext().getRequestDispatcher(destino);
		//rd.forward(req, res);
		res.sendRedirect(destino);
	}
	
	private boolean validateSession(HttpServletRequest req, String atributo){
		boolean estado = false;
		if (req.getSession() == null){
			estado = false;
		}else if (req.getSession().getAttribute(atributo) == null){
			estado = false;
		}else{
			estado = true;
		}
		
		return estado;
	}
	
//	 public static String generateUsersLoggin(String moduleCode, String agencyCode, String userCode, String userPassword)
//	    throws Exception
//	  {
//	    //logger.info("[SWM1]: generando requerimiento de validación");
//	    StringWriter writer = new StringWriter();
//	    Writer stream = writer;
//	    String sWriter = "";
//
//	    if ((moduleCode == null) || (agencyCode == null) || (userCode == null) || (userPassword == null) || 
//	      (moduleCode.length() <= 0) || (agencyCode.length() <= 0) || (userCode.length() <= 0) || (userPassword.length() <= 0))
//	    {
//	      throw new Exception("[ERROR]: Los datos de ingreso no pueden ser vacíos ni nulos");
//	    }
//	    try
//	    {
//	      XMLOutputter outputter = new XMLOutputter();
//	      outputter.setNewlines(false);
//	      outputter.setIndent("");
//	      outputter.setEncoding("UTF-8");
//	      outputter.setOmitDeclaration(false);
//
//	      Element root = new Element("REQUEST_AUTHENTICATION");
//	      Document doc = new Document(root);
//
//	      addTagToTag(root, "USER_CODE", userCode.trim());
//	      addTagToTag(root, "REQUEST_OPERATION_TYPE", "SWM_OPERATION_TYPE_AUTHENTICATION_USER");
//	      addTagToTag(root, "USER_PASSWORD", userPassword);
//	      addTagToTag(root, "AGENCY_CODE", agencyCode);
//	      addTagToTag(root, "MODULE_CODE", moduleCode);
//
//	      outputter.output(doc, stream);
//
//	      sWriter = stream.toString();
//
//	      return sWriter;
//	    } catch (Exception e) {
//	      e.printStackTrace();
//	      throw new Exception("Ocurrió un error al intentar genear la trama = " + e);
//	    }
//	  }
}