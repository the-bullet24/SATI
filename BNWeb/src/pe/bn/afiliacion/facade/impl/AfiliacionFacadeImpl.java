package pe.bn.afiliacion.facade.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.SystemOutLogger;


import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.afiliacion.facade.AfiliacionFacade;
import pe.bn.cldinamica.dao.afiliacionDAO;
import pe.bn.cldinamica.dao.impl.WSF02;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.com.bn.common.Funciones;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;




public class AfiliacionFacadeImpl implements AfiliacionFacade{
	
	
	public List getAfiliaciones(String tipoAfiliacion, String nroTarjeta, String campoAyuda) throws Exception {
		
		AfiliacionImpl afiliacionImpl = new AfiliacionImpl();
		afiliacionImpl.setTipoAfiliacion(tipoAfiliacion);
		afiliacionImpl.setNroTarjeta(nroTarjeta);
		afiliacionImpl.setCampoAsociado(campoAyuda);
		List lista = afiliacionImpl.getAfiliaciones();
		
		
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			AfiliacionImpl element = (AfiliacionImpl) iter.next();
			
			element.setCampoAsociado(campoAyuda);
			if (campoAyuda.equals("00004")) {
				element.setDescripcion(element.getBeneficiario());
			}
		}
		
		return lista;
		
	}
	
	public List getAfiliaExiste(String tipoAfiliacion, String nroTarjeta, String campoAyuda, String numeroServicio) throws Exception {
		
		AfiliacionImpl afiliacionImpl = new AfiliacionImpl();
		afiliacionImpl.setTipoAfiliacion(tipoAfiliacion);
		afiliacionImpl.setNroTarjeta(nroTarjeta);
		afiliacionImpl.setCampoAsociado(campoAyuda);
		afiliacionImpl.setNumeroServicio(numeroServicio);
		List lista = afiliacionImpl.getAfiliaExiste();
		
		return lista;
	}
	
public List getAfiliaExisteDebito(String tipoAfiliacion, String nroTarjeta, String campoAyuda, String numeroServicio, String codServicio) throws Exception {
		
		AfiliacionImpl afiliacionImpl = new AfiliacionImpl();
		afiliacionImpl.setTipoAfiliacion(tipoAfiliacion);
		afiliacionImpl.setNroTarjeta(nroTarjeta);
		
		afiliacionImpl.setCampoAsociado(campoAyuda);
		afiliacionImpl.setNumeroServicio(numeroServicio);
		
		afiliacionImpl.setCodigoServicio(codServicio);
		
		List lista = afiliacionImpl.getAfiliaExisteDebito();
		
		return lista;
	}
	
	public List getListaAfiliaciones(String tipoAfiliacion,String nroTarjeta,String nroCuenta) throws Exception{
	    AfiliacionImpl afiliacionImpl = new AfiliacionImpl();
		afiliacionImpl.setTipoAfiliacion(tipoAfiliacion);
		afiliacionImpl.setNroTarjeta(nroTarjeta);
		afiliacionImpl.setNumeroServicio(nroCuenta);
		List lista = afiliacionImpl.getAfiliaExiste();
		return lista;
	}

	public List getAfiliacionByValues(String codigoAyuda,String tipoAfiliacion,String numTarjeta) throws Exception{
		AfiliacionImpl afiliacionImpl = new AfiliacionImpl();
		return afiliacionImpl.getAfiliacionByValues(codigoAyuda,tipoAfiliacion,numTarjeta);
	}
	
	
	
	public Afiliacion getAfiliacion(String nroTransaccion, String nroTarjeta, Long nroSecuencia,String campoAyuda) throws Exception {
		return (Afiliacion)new AfiliacionImpl(nroTransaccion,nroTarjeta,nroSecuencia, campoAyuda);
	}

	
	
	public void desafiliar(Afiliacion af) throws Exception {
		AfiliacionImpl afiliacionImpl = new AfiliacionImpl(af.getTipoAfiliacion(),af.getNroTarjeta(),af.getSecuencia(), null);
		afiliacionImpl.desafiliar();
	}
	
	
	public void desafiliarDebito(Afiliacion af) throws Exception {
		AfiliacionImpl afiliacionImpl = new AfiliacionImpl(af.getTipoAfiliacion(),af.getNroTarjeta(),af.getSecuencia(), null);
		afiliacionImpl.desafiliarDebito();
	}
	
	
	
	public Afiliacion afiliar(Usuario usuario,Afiliacion af) throws Exception {
		
		af.setNroTarjeta(usuario.getTarjeta().getNumero());
		
		AfiliacionImpl afil_ = new AfiliacionImpl(af);
		//FacadeFactory.getGeneralFacade().validarDatosPersonalesAfiliacion(af,usuario);
		
		if (Constante.TB_MISMO_BANCO.equals(afil_.getTipoAfiliacion())){
		   // FacadeFactory.getGeneralFacade().validacionCuenta(afil_);
		   // validaCuentaDestino(afil_.getNumeroServicio(), afil_.getTipoAfiliacion(),afil_.getNroTarjeta());
		}else if (Constante.TB_INTERBANCO.equals(afil_.getTipoAfiliacion())){
		    FacadeFactory.getGeneralFacade().validacionCuentaCCI(afil_);
		}else if (Constante.AFI_DEBITO_AUTOMATICO.equals(afil_.getTipoAfiliacion())){
			 //FacadeFactory.getGeneralFacade().validacionCuenta(afil_);
			    //validaCuentaDestino(afil_.getCodigoUsuarioSuministro(), afil_.getTipoAfiliacion(),afil_.getNroTarjeta());
		}else if (Constante.CONSULT_DEBITO_AUTOMATICO.equals(afil_.getTipoAfiliacion())){
			 //FacadeFactory.getGeneralFacade().validacionCuenta(afil_);
			    //validaCuentaDestino(afil_.getCodigoUsuarioSuministro(), afil_.getTipoAfiliacion(),afil_.getNroTarjeta());
		}

		//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),af.getClave6Digitos(),af.getClave6Digitos()); --Se quita validacion Clave 6 (09/04/2013)
		Long secuencia = getSecuencia(af.getTipoAfiliacion(),af.getNroTarjeta());
		//afil_.setTipoTelefono(af.getTipoTelefono());
		afil_.setSecuencia(secuencia);
		
		if (Constante.AFI_DEBITO_AUTOMATICO.equals(afil_.getTipoAfiliacion())){
			String servicio = af.getServicio().substring(0, 2);
			afil_.setCodigoServicio(servicio);
			afil_.setNumeroServicio(af.getNumSuministro());
		}
		
		afil_.afiliar();
		afil_.cargar();
		afil_.setCuenta(usuario.getCuentaAhorro());
		afil_.setTarjetaOculta(ObjectUtil.ocultarCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA));
		
	
		
		return afil_;
	}
	
	private void validaCuentaDestino(String nroCuenta, String tipoAfiliacion,String nroTarjeta)throws Exception{
	    List lstAfil = getListaAfiliaciones(tipoAfiliacion,nroTarjeta,nroCuenta);
	    if (lstAfil.size() > 0)
	        throw new ArrayRuleException(ConstanteError.GENERICO,"La Cuenta Destino " + nroCuenta +" ya se encuentra afiliada.");
	}
	
