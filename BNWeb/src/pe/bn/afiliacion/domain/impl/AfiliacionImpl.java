
package pe.bn.afiliacion.domain.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.listener.Util;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.com.bn.common.Funciones;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Afiliaciones;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AfiliacionesAliasImpl;
import pe.cosapi.domain.impl.AfiliacionesImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.dto.PersonaDTO;
import pe.cosapi.sarabank.bean.Transaction;


public class AfiliacionImpl extends OperacionImpl implements Afiliacion, Serializable {
	
	private String numSerFormat;
	private String descripcionTipoTarjeta;
	private String  tipoAfiliacion;
	private String  nroTarjeta;
	private Long  	secuencia;
	private String tipoDocumento;	
	private String  nroDocumento;
	private Timestamp  fechaNacimiento;
	private String  fechaNacimientoConv;
	private String  sexo;
	private String  email;
	private String  codigoLocalidad;
	private String  codigoServicio;
	private String  numeroServicio;
	private String  descripcion;
	private String  cuentaPropia;
	private String  beneficiario;
	private String campoAsociado;
	private String tarjetaOculta;
	
	private PersonaDTO objBenef;
	
	private CuentaImpl cuenta;
	
	private String tipoTarjeta;
	private String clave6Digitos;
	
	private String fecha;
	private String hora;
	private String flagDes;
	private Timestamp  fecDesafilia;
	private List desafiliados;
	private String descripcionTipoDocumento;
	private String descripcionCodigoServicio;
	private String ctaUob;
	
	private String codigoUsuarioSuministro;
	
	private String fechaOperacion;
	private String channel;
	
	
	private String tipoTelefono;
	private String numeroTelefono;
	private String empresa;
	private String servicio;
	private String numSuministro; 
	private String maximo;
	private String montoMaximoDebito;
	private String tipoOperacion;
	private String servidorEmail;
	private String servidorCorreo;
	private String correo;
	private List afiliaciones;
	private String montoDebito;
	private String empresaMostrar;
	private String servicioMostrar;
	private String flagEstado;
	private String indicadorContacto;
	private String tope;
	private String docMostrar;
	private String viaConfirmacion;
	private String viaConfMostrar;
	private String telContactoMostrar;
	private String telNumMostrar;
	private String montoMaxMostrar;
	
	//Se agrega para las nuevas afiliaciones frecuentes - MDB
	private String flagRegistro;
	
	
	//Campos para Banca Celular
	private String numeroCuentaDestino;
	private String tipoCuentaDestino;
	private String numeroCelularDest;
	private String operador;
	private String operadorDesc;
	private String alias;
	private String aliasOperacion;
	private String numServicio;
	private String servicioAfiliacion;
	private String numeroCelular;
	private String mostrarOperador;
	private String mostrarTipoAfil;
	private String mostrarNumAfil;
	private  List afiliados;
	private String operadorOrigen;
	
	//Campos para Datos del Cliente
	private String codCliente;
	private String telFijo;
	private String telFijoLab;
	private String anexo;
	private String telCelular;
	private String codDepartamento;
	private String codProvincia;
	private String codDistrito;
	private String numDocumento;
	private String nomCliente;
	private String nomCliente1;
	private String tipoVia;
	private String nomVia1;
	private String nomVia2;
	private String numero;
	private String lote;
	private String referencia;
	private String referencia1;
	private String correoPersonal;
	private String correoPersonal1;
	private String correoAdicional;
	private String correoAdicional1;
	private String bloque;
	private String manzana;
	private String piso;
	private String interior;
	private String discadoTelFijo;
	private String discadoTelFijoLab;
	private String direccion;
	
	//Medio de Envio
	private List direcciones;
	private List correos;
	private String codProducto;
	private String numSecDir;
	private String numItemDir;
	private String codMedioEnvio;
	private String desMedioEnvio;
	
	private String tipoDocumentoBenef;
	private String nroDocumentoBenef;
	private String flagConsentimiento;
	
	private String identificadorCCE1;
	private String identificadorCCE2;
	private String cciOrigen;
	private String codOcupacion;
	private String desOcupacion;
	
    private String fechaUltiActlzDatosCliente;
    private String canalActlzDatosCliente;
    private String flagDejaMigrar;
    private String diasParaMigrar;
	
	
	

