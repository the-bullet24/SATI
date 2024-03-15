/*
 * Creado el 23/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
package pe.bn.transferencia.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.facade.impl.TransferenciaFacadeImpl;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
public class TransferenciaImpl extends OperacionImplNueva implements Transferencia, Serializable{
	
	private Cuenta 		cuentaOrigen;
	private Cuenta 		cuentaDestino;
	private Afiliacion 	afiliacion;
	private String 		moneda;
	private BigDecimal 	importe;
	private BigDecimal 	importeReal;
	private BigDecimal 	comision;
	private BigDecimal 	itf;
	private BigDecimal 	total;
	private BigDecimal 	saldoAnterior;
	private BigDecimal 	saldoActual;
	private BigDecimal 	importeAbono;
	private BigDecimal 	importeCargoSoles;
	private BigDecimal 	importeCV;
	private BigDecimal 	tipoCambio;
	private String 		nroTransferencia;
	
	private String 		tipoAfectacion;
	private String 		tipoTransferencia;
	private BigDecimal 	comisionIB;
	private BigDecimal 	comisionIBConvertido;
	private BigDecimal 	importeNeto;
	private String 		tipoConsulta;
	private String 		importeTransferido;
	private boolean 	interbancaria;
	private boolean 	interbancariaLinea;
	
	private BigDecimal 	importeCargo;
	private BigDecimal 	importeConvertido;
	/***********/
	private BigDecimal 	comisionAbono;
	private BigDecimal 	itfAbono;
	private BigDecimal 	importeAbonar;
	
	private String 		simboloMonedaImporte;
	private String 		simboloMonedaComision;
	private String 		simboloMonedaItf;
	private String 		simboloMonedaImpNetoCargo;
	private String 		simboloMonedaAbono;
	private String 		simboloMonedaTipoCambio;
	private String		nombreMonedaMail="";
	
	
	private String 		fechaOperacion;
	private String      horaOperacion;
	private String 		fechaProceso;
	
	private String 		beneficiario;
	private String 		codMoneda;
	private String 		cuentaPropia;
	private String 		cuentaPropiaDes;
	private String 		tipoPlaza;
	
	private String		flagRefrendo;
	private String 		motivo;
	private String 		entidadDesc;
	private String 		cciContacto;
	private String 		cciOrigen;
	
	
			
    public String getCciOrigen() {
		return cciOrigen;
	}
	public void setCciOrigen(String cciOrigen) {
		this.cciOrigen = cciOrigen;
	}
	public String getCciContacto() {
		return cciContacto;
	}
	public void setCciContacto(String cciContacto) {
		this.cciContacto = cciContacto;
	}
	public String getEntidadDesc() {
		return entidadDesc;
	}
	public void setEntidadDesc(String entidadDesc) {
		this.entidadDesc = entidadDesc;
	}
	public boolean isInterbancariaLinea() {
		return interbancariaLinea;
	}
	public void setInterbancariaLinea(boolean interbancariaLinea) {
		this.interbancariaLinea = interbancariaLinea;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getFlagRefrendo() {
		return flagRefrendo;
	}
	public void setFlagRefrendo(String flagRefrendo) {
		this.flagRefrendo = flagRefrendo;
	}
	public BigDecimal getComisionIBConvertido() {
		return comisionIBConvertido;
	}
	public void setComisionIBConvertido(BigDecimal comisionIBConvertido) {
		this.comisionIBConvertido = comisionIBConvertido;
	}
	public String getTipoPlaza() {
		return tipoPlaza;
	}
	public void setTipoPlaza(String tipoPlaza) {
		this.tipoPlaza = tipoPlaza;
	}
	public String getCuentaPropiaDes() {
		return cuentaPropiaDes;
	}
	public void setCuentaPropiaDes(String cuentaPropiaDes) {
		this.cuentaPropiaDes = cuentaPropiaDes;
	}
	public String getCuentaPropia() {
		return cuentaPropia;
	}
	public void setCuentaPropia(String cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getSimboloMonedaAbono() {
        return simboloMonedaAbono;
    }
    public void setSimboloMonedaAbono(String simboloMonedaAbono) {
        this.simboloMonedaAbono = simboloMonedaAbono;
    }
    public String getSimboloMonedaComision() {
        return simboloMonedaComision;
    }
    public void setSimboloMonedaComision(String simboloMonedaComision) {
        this.simboloMonedaComision = simboloMonedaComision;
    }
    public String getSimboloMonedaImpNetoCargo() {
        return simboloMonedaImpNetoCargo;
    }
    public void setSimboloMonedaImpNetoCargo(String simboloMonedaImpNetoCargo) {
        this.simboloMonedaImpNetoCargo = simboloMonedaImpNetoCargo;
    }
    public String getSimboloMonedaImporte() {
        return simboloMonedaImporte;
    }
    public void setSimboloMonedaImporte(String simboloMonedaImporte) {
        this.simboloMonedaImporte = simboloMonedaImporte;
    }
    public String getSimboloMonedaItf() {
        return simboloMonedaItf;
    }
    public void setSimboloMonedaItf(String simboloMonedaItf) {
        this.simboloMonedaItf = simboloMonedaItf;
    }
    
    public String getSimboloMonedaTipoCambio() {
        return simboloMonedaTipoCambio;
    }
    public void setSimboloMonedaTipoCambio(String simboloMonedaTipoCambio) {
        this.simboloMonedaTipoCambio = simboloMonedaTipoCambio;
    }
    
	public TransferenciaImpl() {
		
	}
	
	public TransferenciaImpl(Cuenta cuenta, Afiliacion afiliacion, String monedaTransferir ,BigDecimal montoTransferir) {
		this.cuentaOrigen 	= cuenta;
		this.afiliacion 	= afiliacion;
		this.importe 		= montoTransferir;
		CuentaImpl destino 	= new CuentaImpl();
		this.moneda 		= monedaTransferir;
		destino.setTipoProducto(afiliacion.getCodigoServicio().trim());
		destino.setNumeroProducto(afiliacion.getNumeroServicio().trim());
		this.cuentaDestino 	= destino;
		
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_SOL;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_SOL;
		}
		
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		}
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_DOLAR;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_DOLAR;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		}
		
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_SOL;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_SOL;
		}
		if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_DOLAR;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_MN)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_SOL;
		    this.simboloMonedaTipoCambio	= Constante.SIMBOLO_MONEDA_DOLAR;
		}if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) && afiliacion.getCodigoServicio().trim().equals(Constante.COD_CUENTA_AHORROS_ME)){
		    this.simboloMonedaImporte		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaComision		= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaItf			= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaImpNetoCargo 	= Constante.SIMBOLO_MONEDA_DOLAR;
		    this.simboloMonedaAbono			= Constante.SIMBOLO_MONEDA_DOLAR;
		}
		
	}
	
	/**
	 * Metodo encargado de extraer los valores resultado de hacer la consulta de Comision en una 
	 * Transferencia Bancaria.
	 * @param t 		: objeto Transaccion
	 * @param usuario 	: objeto Usuario.
	 */
	public void getConsultaComisionBancaria(Transaction t,Usuario usuario) throws Exception{
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			
			//Cuenta de Cargo
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				//if(Constante.VER_LOG) System.out.println("Cuenta de Cargo:"+element.elementAt(0).toString());
			}
			
			//Importe de Cargo
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    //if(Constante.VER_LOG) System.out.println("Importe de Cargo:"+monto);
			    this.importeCargo = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			//Importe de Comision
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
			    //if(Constante.VER_LOG) System.out.println("Importe de Comision:"+monto);
			}
			
			//Importe de ITF
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
			    //if(Constante.VER_LOG) System.out.println("Importe de ITF:"+monto);
			}
			
			//Importe Convertido
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.importeConvertido = ObjectUtil.tramaToBigDecimal(monto,2);
			    //if(Constante.VER_LOG) System.out.println("Importe Convertido:"+monto);
			}
			
			//Tipo de Cambio
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto 		 = ObjectUtil.replaceChar(monto,'.',"");
			    this.tipoCambio = ObjectUtil.tramaToBigDecimal(monto,7);
			    this.tipoCambio = this.tipoCambio.setScale(4, BigDecimal.ROUND_DOWN);
			    //if(Constante.VER_LOG) System.out.println("Tipo de Cambio:"+monto);
			}
			
			if ((this.importeCargo != null) && (this.comision != null) && (this.itf != null)){
				//if(Constante.VER_LOG) System.out.println("this.importeCargo:"+this.importeCargo+"|this.comision:"+this.comision+"|this.itf:"+this.itf);
				BigDecimal tem = this.importeCargo.add(this.comision);
				tem = tem.add(this.itf);
				String monto = ObjectUtil.replaceChar(tem.toString(),',',"");
			    monto 		 = ObjectUtil.replaceChar(monto,'.',"");
				this.importeNeto = ObjectUtil.tramaToBigDecimal(monto,2);
				//if(Constante.VER_LOG) System.out.println("Importe Neto:"+this.importeNeto);
			}
		}
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		
	}
	
	
	
	/**
	 * @return Devuelve comisionAbono.
	 */
	public BigDecimal getComisionAbono() {
		return comisionAbono;
	}
	/**
	 * @param comisionAbono El comisionAbono a establecer.
	 */
	public void setComisionAbono(BigDecimal comisionAbono) {
		this.comisionAbono = comisionAbono;
	}
	/**
	 * @return Devuelve importeAbonar.
	 */
	public BigDecimal getImporteAbonar() {
		return importeAbonar;
	}
	/**
	 * @param importeAbonar El importeAbonar a establecer.
	 */
	public void setImporteAbonar(BigDecimal importeAbonar) {
		this.importeAbonar = importeAbonar;
	}
	/**
	 * @return Devuelve itfAbono.
	 */
	public BigDecimal getItfAbono() {
		return itfAbono;
	}
	/**
	 * @param itfAbono El itfAbono a establecer.
	 */
	public void setItfAbono(BigDecimal itfAbono) {
		this.itfAbono = itfAbono;
	}
	/**
	 * @return Devuelve importeCargo.
	 */
	public BigDecimal getImporteCargo() {
		return importeCargo;
	}
	/**
	 * @param importeCargo El importeCargo a establecer.
	 */
	public void setImporteCargo(BigDecimal importeCargo) {
		this.importeCargo = importeCargo;
	}
	/**
	 * @return Devuelve importeConvertido.
	 */
	public BigDecimal getImporteConvertido() {
		return importeConvertido;
	}
	/**
	 * @param importeConvertido El importeConvertido a establecer.
	 */
	public void setImporteConvertido(BigDecimal importeConvertido) {
		this.importeConvertido = importeConvertido;
	}
    public boolean isInterbancaria() {
        return interbancaria;
    }
    public void setInterbancaria(boolean interbancaria) {
        this.interbancaria = interbancaria;
    }
    public String getImporteTransferido() {
        return importeTransferido;
    }
    public void setImporteTransferido(String importeTransferido) {
        this.importeTransferido = importeTransferido;
    }
    public BigDecimal getImporteNeto() {
        return importeNeto;
    }
    public void setImporteNeto(BigDecimal importeNeto) {
        this.importeNeto = importeNeto;
    }
    public String getTipoConsulta() {
        return tipoConsulta;
    }
    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    public BigDecimal getComisionIB() {
        return comisionIB;
    }
    public void setComisionIB(BigDecimal comisionIB) {
        this.comisionIB = comisionIB;
    }
    
    public String getTipoTransferencia() {
        return tipoTransferencia;
    }
    public void setTipoTransferencia(String tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }
	/**
	 * @return Devuelve afiliacion.
	 */
	public Afiliacion getAfiliacion() {
		return afiliacion;
	}
	/**
	 * @param afiliacion El afiliacion a establecer.
	 */
	public void setAfiliacion(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
	}
	/**
	 * @return Devuelve comision.
	 */
	public String getComision() {
		return ObjectUtil.formatearMonto(comision);
	}
	/**
	 * @param comision El comision a establecer.
	 */
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	/**
	 * @return Devuelve cuentaOrigen.
	 */
	public Cuenta getCuenta() {
		return cuentaOrigen;
	}
	/**
	 * @param cuentaOrigen El cuentaOrigen a establecer.
	 */
	public void setCuentaOrigen(Cuenta cuenta) {
		this.cuentaOrigen = cuenta;
	}
	/**
	 * @return Devuelve importe.
	 */
	public String getImporte() {
		return ObjectUtil.formatearMonto(importe);
	}
	/**
	 * @param importe El importe a establecer.
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @return Devuelve itf.
	 */
	public String getItf() {
		return ObjectUtil.formatearMonto(itf);
	}
	/**
	 * @param itf El itf a establecer.
	 */
	public void setItf(BigDecimal itf) {
		this.itf = itf;
	}
	/**
	 * @return Devuelve total.
	 */
	public String getTotal() {
		return ObjectUtil.formatearMonto(total);
	}
	/**
	 * @param total El total a establecer.
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
    public String getTipoAfectacion() {
        return tipoAfectacion;
    }
    
    public void setTipoAfectacion(String tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }
    
	public void pagarTransferenciaMismoBanco(Transaction t, Usuario usuario) throws Exception{
		//@DPS Transaction t = new Transaction(transaccion);
	    //@DPS t.setValues(datos);
	    //@DPS t.setCuentas(cuentas);		
		Vector resultado = procesar(t);
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				 String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				 monto 	= ObjectUtil.replaceChar(monto,'.',"");
				 this.importeCargo = ObjectUtil.tramaToBigDecimal(monto,2);
				 //if(Constante.VER_LOG) System.out.println("importe de cargo:"+this.importeCargo);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				monto 	= ObjectUtil.replaceChar(monto,'.',"");
				this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
				//if(Constante.VER_LOG) System.out.println("comision:"+this.comision);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				monto 	  = ObjectUtil.replaceChar(monto,'.',"");
				this.itf  = ObjectUtil.tramaToBigDecimal(monto);
				//if(Constante.VER_LOG) System.out.println("itf:"+this.itf);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				monto 	  			= ObjectUtil.replaceChar(monto,'.',"");
				this.importeNeto  	= ObjectUtil.tramaToBigDecimal(monto);
				//if(Constante.VER_LOG) System.out.println("importe Neto:"+this.importeNeto);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setNroTransferencia(element.elementAt(6).toString().trim());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				monto 	  			= ObjectUtil.replaceChar(monto,'.',"");
				this.importeAbono  	= ObjectUtil.tramaToBigDecimal(monto);
				//if(Constante.VER_LOG) System.out.println("importe Abono:"+this.importeAbono);
			}
			
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				monto 	  			= ObjectUtil.replaceChar(monto,'.',"");
				this.importeCV  	= ObjectUtil.tramaToBigDecimal(monto);
				//if(Constante.VER_LOG) System.out.println("importe C/V:"+this.importeCV);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				monto 	  		= ObjectUtil.replaceChar(monto,'.',"");
				this.tipoCambio = ObjectUtil.tramaToBigDecimal(monto,7);
				this.tipoCambio = this.tipoCambio.setScale(4, BigDecimal.ROUND_DOWN);
				//if(Constante.VER_LOG) System.out.println("tipo cambio:"+this.tipoCambio);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
				this.setNroOperacion(element.elementAt(6).toString().trim());
				//if(Constante.VER_LOG) System.out.println("NroOperacion:"+this.getNroOperacion());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_35)){
				this.setFechaOperacion(element.elementAt(6).toString().trim());

			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_36)){
				this.setHoraOperacion(element.elementAt(6).toString().trim());
				
			}
			/**
			 * Autor: GU
			 * Fecha: 17/10/2011
			 * Asunto: Se desentimo cambio para incluir Fecha de Proceso. 
			 */