public List validacionRestric(String transaccion, String cuenta, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
		
		Transaction t = new Transaction(transaccion);
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
	
		val = new Vector();
		val.addElement("codCuenta");
		val.addElement(cuenta);
		//val.addElement("04572020792");
		
		valores.addElement(val);	
		
		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		AfiliacionImpl consultaDA = new AfiliacionImpl();
		
		List consulta = null;
		
		consulta= consultaDA.validacionCuentas(t,valores,ctas);
		
		return consulta;
		
	}


	
	public Afiliacion getAfiliacionDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
		
		Transaction t = new Transaction(transaccion);
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
	
		val = new Vector();
		val.addElement("txtNumCta");
		val.addElement(afiliacion.getCuenta().getNumeroProducto());
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("txtMontoTope");
		val.addElement(afiliacion.getMaximo());
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtFechaProceso");
		String fecha =  ObjectUtil.getFechaActual2();
		val.addElement(fecha);
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtMoneda");
		val.addElement("604");
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtTipoOperacion");
		val.addElement(tipoOperacion);
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("txtNumSuministro");
		val.addElement(afiliacion.getNumSuministro());
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtCodEntidad");
		val.addElement(afiliacion.getEmpresa());
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtCodServicio");
		val.addElement(afiliacion.getServicio());
		valores.addElement(val);	
				
		String abonado = Funciones.rpad(afiliacion.getBeneficiario().trim()," ",30);
		
		val = new Vector();
		val.addElement("txtAbonado");
		val.addElement(abonado.substring(0, 30));
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtSexo");
		val.addElement(" ");
		valores.addElement(val);	
				
		val = new Vector();
		val.addElement("txtEmail");
		val.addElement(afiliacion.getCorreo().toUpperCase());
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("txtServidorEmail");
		val.addElement(afiliacion.getServidorCorreo().toUpperCase());
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("txtTipoTelefono");
		val.addElement(afiliacion.getTipoTelefono());
		valores.addElement(val);	
		
		val = new Vector();
		val.addElement("txtNroTelefono");
		val.addElement(afiliacion.getNumeroTelefono());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("txtIndicadorCont");
		val.addElement(afiliacion.getViaConfirmacion());
		valores.addElement(val);
	
				
		val = new Vector();
		val.addElement("txtTipoDocumento");
		val.addElement(afiliacion.getTipoDocumento());
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("txtNroDocumento");
		val.addElement(afiliacion.getNroDocumento());
		valores.addElement(val);	
			
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		AfiliacionImpl afiliacionDA = new AfiliacionImpl();
		
		afiliacionDA.setCuenta(afiliacion.getCuenta());
		afiliacionDA.setTipoTelefono(afiliacion.getTipoTelefono());
		afiliacionDA.setNumeroTelefono(afiliacion.getNumeroTelefono());
		afiliacionDA.setNumSuministro(afiliacion.getNumSuministro());
		afiliacionDA.setEmpresaMostrar(afiliacion.getEmpresaMostrar());
		afiliacionDA.setServicioMostrar(afiliacion.getServicioMostrar());
		afiliacionDA.setViaConfMostrar(afiliacion.getViaConfMostrar());
		afiliacionDA.setTope(afiliacion.getTope());
		afiliacionDA.setDocMostrar(afiliacion.getDocMostrar());
		afiliacionDA.setTipoDocumento(afiliacion.getTipoDocumento());
		afiliacionDA.setNroDocumento(afiliacion.getNroDocumento());
		afiliacionDA.setEmail(afiliacion.getEmail());
		afiliacionDA.setTelContactoMostrar(afiliacion.getTelContactoMostrar());
		afiliacionDA.setTelNumMostrar(afiliacion.getTelNumMostrar());
		
		if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
			
			afiliacionDA.setMontoDebito("------");
			
		}else{
			afiliacionDA.setMontoDebito(""+(ObjectUtil.tramaToBigDecimal(afiliacion.getMaximo())));
			
			
		}
		
				
		afiliacionDA.getAfiliaDA(t,valores,ctas,usuario);
		
		//System.out.println("afiliacionDA.getNroOperacion():"+afiliacionDA.getNroOperacion());
		
		return (Afiliacion)afiliacionDA;
		
	}
	
	
