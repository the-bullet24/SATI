package pe.bn.trasferenciacontacto.facade.impl;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.form.DataListas;
import pe.bn.login.action.LoginAction;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.bn.trasferenciacontacto.facade.TransferenciaContactoFacade;
import pe.com.bn.sati.common.LoggerSati;
import pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest;
import pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse;
import pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest;
import pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse;
import pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest;
import pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest;
import pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse;
import pe.com.bn.wscn.bean.interoperabilidad.BarridoData;
import pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest;
import pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse;
import pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest;
import pe.com.bn.wscn.bean.interoperabilidad.Motivo;
import pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse;
import pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest;
import pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse;
import pe.com.bn.wscn.ws.ServicioBNInteroperabilidadProxy;
import pe.com.bn.wscn.ws.ServicioBNSmsProxy;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.MsgComunication;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;

public class TransferenciaContactoFacadeImpl implements TransferenciaContactoFacade {
	
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaContactoFacadeImpl.class.getName());
	
	public TransferenciaContacto enviaSmsCelularContacto(Usuario usuario ,TransferenciaContactoImpl transf,String remoteAddress) throws Exception{
		
		String tipoDoc="";
		String numDoc="";
		TransferenciaContacto trasfResponse = new TransferenciaContactoImpl();
		
		
		ServicioBNSmsProxy proxySms = new ServicioBNSmsProxy();
		GeneraSmsAfiliacionRequest request = new GeneraSmsAfiliacionRequest();
		GeneraSmsAfiliacionResponse response = new GeneraSmsAfiliacionResponse();
		request.setModeloCelular("WEB");
		request.setCicCliente(usuario.getCodigoCic());
		request.setCodCanal("WEB");
		request.setNombreCliente(usuario.getNombre());
	
		if(transf.getIndCambioCelular().equals("1")){
			request.setNumCelular(transf.getNumeroCelularCambio());
		}else{
			request.setNumCelular(usuario.getCelularFormat());
		}
		
				
		if(usuario.getCodigoCLDI() != null){
			tipoDoc=usuario.getCodigoCLDI().substring(12,13);
			numDoc=usuario.getCodigoCLDI().substring(13);	
			numDoc= numDoc.substring(4);
		
		}
		request.setNumDocumento(numDoc);
		request.setTipoDocumento(tipoDoc);
		
		response=proxySms.opGenerarSmsAfiliacion(request);
		
	
		if (response.getCodResult().equals("00000")){
			System.out.println("response.getMsgError():"+response.getMsgError());
			System.out.println("response.getData().getCodeSMS():"+response.getData().getCodeSMS());
		}else{
			throw new ArrayRuleException(ConstanteError.GENERICO,response.getMsgError());
		}
		return trasfResponse;
	}
	
public TransferenciaContacto validaCelularContacto(Usuario usuario ,String codeSMS,TransferenciaContactoImpl transf,String remoteAddress) throws Exception{
		
		String tipoDoc="";
		String numDoc="";
		TransferenciaContacto trasfResponse = new TransferenciaContactoImpl();
		
		
		ServicioBNSmsProxy proxySms = new ServicioBNSmsProxy();
		ValidaSmsAfiliacionRequest request = new ValidaSmsAfiliacionRequest();
		ValidaSmsAfiliacionResponse response = new ValidaSmsAfiliacionResponse();
		
		request.setCanal("WEB");
		request.setCicCliente(usuario.getCodigoCic());
		request.setCodeSMS(codeSMS);
		request.setModeloCelular("WEB");

		if(transf.getIndCambioCelular().equals("1")){
			request.setNumCelular(transf.getNumeroCelularCambio());
		}else{
			request.setNumCelular(usuario.getCelularFormat());
		}
	
		if(usuario.getCodigoCLDI() != null){
			tipoDoc=usuario.getCodigoCLDI().substring(12,13);
			numDoc=usuario.getCodigoCLDI().substring(13);	
			numDoc= numDoc.substring(4);
		
		}
		request.setNumDocumento(numDoc);
		request.setTipoDocumento(tipoDoc);
		
		
		response=proxySms.opValidarSMSAfliacion(request);
		
		if (response.getCodResult().equals("00000")){
			System.out.println("response.getMsgError():"+response.getMsgError());
			System.out.println("response.getData().getNumOperacion():"+response.getData().getNumOperacion());
		}else{
			throw new ArrayRuleException(ConstanteError.GENERICO,response.getMsgError());
		}
		return trasfResponse;
	}
	

