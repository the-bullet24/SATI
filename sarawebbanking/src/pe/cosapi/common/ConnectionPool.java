package pe.cosapi.common;

import java.sql.*;  
import java.util.*;
import java.io.*;
import java.util.Date;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import pe.bn.listener.Constante;

import pe.bn.listener.Parametro;


/**
 *
 * @author  
 * @version 
 */


public class ConnectionPool implements Runnable { 
    
	private String driver, url;
	private int maxConnections,initialConnections;
	private long waittime;
	private boolean waitIfBusy;
	private Vector availableConnections, busyConnections;
	private boolean connectionPending = false;

    public ConnectionPool()throws SQLException 
    {
    	java.util.ResourceBundle resource=null;
    	
    	String prop_driver =Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_DRIVER);
   		String prop_db = 	Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_DB);
   		String prop_url = 	Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_URL);
		String prop_port = 	Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_PORT);
		String prop_user = 	Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_USER);
		String prop_pass =  Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_PASS);
		String prop_system = Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_SYSTEM);
		String prop_app = Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_APP);
		String prop_server = Parametro.getString(Constante.BN_PARAM_DATACOM_SATI_SERVER);
		int prop_maxconnections = Integer.parseInt("5");
		int prop_waittime = Integer.parseInt("2");
		int prop_initialconnections = Integer.parseInt("3");
	   
        this.driver = prop_driver;
        //xml.getNombreDriverBD();
      	
		this.url = "jdbc:" + prop_db + "://" + prop_url +
		":" + prop_port + "/ServerName=" + prop_server + ",SystemID=" + prop_system +
		",ApplicationID=" + prop_app + ",UserID=" + prop_user + ",Password=" + prop_pass;
		
		//if(Constante.VER_LOG)System.out.println("CADENA DE CONEXION: " + this.url);

		//xml.getPreURLBD()+xml.getNombreHostBD() +":" + xml.getPuertoBD() + ":" + xml.getNombreInstanciaBD();
        this.maxConnections = prop_maxconnections;//modificado sppmejia
        this.waittime=prop_waittime*1000;//modificado sppmejia
        this.waitIfBusy = true;
        this.initialConnections = prop_initialconnections;//modificado sppmejia
        if (initialConnections > maxConnections) 
        {
            initialConnections = maxConnections;
        }
        availableConnections = new Vector(initialConnections);
        busyConnections = new Vector();
        for(int i=0; i<initialConnections; i++) {
            availableConnections.addElement(makeNewConnection());
        }               
    }
  
    /**
     * Obtener una conexión a la base de datos. <p>
     * La conexión a entregar no debe estar inválida o cerrada.    
     * @return Conexión a la base de datos.
     * @throws SQLException
     */
    public synchronized Connection getConnection()throws SQLException {
    	// Si existen conexiones disponibles.
        if (! availableConnections.isEmpty()) {
            Connection existingConnection = (Connection)availableConnections.lastElement();
            int lastIndex = availableConnections.size() - 1;
            availableConnections.removeElementAt(lastIndex);
            System.out.println("Se verificará el estado de la conexión [" + lastIndex + "]");
            // Si la conexión esta inválida o cerrada no sera entregada.
            if (estaInvalida(existingConnection) || existingConnection.isClosed()) {            
            //if ((existingConnection==null) || existingConnection.isClosed()) {
                System.out.println("Conexión [" + lastIndex + "] no será entregada debido a que esta inválida o cerrada");
                notifyAll();
                return getConnection();
            // Caso contrario se entrega la conexión.
            } else {             
           		System.out.println("Conexión [" + lastIndex + "] esta OK y se agregará a la cola de conexiones ocupadas");
                busyConnections.addElement(existingConnection);
                System.out.println("Conexión [" + lastIndex + "] será entregada (" + existingConnection + ")");
                return existingConnection;
            }
        // Caso contrario se creará una conexión.
        } else {        
			Calendar cal=Calendar.getInstance();
            if (totalConnections() < maxConnections && ! connectionPending) {              
                System.out.println("Se creará una conexión a la BD");

                makeBackgroundConnection();
            } 
            
            /**modificado sppmejia inicio **/
			cal.setTime(new Date());
			int ini = cal.get(Calendar.SECOND);//inicia wait
			try {			
				wait(waittime);	
			} catch(InterruptedException ie) {
			    System.out.println("Error de Interrupcion en el WAIT: " + ie.getMessage());
			}
			
			cal.setTime(new Date());
			int fin = cal.get(Calendar.SECOND);//finaliza wait
			int dif = fin >= ini ? fin - ini : ((60-ini)+fin);//calcula diferencia
			if (dif * 1000 >= waittime) {			
				throw new SQLException("Limite de Tiempo de Espera");
			} else {			
				System.out.println("Tiempo de espera por conexion: " + dif);
			}
			/**modificado sppmejia fin**/
            return getConnection();
        }
    }

    private void makeBackgroundConnection() {     
    	connectionPending = true;
    	try {  	        
    		Thread connectThread = new Thread(this);
            connectThread.start();
  	   	} catch (OutOfMemoryError e) {
  	   		System.out.println("Error Out Of Memory :"+e.getMessage());
  	   	}  	  
    }

    public void run() {
    	Connection connection = makeNewConnection();
    	synchronized (this) { 		
    		availableConnections.addElement(connection);
    		connectionPending = false;
    		notifyAll();
    	}        
    }
  
    private Connection makeNewConnection() {    
        try {          
            Class.forName(driver).newInstance();
            Connection connection = DriverManager.getConnection(url);
            System.out.println(url);
            return(connection);
        } catch(Exception cnfe) {          
            System.out.println("ERROR "+cnfe.getMessage());
            try {            
            	PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("/home/logs/logsPool.txt"))); 
            	ps.println("ERROR : "+cnfe.getMessage());
            	cnfe.printStackTrace(ps);
                ps.close();
        	} catch (Exception e){
        	    System.out.println("Error de escritura en logsPool: " + e.getMessage());
        	}        	
        }
        
        return null;
    }

    public synchronized void free(Connection connection) {       
        if (busyConnections.removeElement(connection)) {             
			 System.out.println("****" + ConnectionsInfo());
			 System.out.println("****Se esta liberando la conexion "+connection);
		     availableConnections.addElement(connection);
			 //sppmejia
			 notifyAll(); 			 	
        } else {        
        	//connection=null;
        	System.out.println("*-*-*-*-*-No encontro a la conexion dentro de los busyConnection");
        }
        
    }
	
	public synchronized int totalConnections() {		
		return busyConnections.size() + availableConnections.size();
	}
      
	public synchronized String ConnectionsInfo() {
		return "Total de conexiones disponibles: " + availableConnections.size() + ", conexiones ocupadas: " + busyConnections.size();
	}
	
	/**
     * Verifica si la conexión ingresada como parámetro esta inválida.
     * @param conexion Conexión a la base de datos.
     * @return Verdadero si la conexión esta inválida, falso en caso contrario.
     */
   private boolean estaInvalida(Connection conexion) {    
    	boolean invalida = false;
    	System.out.println("METODO ESTAINVALIDA()");
    	// Verifica si la conexión esta inválida.
    	Statement sentencia = null;
    	try {
    	    sentencia = conexion.createStatement();
    	    sentencia.execute("SELECT" +
							  "		A.F02_CTRIBUTO tributo " +
							  "FROM" +
							  "		NACION.BNREF02 A");
			
        } catch(Exception e) {
            invalida = true;
    	} finally {
    	    try {
    	        if (sentencia != null)	{	sentencia.close();	}
    	    } catch (Exception i) { }
    	}
    	
    	// Si la conexión esta inválida entonces la cerramos.
    	if (invalida) {
    	    System.out.println("CONEXION SERA CERRADA CLOSE()");
    	    try {
    	        if (conexion != null)	{ 	conexion.close();	}
    	    } catch (Exception i) {	}
    	}

		return invalida;
    }
}