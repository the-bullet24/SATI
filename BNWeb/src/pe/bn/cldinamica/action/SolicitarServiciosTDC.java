package pe.bn.cldinamica.action;


import java.util.Date;

import org.jdom.JDOMException;

import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Usuario;
import JPsecuritymf.JSecuritymf;

import com.novatronic.accelerator.manager.factory.ManagerFactory;
import com.novatronic.ws.ActivarElementoSeguridadRequest;
import com.novatronic.ws.ActivarElementoSeguridadResponse;
import com.novatronic.ws.BloquearElementoSeguridadRequest;
import com.novatronic.ws.BloquearElementoSeguridadResponse;
import com.novatronic.ws.IniciarOperacionRequest;
import com.novatronic.ws.IniciarOperacionResponse;
import com.novatronic.ws.ObtenerResultadoOperacionRequest;
import com.novatronic.ws.ObtenerResultadoOperacionResponse;
import com.novatronic.ws.ServiceSixSecurityAdvanceDelegateProxy;
import com.novatronic.ws.ValidarTokenRequest;
import com.novatronic.ws.ValidarTokenResponse;
import com.novatronic.ws.VerificarExistenciaElementoSeguridadRequest;
import com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse;

public class SolicitarServiciosTDC {
	
	private static LoggerSati log3 = LoggerSati.getInstance(SolicitarServiciosTDC.class.getName());
	/**
	 * @author Mily Dolores Bustamante
	 *
	 * TODO Para cambiar la plantilla de este comentario generado, vaya a
	 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
	 * 
	 * 0000083380351000029245688

	 */
	
	
	public static ParametrosElemSegTDC verificarTipoElemento(ParametrosTDC paramTDC, Usuario usuario) throws Exception{
		
		int totalElemRel = 0;
	 	int contBloq = 0;
		int contAct = 0;
		int contReg = 0;
		int contEli = 0;
		ParametrosElemSegTDC datos = null;
		
		if(paramTDC==null){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		
		if(paramTDC.getCodObtenerBinesMA()== null){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		if(paramTDC.getCodObtenerBinesMA()!= 0){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_BIN_MEDIO_AUTENTICACIÓN);
		}
		else{
            if(paramTDC.getCodVerifExistMA() == null || paramTDC.getCodVerifExistMA()!=0){
				
						
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
				
			}
			else{
							
                if(paramTDC.getCodObtenerListaESRelac() == null || paramTDC.getCodObtenerListaESRelac()!=0){
					
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
				
				}
				else{
																	
					if(paramTDC.getElemetosRel()== null){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);	
						
					}
					else{
					
					totalElemRel = paramTDC.getElemetosRel().size();
														
					for(int x=0;x<paramTDC.getElemetosRel().size();x++){
						
						ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(x);
						
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)||paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
							
							 datos = new ParametrosElemSegTDC();
							
							datos.setCodElementoSeguridad(paramElemSeg.getCodElementoSeguridad());
							datos.setTipoElementoSeguridad(paramElemSeg.getTipoElementoSeguridad());
							datos.setEstadoElementoSeg(paramElemSeg.getEstadoElementoSeg());
							datos.setCodEstObtenerListaESRelac(paramElemSeg.getCodEstObtenerListaESRelac());
							
							String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
							
							boolean isActiveLog=Boolean.parseBoolean(flagLog);  
							
							if(isActiveLog) {
							
							System.out.println("SATI SIX_Security - CodElementoSeguridad():"+paramElemSeg.getCodElementoSeguridad());
							System.out.println("SATI SIX_Security - TipoElementoSeguridad():"+paramElemSeg.getTipoElementoSeguridad());
							System.out.println("SATI SIX_Security - EstadoElementoSeg():"+paramElemSeg.getEstadoElementoSeg());
							System.out.println("SATI SIX_Security - CodEstObtenerListaESRelac():"+paramElemSeg.getCodEstObtenerListaESRelac());
							}
							
						}
						
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_BLOQUEADO)){
							
							contBloq = contBloq +1;
							
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
							
							contAct = contAct +1;
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
							
							contReg = contReg +1;
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ELIMINADO)){
							
							contEli = contEli +1;
						}
				
					}
					
