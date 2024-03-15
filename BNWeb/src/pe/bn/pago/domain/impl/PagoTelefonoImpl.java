/*
 * Creado el 23/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.PagoTelefono;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Recibo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.ReciboImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.sarabank.bean.Transaction;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoTelefonoImpl extends OperacionImpl implements PagoTelefono, Serializable{
	private static LoggerSati log3 = LoggerSati.getInstance(PagoTelefonoImpl.class.getName());
	
	private Cuenta cuenta;
	private Afiliacion afiliacion;
	private String descripcionLocalidad;
	private BigDecimal comision;
	private BigDecimal itf;
	private BigDecimal total;
	
	private List recibos;
	private String numeroProducto;//codigo de cuenta.
	
	private Timestamp fechaRec;
	private String concepto;
	private String secuencia;
	private String numRecibo;
	private String moneda;
	private String glosa;
	private String codMoneda;
	private BigDecimal importe;
	private BigDecimal importeDol;
	private BigDecimal tipoCambio;
	private BigDecimal importeSol;
	private String simboloMoneda;
	
	private String nomAbonado;
	private String codServicio;
	private String codEntidad;
	private String codServEnt;
	private String codSeccion;
	private String mensaje;
	private String ctaFormateada;
	private String recibo;
	private String fecEmisionRecibo;
	private String montoFormat;
	private String empresa;
	private String servicio;
	
	private String nroSerie;
	private String nroCorrelativo;
	private String nroTrace;
	private String nroDiasVigencia;
	private String codAutoriz;
	private BigDecimal importeSinImp;
	private BigDecimal impuesto;	
	private String codMonedaDes;
	private String importeMinimo;
	private String documento;
	private String ruc;
	
	
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getImporteMinimo() {
		return importeMinimo;
	}

	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	public String getCodMonedaDes() {
		return codMonedaDes;
	}

	public void setCodMonedaDes(String codMonedaDes) {
		this.codMonedaDes = codMonedaDes;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * 
	 */
	public PagoTelefonoImpl() {

	
	}
	
	/**
	 * 
	 */
	//public PagoTelefonoImpl(Cuenta cuenta, Afiliacion afiliacion, String moneda, BigDecimal monto) {
	public PagoTelefonoImpl(Usuario usuario,Cuenta cuenta, Afiliacion afiliacion, String reciboNro, Timestamp fechaEmi, BigDecimal monto, String monedaCod, String abrevMoneda, String codSec) {
		this.cuenta = cuenta;
		this.afiliacion = afiliacion;
		this.importe = monto;
		this.comision = new BigDecimal(0);
		this.itf = new BigDecimal(0);
		this.total = new BigDecimal(0);
		this.numRecibo = reciboNro;
		this.fechaRec = fechaEmi;
		this.importe = monto;
		this.codSeccion = codSec;
	
		this.codMoneda = monedaCod;
		this.simboloMoneda = abrevMoneda; 

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
	public BigDecimal getComision() {
		return comision;
	}
	/**
	 * @param comision El comision a establecer.
	 */
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	/**
	 * @return Devuelve cuenta.
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}
	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @return Devuelve importe.
	 */
	public BigDecimal getImporte() {
		return importe;
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
	public BigDecimal getItf() {
		return itf;
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
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * @param total El total a establecer.
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * @return Devuelve recibos.
	 */
	public List getRecibos() {
		return recibos;
	}
	/**
	 * @param recibos El recibo a establecer.
	 */
	public void setRecibos(List recibos) {
		this.recibos = recibos;
	}
	
	// RECIBO
	/**
	 * @return Devuelve codMoneda.
	 */
	public String getCodMoneda() {
		return codMoneda;
	}
	/**
	 * @param codMoneda El codMoneda a establecer.
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	/**
	 * @return Devuelve codSeccion.
	 */
	public String getCodSeccion() {
		return codSeccion;
	}
	/**
	 * @param codSeccion El codSeccion a establecer.
	 */
	public void setCodSeccion(String codSeccion) {
		this.codSeccion = codSeccion;
	}
	/**
	 * @return Devuelve codServicio.
	 */
	public String getCodServicio() {
		return codServicio;
	}
	/**
	 * @param codServicio El codServicio a establecer.
	 */
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
	/**
	 * @return Devuelve concepto.
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto El concepto a establecer.
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return Devuelve fecha.
	 */
	public Timestamp getFechaRec() {
		return fechaRec;
	}
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(Timestamp fechaRec) {
		this.fechaRec = fechaRec;
	}
	/**
	 * @return Devuelve glosa.
	 */
	public String getGlosa() {
		return glosa;
	}
	/**
	 * @param glosa El glosa a establecer.
	 */
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	/**
	 * @return Devuelve mensaje.
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje El mensaje a establecer.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * @return Devuelve nomAbonado.
	 */
	public String getNomAbonado() {
		return nomAbonado;
	}
	/**
	 * @param nomAbonado El nomAbonado a establecer.
	 */
	public void setNomAbonado(String nomAbonado) {
		this.nomAbonado = nomAbonado;
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
		this.numeroProducto = ObjectUtil.formatearCuenta(numeroProducto.substring(4),Constante.FORMATO_CUENTA);
	}
	/**
	 * @return Devuelve numRecibo.
	 */
	public String getNumRecibo() {
		return numRecibo;
	}
	/**
	 * @param numRecibo El numRecibo a establecer.
	 */
	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}
	/**
	 * @return Devuelve secuencia.
	 */
	public String getSecuencia() {
		return secuencia;
	}
	/**
	 * @param secuencia El secuencia a establecer.
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	
	
	public void pagarTransferenciaMismoBanco(String transaccion, Usuario usuario ,Vector datos, Vector cuentas) throws Exception{
		Transaction t = new Transaction(transaccion);
		t.setValues(datos);
		t.setCuentas(cuentas);		
		procesar(t);
		generarLog(t,usuario,Constante.REFRENDO_TRANSFERENCIA_BANCARIA);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}
	
	public void getVerRecibo1(Transaction t, Usuario usuario) throws Exception{
			Vector mascara = procesar(t);
	}
	
	public void getVerRecibo(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);	
		this.recibos = new ArrayList();
		
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			//String codProducto = this.numeroProducto.substring(0,2);
			
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
			    if (elemento.elementAt(6)!= null){
					String tramaGenerica =  elemento.elementAt(6).toString();					
				
					for(int j = 0; j< tramaGenerica.length();){
					    ReciboImpl recibo = new ReciboImpl();
					    if (j+80 <= tramaGenerica.length()){
			                String tramita = tramaGenerica.substring(j,j=j+80);
			         
			                try{
			                	
			                
			                	if(!tramita.substring(6,16).equals("          ")){
			                			                	
			                	String f1  = tramita.substring(0,1);
			                	String txtSecuencia = tramita.substring(1,3);
				                String f2  = tramita.substring(3,6);
				                String txtRecibo = tramita.substring(6,16);
				                String f3  = tramita.substring(16,18);
				                String txtFecha 	= tramita.substring(18,28);
				                String f4  = tramita.substring(28,30);
				                String txtAmoneda = tramita.substring(30,33);
				                String f5  = tramita.substring(33,34);
				                String txtMonto = tramita.substring(34,45).trim();
				                txtMonto = ObjectUtil.replaceChar(txtMonto,',',"");
				                txtMonto = ObjectUtil.replaceChar(txtMonto,'.',"");	
				                String f6    = tramita.substring(45,47);
				                String txtGlosa = tramita.substring(47,64);
				                String f7    = tramita.substring(64,65);
				                String txtCmoneda = tramita.substring(65,68);
				                String f8    = tramita.substring(68,79);
				                
				         
				                recibo.setSecuencia(txtSecuencia);
				                recibo.setNumRecibo(txtRecibo);
				              
				                if(txtFecha != null && txtFecha.trim() != "" &&  txtFecha.trim() != "          "){
				              
				                	recibo.setFecha(ObjectUtil.tramaToTimestamp(txtFecha.substring(6, 10)+txtFecha.substring(3, 5)+txtFecha.substring(0, 2)));
				                	
				                }
				                recibo.setMoneda(txtAmoneda);
			                    recibo.setImporte(ObjectUtil.tramaToBigDecimal(txtMonto,2,""));
			                    recibo.setGlosa(txtGlosa);
			                    recibo.setCodMoneda(txtCmoneda);
			                    /**
			                    if ("604".equals(txtCmoneda))
			                    	recibo.setCodMoneda(Constante.MONEDA_SOL);
				    		    else if ("840".equals(txtCmoneda))
				    		    	recibo.setCodMoneda(Constante.MONEDA_DOLAR);
				    		    */
				    		    if(!recibo.getNumRecibo().trim().equals(""))	{
				    		    	 this.recibos.add((Recibo)recibo);
				    		    	}			    		    
			                	}
			                	
			                }catch (Exception e) {
			                	e.printStackTrace();
			                	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Ver_Recibo_Telefono");
                                break;
                            }
					    }    
			            else{
			                //puchito
			                String puchito = tramaGenerica.substring(j,tramaGenerica.length());
			                //if(Constante.VER_LOG) log.warn("puchito:"+puchito);
			                break;
			            }
					}
					//System.out.println("recibos.size():"+recibos.size());
					
			    }else {
	      
			    }
		}
		else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    this.setNomAbonado(elemento.elementAt(6).toString().trim());
			}
		
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    this.setCodServicio(elemento.elementAt(6).toString().trim());
			}
			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			    this.setCodSeccion(elemento.elementAt(6).toString().trim());
			}
			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			    this.setNumRecibo(elemento.elementAt(6).toString().trim());
			}
			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			    this.setMensaje(elemento.elementAt(6).toString().trim());
			}
			
		}	
		//generarLog(t,usuario,Constante.REFRENDO_ULTIMOS_MOVIMIENTOS_CTS);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}
	
	public void getConfirmaPago(Usuario usuario,Cuenta cuenta, BigDecimal monto) throws Exception{
		
		// TODO esta para es para validar el Limite de Operacion.
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario, cuenta ,monto);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}
	
	public void getPagoRecibo(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		//if(Constante.VER_LOG) System.out.println("ENTRO a getPagoRecibo");
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNumeroProducto(elemento.elementAt(6).toString().trim());
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			    this.setImporte(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			    this.setImpuesto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
			    this.setImporteDol(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
			    this.setTipoCambio(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),7,"").setScale(4,BigDecimal.ROUND_HALF_EVEN));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
			    this.setImporteSol(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
			    setNroOperacion(elemento.elementAt(6).toString().trim());
			}
		}
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_PAGO_TELEFONICA);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}
	
	public void getPagoRecarga(Transaction t, Usuario usuario) throws Exception{
		Vector mascara = procesar(t);
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			try {
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					setNroSerie(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					setNroCorrelativo(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					setNroTrace(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
					setNomAbonado(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
					setNroDiasVigencia(String.valueOf(Integer.parseInt(elemento.elementAt(6).toString().trim())));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
					setCodAutoriz(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
					setImporteSinImp(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_19)){
					setImpuesto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_21)){
					setImporte(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					setNroOperacion(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
					setItf(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
			}catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Pago_Recarga_Telefono");
                break;
            }
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_RECARGA_TELEFONO);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
	}
	
	/*
	 * METODO QUE ENVIA LA TRAMA Y SEPARA LOS DATOS RECIBIDOS EN LA RECARGA DE CLARO
	 * AUTOR: GUR
	 * FECHA: 05/02/2010
	 * getPagoRecarga(Transaction t, Usuario usuario)
	 */
	
	public void getPagoRecargaClaro(Transaction t, Usuario usuario) throws Exception{
	
		Vector mascara = procesar(t);
	
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			try {
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					setImporteSinImp(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					setComision(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					setItf(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					setNroOperacion(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					setImporte(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					setCodAutoriz(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					setCodSeccion(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
					setNumRecibo(elemento.elementAt(6).toString().trim());
				}
				
			}catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Pago_Recarga_Claro");
                break;
            }
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_RECARGA_CLARO);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
	}
	
	public void getPagoRecargaOtros(Transaction t, Usuario usuario) throws Exception{
		
		Vector mascara = procesar(t);
	
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			try {
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
					setImporteSinImp(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				
				}
				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
					setImpuesto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
					
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					setComision(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
					
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					setItf(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					setNroOperacion(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					setImporte(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim(),2,""));
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					setCodAutoriz(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					setCodSeccion(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
					setNumRecibo(elemento.elementAt(6).toString().trim());
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					setDocumento(elemento.elementAt(6).toString().trim());
				}
				
			}catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Pago_Recarga_Bitel");
                break;
            }
		}
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_RECARGA_BITEL);
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
	}
	
	public void getConsultaRecargaOtros(Transaction t, Usuario usuario) throws Exception{
		
		Vector mascara = procesar1(t);
	
		for (Iterator iter = mascara.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			try { 
				//Viene en la  mascara 500,501,502,600,601 
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){//802
					setImporteMinimo(elemento.elementAt(6).toString().trim());
				
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){//803
					setGlosa(elemento.elementAt(6).toString().trim());
										
				}
				
				
			}catch (Exception e) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Consulta_Recarga_Bitel");
                break;
            }
		}
		
		t.setObjeto(this);
	
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
	}
	
	/**
	 * @return Devuelve simboloMoneda.
	 */
	public String getSimboloMoneda() {
		return simboloMoneda;
	}
	/**
	 * @param simboloMoneda El simboloMoneda a establecer.
	 */
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}
	/**
	 * @return Devuelve importeDol.
	 */
	public BigDecimal getImporteDol() {
		return importeDol;
	}
	/**
	 * @param importeDol El importeDol a establecer.
	 */
	public void setImporteDol(BigDecimal importeDol) {
		this.importeDol = importeDol;
	}
	/**
	 * @return Devuelve importeSol.
	 */
	public BigDecimal getImporteSol() {
		return importeSol;
	}
	/**
	 * @param importeSol El importeSol a establecer.
	 */
	public void setImporteSol(BigDecimal importeSol) {
		this.importeSol = importeSol;
	}
	/**
	 * @return Devuelve tipoCambio.
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}
	/**
	 * @param tipoCambio El tipoCambio a establecer.
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	/**
	 * @return Devuelve descripcionLocalidad.
	 */
	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}
	/**
	 * @param descripcionLocalidad El descripcionLocalidad a establecer.
	 */
	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTelefono#getCtaFormateada()
	 */
	public String getCtaFormateada() {
		// TODO Apéndice de método generado automáticamente
		return this.ctaFormateada;
	}
	public void setCtaFormateada(String ctaFormateada) {
		// TODO Apéndice de método generado automáticamente
		this.ctaFormateada=ctaFormateada;
	}
	/**
	 * @return Devuelve recibo.
	 */
	public String getRecibo() {
		return recibo;
	}
	/**
	 * @param recibo El recibo a establecer.
	 */
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	/**
	 * @return Devuelve fecEmisionRecibo.
	 */
	public String getFecEmisionRecibo() {
		return fecEmisionRecibo;
	}
	/**
	 * @param fecEmisionRecibo El fecEmisionRecibo a establecer.
	 */
	public void setFecEmisionRecibo(String fecEmisionRecibo) {
		this.fecEmisionRecibo = fecEmisionRecibo;
	}
	/**
	 * @return Devuelve montoFormat.
	 */
	public String getMontoFormat() {
		return montoFormat;
	}
	/**
	 * @param montoFormat El montoFormat a establecer.
	 */
	public void setMontoFormat(String montoFormat) {
		this.montoFormat = montoFormat;
	}
	/**
	 * @return Devuelve empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa El empresa a establecer.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return Returns the codAutoriz.
	 */
	public String getCodAutoriz() {
		return codAutoriz;
	}
	/**
	 * @param codAutoriz The codAutoriz to set.
	 */
	public void setCodAutoriz(String codAutoriz) {
		this.codAutoriz = codAutoriz;
	}
	/**
	 * @return Returns the importeSinImp.
	 */
	public BigDecimal getImporteSinImp() {
		return importeSinImp;
	}
	/**
	 * @param importeSinImp The importeSinImp to set.
	 */
	public void setImporteSinImp(BigDecimal importeSinImp) {
		this.importeSinImp = importeSinImp;
	}
	/**
	 * @return Returns the impuesto.
	 */
	public BigDecimal getImpuesto() {
		return impuesto;
	}
	/**
	 * @param impuesto The impuesto to set.
	 */
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}
	/**
	 * @return Returns the nroCorrelativo.
	 */
	public String getNroCorrelativo() {
		return nroCorrelativo;
	}
	/**
	 * @param nroCorrelativo The nroCorrelativo to set.
	 */
	public void setNroCorrelativo(String nroCorrelativo) {
		this.nroCorrelativo = nroCorrelativo;
	}
	/**
	 * @return Returns the nroDiasVigencia.
	 */
	public String getNroDiasVigencia() {
		return nroDiasVigencia;
	}
	/**
	 * @param nroDiasVigencia The nroDiasVigencia to set.
	 */
	public void setNroDiasVigencia(String nroDiasVigencia) {
		this.nroDiasVigencia = nroDiasVigencia;
	}
	/**
	 * @return Returns the nroSerie.
	 */
	public String getNroSerie() {
		return nroSerie;
	}
	/**
	 * @param nroSerie The nroSerie to set.
	 */
	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	/**
	 * @return Returns the nroTrace.
	 */
	public String getNroTrace() {
		return nroTrace;
	}
	/**
	 * @param nroTrace The nroTrace to set.
	 */
	public void setNroTrace(String nroTrace) {
		this.nroTrace = nroTrace;
	}
	
	
	
	/**
	 * @return Returns the codEntidad.
	 */
	public String getCodEntidad() {
		return codEntidad;
	}
	/**
	 * @param codEntidad The codEntidad to set.
	 */
	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}
	/**
	 * @return Returns the codServEnt.
	 */
	public String getCodServEnt() {
		return codServEnt;
	}
	/**
	 * @param codServEnt The codServEnt to set.
	 */
	public void setCodServEnt(String codServEnt) {
		this.codServEnt = codServEnt;
	}
}
