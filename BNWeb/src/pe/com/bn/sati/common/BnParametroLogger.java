/*
 * Creado el 30/04/2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de codigo
 */
package pe.com.bn.sati.common;

import java.util.ResourceBundle;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.common.Constante;




public class BnParametroLogger implements java.io.Serializable {
	
	private static LoggerSati log3 = LoggerSati.getInstance(BnParametroLogger.class.getName());

	public ParamLogger getParamLogger() {
		ParamLogger paramLogger = new ParamLogger();
		return paramLogger;
	}
	
	public class ParamLogger implements java.io.Serializable {
		
		public String getLoggerPrintError() {
			try {
				if(Constante.BN_LOGGER_PRINT_ERROR ==null || Constante.BN_LOGGER_PRINT_ERROR.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
							
					Constante.BN_LOGGER_PRINT_ERROR = rb.getString("bn.logger.error.print");
					if(Constante.BN_LOGGER_PRINT_ERROR ==null || Constante.BN_LOGGER_PRINT_ERROR.equals("")) 
						throw new Exception("Parametro no definido bn.logger.error.print");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_LOGGER_PRINT_ERROR;
		}
		
		public String getLoggerPrintDebugNivel_1() {
			try {
				if(Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_1 ==null || Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_1.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_1 = rb.getString("bn.logger.debug.print.nivel1");
					if(Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_1 ==null || Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_1.equals("")) 
						throw new Exception("Parametro no definido bn.logger.debug.print.nivel1");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_1;
		}
		
		public String getLoggerPrintDebugNivel_2() {
			try {
				if(Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_2 ==null || Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_2.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_2 = rb.getString("bn.logger.debug.print.nivel2");
					if(Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_2 ==null || Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_2.equals("")) 
						throw new Exception("Parametro no definido bn.logger.debug.print.nivel2");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_LOGGER_PRINT_DEBUG_NIVEL_2;
		}
	    
		
		public String getProcLoggerFile() {
			try {
				if(Constante.BN_PROC_LOGGER_FILE ==null || Constante.BN_PROC_LOGGER_FILE.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_PROC_LOGGER_FILE = rb.getString("bn.logger.debug.file.flag");
					if(Constante.BN_PROC_LOGGER_FILE ==null || Constante.BN_PROC_LOGGER_FILE.equals("")) 
						throw new Exception("Parametro no definido bn.logger.debug.file.flag");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_PROC_LOGGER_FILE;
		}
		
		public String getProcLoggerConsole() {
			try {
				if(Constante.BN_PROC_LOGGER_CONSOLE ==null || Constante.BN_PROC_LOGGER_CONSOLE.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_PROC_LOGGER_CONSOLE = rb.getString("bn.logger.debug.console.flag");
					if(Constante.BN_PROC_LOGGER_CONSOLE ==null || Constante.BN_PROC_LOGGER_CONSOLE.equals("")) 
						throw new Exception("Parametro no definido bn.logger.debug.console.flag");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_PROC_LOGGER_CONSOLE;
		}
		
		public String getProcLoggerPath() {
			try {
				if(Constante.BN_PROC_LOGGER_PATH ==null || Constante.BN_PROC_LOGGER_PATH.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_PROC_LOGGER_PATH = rb.getString("bn.logger.debug.file.path");
					if(Constante.BN_PROC_LOGGER_PATH ==null || Constante.BN_PROC_LOGGER_PATH.equals("")) 
						throw new Exception("Parametro no definido bn.logger.debug.file.path");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_PROC_LOGGER_PATH;
		}
	    
		public String getErrLoggerFile() {
			try {
				if(Constante.BN_ERR_LOGGER_FILE ==null || Constante.BN_ERR_LOGGER_FILE.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_ERR_LOGGER_FILE = rb.getString("bn.logger.error.file.flag");
					if(Constante.BN_ERR_LOGGER_FILE ==null || Constante.BN_ERR_LOGGER_FILE.equals("")) 
						throw new Exception("Parametro no definido bn.logger.error.file.flag");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_ERR_LOGGER_FILE;
		}
		

		public String getErrLoggerConsole() {
			try {
				if(Constante.BN_ERR_LOGGER_CONSOLE ==null || Constante.BN_ERR_LOGGER_CONSOLE.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_ERR_LOGGER_CONSOLE = rb.getString("bn.logger.error.console.flag");
					if(Constante.BN_ERR_LOGGER_CONSOLE ==null || Constante.BN_ERR_LOGGER_CONSOLE.equals("")) 
						throw new Exception("Parametro no definido bn.logger.error.console.flag");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_ERR_LOGGER_CONSOLE;
		}
		

		public String getErrLoggerPath() {
			try {
				if(Constante.BN_ERR_LOGGER_PATH ==null || Constante.BN_ERR_LOGGER_PATH.equals("")){
					ResourceBundle rb = ResourceBundle.getBundle("parametro");
					Constante.BN_ERR_LOGGER_PATH = rb.getString("bn.logger.error.file.path");
					if(Constante.BN_ERR_LOGGER_PATH ==null || Constante.BN_ERR_LOGGER_PATH.equals("")) 
						throw new Exception("Parametro no definido bn.logger.error.file.path");
				}
			} catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.printStackTrace();
			}
			return Constante.BN_ERR_LOGGER_PATH;
		}
	}

}