public TransferenciaContacto registroContacto(Usuario usuario , Cuenta cuenta,TransferenciaContactoImpl transf,String remoteAddress,HttpServletRequest requestHttp) throws Exception{
	
	String tipoDoc="";
	String numDoc="";
	TransferenciaContacto responseReg = new TransferenciaContactoImpl();
	responseReg.setCuentaOrigen(cuenta);
	
	Transaction t = new Transaction(Constante.COD_TXN_AFILIACION_CONTACTO);
	
	Vector val;
	Vector valores = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(Constante.COD_TXN_AFILIACION_CONTACTO);
	valores.addElement(val);

	val = new Vector();
	val.addElement("txtNumeroTarjeta");
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
	
	ServicioBNInteroperabilidadProxy servInter = new ServicioBNInteroperabilidadProxy();
	OperacionRequest request = new OperacionRequest();
	OperacionResponse response = new OperacionResponse();
	
	request.setCanal("WEB");
	request.setCciCliente(usuario.getCciCliente());
	request.setCic(usuario.getCodigoCic());
	request.setModeloCelular("WEB");
	request.setNombreCliente(usuario.getNombre());
	//request.setNumCelular(usuario.getCelularFormat())
	request.setNumcuenta(transf.getCuenta());
		
	if(transf.getIndCambioCelular().equals("1")){
		request.setNumCelular(transf.getNumeroCelularCambio());
	}else{
		request.setNumCelular(usuario.getCelularFormat());
	}
	
	
	if(usuario.getCodigoCLDI() != null){
		tipoDoc=usuario.getCodigoCLDI().substring(12,13);
		numDoc=usuario.getCodigoCLDI().substring(13);	
		numDoc= numDoc.substring(4);
	
	}
	request.setNumDcoumento(numDoc);
	request.setTipoDocumento(tipoDoc);
	
	response=servInter.opRegistroContacto(request);
	
	if (response.getCodResult().equals("00000")){
		responseReg.setNumOperacion(response.getData().getNumOperacion());
		responseReg.setFechaAfiliacion(response.getData().getFecha());
		responseReg.setCciContacto(response.getData().getCciCliente());
		responseReg.setNumeroCelularContacto(request.getNumCelular());
		responseReg.setCodigoEntidad(usuario.getEntidadCuenta());
		responseReg.setDescEntidad(usuario.getEntidadCuentaDesc());
	
	}else{
		//MGL - ERROR DE INTERMITENCIA
		System.out.println("codError"+response.getCodResult());
		System.out.println("desError"+response.getMsgError());		
		String mensaje = "";
		if(response.getCodResult().equals("78013")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("79999")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("69990")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else{			
			mensaje = response.getMsgError();
		}
		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);		
	}	
	
	GeneralTest gt = new GeneralTest();
	gt.generarLog3(t,usuario,Constante.REFRENDO_AFILIACION_CONTACTO,responseReg, requestHttp);
	
	return responseReg;
}

public TransferenciaContacto eliminarContacto(Usuario usuario , TransferenciaContactoImpl transf,String remoteAddress,HttpServletRequest requestHttp) throws Exception{
	
	ServicioBNInteroperabilidadProxy servInter = new ServicioBNInteroperabilidadProxy();
	DesafiliacionRequest request = new DesafiliacionRequest();
	OperacionResponse response = new OperacionResponse();
	TransferenciaContacto responseEli = new TransferenciaContactoImpl();
	
	Transaction t = new Transaction(Constante.COD_TXN_DESAFILIACION_CONTACTO);
	
	Vector val;
	Vector valores = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(Constante.COD_TXN_DESAFILIACION_CONTACTO);
	valores.addElement(val);

	val = new Vector();
	val.addElement("txtNumeroTarjeta");
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
	
	request.setCanal("WEB");	
	request.setMotivoDesafiliacion(transf.getCodMotivoDesf());
	request.setNumCelular(transf.getNumeroCelularContacto());
	request.setCciOrigen(transf.getCciContacto());
	request.setCicCliente(usuario.getCodigoCic());
	request.setCciOrigen(usuario.getCciCliente());
	System.out.println("usuario.getCodigoCic():::::"+usuario.getCodigoCic());
	System.out.println("usuario.getCciCliente():::::"+usuario.getCciCliente());
	
	response=servInter.opEliminarCliente(request);
	
	if (response.getCodResult().equals("00000")){
		System.out.println("response.getMsgError():"+response.getMsgError());
		System.out.println("response.getData().getNumOperacion():"+response.getData().getNumOperacion());
		responseEli.setNumOperacion(response.getData().getNumOperacion());
		responseEli.setFechaDesafiliacion(response.getData().getFecha());
		responseEli.setCuentaOrigen(transf.getCuentaOrigen());
		responseEli.setComentarioDesf(transf.getComentarioDesf());
		responseEli.setDescripMotivoDesf(transf.getDescripMotivoDesf());
	}else{
		//MGL - MANEJO DE ERROR DE INTERMITENCIA
		System.out.println("codError"+response.getCodResult());
		System.out.println("desError"+response.getMsgError());		
		String mensaje = "";
		if(response.getCodResult().equals("78013")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("79999")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("69990")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;	
		}else{			
			mensaje = response.getMsgError();
		}
		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);	
	
	}
	
	GeneralTest gt = new GeneralTest();
	gt.generarLog3(t,usuario,Constante.REFRENDO_DESAFILIACION_CONTACTO,responseEli, requestHttp);
	return responseEli;
}

