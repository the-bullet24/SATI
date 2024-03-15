package pe.bn.tcredito.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import pe.bn.tcredito.domain.Pago;
import pe.bn.transferencia.domain.Transferencia;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

public class PagoImpl extends OperacionImplNueva implements Pago, Serializable{

	//in
	private String nroTarjetaCredito;
	private String nroTarjetaDebito;
	private BigDecimal montoPagado;
	//out
	private String nroCuentaAhorros;
	private BigDecimal itf;
	private BigDecimal comision;
	private String nombreCliente;
	private String codigoAutorizacion;
	
	private Cuenta cuentaCargada;
	private Cuenta cuentaCredito;
	private String fecha;
	private String hora;
	private String nroOperacion;
	private String tarjetaFormateada;
	private BigDecimal importeNeto;
	private CuentaImpl datos;
	private String fechaFacturacion;
	
	
	
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public CuentaImpl getDatos() {
		return datos;
	}

	public void setDatos(CuentaImpl datos) {
		this.datos = datos;
	}

	public String getTarjetaFormateada() {
		return tarjetaFormateada;
	}

	public void setTarjetaFormateada(String tarjetaFormateada) {
		this.tarjetaFormateada = tarjetaFormateada;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNroOperacion() {
		return nroOperacion;
	}

	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

	public BigDecimal getImporteNeto() {
		BigDecimal tem = this.montoPagado.add(this.comision);
		tem = tem.add(this.itf);
		String monto = ObjectUtil.replaceChar(tem.toString(),',',"");
	    monto 		 = ObjectUtil.replaceChar(monto,'.',"");
		this.importeNeto = ObjectUtil.tramaToBigDecimal(monto,2);
		return importeNeto;
	}

	public Cuenta getCuentaCargada() {
		return cuentaCargada;
	}

	public void setCuentaCargada(Cuenta cuentaCargada) {
		this.cuentaCargada = cuentaCargada;
	}

	public Cuenta getCuentaCredito() {
		return cuentaCredito;
	}

	public void setCuentaCredito(Cuenta cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getMontoPagado() {
		return ObjectUtil.formatearMonto(montoPagado);
	}

	public void setMontoPagado(BigDecimal montoPagado) {
		this.montoPagado = montoPagado;
	}

	public String getNroTarjetaCredito() {
		return nroTarjetaCredito;
	}

	public void setNroTarjetaCredito(String nroTarjetaCredito) {
		this.nroTarjetaCredito = nroTarjetaCredito;
	}

	public String getNroTarjetaDebito() {
		return nroTarjetaDebito;
	}

	public void setNroTarjetaDebito(String nroTarjetaDebito) {
		this.nroTarjetaDebito = nroTarjetaDebito;
	}

	public String getCodigoAutorizacion() {
		return codigoAutorizacion;
	}

	public void setCodigoAutorizacion(String codigoAutorizacion) {
		this.codigoAutorizacion = codigoAutorizacion;
	}


	public String getNroCuentaAhorros() {
		return nroCuentaAhorros;
	}

	public void setNroCuentaAhorros(String nroCuentaAhorros) {
		this.nroCuentaAhorros = nroCuentaAhorros;
	}

	public String getItf() {
		return ObjectUtil.formatearMonto(itf);
	}
	
	public void setItf(BigDecimal itf) {
		this.itf = itf;
	}
	
	public String getComision() {
		return ObjectUtil.formatearMonto(comision);
	}
	
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	
	public void pagarTCredito(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNroCuentaAhorros(elemento.elementAt(6).toString().trim());				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto 	  = ObjectUtil.replaceChar(monto,'.',"");
				this.itf  = ObjectUtil.tramaToBigDecimal(monto);
				
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				String monto = ObjectUtil.replaceChar(elemento.elementAt(6).toString().trim(),',',"");
				monto 	= ObjectUtil.replaceChar(monto,'.',"");
				this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
				
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.setNombreCliente(elemento.elementAt(6).toString());
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setCodigoAutorizacion(elemento.elementAt(6).toString());
				
			}
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				this.setNroOperacion(elemento.elementAt(6).toString());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setFechaFacturacion(""+ObjectUtil.getYYYYMMDDFormateada(elemento.elementAt(6).toString()));
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setNombreCliente(elemento.elementAt(6).toString().trim());
				
			}
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				this.setCodigoAutorizacion(elemento.elementAt(6).toString().trim());
				
			}
	
		}

		t.setObjeto(this);
	}
}