	public String getCciOrigen() {
		return cciOrigen;
	}
	public void setCciOrigen(String cciOrigen) {
		this.cciOrigen = cciOrigen;
	}
	public String getIdentificadorCCE1() {
		return identificadorCCE1;
	}
	public void setIdentificadorCCE1(String identificadorCCE1) {
		this.identificadorCCE1 = identificadorCCE1;
	}
	public String getIdentificadorCCE2() {
		return identificadorCCE2;
	}
	public void setIdentificadorCCE2(String identificadorCCE2) {
		this.identificadorCCE2 = identificadorCCE2;
	}
	public String getFlagConsentimiento() {
		return flagConsentimiento;
	}
	public void setFlagConsentimiento(String flagConsentimiento) {
		this.flagConsentimiento = flagConsentimiento;
	}
	public String getNroDocumentoBenef() {
		return nroDocumentoBenef;
	}
	public void setNroDocumentoBenef(String nroDocumentoBenef) {
		this.nroDocumentoBenef = nroDocumentoBenef;
	}
	public String getTipoDocumentoBenef() {
		return tipoDocumentoBenef;
	}
	public void setTipoDocumentoBenef(String tipoDocumentoBenef) {
		this.tipoDocumentoBenef = tipoDocumentoBenef;
	}
	public String getDesMedioEnvio() {
		
		if(codMedioEnvio.equals(Constante.MED_ENVIO_NINGUNO)){
			desMedioEnvio = Constante.MED_ENVIO_DES_NINGUNO;
		}
		
		if(codMedioEnvio.equals(Constante.MED_ENVIO_MEDIO_ELECTRONICO)){
				desMedioEnvio = Constante.MED_ENVIO_DES_MEDIO_ELECTRONICO;
		}
		
		if(codMedioEnvio.equals(Constante.MED_ENVIO_MEDIO_FISICO)){
			desMedioEnvio = Constante.MED_ENVIO_DES_MEDIO_FISICO;
		}
		
		if(codMedioEnvio.equals(Constante.MED_ENVIO_MEDIO_ELECT_FISICO)){
			desMedioEnvio = Constante.MED_ENVIO_DES_MEDIO_ELECT_FISICO;
		}
	
		return desMedioEnvio;
	}
	public void setDesMedioEnvio(String desMedioEnvio) {
		this.desMedioEnvio = desMedioEnvio;
	}
	public List getCorreos() {
		return correos;
	}
	public void setCorreos(List correos) {
		this.correos = correos;
	}
	public String getCodMedioEnvio() {
		return codMedioEnvio;
	}
	public void setCodMedioEnvio(String codMedioEnvio) {
		this.codMedioEnvio = codMedioEnvio;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNumItemDir() {
		return numItemDir;
	}
	public void setNumItemDir(String numItemDir) {
		this.numItemDir = numItemDir;
	}
	public String getNumSecDir() {
		return numSecDir;
	}
	public void setNumSecDir(String numSecDir) {
		this.numSecDir = numSecDir;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDiscadoTelFijo() {
		return discadoTelFijo;
	}
	public void setDiscadoTelFijo(String discadoTelFijo) {
		this.discadoTelFijo = discadoTelFijo;
	}
	public String getDiscadoTelFijoLab() {
		return discadoTelFijoLab;
	}
	public void setDiscadoTelFijoLab(String discadoTelFijoLab) {
		this.discadoTelFijoLab = discadoTelFijoLab;
	}
	public String getBloque() {
		return bloque;
	}
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}
	public String getInterior() {
		return interior;
	}
	public void setInterior(String interior) {
		this.interior = interior;
	}
	public String getManzana() {
		return manzana;
	}
	public void setManzana(String manzana) {
		this.manzana = manzana;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDescripcionCodigoServicio() {
		return descripcionCodigoServicio;
	}
	public void setDescripcionCodigoServicio(String descripcionCodigoServicio) {
		this.descripcionCodigoServicio = descripcionCodigoServicio;
	}
	public String getOperadorOrigen() {
		return operadorOrigen;
	}
	public void setOperadorOrigen(String operadorOrigen) {
		this.operadorOrigen = operadorOrigen;
	}
	public List getAfiliados() {
		return afiliados;
	}
	public void setAfiliados(List afiliados) {
		this.afiliados = afiliados;
	}
	public String getMostrarNumAfil() {
		return mostrarNumAfil;
	}
	public void setMostrarNumAfil(String mostrarNumAfil) {
		this.mostrarNumAfil = mostrarNumAfil;
	}
	public String getMostrarTipoAfil() {
		return mostrarTipoAfil;
	}
	public void setMostrarTipoAfil(String mostrarTipoAfil) {
		this.mostrarTipoAfil = mostrarTipoAfil;
	}
	public String getMostrarOperador() {
		return mostrarOperador;
	}
	public void setMostrarOperador(String mostrarOperador) {
		this.mostrarOperador = mostrarOperador;
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAliasOperacion() {
		return aliasOperacion;
	}
	public void setAliasOperacion(String aliasOperacion) {
		this.aliasOperacion = aliasOperacion;
	}
	public String getNumeroCelularDest() {
		return numeroCelularDest;
	}
	public void setNumeroCelularDest(String numeroCelularDest) {
		this.numeroCelularDest = numeroCelularDest;
	}
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}
	public String getNumServicio() {
		return numServicio;
	}
	public void setNumServicio(String numServicio) {
		this.numServicio = numServicio;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	
	public String getOperadorDesc() {
		return operadorDesc;
	}
	public void setOperadorDesc(String operadorDesc) {
		this.operadorDesc = operadorDesc;
	}
	public String getServicioAfiliacion() {
		return servicioAfiliacion;
	}
	public void setServicioAfiliacion(String servicioAfiliacion) {
		this.servicioAfiliacion = servicioAfiliacion;
	}
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}
	public String getFlagRegistro() {
		return flagRegistro;
	}
	public void setFlagRegistro(String flagRegistro) {
		this.flagRegistro = flagRegistro;
	}
	public String getMontoMaxMostrar() {
		return montoMaxMostrar;
	}
	public void setMontoMaxMostrar(String montoMaxMostrar) {
		this.montoMaxMostrar = montoMaxMostrar;
	}
	/**
	 * @return Returns the ctaUob.
	 */
	public String getCtaUob() {
		return ctaUob;
	}
	/**
	 * @param ctaUob The ctaUob to set.
	 */
	public void setCtaUob(String ctaUob) {
		this.ctaUob = ctaUob;
	}
	
	//TDC

	private String  numAfiliacionTDC;
	
	//Debito Automatico
	private String codUsuarioSumi;
	
	public AfiliacionImpl() {
		super();
		objBenef = new PersonaDTO();
	}
	
	public AfiliacionImpl(Afiliacion af) {
		this.tipoAfiliacion		= af.getTipoAfiliacion();
		this.tipoTelefono      	= af.getTipoTelefono();
		this.nroTarjeta			= af.getNroTarjeta();
		this.secuencia			= af.getSecuencia();
		
		this.tipoDocumento 		= af.getTipoDocumento();
		this.nroDocumento 		= af.getNroDocumento();
		this.fechaNacimiento 	= af.getFechaNacimiento();
		this.sexo  				= af.getSexo();
		this.email 				= af.getEmail();
		this.codigoServicio 	= af.getCodigoServicio();
		this.codigoLocalidad 	= af.getCodigoLocalidad();
		this.numeroServicio 	= af.getNumeroServicio();
		this.descripcion 		= af.getDescripcion();
		this.cuentaPropia 		= af.getCuentaPropia();
		this.beneficiario 		= af.getBeneficiario();
		
		this.objBenef 			= af.getObjBenef();
		this.tipoTarjeta		= af.getTipoTarjeta();
		this.clave6Digitos		= af.getClave6Digitos();
		this.flagDes			= "A";

		this.codigoUsuarioSuministro 		= af.getCodigoUsuarioSuministro();
		
		//System.out.println("af.getTipoTelefono()::::"+af.getTipoTelefono());
		
		
		this.numeroTelefono 		= af.getNumeroTelefono();
		this.empresa				= af.getEmpresa();
		this.servicio				= af.getServicio();
		this.numSuministro 			= af.getNumSuministro();
		this.maximo 				= af.getMaximo();
		this.montoMaximoDebito 		= af.getMontoMaximoDebito ();
		this.tipoOperacion 			= af.getTipoOperacion ();
		this.servidorEmail 			= af.getServidorEmail();
		
		this.tipoDocumentoBenef 	= af.getTipoDocumentoBenef();
		this.nroDocumentoBenef 		= af.getNroDocumentoBenef();
		
	}
	
	public void cargar()throws Exception{
		AfiliacionImpl s = DAOFactory.getAfiliacionDAO().getAfiliacion(this);
		
		this.tipoAfiliacion		= s.tipoAfiliacion;
		this.nroTarjeta			= s.nroTarjeta;
		this.secuencia			= s.secuencia;
		this.campoAsociado 		= s.campoAsociado;
		
		this.tipoDocumento 		= s.tipoDocumento;
		this.nroDocumento 		= s.nroDocumento;
		this.fechaNacimiento 	= s.fechaNacimiento;
		this.sexo  				= s.sexo;
		this.email 				= s.email;
		this.codigoLocalidad 	= s.codigoLocalidad;
		this.codigoServicio 	= s.codigoServicio;
		this.numeroServicio 	= s.numeroServicio;
		this.descripcion 		= s.descripcion;
		this.cuentaPropia 		= s.cuentaPropia;
		this.beneficiario 		= s.beneficiario;
		this.tipoTarjeta 		= s.tipoTarjeta;
		this.objBenef 			= s.objBenef;
		
		this.codigoUsuarioSuministro  	= s.codigoUsuarioSuministro;
		
		this.tipoTelefono      		= s.tipoTelefono;
		this.numeroTelefono 		= s.numeroTelefono;
		this.empresa				= s.empresa;
		this.servicio				= s.servicio;
		this.numSuministro 			= s.numSuministro;
		this.maximo 				= s.maximo;
		this.montoMaximoDebito 		= s.montoMaximoDebito;
		this.tipoOperacion 			= s.tipoOperacion;
		this.servidorEmail 			= s.servidorEmail;
		
		this.tipoDocumentoBenef 	= s.tipoDocumentoBenef;
		
		this.nroDocumentoBenef 		= s.nroDocumentoBenef;
		
		
		
		ComboUtil c1 = new ComboUtil();
		ComboUtil c2 = new ComboUtil();
		
		if (this.codigoServicio != null && Constante.AFI_OTRO_BANCO.equals(this.tipoAfiliacion)){
		    c1 = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.COD_HLP_BANCO,codigoServicio);
		    this.objBenef.setObjBanco(c1);
		}
		
		if (this.codigoServicio != null && Constante.AFI_TRA_INTER_BANCO.equals(this.tipoAfiliacion)){
		    c1 = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.COD_HLP_BANCO,codigoServicio);
		    this.objBenef = new PersonaDTO();
		    this.objBenef.setNombre(this.beneficiario);
		    this.objBenef.setObjBanco(c1);
		}
		
		if (this.tipoTarjeta != null && Constante.AFI_OTRO_BANCO.equals(this.tipoAfiliacion)){
		    c2 = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.COD_HLP_TIP_TARJETA,this.tipoTarjeta);
		    this.objBenef.setCodTipoTarjeta(c2.getCodigo());
		    this.objBenef.setDesTipoTarjeta(c2.getDescripcion());
		    this.objBenef.setObjTipoTarjeta(c2);
		}
		
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;
		Date data;

		formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = new Date(fechaActual.getTime());
		this.fecha = formatter.format(data);

		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		this.hora = formatter.format(data);
		
	}
	
	public void afiliar() throws Exception{
		DAOFactory.getAfiliacionDAO().afiliar(this);
	}
	
	public AfiliacionImpl(String tipoAfiliacion, String nroTarjeta, Long nroSecuencia, String codigoAyuda) throws Exception{
		this.tipoAfiliacion	= tipoAfiliacion;
		
		this.nroTarjeta		= nroTarjeta;
		
		this.secuencia		= nroSecuencia;
		
		this.campoAsociado 	= codigoAyuda;
		
		AfiliacionImpl s = DAOFactory.getAfiliacionDAO().getAfiliacion(this);
	
		this.tipoDocumento = s.tipoDocumento;
		this.nroDocumento = s.nroDocumento;
		this.tipoDocumentoBenef = s.tipoDocumentoBenef;
		this.nroDocumentoBenef = s.nroDocumentoBenef;
		this.fechaNacimiento = s.fechaNacimiento;
		this.sexo  = s.sexo;
		this.email = s.email;
		this.codigoLocalidad = s.codigoLocalidad;
		this.codigoServicio = s.codigoServicio;
		this.numeroServicio = s.numeroServicio;
		this.descripcion = s.descripcion;
		this.cuentaPropia = s.cuentaPropia;
		this.beneficiario = s.beneficiario;
		this.objBenef = s.getObjBenef();
		
		this.codigoUsuarioSuministro  = s.codigoUsuarioSuministro;
	
		if(!Constante.AFI_TELEGIRO.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_TEL.equals(this.tipoAfiliacion) 
		   && !Constante.PAGO_MOV.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_CMA.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_TER.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_SED.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_CUP.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_FAC.equals(this.tipoAfiliacion)
		   && !Constante.RECARGA_CLA.equals(this.tipoAfiliacion)
		   && !Constante.PAGO_TEL_CLARO.equals(this.tipoAfiliacion)
		   && !Constante.RECARGA_MOV.equals(this.tipoAfiliacion)
		   && !"0".equals(this.tipoAfiliacion.substring(0, 1))
		   ){
		    
			CuentaImpl cuenta = new CuentaImpl();
			if(s.codigoServicio != null)
			{cuenta.setTipoProducto(s.codigoServicio.trim());}
			cuenta.setNumeroProducto(s.numeroServicio.trim());
			
			this.cuenta = cuenta;
		}
		
		this.tipoTarjeta 		= s.tipoTarjeta;
		this.flagDes			= "A";
		
		
		ComboUtil c1 = new ComboUtil();
		ComboUtil c2 = new ComboUtil();
		
		if (this.codigoServicio != null && Constante.AFI_OTRO_BANCO.equals(this.tipoAfiliacion)){
		    c1 = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.COD_HLP_BANCO,codigoServicio);
		    this.objBenef.setObjBanco(c1);
		}
		
		if (this.codigoServicio != null && Constante.AFI_TRA_INTER_BANCO.equals(this.tipoAfiliacion)){
		    c1 = DAOFactory.getGeneraDAO().getObjDetalleHlp("00460",codigoServicio);
		    this.objBenef = new PersonaDTO();
		    this.objBenef.setNombre(this.beneficiario);
		    this.objBenef.setObjBanco(c1);
		}
		
		if (this.tipoTarjeta != null && Constante.AFI_OTRO_BANCO.equals(this.tipoAfiliacion)){
		    c2 = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.COD_HLP_TIP_TARJETA,this.tipoTarjeta);
		    this.objBenef.setObjTipoTarjeta(c2);
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public List validacionCuentas(Transaction t,Vector datos, Vector cuentas) throws Exception{
	
		Vector resultado = procesar1(t);
		this.afiliaciones = new ArrayList();
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
		
		}
		
		
		t.setObjeto(this);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();	
		
		return afiliaciones;
		
	}
	

	
	public void getAfiliaDA(Transaction t,Vector datos, Vector cuentas, Usuario usuario) throws Exception{

		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
		
				this.setCuentaPropia(element.elementAt(6).toString());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setNroOperacion(element.elementAt(6).toString());
		  }
		}
		
		this.setTipoAfiliacion(Constante.AFI_DEBITO_AUTOMATICO);
		
		t.setObjeto(this);
		generarLog(t,usuario,Constante.REFRENDO_AFILIACION_DEBITO_AUTOMATICO);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
	}
	
	public List getConsultaDA(String transaccion,Vector datos, Vector cuentas) throws Exception{
		Transaction t = new Transaction(transaccion);
		t.setValues(datos);
		t.setCuentas(cuentas);		
		
	
		Vector resultado = procesar1(t);
		this.afiliaciones = new ArrayList();
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			AfiliacionesImpl afil = new AfiliacionesImpl();
			
			
			if(element.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
			    if (element.elementAt(6)!= null){
					String tramaGenerica =  element.elementAt(6).toString();
															
					for(int j = 0; j< tramaGenerica.length();){
						
					    AfiliacionesImpl recibo = new AfiliacionesImpl();
					  
					    if (j+80 <= tramaGenerica.length()){
			                String tramita = tramaGenerica.substring(j,j=j+95);
			                
			          			              
			                if ((!tramita.trim().equals(""))){

				                try{
				                					                	
				                	String txtEntidad  = tramita.substring(0,2); 
				                	String txtServicio = tramita.substring(2,6);
				                	String txtFlag	   = tramita.substring(6,7);
					                String txtSuministro  = tramita.substring(7,22);
					                String txtTipoDoc  = tramita.substring(22,23);
					                String txtNroDoc  = tramita.substring(23,38);
					                String txtTipoTel  = tramita.substring(38,39);
					                String txtNroTel  = tramita.substring(39,49);
					                String txtEmail  = tramita.substring(49,79);
					                String txtVia  = tramita.substring(79,80);
					                String txtFecha  = tramita.substring(80,88);
					                String txtTope  = tramita.substring(88,89);
					                String txtMonto  = tramita.substring(89,95);
					                
					                
					                recibo.setEmpresa(txtEntidad);
					                recibo.setServicio(txtServicio);
					                recibo.setNumSuministro(txtSuministro);
					                recibo.setFlagEstado(txtFlag);
					                recibo.setTipoDoc(txtTipoDoc);
					                recibo.setNroDoc(txtNroDoc);
					                recibo.setTipoTel(txtTipoTel);
					                recibo.setNroTel(txtNroTel);
					                recibo.setEmail(txtEmail);
					                recibo.setVia(txtVia);
					                recibo.setFecha(ObjectUtil.getYYYYMMDDFormateada(txtFecha));
					                recibo.setTope(txtTope);	
					                recibo.setMaximo(""+ObjectUtil.tramaToBigDecimal(txtMonto,2));
					               
					            	 this.afiliaciones.add((Afiliaciones)recibo);
					           
				                    
				                }catch (Exception e) {
				                	e.printStackTrace();
	                                break;
	                            
					    	}
			              }
					    }    

					}
					
			    }
			}
			
		
			
		}
		
		
		t.setObjeto(this);
		this.setTipoAfiliacion(Constante.CONSULT_DEBITO_AUTOMATICO);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();	
		
		return afiliaciones;
		
	}
	
	public void getDesafiliacionDA(Transaction t,Vector datos, Vector cuentas,Usuario usuario) throws Exception{
	
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setCuentaPropia(element.elementAt(6).toString());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setNroOperacion(element.elementAt(6).toString());
		  }
		}
		
				
		this.setTipoAfiliacion(Constante.DESAFI_DEBITO_AUTOMATICO);
		t.setObjeto(this);
		if(this.flagEstado.equals("8")){
			
			generarLog(t,usuario,Constante.REFRENDO_ANULACION_DEBITO_AUTOMATICO);
		}
		else{
			generarLog(t,usuario,Constante.REFRENDO_DESAFILIACION_DEBITO_AUTOMATICO);
		}
	
		
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
				
	}
	
	public void getModificacionDA(Transaction t,Vector datos, Vector cuentas,Usuario usuario) throws Exception{
		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setCuentaPropia(element.elementAt(6).toString());
			}
			
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.setNroOperacion(element.elementAt(6).toString());
		  }
		}
		
			
		this.setTipoAfiliacion(Constante.MOD_DEBITO_AUTOMATICO);
		t.setObjeto(this);
	
		generarLog(t,usuario,Constante.REFRENDO_MODIFICACION_DEBITO_AUTOMATICO);
	
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
				
	}
	
	//Banca Celular
	
	public List validacionBancaCel(Transaction t,Vector datos, Vector cuentas) throws Exception{
		
		Vector resultado = procesar1(t);
		this.afiliaciones = new ArrayList();
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
		
		}
		
		
		t.setObjeto(this);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();	
		
		return afiliaciones;
		
	}
	
