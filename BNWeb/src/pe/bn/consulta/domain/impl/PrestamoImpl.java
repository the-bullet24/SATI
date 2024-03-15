/*
 * Creado el 05/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.consulta.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.consulta.domain.CalendarioPrestamo;
import pe.bn.consulta.domain.Prestamo;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.OperacionImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PrestamoImpl extends OperacionImpl implements Prestamo, Serializable  {
	private static LoggerSati log3 = LoggerSati.getInstance(PrestamoImpl.class.getName());
	
	private String id;
	private String cliente;
	private BigDecimal saldoActual;
	private BigDecimal interesActual;
	private BigDecimal deudaActual;
	private String moneda;
	private String DOI;
	private String nroDocumento;	
	private String pagare;
	private String direccion;
	private String oficina;
	private String garante;
	private List calendarioPrestamo;
	private Timestamp fechaPrestamo;
	private String cuenta;
	private String cuentaFormateada;
	
	//input
	private String nroCuotas;
	private String flagPeriodoGracia;
	private String flagEndoso;
	private String flagCuotaProtegida;
	private String tipoEnvio;
	private String tipoPrestamo;
	private String tipoOperacion;
	private String tarjeta;
	private String montoSolicitado;
	
	//output
	private String nroCuotasDes;
	private String montoPrestamo;
	private String saldoDeuda;
	private String montoCuota;
	private String montoCuotaProtegida;
	private String saldoNeto;
	private String diaPago;
	private String segDesgravamen;
	private String tasa;
	private String tcea;
	private String nroPrestamo;
	private String fechaVenCuota;
	private String fechaVenPrestamo;
	private String desPeriodoGracia;
	private String fechaOperacion;
	private String horaOperacion;
	private String numPrestamo;
	
	//urlPrestamo
	private String url_condicinesPrestamo; 
	private String url_creditoPrestamo;
	private String url_polizaPrestamo;
	private String url_solicitudPrestamo;
	private String url_tarifarioPrestamo;
	private String url_tasas;
	
	//Otros
	private String montoMinimo;
	
	
	private static final String NOMBRE		= "107";
	private static final String _DOI 		= "082"; 
	private static final String DIRECCION	= "083"; 
	private static final String GARANTE		= "084"; 
	private static final String PAGARE		= "085"; 
	private static final String MONEDA		= "021"; 
	private static final String IMPORTE		= "051"; 
	private static final String FECHA		= "115";
	private static final String CALENDARIO	= "393";
	
	
	
	public String getNumPrestamo() {
		return numPrestamo;
	}
	public void setNumPrestamo(String numPrestamo) {
		this.numPrestamo = numPrestamo;
	}
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getHoraOperacion() {
		return horaOperacion;
	}
	public void setHoraOperacion(String horaOperacion) {
		this.horaOperacion = horaOperacion;
	}
	public String getCuentaFormateada() {
		return cuentaFormateada;
	}
	public void setCuentaFormateada(String cuentaFormateada) {
		this.cuentaFormateada = cuentaFormateada;
	}
	public String getMontoMinimo() {
		return montoMinimo;
	}
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	public String getDesPeriodoGracia() {
		return desPeriodoGracia;
	}
	public void setDesPeriodoGracia(String desPeriodoGracia) {
		this.desPeriodoGracia = desPeriodoGracia;
	}
	public String getNroCuotasDes() {
		return nroCuotasDes;
	}
	public void setNroCuotasDes(String nroCuotasDes) {
		this.nroCuotasDes = nroCuotasDes;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getFlagCuotaProtegida() {
		return flagCuotaProtegida;
	}
	public void setFlagCuotaProtegida(String flagCuotaProtegida) {
		this.flagCuotaProtegida = flagCuotaProtegida;
	}
	public String getFlagEndoso() {
		return flagEndoso;
	}
	public void setFlagEndoso(String flagEndoso) {
		this.flagEndoso = flagEndoso;
	}
	public String getFlagPeriodoGracia() {
		return flagPeriodoGracia;
	}
	public void setFlagPeriodoGracia(String flagPeriodoGracia) {
		this.flagPeriodoGracia = flagPeriodoGracia;
	}
	public String getNroCuotas() {
		return nroCuotas;
	}
	public void setNroCuotas(String nroCuotas) {
		this.nroCuotas = nroCuotas;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}
	/**
	 * @return Devuelve calendarioPrestamo.
	 */
	public List getCalendarioPrestamo() {
		return calendarioPrestamo;
	}
	/**
	 * @param calendarioPrestamo El calendarioPrestamo a establecer.
	 */
	public void setCalendarioPrestamo(List calendarioPrestamo) {
		this.calendarioPrestamo = calendarioPrestamo;
	}
	/**
	 * @return Devuelve cliente.
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente El cliente a establecer.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return Devuelve deudaActual.
	 */
	public String getDeudaActual() {
		return ObjectUtil.formatearMonto(deudaActual);
	}
	/**
	 * @param deudaActual El deudaActual a establecer.
	 */
	public void setDeudaActual(BigDecimal deudaActual) {
		this.deudaActual = deudaActual;
	}
	/**
	 * @return Devuelve direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion El direccion a establecer.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return Devuelve dOI.
	 */
	public String getDOI() {
		return DOI;
	}
	/**
	 * @param doi El dOI a establecer.
	 */
	public void setDOI(String doi) {
		DOI = doi;
	}
	/**
	 * @return Devuelve id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id El id a establecer.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Devuelve interesActual.
	 */
	public String getInteresActual() {
		return ObjectUtil.formatearMonto(interesActual);
	}
	/**
	 * @param interesActual El interesActual a establecer.
	 */
	public void setInteresActual(BigDecimal interesActual) {
		this.interesActual = interesActual;
	}
	/**
	 * @return Devuelve oficina.
	 */
	public String getOficina() {
		return oficina;
	}
	/**
	 * @param oficina El oficina a establecer.
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return Devuelve saldoActual.
	 */
	public String getSaldoActual() {
		return ObjectUtil.formatearMonto(saldoActual);
	}
	/**
	 * @param saldoActual El saldoActual a establecer.
	 */
	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}
	/**
	 * @return Devuelve garante.
	 */
	public String getGarante() {
		return garante;
	}
	/**
	 * @param garante El garante a establecer.
	 */
	public void setGarante(String garante) {
		this.garante = garante;
	}
	/**
	 * @return Devuelve pagare.
	 */
	public String getPagare() {
		return pagare;
	}
	/**
	 * @param pagare El pagare a establecer.
	 */
	public void setPagare(String pagare) {
		this.pagare = pagare;
	}
	
	public void getPrestamoActual(Transaction t, Usuario usuario) throws Exception {
		Vector mascara = procesar(t);
		String codigo = "";
		String cuenta = "";
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				cuenta = elemento.elementAt(6).toString().trim();
				cuenta = cuenta.substring(cuenta.length()-11);
				this.setCuenta(cuenta);
				cuenta = ObjectUtil.formatearCuenta(cuenta,Constante.FORMATO_CUENTA);
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				//codigo = String.valueOf(Integer.parseInt(elemento.elementAt(6).toString().trim()));
				codigo = String.valueOf(elemento.elementAt(6).toString().trim());
				codigo = codigo.substring(cuenta.length());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setSaldoActual(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setInteresActual(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setDeudaActual(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setCliente(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setSegDesgravamen(""+ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			
			}
		}		
		this.setId(cuenta+"-"+codigo);
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,Constante.REFRENDO_SALDO_PRESTAMO);
		//@DPS if(getArrayRuleException()!=null)
		//@DPS	throw getArrayRuleException();
	}
	
	public void getCalendarioPagos(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		int x = 0;
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setCliente(elemento.elementAt(6).toString());
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setGarante(elemento.elementAt(6).toString());
			} 
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    this.setPagare(ObjectUtil.formatearCuenta(elemento.elementAt(6).toString(),Constante.FORMATO_CUENTA+"-xx"));
			} 
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setDOI(elemento.elementAt(6).toString());
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setNroDocumento(elemento.elementAt(6).toString().trim());
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setDireccion(elemento.elementAt(6).toString().trim());
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto = ObjectUtil.replaceChar(monto,'.',"");
				this.setDeudaActual(ObjectUtil.tramaToBigDecimal(monto));
			} 
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setFechaPrestamo(ObjectUtil.tramaToTimestamp(elemento.elementAt(6).toString().trim()));
			} 
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				String tramaGenerica =  (elemento.elementAt(6).toString().substring(0, 3516)).trim();;
				
				String monto = null;
				this.calendarioPrestamo = new ArrayList();
				for(int j = 0; j< tramaGenerica.length();){
					try{
						
						CalendarioPrestamoImpl calendarioPrestamo = new CalendarioPrestamoImpl();
						calendarioPrestamo.setId(tramaGenerica.substring(j,j+=3));
						
						
						calendarioPrestamo.setFecha(ObjectUtil.tramaToTimestampyyyymmdd(tramaGenerica.substring(j,j+=8)));
					
						monto = ObjectUtil.replaceChar(tramaGenerica.substring(j,j+=9).trim(),',',"");
					
						monto = ObjectUtil.replaceChar(monto,'.',"");					
						calendarioPrestamo.setAmortizacion(ObjectUtil.tramaToBigDecimal(monto));
						
						monto = ObjectUtil.replaceChar(tramaGenerica.substring(j,j+=8).trim(),',',"");
						
						monto = ObjectUtil.replaceChar(monto,'.',"");	
						calendarioPrestamo.setInteres(ObjectUtil.tramaToBigDecimal(monto));
						
						monto = ObjectUtil.replaceChar(tramaGenerica.substring(j,j+=6).trim(),',',"");
						
						monto = ObjectUtil.replaceChar(monto,'.',"");	
						calendarioPrestamo.setSeguro(ObjectUtil.tramaToBigDecimal(monto));
						
						monto = ObjectUtil.replaceChar(tramaGenerica.substring(j,j+=8).trim(),',',"");
						
						monto = ObjectUtil.replaceChar(monto,'.',"");					
						calendarioPrestamo.setCuota(ObjectUtil.tramaToBigDecimal(monto));
						
						monto = ObjectUtil.replaceChar(tramaGenerica.substring(j,j+=9).trim(),',',"");
						
						monto = ObjectUtil.replaceChar(monto,'.',"");					
						calendarioPrestamo.setSaldo(ObjectUtil.tramaToBigDecimal(monto));
					
					
						calendarioPrestamo.setEstado(tramaGenerica.substring(j,j+=3).trim());
						j+=1;
						this.calendarioPrestamo.add((CalendarioPrestamo)calendarioPrestamo);
						
					}
					catch (Exception e) {
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						
					}
					
				}				
			} 			
		}
		//@DPS generarLog(t,usuario,Constante.REFRENDO_CALENDARIO_PAGO);
		//@DPS if(getArrayRuleException()!=null)
		//@DPS  throw getArrayRuleException();
		
	}
	
	
	public void confimarPrestamo(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		int x = 0;
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			
			Vector elemento = (Vector) iter.next();
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
					this.setNroOperacion(elemento.elementAt(6).toString());
					//System.out.println("this.getNroOperacion():"+this.getNroOperacion());
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					this.setNumPrestamo(elemento.elementAt(6).toString());
					this.setNroPrestamo(this.getNumPrestamo());
					//System.out.println("this.getNumPrestamo():"+this.getNumPrestamo());
				}
				
			 }
	
		this.setFechaOperacion(pe.cosapi.common.ObjectUtil.getFechaActual());
		this.setHoraOperacion(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		t.setObjeto(this);
		
	}
	
	
	public void simularPrestamo(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		int x = 0;
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			
			Vector elemento = (Vector) iter.next();
	
			
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
					this.setNroOperacion(elemento.elementAt(6).toString());
				
				}
			
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					this.setMontoPrestamo(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim())));
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					this.setSaldoDeuda(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim())));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					this.setMontoCuota(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim())));
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					this.setMontoCuotaProtegida(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim())));
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					this.setSaldoNeto(ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim())));
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					this.setDiaPago(""+Integer.parseInt(elemento.elementAt(6).toString().trim()));
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					this.setSegDesgravamen(""+ObjectUtil.tramaToPorcentajeDesgravamen(elemento.elementAt(6).toString().trim()).toString().concat(" %"));
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				this.setTasa(ObjectUtil.tramaToPorcentaje(elemento.elementAt(6).toString().trim()).setScale(2,BigDecimal.ROUND_DOWN).toString()	.concat(" %"));
		
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
					this.setTcea(ObjectUtil.tramaToPorcentaje(elemento.elementAt(6).toString().trim()).setScale(2,BigDecimal.ROUND_DOWN).toString()	.concat(" %"));
			
					}
