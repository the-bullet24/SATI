/*
	 * Creado el 22/04/2008
	 *
	 * TODO Para cambiar la plantilla de este archivo generado, vaya a
	 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
	 */
	package pe.bn.WSServiceHost.serHost.model;
	
	import pe.bn.WSServiceHost.serHost.interactive.WSData;
import pe.bn.WSServiceHost.serHost.interactive.WSData.Campo;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.cosapi.domain.Usuario;
	
	/**
	 * @author llavado
	 *
	 * TODO Para cambiar la plantilla de este comentario generado, vaya a
	 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
	 */
	public class BodyConsultaPago extends WSData  {
	

		
		public void initConsultaPago(){
			this.campos = new Campo[11];
			try {
				int num = -1;
				
				this.campos[++num]		= 	new Campo("BD-FECHA-NAC",		Campo.NUM_CODIGO,15);
				this.campos[++num]		= 	new Campo("BD-NOMBRE-REP",		Campo.CHAR_LEFT,20);
				this.campos[++num]		= 	new Campo("BD-APATERNO-REP",	Campo.CHAR_LEFT,20);
				this.campos[++num]		= 	new Campo("BD-AMATERNO-REP",	Campo.CHAR_LEFT,20);
				this.campos[++num]		= 	new Campo("BD-NUM-TARJETA",		Campo.NUM_CODIGO,30);
				this.campos[++num]		= 	new Campo("BD-TIPO-DOC",		Campo.NUM_CODIGO,3);
				this.campos[++num]		= 	new Campo("BD-NUM-DOC",			Campo.NUM_CODIGO,20);
				this.campos[++num]		= 	new Campo("BD-SEXO",			Campo.CHAR_LEFT,1);
				this.campos[++num]		= 	new Campo("BD-CLAVE-VAL",		Campo.CHAR_LEFT,6);
				this.campos[++num]		= 	new Campo("BD-IP",				Campo.CHAR_LEFT,15);
				this.campos[++num]		= 	new Campo("BD-EMAIL",			Campo.CHAR_LEFT,30);
//				this.campos[++num]		= 	new Campo("BD-DIGITO",			Campo.CHAR_LEFT,10);
//				this.campos[++num]		= 	new Campo("BD-IMPORTE",			Campo.CHAR_LEFT,15);
//				this.campos[++num]		= 	new Campo("BD-COD-BANCO",		Campo.CHAR_LEFT,2);
//				this.campos[++num]		= 	new Campo("BD-NRO-CHEQUE",		Campo.CHAR_LEFT,8);
//				this.campos[++num]		= 	new Campo("BD-CJUZGADO",		Campo.CHAR_LEFT,10);				
//				this.campos[++num]		= 	new Campo("BD-KREGISTROS",		Campo.CHAR_LEFT,5);
//				this.campos[++num]		= 	new Campo("BD-NEXPEDIENTE",		Campo.CHAR_LEFT,8);
//				this.campos[++num]		= 	new Campo("BD-HORA-PAGO",		Campo.CHAR_LEFT,6);
//				this.campos[++num]		= 	new Campo("BD-COD-AGENCIA",		Campo.CHAR_LEFT,4);
//				this.campos[++num]		= 	new Campo("BD-COD-TRX",			Campo.CHAR_LEFT,4);
//				this.campos[++num]		= 	new Campo("BD-COD-BENEFICIARIO",Campo.CHAR_LEFT,3);
//				this.campos[++num]		= 	new Campo("BD-DIG-CHEQUEO",		Campo.CHAR_LEFT,1);
//				this.campos[++num]		= 	new Campo("BD-NPAGOS",			Campo.CHAR_LEFT,4);
//				this.campos[++num]		= 	new Campo("BD-COD-ADMINIST",	Campo.CHAR_LEFT,3);				
//				this.campos[++num]		= 	new Campo("BD-UBIGEO",			Campo.CHAR_LEFT,6);
//				this.campos[++num]		= 	new Campo("BD-CONTRIBUYENTE",	Campo.CHAR_LEFT,15);
//				this.campos[++num]		= 	new Campo("BD-FILLER",			Campo.CHAR_LEFT,50);
 				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 
		public BodyConsultaPago(){
			super();
		}
 
		
		
		public void InputDataConsultaPago(Afiliacion afil, Usuario usuario, String ip, String claveValid) throws Exception {
			
			initConsultaPago();
			int num = -1;
			boolean result = true; 
		
			result = ValidarCampo(true,this.campos[++num].setString(afil.getFechaNacimientoConv()));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getObjBenef().getNombre()));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getObjBenef().getApellidoPaterno()));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getObjBenef().getApellidoMaterno()));
			result = ValidarCampo(result,this.campos[++num].setString(usuario.getTarjeta().getNumero()));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getTipoDocumento()));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getNroDocumento()));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getSexo()));
			result = ValidarCampo(result,this.campos[++num].setString(claveValid));
			result = ValidarCampo(result,this.campos[++num].setString(ip));
			result = ValidarCampo(result,this.campos[++num].setString(afil.getEmail()));
			
		
			if (!result){
				throw new Exception("Error al Dar Formato a la clase:" + this.getClass());
			}
			
		}
		
 
		
		public void FillBobyConsultaPago(String bodyOutPut) throws Exception {
			initConsultaPago();
			super.FillBoby(bodyOutPut);
		}
		
 
	 
	
	}
