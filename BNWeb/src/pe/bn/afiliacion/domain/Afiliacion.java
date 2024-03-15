package pe.bn.afiliacion.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.ReciboImpl;
import pe.cosapi.dto.PersonaDTO;

public interface Afiliacion extends Serializable{
	
	public abstract String getCodigoLocalidad();

	public abstract String getCodigoServicio();

	public abstract String getCuentaPropia();

	public abstract String getDescripcion();
	
	public abstract String getCtaUob();

	public abstract String getEmail();

	public abstract Timestamp getFechaNacimiento();
	
	public abstract String getFechaNacimientoConv();

	public abstract String  getTipoAfiliacion();

	public abstract String getNroDocumento();

	public abstract String getNroTarjeta();

	public abstract String getNumeroServicio();

	public abstract Long getSecuencia();

	public abstract String getSexo();

	public abstract String getTipoDocumento();
	
	public abstract String getBeneficiario();
	
	public abstract String getDescripcionTipoDocumento();
	public abstract void setDescripcionTipoDocumento(String descripcionTipoDocumento);
	
	public abstract String getDescripcionCodigoServicio();
	public abstract void setDescripcionCodigoServicio(String descripcionCodigoServicio);
	
	public abstract String getFlagDes();
	
	public abstract Timestamp getFecDesafilia();

	public abstract void setCodigoLocalidad(String codigoLocalidad);

	public abstract void setCodigoServicio(String codigoServicio);
	
	public abstract void setCuentaPropia(String CuentaPropia);
	
	public abstract void setDescripcion(String descripcion);
	
	public abstract void setCtaUob(String ctaUob);

	public abstract void setEmail(String email);

	public abstract void setFechaNacimiento(Timestamp fechaNacimiento);
	
	public abstract void setFechaNacimientoConv(String fechaNacimientoConv);

	public abstract void  setTipoAfiliacion(String tipoAfiliacion);

	public abstract void setNroDocumento(String nroDocumento);
	
	public abstract void setNroTarjeta(String nroTarjeta);

	public abstract void setNumeroServicio(String numeroServicio);
	
	public abstract void setSecuencia(Long secuencia);

	public abstract void setSexo(String sexo);

	public abstract void setTipoDocumento(String tipoDocumento);
	
	public abstract void setBeneficiario(String beneficiario);
	
	public abstract PersonaDTO getObjBenef();
	
	public abstract void setObjBenef(PersonaDTO objBenef);
	
	public abstract CuentaImpl getCuenta();
	
	public abstract void setCuenta(CuentaImpl cuenta);
	
    public abstract String getTipoTarjeta();
    
    public abstract List getDesafiliados();
     
    public abstract void setTipoTarjeta(String tipoTarjeta);
    
    public abstract String getClave6Digitos();
     
    public abstract void setClave6Digitos(String clave6Digitos);
    
	public abstract String getTarjetaOtroBancoFormateada();
	public abstract String getTarjetaFormateada();
	
	public abstract String getFecha();
	     
	public abstract void setFecha(String fecha);
	     
	public abstract String getHora();
	     
	public abstract void setHora(String hora);
	
	public abstract void setFlagDes(String flagDes);
	
	public abstract void setFecDesafilia(Timestamp fecDesafilia);
	
	public abstract void setTarjetaOculta(String tarjetaOculta);
	public abstract String getTarjetaOculta();
	
	public abstract String getNumSerFormat();
	public abstract void setNumSerFormat(String numero);
	public abstract void setDescripcionTipoTarjeta(String descripcionTipoTarjeta);
	public abstract String getDescripcionTipoTarjeta();
	
	public abstract String getCodigoUsuarioSuministro();
	public abstract void setCodigoUsuarioSuministro(String setcodigoUsuarioSuministro);
	
	
	public abstract String getTipoTelefono();
	public abstract String getNumeroTelefono();
	public abstract String getEmpresa();
	public abstract String getServicio();
	//public abstract String getCodigoUsuarioSumi(); 
	public abstract String getMaximo();
	public abstract String getMontoMaximoDebito();
	public abstract String getServidorEmail();
	public abstract String getTipoOperacion();
	public abstract String getNumSuministro();
	public abstract String getServidorCorreo();
	public abstract String getCorreo();
	public abstract String getNroOperacion();
	public abstract String getMontoDebito();
	
