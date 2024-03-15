package pe.bn.conftarj.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.CardConfSaveResponse;
import pe.bn.cldinamica.action.form.CardRequestSave;
import pe.bn.cldinamica.action.form.CardResponse;
import pe.bn.cldinamica.action.form.CardSaveRequest;
import pe.bn.cldinamica.action.form.CashDispositionSettings;
import pe.bn.cldinamica.action.form.CashDispositionSettingsSave;
import pe.bn.cldinamica.action.form.DataListas;
import pe.bn.cldinamica.action.form.DataListasOrder;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.InfoCard;
import pe.bn.cldinamica.action.form.InfoCardResponse;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.NotificationSettings;
import pe.bn.cldinamica.action.form.NotificationSettingsSave;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.cldinamica.action.form.ShoppingAbroadSettings;
import pe.bn.cldinamica.action.form.ShoppingAbroadSettingsSave;
import pe.bn.cldinamica.action.form.ShoppingInternetSettings;
import pe.bn.cldinamica.action.form.ShoppingInternetSettingsSave;
import pe.bn.cldinamica.action.form.TransferSettings;
import pe.bn.cldinamica.action.form.TransferSettingsSave;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.sati.bean.CardRequest;
import pe.com.bn.common.sati.bean.InfoCardRequest;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AtriTransferenciaImpl;
import pe.cosapi.domain.impl.JournalImpl;
import pe.cosapi.domain.impl.NotificacionImpl;
import pe.cosapi.domain.impl.TarjetasImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;

public class ConfiguracionTarjetasAction extends GrandActionAbstract{

	
	
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	
	private static LoggerSati log3 = LoggerSati.getInstance(ConfiguracionTarjetasAction.class.getName());
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try{
			BNAplicacion aplicacion = BNAplicacion.getInstance();		
			List tarjetas = new ArrayList();
			TarjetasImpl tarjeta = null;
			
	    	request.getSession().removeAttribute("tarjetaSeleccionada");
			request.getSession().removeAttribute("cardDetalleResponse");
			request.getSession().removeAttribute("listaPaisesConfTarjetas");		
			request.getSession().removeAttribute("paises");
		
	    	Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	    	
	    	ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
			request.getSession().setAttribute("mensajeClaveSms", null);
            
    		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
    		{
    			return mapping.findForward("pendienteEnrolamiento");
    		}
    		
			if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
			{
				return mapping.findForward("noPermiteOperaciones");
			}
	    	
	    	//Afiliacion datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
			String tipoDoc="";
			String numDoc="";
						
						
			if(usuario.getCodigoCLDI() != null){
							tipoDoc=usuario.getCodigoCLDI().substring(12,13);
							numDoc=usuario.getCodigoCLDI().substring(13);	
							if(tipoDoc != "1"){
								numDoc=Util.removeZero(numDoc);
							}else{
								numDoc= numDoc.substring(4);
							}
			}
	    	
			String urlInfoCard=  Parametro.getString(ConstanteParametros.BN_PARAM_MS_INFOCARD) + usuario.getCodigoCic() + "/";
			
			
			InfoCardRequest infoCardRequest = new InfoCardRequest();
			infoCardRequest.setTypeDoc(Funciones.lpad(tipoDoc,"0",3));
			infoCardRequest.setNumberDoc(numDoc);
			infoCardRequest.setNumberCard(usuario.getTarjeta().getNumero());
			
			InfoCardResponse infoCardResponse = UtilAction.consultarInfoCard(infoCardRequest, urlInfoCard);
	    	
			if(infoCardResponse==null){
				throw new ArrayRuleException(ConstanteError.GENERICO,"Ocurrio un problema al consultar las informacion de la tarjeta");
			}
			
			usuario.setTarjetas(infoCardResponse.getCards());
	    	
	    	request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);
	    	
	    	if(elementos.getTipoElementoSeguridad().equals("5")){
				System.out.println("Token Fisico");
				usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
				usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
				
			}else if(elementos.getTipoElementoSeguridad().equals("6")){
				System.out.println("Clave Dinamica Digital");
				usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
				usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
			}
	    	
