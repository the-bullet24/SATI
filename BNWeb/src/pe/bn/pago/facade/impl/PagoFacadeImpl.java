/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.facade.impl;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.DepositoJudicial;
import pe.bn.pago.domain.Multipago;
import pe.bn.pago.domain.PagoCuponera;
import pe.bn.pago.domain.PagoFactura;
import pe.bn.pago.domain.PagoFacturaWS;
import pe.bn.pago.domain.PagoPrestamoMultired;
import pe.bn.pago.domain.PagoSedapal;
import pe.bn.pago.domain.PagoTarjeta;
import pe.bn.pago.domain.PagoTasas;
import pe.bn.pago.domain.PagoTelefono;
import pe.bn.pago.domain.PagoUniversidad;
import pe.bn.pago.domain.impl.DepositoJudicialImpl;
import pe.bn.pago.domain.impl.MultipagoImpl;
import pe.bn.pago.domain.impl.PagoCuponeraImpl;
import pe.bn.pago.domain.impl.PagoFacturaImpl;
import pe.bn.pago.domain.impl.PagoFacturaWSImpl;
import pe.bn.pago.domain.impl.PagoPrestamoMultiredImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.pago.domain.impl.PagoUniversidadImpl;
import pe.bn.pago.facade.PagoFacade;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.common.Funciones;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.DetalleTributo;
import pe.cosapi.domain.Entidad;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Tributo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleTributoImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;

//import pe.cosapi.domain.impl.OperacionImpl;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoFacadeImpl implements PagoFacade {
	public PagoPrestamoMultired getPagoPrestamoMultired(String cNroCtaPrestamoSel, String cNroDesembolsoSel, String transaccion, Usuario usuario, String remoteAddress) throws Exception{
		PagoPrestamoMultiredImpl pagoPrestamoMultiredImpl = new PagoPrestamoMultiredImpl();

		
		Transaction t = new Transaction(transaccion);
		CuentaImpl cuenta = null;
		CuentaImpl cuentaPrestamo = null;
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
		}
		
		// Obteiendio la cuenta de prestamo
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuentaPrestamo = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
				break;
			}
		}
		
		if(cuenta==null)
			throw new ArrayRuleException("El cliente no posee una cuenta de ahorros en soles");
		

		Vector valores = new Vector();
		Vector valor = new Vector();

		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(cNroCtaPrestamoSel);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("numeroDesembolso");
		valor.addElement(cNroDesembolsoSel);
		valores.add(valor);
		
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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);				
		
		
		pagoPrestamoMultiredImpl.getPrestamoMultired(t,cuenta,usuario);
						
		return (PagoPrestamoMultired)pagoPrestamoMultiredImpl;
	}


	public PagoPrestamoMultired pagarPrestamoMultired(String transaccion, Usuario usuario, String remoteAddress, PagoPrestamoMultired pagoPrestamo) throws Exception {		 	
	    
		PagoPrestamoMultiredImpl pagoPrestamoMultired =(PagoPrestamoMultiredImpl)pagoPrestamo; 
		
		PagoPrestamoMultiredImpl pagoPrestamoMultiredImpl = new PagoPrestamoMultiredImpl();////aquiii new/////
		
		Transaction t = new Transaction(transaccion);
		//////
		
		CuentaImpl cuenta = null;
		CuentaImpl cuentaPrestamo = null;
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
		}
		/**
		// Obteiendio la cuenta de prestamo
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuentaPrestamo = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
				break;
			}
		}
		**/
		if(cuenta==null)
			throw new ArrayRuleException("El cliente no posee una cuenta prestamos");
		
		
		Vector valores = new Vector();
		Vector valor = new Vector();

		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(pagoPrestamoMultired.getCuenta().getNumeroProducto());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroCuota");
		valor.addElement(pagoPrestamoMultired.getNroCuota());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("tipoAfectacion");
		valor.addElement("2");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroTrx");
		valor.addElement("3024");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("numeroDesembolso");
		valor.addElement(pagoPrestamoMultired.getNroDesembolso());
		valores.add(valor);
	

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

		t.setValues(valores);
		t.setCuentas(ctas);
		
		//pagoPrestamoMultiredImpl.getPrestamoMultired(t,cuenta,usuario);
		
		//return (PagoPrestamoMultired)pagoPrestamoMultiredImpl;
		//}
		
	
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,pagoPrestamoMultired.getCuenta(),new BigDecimal(ObjectUtil.replaceChar(pagoPrestamoMultired.getImporte(),',',"")));
		pagoPrestamoMultired.pagarPrestamoMultired(t,usuario);
		//return pagoPrestamoMultired;
		
        return (PagoPrestamoMultired)pagoPrestamoMultiredImpl;
		
	}


	/* (sin Javadoc)
	 * @see pe.bn.pago.facade.PagoFacade#getPagoTarjeta(pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, java.lang.String)
	 */
	public PagoTarjeta getPagoTarjeta(String transaccion,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario,String moneda,String monto, String remoteAddress) throws Exception {

		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("importeReferencial");
		valor.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(monto)));
		valores.add(valor);
		
		String opcion = "";
		
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL)&&moneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("5");
			valores.add(valor);			
			
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1420");
			valores.add(valor);
			
			opcion = "01";
		}
		else if(cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL)&&moneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("6");
			valores.add(valor);						
			
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1420");
			valores.add(valor);
			
			opcion = "01";
		}
		else if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&moneda.equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("6");
			valores.add(valor);			
		 
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8420");
			valores.add(valor);
			
			opcion = "02";
		}
		else if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&moneda.equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("5");
			valores.add(valor);						
			
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8420");
			valores.add(valor);
			
			opcion = "02";
		}

		
		valor = new Vector();
		valor.addElement("tipoAfectacion");
		valor.addElement("7");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("banco");
		valor.addElement(afiliacion.getCodigoServicio().trim());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("cuentaAhorros");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
						
		valor = new Vector();
		valor.addElement("tarjeta");
		valor.addElement(usuario.getTarjeta().getNumero());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("CCI");
		valor.addElement(afiliacion.getNumeroServicio().trim());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("beneficiario");
		valor.addElement(ObjectUtil.cortarCadena(afiliacion.getObjBenef().getApellidoPaterno()+" "+afiliacion.getObjBenef().getApellidoMaterno()+" "+afiliacion.getObjBenef().getNombre(),30));
		valores.add(valor);

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
				
		t.setValues(valores);
		t.setCuentas(ctas);				
		
		PagoTarjetaImpl pagoTarjetaImpl = new PagoTarjetaImpl(t,cuenta,afiliacion,moneda, new BigDecimal(monto));		
		
		return (PagoTarjeta)pagoTarjetaImpl;
	}
	
	public PagoTarjeta getPagoTarjetaEnLinea(String transaccion,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario,String moneda,String monto, String remoteAddress) throws Exception {

		
		
		transaccion = "";
		//Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("importeReferencial");
		valor.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(monto)));
		valores.add(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("moneda",moneda);
		valores.addElement(valor);
		
		String opcion = "";
		
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL)&&moneda.equals(Constante.MONEDA_SOL)){
			
			transaccion = "PG02";
			
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("5");
			valores.add(valor);			
			
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1481");
			valores.add(valor);
			
			opcion = "01";
		}
		else if(cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL)&&moneda.equals(Constante.MONEDA_DOLAR)){
			
			transaccion = "PG02";
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("6");
			valores.add(valor);						
			
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1481");
			valores.add(valor);
			
			opcion = "01";
		}
		else if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&moneda.equals(Constante.MONEDA_SOL)){
			
			transaccion = "PG04";
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("6");
			valores.add(valor);			
		 
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8481");
			valores.add(valor);
			
			opcion = "02";
		}
		else if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&moneda.equals(Constante.MONEDA_DOLAR)){
			
			transaccion = "PG04";
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("6");
			valores.add(valor);						
			
			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8481");
			valores.add(valor);
			
			opcion = "02";
		}

		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("tipoDocumento",ObjectUtil.getCodDocBeneficiarioTIB(afiliacion.getTipoDocumento()));
		valores.addElement(valor);
		
		/*MGL*/
		valor = new Vector();
		valor.addElement("tipoAfectacion");
		valor.addElement("7");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("banco");		
		valor.addElement(afiliacion.getCodigoServicio().trim());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cuentaOrigen");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		
		/*MGL - INI - reemplazar*/