	public abstract void setTipoTelefono(String tipoTelefono);
	public abstract void setNumeroTelefono(String numeroTelefono);
	public abstract void setEmpresa(String empresa);
	public abstract void setServicio(String servicio);
	public abstract void setMaximo(String maximo);
	public abstract void setMontoMaximoDebito(String montoMaximoDebito);
	public abstract void setServidorEmail(String servidorEmail);
	public abstract void setTipoOperacion(String tipoOperacion);
	public abstract void setNumSuministro(String numSuministro);
	public abstract void setServidorCorreo(String servidorCorreo);
	public abstract void setCorreo(String correo);
	public abstract void setNroOperacion(String nroOperacion);
	public abstract void setMontoDebito(String montoDebito);
	
	//Débito mostrar usuario
	
	public abstract void setEmpresaMostrar(String empresaMostrar);
	public abstract String getEmpresaMostrar();
	
	public abstract void setServicioMostrar(String servicioMostrar);
	public abstract String getServicioMostrar();
	
	public abstract void setFlagEstado(String flagEstado);
	public abstract String getFlagEstado();
	
	public abstract void setIndicadorContacto(String indicadorContacto);
	public abstract String getIndicadorContacto();
	
	public abstract void setTope(String tope);
	public abstract String getTope();
	
	public abstract void setDocMostrar(String docMostrar);
	public abstract String getDocMostrar();
	
	public abstract void setViaConfirmacion(String viaConfirmacion);
	public abstract String getViaConfirmacion();
	
	public abstract void setViaConfMostrar(String viaConfMostrar);
	public abstract String getViaConfMostrar();
	
	public abstract void setTelContactoMostrar(String telContactoMostrar);
	public abstract String getTelContactoMostrar();
	
	public abstract void setTelNumMostrar(String telNumMostrar);
	public abstract String getTelNumMostrar();
	
	public abstract void setMontoMaxMostrar(String montoMaxMostrar);
	public abstract String getMontoMaxMostrar();
	
	public abstract void setFlagRegistro(String flagRegistro);
	public abstract String getFlagRegistro();
	
	
	//Banca Celular
	
	public abstract void setNumeroCuentaDestino(String numeroCuentaDestino);
	public abstract String getNumeroCuentaDestino();
	public abstract void setTipoCuentaDestino(String tipoCuentaDestino);
	public abstract String getTipoCuentaDestino();
	public abstract void setNumeroCelularDest(String numeroCelularDest);
	public abstract String getNumeroCelularDest();
	public abstract void setOperador(String operador);
	public abstract String getOperador();
	public abstract void setOperadorDesc(String operadorDesc);
	public abstract String getOperadorDesc();
	public abstract void setAlias(String alias);
	public abstract String getAlias();
	public abstract void setAliasOperacion(String aliasOperacion);
	public abstract String getAliasOperacion();
	public abstract void setNumServicio(String numServicio);
	public abstract String getNumServicio();
	public abstract void setServicioAfiliacion(String servicioAfiliacion);
	public abstract String getServicioAfiliacion();
	public abstract void setNumeroCelular(String numeroCelular);
	public abstract String getNumeroCelular();
	public abstract void setMostrarOperador(String mostrarOperador);
	public abstract String getMostrarOperador();
	public abstract void setMostrarTipoAfil(String mostrarTipoAfil);
	public abstract String getMostrarTipoAfil();
	public abstract void setMostrarNumAfil(String mostrarNumAfil);
	public abstract String getMostrarNumAfil();
	public abstract void setAfiliados(List afiliados);
	public abstract List getAfiliados();
	public abstract void setOperadorOrigen(String operadorOrigen);
	public abstract String getOperadorOrigen();
	//Datos de Clientes
	public abstract void setCodCliente(String codCliente);
	public abstract String getCodCliente();
	public abstract void setTelFijo(String telFijo);
	public abstract String getTelFijo();
	public abstract void setTelFijoLab(String telFijoLab);
	public abstract String getTelFijoLab();
	public abstract void setAnexo(String anexo);
	public abstract String getAnexo();
	public abstract void setTelCelular(String telCelular);
	public abstract String getTelCelular();
	public abstract void setCodDepartamento(String codDepartamento);
	public abstract String getCodDepartamento();
	public abstract void setCodProvincia(String codProvincia);
	public abstract String getCodProvincia();
	public abstract void setCodDistrito(String codDistrito);
	public abstract String getCodDistrito();
	public abstract void setNumDocumento(String numDocumento);
	public abstract String getNumDocumento();
	public abstract void setNomCliente(String nomCliente);
	public abstract String getNomCliente();
	public abstract void setNomCliente1(String nomCliente1);
	public abstract String getNomCliente1();
	public abstract void setTipoVia(String tipoVia);
	public abstract String getTipoVia();
	public abstract void setNomVia1(String nomVia1);
	public abstract String getNomVia1();
	public abstract void setNomVia2(String nomVia2);
	public abstract String getNomVia2();
	public abstract void setNumero(String numero);
	public abstract String getNumero();
	public abstract void setLote(String lote);
	public abstract String getLote();
	public abstract void setReferencia(String referencia);
	public abstract String getReferencia();
	public abstract void setReferencia1(String referencia1);
	public abstract String getReferencia1();
	public abstract void setCorreoPersonal(String correoPersonal);
	public abstract String getCorreoPersonal();
	public abstract void setCorreoAdicional(String correoAdicional);
	public abstract String getCorreoAdicional();
	public abstract void setCorreoPersonal1(String correoPersonal1);
	public abstract String getCorreoPersonal1();
	public abstract void setCorreoAdicional1(String correoAdicional1);
	public abstract String getCorreoAdicional1();
	