public void consultaCelularBancaCel(Transaction t,Vector datos, Vector cuentas) throws Exception{
		
		Vector resultado = procesar1(t);
		this.afiliaciones = new ArrayList();
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				
				this.numeroCelular = element.elementAt(6).toString().trim();
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.alias = element.elementAt(6).toString().trim();
			
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				this.correo = element.elementAt(6).toString().trim();
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
				this.servidorCorreo = element.elementAt(6).toString().trim();
			}
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
				this.operador = ""+Integer.parseInt(element.elementAt(6).toString().trim());
			}
		}
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();		
		
	}
	
	
	public List consultaAliasBancaCel(String  transaccion,Vector datos, Vector cuentas) throws Exception{
		
		Transaction t = new Transaction(transaccion);
		t.setValues(datos);
		t.setCuentas(cuentas);		
		
	
		Vector resultado = procesar1(t);
		this.afiliaciones = new ArrayList();
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			AfiliacionesImpl afil = new AfiliacionesImpl();
			
		
			if(element.elementAt(0).equals(Constante.OUTPUT_STRING_1)){
				
			    if (element.elementAt(6)!= null){
			    	
					String tramaGenerica =  element.elementAt(6).toString();
															
					for(int j = 0; j< tramaGenerica.length();){
						
						AfiliacionesAliasImpl  consulta  = new AfiliacionesAliasImpl ();
					  
					    if (j+80 <= tramaGenerica.length()){
			                String tramita = tramaGenerica.substring(j,j=j+80);
			                
			          			              
			                if ((!tramita.trim().equals(""))){

				                try{
				                					                	
				                   	String txtAlias  = tramita.substring(0,4); 
				                	String txtProducto = tramita.substring(4,6);
				                	String txtMoneda   = tramita.substring(6,9);
					                String txtNumCuenta  = tramita.substring(9,20);
					                String txtServicio	 = tramita.substring(20,25);
					                String txtAliasServicio	 = tramita.substring(25,30);
					                String txtNumeroRef	 = 	tramita.substring(30,50);

					                consulta.setAlias(txtAlias);
					                consulta.setProducto(txtProducto);
					                consulta.setMoneda(txtMoneda);
					                consulta.setNumCuenta(txtNumCuenta);
					                consulta.setServicio(txtServicio.trim());
					                consulta.setAliasServicio(txtAliasServicio);
					              
					                
					                					         
					                if(!consulta.getServicio().trim().equals("") && !consulta.getServicio().trim().equals(Constante.BAN_CELULAR_CONSULTA_MOV) && !consulta.getServicio().trim().equals(Constante.BAN_CELULAR_CONSULTA_SALDO)){
					                	  if(!txtNumeroRef.trim().equals("")){;
					                	  		if(consulta.getServicio().trim().equals(Constante.BAN_CELULAR_TIPO_AFIL_TRANS)){
					                	  			consulta.setNumReferencia(ObjectUtil.formatearCuenta(Funciones.lpad(txtNumeroRef.trim(),"0",11),Constante.FORMATO_CUENTA));
					                	  		}
					                	
					                	  		else{
					                	  			
					                	  		
					                	  			//if(consulta.getServicio().trim().equals(Constante.BAN_CELULAR_RECARGA_CLARO) || consulta.getServicio().trim().equals(Constante.BAN_CELULAR_RECARGA_MOVISTAR) ){
					                	  			if(consulta.getServicio().trim().equals(Constante.BAN_CELULAR_TIPO_AFIL_RECA)  ){
					                	  				consulta.setNumReferencia(""+Long.parseLong(txtNumeroRef.trim()));
					                	  			}
					                	  			else{
					                	  				consulta.setNumReferencia(txtNumeroRef.trim());
					                	  			}
					                	  		
					                	  		}
					                	  }
							             
					                	  else{consulta.setNumReferencia(Constante.BAN_CELULAR_SIN_DESCRIPCION);}
					 
					               	 this.afiliaciones.add(consulta);
					                
					                }
					        
				                }catch (Exception e) {
				                	e.printStackTrace();
	                                break;
	                            
					    	}
			              }
					    }    

					}
					
			    }
			
			
				}
			
			}
		
		
		
		
		t.setObjeto(this);
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();	
		
		return afiliaciones;
		
	}
	
	public void getAfiliaBancaCel(Transaction t,Vector datos, Vector cuentas, Usuario usuario) throws Exception{

		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
						
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNroOperacion(element.elementAt(6).toString());
		  }
		}


		t.setObjeto(this);
		
		this.setTipoAfiliacion(Constante.AFILIA_BANCA_CELULAR);
		
		
		if(this.servicioAfiliacion.equals(Constante.BAN_CELULAR_TIPO_AFIL_TRANS)){
			generarLog(t,usuario,Constante.REFRENDO_AFILIACION_BANCA_CEL_TRANS);	
		}
		if(this.servicioAfiliacion.equals(Constante.BAN_CELULAR_RECARGA_CLARO)||this.servicioAfiliacion.equals(Constante.BAN_CELULAR_RECARGA_MOVISTAR)){
			generarLog(t,usuario,Constante.REFRENDO_AFILIACION_BANCA_CEL_RECA);		
		}
		if(this.servicioAfiliacion.equals(Constante.BAN_CELULAR_RECIBO_SEDAPAL)||this.servicioAfiliacion.equals(Constante.BAN_CELULAR_TV_MOVISTAR) || this.servicioAfiliacion.equals(Constante.BAN_CELULAR_RECIBO_CLARO) || this.servicioAfiliacion.equals(Constante.BAN_CELULAR_RECIBO_MOVISTAR)||this.servicioAfiliacion.equals(Constante.BAN_CELULAR_TELEFONIA_FIJA_MOVISTAR)){
			generarLog(t,usuario,Constante.REFRENDO_AFILIACION_BANCA_CEL_SERV);	
		}
		if(this.servicioAfiliacion.equals(Constante.BAN_CELULAR_TIPO_AFIL_TASA)){
			generarLog(t,usuario,Constante.REFRENDO_AFILIACION_BANCA_CEL_TASA);		
		}
		
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
	}
	
	public void getDesafiliacionBancaCel(Transaction t,Vector datos, Vector cuentas,Usuario usuario) throws Exception{
		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNroOperacion(element.elementAt(6).toString());
		  }
		}
		
				
	
		t.setObjeto(this);
		this.setTipoAfiliacion(Constante.DESAFILIA_BANCA_CELULAR);
		generarLog(t,usuario,Constante.REFRENDO_DESAFILIACION_BANCA_CEL);
	
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
				
	}
	
