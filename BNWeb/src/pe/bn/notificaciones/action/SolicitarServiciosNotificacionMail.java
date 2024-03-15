package pe.bn.notificaciones.action;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

import pe.bn.notificaciones.form.NotificacionMail;
import pe.com.bn.common.Funciones;
import pe.cosapi.common.Constante;

import www.bn.simm.ws.open.bean.Adjunto;
import www.bn.simm.ws.open.bean.DatosCorreo;
import www.bn.simm.ws.open.bean.DatosParametro;
import www.bn.simm.ws.open.bean.ReqListMessage;
import www.bn.simm.ws.open.bean.RequestMessage;
import www.bn.simm.ws.open.bean.ResponseMessage;
import www.bn.simm.ws.open.service.ServiceMessage;
import www.bn.simm.ws.open.service.ServiceMessageProxy;
import www.bn.simm.ws.open.service.ServiceMessageService;
import www.bn.simm.ws.open.service.ServiceMessageServiceInformation;
import www.bn.simm.ws.open.service.ServiceMessageServiceLocator;
import www.bn.simm.ws.open.service.ServiceMessageSoapBindingStub;

public class SolicitarServiciosNotificacionMail {
	public static void enviarNotificacionMail(NotificacionMail notificacion){
		ServiceMessage serviceMessage = new ServiceMessageProxy();
		RequestMessage requestMessage = new RequestMessage();		
		ReqListMessage rm = new ReqListMessage();
		ReqListMessage[] reqListMessage;
		reqListMessage = new ReqListMessage[1]; 
		
		Adjunto adjunto = new Adjunto();
		adjunto.setFlagAdjunto(0);				
		
		DatosCorreo datosCorreo = new DatosCorreo();
		datosCorreo.setAsunto(notificacion.getAsunto());
		datosCorreo.setCorreoDestinatario(notificacion.getCdestinatario());	
		
		DatosParametro datosParametro = new DatosParametro();
		datosParametro.setParametro1(notificacion.getNcliente());
		datosParametro.setParametro2(Funciones.setNumerotarjetaAsteriscos(notificacion.getNtarjeta().trim()));
		datosParametro.setParametro3(notificacion.getCanal());
		datosParametro.setParametro4(notificacion.getEstablecimiento());
		datosParametro.setParametro5(notificacion.getToperacion());
		datosParametro.setParametro6(notificacion.getMoneda());
		datosParametro.setParametro7(notificacion.getImporte());
		datosParametro.setParametro8(notificacion.getFoperacion());
		datosParametro.setParametro9(notificacion.getHoperacion());
		
		rm.setAdjunto(adjunto);
		rm.setDatosCorreo(datosCorreo);
		rm.setDatosParametro(datosParametro);
		reqListMessage[0]=rm;
				
		requestMessage.setCodRequermiento(Constante.COD_REQUERIMIENTO);
		requestMessage.setReqListMessage(reqListMessage);
		
		try {
				
			System.out.println("NOTIFICACION MAIL - INICIO");
			serviceMessage.sendMessage(requestMessage);
			System.out.println("NOTIFICACION MAIL - FIN");
			
		} catch (RemoteException e) {
			e.printStackTrace();
//			e.getMessage();
		}
		
	}
	
}

