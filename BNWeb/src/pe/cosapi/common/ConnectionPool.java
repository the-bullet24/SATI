package pe.cosapi.common;

import java.sql.*;  
import java.util.*;
import java.io.*;
import java.util.Date;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.com.bn.sati.common.LoggerSati;


/**
 *
 * @author  
 * @version 
 */


public class ConnectionPool implements Runnable { 
	private static LoggerSati log3 = LoggerSati.getInstance(ConnectionPool.class.getName());
    
	private String driver, url;
	private int maxConnections,initialConnections;
	private long waittime;
	private boolean waitIfBusy;
	private Vector availableConnections, busyConnections;
	private boolean connectionPending = false;
	private int errorConnections;

    public ConnectionPool()throws SQLException 
    {
    
    	dataSource();
    	
    	errorConnections = initialConnections;
        availableConnections = new Vector(initialConnections);
        busyConnections = new Vector();
        for(int i=0; i<initialConnections; i++) {
        	Connection con = null;
        	con = makeNewConnection();
        	if(con!=null){
        		availableConnections.addElement(con);
        		errorConnections--;
        	}
        }
    }
    
    	
   public void dataSource() throws SQLException  {
	   
	   java.util.ResourceBundle resource=null;
	   
//	   
  		String prop_driver =Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_DRIVER);
   		String prop_db = 	Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_DB);
   		String prop_url = 	Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_URL);
		String prop_port = 	Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_PORT);
		String prop_user = 	Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_USER);
		String prop_pass =  Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_PASS);
		String prop_system = Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_SYSTEM);
		String prop_app = Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_APP);
		String prop_server = Parametro.getString(ConstanteParametros.BN_PARAM_DATACOM_SATI_SERVER);
		int prop_maxconnections = Integer.parseInt("5");
		int prop_waittime = Integer.parseInt("2");
		int prop_initialconnections = Integer.parseInt("3");
	   
        this.driver = prop_driver;
      	
		this.url = 
			"jdbc:" + prop_db + 
			((prop_url.length()>0)?("://" + prop_url ):"") +
			":" + prop_port + 
			"/ServerName=" + prop_server + 
			",SystemID=" + 	prop_system +
			",ApplicationID=" + prop_app + 
			",UserID=" + prop_user + 
			",Password=" +prop_pass;
		
 		
		this.maxConnections = prop_maxconnections;
        this.waittime=prop_waittime*1000;
        this.initialConnections = prop_initialconnections;
    	
        if (initialConnections > maxConnections){
            initialConnections = maxConnections;
        }
    	
  
    	
    }
  
    /**
     * Obtener una conexión a la base de datos. <p>
     * La conexión a entregar no debe estar inválida o cerrada.    
     * @return Conexión a la base de datos.
     * @throws SQLException
     */
    public synchronized Connection getConnection()throws SQLException {

    	// Verificando Conexiones Disponibles.
        if (! availableConnections.isEmpty()) {
        	//Si existen conexiones disponibles.
            Connection existingConnection = (Connection)availableConnections.lastElement();

            if(existingConnection!=null)
            	availableConnections.removeElement(existingConnection);

            // Si la conexión esta inválida o cerrada no sera entregada.
            if (estaInvalida(existingConnection) || existingConnection.isClosed()) {
            	errorConnections++;
                notifyAll();
                return getConnection();
            // Caso contrario se entrega la conexión.
            } else {
            	errorConnections--;
                busyConnections.addElement(existingConnection);
                return existingConnection;
            }
        // Caso contrario se creará una conexión.
        } else {
        	
			Calendar cal=Calendar.getInstance();
			
			if(errorConnections>=maxConnections){
				throw new SQLException("Numero de errores superado");
			}
			
            if (totalConnections() < maxConnections && ! connectionPending) {              
                makeBackgroundConnection();
            } 
            
			cal.setTime(new Date());
			int ini = cal.get(Calendar.SECOND);//inicia wait
			try {
				wait(waittime);	
			} catch(InterruptedException ie) {
				log3.error(ie,Constante.ERR_LOGICA_NEGOCIO,"");
				ie.printStackTrace();
			}

			cal.setTime(new Date());
			int fin = cal.get(Calendar.SECOND);//finaliza wait
			int dif = (fin >= ini ? fin - ini : ((60-ini)+fin));//calcula diferencia
			
			long difNum = dif * 1000 ;
			if ( difNum >= waittime) {			
				throw new SQLException("Sobrepaso el Limite de Tiempo de Espera:" + difNum + " s.");
			} else {			

			}
            return getConnection();
        }
    }


    private void makeBackgroundConnection() { 
    	
    	connectionPending = true;
    	try {  	        
    		Thread connectThread = new Thread(this);
            connectThread.start();
  	   	} catch (Exception e) {
  	   	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  	   	
  	    	e.printStackTrace();
  	   	}  	  
    }

    public void run() {

    	Connection connection = makeNewConnection();
    	synchronized (this) { 
        	if(connection!=null){
        		availableConnections.addElement(connection);
        	}else{
        		errorConnections++;
        	}
    		connectionPending = false;
    		notifyAll();
    	}        
    }
    private Connection makeNewConnection() {    

    	Connection connection =null;
    	try {         
            Class.forName(driver).newInstance();      
            connection = DriverManager.getConnection(url);

        }catch(Exception cnfe){
        	log3.error(cnfe,Constante.ERR_LOGICA_NEGOCIO,"");
        	cnfe.printStackTrace();
            connection =null;
        }
        return connection;
        
    }


    public synchronized void free(Connection connection) { 
    	if(connection!=null){
            if (busyConnections.removeElement(connection)){             
   		     availableConnections.addElement(connection);
   			 notifyAll(); 			 	
           }
    	}
    }
	
	
    public synchronized int totalConnections() {		
		return busyConnections.size() + availableConnections.size();
	}
      
	public synchronized String ConnectionsInfo() {
		return "Total de conexiones disponibles: " + availableConnections.size() + ", conexiones ocupadas: " + busyConnections.size();
	}

	public synchronized boolean reloadConnection() {
		
		//si el numero de errroes es igual o mayor al número topoe de conexiones
		boolean reload = false;
		
		if(errorConnections>=maxConnections){
			reload=true;
		}
		return reload;
	}
	
	
	private boolean estaInvalida(Connection conexion) {    

    	boolean invalida = false; 
    	
    	// Verifica si la conexión esta inválida.    
    	Statement sentencia = null;
    	try {
    	    sentencia = conexion.createStatement();    	 
    	    sentencia.execute("select A.F02_ATRIBUTO tributo from BNREF02 A fetch first 10 row only ");
    	    
        } catch(Exception e) {
        	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
              		e.printStackTrace();
            invalida = true;
    	} finally {
    	    try {
    	        if (sentencia != null){
    	        	sentencia.close();
    	        }
    	    } catch (Exception i) {
    	    	log3.error(i,Constante.ERR_LOGICA_NEGOCIO,"");
    	    		i.printStackTrace();
    	    }
    	}
    	
    	if (invalida) {
    	    try {
    	        if (conexion != null){
    	        	if (!conexion.isClosed()){
    	        		conexion.close();
    	        	}
    	        }
    	    } catch (Exception i) {
    	    	log3.error(i,Constante.ERR_LOGICA_NEGOCIO,"");
    	    		i.printStackTrace();
    	    }
    	    conexion = null;
    	}
		return invalida;
    }
    
    public void cerrarConexiones(){
    	
    	try{
    		Vector cnxs = availableConnections;
    		//cerrando trx disponibles
    		if(cnxs!=null){
        		for(int i=0;i<cnxs.size();i++){
        			Connection conelement = (Connection)cnxs.get(i);
        			if(conelement!=null){
        	    	    try {
        	    	    	if(!conelement.isClosed()) 
        	    	    		conelement.close();
        	    	    } catch (Exception ex) {
        	    	    	log3.error(ex,Constante.ERR_LOGICA_NEGOCIO,"");
        	    	    		 //log3.error(ex,Constante.ERR_CONECTION_DATACOM,"");
        	    	    }
        				conelement= null;
        			}
        		}
        	}
   		
    	}catch (Exception e) {
    		
    		e.printStackTrace();
		}
    	
    }
    
}