public List getConsultaDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress) throws Exception{
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
	
		
		val = new Vector();
		val.addElement("txtNumCta");
		val.addElement(afiliacion.getCuenta().getNumeroProducto());
		valores.addElement(val);	
	
		
		val = new Vector();
		val.addElement("txtTipo");
		val.addElement(tipoOperacion);
		valores.addElement(val);	
		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		AfiliacionImpl consultaDA = new AfiliacionImpl();
		List consulta = null;
			
		consulta= consultaDA.getConsultaDA(transaccion,valores,ctas);
		
		return consulta;
		//afiliacion.setCtaUob(transferencia.getCuentaUob(transaccion,valores,ctas));
		
	}
	
public Afiliacion getDesafiliacionDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		
	
	
	val = new Vector();
	val.addElement("txtNumCta");
	val.addElement(afiliacion.getCuenta().getNumeroProducto());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtMontoTope");
	val.addElement(afiliacion.getMaximo());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtFechaProceso");
	String fecha =  ObjectUtil.getFechaActual2();
	val.addElement(fecha);
	
	
	val = new Vector();
	val.addElement("txtMoneda");
	val.addElement("604");
	valores.addElement(val);	
		
	val = new Vector();
	val.addElement("txtTipoOperacion");
	val.addElement("2");
	valores.addElement(val);	
		
	val = new Vector();
	val.addElement("txtNumSuministro");
	val.addElement(afiliacion.getNumSuministro());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtCodEntidad");
	val.addElement(afiliacion.getEmpresa());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtCodServicio");
	val.addElement(afiliacion.getServicio());
	valores.addElement(val);	
	
	
	String abonado = Funciones.rpad(afiliacion.getBeneficiario().trim()," ",30);
	
	val = new Vector();
	val.addElement("txtAbonado");
	val.addElement(abonado.substring(0, 30));
	valores.addElement(val);		
	
	
	val = new Vector();
	val.addElement("txtSexo");
	val.addElement(" ");
	valores.addElement(val);	
	

	val = new Vector();
	val.addElement("txtEmail");
	val.addElement(afiliacion.getCorreo().toUpperCase());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtServidorEmail");
	val.addElement(afiliacion.getServidorCorreo().toUpperCase());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("txtTipoTelefono");
	val.addElement(afiliacion.getTipoTelefono());
	valores.addElement(val);	
	

	val = new Vector();
	val.addElement("txtNroTelefono");
	val.addElement(afiliacion.getNumeroTelefono());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("txtIndicadorCont");
	val.addElement(afiliacion.getViaConfirmacion());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("txtTipoDocumento");
	val.addElement(afiliacion.getTipoDocumento());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtNroDocumento");
	val.addElement(afiliacion.getNroDocumento());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("txtFlagestado");
	val.addElement(afiliacion.getFlagEstado());
	valores.addElement(val);	
		
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	AfiliacionImpl afiliacionDA = new AfiliacionImpl();
	afiliacionDA.setCuenta(afiliacion.getCuenta());
	afiliacionDA.setEmpresaMostrar(afiliacion.getEmpresaMostrar());
	afiliacionDA.setServicioMostrar(afiliacion.getServicioMostrar());
	afiliacionDA.setNumSuministro(afiliacion.getNumSuministro());
	afiliacionDA.setTipoDocumento(afiliacion.getTipoDocumento());
	afiliacionDA.setNroDocumento(afiliacion.getNroDocumento());
	afiliacionDA.setEmail(afiliacion.getEmail());
	afiliacionDA.setTelContactoMostrar(afiliacion.getTelContactoMostrar());
	afiliacionDA.setViaConfMostrar(afiliacion.getViaConfMostrar());
	afiliacionDA.setDocMostrar(afiliacion.getDocMostrar());
	afiliacionDA.setFlagEstado(afiliacion.getFlagEstado());
	afiliacionDA.setNumeroTelefono(afiliacion.getNumeroTelefono());
	afiliacionDA.setTelNumMostrar(afiliacion.getTelNumMostrar());
	afiliacionDA.getDesafiliacionDA(t,valores,ctas,usuario);
	
	
	
	//System.out.println("afiliacionDA.getNroOperacion():"+afiliacionDA.getNroOperacion());
	
	
	return (Afiliacion)afiliacionDA;
	
}


