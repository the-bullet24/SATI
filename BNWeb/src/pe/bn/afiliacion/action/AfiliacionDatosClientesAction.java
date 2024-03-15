package pe.bn.afiliacion.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.transferencia.domain.Transferencia;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.LoadDataProperties;
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
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AfiliacionesImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.UsuarioImpl;




public class AfiliacionDatosClientesAction extends GrandActionAbstract {
       BNAplicacion aplicacion = BNAplicacion.getInstance();

       private static LoggerSati log3 = LoggerSati
                    .getInstance(AfiliacionDatosClientesAction.class.getName());
       
       
       public ActionForward inicioValida(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws Exception {
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);    

        
        ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        String mensajeCabeceraInicio = ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC03")).elementAt(2).toString();
        
        if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
        	mensajeCabeceraInicio = mensajeCabeceraInicio.replace("TOKEN", "CLAVE DINÁMICA DIGITAL");
        } 

        request.getSession().setAttribute("mensajeCabeceraInicio",mensajeCabeceraInicio);
        
//        if (!getVerificarHorario(Constante.DAT_CLIENTES_ACTUALIZACION)) {
//
//               request.getSession().setAttribute("mensaje1Hor",((Vector) aplicacion.getMensajePorCodigo("DC01", "00001")) .elementAt(2).toString());
//               request.getSession().setAttribute( "mensaje2Hor",((Vector) aplicacion.getMensajePorCodigo("DC01", "00002")).elementAt(2).toString());
//               request.getSession().setAttribute( "mensajeHorC", ((Vector) aplicacion.getMensajePorCodigo("MH01", "DC01")) .elementAt(2).toString());
//               return mapping.findForward("transaccion.noDisponible");
//        }
        
        if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
        
        if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
        
