package pe.bn.cldinamica.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ObjectUtil;

import pe.bn.cldinamica.domain.ParametrosCldi;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;


public class CargarParametrosTDC {
	private static LoggerSati log3 = LoggerSati.getInstance(CargarParametrosTDC.class.getName());
	/**
	 * @author Mily Dolores Bustamante
	 *
	 * TODO Para cambiar la plantilla de este comentario generado, vaya a
	 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
	 */
	public static  ParametrosTDC mostrarParamentroTDC(String codigoMA) throws Exception{
		

		//String estadoBinMedioAutenticacion = "T";
        String estadoBinMedioAutenticacion = "A";
		ParametrosTDC parametro = null;
		BNAplicacion aplicacion =BNAplicacion.getInstance();
		  
		try {
			//instance = new SixSecurityClientImpl();
			 parametro = new ParametrosTDC();
		
			/*
			 * Verificar Existencia MA: Con esto se obtiene el medio de autenticación virtual único del cliente, con el que se realizarán
			 * el resto de operaciones que impliquen manejo de elementos de seguridad.
			 */
	
//	//OJO	ObtenerListaBinesMedioAutenticacionResponse resulBines =  instance.obtenerListaBinesMedioAutenticacion(null,null,5,estadoBinMedioAutenticacion,null);
//			ObtenerListaBinesMedioAutenticacionResponse resulBines =  instance.obtenerListaBinesMedioAutenticacion(null,null,null,estadoBinMedioAutenticacion,null);
//			parametro.setCodObtenerBinesMA(resulBines.getCodigoRespuestaPrincipal());
//							
//			if(resulBines.getCodigoRespuestaPrincipal() == 0){
			 
	            //OBTENER LISTA BINES MEDIO AUTENTICACION
	            
	            List resultadoBinesMA = null;	                        
	            resultadoBinesMA=DAOFactory.getClaveDinamicaDAO().obtenerListaBinesMedioAutenticacion(estadoBinMedioAutenticacion);
	            if(resultadoBinesMA != null && resultadoBinesMA.size() > 0){
	                 parametro.setCodObtenerBinesMA(Constante.CODIGO_RESULTADO_OK);
				
				
//				String[][]lista=resulBines.getListaBinesMedioAutenticacion();
//					   	     
//	   	        for (int j = 0; j < lista.length; j++) {
//	   	        		   	        		   	       
//	   				if(lista[j][0].trim().toString().equals(Constante.CODIGO_BIN_AUTENTICACIÓN_VIRTUAL))
//	   				{	
//	   					parametro.setNomCodMedioAutentVirt(lista[j][1]);
//	   				
//	   				}
//	   			}	
//	   	      	   	        
//			}else{
	           for (int i = 0; i < resultadoBinesMA.size(); i++) {
	                     ParametrosCldi datos = new ParametrosCldi();
	                     datos = (ParametrosCldi) resultadoBinesMA.get(i);
	                     if(datos.getCodBinMA().equals(Constante.CODIGO_BIN_AUTENTICACIÓN_VIRTUAL))
	                           {    
	                               parametro.setNomCodMedioAutentVirt(datos.getNombreBinMA());
	                           }
	                 }
	            }else{
				
				return parametro;
				
			}
		
	          

	      String flagLog = Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_LOG);
	            		
	      boolean isActiveLog=Boolean.parseBoolean(flagLog); 
				
//	//OJO	VerificarExistenciaMedioAutenticacionResponse obtMAVirtual = instance.verificarExistenciaMedioAutenticacion(codigoMA.substring(0,6), codigoMA);
//			
//			VerificarExistenciaMedioAutenticacionResponse obtMAVirtual = instance.verificarExistenciaMedioAutenticacion("421410", codigoMA);
	            // VERIFICAR EXISTENCIA MEDIO AUTENTICACION
	            
//			parametro.setCodVerifExistMA(obtMAVirtual.getCodigoRespuestaPrincipal());
//		
//			if (obtMAVirtual.getCodigoRespuestaPrincipal() != 0){

	            List resultadoExistenciaMA = null;
	            
	            if(isActiveLog)System.out.println("codigoMA:"+codigoMA);
	            
	            resultadoExistenciaMA=DAOFactory.getClaveDinamicaDAO().verificarExistenciaMedioAutenticacion(Constante.CODIGO_BIN_AUTENTICACIÓN_VIRTUAL, codigoMA);
	            
	            if(resultadoExistenciaMA != null && resultadoExistenciaMA.size() > 0){
	                                
	            	
	                 parametro.setCodVerifExistMA(Constante.CODIGO_RESULTADO_OK);
				//return parametro;
	                 
	                 ParametrosCldi datos = new ParametrosCldi();

	                 datos = (ParametrosCldi) resultadoExistenciaMA.get(0);
	                 
	                 parametro.setCodMedioAutent(datos.getCodMedioAutenticacion());
	                 parametro.setEstCodMedioAutentVirt(datos.getEstadoMedioAutenticacion());
	                 parametro.setBinMedioAutentVirtual(datos.getCodBinMA());
	                 parametro.setBinMedioAutent("421410");
	                 
				
//			}
//			else{
				
//				parametro.setCodMedioAutent(codigoMA);
//				parametro.setEstCodMedioAutentVirt(obtMAVirtual.getEstadoMedioAutenticacionVirtual());
//				parametro.setBinMedioAutentVirtual(obtMAVirtual.getBinMedioAutenticacionVirtual());
//	//  OJO     parametro.setBinMedioAutent(codigoMA.substring(0,6));
//				parametro.setBinMedioAutent("421410");
		
	            }else{
	                return parametro;
			}
		
			   
//	        /// OBTENER LISTA MEDIOS RELACIONADOS
//			        
//	        Integer tipoElementoSeguridad = null;
//            
//	        ObtenerListaElementoSeguridadRelacionadosResponse resultRel = instance.obtenerListaElementoSeguridadRelacionados(obtMAVirtual.getBinMedioAutenticacionVirtual().trim(), codigoMA.trim(), tipoElementoSeguridad, null, null);
//	       	       
//	        
//	        parametro.setCodObtenerListaESRelac(resultRel.getCodigoRespuestaPrincipal());
//	     
//	      //Validar si la respuesta es correcta
//	        if(resultRel.getCodigoRespuestaPrincipal()!= 0){

	            // OBTENER LISTA MEDIOS RELACIONADOS
	            
	            List resultadoObtenerListaES = null;
	            
	            
	            resultadoObtenerListaES=DAOFactory.getClaveDinamicaDAO().obtenerListaElementoSeguridadRelacionados(Constante.CODIGO_BIN_AUTENTICACIÓN_VIRTUAL, codigoMA);
	            
	            if(resultadoObtenerListaES != null && resultadoObtenerListaES.size() > 0){
				
//				return parametro;
//	        	     	
//	        }
//	        else{
//	        	
//	        	String[][]lista=resultRel.getListaElementosSeguridad();
//	        	List elem = new ArrayList();
//	    	
//	   	        for (int j = 0; j < lista.length; j++) {
//	   	        	
//	   	        	
//	   	        	ParametrosElemSegTDC elemRel = new ParametrosElemSegTDC();
//	   	      	
//	   	         	
//	   	         	elemRel.setCodElementoSeguridad(lista[j][2].trim());
//	   				elemRel.setCodElementoSeguridadSec(lista[j][3].trim());	   				
//	   				elemRel.setCodEstObtenerListaESRelac(lista[j][4].trim());
//	   				
//	   				/***************************************************************/
//	   				elemRel.setTipoElementoSeguridad(lista[j][0].trim());
//	   				parametro.setTipoElementoSeguridad(lista[j][0].trim());
//	   				/***************************************************************/
//	   				
//	   				if(elemRel.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO) || elemRel.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
//	   					
//	   					parametro.setTipoElementoSeguridadFinal(elemRel.getTipoElementoSeguridad());
//	   					parametro.setCodEstObtenerListaESRelacFinal(elemRel.getCodEstObtenerListaESRelac());
//	   					
//	   				}
//	   				 // OBTENER LISTA DE BINES TIPO DE ELEMENTO DE SEGURIDAD
//	   				
//	   				if(!lista[j][4].trim().equals("")){
//	   					
//	   				
//	   				ObtenerListaBinesTipoElementoSeguridadResponse resulTipoEl = instance.obtenerListaBinesTipoElementoSeguridad(new Integer(lista[j][0].trim()));
//	   					
//	   				String[][]listaBines=resulTipoEl.getListaBines();
//	   			
//	   		        	   for (int x = 0; x < listaBines.length; x++) {
//	   		        		   	   		        			
//	   		        			if(!listaBines[x][0].trim().equals(""))elemRel.setBinES(listaBines[x][0].trim());
//	   		        			if(!listaBines[x][1].trim().equals(""))elemRel.setCodElementoSeguridadSec(listaBines[x][1].trim());
//	   		   					   		        		   
//	   		        			
//	   		        	   }
//	   				      
//	   				}
//	   					elem.add(elemRel);
//	   				
//	   		
//	   				parametro.setElemetosRel(elem);

	                parametro.setCodObtenerListaESRelac(Constante.CODIGO_RESULTADO_OK);
	                
	                List elem = new ArrayList();
	                
	                    for (int i = 0; i < resultadoObtenerListaES.size(); i++) {
	                     
	                     ParametrosCldi datos = new ParametrosCldi();

	                     datos = (ParametrosCldi) resultadoObtenerListaES.get(i);
	                     
	                     ParametrosElemSegTDC elemRel = new ParametrosElemSegTDC();
	                             
	                            
	                           elemRel.setCodElementoSeguridad(datos.getIdPrimarioES());
	                           elemRel.setCodElementoSeguridadSec(datos.getIdSecundarioES());
	                           elemRel.setTipoElementoSeguridad(datos.getTipoES());
	                           elemRel.setCodEstObtenerListaESRelac(datos.getEstadoES_MA());
	                           
	                           if(isActiveLog) {System.out.println("elemRel.getCodElementoSeguridad():"+elemRel.getCodElementoSeguridad());
	                           System.out.println("elemRel.getTipoElementoSeguridad():"+elemRel.getTipoElementoSeguridad());}
	                           
	                           parametro.setTipoElementoSeguridad(datos.getTipoES());	            	
//	   			}    
//	   	  
//	        }
	
	                           if(elemRel.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO) || elemRel.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)){
	                               
	                               parametro.setTipoElementoSeguridadFinal(elemRel.getTipoElementoSeguridad());
	                               parametro.setCodEstObtenerListaESRelacFinal(elemRel.getCodEstObtenerListaESRelac());
	           
	                               
	                           }
	                           
	                           elem.add(elemRel);
	                                                         
	                           parametro.setElemetosRel(elem);
	                     
	                    }
	                
	            }
	                    
	            
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log3.error(e,Constante.ERR_CARGA_PARAMETROS_TDC,"Cargar_Clave_Dinamica");
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CARGAR_PARAMETROS_TDC);
			
		
		} 
		return parametro;

	}
}