public Afiliacion getModificacionDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("txtNumCta");
	val.addElement(afiliacion.getCuenta().getNumeroProducto());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtMontoTope");
	val.addElement(afiliacion.getMaximo());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtFechaProceso");
	String fecha =  ObjectUtil.getFechaActual2();
	val.addElement(fecha);
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtMoneda");
	val.addElement("604");
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtTipoOperacion");
	val.addElement(tipoOperacion);
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtNumSuministro");
	val.addElement(afiliacion.getNumSuministro());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtCodEntidad");
	val.addElement(afiliacion.getEmpresa());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtCodServicio");
	val.addElement(afiliacion.getServicio());
	valores.addElement(val);	
	
	String abonado = Funciones.rpad(afiliacion.getBeneficiario().trim()," ",30);
	
	val = new Vector();
	val.addElement("txtAbonado");
	val.addElement(abonado.substring(0, 30));
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtSexo");
	val.addElement("");
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtEmail");
	val.addElement(afiliacion.getCorreo().toUpperCase());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtServidorEmail");
	val.addElement(afiliacion.getServidorCorreo().toUpperCase());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtTipoTelefono");
	val.addElement(afiliacion.getTipoTelefono());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("txtNroTelefono");
	val.addElement(afiliacion.getNumeroTelefono());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("txtIndicadorCont");
	val.addElement(afiliacion.getViaConfirmacion());
	valores.addElement(val);

			
	val = new Vector();
	val.addElement("txtTipoDocumento");
	val.addElement(afiliacion.getTipoDocumento());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtNroDocumento");
	val.addElement(afiliacion.getNroDocumento());
	valores.addElement(val);	
		
	val = new Vector();
	val.addElement("txtFlagestado");
	val.addElement(afiliacion.getFlagEstado());
	valores.addElement(val);	
		
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	AfiliacionImpl afiliacionDA = new AfiliacionImpl();
	afiliacionDA.setCuenta(afiliacion.getCuenta());
	afiliacionDA.setEmpresaMostrar(afiliacion.getEmpresaMostrar());
	afiliacionDA.setServicioMostrar(afiliacion.getServicioMostrar());
	afiliacionDA.setNumSuministro(afiliacion.getNumSuministro());
	afiliacionDA.setTipoDocumento(afiliacion.getTipoDocumento());
	afiliacionDA.setNroDocumento(afiliacion.getNroDocumento());
	afiliacionDA.setEmail(afiliacion.getEmail());
	afiliacionDA.setTelContactoMostrar(afiliacion.getTelContactoMostrar());
	afiliacionDA.setViaConfMostrar(afiliacion.getViaConfMostrar());
	afiliacionDA.setDocMostrar(afiliacion.getDocMostrar());
	afiliacionDA.setFlagEstado(afiliacion.getFlagEstado());
	afiliacionDA.setNumeroTelefono(afiliacion.getNumeroTelefono());
	afiliacionDA.setTelNumMostrar(afiliacion.getTelNumMostrar());
	afiliacionDA.setTope(afiliacion.getTope());

	if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
				
				afiliacionDA.setMontoDebito("------");
				
			}else{
				afiliacionDA.setMontoDebito(""+(ObjectUtil.tramaToBigDecimal(afiliacion.getMaximo())));
				
			}
	afiliacionDA.getModificacionDA(t,valores,ctas,usuario);
	return (Afiliacion)afiliacionDA;
	
}


//Banca Celular

public List consultaAliasBancaCel(String transaccion,Afiliacion afiliacion,String telefono,  String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
		
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("numCelular");
	val.addElement(telefono);
	valores.addElement(val);
	
	
	val = new Vector();
	val.addElement("indConsulta");
	val.addElement("4");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("alias");
	val.addElement(afiliacion.getAlias());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("txtOperacion");
	val.addElement(afiliacion.getServicioAfiliacion());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("txtAliasOperacion");
	val.addElement(afiliacion.getAliasOperacion());
	valores.addElement(val);
	
	
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
		
	AfiliacionImpl consultaBancaCelular = new AfiliacionImpl();
	
	List consulta = null;
	
	consulta= consultaBancaCelular.consultaAliasBancaCel(transaccion,valores,ctas);
	
	return consulta;
	
}



public List validacionBancaCel(String transaccion, String cuenta, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
		
		Transaction t = new Transaction(transaccion);
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		
	
		val = new Vector();
		val.addElement("codCuenta");
		val.addElement(cuenta);
		valores.addElement(val);	
		
		val = new Vector();
		val.addElement("codTransaccion");
		val.addElement("4420");
		valores.addElement(val);	
		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		AfiliacionImpl consultaBancaCelular = new AfiliacionImpl();
		
		List consulta = null;
		
		consulta= consultaBancaCelular.validacionBancaCel(t,valores,ctas);
		
		return consulta;
		
	}

public Afiliacion consultaCelularBancaCel(String transaccion, String cuenta,String celular, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("numCuenta");
	//val.addElement("04042403496");
	val.addElement(cuenta);
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numCelular");
	val.addElement("51"+celular);
	//val.addElement(cuenta);
	valores.addElement(val);	
		
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl consultaBancaCelular = new AfiliacionImpl();
	
	String consulta = "";
	
	consultaBancaCelular.consultaCelularBancaCel(t,valores,ctas);
	
	return consultaBancaCelular;
	
}