	    	return mapping.findForward("iniciar");
	    	
		}catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
			//return mapping.findForward("confirmarConfiguracionTarjeta");
			e.setForward("iniciarError");
			throw e;			
		}    	
		
	}
	
	public ActionForward verDetalleTarjeta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		CardResponse cardResponse = null;
		List<DataListas> listaPaises = null;
		List<DataListas> listaPaisesSele = null;
		
		String nroTarjeta= (String)request.getParameter("hddNroTarjeta");
		String tipoTarjeta= (String)request.getParameter("hddTipTarjeta");
		String tipoCliente= (String)request.getParameter("hddTipCliente");
		
		String forward = "";
		
		try{
			ResourceBundle rb = ResourceBundle.getBundle("softoken");

			String urlConsultaConfTarjetas = Parametro.getString(ConstanteParametros.BN_PARAM_MS_CONSULTA_CONFIG_TARJ);
			
			
			Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			Afiliacion datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
			
			
			
			CardRequest cardRequest = new CardRequest();
			cardRequest.setTypeCard(tipoTarjeta);
			cardRequest.setNumberCard(nroTarjeta);
			cardRequest.setTypeClient(tipoCliente);
			cardResponse = UtilAction.consultarConfigTarjeta(cardRequest, urlConsultaConfTarjetas);
			
			if(cardResponse==null){
				throw new ArrayRuleException(ConstanteError.GENERICO,"Ocurrio un problema al consultar las informacion de la tarjeta");
			}
			
			
			/**************** BORRAR **************/
			if(cardResponse.getCashDispositionSettings()!=null){
				cardResponse.getCashDispositionSettings().setAvailable("1");
			}
			
			/**************** BORRAR **************/
			cardResponse = setearDetalleTarjeta(cardResponse);			
	        InfoCard tarjetaSeleccionada = obtenerTarjetaSeleccionada(usuario, nroTarjeta);
			request.getSession().setAttribute("tarjetaSeleccionada", tarjetaSeleccionada);
			request.getSession().setAttribute("cardDetalleResponse", cardResponse);
			datos.setCorreoPersonal(datos.getCorreoPersonal().toLowerCase());
			datos.setTelCelular(datos.getTelCelular().substring(1));
			request.getSession().setAttribute("datosPersona", datos);
			
			String paises = obtenerCodePaisesConcat(cardResponse.getShoppingAbroadSettings().getCountry());
			listaPaises = listaPaises(request, cardResponse.getShoppingAbroadSettings());
			
			if(listaPaises==null){
				request.getSession().setAttribute("listaPaisesConfTarjetas", new ArrayList<DataListas>());
				request.getSession().setAttribute("paises", new String(""));
				throw new ArrayRuleException(ConstanteError.GENERICO,"Ocurrio un problema al consultar la lista de paises");
			}
			
			request.getSession().setAttribute("listaPaisesConfTarjetas", listaPaises);
			request.getSession().setAttribute("paises", paises);
			
			if(tipoTarjeta.equals("1")){
				forward = "detalleTarjeta";
			} else if(tipoTarjeta.equals("2")){
				forward = "detalleTarjetaCredito";
			}
			
			
			return mapping.findForward(forward); 
		
		} catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
						
			if(cardResponse != null && listaPaises==null){
				if(tipoTarjeta.equals("1")){
					e.setForward("detalleTarjeta");
				} else if(tipoTarjeta.equals("2")){
					e.setForward("detalleTarjetaCredito");
				}
			} else {
				e.setForward("iniciar");
			}
			
			throw e;		
		}
	}
	
	public CardResponse setearDetalleTarjeta(CardResponse cardResponse){
		
		cardResponse.getShoppingInternetSettings().setLblActivado("DESACTIVADO");
		if(cardResponse.getShoppingInternetSettings().getMeansNotification() != null &&
				
			(cardResponse.getShoppingInternetSettings().getMeansNotification().equals("3") ||
			cardResponse.getShoppingInternetSettings().getMeansNotification().equals("2") || 
			cardResponse.getShoppingInternetSettings().getMeansNotification().equals("1") ) ){
			cardResponse.getShoppingInternetSettings().setLblActivado("ACTIVADO");
		}

		try{
			if(cardResponse.getShoppingAbroadSettings().getDateDeparture()!=null)
				cardResponse.getShoppingAbroadSettings().setDateDeparture(formatearFecha(cardResponse.getShoppingAbroadSettings().getDateDeparture(), "dd/MM/yyyy", "yyyy-MM-dd"));
				if(cardResponse.getShoppingAbroadSettings().getDateReturn()!=null)
				cardResponse.getShoppingAbroadSettings().setDateReturn(formatearFecha(cardResponse.getShoppingAbroadSettings().getDateReturn(), "dd/MM/yyyy", "yyyy-MM-dd"));

		} catch(Exception e){
			e.printStackTrace();
		}
		cardResponse.getShoppingAbroadSettings().setLblActivado("DESACTIVADO");
		if(cardResponse.getShoppingAbroadSettings().getCountry().length>0){
			if(cardResponse.getShoppingAbroadSettings().getDateDeparture()!=null &&
			   cardResponse.getShoppingAbroadSettings().getDateReturn()!=null &&					
			   !cardResponse.getShoppingAbroadSettings().getDateDeparture().trim().equals("") && 
			   !cardResponse.getShoppingAbroadSettings().getDateReturn().trim().equals("")){
					cardResponse.getShoppingAbroadSettings().setLblActivado("ACTIVADO");
			}
		}


		cardResponse.getNotificationSettings().setLblActivado("DESACTIVADO");
//		if(!cardResponse.getNotificationSettings().getAmount().trim().equals("") &&
//		   !cardResponse.getNotificationSettings().getAmount().trim().equals("0.00") &&
//		   !cardResponse.getNotificationSettings().getAmount().trim().equals("00") && 
//		   !cardResponse.getNotificationSettings().getAmount().trim().equals("0")){

			if(cardResponse.getNotificationSettings().getMeansNotification().equals("3") ||
				cardResponse.getNotificationSettings().getMeansNotification().equals("2") || 
				cardResponse.getNotificationSettings().getMeansNotification().equals("1")){
				cardResponse.getNotificationSettings().setLblActivado("ACTIVADO");				
			}
//		}
		return cardResponse;
		
	}
	
	public String obtenerCodePaisesConcat(String[] countries){
		
		String s = "";
        
		if(countries.length>0){
	        for (String codePais : countries) {        	
	        	s += codePais + ",";
	  		}
		}
        
		if(!s.equals("")){
        	s = s.substring(0, s.length()-1);
        }
		
		return new String(s);
	}
	
	public String obtenerDescPaisesConcat(String[] countries, List<DataListas> listaPaisesConfTarjetas){
		
		String s = "";
        
        for (String codePais : countries) {     
        	for(DataListas dataLista: listaPaisesConfTarjetas){
        		if(codePais.equals(dataLista.getCode())){
        			s += dataLista.getDescription() + ", ";
        		}
        	}
        	
  		}
        s = s.substring(0, s.length()-2);
		
		return new String(s);
	}
	
	public InfoCard obtenerTarjetaSeleccionada(Usuario usuario, String nroTarjeta){
		
		InfoCard card = new InfoCard();
		for(Object tarjeta: usuario.getTarjetas()){
			card = (InfoCard)tarjeta;
			
			if(nroTarjeta.equals(card.getNumCard())){
				return card;
			}
		}
		
		return null;
		
	}
	
	public List<DataListas> listaPaises(HttpServletRequest request, ShoppingAbroadSettings shoppingabroadSettings) throws ArrayRuleException{
		
			
		String urlGenerarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_MS_LISTAS);
		
		MensajesTDC resultado = UtilAction.generarListas(urlGenerarClaveSmsAct+"country/findall");
		List<DataListas> listaPaises = null;
		JSONArray jsonArray = (JSONArray)resultado.getDataListas();
		
		DataListas dataLista = null;
		
		
		if(resultado.isExito()==false){
			
			return null;
		} 
		
		
		Iterator<JSONObject> it = jsonArray.iterator();
		JSONObject object = null;
		String codePais = "";
		String description = "";
		
		if(shoppingabroadSettings.getCountry()!=null){
			listaPaises = new ArrayList<DataListas>();
			for(String pais: shoppingabroadSettings.getCountry()){
				while(it.hasNext()){
					
					object = (JSONObject)it.next();
					codePais = (String)object.get("code"); 
					description = (String)object.get("description");
					
					if(!codePais.equals("PE")) {
						dataLista = new DataListas();
						dataLista.setCode(codePais);
						dataLista.setDescription(description);
						listaPaises.add(dataLista);
					}
				}
			}
		}
		
		
		Collections.sort(listaPaises, new DataListasOrder());
		
		return listaPaises;
		
	}
	
	
	public ActionForward confirmarConfiguracionTarjeta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TransferSettings transferSettings = new TransferSettings();
	    CashDispositionSettings cashDispositionSettings = new CashDispositionSettings();    
	    ShoppingInternetSettings shoppingInternetSettings = new ShoppingInternetSettings();
	    ShoppingAbroadSettings shoppingAbroadSettings = new ShoppingAbroadSettings();
	    NotificationSettings notificationSettings = new NotificationSettings();
	    //SendStatusAccountSettings sendStatusAccountSettings = new SendStatusAccountSettings();
	    
	    InfoCard tarjetaSeleccionada = (InfoCard)request.getSession().getAttribute("tarjetaSeleccionada");
		UsuarioImpl usuario = (UsuarioImpl)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosElemSegTDC elementos =  (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		CardResponse cardResponse = (CardResponse)request.getSession().getAttribute("cardDetalleResponse");
		List<DataListas> listaPaisesConfTarjetas = (ArrayList<DataListas>)request.getSession().getAttribute("listaPaisesConfTarjetas");
        
		Afiliacion datos = (AfiliacionImpl)request.getSession().getAttribute("datosPersona");
		
		if(tarjetaSeleccionada.getCardType().equals("1")){
			/** TRANSFERENCIAS**/
			String flgTransferencias = request.getParameter("chkTransferGirRet")!=null&&request.getParameter("chkTransferGirRet").equals("on")?"1":"0";
			transferSettings.setStatus(flgTransferencias);
			transferSettings.setAvailable(cardResponse.getTransferSettings().getAvailable());
		} else if(tarjetaSeleccionada.getCardType().equals("2")){
			/** DISPOSICION DE EFECTIVO **/
			String flgTransferencias = request.getParameter("chkDisposicion")!=null&&request.getParameter("chkDisposicion").equals("on")?"1":"0";
			cashDispositionSettings.setStatus(flgTransferencias);
			cashDispositionSettings.setAvailable(cardResponse.getCashDispositionSettings().getAvailable());
		}
		
		 
		 /** COMPRAS POR INTERNET **/
		 String flgComprasInternet = request.getParameter("chkComprasInternet")!=null && request.getParameter("chkComprasInternet").equals("on")?"1":"0";
		 if(tarjetaSeleccionada.getCardType().equals("1")){
			 if(flgComprasInternet.equals("1")){
				 String flgComprasEmail = ""; //request.getParameter("chkComprasEmail")!=null&&request.getParameter("chkComprasEmail").equals("on")?"1":"0";
				 String flgComprasSms = "";//request.getParameter("chkComprasSms")!=null&&request.getParameter("chkComprasSms").equals("on")?"1":"0";
				 
				 String flgComprasSms2 = request.getParameter("hddChkComprasSms")!=null && request.getParameter("hddChkComprasSms").equals("true")?"1":"0";
				 String flgComprasEmail2 = request.getParameter("hddChkComprasEmail")!=null && request.getParameter("hddChkComprasEmail").equals("true")?"1":"0";
				 
				 if(flgComprasSms2.equals("1") && flgComprasEmail2.equals("0")){
					 flgComprasEmail = "0";
					 flgComprasSms = "1";
				 } else if(flgComprasSms2.equals("0")&& flgComprasEmail2.equals("1")){
					 flgComprasEmail = "1";
					 flgComprasSms = "0";
				 } else if(flgComprasSms2.equals("1")&& flgComprasEmail2.equals("1")){
					 flgComprasEmail = "1";
					 flgComprasSms = "1";
				 }
				 
				 String meansNotiShoppingInternet = "";
				 String statusShoppingInternet = "0";
				 shoppingInternetSettings.setLblActivado("ACTIVADO");
				 if(flgComprasEmail.equals("1") && flgComprasSms.equals("1")){
					 meansNotiShoppingInternet = "3";
					 statusShoppingInternet = "1";
				 } else if(flgComprasEmail.equals("1") && flgComprasSms.equals("0")){
					 meansNotiShoppingInternet = "1";
					 statusShoppingInternet = "1";
				 } else if(flgComprasEmail.equals("0") && flgComprasSms.equals("1")){
					 meansNotiShoppingInternet = "2";
					 statusShoppingInternet = "1";
				 } else {
					 shoppingInternetSettings.setLblActivado("DESACTIVADO");
				 }
				 shoppingInternetSettings.setMeansNotification(meansNotiShoppingInternet);
				 shoppingInternetSettings.setStatus(statusShoppingInternet);
				 shoppingInternetSettings.setAssociatedEmail(datos.getCorreoPersonal());
				 shoppingInternetSettings.setAssociatedPhone(elementos.getNumberMobile()); 
				 shoppingInternetSettings.setCodeOperator(elementos.getIdOperatorMobile());
				 shoppingInternetSettings.setDesOperator(elementos.getDesOperatorMobile());
				 shoppingInternetSettings.setAvailable(cardResponse.getShoppingInternetSettings().getAvailable());
			 } else {
				 shoppingInternetSettings.setStatus(flgComprasInternet);
				 shoppingInternetSettings.setAvailable(cardResponse.getShoppingInternetSettings().getAvailable());
			 }
		 } else if(tarjetaSeleccionada.getCardType().equals("2")){
			 shoppingInternetSettings.setStatus(flgComprasInternet);
			 shoppingInternetSettings.setAvailable(cardResponse.getShoppingInternetSettings().getAvailable());
		 }
		 
		 
		 /** CONSUMO EXTRANJERO **/
		 String flgConsumoExtranjero = request.getParameter("chkConsumoExtranjero")!=null&&request.getParameter("chkConsumoExtranjero").equals("on")?"1":"0";
		 if(flgConsumoExtranjero.equals("1")){
			 String listaPaises = request.getParameter("paisesSel");		 	 
			 String fecIda = request.getParameter("fechaIda");
			 String fecVuelta = request.getParameter("fechaVuelta");
			 
			 if(listaPaises!=null && !listaPaises.equals("") && !fecIda.equals("") && !fecVuelta.equals("")){		
				 
				 String fecIdaString = fecIda.substring(8,10) +"/"+ fecIda.substring(5,7)+"/"+fecIda.substring(0,4);
				 String fecVueltaString = fecVuelta.substring(8,10)+"/"+fecVuelta.substring(5,7)+"/"+fecVuelta.substring(0,4);
				 
				 listaPaises = listaPaises.substring(0, listaPaises.length()-1);	
				 shoppingAbroadSettings.setStatus("1");
				 shoppingAbroadSettings.setDateDeparture(fecIdaString);
				 shoppingAbroadSettings.setDateReturn(fecVueltaString);
				 String[] paises = listaPaises.split(",");
				 shoppingAbroadSettings.setCountry(paises);
				 String paisesStr = obtenerDescPaisesConcat(paises, listaPaisesConfTarjetas);
				 shoppingAbroadSettings.setCountriesStr(paisesStr);
			 } else {
				 shoppingAbroadSettings.setStatus("0");
			 }
			 shoppingAbroadSettings.setAvailable(cardResponse.getShoppingAbroadSettings().getAvailable());
		 } else {
			 shoppingAbroadSettings.setStatus(flgConsumoExtranjero);
			 shoppingAbroadSettings.setAvailable(cardResponse.getShoppingAbroadSettings().getAvailable());
		 }
		 
		 /** NOTIFICACION POR OPERACION **/ 
		 String flgNotificacion = request.getParameter("chkNotificacionOperacion")!=null&&request.getParameter("chkNotificacionOperacion").equals("on")?"1":"0";
		 if(flgNotificacion.equals("1")){
			 String montoNotificacion = request.getParameter("montoNoti");
			 
			 String flgNotiEmail = ""; //request.getParameter("chkComprasEmail")!=null&&request.getParameter("chkComprasEmail").equals("on")?"1":"0";
			 String flgNotiSms = "";//request.getParameter("chkComprasSms")!=null&&request.getParameter("chkComprasSms").equals("on")?"1":"0";
			 String flgNotiSms2 = request.getParameter("hddChkNotiSms")!=null&&request.getParameter("hddChkNotiSms").equals("true")?"1":"0";
			 String flgNotiEmail2 = request.getParameter("hddChkNotiEmail")!=null&&request.getParameter("hddChkNotiEmail").equals("true")?"1":"0";
			 
			 if(flgNotiSms2.equals("1") && flgNotiEmail2.equals("0")){
				 flgNotiEmail = "0";
				 flgNotiSms = "1";
			 } else if(flgNotiSms2.equals("0") && flgNotiEmail2.equals("1")){
				 flgNotiEmail = "1";
				 flgNotiSms = "0";
			 } else if(flgNotiSms2.equals("1") && flgNotiEmail2.equals("1")){
				 flgNotiEmail = "1";
				 flgNotiSms = "1";
			 }
			 
			 String meansNotification = "";
			 String statusNotification = "1";
			 
			 if(!montoNotificacion.equals("NaN")){
			 //if(cardResponse.getNotificationSettings().getLblActivado().equals("ACTIVADO") ){
				 if(flgNotiEmail.equals("1") && flgNotiSms.equals("1")){
					 meansNotification = "3";
					 statusNotification = "1";
				 } else if(flgNotiEmail.equals("1") && flgNotiSms.equals("0")){
					 meansNotification = "1";
					 statusNotification = "1";
				 } else if(flgNotiEmail.equals("0") && flgNotiSms.equals("1")){
					 meansNotification = "2";
					 statusNotification = "1";
				 }
			 } else {
				 notificationSettings.setStatus("0");
			 }
			 
			 notificationSettings.setAmount(montoNotificacion);
			 notificationSettings.setAmountToReceiveNo(montoNotificacion);
			 notificationSettings.setMeansNotification(meansNotification);
			 notificationSettings.setStatus(statusNotification);
			 notificationSettings.setTypeMoney(cardResponse.getNotificationSettings().getTypeMoney());
			 
			 notificationSettings.setAssociatedEmail(datos.getCorreoPersonal());
			 notificationSettings.setAssociatedPhone(datos.getTelCelular()); 
			 notificationSettings.setCodeOperator(elementos.getIdOperatorMobile());
			 notificationSettings.setDesOperator(elementos.getDesOperatorMobile());
			 notificationSettings.setAvailable(cardResponse.getNotificationSettings().getAvailable());
		 
		 } else {
			 notificationSettings.setStatus(flgNotificacion);
			 notificationSettings.setAvailable(cardResponse.getNotificationSettings().getAvailable());
		 }
		 
		 
		 CardSaveRequest cardSaveRequest = new CardSaveRequest();
		 
		 cardSaveRequest.setTypeCard(tarjetaSeleccionada.getCardType());
		 cardSaveRequest.setNumberCard(tarjetaSeleccionada.getNumCard());
		 cardSaveRequest.setObfuscatedNumberCard(tarjetaSeleccionada.getNumCard().substring(0,4)+ "********" + 
				 							     tarjetaSeleccionada.getNumCard().substring(12,tarjetaSeleccionada.getNumCard().length()));
		 cardSaveRequest.setTypeToken(elementos.getTipoElementoSeguridad());
		 
		 if(tarjetaSeleccionada.getCardType().equals("1")){
			 cardSaveRequest.setTransferSettings(transferSettings);
		 } else if(tarjetaSeleccionada.getCardType().equals("2")){
			 cardSaveRequest.setCashDispositionSettings(cashDispositionSettings);	 
		 }
		 cardSaveRequest.setShoppingInternetSettings(shoppingInternetSettings);
		 cardSaveRequest.setShoppingAbroadSettings(shoppingAbroadSettings);
		 cardSaveRequest.setNotificationSettings(notificationSettings);
		 
		 request.getSession().setAttribute("cardSaveRequest", cardSaveRequest);
		 
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		 
			ElemenSeguridad resultCoord = null;
			
			if(param!= null){
				resultCoord = new ElemenSeguridad();
				try{
					request.getSession().setAttribute("resultCoord",null );
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {						
							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {						
						generarClaveSms(mapping, form, request, response);		
					}
				}catch(ArrayRuleException ar){
					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
					ar.printStackTrace();
					ar.setForward("verMBTransferencia");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord);
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
		 
		
		 return mapping.findForward("confirmarConfiguracionTarjeta");
	}

	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward guardarConfiguracionTarjetas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			
			TransferSettingsSave vTransferSettingsSave = new TransferSettingsSave();
			CashDispositionSettingsSave vCashDispositionSettingsSave = new CashDispositionSettingsSave();
			ShoppingInternetSettingsSave vShoppingInternetSettingsSave = new ShoppingInternetSettingsSave();
			ShoppingAbroadSettingsSave vShoppingAbroadSettingsSave = new ShoppingAbroadSettingsSave();
			NotificationSettingsSave vNotificationSettingsSave = new NotificationSettingsSave();
			
			ResourceBundle rb = ResourceBundle.getBundle("softoken");
			
			String urlRegistrarConfTarjetas = Parametro.getString(ConstanteParametros.BN_PARAM_MS_REGISTRAR_CONFIG_TARJ);
			
			TokenSmsRequest tokenSms = new TokenSmsRequest();
			MensajesTDC resultado = new MensajesTDC();			
			
			CardSaveRequest cardSaveRequest = (CardSaveRequest)request.getSession().getAttribute("cardSaveRequest");
			Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			//Afiliacion datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
			String tipoDoc="";
			String numDoc="";
			if(usuario.getCodigoCLDI() != null){
							tipoDoc=usuario.getCodigoCLDI().substring(12,13);
							numDoc=usuario.getCodigoCLDI().substring(13);	
							if(tipoDoc != "1"){
								numDoc=Util.removeZero(numDoc);
							}else{
								numDoc= numDoc.substring(4);
							}
			}
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			
			
			InfoCard tarjetaSeleccionada = (InfoCard)request.getSession().getAttribute("tarjetaSeleccionada");
			
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
		
			ParametrosElemSegTDC elementos = null;
			try {

				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");

			} catch (ArrayRuleException ar) {
				log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
				ar.printStackTrace();
				ar.setForward("confirmarConfgTarjeta");
				throw ar;
			}
			
			
			if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
				resultado = SolicitarServiciosTDC.validarTDC(param, coord,pinblock);
			} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
				resultado = SolicitarServiciosTDC.validarTokens(param,coord, pinblock);
			} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
				
				tokenSms.setCodCliente(usuario.getCodigoCic());
				tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
				tokenSms.setNroDocumento(numDoc);
				
				tokenSms.setCodeSMS(pinblock);			
				tokenSms.setTypeTrans(Constante.TIP_OPER_CONFIGURACION_TARJETAS);
				tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);					
				tokenSms.setTypeCurrency("PEN");
				
				tokenSms.setCodCliDestinatario(tarjetaSeleccionada.getNumCard());
				tokenSms.setNomCliDestinatario(tarjetaSeleccionada.getAssociatedEntity());
				
				resultado = UtilAction.validarClaveSms(tokenSms);
			}
		
			if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) 
					|| !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {
	
				String codErrEnt = "";
				String mensaje = "";
	
				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK)) {
					codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4 - resultado.getCodRptaPrincipal().toString().length());
					
					if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						codErrEnt = resultado.getCodResult();
						boolean existe = UtilAction.validarExisteCodigoErrorMs(codErrEnt);
						
						if(existe==true){
							mensaje = aplicacion.getMsjesHost("CS", codErrEnt).elementAt(2).toString();
							throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + codErrEnt + ")");
						}else {							
							mensaje = resultado.getMsg();
						}
					} else {
						mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
					}
	
				} else {
					if (!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {
						codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0',4 - resultado.getCodRptaPrincipalOper().toString().length());
						mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
					}
				}
	
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CD-" + codErrEnt + ")");
	
			}
		
			
			 CardRequestSave cardRequest = new CardRequestSave();
			 
			 cardRequest.setTypeCard(tarjetaSeleccionada.getCardType());
			 cardRequest.setNumberCard(tarjetaSeleccionada.getNumCard());
			 
			 
			 cardRequest.setTypeDocument(tipoDoc);
			 cardRequest.setNumberDocument(numDoc);
			 cardRequest.setTypeClient(tarjetaSeleccionada.getClientTypeDesc());
			 
			 cardRequest.setTypeToken(elementos.getTipoElementoSeguridad());
			 
			 cardRequest.setEmail(usuario.getEmail());