					if(contBloq == totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_BLOQUEADA);
						
					}
					
					if(contEli == totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_ELIMINADA);
						
					}
					
					if(contAct > 1){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS);
						
					}
					
					if(contReg > 0){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ACTIVAR_TARJETA_COORDENADAS);
						
					}
							
				
					}
				}
			}
		}
		
		return datos;
		
		
	}
	
	public static ParametrosElemSegTDC verificarTipoElementoAct(ParametrosTDC paramTDC) throws Exception{
		
		int totalElemRel = 0;
	 	int contBloq = 0;
		int contAct = 0;
		int contReg = 0;
		int contEli = 0;
		ParametrosElemSegTDC datos = null;
		
			
		if(paramTDC==null){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		
		if(paramTDC.getCodObtenerBinesMA()== null){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		if(paramTDC.getCodObtenerBinesMA()!= 0){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_BIN_MEDIO_AUTENTICACIÓN);
		}
		else{
            if(paramTDC.getCodVerifExistMA() == null || paramTDC.getCodVerifExistMA()!=0){
				
						
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
				
			}
			else{
							
                if(paramTDC.getCodObtenerListaESRelac() == null || paramTDC.getCodObtenerListaESRelac()!=0){            
					
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO);
				
				}
				else{
																	
					if(paramTDC.getElemetosRel()== null){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO);	
						
					}
					else{
					
					totalElemRel = paramTDC.getElemetosRel().size();
														
					for(int x=0;x<paramTDC.getElemetosRel().size();x++){
						
						ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(x);
						
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)||paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
							
							 datos = new ParametrosElemSegTDC();
							
							datos.setCodElementoSeguridad(paramElemSeg.getCodElementoSeguridad());
							datos.setTipoElementoSeguridad(paramElemSeg.getTipoElementoSeguridad());
							datos.setEstadoElementoSeg(paramElemSeg.getEstadoElementoSeg());
							datos.setCodEstObtenerListaESRelac(paramElemSeg.getCodEstObtenerListaESRelac());
							
							String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
							boolean isActiveLog=Boolean.parseBoolean(flagLog); 
							
							if(isActiveLog){
							System.out.println("SATI SIX_Security - CodElementoSeguridad():"+paramElemSeg.getCodElementoSeguridad());
							System.out.println("SATI SIX_Security - TipoElementoSeguridad():"+paramElemSeg.getTipoElementoSeguridad());
							System.out.println("SATI SIX_Security - EstadoElementoSeg():"+paramElemSeg.getEstadoElementoSeg());
							System.out.println("SATI SIX_Security - CodEstObtenerListaESRelac():"+paramElemSeg.getCodEstObtenerListaESRelac());
							}
							
							
						}
						
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_BLOQUEADO)){
							
							contBloq = contBloq +1;
							
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
							
							contAct = contAct +1;
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
							
							contReg = contReg +1;
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ELIMINADO)){
							
							contEli = contEli +1;
						}
				
					}
					
					if(contBloq == totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_BLOQUEADA);
						
					}
					
					if(contEli == totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_ELIMINADA);
						
					}
					
					if(contAct > 1){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS);
						
					}
					
					if(contReg > 0){
						
						//throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ACTIVAR_TARJETA_COORDENADAS);
						
					}
							
				
					}
				}
			}
		}
		
		return datos;
		
		
	}
	