public Afiliacion getAfiliacionBancaCel(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("txtNumCelular");
	val.addElement(afiliacion.getNumeroCelular());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtTipoCuenta");
	val.addElement(afiliacion.getTipoCuentaDestino());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtNumCuentaDestino");
	val.addElement(afiliacion.getNumeroCuentaDestino());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtNumCelularDestino");
	val.addElement(afiliacion.getNumeroServicio());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtTipoServicio");
	val.addElement(afiliacion.getEmpresa());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtAliasCuenta");
	val.addElement(afiliacion.getAlias());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtOperacion");
	val.addElement(afiliacion.getServicioAfiliacion());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtAliasOperacion");
	val.addElement(afiliacion.getAliasOperacion());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtCorreo1");
	val.addElement(afiliacion.getCorreo());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtCorreo2");
	val.addElement(afiliacion.getServidorCorreo());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtCuentaPropia");
	val.addElement(afiliacion.getCuentaPropia());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtOperador");
	val.addElement(afiliacion.getOperador());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtEntidad");
	val.addElement(afiliacion.getServicio());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("txtNumSuministro");
	val.addElement(afiliacion.getNumSuministro());
	valores.addElement(val);
	
	
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl afiliacionBancaCelular = new AfiliacionImpl();
	afiliacionBancaCelular.setCuenta(afiliacion.getCuenta());
	afiliacionBancaCelular.setServicioAfiliacion(afiliacion.getServicioAfiliacion());
	afiliacionBancaCelular.setNumeroCelular(afiliacion.getNumeroCelular());
	afiliacionBancaCelular.setOperadorOrigen(afiliacion.getOperadorOrigen());
	afiliacionBancaCelular.setMostrarTipoAfil(afiliacion.getMostrarTipoAfil());
	afiliacionBancaCelular.setMostrarNumAfil(afiliacion.getMostrarNumAfil());
	afiliacionBancaCelular.setAliasOperacion(afiliacion.getAliasOperacion());
	afiliacionBancaCelular.setEmail(afiliacion.getEmail());
	afiliacionBancaCelular.setEmpresaMostrar(afiliacion.getEmail());
	afiliacionBancaCelular.setServicioMostrar(afiliacion.getServicioMostrar());
		if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
		afiliacionBancaCelular.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_CLARO);
	}else{
	if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
		afiliacionBancaCelular.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
		}
	}

			
	afiliacionBancaCelular.getAfiliaBancaCel(t,valores,ctas,usuario);
	
		
	return (Afiliacion)afiliacionBancaCelular;
	
}
public Afiliacion getDesafiliacionBancaCel(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		
	
	
	val = new Vector();
	val.addElement("txtNumCelular");
	val.addElement(afiliacion.getNumeroCelular());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("txtAliasCuenta");
	val.addElement(afiliacion.getAlias());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtOperacion");
	val.addElement(afiliacion.getServicioAfiliacion());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("txtAliasOperacion");
	val.addElement(afiliacion.getAliasOperacion());
	valores.addElement(val);	
		
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	AfiliacionImpl desafiliacion = new AfiliacionImpl();
	desafiliacion.setCuenta(afiliacion.getCuenta());
	desafiliacion.setMostrarOperador(afiliacion.getMostrarOperador());
	desafiliacion.setNumeroCelular(afiliacion.getNumeroCelular());
	desafiliacion.setMostrarNumAfil(afiliacion.getMostrarNumAfil());
	desafiliacion.setAliasOperacion(afiliacion.getAliasOperacion());
	desafiliacion.getDesafiliacionBancaCel(t,valores,ctas,usuario);
	
	
	return (Afiliacion)desafiliacion;
	
}

public Afiliacion getVinculacionBancaCel(String transaccion,Afiliacion afiliacion,String clave, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		
	
	val = new Vector();
	val.addElement("numCuenta");
	val.addElement(afiliacion.getCuenta().getNumeroProducto());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numCelular");
	val.addElement("51"+afiliacion.getNumeroCelular());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("operador");
	val.addElement(afiliacion.getOperador());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("tipoOperacion");
	val.addElement(afiliacion.getTipoOperacion());
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("indicador");
	val.addElement("1");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("correo1");
	val.addElement(afiliacion.getCorreo());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("correo2");
	val.addElement(afiliacion.getServidorCorreo());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("pinblock");
	val.addElement(clave);
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("tarjeta");
	val.addElement("4214100487664544");
	valores.addElement(val);
		
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	AfiliacionImpl vinculacion = new AfiliacionImpl();
	vinculacion.setCuenta(afiliacion.getCuenta());
	
	vinculacion.setNumeroCelular(afiliacion.getNumeroCelular());
	if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
		vinculacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_CLARO);
	}else{
	if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
		vinculacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
		}
	}
	vinculacion.setTipoOperacion(afiliacion.getTipoOperacion());
	vinculacion.getVinculacionBancaCel(t,valores,ctas,usuario);
	
	
	return (Afiliacion)vinculacion;
	
}

//Datos del Cliente

public Afiliacion getConsultaDatosCliente(String transaccion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("tipoDocumento");
	val.addElement("");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("indicador");
	val.addElement("2");
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("numDocumento");
	val.addElement("");
	valores.addElement(val);	
	
	
	
	val = new Vector();
	val.addElement("numTarjeta");
	val.addElement(usuario.getTarjeta().getNumero());
	valores.addElement(val);	
	
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl consultaDatosCliente = new AfiliacionImpl();
	
	
	
	consultaDatosCliente.getConsultaDatosCliente(t,valores,ctas);
	GeneralTest gt = new GeneralTest();
	gt.generarLog(t,usuario,null,usuario);
	
	return consultaDatosCliente;
	
}