//			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_37)){
//				this.setFechaProceso(element.elementAt(6).toString().trim());			
//
//			}
			/**
			 * Fin
			 */
				
			
			/*
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				int j = 0;
				String monto = element.elementAt(6).toString().trim().substring(j,j+=15);
				String signo = element.elementAt(6).toString().trim().substring(j);
				this.setSaldoAnterior(ObjectUtil.tramaToBigDecimal(monto,2,signo));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				int j = 0;
				String monto = element.elementAt(6).toString().trim().substring(j,j+=15);
				String signo = element.elementAt(6).toString().trim().substring(j);
				this.setSaldoActual(ObjectUtil.tramaToBigDecimal(monto,2,signo));				
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				this.setNroTransferencia(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				this.setImporteAbono(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				if(!getEsMismaMoneda()){
					this.setImporteCargoSoles(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
				}
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				if(getEsMismaMoneda()&&!(this.getCuentaOrigen().getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)&&this.getCuentaDestino().getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME))){
				    //este marikon es el monto de AmountTra.
					this.setImporteCargoSoles(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
				}
				else{
					this.setImporteCV(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));	
				}
				
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				this.setTipoCambio(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim(),7));
			}			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
				this.setNroOperacion(element.elementAt(6).toString().trim());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				this.setImporteCargoSoles(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}			
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_35)){
				this.setImporteNeto(ObjectUtil.tramaToBigDecimal(element.elementAt(6).toString().trim()));
			}*/
			
		}
		
		
		
		t.setObjeto(this);
		//@DPS generarLog(t,usuario,Constante.REFRENDO_TRANSFERENCIA_BANCARIA);
		//@DPS if(getArrayRuleException()!=null)
		//@DPS	throw getArrayRuleException();
	}
	
	public String getTitularCuenta(String transaccion,Usuario usuario ,Vector datos, Vector cuentas) throws Exception{
		Transaction t = new Transaction(transaccion);
		t.setValues(datos);
		t.setCuentas(cuentas);		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				return element.elementAt(6).toString().trim();
			}
		}
		
		/*
		if(Constante.VER_LOG) log.warn("monto....."+this.total);
		FacadeFactory.getGeneralFacade().validarLimiteImporte(this.getCuenta(),this.total);
		*/
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		else
			return null;  
	}
	
	public String getCuentaUob(String transaccion,Vector datos, Vector cuentas) throws Exception{
		Transaction t = new Transaction(transaccion);
		t.setValues(datos);
		t.setCuentas(cuentas);		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				
				return element.elementAt(6).toString().trim();
			}
		}
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		else
			return null;  
	}
	
	/*** Metodo de Ejecucion de una Transferencia Inter bancaria Mismo Titular ***/
	public void getTitularCuentaIB(Transaction t,Usuario usuario) throws Exception{
		
		Vector resultado = procesar(t);
		
		this.setComisionAbono(new BigDecimal(0)); 
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			//6430 - 6431
			if (this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)
			    || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)
			    || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)
			    || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			    
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");				
					this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");				
				    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.comisionIB = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.comisionAbono = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
			}else{
			    if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");				
					this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");				
				    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.comisionIB = ObjectUtil.tramaToBigDecimal(monto,2);
				}
				
				if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
				    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
				    monto = ObjectUtil.replaceChar(monto,'.',"");
				    this.comisionAbono = ObjectUtil.tramaToBigDecimal(monto,2);
				}
			}
			
		}
		
		/*t.setObjeto(this);
		generarLog(t,usuario,null);*/		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		
	}
	
	
	
	public void getOtroTitularCuentaIB(Transaction t,Usuario usuario) throws Exception{
		
		Vector resultado = procesar(t);
		
		this.setComisionAbono(new BigDecimal(0));
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			//cuenta cargo
			
			//Importe de cargo soles
			//Comision Soles
			//ITF Soles
			//Importe neto Soles
			//Comision IB Dolares
			    
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");				
				this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");				
			    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.comisionIB = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.comisionAbono = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			
		}
		/*t.setObjeto(this);
		generarLog(t,usuario,null);*/		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		
	}
	
	public void getConsultaTransferenciaIBLinea(Transaction t,Usuario usuario) throws Exception{
		
		Afiliacion afil = new AfiliacionImpl();
		String nombre ="";
		String appaterno ="";
		String apmaterno ="";
		Vector resultado = procesar1(t);
		
		this.setComisionAbono(new BigDecimal(0));
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			   
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				System.out.println("importe:"+element.elementAt(6).toString().trim());
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");				
				this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				
				System.out.println("comision:"+element.elementAt(6).toString().trim());
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");				
			    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
				System.out.println("itf:"+element.elementAt(6).toString().trim());
				String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
			    System.out.println("this.getTotal()"+this.getTotal());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			 
			    this.tipoPlaza = ObjectUtil.tramaToString(element.elementAt(6).toString().trim());
			    System.out.println("this.getTipoPlaza()"+this.getTipoPlaza());
			}
			
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.comisionIB = ObjectUtil.tramaToBigDecimal(monto,2);
			    System.out.println( "this.comisionIB:"+this.getComisionIB());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.comisionAbono = ObjectUtil.tramaToBigDecimal(monto,2);
			    System.out.println( "this.comisionBN:"+this.getComisionAbono());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
			    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
			    //System.out.println("monto>>:");
			    monto = ObjectUtil.replaceChar(monto,'.',"");
			    this.comisionIBConvertido = ObjectUtil.tramaToBigDecimal(monto,2);
			    System.out.println( "this.comisionIBConvertido:"+this.getComisionIBConvertido());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				String cuentapropia = element.elementAt(6).toString().trim();
				this.cuentaPropia=cuentapropia;
				System.out.println("cuentapropia:"+cuentapropia);	
			    			    			    
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
							
				afil.setNroDocumentoBenef(element.elementAt(6).toString().trim());
				System.out.println("afil.getNroDocumentoBenef:"+afil.getNroDocumentoBenef());
			   
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				   
			
				//afil.setTipoDocumentoBenef(element.elementAt(6).toString().trim());
				afil.setTipoDocumentoBenef("00"+Long.parseLong(element.elementAt(6).toString().trim()));
				System.out.println("afil.setTipoDocumentoBenef:"+afil.getTipoDocumentoBenef());
			 			
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
							
				afil.setIdentificadorCCE1(element.elementAt(6).toString().trim());		
				System.out.println("afil.getIdentificadorCCE1():"+afil.getIdentificadorCCE1());
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_16)){
							
				afil.setIdentificadorCCE2(element.elementAt(6).toString().trim());
				System.out.println("afil.getIdentificadorCCE1():"+afil.getIdentificadorCCE2());		
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
					
				afil.setCciOrigen(element.elementAt(6).toString().trim());
				System.out.println("afil.getCciOrigen():"+afil.getCciOrigen());		
			 			
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
				   
				appaterno = element.elementAt(6).toString().trim();
//			    System.out.println("appaterno:"+appaterno);
			 
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_19)){
				   
				apmaterno = element.elementAt(6).toString().trim();
//			    System.out.println("apmaterno:"+apmaterno);
			 
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_20)){
				   
				nombre = element.elementAt(6).toString().trim();
//			    System.out.println("nombre:"+nombre);
			 
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_21)){
				   
				this.cciContacto = element.elementAt(6).toString().trim();
//			    System.out.println("nombre:"+nombre);
			 
			}
			
		}
		   this.beneficiario = appaterno+" "+apmaterno+" "+nombre;
