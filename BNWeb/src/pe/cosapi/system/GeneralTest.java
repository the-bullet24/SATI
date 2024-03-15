/*
 * Fecha 13/05/2009
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.system;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.form.CardRequestSave;
import pe.bn.cldinamica.action.form.CardSaveRequest;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.pago.domain.impl.PagoPrestamoMultiredImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.pago.domain.impl.PagoUniversidadImpl;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.tcredito.domain.impl.PagoImpl;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.Refrendo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DiccionarioJournalImpl;
import pe.cosapi.domain.impl.JournalImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati05
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GeneralTest {
	private Transaction transaction;
	private Usuario usuario;
	private Map variablesGlobales; //Quité el static
	Long nroLog;
	private String nroOperacion;
	private ArrayRuleException arrayRuleException;
	private Refrendo refrendo;
	private String canal = "0";

	public void generarLog(Transaction t, Usuario usuario, String plantilla, Object cuenta) throws Exception{
	    generarLog2(t,usuario, plantilla, cuenta, null);
	}
	
	public void generarLog2(Transaction t, Usuario usuario, String plantilla, Object ope, HttpServletRequest request) throws Exception{		
	    Vector resultado = t.getMascVector();
		
		String ip	= "desconocido";
		Vector datos = t.getCuentas();
		
		for (Iterator iter = datos.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
				ip = (String) iterator.next();
				
			}
		}
		 
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;

		
		this.setTransaction(t);
		this.setUsuario(usuario);
				
		Map map = new HashMap();

		if(variablesGlobales!=null){
			map.putAll(this.getVariables());
			map.put("param",ope);
			map.put("usuario",usuario);
		}
		else{
			map.put("param",ope);
			map.put("usuario",usuario);
		}
		if (request!=null){
		    map.put("mensajeDiferenciaSaldo",request.getSession().getAttribute("mensajeDiferenciaSaldo"));
		    map.put("mensajeSaldoAhorro",request.getSession().getAttribute("mensajeSaldoAhorro"));
			map.put("fectransmb",request.getSession().getAttribute("fectransmb"));
			map.put("hortransmb",request.getSession().getAttribute("hortransmb"));
			map.put("fecpago",request.getSession().getAttribute("fecpago"));
			map.put("horpago",request.getSession().getAttribute("horpago"));
			map.put("fectransib",request.getSession().getAttribute("fectransib"));
			map.put("hortransib",request.getSession().getAttribute("hortransib"));
			map.put("mensajebloqueoinfo",request.getSession().getAttribute("mensajebloqueoinfo"));
			map.put("mensajebloqueoatte",request.getSession().getAttribute("mensajebloqueoatte"));
			map.put("tasasDetalleTributo",request.getSession().getAttribute("tasasDetalleTributo"));
		}
		map.put("MONEDA_SOL",Constante.MONEDA_SOL);
		map.put("MONEDA_DOLAR",Constante.MONEDA_DOLAR);		
		
		List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(t.getTransaction());
		
		Map parametros = new HashMap();
		for (Iterator it = lista.iterator(); it.hasNext();) {
			DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
			for (Iterator iter = resultado.iterator(); iter.hasNext();) {
				Vector elemento = (Vector) iter.next();
				if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
					parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
				}
			}			
		}
		
		
		
		
		JournalImpl journal = new JournalImpl();
		
		journal.setFlgerror(ConstanteSesion.HOST_ERROR);
		journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
		

		
		if (ConstanteSesion.HOST_ERROR.equals("0")){///////////
			if(usuario.getClaveDinamica()!= null && usuario.getClaveDinamica().getTipoElementoSeguridadFinal()!=null){
				
				
				if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					journal.setMsghst("OK:CLAVE6,TARJETA DE COORDENADAS");
				}
				else{
					if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						journal.setMsghst("OK:CLAVE6,TOKEN");
					}
				}
						
			}else{
				
				journal.setMsghst("OK:N°TARJ,CLAVE6");
			}
			
			
		}
		
		//@DPS String numLog = String.valueOf(this.nroLog); //!!!
		//String numLog = String.valueOf(((OperacionImpl)cuenta).getNroLog());
		if (ope instanceof CuentaImpl){
		    String numLog = String.valueOf(((CuentaImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
			journal.setNumope(((CuentaImpl)ope).getNroOperacion());
			journal.setMsghst(" ");
		}else if (ope instanceof PagoTasasImpl){
		    String numLog = String.valueOf(((PagoTasasImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((PagoTasasImpl)ope).getNroOperacion());	
			
		}else if (ope instanceof PagoUniversidadImpl){
		    String numLog = String.valueOf(((PagoUniversidadImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((PagoUniversidadImpl)ope).getNroOperacion());	
		}else if (ope instanceof TransferenciaImpl){
			
		    String numLog = String.valueOf(((TransferenciaImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((TransferenciaImpl)ope).getNroOperacion());		    
		}else if (ope instanceof PagoTarjetaImpl){
		    String numLog = String.valueOf(((PagoTarjetaImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((PagoTarjetaImpl)ope).getNroOperacion());		    
		}else if (ope instanceof TelegiroImpl){
		    String numLog = String.valueOf(((TelegiroImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((TelegiroImpl)ope).getNroOperacion());		
			journal.setNumref(((TelegiroImpl)ope).getNroGiro());
		}else if (ope instanceof PrestamoImpl){
		    String numLog = String.valueOf(((PrestamoImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((PrestamoImpl)ope).getNroOperacion());		    
		}else if (ope instanceof PagoImpl){
		    String numLog = String.valueOf(((PagoImpl)ope).getNroLog());
		    journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length())); 
		    journal.setNumope(((PagoImpl)ope).getNroOperacion());
		}else if (ope instanceof TarjetaImpl){
		    String numLog = String.valueOf(((TarjetaImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));    
			journal.setNumope(((TarjetaImpl)ope).getNroOperacion());
		}
		else if (ope instanceof UsuarioImpl){
		    String numLog = String.valueOf(((UsuarioImpl)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
		
//		Pago TC jrios
		}else if (t.getObjeto() instanceof PagoImpl){
			PagoImpl object = (PagoImpl) t.getObjeto();
	    String numLog = String.valueOf(((PagoImpl)ope).getNroLog());
	    
		}
//		configuracion de tarjeta
		else if (ope instanceof CardSaveRequest){
			String numLog = String.valueOf(((CardSaveRequest)ope).getNroLog());
			journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
			map.put("fecpago",((CardSaveRequest)ope).getFecha());
			map.put("horpago",((CardSaveRequest)ope).getHora());
		}

		journal.setCodtra(t.getTransaction());
		journal.setCodtrahst(t.getTransactionHost());
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setTypper(usuario.getTipoPersona());
		journal.setCipadr(ip);
		//journal.setNumope(this.getNroOperacion()); //!!!
		
		journal.setFlgcom(usuario.getTipoIngreso());
		
		ConstanteSesion.HOST_ERROR="0";
		ConstanteSesion.HOST_ERROR_MSG="";
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
			journal.setCoddoc("0001");
			journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
			journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
			if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
				journal.setNumprdsrc(usuario.getTarjeta().getNumero());
			else
				journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
		}

		try {
		    if (t.getObjeto() != null){
			    if (t.getObjeto() instanceof TransferenciaImpl) {
	                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
	                if (object.isInterbancaria()) {
	                	
	                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
	                	journal.setAmotra(importeLimite);
	                	
	                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	                	journal.setAmotxn(importeLimite);
	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
	                	
	                		String moneda=object.getSimboloMonedaImpNetoCargo().trim();
		                	
		                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
		                	    journal.setCodcur("604");
		 	                }
		 	                else{
		 	                	journal.setCodcur("840");
		 	                }
	                
	                }
	                else{
	                	 if (object.isInterbancariaLinea()) {
	                		 	                			                		 
	                		 String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
	 	                	journal.setAmotra(importeLimite);
	 	                	//log.warn("importe getAmotra......"+journal.getAmotra());
	 	                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	 	                	journal.setAmotxn(importeLimite);
	 	                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
	 	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
	 	                	//log.warn("importe journal.getBaltra......"+journal.getBaltra());
	 	                	//System.out.println("object.getCuentaOrigen().getMonedaProducto().toString():"+object.getCuentaOrigen().getMonedaProducto().toString());
	 	                	String moneda=object.getCuentaOrigen().getMonedaProducto().toString().substring(0,3);
	 	                	
	 	                	 if(moneda.equals(Constante.MONEDA_SOL)){
		 	 	                    journal.setCodcur("604");
		 	 	                }
		 	 	                else{
		 	 	                	journal.setCodcur("840");
		 	 	                }
	 	                	
	                	 }
	                	 else{
	                		 String importeLimite = ObjectUtil.replaceChar(object.getImporteNeto().toString(), ',', "");
	 	                	journal.setAmotra(importeLimite);
	 	                	
	 	                	importeLimite = ObjectUtil.replaceChar(object.getImporteCargo().toString(), ',', "");
	 	                	journal.setAmotxn(importeLimite);
	 	                	
	 	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	 	                	
	 	                	String moneda=object.getSimboloMonedaImpNetoCargo();
	 	 	                if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
	 	 	                    journal.setCodcur("604");
	 	 	                }
	 	 	                else{
	 	 	                	journal.setCodcur("840");
	 	 	                }
	 	                   	                  
	 	                }
	                 }
	                	
	            }else if (t.getObjeto() instanceof TelegiroImpl) {
	                TelegiroImpl object = (TelegiroImpl) t.getObjeto();
	                String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
	        		journal.setAmotra(importeLimite);
	        		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	        		journal.setAmotxn(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	                  journal.setNrocta(object.getCuenta().getNumeroProducto());
	                  journal.setCoddoc(object.getAfiliacion().getCodigoServicio());
	                  journal.setNumdoc(object.getAfiliacion().getNumeroServicio());
	                  journal.setNumref(object.getAgencia().getCodigo());
	                  if(journal.getCodtrahst().equals("7520")){
	                  	
	                	journal.setCodcur("604");
	                  }else{
	                	journal.setCodcur("840");
	                  }
	                  journal.setNomben(object.getAfiliacion().getBeneficiario());
	                  //journal.setBaltra(object.getTotal());
	            }
	            else if (t.getObjeto() instanceof PagoSedapalImpl) {
	            	PagoSedapalImpl object = (PagoSedapalImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getRecibo().getImporte().toString(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	             	journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getCuentaFormateada(),'-',""));
	            	journal.setNumref(object.getNroSuministro());
	            	journal.setCoddoc(object.getAfiliacion().getTipoDocumento());
	                journal.setNumdoc(object.getAfiliacion().getNroDocumento());
	            	journal.setNomben(object.getCliente());
	             	journal.setCodcur("604");
	            }		    
	            else if (t.getObjeto() instanceof PagoTasasImpl) {
	            	PagoTasasImpl object = (PagoTasasImpl) t.getObjeto();
	            	if(object.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)){
	            	    String importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	            		journal.setAmotra(importeLimite);
	            		journal.setAmotxn(importeLimite);
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setCodcur("604");
		                
	            	}
	            	else if(object.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
	            	    String importeLimite = ObjectUtil.replaceChar(object.getImporteAlCambio().toString(), ',', "");
	            		journal.setAmotra(importeLimite);
	            		journal.setAmotxn(importeLimite);
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            	    journal.setCodcur("840");
	            	}
	            	journal.setNumdoc(object.getDocumento().getNumero());
	            	journal.setCoddoc(object.getDocumento().getCodigo());
	            	journal.setNumref(object.getTributo().getCodigo());
	            	journal.setNrocta(object.getCuenta().getNumeroProducto());
	            	
	            }
	            else if (t.getObjeto() instanceof PagoUniversidadImpl) {
	            	PagoUniversidadImpl object = (PagoUniversidadImpl) t.getObjeto();
	            	
	            	if(object.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)){
	            	    String importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	            		journal.setAmotra(importeLimite);
	            		journal.setAmotxn(importeLimite);
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setCodcur("604");
	            		journal.setNrocta(object.getCuenta().getNumeroProducto());
		                
	            	}

	            	journal.setNumdoc(object.getNroDoc());
	            	journal.setCoddoc(object.getTipoDoc());
	            	journal.setCodent(object.getCodUniversidad());
	            	journal.setNumref(object.getConcepto());
	            	journal.setNomben(object.getNombreCompleto());
	            	journal.setNrocta(object.getCuenta().getNumeroProducto());
	            	
	            }
	            else if (t.getObjeto() instanceof PagoTelefonoImpl) {
	            	PagoTelefonoImpl object = (PagoTelefonoImpl) t.getObjeto();
	            	journal.setNrocta(ObjectUtil.replaceChar(object.getNumeroProducto(),'-',""));
	            	String moneda=ObjectUtil.replaceChar(object.getNumeroProducto(),'-',"").substring(0,2);
	            	if(object.getImporteSol()!=null && !object.getImporteSol().toString().trim().equals("") && object.getImporteDol()!=null && !object.getImporteDol().toString().trim().equals("")){
	            		if(moneda.equals("04") || moneda.equals("00")){
	            			journal.setAmotra(ObjectUtil.replaceChar(object.getImporteSol().toString(), ',', ""));
	            			journal.setAmotxn(ObjectUtil.replaceChar(object.getImporteSol().toString(), ',', ""));
	            			journal.setBaltra(ObjectUtil.replaceChar(object.getImporteDol().toString(), ',', ""));
	            			//log.warn("dolares............"+journal.getAmotra());
	            			//log.warn("soles............"+journal.getBaltra());
	            			journal.setCodcur("604");
	            		}
	            	
	            		else if(moneda.equals("06") || moneda.equals("08")){
	            			journal.setAmotra(ObjectUtil.replaceChar(object.getImporteDol().toString(), ',', ""));
	            			journal.setAmotxn(ObjectUtil.replaceChar(object.getImporteDol().toString(), ',', ""));
	            			journal.setBaltra(ObjectUtil.replaceChar(object.getImporteSol().toString(), ',', ""));
	            			//log.warn("dolares d............"+journal.getBaltra());
	            			//log.warn("soles d............"+journal.getAmotra());
	            			 journal.setCodcur("840");
	            		}
	            	}
	            	else{
	            		journal.setAmotra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setAmotxn(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	            		//log.warn("misma moneda............"+journal.getAmotra());
	            		 journal.setCodcur(object.getCodMoneda());
	            	}
	            	
	                journal.setNumref(object.getAfiliacion().getNumeroServicio());
	                journal.setCoddoc(object.getAfiliacion().getTipoDocumento());
	                journal.setNumdoc(object.getAfiliacion().getNroDocumento());
	                journal.setNomben(object.getNomAbonado());
	               
	                
	               
	            }
	            else if (t.getObjeto() instanceof PagoPrestamoMultiredImpl) {
	            	PagoPrestamoMultiredImpl object = (PagoPrestamoMultiredImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setBaltra(importeLimite);
	                journal.setAmotxn(importeLimite);
	                journal.setNumref(object.getNroPrestamo());
	                journal.setNrocta(ObjectUtil.replaceChar(object.getCuenta().getNumeroProducto(),'-',""));
	                String moneda=journal.getNrocta().substring(0,2);
	                if(moneda.equals("04") || moneda.equals("00")){
	                journal.setCodcur("604");
	                }
	                else{
	                	journal.setCodcur("840");
	                }
	            }
	            else if (t.getObjeto() instanceof PagoTarjetaImpl) {
	            	PagoTarjetaImpl object = (PagoTarjetaImpl) t.getObjeto();
	            	String importeLimite = ObjectUtil.replaceChar(object.getTotal(), ',', "");
	                journal.setAmotra(importeLimite);
	                journal.setAmotxn(ObjectUtil.replaceChar(object.getImporteAlCambio().toString(), ',', ""));
	                journal.setBaltra(ObjectUtil.replaceChar(object.getImporte().toString(), ',', ""));
	                journal.setNrocta(object.getCuenta().getNumeroProducto());
	                journal.setNumprdtar(object.getAfiliacion().getNumeroServicio());
	                if (object.getCuenta().getMonedaProducto().trim().equals("PEI") ){
	                    journal.setCodcur("604");
	                } else {
	                    journal.setCodcur("840");
	                }
	                
	            }else if (t.getObjeto() instanceof PrestamoImpl) {
	            	System.out.println("entro prestamo");
	            	PrestamoImpl object = (PrestamoImpl) t.getObjeto();
	                String cCtaAct = ObjectUtil.formatearCuenta(object.getCuenta(),Constante.FORMATO_CUENTA);
	                journal.setNrocta(cCtaAct);
	                String importeLimite = ObjectUtil.replaceChar(object.getMontoPrestamo().toString(), ',', "");
	            	journal.setAmotra(importeLimite);
	            	journal.setAmotxn(importeLimite);
	            	journal.setBaltra(ObjectUtil.replaceChar(object.getMontoPrestamo().toString(), ',', ""));
	            	journal.setCodcur("604");
	            	journal.setNumref(object.getNroPrestamo());
	            		            
	            }
			    
			    else if (t.getObjeto() instanceof PagoImpl){
					PagoImpl object = (PagoImpl) t.getObjeto();
			    String numLog = String.valueOf(((PagoImpl)ope).getNroLog());
				journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));
				journal.setAmotra(object.getImporteNeto());
				
				journal.setBaltra(ObjectUtil.replaceChar(object.getMontoPagado().toString(), ',', ""));
				journal.setAmotxn(ObjectUtil.replaceChar(object.getMontoPagado().toString(), ',', ""));
				journal.setNrocta(object.getCuentaCargada().getNumeroProducto());
		    	journal.setNumref(object.getCuentaCredito().getNumeroProducto());
		    	journal.setCodcur("604");
				}
	            else if (t.getObjeto() instanceof CuentaImpl) {
	            	CuentaImpl object = (CuentaImpl) t.getObjeto();
	            	if(object.getNroCuentaCci()!=null){
	            		journal.setNumprdtar(object.getNroCuentaCci());
	            	}
	                //journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
	            	journal.setNrocta(object.getCuentaFormateada());
	                journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
	            }
	            else if (t.getObjeto() instanceof TarjetaImpl) {
	            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
	            	journal.setNumref(object.getCodigo());
	            }
	            else if (t.getObjeto() instanceof UsuarioImpl) {
	            	System.out.println("UsuarioImpl>>>>>>>>>>>");
	            }
	            
			}
	    } catch (Exception e) {}
		
		
		
		if(this.getArrayRuleException()!=null)
			journal.setMsgerror("");
		
		if(plantilla!=null&&this.getArrayRuleException()==null){
			this.setRefrendo(new Refrendo(plantilla));
			if (t.getObjeto() instanceof PagoTelefonoImpl){
				map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
			}
			this.getRefrendo().procesar(map);
			journal.setConstancia(this.getRefrendo().getResultado());
		}
		
		Class cls = journal.getClass(); 
		
		
		//Datos que vienen de la configuracion del journal
		boolean existsNroCta = false;
		for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
		    String key = (String)iter.next();
		    Object obj = parametros.get(key);
		    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
		    if(obj!=null){
			    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
				method.invoke(journal, new Object[]{obj});
		    }
		    if (metodo.equals("setNrocta")){
		        existsNroCta=true;
		    }
		}
		String codTraHost = t.getTransactionHost().trim();
		if (!existsNroCta){
		    if (!codTraHost.equals("0112") && !codTraHost.equals("1162") && !codTraHost.equals("1116") && !codTraHost.equals("0132")){
		        System.out.println("GenerarLog: Trx " + codTraHost +" no tiene cta definida en Journal");
		    }
		}
		
		journal.setFlgcha(this.canal);
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		//journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
		//Constante.WAP_CTA_CTE="";
		//Constante.WAP_DNI_CTA_CTE="";
		if(codTraHost.equals("0123") || codTraHost.equals("0113") ||  codTraHost.equals("0112") ||  codTraHost.equals("0132")){
			
			BNAplicacion aplicacion =BNAplicacion.getInstance();
			
			if(Constante.CONSTANTE_EXPIRO.equals(usuario.getNotificacionClave().getEstadoExpira())){
				String mensaje=aplicacion.getMsjesHost(usuario.getNotificacionClave().getSignoError(),usuario.getNotificacionClave().getCodigoError()).elementAt(2).toString();
				
				System.out.println("mensaje::"+mensaje);
				
				journal.setMsghst("("+usuario.getNotificacionClave().getSignoError()+"-"+usuario.getNotificacionClave().getCodigoError()+")"+" "+mensaje);
				journal.setFlgerror(ConstanteSesion.FLAG_ERROR);
			
			}else{
				
				journal.setNrocta("");
				journal.setMsghst("OK:N°TARJ,CLAVE6");
				
			}
			
		}

		
		else if(codTraHost.equals("1162")){
		    List listaCuentas = usuario.getCuentas();
		    CuentaImpl cuenta = null;
		    String cuentaAHMN = "";
			for (Iterator iter = listaCuentas.iterator(); iter.hasNext();) {
				cuenta = (CuentaImpl) iter.next();
				if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				    cuentaAHMN = cuenta.getNumeroProducto();
					break;
				}
			}
		    journal.setNrocta(cuentaAHMN);
		}
		if( t.getTransaction().trim().equals("LG02") ){
			journal.setNrocta("");
			journal.setMsghst("OK:N°TARJ,CLAVE6");//New Message//
		}

		
		ConstanteSesion.WAP_NUM_TAR="";
		ConstanteSesion.FLAG_ESTADO_LOG=journal.getFlgerror();
		ConstanteSesion.CONSTANCIA_LOG=journal.getConstancia();
		
		DAOFactory.getSaraWebLogImpl().actualizarJournal(journal);

		}
	
	
	public void generarLog3(Transaction t, Usuario usuario, String plantilla, Object ope, HttpServletRequest request) throws Exception{		
	    
		
		Vector resultado = t.getMascVector();
		
		String ip	= "desconocido";
		Vector datos = t.getCuentas();
		
		for (Iterator iter = datos.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
				ip = (String) iterator.next();
				
			}
		}
				
		this.setTransaction(t);
		this.setUsuario(usuario);
				
		Map map = new HashMap();

		if(variablesGlobales!=null){
			map.putAll(this.getVariables());
			map.put("param",ope);
			map.put("usuario",usuario);
		}
		else{
			map.put("param",ope);
			map.put("usuario",usuario);
		}

			
		List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(t.getTransaction());
		
		Map parametros = new HashMap();
		for (Iterator it = lista.iterator(); it.hasNext();) {
			DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
			for (Iterator iter = resultado.iterator(); iter.hasNext();) {
				Vector elemento = (Vector) iter.next();
				if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
					parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
				}
			}			
		}
		
		JournalImpl journal = new JournalImpl();
		
		journal.setFlgerror(ConstanteSesion.HOST_ERROR);
		journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
		
		nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
		String numLog = String.valueOf(nroLog);
		t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
		journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));

//		Seteamos la fecha
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("dd/MM/yyyy");			
		Date data = new Date(fechaActual.getTime());
		String fecha = formatter.format(data);
		
		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		String hora = formatter.format(data);
		
		
		journal.setDatpro(fechaActual);
		journal.setHorpro(hora);
		journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
		journal.setNomben(usuario.getNombre());
		
		//@DPS String numLog = String.valueOf(this.nroLog); //!!!
		//String numLog = String.valueOf(((OperacionImpl)cuenta).getNroLog());
		if (ope instanceof CuentaImpl){
		  
			journal.setNumope(((CuentaImpl)ope).getNroOperacion());
			journal.setMsghst(" ");
			    
		}else if (ope instanceof TarjetaImpl){
		    
			journal.setNumope(((TarjetaImpl)ope).getNroOperacion());
		}else if (ope instanceof UsuarioImpl){
		   
		}
		journal.setCodtra(t.getTransaction());
		journal.setCodtrahst(t.getTransactionHost());
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setTypper(usuario.getTipoPersona());
		journal.setCipadr(ip);
		//journal.setNumope(this.getNroOperacion()); //!!!
		
		journal.setFlgcom(usuario.getTipoIngreso());
		
		ConstanteSesion.HOST_ERROR="0";
		ConstanteSesion.HOST_ERROR_MSG="";
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
			journal.setCoddoc("0001");
			journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
			journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
			if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
				journal.setNumprdsrc(usuario.getTarjeta().getNumero());
			else
				journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
		}

		try {
		    if (t.getObjeto() != null){
			    if (t.getObjeto() instanceof TransferenciaImpl) {
	                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
	                if (object.isInterbancaria()) {

	                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
	                	journal.setAmotra(importeLimite);
	                	//log.warn("importe getAmotra......"+journal.getAmotra());
	                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	                	journal.setAmotxn(importeLimite);
	                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
	                
	                	String moneda=object.getSimboloMonedaImpNetoCargo().trim();
	                	
	                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
	                	    journal.setCodcur("604");
	 	                }
	 	                else{
	 	                	journal.setCodcur("840");
	 	                }
	                	
	                }
			    } 
	            else if (t.getObjeto() instanceof TarjetaImpl) {
	            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
	            	journal.setNumref(object.getCodigo());
	              	            
		      } else if (t.getObjeto() instanceof TransferenciaContactoImpl) {
		    	  TransferenciaContactoImpl object = (TransferenciaContactoImpl) t.getObjeto();
	           	            	
	                journal.setNrocta(ObjectUtil.replaceChar(object.getCuentaOrigen().getCuentaFormateada(),'-',""));
	            	
	            }
	            
			}
	    } catch (Exception e) {}
		
		
		
		if(this.getArrayRuleException()!=null)
			journal.setMsgerror("");
		
		if(plantilla!=null&&this.getArrayRuleException()==null){
			this.setRefrendo(new Refrendo(plantilla));
			if (t.getObjeto() instanceof PagoTelefonoImpl){
				map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
			}
			this.getRefrendo().procesar(map);
			journal.setConstancia(this.getRefrendo().getResultado());
		}
		
		Class cls = journal.getClass(); 
		//Datos que vienen de la configuracion del journal
		boolean existsNroCta = false;
		for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
		    String key = (String)iter.next();
		    Object obj = parametros.get(key);
		    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
		    if(obj!=null){
			    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
				method.invoke(journal, new Object[]{obj});
		    }
		    if (metodo.equals("setNrocta")){
		        existsNroCta=true;
		    }
		}
	
		
		journal.setFlgcha(this.canal);
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setMsghst("OK:N°TARJ,CLAVE4");
	
		if (ConstanteSesion.HOST_ERROR.equals("0")){///////////
			
			
			if(usuario.getClaveDinamica()!= null && usuario.getClaveDinamica().getTipoElementoSeguridadFinal()!=null){
				
				if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					journal.setMsghst("OK:CLAVE6,TARJETA DE COORDENADAS");
				}
				else{
					if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
					
						journal.setMsghst("OK:CLAVE6,TOKEN");
					}
					
					if (usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
					{
					
						journal.setMsghst("OK:CLAVE6,CLAVE SMS");
					}
				}
			}
			
		}
		
		
		ConstanteSesion.WAP_NUM_TAR="";
		ConstanteSesion.FLAG_ESTADO_LOG=journal.getFlgerror();
		ConstanteSesion.CONSTANCIA_LOG=journal.getConstancia();
		DAOFactory.getSaraWebLogImpl().insertarJournal1(journal);

		}
	
public void generarLog4(Transaction t, Usuario usuario, String plantilla, Object ope, HttpServletRequest request) throws Exception{		
	    
		
		Vector resultado = t.getMascVector();
		
		String ip	= "desconocido";
		Vector datos = t.getCuentas();
		
		for (Iterator iter = datos.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
				ip = (String) iterator.next();
				
			}
		}
				
		this.setTransaction(t);
		this.setUsuario(usuario);
				
		Map map = new HashMap();

		if(variablesGlobales!=null){
			map.putAll(this.getVariables());
			map.put("param",ope);
			map.put("usuario",usuario);
		}
		else{
			map.put("param",ope);
			map.put("usuario",usuario);
		}

			
		List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(t.getTransaction());
		
		Map parametros = new HashMap();
		for (Iterator it = lista.iterator(); it.hasNext();) {
			DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
			for (Iterator iter = resultado.iterator(); iter.hasNext();) {
				Vector elemento = (Vector) iter.next();
				if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
					parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
				}
			}			
		}
		
		JournalImpl journal = new JournalImpl();
		
		journal.setFlgerror(ConstanteSesion.HOST_ERROR);
		journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
		
		nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
		String numLog = String.valueOf(nroLog);
		t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
		journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));

//		Seteamos la fecha
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("dd/MM/yyyy");			
		Date data = new Date(fechaActual.getTime());
		String fecha = formatter.format(data);
		
		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		String hora = formatter.format(data);
		
		
		journal.setDatpro(fechaActual);
		journal.setHorpro(hora);
		journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
		journal.setNomben(usuario.getNombre());
		
		//@DPS String numLog = String.valueOf(this.nroLog); //!!!
		//String numLog = String.valueOf(((OperacionImpl)cuenta).getNroLog());
		if (ope instanceof CuentaImpl){
		  
			journal.setNumope(((CuentaImpl)ope).getNroOperacion());
			journal.setMsghst(" ");
			    
		}else if (ope instanceof TarjetaImpl){
		    
			journal.setNumope(((TarjetaImpl)ope).getNroOperacion());
		}else if (ope instanceof UsuarioImpl){
		   
		}
		journal.setCodtra(t.getTransaction());
		journal.setCodtrahst(t.getTransactionHost());
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setTypper(usuario.getTipoPersona());
		journal.setCipadr(ip);
		//journal.setNumope(this.getNroOperacion()); //!!!
		
		journal.setFlgcom(usuario.getTipoIngreso());
		
		ConstanteSesion.HOST_ERROR="0";
		ConstanteSesion.HOST_ERROR_MSG="";
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
			journal.setCoddoc("0001");
			journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
			journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
			if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
				journal.setNumprdsrc(usuario.getTarjeta().getNumero());
			else
				journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
		}

		try {
		    if (t.getObjeto() != null){
			    if (t.getObjeto() instanceof TransferenciaImpl) {
	                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
	                if (object.isInterbancaria()) {

	                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
	                	journal.setAmotra(importeLimite);
	                	//log.warn("importe getAmotra......"+journal.getAmotra());
	                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
	                	journal.setAmotxn(importeLimite);
	                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
	                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
	                
	                	String moneda=object.getSimboloMonedaImpNetoCargo().trim();
	                	
	                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
	                	    journal.setCodcur("604");
	 	                }
	 	                else{
	 	                	journal.setCodcur("840");
	 	                }
	                	
	                }
			    } 
	            else if (t.getObjeto() instanceof TarjetaImpl) {
	            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
	            	journal.setNumref(object.getCodigo());
	            }
	            
			}
	    } catch (Exception e) {}
		
		
		
		if(this.getArrayRuleException()!=null)
			journal.setMsgerror("");
		
		if(plantilla!=null&&this.getArrayRuleException()==null){
			this.setRefrendo(new Refrendo(plantilla));
			if (t.getObjeto() instanceof PagoTelefonoImpl){
				map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
			}
			this.getRefrendo().procesar(map);
			journal.setConstancia(this.getRefrendo().getResultado());
		}
		
		Class cls = journal.getClass(); 
		//Datos que vienen de la configuracion del journal
		boolean existsNroCta = false;
		for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
		    String key = (String)iter.next();
		    Object obj = parametros.get(key);
		    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
		    if(obj!=null){
			    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
				method.invoke(journal, new Object[]{obj});
		    }
		    if (metodo.equals("setNrocta")){
		        existsNroCta=true;
		    }
		}
	
		
		journal.setFlgcha(this.canal);
		journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		journal.setMsghst("OK:CLAVE6");
	
		
		ConstanteSesion.WAP_NUM_TAR="";
		ConstanteSesion.FLAG_ESTADO_LOG=journal.getFlgerror();
		ConstanteSesion.CONSTANCIA_LOG=journal.getConstancia();
		
		DAOFactory.getSaraWebLogImpl().insertarJournal2(journal);

		}


public void generarLog5(Transaction t, Usuario usuario, String plantilla, Object ope, HttpServletRequest request) throws Exception{		
    
	
	Vector resultado = t.getMascVector();
	
	String ip	= "desconocido";
	Vector datos = t.getCuentas();
	
	for (Iterator iter = datos.iterator(); iter.hasNext();) {
		Vector elemento = (Vector) iter.next();
		for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
			ip = (String) iterator.next();
			
		}
	}
			
	this.setTransaction(t);
	this.setUsuario(usuario);
			
	Map map = new HashMap();

	if(variablesGlobales!=null){
		map.putAll(this.getVariables());
		map.put("param",ope);
		map.put("usuario",usuario);
	}
	else{
		map.put("param",ope);
		map.put("usuario",usuario);
	}

		
	List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(t.getTransaction());
	
	Map parametros = new HashMap();
	for (Iterator it = lista.iterator(); it.hasNext();) {
		DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
				parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
			}
		}			
	}
	
	JournalImpl journal = new JournalImpl();
	
	journal.setFlgerror(ConstanteSesion.HOST_ERROR);
	journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
	
	nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
	String numLog = String.valueOf(nroLog);
	t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
	journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));

//	Seteamos la fecha
	SimpleDateFormat formatter;
	Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
	formatter = new SimpleDateFormat("dd/MM/yyyy");			
	Date data = new Date(fechaActual.getTime());
	String fecha = formatter.format(data);
	
	//Seteamos la hora
	formatter = new SimpleDateFormat("HH:mm:ss");
	data = new Date(fechaActual.getTime());
	String hora = formatter.format(data);
	
	
	journal.setDatpro(fechaActual);
	journal.setHorpro(hora);
	journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
	journal.setNomben(usuario.getNombre());
	
	//@DPS String numLog = String.valueOf(this.nroLog); //!!!
	//String numLog = String.valueOf(((OperacionImpl)cuenta).getNroLog());
	if (ope instanceof CuentaImpl){
	  
		journal.setNumope(((CuentaImpl)ope).getNroOperacion());
		journal.setMsghst(" ");
		    
	}else if (ope instanceof TarjetaImpl){
	    
		journal.setNumope(((TarjetaImpl)ope).getNroOperacion());
	}else if (ope instanceof UsuarioImpl){
	   
	}
	journal.setCodtra(t.getTransaction());
	journal.setCodtrahst(t.getTransactionHost());
	journal.setNumprdsrc(usuario.getTarjeta().getNumero());
	journal.setTypper(usuario.getTipoPersona());
	journal.setCipadr(ip);
	//journal.setNumope(this.getNroOperacion()); //!!!
	
	journal.setFlgcom(usuario.getTipoIngreso());
	
	ConstanteSesion.HOST_ERROR="0";
	ConstanteSesion.HOST_ERROR_MSG="";
	journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
	if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
		journal.setCoddoc("0001");
		journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
			journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		else
			journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
	}

	try {
	    if (t.getObjeto() != null){
		    if (t.getObjeto() instanceof TransferenciaImpl) {
                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
                if (object.isInterbancaria()) {

                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
                	journal.setAmotra(importeLimite);
                	//log.warn("importe getAmotra......"+journal.getAmotra());
                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
                	journal.setAmotxn(importeLimite);
                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
                
                	String moneda=object.getSimboloMonedaImpNetoCargo().trim();
                	
                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
                	    journal.setCodcur("604");
 	                }
 	                else{
 	                	journal.setCodcur("840");
 	                }
                	
                }
		    } 
            else if (t.getObjeto() instanceof TarjetaImpl) {
            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
            	journal.setNumref(object.getCodigo());
            }
            
		}
    } catch (Exception e) {}
	
	
	
	if(this.getArrayRuleException()!=null)
		journal.setMsgerror("");
	
	if(plantilla!=null&&this.getArrayRuleException()==null){
		this.setRefrendo(new Refrendo(plantilla));
		if (t.getObjeto() instanceof PagoTelefonoImpl){
			map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
		}
		this.getRefrendo().procesar(map);
		journal.setConstancia(this.getRefrendo().getResultado());
	}
	
	Class cls = journal.getClass(); 
	//Datos que vienen de la configuracion del journal
	boolean existsNroCta = false;
	for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
	    String key = (String)iter.next();
	    Object obj = parametros.get(key);
	    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
	    if(obj!=null){
		    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
			method.invoke(journal, new Object[]{obj});
	    }
	    if (metodo.equals("setNrocta")){
	        existsNroCta=true;
	    }
	}

	
	journal.setFlgcha(this.canal);
	journal.setNumprdsrc(usuario.getTarjeta().getNumero());
	journal.setMsghst("OK:N°TARJ,CLAVE6");

	
	
	ConstanteSesion.WAP_NUM_TAR="";
	DAOFactory.getSaraWebLogImpl().insertarJournal(journal);

	}



//Solo para generacion  de clave de Olvido y nueva primera vez
public void generarLog6(Transaction t, Usuario usuario, String plantilla, Object ope, HttpServletRequest request,String transa ) throws Exception{		
    
	
	Vector resultado = t.getMascVector();
	
	String ip	= "desconocido";
	Vector datos = t.getCuentas();
	
	for (Iterator iter = datos.iterator(); iter.hasNext();) {
		Vector elemento = (Vector) iter.next();
		for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
			ip = (String) iterator.next();
			
		}
	}
			
	this.setTransaction(t);
	this.setUsuario(usuario);
			
	Map map = new HashMap();

	if(variablesGlobales!=null){
		map.putAll(this.getVariables());
		map.put("param",ope);
		map.put("usuario",usuario);
	}
	else{
		map.put("param",ope);
		map.put("usuario",usuario);
	}

		
	List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(transa);
	
	Map parametros = new HashMap();
	for (Iterator it = lista.iterator(); it.hasNext();) {
		DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
				parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
			}
		}			
	}
	
	JournalImpl journal = new JournalImpl();
	
	journal.setFlgerror(ConstanteSesion.HOST_ERROR);
	journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
	
	nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
	String numLog = String.valueOf(nroLog);
	t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
	journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));

//	Seteamos la fecha
	SimpleDateFormat formatter;
	Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
	formatter = new SimpleDateFormat("dd/MM/yyyy");			
	Date data = new Date(fechaActual.getTime());
	String fecha = formatter.format(data);
	
	//Seteamos la hora
	formatter = new SimpleDateFormat("HH:mm:ss");
	data = new Date(fechaActual.getTime());
	String hora = formatter.format(data);
	
	
	journal.setDatpro(fechaActual);
	journal.setHorpro(hora);
	journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
	journal.setNomben(usuario.getNombre());
	
	//@DPS String numLog = String.valueOf(this.nroLog); //!!!
	//String numLog = String.valueOf(((OperacionImpl)cuenta).getNroLog());
	if (ope instanceof CuentaImpl){
	  
		journal.setNumope(((CuentaImpl)ope).getNroOperacion());
		journal.setMsghst(" ");
		    
	}else if (ope instanceof TarjetaImpl){
	    
		journal.setNumope(((TarjetaImpl)ope).getNroOperacion());
	}else if (ope instanceof UsuarioImpl){
	   
	}
	journal.setCodtra(transa);
	journal.setCodtrahst(t.getTransactionHost());
	journal.setNumprdsrc(usuario.getTarjeta().getNumero());
	journal.setTypper(usuario.getTipoPersona());
	journal.setCipadr(ip);
	//journal.setNumope(this.getNroOperacion()); //!!!
	
	journal.setFlgcom(usuario.getTipoIngreso());
	
	ConstanteSesion.HOST_ERROR="0";
	ConstanteSesion.HOST_ERROR_MSG="";
	journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
	if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
		journal.setCoddoc("0001");
		journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
			journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		else
			journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
	}

	try {
	    if (t.getObjeto() != null){
		    if (t.getObjeto() instanceof TransferenciaImpl) {
                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
                if (object.isInterbancaria()) {

                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
                	journal.setAmotra(importeLimite);
                	//log.warn("importe getAmotra......"+journal.getAmotra());
                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
                	journal.setAmotxn(importeLimite);
                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
                
                	String moneda=object.getSimboloMonedaImpNetoCargo().trim();
                	
                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
                	    journal.setCodcur("604");
 	                }
 	                else{
 	                	journal.setCodcur("840");
 	                }
                	
                }
		    } 
            else if (t.getObjeto() instanceof TarjetaImpl) {
            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
            	journal.setNumref(object.getCodigo());
              	            
	      } else if (t.getObjeto() instanceof TransferenciaContactoImpl) {
	    	  TransferenciaContactoImpl object = (TransferenciaContactoImpl) t.getObjeto();
           	            	
                journal.setNrocta(ObjectUtil.replaceChar(object.getCuentaOrigen().getCuentaFormateada(),'-',""));
            	
            }
            
		}
    } catch (Exception e) {}
	
	
	
	if(this.getArrayRuleException()!=null)
		journal.setMsgerror("");
	
	if(plantilla!=null&&this.getArrayRuleException()==null){
		this.setRefrendo(new Refrendo(plantilla));
		if (t.getObjeto() instanceof PagoTelefonoImpl){
			map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
		}
		this.getRefrendo().procesar(map);
		journal.setConstancia(this.getRefrendo().getResultado());
	}
	
	Class cls = journal.getClass(); 
	//Datos que vienen de la configuracion del journal
	boolean existsNroCta = false;
	for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
	    String key = (String)iter.next();
	    Object obj = parametros.get(key);
	    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
	    if(obj!=null){
		    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
			method.invoke(journal, new Object[]{obj});
	    }
	    if (metodo.equals("setNrocta")){
	        existsNroCta=true;
	    }
	}

	
	journal.setFlgcha(this.canal);
	journal.setNumprdsrc(usuario.getTarjeta().getNumero());
	journal.setMsghst("OK:N°TARJ,CLAVE4");

	if (ConstanteSesion.HOST_ERROR.equals("0")){///////////
		
		
		if(usuario.getClaveDinamica()!= null && usuario.getClaveDinamica().getTipoElementoSeguridadFinal()!=null){
			
			if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TDC))
			{
				journal.setMsghst("OK:CLAVE6,TARJETA DE COORDENADAS");
			}
			else{
				if(usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
				{
				
					journal.setMsghst("OK:CLAVE6,TOKEN");
				}
				
				if (usuario.getClaveDinamica().getTipoElementoSeguridadFinal().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
				
					journal.setMsghst("OK:CLAVE6,CLAVE SMS");
				}
			}
		}
		
	}
	
	
	ConstanteSesion.WAP_NUM_TAR="";
	ConstanteSesion.FLAG_ESTADO_LOG=journal.getFlgerror();
	ConstanteSesion.CONSTANCIA_LOG=journal.getConstancia();
	DAOFactory.getSaraWebLogImpl().insertarJournal1(journal);

	}

//log solo para el grabado de cambio de clave de 6 por vencimiento
public void generarLog7(Transaction t, Usuario usuario, String plantilla, Object ope, HttpServletRequest request) throws Exception{		
    
	
	Vector resultado = t.getMascVector();
	
	String ip	= "desconocido";
	Vector datos = t.getCuentas();
	
	for (Iterator iter = datos.iterator(); iter.hasNext();) {
		Vector elemento = (Vector) iter.next();
		for (Iterator iterator = elemento.iterator(); iterator.hasNext();) {
			ip = (String) iterator.next();
			
		}
	}
			
	this.setTransaction(t);
	this.setUsuario(usuario);
			
	Map map = new HashMap();

	if(variablesGlobales!=null){
		map.putAll(this.getVariables());
		map.put("param",ope);
		map.put("usuario",usuario);
	}
	else{
		map.put("param",ope);
		map.put("usuario",usuario);
	}

		
	List lista = DAOFactory.getGeneraDAO().getJournalDiccionario(t.getTransaction());
	
	Map parametros = new HashMap();
	for (Iterator it = lista.iterator(); it.hasNext();) {
		DiccionarioJournalImpl journalImpl = (DiccionarioJournalImpl) it.next();			
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(journalImpl.getCodigoDiccionario())){
				parametros.put(journalImpl.getCampoJournal(),elemento.elementAt(6));					
			}
		}			
	}
	
	JournalImpl journal = new JournalImpl();
	
	journal.setFlgerror(ConstanteSesion.HOST_ERROR);
	journal.setMsghst(ConstanteSesion.HOST_ERROR_MSG);
	
	nroLog = DAOFactory.getSaraWebLogImpl().getSecuencia();
	String numLog = String.valueOf(nroLog);
	t.getValues().add(ObjectUtil.getVectorComponent("nroLog",numLog));
	journal.setNumlog(ObjectUtil.setearCaracterLeft(numLog,'0',30-numLog.length()));

//	Seteamos la fecha
	SimpleDateFormat formatter;
	Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
	formatter = new SimpleDateFormat("dd/MM/yyyy");			
	Date data = new Date(fechaActual.getTime());
	String fecha = formatter.format(data);
	
	//Seteamos la hora
	formatter = new SimpleDateFormat("HH:mm:ss");
	data = new Date(fechaActual.getTime());
	String hora = formatter.format(data);
	
	
	journal.setDatpro(fechaActual);
	journal.setHorpro(hora);
	journal.setFlgcom(ConstanteSesion.CODIGO_COMPANIA);
	journal.setNomben(usuario.getNombre());
	
	//@DPS String numLog = String.valueOf(this.nroLog); //!!!
	//String numLog = String.valueOf(((OperacionImpl)cuenta).getNroLog());
	if (ope instanceof CuentaImpl){
	  
		journal.setNumope(((CuentaImpl)ope).getNroOperacion());
		journal.setMsghst(" ");
		    
	}else if (ope instanceof TarjetaImpl){
	    
		journal.setNumope(((TarjetaImpl)ope).getNroOperacion());
	}else if (ope instanceof UsuarioImpl){
	   
	}
	journal.setCodtra(t.getTransaction());
	journal.setCodtrahst(t.getTransactionHost());
	journal.setNumprdsrc(usuario.getTarjeta().getNumero());
	journal.setTypper(usuario.getTipoPersona());
	journal.setCipadr(ip);
	//journal.setNumope(this.getNroOperacion()); //!!!
	
	journal.setFlgcom(usuario.getTipoIngreso());
	
	ConstanteSesion.HOST_ERROR="0";
	ConstanteSesion.HOST_ERROR_MSG="";
	journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
	if(!ConstanteSesion.WAP_DNI_CTA_CTE.equals("")){
		journal.setCoddoc("0001");
		journal.setNumdoc(ConstanteSesion.WAP_DNI_CTA_CTE);
		journal.setNrocta(ConstanteSesion.WAP_CTA_CTE);
		if(usuario.getTarjeta().getNumero()!=null && !usuario.getTarjeta().getNumero().equals(""))
			journal.setNumprdsrc(usuario.getTarjeta().getNumero());
		else
			journal.setNumprdsrc(ConstanteSesion.WAP_NUM_TAR);
	}

	try {
	    if (t.getObjeto() != null){
		    if (t.getObjeto() instanceof TransferenciaImpl) {
                TransferenciaImpl object = (TransferenciaImpl) t.getObjeto();
                if (object.isInterbancaria()) {

                    String importeLimite = ObjectUtil.replaceChar(object.getTotal().toString(), ',', "");
                	journal.setAmotra(importeLimite);
                	//log.warn("importe getAmotra......"+journal.getAmotra());
                	importeLimite = ObjectUtil.replaceChar(object.getImporte().toString(), ',', "");
                	journal.setAmotxn(importeLimite);
                	//log.warn("importe getAmotxn......"+journal.getAmotxn());
                	journal.setBaltra(ObjectUtil.replaceChar(object.getImporteTransferido().toString(), ',', ""));
                
                	String moneda=object.getSimboloMonedaImpNetoCargo().trim();
                	
                	if(moneda.equals(Constante.SIMBOLO_MONEDA_SOL)){
                	    journal.setCodcur("604");
 	                }
 	                else{
 	                	journal.setCodcur("840");
 	                }
                	
                }
		    } 
            else if (t.getObjeto() instanceof TarjetaImpl) {
            	TarjetaImpl object = (TarjetaImpl) t.getObjeto();
            	journal.setNumref(object.getCodigo());
            }
            
		}
    } catch (Exception e) {}
	
	
	
	if(this.getArrayRuleException()!=null)
		journal.setMsgerror("");
	
	if(plantilla!=null&&this.getArrayRuleException()==null){
		this.setRefrendo(new Refrendo(plantilla));
//		if (t.getObjeto() instanceof PagoTelefonoImpl){
//			map.put("cuenta",((PagoTelefonoImpl)t.getObjeto()).getCuenta());	
//			
			map.put("mensajecambiopie",request.getSession().getAttribute("mensajecambiopie"));	
			map.put("mensajecambiocabecera",request.getSession().getAttribute("mensajecambiocabecera"));
			map.put("mensajecambioatte",request.getSession().getAttribute("mensajecambioatte"));
			
			
			
			
//		}
		this.getRefrendo().procesar(map);
		journal.setConstancia(this.getRefrendo().getResultado());
	}
	
	Class cls = journal.getClass(); 
	//Datos que vienen de la configuracion del journal
	boolean existsNroCta = false;
	for (Iterator iter = parametros.keySet().iterator(); iter.hasNext();){ 
	    String key = (String)iter.next();
	    Object obj = parametros.get(key);
	    String metodo = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
	    if(obj!=null){
		    Method method = cls.getMethod(metodo, new Class[]{obj.getClass()});
			method.invoke(journal, new Object[]{obj});
	    }
	    if (metodo.equals("setNrocta")){
	        existsNroCta=true;
	    }
	}

	
	journal.setFlgcha(this.canal);
	journal.setNumprdsrc(usuario.getTarjeta().getNumero());
	journal.setMsghst("OK:CLAVE6");

	ConstanteSesion.WAP_NUM_TAR="";
	ConstanteSesion.FLAG_ESTADO_LOG=journal.getFlgerror();
	ConstanteSesion.CONSTANCIA_LOG=journal.getConstancia();
	DAOFactory.getSaraWebLogImpl().insertarJournal2(journal);

	}



	
    public Transaction getTransaction() {
        return transaction;
    }
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
	public Map getVariables() {
		return variablesGlobales;
	}
    public String getNroOperacion() {
        return nroOperacion;
    }
    public void setNroOperacion(String nroOperacion) {
        this.nroOperacion = nroOperacion;
    }
    public ArrayRuleException getArrayRuleException() {
        return arrayRuleException;
    }
    public void setArrayRuleException(ArrayRuleException arrayRuleException) {
        this.arrayRuleException = arrayRuleException;
    }
    public Refrendo getRefrendo() {
        return refrendo;
    }
    public void setRefrendo(Refrendo refrendo) {
        this.refrendo = refrendo;
    }
    public String getCanal() {
        return canal;
    }
    public void setCanal(String canal) {
        this.canal = canal;
    }
	public void setVariables(Map variables) {
	    
//		Set keys = variables.keySet();         // The set of keys in the map.
//		Iterator keyIter = keys.iterator();
//		while (keyIter.hasNext()) {
//		    Object key = keyIter.next();  // Get the next key.
//		    Object value = variables.get(key);  // Get the value for that key.
//		    System.out.println(key + "=" + value +"[FIN]");
//		}	    
		variablesGlobales = variables;
	}
	
	
	
	
	
}