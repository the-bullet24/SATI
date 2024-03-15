/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
package pe.bn.transferencia.facade.impl;


import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.bn.transferencia.facade.TransferenciaFacade;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
public class TransferenciaFacadeImpl implements TransferenciaFacade { 
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaFacadeImpl.class.getName());
    
    /***Metodo utlizado en Transferencia Bancaria para la Consulta de Comision***/
	/* (sin Javadoc)
	 * @see pe.bn.transferencia.facade.TransferenciaFacade#getTransferencia(pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, java.math.BigDecimal)
	 */
	public Transferencia getTransferencia(String transaccion,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario, String moneda,String monto,String remoteAddress) throws Exception {
	
		Vector valores 			= new Vector();
		Vector val 				= new Vector();
		String opcion 			= "";
		String codigoTxn		= "";
		String importe 			= "";
		String impReferencia    = "";
		
		TipoCambio tipoCambio = usuario.getTipoCambio();
		
		impReferencia = ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
			opcion 		= "01";
			codigoTxn 	= "1670";
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
			opcion 		= "01";
			if (moneda.equals(Constante.MONEDA_SOL)){
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
				codigoTxn 	= "1670";
			}
			else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "1670";
				//if(Constante.VER_LOG) System.out.println("monto:"+monto);
				//if(Constante.VER_LOG) System.out.println("tipoCambio.getVenta():"+tipoCambio.getVenta());
				BigDecimal total = new BigDecimal(monto).multiply(tipoCambio.getVenta()).setScale(2, BigDecimal.ROUND_HALF_UP);
				//if(Constante.VER_LOG) System.out.println("total:"+total);
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}
		}
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			opcion 		= "01";
			codigoTxn 	= "1670";
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			opcion 		= "01";
			if (moneda.equals(Constante.MONEDA_SOL)){
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
				codigoTxn 	= "1670";
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "1670";
				//if(Constante.VER_LOG) System.out.println("monto:"+monto);
				//if(Constante.VER_LOG) System.out.println("tipoCambio.getVenta():"+tipoCambio.getVenta());
				BigDecimal total = new BigDecimal(monto).multiply(tipoCambio.getVenta()).setScale(2, BigDecimal.ROUND_HALF_UP);
				//if(Constante.VER_LOG) System.out.println("total:"+total);
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}
		}
		
		//8506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
			opcion 		= "02";
			codigoTxn 	= "8670";
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}
		//8506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
			opcion 		= "02";
			if (moneda.equals(Constante.MONEDA_SOL)){
				codigoTxn 	= "8670";
			
				BigDecimal total = new BigDecimal(monto).divide(tipoCambio.getCompra(), 2, BigDecimal.ROUND_HALF_UP);
				
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "8670";
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
			}
		}
		//8506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			opcion 		= "02";
			if (moneda.equals(Constante.MONEDA_SOL)){
				codigoTxn 	= "8670";
				
				BigDecimal total = new BigDecimal(monto).divide(tipoCambio.getCompra(), 2, BigDecimal.ROUND_HALF_UP).setScale(2);
				
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "8670";
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
			}
		}
		//8506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			opcion 		= "02";
			codigoTxn 	= "8670";
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}
		
		
		/*** LAS 4 ULTIMAS TRX ***********************/
		//0506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			opcion 		= "03";
			codigoTxn 	= "0674";
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}//0506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			opcion 		= "03";
			if (moneda.equals(Constante.MONEDA_SOL)){
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
				codigoTxn 	= "0674";
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "0674";
				
				BigDecimal total = new BigDecimal(monto).multiply(tipoCambio.getVenta()).setScale(2, BigDecimal.ROUND_HALF_UP);
				
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}
		}
		//0506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
			opcion 		= "03";
			codigoTxn 	= "0674";
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}//0506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
			opcion 		= "03";
			if (moneda.equals(Constante.MONEDA_SOL)){
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
				codigoTxn 	= "0674";
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "0674";
				
				BigDecimal total = new BigDecimal(monto).multiply(tipoCambio.getVenta()).setScale(2, BigDecimal.ROUND_HALF_UP);
				
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}
		}
		
		
		
		//6506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			opcion 		= "04";
			codigoTxn 	= "6674"; 
			//codigoTxn 	= "6670"; //Comentado por solicitud de EP correo 30102007
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto)); 
		}
		//6506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			opcion 		= "04";
			if (moneda.equals(Constante.MONEDA_SOL)){
				codigoTxn 	= "6674"; 
				// codigoTxn 	= "6670"; //Comentado por solicitud de EP correo 30102007
				//if(Constante.VER_LOG) System.out.println("monto:"+monto);
				//if(Constante.VER_LOG) System.out.println("tipoCambio.getCompra():"+tipoCambio.getCompra());
				BigDecimal total = new BigDecimal(monto).divide(tipoCambio.getCompra(), 2, BigDecimal.ROUND_HALF_UP).setScale(2);
				//if(Constante.VER_LOG) System.out.println("total:"+total);
				importe 	= ObjectUtil.formatearMontoTrama(total);
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "6674"; 
				// codigoTxn 	= "6670"; //Comentado por solicitud de EP correo 30102007
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
			}
		}
		//6506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
			opcion 		= "04";
			if (moneda.equals(Constante.MONEDA_SOL)){
				codigoTxn 	= "6674"; 
				//codigoTxn 	= "6670"; //Comentado por solicitud de EP correo 30102007
				//if(Constante.VER_LOG) System.out.println("monto:"+monto);
				//if(Constante.VER_LOG) System.out.println("tipoCambio.getCompra():"+tipoCambio.getCompra());
				BigDecimal total = new BigDecimal(monto).divide(tipoCambio.getCompra(), 2, BigDecimal.ROUND_HALF_UP).setScale(2);
				//if(Constante.VER_LOG) System.out.println("total:"+total);
				importe 	= ObjectUtil.formatearMontoTrama(total);
				
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				codigoTxn 	= "6674"; 
				// codigoTxn 	= "6670"; //Comentado por solicitud de EP correo 30102007
				importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
			}
		}
		//6506
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
			opcion 		= "04";
			codigoTxn 	= "6674";
			// codigoTxn 	= "6670"; //Comentado por solicitud de EP correo 30102007
			importe 	= ObjectUtil.formatearMontoTrama(new BigDecimal(monto));
		}
		
		//if(Constante.VER_LOG) System.out.println("moneda:"+moneda);
		
		val = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(val);
		
		
		val = ObjectUtil.getVectorComponent("cuentaOrigen",cuenta.getNumeroProducto());
		valores.addElement(val);
		
		
		val = ObjectUtil.getVectorComponent("cuentaDestino",afiliacion.getNumeroServicio().trim());
		valores.addElement(val);
		
		
		val = ObjectUtil.getVectorComponent("codigoTxn",codigoTxn);
		valores.addElement(val);
		
		
		val = ObjectUtil.getVectorComponent("importePrincipal",importe);
		valores.addElement(val);
		
		
		val = ObjectUtil.getVectorComponent("importeReferencial",impReferencia);
		valores.addElement(val);
		
		
		Vector ctas = new Vector();
		Vector v = ObjectUtil.getVectorBlankCuentas(remoteAddress,opcion);
		ctas.addElement(v);
		
		/*** Generacion de Objeto Transaccion **/
		Transaction t = new Transaction(transaccion,valores,ctas);
		
		/** Generacion del Objetop Transferencia **/
		TransferenciaImpl transferenciaImpl = new TransferenciaImpl(cuenta,afiliacion,moneda,new BigDecimal(monto));
		transferenciaImpl.getConsultaComisionBancaria(t,usuario);
		transferenciaImpl.setMoneda(moneda);
		
		return (Transferencia)transferenciaImpl;
	}


	public Transferencia pagarTransferencia(String transaccion ,TransferenciaImpl transferencia, Usuario usuario ,String remoteAddress,HttpServletRequest request) throws Exception {

	    Vector valores = new Vector();
		Vector val = new Vector();
		String opcion = "";
		transferencia.calcularMonto(usuario.getTipoCambio());
		transferencia.setInterbancaria(false);
		
		if (transferencia.getAfiliacion().getCuentaPropia().trim().equals("S")){
		    val = new Vector();
			val.addElement("ctaPropia");
			val.addElement("0");
			valores.addElement(val);	
			//System.out.println("ctaPropia....0");
		} else {
		    val = new Vector();
			val.addElement("ctaPropia");
			val.addElement("7");
			valores.addElement(val);	
			//System.out.println("ctaPropia....7");
		}
	    	    
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("cuentaOrigen");
		val.addElement(transferencia.getCuenta().getNumeroProducto());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("nroDocumento");
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CTS_MN)){
			val.addElement("");
		
		}
		else{
		val.addElement(transferencia.getAfiliacion().getNroDocumento());
		}
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("importePrincipal");
		String importe = ObjectUtil.formatearMontoTrama(transferencia.getImporteReal());
		val.addElement(importe);
		valores.addElement(val);
		
		//(1)3321
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)&&transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			BigDecimal temp = null;
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getMoneda():"+transferencia.getMoneda());
			
			val = new Vector();
			val.addElement("importeConvertido");
			//if(Constante.VER_LOG) System.out.println("importeConvertido:"+transferencia.getImporteConvertido());
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			val = new Vector();
			val.addElement("tipoCambio");
			//val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getTipoCambio()));
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			//if(Constante.VER_LOG) System.out.println("tipoCambio:"+ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3321");
			valores.addElement(val);
			
			opcion="01";
		}
		//(2)3310
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);		

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3310");
			valores.addElement(val);
			opcion="02";
		}
		//(3)3315			
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);		

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3315");
			valores.addElement(val);
			opcion="03";
		}
		//(4)3331		
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);		

			//if(Constante.VER_LOG) System.out.println("transferencia.getMoneda():"+transferencia.getMoneda());			
			val = new Vector();
			val.addElement("importeConvertido");
			//if(Constante.VER_LOG) System.out.println("importeConvertido:"+transferencia.getImporteConvertido());
			
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3331");
			valores.addElement(val);
			opcion="04";
		}
		//5
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);		

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3311");
			valores.addElement(val);
			opcion="05";
		}
		//(6)3330		
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);		
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("tipoCambio");
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3330");
			valores.addElement(val);
			opcion="06";
		}
		//(7)3335
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);		
			
		
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
		
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3335");
			valores.addElement(val);
			opcion="07";
		}
		//(8)3320		
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);		

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3320");
			valores.addElement(val);
			opcion="08";
		}
		//(9)3300 (ok)		
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);		

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3300");
			valores.addElement(val);
			opcion="09";
		}
		//(10) 3301 (ok)
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3301");
			valores.addElement(val);
			opcion="10";
		}
		//(11) 3305 (ok)
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3305");
			valores.addElement(val);
			opcion="11";
		}
		//(12) 3340 (ok) 
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3340");
			valores.addElement(val);
			opcion="12";
		}
		//(13)3341 (ok)
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("2");
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3341");
			valores.addElement(val);
			opcion="13";
		}
		//(14) 3345 (ok)
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3345");
			valores.addElement(val);
			opcion="14";
		}
		//(15) 3350 (ok)
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+transferencia.getTipoCambio());
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(transferencia.getTipoCambio())));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3350");
			valores.addElement(val);
			opcion="15";
		}
		//16
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("4");
			valores.addElement(val);

			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3325");
			valores.addElement(val);
			opcion="16";
		}
		//17
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CTS_MN)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			//System.out.println("entro por aki,,,,,,,,,");
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3351");
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("0");
			valores.addElement(val);

			
			opcion="17";
		}
		//18
		if(transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CTS_ME)&& transferencia.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			val = new Vector();
			val.addElement("codigoTrx");
			val.addElement("3355");
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("importeConvertido");
			val.addElement(ObjectUtil.formatearMontoTrama(transferencia.getImporteConvertido()));
			valores.addElement(val);
			
			//if(Constante.VER_LOG) System.out.println("transferencia.getTipoCambio():"+usuario.getTipoCambio().getCompra());
			val = new Vector();
			val.addElement("tipoCambio");
			val.addElement(ObjectUtil.formatearMontoTrama(usuario.getTipoCambio().getCompra()));
			valores.addElement(val);
			
			val = new Vector();
			val.addElement("tipoAfectacion");
			val.addElement("0");
			valores.addElement(val);

			opcion="18";
		}
		
		if(!transferencia.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CTS_MN)){
		val = new Vector();
		val.addElement("beneficiario");
		val.addElement(ObjectUtil.cortarCadena(transferencia.getAfiliacion().getDescripcion().trim(),30));
		valores.addElement(val);		
		}
		else{
			val = new Vector();
			val.addElement("beneficiario");
			val.addElement(ObjectUtil.cortarCadena(usuario.getNombre(),30));
			valores.addElement(val);		
		}
		val = new Vector();
		val.addElement("cuentaDestino");
		//val.addElement(transferencia.getAfiliacion().getNumeroServicio());  08-006-901477
		val.addElement(transferencia.getCuentaDestino().getNumeroProducto());
		valores.addElement(val);		


		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement(opcion);
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		
		/**
		 * CAMBIO SOLICITADO POR CANALES VIRTUALES (LP)
		 * FECHA : 10/04/2012
		 * MOTIVO: No realizar el cálculo de la sumatoria de Límite máximo sin Comisión e ITF
		 */
		
		transferencia.getCuentaOrigen().getNumeroProducto();
		
		
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(transferencia.getImporteNeto());
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transferencia.getComision()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transferencia.getItf()));
		
		//FacadeFactory.getGeneralFacade().validarLimiteImporte(transferencia.getCuenta(),transferencia.getImporteNeto());
		
		/**
		 * CAMBIO SOLICITADO POR HPO
		 * FECHA : 16/01/2013
		 * MOTIVO: Pedido de la Gerencia, no validar importe maximo para ciertas cuentas
		 */
		
		//PARA PRODUCCION 
		