//				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
//					this.setTasa(ObjectUtil.redondearDecimales(ObjectUtil.tramaToPorcentaje(elemento.elementAt(6).toString().trim()).toString(),2).concat(" %"));
//				}
//				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
//					this.setTcea(ObjectUtil.redondearDecimales(ObjectUtil.tramaToPorcentaje(elemento.elementAt(6).toString().trim()).toString(),2).concat(" %"));
//				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
					this.setNroPrestamo(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					this.setFechaVenCuota(elemento.elementAt(6).toString().trim());
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
					this.setFechaVenPrestamo(elemento.elementAt(6).toString().trim());
				}
			 } 
		t.setObjeto(this);
		generarLog(t,usuario,null);
	}
	
	/**
	 * @return Devuelve moneda.
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda El moneda a establecer.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return Devuelve fechaPrestamo.
	 */
	public Timestamp getFechaPrestamo() {
		return fechaPrestamo;
	}
	/**
	 * @param fechaPrestamo El fechaPrestamo a establecer.
	 */
	public void setFechaPrestamo(Timestamp fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	
	/**
	 * @return Devuelve cuenta.
	 */
	public String getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Devuelve nroDocumento.
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}
	/**
	 * @param nroDocumento El nroDocumento a establecer.
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getDiaPago() {
		return diaPago;
	}
	public void setDiaPago(String diaPago) {
		this.diaPago = diaPago;
	}


	public String getMontoPrestamo() {
		return montoPrestamo;
	}
	public void setMontoPrestamo(String montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getTcea() {
		return tcea;
	}
	public void setTcea(String tcea) {
		this.tcea = tcea;
	}
	public String getFechaVenCuota() {
		return fechaVenCuota;
	}
	public void setFechaVenCuota(String fechaVenCuota) {
		this.fechaVenCuota = fechaVenCuota;
	}
	public String getFechaVenPrestamo() {
		return fechaVenPrestamo;
	}
	public void setFechaVenPrestamo(String fechaVenPrestamo) {
		this.fechaVenPrestamo = fechaVenPrestamo;
	}
	public String getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}
	public String getMontoCuotaProtegida() {
		return montoCuotaProtegida;
	}
	public void setMontoCuotaProtegida(String montoCuotaProtegida) {
		this.montoCuotaProtegida = montoCuotaProtegida;
	}
	public String getNroPrestamo() {
		return nroPrestamo;
	}
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}
	public String getSaldoDeuda() {
		return saldoDeuda;
	}
	public void setSaldoDeuda(String saldoDeuda) {
		this.saldoDeuda = saldoDeuda;
	}
	public String getSaldoNeto() {
		return saldoNeto;
	}
	public void setSaldoNeto(String saldoNeto) {
		this.saldoNeto = saldoNeto;
	}
	public String getSegDesgravamen() {
		return segDesgravamen;
	}
	public void setSegDesgravamen(String segDesgravamen) {
		this.segDesgravamen = segDesgravamen;
	}
	public String getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(String montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public String getUrl_condicinesPrestamo() {
		return url_condicinesPrestamo;
	}
	public void setUrl_condicinesPrestamo(String url_condicinesPrestamo) {
		this.url_condicinesPrestamo = url_condicinesPrestamo;
	}
	public String getUrl_creditoPrestamo() {
		return url_creditoPrestamo;
	}
	public void setUrl_creditoPrestamo(String url_creditoPrestamo) {
		this.url_creditoPrestamo = url_creditoPrestamo;
	}
	public String getUrl_polizaPrestamo() {
		return url_polizaPrestamo;
	}
	public void setUrl_polizaPrestamo(String url_polizaPrestamo) {
		this.url_polizaPrestamo = url_polizaPrestamo;
	}
	public String getUrl_solicitudPrestamo() {
		return url_solicitudPrestamo;
	}
	public void setUrl_solicitudPrestamo(String url_solicitudPrestamo) {
		this.url_solicitudPrestamo = url_solicitudPrestamo;
	}
	public String getUrl_tarifarioPrestamo() {
		return url_tarifarioPrestamo;
	}
	public void setUrl_tarifarioPrestamo(String url_tarifarioPrestamo) {
		this.url_tarifarioPrestamo = url_tarifarioPrestamo;
	}
	public String getUrl_tasas() {
		return url_tasas;
	}
	public void setUrl_tasas(String url_tasas) {
		this.url_tasas = url_tasas;
	}
	
	
}