public TransferenciaContacto actualizarContacto(Usuario usuario , TransferenciaContactoImpl transf,String remoteAddress,HttpServletRequest requestHttp) throws Exception{
	System.out.println("actualizarContacto");
	String tipoDoc="";
	String numDoc="";
	TransferenciaContacto responseAct = new TransferenciaContactoImpl();
	
	ServicioBNInteroperabilidadProxy servInter = new ServicioBNInteroperabilidadProxy();
	ActualizacionRequest request = new ActualizacionRequest();
	OperacionResponse response = new OperacionResponse();
	
	Transaction t = new Transaction(Constante.COD_TXN_ACTUALIZACION_CONTACTO);
	
	Vector val;
	Vector valores = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement("CO02");
	valores.addElement(val);

	val = new Vector();
	val.addElement("txtNumeroTarjeta");
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
	
	request.setCanal("WEB");	
	request.setModeloCelular("WEB");
	request.setNumCelular(transf.getNumeroCelularCambio());
	request.setCic(usuario.getCodigoCic());
	request.setCcicliente(usuario.getCciCliente());
	System.out.println("usuario.getCodigoCic():::::"+usuario.getCodigoCic());
	System.out.println("usuario.getCciCliente():::::"+usuario.getCciCliente());
	
		
	response=servInter.opActualizarCliente(request);
	
	if (response.getCodResult().equals("00000")){
		System.out.println("response.getMsgError():"+response.getMsgError());
		System.out.println("response.getData().getNumOperacion():"+response.getData().getNumOperacion());
		responseAct.setNumOperacion(response.getData().getNumOperacion());
		responseAct.setFechaAfiliacion(response.getData().getFecha());
		responseAct.setCciContacto(response.getData().getCciCliente());
		responseAct.setNumeroCelularContacto(request.getNumCelular());
		responseAct.setCuentaOrigen(transf.getCuentaOrigen());
		responseAct.setCodigoEntidad(usuario.getEntidadCuenta());
		responseAct.setDescEntidad(usuario.getEntidadCuentaDesc());
	}else{
		//MGL - manejo de error en intermitencia
		System.out.println("codError"+response.getCodResult());
		System.out.println("desError"+response.getMsgError());		
		String mensaje = "";
		if(response.getCodResult().equals("78013")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("79999")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("69990")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;	
		}else{			
			mensaje = response.getMsgError();
		}
		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);	
	}
	
	GeneralTest gt = new GeneralTest();
	gt.generarLog3(t,usuario,Constante.REFRENDO_ACTUALIZACION_CONTACTO,responseAct, requestHttp);
	
	return responseAct;
}

public TransferenciaContacto consultarContacto(Usuario usuario , String remoteAddress) throws Exception{
	
	String tipoDoc="";
	String numDoc="";
	
	TransferenciaContacto responseCon = new TransferenciaContactoImpl();
	
	
	ServicioBNInteroperabilidadProxy servInter = new ServicioBNInteroperabilidadProxy();
	AfilicionRequest request = new AfilicionRequest();
	AfilicionResponse response = new AfilicionResponse();
	
	request.setCiccliente(usuario.getCodigoCic());	
	request.setCcicliente(usuario.getCciCliente());
	System.out.println("usuario.getCodigoCic():::::"+usuario.getCodigoCic());
	System.out.println("usuario.getCciCliente():::::"+usuario.getCciCliente());
		
	response=servInter.opConsultaAfilicion(request);
	responseCon.setCodigoErr(response.getCodResult());
	
	if (response.getCodResult().equals("00000")){
		System.out.println("response.getMsgError():"+response.getMsgError());
		System.out.println("response.getData().getNombreCliente():::"+response.getData().getNombreCliente());
		responseCon.setNombreClienteContacto(response.getData().getNombreCliente());
		responseCon.setNroCuentaContacto(response.getData().getNroCuenta());
		responseCon.setCciContacto(response.getData().getCci());
		responseCon.setNumeroCelularContacto(response.getData().getNumeroCelular());
		responseCon.setEstadoAfil(response.getData().getEstadoAfiliacion());
	}else if(response.getCodResult().equals("89989")){
		responseCon.setEstadoAfil(Constante.CONSTANTE_SIN_AFIL_CONTACTO);
	}
	else{
		//MGL - manejo de error en intermitencia
		System.out.println("codError"+response.getCodResult());
		System.out.println("desError"+response.getMsgError());		
		String mensaje = "";
		if(response.getCodResult().equals("78013")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("79999")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(response.getCodResult().equals("69990")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;	
		}else{			
			mensaje = response.getMsgError();
		}
		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);		
	}
	return responseCon;
}