//		if(!transferencia.getCuentaOrigen().getNumeroProducto().equals("04007327337") && !transferencia.getCuentaOrigen().getNumeroProducto().equals("04040571936")){
//				//&& !transferencia.getCuentaOrigen().getNumeroProducto().equals("04000921810")){   // Se quita el 26/02/2013 segun documento
//			
//			if(transferencia.getCuentaOrigen().getNumeroProducto().equals("04048287021") || transferencia.getCuentaOrigen().getNumeroProducto().equals("04048286955") ){
//				FacadeFactory.getGeneralFacade().validarLimiteImporteConTope(transferencia.getCuenta(),mont);
//			}
//			else{
//				
//				FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,transferencia.getCuenta(),mont);
//				
//				
//			}
//			
//		}
	
		
		//CERTIFICACION
		
//		if(!transferencia.getCuentaOrigen().getNumeroProducto().equals("04011508524") && !transferencia.getCuentaOrigen().getNumeroProducto().equals("08046307578")){
//			
//				if(transferencia.getCuentaOrigen().getNumeroProducto().equals("04045953500") || transferencia.getCuentaOrigen().getNumeroProducto().equals("04045953519") ){
//				FacadeFactory.getGeneralFacade().validarLimiteImporteConTope(transferencia.getCuenta(),mont);
//			}
//			else{
//				
//				FacadeFactory.getGeneralFacade().validarLimiteImporte(transferencia.getCuenta(),mont);
//			}
//		}
	
		//PARA DESARROLLO
		
		//FacadeFactory.getGeneralFacade().validarLimiteImporte(transferencia.getCuenta(),mont);
		
		/**
		 * CAMBIO SOLICITADO POR HPO
		 * FECHA : 18/02/2014
		 * MOTIVO: Pedido de la Gerencia,  validar importe maximo por 10 mil para ciertas cuentas
		 */

		/**
				FIN DEL CAMBIO
		 */
		Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		//@DPS transferencia.pagarTransferenciaMismoBanco(transaccion,usuario,valores,ctas);
		transferencia.pagarTransferenciaMismoBanco(t,usuario);
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_TRANSFERENCIA_BANCARIA,transferencia,request);
		if(transferencia.getArrayRuleException()!=null){
		    throw transferencia.getArrayRuleException();
		}
		
		return (Transferencia)transferencia;
	}

	/* (sin Javadoc)
	 * @see pe.bn.transferencia.facade.TransferenciaFacade#getTitularTransferencia(pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion)
	 */
	public void getTitularTransferencia(String transaccion,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception {
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
		
		val = new Vector();
		val.addElement("nroCuenta");
		val.addElement(afiliacion.getNumeroServicio());
		valores.addElement(val);		

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
		v.addElement(remoteAddress);
		ctas.addElement(v);
		TransferenciaImpl transferencia = new TransferenciaImpl(); 
		afiliacion.setDescripcion(transferencia.getTitularCuenta(transaccion,usuario,valores,ctas));
		
	}
	
	public void getTransferenciaCtaUob(String transaccion,Afiliacion afiliacion, String remoteAddress) throws Exception {
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
		
		val = new Vector();
		val.addElement("nroCuenta");
		val.addElement(afiliacion.getNumeroServicio());
		valores.addElement(val);		

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
		v.addElement(remoteAddress);
		ctas.addElement(v);
		TransferenciaImpl transferencia = new TransferenciaImpl(); 
		afiliacion.setCtaUob(transferencia.getCuentaUob(transaccion,valores,ctas));
		
	}
	
	//Metodo Para Moneda Nacional y Extranjera Mismo Titular 
	public Transferencia getTitularTransferenciaIB(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception {
	   		
		 if(Constante.VER_LOG) System.out.println("getTitularTransferenciaIB");
		
		String transaccion = "TB05"; 
		
		Vector valores 			= new Vector();
		Vector val 				= new Vector();
		
		String opt 				= "";
		String tipTransferencia = "";
		String tipAfectacion    = "2";
		
		val = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(val);
		
		if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    opt="01";
		    val = ObjectUtil.getVectorComponent("codigoTrx","1411");
			valores.addElement(val);
			if (codMoneda.equals(Constante.MONEDA_SOL))
				tipTransferencia = "1";
			else
		    	tipTransferencia = "3";
	    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
	        opt="07";
	        tipAfectacion = "4";
	       //tipTransferencia = "3";
	        val = ObjectUtil.getVectorComponent("codigoTrx","8411");
			valores.addElement(val);
			
			if (codMoneda.equals(Constante.MONEDA_SOL))
					tipTransferencia = "3";
			else
			    	tipTransferencia = "1";
	    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		    opt="10";
		    val = ObjectUtil.getVectorComponent("codigoTrx","0411");
			valores.addElement(val);
			if (codMoneda.equals(Constante.MONEDA_SOL))
				tipTransferencia = "1";
			else if (codMoneda.equals(Constante.MONEDA_DOLAR))
		    	tipTransferencia = "3";
	    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
	        opt="19";
	        tipAfectacion = "4";
	        
	        val = ObjectUtil.getVectorComponent("codigoTrx","6411");
			valores.addElement(val);
			
			if (codMoneda.equals(Constante.MONEDA_SOL))
					tipTransferencia = "3";
			else if (codMoneda.equals(Constante.MONEDA_DOLAR))
			    	tipTransferencia = "1";
	    }
	    
		String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(afiliacion.getTipoDocumento());
		
	
	    
		val = ObjectUtil.getVectorComponent("importeReferencial",ObjectUtil.formatearMontoTrama(new BigDecimal(monto)));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("tipoTransfer",tipTransferencia);
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
		valores.addElement(val);
		
		// val = ObjectUtil.getVectorComponent("tipoAfectacion","4");
		val = ObjectUtil.getVectorComponent("tipoAfectacion",tipAfectacion);
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaOrigen",cuenta.getNumeroProducto());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaDestino",afiliacion.getNumeroServicio());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("beneficiario",afiliacion.getBeneficiario());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("numeroDocumento",afiliacion.getNroDocumento());
		valores.addElement(val);
		
		/** Aqui se Usa Opt **/
		Vector ctas=new Vector();
		Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,opt);
		ctas.addElement(v);
		
		/*** Generacion de Objeto Transaccion **/
		Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		
		/** Generacion del Objetop Transferencia **/
		TransferenciaImpl transferencia = new TransferenciaImpl();
		transferencia.setCuentaOrigen(cuenta);
		transferencia.getTitularCuentaIB(t,usuario);
		
		transferencia.setMoneda(codMoneda);
		if (opt.equals("01") && codMoneda.equals(Constante.MONEDA_SOL)) {
		    transferencia.setItf(new BigDecimal("0.00"));
        }
		
		return transferencia;
	}
	

	public Transferencia getConsultaComisionBancaria(String transaccion,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception {
	    //if(Constante.VER_LOG) System.out.println("Begin getConsultaComisionBancaria");
		
		Vector valores 			= new Vector();
		Vector val 		= new Vector();
		String opcion 			= "";
		String tipTransferencia = "";
		String tipAfectacion    = "";
		
		if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    
		}
		
		/** Aqui se Usa Opt **/
		Vector ctas=new Vector();
		Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,opcion);
		ctas.addElement(v);
		
		/*** Generacion de Objeto Transaccion **/
		Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		
		/** Generacion del Objetop Transferencia **/
		TransferenciaImpl transferencia = new TransferenciaImpl();
		transferencia.setCuentaOrigen(cuenta);
		transferencia.getConsultaComisionBancaria(t,usuario);
		return transferencia;
	}
	
	
	public Transferencia getOtroTitularTransferenciaIB(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String remoteAddress) throws Exception {
	   if(Constante.VER_LOG) System.out.println("getOtroTitularTransferenciaIB");
	  
		String transaccion = "TB05"; 
		
		Vector valores 	= new Vector();
		Vector val 		= new Vector();
		
		String opt 				= "";
		String tipTransferencia = "";
		String tipAfectacion    = "2";
		
		if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    opt="04";
		    val = ObjectUtil.getVectorComponent("codigoTrx","1410");
			valores.addElement(val);
			if (codMoneda.equals(Constante.MONEDA_SOL))
				tipTransferencia = "2";
			else
		    	tipTransferencia = "4";
	    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
	        opt="13";
	        tipAfectacion = "4";
	       
	        val = ObjectUtil.getVectorComponent("codigoTrx","8410");
			valores.addElement(val);
			
			if (codMoneda.equals(Constante.MONEDA_SOL))
					tipTransferencia = "4";
			else
			    	tipTransferencia = "2";
	    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		    opt="16";
		    val = ObjectUtil.getVectorComponent("codigoTrx","0410");
			valores.addElement(val);
			if (codMoneda.equals(Constante.MONEDA_SOL))
				tipTransferencia = "2";
			else
		    	tipTransferencia = "4";
	    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
		    opt="21";
		    tipAfectacion = "4";
		    
		    val = ObjectUtil.getVectorComponent("codigoTrx","6410");
			valores.addElement(val);
			if (codMoneda.equals(Constante.MONEDA_SOL))
				tipTransferencia = "4";
			else
		    	tipTransferencia = "2";
	    }
	    
		val = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("importeReferencial",ObjectUtil.formatearMontoTrama(new BigDecimal(monto)));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("tipoTransfer",tipTransferencia);
		valores.addElement(val);
		
		// val = ObjectUtil.getVectorComponent("tipoAfectacion","4");
		val = ObjectUtil.getVectorComponent("tipoAfectacion",tipAfectacion);
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaOrigen",cuenta.getNumeroProducto());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaDestino",afiliacion.getNumeroServicio());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("beneficiario",afiliacion.getBeneficiario());
		valores.addElement(val);
		
		Vector ctas=new Vector();
		Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,opt);
		ctas.addElement(v);
		
		Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		
		TransferenciaImpl transferencia = new TransferenciaImpl();
		transferencia.getOtroTitularCuentaIB(t,usuario);
		transferencia.setMoneda(codMoneda);
		
		//if(Constante.VER_LOG) System.out.println("End getOtroTitularTransferenciaIB");
		return transferencia;
	}
	
	public Transferencia transferirInterBancoLinea(String moneda, Transferencia transferencia, Usuario usuario ,String remoteAddress, HttpServletRequest request)throws Exception {
	   		
		TransferenciaImpl transfer = (TransferenciaImpl)transferencia;
	    transfer.setInterbancariaLinea(true);
	  
	    String transaccion = "";
	    Vector valores 	= new Vector();
		Vector val 		= new Vector();
		
	
		String importeRefTransf = ObjectUtil.replaceChar(transfer.getImporteTransferido().trim(),',',"");
		val = ObjectUtil.getVectorComponent("importeTransferencia",ObjectUtil.formatearMontoTrama(new BigDecimal(importeRefTransf)));
		valores.addElement(val);
					
		val = ObjectUtil.getVectorComponent("tipoAfectacion","2");
		valores.addElement(val);
				
		val = ObjectUtil.getVectorComponent("comision",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaOrigen",transfer.getCuentaOrigen().getNumeroProducto());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaDestino",transfer.getAfiliacion().getCuenta().getNumeroProducto());
		valores.addElement(val);
		
//		System.out.println("moneda:"+transfer.getCodMoneda());
		val = ObjectUtil.getVectorComponent("moneda",transfer.getCodMoneda());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("correo1",transfer.getAfiliacion().getCorreo().toUpperCase());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("correo2",transfer.getAfiliacion().getServidorCorreo().toUpperCase());
		valores.addElement(val);

		val = ObjectUtil.getVectorComponent("identificadorCCE1",transfer.getAfiliacion().getIdentificadorCCE1());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("identificadorCCE2",transfer.getAfiliacion().getIdentificadorCCE2());
		valores.addElement(val);
		//val = ObjectUtil.getVectorComponent("cciOrigen",transfer.getAfiliacion().getCciOrigen());
		val = ObjectUtil.getVectorComponent("cciOrigen", usuario.getCciCliente());
		valores.addElement(val);

		val = ObjectUtil.getVectorComponent("ruteo1","0140020" + transfer.getAfiliacion().getCodigoServicio());
		//val = ObjectUtil.getVectorComponent("ruteo1","0140020054");
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("impReferenciaMon",ObjectUtil.formatearMontoTrama(transfer.getImporte()));
		valores.addElement(val);
						
		val = ObjectUtil.getVectorComponent("numTarjetaDebito",usuario.getTarjeta().getNumero());
		valores.addElement(val); 
		
		val = ObjectUtil.getVectorComponent("cuentaPropia",transfer.getCuentaPropia());
		valores.addElement(val);
		
		//MGL - ini - extraccion de nombre completo
				int spaces = 1;
				String[] array = usuario.getNombre().toUpperCase().split(" ");
				spaces = array.length-1; 
				
				String nombres="";
				String apePaterno="";
				String apeMaterno="";
				
			    if (spaces == 1) {
			        System.out.println("El nombre está en formato 'NOMBRE' 'APELLIDO'");
			        apePaterno=array[0];
			        nombres=array[1];
			        
			    } else if (spaces == 2) {
			        System.out.println("El nombre está en formato 'NOMBRE' 'APELLIDO1' 'APELLIDO2'");
			        apePaterno=array[0];	        
			        apeMaterno=array[1];
			        nombres=array[2];
			        
			    } else if (spaces >= 3) {
			        System.out.println("El nombre está en formato 'NOMBRE1' 'NOMBRE2' 'APELLIDO1' 'APELLIDO2'");
			        apePaterno=array[0];	        
			        apeMaterno=array[1];
			        nombres=array[2].concat(array[3]);
			        
			    } else {
			        System.out.println("El nombre está en formato incorrecto");
			    }
				
			    val = ObjectUtil.getVectorComponent("nombreBenef",nombres);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("apellidoPatBenef",apePaterno);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("apellidoMatBenef",apeMaterno);
				valores.addElement(val);
				
				//MGL - fin
		
		/*
		String[] array = usuario.getNombre().toUpperCase().split(" ");
		
		if(array.length >= 3){
			
			String nombres = "";
			String primerNombre=array[2];
		
			if (array[3] != null){
				nombres = primerNombre.concat(" ").concat(array[3]);
			}
			else{
				nombres=primerNombre;
			}
			val = ObjectUtil.getVectorComponent("nombreBenef",nombres);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("apellidoPatBenef",array[0]);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("apellidoMatBenef",array[1]);
			valores.addElement(val);
		}
		*/
		
		if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
						
			if(transfer.getMoneda().equals(Constante.MONEDA_SOL)){
				
				transaccion = "TB11";
				
				val = ObjectUtil.getVectorComponent("tipoTransfer","1");
				valores.addElement(val);
				
				String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
												
				val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(transfer.getAfiliacion().getNroDocumento(),"0",12));
				valores.addElement(val);
				
		
				val = ObjectUtil.getVectorComponent("codigoTrx","1480");
				valores.addElement(val);
				  
			}else{
				if(transfer.getMoneda().equals(Constante.MONEDA_DOLAR)){
					
					
				transaccion = "TB14";
				
				String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
													
				val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
					
				val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(transfer.getAfiliacion().getNroDocumento(),"0",12));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("tipoDocumentoBenef",transfer.getAfiliacion().getTipoDocumentoBenef());
				valores.addElement(val);
								
				
				val = ObjectUtil.getVectorComponent("tipoTransfer","4");
				valores.addElement(val);
			
				val = ObjectUtil.getVectorComponent("codigoTrx","1480");
				valores.addElement(val);
				
				
				val = ObjectUtil.getVectorComponent("comisionBN",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
				valores.addElement(val);
				}
			}
			
			
		}else if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			
			
			if(transfer.getMoneda().equals(Constante.MONEDA_SOL)){
				
				transaccion = "TB15";
				
				
				
				val = ObjectUtil.getVectorComponent("tipoTransfer","4");
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("codigoTrx","8480");
				valores.addElement(val);
								
				val = ObjectUtil.getVectorComponent("comisionBN",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
				valores.addElement(val);
				
				
				
				
			}else{
				if(transfer.getMoneda().equals(Constante.MONEDA_DOLAR)){
					transaccion = "TB13";
					
					val = ObjectUtil.getVectorComponent("tipoTransfer","1");
					valores.addElement(val);
					
					val = ObjectUtil.getVectorComponent("codigoTrx","8480");
					valores.addElement(val);
					
				}
			}
					
			val = ObjectUtil.getVectorComponent("tipoPlaza",transfer.getTipoPlaza());
			valores.addElement(val);
					    
		String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
			    val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
			
				val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(transfer.getAfiliacion().getNroDocumento(),"0",12));
				valores.addElement(val);

			
		    
		}
		val = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(val);
		
		Vector ctas=new Vector();
		Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,transfer.getTipoConsulta());
		ctas.addElement(v);
		
	    Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		
	
		
		String montoValida = ObjectUtil.replaceChar(transfer.getTotal(),',',"");
		montoValida = ObjectUtil.replaceChar(montoValida,'.',"");
		
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(ObjectUtil.tramaToBigDecimal(montoValida,2));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transfer.getComision()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transfer.getItf()));

		
		String beneficiario= transferencia.getBeneficiario();
		transfer.transferirInterBancoLinea(t,usuario);
			
		transfer.setBeneficiario(beneficiario);
		
		GeneralTest gt= new GeneralTest();
	
			
			if(transfer.getArrayRuleException()!=null){
			    throw transfer.getArrayRuleException();
			}else{
				if(transfer.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
					gt.generarLog2(t,usuario,Constante.REFRENDO_TRANSFERENCIA_INTERBANCARIA_LINEA_RECHAZO,transferencia,request);
				}else{			
					gt.generarLog2(t,usuario,Constante.REFRENDO_TRANSFERENCIA_INTERBANCARIA_LINEA,transferencia,request);
				}	
			}	

	    return transfer;
	}
	
	public Transferencia transferirInterBancoCelLinea(String moneda, Transferencia transferencia, Usuario usuario ,String remoteAddress, HttpServletRequest request)throws Exception {
   		
		TransferenciaImpl transfer = (TransferenciaImpl)transferencia;
	    transfer.setInterbancariaLinea(true);
	  
	    String transaccion = "";
	    Vector valores 	= new Vector();
		Vector val 		= new Vector();
		
	
		String importeRefTransf = ObjectUtil.replaceChar(transfer.getImporteTransferido().trim(),',',"");
		val = ObjectUtil.getVectorComponent("importeTransferencia",ObjectUtil.formatearMontoTrama(new BigDecimal(importeRefTransf)));
		valores.addElement(val);
					
		val = ObjectUtil.getVectorComponent("tipoAfectacion","2");
		valores.addElement(val);
				
		val = ObjectUtil.getVectorComponent("comision",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaOrigen",transfer.getCuentaOrigen().getNumeroProducto());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cciOrigen",transfer.getCciOrigen());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaDestino",transfer.getCciContacto());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("numCelularOrigen",transfer.getAfiliacion().getNumeroCelular());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("numCelularDestino",transfer.getAfiliacion().getNumeroServicio());
		valores.addElement(val);
		
//		System.out.println("moneda:"+transfer.getCodMoneda());
		val = ObjectUtil.getVectorComponent("moneda",transfer.getCodMoneda());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("correo1",transfer.getAfiliacion().getCorreo().toUpperCase());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("correo2",transfer.getAfiliacion().getServidorCorreo().toUpperCase());
		valores.addElement(val);

		val = ObjectUtil.getVectorComponent("identificadorCCE1",transfer.getAfiliacion().getIdentificadorCCE1());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("identificadorCCE2",transfer.getAfiliacion().getIdentificadorCCE2());
		valores.addElement(val);

		//val = ObjectUtil.getVectorComponent("cciOrigen", usuario.getCciCliente());
		//valores.addElement(val);
		//val = ObjectUtil.getVectorComponent("ruteo1","014002" + transfer.getAfiliacion().getCodigoServicio());
		val = ObjectUtil.getVectorComponent("ruteo1","0140020" + transfer.getCciContacto().substring(0, 3));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("impReferenciaMon",ObjectUtil.formatearMontoTrama(transfer.getImporte()));
		valores.addElement(val);
						
		val = ObjectUtil.getVectorComponent("numTarjetaDebito",usuario.getTarjeta().getNumero());
		valores.addElement(val); 
		
		val = ObjectUtil.getVectorComponent("cuentaPropia",transfer.getCuentaPropia());
		valores.addElement(val);
		
		//MGL - ini - extraccion de nombre completo
		int spaces = 1;
		String[] array = usuario.getNombre().toUpperCase().split(" ");
		spaces = array.length-1; 
		
		String nombres="";
		String apePaterno="";
		String apeMaterno="";
		
	    if (spaces == 1) {
	        System.out.println("El nombre está en formato 'NOMBRE' 'APELLIDO'");
	        apePaterno=array[0];
	        nombres=array[1];
	        
	    } else if (spaces == 2) {
	        System.out.println("El nombre está en formato 'NOMBRE' 'APELLIDO1' 'APELLIDO2'");
	        apePaterno=array[0];	        
	        apeMaterno=array[1];
	        nombres=array[2];
	        
	    } else if (spaces >= 3) {
	        System.out.println("El nombre está en formato 'NOMBRE1' 'NOMBRE2' 'APELLIDO1' 'APELLIDO2'");
	        apePaterno=array[0];	        
	        apeMaterno=array[1];
	        nombres=array[2].concat(array[3]);
	        
	    } else {
	        System.out.println("El nombre está en formato incorrecto");
	    }
		
	    val = ObjectUtil.getVectorComponent("nombreBenef",nombres);
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("apellidoPatBenef",apePaterno);
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("apellidoMatBenef",apeMaterno);
		valores.addElement(val);
		
		//MGL - fin
		
		/*
		String[] array = usuario.getNombre().toUpperCase().split(" ");
		
		if(array.length >= 3){
			
			String nombres = "";
			String primerNombre=array[2];
		
			if (array[3] != null){
				nombres = primerNombre.concat(" ").concat(array[3]);
			}
			else{
				nombres=primerNombre;
			}
			val = ObjectUtil.getVectorComponent("nombreBenef",nombres);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("apellidoPatBenef",array[0]);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("apellidoMatBenef",array[1]);
			valores.addElement(val);
		}
		*/
		
		if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
						
			if(transfer.getMoneda().equals(Constante.MONEDA_SOL)){
				
				transaccion = "TB11";
				
				val = ObjectUtil.getVectorComponent("tipoTransfer","1");
				valores.addElement(val);
				
				String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
												
				val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(transfer.getAfiliacion().getNroDocumento(),"0",12));
				valores.addElement(val);
				
		
				val = ObjectUtil.getVectorComponent("codigoTrx","1480");
				valores.addElement(val);
				  
			}else{
				if(transfer.getMoneda().equals(Constante.MONEDA_DOLAR)){
					
					
				transaccion = "TB14";
				
				String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
													
				val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
					
				val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(transfer.getAfiliacion().getNroDocumento(),"0",12));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("tipoDocumentoBenef",transfer.getAfiliacion().getTipoDocumentoBenef());
				valores.addElement(val);
								
				
				val = ObjectUtil.getVectorComponent("tipoTransfer","4");
				valores.addElement(val);
			
				val = ObjectUtil.getVectorComponent("codigoTrx","1480");
				valores.addElement(val);
				
				
				val = ObjectUtil.getVectorComponent("comisionBN",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
				valores.addElement(val);
				}
			}
			
			
		}else if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			
			
			if(transfer.getMoneda().equals(Constante.MONEDA_SOL)){
				
				transaccion = "TB15";
				
				
				
				val = ObjectUtil.getVectorComponent("tipoTransfer","4");
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("codigoTrx","8480");
				valores.addElement(val);
								
				val = ObjectUtil.getVectorComponent("comisionBN",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
				valores.addElement(val);
				
				
				
				
			}else{
				if(transfer.getMoneda().equals(Constante.MONEDA_DOLAR)){
					transaccion = "TB13";
					
					val = ObjectUtil.getVectorComponent("tipoTransfer","1");
					valores.addElement(val);
					
					val = ObjectUtil.getVectorComponent("codigoTrx","8480");
					valores.addElement(val);
					
				}
			}
					
			val = ObjectUtil.getVectorComponent("tipoPlaza",transfer.getTipoPlaza());
			valores.addElement(val);
					    
		String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
			    val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
			
				val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(transfer.getAfiliacion().getNroDocumento(),"0",12));
				valores.addElement(val);

			
		    
		}
		val = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(val);
		
		Vector ctas=new Vector();
		Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,transfer.getTipoConsulta());
		ctas.addElement(v);
		
	    Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		
	
		
		String montoValida = ObjectUtil.replaceChar(transfer.getTotal(),',',"");
		montoValida = ObjectUtil.replaceChar(montoValida,'.',"");
		
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(ObjectUtil.tramaToBigDecimal(montoValida,2));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transfer.getComision()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transfer.getItf()));

		
		String beneficiario= transferencia.getBeneficiario();
		transfer.transferirInterBancoLinea(t,usuario);
			
		transfer.setBeneficiario(beneficiario);
		
		GeneralTest gt= new GeneralTest();
	
			
			if(transfer.getArrayRuleException()!=null){
			    throw transfer.getArrayRuleException();
			}else{
				if(transfer.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
					gt.generarLog2(t,usuario,Constante.REFRENDO_TRANSFERENCIA_INTERBANCARIA_LINEA_RECHAZO,transferencia,request);
				}else{			
					gt.generarLog2(t,usuario,Constante.REFRENDO_TRANSFERENCIA_INTERBANCARIA_LINEA,transferencia,request);
				}	
			}	

	    return transfer;
	}
	
	public Transferencia transferirInterBanco(String moneda, Transferencia transferencia, Usuario usuario ,String remoteAddress, HttpServletRequest request)throws Exception {
	    TransferenciaImpl transfer = (TransferenciaImpl)transferencia;
	    transfer.setInterbancaria(true);
	    String transaccion = "TB05";
	    Vector valores 	= new Vector();
		Vector val 		= new Vector();
		
		//if(Constante.VER_LOG) System.out.println("Moneda - transferirInterBanco:"+moneda);
		val = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(val);
		
		//val = ObjectUtil.getVectorComponent("importeReferencial",ObjectUtil.formatearMontoTrama(new BigDecimal(transfer.getImporte())));
		String importeRefTransf = ObjectUtil.replaceChar(transfer.getImporteTransferido().trim(),',',"");
		val = ObjectUtil.getVectorComponent("importeReferencial",ObjectUtil.formatearMontoTrama(new BigDecimal(importeRefTransf)));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("tipoTransfer",transfer.getTipoTransferencia());
		valores.addElement(val);
		
		//if(Constante.VER_LOG) System.out.println("transfer.getTipoAfectacion():"+transfer.getTipoAfectacion());
		val = ObjectUtil.getVectorComponent("tipoAfectacion",transfer.getTipoAfectacion());
		valores.addElement(val);
		
		//val = ObjectUtil.getVectorComponent("comision",ObjectUtil.formatearMontoTrama(new BigDecimal(transfer.getComision())));
		val = ObjectUtil.getVectorComponent("comision",ObjectUtil.formatearMontoTrama(transfer.getComisionAbono()));
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaOrigen",transfer.getCuentaOrigen().getNumeroProducto());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cuentaDestino",transfer.getAfiliacion().getCuenta().getNumeroProducto());
		
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("beneficiario",transfer.getAfiliacion().getBeneficiario());
		
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("impReferenciaMon",ObjectUtil.formatearMontoTrama(transfer.getImporte()));
		valores.addElement(val);
		
		


		
		if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			if (transfer.getAfiliacion().getCuentaPropia().equals("N")){
			    val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("codigoTrx","1410");
				valores.addElement(val);
				//if(Constante.VER_LOG) System.out.println("codigoTrx:1410");
				
			}else if (transfer.getAfiliacion().getCuentaPropia().equals("S")){
			    String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
			    
			    val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("codigoTrx","1411");
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("numeroDocumento",transfer.getAfiliacion().getNroDocumento());
				
				valores.addElement(val);
				
			}
			
			if(moneda.equals(Constante.MONEDA_DOLAR)){
			    val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
				valores.addElement(val);
			}
			
		}else if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
		    
		    if (transfer.getAfiliacion().getCuentaPropia().equals("N")){
			    
			    val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("codigoTrx","8410");
				valores.addElement(val);
				
				
			}else if (transfer.getAfiliacion().getCuentaPropia().equals("S")){
			    //TODO cambiando esta parte(mismo titular).
			    String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
			    val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("codigoTrx","8411");
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("numeroDocumento",transfer.getAfiliacion().getNroDocumento());
				valores.addElement(val);
				
			}
			
		    if(moneda.equals(Constante.MONEDA_SOL)){
			    val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
				valores.addElement(val);
			}else if (moneda.equals(Constante.MONEDA_DOLAR)){
			    
			}
		    
		}else if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			if (transfer.getAfiliacion().getCuentaPropia().equals("N")){
			    String codigoTransaccion = "";
			    
			    val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				if(moneda.equals(Constante.MONEDA_SOL)){
				    codigoTransaccion = "0410";
				}
				else if (moneda.equals(Constante.MONEDA_DOLAR)){
				    codigoTransaccion = "0410";
				    val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
					valores.addElement(val);
				}
				
				val = ObjectUtil.getVectorComponent("codigoTrx",codigoTransaccion);
				valores.addElement(val);
				
			}else if (transfer.getAfiliacion().getCuentaPropia().equals("S")){
			    String codigoTransaccion = "";
			    String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
			    
			    val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("numeroDocumento",transfer.getAfiliacion().getNroDocumento());
				valores.addElement(val);
				
				if(moneda.equals(Constante.MONEDA_SOL)){
				    codigoTransaccion = "0411";
				}
				else if (moneda.equals(Constante.MONEDA_DOLAR)){
				    codigoTransaccion = "0411";
				    val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
					valores.addElement(val);
				}
				
				val = ObjectUtil.getVectorComponent("codigoTrx",codigoTransaccion);
				valores.addElement(val);
				
				
		  }
		}else if (transfer.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			if (transfer.getAfiliacion().getCuentaPropia().equals("N")){
			    String codigoTransaccion = "";
			    val = ObjectUtil.getVectorComponent("itf",ObjectUtil.formatearMontoTrama(transfer.getItf()));
				valores.addElement(val);
				
				if(moneda.equals(Constante.MONEDA_SOL)){
				    codigoTransaccion = "6410";
				}
				else if (moneda.equals(Constante.MONEDA_DOLAR)){
				    codigoTransaccion = "6410";
				}
				
				val = ObjectUtil.getVectorComponent("codigoTrx",codigoTransaccion);
				valores.addElement(val);
				
			}else if (transfer.getAfiliacion().getCuentaPropia().equals("S")){
			    String codigoTransaccion = "";
			    
			    String tipDoc = ObjectUtil.getCodDocBeneficiarioTIB(transfer.getAfiliacion().getTipoDocumento());
			    
			    val = ObjectUtil.getVectorComponent("tipoDocumento",tipDoc);
				valores.addElement(val);
				
				val = ObjectUtil.getVectorComponent("numeroDocumento",transfer.getAfiliacion().getNroDocumento());
				valores.addElement(val);
				
				if(moneda.equals(Constante.MONEDA_SOL)){
				    codigoTransaccion = "6411";
				    val = ObjectUtil.getVectorComponent("importeNeto",ObjectUtil.formatearMontoTrama(transfer.getImporteNeto()));
					valores.addElement(val);
				}else if (moneda.equals(Constante.MONEDA_DOLAR)){
				    codigoTransaccion = "6411";
				}
				
				val = ObjectUtil.getVectorComponent("codigoTrx",codigoTransaccion);
				valores.addElement(val);
		  }
		}
		
		Vector ctas=new Vector();
		Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,transfer.getTipoConsulta());
		ctas.addElement(v);
		
	    Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
		
		//Validando el limite del importe a transferir 
	    //if(Constante.VER_LOG) System.out.println("monto....."+transfer.getTotal());
		
		/**
		 * CAMBIO SOLICITADO POR CANALES VIRTUALES (LP)
		 * FECHA : 10/04/2012
		 * MOTIVO: No realizar el cálculo de la sumatoria de Límite máximo sin Comisión e ITF
		 */
		
		String montoValida = ObjectUtil.replaceChar(transfer.getTotal(),',',"");
		montoValida = ObjectUtil.replaceChar(montoValida,'.',"");
		
		BigDecimal mont= new BigDecimal("0.00");
		mont=mont.add(ObjectUtil.tramaToBigDecimal(montoValida,2));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transfer.getComision()));
		mont=mont.subtract(ObjectUtil.deFormatearMonto(transfer.getItf()));

		
		/**
		 * CAMBIO SOLICITADO POR HPO
		 * FECHA : 16/01/2013
		 * MOTIVO: Pedido de la Gerencia, no validar importe maximo para ciertas cuentas
		 */
		
