/*
 * Creado el 21/04/2008
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.WSServiceHost.serHost.model;

import pe.bn.WSServiceHost.serHost.interactive.WSData;


/**
 * @author llavado
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class HeadRespose extends WSData {


	public void init(){
		this.campos = new Campo[8];
		try {
			this.campos[0]		= 	new Campo("HD-LONG",		Campo.NUM_CODIGO,4);
			this.campos[1]		=	new Campo("HD-TRAN",		Campo.CHAR_LEFT,4);
			this.campos[2]		=	new Campo("HD-SUB-TRX",		Campo.CHAR_LEFT,4);
			this.campos[3]		=	new Campo("HD-FECHA",		Campo.CHAR_LEFT,8);
			this.campos[4]		=	new Campo("HD-HORA",		Campo.CHAR_LEFT,6);
			this.campos[5]		=	new Campo("HD-ENTIDAD",		Campo.CHAR_LEFT,4);
			this.campos[6]		=	new Campo("HD-COD-RET",		Campo.CHAR_LEFT,5);
			this.campos[7]		=	new Campo("HD-DES-RET",		Campo.CHAR_LEFT,25);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public HeadRespose() {
		super();
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public void FillCabecera(String cabeceraRespose) throws Exception {
		
		init();
		super.FillBoby(cabeceraRespose);
	}
}