//		   System.out.println("this.beneficiario:"+this.beneficiario);
		   this.afiliacion = afil;
		  //  System.out.println( "this.afiliacion.getNroDocumentoBenef():"+this.afiliacion.getNroDocumentoBenef());
		   // System.out.println( "this.afiliacion.setTipoDocumentoBenef():"+this.afiliacion.getTipoDocumentoBenef());
		  
		/*t.setObjeto(this);
		generarLog(t,usuario,null);*/		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		
	}
	
	public void  transferirInterBanco (Transaction t,Usuario usuario) throws Exception{
	    
	    Vector resultado = procesar(t);
	    
	    for (Iterator iter = resultado.iterator(); iter.hasNext();) {//cuando es mismo titular
			Vector element = (Vector) iter.next();
			if (this.afiliacion.getCuentaPropia().equals("S")){
			    if (this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)
			        || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)
			        || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)
			        || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){//mismo titular
			        //comision
			        if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
					}
			        
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//itf
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//Importe Neto a Pagar
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");
					    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//numero transferencia ok
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					    this.nroTransferencia = element.elementAt(6).toString().trim();
					}
					//numero de operacion
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					    this.setNroOperacion(element.elementAt(6).toString().trim());
					}
					
			  }else{
			    //mismo titular
			        //comision
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//Importe Neto a Pagar
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");
					    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//numero transferencia ok
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					    this.nroTransferencia = element.elementAt(6).toString().trim();
					}
					//numero de operacion
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
					    this.setNroOperacion(element.elementAt(6).toString().trim());
					}
			}
			
			}else if (this.afiliacion.getCuentaPropia().equals("N")){//cuando es otro titular
			    
			    if (this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME) || 
			        this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) || 
			        this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) 
			        || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) ){//otro titular
			        //comision
			        if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
					}
			        
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//itf
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//Importe Neto a Pagar
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");
					    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//numero transferencia ok
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					    this.nroTransferencia = element.elementAt(6).toString().trim();
					}
					//numero de operacion
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					    this.setNroOperacion(element.elementAt(6).toString().trim());
					}
			  }else{
			        //comision
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//itf
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//Importe Neto a Pagar
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");
					    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//numero transferencia ok
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					    this.nroTransferencia = element.elementAt(6).toString().trim();
					}
					//numero de operacion
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					    this.setNroOperacion(element.elementAt(6).toString().trim());
					}
			 }
		}
	 }//FIN FOR
	    t.setObjeto(this);
	    //@DPS generarLog(t,usuario,Constante.REFRENDO_TRANSFERENCIA_INTERBANCARIA);		
		//@DPS if(getArrayRuleException()!=null)
		//@DPS	throw getArrayRuleException();	
	}
	
	