        //REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		
        ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		
		try{
		
			
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicioValida");
			throw ar;
		}
		
		
		//REALIZAR CONSULTA DE COORDENADAS
		

		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					generarClaveSmsActualizacion(mapping, form, request, response);		
				}
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioValida");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

      
        return mapping.findForward("inicioValida");
  }
       
       public ActionForward consultaDatos(ActionMapping mapping, ActionForm form,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
    	   Boolean swActDatos = false;	 
    	   
    	   TokenSmsRequest tokenSms = new TokenSmsRequest();
    	     
             BNAplicacion aplicacion = BNAplicacion.getInstance();
             Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
             
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             
             Tarjeta tarjeta = usuario.getTarjeta(); 
     		 

             loadObjectCoreActual(request);
             

             request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
             request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
            
        
             try {
            	 
            	ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
         		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
         		
         		
         		
         	
                 
         		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
         			
         		
         		ParametrosElemSegTDC elementos = null;
         		
         		Afiliacion afiliacion = new AfiliacionImpl();
         		
//         		Afiliacion datosUsuario  = new AfiliacionImpl();
//        		datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
        		afiliacion.setCodCliente(usuario.getCodigoCic());					
        		//afiliacion.setCodCliente(codCliente);
        		afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
        		afiliacion.setNroDocumento(numDoc);
         		
            			
    			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
    			
    			try{
    				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
    				
    				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
    				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
    					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
    				}
    			
    				
    			}catch(ArrayRuleException ar){
    				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
    				ar.printStackTrace();
    				ar.setForward("inicioValida");
    				throw ar;
    			}
    			
    			
    			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");	

    			if(coord == null){
    				
    				ElemenSeguridad resultCoord = null;
        			if(param!= null){
        				resultCoord = new ElemenSeguridad();
        				try{
        					request.getSession().setAttribute("resultCoord",null );
        					
        					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
        						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
        					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
        							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
        					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
        						generarClaveSmsActualizacion(mapping, form, request, response);		
        					}
        					
        				}catch(ArrayRuleException ar){
        					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
        					ar.printStackTrace();
        					ar.setForward("inicioValida");
        					throw ar;
        				}
        				request.getSession().setAttribute("resultCoord",resultCoord );
        			}
        			else{
        				
        				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
        			}
        			
        		coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
    				
    			}
    			
    			// Código Nuevo para TDC
    			
    			MensajesTDC resultado = new MensajesTDC();
    			Transferencia transf = null;
    			
    			if(param!= null && coord!=null ){
    				
    				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						tokenSms.setCodCliente(afiliacion.getCodCliente());
						tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
						tokenSms.setNroDocumento(afiliacion.getNroDocumento());
						
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_ACTUALIZACION_DATOS);
						tokenSms.setTypeOp("ADMIN");						
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setTypeCurrency("PEN");
						
						resultado = UtilAction.validarClaveSms(tokenSms);		
					}
					
  
    				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {

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
    			}
    				//Validacion de datos
					//MGL
				
	    			swActDatos=true;
	    							 
					usuario.setFlagActualizaDato(swActDatos);
	
					request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);	
    			
                    Afiliacion datos = new AfiliacionImpl();
                    datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);

                    // Enviando datos al formulario
                    frm.setCmbDepartamento(datos.getCodDepartamento());
                    frm.setCmbProvincia(datos.getCodProvincia());
                    frm.setCmbDistrito(datos.getCodDistrito());

                    frm.setCmbTipoVia(datos.getTipoVia());
               
                    frm.setTxtNombreVia(datos.getNomVia1().toLowerCase() + ""
                                  + datos.getNomVia2().toLowerCase());
                    frm.setTxtNumeroVia(datos.getNumero());
                    frm.setTxtBloque(datos.getBloque());
                    frm.setTxtLote(datos.getLote().toLowerCase());
                    frm.setTxtInterior(datos.getInterior());
                    frm.setTxtManzana(datos.getManzana());
                    frm.setTxtPiso(datos.getPiso());
                    frm.setTxtReferencia(datos.getReferencia().toLowerCase() + ""
                                  + datos.getReferencia1().toLowerCase());
                    frm.setTxtTelFijo(datos.getTelFijo());
                    frm.setTxtTelFijoLab(datos.getTelFijoLab());
                    frm.setTxtAnexo(datos.getAnexo());
                    frm.setCmbConsentimiento(datos.getFlagConsentimiento());

                    if (datos.getTelCelular() != null) {
                           int datosCelular = datos.getTelCelular().trim().length();
                          
                           if (datosCelular > Constante.DAT_CLIENTES_LONG_CELULAR) {
                                  frm.setCmbOperador(datos.getTelCelular().substring(0, 1));
                                  frm.setTxtCelular(datos.getTelCelular().substring(1));

                           } else {
                                  frm.setCmbOperador(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO);
                                  frm.setTxtCelular(datos.getTelCelular());
                           }

                    }

                    frm.setTxtCorreo(datos.getCorreoPersonal().toLowerCase());
                    frm.setTxtAdicional(datos.getCorreoAdicional().toLowerCase());
                    frm.setCmbZonaTelFijo(datos.getDiscadoTelFijo());
                    frm.setCmbZonaTelFijoLab(datos.getDiscadoTelFijoLab());
                    
                    if(!(datos.getCodOcupacion() == null || datos.getCodOcupacion().isEmpty())){
                    	frm.setCmbOcupacion(datos.getCodOcupacion().substring(datos.getCodOcupacion().length()-5,datos.getCodOcupacion().length()));
                    }else{
                    	frm.setCmbOcupacion("");
                    }

                    //request.getSession().setAttribute("flagMigrar",datos.getFlagDejaMigrar());
                    request.getSession().setAttribute("codCliente",datos.getCodCliente());
                    
                    request.getSession().setAttribute("flagMigrar",datos.getFlagDejaMigrar());
                    request.getSession().setAttribute("diasParaMigrar",datos.getDiasParaMigrar());

                    request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade().getComboProvincia( frm.getCmbDepartamento()));
                    request.setAttribute( "lstDistrito",FacadeFactory.getGeneralFacade().getComboDistrito(frm.getCmbDepartamento() + frm.getCmbProvincia()));
             } catch (ArrayRuleException e) {
                    e.printStackTrace();
                    log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

                    e.setForward("inicioValida");
                    throw e;
             }

             return mapping.findForward("consultaDatos");
       }

       public ActionForward listarProvincia(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             loadObjectCoreActual(request);

             request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade()
                           .getComboProvincia(frm.getCmbDepartamento()));
             
             frm.setCmbProvincia("");
             frm.setCmbDistrito("");
             return mapping.findForward("consultaDatos");
       }

       public ActionForward listarDistrito(ActionMapping mapping, ActionForm form,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             loadObjectCoreActual(request);

             request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade()
                           .getComboProvincia(frm.getCmbDepartamento()));
             request.setAttribute( "lstDistrito",  FacadeFactory.getGeneralFacade().getComboDistrito(  frm.getCmbDepartamento() + frm.getCmbProvincia()));
             frm.setCmbDistrito("");
             return mapping.findForward("consultaDatos");
       }

       public ActionForward actualizarDatos(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
    	   
    	   	 //System.out.println("entro a actualizarDatos");
    		 ActionForward forward = new ActionForward();	
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             loadObjectCoreActual(request);
             Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
             
             if(usuario.getFlagActualizaDato().equals(true)){
//            	MGL	 
            	 Tarjeta tarjeta = usuario.getTarjeta();
                 Afiliacion afiliacion = setearAfiliacion(frm, tarjeta, usuario, request);

                 List x = usuario.getCuentas();
                 CuentaImpl cuenta1 = (CuentaImpl) x.get(0);

                 afiliacion.setCuenta(cuenta1);

                 List listOperador = new ArrayList();
                 Afiliacion datos = new AfiliacionImpl();

                 try {

                        listOperador = FacadeFactory
                                      .getGeneralFacade()
                                      .getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR);

                        for (int i = 0; i < listOperador.size(); i++) {
                               DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
                               dHelp_ = (DetalleAyudaDatosImpl) listOperador.get(i);
                               if (afiliacion.getOperador().equals(dHelp_.getCodigoDetalleAyuda())) {
                                      if (afiliacion.getOperador().equals(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO)) {
                                            afiliacion.setMostrarOperador(Constante.DAT_CLIENTES_SIN_DESCRIPCION);
                                            break;
                                      } else {
                                            afiliacion.setMostrarOperador(dHelp_.getDescripcionDetalle().toUpperCase());
                                      }
                               }
                        }

                        datos = FacadeFactory.getAfiliacionFacade().getActualizaDatosCliente(
                        		Constante.DAT_CLIENTES_ACTUALIZACION,
                        		request.getRemoteAddr(), 
                        		afiliacion, 
                        		usuario,
                        		request);

                        datos.setMostrarOperador(afiliacion.getMostrarOperador());
                        datos.setDesOcupacion(afiliacion.getDesOcupacion());
                       
                        usuario.setCelular(afiliacion.getOperador().concat(afiliacion.getTelCelular()));
                        usuario.setOpeCelular(afiliacion.getOperador());
                      	usuario.setCelularFormat(afiliacion.getTelCelular());
                      	request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);

                 } catch (ArrayRuleException e) {
                        e.printStackTrace();
                        log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
                        request.setAttribute("lstProvincia",FacadeFactory.getGeneralFacade().getComboProvincia(frm.getCmbDepartamento()));
                        request.setAttribute("lstDistrito",FacadeFactory.getGeneralFacade().getComboDistrito(frm.getCmbDepartamento() + frm.getCmbProvincia()));
                        e.setForward("consultaDatos");
                        throw e;
                 }

                 // Inserta fecha y hora
                 datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
                 datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
                 datos.setCuenta(afiliacion.getCuenta());
                 if (afiliacion.getCorreoPersonal() != null && afiliacion.getCorreoPersonal1() != null) {
                        datos.setCorreo(afiliacion.getCorreoPersonal() + Constante.SEP_ALPHANUMERIC + afiliacion.getCorreoPersonal1());
                 } else {
                        datos.setCorreo(Constante.DAT_CLIENTES_SIN_DESCRIPCION);
                 }
                 if (afiliacion.getCorreoAdicional() != null && afiliacion.getCorreoAdicional1() != null) {
                        datos.setCorreoAdicional(afiliacion.getCorreoAdicional() + Constante.SEP_ALPHANUMERIC + afiliacion.getCorreoAdicional1());
                 } else {
                        datos.setCorreoAdicional(Constante.DAT_CLIENTES_SIN_DESCRIPCION);
                 }

                 request.getSession().setAttribute(ConstanteSesion.AFILIAR, datos);

                 if(afiliacion.getFlagConsentimiento().equals("1")){
                	ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ACTUALIZACION_DATOS_ACEPTA, ConstanteSesion.AFILIAR, request);
         			forward = mapping.findForward("finActualizacionAcepta");
         			
         		}else{
         			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ACTUALIZACION_DATOS_NO_ACEPTA, ConstanteSesion.AFILIAR, request);
         			forward = mapping.findForward("finActualizacionNoAcepta");
         		}
         		
                 usuario.setFlagActualizaDato(false);
                 usuario.setFlagActualizaDatoHost("0");
                 
                 request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);	
         		
             }else{
            	 // e.printStackTrace();
                  //log3.error(null, Constante.ERR_LOGICA_NEGOCIO, "");
                  log3.error(null,Constante.ERR_LOGICA_NEGOCIO,"Los datos ingresados no corresponden");
             }
             return forward;
       }

       public ActionForward inicioValidaEstadoCuenta(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws Exception {
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);    
       // 
       
        if (!getVerificarHorario(Constante.DAT_CLIENTES_ACTUALIZACION)) {

               request.getSession().setAttribute("mensaje1Hor",((Vector) aplicacion.getMensajePorCodigo("DC01", "00001")) .elementAt(2).toString());
               request.getSession().setAttribute( "mensaje2Hor",((Vector) aplicacion.getMensajePorCodigo("DC01", "00002")).elementAt(2).toString());
               request.getSession().setAttribute( "mensajeHorC", ((Vector) aplicacion.getMensajePorCodigo("MH01", "DC01")) .elementAt(2).toString());
               return mapping.findForward("transaccion.noDisponible");
        }
        
        ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
        if(elementos.getTipoElementoSeguridad().equals("5")){
 			System.out.println("Token Fisico");
 			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
 			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
 			
 		}else if(elementos.getTipoElementoSeguridad().equals("6")){
 			System.out.println("Clave Dinamica Digital");
 			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
 			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
 		}
        
        String mensajeCabeceraInicioEC = ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC04")).elementAt(2).toString();
        
        if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
        	mensajeCabeceraInicioEC = mensajeCabeceraInicioEC.replace("TOKEN", "CLAVE DINÁMICA DIGITAL");
        } 

        request.getSession().setAttribute("mensajeCabeceraInicioEC",mensajeCabeceraInicioEC);
   
        //REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		
        ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
		String flagActdatos = usuario.getFlagActualizaDatoHost();
		if(flagActdatos.equals("1"))
		{				
			return mapping.findForward("inicioValida");
		}
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicioValidaEstadoCuenta");
			throw ar;
		}
		
		
		//REALIZAR CONSULTA DE COORDENADAS
		

		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					generarClaveSmsEnvio(mapping, form, request, response);		
				}
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioValidaEstadoCuenta");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

      
        return mapping.findForward("inicioValidaEstadoCuenta");
  }
       public ActionForward mostrarMedioEnvio(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

    	     TokenSmsRequest tokenSms = new TokenSmsRequest();
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);  
             frm.reset(mapping, request);

             request.getSession().setAttribute("mensajeCabeceraMedioEnvio", ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());

             loadObjectMedioEnvio(request);
             

              if(!getVerificarHorario(Constante.DAT_CLIENTES_ACTUALIZACION)){
              request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("DC01","00001")).elementAt(2).toString());
              request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("DC01","00002")).elementAt(2).toString());
              request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","DC01")).elementAt(2).toString());
              return mapping.findForward("transaccion.noDisponible");
              }
              
              try {
            	  
            	Afiliacion afiliacion = new AfiliacionImpl();
           		
//           		Afiliacion datosUsuario  = new AfiliacionImpl();
//          		datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
          		afiliacion.setCodCliente(usuario.getCodigoCic());					
          		
          		afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
          		afiliacion.setNroDocumento(numDoc);
          		
             	 
             	 ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
          		 request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
          		
          		
          		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
          	
                  
          		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
          			
          		
          		ParametrosElemSegTDC elementos = null;
              
  			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
  			
		  			try{
		  				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
						
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
						   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
							elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
						}
		  				
		  			}catch(ArrayRuleException ar){
		  				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
		  				ar.printStackTrace();
		  				ar.setForward("inicioValidaEstadoCuenta");
		  				throw ar;
		  			}
  			
  			
  			// Código Nuevo para TDC
  			
	  			MensajesTDC resultado = new MensajesTDC();
	  			Transferencia transf = null;
	  			
	  			if(param!= null && coord!=null ){
	  				
	  				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){					
							resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
						
						tokenSms.setCodCliente(afiliacion.getCodCliente());
						tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
						tokenSms.setNroDocumento(afiliacion.getNroDocumento());
						
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_ACTUALIZACION_DATOS);
						tokenSms.setTypeOp("ADMIN");						
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setTypeCurrency("PEN");
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
	  				
	  				
	  			}
	  			
              } catch (ArrayRuleException e) {
                  e.printStackTrace();
                  log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

                  e.setForward("inicioValidaEstadoCuenta");
                  throw e;
           }

             return mapping.findForward("consultaMedioEnvio");
       }
       
       public ActionForward mostrarMedioEnvioNCB(ActionMapping mapping,
               ActionForm form, HttpServletRequest request,
               HttpServletResponse response) throws Exception {
    	   
    	//System.out.println("mostrarMedioEnvioNCB..");

        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

        frm.reset(mapping, request);

        request.getSession().setAttribute("mensajeCabeceraMedioEnvio", ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());

        loadObjectMedioEnvio(request);

         if(!getVerificarHorario(Constante.DAT_CLIENTES_ACTUALIZACION)){
         request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("DC01","00001")).elementAt(2).toString());
         request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("DC01","00002")).elementAt(2).toString());
         request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","DC01")).elementAt(2).toString());
         return mapping.findForward("transaccion.noDisponible");
         }

        return mapping.findForward("consultaMedioEnvio");
        
  }
       
       public ActionForward consultaMedioEnvioNCB(ActionMapping mapping,
               ActionForm form, HttpServletRequest request,
               HttpServletResponse response) throws Exception {

        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
        Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
        Afiliacion consulta = new AfiliacionImpl();
        List correo = new ArrayList();
        List x = usuario.getCuentas();
        loadObjectMedioEnvio(request);

        try {
               Afiliacion afil_ = new AfiliacionImpl();
               String cuenta = frm.getCmbCuenta();

               if (cuenta != null && cuenta != "") {
                      String[] tipo = cuenta.split("-");

                      afil_.setCodigoServicio(tipo[0]);
                      if (afil_.getCodigoServicio().equals(
                                   Constante.COD_CUENTA_PRESTAMOS)) {
                             afil_.setCuentaPropia(tipo[1] + tipo[2]);
                      } else {
                             afil_.setCuentaPropia(tipo[1]);
                      }

                      // NUEVA VALIDACION
                      CuentaImpl cuentaValida = null;
                      List lista = usuario.getCuentas();
                      int nFlgExiste = 0;
                      for (Iterator iter = lista.iterator(); iter.hasNext();) {
                             cuentaValida = (CuentaImpl) iter.next();

                             if (cuentaValida.getNumeroProducto().equals(tipo[1])) {
                                   nFlgExiste = 1;
                                   break;
                             }
                      }

                      if (nFlgExiste == 0)
                             throw new ArrayRuleException(ConstanteError.GENERICO,
                                          "La cuenta consultada no le pertenece.");

               }

               request.setAttribute("cuentaSel", afil_.getCodigoServicio());
               request.setAttribute("datosMedioEnvio", "si");

               consulta = FacadeFactory.getAfiliacionFacade()
                             .getConsultaMedioEnvio(Constante.MED_ENVIO_CONSULTA, afil_,
                                          request.getRemoteAddr(), usuario, request);

               // Setear valores al front

               frm.setCmbMedioEnvio(consulta.getCodMedioEnvio());
               // Envia la lista de correo electronico
               request.getSession().setAttribute("lstCorreoElectronico", consulta.getCorreos());

               // Envia la lista de direcciones de correspondencia
               request.getSession().setAttribute("lstDirecciones",consulta.getDirecciones());

               // Obteniendo correo

               List correoSel = consulta.getCorreos();

               if (consulta.getCorreos().size() > 0) {
                      for (int i = 0; i < correoSel.size(); i++) {
                             AfiliacionesImpl valor = new AfiliacionesImpl();
                             valor = (AfiliacionesImpl) correoSel.get(i);
                             consulta.setEmail(valor.getEmail());
                      }
               }

               request.getSession().setAttribute("consultaMedioEnvio", consulta);

        } catch (ArrayRuleException e) {
               e.printStackTrace();
               log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
               request.setAttribute("flag", "0");
               e.setForward("consultaMedioEnvio");
               throw e;
        }

        return mapping.findForward("consultaMedioEnvioNCB");
  }

       public ActionForward consultaMedioEnvio(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
             Afiliacion consulta = new AfiliacionImpl();
             List correo = new ArrayList();
             List x = usuario.getCuentas();
             loadObjectMedioEnvio(request);

             try {
                    Afiliacion afil_ = new AfiliacionImpl();
                    String cuenta = frm.getCmbCuenta();

                    if (cuenta != null && cuenta != "") {
                           String[] tipo = cuenta.split("-");

                           afil_.setCodigoServicio(tipo[0]);
                           if (afil_.getCodigoServicio().equals(
                                        Constante.COD_CUENTA_PRESTAMOS)) {
                                  afil_.setCuentaPropia(tipo[1] + tipo[2]);
                           } else {
                                  afil_.setCuentaPropia(tipo[1]);
                           }

                           // NUEVA VALIDACION
                           CuentaImpl cuentaValida = null;
                           List lista = usuario.getCuentas();
                           int nFlgExiste = 0;
                           for (Iterator iter = lista.iterator(); iter.hasNext();) {
                                  cuentaValida = (CuentaImpl) iter.next();

                                  if (cuentaValida.getNumeroProducto().equals(tipo[1])) {
                                        nFlgExiste = 1;
                                        break;
                                  }
                           }

                           if (nFlgExiste == 0)
                                  throw new ArrayRuleException(ConstanteError.GENERICO,
                                               "La cuenta consultada no le pertenece.");

                    }

                    request.setAttribute("cuentaSel", afil_.getCodigoServicio());
                    request.setAttribute("datosMedioEnvio", "si");

                    consulta = FacadeFactory.getAfiliacionFacade()
                                  .getConsultaMedioEnvio(Constante.MED_ENVIO_CONSULTA, afil_,
                                               request.getRemoteAddr(), usuario, request);

                    // Setear valores al front

                    frm.setCmbMedioEnvio(consulta.getCodMedioEnvio());
                    // Envia la lista de correo electronico
                    request.getSession().setAttribute("lstCorreoElectronico", consulta.getCorreos());

                    // Envia la lista de direcciones de correspondencia
                    request.getSession().setAttribute("lstDirecciones",consulta.getDirecciones());

                    // Obteniendo correo

                    List correoSel = consulta.getCorreos();

                    if (consulta.getCorreos().size() > 0) {
                           for (int i = 0; i < correoSel.size(); i++) {
                                  AfiliacionesImpl valor = new AfiliacionesImpl();
                                  valor = (AfiliacionesImpl) correoSel.get(i);
                                  consulta.setEmail(valor.getEmail());
                           }
                    }

                    request.getSession().setAttribute("consultaMedioEnvio", consulta);

             } catch (ArrayRuleException e) {
                    e.printStackTrace();
                    log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
                    request.setAttribute("flag", "0");
                    e.setForward("consultaMedioEnvio");
                    throw e;
             }

             return mapping.findForward("consultaMedioEnvio");
       }

       public ActionForward guardarMedioEnvio(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

             ActionForward forward = new ActionForward();
             Usuario usuario = (Usuario) request.getSession().getAttribute(
                           ConstanteSesion.USUARIO_EN_SESION);
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             Afiliacion datos = new AfiliacionImpl();
             Afiliacion medioEnvio = new AfiliacionImpl();
             Afiliacion consulta = new AfiliacionImpl();

             try {

                    // Obteniendo codigo del cliente

            	 consulta = (Afiliacion)request.getSession().getAttribute("consultaMedioEnvio");
                 datos.setCodCliente(consulta.getCodCliente());

                    if (frm.getCmbMedioEnvio().equals(Constante.MED_ENVIO_MEDIO_FISICO)
                                  || frm.getCmbMedioEnvio().equals(
                                               Constante.MED_ENVIO_MEDIO_ELECT_FISICO)) {

                           String valor[] = frm.getCmbDireccionCorresp().split("-");
                           datos.setNumSecDir(valor[0]);
                           datos.setNumItemDir(valor[1]);

                    }

                    datos.setCodMedioEnvio(frm.getCmbMedioEnvio());

                    // Obtener Cuentas Asociadas
                    String cuenta[] = request.getParameter("cmbCuenta").split("-");
                    CuentaImpl cta = null;
                    List x = usuario.getCuentas();
                    for (int i = 0; i < x.size(); i++) {
                           cta = (CuentaImpl) x.get(i);
                           if (cta.getTipoProducto().equals(cuenta[0])) {
                                  datos.setCuenta(cta);

                           }
                    }

                    medioEnvio = FacadeFactory.getAfiliacionFacade()
                                  .getGuardarMedioEnvio(Constante.MED_ENVIO_GUARDAR_ELECCION,
                                               datos, request.getRemoteAddr(), usuario, request);

                    // Seteando valores al refrendo
                    medioEnvio.setCuenta(datos.getCuenta());
                    medioEnvio.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
                    medioEnvio.setHora(pe.cosapi.common.ObjectUtil
                                  .getHHMMSSformateado());

                    List doc = FacadeFactory.getGeneralFacade().getComboDetalleHlp(
                                  Constante.MED_ENVIO_COD_HLP_DET_DOC);

                    for (int a = 0; a < doc.size(); a++) {

                           ComboUtil combo = new ComboUtil();

                           combo = (ComboUtil) doc.get(a);

                           if (medioEnvio.getTipoDocumento().equals(combo.getCodigo())) {
                                  medioEnvio.setDocMostrar(combo.getDescripcion());

                           }

                    }

                    request.getSession().setAttribute(ConstanteSesion.AFILIAR,
                                  medioEnvio);

             } catch (ArrayRuleException e) {
                    e.printStackTrace();
                    log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
                    loadObjectMedioEnvio(request);
                    request.setAttribute("datosMedioEnvio", "si");
                    request.setAttribute("cuentaSel", datos.getCuenta()
                                  .getTipoProducto());
                    request.setAttribute("codCliente", datos.getCodCliente());
                    request.getSession().setAttribute("lstDirecciones",
                                  request.getSession().getAttribute("lstDirecciones"));
                    request.getSession().setAttribute("lstCorreoElectronico",
                                  request.getSession().getAttribute("lstCorreoElectronico"));
                    e.setForward("consultaMedioEnvio");
                    throw e;
             }
             if (frm.getCmbMedioEnvio().equals(Constante.MED_ENVIO_NINGUNO)) {
            	 ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ESTADO_CUENTA_NO_ENVIO, ConstanteSesion.AFILIAR, request);
                 forward = mapping.findForward("finMedioNoEnvio");
             } else {
            	 ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ESTADO_CUENTA_ENVIO, ConstanteSesion.AFILIAR, request);
                 forward = mapping.findForward("finMedioEnvio");
             }

             return forward;
       }

       public ActionForward mostrarDirCorresp(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

             ActionForward forward = new ActionForward();
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

             frm.reset(mapping, request);

             try {

                    loadObjectCoreActual(request);
                    request.getSession().setAttribute("codCliente",
                                  request.getParameter("codCliente"));
             } catch (ArrayRuleException e) {
                    e.printStackTrace();
                    log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
                    e.setForward("iniciarDirCorresp");
                    throw e;
             }

             forward = mapping.findForward("iniciarDirCorresp");

             return forward;
       }

       public ActionForward guardarDirCorresp(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

             ActionForward forward = new ActionForward();
             Usuario usuario = (Usuario) request.getSession().getAttribute(
                           ConstanteSesion.USUARIO_EN_SESION);
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

             loadObjectCoreActual(request);
             loadObjectMedioEnvio(request);

             try {

                    Tarjeta tarjeta = usuario.getTarjeta();
                    Afiliacion afiliacion = setearDirCorresp(frm, tarjeta, usuario,
                                  request);

                    FacadeFactory.getAfiliacionFacade().getGuardarDirCorresp(
                                  Constante.MED_ENVIO_GUARDAR_ELECCION, afiliacion,
                                  request.getRemoteAddr(), usuario, request);

             } catch (ArrayRuleException e) {
                    e.printStackTrace();
                    log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
                    request.setAttribute(
                                  "lstProvincia",
                                  FacadeFactory.getGeneralFacade().getComboProvincia(
                                               frm.getCmbDepartamento()));
                    request.setAttribute(
                                  "lstDistrito",
                                  FacadeFactory.getGeneralFacade().getComboDistrito(
                                               frm.getCmbDepartamento() + frm.getCmbProvincia()));
                    e.setForward("iniciarDirCorresp");
                    throw e;
             }

             request.setAttribute("codOperacion", "ok");

             forward = mapping.findForward("consultaMedioEnvio");

             return forward;
       }

       public ActionForward listarProvinciaCorresp(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             loadObjectCoreActual(request);

             request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade()
                           .getComboProvincia(frm.getCmbDepartamento()));
             return mapping.findForward("iniciarDirCorresp");
       }

       public ActionForward listarDistritoCorresp(ActionMapping mapping,
                    ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
             loadObjectCoreActual(request);

             request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade()
                           .getComboProvincia(frm.getCmbDepartamento()));
             request.setAttribute(
                           "lstDistrito",
                           FacadeFactory.getGeneralFacade().getComboDistrito(
                                        frm.getCmbDepartamento() + frm.getCmbProvincia()));
             return mapping.findForward("iniciarDirCorresp");
       }

       @Override
       public ActionForward iniciar(ActionMapping mapping, ActionForm form,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
             // TODO Apéndice de método generado automáticamente
             return null;
       }

       /*
       * CAMBIO POR CONVIVENCIA DEL NUEVO CORE BANCARIO 24/04/2015 MDOLORES
       * 
       * 
       */
       
       /*
       public ActionForward listarDireccion(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws Exception {

    
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        //Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
    	
    	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
    	
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

        request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
        request.getSession().setAttribute( "mensajeYaActualizado", ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
        loadObject(request);
        Afiliacion datos = new AfiliacionImpl();
        
        
        EE_I_ConsultaListaDom_type inputDomicilio = new EE_I_ConsultaListaDom_type();
        EE_O_ConsultaListaDom_type outputDomicilio = new EE_O_ConsultaListaDom_type(); 
        EE_O_ConsultaPublicidad_type marcaPD = new EE_O_ConsultaPublicidad_type();

        try {
        	
        	    marcaPD = consultarMarcaPD(form, usuarioCLDI,request);
        	
        		if(marcaPD.getIndicadorDocumentoPublicidad().toString().equals("N")){
        			frm.reset(mapping, request);
        			frm.setCmbConsentimiento(""+marcaPD.getIndicadorAutorizacionPublicidad());
        			return nuevoMarcaPD(mapping,form,request,response);
        			
        			
        		}
               inputDomicilio.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
         
               inputDomicilio.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
              
              outputDomicilio= generarCabeceraDomiciliov1(request).consultaLista(inputDomicilio);
               
               if (outputDomicilio.getCodigoRetorno() != Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK) {
                   String codErrEnt = "";
                   String mensaje = "";
                   
                   String codigo = "" + outputDomicilio.getErrores().getCodigoMostrar();

                   codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',5 - codigo.length());
                   mensaje = aplicacion.getMsjesHost("AD", codErrEnt).elementAt(2).toString();
                

                   throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase() + " (AD-" + codErrEnt + ")");
                   
               }else{
	                   ListaDomicilios[] lista =  outputDomicilio.getListaDomicilios();
	                   
	                   for (int i = 0; i < lista.length; i++) {
	           			ListaDomicilios domicilios = lista[i];
	           			
	           			
	           		}
	                   
	                   List tipoDomicilio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO);

				          for (int i = 0; i < tipoDomicilio.size(); i++) {
				
				                 DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				
				                 dHelp_ = (DetalleAyudaDatosImpl) tipoDomicilio.get(i);
				     
				          }
				         
				
				          request.getSession().setAttribute("lstTipoDomicilio", tipoDomicilio);
				 
               }
         
               

        } catch (ArrayRuleException e) {
               e.printStackTrace();
               log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
               request.getSession().setAttribute("consulta",outputDomicilio);
               e.setForward("listarDomicilio");
               throw e;
        }
        
        request.getSession().setAttribute("consulta",outputDomicilio);

        return mapping.findForward("listarDomicilio");
  }

       public ActionForward consultarDomicilio(ActionMapping mapping, ActionForm
    	        form, HttpServletRequest request, HttpServletResponse response) throws
    	        Exception {
    	        BNAplicacion aplicacion = BNAplicacion.getInstance();
    	        //Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
    	        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
    	    	
    	    	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	    	
    	       
    	       
    	        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
    	       
    	        request.getSession().setAttribute("mensajeCabecera",((Vector)aplicacion.getMensajePorCodigo("DC00","DC01")).elementAt(2).toString());
    	        request.getSession().setAttribute("mensajeYaActualizado",((Vector)aplicacion.getMensajePorCodigo("DC00","DC02")).elementAt(2).toString());
    	        loadObject(request);
    	       
    	        EE_I_ConsultaDomicilio_type input = new EE_I_ConsultaDomicilio_type();
    	          
    	        EE_O_ConsultaDomicilio_type consulta = new EE_O_ConsultaDomicilio_type();
    	       
    	         	       
    	        try{
    	       
    	              
    	        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
    	        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
    	        input.setNumeroDireccion( new BigInteger ( request.getParameter("txtNumeroDireccion")));
    	        
    	     	            	        
    	        consulta= generarCabeceraDomiciliov1(request).consultaDetalle(input);
    	        
    	          	           
    	        if(consulta.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK)
    	        {
    		        String codErrEnt="";
    		        String mensaje="";
    		       
    		        String codigo = ""+consulta.getCodigoRetorno();
    		       
    		        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',5-codigo.length());
    		        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
    		        mensaje=consulta.getErrores().getMensajeMostrar();
    		       
    		        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (AD-"+codErrEnt+")");
    	       
    	        }
    	        else{
    	       
    	        frm.setCmbCodigoDomicilioTipoVia(consulta.getDatosDireccion().getCodigoDomicilioTipoVia());
    	        frm.setTxtNombreVia(consulta.getDatosDireccion().getNombreVia().toUpperCase());
    	        frm.setTxtNumeroDomicilio(consulta.getDatosDireccion().getNumeroDomicilio().toUpperCase());
    	        frm.setTxtDomicilioPortal(consulta.getDatosDireccion().getDomicilioPortal().toUpperCase());
    	        frm.setTxtDomicilioEscalera(consulta.getDatosDireccion().getDomicilioEscalera().toUpperCase());
    	        frm.setTxtDomicilioPlanta(consulta.getDatosDireccion().getDomicilioPlanta().toUpperCase());
    	        frm.setTxtDomicilioPuerta(consulta.getDatosDireccion().getDomicilioPuerta().toUpperCase());
    	        frm.setTxtBloqueDireccion(consulta.getDatosDireccion().getBloqueDireccion().toUpperCase());
    	        frm.setTxtFaseDireccion(consulta.getDatosDireccion().getFaseDireccion().toUpperCase());
    	        frm.setTxtBarrioDireccion(consulta.getDatosDireccion().getBarrioDireccion().toUpperCase());
    	        frm.setTxtUrbanizacionDireccion(consulta.getDatosDireccion().getUrbanizacionDireccion().toUpperCase());
    	        frm.setTxtPoligonoDireccion(consulta.getDatosDireccion().getPoligonoDireccion().toUpperCase());
    	        frm.setTxtParcelaDireccion(consulta.getDatosDireccion().getParcelaDireccion().toUpperCase());
    	        frm.setTxtReferencia(consulta.getDatosDireccion().getOtrosDatosDireccion().toUpperCase());
    	        frm.setTxtEdificionDireccion(consulta.getDatosDireccion().getEdificioDireccion().toUpperCase());
    	        frm.setCbxTipoDomicilioHabitual(""+consulta.getDatosDomicilio().getIndicadorDomHabitual());
    	        frm.setCbxTipoDomicilioFiscal(""+consulta.getDatosDomicilio().getIndicadorDomFiscal());
    	        frm.setCbxTipoDomicilioPostal(""+consulta.getDatosDomicilio().getIndicadorDomPostal());
    	        
    	        
    	        //Datos del Telefono
    	        frm.setCmbCodOperTelefono(consulta.getDatosTelefono().getCodOperTelefono());
    	        frm.setCmbPrefTelefonoDomic(consulta.getDatosTelefono().getPrefTelefonoDomic());
    	        frm.setTxtTelefono(consulta.getDatosTelefono().getTelefonoDomic());
    	        frm.setTxtExtTelefono(consulta.getDatosTelefono().getExtTelefono());
    	         	      
    	        
    	      
    	          	     
    	        String indicador =""+consulta.getDatosLocalidad().getIndLocalidadCodif();
    	        String codigoLocalidad = consulta.getDatosLocalidad().getCodLocalidadOficial();
    	        frm.setCmbPais(consulta.getDatosLocalidad().getCodigoPais());
    	       		       
    	        if(indicador.equals("S")){
    	        	
    	        	if(consulta.getDatosLocalidad().getCodigoPais().equals(Constante.ACTUALIZ_DATOS_NCB_CODIGO_PAIS_PERU)){
    	        	
    	        		frm.setRdnExtranjero(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_PERU);
    	        		
    	        	     if(codigoLocalidad != null && codigoLocalidad != ""){
    	      		       
    	     		        frm.setCmbDepartamento(codigoLocalidad.substring(0, 2));
    	     		        frm.setCmbProvincia(codigoLocalidad.substring(0, 4));
    	     		        frm.setCmbDistrito(codigoLocalidad.substring(0, 6));
    	     		        frm.setCmbLocalidad(consulta.getDatosLocalidad().getCodLocalidadOficial());
    	     		        
    	     		 
    	     		        }
    	        	}
    	        	else{
    	        		frm.setRdnExtranjero(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_EXTRANJERO);
    	        		frm.setTxtDepartamento(consulta.getDatosLocalidad().getNombreComAutonoma());
    	        		frm.setTxtProvincia(consulta.getDatosLocalidad().getNombreProvincia());
    	        		frm.setTxtDistrito(consulta.getDatosLocalidad().getNombreMunicipio());
    	        		frm.setTxtLocalidad(consulta.getDatosLocalidad().getNombreLocalidad());
    	        	}
    		   
    	        }else{
    	        	frm.setRdnExtranjero(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_EXTRANJERO);
    	      
    		        frm.setCmbDepartamento(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
    		        frm.setCmbProvincia(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
    		        frm.setCmbDistrito(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
    		        frm.setCmbLocalidad(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
    				frm.setTxtDepartamento(consulta.getDatosLocalidad().getNombreComAutonoma());
	        		frm.setTxtProvincia(consulta.getDatosLocalidad().getNombreProvincia());
	        		frm.setTxtDistrito(consulta.getDatosLocalidad().getNombreMunicipio());
	        		frm.setTxtLocalidad(consulta.getDatosLocalidad().getNombreLocalidad());
	        		frm.setCmbPais(consulta.getDatosLocalidad().getCodigoPais());
    		       
    		        }
    	 
    	        }
    	        
    	        request.getSession().setAttribute("tipoDomicilio",frm);
    	        List localidad = FacadeFactory.getGeneralFacade().getComboLocalidad(frm.getCmbDistrito());
    	        
    	      
    	        
    	        for (int j = 0; j < localidad.size(); j++) {
    	 	       
    		        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
    		       
    		        dHelp_ = (DetalleAyudaDatosImpl)localidad.get(j);
    		       
    		            
    	        }
    	        
    	        request.setAttribute("lstLocalidad",localidad );
    	        request.setAttribute("lstProvincia",FacadeFactory.getGeneralFacade().getComboProvinciaCore(frm.getCmbDepartamento()));
    	        request.setAttribute("lstDistrito", FacadeFactory.getGeneralFacade().getComboDistritoCore(frm.getCmbProvincia()));
    	      
    	        }
    	        catch (ArrayRuleException e) {
    	        e.printStackTrace();
    	        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
    	       
    	        e.setForward("consultaDireccion");
    	        
    	        loadObject(request);
    	        throw e;
    	        }
    	       
    	       
    	        request.getSession().setAttribute("consultaDomicilio", consulta);
    	        return mapping.findForward("consultaDireccion");
    	        }
    	        
    	        */