//		valor = new Vector();
//		valor.addElement("tarjeta");
//		valor.addElement(usuario.getTarjeta().getNumero());
//		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("numTarjetaDebito");
		valor.addElement(usuario.getTarjeta().getNumero());
		valores.add(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("ruteo1","0140020" + afiliacion.getCodigoServicio().trim());
		valores.addElement(valor);
		
		String cci = usuario.getCciCliente();
		System.out.println("cci::::"+cci);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("cciOrigen",cci);
		valores.addElement(valor);
		
		
		/*MGL - FIN*/
		
		valor = new Vector();
		valor.addElement("cuentaDestino");
		valor.addElement(afiliacion.getNumeroServicio().trim());
		valores.add(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("numeroDocumento",Funciones.lpad(afiliacion.getNroDocumento(),"0",12));
		valores.add(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("correo1",afiliacion.getCorreo().toUpperCase());
		valores.addElement(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("correo2",afiliacion.getServidorCorreo().toUpperCase());
		valores.addElement(valor);
		
		valor = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(valor);
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		//v.addElement(opcion);
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
		Transaction t = new Transaction(transaccion);		
		t.setValues(valores);
		t.setCuentas(ctas);				
		
	
		
		PagoTarjetaImpl pagoTarjetaImpl = new PagoTarjetaImpl();
		pagoTarjetaImpl.getPagoTarjetaEnLinea(t,cuenta,afiliacion,moneda, new BigDecimal(monto));
		
		return (PagoTarjeta)pagoTarjetaImpl;
	}

	
	public PagoTarjeta pagarTarjeta(String transaccion, PagoTarjetaImpl pago, Usuario usuario, String remoteAddress, HttpServletRequest request) throws Exception {
	
		String opcion = "01";
		
		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("importeReferencial");
		valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporte()));
		valores.add(valor);
		
		if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)&&pago.getMoneda().equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("5");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1420");
			valores.add(valor);
			
			//System.out.println("pago.getImporteAlCambio():"+pago.getImporteAlCambio());
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			opcion = "01";
		}
		else if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)&&pago.getMoneda().equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("6");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1420");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			opcion = "02";
		}
		else if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&pago.getMoneda().equals(Constante.MONEDA_SOL)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("6");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8420");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			opcion = "03";
		}
		else if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&pago.getMoneda().equals(Constante.MONEDA_DOLAR)){
			valor = new Vector();
			valor.addElement("tipoTransferencia");
			valor.addElement("5");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8420");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			opcion = "04";
		}
		
		
		valor = new Vector();
		valor.addElement("tipoAfectacion");
		valor.addElement("7");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("itf");
		valor.addElement(ObjectUtil.formatearMontoTrama(pago.getItf()));
		valores.add(valor);

		valor = new Vector();
		valor.addElement("bancoDestino");
		valor.addElement(pago.getAfiliacion().getCodigoServicio().trim());
		valores.add(valor);
			
		valor = new Vector();
		valor.addElement("cuentaOrigen");
		valor.addElement(pago.getCuenta().getNumeroProducto());
		valores.add(valor);
		

		valor = new Vector();
		valor.addElement("cuentaDestino");
		valor.addElement(pago.getAfiliacion().getNumeroServicio().trim());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("beneficiario");
		valor.addElement(ObjectUtil.cortarCadena(pago.getAfiliacion().getObjBenef().getApellidoPaterno()+" "+pago.getAfiliacion().getObjBenef().getApellidoMaterno()+" "+pago.getAfiliacion().getObjBenef().getNombre(),30));
		valores.add(valor);

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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);				
		
		pago.pagar(t,usuario);
		//
		GeneralTest gt= new GeneralTest();
		gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TARJETA,pago, request);
		if(pago.getArrayRuleException()!=null){
		    throw pago.getArrayRuleException();
		}

		return (PagoTarjeta)pago;
	}
	
	
	public PagoTarjeta pagarTarjetaEnLinea(String transaccion, PagoTarjetaImpl pago, Usuario usuario, String remoteAddress, HttpServletRequest request) throws Exception {
	
		transaccion = "";
		
		
		Vector valores = new Vector();
		Vector valor = new Vector();
		
				
		valor = new Vector();
		valor.addElement("importeReferencial");
		valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporte()));
		valores.add(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("tipoDocumento",ObjectUtil.getCodDocBeneficiarioTIB(pago.getAfiliacion().getTipoDocumento()));
		valores.addElement(valor);
		
		
//		valor = new Vector();
//		valor = ObjectUtil.getVectorComponent("tipoDocumentoBenef",ObjectUtil.getCodDocBeneficiarioTIB(pago.getAfiliacion().getTipoDocumentoBenef()));
//		valores.addElement(valor);
		
		
		/*MGL - INI*/
		valor = new Vector();
		String numDoc = Funciones.lpad(pago.getAfiliacion().getNroDocumento(),"0",12);
		System.out.println("numDoc:::"+numDoc);
		valor = ObjectUtil.getVectorComponent("numeroDocumento",numDoc);
		valores.add(valor);
		
		/*MGL - FIN*/
		
	
		if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)&&pago.getMoneda().equals(Constante.MONEDA_SOL)){
			
			transaccion = "PG03";
			
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("5");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1481");
			valores.add(valor);
					
			
//			valor = new Vector();
//			valor.addElement("impReferenciaMon");
//			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
//			valores.add(valor);

			
		}
		else if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)&&pago.getMoneda().equals(Constante.MONEDA_DOLAR)){
			
			transaccion = "PG06";
			
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("6");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("1481");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("comisionIB");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteNeto()));
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			
		}
		else if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&pago.getMoneda().equals(Constante.MONEDA_SOL)){
			transaccion = "PG07";
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("6");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8481");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("comisionIB");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteNeto()));
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			
		}
		else if(pago.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)&&pago.getMoneda().equals(Constante.MONEDA_DOLAR)){
			
			transaccion = "PG05";
			
			valor = new Vector();
			valor.addElement("tipoTransfer");
			valor.addElement("5");
			valores.add(valor);

			valor = new Vector();
			valor.addElement("codigoTrx");
			valor.addElement("8481");
			valores.add(valor);
			
			valor = new Vector();
			valor.addElement("impReferenciaMon");
			valor.addElement(ObjectUtil.formatearMontoTrama(pago.getImporteAlCambio()));
			valores.add(valor);
			
			
		}
		
		
		valor = new Vector();
		valor.addElement("tipoAfectacion");
		valor.addElement("7");
		valores.add(valor);