public void  transferirInterBancoLinea (Transaction t,Usuario usuario) throws Exception{
	   
	    Vector resultado = procesar(t);
	    
	    for (Iterator iter = resultado.iterator(); iter.hasNext();) {//cuando es mismo titular
			Vector element = (Vector) iter.next();
			//if (this.afiliacion.getCuentaPropia().equals("S")){
//			System.out.println("element.elementAt(0): "+element.elementAt(0));
//			System.out.println("element.elementAt(6): "+element.elementAt(6));
			    if ( this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)
			        || this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){//mismo titular
			        //comision
			        if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
					}
			        
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//itf
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");				
					    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//Importe Neto a Pagar
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
					    monto = ObjectUtil.replaceChar(monto,'.',"");
					    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
					}
					//numero transferencia ok
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					    this.nroTransferencia = element.elementAt(6).toString().trim();
					}
					//numero de operacion
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					    this.setNroOperacion(element.elementAt(6).toString().trim());
					}
					
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
			 			
					    this.beneficiario = element.elementAt(6).toString().trim();
					}
					
					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
			 			
					    this.flagRefrendo = element.elementAt(6).toString().trim();
					}
			  }
			
			//}else if (this.afiliacion.getCuentaPropia().equals("N")){//cuando es otro titular
			    
				//System.out.println("cuando es otro titular");
				
