/*
 * Creado el 30/04/2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.listener;
 
/**
 * @author 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Bnmvf07Parametro implements java.io.Serializable {
	
	ParamDb   		paramDb;
	ParamDatacom    paramDatacom;
	ParamWebService paramWebService;	
	ParamMQSeries   paramMQSeries;	
	ParamConJDNI    paramConJNDI;	
	
	public ParamDatacom getParamDatacom() {
		return paramDatacom;
	}
	/**
	 * @param paramDatacom El paramDatacom a establecer.
	 */
	public void setParamDatacom(ParamDatacom paramDatacom) {
		this.paramDatacom = paramDatacom;
	}


	public ParamDb getParamDb() {
		return paramDb;
	}
	public void setParamDb(ParamDb paramDb) {
		this.paramDb = paramDb;
	}
	public ParamConJDNI getParamConJNDI() {
		return paramConJNDI;
	}
	public void setParamConJNDI(ParamConJDNI paramConJNDI) {
		this.paramConJNDI = paramConJNDI;
	}
	/**
	 * @return Devuelve paramMQSeries.
	 */
	public ParamMQSeries getParamMQSeries() {
		return paramMQSeries;
	}
	/**
	 * @param paramMQSeries El paramMQSeries a establecer.
	 */
	public void setParamMQSeries(ParamMQSeries paramMQSeries) {
		this.paramMQSeries = paramMQSeries;
	}
	/**
	 * @return Devuelve paramWebService.
	 */
	public ParamWebService getParamWebService() {
		return paramWebService;
	}
	/**
	 * @param paramWebService El paramWebService a establecer.
	 */
	public void setParamWebService(ParamWebService paramWebService) {
		this.paramWebService = paramWebService;
	}

	
	public class ParamDb implements java.io.Serializable {
		private String paramDbApp;

		public String getParamDbApp() {
			return paramDbApp;
		}

		public void setParamDbApp(String paramDbApp) {
			this.paramDbApp = paramDbApp;
		}
		
		
		
	}

		
	public class ParamMQSeries implements java.io.Serializable {

		private String paramNameApp;
		private String paramUserApp;
		private String paramPassApp;
		private String paramOperApp;
		private String paramServiceReniec;
		
		/**
		 * @return Devuelve paramServiceReniec.
		 */
		public String getParamServiceReniec() {
			return paramServiceReniec;
		}
		/**
		 * @param paramServiceReniec El paramServiceReniec a establecer.
		 */
		public void setParamServiceReniec(String paramServiceReniec) {
			this.paramServiceReniec = paramServiceReniec;
		}
		/**
		 * @return Devuelve paramNameApp.
		 */
		public String getParamNameApp() {
			return paramNameApp;
		}
		/**
		 * @param paramNameApp El paramNameApp a establecer.
		 */
		public void setParamNameApp(String paramNameApp) {
			this.paramNameApp = paramNameApp;
		}
		/**
		 * @return Devuelve paramOperApp.
		 */
		public String getParamOperApp() {
			return paramOperApp;
		}
		/**
		 * @param paramOperApp El paramOperApp a establecer.
		 */
		public void setParamOperApp(String paramOperApp) {
			this.paramOperApp = paramOperApp;
		}
		/**
		 * @return Devuelve paramPassApp.
		 */
		public String getParamPassApp() {
			return paramPassApp;
		}
		/**
		 * @param paramPassApp El paramPassApp a establecer.
		 */
		public void setParamPassApp(String paramPassApp) {
			this.paramPassApp = paramPassApp;
		}
		/**
		 * @return Devuelve paramUserApp.
		 */
		public String getParamUserApp() {
			return paramUserApp;
		}
		/**
		 * @param paramUserApp El paramUserApp a establecer.
		 */
		public void setParamUserApp(String paramUserApp) {
			this.paramUserApp = paramUserApp;
		}
	}
	
	public class ParamConJDNI implements java.io.Serializable {
		
		private String paramJNDIOracleSpcl;
		private String paramJNDISqlServerSbs;
		private String paramJNDISqlReniec;
		
		public String getParamJNDIOracleSpcl() {
			return paramJNDIOracleSpcl;
		}
		public void setParamJNDIOracleSpcl(String paramJNDIOracleSpcl) {
			this.paramJNDIOracleSpcl = paramJNDIOracleSpcl;
		}
		public String getParamJNDISqlServerSbs() {
			return paramJNDISqlServerSbs;
		}
		public void setParamJNDISqlServerSbs(String paramJNDISqlServerSbs) {
			this.paramJNDISqlServerSbs = paramJNDISqlServerSbs;
		}
		
		public String getParamJNDIReniec() {
			return paramJNDISqlReniec;
		}
		public void setParamJNDIReniec(String paramJNDISqlReniec) {
			this.paramJNDISqlReniec = paramJNDISqlReniec;
		}	
		
	}
	


	public class ParamWebService implements java.io.Serializable {
		
		private String paramClavesSoapUrl;
		private String paramClavesSoapActionUri;
		private String paramClavesSoapXmlnsBoby;
		private String paramClavesSoapMethod;
		
		
		/**
		 * @return Devuelve paramClavesSoapActionUri.
		 */
		public String getParamClavesSoapActionUri() {
			return paramClavesSoapActionUri;
		}
		/**
		 * @param paramClavesSoapActionUri El paramClavesSoapActionUri a establecer.
		 */
		public void setParamClavesSoapActionUri(String paramClavesSoapActionUri) {
			this.paramClavesSoapActionUri = paramClavesSoapActionUri;
		}
		/**
		 * @return Devuelve paramClavesSoapMethod.
		 */
		public String getParamClavesSoapMethod() {
			return paramClavesSoapMethod;
		}
		/**
		 * @param paramClavesSoapMethod El paramClavesSoapMethod a establecer.
		 */
		public void setParamClavesSoapMethod(String paramClavesSoapMethod) {
			this.paramClavesSoapMethod = paramClavesSoapMethod;
		}
		/**
		 * @return Devuelve paramClavesSoapUrl.
		 */
		public String getParamClavesSoapUrl() {
			return paramClavesSoapUrl;
		}
		/**
		 * @param paramClavesSoapUrl El paramClavesSoapUrl a establecer.
		 */
		public void setParamClavesSoapUrl(String paramClavesSoapUrl) {
			this.paramClavesSoapUrl = paramClavesSoapUrl;
		}
		/**
		 * @return Devuelve paramClavesSoapXmlnsBoby.
		 */
		public String getParamClavesSoapXmlnsBoby() {
			return paramClavesSoapXmlnsBoby;
		}
		/**
		 * @param paramClavesSoapXmlnsBoby El paramClavesSoapXmlnsBoby a establecer.
		 */
		public void setParamClavesSoapXmlnsBoby(String paramClavesSoapXmlnsBoby) {
			this.paramClavesSoapXmlnsBoby = paramClavesSoapXmlnsBoby;
		}
	}



	public class ParamDatacom implements java.io.Serializable {

		
		private String paramDatacomDriver;
		private String paramDatacomBd;
		private String paramDatacomUrl;
		private String paramDatacomPort;
		private String paramDatacomUser;
		private String paramDatacomPass;
		private String paramDatacomSystem;
		private String paramDatacomApp;
		private String paramDatacomServer;
		private String paramDatacomMaxconnections;
		private String paramDatacomWaittime;
		private String paramDatacomInitialconnections;

		
		
		/**
		 * @return Devuelve paramDatacomApp.
		 */
		public String getParamDatacomApp() {
			return paramDatacomApp;
		}
		/**
		 * @param paramDatacomApp El paramDatacomApp a establecer.
		 */
		public void setParamDatacomApp(String paramDatacomApp) {
			this.paramDatacomApp = paramDatacomApp;
		}
		/**
		 * @return Devuelve paramDatacomBb.
		 */
		public String getParamDatacomBd() {
			return paramDatacomBd;
		}
		/**
		 * @param paramDatacomBb El paramDatacomBb a establecer.
		 */
		public void setParamDatacomBd(String paramDatacomBd) {
			this.paramDatacomBd = paramDatacomBd;
		}
		/**
		 * @return Devuelve paramDatacomDriver.
		 */
		public String getParamDatacomDriver() {
			return paramDatacomDriver;
		}
		/**
		 * @param paramDatacomDriver El paramDatacomDriver a establecer.
		 */
		public void setParamDatacomDriver(String paramDatacomDriver) {
			this.paramDatacomDriver = paramDatacomDriver;
		}
		/**
		 * @return Devuelve paramDatacomInitialconnections.
		 */
		public String getParamDatacomInitialconnections() {
			return paramDatacomInitialconnections;
		}
		/**
		 * @param paramDatacomInitialconnections El paramDatacomInitialconnections a establecer.
		 */
		public void setParamDatacomInitialconnections(
				String paramDatacomInitialconnections) {
			this.paramDatacomInitialconnections = paramDatacomInitialconnections;
		}
		/**
		 * @return Devuelve paramDatacomMaxconnections.
		 */
		public String getParamDatacomMaxconnections() {
			return paramDatacomMaxconnections;
		}
		/**
		 * @param paramDatacomMaxconnections El paramDatacomMaxconnections a establecer.
		 */
		public void setParamDatacomMaxconnections(
				String paramDatacomMaxconnections) {
			this.paramDatacomMaxconnections = paramDatacomMaxconnections;
		}
		/**
		 * @return Devuelve paramDatacomPass.
		 */
		public String getParamDatacomPass() {
			return paramDatacomPass;
		}
		/**
		 * @param paramDatacomPass El paramDatacomPass a establecer.
		 */
		public void setParamDatacomPass(String paramDatacomPass) {
			this.paramDatacomPass = paramDatacomPass;
		}
		/**
		 * @return Devuelve paramDatacomPort.
		 */
		public String getParamDatacomPort() {
			return paramDatacomPort;
		}
		/**
		 * @param paramDatacomPort El paramDatacomPort a establecer.
		 */
		public void setParamDatacomPort(String paramDatacomPort) {
			this.paramDatacomPort = paramDatacomPort;
		}
		/**
		 * @return Devuelve paramDatacomServer.
		 */
		public String getParamDatacomServer() {
			return paramDatacomServer;
		}
		/**
		 * @param paramDatacomServer El paramDatacomServer a establecer.
		 */
		public void setParamDatacomServer(String paramDatacomServer) {
			this.paramDatacomServer = paramDatacomServer;
		}
		/**
		 * @return Devuelve paramDatacom.
		 */
		public String getParamDatacomSystem() {
			return paramDatacomSystem;
		}
		/**
		 * @param paramDatacomSystem El paramDatacomSystem a establecer.
		 */
		public void setParamDatacomSystem(String paramDatacomSystem) {
			this.paramDatacomSystem = paramDatacomSystem;
		}
		/**
		 * @return Devuelve paramDatacomUrl.
		 */
		public String getParamDatacomUrl() {
			return paramDatacomUrl;
		}
		/**
		 * @param paramDatacomUrl El paramDatacomUrl a establecer.
		 */
		public void setParamDatacomUrl(String paramDatacomUrl) {
			this.paramDatacomUrl = paramDatacomUrl;
		}
		/**
		 * @return Devuelve paramDatacomUser.
		 */
		public String getParamDatacomUser() {
			return paramDatacomUser;
		}
		/**
		 * @param paramDatacomUser El paramDatacomUser a establecer.
		 */
		public void setParamDatacomUser(String paramDatacomUser) {
			this.paramDatacomUser = paramDatacomUser;
		}
		/**
		 * @return Devuelve paramDatacomWaittime.
		 */
		public String getParamDatacomWaittime() {
			return paramDatacomWaittime;
		}
		/**
		 * @param paramDatacomWaittime El paramDatacomWaittime a establecer.
		 */
		public void setParamDatacomWaittime(String paramDatacomWaittime) {
			this.paramDatacomWaittime = paramDatacomWaittime;
		}
		
		
		
		
	}

	
}
