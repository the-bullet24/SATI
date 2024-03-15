package pe.bn.WSServiceHost.serHost.model;

 
import javax.xml.rpc.holders.StringHolder;


import pe.bn.WSServiceHost.serHost.interactive.WSConnect;



public class WSConsultaPago {

	private HeadRequest cabeceraRequest;
	private BodyConsultaPago body;
	
	public WSConsultaPago(HeadRequest cab,BodyConsultaPago input) {
		super();
		cabeceraRequest = cab;
		body = input;
	}
 
	
	public int EjecutarConsultaPago(
			HeadRespose cabeceraRespose
			,BodyConsultaPago outPut) throws Exception {
		
		String cadenaHost = new String();
		StringHolder cadenaResult = new StringHolder();		
		WSConnect wsConnect  =  new WSConnect();
		cadenaHost = cabeceraRequest.toString() + body.toString() ;
		int result = 9999;
		
//		System.out.println("cadenaHost:"+cadenaHost);
		 
		result =  wsConnect.EjecutarTramaHost(cadenaHost,cadenaResult);
		 
		int longitudHead = cabeceraRequest.LongitudTrama();
		int longitudresult =cadenaResult.value.length();
		
		if (longitudresult >= longitudHead){
			String HeadResult = cadenaResult.value.substring(0,longitudHead);
			cabeceraRespose.FillCabecera(HeadResult);
		}else{
			cabeceraRespose = null;
		};

		int longitudBody =  body.LongitudTrama();
		if ( longitudresult >= longitudHead + longitudBody){
			String BodyResult = cadenaResult.value.substring(longitudHead,longitudHead + longitudBody);
			//outPut.FillBobyConsultaPago(BodyResult);
		}else{
			outPut = null;
		};
		return result;
	}
	
 
	
	public BodyConsultaPago getBody() {
		return body;
	}
	public void setBody(BodyConsultaPago body) {
		this.body = body;
	}
	public HeadRequest getCabeceraRequest() {
		return cabeceraRequest;
	}
	public void setCabeceraRequest(HeadRequest cabeceraRequest) {
		this.cabeceraRequest = cabeceraRequest;
	}
	
}
