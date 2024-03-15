/*
 * Creado el 23/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Movimiento;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
public class CuentaImpl extends OperacionImplNueva implements Cuenta, Serializable, Cloneable {
	private static LoggerSati log3 = LoggerSati.getInstance(CuentaImpl.class.getName());
    
	private String tipoProducto;
	private String numeroProducto;//codigo de cuenta.
	private String monedaProducto;
	private String nombreMonedaProductoMail;
	private BigDecimal saldo;//saldo disponible.
	private BigDecimal saldoContable;//saldo contable.
	private BigDecimal retencion;
	private BigDecimal retVariable;
	private BigDecimal totalRetencion;
	private BigDecimal interes;
	private String nroCuentaCci;
	private String nombreCliente;
	private List movimientos;
	private String estado;
	private String cuentaformateada;
	private String tipoCuenta;
	private String situacionCuenta;
	private String indicadorCtaCte;
	private String indicadorSaldo;
	private String indicadorItf;
	private String descripcionCuenta;
	private String numeroDesembolso;
	private String nPagina = "0";
	private String flagPrestamo;
	
	private String fechaInicial;
	private String fechaFin;
	private String cheque;
	private String trxCtaCte;
	private String corrori; 
	private String tranori;
	private String canalori;
	private String cajeroori;
	private String jrnaori;
	private String idenarchivo;
	private String cheqori;
	
	
	private ArrayList fechaInicialAnt;
	private ArrayList fechaFinAnt;
	private ArrayList chequeAnt;
	private ArrayList trxCtaCteAnt;
	private ArrayList corroriAnt;
	private ArrayList tranoriAnt;
	private ArrayList canaloriAnt;
	private ArrayList cajerooriAnt;
	private ArrayList jrnaoriAnt;
	private ArrayList idenarchivoAnt;
	private ArrayList cheqoriAnt;

	private BigDecimal saldoTangible;
	private BigDecimal saldoIntangible;
	private BigDecimal montoMovimiento;
	private BigDecimal cargoDiario;
	private BigDecimal saldoAnterior;
	private BigDecimal saldoBloqueado;
	private BigDecimal lineaCredito;
	List lista = null;
	private TransferenciaImpl  transferencia;
	private String tipoTarjeta;
	
	private static final String CUENTA = "395";	
	private static final String CUENTA_AH ="800"; // "001";
	private static final String MONEDA= "021";	
	private static final String SALDO_DISPONIBLE="802"; // "051";		
	private static final String SALDO_CONTABLE="801"; // "052";
	private static final String INTERES= "053";
	private static final String RETENCION1="803"; // "053";
	private static final String RETENCION2="804"; // "054";
	private static final String RETENCION3="805"; // "055";
	private static final String SALDOBLOQ ="806"; // "055";
	private static final String LINEACRE ="808"; // "055";
	private static final String SITUACION="807"; // "055";
	/* Usado para la consulta de CCI */  
	private static final String CUENTA_CCI= "101";
	private static final String CLIENTE= "107";
	private static final String MOVIMIENTOS= "393";
		
	private static final String GRUPO_MOVIMIENTO= "55";
	
	/* Usado para la consulta de Tarjeta de Credito */  
	private String moneda;

	private String lineaCreditoTC;
	private String dispConsumo;
	private String lineaDispEfectivo;
	private String dispEfectivo;
	private String montoPagoMinimo;
	private String montoPagoTotal;
	private String montoPagoFacturacion;
	private String montoMinimo;
	private String fechaLimitePago;
	private String fechaUltimoPago;
	private String fechaExpiracion;
	private String fechaApertura;
	private String fechaPago;
	private String situacion;
	private String desMoneda;
	private List movimientosTC;
	private String titular;
	private String fechaFacturacion;
	
	
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public String getFlagPrestamo() {
		return flagPrestamo;
	}

	public void setFlagPrestamo(String flagPrestamo) {
		this.flagPrestamo = flagPrestamo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getDesMoneda() {
		return desMoneda;
	}

	public void setDesMoneda(String desMoneda) {
		this.desMoneda = desMoneda;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getLineaCreditoTC() {
		return lineaCreditoTC;
	}

	public void setLineaCreditoTC(String lineaCreditoTC) {
		this.lineaCreditoTC = lineaCreditoTC;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public List getMovimientosTC() {
		return movimientosTC;
	}

	public void setMovimientosTC(List movimientosTC) {
		this.movimientosTC = movimientosTC;
	}

	

	public String getDispConsumo() {
		return dispConsumo;
	}

	public void setDispConsumo(String dispConsumo) {
		this.dispConsumo = dispConsumo;
	}

	

	public String getDispEfectivo() {
		return dispEfectivo;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getFechaLimitePago() {
		return fechaLimitePago;
	}

	public void setFechaLimitePago(String fechaLimitePago) {
		this.fechaLimitePago = fechaLimitePago;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFechaUltimoPago() {
		return fechaUltimoPago;
	}

	public void setFechaUltimoPago(String fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}


	public String getLineaDispEfectivo() {
		return lineaDispEfectivo;
	}

	public void setLineaDispEfectivo(String lineaDispEfectivo) {
		this.lineaDispEfectivo = lineaDispEfectivo;
	}

	public String getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	public String getMontoPagoFacturacion() {
		return montoPagoFacturacion;
	}

	public void setMontoPagoFacturacion(String montoPagoFacturacion) {
		this.montoPagoFacturacion = montoPagoFacturacion;
	}

	public String getMontoPagoMinimo() {
		return montoPagoMinimo;
	}

	public void setMontoPagoMinimo(String montoPagoMinimo) {
		this.montoPagoMinimo = montoPagoMinimo;
	}

	public String getMontoPagoTotal() {
		return montoPagoTotal;
	}

	public void setMontoPagoTotal(String montoPagoTotal) {
		this.montoPagoTotal = montoPagoTotal;
	}

	public void setDispEfectivo(String dispEfectivo) {
		this.dispEfectivo = dispEfectivo;
	}

	public String getNPagina() {
		return nPagina;
	}

	public void setNPagina(String pagina) {
		this.nPagina = pagina;
	}

	public BigDecimal getCargoDiario() {
		return cargoDiario;
	}
	
	public void setCargoDiario(BigDecimal cargoDiario) {
		this.cargoDiario = cargoDiario;
	}
	
	public BigDecimal getMontoMovimiento() {
		return montoMovimiento;
	}
	
	public void setMontoMovimiento(BigDecimal montoMovimiento) {
		this.montoMovimiento = montoMovimiento;
	}
	
	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}
	
	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	
	public BigDecimal getSaldoBloqueado() {
		return saldoBloqueado;
	}
	
	public void setSaldoBloqueado(BigDecimal saldoBloqueado) {
		this.saldoBloqueado = saldoBloqueado;
	}
	
	public BigDecimal getLineaCredito() {
		return lineaCredito;
	}
	
	public void setLineaCredito(BigDecimal lineaCredito) {
		this.lineaCredito = lineaCredito;
	}
	
	public BigDecimal getSaldoIntangible() {
		return saldoIntangible;
	}
	
	public void setSaldoIntangible(BigDecimal saldoIntangible) {
		this.saldoIntangible = saldoIntangible;
	}
	
	public BigDecimal getSaldoTangible() {
		return saldoTangible;
	}
	
	public void setSaldoTangible(BigDecimal saldoTangible) {
		this.saldoTangible = saldoTangible;
	}
	
	
    public TransferenciaImpl getTransferencia() {
        return transferencia;
    }
    public void setTransferencia(TransferenciaImpl transferencia) {
        this.transferencia = transferencia;
    }
	/**
	 * @return Devuelve monedaProducto.
	 */
	public String getMonedaProducto() {
		if(monedaProducto == null){
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME)||tipoProducto.equals(Constante.COD_CUENTA_CTS_ME))
				return Constante.MONEDA_DOLAR;			
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN)||tipoProducto.equals(Constante.COD_CUENTA_CTS_MN)||tipoProducto.equals(Constante.COD_CUENTA_PRESTAMOS)||tipoProducto.equals(Constante.COD_TARJETA_CREDITO))
				return Constante.MONEDA_SOL;
		}		
		return monedaProducto;
	}
	/**
	 * @param monedaProducto El monedaProducto a establecer.
	 */
	public void setMonedaProducto(String monedaProducto) {
		this.monedaProducto = monedaProducto;
	}
	/**
	 * @return Devuelve numeroProducto.
	 */
	public String getNumeroProducto() {
		return numeroProducto;
	}
	/**
	 * @param numeroProducto El numeroProducto a establecer.
	 */
	public void setNumeroProducto(String numeroProducto) {
		
		this.numeroProducto = numeroProducto;
		if(numeroProducto.length()>10){
			this.cuentaformateada = ObjectUtil.formatearCuenta(this.numeroProducto,Constante.FORMATO_CUENTA);	
		}
		
	}
	/**
	 * @return Devuelve saldo.
	 */
	public String getSaldo() {
		return ObjectUtil.formatearMonto(saldo);
	}
	/**
	 * @param saldo El saldo a establecer.
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return Devuelve tipoProducto.
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}
	/**
	 * @param tipoProducto El tipoProducto a establecer.
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	/**
	 * @return Devuelve nroCuentaCci.
	 */
	public String getNroCuentaCci() {
		return nroCuentaCci;
	}
	/**
	 * @param nroCuentaCci El nroCuentaCci a establecer.
	 */
	public void setNroCuentaCci(String nroCuentaCci) {
		this.nroCuentaCci = nroCuentaCci;
	}
	
	/**
	 * @return Devuelve nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nroCuentaCci El nombreCliente a establecer.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	public String getSimboloMonedaProducto(){
		if(monedaProducto==null){
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME)||tipoProducto.equals(Constante.COD_CUENTA_CTS_ME))
				return Constante.SIMBOLO_MONEDA_DOLAR;			
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN)||tipoProducto.equals(Constante.COD_CUENTA_CTS_MN)||tipoProducto.equals(Constante.COD_CUENTA_PRESTAMOS)||tipoProducto.equals(Constante.COD_TARJETA_CREDITO))
				return Constante.SIMBOLO_MONEDA_SOL;			
		}
					
		if(monedaProducto.equals(Constante.MONEDA_SOL))
			return Constante.SIMBOLO_MONEDA_SOL;
		if(monedaProducto.equals(Constante.MONEDA_DOLAR))
			return Constante.SIMBOLO_MONEDA_DOLAR;
		return null;
	}
	
	public String getNombreTipoProducto(){
		if(tipoProducto==null)
			return null;
		if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME)||tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN))
			return Constante.NOMBRE_CUENTA_AHORROS;
		if(tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN))
			return Constante.NOMBRE_CUENTA_CORRIENTE;
		if(tipoProducto.equals(Constante.COD_CUENTA_PRESTAMOS))
			return Constante.NOMBRE_CUENTA_PRESTAMOS;
		if(tipoProducto.equals(Constante.COD_CUENTA_CTS_ME)||tipoProducto.equals(Constante.COD_CUENTA_CTS_MN))
			return Constante.NOMBRE_CUENTA_CTS;		
		if(tipoProducto.equals(Constante.COD_TARJETA_CREDITO))
			return Constante.NOMBRE_TARJETA_CREDITO;	
		return null;
	}
	
	public String getNombreCuenta(){
		if(tipoProducto==null)
			return null;
		if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME)||tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN))
			return Constante.NOMBRE_AHORROS;
		if(tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN))
			return Constante.NOMBRE_CORRIENTE;
		if(tipoProducto.equals(Constante.COD_CUENTA_PRESTAMOS))
			return Constante.NOMBRE_PRESTAMOS;
		if(tipoProducto.equals(Constante.COD_CUENTA_CTS_ME)||tipoProducto.equals(Constante.COD_CUENTA_CTS_MN))
			return Constante.NOMBRE_CTS;
		if(tipoProducto.equals(Constante.COD_TARJETA_CREDITO))
			return Constante.NOMBRE_TARJETA;
		return null;
	}
	
	public String getNombreMonedaProducto(){
		if(monedaProducto == null){
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME)||tipoProducto.equals(Constante.COD_CUENTA_CTS_ME))
				return Constante.NOMBRE_MONEDA_DOLAR;			
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN)||tipoProducto.equals(Constante.COD_CUENTA_CTS_MN)||tipoProducto.equals(Constante.COD_CUENTA_PRESTAMOS)||tipoProducto.equals(Constante.COD_TARJETA_CREDITO))
				return Constante.NOMBRE_MONEDA_SOL;
		}
			
		else if(monedaProducto.equals(Constante.MONEDA_DOLAR))
			return Constante.NOMBRE_MONEDA_DOLAR;		
		else if (monedaProducto.equals(Constante.MONEDA_SOL))
			return Constante.NOMBRE_MONEDA_SOL;
		return monedaProducto;
	}
	
	public String getNombreMonedaProductoMail(){
		if(monedaProducto == null){
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME)||tipoProducto.equals(Constante.COD_CUENTA_CTS_ME)){
				nombreMonedaProductoMail =  Constante.NOMBRE_MONEDA_DOLAR_MAIL;
				return Constante.NOMBRE_MONEDA_DOLAR_MAIL;
			}
			if(tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN)||tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN)||tipoProducto.equals(Constante.COD_CUENTA_CTS_MN)||tipoProducto.equals(Constante.COD_CUENTA_PRESTAMOS)||tipoProducto.equals(Constante.COD_TARJETA_CREDITO) ){
				nombreMonedaProductoMail =  Constante.NOMBRE_MONEDA_SOL;
				return Constante.NOMBRE_MONEDA_SOL;
			}
				
		}
			
		else if(monedaProducto.equals(Constante.MONEDA_DOLAR)){
			nombreMonedaProductoMail =  Constante.NOMBRE_MONEDA_DOLAR_MAIL;
			return Constante.NOMBRE_MONEDA_DOLAR_MAIL;
		}					
		else if (monedaProducto.equals(Constante.MONEDA_SOL)){
			nombreMonedaProductoMail =  Constante.NOMBRE_MONEDA_SOL;
			return Constante.NOMBRE_MONEDA_SOL;
		}
			
		return nombreMonedaProductoMail;
	}
	/**
	 * @return Devuelve saldoContable.
	 */
	public String getSaldoContable() {
		return ObjectUtil.formatearMonto(saldoContable);
	}
	/**
	 * @param saldoContable El saldoContable a establecer.
	 */
	public void setSaldoContable(BigDecimal saldoContable) {
		this.saldoContable = saldoContable;
	}
	/**
	 * @return Devuelve retencion.
	 */
	public String getRetencion() {
		return ObjectUtil.formatearMonto(retencion);
	}
	/**
	 * @param retencion la retencion a establecer.
	 */
	public void setRetencion(BigDecimal retencion) {
		this.retencion = retencion;
	}
	/**
	 * @return Devuelve saldoContable.
	 */
	public String getInteres() {
		return ObjectUtil.formatearMonto(interes);
	}
	/**
	 * @param saldoContable El saldoContable a establecer.
	 */
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	/**
	 * @return Devuelve movimientos.
	 */
	public List getMovimientos() {
		return movimientos;
	}
	/**
	 * @param movimientos El movimientos a establecer.
	 */
	public void setMovimientos(List movimientos) {
		this.movimientos = movimientos;
	}
	
	public void getUltimosMovimientos(Transaction t, Usuario usuario) throws Exception{
	    this.cuentaformateada = ObjectUtil.formatearCuenta(this.getNumeroProducto(),Constante.FORMATO_CUENTA);
		Vector mascara = procesar(t);
		this.movimientos = new ArrayList();
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			if(elemento.elementAt(0).toString().equals(CuentaImpl.CUENTA)){
				this.setNumeroProducto(elemento.elementAt(6).toString());
				this.cuentaformateada = ObjectUtil.formatearCuenta(elemento.elementAt(6).toString(),Constante.FORMATO_CUENTA);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.MONEDA)){
				this.setMonedaProducto(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");				
				this.setSaldo(ObjectUtil.tramaToBigDecimal(monto));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_4)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");				
				this.setSaldoContable(ObjectUtil.tramaToBigDecimal(monto));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){
				String tramaGenerica =  elemento.elementAt(6).toString();					
			
				for(int j = 0; j< tramaGenerica.length();){
					try{
						MovimientoImpl movimiento = new MovimientoImpl();

						j=j+13;
						String nemonico = tramaGenerica.substring(j,j=j+4);
						

						try{
							movimiento.setConcepto(nemonico);
							movimiento.setDesConcepto(getDescripcionMov(nemonico));
						}
						catch (ArrayRuleException e) {
							log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
							e.printStackTrace();
						}
						j++;
						String fecha = tramaGenerica.substring(j,j=j+10);
					
						if(nemonico.trim().length() != 0){
						
							movimiento.setFecha(ObjectUtil.tramaToTimestampFormateado(fecha));
							}
					
						j+=3;
						String monto = tramaGenerica.substring(j,j=j+16).trim();
					
						String signed = tramaGenerica.substring(j,j=j+1);
						movimiento.setSigno(signed);
					
						movimiento.setMonto(signed+monto.trim());
						if(signed.equals("-")){
	                    	movimiento.setCargo("-"+monto);
	                    }else{
	                    	movimiento.setAbono(""+monto);
	                    }
						j+=32;
					
						if(nemonico.trim().length() != 0){
						
						this.movimientos.add((Movimiento)movimiento);}
					}
					catch(Exception e){
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						e.printStackTrace();
						break;
					}
				}
			}
			
		}
		
		t.setObjeto(this);
		
		//@DPS generarLog(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_AHORROS);
		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
	}
	
	
	public void getUltimosMovimientosCTS(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);	
		this.movimientos = new ArrayList();
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			String codProducto = this.numeroProducto.substring(0,2);
		    if ("54".equals(codProducto))
		        this.setMonedaProducto(Constante.MONEDA_SOL);
		    else if ("64".equals(codProducto))
		        this.setMonedaProducto(Constante.MONEDA_DOLAR);
		    
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			    //String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
			    //monto = ObjectUtil.replaceChar(monto,'.',"");
			    if (!ObjectUtil.isStringBlank(elemento.elementAt(6).toString().trim()))
			        this.setSaldoContable(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			    else
			        this.setSaldoContable(new BigDecimal("0.00"));
			    //this.setSaldo(ObjectUtil.tramaToBigDecimalMov("66666",2));

			}
			
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
			   // String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				//monto = ObjectUtil.replaceChar(monto,'.',"");
				if (!ObjectUtil.isStringBlank(elemento.elementAt(6).toString().trim()))
				    this.setSaldo(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2," "));
				else
				    this.setSaldo(new BigDecimal("0.00"));
				//this.setSaldo(ObjectUtil.tramaToBigDecimalMov("12566",2));
			}
			
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){
			    TransferenciaImpl transf = new TransferenciaImpl();
			    String nroTransferen = elemento.elementAt(6).toString();
			    //if(Constante.VER_LOG)System.out.println("setNroTransferencia:"+nroTransferen);
			    transf.setNroTransferencia(nroTransferen);
				this.setTransferencia(transf);
			}
			
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_4)){
				    if (elemento.elementAt(6)!= null){
						String tramaGenerica =  elemento.elementAt(6).toString();					
						tramaGenerica = tramaGenerica.trim();
						//if(Constante.VER_LOG)System.out.println("tramaGenerica="+tramaGenerica);
						//if(Constante.VER_LOG)System.out.println("tramaGenerica.length()="+tramaGenerica.length());
						for(int j = 0; j< tramaGenerica.length();){
						    MovimientoImpl movimiento = new MovimientoImpl();
						    if (j+80 < tramaGenerica.length()){
				                String tramita = tramaGenerica.substring(j,j=j+80);
				                //if(Constante.VER_LOG)log.warn("antes del trim tramita:"+tramita);
				                tramita = tramita.trim();
				                //if(Constante.VER_LOG)log.warn("despues del tramita:"+tramita);
				                try{
					                String oficina  = tramita.substring(0,4);
					                String f1  = tramita.substring(4,5);
					                String cajero  = tramita.substring(5,9);
					                String f2  = tramita.substring(9,10);
					                String nemonico = tramita.substring(10,14);
					                String f3  = tramita.substring(14,15);
					                String fecha 	= tramita.substring(15,25);
					                String f4    = tramita.substring(25,28);
					                String monto = tramita.substring(28,44).trim();
					                //monto = ObjectUtil.replaceChar(monto,',',"");
					                //monto = ObjectUtil.replaceChar(monto,'.',"");	
					                String signed = " ";
					                if (tramita.length()==45)
					                    signed 	= tramita.substring(44,45);
					               
					                //if(Constante.VER_LOG)System.out.println("nemonico="+nemonico);System.out.println("fecha="+fecha);
					                //if(Constante.VER_LOG)System.out.println("monto="+monto);
				                    
					                movimiento.setFecha(ObjectUtil.tramaToTimestamp(fecha));
				                    movimiento.setConcepto(nemonico);
				                    movimiento.setDesConcepto(getDescripcionMov(nemonico));
				                    //movimiento.setImporte(ObjectUtil.tramaToBigDecimal(monto,2,signed));
				                    //movimiento.setImporte(ObjectUtil.tramaToBigDecimalMov(monto.trim()+signed,2));
				                    movimiento.setSigno(signed);
				                    if(signed.equals("-")){
				                    	movimiento.setCargo("-"+monto);
				                    }else{
				                    	movimiento.setAbono(""+monto);
				                    }
				                    movimiento.setMonto(signed+monto.trim());
				                    this.movimientos.add((Movimiento)movimiento);
				                    signed = "";
				                    
				                }catch (Exception e) {
				                	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
                                    break;
                                }
						    }    
				            else{
				                //puchito
				                String puchito = tramaGenerica.substring(j,tramaGenerica.length());
				                //if(Constante.VER_LOG)System.out.println("puchito:"+puchito);
				                break;
				            }
						}
						
				    }else {
		      
				    }
			}
		}	
		
		t.setObjeto(this);
		
		//@DPS generarLog(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_CTS);
		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
	}
	
	public void getUltimosMovimientosCorriente(Transaction t, Usuario usuario, int nPagina) throws Exception{
		Vector mascara = procesar(t);	
		this.movimientos = new ArrayList();
		String codProducto="";
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){//Nro cuenta
				this.setNumeroProducto(elemento.elementAt(6).toString());
				this.cuentaformateada = ObjectUtil.formatearCuenta(elemento.elementAt(6).toString(),Constante.FORMATO_CUENTA);
				 codProducto = elemento.elementAt(6).toString().substring(0,2);
			    if ("00".equals(codProducto))
			        this.setMonedaProducto(Constante.MONEDA_SOL);
			    else if ("06".equals(codProducto))
			        this.setMonedaProducto(Constante.MONEDA_DOLAR);
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){//Saldo contable
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				if(monto.charAt(monto.length()-1)=='-'){
					this.setSaldoContable(new BigDecimal("-"+monto.substring(0,monto.length()-1)));
				}
				else//monto = ObjectUtil.replaceChar(monto,'.',"");				
					this.setSaldoContable(new BigDecimal(monto));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){//Saldo disponible
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				//monto = ObjectUtil.replaceChar(monto,'.',"");	
				if(monto.charAt(monto.length()-1)=='-'){
					this.setSaldo(new BigDecimal("-"+monto.substring(0,monto.length()-1)));
				}
				else//monto = ObjectUtil.replaceChar(monto,'.',"");				
					this.setSaldo(new BigDecimal(monto));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_4)){//Tipo de cuenta
				String data = elemento.elementAt(6).toString().trim();			
				this.setTipoCuenta(data);
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_5)){//Situacion de la cuenta
				String data = elemento.elementAt(6).toString().trim();		
				this.setSituacionCuenta(data);
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_6)){//descripcion de la cuenta
				String data = elemento.elementAt(6).toString().trim();		
				this.setDescripcionCuenta(data);
			}
			
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_8)){//fecha inicial de la busqueda
				String data = elemento.elementAt(6).toString().trim();
				this.setFechaInicial(ObjectUtil.getYYYYMMDDFormateada(data));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_9)){//fecha fin de la busqueda
				String data = elemento.elementAt(6).toString().trim();
			    this.setFechaFin(ObjectUtil.getYYYYMMDDFormateada(data));
		   }
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_10)){// cheque
				String data = elemento.elementAt(6).toString().trim();
				this.setCheque(data);
		    }
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_11)){// transaccion
				this.setTrxCtaCte(elemento.elementAt(6).toString().trim());	
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_12)){//corr-ori
				String data = elemento.elementAt(6).toString().trim();
			    this.setCorrOri(elemento.elementAt(6).toString().trim());
		   }				
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_13)){//tran-ori
				String data = elemento.elementAt(6).toString().trim();
			    this.setTranOri(elemento.elementAt(6).toString().trim());
		   }	
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_14)){//canal-ori
				String data = elemento.elementAt(6).toString().trim();
			    this.setCanalOri(elemento.elementAt(6).toString().trim());
		   }
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_15)){//cajero-ori
				String data = elemento.elementAt(6).toString().trim();
			    this.setCajeroOri(elemento.elementAt(6).toString().trim());
		   }
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_16)){//jrna-ori
				String data = elemento.elementAt(6).toString().trim();
			    this.setJrnaOri(elemento.elementAt(6).toString().trim());
		   }
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_17)){//identificadro de archivo
				String data = elemento.elementAt(6).toString().trim();
			    this.setIdenArchivo(elemento.elementAt(6).toString().trim());
			    //this.setIdenArchivo("");
			  
		   }	
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_18)){//cheque original
				String data = elemento.elementAt(6).toString().trim();
			    this.setCheqOri(elemento.elementAt(6).toString().trim());
		   }	
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_7)){//Trama de movimientos
					int v=0;
				    if (elemento.elementAt(6)!= null){
				    // if (elemento.elementAt(6)!= null && Integer.parseInt(elemento.elementAt(6).toString().trim().substring(0, 30).replace(" ", ""))!= 0){
												
						String tramaGenerica =  elemento.elementAt(6).toString().trim();
						
						//System.out.println("tramaGenerica:"+tramaGenerica);
						
						//System.out.println("tramaGenerica:"+tramaGenerica);
						//if(Constante.VER_LOG)System.out.println("tramaGenerica="+tramaGenerica);
						//if(Constante.VER_LOG)System.out.println("tramaGenerica.length()="+tramaGenerica.length());
						
						int nSecuencia = 1;
						
						if (nPagina == 1){
							nSecuencia = 0;
						} else if(nPagina == 2){
							nSecuencia = 40;
						} else if(nPagina == 3){
							nSecuencia = 80;
						} else if(nPagina == 4){
							nSecuencia = 120;
						} else if(nPagina == 5){
							nSecuencia = 160;
						} else if(nPagina == 6){
							nSecuencia = 200;
						} else if(nPagina == 7){
							nSecuencia = 240;
						} else if(nPagina == 8){
							nSecuencia = 280;
						} else if(nPagina == 9){
							nSecuencia = 320;
						} else if(nPagina == 10){
							nSecuencia = 360;
						}
						
						int nSec = 0;
						
						for(int j = 0; j< tramaGenerica.length();j=j+80){
						    MovimientoImpl movimiento = new MovimientoImpl();
						    nSec++;
						    
						    if (j+80 < tramaGenerica.length()){
				                String tramita = tramaGenerica.substring(j,j+80);
				                v++;
				                if(v<=40){
				                	//if(Constante.VER_LOG)System.out.println("LENGTH TRAMITA-->"+tramita.trim().length());
					                tramita = tramita.trim();
					                //System.out.println("tramita"+tramita);
					                //if(Constante.VER_LOG)System.out.println("tramita="+tramita);
					                if(tramita.length()>0){
						                String dia = tramita.substring(0,2).trim();
						                String mes =tramita.substring(2,4).trim();
						                String ao= tramita.substring(4,8).trim();
						                String fecha = tramita.substring(0,8).trim();
						                String nemonico = tramita.substring(8,16).trim();
						                String nroCheque = tramita.substring(16,24).trim();
						                String codOficina = "";
						                String tipOper = "";
						                String importeCte = "";
						                String ruc ="";
						                //*********************DOLARES**********************
						                if ("00".equals(codProducto)){
						                	ruc= tramita.substring(24,35).trim();
							                codOficina = tramita.substring(35,39).trim();
							                tipOper = tramita.substring(39,40).trim();
							                importeCte = tramita.substring(40,55).trim();
						                }									    
									    else if ("06".equals(codProducto)){
									    	codOficina = tramita.substring(24,28).trim();
							                tipOper = tramita.substring(28,29).trim();
							                importeCte = tramita.substring(29,44).trim();
									    }				                
						                //**************************************************
						                
						                String signed = "";

						                if (!"00".equals(dia)){
						                    if ("C".equals(tipOper))  signed = "-";
							                //else if ("A".equals(tipOper)) signed = "+";
						                    movimiento.setFecha(ObjectUtil.tramaToTimestampddmmyyyy(fecha));
						                    movimiento.setConcepto(nemonico);
						                    movimiento.setRuc(ruc);
						                    movimiento.setNroCheque(nroCheque);
						                    movimiento.setOficina(codOficina);
						                    movimiento.setDesConcepto(getDescripcionMov(nemonico));
						                    //movimiento.setImporte(ObjectUtil.tramaToBigDecimal(importe,2,signed));
						                    movimiento.setSigno(signed);
						                    BigDecimal imp= ObjectUtil.tramaToBigDecimal(importeCte,2,signed);
						                    movimiento.setImporte(imp);
						                    if(signed.equals("-")){
						                    	movimiento.setCargo(""+ObjectUtil.formatearMonto(imp));
						                    }else{
						                    	movimiento.setAbono(""+ObjectUtil.formatearMonto(imp));
						                    }
						                    
						                    if(nSec+1+nSecuencia<10){
						                    	movimiento.setSecuencia("0"+(nSec+nSecuencia));
							        		}else
							        			movimiento.setSecuencia(""+(nSec+nSecuencia));
						                    
						                    this.movimientos.add((Movimiento)movimiento);
						                    //if(Constante.VER_LOG)System.out.println("LIST SIZE() MOVIMIENTOS: "+movimientos.size());
						                    signed = "";
						                }
					                }else
					                	break;
				                }
						    }    
				            else{
				                //puchito
				                String puchito = tramaGenerica.substring(j,tramaGenerica.length());
				                //if(Constante.VER_LOG)System.out.println("puchito:"+puchito);
				                break;
				            }
						}
						
				    }
			}
		}	
		System.out.println("INDICAR FINAL CTAS CTES :"+this.getIdenArchivo());
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_CORRIENTE);
		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
	}
	
	public void getSaldoAhorros(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
			
		
		this.totalRetencion = new BigDecimal(0);
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(CuentaImpl.CUENTA_AH)){
			    this.setNumeroProducto(elemento.elementAt(6).toString().substring(elemento.elementAt(6).toString().length()-11,elemento.elementAt(6).toString().length()));
				this.cuentaformateada = ObjectUtil.formatearCuenta(elemento.elementAt(6).toString().substring(elemento.elementAt(6).toString().length()-11,elemento.elementAt(6).toString().length()),Constante.FORMATO_CUENTA);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.MONEDA)){
				this.setMonedaProducto(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDO_DISPONIBLE)){
				this.setSaldo(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDO_CONTABLE)){
				this.setSaldoContable(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.RETENCION1)){
				this.retVariable = ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString());
				this.totalRetencion = totalRetencion.add(retVariable); 
				this.setRetencion(totalRetencion);
			}		
			else if(elemento.elementAt(0).equals(CuentaImpl.RETENCION2)){
				this.retVariable = ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString());
				this.totalRetencion = this.totalRetencion.add(retVariable); 
				this.setRetencion(totalRetencion);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.RETENCION3)){
				this.retVariable = ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString());
				this.totalRetencion = this.totalRetencion.add(retVariable); 
				this.setRetencion(totalRetencion);
			}		
		}
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,Constante.REFRENDO_SALDO_AHORROS);		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
		
	}
	
	public void getSaldoCtaCte(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);

		this.totalRetencion = new BigDecimal(0);
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals("500")){
				this.setNumeroProducto(elemento.elementAt(6).toString());
				this.cuentaformateada = ObjectUtil.formatearCuenta(elemento.elementAt(6).toString(),Constante.FORMATO_CUENTA);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.MONEDA)){
				this.setMonedaProducto(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDO_DISPONIBLE)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				monto = ObjectUtil.replaceChar(monto,'-',"");
				this.setSaldo(ObjectUtil.tramaToBigDecimal(monto,2,elemento.elementAt(6).toString().substring(17,18)));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDO_CONTABLE)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				monto = ObjectUtil.replaceChar(monto,'-',"");
				this.setSaldoContable(ObjectUtil.tramaToBigDecimal(monto,2,elemento.elementAt(6).toString().substring(16,17)));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.RETENCION1)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				this.retVariable = ObjectUtil.tramaToBigDecimal(monto);
				this.totalRetencion = this.totalRetencion.add(retVariable);
				this.setRetencion(totalRetencion);
			}		
			else if(elemento.elementAt(0).equals(CuentaImpl.RETENCION2)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				this.retVariable = ObjectUtil.tramaToBigDecimal(monto);
				this.totalRetencion = this.totalRetencion.add(retVariable);
				this.setRetencion(totalRetencion);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.RETENCION3)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				this.retVariable = ObjectUtil.tramaToBigDecimal(monto);
				this.totalRetencion = this.totalRetencion.add(retVariable);
				this.setRetencion(totalRetencion);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDOBLOQ)){
			    String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				monto = ObjectUtil.replaceChar(monto,'-',"");
				this.setSaldoBloqueado(ObjectUtil.tramaToBigDecimal(monto,2,elemento.elementAt(6).toString().substring(16,17)));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SITUACION)){
				this.setSituacionCuenta(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.LINEACRE)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				monto = ObjectUtil.replaceChar(monto,'-',"");
				this.setLineaCredito(ObjectUtil.tramaToBigDecimal(monto,2,elemento.elementAt(6).toString().substring(16,17)));
			}
		}
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,Constante.REFRENDO_SALDO_CTACTE);		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
	}
	
	public void getConsultaCci(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);	
		String c1="";
		String c2="";
		String c3="";
		String c4="";
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				c1 = elemento.elementAt(6).toString();
			}			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				Integer num = new Integer(elemento.elementAt(6).toString());
