package pe.bn.WSServiceHost.serHost.interactive;



import javax.xml.rpc.holders.StringHolder;
import org.apache.log4j.Logger;
import pe.bn.service.bean.RequestGateway;
import pe.bn.service.bean.ResponseGateway;
import pe.bn.service.interfaz.GatewayInterfaceProxy;

 


public class WSConnect  {

	
	private static Logger log = Logger.getLogger(WSConnect.class.getName());
	
	
	
	
	public int EjecutarTramaHost(
			
			String requestTrama,
			StringHolder responseTrama) throws Exception{		
		int codRes = 9997;
		RequestGateway request = new RequestGateway();
	    ResponseGateway response = new ResponseGateway();
	    String TempDescRes = "Error Interfaz SOAP:";
	 	
		try{
			
//			System.out.println("requestTrama:"+requestTrama);
	    	
 		    String Transid = requestTrama.substring(0,4);
// 		    System.out.println("Transid:"+Transid);
		    String longitud = "9999"; 
		    String filler= "";			    
		    GatewayInterfaceProxy proxi = new GatewayInterfaceProxy();		    
		    request.setLongitud(longitud);
		    request.setTransid("WS20");
		    request.setDatos(requestTrama);
		    request.setFiller(filler);	 		    
		    response = proxi.enviarTramaConsulta(pe.com.bn.common.Constante.BN_KEY_WS_GATEWAY,request);	
		     
	 	    if (response.getMsgno() != null && !response.getMsgno().equals("")){
			    codRes = (new Integer(response.getMsgno())).intValue();
			    if(codRes != 0){
			    	log.info(TempDescRes+ " Nro Msg:" + response.getMsgno());
			    	log.info(TempDescRes+ " Desc Msg:" + response.getMensaje());
			    }
		    }else{
		    	codRes = 9995;
	    		response.setDatos("");
	    		response.setMsgno(codRes+"");
	    		response.setMensaje(TempDescRes + "Sin Mensaje de Respuesta");
		    	log.info(TempDescRes+ " Nro Msg:" + response.getMsgno());
		    	log.info(TempDescRes+ " Desc Msg:" + response.getMensaje());
		    }		    
		    responseTrama.value = response.getDatos();			
		}catch(Exception ex){
			codRes = 9996;
			responseTrama.value = "";
    		response.setDatos("");
    		response.setMsgno(codRes+"");
    		response.setMensaje(TempDescRes + ex.toString());
    		response.setFiller("");
			log.error(ex);
			ex.printStackTrace();
		}
		return codRes;	
	}
	
}