public static ParametrosElemSegTDC verificarBloqueo(ParametrosTDC paramTDC) throws Exception{
		
		int totalElemRel = 0;
	 	int contBloq = 0;
		int contAct = 0;
		int contReg = 0;
		int contEli = 0;
		ParametrosElemSegTDC datos = null;
		
		
		if(paramTDC==null){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		if(paramTDC.getCodObtenerBinesMA()!= 0){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_BIN_MEDIO_AUTENTICACIÓN);
		}
		else{
            if(paramTDC.getCodVerifExistMA() == null || paramTDC.getCodVerifExistMA()!=0){
				
						
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
				
			}
			else{
							
                if(paramTDC.getCodObtenerListaESRelac() == null || paramTDC.getCodObtenerListaESRelac()!=0){            
					
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO);
				
				}
				else{
																	
					if(paramTDC.getElemetosRel()== null){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO);	
						
					}
					else{
					
					totalElemRel = paramTDC.getElemetosRel().size();
														
					for(int x=0;x<paramTDC.getElemetosRel().size();x++){
						
						ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(x);
						
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
							
							 datos = new ParametrosElemSegTDC();
							
							datos.setCodElementoSeguridad(paramElemSeg.getCodElementoSeguridad());
							datos.setTipoElementoSeguridad(paramElemSeg.getTipoElementoSeguridad());
							datos.setEstadoElementoSeg(paramElemSeg.getEstadoElementoSeg());
							datos.setCodEstObtenerListaESRelac(paramElemSeg.getCodEstObtenerListaESRelac());
							
					
							
						}
						
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_BLOQUEADO)){
							
							contBloq = contBloq +1;
							
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
							
							contAct = contAct +1;
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
							
							contReg = contReg +1;
						}
						if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ELIMINADO)){
							
							contEli = contEli +1;
						}
				
					}
					
									
					if(contBloq == totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_BLOQUEADA);
						
					}
					
					if(contEli == totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_ELIMINADA);
						
					}
					
					if(contAct == 1){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_YA_ACTIVADA_TARJETA_COORDENADAS);
						
					}
					
																	
				
					}
				}
			}
		}
		
		return datos;
		
		
	}
	
	
	public static ElemenSeguridad solicitCoordenada(ParametrosTDC paramTDC) throws Exception{
	
	
	    String coordenada="";
	    String coordenaMostrar = "";
	    String tipoElementoSeguridad = "";
        String identificadorTDC = "";
        ElemenSeguridad coord = new ElemenSeguridad();
    	Integer canal = 15;
        Integer transaccion = 1; 
   
		
		try {
			
			/*
			instance = new SixSecurityClientImpl();
				
		
						
						//totalElemRel = paramTDC.getElemetosRel().size();
															
						for(int x=0;x<paramTDC.getElemetosRel().size();x++){
							
							ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(x);
							
																
							if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
								
								/// OBTENER COORDENADAS
						        Integer numeroCoordenadas = 1;
						        tipoElementoSeguridad = paramElemSeg.getTipoElementoSeguridad();
						        identificadorTDC = paramElemSeg.getCodElementoSeguridad();
						    	
								//Cargar parametros
								instance.setFecha(new Date());
								instance.setCodigoComercio(Constante.CODIGO_COMERCIO);
								instance.settipoTerminal(Constante.TIPO_TERMINAL);
								instance.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
								instance.setUsuario(Constante.USUARIO);
											
								if(Constante.VER_LOG) {
									System.out.println("SATI SIX_Security - tipoElementoSeguridad:"+tipoElementoSeguridad);
									System.out.println("SATI SIX_Security - identificadorTDC:"+identificadorTDC);
									System.out.println("SATI SIX_Security - numeroCoordenadas:"+numeroCoordenadas);
								}
								
						        ConsultarCoordenadasResponse resultCoord = instance.consultarCoordenadas(Integer.parseInt(tipoElementoSeguridad),
						        		identificadorTDC, numeroCoordenadas);
						        coord.setRptaConsultCoord(resultCoord.getCodigoRespuestaPrincipal());
						        
						      			        
						        if(resultCoord.getCodigoRespuestaPrincipal()==503){
						        							       					
									
						        	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INHABILITADA_TARJETA_COORDENADAS);
						        
						        }
						        
								if(resultCoord.getCodigoRespuestaPrincipal()==512){
									
									throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TARJETA_COORDENADAS_TEMP);
								}

								if(resultCoord.getCodigoRespuestaPrincipal()==513){
																	
									throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TARJETA_COORDENADAS_PER);
								}
																
						        
						        if(resultCoord.getCodigoRespuestaPrincipal()==0){
						        	
						        coord.setRptaCoordenada(resultCoord.getListaCoordenadas());	
						        
						        String[][]vecCord=resultCoord.getListaCoordenadas();
						      				        
						        for (int j = 0; j < vecCord.length; j++) {
									for (int j2 = 0; j2 < vecCord[j].length; j2++) {
										
										coordenada = coordenada+ "-"+vecCord[j][j2];
									}
								
								}
						        String resultFin[] = coordenada.split("-");
						        //Armar coordenada
						        coordenaMostrar = resultFin[1]+" - "+resultFin[2];
						        coord.setCoordConcat(coordenaMostrar);
						        coord.setPanVirtual(resultCoord.getPanVirtual());	
						        
						        						       
						    						        
						        /// FIN OBTENER COORDENADAS
						        
						    	
								//Cargar parametros
								instance.setFecha(new Date());
								instance.setCodigoComercio(Constante.CODIGO_COMERCIO);
								instance.settipoTerminal(Constante.TIPO_TERMINAL);
								instance.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
								instance.setUsuario(Constante.USUARIO);
											
						        //Iniciar operación
						        IniciarOperacionResponse result = instance.iniciarOperacion(canal, transaccion, paramTDC.getBinMedioAutentVirtual(), paramTDC.getCodMedioAutent());
						        coord.setIdOperacion(result.getIdentificadorOperacion());
						        
						        // Validamos si el código de operación es valido
							        if(result.getIdentificadorOperacion().length() == 0 || result.getIdentificadorOperacion()== null){
							        	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INVALIDO_CODIGO_OPERACION);
							        	
							        }
						        
						        }
						        else{
						        	
						        	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS);
						        }
						       
							
							}
							
						}
								
						if(coord.getCoordConcat()== null){
							
													
							throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_CONSULTA_COORDENADAS_NO_DISPONIBLE);
						}
		*/
		} 
		catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e;
		}
		
		
		return coord;

	}
	
	public static ElemenSeguridad solicitarTokens(ParametrosTDC paramTDC) throws Exception{
		
	
	    String tipoElementoSeguridad = "";
        String identificadorTDC = "";
        ElemenSeguridad token = new ElemenSeguridad();
    	Integer canal = 15;
        Integer transaccion = 1; 
    	
		int totalElemRel = 0;
		
		try {
			ServiceSixSecurityAdvanceDelegateProxy proxy = new ServiceSixSecurityAdvanceDelegateProxy();
			
			String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
			boolean isActiveLog=Boolean.parseBoolean(flagLog); 
			if(isActiveLog)System.out.println("solicitarTokens.url.clavedinamica:"+proxy.getEndpoint());
				
			if(paramTDC.getElemetosRel()!=null){
				totalElemRel = paramTDC.getElemetosRel().size();
													
				for(int x=0;x<paramTDC.getElemetosRel().size();x++){
					
					ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(x);
					
														
					if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
						
						 tipoElementoSeguridad = paramElemSeg.getTipoElementoSeguridad();
					        identificadorTDC = paramElemSeg.getCodElementoSeguridad();
					        
					        //OBTENER ESTADO DEL ELEMENTO DE SEGURIDAD
					        //OBTENER ESTADO DEL ELEMENTO DE SEGURIDAD
					        VerificarExistenciaElementoSeguridadRequest request = new VerificarExistenciaElementoSeguridadRequest();
					        request.setCodigoPrimarioElementoSeguridad(paramElemSeg.getCodElementoSeguridad());
					        request.setTipoElementoSeguridad(Integer.parseInt(tipoElementoSeguridad));
					        request.setCodigoSecundarioElementoSeguridad(paramElemSeg.getCodElementoSeguridadSec());
					        request.setCodigoComercio(Constante.CODIGO_COMERCIO);
					        request.setTipoTerminal(Constante.TIPO_TERMINAL);
					        request.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
					        request.setUsuario(Constante.USUARIO);
			   				VerificarExistenciaElementoSeguridadResponse verificarExistES = proxy.verificarExistenciaElementoSeguridad(request);
			   				
		   								   				
		   			  if(verificarExistES.getCodigoRespuestaPrincipal() == 909){
					     	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TOKEN_CADUCADO);
					        	
					     }
		   			  
		   			 if(verificarExistES.getEstadoElementoSeguridad() == Constante.CODIGO_TDC_BLOQUEADO){
					     	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TOKEN_TEMP);
					        	
					     }
				    	
		   			
		   			 	if(isActiveLog) {
							System.out.println("SATI SIX_Security - tipoElementoSeguridad:"+tipoElementoSeguridad);
							System.out.println("SATI SIX_Security - identificadorTDC:"+identificadorTDC);
							
						}
					
						
													
						//Obtenemos el bin del Elemento de Seguridad
						String panVirtualToken = paramElemSeg.getBinES()+""+paramElemSeg.getCodElementoSeguridad()+Constante.DIGITO_CHEQUEO_TOKENS;
						
													
						token.setPanVirtualToken(paramElemSeg.getBinES());
									
						IniciarOperacionRequest request1 = new IniciarOperacionRequest();
						request1.setCanal(canal);
						request1.setBinMedioAutenticacion(paramTDC.getBinMedioAutentVirtual());
						request1.setCodigoMedioAutenticacion(paramTDC.getCodMedioAutent());
						request1.setTransaccion(transaccion);
												
				        IniciarOperacionResponse result = proxy.iniciarOperacion(request1);
	
				        
				    	if(isActiveLog) {
				    		System.out.println("SATI SIX_Security - iniciarOperacion.getCodigoRespuestaPrincipal():"+result.getCodigoRespuestaPrincipal());
							System.out.println("SATI SIX_Security - iniciarOperacion.getMensajeError():"+result.getMensajeError());
						}
				       
				        token.setIdOperacion(result.getIdentificadorOperacion());
				        
				        // Validamos si el código de operación es valido
					    if(result.getIdentificadorOperacion().length() == 0 || result.getIdentificadorOperacion()== null){
					     	//throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INVALIDO_CODIGO_OPERACION);
					        	
					     }
				        
					
					}
				}
		}
		}
	

	
		catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e;
		}
		

		return token;

	}
	public static  MensajesTDC activarTDC(ParametrosTDC paramTDC) throws Exception{
	
	
		
		MensajesTDC rptaActivar = new MensajesTDC();
		int contBloq = 0;
		int contAct = 0;
		int contEli = 0;
		int totalElemRel = 0;
	
		try {
			ServiceSixSecurityAdvanceDelegateProxy proxy = new ServiceSixSecurityAdvanceDelegateProxy();
			

			String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
			boolean isActiveLog=Boolean.parseBoolean(flagLog); 
			if(isActiveLog)System.out.println("activar.url.clavedinamica:"+proxy.getEndpoint());
		
			if(paramTDC.getCodObtenerBinesMA()!= 0){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_BIN_MEDIO_AUTENTICACIÓN);
			}
			else{
				if(paramTDC.getCodVerifExistMA()!=0){
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
					
				}
				else{
					if(paramTDC.getCodObtenerListaESRelac()!=0){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO);
						
					}
					else{
						  /**
						ActivarElementoSeguridad
						  */
						
						if(paramTDC.getElemetosRel()==null){
							
							throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
							
						}else{
						
						totalElemRel = paramTDC.getElemetosRel().size();
										
						for(int i=0;i<paramTDC.getElemetosRel().size();i++){
							
									ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(i);
												
									if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
										
										
										ActivarElementoSeguridadRequest request = new ActivarElementoSeguridadRequest();
										request.setBinMedioAutenticacion(paramTDC.getBinMedioAutentVirtual());
										request.setCodigoMedioAutenticacion( paramTDC.getCodMedioAutent());
										request.setCodigoPrimarioElementoSeguridad( paramElemSeg.getCodElementoSeguridad());
										request.setTipoElementoSeguridad(Integer.parseInt(paramElemSeg.getTipoElementoSeguridad()));
										request.setCodigoComercio(Constante.CODIGO_COMERCIO);
										request.setTipoTerminal(Constante.TIPO_TERMINAL);
										request.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
										request.setUsuario(Constante.USUARIO);
									  	ActivarElementoSeguridadResponse result = proxy.activarElementoSeguridad(request);
								      	
									  	//Setear los valores de la respuesta
									  	
								        rptaActivar.setCodRptaPrincipal(result.getCodigoRespuestaPrincipal());
								        rptaActivar.setCodAutorizacion(result.getCodigoAutorizacion());
								        rptaActivar.setCodElemSeg(paramElemSeg.getCodElementoSeguridad());
					        
								        
								        if(isActiveLog) {

								        System.out.println("SATI SIX_Security - CodigoAutorizacion():"+result.getCodigoAutorizacion());
								        System.out.println("SATI SIX_Security - CodigoRespuestaPrincipal():"+result.getCodigoRespuestaPrincipal());
								        System.out.println("SATI SIX_Security - MensajeError():"+result.getMensajeError());
								        
								        }
										        
										 if(result.getCodigoRespuestaPrincipal() == 515){
					        			        	
										        	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_VENCIO_TARJETA_COORDENADAS);
										        }
										
										    	return rptaActivar;     
									}
					
					
						
									if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_BLOQUEADO)){
										
										contBloq = contBloq +1;
										
									}

									if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ELIMINADO)){
										
										contEli = contEli +1;
										
									}
									
									if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
										
										contAct = contAct +1;
							
						}
					
					}
						
										
						if(contBloq == totalElemRel){
							throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_BLOQUEADA);
							
						}
						
						if(contEli == totalElemRel){
							throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_ELIMINADA);
							
						}
						
						if(contAct >= 1){
							throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_YA_ACTIVADA_TARJETA_COORDENADAS);
							
						}
						
					
					}
					
				}
				
			   }
			}
		
		} 
		
		catch (Exception e) {
			// TODO: handle exception
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e;
		}
		
		return rptaActivar;

	}
	
	public static MensajesTDC bloquearTDC(ParametrosTDC param, String numTDC) throws Exception{
		
	
		
		MensajesTDC rptaBloquear = null;
		try {
			String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
			boolean isActiveLog=Boolean.parseBoolean(flagLog);  	    
	   	       /**
				BloquearElementoSeguridad
			   */ 
			
			for(int i=0;i<param.getElemetosRel().size();i++){
				
				ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)param.getElemetosRel().get(i);
							
				if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)||paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
					
					if(isActiveLog){
						System.out.println("SATI SIX_Security - param.getBinMedioAutentVirtual():"+param.getBinMedioAutentVirtual());
						System.out.println("SATI SIX_Security - param.getCodMedioAutent():"+param.getCodMedioAutent());
						System.out.println("SATI SIX_Security - paramElemSeg.getTipoElementoSeguridad():"+paramElemSeg.getTipoElementoSeguridad());
						System.out.println("SATI SIX_Security - paramElemSeg.getCodElementoSeguridad():"+paramElemSeg.getCodElementoSeguridad());
						System.out.println("SATI SIX_Security - paramElemSeg.getCodElementoSeguridadSec():"+paramElemSeg.getCodElementoSeguridadSec());
						System.out.println("SATI SIX_Security - param.getTipoBloqueo():"+param.getTipoBloqueo());
					}
					
ServiceSixSecurityAdvanceDelegateProxy proxy = new ServiceSixSecurityAdvanceDelegateProxy();
			    	
			    	BloquearElementoSeguridadRequest request = new BloquearElementoSeguridadRequest();
			    	request.setBinMedioAutenticacion(param.getBinMedioAutentVirtual());
			    	request.setCodigoMedioAutenticacion(param.getCodMedioAutent());
			    	request.setCodigoPrimarioElementoSeguridad(paramElemSeg.getCodElementoSeguridad());
			    	request.setCodigoSecundarioElementoSeguridad(paramElemSeg.getCodElementoSeguridadSec());
			    	request.setTipoElementoSeguridad(Integer.parseInt(paramElemSeg.getTipoElementoSeguridad()));
			    	request.setTipoBloqueo(param.getTipoBloqueo());
			    	request.setCodigoComercio(Constante.CODIGO_COMERCIO);
				    request.setTipoTerminal(Constante.TIPO_TERMINAL);
				    request.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
				    request.setUsuario(Constante.USUARIO);
			    	
			    	
			        BloquearElementoSeguridadResponse result = proxy.bloquearElementoSeguridad(request);
			        
			        rptaBloquear.setCodAutorizacion(result.getCodigoAutorizacion());
			        rptaBloquear.setCodRptaPrincipal(result.getCodigoRespuestaPrincipal());
			        
			        
			    	if(isActiveLog){
			    	
			    		System.out.println("SATI SIX_Security - CodigoAutorizacion():"+result.getCodigoAutorizacion());
				        System.out.println("SATI SIX_Security - CodigoRespuestaPrincipal():"+result.getCodigoRespuestaPrincipal());
				        System.out.println("SATI SIX_Security - MensajeError():"+result.getMensajeError());
			    	}
			        
			      
			  }
				
			
			}
		        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_BLOQUEAR_TARJETA_COORDENADAS);
			
		
		} 
		return rptaBloquear;

	}
	public static String solicitarNumTDC(ParametrosTDC paramTDC) throws Exception{
	


	String rptaSolicitarNumTDC = "";
	int contBloq = 0;
	int contAct = 0;
	int contReg = 0;
	int contEli = 0;
	int totalElemRel = 0;
	
	
	try {
		
		if(paramTDC.getCodObtenerBinesMA()!= 0){
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_BIN_MEDIO_AUTENTICACIÓN);
		}
		else{
			if(paramTDC.getCodVerifExistMA()!=0){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
				
			}
			else{
				if(paramTDC.getCodObtenerListaESRelac()!=0){
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ELEMENTO_SEGURIDAD_RELACIONADO);
					
				}
				else{
						
					if(paramTDC.getElemetosRel()==null){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
						
					}else{
					
						totalElemRel = paramTDC.getElemetosRel().size();
									
					for(int i=0;i<paramTDC.getElemetosRel().size();i++){
						
								ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)paramTDC.getElemetosRel().get(i);
						
								if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_BLOQUEADO)){
									
									contBloq = contBloq +1;
									
								}
								if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
									
									contAct = contAct +1;
									
									rptaSolicitarNumTDC = paramElemSeg.getCodElementoSeguridad();
								}
								
								if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
									
									rptaSolicitarNumTDC = paramElemSeg.getCodElementoSeguridad();
									contReg = contReg +1;
								}
								if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ELIMINADO)){
									
									contEli = contEli +1;
								}
					
					}
					
					if(contBloq==totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_BLOQUEADA);
						
					}
					if(contEli==totalElemRel){
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_TARJETA_COORDENADAS_ELIMINADA);
						
					}
					
					if(contReg ==1 && contAct==1){
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS);
					
					}
					
					
					if(contAct >1){
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS);
					}
					
				  }
					
				}
				
			}
			
		}

	}
	
	catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw e;
	}
	
	return rptaSolicitarNumTDC;

}

	public static MensajesTDC validarTDC(ParametrosTDC param,ElemenSeguridad coord, String pinblock) throws Exception{
	

	MensajesTDC mensajes = new MensajesTDC();
	
	int totalElemRel = 0;
	String[] pinbl=null;
	int metodo = 2;
	int formato = 1;
	
	
	
	try{
	/*
		instance = new SixSecurityClientImpl();
		ValidarTDCResponse valResult = new ValidarTDCResponse();
		
        String  str_idOperacion=coord.getIdOperacion();
      
        JSecuritymf js = new JSecuritymf();
    
        
        //Obtener el Pan Virtual
        for (int i = 0; i < coord.getPanVirtual().length; i++) {
	
		String pin=pinblock;
		 
				
			    //Generar Pinblock
			
			    int rc = js.jsec_genpinblk2(metodo, ObjectUtil.setearCaracterRight(pin,'0',2), coord.getPanVirtual()[i], formato, 0, "");
						   
			    if (rc == 0) {
			    	
			    		    
			    	try{
			        String epinblock = js.getEPINBLOCK();
			       
			        pinbl = new  String[]{epinblock};
			    
			    	} catch (Exception e) {
						// TODO: handle exception
			        	e.printStackTrace();
			        	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			        	
			        	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CIFRADO);
					}
			        
			     }else {
			    	 throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CIFRADO);
			      }
	          
		}
	
               
        //Validar TDC
        
    	totalElemRel = param.getElemetosRel().size();
		for(int x=0;x<param.getElemetosRel().size();x++){
			
			ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)param.getElemetosRel().get(x);
						
			if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
							
				
				try{
					
					
					//Cargar parametros
					instance.setFecha(new Date());
					instance.setCodigoComercio(Constante.CODIGO_COMERCIO);
					instance.settipoTerminal(Constante.TIPO_TERMINAL);
					instance.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
					instance.setUsuario(Constante.USUARIO);
								
				valResult = instance.validarTDC(2, str_idOperacion, paramElemSeg.getCodElementoSeguridad(), coord.getRptaCoordenada(),pinbl);
				
				mensajes.setCodRptaPrincipal(valResult.getCodigoRespuestaPrincipal());
				
				if(Constante.VER_LOG) {
				System.out.println("***********VALIDAR TDC***********************");
				System.out.println("SATI SIX_Security - valResult.getMensajeError():"+valResult.getMensajeError());
				System.out.println("SATI SIX_Security - valResult.getCodigoRespuestaPrincipal():"+valResult.getCodigoRespuestaPrincipal());
				}
				
					
				}
				catch (Exception e) {
					e.printStackTrace();
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Validar_CLDI_TDC");
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_VALIDAR_TARJETA_COORDENADAS);
				}
				
				
				
//				if(valResult.getCodigoRespuestaPrincipal()== 500){
//					
//					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INCORRECTO_CODIGO_TARJETA_COORDENADAS);
//				}
				
				if(valResult.getCodigoRespuestaPrincipal()== 512){
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TARJETA_COORDENADAS_TEMP);
				}

				if(valResult.getCodigoRespuestaPrincipal()== 513){
				
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TARJETA_COORDENADAS_PER);
				}
			
				//resultado operacion
				ObtenerResultadoOperacionResponse valOpera=instance.obtenerResultadoOperacion(str_idOperacion);
				mensajes.setCodResultOper(""+valOpera.getResultadoValidacionOperacion());
				mensajes.setCodRptaPrincipalOper(valOpera.getCodigoRespuestaPrincipal());
				
				if(Constante.VER_LOG) {
				System.out.println("SATI SIX_Security - getResultadoValidacionOperacion():"+valOpera.getResultadoValidacionOperacion());
				System.out.println("SATI SIX_Security - getMensajeError():"+valOpera.getMensajeError());
				}
											
//				if(valOpera.getResultadoValidacionOperacion()!= 1){
//					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INVALIDO_RESULTADO_OPERACION);
//				}
							
				
			}
			
		}
		
		*/
	
	}
	catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		throw e;
	}
	

	return mensajes;

}
	
	
	
	public static MensajesTDC validarTokens(ParametrosTDC param,ElemenSeguridad coord, String pinblock) throws Exception{
		

		ValidarTokenResponse valResult = new ValidarTokenResponse();
		MensajesTDC mensajes = new MensajesTDC();
	
		int totalElemRel = 0;
		String pinbl=null;
		int metodo = 2;
		int formato = 1;
		
		
		
		try{
		

			ServiceSixSecurityAdvanceDelegateProxy proxy = new ServiceSixSecurityAdvanceDelegateProxy();
			
			String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
			boolean isActiveLog=Boolean.parseBoolean(flagLog); 
			if(isActiveLog)System.out.println("validar.url.clavedinamica:"+proxy.getEndpoint());
			//ValidarTOKENResponse valResult = new ValidarTOKENResponse();
			
	        String  str_idOperacion=coord.getIdOperacion();
	        	        	        
	        //Obtener el Pan Virtual
	      
			String pin=pinblock;
		
	               
	        //Validar TOKENS
       
	    	totalElemRel = param.getElemetosRel().size();
			for(int x=0;x<param.getElemetosRel().size();x++){
				
				ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)param.getElemetosRel().get(x);
							
				if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO)){
					if(isActiveLog) {
						System.out.println("SATI SIX_Security - paramElemSeg.getCodElementoSeguridad():"+paramElemSeg.getCodElementoSeguridad());
					}
					
					try{
						ValidarTokenRequest request = new ValidarTokenRequest();
						request.setTipoElementoSeguridad(5);
						request.setMedioSeguridad(param.getCodMedioAutent());
						request.setElementoSeguridad(paramElemSeg.getCodElementoSeguridad());
						request.setIdentificadorOperacion(str_idOperacion);
						request.setValorSeguridad(pinblock);
						request.setCodigoComercio(Constante.CODIGO_COMERCIO);
						request.setTipoTerminal(Constante.TIPO_TERMINAL);
						request.setCodigoAplicacionOrigen(Constante.CODIGO_APLICACION_ORIGEN);
						request.setUsuario(Constante.USUARIO);
						
						 valResult = proxy.validarToken(request);
										
						mensajes.setCodRptaPrincipal(valResult.getCodigoRespuestaPrincipal());
					
					
						if(isActiveLog) {
							System.out.println("***********VALIDAR TOKEN***********************");
							System.out.println("SATI SIX_Security - getCodigoRespuestaExtendido:"+valResult.getCodigoRespuestaExtendido());
							System.out.println("SATI SIX_Security - getCodigoRespuestaPrincipal:"+valResult.getCodigoRespuestaPrincipal());
							System.out.println("SATI SIX_Security - getMensajeError:"+valResult.getMensajeError());
						}
					}
					catch (Exception e) {
						e.printStackTrace();
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Validar_CLDI_Token");
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_VALIDAR_TOKEN);
					}
					
					if(valResult.getCodigoRespuestaPrincipal()== 905){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TOKEN_PER);
					}
					
					if(valResult.getCodigoRespuestaPrincipal()== 908){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_BLOQUEAR_TOKEN_TEMP);
					}
						
					
					
					
					//Resultado Operacion
					ObtenerResultadoOperacionRequest request = new ObtenerResultadoOperacionRequest();
					request.setIdentificadorOperacion(str_idOperacion);
					ObtenerResultadoOperacionResponse valOpera=proxy.obtenerResultadoOperacion(request);
					mensajes.setCodResultOper(""+valOpera.getResultadoValidacionOperacion());
					mensajes.setCodRptaPrincipalOper(valOpera.getCodigoRespuestaPrincipal());
					
					
					if(isActiveLog) {
						System.out.println("SATI SIX_Security - getResultadoValidacionOperacion():"+valOpera.getResultadoValidacionOperacion());
						System.out.println("SATI SIX_Security - getMensajeError():"+valOpera.getMensajeError());
						}
			
			
				 }
			}
		}
		catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			throw e;
		}
	
		return mensajes;

	}
	
	
	
	public static MensajesTDC idOperacion(ParametrosTDC param) throws Exception{
	
	// TODO Apéndice de método generado automáticamente
	
	MensajesTDC rptaIdOperacion = new MensajesTDC();
	Integer canal = 15;
    Integer transaccion = 1; 
	
	try {
		  //Iniciar operación
		ServiceSixSecurityAdvanceDelegateProxy proxy = new ServiceSixSecurityAdvanceDelegateProxy();
				
		
		String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
		boolean isActiveLog=Boolean.parseBoolean(flagLog); 
		if(isActiveLog)System.out.println("idOperacion.clavedinamica:"+proxy.getEndpoint());
				
		IniciarOperacionRequest iniciarOperacion = new IniciarOperacionRequest();
		iniciarOperacion.setCanal(canal);
		iniciarOperacion.setTransaccion(transaccion);
		iniciarOperacion.setBinMedioAutenticacion(param.getBinMedioAutentVirtual());
		iniciarOperacion.setCodigoMedioAutenticacion(param.getCodMedioAutent());
		IniciarOperacionResponse result = proxy.iniciarOperacion(iniciarOperacion);       
        // Validamos si el código de operación es valido
        if(result.getIdentificadorOperacion().length() == 0 || result.getIdentificadorOperacion()== null){
        	throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INVALIDO_CODIGO_OPERACION);
        	
        }
	}
	catch (Exception e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Iniciar_Operacion_CLDI");
		throw e;
	}
	
	
	return rptaIdOperacion;

}
	
	public static ElemenSeguridad solicitarTokensSms(ParametrosTDC paramTDC) throws Exception{
		ElemenSeguridad token = new ElemenSeguridad();
		
		
		return token;
	}
	
}
