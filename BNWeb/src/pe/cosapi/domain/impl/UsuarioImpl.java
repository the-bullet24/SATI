
package pe.cosapi.domain.impl;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import pe.bn.cldinamica.action.form.Cuentas;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.cldinamica.action.form.ProductoResponse;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.listener.Util;
import pe.bn.login.domain.IngresoTarjeta;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.AtriTransferencia;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Notificacion;
import pe.cosapi.domain.NotificacionClave;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class UsuarioImpl extends OperacionImplNueva implements Usuario, Serializable{
	private static LoggerSati log3 = LoggerSati.getInstance(UsuarioImpl.class.getName());
 
	private String nombre;
	private String tipoPersona;
	private String tipoIngreso;
	private String nombreTipoPersona;
	private List cuentas;
	private Map mapCuentas;
	private List listCuentas;
	private Tarjeta tarjeta;
	private TipoCambio tipoCambio;
	private Notificacion notificacion;
	private NotificacionClave notificacionClave;
	private AtriTransferencia estadoOperaciones; 
	
	public IngresoTarjeta ingreso;
	
	private ParametrosTDC claveDinamica;
	private String tipoTarjeta;//usada solo para tomar el dato del cmb Tip Tarjeta del Login
	private String fechaIngreso;
	private String flagAfiliacion;
	
	private String email;
	private String celular;
	private String opeCelular;
	private String celularFormat;
	private static final String TIPO_PERSONA = "600";
	private static final String TIPO_TARJETA= "601";	
	private static final String NUMERO_TARJETA= "602";	
	private static final String CLAVE_TARJETA= "603";	
	private static final String CVV2 = "604";
	
	private static final String NOMBRE_PERSONA = "107";
	private static final String CUENTA = "393";	
	private static final String DNI = "400";	
	private static final String TARJETA_ASOCIADA= "103";	

	private String numDocumento;
	private String tipoDocumento;
	private String fechaNacimiento;
	private String codPerfil;
	private String montoLimite;
	private String codigoCLDI;
	private String codMontoLimite;
	private String codigoCic;
	private String flagBannerPrestamo;
	private String montoMaxPrestamo;
	private String flagPagoTC;
	
	private Boolean flagActualizaDato;
	
	private String  flagActualizaDatoHost;
	private String  cciCliente;
	private String  entidadCuenta;
	private String  entidadCuentaDesc;
	private String  indTransfContacto;
	


	public String getIndTransfContacto() {
		return indTransfContacto;
	}

	public void setIndTransfContacto(String indTransfContacto) {
		this.indTransfContacto = indTransfContacto;
	}

	public String getEntidadCuentaDesc() {
		return entidadCuentaDesc;
	}

	public void setEntidadCuentaDesc(String entidadCuentaDesc) {
		this.entidadCuentaDesc = entidadCuentaDesc;
	}

	public String getOpeCelular() {
		return opeCelular;
	}

	public void setOpeCelular(String opeCelular) {
		this.opeCelular = opeCelular;
	}

	public String getCciCliente() {
		return cciCliente;
	}

	public void setCciCliente(String cciCliente) {
		this.cciCliente = cciCliente;
	}

	public String getEntidadCuenta() {
		return entidadCuenta;
	}

	public void setEntidadCuenta(String entidadCuenta) {
		this.entidadCuenta = entidadCuenta;
	}

	private List tarjetas;
	
	private String codCliente;

	
	public String getCelularFormat() {
		return celularFormat;
	}

	public void setCelularFormat(String celularFormat) {
		this.celularFormat = celularFormat;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFlagPagoTC() {
		return flagPagoTC;
	}

	public void setFlagPagoTC(String flagPagoTC) {
		this.flagPagoTC = flagPagoTC;
	}

	public String getFlagBannerPrestamo() {
		return flagBannerPrestamo;
	}

	public void setFlagBannerPrestamo(String flagBannerPrestamo) {
		this.flagBannerPrestamo = flagBannerPrestamo;
	}

	public String getMontoMaxPrestamo() {
		return montoMaxPrestamo;
	}

	public void setMontoMaxPrestamo(String montoMaxPrestamo) {
		this.montoMaxPrestamo = montoMaxPrestamo;
	}

	public String getCodigoCic() {
		return codigoCic;
	}

	public void setCodigoCic(String codigoCic) {
		this.codigoCic = codigoCic;
	}
	
	
    public String getCodMontoLimite() {
		return codMontoLimite;
	}

	public void setCodMontoLimite(String codMontoLimite) {
		this.codMontoLimite = codMontoLimite;
	}

	public String getCodigoCLDI() {
		return codigoCLDI;
	}

	public void setCodigoCLDI(String codigoCLDI) {
		this.codigoCLDI = codigoCLDI;
	}

	public String getMontoLimite() {
		return montoLimite;
	}

	public void setMontoLimite(String montoLimite) {
		this.montoLimite = montoLimite;
	}

	public String getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(String codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getFlagAfiliacion() {
		return flagAfiliacion;
	}

	public void setFlagAfiliacion(String flagAfiliacion) {
		this.flagAfiliacion = flagAfiliacion;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public ParametrosTDC getClaveDinamica() {
		return claveDinamica;
	}

	public void setClaveDinamica(ParametrosTDC claveDinamica) {
		this.claveDinamica = claveDinamica;
	}

	public String getTipoTarjeta() {
        return tipoTarjeta;
    }
    
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
	/**
	 * @return Devuelve cuentas.
	 */
	public List getCuentas() {
		return cuentas;
	}
	/**
	 * @param cuentas El cuentas a establecer.
	 */
	public void setCuentas(List cuentas) {
		this.cuentas = cuentas;
	}
	/**
	 * @return Devuelve nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre El nombre a establecer.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return Devuelve tipoPersona.
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	/**
	 * @param tipoPersona El tipoPersona a establecer.
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	/**
	 * @return Devuelve tipoPersona.
	 */
	public String getTipoIngreso() {
		return tipoIngreso;
	}
	/**
	 * @param tipoPersona El tipoPersona a establecer.
	 */
	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}
	
	public UsuarioImpl(){
		/*
		 * Creamos un usuario dummy
		 */
		this.nombre = "";
		this.tipoPersona = "";
		Tarjeta x = new TarjetaImpl("");
		x.setClave("1111");
		x.setAsociado(true);
		x.setTipo("");
		this.tarjeta = x;
		this.cuentas = new ArrayList();
		this.listCuentas = new ArrayList();
		this.mapCuentas = new HashMap();		
		CuentaImpl cuenta  = null;
		
		cuenta = new CuentaImpl();
		cuenta.setTipoProducto("CA");
		cuenta.setNumeroProducto("99999999999999999999");	
		cuenta.setMonedaProducto("SOL");
		cuenta.setSaldo(new BigDecimal(0.55));
		this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
		this.cuentas.add((Cuenta)cuenta);
	}
	
	/*
	 * 
	 */
	public void ValidaCtaCte(Transaction t) throws Exception{
		try{
		    Vector resultado = procesar(t);
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
		}
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
	}

	NotificacionImpl noti;	
	AtriTransferenciaImpl operaciones;
	NotificacionClaveImpl notiClave = new NotificacionClaveImpl();;	
	
	public UsuarioImpl(String codTransaccion, String codCompany, Vector valores, Vector cuentas_, Transaction t) throws Exception{

		t.setValues(valores);
		t.setCuentas(cuentas_);		
		String codgo = "";
		String cta = "";
		
		//consultar trama
		Vector resultado = procesar(t);
		
		TarjetaImpl tarjeta = new TarjetaImpl();
		TipoCambioImpl tipo = new TipoCambioImpl();
		PrestamoImpl prestamo= new PrestamoImpl();

		this.mapCuentas = new HashMap();	
		this.cuentas = new ArrayList();
		this.listCuentas = new ArrayList();
		
		
		
		if ("LG01".trim().equals(codTransaccion)){		
		noti = new NotificacionImpl();
		operaciones = new AtriTransferenciaImpl();
		}
	
		if ("LG09".trim().equals(codTransaccion)){		
			noti = new NotificacionImpl();
			operaciones = new AtriTransferenciaImpl();
		}
		
		

		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
			
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
				this.setNombre(elemento.elementAt(6).toString());
				//this.setCodPerfil("1");
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
				tarjeta.setAsociado(false);
				if(elemento.elementAt(6).toString().trim().equals("T"))
					tarjeta.setAsociado(true);
				//TODO PARCHANDO SI VIENE 
				if(ObjectUtil.isStringBlank(elemento.elementAt(6).toString()))
				    tarjeta.setAsociado(true);
				
				//if(Constante.VER_LOG) System.out.println("Flag Titular:"+elemento.elementAt(6).toString().trim());
			}
			
/**
			else if(elemento.elementAt(0).toString().equals(UsuarioImpl.TIPO_PERSONA)){
				this.setTipoPersona(elemento.elementAt(6).toString());
*/
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				String codTpRetorno = elemento.elementAt(6).toString().trim();
								
				//codTpRetorno = "N";
				if (codTpRetorno.equals("N")){
					this.setTipoPersona("01");
				} else if (codTpRetorno.equals("J")){
					this.setTipoPersona("02");
				} else if (tarjeta.getTipo().equals("01")){
					this.setTipoPersona("01");
				} else {
					throw new ArrayRuleException(ConstanteError.GENERICO,"Error de acceso, definir el tipo de persona...");
				}
			}
			else if(elemento.elementAt(0).equals(UsuarioImpl.NUMERO_TARJETA)){
				if(!elemento.elementAt(6).toString().equals(""))
					
					tarjeta.setNumero(elemento.elementAt(6).toString());
					///tarjeta.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
			}
			
			else if(elemento.elementAt(0).equals(UsuarioImpl.DNI)){
				if(!elemento.elementAt(6).toString().equals(""))
					tarjeta.setNumero(elemento.elementAt(6).toString());
					tarjeta.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
			}
			else if(elemento.elementAt(0).equals(UsuarioImpl.CLAVE_TARJETA)){
				tarjeta.setClave(elemento.elementAt(6).toString());
			}
			else if(elemento.elementAt(0).equals(UsuarioImpl.TIPO_TARJETA)){
				tarjeta.setTipo(elemento.elementAt(6).toString());
				
				
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){
				String cTcCompra = elemento.elementAt(6).toString();
				
				
				if (cTcCompra.trim().length() == 0){
					cTcCompra = "0";
				}
				int nTcCompra = Integer.valueOf(cTcCompra.trim()).intValue();
				if (nTcCompra == 0){
					tipo.setCompra(ObjectUtil.tramaToBigDecimal("0",4));
					tipo.setCompraMuestra(new BigDecimal("0.0000"));
				} else {
					tipo.setCompra(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(),7));
					tipo.setCompraMuestra(ObjectUtil.tramaToBigDecimal(cTcCompra.substring(0, cTcCompra.trim().length() - 3),4));
				}
				
			}
			else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_4)){
				String cTcVenta = elemento.elementAt(6).toString();
				
				if (cTcVenta.trim().length() == 0){
					cTcVenta = "0";
				}
				int nTcVenta = Integer.valueOf(cTcVenta.trim()).intValue();
				if (nTcVenta == 0){
					tipo.setVenta(new BigDecimal("0.0000"));
					tipo.setVentaMuestra(ObjectUtil.tramaToBigDecimal("0",4));
				} else {
					tipo.setVenta(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(),7));
					tipo.setVentaMuestra(ObjectUtil.tramaToBigDecimal(cTcVenta.substring(0, cTcVenta.trim().length() - 3),4));
				}
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
				cta = elemento.elementAt(6).toString().trim();
							
				if(cta!=null && !cta.equals("") && cta.length()>0){
				//cta = cta.substring(cta.length()-11);
				prestamo.setCuenta(cta);
				cta = ObjectUtil.formatearCuenta(cta,Constante.FORMATO_CUENTA);
				}else{	
						cta=null;	
				}
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
		
				codgo = String.valueOf(elemento.elementAt(6).toString().trim());
	
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
				if(cta!=null)
				prestamo.setSaldoActual(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
				if(cta!=null)
				prestamo.setInteresActual(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
				if(cta!=null)
				prestamo.setDeudaActual(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim()));
			}
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
				if(cta!=null)
				
				this.setMontoMaxPrestamo(""+ObjectUtil.formatearMonto(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString().trim())));
			}
			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
				
				this.setNumDocumento(elemento.elementAt(6).toString().trim());
			
			}
			
			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_16)){
				
				this.setCodMontoLimite(elemento.elementAt(6).toString().trim());
						
			}
		

			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
								
				this.setCodigoCLDI(elemento.elementAt(6).toString().trim());
		
				codCliente = elemento.elementAt(6).toString().trim();
			
				//Para cuentas CTS
				obtenerCuentas(codCliente);	
				
		    	if(this.getCodigoCLDI() != null && !this.getCodigoCLDI().equals("")){
		        	
		    		 this.setCodigoCic(this.getCodigoCLDI().substring(0,12));
		    		
		        }
				
			}

			else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
		
				this.setFlagBannerPrestamo(elemento.elementAt(6).toString().trim());
			
			}
           
            else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_19)){
                this.setFlagActualizaDatoHost(elemento.elementAt(6).toString().trim());    
            }
			
            else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_20)){
            	
            
            	String estadoNotificacion ="";
            	if (elemento.elementAt(6).toString().trim().length()>0){
					if(elemento.elementAt(6).toString().trim().equals("S")){
						estadoNotificacion= "1";
					}else if(elemento.elementAt(6).toString().trim().equals("N")){
						estadoNotificacion= "0";
					}
				
					noti.setEstadoNotificacion(estadoNotificacion);   

					
				}
            	                              
            }
			
            else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_21)){
            	
            	String montoNotificacion="";
            	if (elemento.elementAt(6).toString().trim().length()>0){
					montoNotificacion= elemento.elementAt(6).toString().trim();
					
					String monto = Funciones.elmiminarCerosAlaIzquierda(montoNotificacion);
					if (monto== null || monto.isEmpty()){
						noti.setMontoNotificacion("0");
					} else{
						noti.setMontoNotificacion(monto);
					}
					

				}
            	                
            }
			
			
            else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_22)){
            	
            	String tipoMonedaNotificacion="";
            	if (elemento.elementAt(6).toString().trim().length()>0){
            		if(elemento.elementAt(6).toString().trim().equals("S")){
            			tipoMonedaNotificacion= "PEN";
            			noti.setTipoMonedaNotificacion(tipoMonedaNotificacion);    
    	                //System.out.println("noti.getTipoMonedaNotificacion():::"+noti.getTipoMonedaNotificacion());
            			
            		}else if(elemento.elementAt(6).toString().trim().equals("D")){
            			tipoMonedaNotificacion= "USD";
            			noti.setTipoMonedaNotificacion(tipoMonedaNotificacion);    
    	                //System.out.println("noti.getTipoMonedaNotificacion():::"+noti.getTipoMonedaNotificacion());
            		}
					
					
				}
            	

            }
			
            else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_23)){
            	   
                String medioNotificacion="";
            	if (elemento.elementAt(6).toString().trim().length()>0){
					medioNotificacion= elemento.elementAt(6).toString().trim();
					noti.setMedioNotificacion(medioNotificacion); 
					System.out.println("noti.getMedioNotificacion():::"+noti.getMedioNotificacion());
				}
            	

                
            }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_25)){
            	   
            	
                String estadoOperaciones="";
            	if (elemento.elementAt(6).toString().trim().length()>0){
            		estadoOperaciones= elemento.elementAt(6).toString().trim();
            		operaciones.setEstadoOperaciones(estadoOperaciones);
            		
//            		if(!(operaciones.getEstadoOperaciones() == null || operaciones.getEstadoOperaciones().isEmpty())){
//            			usuario.getEstadoOperaciones().getEstadoOperaciones() = operaciones.getEstadoOperaciones();
//            		}
            	
					System.out.println("operaciones.getEstadoOperaciones():::"+operaciones.getEstadoOperaciones());
				}
            	
                
            
			
		   }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_27)){
        	   
			   this.setCciCliente(elemento.elementAt(6).toString().trim());    
           	
               
           	
		  }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_28)){
       	   
			   this.setEntidadCuenta(elemento.elementAt(6).toString().trim());    
          	
              
          }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_29)){
          	   
   			   this.setEntidadCuentaDesc(elemento.elementAt(6).toString().trim());  
   			   
   			   
   			/******************************* INICIO *****************************/
   			/************************* MGL - 11/12/2023 *************************/
   			/********************************************************************/
   			/* Valores para estadoExpira
   			 * EXPERO = 1
   			 * POR EXPIRAR = 2
   			 * 
   			 * */
   			/*
   			String estadoExpira ="2";
   			this.notiClave.setEstadoExpira(estadoExpira);   
   			
   			String diasVencimiento ="5";
   			this.notiClave.setDiasVencimiento(diasVencimiento);
   			
   			this.setNotificacionClave(notiClave);
   			 */	  			   
   			   
   			   
          }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_30)){         	   
  			   String estadoExpira = (elemento.elementAt(6).toString().trim());   
  			   this.notiClave.setEstadoExpira(estadoExpira);   
  			   System.out.println("estadoExpira:"+estadoExpira);
  			   
          }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_31)){         	   
        	  String diasVencimiento =(elemento.elementAt(6).toString().trim());  
        	  this.notiClave.setDiasVencimiento(diasVencimiento);
        	  System.out.println("diasVencimiento:"+diasVencimiento);
        	  
          }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_32)){         	   
  			   String signoError =(elemento.elementAt(6).toString().trim());    
  			   this.notiClave.setSignoError(signoError);
  			   System.out.println("signoError:"+signoError);
  			   
          }else if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_33)){         	   
        	  String codigoError =(elemento.elementAt(6).toString().trim());   
        	  this.notiClave.setCodigoError(codigoError);
        	  System.out.println("codigoError:"+codigoError);
                 

        /******************************** FIN *******************************/
     	/************************* MGL - 11/12/2023 *************************/
     	/********************************************************************/
     	
     	/*******************************************************************/	
  			   
  			   
          }else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_14)){
				
				String tramaGenerica =  elemento.elementAt(6).toString();
				
				this.cuentas = new ArrayList();
				int j = 0;
	
				for(; j< tramaGenerica.length();){					
					try{
						CuentaImpl cuenta = new CuentaImpl();
						String codServicio=tramaGenerica.substring(j+2,j+4);
						
						if(tramaGenerica.substring(j,j+=4).trim().equals(""))
							break;
						if (!codServicio.equals(Constante.COD_SERVICIO_PRESTAMO) && !codServicio.equals(Constante.COD_SERVICIO_TAR_CREDITO)
								&& !codServicio.equals(Constante.COD_CUENTA_CTS_MN) && !codServicio.equals(Constante.COD_CUENTA_CTS_ME)){ 
							
//						if (codServicio.equals(Constante.COD_CUENTA_AHORROS_MN) || codServicio.equals(Constante.COD_CUENTA_AHORROS_ME))
//							{ 
							
							String tipoCuenta = tramaGenerica.substring(j,j=j+2).trim();
							
							if(tipoCuenta.equals(""))
								break;
							cuenta.setTipoProducto(tipoCuenta);
							
							String codigo = tipoCuenta+tramaGenerica.substring(j,j=j+9);
							
							if(codigo.equals("00000000000"))
								break;
							cuenta.setNumeroProducto(codigo);
						
							String moneda = tramaGenerica.substring(j,j=j+3);
							cuenta.setMonedaProducto(moneda);
							cuenta.setSaldo(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j,j=j+15),2,tramaGenerica.substring(j,j=j+1)));
							cuenta.setSaldoContable(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j,j=j+15),2,tramaGenerica.substring(j,j=j+1)));
							cuenta.setEstado(tramaGenerica.substring(j,j=j+1));
							
							cuenta.setIndicadorSaldo(tramaGenerica.substring(j,j=j+1));
							//cuenta.setIndicadorSaldo(String.valueOf(fg+1));
							//System.out.println("***--- IndicadorSaldo:"+cuenta.getIndicadorSaldo());
							cuenta.setIndicadorItf(tramaGenerica.substring(j,j=j+1));
							//cuenta.setIndicadorItf(String.valueOf(fg));
							//System.out.println("***--- IndicadorItf:"+cuenta.getIndicadorItf());
							cuenta.setIndicadorCtaCte(tramaGenerica.substring(j,j=j+1));
							//System.out.println("***--- FG Valor:"+String.valueOf(fg));
							//cuenta.setIndicadorCtaCte(String.valueOf(fg));
							//System.out.println("***--- IndicadorCtaCte:"+cuenta.getIndicadorCtaCte());
							//fg = fg + 1;
							//j+=29; 
							j+=26;
						}else{ //Prestamo						    
							if(codServicio.equals(Constante.COD_SERVICIO_PRESTAMO)){
								
							    cuenta.setNumeroProducto(tramaGenerica.substring(j,j+11));
								
							    cuenta.setTipoProducto(Constante.COD_CUENTA_PRESTAMOS);
							    cuenta.setNumeroDesembolso(tramaGenerica.substring(j+14,j+16));
							    cuenta.setSaldo(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j+46,j+60).trim(),2));
							    cuenta.setFlagPrestamo(tramaGenerica.substring(j+69,j+70));
							  							 
							    j+=76;
							}else{								
								
								if(codServicio.equals(Constante.COD_CUENTA_CTS_MN) || codServicio.equals(Constante.COD_CUENTA_CTS_ME)){
								
									  j=j+76;
									
								}
																
								else if (codServicio.equals(Constante.COD_SERVICIO_TAR_CREDITO)){
									
									List lista= FacadeFactory.getGeneralFacade().getFlagTransaccion(Constante.TC_BN_FLAG_THLPDET_COD);
									if(lista!=null && lista.size()>0){
										ComboUtil list = (ComboUtil)lista.get(0); 
										this.setFlagPagoTC(list.getCodigo());
										
									}
									
								
									  cuenta.setTipoProducto(Constante.COD_TARJETA_CREDITO);
									  cuenta.setTipoTarjeta(tramaGenerica.substring(j,j+10));
									  cuenta.setNumeroProducto(tramaGenerica.substring(j+10,j+26));
									  cuenta.setSaldo(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j+26,j+41),2));
									  		  
									  j+=76;
								}
								
							}
						}
						
					
						if(cuenta.getNumeroProducto() != null){
						this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
						this.cuentas.add((Cuenta)cuenta);}
					}
					catch(Exception e){
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						e.printStackTrace();
						break;
					}
				}
				 
          	
				
			}
			
			
				
