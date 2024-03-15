package pe.bn.notificaciones.action;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import pe.bn.notificaciones.form.NotificacionMail;
import pe.bn.notificaciones.form.NotificacionSms;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.com.bn.common.Funciones;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.Notificacion;

public class ServiciosNotificacionPrincipal {
	public static void enviarNotificacion(//ParametrosSIAT registro, 
			Usuario usuario,
			String cliente, String moneda, String importe, BigDecimal tipoCambio, String toperacion, Notificacion notificacion){
		
		String importeN="";
		importeN = importe.replace(",", "");
			
				try {
					
				
					
					String des = FacadeFactory.getTransaccionFacade().getDescripcionCorta(toperacion);
				
					
					double importeDouble	= Double.parseDouble(importe.trim());
					
                    double montoNotiDouble		= Double.parseDouble(notificacion.getMontoNotificacion().trim());
                    
                    int importeInt	= (int)importeDouble;
                    int montoInt	= (int)montoNotiDouble;
                    
                    String celular="";
                    String operadora="";
                    
                    if(usuario.getCelular().length()> 9){
               		 celular=usuario.getCelular().trim().substring(1, 10);
               		 	operadora=Funciones.obtenerOperadorLetraToNumero(usuario.getCelular().trim().substring(0,1));
               		}
               		else{
               			celular=usuario.getCelular().trim();
               			operadora="0";
               		
               		}
					
					if(notificacion.getEstadoNotificacion().trim().equals("1")){
						if(importeInt>=montoInt){
							
							if(notificacion.getMedioNotificacion().trim().equals(Constante.TIPO_NOTIF_CORREO)){
								/*Se comenta codigo por solicitud de no envio de correo por siat*/
//								NotificacionMail notificacionMail = new NotificacionMail(cliente,
//																						usuario.getTarjeta().getNumero(),
//																						moneda,
//																						importe,
//																						des,
//																						usuario.getEmail());
//								SolicitarServiciosNotificacionMail.enviarNotificacionMail(notificacionMail);
								
							} else if(notificacion.getMedioNotificacion().trim().equals(Constante.TIPO_NOTIF_TELEFONO)){
								NotificacionSms notificacionSms = new NotificacionSms(des,
																					moneda,
																					importe,
																					celular,
																					operadora);
								SolicitarServiciosNotificacionSms.enviarNotificacionSms(notificacionSms);
								
							} else if(notificacion.getMedioNotificacion().trim().equals(Constante.TIPO_NOTIF_AMBOS)){
								NotificacionMail notificacionMail = new NotificacionMail(
																						cliente,
																						usuario.getTarjeta().getNumero(),
																						moneda,
																						importe,
																						des,
																						usuario.getEmail());
								NotificacionSms notificacionSms = new NotificacionSms(des,
																						moneda,
																						importe,
																						celular,
																						operadora);
								
								//SolicitarServiciosNotificacionMail.enviarNotificacionMail(notificacionMail);					
								SolicitarServiciosNotificacionSms.enviarNotificacionSms(notificacionSms);
							}
							
						}
					}
					
					
					
//					if(registro.getNotioperacion().equals(Constante.NOTIFICACIONES_SI)){
//						
//						if(registro.getTiponoti()==Constante.TIPO_NOTIFICACION_CORREO){
//							NotificacionMail notificacionMail = new NotificacionMail(cliente,registro.getNtarjeta(),monedaMail,importe,des,registro.getEmail());
//							SolicitarServiciosNotificacionMail.enviarNotificacionMail(notificacionMail);
//							
//						} else if(registro.getTiponoti()==Constante.TIPO_NOTIFICACION_TELEFONO){
//							NotificacionSms notificacionSms = new NotificacionSms(des,monedaSms,importe,registro.getTelefono(),registro.getIdtelefono());
//							SolicitarServiciosNotificacionSms.enviarNotificacionSms(notificacionSms);
//							
//						} else if(registro.getTiponoti()==Constante.TIPO_NOTIFICACION_AMBOS){
//							NotificacionMail notificacionMail = new NotificacionMail(cliente,registro.getNtarjeta(),monedaMail,importe,des,registro.getEmail());
//							NotificacionSms notificacionSms = new NotificacionSms(des,monedaSms,importe,registro.getTelefono(),registro.getIdtelefono());
//							
//							SolicitarServiciosNotificacionMail.enviarNotificacionMail(notificacionMail);					
//							SolicitarServiciosNotificacionSms.enviarNotificacionSms(notificacionSms);
//						}
//					}
					
					
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}				
			//}			
		//}
	}
	
	public static ParametrosSIAT cargarParametrosSiat(String tarjeta){		
		
		ParametrosSIAT registro = new ParametrosSIAT();
		
		try {
			registro = FacadeFactory.getParametrosSiatFacade().getParametrosSiat(tarjeta);
			
			if(Constante.VER_LOG){
				System.out.println("registro.getNotioperacion(): "+registro.getNotioperacion());
				System.out.println("registro.getTiponoti(): "+registro.getTiponoti());
				System.out.println("registro.getImporte(): "+registro.getImporte());
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		return registro;
	}
}
