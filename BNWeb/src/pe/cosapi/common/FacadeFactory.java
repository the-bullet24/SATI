/*
 * Creado el 07/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de cdigo - Plantillas de cdigo
 */
package pe.cosapi.common;

import pe.bn.afiliacion.facade.AfiliacionFacade;
import pe.bn.antiphishing.facade.ServiciosAntiphishingFacade;
import pe.bn.cldinamica.dao.afiliacionDAO;
import pe.bn.consulta.facade.ConsultaFacade;
import pe.bn.consulta.facade.ConsultaWAPFacade;
import pe.bn.consulta.facade.PrestamoFacade;
import pe.bn.consulta.facade.SeleccionMontoFacade;
import pe.bn.login.facade.LoginFacade;
import pe.bn.notificaciones.facade.ParametrosSiatFacade;
import pe.bn.notificaciones.facade.TransaccionFacade;
import pe.bn.pago.facade.PagoFacade;
import pe.bn.tarjeta.facade.TarjetaFacade;
import pe.bn.tcredito.facade.TarjetaCreditoFacade;
import pe.bn.telegiro.facade.TelegiroFacade;
import pe.bn.transferencia.facade.TransferenciaFacade;
import pe.bn.trasferenciacontacto.facade.TransferenciaContactoFacade;
import pe.cosapi.facade.GeneralFacade;

/**
 * @author cosapi_ati04 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de cdigo - Plantillas de cdigo
 */
public class FacadeFactory extends ObjectFactory {

	public static ConsultaFacade getConsultaFacade(){
		return (ConsultaFacade)getObject("consultaFacade");
	}
	
	public static ConsultaWAPFacade getConsultaWAPFacade(){
		return (ConsultaWAPFacade)getObject("consultaFacade");
	}
	
	public static LoginFacade getLoginFacade(){
		return (LoginFacade)getObject("loginFacade");
	}
	
	public static PagoFacade getPagoFacade(){
		return (PagoFacade)getObject("pagoFacade");
	}
	
	public static TransferenciaFacade getTransferenciaFacade(){
		return (TransferenciaFacade)getObject("transferenciaFacade");
	}
	
	public static GeneralFacade getGeneralFacade(){
		return (GeneralFacade)getObject("generalFacade");
	}
	
	public static TarjetaFacade getTarjetaFacade(){
		return (TarjetaFacade)getObject("tarjetaFacade");
	}
	
	public static AfiliacionFacade getAfiliacionFacade(){
		return (AfiliacionFacade)getObject("afiliacionFacade");
	}
	
	public static TelegiroFacade getTelegiroFacade(){
		return (TelegiroFacade)getObject("telegiroFacade");
	}

	public static TarjetaCreditoFacade getTarjetaCreditoFacade(){
		return (TarjetaCreditoFacade)getObject("tarjetaCreditoFacade");
	}
	
	public static SeleccionMontoFacade getSeleccionMontoFacade(){
		return (SeleccionMontoFacade)getObject("seleccionMontoFacade");
	}
	
	public static TransaccionFacade getTransaccionFacade(){
		return (TransaccionFacade)getObject("transaccionFacade");
	}
	
	public static ParametrosSiatFacade getParametrosSiatFacade(){
		return (ParametrosSiatFacade)getObject("parametrosSiatFacade");
	}
	public static ServiciosAntiphishingFacade getServiciosAntiphishingFacade(){
		return (ServiciosAntiphishingFacade)getObject("serviciosAntiphishingFacade");
	}
	
	public static TransferenciaContactoFacade getTransferenciaContactoFacade(){
		return (TransferenciaContactoFacade)getObject("transferenciaContactoFacade");
	}
	
	
	public static PrestamoFacade getPrestamoFacade(){
		return (PrestamoFacade)getObject("prestamoFacade");
	}
	/**
	 */
	public void Operacion1() {
		return;
	}
	/**
	 */
	public void Operacion2() {
		return;
	}
	/**
	 */
	public void Operacion3() {
		return;
	}
}