//			 cardRequest.setEmail(cardSaveRequest.getShoppingInternetSettings().getAssociatedEmail());
			 cardRequest.setOperatorType(elementos.getIdOperatorMobile());
			 cardRequest.setCellNumber(elementos.getNumberMobile());
			
			
			 /***********TransferSettings*************/
			 if(tarjetaSeleccionada.getCardType().equals("1")){
	 
				 if(cardSaveRequest.getTransferSettings().getStatus()==null){
					 vTransferSettingsSave.setStatus("0");
				 }else{
					 vTransferSettingsSave.setStatus(cardSaveRequest.getTransferSettings().getStatus());
				 }
				 
				 
				 if(cardSaveRequest.getTransferSettings().getTypeMoney()==null){
					 vTransferSettingsSave.setTypeMoney("S");
				 }else{
					 vTransferSettingsSave.setTypeMoney(cardSaveRequest.getTransferSettings().getTypeMoney());
				 }
				 
				 if(cardSaveRequest.getTransferSettings().getAmount()==null){
					 vTransferSettingsSave.setAmount("0");
				 }else{
					 vTransferSettingsSave.setAmount(cardSaveRequest.getTransferSettings().getAmount());
				 }
				 
				 if(cardSaveRequest.getTransferSettings().getTypeAmount()==null){
					 vTransferSettingsSave.setTypeAmount("0");
				 }else{
					 vTransferSettingsSave.setTypeAmount(cardSaveRequest.getTransferSettings().getTypeAmount());
				 }
				 				 
				 cardRequest.setTransferSettings(vTransferSettingsSave);
				 				 
				 
			 } else if(tarjetaSeleccionada.getCardType().equals("2")){
				 
				 /***********CashDispositionSettings*************/
				 
				 
				 if(cardSaveRequest.getCashDispositionSettings().getStatus()==null){
					 vCashDispositionSettingsSave.setStatus("0");
				 }else{
					 vCashDispositionSettingsSave.setStatus(cardSaveRequest.getCashDispositionSettings().getStatus());
				 }
				 
				 vCashDispositionSettingsSave.setTypeMoney("S");
				 vCashDispositionSettingsSave.setAmount("0");
				 vCashDispositionSettingsSave.setTypeAmount("0");
				 cardRequest.setCashDispositionSettings(vCashDispositionSettingsSave);
				 
			 }
			 
			 
			 /***********ShoppingInternetSettings*************/
 
			 
			 if(cardSaveRequest.getShoppingInternetSettings().getStatus()==null){
				 vShoppingInternetSettingsSave.setStatus("0");
			 }else{
				 vShoppingInternetSettingsSave.setStatus(cardSaveRequest.getShoppingInternetSettings().getStatus());
			 }
			 
			 if(cardSaveRequest.getShoppingInternetSettings().getTypeMoney()==null){
				 vShoppingInternetSettingsSave.setTypeMoney("S");
				 
			 }else{
				 vShoppingInternetSettingsSave.setTypeMoney(cardSaveRequest.getShoppingInternetSettings().getTypeMoney());	 
			 }
			 
			 if(cardSaveRequest.getShoppingInternetSettings().getAmount()==null){
				 vShoppingInternetSettingsSave.setAmount("0");
			 }else{
				 vShoppingInternetSettingsSave.setAmount(cardSaveRequest.getShoppingInternetSettings().getAmount());
			 }
			 
			 if(cardSaveRequest.getShoppingInternetSettings().getTypeAmount()==null){
				 vShoppingInternetSettingsSave.setTypeAmount("0");
			 }else{
				 vShoppingInternetSettingsSave.setTypeAmount(cardSaveRequest.getShoppingInternetSettings().getTypeAmount());
			 }
			 
			 if(cardSaveRequest.getShoppingInternetSettings().getMeansNotification()==null){
				 vShoppingInternetSettingsSave.setMeansNotification("0");
			 }else{
				 vShoppingInternetSettingsSave.setMeansNotification(cardSaveRequest.getShoppingInternetSettings().getMeansNotification());
			 }
			 
			 
			 cardRequest.setShoppingInternetSettings(vShoppingInternetSettingsSave);
			 
			 
			 
			 /***********ShoppingAbroadSettings*************/
			 //cardSaveRequest.setShoppingAbroadSettings(shoppingAbroadSettings);

			 
			 if(cardSaveRequest.getShoppingAbroadSettings().getStatus()==null){
				 vShoppingAbroadSettingsSave.setStatus("0");
			 }else{
				 vShoppingAbroadSettingsSave.setStatus(cardSaveRequest.getShoppingAbroadSettings().getStatus());
			 }
			 			 
			 if(cardSaveRequest.getShoppingAbroadSettings().getTypeMoney()==null){
				 vShoppingAbroadSettingsSave.setTypeMoney("S");
			 }else{
				 vShoppingAbroadSettingsSave.setTypeMoney(cardSaveRequest.getShoppingAbroadSettings().getTypeMoney());
			 }
			 
			 if(cardSaveRequest.getShoppingAbroadSettings().getAmount()==null){
				 vShoppingAbroadSettingsSave.setAmount("0");
			 }else{
				 vShoppingAbroadSettingsSave.setAmount(cardSaveRequest.getShoppingAbroadSettings().getAmount());
			 }
			 
			 if(cardSaveRequest.getShoppingAbroadSettings().getTypeAmount()==null){
				 vShoppingAbroadSettingsSave.setTypeAmount("0");	 
			 }else{
				 vShoppingAbroadSettingsSave.setTypeAmount(cardSaveRequest.getShoppingAbroadSettings().getTypeAmount());
			 }
			 
			vShoppingAbroadSettingsSave.setDateDeparture(cardSaveRequest.getShoppingAbroadSettings().getDateDeparture());
			
			vShoppingAbroadSettingsSave.setDateReturn(cardSaveRequest.getShoppingAbroadSettings().getDateReturn());
			 
			 
			 if(cardSaveRequest.getShoppingAbroadSettings().getCountry()==null){
				 String b[] = new String[0];				 
				 vShoppingAbroadSettingsSave.setCountry(b);	 
			 }else{
				 vShoppingAbroadSettingsSave.setCountry(cardSaveRequest.getShoppingAbroadSettings().getCountry());
			 }
			 
						 
			 cardRequest.setShoppingAbroadSettings(vShoppingAbroadSettingsSave);
			 
			 /***********NotificationSettings*************/
			 //cardSaveRequest.setNotificationSettings(notificationSettings);

			 
			 
			 if(cardSaveRequest.getNotificationSettings().getStatus()==null){
				 vNotificationSettingsSave.setStatus("0");
			 }else{
				 vNotificationSettingsSave.setStatus(cardSaveRequest.getNotificationSettings().getStatus());
			 }
			 
			 if(cardSaveRequest.getNotificationSettings().getTypeMoney()==null){
				 vNotificationSettingsSave.setTypeMoney("S");	 
			 }else{
				 vNotificationSettingsSave.setTypeMoney(cardSaveRequest.getNotificationSettings().getTypeMoney());
			 }
			 		 
			 if(cardSaveRequest.getNotificationSettings().getAmount()==null){
				 vNotificationSettingsSave.setAmount("0");
			 }else{
				 vNotificationSettingsSave.setAmount(cardSaveRequest.getNotificationSettings().getAmount());
			 }
			 
			 if(cardSaveRequest.getNotificationSettings().getTypeAmount()==null){
				 vNotificationSettingsSave.setTypeAmount("0");
			 }else{
				 vNotificationSettingsSave.setTypeAmount(cardSaveRequest.getNotificationSettings().getTypeAmount());
			 }
			 
			 if(cardSaveRequest.getNotificationSettings().getMeansNotification()==null){
				 vNotificationSettingsSave.setMeansNotification("0");	 
			 }else{
				 vNotificationSettingsSave.setMeansNotification(cardSaveRequest.getNotificationSettings().getMeansNotification());
			 }
			 
			 cardRequest.setNotificationSettings(vNotificationSettingsSave);
			 
			
			 /******************guardamos la configuracion********************/
			CardConfSaveResponse cardConfSaveResponse = UtilAction.guardarConfigTarjeta(cardRequest, urlRegistrarConfTarjetas);
			
			//log3.debug("cardRequest", cardRequest.toString(), "1");
			//System.out.println(cardRequest.toString());
			
			
			if(cardConfSaveResponse==null){
				throw new ArrayRuleException(ConstanteError.GENERICO,"Ocurrio un problema al realizar la operacion");
			} else {
				
//				request.getSession().setAttribute(ConstanteSesion.CONFIGURACION_TARJETA, cardRequest);
				request.getSession().setAttribute(ConstanteSesion.CONFIGURACION_TARJETA, cardSaveRequest);
								
				request.getSession().setAttribute("dateOperation", cardConfSaveResponse.getDateOperation());
				
				NotificacionImpl noti = new NotificacionImpl();
				AtriTransferenciaImpl operaciones = new AtriTransferenciaImpl();
				
				if(cardSaveRequest.getTypeCard().equals("1")){
					if(vTransferSettingsSave.getStatus().equals("1")){
						operaciones.setEstadoOperaciones("S");
					}else{
						operaciones.setEstadoOperaciones("N");
					}
				}
				
				if(cardSaveRequest.getTypeCard().equals("2")){
					if(vCashDispositionSettingsSave.getStatus().equals("1")){
						operaciones.setEstadoOperaciones("S");
					}else{
						operaciones.setEstadoOperaciones("N");
					}
				}
				
				
				if(vNotificationSettingsSave.getStatus().equals("1")){
					noti.setEstadoNotificacion("S");
				}else{
					noti.setEstadoNotificacion("N");
				}
				
				if(vNotificationSettingsSave.getTypeMoney().equals("S")){
					noti.setTipoMonedaNotificacion("S");
				}else{
					noti.setTipoMonedaNotificacion("N");
				}
				
				if(vNotificationSettingsSave.getMeansNotification().equals("1")){
					noti.setMedioNotificacion("1");
				}else if (vNotificationSettingsSave.getMeansNotification().equals("2")){
					noti.setMedioNotificacion("2");
				}else if (vNotificationSettingsSave.getMeansNotification().equals("3")){
					noti.setMedioNotificacion("3");
				}

				if(vNotificationSettingsSave.getAmount().equals("0")){
					noti.setMontoNotificacion("0");
				}else{
					noti.setMontoNotificacion(cardSaveRequest.getNotificationSettings().getAmount());
				}	
				
				usuario.setEstadoOperaciones(operaciones);
				usuario.setNotificacion(noti);
				
				
				ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_CONFG_TARJETAS, ConstanteSesion.CONFIGURACION_TARJETA, request);
				
				//guardar LOG
				String codTransaction="";
				if(cardRequest.getTypeCard().equals("1")){
					codTransaction = "CT02";
				}
				if(cardRequest.getTypeCard().equals("2")){
					codTransaction = "CT04";
				}
				
				Transaction t = new Transaction(codTransaction);				
				CardSaveRequest card = (CardSaveRequest) request.getSession().getAttribute(ConstanteSesion.CONFIGURACION_TARJETA);
				card.setFecha(cardConfSaveResponse.getDateOperation().substring(0,10));
				card.setHora(cardConfSaveResponse.getDateOperation().substring(11));
				
				//guardado en el log 
				JournalImpl journal = new JournalImpl();
				Long nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
				String numLog = String.valueOf(nroLog);
				journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
				card.setNroLog(Long.parseLong(journal.getNumlog()));
				//Seteamos la fecha
				SimpleDateFormat formatter;
				Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
				formatter = new SimpleDateFormat("dd/MM/yyyy");			
				Date data = new Date(fechaActual.getTime());
				String hora = "";				
				String fecha = formatter.format(data);
				
				//Seteamos la hora
				formatter = new SimpleDateFormat("HH:mm:ss");
				data = new Date(fechaActual.getTime());
				hora = formatter.format(data);

				//journal.setNumprdsrc(usuario.getTarjeta().getNumero());
				journal.setDatpro(fechaActual);
				journal.setHorpro(hora);
				journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
				
				
				if(cardRequest.getTypeCard().equals("1")){
					journal.setCodtra("CT02");
				}
				if(cardRequest.getTypeCard().equals("2")){
					journal.setCodtra("CT04");
				}
				
				DAOFactory.getSaraWebLogImpl().insertarJournal(journal);
				
				Vector ctas=new Vector();
				Vector v=new Vector();
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement("");
				v.addElement(request.getRemoteAddr());
				ctas.addElement(v);
				
				t.setCuentas(ctas);
				t.setTransactionHost("1163");
				
				
				
				GeneralTest gt= new GeneralTest();
				gt.generarLog2(t, usuario, Constante.REFRENDO_CONF_TARJETA, card, request);
				
				return mapping.findForward("mostrarConstanciaConfigTarjeta");
				
			}
		
		} catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
			e.setForward("confirmarConfiguracionTarjeta");
			throw e;
		}
		
		
		
	}
	
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		//Afiliacion datosUsuario = new AfiliacionImpl();
		InfoCard tarjetaSeleccionada = (InfoCard)request.getSession().getAttribute("tarjetaSeleccionada");

		try {

			//datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
			String tipoDoc="";
			String numDoc="";
						
			if(usuario.getCodigoCLDI() != null){
							tipoDoc=usuario.getCodigoCLDI().substring(12,13);
							numDoc=usuario.getCodigoCLDI().substring(13);	
							if(tipoDoc != "1"){
								numDoc=Util.removeZero(numDoc);
							}else{
								numDoc= numDoc.substring(4);
							}
			}

			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
			ParametrosElemSegTDC elementos = null;

			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento", elementos);

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;
			
			
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans(Constante.TIP_OPER_CONFIGURACION_TARJETAS);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);					
					tokenSms.setTypeCurrency("PEN");
					
					tokenSms.setCodCliDestinatario(tarjetaSeleccionada.getNumCard());
					tokenSms.setNomCliDestinatario(tarjetaSeleccionada.getAssociatedEntity());

					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					
				}

				request.getSession().setAttribute("resultCoord", resultCoord);

			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO, Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			response.setStatus(404);
			request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarConfiguracionTarjeta");

	}
	
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}
	
	public String NomTipoDocBenef(String tipodoc, String campoasoc) {
		int td = Integer.parseInt(tipodoc);
		List lista = null;
		if (tipodoc != null && campoasoc != null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(
					campoasoc, tipodoc);
		if (lista != null) {
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();

				if (element.getCodigo().trim().equals(tipodoc + "")) {
					return element.getDescripcion();
				}
			}
		}

		return "";
	}
	
	public String formatearFecha(String fechaAconvertir, String formateEntrada, String formatoSalida) throws Exception{
//		Formato inicial.  
        SimpleDateFormat formato = new SimpleDateFormat(formateEntrada);
        String fechaInicio  = fechaAconvertir;
        Date d = formato.parse(fechaInicio);

        //Aplica formato requerido.
        formato.applyPattern(formatoSalida);
        String nuevoFechaFormateada = formato.format(d);
        
        return nuevoFechaFormateada;
	}
	
}