public void getVinculacionBancaCel(Transaction t,Vector datos, Vector cuentas,Usuario usuario) throws Exception{
		
		Vector resultado = procesar(t);
		
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector element = (Vector) iter.next();
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
				this.setNroOperacion(element.elementAt(6).toString());
				
		  }
			if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
				this.setAlias(element.elementAt(6).toString());
				
		  }
			
		}
						
	
		t.setObjeto(this);
		this.setTipoAfiliacion(Constante.VINCULA_BANCA_CELULAR);
		
		
		if(this.tipoOperacion.equals(Constante.BAN_CELULAR_VINCULACION)){
		
			generarLog(t,usuario,Constante.REFRENDO_VINCULACION_BANCA_CEL);	
		}
		
		if(this.tipoOperacion.equals(Constante.BAN_CELULAR_DESVINCULACION)){
		
			generarLog(t,usuario,Constante.REFRENDO_DESVINCULACION_BANCA_CEL);	
		}
		
		
		//generarLog(t,usuario,Constante.REFRENDO_DESAFILIACION_BANCA_CEL);
	
		if(getArrayRuleException()!=null)
			throw getArrayRuleException();
		
				
	}


//Datos del Cliente

public void getConsultaDatosCliente(Transaction t,Vector datos, Vector cuentas) throws Exception{
	
	Vector resultado = procesar1(t);
	this.afiliaciones = new ArrayList();
	
    Date fechaActual = new Date();
    String fechaHost  = "";        
    SimpleDateFormat formatoHost = new SimpleDateFormat("yyyyMMdd");
    Date fechaHostDate = null;    
    int diasTranscurridos=0;
	
	for (Iterator iter = resultado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			this.setTipoDocumento(element.elementAt(6).toString());
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			this.setCodCliente(element.elementAt(6).toString());
		
	  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			this.setTelFijo(ObjectUtil.tramaToString1(element.elementAt(6).toString()));
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			this.setTelFijoLab(ObjectUtil.tramaToString1(element.elementAt(6).toString()));
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			this.setAnexo(ObjectUtil.tramaToString(element.elementAt(6).toString()));
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			this.setTelCelular(ObjectUtil.tramaToString1(element.elementAt(6).toString()));
	
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
			this.setCodDepartamento(element.elementAt(6).toString());
		
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
			this.setCodProvincia(element.elementAt(6).toString());
			
	  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
			this.setCodDistrito(element.elementAt(6).toString());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
			this.setNumDocumento(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
			this.setNomCliente(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
			this.setNomCliente1(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){
			this.setTipoVia(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_14)){
			this.setNomVia1(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_15)){
			this.setNomVia2(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_16)){
			this.setNumero(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_17)){
			this.setBloque(element.elementAt(6).toString().trim());
			
	  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_18)){
			this.setManzana(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_19)){
			this.setLote(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_20)){
			this.setPiso(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_21)){
			this.setInterior(element.elementAt(6).toString().trim());
			
	  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_22)){
			this.setReferencia(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_23)){
			
			this.setReferencia1(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_24)){
			
			this.setCorreoPersonal(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_25)){
		
		this.setCorreoAdicional(element.elementAt(6).toString().trim());
		
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_26)){
			
			this.setFlagEstado(element.elementAt(6).toString().trim());
			
	  }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_27)){
			
			this.setDiscadoTelFijo(element.elementAt(6).toString().trim());
			
	  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_28)){
			
			this.setDiscadoTelFijoLab(element.elementAt(6).toString().trim());
			
	  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_29)){
				
				this.setFlagConsentimiento(element.elementAt(6).toString().trim());
				
		  }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_30)){
			this.setCodOcupacion(element.elementAt(6).toString());
		
		}
		
        if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_31)){
            fechaActual = new Date();        
            fechaHost  = element.elementAt(6).toString().trim();        
            fechaHostDate = formatoHost.parse(fechaHost);
            formatoHost = new SimpleDateFormat("yyyyMMdd");
            diasTranscurridos=(int) ((fechaActual.getTime()-fechaHostDate.getTime())/86400000);
            
            
            
            this.setFechaUltiActlzDatosCliente(element.elementAt(6).toString().trim());
            this.setDiasParaMigrar(String.valueOf(diasTranscurridos));
            
        }
        
	      if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_32)){
	            this.setCanalActlzDatosCliente(element.elementAt(6).toString().trim());
	      }
	        
      
        
		
	}
	

	int diasParameters = Integer.parseInt(FacadeFactory.getGeneralFacade().getDiasMigracion(Constante.TC_BN_FLAG_DIAS_MIGRACION));
	if(fechaHost.equals("00000000")){
		this.setFlagDejaMigrar("1");
		this.setDiasParaMigrar(String.valueOf(diasParameters));
	}else{
		if( this.getCanalActlzDatosCliente() != null && (this.getCanalActlzDatosCliente().equals("SABM") || 
	        this.getCanalActlzDatosCliente().equals("ATI1")) &&
		         (diasTranscurridos<diasParameters) ) {
			this.setFlagDejaMigrar("0");//No deja migrar
			int diasTransc = diasParameters - diasTranscurridos;
			this.setDiasParaMigrar(String.valueOf(diasTransc));
		}else{
			this.setFlagDejaMigrar("1"); //me deja migrar
			this.setDiasParaMigrar(String.valueOf(diasParameters));
		}
	}    
	
	t.setObjeto(this);
	
	
	
	if(getArrayRuleException()!=null)
		throw getArrayRuleException();	
		
	
}