public Afiliacion getActualizaDatosCliente(String transaccion, String remoteAddress,Afiliacion afil,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("codServicio");
	val.addElement("04");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("codUbigeo");
	val.addElement(afil.getCodDepartamento()+afil.getCodProvincia()+afil.getCodDistrito());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("telFijo");
	val.addElement(afil.getTelFijo());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("telCelular");
	val.addElement(afil.getOperador()+afil.getTelCelular());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("telFijoLab");
	val.addElement(afil.getTelFijoLab());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("anexo");
	val.addElement(afil.getAnexo());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("tipoOperacion");
	val.addElement("1");
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("codCuenta");
	val.addElement(afil.getCuenta().getNumeroProducto());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("codCliente");
	val.addElement(afil.getCodCliente());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("indicador");
	val.addElement("2");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("tipoDoc");
	val.addElement("");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("tipoVia");
	val.addElement(afil.getTipoVia());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("nomVia1");
	val.addElement(afil.getNomVia1());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("nomVia2");
	val.addElement(afil.getNomVia2());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numero");
	val.addElement(afil.getNumero());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("bloque");
	val.addElement(afil.getBloque());
	valores.addElement(val);	
	
	
	val = new Vector();
	val.addElement("correoPer1");
	val.addElement(afil.getCorreoPersonal());
	valores.addElement(val);	
	
		
	val = new Vector();
	val.addElement("correoPer2");
	val.addElement(afil.getCorreoPersonal1());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("referencia1");
	val.addElement(afil.getReferencia());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("referencia2");
	val.addElement(afil.getReferencia1());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("correoAdicional1");
	val.addElement(afil.getCorreoAdicional());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("correoAdicional2");
	val.addElement(afil.getCorreoAdicional1());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numDocumento");
	val.addElement("");
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("manzana");
	val.addElement(afil.getManzana());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("lote");
	val.addElement(afil.getLote());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("piso");
	val.addElement(afil.getPiso());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("interior");
	val.addElement(afil.getInterior());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("flagConsentimiento");
	val.addElement(afil.getFlagConsentimiento());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("codOcupacion");
	val.addElement(afil.getCodOcupacion());
	valores.addElement(val);
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl actualizaDatosCliente = new AfiliacionImpl();
	actualizaDatosCliente.setCuenta(afil.getCuenta());
	actualizaDatosCliente.setMostrarOperador(afil.getMostrarOperador());
	if(afil.getCorreoPersonal() !=null&& afil.getCorreoPersonal1()!=null){actualizaDatosCliente.setCorreo(afil.getCorreoPersonal()+Constante.SEP_ALPHANUMERIC+afil.getCorreoPersonal1());}
	else{actualizaDatosCliente.setCorreo(Constante.DAT_CLIENTES_SIN_DESCRIPCION);}
	if(afil.getCorreoAdicional() !=null && afil.getCorreoAdicional1()!=null){actualizaDatosCliente.setCorreoAdicional(afil.getCorreoAdicional()+Constante.SEP_ALPHANUMERIC+afil.getCorreoAdicional1());}
	else{actualizaDatosCliente.setCorreoAdicional(Constante.DAT_CLIENTES_SIN_DESCRIPCION);}
	
	actualizaDatosCliente.getActualizaDatosCliente(t,valores,ctas,usuario,afil);
	

	
	return actualizaDatosCliente;
	
}

public Afiliacion getActualizaCelularCliente(String transaccion, Usuario usuario, String remoteAddress, String celular, String nombreOperador,Afiliacion afil,TransferenciaContactoImpl transferencia, HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("codigoCic");
	val.addElement(usuario.getCodigoCic());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("celular");
	val.addElement(celular);
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("flagConsulta");
	val.addElement("1");
	valores.addElement(val);
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl actualizaDatosCliente = new AfiliacionImpl();
	actualizaDatosCliente.setCuenta(afil.getCuenta());
	actualizaDatosCliente.getActualizaCelularCliente(t,valores,ctas,usuario,celular, nombreOperador);
	
	return actualizaDatosCliente;
	
}

public Afiliacion getConsultaMedioEnvio(String transaccion, Afiliacion afiliacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		

	val = new Vector();
	val.addElement("codProducto");
	val.addElement(afiliacion.getCodigoServicio());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("indicador");
	val.addElement("2");
	valores.addElement(val);	
		
	val = new Vector();
	val.addElement("numDocumento");
	val.addElement("");
	valores.addElement(val);	
			
	val = new Vector();
	val.addElement("numTarjeta");
	val.addElement(usuario.getTarjeta().getNumero());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numCuenta");
	val.addElement(afiliacion.getCuentaPropia());
	valores.addElement(val);	
	
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl consultaMedioEnvio = new AfiliacionImpl();
	
	
	
	consultaMedioEnvio.getConsultaMedioEnvio(t,valores,ctas);
	GeneralTest gt = new GeneralTest();
	gt.generarLog(t,usuario,null,usuario);
	
	return consultaMedioEnvio;
	
}