//		valor = new Vector();
//		valor.addElement("itf");
//		valor.addElement(ObjectUtil.formatearMontoTrama(pago.getItf()));
//		valores.add(valor);
//
		valor = new Vector();
		valor.addElement("banco");
		valor.addElement(pago.getAfiliacion().getCodigoServicio().trim());
		valores.add(valor);
				
		valor = new Vector();
		valor.addElement("cuentaPropia");
		valor.addElement(pago.getCuentaPropia());
		valores.add(valor);
				
		valor = new Vector();
		valor.addElement("cuentaOrigen");
		valor.addElement(pago.getCuenta().getNumeroProducto());
		valores.add(valor);


		valor = new Vector();
		valor.addElement("cuentaDestino");
		valor.addElement(pago.getAfiliacion().getNumeroServicio().trim());
		valores.add(valor);
		
		/*MGL - INI*/
		
		valor = new Vector();
		valor.addElement("numTarjetaDebito");
		valor.addElement(usuario.getTarjeta().getNumero());
		valores.add(valor);
		
		String cci = usuario.getCciCliente();
		System.out.println("cci::::"+cci);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("cciOrigen",cci);
		valores.addElement(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("ruteo1","0140020" + pago.getAfiliacion().getCodigoServicio().trim());
		valores.addElement(valor);
		
		/*MGL - FIN*/
		
		valor = new Vector();
		valor.addElement("moneda");
		valor.addElement(pago.getMoneda_());
		valores.add(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("correo1",(pago.getAfiliacion().getCorreo().toUpperCase()));
		valores.addElement(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("correo2",(pago.getAfiliacion().getServidorCorreo().toUpperCase()));
		valores.addElement(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("identificadorCCE1",pago.getAfiliacion().getIdentificadorCCE1());
		valores.addElement(valor);
		
		valor = new Vector();
		valor = ObjectUtil.getVectorComponent("identificadorCCE2",pago.getAfiliacion().getIdentificadorCCE2());
		valores.addElement(valor);

		
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
		
	    valor = ObjectUtil.getVectorComponent("nombreBenef",nombres);
		valores.addElement(valor);
		
		valor = ObjectUtil.getVectorComponent("apellidoPatBenef",apePaterno);
		valores.addElement(valor);
		
		valor = ObjectUtil.getVectorComponent("apellidoMatBenef",apeMaterno);
		valores.addElement(valor);
				
		
		
//		valor = new Vector();
//		valor.addElement("beneficiario");
//		valor.addElement(ObjectUtil.cortarCadena(pago.getAfiliacion().getObjBenef().getApellidoPaterno()+" "+pago.getAfiliacion().getObjBenef().getApellidoMaterno()+" "+pago.getAfiliacion().getObjBenef().getNombre(),30));
//		valores.add(valor);

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
		v.addElement(remoteAddress);
		ctas.addElement(v);
		
		valor = ObjectUtil.getVectorComponent("transaccion",transaccion);
		valores.addElement(valor);
		
		Transaction t = new Transaction(transaccion);
		t.setValues(valores);
		t.setCuentas(ctas);
			
		t.setValues(valores);
		t.setCuentas(ctas);				
		
		pago.pagarEnLinea(t, usuario);
		
		GeneralTest gt= new GeneralTest();
	
		if(pago.getArrayRuleException()!=null){
		    throw pago.getArrayRuleException();
		}else{
			if(pago.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
				gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TARJETA_CREDITO_EN_LINEA_OB,pago, request);
			}else{			
				gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TARJETA_CREDITO_EN_LINEA_OB_RECHAZO,pago, request);
			}	
		}	

		return (PagoTarjeta)pago;
	}
	
	public PagoTelefono getReciboTelefono(String transaccion, Cuenta cuenta, Afiliacion afiliacion, Usuario usuario, String remoteAddress) throws Exception {

		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codServicio");
		valor.addElement(afiliacion.getCodigoServicio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codLocalidad");
		valor.addElement(afiliacion.getCodigoLocalidad());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroAbonado");
		if(afiliacion.getCodigoLocalidad().equals("00") && (afiliacion.getCodigoServicio().equals("T") || afiliacion.getCodigoServicio().equals("Y"))){
			valor.addElement(afiliacion.getNumeroServicio().substring(2));
		}
		else{
			valor.addElement(ObjectUtil.replaceChar(afiliacion.getNumeroServicio(),'-',""));
		}
		valores.add(valor);
		
		
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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
		pagoTelefonoImpl.getVerRecibo(t, usuario);
		return (PagoTelefono)pagoTelefonoImpl;
	}
	
	public PagoTelefono getConfirmaTelefono(Usuario usuario,Cuenta cuenta, Afiliacion afiliacion, String reciboNro, Timestamp fechaEmi, BigDecimal monto, String monedaCod, String abrevMoneda, String codSeccion, pe.cosapi.domain.TipoCambio tipoCambio) throws Exception {
		PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl(usuario,cuenta, afiliacion, reciboNro, fechaEmi, monto, monedaCod, abrevMoneda, codSeccion);
		BigDecimal montoEval = monto;
		if (cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL) && monedaCod.equals(Constante.COD_MONEDA_DOL)){
		    montoEval = monto.multiply(tipoCambio.getVenta());
		}else if (cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR) && monedaCod.equals(Constante.COD_MONEDA_SOL)){
		    montoEval = monto.divide(tipoCambio.getCompra(),2,BigDecimal.ROUND_HALF_EVEN);
		}
		pagoTelefonoImpl.getConfirmaPago(usuario,cuenta, montoEval);
		//Cuenta cuenta, Afiliacion afiliacion, String reciboNro, Timestamp fechaEmi, BigDecimal monto
		return (PagoTelefono)pagoTelefonoImpl;
	}
	
	public PagoTelefono getPagoTelefono(String transaccion, Cuenta cuenta, String hidCodServ, String hidCodEntidad, Afiliacion afiliacion, String reciboNro, String fechaEmi, String monto, String monedaCod, String codsec, Usuario usuario, String nomben,String localidad,String montoformat,String ctaorigen,String empresa,String servicio, String remoteAddress) throws Exception {

		Transaction t = new Transaction(transaccion);


		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCampo2");
		valor.addElement("0");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nImporte");
		valor.addElement(monto);
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nFecha");
		valor.addElement(fechaEmi);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tMoneda");
		valor.addElement(monedaCod);
		valores.add(valor);
		
		if (transaccion.equals("PS02")){
			String cMontoDolar = "";
			BigDecimal nMontoDolar = ObjectUtil.tramaToBigDecimal(monto,2);
			
			TipoCambio tipoCambio = usuario.getTipoCambio();
			
			nMontoDolar = nMontoDolar.divide(tipoCambio.getCompra(),2,BigDecimal.ROUND_HALF_EVEN);
			
			cMontoDolar = ObjectUtil.formatearMonto(nMontoDolar);
			cMontoDolar = ObjectUtil.formatearMontoTrama(cMontoDolar);
			
			valor = new Vector();
			valor.addElement("nImporteDol");
			valor.addElement(cMontoDolar);
			valores.add(valor);
		}
		
		if (transaccion.equals("PS06")){
			String cMontoDolar = "";
			BigDecimal nMontoDolar = ObjectUtil.tramaToBigDecimal(monto,2);
			
			TipoCambio tipoCambio = usuario.getTipoCambio();
			
			//nMontoDolar = nMontoDolar.divide(tipoCambio.getCompra(),2,BigDecimal.ROUND_CEILING);
			nMontoDolar = nMontoDolar.multiply(tipoCambio.getVenta());
			
			cMontoDolar = ObjectUtil.formatearMonto(nMontoDolar);
			cMontoDolar = ObjectUtil.formatearMontoTrama(cMontoDolar);
			
			valor = new Vector();
			valor.addElement("nImporteDol");
			valor.addElement(cMontoDolar);
			valores.add(valor);
		}
		
		if (transaccion.equals("PS07")){
			String cMontoDolar = "";
			BigDecimal nMontoDolar = ObjectUtil.tramaToBigDecimal(monto,2);
			
			TipoCambio tipoCambio = usuario.getTipoCambio();
			
			nMontoDolar = nMontoDolar.multiply(tipoCambio.getVenta());
			
			cMontoDolar = ObjectUtil.formatearMonto(nMontoDolar);
			cMontoDolar = ObjectUtil.formatearMontoTrama(cMontoDolar);
			
			valor = new Vector();
			valor.addElement("nImporteDol");
			valor.addElement(cMontoDolar);
			valores.add(valor);
		}
		
		if (transaccion.equals("PS08")){
			String cMontoDolar = "";
			BigDecimal nMontoDolar = ObjectUtil.tramaToBigDecimal(monto,2);
			
			TipoCambio tipoCambio = usuario.getTipoCambio();
			
			nMontoDolar = nMontoDolar.divide(tipoCambio.getCompra(),2,BigDecimal.ROUND_HALF_EVEN);
			
			cMontoDolar = ObjectUtil.formatearMonto(nMontoDolar);
			cMontoDolar = ObjectUtil.formatearMontoTrama(cMontoDolar);
			
			valor = new Vector();
			valor.addElement("nImporteDol");
			valor.addElement(cMontoDolar);
			valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("nroServicio");
		valor.addElement(afiliacion.getCodigoServicio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroSeccion");
		valor.addElement(codsec);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroAbonado");
		if(afiliacion.getCodigoLocalidad().equals("00") && (afiliacion.getCodigoServicio().equals("T") || afiliacion.getCodigoServicio().equals("Y"))){
			valor.addElement(afiliacion.getNumeroServicio().substring(2));
		}
		else{
			valor.addElement(ObjectUtil.replaceChar(afiliacion.getNumeroServicio(),'-',""));
		}
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroFactura");
		valor.addElement(reciboNro);
		valores.add(valor);
		
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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
		pagoTelefonoImpl.setAfiliacion(afiliacion);
		pagoTelefonoImpl.setNomAbonado(nomben);
		pagoTelefonoImpl.setCodMoneda(monedaCod);
		pagoTelefonoImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoTelefonoImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		pagoTelefonoImpl.setCtaFormateada(ctaorigen);
		pagoTelefonoImpl.setCodServicio(afiliacion.getNumeroServicio());
		pagoTelefonoImpl.setRecibo(reciboNro);
		String dia  = fechaEmi.substring(6,8);
	    String mes  = fechaEmi.substring(4,6);
		String anio = fechaEmi.substring(0,4);
		String fecha = dia+"/"+mes+"/"+anio; 
		pagoTelefonoImpl.setFecEmisionRecibo(fecha);
		pagoTelefonoImpl.setDescripcionLocalidad(localidad);
		pagoTelefonoImpl.setCuenta(cuenta);
		
		if(pagoTelefonoImpl.getCodMoneda().equals(Constante.COD_MONEDA_SOL)){
			
			pagoTelefonoImpl.setCodMonedaDes(Constante.SIMBOLO_MONEDA_SOL + " ");
		}else{
		 if(pagoTelefonoImpl.getCodMoneda().equals(Constante.COD_MONEDA_DOL)){
			 pagoTelefonoImpl.setCodMonedaDes(Constante.SIMBOLO_MONEDA_DOLAR+ " ");
		 }
	}
		pagoTelefonoImpl.setMontoFormat(montoformat);
		pagoTelefonoImpl.setEmpresa(empresa);
		pagoTelefonoImpl.setServicio(servicio);
		pagoTelefonoImpl.setCodEntidad(hidCodEntidad);
		pagoTelefonoImpl.setCodServEnt(hidCodServ);
		pagoTelefonoImpl.getPagoRecibo(t, usuario);
		
		return (PagoTelefono)pagoTelefonoImpl; //pagoTelefonoImpl;
	}


	public PagoTelefono pagarPagoTelefono(String transaccion, PagoTelefonoImpl pagoTelefonoImpl, Usuario usuario, String remoteAddress) throws Exception {
		return null;
	}

	public PagoTasas pagarTasas(String codTransaccion, PagoTasas pago,Usuario usuario, String remoteAddress,HttpServletRequest request) throws Exception {
		PagoTasasImpl pagoTasas = (PagoTasasImpl)pago;
		
		Transaction t = new Transaction(codTransaccion);
		
		String opcion = "";
		
		Vector valores	= new Vector();
		Vector ctas		= new Vector();
		Vector val		= new Vector();
		//*********************************************************************************
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;
		Date data;

		//Seteamos la fecha
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = new Date(fechaActual.getTime());
		pagoTasas.setFechaOpe(formatter.format(data));
		pagoTasas.setFecha(formatter.format(data));

		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		pagoTasas.setHoraOpe(formatter.format(data));
		pagoTasas.setHora(formatter.format(data));
		//*********************************************************************************
		
		if (codTransaccion.equals("PT01")){		
			val = ObjectUtil.getVectorComponent("transaccion",codTransaccion);
			valores.addElement(val);	
			val = ObjectUtil.getVectorComponent("nroCuenta",pagoTasas.getCuenta().getNumeroProducto());
			valores.addElement(val);	
			val = ObjectUtil.getVectorComponent("codTributo",pagoTasas.getTributo().getCodigo());
			valores.addElement(val);	
			val = ObjectUtil.getVectorComponent("tipoDocumento",pagoTasas.getDocumento().getCodigo());
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("cantidadDocumento",pagoTasas.getCantidad());
			valores.addElement(val);
			if (pagoTasas.getPlantilla().equals("5")){
			
				//val = ObjectUtil.getVectorComponent("importe","0");
				val = ObjectUtil.getVectorComponent("importe",ObjectUtil.formatearMontoTrama(pagoTasas.getImporteTotal()));
				valores.addElement(val);
			}
			else{
				val = ObjectUtil.getVectorComponent("importe",ObjectUtil.formatearMontoTrama(pagoTasas.getImporte()));
				valores.addElement(val);
					
			}
		
			
			if (pagoTasas.getPlantilla().equals("5")){
				
					val = ObjectUtil.getVectorComponent("plantilla","2");
					valores.addElement(val);
				}
			else{
					val = ObjectUtil.getVectorComponent("plantilla","0");
					valores.addElement(val);
						
				}
				
			if(pagoTasas.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
				//System.out.println("ENTRO A MONEDA DOLAR");
				val = ObjectUtil.getVectorComponent("nroTrx","9635");
				valores.addElement(val);
				pagoTasas.calcularCambio(usuario);
				val = ObjectUtil.getVectorComponent("importeDolar",ObjectUtil.formatearMontoTrama(pagoTasas.getImporteAlCambio()));
				valores.addElement(val);
	
				opcion = "02";
			}
			else if(pagoTasas.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)){
				//System.out.println("ENTRO A MONEDA SOL");
				val = ObjectUtil.getVectorComponent("nroTrx","9625");
				valores.addElement(val);
				opcion = "01";
			}
			
			val = ObjectUtil.getVectorComponent("nroDocumento",pagoTasas.getDocumento().getNumero());
			valores.addElement(val);
		
			
		}else{ //PT02
			val = ObjectUtil.getVectorComponent("transaccion",codTransaccion);
			valores.addElement(val);	
			val = ObjectUtil.getVectorComponent("nroCuenta",pagoTasas.getCuenta().getNumeroProducto());
			valores.addElement(val);	
			val = ObjectUtil.getVectorComponent("codTributo",pagoTasas.getTributo().getCodigo());
			valores.addElement(val);	
			val = ObjectUtil.getVectorComponent("tipoDocumento",pagoTasas.getDocumento().getCodigo());
			valores.addElement(val);
			val = ObjectUtil.getVectorComponent("depJudicial",pagoTasas.getCodDependencia());
			valores.addElement(val);
			val = ObjectUtil.getVectorComponent("importe",ObjectUtil.formatearMontoTrama(pagoTasas.getImporte()));
			valores.addElement(val);
			
			if(pagoTasas.getCuenta().getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
				//System.out.println("ENTRO A MONEDA DOLAR");
				val = ObjectUtil.getVectorComponent("nroTrx","96xx"); //!!!!!!!!
				valores.addElement(val);
				pagoTasas.calcularCambio(usuario);
				val = ObjectUtil.getVectorComponent("importeDolar",ObjectUtil.formatearMontoTrama(pagoTasas.getImporteAlCambio()));
				valores.addElement(val);
	
				BigDecimal importeAlCambio = pagoTasas.calcularCambio(usuario, ObjectUtil.deFormatearMonto(pagoTasas.getImporteTotal()));
				pagoTasas.setImporteTotalAlCambio(importeAlCambio);
				
				opcion = "02";
			}
			else if(pagoTasas.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)){
				//System.out.println("ENTRO A MONEDA SOL");
				val = ObjectUtil.getVectorComponent("nroTrx","9686");
				valores.addElement(val);
				opcion = "01";
			}
			val = ObjectUtil.getVectorComponent("plantilla",pagoTasas.getPlantilla()); 
			valores.addElement(val);
			
			val = ObjectUtil.getVectorComponent("nroDocumento",pagoTasas.getDocumento().getNumero());
			valores.addElement(val);
			
			if (pagoTasas.getNroExpediente() != null){
				
				val = ObjectUtil.getVectorComponent("nroExp_Cant",pagoTasas.getNroExpediente());
				valores.addElement(val);
			}else if (pagoTasas.getCantidad() != null){
				
				val = ObjectUtil.getVectorComponent("nroExp_Cant",pagoTasas.getCantidad());
				valores.addElement(val);
			}
		}
		
		Vector v = ObjectUtil.getVectorBlankCuentas(remoteAddress,opcion);
		ctas.addElement(v);
		    
      	t.setValues(valores);
      	t.setCuentas(ctas);
		
      	pagoTasas.pagarTasas(t,usuario);
		GeneralTest gt= new GeneralTest();
		if (codTransaccion.equals("PT02")){	
			if (pagoTasas.getTipoImporte().equals("3") && pagoTasas.getFlgCantidadVisible().equals("1")){
				pagoTasas.setImporte(ObjectUtil.deFormatearMonto(pagoTasas.getImporteTotal()));
			}
		}
		gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TASAS,pagoTasas, request);
		if(pagoTasas.getArrayRuleException()!=null){
		    throw pagoTasas.getArrayRuleException();
		}

      	
		return pago;
	}


	/* (sin Javadoc)
	 * @see pe.bn.pago.facade.PagoFacade#getPagoSedapal(pe.cosapi.domain.Usuario)
	 */
	public PagoSedapal getPagoSedapal(Usuario usuario) throws Exception {

		CuentaImpl cuenta = null;
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
		}
		
		if(cuenta==null)
			throw new ArrayRuleException("El cliente no posee una cuenta de ahorros en soles");
		
		
		PagoSedapalImpl pagoSedapal = new PagoSedapalImpl();
		pagoSedapal.setCuenta(cuenta);
		
		return (PagoSedapal)pagoSedapal;
	}


	/* (sin Javadoc)
	 * @see pe.bn.pago.facade.PagoFacade#getPagoSedapal(pe.bn.pago.domain.PagoSedapal, java.lang.String, pe.cosapi.domain.Usuario, java.lang.String)
	 */
	public PagoSedapal getPagoSedapal(String transaccion,PagoSedapal pago, String nroSuministro, Usuario usuario, String remoteAddress) throws Exception {
		Transaction t = new Transaction(transaccion);

		PagoSedapalImpl pagoSedapal = (PagoSedapalImpl) pago;
		//System.out.println("nroSuministro.substring(0,nroSuministro.length()-1)"+ nroSuministro.substring(0,nroSuministro.length()-1));
		pagoSedapal.setNroSuministro(nroSuministro.substring(0,nroSuministro.length()-1));
		pagoSedapal.setNroChequeo(nroSuministro.substring(nroSuministro.length()-1));
		
		Vector valores = new Vector();
		Vector valor = new Vector();

		
		valor.addElement("transaccion");
		valor.addElement("PA02");
		//valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroSuministro");
		valor.addElement(pagoSedapal.getNroSuministro());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("digitoChequeo");
		valor.addElement(pagoSedapal.getNroChequeo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroTrx");
		valor.addElement("01");
		valores.add(valor);
				
		valor = new Vector();
		valor.addElement("codigoAgencia");
		valor.addElement("0");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroRecibos");
		valor.addElement("0");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroRecibosPagados");
		valor.addElement("0");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("codigoBanco");
		valor.addElement("2136");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("codigoCompania");
		valor.addElement("0001");
		valores.add(valor);

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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		//pagoSedapal.getPago(t,usuario);
		pagoSedapal.getPagov2(t,usuario);
				
		return (PagoSedapal)pagoSedapal;
	}


	/* (sin Javadoc)
	 * @see pe.bn.pago.facade.PagoFacade#pagarSedapal(java.lang.String, pe.bn.pago.domain.PagoSedapal, pe.cosapi.domain.Usuario, java.lang.String)
	 */
	public PagoSedapal pagarSedapal(String transaccion, PagoSedapal pago, Usuario usuario, String remoteAddress) throws Exception {
		Transaction t = new Transaction(transaccion);
		PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)pago;
		
		Vector valores = new Vector();
		Vector valor = new Vector();

		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroSuministro");
		valor.addElement(pagoSedapal.getNroSuministro());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroChequeo");
		valor.addElement(pagoSedapal.getNroChequeo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroRecibo");
		valor.addElement(pagoSedapal.getRecibo().getNumRecibo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("fechaRecibo");
		valor.addElement(ObjectUtil.timestampToTramaDDMMAAAA(pagoSedapal.getRecibo().getFecha()));
		valores.add(valor);
				
		valor = new Vector();
		valor.addElement("importe");
		String imp= pagoSedapal.getRecibo().getImporte();
		imp=ObjectUtil.replaceChar(imp,',',"");
		valor.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(imp)));
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(pagoSedapal.getCuenta().getNumeroProducto());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("tipoPago");
		valor.addElement("3");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cliente");
		valor.addElement(pagoSedapal.getCliente());
		valores.add(valor);


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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		pagoSedapal.pagar(t,usuario);
		return (PagoSedapal)pagoSedapal;
	}


	/* (sin Javadoc)
	 * @see pe.bn.pago.facade.PagoFacade#getPagoTasas()
	 */
	public List getPagoTasas() throws Exception {
		List lista = null;
		return lista;
	}


	/* (sin Javadoc)
	 * @see pe.bn.pago.facade.PagoFacade#getPagoTasas(pe.cosapi.domain.Cuenta, pe.cosapi.domain.Entidad, pe.cosapi.domain.Tributo)
	 */
	public PagoTasas getPagoTasas(Cuenta cuenta, Entidad entidad, Tributo tributo) throws Exception{
		PagoTasasImpl pagoTasas = new PagoTasasImpl();
		pagoTasas.loadConfig(tributo.getCodigo());
		pagoTasas.setCuenta(cuenta);
		pagoTasas.setEntidad(entidad);
		
		List detalles = FacadeFactory.getGeneralFacade().getComboDetHlpTasa(tributo.getCodigo());
		if(!(detalles==null||detalles.size()==0)){
			List tributillo = new ArrayList();
			for(int i = 0 ; i < detalles.size();i++){
				ComboUtil combo = (ComboUtil)detalles.get(i);
				DetalleTributo det = new DetalleTributoImpl();
				det.setCodigo(combo.getCodigo());
				det.setDescripcion(combo.getDescripcion());
				tributillo.add(det);				
			}
			tributo.setDetalles(tributillo);
		}
		pagoTasas.setTributo(tributo);
		//BigDecimal importe = DAOFactory.getPagoDAO().getImporteTasa(pagoTasas);
		BigDecimal importe = new BigDecimal(0);

		if (pagoTasas.getFlgConfig().equals("0")){
		    importe = DAOFactory.getPagoDAO().getImporteTasa(pagoTasas);
		}else if (pagoTasas.getTipoImporte().equals("1")){ //Importe fijo
		    importe = DAOFactory.getPagoDAO().getImporteTasa(pagoTasas);
		}
		if(importe==null){
			throw new ArrayRuleException(ConstanteError.GENERICO,"IMPORTE DEL TRIBUTO INEXISTENTE");
		}
		
		pagoTasas.setImporte(importe);
		return (PagoTasas)pagoTasas;
	}	
	
	//BANCO DE LA NACION - JOSE TOLEDO - 2009/06/25
	public PagoTelefono getConfirmaRecargaTelefono(Usuario usuario,Cuenta cuenta, Afiliacion afiliacion, BigDecimal monto, String abrevMoneda, String monedaCod, pe.cosapi.domain.TipoCambio tipoCambio) throws Exception {
		BigDecimal montoEval = monto;
		if (cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR) && monedaCod.equals(Constante.COD_MONEDA_SOL)){
		    montoEval = monto.multiply(tipoCambio.getCompra()).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		}
		PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl(usuario,cuenta, afiliacion, "", null, montoEval, monedaCod, abrevMoneda, "");
		pagoTelefonoImpl.getConfirmaPago(usuario,cuenta, montoEval);
		return (PagoTelefono)pagoTelefonoImpl;
	}
	
	public PagoTelefono getRecargaTelefono(String transaccion, Cuenta cuenta, String hidCodEntidad, String hidCodServ, Afiliacion afiliacion, String fecha, String monto, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa,String servicio, String remoteAddress, HttpServletRequest request) throws Exception {

		
		//System.out.println("RECARGA..");
		
		//System.out.println(transaccion);
		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCampo2");
		valor.addElement("0");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nImporte");
		valor.addElement(monto);
		valores.add(valor);

		valor = new Vector();
		valor.addElement("nFecha");
		valor.addElement(fecha);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tMoneda");
		valor.addElement(monedaCod);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroServicio");
		valor.addElement("8 ");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroSeccion");
		valor.addElement("00");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tPago");
		valor.addElement("1");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroAbonado");
		valor.addElement(afiliacion.getNumeroServicio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroFactura");
		valor.addElement("");
		valores.add(valor);
		
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
				
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
		pagoTelefonoImpl.setAfiliacion(afiliacion);
		pagoTelefonoImpl.setNomAbonado("");
		pagoTelefonoImpl.setCodMoneda(monedaCod);
		pagoTelefonoImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoTelefonoImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		pagoTelefonoImpl.setCtaFormateada(ctaorigen);
		pagoTelefonoImpl.setCodServicio(afiliacion.getNumeroServicio());
		pagoTelefonoImpl.setRecibo("");
		String dia  = fecha.substring(6,8);
	    String mes  = fecha.substring(4,6);
		String anio = fecha.substring(0,4);
		pagoTelefonoImpl.setFecEmisionRecibo(dia+"/"+mes+"/"+anio);
		pagoTelefonoImpl.setFecEmisionRecibo(fecha);
		pagoTelefonoImpl.setDescripcionLocalidad("");
		pagoTelefonoImpl.setCuenta(cuenta);
		pagoTelefonoImpl.setMontoFormat(montoformat);
		pagoTelefonoImpl.setImporteSol(montoSol);
		pagoTelefonoImpl.setImporteDol(montoDol);
		pagoTelefonoImpl.setTipoCambio(montoTipCambio);
		pagoTelefonoImpl.setEmpresa(empresa);
		pagoTelefonoImpl.setServicio(servicio);
		pagoTelefonoImpl.setMensaje(ConstanteSesion.MSG_TEL_CELULAR_RECARGA);
		pagoTelefonoImpl.setCodEntidad(hidCodEntidad);
		pagoTelefonoImpl.setCodServEnt(hidCodServ);
		
		pagoTelefonoImpl.getPagoRecarga(t, usuario);
		
		GeneralTest gt= new GeneralTest();
		//gt.generarLog2(t,usuario,Constante.REFRENDO_RECARGA_TELEFONO,pagoTelefonoImpl, request);
		if(pagoTelefonoImpl.getArrayRuleException()!=null){
		    throw pagoTelefonoImpl.getArrayRuleException();
		}
		
		return (PagoTelefono)pagoTelefonoImpl;
	}
	
	/*
	 * METODO PARA PODER REALIZAR LA RECARGA CON LA EMPRESA CLARO
	 * AUTOR: GUR
	 * FECHA: 04/02/2010
	 * @see pe.bn.pago.facade.PagoFacade#getRecargaClaro(String transaccion, Cuenta cuenta, Afiliacion afiliacion, String fecha, String monto, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa, String remoteAddress, HttpServletRequest request)
	 */
	
	public PagoTelefono getRecargaClaro(String transaccion, PagoTelefono pagoTel, Cuenta cuenta, Afiliacion afiliacion, String fecha, String monto, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa, String remoteAddress, HttpServletRequest request) throws Exception {

		Transaction t = new Transaction(transaccion);
		
		System.out.println("transaccion:"+transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cTipoReferencia");
		valor.addElement("1");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cFormaPago");
		valor.addElement("2");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nImporte");
		valor.addElement(monto);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cNroCuenta");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cNroCuota");
		valor.addElement("1");
		valores.add(valor);

		valor = new Vector();
		valor.addElement("cFecha");
		valor.addElement(fecha);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nTotalPagar");
		valor.addElement(monto);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroServicio");
		valor.addElement(afiliacion.getNumeroServicio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nEntidad");
		valor.addElement("2010");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroTelefono");
		valor.addElement(afiliacion.getNumeroServicio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codCliente");
		valor.addElement(afiliacion.getNumeroServicio());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nMoneda");
		valor.addElement("PEN");
		valores.add(valor);
		
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
				
		t.setValues(valores);
		t.setCuentas(ctas);	
				
		PagoTelefonoImpl pagoTelefonoImpl = (PagoTelefonoImpl)pagoTel;
		pagoTelefonoImpl.setAfiliacion(afiliacion);
		pagoTelefonoImpl.setNomAbonado("");
		pagoTelefonoImpl.setCodMoneda(monedaCod);
		pagoTelefonoImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoTelefonoImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		pagoTelefonoImpl.setCtaFormateada(ctaorigen);
		pagoTelefonoImpl.setCodServicio(afiliacion.getNumeroServicio());
		pagoTelefonoImpl.setRecibo("");
		String dia  = fecha.substring(6,8);
	    String mes  = fecha.substring(4,6);
		String anio = fecha.substring(0,4);
		pagoTelefonoImpl.setFecEmisionRecibo(dia+"/"+mes+"/"+anio);
		pagoTelefonoImpl.setFecEmisionRecibo(fecha);
		pagoTelefonoImpl.setDescripcionLocalidad("");
		pagoTelefonoImpl.setCuenta(cuenta);
		pagoTelefonoImpl.setMontoFormat(montoformat);
		pagoTelefonoImpl.setImporteSol(montoSol);
		pagoTelefonoImpl.setImporteDol(montoDol);
		pagoTelefonoImpl.setTipoCambio(montoTipCambio);
		pagoTelefonoImpl.setEmpresa(empresa);
		pagoTelefonoImpl.setMensaje(ConstanteSesion.MSG_TEL_RECARGA_CLARO);
		
		pagoTelefonoImpl.getPagoRecargaClaro(t, usuario);
		
		GeneralTest gt= new GeneralTest();
		//gt.generarLog2(t,usuario,Constante.REFRENDO_RECARGA_CLARO,pagoTelefonoImpl, request);
		if(pagoTelefonoImpl.getArrayRuleException()!=null){
		    throw pagoTelefonoImpl.getArrayRuleException();
		}
		
		return (PagoTelefono)pagoTelefonoImpl;
	}
	
	/*
	 *  NUEVO METODO PARA PODER VER CUPONES
	 * AUTOR: GUR
	 * FECHA: 26/10/2009 
	 * @see pe.bn.pago.facade.PagoFacade#getConsultaCupon(java.lang.String, pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, pe.cosapi.domain.Usuario, java.lang.String)
	 */
	public PagoCuponera getConsultaCupon(String transaccion, Cuenta cuenta, Afiliacion afiliacion, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception {
		
		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		
		valor.addElement("PC00");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codEntidad");
		valor.addElement(tipoEntidad);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("numReferencia");
		valor.addElement(afiliacion.getNumeroServicio().trim());
		valores.add(valor);

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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);
		
		PagoCuponeraImpl pagoCupon = new PagoCuponeraImpl();
		pagoCupon.setNroReferencia(afiliacion.getNumeroServicio().trim());
		pagoCupon.setAfiliacion(afiliacion);
		pagoCupon.setCuenta(cuenta);
		pagoCupon.setCodEntidad(tipoEntidad);
		pagoCupon.getPago(t,usuario);
		
		return (PagoCuponera)pagoCupon;
	}
	
	public PagoCuponera pagarCuponera(String transaccion, PagoCuponera pago, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception {
		Transaction t = new Transaction(transaccion);
		PagoCuponeraImpl pagoCupon = (PagoCuponeraImpl)pago;
		
		Vector valores = new Vector();
		Vector valor = new Vector();

		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(pagoCupon.getCuenta().getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codEntidad");
		valor.addElement(tipoEntidad);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroReferencia");
		valor.addElement(pagoCupon.getNroReferencia());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuota");
		valor.addElement(pagoCupon.getRecibo().getNumRecibo());
		valores.add(valor);
				
		valor = new Vector();
		valor.addElement("importe");
		//Inicio 2019-M0400
		//String imp= pagoCupon.getRecibo().getImporte();
		String imp= pagoCupon.getRecibo().getImporteTotal();
		//Fin 2019-M0400
		imp=ObjectUtil.replaceChar(imp,',',"");
		valor.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(imp)));
		valores.add(valor);

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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		pagoCupon.pagar(t,usuario);
		return (PagoCuponera)pagoCupon;
	}

	public List getListaImportes(String codTributo,Cuenta cuenta) throws Exception {
	    return DAOFactory.getPagoDAO().getListaImportesTasa(codTributo,cuenta);
	    
	}
	
	public List getListaImportesDetallado(String codTributo,String detalle) throws Exception {
	    return DAOFactory.getPagoDAO().getListaImportesTasaDetalle(codTributo,detalle);
	    
	}
	public List getListaDistritos(String codTributo) throws Exception {
	    return DAOFactory.getPagoDAO().getListaDistritos(codTributo);
	}

	public List getListaDependencias(String distrito) throws Exception {
	    return DAOFactory.getPagoDAO().getListaDependencias(distrito);
	}

	public List getListaDistritosJudiciales() throws Exception {
	    return DAOFactory.getPagoDAO().getListaDistritosJudiciales();
	}
	
	/*
	 *  NUEVO METODO PARA PODER VER LA LISTA DE FACTURAS
	 * AUTOR: GUR
	 * FECHA: 16/03/2010 
	 * @see pe.bn.pago.facade.PagoFacade#getConsultaFactura(java.lang.String, pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, pe.cosapi.domain.Usuario, java.lang.String)
	 */
	public PagoFactura getConsultaFactura(String transaccion, Cuenta cuenta, Afiliacion afiliacion, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception {
		
		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement("PF00");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codBusqueda");
		valor.addElement("1");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codEntidad");
		valor.addElement(tipoEntidad);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("numFactura");
		valor.addElement(afiliacion.getNumeroServicio().trim());
		valores.add(valor);

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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);
		
		PagoFacturaImpl pagoFactura = new PagoFacturaImpl();
		pagoFactura.setNroReferencia(afiliacion.getNumeroServicio().trim());
		pagoFactura.setAfiliacion(afiliacion);
		pagoFactura.setCuenta(cuenta);
		pagoFactura.setCodEntidad(tipoEntidad);
		pagoFactura.getPago(t,usuario);
		
		return (PagoFactura)pagoFactura;
	}
	
	public PagoFactura pagarFactura(String transaccion, PagoFactura pago, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception {
		Transaction t = new Transaction(transaccion);
		PagoFacturaImpl pagoFactura = (PagoFacturaImpl)pago;
		
		Vector valores = new Vector();
		
		Vector valor = new Vector();
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroCuenta");
		valor.addElement(pagoFactura.getCuenta().getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("constante1");
		valor.addElement("2");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codEntidad");
		valor.addElement(tipoEntidad);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("interes");
		valor.addElement("0");
		valores.add(valor);
		
		String imp= pagoFactura.getRecibo().getImporte();
		//System.out.println("imp:"+imp);
		imp=ObjectUtil.replaceChar(imp,',',"");
		
		valor = new Vector();
		valor.addElement("totalDeuda");
		valor.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(imp)));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("importe");
		valor.addElement(ObjectUtil.formatearMontoTrama(new BigDecimal(imp)));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("constante2");
		valor.addElement("0");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("constante3");
		valor.addElement("0");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("constante4");
		valor.addElement("0");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroSecuencia");
		valor.addElement(pagoFactura.getRecibo().getSecuencia().trim());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroReferencia");
		valor.addElement(pagoFactura.getNroReferencia());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codCliente");
		valor.addElement(pagoFactura.getRecibo().getNumRecibo());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codRegistro");
		valor.addElement(pagoFactura.getRecibo().getConcepto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("moneda");
		valor.addElement(pagoFactura.getRecibo().getMoneda().trim());
		valores.add(valor);
				
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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		pagoFactura.pagar(t,usuario);
		return (PagoFactura)pagoFactura;
	}
	
	/*
	 * NUEVO METODO PARA PODER VER LA LISTA DE FACTURAS - ONLINE Vía WebService
	 * AUTOR: GUR
	 * FECHA: 16/03/2010 
	 * @see pe.bn.pago.facade.PagoFacade#getConsultaFactura(java.lang.String, pe.cosapi.domain.Cuenta, pe.bn.afiliacion.domain.Afiliacion, pe.cosapi.domain.Usuario, java.lang.String)
	 */
	public PagoFacturaWS getConsultaFacturaOnline(String transaccion, Cuenta cuenta, String numReferencia, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception {
		
		Transaction t = new Transaction(transaccion);

		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement("PF02");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("tipoReferencia");
		if(tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_CLARO)||tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE )
				||tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_ENTEL ) ||tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL ) ){
			valor.addElement("01");
		}
		else{valor.addElement("03");} //Comentado solo para prueba
		//else{valor.addElement("01");}
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("formaPago");
		valor.addElement("4");
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtMoneda");
		if(tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE) || tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_ENTEL) || tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL)){
			valor.addElement("01");
		}
		
		else
		{
			if(tipoEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
				valor.addElement("01");
			}else{
			valor.addElement("");}
		}
		
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("numReferencia");
		if(tipoEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
			valor.addElement(Long.parseLong(numReferencia.trim()));
		}else{
			valor.addElement(numReferencia.trim());
		}
		
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codEntidad");
		//valor.addElement("3080");
		valor.addElement(tipoEntidad);
		
		valores.add(valor);

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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);
		
		PagoFacturaWSImpl pagoFactura = new PagoFacturaWSImpl();
		
		if(tipoEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			pagoFactura.setNroReferencia(numReferencia.trim());
			
		}
		else
		{pagoFactura.setNroReferencia(numReferencia.trim());}
		//pagoFactura.setAfiliacion(afiliacion);
		pagoFactura.setCuenta(cuenta);
		pagoFactura.setCodEntidad(tipoEntidad);
		pagoFactura.getPagoOnline(t,usuario);
		
		return (PagoFacturaWS)pagoFactura;
	}
	
	/*
	 * METODO PARA PODER REALIZAR REALIZAR EL PAGO DE FACTURAS ONLINE
	 * AUTOR: GUR
	 * FECHA: 10/08/2010
	 * @see pe.bn.pago.facade.PagoFacade#getRecargaClaro(String transaccion, Cuenta cuenta, Afiliacion afiliacion, String fecha, String monto, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa, String remoteAddress, HttpServletRequest request)
	 */
	
	public PagoFacturaWS getPagoFacturaOnline(String transaccion, Cuenta cuenta, PagoFacturaWSImpl pagoFactura, String cNumReferencia, String cCodEntidad, String fecha, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa, String remoteAddress, HttpServletRequest request) throws Exception {

		Transaction t = new Transaction(transaccion);
	
		Vector valores = new Vector();
		Vector valor = new Vector();
		
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cTipoReferencia");
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_CLARO)||cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE) 
				||cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_ENTEL) ||cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL)){
			valor.addElement("01");
		}
		else{
		
			valor.addElement("03");
			} 
		
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cFormaPago");
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE) || cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_ENTEL) || cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL)){
			valor.addElement("4");
		}
		else{
		valor.addElement("1");}
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nImporte");
		//valor.addElement(monto);
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getImporte().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cNroCuenta");
		valor.addElement(cuenta.getNumeroProducto());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cNroCuota");
		valor.addElement(pagoFactura.getFactura().getCuota());
		valores.add(valor);

		valor = new Vector();
		valor.addElement("cFecha");
		valor.addElement(fecha);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nTotalCuota");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getImpCuota().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nPagoMinimo");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getImpMinimo().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nInteres");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getInteres().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nInteresCom");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getInteresCom().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nMora");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getMora().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nGastos");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getGasto().trim()));
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nTotalPagar");
		valor.addElement(ObjectUtil.formatearMontoTrama(pagoFactura.getFactura().getImpCuota().trim()));
		valores.add(valor);
		
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE) || cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_ENTEL) || cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL))
		{
		valor = new Vector();
		valor.addElement("constante2");
		valor.addElement("1");
		valores.add(valor);
		}
		
		valor = new Vector();
		valor.addElement("nroServicio");
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_CLARO)){
			valor.addElement(pagoFactura.getFactura().getCodClient().trim());
		}
		else{
			
			if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
				valor.addElement(Long.parseLong(cNumReferencia.trim()));
				
			}else{
				valor.addElement(cNumReferencia.trim());
			}
			
			}
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nEntidad");
		valor.addElement(cCodEntidad);
		valores.add(valor);
		
		String cNomCliente1 = "";
		String cNomCliente2 = "";
		
		if (pagoFactura.getCliente().length() <= 30){
			cNomCliente1 = pagoFactura.getCliente().trim();
		} else {
			cNomCliente1 = pagoFactura.getCliente().trim().substring(0, 30);
			cNomCliente2 = pagoFactura.getCliente().trim().substring(30, pagoFactura.getCliente().length());
		}
		
		valor = new Vector();
		valor.addElement("cNombre1");
		valor.addElement(cNomCliente1);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("cNombre2");
		valor.addElement(cNomCliente2);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nroTelefono");
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE) || cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_ENTEL) || cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL)){
		valor.addElement(pagoFactura.getFactura().getNumRecibo());
		}
		else{
			if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
				valor.addElement(pagoFactura.getFactura().getNumRecibo()); //Validar Pago con Numero Telefono
			}
			else{
				valor.addElement(cNumReferencia.trim());	
			}
		
		}
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("codCliente");
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_CLARO)){
			valor.addElement(pagoFactura.getFactura().getCodClient().trim());
		}
		else{valor.addElement(pagoFactura.getFactura().getCodClient().trim());}
		
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("nMoneda");
		valor.addElement("PEN");
		valores.add(valor);
		
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE))
		{
			
		valor = new Vector();
		valor.addElement("cMensaje1");
		valor.addElement(Funciones.lpad(pagoFactura.getFactura().getConcepto(),"0",9));
		valores.add(valor);
		}
		
		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_BITEL))
		{
			
		valor = new Vector();
		valor.addElement("cMensaje1");
		valor.addElement(pagoFactura.getGlosas());
		valores.add(valor);
		}
		
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
				
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		
		PagoFacturaWSImpl pagoFacturaImpl = (PagoFacturaWSImpl) pagoFactura;
		String cValorPago = ObjectUtil.replaceChar(pagoFactura.getFactura().getImpCuota(), ',', "");
		pagoFacturaImpl.setImporte(new BigDecimal(cValorPago));
		pagoFacturaImpl.setCodServicio(pagoFactura.getCodServicio());
		
		pagoFacturaImpl.getPagoFacturaOnline(t, usuario);
		
		
	//	GeneralTest gt= new GeneralTest();