//				if(num.intValue()<101)
//					c2 = "000";
//				else
					c2 = elemento.elementAt(6).toString();
			}			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				c3 = elemento.elementAt(6).toString();
			}			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				c4 = elemento.elementAt(6).toString();
			}
		}
		
		this.setNroCuentaCci(c1+"-"+c2+"-"+c3+"-"+c4);
		if (c3.length() > 0){
		    String codTipPer = c3.substring(1, 3);
			
			if(codTipPer.equals(Constante.COD_CUENTA_AHORROS_ME)||codTipPer.equals(Constante.COD_CUENTA_CORRIENTE_ME)||codTipPer.equals(Constante.COD_CUENTA_CTS_ME))
			    this.setMonedaProducto(Constante.MONEDA_DOLAR);			
			if(codTipPer.equals(Constante.COD_CUENTA_AHORROS_MN)||codTipPer.equals(Constante.COD_CUENTA_CORRIENTE_MN)||codTipPer.equals(Constante.COD_CUENTA_CTS_MN)||codTipPer.equals(Constante.COD_CUENTA_PRESTAMOS))
			    this.setMonedaProducto(Constante.MONEDA_SOL);
		}
		
		t.setObjeto(this);
		
		//@DPS generarLog(t,usuario,Constante.REFRENDO_CCI);
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
		
	}
	
	public void getConsultaCtaCteCci(Transaction t, Usuario usuario) throws Exception{
		/*Vector mascara = procesar(t);	
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(CuentaImpl.CUENTA)){
				this.setNumeroProducto(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.CUENTA_CCI)){
				this.setNroCuentaCci(elemento.elementAt(5).toString());
			}
		}
		
		generarLog(t,usuario,null);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();*/
	    

		Vector mascara = procesar(t);	
		String c1="";
		String c2="";
		String c3="";
		String c4="";
		String c5="";
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				c1 = elemento.elementAt(6).toString();
				
				
			}			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				Integer num = new Integer(elemento.elementAt(6).toString());
				//if(num.intValue()<101)
				//	c2 = "000";
				//else
					c2 = elemento.elementAt(6).toString();
				
			}			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				c3 = elemento.elementAt(6).toString();
				
			}			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				c4 = elemento.elementAt(6).toString();
				
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.titular = elemento.elementAt(6).toString();
			
			}
		}
		this.setNroCuentaCci(c1+"-"+c2+"-"+c3+"-"+c4);
		if (c3.length() > 0){
		    String codTipPer = c3.substring(1, 3);
			
			if(codTipPer.equals(Constante.COD_CUENTA_AHORROS_ME)||codTipPer.equals(Constante.COD_CUENTA_CORRIENTE_ME)||codTipPer.equals(Constante.COD_CUENTA_CTS_ME))
			    this.setMonedaProducto(Constante.MONEDA_DOLAR);			
			if(codTipPer.equals(Constante.COD_CUENTA_AHORROS_MN)||codTipPer.equals(Constante.COD_CUENTA_CORRIENTE_MN)||codTipPer.equals(Constante.COD_CUENTA_CTS_MN)||codTipPer.equals(Constante.COD_CUENTA_PRESTAMOS))
			    this.setMonedaProducto(Constante.MONEDA_SOL);
		}
				
		t.setObjeto(this);
		
		//@DPS generarLog(t,usuario,Constante.REFRENDO_CTACTECCI);
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
		
	
		
		
	}
	
	public void getSaldoCTS(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);

		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				String numCuenta = elemento.elementAt(6).toString().trim().substring(4,elemento.elementAt(6).toString().length());
				this.setNumeroProducto(numCuenta);
				this.setMonedaProducto(ObjectUtil.getCodMonedaxCuenta(numCuenta));
				this.cuentaformateada = ObjectUtil.formatearCuenta(numCuenta,Constante.FORMATO_CUENTA);
			}
			
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
				this.setSaldoContable(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){
				this.setSaldo(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_4)){
				this.setSaldoTangible(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_5)){
				this.setSaldoIntangible(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_6)){
				this.setRetencion(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_7)){
				this.setInteres(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_8)){
				this.setMontoMovimiento(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_9)){
				this.setCargoDiario(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_10)){
				this.setSaldoAnterior(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2));
			}
			/*else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_11)){
				this.setSaldoAnterior(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),7));
			}*/
		}
		
		t.setObjeto(this);

		//@DPS generarLog(t,usuario,Constante.REFRENDO_SALDO_CTS);		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
		
	}
	
	public void getSaldoPrestamo(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);

		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(CuentaImpl.CUENTA)){
				this.setNumeroProducto(elemento.elementAt(6).toString());
				this.cuentaformateada = ObjectUtil.formatearCuenta(elemento.elementAt(6).toString(),Constante.FORMATO_CUENTA);
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.CLIENTE)){
				this.setNombreCliente(elemento.elementAt(5).toString());
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDO_DISPONIBLE)){
				this.setSaldo(ObjectUtil.tramaToBigDecimal(elemento.elementAt(5).toString()));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.INTERES)){
				this.setSaldoContable(ObjectUtil.tramaToBigDecimal(elemento.elementAt(5).toString()));
			}
			else if(elemento.elementAt(0).equals(CuentaImpl.SALDO_CONTABLE)){
				this.setInteres(ObjectUtil.tramaToBigDecimal(elemento.elementAt(5).toString()));
			}
		}
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,null);		
		//if(getArrayRuleException()!=null)
		//	throw getArrayRuleException();
		
	}
	
	public void getSaldoTCredito(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);

		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setLineaCreditoTC(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setDispConsumo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setLineaDispEfectivo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setDispEfectivo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setMontoPagoMinimo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				//this.setMontoPagoMinimo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal("000000000006123")));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setMontoPagoTotal(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				//this.setMontoPagoTotal(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal("000000000011123")));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setMontoPagoFacturacion(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				//this.setMontoPagoFacturacion(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal("000000000110200")));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setSaldo(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				this.setMontoMinimo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				this.setFechaExpiracion(ObjectUtil.getYYYYMMDDFormateada(elemento.elementAt(6).toString().trim()));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				this.setFechaLimitePago(ObjectUtil.getYYYYMMDDFormateada(elemento.elementAt(6).toString().trim()));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
				this.setFechaUltimoPago(ObjectUtil.getYYYYMMDDFormateada(elemento.elementAt(6).toString().trim()));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				this.setFechaPago(elemento.elementAt(6).toString().trim());
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
				
				this.setNumeroProducto(elemento.elementAt(6).toString().trim());
				
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
				this.setSituacion(elemento.elementAt(6).toString().trim());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
				this.setDesMoneda(elemento.elementAt(6).toString().trim());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_26)){
				this.setNombreCliente(elemento.elementAt(6).toString().trim());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_25)){
				this.setFechaFacturacion(ObjectUtil.getYYYYMMDDFormateada(elemento.elementAt(6).toString().trim()));
				
			}
		}
		t.setObjeto(this);
	
	}
	
	public void getUltimosMovimientosTCredito(Transaction t, Usuario usuario) throws Exception{
		
		
	    
		Vector mascara = procesar(t);
		this.movimientosTC = new ArrayList();
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_20)){
				this.setNumeroProducto(elemento.elementAt(6).toString().trim());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_21)){
				this.setTipoTarjeta(elemento.elementAt(6).toString().trim());
				
			}
				
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_22)){
				this.setMoneda(elemento.elementAt(6).toString().trim());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_23)){
				this.setLineaCreditoTC(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_24)){
				this.setDispConsumo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString())));
				
			}
			
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_19)){
				String tramaGenerica =  elemento.elementAt(6).toString();	
				
							
				for(int j = 0; j< tramaGenerica.length();){
					
				    if (j+80 <= tramaGenerica.length()){
		                String tramita = tramaGenerica.substring(j,j=j+80);
		                
		                try{
		                	MovimientoImpl movimiento = new MovimientoImpl();
							
							if(!tramita.trim().equals("")){
													
							String txtFecha    = tramita.substring(0,10);
		                	String txtDescripcion    = tramita.substring(11,31); 
		                	String txtSigno		= tramita.substring(52,54).trim();
		                	String txtmonto  = tramita.substring(32,52);
		                	
		                	
							movimiento.setFechaFormat(txtFecha);
							movimiento.setDesConcepto(txtDescripcion);
							movimiento.setSigno(txtSigno);
												
							if(txtSigno.trim().equals("-")){movimiento.setMonto(txtSigno.trim()+txtmonto);}
							else{movimiento.setMonto(txtmonto);}
							
							this.movimientosTC.add((Movimiento)movimiento);
							
							
							}
						}catch(Exception e){
							log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
							e.printStackTrace();
							break;
						}
				    }
					
					
				}
			}
					
		}
		
		
		
		t.setObjeto(this);

	}
	
		
	/**
	 * @return Devuelve estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado El estado a establecer.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCuentaFormateada(){
		cuentaformateada = ObjectUtil.formatearCuenta(this.numeroProducto,Constante.FORMATO_CUENTA);
		return ObjectUtil.formatearCuenta(this.numeroProducto,Constante.FORMATO_CUENTA);
	}

	public String getCuentaOculta(){
		return ObjectUtil.ocultarCuenta(this.numeroProducto,Constante.FORMATO_CUENTA);
	}

	public boolean getEsCuentaAhorro(){		
		
		if(this.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)||this.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME))
			return true;
		return false;
	}
	
	public boolean getEsCuentaCorriente(){		
		if(this.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)||this.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME))
			return true;
		return false;
	}

	public boolean getEsCuentaPrestamo(){		
		if(this.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS))
			return true;
		return false;
	}
	
	public boolean getEsCuentaCTS(){
		System.out.println(":::::::::::::VALIDACION DE CUENTA CTS::::::::::::::::"+this.getTipoProducto());
		if(this.getTipoProducto().equals(Constante.COD_CUENTA_CTS_MN)||this.getTipoProducto().equals(Constante.COD_CUENTA_CTS_ME))
			return true;
		return false;
				
	}
	
	public boolean getEsTarjetaCredito(){		
		if(this.getTipoProducto().equals(Constante.COD_TARJETA_CREDITO))
			return true;
		return false;
	}
	
	/* (sin Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		// TODO Ap?ndice de m?todo generado autom?ticamente
		return super.clone();
	}
	
	/**
	 * @return Devuelve cuentaformateada.
	 */
	public String getCuentaformateada() {
		return cuentaformateada;
	}
	/**
	 * @param cuentaformateada El cuentaformateada a establecer.
	 */
	public void setCuentaformateada(String cuentaformateada) {
		this.cuentaformateada = cuentaformateada;
	}
	public String getDescripcionMov(String codigo) throws Exception{
		codigo=codigo.trim();	
		if(lista==null)
			lista = DAOFactory.getDescripcionDAO().getDescripcionesMov();
		if(lista!=null && codigo!=null){
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				MovDescripcionImpl element = (MovDescripcionImpl) iter.next();
				if(element.getCodigo().trim().equals(codigo)){
					return element.getDescripcion();
				}	
			}
		}
		return "";
	}
	/**
	 * @return Devuelve descripcionCuenta.
	 */
	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}
	/**
	 * @param descripcionCuenta El descripcionCuenta a establecer.
	 */
	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}
	/**
	 * @return Devuelve situacionCuenta.
	 */
	public String getSituacionCuenta() {
		return situacionCuenta;
	}
	/**
	 * @param situacionCuenta El situacionCuenta a establecer.
	 */
	public void setSituacionCuenta(String situacionCuenta) {
		this.situacionCuenta = situacionCuenta;
	}
	/**
	 * @return Devuelve indicadorCtaCte.
	 */
	public String getIndicadorCtaCte() {
		return indicadorCtaCte;
	}
	/**
	 * @param indicadorCtaCte El situacionCuenta a establecer.
	 */
	public void setIndicadorCtaCte(String indicadorCtaCte) {
		this.indicadorCtaCte = indicadorCtaCte;
	}
	
	/**
	 * @return Devuelve indicadorSaldo.
	 */
	public String getIndicadorSaldo() {
		return indicadorSaldo;
	}
	/**
	 * @param indicadorSaldo El situacionCuenta a establecer.
	 */
	public void setIndicadorSaldo(String indicadorSaldo) {
		this.indicadorSaldo = indicadorSaldo;
	}
	
	/**
	 * @return Devuelve indicadorItf.
	 */
	public String getIndicadorItf() {
		return indicadorItf;
	}
	/**
	 * @param indicadorItf El situacionCuenta a establecer.
	 */
	public void setIndicadorItf(String indicadorItf) {
		this.indicadorItf = indicadorItf;
	}
	
	/**
	 * @return Devuelve tipoCuenta.
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	/**
	 * @param tipoCuenta El tipoCuenta a establecer.
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public String getNumeroDesembolso() {
		return numeroDesembolso;
	}

	public void setNumeroDesembolso(String numeroDesembolso) {
		this.numeroDesembolso = numeroDesembolso;
	}
	
	
	

	public String getCheque() {
		return cheque;
	}
	
	public void setCheque(String cheque) {
		this.cheque = cheque;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	/**
	 * @return Returns the trxCtaCte.
	 */
	public String getTrxCtaCte() {
		return trxCtaCte;
	}
	/**
	 * @param trxCtaCte The trxCtaCte to set.
	 */
	public void setTrxCtaCte(String trxCtaCte) {
		this.trxCtaCte = trxCtaCte;
	}

	public String getCorrOri() {
		return corrori;
	}
	
	public void setCorrOri(String corrori) {
		this.corrori = corrori;
	}
	
	public String getTranOri() {
		return tranori;
	}
	
	public void setTranOri(String tranori) {
		this.tranori = tranori;
	}
	
	public String getCanalOri() {
		return canalori;
	}
	
	public void setCanalOri(String canalori) {
		this.canalori = canalori;
	}
	
	public String getCajeroOri() {
		return cajeroori;
	}
	
	public void setCajeroOri(String cajeroori) {
		this.cajeroori = cajeroori;
	}
	
	public String getJrnaOri() {
		return jrnaori;
	}
	
	public void setJrnaOri(String jrnaori) {
		this.jrnaori = jrnaori;
	}
	
	public String getIdenArchivo() {
		return idenarchivo;
	}
	
	public void setIdenArchivo(String idenarchivo) {
		this.idenarchivo = idenarchivo;
	}
	
	public String getCheqOri() {
		return cheqori;
	}
	
	public void setCheqOri(String cheqori) {
		this.cheqori = cheqori;
	}
	/**
	 * @return Returns the cajerooriAnt.
	 */
	public ArrayList getCajerooriAnt() {
		return cajerooriAnt;
	}
	/**
	 * @param cajerooriAnt The cajerooriAnt to set.
	 */
	public void setCajerooriAnt(ArrayList cajerooriAnt) {
		this.cajerooriAnt = cajerooriAnt;
	}
	/**
	 * @return Returns the canaloriAnt.
	 */
	public ArrayList getCanaloriAnt() {
		return canaloriAnt;
	}
	/**
	 * @param canaloriAnt The canaloriAnt to set.
	 */
	public void setCanaloriAnt(ArrayList canaloriAnt) {
		this.canaloriAnt = canaloriAnt;
	}
	/**
	 * @return Returns the chequeAnt.
	 */
	public ArrayList getChequeAnt() {
		return chequeAnt;
	}
	/**
	 * @param chequeAnt The chequeAnt to set.
	 */
	public void setChequeAnt(ArrayList chequeAnt) {
		this.chequeAnt = chequeAnt;
	}
	/**
	 * @return Returns the corroriAnt.
	 */
	public ArrayList getCorroriAnt() {
		return corroriAnt;
	}
	/**
	 * @param corroriAnt The corroriAnt to set.
	 */
	public void setCorroriAnt(ArrayList corroriAnt) {
		this.corroriAnt = corroriAnt;
	}
	/**
	 * @return Returns the fechaFinAnt.
	 */
	public ArrayList getFechaFinAnt() {
		return fechaFinAnt;
	}
	/**
	 * @param fechaFinAnt The fechaFinAnt to set.
	 */
	public void setFechaFinAnt(ArrayList fechaFinAnt) {
		this.fechaFinAnt = fechaFinAnt;
	}
	/**
	 * @return Returns the fechaInicialAnt.
	 */
	public ArrayList getFechaInicialAnt() {
		return fechaInicialAnt;
	}
	/**
	 * @param fechaInicialAnt The fechaInicialAnt to set.
	 */
	public void setFechaInicialAnt(ArrayList fechaInicialAnt) {
		this.fechaInicialAnt = fechaInicialAnt;
	}
	/**
	 * @return Returns the idenarchivoAnt.
	 */
	public ArrayList getIdenarchivoAnt() {
		return idenarchivoAnt;
	}
	/**
	 * @param idenarchivoAnt The idenarchivoAnt to set.
	 */
	public void setIdenarchivoAnt(ArrayList idenarchivoAnt) {
		this.idenarchivoAnt = idenarchivoAnt;
	}
	
	public ArrayList getCheqoriAnt() {
		return cheqoriAnt;
	}
	
	public void setCheqoriAnt(ArrayList cheqoriAnt) {
		this.cheqoriAnt = cheqoriAnt;
	}
	

	public ArrayList getJrnaoriAnt() {
		return jrnaoriAnt;
	}
	/**
	 * @param jrnaoriAnt The jrnaoriAnt to set.
	 */
	public void setJrnaoriAnt(ArrayList jrnaoriAnt) {
		this.jrnaoriAnt = jrnaoriAnt;
	}
	/**
	 * @return Returns the tranoriAnt.
	 */
	public ArrayList getTranoriAnt() {
		return tranoriAnt;
	}
	/**
	 * @param tranoriAnt The tranoriAnt to set.
	 */
	public void setTranoriAnt(ArrayList tranoriAnt) {
		this.tranoriAnt = tranoriAnt;
	}
	/**
	 * @return Returns the trxCtaCteAnt.
	 */
	public ArrayList getTrxCtaCteAnt() {
		return trxCtaCteAnt;
	}
	/**
	 * @param trxCtaCteAnt The trxCtaCteAnt to set.
	 */
	public void setTrxCtaCteAnt(ArrayList trxCtaCteAnt) {
		this.trxCtaCteAnt = trxCtaCteAnt;
	}

	/**
	 * @return
	 */
	public Object getPagina() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	@Override
	public String toString() {
		return "CuentaImpl [tipoProducto=" + tipoProducto + ", numeroProducto="
				+ numeroProducto + ", monedaProducto=" + monedaProducto
				+ ", nombreMonedaProductoMail=" + nombreMonedaProductoMail
				+ ", saldo=" + saldo + ", saldoContable=" + saldoContable
				+ ", retencion=" + retencion + ", retVariable=" + retVariable
				+ ", totalRetencion=" + totalRetencion + ", interes=" + interes
				+ ", nroCuentaCci=" + nroCuentaCci + ", nombreCliente="
				+ nombreCliente + ", movimientos=" + movimientos + ", estado="
				+ estado + ", cuentaformateada=" + cuentaformateada
				+ ", tipoCuenta=" + tipoCuenta + ", situacionCuenta="
				+ situacionCuenta + ", indicadorCtaCte=" + indicadorCtaCte
				+ ", indicadorSaldo=" + indicadorSaldo + ", indicadorItf="
				+ indicadorItf + ", descripcionCuenta=" + descripcionCuenta
				+ ", numeroDesembolso=" + numeroDesembolso + ", nPagina="
				+ nPagina + ", flagPrestamo=" + flagPrestamo
				+ ", fechaInicial=" + fechaInicial + ", fechaFin=" + fechaFin
				+ ", cheque=" + cheque + ", trxCtaCte=" + trxCtaCte
				+ ", corrori=" + corrori + ", tranori=" + tranori
				+ ", canalori=" + canalori + ", cajeroori=" + cajeroori
				+ ", jrnaori=" + jrnaori + ", idenarchivo=" + idenarchivo
				+ ", cheqori=" + cheqori + ", fechaInicialAnt="
				+ fechaInicialAnt + ", fechaFinAnt=" + fechaFinAnt
				+ ", chequeAnt=" + chequeAnt + ", trxCtaCteAnt=" + trxCtaCteAnt
				+ ", corroriAnt=" + corroriAnt + ", tranoriAnt=" + tranoriAnt
				+ ", canaloriAnt=" + canaloriAnt + ", cajerooriAnt="
				+ cajerooriAnt + ", jrnaoriAnt=" + jrnaoriAnt
				+ ", idenarchivoAnt=" + idenarchivoAnt + ", cheqoriAnt="
				+ cheqoriAnt + ", saldoTangible=" + saldoTangible
				+ ", saldoIntangible=" + saldoIntangible + ", montoMovimiento="
				+ montoMovimiento + ", cargoDiario=" + cargoDiario
				+ ", saldoAnterior=" + saldoAnterior + ", saldoBloqueado="
				+ saldoBloqueado + ", lineaCredito=" + lineaCredito
				+ ", lista=" + lista + ", transferencia=" + transferencia
				+ ", tipoTarjeta=" + tipoTarjeta + ", moneda=" + moneda
				+ ", lineaCreditoTC=" + lineaCreditoTC + ", dispConsumo="
				+ dispConsumo + ", lineaDispEfectivo=" + lineaDispEfectivo
				+ ", dispEfectivo=" + dispEfectivo + ", montoPagoMinimo="
				+ montoPagoMinimo + ", montoPagoTotal=" + montoPagoTotal
				+ ", montoPagoFacturacion=" + montoPagoFacturacion
				+ ", montoMinimo=" + montoMinimo + ", fechaLimitePago="
				+ fechaLimitePago + ", fechaUltimoPago=" + fechaUltimoPago
				+ ", fechaExpiracion=" + fechaExpiracion + ", fechaApertura="
				+ fechaApertura + ", fechaPago=" + fechaPago + ", situacion="
				+ situacion + ", desMoneda=" + desMoneda + ", movimientosTC="
				+ movimientosTC + ", titular=" + titular
				+ ", fechaFacturacion=" + fechaFacturacion + "]";
	}
	
	
	
}