public java.util.List obtenerMotivoDes( Usuario usuario ,  String remoteAddress) throws Exception{
	
	java.util.List<Motivo> listMot = new java.util.ArrayList<Motivo>();
	
	ServicioBNInteroperabilidadProxy servInter = new ServicioBNInteroperabilidadProxy();
	MotivoDesafilaicionResponse response = new MotivoDesafilaicionResponse();
			
	response=servInter.opObtenerMotivDesaInteroperabilidad();
	
	if (response.getCodResult().equals("00000")){
		
		pe.com.bn.wscn.bean.interoperabilidad.Motivo motivos[]= response.getMotivos();
		
		for (int i = 0; i < motivos.length; i++) {
			pe.com.bn.wscn.bean.interoperabilidad.Motivo mot = (pe.com.bn.wscn.bean.interoperabilidad.Motivo)motivos[i];
			
			if(mot.getIsActivo().equals("A"))listMot.add(motivos[i]);
			
		}
		
		
	}else{
		System.out.println(response.getMsgError());
		throw new ArrayRuleException(ConstanteError.GENERICO,response.getMsgError());
	}
	return listMot;
	}

public java.util.List barridoClientes( Usuario usuario , String celular, String remoteAddress) throws Exception{
	
	System.out.println("barridoClientes...");
	java.util.List<BarridoData> listCel = new java.util.ArrayList<BarridoData>();
	
	ServicioBNInteroperabilidadProxy servInter = new ServicioBNInteroperabilidadProxy();
	BarridoRequest requestBarrido = new BarridoRequest();
	BarridoResponse responseBarrido = new BarridoResponse();
	
	requestBarrido.setCanal("WEB");
	requestBarrido.setNumCelular(celular);
	
	System.out.println("requestBarrido.getNumCelular():"+requestBarrido.getNumCelular());
			
	responseBarrido=servInter.opBarridoClientes(requestBarrido);
	
	
	if (responseBarrido.getCodResult()!= null && responseBarrido.getCodResult().equals("00000")){
		
		BarridoData data[]= responseBarrido.getData();
		
		for (int i = 0; i < data.length; i++) {
			BarridoData barridoData = (BarridoData)data[i];
			System.out.println("barridoData.getDescEntidad()"+barridoData.getDescEntidad());
			listCel.add(data[i]);
			
		}
	
	}else{
		//MGL - manejo de error en intermitencia
		System.out.println("codError"+responseBarrido.getCodResult());
		System.out.println("desError"+responseBarrido.getMsgError());		
		String mensaje = "";
		if(responseBarrido.getCodResult().equals("78013")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(responseBarrido.getCodResult().equals("79999")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;
		}else if(responseBarrido.getCodResult().equals("69990")){
			mensaje = Constante.MENSAJE_ERROR_INTERMITENCIA;	
		}else if(responseBarrido.getCodResult().equals("69989")){
			mensaje = Constante.MENSAJE_NUMERO_NO_ASOCIADO;
		}else{			
			mensaje = responseBarrido.getMsgError();
		}
		throw new ArrayRuleException(ConstanteError.GENERICO,mensaje);
	}
	return listCel;
}

public TransferenciaContacto actualizaDatosCliente(String transaccion, String remoteAddress,Afiliacion afil,Usuario usuario,HttpServletRequest request) throws Exception{
	
	Transaction t = new Transaction(transaccion);
	Vector valores = new Vector();
	Vector val = new Vector();
	
	val = new Vector();
	val.addElement("transaccion");
	val.addElement(transaccion);
	valores.addElement(val);		
	
	val = new Vector();
	val.addElement("telCelular");
	val.addElement(afil.getOperador()+afil.getTelCelular());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("codCuenta");
	val.addElement(afil.getCuenta().getNumeroProducto());
	valores.addElement(val);	
	
	val = new Vector();
	val.addElement("codCliente");
	val.addElement(afil.getCodCliente());
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
	
	return null;
	
}
}