//		//PRODUCCION
//		
//		
//		if(!transferencia.getCuentaOrigen().getNumeroProducto().equals("04007327337") && !transferencia.getCuentaOrigen().getNumeroProducto().equals("04040571936")){
//		//&& !transferencia.getCuentaOrigen().getNumeroProducto().equals("04000921810")){   // Se quita el 26/02/2013 segun documento
//	
//			if(transferencia.getCuentaOrigen().getNumeroProducto().equals("04048287021") || transferencia.getCuentaOrigen().getNumeroProducto().equals("04048286955") ){
//				FacadeFactory.getGeneralFacade().validarLimiteImporteConTope(transferencia.getCuenta(),mont);
//			}
//			else{
//				
//				FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,transferencia.getCuenta(),mont);
//			}
//	
//		}
		
	//CERTIFICACION
		

//		if(!transferencia.getCuentaOrigen().getNumeroProducto().equals("04011508524") && !transferencia.getCuentaOrigen().getNumeroProducto().equals("08046307578")){
//			if(transferencia.getCuentaOrigen().getNumeroProducto().equals("04045953500") || transferencia.getCuentaOrigen().getNumeroProducto().equals("04045953519") ){
//				FacadeFactory.getGeneralFacade().validarLimiteImporteConTope(transferencia.getCuenta(),mont);
//			}
//			else{
//				
//				FacadeFactory.getGeneralFacade().validarLimiteImporte(transferencia.getCuenta(),mont);
//			}
//		}
				
		transfer.transferirInterBanco(t,usuario);
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_TRANSFERENCIA_INTERBANCARIA,transferencia,request);
		if(transfer.getArrayRuleException()!=null){
		    throw transfer.getArrayRuleException();
		}
	    return transferencia;
	}

	public BigDecimal calcularCambio(String importe,BigDecimal tipocambio){
		BigDecimal dolar = ObjectUtil.deFormatearMonto(importe);
		dolar = dolar.divide(tipocambio,BigDecimal.ROUND_HALF_UP).setScale(2);
		return dolar;
	}
	
	
	public Transferencia getConsultaTransferenciaIBLinea(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String cciOrigen,String remoteAddress) throws Exception {
	  		   

			
		   String transaccion = ""; 
			
			Vector valores 	= new Vector();
			Vector val 		= new Vector();
			
			String opt 				= "";
			String tipTransferencia = "1";
			String tipAfectacion    = "2";
			
			if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			    opt="04";
			    
			    if(codMoneda.equals(Constante.MONEDA_SOL)){
			    
			    	transaccion = "TB10";
				    val = ObjectUtil.getVectorComponent("codigoTrx","1480");
					valores.addElement(val);
			    }else{
			    	
			    	if(codMoneda.equals(Constante.MONEDA_DOLAR)){
			    		
			    		transaccion = "TB10";
					    val = ObjectUtil.getVectorComponent("codigoTrx","1480");
						valores.addElement(val);
			    	}
			    
			    }
			    				
			
		    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
		        opt="08";
		        
		        if(codMoneda.equals(Constante.MONEDA_SOL)){
		        	
		        	
			    		
			    		transaccion = "TB12";
					    val = ObjectUtil.getVectorComponent("codigoTrx","8480");
						valores.addElement(val);
			    	
			    	
			    }else{
			    	
			    	if(codMoneda.equals(Constante.MONEDA_DOLAR)){
			    		
			    		transaccion = "TB12";
					    val = ObjectUtil.getVectorComponent("codigoTrx","8480");
						valores.addElement(val);
			    	}
			    
			    }
	
		    }
		    
			val = ObjectUtil.getVectorComponent("transaccion",transaccion);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("importeReferencial",ObjectUtil.formatearMontoTrama(new BigDecimal(monto)));
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("tipoTransfer",tipTransferencia);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("tipoAfectacion",tipAfectacion);
			valores.addElement(val);
							
			val = ObjectUtil.getVectorComponent("tipoDocumento",ObjectUtil.getCodDocBeneficiarioTIB(afiliacion.getTipoDocumento()));
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("cciOrigen",cciOrigen);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("cuentaOrigen",cuenta.getNumeroProducto());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("moneda",codMoneda);
			valores.addElement(val);
			
			String tipoCuenta = afiliacion.getCuentaPropia();
			
			
			val = ObjectUtil.getVectorComponent("cuentaDestino",afiliacion.getNumeroServicio());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(afiliacion.getNroDocumento(),"0",12));
			valores.addElement(val);
			
		
			val = ObjectUtil.getVectorComponent("correo1",afiliacion.getCorreo().toUpperCase());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("correo2",afiliacion.getServidorCorreo().toUpperCase());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("numTarjetaDebito",usuario.getTarjeta().getNumero());
			valores.addElement(val);	
			
			//val = ObjectUtil.getVectorComponent("ruteo1","0140020054");
			val = ObjectUtil.getVectorComponent("ruteo1","0140020" + afiliacion.getCodigoServicio());
			valores.addElement(val);
			
			Vector ctas=new Vector();
			Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,opt);
			ctas.addElement(v);
			
			Transaction t = new Transaction(transaccion);
			t.setValues(valores);
			t.setCuentas(ctas);
			
				
			
			TransferenciaImpl transferencia = new TransferenciaImpl();
			transferencia.getConsultaTransferenciaIBLinea(t,usuario);
			transferencia.setMoneda(codMoneda);
			
			//if(Constante.VER_LOG) System.out.println("End getOtroTitularTransferenciaIB");
			return transferencia;
		}
	//transferencia inmediata interop .contacto celular
	public Transferencia getConsultaTransferenciaCelIBLinea(String codMoneda,String monto,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario ,String cciOrigen ,String entidadCCE,String remoteAddress) throws Exception {
	
		   System.out.println("getConsultaTransferenciaCelIBLinea...");
		
		   String transaccion = ""; 
			
			Vector valores 	= new Vector();
			Vector val 		= new Vector();
			
			String opt 				= "";
			String tipTransferencia = "1";
			String tipAfectacion    = "2";
			
			if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			    opt="04";
			    
			    if(codMoneda.equals(Constante.MONEDA_SOL)){
			    
			    	transaccion = "TB10";
				    val = ObjectUtil.getVectorComponent("codigoTrx","1480");
					valores.addElement(val);
			    }else{
			    	
			    	if(codMoneda.equals(Constante.MONEDA_DOLAR)){
			    		
			    		transaccion = "TB10";
					    val = ObjectUtil.getVectorComponent("codigoTrx","1480");
						valores.addElement(val);
			    	}
			    
			    }
			    				
			
		    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
		        opt="08";
		        
		        if(codMoneda.equals(Constante.MONEDA_SOL)){
		        	
		        	
			    		
			    		transaccion = "TB12";
					    val = ObjectUtil.getVectorComponent("codigoTrx","8480");
						valores.addElement(val);
			    	
			    	
			    }else{
			    	
			    	if(codMoneda.equals(Constante.MONEDA_DOLAR)){
			    		
			    		transaccion = "TB12";
					    val = ObjectUtil.getVectorComponent("codigoTrx","8480");
						valores.addElement(val);
			    	}
			    
			    }
		  		
		    }
		    
			val = ObjectUtil.getVectorComponent("transaccion",transaccion);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("importeReferencial",ObjectUtil.formatearMontoTrama(new BigDecimal(monto)));
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("tipoTransfer",tipTransferencia);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("tipoAfectacion",tipAfectacion);
			valores.addElement(val);
					
			
			
			val = ObjectUtil.getVectorComponent("tipoDocumento",ObjectUtil.getCodDocBeneficiarioTIB(afiliacion.getTipoDocumento()));
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("cuentaOrigen",cuenta.getNumeroProducto());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("moneda",codMoneda);
			valores.addElement(val);
			
			String tipoCuenta = afiliacion.getCuentaPropia();
			
			
			val = ObjectUtil.getVectorComponent("cuentaDestino",entidadCCE);
			valores.addElement(val);
			
			
			val = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(afiliacion.getNroDocumento(),"0",12));
			valores.addElement(val);
			
		
			val = ObjectUtil.getVectorComponent("correo1",afiliacion.getCorreo().toUpperCase());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("correo2",afiliacion.getServidorCorreo().toUpperCase());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("numTarjetaDebito",usuario.getTarjeta().getNumero());
			valores.addElement(val);	
			
			val = ObjectUtil.getVectorComponent("cciOrigen",cciOrigen);
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("numCelularOrigen",afiliacion.getNumeroCelular());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("numCelularDestino",afiliacion.getNumeroServicio());
			valores.addElement(val);
			
			//MGL - consultar a Mily
			//val = ObjectUtil.getVectorComponent("ruteo1","0140020054");
			val = ObjectUtil.getVectorComponent("ruteo1","014002" + afiliacion.getCodigoServicio());
			valores.addElement(val);
			
			Vector ctas=new Vector();
			Vector v= ObjectUtil.getVectorBlankCuentas(remoteAddress,opt);
			ctas.addElement(v);
			
			Transaction t = new Transaction(transaccion);
			t.setValues(valores);
			t.setCuentas(ctas);
			
				
			
			TransferenciaImpl transferencia = new TransferenciaImpl();
			transferencia.getConsultaTransferenciaIBLinea(t,usuario);
			transferencia.setMoneda(codMoneda);
			
			
			//if(Constante.VER_LOG) System.out.println("End getOtroTitularTransferenciaIB");
			return transferencia;
		}
	
	public Transferencia consultaBarridoIB(String numCelular, Usuario usuario ,String remoteAddress) throws Exception {
   		
		
		TransferenciaImpl transferencia = new TransferenciaImpl();
	

		
		return transferencia;
	}
	
	public Transferencia consultaClienteIB(String cic, Usuario usuario ,String remoteAddress) throws Exception {
   		
		
		TransferenciaImpl transferencia = new TransferenciaImpl();
	

		
		return transferencia;
	}
	
	public Transferencia registrarContactoIB(Cuenta cuenta, Usuario usuario ,String remoteAddress) throws Exception {
   		
		
		TransferenciaImpl transferencia = new TransferenciaImpl();
	

		
		return transferencia;
	}


	

}