//        public ActionForward consultaDireccion(ActionMapping mapping, ActionForm
//        form, HttpServletRequest request, HttpServletResponse response) throws
//        Exception {
//        BNAplicacion aplicacion = BNAplicacion.getInstance();
//        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
//       
//       
//        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
//       
//        request.getSession().setAttribute("mensajeCabecera",((Vector)aplicacion.getMensajePorCodigo("DC00","DC01")).elementAt(2).toString());
//        request.getSession().setAttribute("mensajeYaActualizado",((Vector)aplicacion.getMensajePorCodigo("DC00","DC02")).elementAt(2).toString());
//        loadObject(request);
//       
//          
//        EE_O_ConsultaDomicilioHabitual_type consulta = new EE_O_ConsultaDomicilioHabitual_type();
//       
//        EE_I_ConsultaIdInternoDomicilioHabitual_type input = new EE_I_ConsultaIdInternoDomicilioHabitual_type();
//        
//        //-------------------------------------------------------------------------------------------------------------
//        
//        
//        EE_I_ConsultaListaDom_type inputDomicilio = new EE_I_ConsultaListaDom_type();
//        EE_O_ConsultaListaDom_type outputDomicilio = new EE_O_ConsultaListaDom_type();
//        
//        //-------------------------------------------------------------------------------------------------------------
//        
//       
//        try{
//       
//              
//        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
//        input.setIdInternoPe(20000269);
////        
//        //-------------------------------------------------------------------------------------------------------------
//        inputDomicilio.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
//        inputDomicilio.setIdInternoPe(20000269);
//        
//        
//        
//        outputDomicilio= generarCabeceraDomiciliov1(request).consultaLista(inputDomicilio);
//        
//        ListaDomicilios[] lista =  outputDomicilio.getListaDomicilios();
//        
//        for (int i = 0; i < lista.length; i++) {
//			ListaDomicilios domicilios = lista[i];
//			System.out.println("domicilios.getCodigoPersonaDireccion():"+domicilios.getCodigoPersonaDireccion());
//			
//		}
//        
//       
//        
//        //-------------------------------------------------------------------------------------------------------------
//                      
//        consulta = generarCabeceraDomicilio(request).consultaIdInterno(input);
//       
//           
//        if(consulta.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK)
//        {
//	        String codErrEnt="";
//	        String mensaje="";
//	       
//	        String codigo = ""+consulta.getCodigoRetorno();
//	       
//	        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',5-codigo.length());
//	        mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
//	       
//	        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (AD-"+codErrEnt+")");
//       
//        }
//        else{
//       
//       
//        frm.setCmbCodigoDomicilioTipoVia(consulta.getDatosDireccion().getCodigoDomicilioTipoVia());
//        frm.setTxtNombreVia(consulta.getDatosDireccion().getNombreVia().toUpperCase());
//        frm.setTxtNumeroDomicilio(consulta.getDatosDireccion().getNumeroDomicilio().toUpperCase());
//        frm.setTxtDomicilioPortal(consulta.getDatosDireccion().getDomicilioPortal().toUpperCase());
//        frm.setTxtDomicilioEscalera(consulta.getDatosDireccion().getDomicilioEscalera().toUpperCase());
//        frm.setTxtDomicilioPlanta(consulta.getDatosDireccion().getDomicilioPlanta().toUpperCase());
//        frm.setTxtDomicilioPuerta(consulta.getDatosDireccion().getDomicilioPuerta().toUpperCase());
//        frm.setTxtBloqueDireccion(consulta.getDatosDireccion().getBloqueDireccion().toUpperCase());
//        frm.setTxtFaseDireccion(consulta.getDatosDireccion().getFaseDireccion().toUpperCase());
//        frm.setTxtBarrioDireccion(consulta.getDatosDireccion().getBarrioDireccion().toUpperCase());
//        frm.setTxtUrbanizacionDireccion(consulta.getDatosDireccion().getUrbanizacionDireccion().toUpperCase());
//        frm.setTxtPoligonoDireccion(consulta.getDatosDireccion().getPoligonoDireccion().toUpperCase());
//        frm.setTxtParcelaDireccion(consulta.getDatosDireccion().getParcelaDireccion().toUpperCase());
//        frm.setTxtReferencia(consulta.getDatosDireccion().getOtrosDatosDireccion().toUpperCase());
//        frm.setTxtEdificionDireccion(consulta.getDatosDireccion().getEdificioDireccion().toUpperCase());
//       
//     
//        String indicador =""+consulta.getDatosLocalidad().getIndLocalidadCodif();
//        if(indicador.equals("S")){
//       
//	        String codigoLocalidad = consulta.getDatosLocalidad().getCodLocalidadOficial();
//	       
//	       
//	        if(codigoLocalidad != null && codigoLocalidad != ""){
//	       
//	        frm.setCmbDepartamento(codigoLocalidad.substring(0, 2));
//	        frm.setCmbProvincia(codigoLocalidad.substring(2, 4));
//	        frm.setCmbDistrito(codigoLocalidad.substring(4, 6));
//	     
//	        frm.setCmbLocalidad(consulta.getDatosLocalidad().getCodLocalidadOficial());
//	        }
//       
//       
//        }else{
//       
//      
//	        frm.setCmbDepartamento(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
//	        frm.setCmbProvincia(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
//	        frm.setCmbDistrito(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
//	        frm.setCmbLocalidad(Constante.ACTUALIZ_DATOS_NCB_SIN_CODIGO);
//	       
//	        }
//         
//       
//       
//        }
//        
//        List localidad = FacadeFactory.getGeneralFacade().getComboLocalidad(frm.getCmbDepartamento()+frm.getCmbProvincia()+frm.getCmbDistrito());
//        
//      
//        
//        for (int j = 0; j < localidad.size(); j++) {
// 	       
//	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
//	       
//	        dHelp_ = (DetalleAyudaDatosImpl)localidad.get(j);
//	       
//	            
//        }
//        
//        request.setAttribute("lstLocalidad",localidad );
//        request.setAttribute("lstProvincia",FacadeFactory.getGeneralFacade().getComboProvincia(frm.getCmbDepartamento()));
//        request.setAttribute("lstDistrito", FacadeFactory.getGeneralFacade().getComboDistrito(frm.getCmbDepartamento()+frm.getCmbProvincia()));
//      
//        }
//        catch (ArrayRuleException e) {
//        e.printStackTrace();
//        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
//       
//        e.setForward("consultaDireccion");
//        
//        loadObject(request);
//        throw e;
//        }
//       
//       
//        request.getSession().setAttribute("consultaDomicilio", consulta);
//        return mapping.findForward("consultaDireccion");
//        }
       
       /*
        public ActionForward listarProvincia1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
	        loadObject(request);
	      
	       
	        ActionForward forward = new ActionForward();
	        
	        EE_O_ConsultaDomicilio_type consultaDomicilio = (EE_O_ConsultaDomicilio_type)request.getSession().getAttribute("consultaDomicilio");
	        
	    
	        if(request.getParameter("parametro")!= null && request.getParameter("parametro").trim().equals("true")){
	        
	        	  forward = mapping.findForward("registrarDomicilio");
	        	  frm=validarTipoDomicilio(request,frm);
	        }else{
	        	  forward = mapping.findForward("consultaDireccion");
	        	    if(consultaDomicilio!= null){
	        	    	
	        	    	frm.setCbxTipoDomicilioHabitual(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomHabitual());
    	      	        frm.setCbxTipoDomicilioFiscal(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomFiscal());
    	      	        frm.setCbxTipoDomicilioPostal(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomPostal());
//    	      	        if (frm.getCbxTipoDomicilioPostal() == null || frm.getCbxTipoDomicilioPostal().equals(""))
//    	      	    	frm.setCbxTipoDomicilioPostal("N");else{frm.setCbxTipoDomicilioPostal("S");	
//    	                }
    	      	        
    	             }
	    	 }
	       
	        request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade().getComboProvinciaCore(frm.getCmbDepartamento()));
	        
	       
	        
	      
	        request.getSession().setAttribute("tipoDomicilio",frm);
	       
	        return forward;
        }
       
    
        public ActionForward listarDistrito1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
	        loadObject(request);
	       
	        request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade().getComboProvinciaCore(frm.getCmbDepartamento()));
	        request.setAttribute("lstDistrito", FacadeFactory.getGeneralFacade().getComboDistritoCore(frm.getCmbProvincia()));
	      
	        ActionForward forward = new ActionForward();
	        	       
	        EE_O_ConsultaDomicilio_type consultaDomicilio = (EE_O_ConsultaDomicilio_type)request.getSession().getAttribute("consultaDomicilio");
	        	        
	        if(request.getParameter("parametro")!= null &&  request.getParameter("parametro").trim().equals("true")){
		        
	        	  forward = mapping.findForward("registrarDomicilio");
	  	        if(frm.getCbxTipoDomicilioPostal().equals("on")){
		        	frm.setCbxTipoDomicilioPostal("S");
				}else{
					if(frm.getCbxTipoDomicilioPostal()==null && frm.getCbxTipoDomicilioPostal().equals("") ){
					frm.setCbxTipoDomicilioPostal("N");
					}
				}
		        
		        frm=validarTipoDomicilio(request,frm);
	        }else{
	        	  forward = mapping.findForward("consultaDireccion");
	      	    	if(consultaDomicilio!= null){
        	    	
        	    	frm.setCbxTipoDomicilioHabitual(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomHabitual());
	      	        frm.setCbxTipoDomicilioFiscal(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomFiscal());
	      	        frm.setCbxTipoDomicilioPostal(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomPostal());
//	      	        if (frm.getCbxTipoDomicilioPostal() == null || frm.getCbxTipoDomicilioPostal().equals(""))
//	      	    	frm.setCbxTipoDomicilioPostal("N");else{frm.setCbxTipoDomicilioPostal("S");	
//	                }
	      	        
	             }
	        }
	        request.getSession().setAttribute("tipoDomicilio",frm);
	        
	        return forward;
        }
       
        public ActionForward listarLocalidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
	        loadObject(request);
	        ActionForward forward = new ActionForward();
	        EE_O_ConsultaDomicilio_type consultaDomicilio = (EE_O_ConsultaDomicilio_type)request.getSession().getAttribute("consultaDomicilio");
	        
	        
	        
	        request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade().getComboProvinciaCore(frm.getCmbDepartamento()));
	        request.setAttribute("lstDistrito",  FacadeFactory.getGeneralFacade().getComboDistritoCore(frm.getCmbProvincia()));
	        request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboLocalidad(frm.getCmbDistrito()));
	    	        
	        if(request.getParameter("parametro")!= null && request.getParameter("parametro").trim().equals("true")){
	        	 
	        	  forward = mapping.findForward("registrarDomicilio");
	        	  frm=validarTipoDomicilio(request,frm);
	        }else{
	        	  forward = mapping.findForward("consultaDireccion");
	        	  if(consultaDomicilio!= null){
        	    	
        	    	frm.setCbxTipoDomicilioHabitual(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomHabitual());
	      	        frm.setCbxTipoDomicilioFiscal(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomFiscal());
	      	        frm.setCbxTipoDomicilioPostal(""+consultaDomicilio.getDatosDomicilio().getIndicadorDomPostal());
//	      	        if (frm.getCbxTipoDomicilioPostal() == null || frm.getCbxTipoDomicilioPostal().equals(""))
//	      	    	frm.setCbxTipoDomicilioPostal("N");else{frm.setCbxTipoDomicilioPostal("S");	
//	                }
	      	        
	             }
	        }
	        request.getSession().setAttribute("tipoDomicilio",frm);
	        
	        return forward;
        }
       
        /*
        public ActionForward guardarDireccion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws  Exception {
       
        //Metodo Modificar
      
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
    	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
        Afiliacion datos = new AfiliacionImpl();
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
        EE_O_ConsultaDomicilio_type consultaDomicilio = (EE_O_ConsultaDomicilio_type)request.getSession().getAttribute("consultaDomicilio");
        DomicilioCliente domicilio = new DomicilioCliente();
       
        try{
      
        EE_I_ModificacionDomicilio_type input = new EE_I_ModificacionDomicilio_type();
       
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        DatosDireccion datosDireccion = new DatosDireccion();
        DatosLocalidad datosLocalidad = new DatosLocalidad();
        DatosDomicilio datosDomicilio = new DatosDomicilio();
        com.abside_product.www.empresa.SE_PE_Domicilios.DatosTelefono datosTelefono = new com.abside_product.www.empresa.SE_PE_Domicilios.DatosTelefono();
       
        frm = validarCampos(request, frm);
        //Datos del Domicilio
        T_valor_S_N indicadorDom= null;
      
        datosDomicilio.setIndicadorDomHabitual(consultaDomicilio.getDatosDomicilio().getIndicadorDomHabitual());
        datosDomicilio.setIndicadorDomFiscal((consultaDomicilio.getDatosDomicilio().getIndicadorDomFiscal()));
        //datosDomicilio.setIndicadorDomPostal(indicadorDom.fromString(frm.getCbxTipoDomicilioPostal()));
        datosDomicilio.setIndicadorDomPostal((consultaDomicilio.getDatosDomicilio().getIndicadorDomPostal()));
        datosDomicilio.setIndErrorDomicilio(indicadorDom.fromString("N"));
       
        datosDomicilio.setCodRegimenOcupacion(consultaDomicilio.getDatosDomicilio().getCodRegimenOcupacion());
        datosDomicilio.setAnioAdquisicion(consultaDomicilio.getDatosDomicilio().getAnioAdquisicion());
        datosDomicilio.setNombreParaCorreo(consultaDomicilio.getDatosDomicilio().getNombreParaCorreo());
        datosDomicilio.setFechaDesdeDomicilio(consultaDomicilio.getDatosDomicilio().getFechaDesdeDomicilio());
        datosDomicilio.setFechaHastaDomicilio(consultaDomicilio.getDatosDomicilio().getFechaHastaDomicilio());
      
        input.setDatosDomicilio(datosDomicilio);
        
     
        //Datos de la Direccion
        datosDireccion.setCodigoDomicilioTipoVia(frm.getCmbCodigoDomicilioTipoVia());
        datosDireccion.setNombreVia(frm.getTxtNombreVia().toUpperCase());
        datosDireccion.setNumeroDomicilio(frm.getTxtNumeroDomicilio().toUpperCase());
        datosDireccion.setDomicilioPortal(frm.getTxtDomicilioPortal().toUpperCase());
        datosDireccion.setDomicilioEscalera(frm.getTxtDomicilioEscalera().toUpperCase());
        datosDireccion.setDomicilioPlanta(frm.getTxtDomicilioPlanta().toUpperCase());
        datosDireccion.setDomicilioPuerta(frm.getTxtDomicilioPuerta().toUpperCase());
        datosDireccion.setBloqueDireccion(frm.getTxtBloqueDireccion().toUpperCase());
        datosDireccion.setFaseDireccion(frm.getTxtFaseDireccion().toUpperCase());
        datosDireccion.setEdificioDireccion(frm.getTxtEdificionDireccion().toUpperCase());
        datosDireccion.setBarrioDireccion(frm.getTxtBarrioDireccion().toUpperCase());
        datosDireccion.setUrbanizacionDireccion(frm.getTxtUrbanizacionDireccion().toUpperCase());
        datosDireccion.setPoligonoDireccion(frm.getTxtPoligonoDireccion().toUpperCase());
        datosDireccion.setParcelaDireccion(frm.getTxtParcelaDireccion().toUpperCase());
        datosDireccion.setOtrosDatosDireccion(frm.getTxtReferencia().toUpperCase());
       
        input.setDatosDireccion(datosDireccion);
       
        //Datos de la Localidad
        
        T_ind_local_codif indicador = null;
        
    
    	if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_PERU)){
    		
    		   datosLocalidad.setCodigoPais(Constante.ACTUALIZ_DATOS_CODIGO_PAIS);
    	        datosLocalidad.setCodigoPostal(Constante.ACTUALIZ_DATOS_CODIGO_POSTAL);
    	        datosLocalidad.setNombreComAutonoma("");
    	        datosLocalidad.setNombreLocalidad("");
    	        datosLocalidad.setNombreMunicipio("");
    	        datosLocalidad.setNombreProvincia("");
    	        datosLocalidad.setIndLocalidadCodif(indicador.fromString(Constante.ACTUALIZ_DATOS_CODIGO_IND_LOCALIDAD));
    	      
    	        datosLocalidad.setCodProvinciaOficial(frm.getCmbProvincia());
    	        
    	        if(frm.getCmbLocalidad() != null && !frm.getCmbLocalidad().equals("") ){
    	            datosLocalidad.setCodLocalidadOficial(frm.getCmbLocalidad());
    	            
    	       }else
    	       		{
    	            datosLocalidad.setCodLocalidadOficial(frm.getCmbDistrito()+Constante.ACTUALIZ_DATOS_CODIGO_LOCALIDAD_NO_EXISTE);
    	           
    	            }
    	     
    	}else{
    		if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_EXTRANJERO)){
    			
    			 datosLocalidad.setIndLocalidadCodif(indicador.fromString(Constante.ACTUALIZ_DATOS_CODIGO_IND_LOCALIDAD_NO));
    			 datosLocalidad.setCodLocalidadOficial("");
    			 datosLocalidad.setNombreComAutonoma(frm.getTxtDepartamento());
       	         datosLocalidad.setNombreLocalidad(frm.getTxtLocalidad());
       	         datosLocalidad.setNombreMunicipio(frm.getTxtDistrito());
       	         datosLocalidad.setNombreProvincia(frm.getTxtProvincia());
       	         datosLocalidad.setCodigoPais(frm.getCmbPais());
       	      	 datosLocalidad.setCodigoPostal(Constante.ACTUALIZ_DATOS_CODIGO_POSTAL);
       	      	 datosLocalidad.setCodProvinciaOficial("9999");
       	    
    		}
    		
    	}
    
        //Datos del Telefono
        
        datosTelefono.setCodOperTelefono(frm.getCmbCodOperTelefono());
        datosTelefono.setExtTelefono(frm.getTxtExtTelefono());
        datosTelefono.setPrefTelefonoDomic(frm.getCmbPrefTelefonoDomic());
        datosTelefono.setTelefonoDomic(frm.getTxtTelefono());
  
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
    
        input.setNumeroDireccion(BigInteger.valueOf(Long.parseLong(""+consultaDomicilio.getNumeroDireccion())));
        input.setDatosTelefono(datosTelefono);
        input.setDatosLocalidad(datosLocalidad);
       
       
        EE_O_ModificacionDomiciliol_type output = new  EE_O_ModificacionDomiciliol_type();
       
        // Invocar Servicio Web
       
       
       output = generarCabeceraDomiciliov1(request).modificacion(input);
               
        if(output.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
        String codErrEnt="";
        String mensaje="";
       
       
        String codigoMostrar = ""+output.getErrores().getCodigoMostrar();
       
        codErrEnt = ObjectUtil.setearCaracterLeft(codigoMostrar, '0', 5-codigoMostrar.length());
        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
        mensaje=output.getErrores().getMensajeMostrar();
       
        throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
       
        }
        else{
        frm=validarCampos(request,frm);
       
        String direccion = obtenerDireccion(request,frm);
        domicilio.setDomicilioConcatenado(direccion);
        String desUbigeo= FacadeFactory.getGeneralFacade().getDescripcionUbigeo(frm.getCmbDistrito());
        if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_PERU) && desUbigeo != null  && !desUbigeo.equals("")){
        	 domicilio.setLocalidadCompleta(desUbigeo);
        }else{
        	 domicilio.setLocalidadCompleta("");
        }
       
        domicilio.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
        domicilio.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
        domicilio.setTipoDomicilio( mostrarTipoDomicilio( request,  datosDomicilio));
        
       
       
        FacadeFactory.getAfiliacionFacade() .guardarLogDomicilio(Constante.ACTUALIZ_DATOS_NCB_COD_ACT_DOMICILIO, request.getRemoteAddr(), usuario, domicilio, request);
        
        }
       
      
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
        request.setAttribute("lstProvincia",FacadeFactory.getGeneralFacade().getComboProvinciaCore(frm.getCmbDepartamento()));
        request.setAttribute("lstDistrito",FacadeFactory.getGeneralFacade().getComboDistritoCore(frm.getCmbProvincia()));
       
        e.setForward("consultaDireccion");
        throw e;
        }
       
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_DOMICILIO, domicilio);
       
        return mapping.findForward("guardarDireccion");
        }
       
       */
        
        public ActionForward registrarDomicilio(ActionMapping mapping, ActionForm
    	        form, HttpServletRequest request, HttpServletResponse response) throws
    	        Exception {
        	
        		
    	 BNAplicacion aplicacion = BNAplicacion.getInstance();
    	
    	           	         	       
	    	        try{
	    	       
	    	  
	    	        	   AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
	    	    	        frm.reset(mapping, request);
	    	    	       
	    	    	        request.getSession().setAttribute("mensajeCabecera",((Vector)aplicacion.getMensajePorCodigo("DC00","DC01")).elementAt(2).toString());
	    	    	        request.getSession().setAttribute("mensajeYaActualizado",((Vector)aplicacion.getMensajePorCodigo("DC00","DC02")).elementAt(2).toString());
	    	    	        loadObject(request);
	    	    	       
	    	        }
	    	        catch (ArrayRuleException e) {
	    	        e.printStackTrace();
	    	        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	    	       
	    	        e.setForward("listarDomicilio");
	    	        
	    	        loadObject(request);
	    	        throw e;
	    	        }
	    	       
    	       
    	       
    	        return mapping.findForward("registrarDomicilio");
    	}
        
        /*
        
        public ActionForward guardarDireccion1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws  Exception {
            
         
            BNAplicacion aplicacion = BNAplicacion.getInstance();
            Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
            
            UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
           	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
        	
            
            Afiliacion datos = new AfiliacionImpl();
           
            loadObject(request);
            AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
            
            frm=validarTipoDomicilio(request,frm);
            
            request.getSession().setAttribute("tipoDomicilio",frm);
           
          
            DomicilioCliente domicilio = new DomicilioCliente();
          
                    
            try{
            	
            	          
            EE_I_AltaDomicilio_type input = new EE_I_AltaDomicilio_type();
           
            input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
            DatosDireccion datosDireccion = new DatosDireccion();
            DatosLocalidad datosLocalidad = new DatosLocalidad();
            DatosDomicilio datosDomicilio = new DatosDomicilio();
            com.abside_product.www.empresa.SE_PE_Domicilios.DatosTelefono datosTelefono = new com.abside_product.www.empresa.SE_PE_Domicilios.DatosTelefono();
           
            //frm = validarCampos(request, frm);
            //Datos del Domicilio
            T_valor_S_N indicadorDom= null;
          
            datosDomicilio.setIndicadorDomHabitual(indicadorDom.fromString("N"));
            datosDomicilio.setIndicadorDomFiscal(indicadorDom.fromString(frm.getCbxTipoDomicilioFiscal()));
            datosDomicilio.setIndicadorDomPostal(indicadorDom.fromString(frm.getCbxTipoDomicilioPostal()));
            datosDomicilio.setIndErrorDomicilio(indicadorDom.fromString("N"));
           
        
            input.setDatosDomicilio(datosDomicilio);
            
         
            //Datos de la Direccion
            datosDireccion.setCodigoDomicilioTipoVia(frm.getCmbCodigoDomicilioTipoVia());
            datosDireccion.setNombreVia(frm.getTxtNombreVia().toUpperCase());
            datosDireccion.setNumeroDomicilio(frm.getTxtNumeroDomicilio().toUpperCase());
            datosDireccion.setDomicilioPortal(frm.getTxtDomicilioPortal().toUpperCase());
            datosDireccion.setDomicilioEscalera(frm.getTxtDomicilioEscalera().toUpperCase());
            datosDireccion.setDomicilioPlanta(frm.getTxtDomicilioPlanta().toUpperCase());
            datosDireccion.setDomicilioPuerta(frm.getTxtDomicilioPuerta().toUpperCase());
            datosDireccion.setBloqueDireccion(frm.getTxtBloqueDireccion().toUpperCase());
            datosDireccion.setFaseDireccion(frm.getTxtFaseDireccion().toUpperCase());
            datosDireccion.setEdificioDireccion(frm.getTxtEdificionDireccion().toUpperCase());
            datosDireccion.setBarrioDireccion(frm.getTxtBarrioDireccion().toUpperCase());
            datosDireccion.setUrbanizacionDireccion(frm.getTxtUrbanizacionDireccion().toUpperCase());
            datosDireccion.setPoligonoDireccion(frm.getTxtPoligonoDireccion().toUpperCase());
            datosDireccion.setParcelaDireccion(frm.getTxtParcelaDireccion().toUpperCase());
            datosDireccion.setOtrosDatosDireccion(frm.getTxtReferencia().toUpperCase());
           
            input.setDatosDireccion(datosDireccion);
           
            //Datos de la Localidad
            
            T_ind_local_codif indicador = null;
            
        
        	if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_PERU)){
        		
        		   datosLocalidad.setCodigoPais(Constante.ACTUALIZ_DATOS_CODIGO_PAIS);
        	        datosLocalidad.setCodigoPostal(Constante.ACTUALIZ_DATOS_CODIGO_POSTAL);
        	        datosLocalidad.setNombreComAutonoma("");
        	        datosLocalidad.setNombreLocalidad("");
        	        datosLocalidad.setNombreMunicipio("");
        	        datosLocalidad.setNombreProvincia("");
        	        datosLocalidad.setIndLocalidadCodif(indicador.fromString(Constante.ACTUALIZ_DATOS_CODIGO_IND_LOCALIDAD));
        	        datosLocalidad.setCodProvinciaOficial(frm.getCmbProvincia());
        	        
        	        if(frm.getCmbLocalidad() != null && !frm.getCmbLocalidad().equals("") ){
        	            datosLocalidad.setCodLocalidadOficial(frm.getCmbLocalidad());
        	       }else
        	       		{
        	            datosLocalidad.setCodLocalidadOficial(frm.getCmbDistrito()+Constante.ACTUALIZ_DATOS_CODIGO_LOCALIDAD_NO_EXISTE);
        	            }
        	    
        	}else{
        		if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_EXTRANJERO)){
        			
        			 datosLocalidad.setIndLocalidadCodif(indicador.fromString(Constante.ACTUALIZ_DATOS_CODIGO_IND_LOCALIDAD_NO));
        			 datosLocalidad.setCodLocalidadOficial("");
        			 datosLocalidad.setNombreComAutonoma(frm.getTxtDepartamento());
           	         datosLocalidad.setNombreLocalidad(frm.getTxtLocalidad().toUpperCase());
           	         datosLocalidad.setNombreMunicipio(frm.getTxtDistrito().toUpperCase());
           	         datosLocalidad.setNombreProvincia(frm.getTxtProvincia().toUpperCase());
           	         datosLocalidad.setCodigoPais(frm.getCmbPais());
           	      	 datosLocalidad.setCodigoPostal(Constante.ACTUALIZ_DATOS_CODIGO_POSTAL);
           	      	 datosLocalidad.setCodProvinciaOficial("9999");
           	    
        		}
        		
        	}
        	
            //Datos del Telefono
            
            datosTelefono.setCodOperTelefono(frm.getCmbCodOperTelefono());
            datosTelefono.setExtTelefono(frm.getTxtExtTelefono());
            datosTelefono.setPrefTelefonoDomic(frm.getCmbPrefTelefonoDomic());
            datosTelefono.setTelefonoDomic(frm.getTxtTelefono());
      
            input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        
           
            input.setDatosTelefono(datosTelefono);
            input.setDatosLocalidad(datosLocalidad);
           
           
            EE_O_AltaDomicilio_type output = new  EE_O_AltaDomicilio_type();
           
            // Invocar Servicio Web
           
           
           output = generarCabeceraDomiciliov1(request).alta(input);
                   
            if(output.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
            String codErrEnt="";
            String mensaje="";
           
            String codigo = ""+output.getErrores().getCodigoMostrar();
           
            codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0', 5-codigo.length());
            //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
            mensaje=output.getErrores().getMensajeMostrar();
           
            throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
           
            }
            else{
           
           
            String direccion = obtenerDireccion(request,frm);
            String desUbigeo= FacadeFactory.getGeneralFacade().getDescripcionUbigeo(frm.getCmbDistrito());
            domicilio.setDomicilioConcatenado(direccion);
            if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_PERU) && desUbigeo != null  && !desUbigeo.equals("")){
            	domicilio.setLocalidadCompleta(desUbigeo);
           }else{
           	 	domicilio.setLocalidadCompleta("");
           }
            
            domicilio.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
            domicilio.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
            domicilio.setTipoDomicilio( mostrarTipoDomicilio( request,  datosDomicilio));
            
            FacadeFactory.getAfiliacionFacade() .guardarLogDomicilio(Constante.ACTUALIZ_DATOS_NCB_COD_ALTA_DOMICILIO, request.getRemoteAddr(), usuario, domicilio, request);
            
            }
           
          
           
            }
            catch (ArrayRuleException e) {
         
            e.printStackTrace();
            log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
            
            if(frm.getRdnExtranjero().equals(Constante.ACTUALIZ_DATOS_NCB_DOMICILIO_PERU)){
            	 request.setAttribute("lstProvincia",FacadeFactory.getGeneralFacade().getComboProvinciaCore(frm.getCmbDepartamento()));
                 request.setAttribute("lstDistrito",FacadeFactory.getGeneralFacade().getComboDistritoCore(frm.getCmbProvincia()));
                 request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboLocalidad(frm.getCmbDistrito()));
                
	          }
            
           
            e.setForward("registrarDomicilio");
            throw e;
            }
           
            request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_DOMICILIO, domicilio);
           
            return mapping.findForward("guardarDomicilio");
        
        }
        
        
        public ActionForward listarTelefono(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
       
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        //Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
    	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
        request.getSession().setAttribute("mensajeCabecera",((Vector)aplicacion.getMensajePorCodigo("DC00","DC01")).elementAt(2).toString());
        request.getSession().setAttribute("mensajeYaActualizado",((Vector)aplicacion.getMensajePorCodigo("DC00","DC02")).elementAt(2).toString());
        loadObject(request);
        EE_I_ConsultaTelefonos_type input = new EE_I_ConsultaTelefonos_type();
        EE_O_ConsultaTelefonos_type consulta = new EE_O_ConsultaTelefonos_type();
        ListaTelefonos listaTelefonos[] = null;
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        EE_O_ConsultaPublicidad_type marcaPD = new EE_O_ConsultaPublicidad_type();
       
        try{
             
    		marcaPD = consultarMarcaPD(form, usuarioCLDI, request);
        	
    		if(marcaPD.getIndicadorDocumentoPublicidad().toString().equals("N")){
    			frm.reset(mapping, request);
    			frm.setCmbConsentimiento(""+marcaPD.getIndicadorAutorizacionPublicidad());
    			return nuevoMarcaPD(mapping,form,request,response);
    		
    		}
       
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
      
        consulta = generarCabeceraTelefono().consulta(input);
       
       
        ListaTelefonos lista[] = consulta.getListaTelefonos();
        
              
        List tipoTelefono =  FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_TELEFONO);
       
       
        request.getSession().setAttribute("lstTipoTelefono", tipoTelefono);
       
       
        if(consulta.getCodigoRetorno()!=Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
	        String codErrEnt="";
	        String mensaje="";
	        
	          
	        String codigo = ""+consulta.getCodigoRetorno();
	       
	        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
	        5-codigo.length());
	        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
	        mensaje=consulta.getErrores().getMensajeMostrar().toUpperCase();
	             
	        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (AD-"+codErrEnt+")");
	       
        }
        else{
  
	        consulta.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
	        consulta.setListaTelefonos(lista);
       
        }
      
       
        request.getSession().setAttribute("consulta", consulta);
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
        request.getSession().setAttribute("consulta", consulta);
        e.setForward("listarTelefono");
        throw e;
        }
       
        return mapping.findForward("listarTelefono");
        }
       
       
        public ActionForward registrarTelefono(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
           
        try{
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
        frm.reset(mapping, request);
        
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("registrarTelefono");
        throw e;
        }
       
        return mapping.findForward("registrarTelefono");
        }
       
       
        public ActionForward guardarTelefono(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
       
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
 
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
       	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
        
        ActionForward forward = new ActionForward();
        
        String tipoTelefonoLog = "";
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
       
       
        try{
       
        EE_I_AltaTelefonos_type input = new EE_I_AltaTelefonos_type();
        EE_O_AltaTelefonos_type output= new EE_O_AltaTelefonos_type();
       
        DatosTelefono datosTelefono = new DatosTelefono();
       
        TelefonoCliente datos = new TelefonoCliente();
       
        datosTelefono.setTelefono(frm.getTxtTelefono());
        datosTelefono.setCodOperTelefono(frm.getCmbCodOperTelefono());
        datosTelefono.setPrefTelefonoDomic(frm.getCmbPrefTelefonoDomic());
        datosTelefono.setExtTelefono(frm.getTxtExtTelefono());
        input.setCodigoDirElectronica(frm.getCmbTipoTelefono());
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        input.setDatosTelefono(datosTelefono);
       
        tipoTelefonoLog = input.getCodigoDirElectronica();
        
        SE_PE_TelefonosPortTypeProxy proxy =  generarCabeceraTelefono();
      
       
        output= proxy.alta(input);
       
       
       
        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
       
       
        forward = mapping.findForward("guardarTelefonoFijo");
        }
        else{
       
	        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
	       
	        forward = mapping.findForward("guardarTelefonoMovil");
	        }else{
	       
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
		       
		        forward = mapping.findForward("guardarTelefonoLaboral");
		        }
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
		        	
				       
			        forward = mapping.findForward("guardarTelefonoExtranjero");
			        }
	        }
       
        }
       
       
        if(output.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
	        String codErrEnt="";
	        String mensaje="";
	       
	        String codigo = ""+output.getErrores().getCodigoMostrar();
	    
	        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',5-codigo.length());
	        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
	        mensaje=output.getErrores().getMensajeMostrar();
	      
	        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
	       
        }
        else{
       
	        List tipoOperador = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_OPERADOR);
	       
	       
	        for (int j = 0; j < tipoOperador.size(); j++) {
	       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoOperador.get(j);
	       
	       
	        if(input.getDatosTelefono().getCodOperTelefono().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	       
	       
	        input.getDatosTelefono().setCodOperTelefono(dHelp_.getDescripcionDetalle());
	       
	        }
	       
        }
       
        //--
       
        List tipoTelefono = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_TELEFONO);
       
       
        for (int j = 0; j < tipoTelefono.size(); j++) {
       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoTelefono.get(j);
	       
	       
	        if(input.getCodigoDirElectronica().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	        input.setCodigoDirElectronica(dHelp_.getDescripcionDetalle());
	        }
	       
       
        }
       
        if(input.getDatosTelefono().getExtTelefono().equals("")){input.getDatosTelefono().setExtTelefono(Constante.ACTUALIZ_DATOS_NCB_DESCRIPCION);}
       
        datos.setInputAlta(input);
       
        datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
        datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
        
    
        
	        if(datos.getInputAlta().getDatosTelefono().getPrefTelefonoDomic() != null  && !datos.getInputAlta().getDatosTelefono().getPrefTelefonoDomic().equals("")){
	    		String telefonoCod = "("+datos.getInputAlta().getDatosTelefono().getPrefTelefonoDomic()+") "+datos.getInputAlta().getDatosTelefono().getTelefono();
	    		datos.getInputAlta().getDatosTelefono().setTelefono(telefonoCod);
	    		}
	    	
        
        }
       
        FacadeFactory.getAfiliacionFacade() .guardarLogTelefono(Constante.ACTUALIZ_DATOS_NCB_COD_ALTA_TELF, request.getRemoteAddr(), usuario, datos,tipoTelefonoLog, request);
        
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO,datos);
       
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("registrarTelefono");
        throw e;
        }
       
        return forward;
        }
       
       
        public ActionForward consultarTelefono(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
       
        ActionForward forward = new ActionForward();
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
 
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
       	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
       
        loadObject(request);
        EE_I_ModificacionTelefonos_type input = new
        EE_I_ModificacionTelefonos_type();
       
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
       
        try{
        frm.setCmbTipoTelefono(frm.getCmbTipoTelefono()) ;
        frm.setTxtTelefono(frm.getTxtTelefono());
        frm.setCmbCodOperTelefono(frm.getCmbCodOperTelefono());
       
        frm.setCmbPrefTelefonoDomic(frm.getCmbPrefTelefonoDomic());
        frm.setTxtExtTelefono(frm.getTxtExtTelefono());
        input.setCodigoDirElectronica(frm.getCodigoDirElectronica());
        input.setNumeroDireccion(BigInteger.valueOf(Long.parseLong(frm.getNumeroDireccion())));
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
       
        }
       
       
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("listarTelefono");
        throw e;
        }
       
        List tipoTelefono =FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_TELEFONO);
        request.getSession().setAttribute("lstTipoTelefono", tipoTelefono);
        request.getSession().setAttribute("consultaTelefono", input);
       
        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
       
        	forward = mapping.findForward("consultarTelefonoFijo");
        }
        else{
       
	        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
	       
	        forward = mapping.findForward("consultarTelefonoMovil");
	        }else{
	       
	        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
	       
	        forward = mapping.findForward("consultarTelefonoLaboral");
	        }
	        
	        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
	 	       
		        forward = mapping.findForward("consultarTelefonoExtranjero");
		        }
	        }
       
        }
       
        return (forward);
        }
       
        public ActionForward actualizarTelefono(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
       
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
 
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
       	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
       
        String tipoTelefonoLog="";
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
        EE_I_ModificacionTelefonos_type input = (EE_I_ModificacionTelefonos_type)(request.getSession().getAttribute("consultaTelefono"));
       
        EE_O_ModificacionTelefonos_type output= new EE_O_ModificacionTelefonos_type();
        
        DatosTelefono datosTelefono = new DatosTelefono();
       
        TelefonoCliente datos = new TelefonoCliente();
       
        ActionForward forward = new ActionForward();
       
        try{
       
        tipoTelefonoLog =     input.getCodigoDirElectronica();
        	  
        datosTelefono.setTelefono(frm.getTxtTelefono());
       
        datosTelefono.setCodOperTelefono(frm.getCmbCodOperTelefono());
        datosTelefono.setPrefTelefonoDomic(frm.getCmbPrefTelefonoDomic());
        datosTelefono.setExtTelefono(frm.getTxtExtTelefono());
       
        input.setDatosTelefono(datosTelefono);
      
       
        output= generarCabeceraTelefono().modificacion(input);
       
       
        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
       
       
        forward = mapping.findForward("actualizarTelefonoFijo");
        }
        else{
       
	        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
	       
	        forward = mapping.findForward("actualizarTelefonoMovil");
	        }else{
	       
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
		       
		        forward = mapping.findForward("actualizarTelefonoLaboral");
		        }
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
				       
			        forward = mapping.findForward("actualizarTelefonoLaboral");
			    }
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
				       
			        forward = mapping.findForward("actualizarTelefonoExtranjero");
			    }
	        }
       
        }
       
        if(output.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
        String codErrEnt="";
        String mensaje="";
       
        String codigo = ""+output.getErrores().getCodigoMostrar();
       
        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0', 5-codigo.length());
        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
        mensaje=output.getErrores().getMensajeMostrar();
       
       
        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
       
        }
        else{
       
        	List tipoOperador =FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_OPERADOR);
       
       
        for (int j = 0; j < tipoOperador.size(); j++) {
       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoOperador.get(j);
	       
       
	        if(input.getDatosTelefono().getCodOperTelefono().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	      
	        input.getDatosTelefono().setCodOperTelefono(dHelp_.getDescripcionDetalle());
	       
	        }
	       
        }
       
        //--
       
        List tipoTelefono = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_TELEFONO);
       
       
        for (int j = 0; j < tipoTelefono.size(); j++) {
       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoTelefono.get(j);
	       
	       
	        if(input.getCodigoDirElectronica().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	        input.setCodigoDirElectronica(dHelp_.getDescripcionDetalle());
	        }
	       
       
        }
       
        if(input.getDatosTelefono().getExtTelefono().equals("")){input.getDatosTelefono().setExtTelefono(Constante.ACTUALIZ_DATOS_NCB_DESCRIPCION);}
       
        datos.setInput(input);
       
        datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
        datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
       
        
	        if(datos.getInput().getDatosTelefono().getPrefTelefonoDomic() != null && !datos.getInput().getDatosTelefono().getPrefTelefonoDomic().equals("")){
	    		String telefonoCod = "("+datos.getInput().getDatosTelefono().getPrefTelefonoDomic()+") "+datos.getInput().getDatosTelefono().getTelefono();
	    		datos.getInput().getDatosTelefono().setTelefono(telefonoCod);
	    		}
	    	}
        
       
        FacadeFactory.getAfiliacionFacade() .guardarLogTelefono(Constante.ACTUALIZ_DATOS_NCB_COD_ACT_TELF, request.getRemoteAddr(), usuario, datos,tipoTelefonoLog, request);
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO,datos);
       
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
       
        e.setForward("consultarTelefonoFijo");
        }
        else{
       
	        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
	       
	        e.setForward("consultarTelefonoMovil");
	        }else{
	       
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
		       
		        e.setForward("consultarTelefonoLaboral");
		        }
		        if(input.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
				       
			    e.setForward("consultarTelefonoExtranjero");
			    }
	        }
       
        }
       
        throw e;
        }
       
        return forward;
        }
       
        public ActionForward eliminarTelefono(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
             
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
    	usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
    	
       
        String tipoTelefonoLog ="";
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
        DatosTelefono datosTelefono = new DatosTelefono();
       
        TelefonoCliente datos = new TelefonoCliente();
       
        ActionForward forward = new ActionForward();
       
        EE_I_BajaTelefonos_type inputBaja = new EE_I_BajaTelefonos_type();
        EE_I_ModificacionTelefonos_type input = new
        EE_I_ModificacionTelefonos_type();
       
        try{
       
        datosTelefono.setTelefono(frm.getTxtTelefono());
        datosTelefono.setCodOperTelefono(frm.getCmbCodOperTelefono());
        datosTelefono.setPrefTelefonoDomic(frm.getCmbPrefTelefonoDomic());
        datosTelefono.setExtTelefono(frm.getTxtExtTelefono());
        input.setCodigoDirElectronica(frm.getCodigoDirElectronica());
        input.setNumeroDireccion(BigInteger.valueOf(Long.parseLong(frm.getNumeroDireccion())));
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        input.setDatosTelefono(datosTelefono);
       
        inputBaja.setNumeroDireccion(input.getNumeroDireccion());
        inputBaja.setIdInternoPe(input.getIdInternoPe());
        inputBaja.setCodigoEntidad(input.getCodigoEntidad());
       
        tipoTelefonoLog = input.getCodigoDirElectronica();
        EE_O_BajaTelefonos_type output= new EE_O_BajaTelefonos_type();
        SE_PE_TelefonosPortTypeProxy proxy =  generarCabeceraTelefono();
     
        output= proxy.baja(inputBaja);
       
       
       
        if(frm.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
       
       
        forward = mapping.findForward("bajaTelefonoFijo");
        }
        else{
       
	        if(frm.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
	       
	        forward = mapping.findForward("bajaTelefonoMovil");
	        }
	        else{
	       
		        if(frm.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
		       
		        forward = mapping.findForward("bajaTelefonoLaboral");
		        }
		        if(frm.getCodigoDirElectronica().equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
				       
			        forward = mapping.findForward("bajaTelefonoExtranjero");
			   }
	        }
       
        }
       
       
        if(output.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
	        String codErrEnt="";
	        String mensaje="";
	       
	        String codigo = ""+output.getErrores().getCodigoMostrar();
	       
	        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
	        5-codigo.length());
	        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
	        mensaje=output.getErrores().getMensajeMostrar();
	       
	       
	        
	        throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
       
        }
        else{
       
	        List tipoOperador = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_OPERADOR);
	       
	       
	        for (int j = 0; j < tipoOperador.size(); j++) {
	       
		        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		       
		        dHelp_ = (DetalleAyudaDatosImpl)tipoOperador.get(j);
		       
		       
		        if(input.getDatosTelefono().getCodOperTelefono().equals(dHelp_.getCodigoDetalleAyuda()))
		        {
		       
		       
		       
		        input.getDatosTelefono().setCodOperTelefono(dHelp_.getDescripcionDetalle());
		       
		        }
	       
	        }
	       
	        //--
	       
	        List tipoTelefono =
	        FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_TELEFONO);
	       
	       
	        for (int j = 0; j < tipoTelefono.size(); j++) {
	       
		        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		       
		        dHelp_ = (DetalleAyudaDatosImpl)tipoTelefono.get(j);
		       
		       
		        if(input.getCodigoDirElectronica().equals(dHelp_.getCodigoDetalleAyuda()))
		        {
		       
		        input.setCodigoDirElectronica(dHelp_.getDescripcionDetalle());
		        }
	       
	       
	        } 
	        
	        if(input.getDatosTelefono().getExtTelefono().equals("")){input.getDatosTelefono().setExtTelefono(Constante.ACTUALIZ_DATOS_NCB_DESCRIPCION);}
	       
	       
	        datos.setInput(input);
	       
	        datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
	        datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
	        
	        if(datos.getInput().getDatosTelefono().getPrefTelefonoDomic() != null && !datos.getInput().getDatosTelefono().getPrefTelefonoDomic().equals("")){
	        	
	    		String telefonoCod = "("+datos.getInput().getDatosTelefono().getPrefTelefonoDomic()+") "+datos.getInput().getDatosTelefono().getTelefono();
	    		datos.getInput().getDatosTelefono().setTelefono(telefonoCod);
	    		}
       
        }
        
        FacadeFactory.getAfiliacionFacade() .guardarLogTelefono(Constante.ACTUALIZ_DATOS_NCB_COD_BAJA_TELF, request.getRemoteAddr(), usuario, datos,tipoTelefonoLog, request);
       
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO,datos);
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("listarTelefono");
       
        throw e;
        }
       
        return forward;
        }
       
     
       public ActionForward listarCorreo(ActionMapping mapping, ActionForm form,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

           
             BNAplicacion aplicacion = BNAplicacion.getInstance();
             Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
             UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
         	 usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
         	
             

             loadObject(request);
             AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

             request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
             request.getSession().setAttribute( "mensajeYaActualizado", ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
             EE_O_ConsultaPublicidad_type marcaPD = new EE_O_ConsultaPublicidad_type();  

             try {

                    Afiliacion datos = new AfiliacionImpl();
                    
                	marcaPD = consultarMarcaPD(form, usuarioCLDI, request);
                	
            		if(marcaPD.getIndicadorDocumentoPublicidad().toString().equals("N")){
            			frm.reset(mapping, request);
            			frm.setCmbConsentimiento(""+marcaPD.getIndicadorAutorizacionPublicidad());
            			return nuevoMarcaPD(mapping,form,request,response);
            		
            		}
             
                    EE_I_ConsultaEmails_type beanRequest= new EE_I_ConsultaEmails_type();
                    
                    beanRequest.setCodigoEntidad("0018");
                    beanRequest.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
                    //beanRequest.setIdInternoPe(Integer.parseInt("20000291"));
                    
                    EE_O_ConsultaEmails_type consulta = generarCabecera().consulta(beanRequest);

                    if (consulta.getCodigoRetorno() != Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK) {
                           String codErrEnt = "";
                           String mensaje = "";

                           String codigo = "" + consulta.getErrores().getCodigoMostrar();

                           codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',5 - codigo.length());
                           //mensaje = aplicacion.getMsjesHost("AD", codErrEnt).elementAt(2).toString();
                           mensaje=consulta.getErrores().getMensajeMostrar();
                        

                           throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase() + " (AD-" + codErrEnt + ")");

                    } else {
                           ListaEmails[] lista = consulta.getListaEmails();

                           List tipoEmail = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_EMAIL);

                           for (int i = 0; i < tipoEmail.size(); i++) {

                                  DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();

                                  dHelp_ = (DetalleAyudaDatosImpl) tipoEmail.get(i);

                           }

                           request.getSession().setAttribute("lstTipoEmail", tipoEmail);

                           consulta.setCodigoRetorno(Short.parseShort("0"));
                           consulta.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
                           consulta.setListaEmails(lista);
                    }

                    request.getSession().setAttribute("consulta",
                                  consulta.getListaEmails());

             } catch (ArrayRuleException e) {
                    e.printStackTrace();
                    log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
                    request.getSession().setAttribute("consulta","");
                    e.setForward("listarCorreo");
                    throw e;
             }

             return mapping.findForward("listarCorreo");
       }

        public ActionForward registrarCorreo(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
     
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
       
        try{
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
        frm.reset(mapping, request);
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("registrarCorreo");
        throw e;
        }
       
        return mapping.findForward("registrarCorreo");
        }
       
       
        public ActionForward guardarCorreo(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
        usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
         	
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        ActionForward forward = new ActionForward();
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
       
       
        try{
       
        EE_I_AltaEmails_type input = new EE_I_AltaEmails_type();
       
       
        CorreoCliente datos = new CorreoCliente();
       
        input.setCodigoDirElectronica(frm.getCmbTipoEmail());
        input.setDireccionElectronica(frm.getTxtDireccionElectronica().toUpperCase());
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
                   
       
        EE_O_AltaEmails_type output= new EE_O_AltaEmails_type();
       
        output=  generarCabecera().alta(input);
       
       

        if(output.getCodigoRetorno()!=  Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
        String codErrEnt="";
        String mensaje="";
       
        String codigo = ""+output.getErrores().getCodigoMostrar();
        
             
        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
        5-codigo.length());
        mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
       
       
        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
       
        }
        else{
       
        List tipoCorreo = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_EMAIL);
       
       
        for (int j = 0; j < tipoCorreo.size(); j++) {
       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoCorreo.get(j);
	       
	       
	        if(input.getCodigoDirElectronica().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	        input.setCodigoDirElectronica(dHelp_.getDescripcionDetalle());
	        }
       
       
        }
       
       
        datos.setInputAlta(input);
       
        datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
        datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
       
       
        }
       
        FacadeFactory.getAfiliacionFacade() .guardarLogEmail(Constante.ACTUALIZ_DATOS_NCB_COD_ALTA_EMAIL, request.getRemoteAddr(), usuario, datos, request);
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_CORREO,datos);
       
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("registrarCorreo");
        throw e;
        }
       
        forward = mapping.findForward("guardarCorreo");
       
        return forward;
        }
       
        public ActionForward consultarCorreo(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
       
         ActionForward forward = new ActionForward();
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
        usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
         	 
        loadObject(request);
        EE_I_ModificacionEmails_type input = new EE_I_ModificacionEmails_type();
       
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
       
        try{
        frm.setCmbTipoEmail(frm.getCmbTipoEmail()) ;
        frm.setTxtCodigoDirElectronica(frm.getTxtCodigoDirElectronica());
        frm.setTxtDireccionElectronica(frm.getTxtDireccionElectronica());
        frm.setTxtNumeroDireccion(frm.getTxtNumeroDireccion());
       
        input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        //input.setIdInternoPe(Integer.parseInt("20000291"));
        input.setCodigoDirElectronica(frm.getTxtCodigoDirElectronica());
        input.setDireccionElectronica(frm.getTxtDireccionElectronica().toUpperCase());
        input.setNumeroDireccion(BigInteger.valueOf(new
        Long(frm.getTxtNumeroDireccion())));
       
      
        }
       
       
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("listarCorreo");
        throw e;
        }
       
        List tipoTelefono = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_EMAIL);
        request.getSession().setAttribute("lstTipoEmail", tipoTelefono);
        request.getSession().setAttribute("consultaCorreo", input);
       
       
        forward = mapping.findForward("consultarCorreo");
       
       
        return (forward);
       
        }
       
        public ActionForward actualizarCorreo(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
    
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        	 
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
        EE_I_ModificacionEmails_type input = (EE_I_ModificacionEmails_type)(request.getSession().getAttribute("consultaCorreo"));
       
        CorreoCliente datos = new CorreoCliente();
       
        ActionForward forward = new ActionForward();
       
        try{
       
        input.setCodigoDirElectronica(frm.getCmbTipoEmail());
        input.setDireccionElectronica(frm.getTxtDireccionElectronica().toUpperCase());
     
       
        EE_O_ModificacionEmails_type output= new EE_O_ModificacionEmails_type();
       
        output= generarCabecera().modificacion(input);
       
       
        if(output.getCodigoRetorno()!= Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
	        String codErrEnt="";
	        String mensaje="";
	       
	        String codigo = ""+output.getErrores().getCodigoMostrar();
	       
	        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
	        5-codigo.length());
	        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
	        mensaje=output.getErrores().getMensajeMostrar();
	    
       
        throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
       
        }
        else{
        
        	List tipoCorreo = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_EMAIL);
       
       
        for (int j = 0; j < tipoCorreo.size(); j++) {
       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoCorreo.get(j);
	       
	       
	        if(input.getCodigoDirElectronica().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	        input.setCodigoDirElectronica(dHelp_.getDescripcionDetalle());
	        }
	       
       
        }
       
       
        datos.setInput(input);
       
        datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
        datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
        }
        
        FacadeFactory.getAfiliacionFacade() .guardarLogEmail(Constante.ACTUALIZ_DATOS_NCB_COD_ACT_EMAIL, request.getRemoteAddr(), usuario, datos, request);
    
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_CORREO,datos);
    
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
        e.setForward("consultarCorreo");
        throw e;
        }
       
        forward = mapping.findForward("actualizarCorreo");
       
        return forward;
        }
       
        public ActionForward eliminarCorreo(ActionMapping mapping, ActionForm
        form, HttpServletRequest request, HttpServletResponse response) throws
        Exception {
       
        BNAplicacion aplicacion = BNAplicacion.getInstance();
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
        UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
        usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
         	
       
        loadObject(request);
        AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
       
       
        CorreoCliente datos = new CorreoCliente();
       
        ActionForward forward = new ActionForward();
       
        EE_I_BajaEmails_type inputBaja = new EE_I_BajaEmails_type();
        EE_I_ModificacionEmails_type input = new EE_I_ModificacionEmails_type();
        EE_O_BajaEmails_type output= new EE_O_BajaEmails_type();
       
        try{
       
        frm.setCmbTipoEmail(frm.getCmbTipoEmail()) ;
        input.setCodigoDirElectronica(frm.getTxtCodigoDirElectronica());
        input.setDireccionElectronica(frm.getTxtDireccionElectronica().toUpperCase());
        input.setNumeroDireccion(BigInteger.valueOf(new
        Long(frm.getTxtNumeroDireccion())));
       
        inputBaja.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        inputBaja.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        //inputBaja.setIdInternoPe(Integer.parseInt("20000291"));
        inputBaja.setNumeroDireccion(input.getNumeroDireccion());
    
        output= generarCabecera().baja(inputBaja);
       
       
       
        if(output.getCodigoRetorno()!=
        Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
        String codErrEnt="";
        String mensaje="";
       
        String codigo = ""+output.getErrores().getCodigoMostrar();
       
        codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
        5-codigo.length());
        //mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
        mensaje=output.getErrores().getMensajeMostrar();
       
       
        throw new
        ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
       
        }
        else{
       
        List tipoCorreo = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_EMAIL);
       
       
        for (int j = 0; j < tipoCorreo.size(); j++) {
       
	        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	       
	        dHelp_ = (DetalleAyudaDatosImpl)tipoCorreo.get(j);
	       
	       
	        if(input.getCodigoDirElectronica().equals(dHelp_.getCodigoDetalleAyuda()))
	        {
	       
	        input.setCodigoDirElectronica(dHelp_.getDescripcionDetalle());
	        }
	       
       
        }
       
       
        datos.setInput(input);
       
        datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
        datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
       
        }
        
        FacadeFactory.getAfiliacionFacade() .guardarLogEmail(Constante.ACTUALIZ_DATOS_NCB_COD_BAJA_EMAIL, request.getRemoteAddr(), usuario, datos, request);
       
        request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_CORREO,datos);
       
       
        }
        catch (ArrayRuleException e) {
        e.printStackTrace();
        log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
       
        e.setForward("listarCorreo");
       
        throw e;
        }
       
        forward = mapping.findForward("bajaCorreo");
       
        return forward;
        }
        
        public EE_O_ConsultaPublicidad_type consultarMarcaPD(ActionForm form,UsuarioCLDI usuario,HttpServletRequest request)
                throws Exception {
         BNAplicacion aplicacion = BNAplicacion.getInstance();
      
         loadObject(request);
         AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

         request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
         request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
         loadObject(request);
         
         EE_I_ConsultaPublicidad_type input = new EE_I_ConsultaPublicidad_type();
    	 EE_O_ConsultaPublicidad_type output = new  EE_O_ConsultaPublicidad_type();
    
         try {
        	
        	 input.setIdInternoPe(Integer.parseInt(usuario.getCodigoCic()));
        	 //input.setIdInternoPe(Integer.parseInt("20000291"));
        	 
        	 input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
      
        	// Invocar Servicio Web
        	 
        	 output = generarCabeceraIndicador(request).consulta(input);
        	 
             if(output.getCodigoRetorno()!=  Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
                 String codErrEnt="";
                 String mensaje="";
                
                 String codigo = ""+output.getErrores().getCodigoMostrar();
                 
                      
                 codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
                 5-codigo.length());
                 mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
                
                
                 throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
                
                 }
                
        	 
        	
         } catch (ArrayRuleException e) {
                e.printStackTrace();
                log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

                e.setForward("verMarcaPD");
                throw e;
         }

         return output;
   }
        
        
        public ActionForward nuevoMarcaPD(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
        	
      
		 BNAplicacion aplicacion = BNAplicacion.getInstance();
		 Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
		 
		
		 AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
		 
		 
		
		 request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
		 request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
				
		  return mapping.findForward("verMarcaPD");
		}
        public ActionForward verMarcaPD(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
         BNAplicacion aplicacion = BNAplicacion.getInstance();
         
         Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
         UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
		 usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
		    	
	
        
         AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;

         request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
         request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
       
    
         try {

        	 EE_I_ConsultaPublicidad_type input = new EE_I_ConsultaPublicidad_type();
        	 
        	 input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        	 input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);

        	 EE_O_ConsultaPublicidad_type output = new  EE_O_ConsultaPublicidad_type();
        	           
        	// Invocar Servicio Web
        	 
        	 output = generarCabeceraIndicador(request).consulta(input);
        	 
             if(output.getCodigoRetorno()!=  Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
                 String codErrEnt="";
                 String mensaje="";
                
                 String codigo = ""+output.getErrores().getCodigoMostrar();
                 
                      
                 codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
                 5-codigo.length());
                 mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
                
                
                 throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
                
                 }
                 else{
                	 
                	 frm.setCmbConsentimiento(""+output.getIndicadorAutorizacionPublicidad());
                	                	 
                 }
        	 
        	
         } catch (ArrayRuleException e) {
                e.printStackTrace();
                log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

                e.setForward("verMarcaPD");
                throw e;
         }

         return mapping.findForward("verMarcaPD");
   }
        
        public ActionForward guardarMarcaPD(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
        
        
         ActionForward forward = new ActionForward();
         BNAplicacion aplicacion = BNAplicacion.getInstance();
         Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
         
         UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
		 usuarioCLDI = (UsuarioCLDI)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
		
         AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm) form;
         Afiliacion datos = new AfiliacionImpl();
         String tipoDoc = "";
		 String numDoc="";
		 String cic="";
                     
         try {
        	 
        	 T_valor_S_N indicadorMarca= null;
        	 
        	 EE_I_ModificacionPublicidad_type input = new EE_I_ModificacionPublicidad_type();
        	 
        	 input.setIdInternoPe(Integer.parseInt(usuarioCLDI.getCodigoCic()));
        	 input.setCodigoEntidad(Constante.ACTUALIZ_DATOS_CODIGO_ENTIDAD);
        	 input.setIndicadorAutorizacionPublicidad(indicadorMarca.fromString(frm.getCmbConsentimiento()));

        	 EE_O_ModificacionPublicidad_type output = new  EE_O_ModificacionPublicidad_type();
        	 
        	 output = generarCabeceraIndicador(request).modificacion(input);
        	 
             if(output.getCodigoRetorno()!=  Constante.ACTUALIZ_DATOS_NCB_RESULTADO_OK){
                 String codErrEnt="";
                 String mensaje="";
                
                 String codigo = ""+output.getErrores().getCodigoMostrar();
                 
                      
                 codErrEnt = ObjectUtil.setearCaracterLeft(codigo, '0',
                 5-codigo.length());
                 mensaje=aplicacion.getMsjesHost("AD",codErrEnt).elementAt(2).toString();
                
                
                 throw new ArrayRuleException(ConstanteError.GENERICO,mensaje.toUpperCase()+" (AD-"+codErrEnt+")");
                
                 }
                 else{
                	 
                	 frm.setCmbConsentimiento(""+output.getIndicadorAutorizacionPublicidad());
                                	 
                 }
        	 
        	 if(usuarioCLDI.getCodigoCLDI() != null && !usuarioCLDI.getCodigoCLDI().equals("")){
        		
        		 tipoDoc = ""+Integer.parseInt(usuarioCLDI.getCodigoCLDI().substring(12, 13));
        		 numDoc = ""+Long.parseLong(usuarioCLDI.getCodigoCLDI().substring(14));
        		 
        	                		 
        		List tipoDocumento = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_DOCUMENTO);
        	       
        
        		
        	    for (int j = 0; j < tipoDocumento.size(); j++) {
        	       
        		        DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
        		       
        		        dHelp_ = (DetalleAyudaDatosImpl)tipoDocumento.get(j);
        		      
        		        if(tipoDoc.equals(dHelp_.getCodigoDetalleAyuda()))
        		        {
        		        	
        		        	datos.setDescripcionTipoDocumento(dHelp_.getDescripcionDetalle());
        		        }
        	       
        	       
        	        }
        		 
        		 datos.setTipoDocumento(tipoDoc);
        		 datos.setNumDocumento(numDoc);
        		 datos.setFlagConsentimiento(frm.getCmbConsentimiento());
        		        		 
        	
        	 }
             if (frm.getCmbConsentimiento().equals(Constante.ACTUALIZ_DATOS_NCB_MARCA_LPDP_SI))
             {
            	 		forward = mapping.findForward("guardarMarcaPD");
              } else {
                        forward = mapping.findForward("guardarMarcaNoPD");
              }

             // Inserta fecha y hora
             datos.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
             datos.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
      
             FacadeFactory.getAfiliacionFacade() .guardarLogIndicador(Constante.ACTUALIZ_DATOS_NCB_COD_INDICADOR_LPDP, request.getRemoteAddr(), usuario, datos, request);
             
         } catch (ArrayRuleException e) {
                e.printStackTrace();
                log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

                e.setForward("verMarcaPD");
                throw e;
         }
         
         request.getSession().setAttribute(ConstanteSesion.ACTUALIZACION_DATOS_DOMICILIO,datos);
         return forward;

         //return mapping.findForward("guardarMarcaPD");
   }
*/
       private void loadObjectCoreActual(HttpServletRequest request) throws Exception {

        	
        	//List lista= FacadeFactory.getGeneralFacade().getComboDetalleAyudaOrden(Constante.DAT_CLIENTES_TIPO_VIA);
        	
        	String urlGenerarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_MS_LISTAS);
			MensajesTDC resultado = UtilAction.generarListas(urlGenerarClaveSmsAct+"profession/findall");
			
			if(resultado.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
				System.out.println(mensaje);
			}
			
			request.getSession().setAttribute("lstOcupacion", resultado.getDataListas());
			
			
    		request.setAttribute("lstTipoVia",FacadeFactory.getGeneralFacade().getComboDetalleAyudaOrden("00550"));
    		request.setAttribute("lstDepartamento", FacadeFactory.getGeneralFacade().getComboDepartamento());
    		request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade().getComboProvincia("00"));
    		request.setAttribute("lstDistrito", FacadeFactory.getGeneralFacade().getComboDistrito("00"));
    		request.setAttribute("lstDiscado",FacadeFactory.getGeneralFacade().getComboDetalleAyudaDiscado(Constante.DEB_AUTOMATICO_DISCADO));
    		request.setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
    		request.setAttribute("lstConsentimiento",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_FLAG_PROTECCION_DATOS));

      }
      
      
       
       
       private void loadObject(HttpServletRequest request) throws Exception {
  

             List lista = FacadeFactory.getGeneralFacade().getComboDetalleAyudaOrden(Constante.ACTUALIZ_DATOS_NCB_TIPO_VIA);

             request.setAttribute("lstTipoVia", lista);

             request.setAttribute("lstDiscado", FacadeFactory.getGeneralFacade().getComboDetalleAyudaDiscado(Constante.DEB_AUTOMATICO_DISCADO));
             request.setAttribute("lstOperador", FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_OPERADOR));
             request.setAttribute("lstTipoTelefono",FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.ACTUALIZ_DATOS_NCB_TIPO_TELEFONO));
             request.setAttribute("lstTipoEmail", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.ACTUALIZ_DATOS_NCB_TIPO_EMAIL));

             request.setAttribute("lstDepartamento",FacadeFactory.getGeneralFacade().getComboDepartamentoCore());
             request.setAttribute("lstPais",FacadeFactory.getGeneralFacade().getComboPais());
             request.setAttribute("lstProvincia",FacadeFactory.getGeneralFacade().getComboProvinciaCore("00"));
             request.setAttribute("lstDistrito", FacadeFactory.getGeneralFacade().getComboDistritoCore("00"));
             request.setAttribute("lstLocalidad",FacadeFactory.getGeneralFacade().getComboLocalidad("00"));

       }

       private AfiliacionDatosClientesForm validarCampos(
                    HttpServletRequest request, AfiliacionDatosClientesForm frm)
                    throws Exception {
    
    	   
    	    if (frm.getCbxTipoDomicilioHabitual() == null || frm.getCbxTipoDomicilioHabitual().equals(""))
    	    	frm.setCbxTipoDomicilioHabitual("N");else{
    	    	frm.setCbxTipoDomicilioHabitual("S");
             }
    	    if (frm.getCbxTipoDomicilioPostal() == null || frm.getCbxTipoDomicilioPostal().equals(""))
    	    	frm.setCbxTipoDomicilioPostal("N");else{
    	    		frm.setCbxTipoDomicilioPostal("S");	
                }
    	    if (frm.getCbxTipoDomicilioFiscal() == null || frm.getCbxTipoDomicilioFiscal().equals(""))
                frm.setCbxTipoDomicilioFiscal("N");else{
                frm.setCbxTipoDomicilioFiscal("S");	
                }
    	    

   
             if (frm.getCmbCodigoDomicilioTipoVia() == null
                           || frm.getCmbCodigoDomicilioTipoVia().equals(""))
                    frm.setCmbCodigoDomicilioTipoVia("");
             if (frm.getTxtNombreVia() == null || frm.getTxtNombreVia().equals(""))
                    frm.setTxtNombreVia("");
             if (frm.getTxtNumeroDomicilio() == null
                           || frm.getTxtNumeroDomicilio().equals(""))
                    frm.setTxtNumeroDomicilio("");
             if (frm.getTxtDomicilioPortal() == null
                           || frm.getTxtDomicilioPortal().equals(""))
                    frm.setTxtDomicilioPortal("");
             if (frm.getTxtDomicilioEscalera() == null
                           || frm.getTxtDomicilioEscalera().equals(""))
                    frm.setTxtDomicilioEscalera("");
             if (frm.getTxtDomicilioPlanta() == null
                           || frm.getTxtDomicilioPlanta().equals(""))
                    frm.setTxtDomicilioPlanta("");
             if (frm.getTxtDomicilioPuerta() == null
                           || frm.getTxtDomicilioPuerta().equals(""))
                    frm.setTxtDomicilioPuerta("");
             if (frm.getTxtBloqueDireccion() == null
                           || frm.getTxtBloqueDireccion().equals(""))
                    frm.setTxtBloqueDireccion("");
             if (frm.getTxtFaseDireccion() == null
                           || frm.getTxtFaseDireccion().equals(""))
                    frm.setTxtFaseDireccion("");
             if (frm.getTxtBarrioDireccion() == null
                           || frm.getTxtBarrioDireccion().equals(""))
                    frm.setTxtBarrioDireccion("");
             if (frm.getTxtUrbanizacionDireccion() == null
                           || frm.getTxtUrbanizacionDireccion().equals(""))
                    frm.setTxtUrbanizacionDireccion("");
             if (frm.getTxtPoligonoDireccion() == null
                           || frm.getTxtPoligonoDireccion().equals(""))
                    frm.setTxtPoligonoDireccion("");
             if (frm.getTxtParcelaDireccion() == null
                           || frm.getTxtParcelaDireccion().equals(""))
                    frm.setTxtParcelaDireccion("");
             if (frm.getTxtEdificionDireccion() == null
                           || frm.getTxtEdificionDireccion().equals(""))
                    frm.setTxtEdificionDireccion("");
             if (frm.getTxtReferencia() == null || frm.getTxtReferencia().equals(""))
                    frm.setTxtReferencia("");

             return frm;

       }

       private AfiliacionDatosClientesForm validarTipoDomicilio( HttpServletRequest request, AfiliacionDatosClientesForm frm)
               throws Exception {

	   
	    if (frm.getCbxTipoDomicilioHabitual() == null || frm.getCbxTipoDomicilioHabitual().equals(""))
	    	frm.setCbxTipoDomicilioHabitual("N");else{
	    	frm.setCbxTipoDomicilioHabitual("S");
        }
	    if (frm.getCbxTipoDomicilioPostal() == null || frm.getCbxTipoDomicilioPostal().equals(""))
	    	frm.setCbxTipoDomicilioPostal("N");else{
	    		frm.setCbxTipoDomicilioPostal("S");	
           }
	    if (frm.getCbxTipoDomicilioFiscal() == null || frm.getCbxTipoDomicilioFiscal().equals(""))
           frm.setCbxTipoDomicilioFiscal("N");else{
           frm.setCbxTipoDomicilioFiscal("S");	
           }
	    
	    return frm;

       }
       
       /*
       private String mostrarTipoDomicilio( HttpServletRequest request, DatosDomicilio datosDomicilio)
       throws Exception {

    	   String tipoDomicilio="";
    	   String tipoDomicilioHabitual="";
    	   String tipoDomicilioFiscal="";
    	   String tipoDomicilioPostal="";
    	   String separador1="/";
    	   String separador2="/";
    	   
    	   T_valor_S_N indicadorDom= null; 

    
		if (datosDomicilio.getIndicadorDomHabitual().equals(indicadorDom.fromString(Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_SI)))
			{tipoDomicilioHabitual = Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_HABITUAL;}
		
	   	if (datosDomicilio.getIndicadorDomFiscal().equals(indicadorDom.fromString(Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_SI)))
	   			
	   		{tipoDomicilioFiscal =	Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_FISCAL;}
	
		if (datosDomicilio.getIndicadorDomPostal().equals(indicadorDom.fromString(Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_SI)))
			
			{tipoDomicilioPostal = Constante.ACTUALIZ_DATOS_NCB_TIPO_DOMICILIO_POSTAL;}
		
			
		if(tipoDomicilioFiscal == ""){separador1 = "";}
		if(tipoDomicilioHabitual == ""){separador1 = "";}
		if(tipoDomicilioPostal == ""){ separador2 = "";}
	
		if(tipoDomicilioHabitual == "" && tipoDomicilioFiscal == ""){ separador2 = ""; separador1 = "";}
		if(tipoDomicilioHabitual == "" && tipoDomicilioPostal == ""){ separador2 = ""; separador1 = "";}
		if(tipoDomicilioFiscal == "" && tipoDomicilioPostal == "") {separador2 = ""; separador1 = "";}
		
	
		tipoDomicilio = tipoDomicilioHabitual +separador1+ tipoDomicilioFiscal +separador2+ tipoDomicilioPostal;
		
		return tipoDomicilio;

       }
       
       */
       private String obtenerDireccion(HttpServletRequest request,
                    AfiliacionDatosClientesForm frm) throws Exception {

             String portal = "";
             String escalera = "";
             String planta = "";
             String puerta = "";
             String bloque = "";
             String fase = "";
             String edificio = "";
             String barrio = "";
             String urbanizacion = "";
             String poligono = "";
             String parcela = "";
             String referencia ="";
             String numero ="";
             
             if (frm.getTxtNumeroDomicilio().equals("")) {
            	 numero = "";
	          } else {
	        	  numero = " Nro. ";
	          }

             if (frm.getTxtDomicilioPortal().equals("")) {
                    portal = "";
             } else {
                    portal = " Chalet ";
             }
             if (frm.getTxtDomicilioEscalera().equals("")) {
                    escalera = "";
             } else {
                    escalera = " Dpt. ";
             }
             if (frm.getTxtDomicilioPlanta().equals("")) {
                    planta = "";
             } else {
                    planta = " Piso ";
             }
             if (frm.getTxtDomicilioPuerta().equals("")) {
                    puerta = "";
             } else {
                    puerta = " Int. ";
             }
             if (frm.getTxtBloqueDireccion().equals("")) {
                    bloque = "";
             } else {
                    bloque = " Bloque ";
             }
             if (frm.getTxtFaseDireccion().equals("")) {
                    fase = "";
             } else {
                    fase = " Etapa ";
             }
             if (frm.getTxtBarrioDireccion().equals("")) {
                    barrio = "";
             } else {
                    barrio = " Barrio ";
             }
             if (frm.getTxtUrbanizacionDireccion().equals("")) {
                    urbanizacion = "";
             } else {
                    urbanizacion = " Urb. ";
             }
             if (frm.getTxtPoligonoDireccion().equals("")) {
                    poligono = "";
             } else {
                    poligono = " Mzn. ";
             }
             if (frm.getTxtParcelaDireccion().equals("")) {
                    parcela = "";
             } else {
                    parcela = " Lote ";
             }
             if (frm.getTxtEdificionDireccion().equals("")) {
                    edificio = "";
             } else {
                    edificio = " Edif. ";
             }
             
             if (frm.getTxtReferencia().equals("")) {
                 referencia = "";
          } else {
        	  referencia = " - ";
          }
             
             List tipoVia = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.ACTUALIZ_DATOS_NCB_TIPO_VIA);
             String descVia ="";
             
             for (int j = 0; j < tipoVia.size(); j++) {
            
	             DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	            
	             dHelp_ = (DetalleAyudaDatosImpl)tipoVia.get(j);
	            
	            
	             if(frm.getCmbCodigoDomicilioTipoVia().equals(dHelp_.getCodigoDetalleAyuda()))
	             {
	            
	            	 descVia = dHelp_.getDescripcionDetalle();
	            
	             }
            
            
             }

             String cadena = descVia + " "
                           + frm.getTxtNombreVia() + numero + frm.getTxtNumeroDomicilio()
                           + portal + frm.getTxtDomicilioPortal() + escalera
                           + frm.getTxtDomicilioEscalera() + planta
                           + frm.getTxtDomicilioPlanta() + puerta
                           + frm.getTxtDomicilioPuerta() + bloque
                           + frm.getTxtBloqueDireccion() + fase
                           + frm.getTxtFaseDireccion() + edificio
                           + frm.getTxtEdificionDireccion() + barrio
                           + frm.getTxtBarrioDireccion() + urbanizacion
                           + frm.getTxtUrbanizacionDireccion() + poligono
                           + frm.getTxtPoligonoDireccion() + parcela
                           + frm.getTxtParcelaDireccion() + referencia
                           +frm.getTxtReferencia();
             
             
             