public void getActualizaDatosCliente(Transaction t,Vector datos, Vector cuentas, Usuario usuario, Afiliacion afil) throws Exception{
	
	Vector resultado = procesar(t);
	
	
	for (Iterator iter = resultado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			this.setNroOperacion(element.elementAt(6).toString());
		 }
			
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			this.setTelFijo(ObjectUtil.convierteDiscadoTel(ObjectUtil.tramaEnBlanco(element.elementAt(6).toString())));
			
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
		
			String celular = ObjectUtil.tramaEnBlanco(element.elementAt(6).toString().trim());
		
			this.setTelCelular(celular);
			
			if(this.getTelCelular()!= null){
				int datosCelular = this.getTelCelular().trim().length();
				
				if(datosCelular>Constante.DAT_CLIENTES_LONG_CELULAR){
					this.setOperador(celular.substring(0,1));
					this.setTelCelular(celular.substring(1));
				
				
				}
				else{
					this.setOperador(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO);
					this.setTelCelular(celular);
				}
			}
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			this.setAnexo(ObjectUtil.tramaEnBlanco(element.elementAt(6).toString()));
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			this.setTelFijoLab(ObjectUtil.convierteDiscadoTel(ObjectUtil.tramaEnBlanco(element.elementAt(6).toString())));
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
			this.setTipoDocumento(element.elementAt(6).toString().trim());
			
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
			
				if(this.getTipoDocumento().equals(Constante.NOMBRE_DNI)){
					
					String dni=element.elementAt(6).toString().substring(4);
					this.setNumDocumento(dni);
				}else{
					if(this.getTipoDocumento().equals(Constante.NOMBRE_RUC)){
						String ruc=element.elementAt(6).toString().substring(1);
						this.setNumDocumento(ruc);
					}
					else{
						
						this.setNumDocumento(element.elementAt(6).toString().trim());
					}
					
				}
				
		 }
	
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
			this.setDireccion(element.elementAt(6).toString().toUpperCase());
		 }
	
	}
	
	this.setTipoAfiliacion(Constante.DAT_CLIENTES_ACTUALIZACION);
	t.setObjeto(this);
	
		
	if(afil.getFlagConsentimiento().equals("1")){
		generarLog(t,usuario,Constante.REFRENDO_ACTUALIZACION_DATOS_CLIENTE_ACEPTA);
	}else{
		generarLog(t,usuario,Constante.REFRENDO_ACTUALIZACION_DATOS_CLIENTE_NO_ACEPTA);
	}
	
	
	if(getArrayRuleException()!=null)
		throw getArrayRuleException();	
		
	
}

public void getActualizaCelularCliente(Transaction t,Vector datos, Vector cuentas, Usuario usuario, String celular, String nombreOperador) throws Exception{
	
	Vector resultado = procesar(t);
	
	for (Iterator iter = resultado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			this.setNroOperacion(element.elementAt(6).toString());
		 }
			
	
		this.setTelCelular(celular);
		this.setMostrarOperador(nombreOperador);
			
		if(this.getTelCelular()!= null){
				int datosCelular = this.getTelCelular().trim().length();
				
				if(datosCelular>Constante.DAT_CLIENTES_LONG_CELULAR){
					this.setOperador(celular.substring(0,1));
					this.setTelCelular(celular.substring(1));
				
				
				}
			else{
					this.setOperador(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO);
					this.setTelCelular(celular);
				}
			}
		 
		
		String tipoDoc="";
		String numDoc="";
					
					
		if(usuario.getCodigoCLDI() != null){
						tipoDoc=usuario.getCodigoCLDI().substring(12,13);
						numDoc=usuario.getCodigoCLDI().substring(13);	
						if(tipoDoc != "1"){
							numDoc=Util.removeZero(numDoc);
						}else{
							numDoc= numDoc.substring(4);
						}
			
		}
		
		
		this.setTipoDocumento(NomTipoDoc(Funciones.lpad(tipoDoc,"0",3), Constante.COD_HLP_DET_DOCU_GIRO));
		this.setNumDocumento(numDoc);
	
	
	}

	t.setObjeto(this);
	
	this.setTipoAfiliacion(Constante.DAT_CLIENTES_ACTUALIZACION_CEL);
	generarLog(t,usuario,Constante.REFRENDO_ACTUALIZACION_DATOS_CLIENTE_CEL);
	
	
	if(getArrayRuleException()!=null)
		throw getArrayRuleException();	
		
	
}

public void getConsultaMedioEnvio(Transaction t,Vector datos, Vector cuentas) throws Exception{
	
	Vector resultado = procesar1(t);
	this.direcciones = new ArrayList();
	this.correos = new ArrayList();
	
	for (Iterator iter = resultado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
			//System.out.println("this.setCodCliente:"+this.getCodCliente());
			this.setCodCliente(element.elementAt(6).toString());
			
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			this.setCodProducto(element.elementAt(6).toString());
			
		}
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
			this.setNumSecDir(element.elementAt(6).toString());
			
		}
				
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			this.setNumItemDir(element.elementAt(6).toString());
			
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			this.setCodMedioEnvio(element.elementAt(6).toString());
			
		}
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			this.setCorreoPersonal(element.elementAt(6).toString());
			
			  if (element.elementAt(6)!= null){
					String tramaCorreo =  element.elementAt(6).toString();
					for(int j = 0; j< tramaCorreo.length();){
						  
						    if (j+60 <= tramaCorreo.length()){
						    	
						    	AfiliacionesImpl correo = new AfiliacionesImpl();
						    	
				                String tramita = tramaCorreo.substring(j,j=j+60);
				             
					                try{
					                
					                    if ((!tramita.trim().equals(""))){
					                    	
					                    	String correos = tramita.substring(0,60);
					                    	
					                    
					                    	correo.setEmail(correos.trim().toLowerCase());
					         
					                    	this.correos.add(correo);
					                    }
					                    
					                }catch (Exception e) {
					                	e.printStackTrace();
					               
		                                break;
		                            }
						    }	
					    }
					 }
		 }
		

		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
		    if (element.elementAt(6)!= null){
				String tramaGenerica =  element.elementAt(6).toString();

				for(int j = 0; j< tramaGenerica.length();){
				  
				    if (j+120 <= tramaGenerica.length()){
				    	AfiliacionesImpl direccion = new AfiliacionesImpl();
				    	
		                String tramita = tramaGenerica.substring(j,j=j+120);
		            
			                try{
			             
			                    if ((!tramita.trim().equals("")) && !tramita.trim().substring(0,3).equals("000")){
			                    
			                 
			                	String txtSecuencia    = tramita.substring(0,3);
			                	String txtTipoDir     = tramita.substring(3,5); 
			                	String txtItemDir  = tramita.substring(5,10);
				                String txtDireccion    = tramita.substring(10,120);

				                
				                direccion.setSecuencia(txtSecuencia);
				                direccion.setTipoDireccion(txtTipoDir);
				                direccion.setItemDireccion(txtItemDir);
				                direccion.setDireccion(ObjectUtil.convierteMayusculaPriLetra(txtDireccion));
				                
				                	
				                this.direcciones.add(direccion);	
				                	
				                
				                	
			                    }
			                    
			                  	
				                
			                }catch (Exception e) {
			                	e.printStackTrace();
			                	//log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"Consulta_Pago_Online");
                                break;
                            }
				    	
				    }    
		     
				}
				
		    }
		 }
		
	
		
	}
	
	
	t.setObjeto(this);
	
	
	
	if(getArrayRuleException()!=null)
		throw getArrayRuleException();	
		
	
}