//		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE))
//		{
//			gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_FACTURA_PARCIAL,pagoFacturaImpl, request);	
//		}
//		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_AMAG))
//		{
//			gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_FACTURA_MOD,pagoFacturaImpl, request);
//		}
//		if(cCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_CLARO))
//		{
//			gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_FACTURA_WS,pagoFacturaImpl, request);
//		}
		
		if(pagoFacturaImpl.getArrayRuleException()!=null){
		    throw pagoFacturaImpl.getArrayRuleException();
		}
		
		return (PagoFacturaWS)pagoFacturaImpl;
	}
	
	public DepositoJudicial getConsultaConsignacion(String transaccion, String indicador,DepositoJudicial dep, Usuario usuario, String remoteAddress) throws Exception {
		
		Transaction t = new Transaction(transaccion);
	
		Vector valores = new Vector();
		
		Vector valor = new Vector();
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("indConsulta");
		valor.addElement(indicador);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNumero");
		valor.addElement(dep.getNumero());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtIp");
		valor.addElement(remoteAddress);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtMac");
		valor.addElement("00-21-97-6C-81-EB");
		valores.add(valor);
	
				
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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		DepositoJudicialImpl depositoJudicial = new DepositoJudicialImpl();
		
		depositoJudicial.consultar(t,usuario);
		
		
		return (DepositoJudicial)depositoJudicial;
	}
	