//             char[] caracteres = cadena.toLowerCase().toCharArray();
//             caracteres[0] = Character.toUpperCase(caracteres[0]);
//             
//             // el -2 es para evitar una excepción al caernos del arreglo
//             for (int i = 0; i < cadena.length()- 2; i++) 
//               // Es 'palabra'
//               if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
//                 // Reemplazamos
//                 caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
// 				return new String(caracteres);
             	return cadena.toUpperCase();
            }

       private void loadObjectMedioEnvio(HttpServletRequest request)
                    throws Exception {

             request.setAttribute("lstMedioEnvio", FacadeFactory.getGeneralFacade()
                           .getComboDetalleAyuda(Constante.DAT_CLIENTES_MEDIO_ENVIO));

       }

       private Afiliacion setearAfiliacion(AfiliacionDatosClientesForm frm,
                    Tarjeta tarjeta, Usuario usuario, HttpServletRequest request)
                    throws Exception {
             Afiliacion afiliacion = new AfiliacionImpl();
             try {

                    afiliacion.setCodCliente(request.getParameter("codCliente"));
                    afiliacion.setCodDepartamento(frm.getCmbDepartamento());
                    afiliacion.setCodProvincia(frm.getCmbProvincia());
                    afiliacion.setCodDistrito(frm.getCmbDistrito());
                    afiliacion.setTipoVia(frm.getCmbTipoVia());
                    if (frm.getTxtNombreVia().trim().length() > 30) {
                           int fin = frm.getTxtNombreVia().trim().length();
                           afiliacion.setNomVia1(frm.getTxtNombreVia().trim()
                                        .substring(0, 30).toUpperCase());
                           afiliacion.setNomVia2(frm.getTxtNombreVia().trim()
                                        .substring(30, fin).toUpperCase());

                    } else {
                           afiliacion.setNomVia1(frm.getTxtNombreVia().trim()
                                        .toUpperCase());
                           afiliacion.setNomVia2("");
                    }

                    afiliacion.setNumero(frm.getTxtNumeroVia().trim());
                    //afiliacion.setBloque(frm.getTxtBloque().trim());
                    afiliacion.setBloque(frm.getTxtBloque()== null?"":frm.getTxtBloque().trim().toUpperCase());
                              
                    //afiliacion.setLote(frm.getTxtLote().trim());
                    afiliacion.setLote(frm.getTxtLote()== null?"":frm.getTxtLote().trim().toUpperCase());
                    
                    //afiliacion.setInterior(frm.getTxtInterior().trim());
                    afiliacion.setInterior(frm.getTxtInterior()== null?"":frm.getTxtInterior().trim().toUpperCase());
                    
                    //afiliacion.setManzana(frm.getTxtManzana().trim());
                    afiliacion.setManzana(frm.getTxtManzana()== null?"":frm.getTxtManzana().trim().toUpperCase());
                    
                    //afiliacion.setPiso(frm.getTxtPiso().trim());
                    afiliacion.setPiso(frm.getTxtManzana()== null?"":frm.getTxtPiso().trim().toUpperCase());

                    if (frm.getTxtReferencia().trim().length() > 30) {
                           int fin = frm.getTxtReferencia().trim().length();
                           afiliacion.setReferencia(frm.getTxtReferencia().trim()
                                        .substring(0, 30).toUpperCase());
                           afiliacion.setReferencia1(frm.getTxtReferencia().trim()
                                        .substring(30, fin).toUpperCase());

                    } else {
                           afiliacion.setReferencia(frm.getTxtReferencia().trim()
                                        .toUpperCase());
                           afiliacion.setReferencia1("");
                    }

                    afiliacion.setTelFijo(frm.getCmbZonaTelFijo()
                                  + frm.getTxtTelFijo().trim());
                    afiliacion.setTelFijoLab(frm.getCmbZonaTelFijoLab()
                                  + frm.getTxtTelFijoLab().trim());
                    afiliacion.setAnexo(frm.getTxtAnexo().trim());
                    afiliacion.setTelCelular(frm.getTxtCelular().trim());
                    afiliacion.setOperador(frm.getCmbOperador().trim());
                    afiliacion.setFlagConsentimiento(frm.getCmbConsentimiento());
                    
                    String codigoOcupacion = String.format("%15s", frm.getCmbOcupacion()).replace(" ", "0");
                    
                    String desOcupacion = "";
                    String code = "";
                    String description = "";
                    List<JSONObject> lstOcupacion = (ArrayList<JSONObject>)request.getSession().getAttribute("lstOcupacion");
                    
                    for(int i=0; i<lstOcupacion.size();i++){
                    	JSONObject ocupacion = (JSONObject)lstOcupacion.get(i);
                    	code =  ocupacion.get("code").toString();
                    	if(code.equals(frm.getCmbOcupacion())){
                    		desOcupacion = ocupacion.get("description").toString();
                    		break;                    		
                    	}
                    }
                    
                    request.getSession().setAttribute("desOcupacion", desOcupacion);
                    afiliacion.setCodOcupacion(codigoOcupacion);
                    afiliacion.setDesOcupacion(desOcupacion);

                    String correo = frm.getTxtCorreo().trim();
                    if (!correo.equals(null) && !correo.equals("")) {
                           String[] varCorreo = correo.split(Constante.SEP_ALPHANUMERIC);

                           afiliacion.setCorreoPersonal(varCorreo[0].toUpperCase());
                           afiliacion.setCorreoPersonal1(varCorreo[1].toUpperCase());
                    }
                    String adicional = frm.getTxtAdicional().trim();

                    if (!adicional.equals(null) && !adicional.equals("")) {

                           String[] varAdicional = adicional
                                        .split(Constante.SEP_ALPHANUMERIC);

                           afiliacion.setCorreoAdicional(varAdicional[0].toUpperCase());
                           afiliacion.setCorreoAdicional1(varAdicional[1].toUpperCase());

                    }

             } catch (Exception e) {
                    e.printStackTrace();
             }

             return afiliacion;
       }

       private Afiliacion setearDirCorresp(AfiliacionDatosClientesForm frm,
                    Tarjeta tarjeta, Usuario usuario, HttpServletRequest request)
                    throws Exception {
             Afiliacion afiliacion = new AfiliacionImpl();
             try {

                    afiliacion.setCodCliente(request.getParameter("codCliente"));
                    afiliacion.setCodDepartamento(frm.getCmbDepartamento());
                    afiliacion.setCodProvincia(frm.getCmbProvincia());
                    afiliacion.setCodDistrito(frm.getCmbDistrito());
                    afiliacion.setTipoVia(frm.getCmbTipoVia());
                 
                    if (frm.getTxtNombreVia().trim().length() > 30) {
                           int fin = frm.getTxtNombreVia().trim().length();
                           afiliacion.setNomVia1(frm.getTxtNombreVia().trim()
                                        .substring(0, 30).toUpperCase());
                           afiliacion.setNomVia2(frm.getTxtNombreVia().trim()
                                        .substring(30, fin).toUpperCase());

                    } else {
                           afiliacion.setNomVia1(frm.getTxtNombreVia().trim()
                                        .toUpperCase());
                           afiliacion.setNomVia2("");
                    }

                    afiliacion.setNumero(frm.getTxtNumeroVia().trim());
                                      
                    afiliacion.setBloque(frm.getTxtBloque()== null?"":frm.getTxtBloque().trim().toUpperCase());
                    
                    //afiliacion.setLote(frm.getTxtLote().trim());
                    afiliacion.setLote(frm.getTxtLote()== null?"":frm.getTxtLote().trim().toUpperCase());
                    
                    //afiliacion.setInterior(frm.getTxtInterior().trim());
                    afiliacion.setInterior(frm.getTxtInterior()== null?"":frm.getTxtInterior().trim().toUpperCase());
                    
                    //afiliacion.setManzana(frm.getTxtManzana().trim());
                    afiliacion.setManzana(frm.getTxtManzana()== null?"":frm.getTxtManzana().trim().toUpperCase());
                    
                    //afiliacion.setPiso(frm.getTxtPiso().trim());
                    afiliacion.setPiso(frm.getTxtManzana()== null?"":frm.getTxtPiso().trim().toUpperCase());
                    
                    

                    if (frm.getTxtReferencia().trim().length() > 30) {
                           int fin = frm.getTxtReferencia().trim().length();
                           afiliacion.setReferencia(frm.getTxtReferencia().trim()
                                        .substring(0, 30).toUpperCase());
                           afiliacion.setReferencia1(frm.getTxtReferencia().trim()
                                        .substring(30, fin).toUpperCase());

                    } else {
                           afiliacion.setReferencia(frm.getTxtReferencia().trim()
                                        .toUpperCase());
                           afiliacion.setReferencia1("");
                    }

             } catch (Exception e) {
                    e.printStackTrace();
             }

             return afiliacion;
       }
       
       public ActionForward generarClaveSmsEnvio(ActionMapping mapping,
   			ActionForm form, HttpServletRequest request,
   			HttpServletResponse response) throws Exception {
   		//System.out.println("ENTROOOOOOOOOOO");
   		BNAplicacion aplicacion = BNAplicacion.getInstance();
   		loadObject(request);
   		TokenSmsRequest tokenSms = new TokenSmsRequest();
   		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
   		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
   		String monto = (String) request.getSession().getAttribute("monto");
   		//Afiliacion datosUsuario = new AfiliacionImpl();
   		
   		AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
   		frm.reset(mapping, request);

   		try {

   			Afiliacion afiliacion 	= new AfiliacionImpl(); 
   			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
   			request.getSession().setAttribute("afiliacion", afiliacion);
   			request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
   			
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

   			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
   			afiliacion.setNroDocumento(numDoc);	
   			afiliacion.setCodCliente(usuario.getCodigoCic());
   			//afiliacion.setCodCliente(codCliente);

   			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
   			ParametrosTDC param = (ParametrosTDC) request.getSession()
   					.getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
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
   					
   					tokenSms.setCodCliente(afiliacion.getCodCliente());
   					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
   					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
   					
   					tokenSms.setTypeTrans(Constante.TIP_OPER_ELECCION_MEDIO_ENVIO);
   					tokenSms.setTypeOp("ADMIN");						
   					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
   					//tokenSms.setTypeCurrency("PEN");
   					
					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					if(resultado.isExito()==false){		
						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
						response.setStatus(404);
						response.getWriter().write(mensaje!=null&&!mensaje.equals("")?mensaje:"Ocurrió un problema al generar la Clave Dinámica Digital");
						response.getWriter().flush();
						response.getWriter().close();
					}
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
   		return mapping.findForward("inicio");

   	}
       
       
       public ActionForward generarClaveSmsActualizacion(ActionMapping mapping,
      			ActionForm form, HttpServletRequest request,
      			HttpServletResponse response) throws Exception {
      		//System.out.println("ENTROOOOOOOOOOO");
      		BNAplicacion aplicacion = BNAplicacion.getInstance();
      		loadObject(request);
      		TokenSmsRequest tokenSms = new TokenSmsRequest();
      		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
      		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
      		String monto = (String) request.getSession().getAttribute("monto");
      		//Afiliacion datosUsuario = new AfiliacionImpl();
      		
      		AfiliacionDatosClientesForm frm = (AfiliacionDatosClientesForm)form;
      		frm.reset(mapping, request);

      		try {

      			Afiliacion afiliacion 	= new AfiliacionImpl(); 
      			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
      			request.getSession().setAttribute("afiliacion", afiliacion);
      			request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
      			
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
      			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
      			afiliacion.setNroDocumento(numDoc);	
      			afiliacion.setCodCliente(usuario.getCodigoCic());
      			//afiliacion.setCodCliente(codCliente);

      			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
      			ParametrosTDC param = (ParametrosTDC) request.getSession()
      					.getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
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
      					
      					tokenSms.setCodCliente(afiliacion.getCodCliente());
      					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
      					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
      					
      					tokenSms.setTypeTrans(Constante.TIP_OPER_ACTUALIZACION_DATOS);
      					tokenSms.setTypeOp("ADMIN");						
      					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
      					//tokenSms.setTypeCurrency("PEN");
      					
    					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
    					
    					if(resultado.isExito()==false){		
    						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
    						response.setStatus(404);
    						response.getWriter().write(mensaje!=null&&!mensaje.equals("")?mensaje:"Ocurrió un problema al generar la Clave Dinámica Digital");
    						response.getWriter().flush();
    						response.getWriter().close();
    					}
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
      		return mapping.findForward("inicio");

      	}
   	
   	private void loadMessage(HttpServletRequest request) throws Exception{
   		BNAplicacion aplicacion = BNAplicacion.getInstance();
   		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
   		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
   		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
   	}
       
/*
       
       public SE_PE_EmailsPortTypeProxy generarCabecera (){
    	   
			try {
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
			     QName headerName = new QName("http://www.abside-product.com/empresa/SE_PE_Emails/","RSI_HeaderEmails");  // Or any other namespace that fits in your case
                   
                AtomicReference<SOAPHeaderElement> header00 
                   = new AtomicReference<SOAPHeaderElement>
                       (new SOAPHeaderElement(headerName) {                       
                     
                          @Override
                          public void setAttribute(String namespace, String localName, String value) {
                            if (!Constants.ATTR_MUST_UNDERSTAND.equals(localName)) {  // Or any other attribute name you'd want to avoid
                              super.setAttribute(namespace, localName, value);
                            }
                          }
                       });
               
                SOAPHeaderElement header =  header00.get();
        //     SOAPHeaderElement header = new SOAPHeaderElement("http://www.abside-product.com/empresa/SE_PE_Emails/","RSI_HeaderEmails");
               header.setActor(null);
               
               header.setMustUnderstand(false);

               SOAPElement node = header.addChildElement("CODSecUser", "","http://www.abside-product.com/XSD/SecurityHeader/");
               node.addTextNode(rb.getString("bn.ncb.CODSecUser"));
               node = header.addChildElement("CODSecTrans", "", "http://www.abside-product.com/XSD/SecurityHeader/");
               node.addTextNode(rb.getString("bn.ncb.CODSecTrans"));

               node = header.addChildElement("CODSecEnt", "", "http://www.abside-product.com/XSD/SecurityHeader/");
               node.addTextNode(rb.getString("bn.ncb.CODSecEnt"));
               node = header.addChildElement("CODTerminal", "","http://www.abside-product.com/XSD/SecurityHeader/");
               node.addTextNode(rb.getString("bn.ncb.CODTerminal"));
               node = header.addChildElement("CODSecIp", "","http://www.abside-product.com/XSD/SecurityHeader/");
               node.addTextNode("1.1.1.1");
               
               
               SE_PE_EmailsPortTypeProxy proxy = new SE_PE_EmailsPortTypeProxy();
               
               SE_PE_EmailsSoap11BindingStub _stub = (SE_PE_EmailsSoap11BindingStub) proxy.getSE_PE_EmailsPortType(); 
               _stub.setHeader(header);
               
               return proxy;
				
			} catch (Exception e) {
				
				throw new ArrayRuleException(ConstanteError.GENERICO,"Error al consultar sus datos");
			}
  }
       
       public SE_PE_TelefonosPortTypeProxy generarCabeceraTelefono (){
    	   
			try {
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
			     QName headerName = new QName("http://www.abside-product.com/empresa/SE_PE_Telefonos/","RSI_HeaderTelefonos");  // Or any other namespace that fits in your case
                  
               AtomicReference<SOAPHeaderElement> header00 
                  = new AtomicReference<SOAPHeaderElement>
                      (new SOAPHeaderElement(headerName) {                       
                    
                         @Override
                         public void setAttribute(String namespace, String localName, String value) {
                           if (!Constants.ATTR_MUST_UNDERSTAND.equals(localName)) {  // Or any other attribute name you'd want to avoid
                             super.setAttribute(namespace, localName, value);
                           }
                         }
                      });
              
               SOAPHeaderElement header =  header00.get();
     
              header.setActor(null);
              
              header.setMustUnderstand(false);

              SOAPElement node = header.addChildElement("CODSecUser", "","http://www.abside-product.com/XSD/SecurityHeader/");
              node.addTextNode(rb.getString("bn.ncb.CODSecUser"));
              node = header.addChildElement("CODSecTrans", "", "http://www.abside-product.com/XSD/SecurityHeader/");
              node.addTextNode(rb.getString("bn.ncb.CODSecTrans"));

              node = header.addChildElement("CODSecEnt", "", "http://www.abside-product.com/XSD/SecurityHeader/");
              node.addTextNode(rb.getString("bn.ncb.CODSecEnt"));
              node = header.addChildElement("CODTerminal", "","http://www.abside-product.com/XSD/SecurityHeader/");
              node.addTextNode(rb.getString("bn.ncb.CODTerminal"));
              node = header.addChildElement("CODSecIp", "","http://www.abside-product.com/XSD/SecurityHeader/");
              node.addTextNode("1.1.1.1");
              
              SE_PE_TelefonosPortTypeProxy proxy = new SE_PE_TelefonosPortTypeProxy();
              
         
              
              SE_PE_TelefonosSoap11BindingStub _stub = (SE_PE_TelefonosSoap11BindingStub) proxy.getSE_PE_TelefonosPortType(); 
              _stub.setHeader(header);
              
           
           
              
              return proxy;
				
			} catch (Exception e) {
				
				throw new ArrayRuleException(ConstanteError.GENERICO,"Error al consultar sus datos");
			}
       }
       
       */
     
       
//       public SE_PE_DomicilioHabitualPortTypeProxy  generarCabeceraDomicilio (HttpServletRequest request){
//    	   
//			try {
//				
//				ResourceBundle rb = ResourceBundle.getBundle("parametro");
//				
//			     QName headerName = new QName("http://www.abside-product.com/empresa/SE_PE_DomicilioHabitual/","RSI_HeaderDomicilioHabitual");  // Or any other namespace that fits in your case
//                 
//              AtomicReference<SOAPHeaderElement> header00 
//                 = new AtomicReference<SOAPHeaderElement>
//                     (new SOAPHeaderElement(headerName) {                       
//                   
//                        @Override
//                        public void setAttribute(String namespace, String localName, String value) {
//                          if (!Constants.ATTR_MUST_UNDERSTAND.equals(localName)) {  // Or any other attribute name you'd want to avoid
//                            super.setAttribute(namespace, localName, value);
//                          }
//                        }
//                     });
//             
//              SOAPHeaderElement header =  header00.get();
//    
//             header.setActor(null);
//             
//             header.setMustUnderstand(false);
//
//             SOAPElement node = header.addChildElement("CODSecUser", "","http://www.abside-product.com/XSD/SecurityHeader/");
//             node.addTextNode(rb.getString("bn.ncb.CODSecUser"));
//             node = header.addChildElement("CODSecTrans", "", "http://www.abside-product.com/XSD/SecurityHeader/");
//             node.addTextNode(rb.getString("bn.ncb.CODSecTrans"));
//
//             node = header.addChildElement("CODSecEnt", "", "http://www.abside-product.com/XSD/SecurityHeader/");
//             node.addTextNode(rb.getString("bn.ncb.CODSecEnt"));
//             node = header.addChildElement("CODTerminal", "","http://www.abside-product.com/XSD/SecurityHeader/");
//             node.addTextNode(rb.getString("bn.ncb.CODTerminal"));
//             node = header.addChildElement("CODSecIp", "","http://www.abside-product.com/XSD/SecurityHeader/");
//             node.addTextNode("1.1.1.1");
//             
//  
//             SE_PE_DomicilioHabitualPortTypeProxy proxy = new SE_PE_DomicilioHabitualPortTypeProxy();
//        
//             
//             SE_PE_DomicilioHabitualSoap11BindingStub _stub = (SE_PE_DomicilioHabitualSoap11BindingStub) proxy.getSE_PE_DomicilioHabitualPortType(); 
//             _stub.setHeader(header);
//             
//          
//          
//             
//             return proxy;
//				
//			} catch (Exception e) {
//				
//				throw new ArrayRuleException(ConstanteError.GENERICO,"Error al consultar sus datos");
//			}
//       }
       
       /*
       
       public SE_PE_DomiciliosPortTypeProxy  generarCabeceraDomiciliov1 (HttpServletRequest request){
    	   
			try {
				
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
			     QName headerName = new QName("http://www.abside-product.com/empresa/SE_PE_Domicilios/","RSI_HeaderDomicilios");  // Or any other namespace that fits in your case
                
             AtomicReference<SOAPHeaderElement> header00 
                = new AtomicReference<SOAPHeaderElement>
                    (new SOAPHeaderElement(headerName) {                       
                  
                       @Override
                       public void setAttribute(String namespace, String localName, String value) {
                         if (!Constants.ATTR_MUST_UNDERSTAND.equals(localName)) {  // Or any other attribute name you'd want to avoid
                           super.setAttribute(namespace, localName, value);
                           
                         }
                       }
                    });
            
             SOAPHeaderElement header =  header00.get();
   
            header.setActor(null);
            
            header.setMustUnderstand(false);

            SOAPElement node = header.addChildElement("CODSecUser", "","http://www.abside-product.com/XSD/SecurityHeader/");
            node.addTextNode(rb.getString("bn.ncb.CODSecUser"));
            node = header.addChildElement("CODSecTrans", "", "http://www.abside-product.com/XSD/SecurityHeader/");
            node.addTextNode(rb.getString("bn.ncb.CODSecTrans"));

            node = header.addChildElement("CODSecEnt", "", "http://www.abside-product.com/XSD/SecurityHeader/");
            node.addTextNode(rb.getString("bn.ncb.CODSecEnt"));
            node = header.addChildElement("CODTerminal", "","http://www.abside-product.com/XSD/SecurityHeader/");
            node.addTextNode("");
            node = header.addChildElement("CODSecIp", "","http://www.abside-product.com/XSD/SecurityHeader/");
            node.addTextNode("1.1.1.1");
           
           
            SE_PE_DomiciliosPortTypeProxy proxy = new SE_PE_DomiciliosPortTypeProxy();
       
            
            SE_PE_DomiciliosSoap11BindingStub _stub = (SE_PE_DomiciliosSoap11BindingStub) proxy.getSE_PE_DomiciliosPortType(); 
            _stub.setHeader(header);
            
                 
            
            return proxy;
				
			} catch (Exception e) {
				
				throw new ArrayRuleException(ConstanteError.GENERICO,"Error al consultar sus datos");
			}
      }
       
       
       public SE_PE_IndicadorPublicidadPortTypeProxy  generarCabeceraIndicador (HttpServletRequest request){
    	   
			try {
				
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
			     QName headerName = new QName("http://www.abside-product.com/empresa/SE_PE_IndicadorPublicidad/","RSI_HeaderIndicadorPublicidad");  // Or any other namespace that fits in your case
               
            AtomicReference<SOAPHeaderElement> header00  = new AtomicReference<SOAPHeaderElement>
                   (new SOAPHeaderElement(headerName) {                       
                 
                      @Override
                      public void setAttribute(String namespace, String localName, String value) {
                        if (!Constants.ATTR_MUST_UNDERSTAND.equals(localName)) {  // Or any other attribute name you'd want to avoid
                          super.setAttribute(namespace, localName, value);
                          
                        }
                      }
                   });
           
            SOAPHeaderElement header =  header00.get();
  
           header.setActor(null);
           
           header.setMustUnderstand(false);

           SOAPElement node = header.addChildElement("CODSecUser", "","http://www.abside-product.com/XSD/SecurityHeader/");
           node.addTextNode(rb.getString("bn.ncb.CODSecUser"));
           node = header.addChildElement("CODSecTrans", "", "http://www.abside-product.com/XSD/SecurityHeader/");
           node.addTextNode(rb.getString("bn.ncb.CODSecTrans"));

           node = header.addChildElement("CODSecEnt", "", "http://www.abside-product.com/XSD/SecurityHeader/");
           node.addTextNode(rb.getString("bn.ncb.CODSecEnt"));
           node = header.addChildElement("CODTerminal", "","http://www.abside-product.com/XSD/SecurityHeader/");
           node.addTextNode("");
           node = header.addChildElement("CODSecIp", "","http://www.abside-product.com/XSD/SecurityHeader/");
           node.addTextNode("1.1.1.1");
           node = header.addChildElement("CODApl", "","http://www.abside-product.com/XSD/SecurityHeader/");
           node.addTextNode("");
           
          
          
           SE_PE_IndicadorPublicidadPortTypeProxy proxy = new SE_PE_IndicadorPublicidadPortTypeProxy();
      
           
         
           SE_PE_IndicadorPublicidadSoap11BindingStub _stub = (SE_PE_IndicadorPublicidadSoap11BindingStub) proxy.getSE_PE_IndicadorPublicidadPortType();
           _stub.setHeader(header);
           
                
           
           return proxy;
				
			} catch (Exception e) {
				
				throw new ArrayRuleException(ConstanteError.GENERICO,"Error al consultar sus datos");
			}
     }
       */
}

