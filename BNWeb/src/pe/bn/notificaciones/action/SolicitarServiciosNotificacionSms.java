package pe.bn.notificaciones.action;

import java.rmi.RemoteException;

import pe.bn.notificaciones.form.NotificacionSms;
import pe.cosapi.common.Constante;

import www.bn.sims.ws.open.bean.DatosParametro;
import www.bn.sims.ws.open.bean.DatosSms;
import www.bn.sims.ws.open.bean.ReqListMessage;
import www.bn.sims.ws.open.bean.RequestMessage;
import www.bn.sims.ws.open.bean.ResponseMessage;
import www.bn.sims.ws.open.service.ServiceMessageSMS;
import www.bn.sims.ws.open.service.ServiceMessageSMSProxy;

public class SolicitarServiciosNotificacionSms {
	public static void enviarNotificacionSms(NotificacionSms notificacionSms){
		ServiceMessageSMS serviceMessageSMS = new ServiceMessageSMSProxy();
		
		RequestMessage requestMessage = new RequestMessage();
		ReqListMessage rm = new ReqListMessage();
		ReqListMessage[] reqListMessage;
		reqListMessage = new ReqListMessage[1]; 
		
		DatosParametro datosParametro = new DatosParametro();
		DatosSms datosSms = new DatosSms();
		
		datosParametro.setParametro1(notificacionSms.getToperacion());
		datosParametro.setParametro2(notificacionSms.getCanal());
		datosParametro.setParametro3(notificacionSms.getMoneda());
		datosParametro.setParametro4(notificacionSms.getImporte());
		datosParametro.setParametro5(notificacionSms.getFoperacion());
		datosParametro.setParametro6(notificacionSms.getHoperacion());
		datosSms.setCarrier(notificacionSms.getOperadora());
		datosSms.setCelular(notificacionSms.getCelular());
		
		
		rm.setDatosParametro(datosParametro);
		rm.setDatosSms(datosSms);
		
		reqListMessage[0]=rm;
		
		requestMessage.setCodRequerimiento(Constante.COD_REQUERIMIENTO);
		requestMessage.setReqListMessage(reqListMessage);
		
		try {
			
			System.out.println("NOTIFICACION SMS - INICIO");
			serviceMessageSMS.sendMessage(requestMessage);
			System.out.println("NOTIFICACION SMS - FIN");
			
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
//			e.getMessage();
		}
	}
}