//			    if (
//			        this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) || 
//			        this.cuentaOrigen.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME) ){//otro titular
//			       
//			    	
//			    		
//			        if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
//					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
//					    monto = ObjectUtil.replaceChar(monto,'.',"");				
//					    this.importe = ObjectUtil.tramaToBigDecimal(monto,2);
//					}
//			        
//					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
//					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
//					    monto = ObjectUtil.replaceChar(monto,'.',"");				
//					    this.comision = ObjectUtil.tramaToBigDecimal(monto,2);
//					}
//					//itf
//					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
//					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
//					    monto = ObjectUtil.replaceChar(monto,'.',"");				
//					    this.itf = ObjectUtil.tramaToBigDecimal(monto,2);
//					}
//					//Importe Neto a Pagar
//					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
//					    String monto = ObjectUtil.replaceChar(element.elementAt(6).toString().trim(),',',"");
//					    monto = ObjectUtil.replaceChar(monto,'.',"");
//					    this.total = ObjectUtil.tramaToBigDecimal(monto,2);
//					}
//					//numero transferencia ok
//					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
//					    this.nroTransferencia = element.elementAt(6).toString().trim();
//					}
//					//numero de operacion
//					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
//					    this.setNroOperacion(element.elementAt(6).toString().trim());
//					}
//					
//					if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
//						 			
//						    this.beneficiario = element.elementAt(6).toString().trim();
//						}
//					
//			  }
	//	}
	 }//FIN FOR
	    t.setObjeto(this);
	   
	}
	
	
	/**
	 * @return Devuelve cuentaDestino.
	 */
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	/**
	 * @param cuentaDestino El cuentaDestino a establecer.
	 */
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	/**
	 * @return Devuelve cuentaOrigen.
	 */
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	
	public void calcularMonto(TipoCambio tipoCambio){
		BigDecimal importe = this.importe.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.importeReal = importe;
		if(cuentaOrigen.getMonedaProducto().equals(Constante.MONEDA_SOL)){
			if(cuentaDestino.getMonedaProducto().equals(Constante.MONEDA_SOL)){
				if(this.moneda.equals(Constante.MONEDA_SOL)){
					//igual
				}
				else if(this.moneda.equals(Constante.MONEDA_DOLAR)){
					//no aplica
				}
			}
			else if(cuentaDestino.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
				if(this.moneda.equals(Constante.MONEDA_SOL)){
					//this.importeReal = importe.divide(tipoCambio.getVenta(),2,BigDecimal.ROUND_CEILING);
				}
				else if(this.moneda.equals(Constante.MONEDA_DOLAR)){
					this.importeReal = importe.multiply(tipoCambio.getVenta()).setScale(2,BigDecimal.ROUND_HALF_UP);
				}				
			}
		}
		else if(cuentaOrigen.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			if(cuentaDestino.getMonedaProducto().equals(Constante.MONEDA_SOL)){
				if(this.moneda.equals(Constante.MONEDA_SOL)){
					//this.importeReal = importe.divide(tipoCambio.getCompra(),2,BigDecimal.ROUND_DOWN);
					this.importeReal = this.importeCargo; 
				}
				else if(this.moneda.equals(Constante.MONEDA_DOLAR)){
					//this.importeReal = importe.multiply(tipoCambio.getCompra()).setScale(2,BigDecimal.ROUND_CEILING);
				}
			}
			else if(cuentaDestino.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
				if(this.moneda.equals(Constante.MONEDA_SOL)){
					//no aplica
				}
				else if(this.moneda.equals(Constante.MONEDA_DOLAR)){
					//igual
				}				
			}			
		}		
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
	
	public String getNombreMoneda(){
		if(moneda == null)
			return null;			
		else if(moneda.equals(Constante.MONEDA_DOLAR)){
			nombreMonedaMail=Constante.NOMBRE_MONEDA_DOLAR_MAIL;
			this.simboloMonedaImporte = Constante.SIMBOLO_MONEDA_DOLAR;
			return Constante.NOMBRE_MONEDA_DOLAR;
		}					
		else if (moneda.equals(Constante.MONEDA_SOL)){
			nombreMonedaMail=Constante.NOMBRE_MONEDA_SOL;
			this.simboloMonedaImporte = Constante.SIMBOLO_MONEDA_SOL;
			return Constante.NOMBRE_MONEDA_SOL;
		}			
		return moneda;
	}
	public String getNombreMonedaMail(){
		if(moneda == null)
			return null;			
		else if(moneda.equals(Constante.MONEDA_DOLAR)){
			nombreMonedaMail=Constante.NOMBRE_MONEDA_DOLAR_MAIL;
			return Constante.NOMBRE_MONEDA_DOLAR_MAIL;
		}					
		else if (moneda.equals(Constante.MONEDA_SOL)){
			nombreMonedaMail=Constante.NOMBRE_MONEDA_SOL;
			return Constante.NOMBRE_MONEDA_SOL;
		}			
		return nombreMonedaMail;
	}
	
	public String getSimboloMoneda(){
		if(moneda == null)
			return null;			
		else if(moneda.equals(Constante.MONEDA_DOLAR))
			return Constante.SIMBOLO_MONEDA_DOLAR;		
		else if (moneda.equals(Constante.MONEDA_SOL))
			return Constante.SIMBOLO_MONEDA_SOL;
		return moneda;
	}
	
	/**
	 * @return Devuelve importeReal.
	 */
	public BigDecimal getImporteReal() {
		return importeReal;
	}
	/**
	 * @param importeReal El importeReal a establecer.
	 */
	public void setImporteReal(BigDecimal importeReal) {
		this.importeReal = importeReal;
	}
	/**
	 * @return Devuelve importeAbono.
	 */
	public BigDecimal getImporteAbono() {
		return importeAbono;
	}
	/**
	 * @param importeAbono El importeAbono a establecer.
	 */
	public void setImporteAbono(BigDecimal importeAbono) {
		this.importeAbono = importeAbono;
	}
	/**
	 * @return Devuelve importeCargoSoles.
	 */
	public String getImporteCargoSoles() {
		return ObjectUtil.formatearMonto(importeCargoSoles);
	}
	/**
	 * @param importeCargoSoles El importeCargoSoles a establecer.
	 */
	public void setImporteCargoSoles(BigDecimal importeCargoSoles) {
		this.importeCargoSoles = importeCargoSoles;
	}
	/**
	 * @return Devuelve importeCV.
	 */
	public String getImporteCV() {
		return ObjectUtil.formatearMonto(importeCV);
	}
	/**
	 * @param importeCV El importeCV a establecer.
	 */
	public void setImporteCV(BigDecimal importeCV) {
		this.importeCV = importeCV;
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
	 * @return Devuelve saldoAnterior.
	 */
	public String getSaldoAnterior() {
		return ObjectUtil.formatearMonto(saldoAnterior);
	}
	/**
	 * @param saldoAnterior El saldoAnterior a establecer.
	 */
	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	/**
	 * @return Devuelve tipoCambio.
	 */
	public String getTipoCambio() {
		return String.valueOf(tipoCambio);
	}
	/**
	 * @param tipoCambio El tipoCambio a establecer.
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	/**
	 * @return Devuelve nroTransferencia.
	 */
	public String getNroTransferencia() {
		return nroTransferencia;
	}
	/**
	 * @param nroTransferencia El nroTransferencia a establecer.
	 */
	public void setNroTransferencia(String nroTransferencia) {
		this.nroTransferencia = nroTransferencia;
	}
	
	public boolean getEsMismaMoneda(){
		if(this.cuentaDestino.getMonedaProducto().equals(this.cuentaOrigen.getMonedaProducto()))
			return true;
		return false;
	}
	/**
	 * @param nombreMonedaMail El nombreMonedaMail a establecer.
	 */
	public void setNombreMonedaMail(String nombreMonedaMail) {
		this.nombreMonedaMail = nombreMonedaMail;
	}
	/**
	 * @return Returns the fechaOperacion.
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	/**
	 * @param fechaOperacion The fechaOperacion to set.
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the horaOperacion.
	 */
	public String getHoraOperacion() {
		return horaOperacion;
	}
	/**
	 * @param horaOperacion The horaOperacion to set.
	 */
	public void setHoraOperacion(String horaOperacion) {
		this.horaOperacion = horaOperacion;
	}
}