public DepositoJudicial getConsultaLiquidacion(String transaccion, String indicador,DepositoJudicial dep, Usuario usuario, String remoteAddress) throws Exception {
		
		Transaction t = new Transaction(transaccion);
	
		Vector valores = new Vector();
		
		Vector valor = new Vector();
		valor.addElement("transaccion");
		valor.addElement(transaccion);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("indConsulta");
		valor.addElement(indicador);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtNumero");
		valor.addElement(dep.getNumero());
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtIp");
		valor.addElement(remoteAddress);
		valores.add(valor);
		
		valor = new Vector();
		valor.addElement("txtMac");
		valor.addElement("00-21-97-6C-81-EB");
		valores.add(valor);
	
				
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
		
		
		t.setValues(valores);
		t.setCuentas(ctas);	
		DepositoJudicialImpl depositoJudicial = new DepositoJudicialImpl();
		
		depositoJudicial.consultarLiquidacion(t,usuario);
		
		
		return (DepositoJudicial)depositoJudicial;
	}

public Multipago getConsultaMultipago(String transaccion, Multipago pago, Usuario usuario, String remoteAddress) throws Exception {
	
	Transaction t = new Transaction(transaccion);

	Vector valores = new Vector();
	
	Vector valor = new Vector();
	valor.addElement("transaccion");
	valor.addElement(transaccion);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codServicio");
	valor.addElement("");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("numUnicoPago");
	valor.addElement(pago.getNumUnico());
	valores.add(valor);
	
			
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
	
	
	t.setValues(valores);
	t.setCuentas(ctas);	
	MultipagoImpl consulta = new MultipagoImpl();
	
	consulta.consultar(t,usuario);
	
	
	return (Multipago)consulta;
}
public Multipago getPagoMultipago(String transaccion, Cuenta cuenta, MultipagoImpl pago, Usuario usuario, String remoteAddress) throws Exception {
	
	Transaction t = new Transaction(transaccion);

	Vector valores = new Vector();
	
	Vector valor = new Vector();
	valor.addElement("transaccion");
	valor.addElement(transaccion);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codServicio");
	valor.addElement(pago.getCodServicio());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("semilla");
	valor.addElement(pago.getNumUnico());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("moneda");
	valor.addElement(pago.getCodMoneda());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("importe");
	valor.addElement(pago.getImporte());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("formaPago");
	valor.addElement("3");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nroCheque");
	valor.addElement("");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codBanco");
	valor.addElement("");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nroCuenta");
	valor.addElement(cuenta.getNumeroProducto());
	valores.add(valor);
	
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
	
	
	t.setValues(valores);
	t.setCuentas(ctas);	
	MultipagoImpl pagoTxn = new MultipagoImpl();
//	pagoTxn.setImporte(new BigDecimal(pago.getImporte()));
//	pagoTxn.setCuenta(pago.getCuenta());
	pagoTxn.pago(t,usuario);
	
	
	return (Multipago)pago;
}