//				Iterator < CuentaImpl > it = this.listCuentas.iterator();
//				
//				System.out.println(":::::::::.TAMAÑO DE LA LISTA2:::::::::::::"+this.cuentas.size());
//				System.out.println("::::::::::CUENTAS - INICIO2::::::::::::");
//				while (it.hasNext()) {
//					System.out.println(it.next());
//				}
//				System.out.println("::::::::::CUENTAS - FIN2::::::::::::");
//				
//			}	
						
		  }
		
		
		this.setNotificacionClave(notiClave);
		this.setTipoCambio(tipo);
		this.setTarjeta(tarjeta);
		
		this.setTipoIngreso(codCompany);
		
		if ("LG01".trim().equals(codTransaccion)){
			this.setNotificacion(noti);		
			this.setEstadoOperaciones(operaciones);
		}
		
		if ("LG09".trim().equals(codTransaccion)){
			this.setNotificacion(noti);		
			this.setEstadoOperaciones(operaciones);
		}
		
		if(codTransaccion.substring(0, 4).equals("LG06")){
			obtenerCuentas(codTransaccion.substring(4));
			codTransaccion = codTransaccion.substring(0, 4);
		}
		
	

	}
	
	
public void obtenerCuentas(String codCliente) throws JsonParseException, JsonMappingException, IOException{
	
	
		
		//consultar ms de productos para obtener cuentas CTS
				String tipoDoc = ""; 
				String numDoc = "";
				String saldo = "";
				
				
				
				if(!codCliente.isEmpty()){
						tipoDoc=codCliente.substring(12,13);
						numDoc=codCliente.substring(13);	
				
				ProductoResponse productoResponse = UtilAction.consultarProductos(tipoDoc,numDoc);
				if(productoResponse.getF09_cuentas() != null)
				{
				
				for(Cuentas ctas: productoResponse.getF09_cuentas()){
					
//					if( Constante.COD_CUENTA_AHORROS_MN.equals(ctas.getF09_producto()) 
//							&& Constante.COD_CUENTA_ESTADO_ACTIVA.equals(ctas.getF09_estado())
//							&& Constante.COD_CUENTA_SITUACION_NORMAL.equals(ctas.getF09_situacion())){
//						
//						saldo = ctas.getF09_saldo().replace(".","");
//						saldo = saldo.replace("+", "");
//						
//						CuentaImpl cuenta = new CuentaImpl();
//						cuenta.setTipoProducto(ctas.getF09_producto());
//						cuenta.setNumeroProducto(Funciones.formatearCuenta1(ctas.getF09_ncuenta()));
//						cuenta.setMonedaProducto(Constante.MONEDA_SOL);
//						cuenta.setSaldo( ObjectUtil.tramaToBigDecimal(saldo,2) );
//						cuenta.setSaldoContable(ObjectUtil.tramaToBigDecimal(saldo,2));
//						
//						String saldoTmp = UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual();
//						String saldoContableTmp = UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoContable();
//						
//						if (!saldoTmp.isEmpty() || !saldoContableTmp.isEmpty()) {
//							
//							cuenta.setSaldo( new BigDecimal(UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual()));
//							cuenta.setSaldoContable( new BigDecimal( UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoContable()));
//							
//							cuenta.setEstado(ctas.getF09_estado());
//							cuenta.setCuentaformateada(Funciones.formatearCuenta2(ctas.getF09_ncuenta()));
//							
//							this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
//							this.cuentas.add((Cuenta)cuenta);
//							this.listCuentasWS.add((Cuenta)cuenta);
//						}
//						
//					}
					
					
//					if(Constante.COD_CUENTA_AHORROS_ME.equals(ctas.getF09_producto())
//							&& Constante.COD_CUENTA_ESTADO_ACTIVA.equals(ctas.getF09_estado())
//							&& Constante.COD_CUENTA_SITUACION_NORMAL.equals(ctas.getF09_situacion())){
//						
//						saldo = ctas.getF09_saldo().replace(".","");
//						saldo = saldo.replace("+", "");
//						
//						BigDecimal totalCurrentSales = new BigDecimal(0);
//						
//						CuentaImpl cuenta = new CuentaImpl();
//						cuenta.setTipoProducto(ctas.getF09_producto());
//						cuenta.setNumeroProducto(Funciones.formatearCuenta1(ctas.getF09_ncuenta()));
//						cuenta.setMonedaProducto(Constante.MONEDA_DOLAR);
//						
//						String saldoTmp = UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual();
//						String saldoContableTmp = UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoContable();
//						
//						if (!saldoTmp.isEmpty() || !saldoContableTmp.isEmpty()) {
//							
//							cuenta.setSaldo( new BigDecimal(UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual()));
//							cuenta.setSaldoContable( new BigDecimal( UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoContable()));
//							
//							cuenta.setEstado(ctas.getF09_estado());
//							cuenta.setCuentaformateada(Funciones.formatearCuenta2(ctas.getF09_ncuenta()));
//							
//							this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
//							this.cuentas.add((Cuenta)cuenta);
//							this.listCuentasWS.add((Cuenta)cuenta);
//						}
//						
//					}
//					
					if(Constante.COD_CUENTA_CTS_MN.equals(ctas.getF09_producto())
							&& Constante.COD_CUENTA_ESTADO_ACTIVA.equals(ctas.getF09_estado())
							//&& Constante.COD_CUENTA_SITUACION_NORMAL.equals(ctas.getF09_situacion())
							){
						
						saldo = ctas.getF09_saldo().replace(".","");
						saldo = saldo.replace("+", "");
						
						CuentaImpl cuenta = new CuentaImpl();
						cuenta.setTipoProducto(ctas.getF09_producto());
						cuenta.setNumeroProducto(Funciones.formatearCuenta1(ctas.getF09_ncuenta()));
						cuenta.setMonedaProducto(Constante.MONEDA_SOL);
						String saldoTmp = UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual();
						
						if (!saldoTmp.isEmpty()) {
							cuenta.setSaldo( new BigDecimal(UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual()));
							cuenta.setEstado(ctas.getF09_estado());
							cuenta.setCuentaformateada(Funciones.formatearCuenta2(ctas.getF09_ncuenta()));
							
							this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
							this.cuentas.add((Cuenta)cuenta);
							//this.listCuentas.add((Cuenta)cuenta);
								
						}
						
					}
					
					
					if(Constante.COD_CUENTA_CTS_ME.equals(ctas.getF09_producto())
							&& Constante.COD_CUENTA_ESTADO_ACTIVA.equals(ctas.getF09_estado())
							//&& Constante.COD_CUENTA_SITUACION_NORMAL.equals(ctas.getF09_situacion())
							){
						
						saldo = ctas.getF09_saldo().replace(".","");
						saldo = saldo.replace("+", "");
						
						CuentaImpl cuenta = new CuentaImpl();
						cuenta.setTipoProducto(ctas.getF09_producto());
						cuenta.setNumeroProducto(Funciones.formatearCuenta1(ctas.getF09_ncuenta()));
						cuenta.setMonedaProducto(Constante.MONEDA_DOLAR);
						String saldoTmp = UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual();
						
						if (!saldoTmp.isEmpty()) {
							cuenta.setSaldo( new BigDecimal(UtilAction.consultaSaldos(ctas.getF09_producto(), Funciones.formatearCuenta1(ctas.getF09_ncuenta())).getSaldoActual()));
						
							cuenta.setEstado(ctas.getF09_estado());
							cuenta.setCuentaformateada(Funciones.formatearCuenta2(ctas.getF09_ncuenta()));
							
							this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
							this.cuentas.add((Cuenta)cuenta);
							//this.listCuentas.add((Cuenta)cuenta);
								
						}
						
					}
								
					
				  }
				
				}
//						Iterator < CuentaImpl > it = this.listCuentas.iterator();
//						
//						System.out.println(":::::::::.TAMAÑO DE LA LISTA:::::::::::::"+this.cuentas.size());
//						System.out.println("::::::::::CUENTAS - INICIO::::::::::::");
//						while (it.hasNext()) {
//							System.out.println(it.next());
//						}
//						System.out.println("::::::::::CUENTAS - FIN::::::::::::");
						
				}
		
	}
	
	/*
	 *  Validar Usuario por DNI
	 * @see pe.cosapi.domain.Usuario#getMapCuentas()
	 */
	
	public UsuarioImpl(String codTransaccion, String codCompany, Vector valores, Transaction t) throws Exception{

		t.setValues(valores);
		String codgo = "";
		String cta = "";
		
		Vector resultado = procesar(t);
		
		TarjetaImpl tarjeta = new TarjetaImpl();
		PrestamoImpl prestamo= new PrestamoImpl();
		this.mapCuentas = new HashMap();
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
				this.setNombre(elemento.elementAt(6).toString());
			} else if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
				tarjeta.setNumero(elemento.elementAt(6).toString());
			}
		}
		this.setTarjeta(tarjeta);
		this.setTipoIngreso(codCompany);
	}
	
	public Usuario autenticarAfilInternet(String codTransaccion,  Vector valores, Vector cuentas_, Transaction t) throws Exception{
		//Transaction t = new Transaction(codTransaccion);
		t.setValues(valores);
		t.setCuentas(cuentas_);		
		
		Usuario usuario = new UsuarioImpl();
		Vector resultado = procesar1(t);
		
	
		this.mapCuentas = new HashMap();		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			
				if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
					CuentaImpl cuenta = new CuentaImpl();
					cuenta.setNumeroProducto(elemento.elementAt(6).toString());
					cuenta.setTipoProducto(Constante.COD_CUENTA_AHORROS_MN);
					
					this.cuentas.add((Cuenta)cuenta);
					usuario.setCuentas(cuentas);
				}
				if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_2)){
					usuario.setFlagAfiliacion(elemento.elementAt(6).toString());
				}
				if(elemento.elementAt(0).equals(Constante.OUTPUT_STRING_3)){
					usuario.setNombre(elemento.elementAt(6).toString());
				}
									
			}
		
						
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		return usuario;

	}
	
	public String getValidarDatosPersonales(Transaction t,Vector datos, Vector cuentas,Usuario usuario) throws Exception{
		
		Vector resultado = procesar1(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
							
				this.setNombre(element.elementAt(6).toString().trim());
		  }
		}
		
			
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
				
	
		return this.getNombre();		
	}
	
	/**
	 * @return Devuelve mapCuentas.
	 */
	public Map getMapCuentas() {
		return mapCuentas;
	}
	/**
	 * @param mapCuentas El mapCuentas a establecer.
	 */
	public void setMapCuentas(Map mapCuentas) {
		this.mapCuentas = mapCuentas;
	}
	/**
	 * @return Devuelve nombreTipoPersona.
	 */
	public String getNombreTipoPersona() {
		if(this.tipoPersona==null)
			return null;
		if(this.tipoPersona.equals(Constante.COD_PERSON_NAT))
			return Constante.NOMBRE_PERSONA_NATURAL;
		return Constante.NOMBRE_PERSONA_JURIDICA;
	}
	
	  /* (sin Javadoc)
     * @see pe.cosapi.domain.Usuario#getCuentaAhorro()
     */
    public CuentaImpl getCuentaAhorro() {
		CuentaImpl cuenta = null;
		List lista = this.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				break;
			}
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
				break;
			}
		}
		
		if (cuenta != null)
		    if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN) || cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME))
		        return null;
		    
		return cuenta;
	}
    
    
	
	public CuentaImpl getCuentaCorriente(){
		CuentaImpl cuenta = null;
		List lista = this.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
				break;
			}
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
				break;
			}
		}
		
		if (cuenta != null)
		    if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN) || cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME))
		        return null;
		
		    
		return cuenta;
	}

	
	public CuentaImpl getCuentaPrestamo(){
		CuentaImpl cuenta = null;
		List lista = this.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
				break;
			}
		}
		  
		return cuenta;
	}
	
	/**
	 * @param nombreTipoPersona El nombreTipoPersona a establecer.
	 */
	public void setNombreTipoPersona(String nombreTipoPersona) {
		this.nombreTipoPersona = nombreTipoPersona;
	}
	/**
	 * @return Devuelve tarjeta.
	 */
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	/**
	 * @param tarjeta El tarjeta a establecer.
	 */
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	/**
	 * @return Devuelve tipoCambio.
	 */
	public TipoCambio getTipoCambio() {
		return tipoCambio;
	}
	/**
	 * @param tipoCambio El tipoCambio a establecer.
	 */
	public void setTipoCambio(TipoCambio tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.domain.Usuario#actualizarCuentas(pe.cosapi.sarabank.bean.Transaction)
	 */
	public void actualizarCuentas(Transaction t) throws Exception{
		System.out.println("actualizarCuentas..");
		CuentaImpl cuentaPrestamo = this.getCuentaPrestamo();
		Vector resultado = procesar(t);
//		this.cuentas = new ArrayList();
//		this.mapCuentas = new HashMap();		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			//this.cuentas = new ArrayList();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				String tramaGenerica =  element.elementAt(6).toString();		
				
				int j = 0;
				for(; j< tramaGenerica.length();){					
					try{
//						CuentaImpl cuenta = new CuentaImpl();
						if(tramaGenerica.substring(j,j+=4).trim().equals(""))
							break;
						String tipoCuenta = tramaGenerica.substring(j,j=j+2).trim();
						if(tipoCuenta.equals(""))
							break;
//						cuenta.setTipoProducto(tipoCuenta);
						String codigo = tipoCuenta+tramaGenerica.substring(j,j=j+9);
						if(codigo.equals("00000000000"))
							break;
//						cuenta.setNumeroProducto(codigo);
						String moneda = tramaGenerica.substring(j,j=j+3);
//						cuenta.setMonedaProducto(moneda);
						
						j=j+15;
						j=j+1;
						j=j+15;
						j=j+1;
						j=j+1;
//						cuenta.setSaldo(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j,j=j+15),2,tramaGenerica.substring(j,j=j+1)));
//						cuenta.setSaldoContable(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j,j=j+15),2,tramaGenerica.substring(j,j=j+1)));
//						cuenta.setEstado(tramaGenerica.substring(j,j=j+1));
						j+=29; 
//						this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
//						this.cuentas.add((Cuenta)cuenta);
					}
					catch(Exception e){
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						//e.printStackTrace();
						break;
					}
				}
			}
			
			//Prestamos
		
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				String tramaGenerica =  element.elementAt(6).toString();
			
				//this.cuentas = new ArrayList();
				int j = 0;

				for(; j< tramaGenerica.length();){					
					try{
						CuentaImpl cuenta = new CuentaImpl();
						String codServicio=tramaGenerica.substring(j+2,j+4);
						if(tramaGenerica.substring(j,j+=4).trim().equals(""))
							break;
					    cuenta.setNumeroProducto(tramaGenerica.substring(j,j+11));
					    cuenta.setTipoProducto(Constante.COD_CUENTA_PRESTAMOS);
					    cuenta.setNumeroDesembolso(tramaGenerica.substring(j+14,j+16));
					
					   
					    //cuenta.setSaldo(ObjectUtil.tramaToBigDecimal(tramaGenerica.substring(j+31,j+46),2));
					    
					    
						    
					    j+=76;
						this.mapCuentas.put(cuenta.getNumeroProducto(),(Cuenta)cuenta);
						this.cuentas.add((Cuenta)cuenta);
					}
					catch(Exception e){
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						//e.printStackTrace();
						break;
					}
				}
			}
			

		}
		
		//generarLog(t,this,(String)null);
		if(getArrayRuleException()!=null){
			throw getArrayRuleException();
		}
		//else{//////////////
			//if(cuentaPrestamo!=null && cuentaPrestamo.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
				//cuentaPrestamo.setSaldo(ObjectUtil.deFormatearMonto(this.getCuentaAhorro().getSaldo()));
				//cuentaPrestamo.setSaldoContable(ObjectUtil.deFormatearMonto(this.getCuentaAhorro().getSaldoContable()));
				//this.mapCuentas.put(cuentaPrestamo.getNumeroProducto(),(Cuenta)cuentaPrestamo);
				//this.cuentas.add((Cuenta)cuentaPrestamo);
			//}
		//}
			
		

	}

	public void validarMontoHost(Transaction t) throws Exception{
		
		Vector resultado = procesar(t);
		this.cuentas = new ArrayList();
		this.mapCuentas = new HashMap();		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			//this.cuentas = new ArrayList();
//			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
//				String tramaGenerica =  element.elementAt(6).toString();		
//				
//			
//	
//			}
	
		}
		
	
		if(getArrayRuleException()!=null){
			throw getArrayRuleException();
		}
	
	}

	/**
	 * @return Devuelve listCuentas.
	 */
	public List getListCuentas() {
		return listCuentas;
	}
	/**
	 * @param listCuentas El listCuentas a establecer.
	 */
	public void setListCuentas(List listCuentas) {
		this.listCuentas = listCuentas;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public IngresoTarjeta getIngreso() {
		return ingreso;
	}

	public void setIngreso(IngresoTarjeta ingreso) {
		this.ingreso = ingreso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFlagActualizaDato(Boolean flagActualizaDato){
		this.flagActualizaDato=flagActualizaDato;
	}
	public Boolean getFlagActualizaDato(){
		return flagActualizaDato;
	}

	public String getFlagActualizaDatoHost() {
		return flagActualizaDatoHost;
	}

	public void setFlagActualizaDatoHost(String flagActualizaDatoHost) {
		this.flagActualizaDatoHost = flagActualizaDatoHost;
	}

	public List getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public AtriTransferencia getEstadoOperaciones() {
		return estadoOperaciones;
	}

	public void setEstadoOperaciones(AtriTransferencia estadoOperaciones) {
		this.estadoOperaciones = estadoOperaciones;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	
	
	public NotificacionClave getNotificacionClave() {
		return notificacionClave;
	}

	public void setNotificacionClave(NotificacionClave notificacionClave) {
		this.notificacionClave = notificacionClave;
	}

	

	
	
}