package pe.bn.consulta.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import pe.bn.consulta.domain.Monto;
import pe.bn.tcredito.domain.Pago;
import pe.bn.transferencia.domain.Transferencia;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.OperacionImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

public class MontoImpl extends OperacionImpl implements Monto, Serializable{

	
	private String montoMaximo;
	private String tipoCanal;
	private String nroCuenta;
	private String tipoElemento;
	private String nroTarjeta;
	private String tipoCliente;
	private String codigoElemento;
	private CuentaImpl cuenta;
	private String fecha;
	private String hora;
	private String desMontoMaximo;
	
	
	public String getDesMontoMaximo() {
		return desMontoMaximo;
	}

	public void setDesMontoMaximo(String desMontoMaximo) {
		this.desMontoMaximo = desMontoMaximo;
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

	public String getCodigoElemento() {
		return codigoElemento;
	}

	public void setCodigoElemento(String codigoElemento) {
		this.codigoElemento = codigoElemento;
	}

	public CuentaImpl getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaImpl cuenta) {
		this.cuenta = cuenta;
	}

	public String getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(String montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}


	public String getTipoCanal() {
		return tipoCanal;
	}

	public void setTipoCanal(String tipoCanal) {
		this.tipoCanal = tipoCanal;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(String tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

	public void guardarMontoMaximo(Transaction t,Vector datos, Vector cuentas,Usuario usuario) throws Exception{
		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNroOperacion(element.elementAt(6).toString());
			 }

		}
		t.setObjeto(this);
			
		generarLog(t,usuario,Constante.REFRENDO_SELECCION_MONTO);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}


}