public PagoTelefono getRecargaOtros(String transaccion, PagoTelefono pagoTel, Cuenta cuenta, Afiliacion afiliacion, String fecha, String monto, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa,String hidServicio, String remoteAddress, HttpServletRequest request) throws Exception {

	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector valor = new Vector();
	
	valor.addElement("transaccion");
	valor.addElement(transaccion);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cTipoReferencia");
	valor.addElement("1");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cFormaPago");
	valor.addElement("2");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nImporte");
	valor.addElement(monto);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cNroCuenta");
	valor.addElement(cuenta.getNumeroProducto());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cNroCuota");
	valor.addElement("1");
	valores.add(valor);

	valor = new Vector();
	valor.addElement("cFecha");
	valor.addElement(fecha);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nPagoMinimo");
	valor.addElement(pagoTel.getImporteMinimo());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nTotalPagar");
	valor.addElement(monto);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("constante2");
	valor.addElement("1");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nroServicio");
	valor.addElement(afiliacion.getNumeroServicio());
	valores.add(valor);
	
	System.out.println("ENTEL_hidServicio:"+hidServicio);
	if(hidServicio.equals("14")){
		valor = new Vector();
		valor.addElement("nEntidad");
		valor.addElement(Constante.COD_ENTIDAD_BITEL_RECARGAS);
		valores.add(valor);
	}
	else{
		valor = new Vector();
		valor.addElement("nEntidad");
		valor.addElement(Constante.COD_ENTIDAD_ENTEL_RECARGAS);
		valores.add(valor);
	}
	
	valor = new Vector();
	valor.addElement("nroTelefono");
	valor.addElement(afiliacion.getNumeroServicio());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codCliente");
	valor.addElement(afiliacion.getNumeroServicio());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nMoneda");
	valor.addElement("PEN");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cMensaje1");
	valor.addElement(pagoTel.getGlosa());
	valores.add(valor);
	
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
			
	t.setValues(valores);
	t.setCuentas(ctas);	
			
	PagoTelefonoImpl pagoTelefonoImpl = (PagoTelefonoImpl)pagoTel;
	pagoTelefonoImpl.setAfiliacion(afiliacion);
	pagoTelefonoImpl.setNomAbonado("");
	pagoTelefonoImpl.setCodMoneda(monedaCod);
	pagoTelefonoImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
	pagoTelefonoImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
	pagoTelefonoImpl.setCtaFormateada(ctaorigen);
	pagoTelefonoImpl.setCodServicio(afiliacion.getNumeroServicio());
	pagoTelefonoImpl.setRecibo("");
	String dia  = fecha.substring(6,8);
    String mes  = fecha.substring(4,6);
	String anio = fecha.substring(0,4);
	pagoTelefonoImpl.setFecEmisionRecibo(dia+"/"+mes+"/"+anio);
	pagoTelefonoImpl.setCodEntidad("");
	pagoTelefonoImpl.setEmpresa(empresa);
	pagoTelefonoImpl.setCuenta(cuenta);
	pagoTelefonoImpl.setMontoFormat(montoformat);
	pagoTelefonoImpl.setImporteSol(montoSol);
	pagoTelefonoImpl.setImporteDol(montoDol);
	pagoTelefonoImpl.setTipoCambio(montoTipCambio);
	pagoTelefonoImpl.setEmpresa(empresa);
	
	if(hidServicio.equals("14")){
		pagoTelefonoImpl.setCodEntidad(Constante.COD_ENTIDAD_BITEL_RECARGAS);
		pagoTelefonoImpl.setMensaje(ConstanteSesion.MSG_TEL_RECARGA_BITEL);
		pagoTelefonoImpl.setRuc(ConstanteSesion.TEL_RUC_RECARGA_BITEL);
	}
	else{
		pagoTelefonoImpl.setCodEntidad(Constante.COD_ENTIDAD_ENTEL_RECARGAS);
		pagoTelefonoImpl.setMensaje(ConstanteSesion.MSG_TEL_RECARGA_ENTEL);
		pagoTelefonoImpl.setRuc(ConstanteSesion.TEL_RUC_RECARGA_ENTEL);
	}
	
	pagoTelefonoImpl.getPagoRecargaOtros(t, usuario);
	
	GeneralTest gt= new GeneralTest();
	
	if(pagoTelefonoImpl.getArrayRuleException()!=null){
	    throw pagoTelefonoImpl.getArrayRuleException();
	}
	
	return (PagoTelefono)pagoTelefonoImpl;
}
public PagoUniversidad getConsultaAlumno(String transaccion, Cuenta cuenta, String universidad,String codAlumno, Usuario usuario, String remoteAddress) throws Exception{
	
	Transaction t = new Transaction(transaccion);

	Vector valores = new Vector();
	Vector valor = new Vector();
	
	valor.addElement("transaccion");
	
	valor.addElement(Constante.UNI_TRANSACION_CONSULTA_ALUMNO);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("universidad");
	valor.addElement(universidad);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codAlumno");
	valor.addElement(codAlumno.trim());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoPersona");
	valor.addElement("1");
	valores.add(valor);

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
	
	
	t.setValues(valores);
	t.setCuentas(ctas);
	
	PagoUniversidadImpl pagoUniversidad = new PagoUniversidadImpl();
	
	pagoUniversidad.getConsultaAlumno(t,usuario);
	
	return (PagoUniversidadImpl)pagoUniversidad;
}

