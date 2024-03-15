/*
 * Creado el 09/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de codigo
 */
package pe.bn.pago.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import pe.bn.pago.domain.PagoTasas;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Ciudad;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.DetalleTributo;
import pe.cosapi.domain.Documento;
import pe.cosapi.domain.Entidad;
import pe.cosapi.domain.Tributo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.DetalleTributoImpl;
import pe.cosapi.domain.impl.PagoImplNueva;
import pe.cosapi.sarabank.bean.Transaction;

public class PagoTasasImpl extends PagoImplNueva implements Serializable, PagoTasas {
	
	
	private Cuenta cuenta;
	private Entidad entidad;
	private Tributo tributo;
	private Documento documento;
	private Ciudad ciudad;
	private DetalleTributo detalle;
	private BigDecimal tipoCambio;
	private String fechaOpe, horaOpe, fecha, hora, codTransaccion, codUsuario, codAgencia, administradorTrib, numeroSecuencia, nomtribcbo, digitoControl;
	
	private String tipoDoc;
	private String flgDetalleTributoVisible;
	private String flgCiudadVisible;
	private String flgDistritoJuzgadoVisible;
	private String flgExpedienteVisible;
	private String tipoImporte;
	private String flgConfig;
	private String flgCantidadVisible;
	private String cantidad;
	private String plantilla;
	private String nroExpediente;
	private BigDecimal importeTotal;
	private BigDecimal importeTotalAlCambio;
	
	private String codDependencia;
	private String descripcionDependencia;
	
	private List listaImportes;
	private List listaDistritos;
	
	public static final Map TRIBUTOS_SIN_DETALLE = new HashMap();
	public static final Map ENTIDAD_SIN_CIUDAD = new HashMap();
	public static final Map SOLO_DNI = new HashMap();
	public static final Map SIN_DNI_PN = new HashMap();
	public static final Map SIN_DNI_LM_PN = new HashMap();
	
	static{
		//TRIBUTOS_SIN_DETALLE.put("00523","");
		TRIBUTOS_SIN_DETALLE.put("06823","");
		TRIBUTOS_SIN_DETALLE.put("09040","");		
		TRIBUTOS_SIN_DETALLE.put("02313","");
		TRIBUTOS_SIN_DETALLE.put("05983","");
		TRIBUTOS_SIN_DETALLE.put("05703","");
		TRIBUTOS_SIN_DETALLE.put("06645","");
		TRIBUTOS_SIN_DETALLE.put("09996","");
		TRIBUTOS_SIN_DETALLE.put("02121","");
		TRIBUTOS_SIN_DETALLE.put("02122","");
		TRIBUTOS_SIN_DETALLE.put("06645","");
		
		ENTIDAD_SIN_CIUDAD.put("00421","");

		SOLO_DNI.put("06645","");
		
		SIN_DNI_PN.put("00647","");
		
		//SIN_DNI_LM_PN.put("02119","");
		
		SIN_DNI_LM_PN.put("02121","");
		
		
	}
	

	public PagoTasasImpl() {
		
	}
	