public Afiliacion getGuardarMedioEnvio(String transaccion, Afiliacion afiliacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numSecDir");
//	System.out.println("afiliacion.getNumSecDir():"+afiliacion.getNumSecDir());
	val.addElement(afiliacion.getNumSecDir());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("numItemDir");
//	System.out.println("afiliacion.getNumItemDir():"+afiliacion.getNumItemDir());
	val.addElement(afiliacion.getNumItemDir());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("codProducto");
	val.addElement(afiliacion.getCuenta().getTipoProducto());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("indicadorAct");
	val.addElement("1");
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("numCuenta");
	if(afiliacion.getCuenta().getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
		val.addElement(afiliacion.getCuenta().getNumeroProducto()+afiliacion.getCuenta().getNumeroDesembolso());
	}else{
		val.addElement(afiliacion.getCuenta().getNumeroProducto());	
	}
	
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("codCliente");
//	System.out.println("afiliacion.getCodCliente():"+afiliacion.getCodCliente());
	val.addElement(afiliacion.getCodCliente());
	valores.addElement(val);
		
	val = new Vector();
	val.addElement("indicador");
	val.addElement("2");
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("indicadorGrab");
	val.addElement("1");
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("numDocumento");
	val.addElement("");
	valores.addElement(val);	
					
	val = new Vector();
	val.addElement("codMedioEnvio");
	val.addElement(afiliacion.getCodMedioEnvio());
	valores.addElement(val);	
			
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl guardarMedioEnvio = new AfiliacionImpl();
	
	
	
	guardarMedioEnvio.getGuardarMedioEnvio(t,valores,ctas);
	GeneralTest gt = new GeneralTest();
	gt.generarLog(t,usuario,null,usuario);
	
	return guardarMedioEnvio;
	
}