public PagoUniversidad getConsultaTarifario(String transaccion, PagoUniversidad universidad,String remoteAddress) throws Exception{
	
	Transaction t = new Transaction(transaccion);

	Vector valores = new Vector();
	Vector valor = new Vector();
	
	valor.addElement("transaccion");
	valor.addElement(Constante.UNI_TRANSACION_CONSULTA_TARIFARIO);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codUniversidad");
	valor.addElement(universidad.getCodUniversidad());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codConcepto");
	valor.addElement(universidad.getConcepto());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoPersona");
	valor.addElement(universidad.getTipoPersona());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoDocumento");
	valor.addElement(universidad.getTipoDoc());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codSituacion");
	if(universidad.getTipoPersona().equals(Constante.UNI_TIPO_PERSONA_OTROS)){
		valor.addElement("9");
	}else{
		valor.addElement(universidad.getCodSituacion());
	}
	
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("sede");
	valor.addElement(universidad.getSede());
	
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("numDocumento");
	if(universidad.getTipoPersona().equals(Constante.UNI_TIPO_PERSONA_ALUMNO)){
		valor.addElement(universidad.getCodAlumno().toUpperCase());
	}
	else{
		valor.addElement(universidad.getNroDoc());
	}
	
	valores.add(valor);

	valor = new Vector();
	valor.addElement("codAlumno");
	if(universidad.getTipoPersona().equals(Constante.UNI_TIPO_PERSONA_ALUMNO)){
	valor.addElement(universidad.getCodAlumno());
	}
	else{
		valor.addElement(universidad.getNombreCompleto());
	}
	valores.add(valor);
	
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
	
	
	t.setValues(valores);
	t.setCuentas(ctas);
	
	PagoUniversidadImpl pagoUniversidad = new PagoUniversidadImpl();
	
	pagoUniversidad.getConsultaTarifario(t);
	
	return (PagoUniversidadImpl)pagoUniversidad;
}