	public abstract void setBloque(String bloque);
	public abstract String getBloque();
	public abstract void setManzana(String manzana);
	public abstract String getManzana();
	public abstract void setPiso(String piso);
	public abstract String getPiso();
	public abstract void setInterior(String interior);
	public abstract String getInterior();
	
	public abstract void setDiscadoTelFijo(String discadoTelFijo);
	public abstract String getDiscadoTelFijo();
	public abstract void setDiscadoTelFijoLab(String discadoTelFijoLab);
	public abstract String getDiscadoTelFijoLab();
	public abstract void setDireccion(String direccion);
	public abstract String getDireccion();
	
	public abstract void setDirecciones(List direcciones);
	public abstract List getDirecciones();
	public abstract void setCodProducto(String codProducto);
	public abstract String getCodProducto();
	public abstract void setNumSecDir(String numSecDir);
	public abstract String getNumSecDir();
	public abstract void setNumItemDir(String numItemDir);
	public abstract String getNumItemDir();
	public abstract void setCodMedioEnvio(String codMedioEnvio);
	public abstract String getCodMedioEnvio();
	public abstract void setCorreos(List correos);
	public abstract List getCorreos();
	public abstract void setDesMedioEnvio(String desMedioEnvio);
	public abstract String getDesMedioEnvio();
	
	public abstract void setFlagConsentimiento(String flagConsentimiento);
	public abstract String getFlagConsentimiento();
	
	//Transferencias en Linea
	
	public abstract void setTipoDocumentoBenef(String tipoDocumentoBenef);
	public abstract String getTipoDocumentoBenef();
	public abstract void setNroDocumentoBenef(String nroDocumentoBenef);
	public abstract String getNroDocumentoBenef();
	
	public abstract void setIdentificadorCCE1(String identificadorCCE1);
	public abstract String getIdentificadorCCE1();

	public abstract void setIdentificadorCCE2(String identificadorCCE2);
	public abstract String getIdentificadorCCE2();

	public abstract void setCciOrigen(String cciOrigen);
	public abstract String getCciOrigen();
	
	public abstract void setFechaOperacion(String fechaOperacion);
	public abstract String getFechaOperacion();
	
	public abstract void setChannel(String channel);
	public abstract String getChannel();

	public abstract String getCodOcupacion();
	public abstract void setCodOcupacion(String codDepartamento);
	public abstract String getDesOcupacion();
	public abstract void setDesOcupacion(String codDepartamento);
	
    
    
    public abstract void setFlagDejaMigrar(String flagDejaMigrar);
    public abstract String getFlagDejaMigrar();
    
    public abstract void setDiasParaMigrar(String diasParaMigrar);
    public abstract String getDiasParaMigrar();
}