public void getGuardarMedioEnvio(Transaction t,Vector datos, Vector cuentas) throws Exception{
	
	Vector resultado = procesar1(t);
	
	
	for (Iterator iter = resultado.iterator(); iter.hasNext();) {
		Vector element = (Vector) iter.next();
		
				
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
			this.setNroOperacion(element.elementAt(6).toString());
		 }

		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
			this.setDireccion(ObjectUtil.convierteMayusculaPriLetra(element.elementAt(6).toString().trim()));
		 }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
						
			String correo = element.elementAt(6).toString().trim();
			
			if(correo.equals("")){
				this.setCorreo(Constante.MED_ENVIO_SIN_DESCRIPCION);
			}else{
				this.setCorreo(correo.toLowerCase());
			}
			
						
		 }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
			this.setCodMedioEnvio(element.elementAt(6).toString().trim());
		 }
		
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
			this.setTipoDocumento(""+Integer.parseInt(element.elementAt(6).toString().trim()));
			
		 }
		if(element.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
			
				if(Integer.parseInt(this.getTipoDocumento())==Constante.TIPO_DOCUMENTO_DNI){
				
					String dni=element.elementAt(6).toString().substring(4);
					this.setNumDocumento(dni);
				}else{
					if(this.getTipoDocumento().equals(Constante.NOMBRE_RUC)){
						String ruc=element.elementAt(6).toString().substring(1);
						this.setNumDocumento(ruc);
					}
					else{
						
						this.setNumDocumento(element.elementAt(6).toString().trim());
					}
					
				}
				
		 }
	
	}
	
	
	
	
	t.setObjeto(this);
	
	
	
	if(getArrayRuleException()!=null)
		throw getArrayRuleException();	
		
	
}
	
    public String getClave6Digitos() {
        return clave6Digitos;
    }
    public void setClave6Digitos(String clave6Digitos) {
        this.clave6Digitos = clave6Digitos;
    }
    
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
	public CuentaImpl getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(CuentaImpl cuenta) {
		this.cuenta = cuenta;
	}
	
	public void desafiliar() throws Exception{
		DAOFactory.getAfiliacionDAO().desafiliar(this);
	}
	
	public void desafiliarDebito() throws Exception{
		DAOFactory.getAfiliacionDAO().desafiliarDebito(this);
	}
	
	public Long getMaximaSecuencia(String tipAfiliacion, String numTarjeta) throws Exception{
		return DAOFactory.getAfiliacionDAO().getMaximaSecuencia(tipAfiliacion,numTarjeta);
	}
	
	
	public List getDesafiliados() {
		return desafiliados;
	}
	
	public void setDesafiliados(List desafiliados) {
		this.desafiliados = desafiliados;
	}
	
	
	public String getCodigoLocalidad() {
		return codigoLocalidad;
	}
	
	public void setCodigoLocalidad(String codigoLocalidad) {
		this.codigoLocalidad = codigoLocalidad;
	}
	
	public String getCodigoServicio() {
		return codigoServicio;
	}
	
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	
	public String getCuentaPropia() {
		return cuentaPropia;
	}
	
	public void setCuentaPropia(String cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNroDocumento() {
		return nroDocumento;
	}
	
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	public String getNumeroServicio() {
		return numeroServicio;
	}
	
	public void setNumeroServicio(String numeroServicio) {
		this.numeroServicio = numeroServicio;
	}
	
	public Long getSecuencia() {
		return secuencia;
	}
	
	public void setSecuencia(Long secuencia) {
		this.secuencia = secuencia;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public List getAfiliaciones() throws Exception{
		return DAOFactory.getAfiliacionDAO().getAfiliaciones(this);
	}
	
	public List getAfiliaExiste() throws Exception{
		return DAOFactory.getAfiliacionDAO().getAfiliaExiste(this);
	}
	
	public List getAfiliaExisteDebito() throws Exception{
		return DAOFactory.getAfiliacionDAO().getAfiliaExisteDebito(this);
	}
	
	public List getAfiliacionByValues(String codigoAyuda,String tipoAfiliacion,String numTarjeta) throws Exception{
		return DAOFactory.getAfiliacionDAO().getAfiliacionByValues(codigoAyuda,tipoAfiliacion,numTarjeta);
	}
	
	
	public String getBeneficiario() {
		return beneficiario;
	}
	
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getTipoAfiliacion() {
		return tipoAfiliacion;
	}

	public void setTipoAfiliacion(String tipoAfiliacion) {
		this.tipoAfiliacion = tipoAfiliacion;
	}


	
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}
	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento= descripcionTipoDocumento;
	}



	public String getCampoAsociado() {
		return campoAsociado;
	}

	public void setCampoAsociado(String campoAsociado) {
		this.campoAsociado = campoAsociado;
	}
	
	public PersonaDTO getObjBenef() {
		return objBenef;
	}
	public void setObjBenef(PersonaDTO objBenef) {
		this.objBenef = objBenef;
	}
	
	public String getTarjetaOtroBancoFormateada(){
	    if (this.numeroServicio != null)	    	
	        return ObjectUtil.formatearCuenta(this.numeroServicio,Constante.FORMATO_TARJETA);
	    return "";
	}
	
	public String getTarjetaFormateada(){
	    if (this.nroTarjeta != null)
	        return ObjectUtil.formatearCuenta(this.nroTarjeta,Constante.FORMATO_TARJETA);
	    return "";
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
    

    public String getFlagDes() {
        return flagDes;
    }
    public void setFlagDes(String flagDes) {
        this.flagDes = flagDes;
    }
    

    public Timestamp getFecDesafilia() {
        return fecDesafilia;
    }
    public void setFecDesafilia(Timestamp fecDesafilia) {
        this.fecDesafilia = fecDesafilia;
    }
    
	public String getCuentaFormateada(){
	    if (this.numeroServicio != null && !this.numeroServicio.equals("")){
	    	if ( this.tipoAfiliacion != "TRIL")	  {
	    		return this.numeroServicio.trim();
	    	} else{ 	
	        
	    		return ObjectUtil.formatearCuenta(this.numeroServicio.trim(),Constante.FORMATO_CUENTA);}
	    
	    }
	    return "";
	}
	
	
	public String getCodigoServicioCuenta(){
		if(this.codigoServicio==null)
			return "DESCONOCIDO";
		if(this.codigoServicio.equals(Constante.COD_CUENTA_AHORROS_MN)){
			return Constante.NOMBRE_CUENTA_AHORROS+" "+Constante.NOMBRE_MONEDA_SOL ;
		}
		if(this.codigoServicio.equals(Constante.COD_CUENTA_AHORROS_ME)){
			return Constante.NOMBRE_CUENTA_AHORROS+" "+Constante.NOMBRE_MONEDA_DOLAR ;
		}
		if(this.codigoServicio.equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			return Constante.NOMBRE_CUENTA_CORRIENTE+" "+Constante.NOMBRE_MONEDA_SOL ;
		}
		if(this.codigoServicio.equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			return Constante.NOMBRE_CUENTA_CORRIENTE+" "+Constante.NOMBRE_MONEDA_DOLAR ;
		}

		if(this.codigoServicio.equals(Constante.COD_CUENTA_CTS_MN)){
		}
		if(this.codigoServicio.equals(Constante.COD_CUENTA_CTS_ME)){
			return Constante.NOMBRE_CUENTA_CTS+" "+Constante.NOMBRE_MONEDA_DOLAR ;
		}
		return "DESCONOCIDO";
	}
	
	public String getCuentaPropiaDes(){
		if(this.cuentaPropia==null)
			return null;
		else if (this.cuentaPropia.equals("S"))
			return "SÍ";
		else
			return "NO";
	}

    
   
	/**
	 * @return Devuelve tarjetaOculta.
	 */
	public String getTarjetaOculta() {
		return tarjetaOculta;
	}
	/**
	 * @param tarjetaOculta El tarjetaOculta a establecer.
	 */
	public void setTarjetaOculta(String tarjetaOculta) {
		this.tarjetaOculta = tarjetaOculta;
	}
	
	/**
	 * @return Devuelve numSerFormat.
	 */
	public String getNumSerFormat() {
		return numSerFormat;
	}
	/**
	 * @param numSerFormat El numSerFormat a establecer.
	 */
	public void setNumSerFormat(String numSerFormat) {
		this.numSerFormat = numSerFormat;
	}
	/**
	 * @return Devuelve descripcionTipoTarjeta.
	 */
	public String getDescripcionTipoTarjeta() {
		return descripcionTipoTarjeta;
	}
	/**
	 * @param descripcionTipoTarjeta El descripcionTipoTarjeta a establecer.
	 */
	public void setDescripcionTipoTarjeta(String descripcionTipoTarjeta) {
		this.descripcionTipoTarjeta = descripcionTipoTarjeta;
	}

	public String getFechaNacimientoConv() {
		// TODO Apéndice de método generado automáticamente
		return fechaNacimientoConv;
	}

	public void setFechaNacimientoConv(String fechaNacimientoConv) {
		this.fechaNacimientoConv = fechaNacimientoConv;
		
	}

	public String getNumAfiliacionTDC() {
		return numAfiliacionTDC;
	}

	public void setNumAfiliacionTDC(String numAfiliacionTDC) {
		this.numAfiliacionTDC = numAfiliacionTDC;
	}
	
	public String getCodUsuarioSumi() {
		return codUsuarioSumi;
	}

	public void setCodUsuarioSumi(String codUsuarioSumi) {
		this.codUsuarioSumi = codUsuarioSumi;
	}
	
	
	public String getCodigoUsuarioSuministro() {
		return codigoUsuarioSuministro;
	}
	public void setCodigoUsuarioSuministro(String codigoUsuarioSuministro) {
		this.codigoUsuarioSuministro = codigoUsuarioSuministro;
	
	}
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getMaximo() {
		return maximo;
	}
	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}
	public String getMontoMaximoDebito() {
		return montoMaximoDebito;
	}
	public void setMontoMaximoDebito(String montoMaximoDebito) {
		this.montoMaximoDebito = montoMaximoDebito;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getTipoTelefono() {
		return tipoTelefono;
	}
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getServidorEmail() {
		return servidorEmail;
	}
	public void setServidorEmail(String servidorEmail) {
		this.servidorEmail = servidorEmail;
	}
	public String getNumSuministro() {
		return numSuministro;
	}
	public void setNumSuministro(String numSuministro) {
		this.numSuministro = numSuministro;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getServidorCorreo() {
		return servidorCorreo;
	}
	public void setServidorCorreo(String servidorCorreo) {
		this.servidorCorreo = servidorCorreo;
	}
	
	public String getEmpresaMostrar() {
		return empresaMostrar;
	}
	public void setEmpresaMostrar(String empresaMostrar) {
		this.empresaMostrar = empresaMostrar;
	}
	public String getServicioMostrar() {
		return servicioMostrar;
	}
	public void setServicioMostrar(String servicioMostrar) {
		this.servicioMostrar = servicioMostrar;
	}
	public String getFlagEstado() {
		return flagEstado;
	}
	public void setFlagEstado(String flagEstado) {
		this.flagEstado = flagEstado;
	}
	public String getIndicadorContacto() {
		return indicadorContacto;
	}
	public void setIndicadorContacto(String indicadorContacto) {
		this.indicadorContacto = indicadorContacto;
	}
	public String getTope() {
		return tope;
	}
	public void setTope(String tope) {
		this.tope = tope;
	}
	public String getDocMostrar() {
		return docMostrar;
	}
	public void setDocMostrar(String docMostrar) {
		this.docMostrar = docMostrar;
	}
	public String getViaConfirmacion() {
		return viaConfirmacion;
	}
	public void setViaConfirmacion(String viaConfirmacion) {
		this.viaConfirmacion = viaConfirmacion;
	}
	public String getViaConfMostrar() {
		return viaConfMostrar;
	}
	public void setViaConfMostrar(String viaConfMostrar) {
		this.viaConfMostrar = viaConfMostrar;
	}
	public String getTelContactoMostrar() {
		return telContactoMostrar;
	}
	public void setTelContactoMostrar(String telContactoMostrar) {
		this.telContactoMostrar = telContactoMostrar;
	}
	public String getMontoDebito() {
		return montoDebito;
	}
	public void setMontoDebito(String montoDebito) {
		this.montoDebito = montoDebito;
	}
	public void setAfiliaciones(List afiliaciones) {
		this.afiliaciones = afiliaciones;
	}
	public String getTelNumMostrar() {
		return telNumMostrar;
	}
	public void setTelNumMostrar(String telNumMostrar) {
		this.telNumMostrar = telNumMostrar;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}
	public String getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	public String getCorreoAdicional() {
		return correoAdicional;
	}
	public void setCorreoAdicional(String correoAdicional) {
		this.correoAdicional = correoAdicional;
	}
	public String getCorreoPersonal() {
		return correoPersonal;
	}
	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	public String getNomVia1() {
		return nomVia1;
	}
	public void setNomVia1(String nomVia1) {
		this.nomVia1 = nomVia1;
	}
	public String getNomVia2() {
		return nomVia2;
	}
	public void setNomVia2(String nomVia2) {
		this.nomVia2 = nomVia2;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
	public String getTelCelular() {
		return telCelular;
	}
	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}
	public String getTelFijo() {
		return telFijo;
	}
	public void setTelFijo(String telFijo) {
		this.telFijo = telFijo;
	}
	public String getTelFijoLab() {
		return telFijoLab;
	}
	public void setTelFijoLab(String telFijoLab) {
		this.telFijoLab = telFijoLab;
	}
	public String getTipoVia() {
		return tipoVia;
	}
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}
	public String getCorreoAdicional1() {
		return correoAdicional1;
	}
	public void setCorreoAdicional1(String correoAdicional1) {
		this.correoAdicional1 = correoAdicional1;
	}
	public String getCorreoPersonal1() {
		return correoPersonal1;
	}
	public void setCorreoPersonal1(String correoPersonal1) {
		this.correoPersonal1 = correoPersonal1;
	}
	public String getNomCliente1() {
		return nomCliente1;
	}
	public void setNomCliente1(String nomCliente1) {
		this.nomCliente1 = nomCliente1;
	}
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	public List getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List direcciones) {
		this.direcciones = direcciones;
	}
	public String getChannel() {
		return channel;
	}
	public String getCodOcupacion() {
		return codOcupacion;
	}
	public void setCodOcupacion(String codOcupacion) {
		this.codOcupacion = codOcupacion;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getDesOcupacion() {
		return desOcupacion;
	}
	public void setDesOcupacion(String desOcupacion) {
		this.desOcupacion = desOcupacion;
	}
	public String getCanalActlzDatosCliente() {
		return canalActlzDatosCliente;
	}
	public void setCanalActlzDatosCliente(String canalActlzDatosCliente) {
		this.canalActlzDatosCliente = canalActlzDatosCliente;
	}
	public String getDiasParaMigrar() {
		return diasParaMigrar;
	}
	public void setDiasParaMigrar(String diasParaMigrar) {
		this.diasParaMigrar = diasParaMigrar;
	}
	public String getFechaUltiActlzDatosCliente() {
		return fechaUltiActlzDatosCliente;
	}
	public void setFechaUltiActlzDatosCliente(String fechaUltiActlzDatosCliente) {
		this.fechaUltiActlzDatosCliente = fechaUltiActlzDatosCliente;
	}
	public String getFlagDejaMigrar() {
		return flagDejaMigrar;
	}
	public void setFlagDejaMigrar(String flagDejaMigrar) {
		this.flagDejaMigrar = flagDejaMigrar;
	}
	
	public String NomTipoDoc(String tipodoc, String campoasoc) {
		int td = Integer.parseInt(tipodoc);
		List lista = null;
		if (tipodoc != null && campoasoc != null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(
					campoasoc, tipodoc);
		if (lista != null) {
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();

				if (element.getCodigo().trim().equals(tipodoc + "")) {
					return element.getDescripcion();
				}
			}
		}

		return "";
	}
	
}