public PagoUniversidad getPagarUniversidad(String transaccion, PagoUniversidad universidad,Cuenta cuenta, Usuario usuario,String remoteAddress,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);

	Vector valores = new Vector();
	Vector valor = new Vector();
	
	valor.addElement("transaccion");
	valor.addElement(Constante.UNI_TRANSACION_PAGO_TASA);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cuenta");
	valor.addElement(cuenta.getNumeroProducto());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codUniversidad");
	valor.addElement(universidad.getCodUniversidad());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codConcepto");
	valor.addElement(universidad.getConcepto());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoPersona");
	valor.addElement(universidad.getTipoPersona());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoDocumento");
	valor.addElement(universidad.getTipoDoc());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("codSituacion");
	if(universidad.getTipoPersona().equals(Constante.UNI_TIPO_PERSONA_OTROS)){
		valor.addElement(universidad.getCodSituacion());
	}else{
		valor.addElement(universidad.getCodSituacion());
	}
	
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("sede");
	valor.addElement(universidad.getSede());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("importe");
	//System.out.println("importe formateado"+ObjectUtil.formatearMontoTrama(universidad.getImportePago()));
	valor.addElement(ObjectUtil.formatearMontoTrama(universidad.getImportePago()));
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("numDocumento");
	if(universidad.getTipoPersona().equals(Constante.UNI_TIPO_PERSONA_ALUMNO)){
		valor.addElement(universidad.getCodAlumno().toUpperCase());
	}
	else{
		valor.addElement(universidad.getNroDoc());
	}
	
	valores.add(valor);

	valor = new Vector();
	valor.addElement("codAlumno");
	if(universidad.getTipoPersona().equals(Constante.UNI_TIPO_PERSONA_ALUMNO)){
	valor.addElement(universidad.getCodAlumno());
	}
	else{
		valor.addElement(universidad.getNombreCompleto());
	}
	valores.add(valor);
	
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
	
	
	t.setValues(valores);
	t.setCuentas(ctas);
	
	PagoUniversidadImpl pagoUniversidad = new PagoUniversidadImpl();
	
	pagoUniversidad.setCuenta(cuenta);
	pagoUniversidad.setImporte(""+universidad.getImportePago());
	pagoUniversidad.setOpcionPago(universidad.getOpcionPago());
	pagoUniversidad.setTipoDocDesc(universidad.getTipoDocDesc());
	pagoUniversidad.setTipoDoc(universidad.getTipoDoc());
	pagoUniversidad.setCodUniversidad(universidad.getCodUniversidad());
	pagoUniversidad.setConcepto(universidad.getConcepto());
	pagoUniversidad.getPagarUniversidad(t,usuario);
	
	GeneralTest gt= new GeneralTest();
	
	if(pagoUniversidad.getOpcionPago().equals(Constante.UNI_OPCION_PAGO_OTROS)){
				
		gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_UNIVERSIDAD_OTROS,pagoUniversidad, request);
	}else{
			
		gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_UNIVERSIDAD_ALUMNO,pagoUniversidad, request);
	}


	
	return (PagoUniversidadImpl)pagoUniversidad;
}

public PagoTelefono getConsultaRecargaOtros(String transaccion, PagoTelefono pagoTel, Afiliacion afiliacion, Usuario usuario,String remoteAddress, HttpServletRequest request, BigDecimal monto, String hidServicio) throws Exception {

	Transaction t = new Transaction(transaccion);
	

	Vector valores = new Vector();
	Vector valor = new Vector();
	
	valor.addElement("transaccion");
	valor.addElement(transaccion);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoReferencia");
	valor.addElement("1");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("formaPago");
	valor.addElement("2");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("txtMoneda");
	valor.addElement("1");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("numReferencia");
	valor.addElement(afiliacion.getNumeroServicio());
	valores.add(valor);
	
	System.out.println("hidServicio>>>>>:"+hidServicio);
	
	if(hidServicio.equals("14")){
		valor = new Vector();
		valor.addElement("codEntidad");
		valor.addElement(Constante.COD_ENTIDAD_BITEL_RECARGAS);
		valores.add(valor);
	}else{
		valor = new Vector();
		valor.addElement("codEntidad");
		valor.addElement(Constante.COD_ENTIDAD_ENTEL_RECARGAS);
		valores.add(valor);
		
	}
	
	
	
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
			
	t.setValues(valores);
	t.setCuentas(ctas);	
			
	PagoTelefonoImpl pagoTelefonoImpl = (PagoTelefonoImpl)pagoTel;
	pagoTelefonoImpl.setAfiliacion(afiliacion);
	pagoTelefonoImpl.setCodServicio(afiliacion.getNumeroServicio());
	pagoTelefonoImpl.setImporte(monto);
	pagoTelefonoImpl.getConsultaRecargaOtros(t, usuario);
	
	GeneralTest gt= new GeneralTest();
	
	if(pagoTelefonoImpl.getArrayRuleException()!=null){
	    throw pagoTelefonoImpl.getArrayRuleException();
	}
	
	return (PagoTelefono)pagoTelefonoImpl;
}
public List getListaImporteTasaEducativa(String codTributo) throws Exception {
    return DAOFactory.getPagoDAO().getListaImporteTasaEducativa(codTributo);
    
}

public PagoUniversidad getPagarTasaEducativa(String transaccion, PagoUniversidad universidad,Cuenta cuenta, Usuario usuario,String remoteAddress,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);

	Vector valores = new Vector();
	Vector valor = new Vector();
	
	System.out.println(transaccion);
	valor.addElement("transaccion");
	valor.addElement(transaccion);
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nroCuenta");
	valor.addElement(cuenta.getNumeroProducto());
	valores.add(valor);
		
	valor = new Vector();
	valor.addElement("codTributo");
	valor.addElement(universidad.getConcepto());
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("cantidadDocumento");
	valor.addElement("1");
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("tipoDocumento");
	valor.addElement(universidad.getTipoDoc());
	valores.add(valor);
	
					
	valor = new Vector();
	valor.addElement("importe");
	//System.out.println("importe formateado"+ObjectUtil.formatearMontoTrama(universidad.getImportePago()));
	valor.addElement(ObjectUtil.formatearMontoTrama(universidad.getImportePago()));
	valores.add(valor);
	
	valor = new Vector();
	valor.addElement("nroDocumento");
	valor.addElement(universidad.getNroDoc());
	valores.add(valor);

	valor = new Vector();
	valor.addElement("nroTrx");
	valor.addElement("9625");
	valores.add(valor);
	
	Vector ctas=new Vector();

	Vector v = ObjectUtil.getVectorBlankCuentas(remoteAddress,"01");
	ctas.addElement(v);
	    
  	t.setValues(valores);
  	t.setCuentas(ctas);
	
	PagoUniversidadImpl pagoUniversidad = new PagoUniversidadImpl();
	pagoUniversidad.setTipoDoc(universidad.getTipoDoc());
	pagoUniversidad.setCodUniversidad(universidad.getCodUniversidad());
	pagoUniversidad.setConcepto(universidad.getConcepto());
	pagoUniversidad.setCuenta(cuenta);
	pagoUniversidad.setNombreCompleto(universidad.getNombreCompleto());
	pagoUniversidad.setImporte(""+universidad.getImportePago());
	pagoUniversidad.setTipoDocDesc(universidad.getTipoDocDesc());
	pagoUniversidad.setFechaSistema(universidad.getFechaSistema());
	pagoUniversidad.getPagarTasaEducativa(t,usuario);
	
	GeneralTest gt= new GeneralTest();

	gt.generarLog2(t,usuario,Constante.REFRENDO_PAGO_TASAS_EDUCATIVAS,pagoUniversidad, request);
	
	return (PagoUniversidadImpl)pagoUniversidad;
}
}

