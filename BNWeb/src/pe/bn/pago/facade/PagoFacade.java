/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.facade;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
import pe.bn.pago.domain.impl.MultipagoImpl;
import pe.bn.pago.domain.impl.PagoFacturaWSImpl;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Entidad;
import pe.cosapi.domain.Tributo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.PagoImplNueva;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoFacade {
 
	public PagoPrestamoMultired getPagoPrestamoMultired(String cNroCtaSel, String cNroDesSel,String transaccion, Usuario usuario, String remoteAddress) throws Exception;
	
	public PagoPrestamoMultired pagarPrestamoMultired(String transaccion, Usuario usuario, String remoteAddress, PagoPrestamoMultired pagoPrestamo) throws Exception;
	
	public PagoTarjeta getPagoTarjeta(String transaccion,Cuenta cuenta, Afiliacion afiliacion,Usuario usuario,String moneda, String monto, String remoteAddress) throws Exception;
	
	public PagoTarjeta getPagoTarjetaEnLinea(String transaccion,Cuenta cuenta, Afiliacion afiliacion,Usuario usuario,String moneda, String monto, String remoteAddress) throws Exception;
	
	public PagoTarjeta pagarTarjeta(String transaccion, PagoTarjetaImpl pagoTarjetaImpl, Usuario usuario ,String remoteAddress, HttpServletRequest request) throws Exception;
	
	public PagoTarjeta pagarTarjetaEnLinea(String transaccion, PagoTarjetaImpl pagoTarjetaImpl, Usuario usuario ,String remoteAddress, HttpServletRequest request) throws Exception;
	
	public List getPagoTasas() throws Exception;
	
	public PagoTasas getPagoTasas(Cuenta cuenta, Entidad entidad, Tributo tributo) throws Exception;

	public PagoTasas pagarTasas(String codTransaccion, PagoTasas pagoTasas ,Usuario usuario, String remoteAddress,HttpServletRequest request) throws Exception;
	
	public PagoTelefono pagarPagoTelefono(String transaccion ,PagoTelefonoImpl pagoTelefonoImpl, Usuario usuario ,String remoteAddress) throws Exception;
	
	public PagoTelefono getReciboTelefono(String transaccion,Cuenta cuenta, Afiliacion afiliacion, Usuario usuario, String remoteAddress) throws Exception;
	
	public PagoTelefono getPagoTelefono(String transaccion, Cuenta cuenta, String hidCodServ, String hidCodEntidad, Afiliacion afiliacion, String reciboNro, String fechaEmi, String monto, String monedaCod,String codSeccion, Usuario usuario,String nomben,String localidad,String montoformat,String ctaorigen,String empresa, String servicio, String remoteAddress) throws Exception;
	
	// public PagoTelefono getPagoTelefono(String transaccion, String remoteAddress) throws Exception;
	
	public PagoTelefono getConfirmaTelefono(Usuario usuario,Cuenta cuenta, Afiliacion afiliacion, String reciboNro, Timestamp fechaEmi, BigDecimal monto, String monedaCod, String abrevMoneda, String codSeccion, pe.cosapi.domain.TipoCambio tipoCambio) throws Exception;
	
	public PagoSedapal getPagoSedapal(Usuario usuario) throws Exception;
	
	public PagoSedapal getPagoSedapal(String transaccion,PagoSedapal pagoSedapal,String nroSuministro,Usuario usuario, String remoteAddress) throws Exception;
	
	public PagoSedapal pagarSedapal(String transaccion,PagoSedapal pagoSedapal,Usuario usuario, String remoteAddress) throws Exception;
		
	//BANCO DE LA NACION - JOSE TOLEDO - 2009/06/25
	public PagoTelefono getConfirmaRecargaTelefono(Usuario usuario,Cuenta cuenta, Afiliacion afiliacion, BigDecimal monto, String abrevMoneda, String monedaCod, pe.cosapi.domain.TipoCambio tipoCambio) throws Exception;
	
	public PagoTelefono getRecargaTelefono(String transaccion, Cuenta cuenta, String hidCodEntidad, String hidCodServ, Afiliacion afiliacion,  String fecha, String monto, String monedaCod, Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa,String servicio, String remoteAddress, HttpServletRequest request) throws Exception;
	
	//	BANCO DE LA NACION - GODOFREDO URETA - 04/02/2010
	public PagoTelefono getRecargaClaro(String transaccion, PagoTelefono pagoTel, Cuenta cuenta, Afiliacion afiliacion,  String fecha, String monto, String monedaCod, Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa, String remoteAddress, HttpServletRequest request) throws Exception;
	
	// BANCO DE LA NACION - GODOFREDO URETA - 27/08/2010
	public PagoFacturaWS getPagoFacturaOnline(String transaccion, Cuenta cuenta, PagoFacturaWSImpl pagoFactura, String cNumReferencia, String cCodEntidad, String fecha, String monedaCod,Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa, String remoteAddress, HttpServletRequest request) throws Exception;
	
	//BANCO DE LA NACION - GODOFREDO URETA - 27/10/2009
	
	public PagoCuponera getConsultaCupon(String transaccion, Cuenta cuenta, Afiliacion afiliacion, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception;
	
	public PagoCuponera pagarCuponera(String transaccion, PagoCuponera pago, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception;
	
	public List getListaImportes(String codTributo, Cuenta cuenta) throws Exception;
	
	public List getListaImportesDetallado(String codTributo,String detalle) throws Exception;
	
	public List getListaDistritos(String codTributo) throws Exception;
	
	public List getListaDistritosJudiciales() throws Exception;
	
	public List getListaDependencias(String distrito) throws Exception;
	
	public PagoFactura getConsultaFactura(String transaccion, Cuenta cuenta, Afiliacion afiliacion, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception;
	
	public PagoFacturaWS getConsultaFacturaOnline(String transaccion, Cuenta cuenta, String numReferencia, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception;
	
	public PagoFactura pagarFactura(String transaccion, PagoFactura pago, String tipoEntidad, Usuario usuario, String remoteAddress) throws Exception;
	
	
	//DEPOSITOS JUDICIALES -MILY DOLORES -  26/08/2013 
	
	public DepositoJudicial getConsultaConsignacion(String transaccion, String indicador,DepositoJudicial dep, Usuario usuario, String remoteAddress) throws Exception;
	public DepositoJudicial getConsultaLiquidacion(String transaccion, String indicador,DepositoJudicial dep, Usuario usuario, String remoteAddress) throws Exception;
	
	
	//MULTIPAGOS -MILY DOLORES -  11/11/2013 
	
	public Multipago getConsultaMultipago(String transaccion,Multipago pago, Usuario usuario, String remoteAddress) throws Exception;
	public Multipago getPagoMultipago(String transaccion, Cuenta cuenta,MultipagoImpl pago, Usuario usuario, String remoteAddress) throws Exception;
	
	//MULTIPAGOS -MILY DOLORES -  27/10/2017 
	public PagoUniversidad getConsultaAlumno(String transaccion, Cuenta cuenta, String universidad,String codAlumno, Usuario usuario, String remoteAddress) throws Exception;
	public PagoUniversidad getConsultaTarifario(String transaccion, PagoUniversidad universidad,String remoteAddress) throws Exception;
	public PagoUniversidad getPagarUniversidad(String transaccion, PagoUniversidad universidad, Cuenta cuenta,Usuario usuario,String remoteAddress,HttpServletRequest request) throws Exception;
	
	//RECARGA BITEL 
	public PagoTelefono getConsultaRecargaOtros(String transaccion, PagoTelefono pagoTel,  Afiliacion afiliacion,  Usuario usuario, String remoteAddress, HttpServletRequest request, BigDecimal monto, String hidServicio) throws Exception;
	public PagoTelefono getRecargaOtros(String transaccion, PagoTelefono pagoTel, Cuenta cuenta, Afiliacion afiliacion,  String fecha, String monto, String monedaCod, Usuario usuario, String montoformat, BigDecimal montoSol, BigDecimal montoDol, BigDecimal montoTipCambio, String ctaorigen,String empresa,String hidServicio, String remoteAddress, HttpServletRequest request) throws Exception;
//	PAGO TASAS EDUCATIVAS
	public List getListaImporteTasaEducativa(String codTributo) throws Exception;
	public PagoUniversidad getPagarTasaEducativa(String transaccion, PagoUniversidad universidad, Cuenta cuenta,Usuario usuario,String remoteAddress,HttpServletRequest request) throws Exception;
}