	public void pagarTasas(Transaction t ,Usuario usuario) throws Exception{

		BigDecimal importeX = null;
		
		String nombreTributo1 = "";
		String nombreTributo2 = "";
		String nombreJuzgado1 = "";
		String nombreJuzgado2 = "";
		
		if(this.cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL))
			importeX = ObjectUtil.deFormatearMonto(this.getImporte());	
		else if(this.cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR))
			importeX = ObjectUtil.deFormatearMonto(this.getImporteAlCambio());		
		//FacadeFactory.getGeneralFacade().validarLimiteImporteHost(usuario,this.getCuenta(),importeX);
		
		Vector resultado = procesar(t);
		for (Iterator iter = resultado.iterator(); iter.hasNext();) {
			Vector elemento = (Vector) iter.next();
			if (t.transaction.equals("PT01")){
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
					setNroOperacion(elemento.elementAt(6).toString());
				}
				/*if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					setTipoCambio(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString(),7));				
				}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					setImporteAlCambio(ObjectUtil.tramaToBigDecimal(elemento.elementAt(6).toString()));
				}*/

				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					setAdministradorTrib(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					tributo.setCodigo(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					tributo.setDescripcion(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					setHora(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
					setFecha(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
					setCodTransaccion(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
					setCodUsuario(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					setCodAgencia(elemento.elementAt(6).toString().trim());}	
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					setNumeroSecuencia(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){/////new pago tasas varios
					setDigitoControl(elemento.elementAt(6).toString().trim());}
				
			}else{
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_1)){
					setNroOperacion(elemento.elementAt(6).toString());}				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_2)){
					setAdministradorTrib(elemento.elementAt(6).toString().trim());}
				//if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
				//	tributo.setCodigo(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_3)){
					nombreTributo1 = elemento.elementAt(6).toString().trim();}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_4)){
					nombreTributo2 = elemento.elementAt(6).toString().trim();}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_5)){
					nombreJuzgado1 = elemento.elementAt(6).toString().trim();}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_6)){
					nombreJuzgado2 = elemento.elementAt(6).toString().trim();}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_7)){
					setNumeroSecuencia(elemento.elementAt(6).toString().trim());}				
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_8)){
					setFecha(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_9)){
					setCodTransaccion(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_10)){
					setCodUsuario(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_11)){
					setHora(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_12)){
					setCodAgencia(elemento.elementAt(6).toString().trim());}
				if(elemento.elementAt(0).toString().equals(Constante.OUTPUT_STRING_13)){///////new pago tasas judiciales
					setDigitoControl(elemento.elementAt(6).toString().trim());}	
				
			}
		}
			
		if (t.transaction.equals("PT02")){
			tributo.setDescripcion(nombreTributo1 + nombreTributo2);
			setDescripcionDependencia(nombreJuzgado1 + nombreJuzgado2);
		}
		t.setObjeto(this);
		
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());		
		SimpleDateFormat formatter;
		Date data;

		//Seteamos la fecha
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = new Date(fechaActual.getTime());
		setFechaOpe(formatter.format(data));

		//Seteamos la hora
		formatter = new SimpleDateFormat("HH:mm:ss");
		data = new Date(fechaActual.getTime());
		setHoraOpe(formatter.format(data));
		
		//@DPSif(getArrayRuleException()!=null)
		//@DPS	throw getArrayRuleException();
			 
		//setNroOperacion("000000");
	}


	/**
	 * @return Devuelve ciudad.
	 */
	public Ciudad getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad El ciudad a establecer.
	 */
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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
	 * @return Devuelve documento.
	 */
	public Documento getDocumento() {
		return documento;
	}
	/**
	 * @param documento El documento a establecer.
	 */
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	/**
	 * @return Devuelve entidad.
	 */
	public Entidad getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad El entidad a establecer.
	 */
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return Devuelve tributo.
	 */
	public Tributo getTributo() {
		return tributo;
	}
	/**
	 * @param tributo El tributo a establecer.
	 */
	public void setTributo(Tributo tributo) {
		this.tributo = tributo;
	}
	
	public boolean getMostrarDetalleTributo(){

	    if (this.flgConfig.equals("0")){
			String codigo = this.getTributo().getCodigo();
			if(TRIBUTOS_SIN_DETALLE.containsKey(codigo)){
				return false;
			}
			return true;
	    }else{
	        return (this.flgDetalleTributoVisible.equals("1"));
	    }
	}
	
	public boolean getMostrarCiudad(){
	    if (this.flgConfig.equals("0")){	    
			String codigo = this.getEntidad().getCodigo();
			if(ENTIDAD_SIN_CIUDAD.containsKey(codigo)){
				return false;
			}
			return true;
	    }else{
	        return (this.flgCiudadVisible.equals("1"));
	    }
	}
	
	
	public List getDocumentos(List lista){
		String codigo = this.getTributo().getCodigo();
		List docs = new ArrayList();
		if(SOLO_DNI.containsKey(codigo)){
			for(int i = 0 ; i < lista.size(); i++){
				ComboUtil combo = (ComboUtil)lista.get(i);
				if(combo.getCodigo().equals(Constante.DNI)){
					docs.add(combo);
				}
			}
			return docs;
		}
		else if(SIN_DNI_PN.containsKey(codigo)){
			for(int i = 0 ; i < lista.size(); i++){
				ComboUtil combo = (ComboUtil)lista.get(i);
				if(!combo.getCodigo().equals(Constante.DNI)&&!combo.getCodigo().equals(Constante.PARTIDA_DE_NACIMIENTO)){
					docs.add(combo);
				}
			}
			return docs;
		}
		else if(SIN_DNI_LM_PN.containsKey(codigo)){
			for(int i = 0 ; i < lista.size(); i++){
				ComboUtil combo = (ComboUtil)lista.get(i);
				if(!combo.getCodigo().equals(Constante.DNI)&&!combo.getCodigo().equals(Constante.PARTIDA_DE_NACIMIENTO)&&!combo.getCodigo().equals(Constante.LIBRETA_MILITAR)){
					docs.add(combo);
				}
			}
			return docs;
		}
		else{
			return lista;
		}
	}
	
	public void setDetalle(String codigo){
		List lista = this.getTributo().getDetalles();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			DetalleTributoImpl element = (DetalleTributoImpl) iter.next();
			if(element.getCodigo().equals(codigo)){
				this.detalle = element;
				break;
			}				 
		}
	}
	
	public DetalleTributo getDetalle(){
		return detalle;
	}
	
	public void calcularCambio(Usuario usuario){
		
		BigDecimal dolar = null;
		if(this.getTributo().getCodigo().equals(Constante.PAGO_TASAS_COD_TRX_MINISTERIO_INT)){
			 dolar = ObjectUtil.deFormatearMonto(this.getImporteTotal());
		}
		else{
			 dolar = ObjectUtil.deFormatearMonto(this.getImporte());
		}
		dolar = dolar.divide(usuario.getTipoCambio().getCompra(),BigDecimal.ROUND_HALF_EVEN).setScale(2);
		setImporteAlCambio(dolar);
		setTipoCambio(usuario.getTipoCambio().getCompra().setScale(4,BigDecimal.ROUND_HALF_EVEN));
	}
	
	public BigDecimal calcularCambio(Usuario usuario, BigDecimal importe){
		BigDecimal dolar = importe;
		dolar = dolar.divide(usuario.getTipoCambio().getCompra(),BigDecimal.ROUND_HALF_EVEN).setScale(2);
		return dolar;
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
	 * @return Devuelve fecha.
	 */

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#getCodTransaccion()
	 */
	public String getCodTransaccion() {
		// TODO Apendice de metodo generado automaticamente
		return codTransaccion;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setCodTransaccion(java.lang.String)
	 */
	public void setCodTransaccion(String codTransaccion) {
		// TODO Apendice de metodo generado automaticamente
		this.codTransaccion=codTransaccion;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#getCodUsuario()
	 */
	public String getCodUsuario() {
		// TODO Apendice de metodo generado automaticamente
		return codUsuario;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setCodUsuario(java.lang.String)
	 */
	public void setCodUsuario(String codUsuario) {
		// TODO Apendice de metodo generado automaticamente
		this.codUsuario=codUsuario;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#getCodAgencia()
	 */
	public String getCodAgencia() {
		// TODO Apendice de metodo generado automaticamente
		return codAgencia;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setCodAgencia(java.lang.String)
	 */
	public void setCodAgencia(String codAgencia) {
		// TODO Apendice de metodo generado automaticamente
		this.codAgencia=codAgencia;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#getAdministradorTrib()
	 */
	public String getAdministradorTrib() {
		// TODO Apendice de metodo generado automaticamente
		return administradorTrib;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setAdministradorTrib(java.lang.String)
	 */
	public void setAdministradorTrib(String administradorTrib) {
		// TODO Apendice de metodo generado automaticamente
		this.administradorTrib=administradorTrib;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#getNumeroSecuencia()
	 */
	public String getNumeroSecuencia() {
		// TODO Apendice de metodo generado automaticamente
		return numeroSecuencia;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setNumeroSecuencia(java.lang.String)
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		// TODO Apendice de metodo generado automaticamente
		this.numeroSecuencia=numeroSecuencia;
	}
	
	public String getFechaOpe() {
		// TODO Apendice de metodo generado automaticamente
		return fechaOpe;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setNumeroSecuencia(java.lang.String)
	 */
	public void setFechaOpe(String fechaOpe) {
		// TODO Apendice de metodo generado automaticamente
		this.fechaOpe=fechaOpe;
	}
	
	public String getHoraOpe() {
		// TODO Apendice de metodo generado automaticamente
		return horaOpe;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setNumeroSecuencia(java.lang.String)
	 */
	public void setHoraOpe(String horaOpe) {
		// TODO Apendice de metodo generado automaticamente
		this.horaOpe=horaOpe;
	}
	
	public String getNomtribcbo() {
		// TODO Apendice de metodo generado automaticamente
		return nomtribcbo;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setNumeroSecuencia(java.lang.String)
	 */
	public void setNomtribcbo(String nomtribcbo) {
		// TODO Apendice de metodo generado automaticamente
		this.nomtribcbo=nomtribcbo;
	}

	public String getDigitoControl() {
		// TODO Apendice de metodo generado automaticamente
		return digitoControl;
	}

	/* (sin Javadoc)
	 * @see pe.bn.pago.domain.PagoTasas#setNumeroSecuencia(java.lang.String)
	 */
	public void setDigitoControl(String digitoControl) {
		// TODO Apendice de metodo generado automaticamente
		this.digitoControl=digitoControl;
	}
	
	
	
	
	
	
	public String getTipoDoc(){
	    return tipoDoc;
	}
	
	public void setTipoDoc(String value){
	    this.tipoDoc = value;	
	}
	
	public String getFlgDetalleTributoVisible(){
	    return flgDetalleTributoVisible;
	}
	
	public void setFlgDetalleTributoVisible(String value){
	    this.flgDetalleTributoVisible = value;
	}

	public String getFlgCiudadVisible(){
	    return flgCiudadVisible;
	}
	
	public void setFlgCiudadVisible(String value){
	    this.flgCiudadVisible = value;
	}

	public String getFlgDistritoJuzgadoVisible(){
	    return flgDistritoJuzgadoVisible;
	}
	
	public void setFlgDistritoJuzgadoVisible(String value){
	    this.flgDistritoJuzgadoVisible = value;
	}

	public String getFlgExpedienteVisible(){
	    return flgExpedienteVisible;
	}
	
	public void setFlgExpedienteVisible(String value){
	    this.flgExpedienteVisible = value;
	}
	
	public String getTipoImporte(){
	    return tipoImporte;
	}
	
	public void setTipoImporte(String value){
	    this.tipoImporte = value;
	}
	
	public String getFlgConfig(){
	    return flgConfig;
	}
	
	public void setFlgConfig(String value){
	    this.flgConfig = value;
	}

	public List getListaImportes(){
	    return listaImportes;
	}
	
	public void setListaImportes(List value){
	    this.listaImportes = value;
	}
	
	public List getListaDistritos(){
	    return listaDistritos;
	}
	
	public void setListaDistritos(List value){
	    this.listaDistritos = value;
	}
	
	public String getFlgCantidadVisible(){
	    return flgCantidadVisible;
	}
	
	public void setFlgCantidadVisible(String value){
	    this.flgCantidadVisible = value;
	}

	
	public String getCantidad(){
	    return cantidad;
	}
	
	public void setCantidad(String value){
	    this.cantidad = value;
	}
	
	public void loadConfig(String codTributo){
	    //Buscar en Tabla y Cargar Config
	    flgConfig = "1";
	    if (codTributo.equals("03670")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("2");
	    }else if (codTributo.equals("09970")){
	        setTipoDoc("10006");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("1");
	        setPlantilla("4");
	    }else if (codTributo.equals("09996")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("06645")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("1");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01857")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01806")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01792")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01873")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01814")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("07900")){
	        setTipoDoc("10006");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    }else if (codTributo.equals("01775")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01800")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("00044")){
	        setTipoDoc("1");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("09040")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }else if (codTributo.equals("01988")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("2"); //Libre
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	        
	    	}
	     else if (codTributo.equals("07307")){
		    setTipoDoc("10006");
		    setFlgDetalleTributoVisible("0");
		    setFlgCiudadVisible(("0"));
		    setFlgDistritoJuzgadoVisible("1");
		    setFlgExpedienteVisible("1");
		    setTipoImporte("3");
		    setFlgCantidadVisible("0");
		    setPlantilla("1");
	     	}
	     else if (codTributo.equals("07439")){
	        setTipoDoc("10006");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    	} 
	     else if (codTributo.equals("08214")){
        	setTipoDoc("10006");
        	setFlgDetalleTributoVisible("0");
        	setFlgCiudadVisible(("0"));
        	setFlgDistritoJuzgadoVisible("1");
        	setFlgExpedienteVisible("1");
        	setTipoImporte("3");
        	setFlgCantidadVisible("0");
        	setPlantilla("1");
    		}  
    	else if (codTributo.equals("07447")){
    		setTipoDoc("10006");
    		setFlgDetalleTributoVisible("0");
    		setFlgCiudadVisible(("0"));
    		setFlgDistritoJuzgadoVisible("1");
    		setFlgExpedienteVisible("1");
    		setTipoImporte("3");
    		setFlgCantidadVisible("0");
    		setPlantilla("1");
			
	 }else if (codTributo.equals("09996")){
        setTipoDoc("10004");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("0");
        setPlantilla("");
    }else if (codTributo.equals("06645")){
        setTipoDoc("10004");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("");
    }else if (codTributo.equals("01857")){
        setTipoDoc("10003");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("0");
        setPlantilla("");
    }else if (codTributo.equals("01806")){
        setTipoDoc("10003");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("0");
        setPlantilla("");
    }else if (codTributo.equals("01792")){
        setTipoDoc("10003");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("0");
        setPlantilla("");
    }else if (codTributo.equals("01873")){
        setTipoDoc("10003");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("0");
        setPlantilla("");
    }else if (codTributo.equals("01814")){
        setTipoDoc("10003");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("0");
        setPlantilla("");
        
	  }else if (codTributo.equals("00116")){
        setTipoDoc("10003");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("3");
        setFlgCantidadVisible("1");
        setPlantilla("5"); // Importe Default CERO
	  }
	  else if (codTributo.equals("07609")){
        setTipoDoc("10007");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("1");
    }
	 else if (codTributo.equals("07625")){
        setTipoDoc("10007");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("1");
    }
	 else if (codTributo.equals("07684")){
        setTipoDoc("10007");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("1");
    }
	 else if (codTributo.equals("07463")){
        setTipoDoc("10007");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("1");
    }
	 else if (codTributo.equals("07471")){
        setTipoDoc("10007");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("1");
    }
	 else if (codTributo.equals("07668")){
        setTipoDoc("10007");
        setFlgDetalleTributoVisible("0");
        setFlgCiudadVisible(("0"));
        setFlgDistritoJuzgadoVisible("0");
        setFlgExpedienteVisible("0");
        setTipoImporte("1");
        setFlgCantidadVisible("0");
        setPlantilla("1");
  }
	else if (codTributo.equals("02119")){
		        setTipoDoc("10008");
		        setFlgDetalleTributoVisible("1");
		        setFlgCiudadVisible(("0"));
		        setFlgDistritoJuzgadoVisible("0");
		        setFlgExpedienteVisible("0");
		        setTipoImporte("3");
		        setFlgCantidadVisible("0");
		        setPlantilla("");
	 }
	 else if (codTributo.equals("00523")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00524")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00730")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("02143")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00728")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("06610")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }  
	 else if (codTributo.equals("06831")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }  
	    
	 else if (codTributo.equals("08991")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("1");
	        setPlantilla("4");
	 }
	    
     else if (codTributo.equals("08222")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	  }
	    
     else if (codTributo.equals("07935")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	  
	    
	 }else if (codTributo.equals("02844")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 
	 }else if (codTributo.equals("02879")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 
	 }else if (codTributo.equals("08095")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 
	 }else if (codTributo.equals("08494")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 
//	 }else if (codTributo.equals("05533")){
//	        setTipoDoc("10003");
//	        setFlgDetalleTributoVisible("0");
//	        setFlgCiudadVisible(("0"));
//	        setFlgDistritoJuzgadoVisible("0");
//	        setFlgExpedienteVisible("0");
//	        setTipoImporte("3");
//	        setFlgCantidadVisible("0");
//	        setPlantilla("");
//	        
//	 }else if (codTributo.equals("03964")){
//	        setTipoDoc("10003");
//	        setFlgDetalleTributoVisible("0");
//	        setFlgCiudadVisible(("0"));
//	        setFlgDistritoJuzgadoVisible("0");
//	        setFlgExpedienteVisible("0");
//	        setTipoImporte("3");
//	        setFlgCantidadVisible("1");
//	        setPlantilla("");
//	 
//	 }else if (codTributo.equals("05541")){
//	        setTipoDoc("10003");
//	        setFlgDetalleTributoVisible("0");
//	        setFlgCiudadVisible(("0"));
//	        setFlgDistritoJuzgadoVisible("0");
//	        setFlgExpedienteVisible("0");
//	        setTipoImporte("3");
//	        setFlgCantidadVisible("1");
//	        setPlantilla("");
//	        
//	 }else if (codTributo.equals("05991")){
//	        setTipoDoc("10003");
//	        setFlgDetalleTributoVisible("0");
//	        setFlgCiudadVisible(("0"));
//	        setFlgDistritoJuzgadoVisible("0");
//	        setFlgExpedienteVisible("0");
//	        setTipoImporte("3");
//	        setFlgCantidadVisible("1");
//	        setPlantilla("");
//	 }else if (codTributo.equals("06122")){
//	        setTipoDoc("10003");
//	        setFlgDetalleTributoVisible("0");
//	        setFlgCiudadVisible(("0"));
//	        setFlgDistritoJuzgadoVisible("0");
//	        setFlgExpedienteVisible("0");
//	        setTipoImporte("3");
//	        setFlgCantidadVisible("1");
//	        setPlantilla("");
//	        
//	 }else if (codTributo.equals("06386")){
//	        setTipoDoc("10003");
//	        setFlgDetalleTributoVisible("0");
//	        setFlgCiudadVisible(("0"));
//	        setFlgDistritoJuzgadoVisible("0");
//	        setFlgExpedienteVisible("0");
//	        setTipoImporte("3");
//	        setFlgCantidadVisible("1");
//	        setPlantilla("");
	 }
	 else if (codTributo.equals("07374")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	  	    
	 }

	 else if (codTributo.equals("07927")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	 else if (codTributo.equals("07943")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	 else if (codTributo.equals("07951")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	 else if (codTributo.equals("07978")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	 else if (codTributo.equals("08168")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	   
	 else if (codTributo.equals("07234")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	 else if (codTributo.equals("09148")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("2");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 }
	 else if (codTributo.equals("07021")){
	        setTipoDoc("10005");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("1");
	        setFlgExpedienteVisible("1");
	        setTipoImporte("2");
	        setFlgCantidadVisible("0");
	        setPlantilla("1");
	    
	 } 
	 else if (codTributo.equals("01810")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("06662")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("06666")){
	        setTipoDoc("10003");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00522")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00521")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00525")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00530")){
	        setTipoDoc("10004");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("00729")){
		  setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("01601")){
		 setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 } else if (codTributo.equals("01602")){
		 setTipoDoc("10004");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 
	 } else if (codTributo.equals("00529")){
		 	setTipoDoc("10007");
	        setFlgDetalleTributoVisible("1");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("3");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else if (codTributo.equals("01023")){
		 setTipoDoc("10007");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("2");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }else if (codTributo.equals("01031")){
		 setTipoDoc("10009");
	        setFlgDetalleTributoVisible("0");
	        setFlgCiudadVisible(("0"));
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("2");
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	 }
	 else{
	        setFlgConfig("0"); //Tributo no configurado
	        setTipoDoc("1"); //Todos
	        setFlgCantidadVisible("0");
	        setFlgCiudadVisible("0");
	        setFlgDetalleTributoVisible("0");
	        setFlgDistritoJuzgadoVisible("0");
	        setFlgExpedienteVisible("0");
	        setTipoImporte("1"); //Fijo
	        setFlgCantidadVisible("0");
	        setPlantilla("");
	    }
	    
	   
	    
	}
	
	public String getCodDependencia(){
		return codDependencia;
	}
	
	public void setCodDependencia(String value){
		this.codDependencia = value;
	}
	
	public String getDescripcionDependencia(){
		return descripcionDependencia;
	}
	
	public void setDescripcionDependencia(String value){
		this.descripcionDependencia = value;
	}
	
	public String getPlantilla(){
		return plantilla;
	}
	
	public void setPlantilla(String value){
		this.plantilla = value;
	}

	public String getNroExpediente(){
		return nroExpediente;
	}
	
	public void setNroExpediente(String value){
		this.nroExpediente = value;
	}
	
	public String getImporteTotal(){
		return ObjectUtil.formatearMonto(importeTotal);
	}
	
	public void setImporteTotal(BigDecimal value){
		this.importeTotal = value;
	}

	public String getImporteTotalAlCambio(){
		return ObjectUtil.formatearMonto(importeTotalAlCambio);
	}
	
	public void setImporteTotalAlCambio(BigDecimal value){
		this.importeTotalAlCambio = value;
	}
}
