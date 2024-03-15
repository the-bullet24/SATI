/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoCuponera;
import pe.bn.pago.domain.PagoUniversidad;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.PagoImpl;
import pe.cosapi.domain.impl.ReciboImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoUniversidadImpl extends PagoImpl implements Serializable, PagoUniversidad{
	
	private String codUniversidad;
	private String descUniversidad;
	private String concepto;
	private String tipoPersona;
	private String codSituacion;
	private String sede;
	private String nombreCompleto;
	
	private String hora;
	private String fecha;
	private Cuenta cuenta;
	
	private String moneda;
	private String importe;
	private String opcionPago;
	private String nroDoc;
	private String tipoDoc;
	private String tipoDocDesc;
	private BigDecimal importePago;
	private String codAlumno;
	private String sedeDesc;
	private String situacionDesc;
	private String conceptoDesc;
	private String itf;
	private String total;
	private String digitoChequeo;
	private String flagImporte;
	private String codTransaccion;
	private String codUsuario;
	private String codAgencia;
	private String administradorTrib;
	private String numeroSecuencia;
	private String digitoControl;
	private String fechaSistema;
					
	
	public String getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public String getAdministradorTrib() {
		return administradorTrib;
	}

	public void setAdministradorTrib(String administradorTrib) {
		this.administradorTrib = administradorTrib;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getCodTransaccion() {
		return codTransaccion;
	}

	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getDigitoControl() {
		return digitoControl;
	}

	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	public String getFlagImporte() {
		return flagImporte;
	}

	public void setFlagImporte(String flagImporte) {
		this.flagImporte = flagImporte;
	}

	public String getDigitoChequeo() {
		return digitoChequeo;
	}

	public void setDigitoChequeo(String digitoChequeo) {
		this.digitoChequeo = digitoChequeo;
	}

	public String getItf() {
		return itf;
	}

	public void setItf(String itf) {
		this.itf = itf;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getConceptoDesc() {
		return conceptoDesc;
	}

	public void setConceptoDesc(String conceptoDesc) {
		this.conceptoDesc = conceptoDesc;
	}

	public String getSedeDesc() {
		return sedeDesc;
	}

	public void setSedeDesc(String sedeDesc) {
		this.sedeDesc = sedeDesc;
	}

	public String getSituacionDesc() {
		return situacionDesc;
	}

	public void setSituacionDesc(String situacionDesc) {
		this.situacionDesc = situacionDesc;
	}

	public String getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public String getTipoDocDesc() {
		return tipoDocDesc;
	}

	public void setTipoDocDesc(String tipoDocDesc) {
		this.tipoDocDesc = tipoDocDesc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getOpcionPago() {
		return opcionPago;
	}

	public void setOpcionPago(String opcionPago) {
		this.opcionPago = opcionPago;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCodSituacion() {
		return codSituacion;
	}

	public void setCodSituacion(String codSituacion) {
		this.codSituacion = codSituacion;
	}

	public String getCodUniversidad() {
		return codUniversidad;
	}

	public void setCodUniversidad(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getDescUniversidad() {
		return descUniversidad;
	}

	public void setDescUniversidad(String descUniversidad) {
		this.descUniversidad = descUniversidad;
	}

	public BigDecimal getImportePago() {
		return importePago;
	}

	public void setImportePago(BigDecimal importePago) {
		this.importePago = importePago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public void getConsultaAlumno(Transaction t, Usuario usuario) throws Exception{
		
		Vector resutado = procesar1(t);
		Vector recibs= new Vector();
		
		List idsl = new ArrayList();
				
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNombreCompleto(element.elementAt(6).toString().trim());
			}
						
		}

		if(getArrayRuleException()!=null) 
			throw getArrayRuleException();
	}
	
public void getConsultaTarifario(Transaction t) throws Exception{
		
		Vector resutado = procesar1(t);
	
				
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				
				this.setImportePago(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
				
				if(Long.parseLong(element.elementAt(6).toString().trim()) == 0){
					this.setFlagImporte(""+Long.parseLong(element.elementAt(6).toString().trim()));
				}

			}
						
		}

		if(getArrayRuleException()!=null) 
			throw getArrayRuleException();
	}

public void getPagarUniversidad(Transaction t, Usuario usuario) throws Exception{
	
	Vector resutado = procesar(t);

			
	for (Iterator iter = resutado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			this.setNroOperacion(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			this.setNombreCompleto(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			this.setSedeDesc(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			this.setSituacionDesc(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			this.setDescUniversidad(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			this.setConceptoDesc(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
			this.setNroDoc(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
			this.setDigitoChequeo(element.elementAt(6).toString().trim());
		}
//		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
//			
//			this.setImportePago(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
//			
//		}
					
	}

	t.setObjeto(this);
//	if(this.getOpcionPago().equals(Constante.UNI_OPCION_PAGO_OTROS)){
//		generarLog(t,usuario,Constante.REFRENDO_PAGO_UNIVERSIDAD_OTROS);
//	}else{
//		
//		generarLog(t,usuario,Constante.REFRENDO_PAGO_UNIVERSIDAD_ALUMNO);	
//	}
		
	
	if(getArrayRuleException()!=null) throw getArrayRuleException();
}

public void getPagarTasaEducativa(Transaction t, Usuario usuario) throws Exception{
	
	Vector resutado = procesar(t);

			
	for (Iterator iter = resutado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			this.setNroOperacion(element.elementAt(6).toString().trim());
//			System.out.println("this.getNroOperacion()"+this.getNroOperacion());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			this.setNroDoc(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			this.setDescUniversidad(element.elementAt(6).toString().trim());
		}
		
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			this.setConceptoDesc(element.elementAt(6).toString().trim());
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
			this.setHora(element.elementAt(6).toString().trim());
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
			this.setFecha(element.elementAt(6).toString().trim());
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
			this.setCodTransaccion(element.elementAt(6).toString().trim());
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
			this.setCodUsuario(element.elementAt(6).toString().trim());
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
			this.setCodAgencia(element.elementAt(6).toString().trim());
		}	
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
			this.setNumeroSecuencia(element.elementAt(6).toString().trim());
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
			this.setDigitoControl(element.elementAt(6).toString().trim());
		}
	
	}

	t.setObjeto(this);
	
	if(getArrayRuleException()!=null) throw getArrayRuleException();
}

	public void pagar(Transaction t, Usuario usuario) throws Exception{
		//String importeSinFormato = ObjectUtil.replaceChar(this.getRecibo().getImporte(),',',"");
//		importeSinFormato = ObjectUtil.replaceChar(importeSinFormato,'.',"");
//		BigDecimal importeX = ObjectUtil.tramaToBigDecimal(importeSinFormato);
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),importeX);
		Vector resutado = procesar(t);
		for (Iterator iter = resutado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setImporte(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.setComision(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setItf(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				setNroOperacion(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setTotal(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setMora(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setComp(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setGasto(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_PAGO_CUPONERA);		
		if(getArrayRuleException()!=null) throw getArrayRuleException();
	}
	
	/**
	 * @return Devuelve fecha.
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return Devuelve hora.
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora El hora a establecer.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
