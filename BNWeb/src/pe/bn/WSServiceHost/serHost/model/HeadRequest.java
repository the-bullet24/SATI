package pe.bn.WSServiceHost.serHost.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pe.bn.WSServiceHost.serHost.interactive.WSData;
import pe.com.bn.common.Constante;


 

public class HeadRequest extends WSData {
	public static int TIPO_TRANSACCION_HP = 1;
	public static int TIPO_TRANSACCION_CH = 2;
	public void init(){
		this.campos = new Campo[17];
		try {
				this.campos[0]		= 	new Campo("HD-MSJE",		Campo.CHAR_LEFT,4);
				this.campos[1]		=	new Campo("HD-CAPLIC",		Campo.CHAR_LEFT,4);
				this.campos[2]		=	new Campo("HD-CTRANS",		Campo.CHAR_LEFT,4);
				this.campos[3]		=	new Campo("HD-TTRANS",		Campo.CHAR_LEFT,1);
				this.campos[4]		=	new Campo("HD-IND-TRANS",	Campo.CHAR_LEFT,1);
				this.campos[5]		=	new Campo("HD-CMONED",		Campo.CHAR_LEFT,3);
				this.campos[6]		=	new Campo("HD-CCANAL",		Campo.CHAR_LEFT,3);
				this.campos[7]		=	new Campo("HD-CCAJER",		Campo.CHAR_LEFT,4);
				this.campos[8]		=	new Campo("HD-COFIC",		Campo.CHAR_LEFT,4);
				this.campos[9]		=	new Campo("HD-CENTIDAD",	Campo.CHAR_LEFT,8);
				this.campos[10]		=	new Campo("HD-UBIGEO",		Campo.CHAR_LEFT,8);
				this.campos[11]		=	new Campo("HD-COFICINA",	Campo.CHAR_LEFT,4);
				this.campos[12]		=	new Campo("HD-SECUENCIA",	Campo.CHAR_LEFT,7);
				this.campos[13]		=	new Campo("HD-FEC-TOLD",	Campo.CHAR_LEFT,8);
				this.campos[14]		=	new Campo("HD-FEC-TODAY",	Campo.CHAR_LEFT,8);
				this.campos[15]		=	new Campo("HD-HORA",		Campo.CHAR_LEFT,6);
				this.campos[16]		=	new Campo("HD-CTERM",		Campo.CHAR_LEFT,4);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HeadRequest() throws Exception {
		init();
			
		    	String longs =  Constante.gBN_CONST_SOAP_LONGS;	   
		    	String aplic =  Constante.gBN_CONST_SOAP_CAPLIC;	   
		    	String trans =  Constante.gBN_CONST_SOAP_CTRANS;	   
				this.campos[0].setString(longs);
				this.campos[1].setString(aplic);
				this.campos[2].setString(trans);	
				this.campos[3].setString("N");	
				this.campos[4].setString("N");	
				this.campos[5].setString("PEN");	
				this.campos[6].setString("WEB");
				this.campos[7].setString("    ");
				this.campos[8].setString("0000");
				this.campos[9].setString("        ");
				this.campos[10].setString("        ");
				this.campos[11].setString("0000");
				this.campos[12].setString("0000000");
				this.campos[13].setString("00000000");
				this.campos[14].setString("00000000");
				this.campos[15].setString("000000");
				this.campos[16].setString("    ");
	
}
}

