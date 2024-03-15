package pe.cosapi.common;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import pe.bn.login.action.LoginAction;
import pe.bn.login.action.LoginAfiliacionInternetAction;
import pe.bn.login.action.LoginBloqueoAction;
import pe.bn.login.action.LoginOlvidoClaveInternetAction;
import pe.bn.consulta.action.*;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.GeneratorKeys;

public class SeguridadUtil implements Serializable{
    //public static Logger logger = Logger.getLogger(SeguridadUtil.class);
    
	private static LoggerSati log3 = LoggerSati.getInstance(UsuarioImpl.class.getName());
	 
	
	public static boolean existeUsuarioSesion(HttpServletRequest request) {
		boolean existe = false;
		Usuario usuario;
		HttpSession session;
		
		session = request.getSession();
		if(session != null) {
			
			usuario = (Usuario) session.getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			
			if(usuario != null) {
				existe = true;
			}else{
				//log3.debug(null, "Mensaje de Metodo existeUsuarioSesion","Usuario es igual a null");
								
			}
		}else{
			//log3.debug(null, "Mensaje de Metodo existeUsuarioSesion","sesion es igual a null");	
		}
		
		return existe;
	}
	
	/**
	 * Metodo encargado de  
	 * @param request
	 * @return
	 */
	public static Usuario getUsuarioSession(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		return (Usuario) session.getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	}
	
	public static boolean isPaginaInicio(Action action) {
		boolean isPaginaInicio = true;
		if(!(action instanceof LoginAction)) {		
			isPaginaInicio = false;
		}
		
		if(action instanceof LoginBloqueoAction) {		
			isPaginaInicio = true;
		}
		
		if(action instanceof LoginAfiliacionInternetAction) {		
			isPaginaInicio = true;
		}	
		
		if(action instanceof LoginOlvidoClaveInternetAction) {		
			isPaginaInicio = true;
		}	
		
		return isPaginaInicio;
	}
	
	public static boolean isConsulta(Action action) {
		boolean isConsulta = true;
		if(!(action instanceof ConsultaAction)) {		
			isConsulta = false;
		}		
		return isConsulta;
	}
	
	public static void setearObjectSession(String key,Object obj,HttpServletRequest req) {
		req.getSession().setAttribute(key,obj);
	}
	
	public static Object getObjectSession(String key,HttpServletRequest req) {
		
		return req.getSession().getAttribute(key);
	}
	
	public static String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		GeneratorKeys gen = new GeneratorKeys();
		Map mapa = (Map)getObjectSession(ConstanteSesion.MAP_VALUES,request);
		String  valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		return valorClave;
	}
	
}