public Afiliacion getGuardarDirCorresp(String transaccion, Afiliacion afiliacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("codUbigeo");
	val.addElement(afiliacion.getCodDepartamento()+afiliacion.getCodProvincia()+afiliacion.getCodDistrito());
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("indicadorAct");
	val.addElement("1");
	valores.addElement(val);
		
	val = new Vector();
	val.addElement("codCliente");
	val.addElement(afiliacion.getCodCliente());
	valores.addElement(val);
		
	val = new Vector();
	val.addElement("indicador");
	val.addElement("2");
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("indicadorGrab");
	val.addElement("2");
	valores.addElement(val);
	
	val = new Vector();
	val.addElement("numDocumento");
	val.addElement("");
	valores.addElement(val);	

	val = new Vector();
	val.addElement("tipoVia");
	val.addElement(afiliacion.getTipoVia());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("nomVia1");
	val.addElement(afiliacion.getNomVia1());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("nomVia2");
	val.addElement(afiliacion.getNomVia2());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("numero-bloque-manz");
	String numBloqueManz = ObjectUtil.setearEspacioRight(afiliacion.getNumero(), 10)+ObjectUtil.setearEspacioRight(afiliacion.getBloque(), 10)+
	ObjectUtil.setearEspacioRight(afiliacion.getManzana(), 10);
	
	val.addElement(numBloqueManz);
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("lote-piso-int");
	String lotePisoInt = ObjectUtil.setearEspacioRight(afiliacion.getLote(), 10)+ObjectUtil.setearEspacioRight(afiliacion.getPiso(), 10)+
	ObjectUtil.setearEspacioRight(afiliacion.getInterior(), 10);
	val.addElement(lotePisoInt);
	
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("referencia1");
	val.addElement(afiliacion.getReferencia());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("referencia2");
	val.addElement(afiliacion.getReferencia1());
	valores.addElement(val);	
			
	Vector ctas=new Vector();
	Vector v=new Vector();
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement("");
	v.addElement(remoteAddress);
	ctas.addElement(v);
	t.setValues(valores);
	t.setCuentas(ctas);	
	
	AfiliacionImpl guardarMedioEnvio = new AfiliacionImpl();
	
	
	
	guardarMedioEnvio.getGuardarMedioEnvio(t,valores,ctas);
	GeneralTest gt = new GeneralTest();
	gt.generarLog(t,usuario,null,usuario);
	
	return guardarMedioEnvio;
	
}


	//CLAVES DINAMICAS -- MDB
	public Long getSecuencia(String tipAfiliacion,String numTarjeta)throws Exception {
	AfiliacionImpl afil_ = new AfiliacionImpl();
	return afil_.getMaximaSecuencia(tipAfiliacion,numTarjeta);
	}


	public Afiliacion getAfiliacionTelegiro(Usuario usuario, Afiliacion afiliacion) throws Exception {
	afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
	afiliar(usuario,afiliacion);
	AfiliacionImpl afil_ = new AfiliacionImpl(afiliacion);
	afil_.cargar();
	return afil_;
	}

	
	public String mostrarIdBNWSF02() throws Exception{
		
		WSF02 wsf02 = new WSF02();
		return wsf02.mostrarIdBNWSF02();
	
	}
	
	public Integer insertAfiliacion(Afiliacion afiliacion, Usuario usuario, Integer idAfil, String ip, String claveValid) throws Exception{
		
		WSF02 wsf02 = new WSF02();
		return wsf02.insertAfiliacion(afiliacion, usuario, idAfil, ip, claveValid);
	}

	public List consultarBNWSF02(String numTarjeta) throws SQLException{
		
		WSF02 wsf02 = new WSF02();
		return wsf02.consultarBNWSF02(numTarjeta);
		
	}

	public Afiliacion getConsultaDatosClienteLogin(String transaccion, String remoteAddress, Usuario usuario, HttpServletRequest request) throws Exception {
		Transaction t = new Transaction(transaccion);
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		

		val = new Vector();
		val.addElement("tipoDocumento");
		val.addElement("");
		valores.addElement(val);	
		
		val = new Vector();
		val.addElement("indicador");
		val.addElement("2");
		valores.addElement(val);	
		
		
		val = new Vector();
		val.addElement("numDocumento");
		val.addElement("");
		valores.addElement(val);	
		
		
		
		val = new Vector();
		val.addElement("numTarjeta");
		val.addElement(usuario.getTarjeta().getNumero());
		valores.addElement(val);	
		
		
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);
		t.setValues(valores);
		t.setCuentas(ctas);	
		
		AfiliacionImpl consultaDatosCliente = new AfiliacionImpl();
		
		consultaDatosCliente.getConsultaDatosCliente(t,valores,ctas);
		
		return consultaDatosCliente;
	}
	
	/*
	public void guardarLogDomicilio(String transaccion,  String remoteAddress, Usuario usuario,DomicilioCliente domicilio,HttpServletRequest request) throws Exception{
		
//		Guardar Log
		String plantilla = "";	
		GeneralTest gt = new GeneralTest(); 
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
			
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);

		if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_ALTA_DOMICILIO)){
			plantilla = Constante.REFRENDO_NCB_ALTA_DOMICILIO;
		}else{
			if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_ACT_DOMICILIO)){
				plantilla = Constante.REFRENDO_NCB_ACT_DOMICILIO;
			}
			
		}

		gt.generarLog5(t,usuario,plantilla,domicilio, request);
		
	}


	public void guardarLogTelefono(String transaccion,  String remoteAddress, Usuario usuario,TelefonoCliente telefono,String tipoTelefono,HttpServletRequest request) throws Exception{
		
//		Guardar Log
		GeneralTest gt = new GeneralTest(); 
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		String plantilla = "";	
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);
		

		if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_ALTA_TELF)){
			
			if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
				plantilla = Constante.REFRENDO_NCB_TELEFONO_ALTA_TEL_FIJO;
			}
			if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
				plantilla = Constante.REFRENDO_NCB_TELEFONO_ALTA_TEL_MOV;
			}
			if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
				plantilla = Constante.REFRENDO_NCB_TELEFONO_ALTA_TEL_LAB; 
			}
			if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
				plantilla = Constante.REFRENDO_NCB_TELEFONO_ALTA_TEL_FIJO; 
			}
			
		}else{
			if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_BAJA_TELF)){
				
				if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
					plantilla = Constante.REFRENDO_NCB_TELEFONO_BAJA_TEL_FIJO;
				}
				if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
					plantilla = Constante.REFRENDO_NCB_TELEFONO_BAJA_TEL_MOV;
				}
				if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
					plantilla = Constante.REFRENDO_NCB_TELEFONO_BAJA_TEL_LAB;
				}
				if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
					plantilla = Constante.REFRENDO_NCB_TELEFONO_BAJA_TEL_FIJO;
				}
				
				
			
			}else{
				
				if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_ACT_TELF)){
					
					if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_FIJO)){
						plantilla = Constante.REFRENDO_NCB_TELEFONO_ACT_TEL_FIJO;
					}
					if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_MOVIL)){
						plantilla = Constante.REFRENDO_NCB_TELEFONO_ACT_TEL_MOV;
					}
					if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_LABORAL)){
						plantilla = Constante.REFRENDO_NCB_TELEFONO_ACT_TEL_LAB;
					}
					if(tipoTelefono.equals(Constante.ACTUALIZ_DATOS_NCB_TEL_EXTRANJERO)){
						plantilla = Constante.REFRENDO_NCB_TELEFONO_ACT_TEL_FIJO;
					}
					
				}
			}
		}

		gt.generarLog5(t,usuario,plantilla,telefono, request);
		
			
	}

	public void guardarLogEmail(String transaccion,  String remoteAddress, Usuario usuario,CorreoCliente correo,HttpServletRequest request) throws Exception{
		
//		Guardar Log
		GeneralTest gt = new GeneralTest(); 
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		String plantilla = "";
			
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);
		
		if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_ALTA_EMAIL)){
			plantilla = Constante.REFRENDO_NCB_EMAIL_ALTA;
		}else{
			if(transaccion.equals(Constante.ACTUALIZ_DATOS_NCB_COD_BAJA_EMAIL)){
				plantilla = Constante.REFRENDO_NCB_EMAIL_BAJA;
			}
			else{
				plantilla = Constante.REFRENDO_NCB_EMAIL_ACT;
			}
		}

		gt.generarLog5(t,usuario,plantilla,correo, request);
		
	}		
	
public void guardarLogIndicador(String transaccion,  String remoteAddress, Usuario usuario,Afiliacion indicador,HttpServletRequest request) throws Exception{
		
//		Guardar Log
		GeneralTest gt = new GeneralTest(); 
		Transaction t = new Transaction(transaccion);
		
		Vector val;
		Vector valores = new Vector();
		
		String plantilla = "";
			
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);
		Vector ctas=new Vector();
		Vector v=new Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(remoteAddress);
		ctas.addElement(v);		
		
		t.setValues(valores);
		t.setCuentas(ctas);
		
				
		if(indicador.getFlagConsentimiento().equals(Constante.ACTUALIZ_DATOS_NCB_MARCA_LPDP_SI)){
			plantilla = Constante.REFRENDO_NCB_INDICADOR_SI;
		}else{
			plantilla = Constante.REFRENDO_NCB_INDICADOR_NO;
		}

		gt.generarLog5(t,usuario,plantilla,indicador, request);
		
	}	
	
		*/
	
}
