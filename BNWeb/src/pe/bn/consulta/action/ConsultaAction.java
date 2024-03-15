
package pe.bn.consulta.action;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.consulta.domain.Prestamo;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.consulta.reportes.PedidoFicha;
import pe.bn.login.domain.IngresoTarjeta;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.tcredito.domain.Pago;
import pe.bn.tcredito.domain.impl.PagoImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Itf;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AtriTransferenciaImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.ItfImpl;
import pe.cosapi.domain.impl.MovimientoImpl;
import pe.cosapi.domain.impl.NotificacionImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.UsuarioImpl;

public class ConsultaAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(ConsultaAction.class.getName());
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		BNAplicacion aplicacion = BNAplicacion.getInstance();
	
		request.getSession().setAttribute("mensajeDiferenciaSaldo",((Vector)aplicacion.getMensajePorCodigo("CL01","00002")).elementAt(2).toString());
		request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","MB001")).elementAt(2).toString());
		request.setAttribute("mensajeHora",((Vector)aplicacion.getMensajePorCodigo("CL01","MB002")).elementAt(2).toString());
		request.setAttribute("mensajeClaveDinamica",((Vector)aplicacion.getMensajePorCodigo("CL01","CD001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeSaldoAhorro",((Vector)aplicacion.getMensajePorCodigo("CL01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeHorario",((Vector)aplicacion.getMensajePorCodigo("PP00","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCCI",((Vector)aplicacion.getMensajePorCodigo("CL01","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeItf",((Vector)aplicacion.getMensajePorCodigo("CL01","ITF01")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeWap",((Vector)aplicacion.getMensajePorCodigo("CL01","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeHorario",((Vector)aplicacion.getMensajePorCodigo("CL01","CTE07")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeNota",((Vector)aplicacion.getMensajePorCodigo("CL01","CTE08")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeTarjetaDeCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC003")).elementAt(2).toString());
		
		request.getSession().removeAttribute("06");
    	request.getSession().removeAttribute("02");
    	request.getSession().removeAttribute("13");
	
    	Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
    
		
    	Vector valores = new Vector();
			
		valores.addElement(ObjectUtil.getVectorComponent("transaccion","LG06"));
		valores.addElement(ObjectUtil.getVectorComponent("cboTipoPersona",usuario.getTipoPersona()));
		valores.addElement(ObjectUtil.getVectorComponent("cboTipoTarjeta",usuario.getTarjeta().getTipo()));
		valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",usuario.getTarjeta().getNumero()));
		
	
		java.util.Vector ctas=new java.util.Vector();
		java.util.Vector v=new java.util.Vector();
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
		v.addElement(request.getRemoteAddr());
		ctas.addElement(v);
		
		String cic = usuario.getCodigoCic();
		String codigoCLDI = usuario.getCodigoCLDI();
		String montoLimite = usuario.getMontoLimite();
		IngresoTarjeta ingreso = usuario.getIngreso();
        String flagActDatosHost = usuario.getFlagActualizaDatoHost();
        
        //recuperando valores de atributos
        
        String estadoOperaciones = usuario.getEstadoOperaciones().getEstadoOperaciones();
        String estadoNotificacion = usuario.getNotificacion().getEstadoNotificacion();
        String medioNotificacion = usuario.getNotificacion().getMedioNotificacion();
        String montoNotificacion = usuario.getNotificacion().getMontoNotificacion();
        String tipoMonedaNotificacion = usuario.getNotificacion().getTipoMonedaNotificacion();
        String cciCliente = usuario.getCciCliente();
        String entidadCuenta= usuario.getEntidadCuenta();
        String entidadCuentaDesc= usuario.getEntidadCuentaDesc();
        String correo = usuario.getEmail();
        String celular =  usuario.getCelular();
        String operadorCelular = usuario.getOpeCelular();
        String celularFormat =usuario.getCelularFormat();
        
        Map mapCuentas = usuario.getMapCuentas();
        List cuentas = usuario.getCuentas();
        String flagActdatos = usuario.getFlagActualizaDatoHost();
        
        //METODO DEL DEMONIO

		usuario = FacadeFactory.getLoginFacade().autenticar("LG06"+codigoCLDI,"1",valores,ctas, usuario.getTipoDocumento(), usuario.getNumDocumento());
		usuario.setEmail(correo);
		usuario.setCciCliente(cciCliente);
		usuario.setEntidadCuenta(entidadCuenta);
		usuario.setEntidadCuentaDesc(entidadCuentaDesc);
		usuario.setCelular(celular);
		usuario.setCelularFormat(celularFormat);
		usuario.setOpeCelular(operadorCelular);
		usuario.setFlagActualizaDatoHost(flagActdatos);
		
		//seteando datos de atributos
//		UsuarioImpl usuarioImpl = new UsuarioImpl();
//		usuarioImpl.obtenerCuentas(codigoCLDI);
		
//		usuario.setCuentas(cuentas);
//		usuario.setMapCuentas(mapCuentas);
		
		NotificacionImpl noti = new NotificacionImpl();	
		AtriTransferenciaImpl operaciones = new AtriTransferenciaImpl();
		
		
		operaciones.setEstadoOperaciones(estadoOperaciones);
		noti.setEstadoNotificacion(estadoNotificacion);
		noti.setMedioNotificacion(medioNotificacion);
		noti.setMontoNotificacion(montoNotificacion);
		noti.setTipoMonedaNotificacion(tipoMonedaNotificacion);
		
        usuario.setEstadoOperaciones(operaciones);
        usuario.setNotificacion(noti);
        
		
		usuario.setCodigoCic(cic);
		usuario.setCodigoCLDI(codigoCLDI);
		usuario.setMontoLimite(montoLimite);
		usuario.setIngreso(ingreso);
        usuario.setFlagActualizaDatoHost(flagActDatosHost);
		
		usuario.setTipoTarjeta(usuario.getTarjeta().getTipo());
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		usuario.setClaveDinamica(param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
//		List listaCuentas = usuario.getCuentas();
//		for(int i=0;i<listaCuentas.size();i++){
//			Cuenta cta= (Cuenta)listaCuentas.get(i);
//			
//		}
		List x=usuario.getCuentas();
		
		if(x!= null && x.size() >0)
    	{
		
		CuentaImpl cuenta = (CuentaImpl)x.get(0);
		String codProducto="";
		String indRenov = "";
		boolean indPrestamo=false;
		
		for(int i=0;i<x.size();i++){
			
			CuentaImpl cuenta1 = (CuentaImpl)x.get(i);
			
			if(cuenta1.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
								
				indPrestamo=cuenta1.getEsCuentaPrestamo();
				
			}
		
		}
		
        
		
		int numero;
		ArrayList numeros = new ArrayList();
		String valorAleatorio = "";
		
		ArrayList<String> numerosCel = new ArrayList<String>();
		numerosCel.add("51942896863");
		numerosCel.add("51942896489");

		//Genera aleatorio 
		for (int i = 1; i <= 1; i++) {
		    numero = (int) (Math.random() * 2 + 1);
		    if (numeros.contains(numero)) {
		        i--;
		    } else {
		        numeros.add(numero);
		    }
		}

		int num=0;
		for(int i=0;i<numeros.size();i++){
		
			 num =Integer.parseInt(""+numeros.get(i))-1;
		
		}
		
		valorAleatorio = numerosCel.get(num);

		request.setAttribute("celular", valorAleatorio);
		request.setAttribute("indPrestamo", indPrestamo);

    	}

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
		request.getSession().setAttribute(ConstanteSesion.CONSULTA,null);
		request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,null);
		if (usuario.getTipoTarjeta().equals("01")){
		    return mapping.findForward("iniciarDni");
		} else {
		    return mapping.findForward("iniciar");
		}
		
	}
	
public ActionForward mostrarReporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String numeroCuenta = request.getSession().getAttribute("hidCuenta").toString();
		Constante.FLAG_CACHE = "1";
		
		HttpSession session=request.getSession(false); 
		String destino = new String("failure");
		BNAplicacion apli = BNAplicacion.getInstance(); 
		HashMap parameters = new HashMap();
		
		String fechaPagina = ObjectUtil.timestampToString(new Timestamp(new Date().getTime()),"dd/MM/yyyy HH:mm:ss");
		
		parameters.put("numCta",numeroCuenta);
		parameters.put("cFechaRep", fechaPagina);
		
		String anioFin = request.getParameter("txtAnio");
		String mesFin = request.getParameter("optMes");

		
	    String datePeriodoIni="01"+"/"+mesFin+"/"+anioFin;  
	    
	    Date date=new SimpleDateFormat("dd/MM/yyyy").parse(datePeriodoIni);      
		Calendar c = Calendar.getInstance();
		c.setTime(date);

        String finDiaMes = ""+c.getActualMaximum(Calendar.DAY_OF_MONTH);
        
       
        
        String datePeriodoFinal=anioFin+mesFin+finDiaMes;  
        
        parameters.put("nPeriodo", datePeriodoFinal);
        
     
	    response.setContentType("application/pdf");
	  
	    destino = "estadoDefault";
	    //int resultFicha = PedidoFicha.imprimeRepMovimiento(response, reportStream, parameters);
	    //if(Constante.VER_LOG) System.out.println("Resultado de resultFicha:"+resultFicha);
	    
	    PedidoFicha.obtenerEstadoCuentaCorriente(response,  parameters);
	
	    return null;
	    
//		if (resultFicha == 0){
//			return mapping.findForward("estadoDefault");
//		} else {
//			return mapping.findForward("consultaEstado");
//		}
		
	}
	
	
	public ActionForward consultarCtaCte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sValor = request.getParameter("hidCuenta");request.getSession().setAttribute("hidCuenta",request.getParameter("hidCuenta"));
		String vCuenta = ObjectUtil.formatearCuenta(sValor.trim(),Constante.FORMATO_CUENTA);
		
		if(Consulta(request,vCuenta)==true){
			String numeroCuenta ="";
			String codConsulta ="";
			String codMoneda ="";
			
			
			if(request.getParameter("hidConsulta")!=null){
				numeroCuenta = request.getParameter("hidCuenta");request.getSession().setAttribute("hidCuenta",request.getParameter("hidCuenta"));
				codConsulta  = request.getParameter("hidConsulta");request.getSession().setAttribute("hidConsulta",request.getParameter("hidConsulta"));
				codMoneda 	 = request.getParameter("hidMoneda");request.getSession().setAttribute("hidMoneda",request.getParameter("hidMoneda"));
							
			}else{
				numeroCuenta = (String)request.getSession().getAttribute("hidCuenta");
				codConsulta  = (String)request.getSession().getAttribute("hidConsulta");
				codMoneda 	 = (String)request.getSession().getAttribute("hidMoneda");
			
			}
			
			String codProducto = numeroCuenta.trim().substring(0,2);
			
			if ("00".equals(codProducto))
				codMoneda = Constante.MONEDA_SOL;
			else if ("06".equals(codProducto))
				codMoneda = Constante.MONEDA_DOLAR;
			
			
			request.getSession().setAttribute("hidMoneda",codMoneda);
			request.getSession().setAttribute("hidCuenta",numeroCuenta);
			request.getSession().setAttribute("MONEDA_SOL",Constante.MONEDA_SOL);
			request.getSession().setAttribute("MONEDA_DOLAR",Constante.MONEDA_DOLAR);
		
		
			Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		}else{
			log3.error(null,Constante.ERR_LOGICA_NEGOCIO,"La cuenta ingresada no pertenece al cliente");
		}

		
		
		
		return mapping.findForward("movimientoCtaCte");
		
	}

	public ActionForward consultaestado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String vCuenta ="";
		vCuenta=ObjectUtil.formatearCuenta(request.getParameter("hidCuenta").trim(),Constante.FORMATO_CUENTA);
//		System.out.println("vCuenta::::"+vCuenta);	
		
		if(Consulta(request,vCuenta)==true){
			Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			Constante.FLAG_CACHE = "1";
			request.getSession().removeAttribute(ConstanteSesion.CONSULTA);
			String numeroCuenta = request.getParameter("hidCuenta");
			String codConsulta 	= request.getParameter("hidConsulta");
			String codMoneda 	= request.getParameter("hidMoneda");
			
			// Validando que la cuenta existe, solo para CUENTA CORRIENTE - DNI
			try{
				if (usuario.getTipoTarjeta().equals("01")){
				    FacadeFactory.getConsultaFacade().getVerificaCta(request.getRemoteAddr(), "5", numeroCuenta, usuario.getTarjeta().getNumero());
				}
			} catch(ArrayRuleException e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		        throw e;
			}
			
			request.getSession().setAttribute("hidCuenta",numeroCuenta);
			request.getSession().setAttribute("hidMoneda",codMoneda);
			
			
			String codProducto = numeroCuenta.trim().substring(0,2);
			
			if ("00".equals(codProducto))
				codMoneda = Constante.MONEDA_SOL;
			else if ("06".equals(codProducto))
				codMoneda = Constante.MONEDA_DOLAR;
			else if("04".equals(codProducto))
				codMoneda = Constante.MONEDA_SOL;
			else if("08".equals(codProducto))
				codMoneda = Constante.MONEDA_DOLAR;
			
			CuentaImpl cuenta= new CuentaImpl();
			cuenta.setMonedaProducto(codMoneda);
			cuenta.setNumeroProducto(numeroCuenta);
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,cuenta);
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			
			
		}else{
			log3.error(null,Constante.ERR_LOGICA_NEGOCIO,"La cuenta ingresada no pertenece al cliente");
		}
		
		return mapping.findForward("consultaEstado");
	}
	
	public ActionForward consultar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("mensajeCliente",((Vector)aplicacion.getMensajePorCodigo("CL01","CC001")).elementAt(2).toString());
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("listaMovs",null);
		
		String numeroCuenta ="";
		String codConsulta ="";
		String codMoneda ="";
		String numeroPrestamo="";
		
//		System.out.println("Miguel - Cuenta que viene del request:::"+request.getParameter("hidCuenta").trim());
//		System.out.println("Miguel - cuenta de la sesion::::"+(String)request.getSession().getAttribute("hidCuenta"));
		
		String vCuenta ="";
		
		if(request.getParameter("hidConsulta")!=null){
			vCuenta=ObjectUtil.formatearCuenta(request.getParameter("hidCuenta").trim(),Constante.FORMATO_CUENTA);
		}else{
			vCuenta=ObjectUtil.formatearCuenta((String)request.getSession().getAttribute("hidCuenta"),Constante.FORMATO_CUENTA);
		}
		
		System.out.println("vCuenta::::"+vCuenta);
			
		try{
			
			if(Consulta(request,vCuenta)==true){
				
				if(request.getParameter("hidConsulta")!=null){
					numeroCuenta = request.getParameter("hidCuenta").trim();
					request.getSession().setAttribute("hidCuenta",request.getParameter("hidCuenta"));
					codConsulta  = request.getParameter("hidConsulta");
					request.getSession().setAttribute("hidConsulta",request.getParameter("hidConsulta"));
					codMoneda 	 = request.getParameter("hidMoneda");
					request.getSession().setAttribute("hidMoneda",request.getParameter("hidMoneda"));
					numeroPrestamo = request.getParameter("hidNroPrestamo");
					request.getSession().setAttribute("hidNroPrestamo",request.getParameter("hidNroPrestamo"));
					
					
						
				}else{
					numeroCuenta = (String)request.getSession().getAttribute("hidCuenta");
					codConsulta  = (String)request.getSession().getAttribute("hidConsulta");
					codMoneda 	 = (String)request.getSession().getAttribute("hidMoneda");
					numeroPrestamo = (String)request.getSession().getAttribute("hidNroPrestamo");
				}
			
				
				if (ObjectUtil.isNumeric1(numeroCuenta)){
					
				}else{
			
					throw new ArrayRuleException(ConstanteError.GENERICO,"Estimado Cliente, no se puede realizar la consulta.");
					 
				}
				
				// NUEVA VALIDACION
				CuentaImpl cuenta = null;
				List lista = usuario.getCuentas();
				int nFlgExiste = 0;
				for (Iterator iter = lista.iterator(); iter.hasNext();) {
					cuenta = (CuentaImpl) iter.next();
					
					if(cuenta.getNumeroProducto().equals(numeroCuenta)){
						nFlgExiste = 1;
						break;
					}
				}
				
				//if(nFlgExiste == 0)throw new ArrayRuleException(ConstanteError.GENERICO,"La cuenta consultada no le pertenece.");
				
				String codProducto = numeroCuenta.trim().substring(0,2);
				
				
				if ("00".equals(codProducto))
					codMoneda = Constante.MONEDA_SOL;
				else if ("06".equals(codProducto))
					codMoneda = Constante.MONEDA_DOLAR;
				else if("04".equals(codProducto))
					codMoneda = Constante.MONEDA_SOL;
				else if("08".equals(codProducto))
					codMoneda = Constante.MONEDA_DOLAR;
						
				
				request.getSession().setAttribute("hidMoneda",codMoneda);
				request.getSession().setAttribute("MONEDA_SOL",Constante.MONEDA_SOL);
				request.getSession().setAttribute("MONEDA_DOLAR",Constante.MONEDA_DOLAR);
				
				
				
			}else{
				log3.error(null, null, "el numero de cuenta no corresponde");
			}
			
			
		}
		catch (ArrayRuleException e) {
			
				e.printStackTrace();
			   BNAplicacion aplicacion = BNAplicacion.getInstance();
		       
				request.setAttribute("mensajeHora",((Vector)aplicacion.getMensajePorCodigo("CL01","MB002")).elementAt(2).toString());
				request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","MB001")).elementAt(2).toString());
				request.setAttribute("mensajeClaveDinamica",((Vector)aplicacion.getMensajePorCodigo("CL01","CD001")).elementAt(2).toString());
			throw e;
		}
	
		request.getSession().removeAttribute(ConstanteSesion.CONSULTA);
		
		// Validando que la cuenta existe, solo para CUENTA CORRIENTE - DNI
		if (usuario.getTipoTarjeta().equals("01")){
		    String codTipoTrx = "";
		    if (codConsulta.equals("05") || codConsulta.equals("18")){
		        codTipoTrx = "1";
		    } else if (codConsulta.equals("06") || codConsulta.equals("21")){
		        codTipoTrx = "2";
		    } else if (codConsulta.equals("08")){
		        codTipoTrx = "3";
		    } else if (codConsulta.equals("07") || codConsulta.equals("22")){
		        codTipoTrx = "4";
		    } else if (codConsulta.equals("05")){
		        codTipoTrx = "5";
		    }
		    
		    
		    FacadeFactory.getConsultaFacade().getVerificaCta(request.getRemoteAddr(), codTipoTrx, numeroCuenta, usuario.getTarjeta().getNumero());
		     
		}
		
		// NUEVA VALIDACION
		CuentaImpl cuenta = null;
		List lista = usuario.getCuentas();
		int nFlgExiste = 0;
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuenta = (CuentaImpl) iter.next();
			if(cuenta.getNumeroProducto().equals(numeroCuenta)){
				//nFlgExiste = 1;
				//break;
			}
		}
		
//		if(nFlgExiste == 0)
//			throw new ArrayRuleException("La cuenta consultada no le pertenece...");
		
		// FIN DE VALIDACION
		
		try{
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,"");
			
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.CONSULTA));
			OperacionImpl.setVariables(map);
		    Object obj = null;
		    Object x= (Object)request.getSession().getAttribute(codConsulta);
//		    if(x!=null){
//		    	obj=request.getSession().getAttribute(codConsulta);
//		    }else{
		    	request.getSession().setAttribute("06",null);
		    	request.getSession().setAttribute("02",null);
		    	request.getSession().setAttribute("13",null);
		    	obj=FacadeFactory.getConsultaFacade().consultar(codConsulta,numeroCuenta,codMoneda,numeroPrestamo, request.getRemoteAddr(),usuario,request);
		//    }
		    
		    if (codConsulta.equals("01") || codConsulta.equals("02") || codConsulta.equals("03")
		    		|| codConsulta.equals("05") || codConsulta.equals("06") || codConsulta.equals("07")
		            || codConsulta.equals("12") || codConsulta.equals("13"))
		    {
		    	
		        request.getSession().setAttribute(ConstanteSesion.CONSULTA,getCuenta(obj));
		        if(codConsulta.equals("06")|| codConsulta.equals("02") || codConsulta.equals("13")){
		        	request.getSession().setAttribute(codConsulta,obj);
		        	Cuenta cta=getCuenta(obj);
		        	List movs= cta.getMovimientos();
		        	ArrayList listaMov= new ArrayList();
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		        	
		        	for(int i=0;i<movs.size();i++){
		        		MovimientoImpl mto=(MovimientoImpl)movs.get(i);

		        		mto.setFechaFormat(sdf.format(mto.getFecha()));
		        		if(i+1<10){
		        			mto.setSecuencia("0"+(i+1));
		        		}else
		        			mto.setSecuencia(""+(i+1));
		        		listaMov.add(i,mto);
		        	}
		        	
		        	request.setAttribute("listaMovs",listaMov);
		        }
		    }
		    else if (codConsulta.equals("04") || codConsulta.equals("08")){
		    	BNAplicacion aplicacion = BNAplicacion.getInstance();
		    	request.getSession().setAttribute("mensajeItf",((Vector)aplicacion.getMensajePorCodigo("CL01","ITF01")).elementAt(2).toString());
		        request.getSession().setAttribute(ConstanteSesion.CONSULTA,getItf(obj));
		    }
		    else if (codConsulta.equals("09") || codConsulta.equals("10")){
		        request.getSession().setAttribute(ConstanteSesion.CONSULTA,getPrestamo(obj));
		    }
		}
		catch(ArrayRuleException e){
			log3.error(null,Constante.ERR_LOGICA_NEGOCIO,"La cuenta ingresada no pertenece al cliente");
		   
	        BNAplicacion aplicacion = BNAplicacion.getInstance();
	       
			request.setAttribute("mensajeHora",((Vector)aplicacion.getMensajePorCodigo("CL01","MB002")).elementAt(2).toString());
			request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","MB001")).elementAt(2).toString());
			request.setAttribute("mensajeClaveDinamica",((Vector)aplicacion.getMensajePorCodigo("CL01","CD001")).elementAt(2).toString());
	   
	        throw e;
		}
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return getMappingConsulta(mapping,codConsulta);			
	}
	
	public ActionForward consultarCuentaCte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.getSession().setAttribute("mensajeCliente",((Vector)aplicacion.getMensajePorCodigo("CL01","CC001")).elementAt(2).toString());
				
		String numeroCuenta ="";
		String codConsulta ="";
		String codMoneda ="";
		String DatProI = "";
		String DatProF = "";
		String CodTrx  = "";
		String fecha = "";
	
		String flgPagina = "";
		String flgExportar = "";
		String flgNumPagina = "";
	
		String CorrOrigen = "";    
		String TranOrigen  = "";   
		String CanalOrigen  = "";  
		String CajeroOrigen  = ""; 
		String JournalOrigen  = "";
			
		String numeroPrestamo="";
		
		flgPagina = request.getParameter("hidPaginacion");
		
		flgExportar = request.getParameter("hidExportar");
		flgNumPagina = request.getParameter("txtPagina");
		
		ArrayList listaMovAnt= new ArrayList();
		listaMovAnt = (ArrayList)request.getSession().getAttribute("listaMovs");
		
		request.getSession().setAttribute("hidPaginacion",request.getParameter("hidPaginacion"));

		if(request.getParameter("hidConsulta")!=null){
			numeroCuenta = request.getParameter("hidCuenta");
				request.getSession().setAttribute("hidCuenta",request.getParameter("hidCuenta"));
			codConsulta  = request.getParameter("hidConsulta");
				request.getSession().setAttribute("hidConsulta",request.getParameter("hidConsulta"));
			codMoneda 	 = request.getParameter("hidMoneda");
				request.getSession().setAttribute("hidMoneda",request.getParameter("hidMoneda"));
			numeroPrestamo = request.getParameter("hidNroPrestamo");
				request.getSession().setAttribute("hidNroPrestamo",request.getParameter("hidNroPrestamo"));
			
				
		}else{
			numeroCuenta = (String)request.getSession().getAttribute("hidCuenta");
			codConsulta  = (String)request.getSession().getAttribute("hidConsulta");
			codMoneda 	 = (String)request.getSession().getAttribute("hidMoneda");
			numeroPrestamo = (String)request.getSession().getAttribute("hidNroPrestamo");
		}
		
		String codProducto = numeroCuenta.trim().substring(0,2);
		
		if ("00".equals(codProducto))
			codMoneda = Constante.MONEDA_SOL;
		else if ("06".equals(codProducto))
			codMoneda = Constante.MONEDA_DOLAR;
		
		request.getSession().setAttribute("hidMoneda",codMoneda);
		request.getSession().setAttribute("MONEDA_SOL",Constante.MONEDA_SOL);
		request.getSession().setAttribute("MONEDA_DOLAR",Constante.MONEDA_DOLAR);
	
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		Cuenta consultaAnt =  (CuentaImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		
		request.getSession().removeAttribute(ConstanteSesion.CONSULTA);
		
		// Validando que la cuenta existe, solo para CUENTA CORRIENTE - DNI
		if (usuario.getTipoTarjeta().equals("01")){
			String codTipoTrx = "";
			if (codConsulta.equals("06")){
				codTipoTrx = "2";
			}
			
			FacadeFactory.getConsultaFacade().getVerificaCta(request.getRemoteAddr(), codTipoTrx, numeroCuenta, usuario.getTarjeta().getNumero());
		}
		
		int nSecuencia = 1;
		
		try{
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.CONSULTA));
			OperacionImpl.setVariables(map);
			Object obj = null;
			
			Object x= (Object)request.getSession().getAttribute(codConsulta);
			
			CuentaImpl ctaImpl = new CuentaImpl();
			
			if(x!=null){
				ctaImpl = (CuentaImpl)x;
			}
			if (codConsulta.equals("06")){
				x=null;
			}
			if(x!=null){
				obj=request.getSession().getAttribute(codConsulta);
			}

			else{
				if (flgNumPagina.equals(ctaImpl.getNPagina())){
					request.getSession().setAttribute("06",null);
					request.getSession().setAttribute("02",null);
					request.getSession().setAttribute("13",null);
					if (codConsulta.equals("06")){
			    		
						String cDatProI = request.getParameter("f04FeCaduClaves").toString().trim();
						String cDatProF = request.getParameter("f04FeCaduClaves2").toString().trim();
						String cCodTrx  = request.getParameter("txtTRAN").toString().trim();
						String cNumDoc  = request.getParameter("txtCHEQ").toString().trim();
			    		
						String txtPagina  = request.getParameter("txtPagina").toString().trim();
						
									    		 
						int nPagina = Integer.parseInt(txtPagina);
	
						if (flgPagina.trim().equals("1")){
							nPagina--;
						} else {
							nPagina++;
						}
			    		
						if (nPagina == 1){
							nSecuencia = 0;
						} else if(nPagina == 2){
							nSecuencia = 40;
						} else if(nPagina == 3){
							nSecuencia = 80;
						} else if(nPagina == 4){
							nSecuencia = 120;
						} else if(nPagina == 5){
							nSecuencia = 160;
						} else if(nPagina == 6){
							nSecuencia = 200;
						} else if(nPagina == 7){
							nSecuencia = 240;
						} else if(nPagina == 8){
							nSecuencia = 280;
						} else if(nPagina == 9){
							nSecuencia = 320;
						} else if(nPagina == 10){
							nSecuencia = 360;
						}
	
						String dia  = cDatProI.substring(0,2);
						String mes  = cDatProI.substring(3,5);
						String anio = cDatProI.substring(6,10);
			    		
						cDatProI = anio+mes+dia;
						
						dia  = cDatProF.substring(0,2);
						mes  = cDatProF.substring(3,5);
						anio = cDatProF.substring(6,10);
			    		
						cDatProF = anio+mes+dia;
			    		ctaImpl.setNPagina(Integer.toString(nPagina));
			    		
			    		
						obj=FacadeFactory.getConsultaFacade().consultarCtaCte(ctaImpl, nPagina, codConsulta,numeroCuenta,codMoneda,cDatProI,cDatProF,cCodTrx,cNumDoc,flgPagina,request.getRemoteAddr(),usuario,request);
			    		
						CuentaImpl objCuenta =  (CuentaImpl)obj;
			    		
						request.getSession().setAttribute("txtTRAN",objCuenta.getTrxCtaCte());
						request.getSession().setAttribute("f04FeCaduClaves",objCuenta.getFechaInicial());
						request.getSession().setAttribute("f04FeCaduClaves2",objCuenta.getFechaFin());
						request.getSession().setAttribute("txtCHEQ",objCuenta.getCheque());
			    		
						request.getSession().setAttribute("txtPagina", Integer.toString(nPagina));
			    		
					} else {
						obj=FacadeFactory.getConsultaFacade().consultar(codConsulta,numeroCuenta,codMoneda,numeroPrestamo, request.getRemoteAddr(),usuario,request);
					}
					
					// Lista de Movimientos
					if(codConsulta.equals("06")|| codConsulta.equals("02") || codConsulta.equals("13")){
						request.getSession().setAttribute(codConsulta,obj);
						Cuenta cta=getCuenta(obj);
						List movs= cta.getMovimientos();
						ArrayList listaMov= new ArrayList();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			        	
			        	for(int i=0;i<movs.size();i++){
			        	
			        		MovimientoImpl mto=(MovimientoImpl)movs.get(i);
		
			        		mto.setFechaFormat(sdf.format(mto.getFecha()));
		
			        		if(i+1+nSecuencia<10){
			        			mto.setSecuencia("0"+(i+1+nSecuencia));
			        		}else
			        			mto.setSecuencia(""+(i+1+nSecuencia));
			        		listaMov.add(i,mto);
			        	}
			        	
			        	request.getSession().setAttribute(ConstanteSesion.CONSULTA,getCuenta(obj));
			        	request.getSession().setAttribute("listaMovs",listaMov);
			        	request.setAttribute("listaMovs",listaMov);
		
			        } 
					
				} else {
					request.getSession().setAttribute(ConstanteSesion.CONSULTA,consultaAnt);
	        		request.getSession().setAttribute("listaMovs",listaMovAnt);
		        	request.setAttribute("listaMovs",listaMovAnt);
	        	
	        	}
			}
		    

		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	        BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","00001")).elementAt(2).toString());
	        throw e;
		}
			
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		request.setAttribute("hidExportar","1");
		request.getSession().setAttribute("hidExportar","1");
	
		return getMappingConsulta(mapping,codConsulta);
	}
	

	public ActionForward siguiente(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return getMappingConsulta(mapping,"06");	
	}
	
	//Tarjeta de Credito -- 27/11/2013
	
	public ActionForward consultarTarjCredito(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
						
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("mensajeTarjetaCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC005")).elementAt(2).toString());
		
		ArrayList listaMovTarjCredito= new ArrayList();
		
		
		String transaccion="";
		String tipoConsulta = "";
		String hidConsulta= request.getParameter("hidConsulta");
		
		
		//Seteando el tipo de transacción
		if(hidConsulta.equals(Constante.TARJETA_CREDITO_FORM_CONSULTA_SALDO)){
			transaccion = Constante.TARJETA_CREDITO_CONSULTA_SALDO;
		}
		if(hidConsulta.equals(Constante.TARJETA_CREDITO_FORM_CONSULTA_MOV)){
			transaccion = Constante.TARJETA_CREDITO_CONSULTA_MOV;
		}
		if(hidConsulta.equals(Constante.TARJETA_CREDITO_FORM_PAGO_TC)){
			transaccion = Constante.TARJETA_CREDITO_PAGO_TC;
		}	
		CuentaImpl cuentaImpl = new CuentaImpl();
		CuentaImpl consulta = new CuentaImpl();
		cuentaImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		cuentaImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
		//Obteniendo las cuentas del cliente
		List x=usuario.getCuentas();
		CuentaImpl cta=null;
	
		for(int i=0; i<x.size();i++){
			cta=(CuentaImpl)x.get(i);
		
			if(cta.getTipoProducto().equals(Constante.COD_TARJETA_CREDITO)){
				
					cuentaImpl.setTipoProducto(cta.getTipoProducto());
					cuentaImpl.setNumeroProducto(cta.getNumeroProducto());
					cuentaImpl.setTipoTarjeta(cta.getTipoTarjeta());
					cuentaImpl.setSaldo(ObjectUtil.deFormatearMonto(cta.getSaldo()));
								
			}	
		}
		//Realizando la consulta según transacción
		if(transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_SALDO) ||transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_MOV) ){
			
			if(transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_SALDO)) tipoConsulta = Constante.TARJETA_CREDITO_CONSULTA_SALDO_VALOR_TRAMA;
			if(transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_MOV)) tipoConsulta = Constante.TARJETA_CREDITO_CONSULTA_MOV_VALOR_TRAMA;
			consulta =FacadeFactory.getTarjetaCreditoFacade().consultar(transaccion,tipoConsulta, cuentaImpl.getNumeroProducto(), request.getRemoteAddr(), usuario, request);
			consulta.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
			consulta.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
			
			
			
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,consulta);
		
			if(transaccion.equals(Constante.TARJETA_CREDITO_CONSULTA_MOV)){
				
				int nSecuencia = 0;
				ArrayList listaMovTC= new ArrayList();
				
				List resultado = consulta.getMovimientosTC();
				
				for (int i = 0; i < resultado.size(); i++) {
					
					MovimientoImpl valor = new MovimientoImpl();
					
					valor = (MovimientoImpl)resultado.get(i);

					if(i+1+nSecuencia<10){
						valor.setSecuencia("0"+(i+1+nSecuencia));
	        		}else
	        			valor.setSecuencia(""+(i+1+nSecuencia));
					listaMovTC.add(i,valor);
								
				}
					
				request.getSession().setAttribute("listaMovimientosTC",listaMovTC);
				
			}
					
		}else{
			//Pago TC jrios
			if(transaccion.equals(Constante.TARJETA_CREDITO_PAGO_TC)){
				tipoConsulta = Constante.TARJETA_CREDITO_CONSULTA_SALDO_VALOR_TRAMA;
				transaccion = Constante.TARJETA_CREDITO_CONSULTA_SALDO;
				
				if( cuentaImpl.getNumeroProducto()== null || cuentaImpl.getNumeroProducto().trim().equals("") 
						|| Long.parseLong(cuentaImpl.getNumeroProducto())==0){
					
					throw new ArrayRuleException(ConstanteError.GENERICO,"Estimado cliente, no cuenta con este producto.");
				}
//				System.out.println("TARJETA CREDITO:"+ cuentaImpl.getNumeroProducto());
				//if()
				consulta =FacadeFactory.getTarjetaCreditoFacade().consultar(transaccion,tipoConsulta, cuentaImpl.getNumeroProducto(), request.getRemoteAddr(), usuario, request);
				consulta.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
				consulta.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());	
				
				
				request.getSession().setAttribute("nrotc",cuentaImpl.getNumeroProducto());
				request.getSession().setAttribute(ConstanteSesion.CONSULTA,consulta);
				request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
				
				ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
				request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
				
				ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
				request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
				
				request.getSession().setAttribute("mensajeArriba",((Vector)aplicacion.getMensajePorCodigo("CL01","TC006")).elementAt(2).toString());
				//request.getSession().setAttribute("mensajeAbajo",((Vector)aplicacion.getMensajePorCodigo("CL01","TC007")).elementAt(2).toString());
				request.getSession().setAttribute("mensajeCondiciones",((Vector)aplicacion.getMensajePorCodigo("CL01","TC008")).elementAt(2).toString());
			//
			}else{
			
				List resultado = null;
				
				
				
				resultado=FacadeFactory.getTarjetaCreditoFacade().buscarEstadoCuenta(usuario.getNumDocumento());
				
				request.getSession().setAttribute("consultaEstadoCuenta", resultado);
				request.getSession().setAttribute(ConstanteSesion.CONSULTA,cuentaImpl);
				request.getSession().setAttribute("mensajeEstadoCuenta",((Vector)aplicacion.getMensajePorCodigo("CL01","TC004")).elementAt(2).toString());
				request.getSession().setAttribute("pieMensajeEstadoCuenta",((Vector)aplicacion.getMensajePorCodigo("CL01","TC002")).elementAt(2).toString());
			}
		}
		
		
		Map map = UtilAction.cargarVar(request,cuentaImpl);
		OperacionImpl.setVariables(map);
		
		
		return getMappingTCredito(request,mapping,hidConsulta);
		
		
	}
//	Pago TC jrios
	public ActionForward confirmaPagoTCredito(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		CuentaImpl cuentaImpl = (CuentaImpl) request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String tipoImporte =(String) request.getParameter("tipoImporte");
	
		String cmbCuenta 	= "";
		
		cmbCuenta = request.getParameter("cmbCuenta");
		
		
		List x=usuario.getCuentas();
		CuentaImpl cta=null;
		boolean flag= false;
		for(int i=0; i<x.size();i++){
			cta=(CuentaImpl)x.get(i);		
			if(cta.getTipoProducto().equals("44")){
				flag=true;	
				break;
			}	
		}
		if(!flag){
			cta	= (CuentaImpl)usuario.getMapCuentas().get(cmbCuenta);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cmbCuenta)){			
					break;					
				}	
			}		
		}
		//*****************************************
		
		
		Cuenta cuenta 		= cta;
		String importePagado = request.getParameter("importeNuevo").replace(",", "");
		PagoImpl pago = null;
		pago = new PagoImpl();
		
		pago.setCuentaCargada(cuenta);
		pago.setCuentaCredito(cuentaImpl);
		pago.setMontoPagado(new BigDecimal(importePagado));
		pago.setNroCuentaAhorros(usuario.getTarjeta().getNumero());
		pago.setDatos(cuentaImpl);
		request.getSession().setAttribute("tipoImporte", tipoImporte);
		request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pago);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		//request.getSession().setAttribute("tipoElemento",null );
		try{
		
			request.getSession().setAttribute("tipoImporte",tipoImporte );
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("iniciar");
			throw ar;
		}
		
//		REALIZAR CONSULTA DE COORDENADAS
		

		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				}
				else{
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
					}
					
				}
				
				
				
			}catch(ArrayRuleException ar){
			
				request.getSession().setAttribute("tipoImporte",tipoImporte );
				
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioPagoTCredito");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			request.getSession().setAttribute("tipoImporte",tipoImporte );
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		

		return mapping.findForward("confirmaPagoTCredito");
		
	}
	public ActionForward pagarTCredito(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String transaccion = request.getParameter("transaccion");
		String tipoConsulta= "";
		if(transaccion.equals(Constante.TARJETA_CREDITO_PAGO_TC)) tipoConsulta = Constante.TARJETA_CREDITO_PAGO_TC_VALOR_TRAMA;
		
		PagoImpl pago = (PagoImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TC);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
	
		
		ElemenSeguridad resultCoord = null;
		//String pinblock = request.getParameter("txtCoordenada");
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
		ParametrosElemSegTDC elementos = null;
		try{
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.PAGO_TC));
			OperacionImpl.setVariables(map);			
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("iniciar");
				throw ar;
			}
			
			// Código Nuevo para TDC
		
			MensajesTDC resultado = new MensajesTDC();
			Pago pg = null;
			
			
			if(param!= null && coord!=null ){
				try{
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);					
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){						
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
						}						
					}
					
					if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
						String codErrEnt="";
						String mensaje="";
						
						if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
							 	codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4-resultado.getCodRptaPrincipal().toString().length());
							 	mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
						}	
						else{
							if(!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
								 codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0', 4-resultado.getCodRptaPrincipalOper().toString().length());
								 mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
							}
						}
												
						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");						
					}
					
					String tipo = Constante.TC_TIPO_SERVICIO_TITULAR;
					
					pg =FacadeFactory.getTarjetaCreditoFacade().pagarTCredito(transaccion, tipoConsulta, pago, request.getRemoteAddr(), usuario, tipo,request);
					
					//if(registro != null){
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pg.getCuentaCargada().getSimboloMonedaProducto(), 
								 pago.getMontoPagado(),usuario.getTipoCambio().getVenta() ,
								 Constante.NOTI_PAGO_TC_BN, 
								 usuario.getNotificacion());
					 }
					
									
				}catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();					
					request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pago);
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
							
							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
						}
					}
					
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmaPagoTCredito");
					throw e;
				}
			}
			
			request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pg);			
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
			}
			else{
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){					
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
			e.setForward("confirmaPagoTCredito");
			throw e;
		}
		
		return mapping.findForward("pagarTCredito");
		
	}
	
	//
	
	public ActionForward descargarEstadoCuenta(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String numDocumento = "";
		String periodo = "";
		
		numDocumento = request.getParameter("txtDoc");
		periodo = request.getParameter("txtPeriodo");
		

		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + numDocumento+"_"+periodo+".pdf" + "\"");
			FacadeFactory.getTarjetaCreditoFacade().encontrarFormato2( numDocumento,periodo,response.getOutputStream());
		
		} catch (ArrayRuleException e) {
			e.printStackTrace();
			e.setForward("consultaEstadoTCredito");
			throw e;
		}
		
		
		return null;	
	}
	
	
	public ActionForward simularPrestamo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrestamoImpl simulador = new PrestamoImpl();
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		try {
			
			simulador.setNroCuotas(Constante.PRESTAMO_CONST_CUOTA_DEFAULT);
			simulador.setFlagPeriodoGracia(Constante.PRESTAMO_CONST_FLAG_NO);
			simulador.setFlagCuotaProtegida(Constante.PRESTAMO_CONST_FLAG_NO);
			simulador.setFlagEndoso(Constante.PRESTAMO_CONST_FLAG_NO);
			simulador.setTipoPrestamo(Constante.PRESTAMO_CONST_TIPO_PRESTAMO);
			simulador.setTipoOperacion(Constante.PRESTAMO_CONST_TIPO_OPERACION);
			simulador.setTipoEnvio(Constante.PRESTAMO_CONST_TIPO_ENVIO_VIRTUAL);
			simulador.setMontoSolicitado(Constante.PRESTAMO_CONST_MONTO_DEFAULT);
			List x=usuario.getCuentas();
			CuentaImpl cuenta = (CuentaImpl)x.get(0);
			
			simulador.setCuenta(cuenta.getNumeroProducto());
			simulador.setTarjeta(usuario.getTarjeta().getNumero());
				
			
			simulador=FacadeFactory.getPrestamoFacade().simularPrestamo( simulador, request.getRemoteAddr(), usuario, request);
			simulador.setCliente(usuario.getNombre());
	
			request.getSession().setAttribute(ConstanteSesion.PRESTAMO,simulador);
			
		} catch (ArrayRuleException e) {
			e.printStackTrace();
			//e.setForward("iniciar");
			iniciar(mapping,form,request,response);
			
			throw e;
		}
		return mapping.findForward("simularPrestamoDefault");
	}
	

	//01,02,03,06,07,12,13
	private Cuenta getCuenta(Object obj) throws Exception{
	    Cuenta objCuenta =  (CuentaImpl)obj;
	    return objCuenta;
	}
	
	//04,08
	private Itf getItf(Object obj) throws Exception{
	    Itf objItf =  (ItfImpl)obj;
	    return objItf;
	}
	
	//09,10
	private Prestamo getPrestamo(Object obj) throws Exception{
	    Prestamo objPrestamo =  (PrestamoImpl)obj;
	    return objPrestamo;
	}
	
	private void setearSessionError(HttpServletRequest request,String codConsulta){
	    if (codConsulta.equals("01") || codConsulta.equals("02") || codConsulta.equals("03")
	            || codConsulta.equals("04") || codConsulta.equals("06") || codConsulta.equals("07")
	            || codConsulta.equals("12") || codConsulta.equals("13")){
	        request.getSession().setAttribute(ConstanteSesion.CONSULTA,new CuentaImpl());
	    }else if (codConsulta.equals("04") || codConsulta.equals("08")){
	        request.getSession().setAttribute(ConstanteSesion.CONSULTA,new ItfImpl());
	    }else if (codConsulta.equals("09") || codConsulta.equals("10")){
	        request.getSession().setAttribute(ConstanteSesion.CONSULTA,new PrestamoImpl());
	    }
	}
	
	

	private ActionForward getMappingConsulta(ActionMapping mapping,String codConsulta){
		
				
		if(codConsulta.equals("01")){
			return mapping.findForward("consultaSaldoAhorro");
		}
		if(codConsulta.equals("02")){
			return mapping.findForward("consultaUltimosMovimientosAhorros");
		}
		if(codConsulta.equals("03")){
			return mapping.findForward("consultaCCI");
		}
		if(codConsulta.equals("04")){
			return mapping.findForward("consultaITFAnualCA");
		}
		if(codConsulta.equals("05")){
			return mapping.findForward("consultaSaldoCtaCte");
		}
		if(codConsulta.equals("06")){
			return mapping.findForward("consultaUltimosMovimientosCCT");
		}
		if(codConsulta.equals("07")){
			return mapping.findForward("consultaCtaCteCCI");
		}
		if(codConsulta.equals("08")){
			return mapping.findForward("consultaITFAnualCC");
		}
		if(codConsulta.equals("09")){
			return mapping.findForward("consultaSaldoPrestamo");
		}
		if(codConsulta.equals("10")){
			return mapping.findForward("consultaCalendarioPago");
		}
		if(codConsulta.equals("12")){
			return mapping.findForward("consultaSaldoCTS");
		}
		if(codConsulta.equals("13")){
			return mapping.findForward("consultaUtimosMovimientosCTS");
		}
		if(codConsulta.equals("19")){
			return mapping.findForward("consultaEstadoCtaCte");
		}
		return null;
	}
	
	private String getForward(HttpServletRequest request,String codConsulta){
	    String forward = "";
	    if(codConsulta.equals("04")){
	        forward = "consultaITFAnualCA";
	    }
	    else if(codConsulta.equals("08")){
	        forward = "consultaITFAnualCC";
	    }
	    else if(codConsulta.equals("19")){
	        forward = "consultaEstadoCtaCte";
	    }
	    else if(codConsulta.equals("01")){
	        forward = "consultaSaldoAhorro";
	    }
	    else if(codConsulta.equals("02")){
	        forward = "consultaUltimosMovimientosAhorros";
	    }
	    else if(codConsulta.equals("03")){
	        forward = "consultaCCI";
	    }
	    else if(codConsulta.equals("05")){
	        forward = "consultaSaldoCtaCte";
	    }
	    else if(codConsulta.equals("06")){
	        forward = "consultaUltimosMovimientosCCT";
	    }
	    else if(codConsulta.equals("07")){
	        forward = "consultaCtaCteCCI";
	    }
	    else if(codConsulta.equals("09")){
	        forward = "consultaSaldoPrestamo";
	    }
	    else if(codConsulta.equals("10")){
	        forward = "consultaCalendarioPago";
	    }
	    else if(codConsulta.equals("12")){
	        forward = "consultaSaldoCTS";
	    }
	    else if(codConsulta.equals("13")){
	        forward = "consultaUtimosMovimientosCTS";
	    }
	    return forward;
	}
	
	private ActionForward getMappingTCredito(HttpServletRequest request,ActionMapping mapping,String codConsulta) throws Exception{
		
		
		if(codConsulta.equals("70")){
			return mapping.findForward("consultaSaldoTCredito");
		}
		if(codConsulta.equals("80")){
			
			List valor=(List)request.getSession().getAttribute("listaMovimientosTC");
			
			
			return mapping.findForward("consultaUltimosMovimientosTCredito");
		}
		if(codConsulta.equals("90")){
			return mapping.findForward("consultaEstadoTCredito");
		}
//		Pago TC jrios
		if(codConsulta.equals("100")){
				if(!getVerificarHorario("TC00")){
						
						request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
						request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
						request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","TC00")).elementAt(2).toString());
						return mapping.findForward("transaccion.noDisponible");
					}
			return mapping.findForward("inicioPagoTCredito");
		}
//		
		return null;
	}
	
	private Exception lanzarException(HttpServletRequest request, Exception e, String codConsulta){
		if(e instanceof ArrayRuleException){
		    
		    if(codConsulta.equals("04")){
				((ArrayRuleException)e).setForward("consultaITFAnualCA");
		    }
		    else if(codConsulta.equals("08")){
				((ArrayRuleException)e).setForward("consultaITFAnualCC");
		    }
		    else if(codConsulta.equals("19")){
				((ArrayRuleException)e).setForward("consultaEstadoCtaCte");
		    }
			else if(codConsulta.equals("01")){
				((ArrayRuleException)e).setForward("consultaSaldoAhorro");
			}
			else if(codConsulta.equals("02")){
				((ArrayRuleException)e).setForward("consultaUltimosMovimientosAhorros");
			}
			else if(codConsulta.equals("03")){
				((ArrayRuleException)e).setForward("consultaCCI");
			}
			else if(codConsulta.equals("05")){
				((ArrayRuleException)e).setForward("consultaSaldoCtaCte");
			}
			else if(codConsulta.equals("06")){
				((ArrayRuleException)e).setForward("consultaUltimosMovimientosCCT");
			}
			else if(codConsulta.equals("07")){
				((ArrayRuleException)e).setForward("consultaCtaCteCCI");
			}
			else if(codConsulta.equals("09")){
				((ArrayRuleException)e).setForward("consultaSaldoPrestamo");
			}
			else if(codConsulta.equals("10")){
				((ArrayRuleException)e).setForward("consultaCalendarioPago");
			}
			else if(codConsulta.equals("12")){
				((ArrayRuleException)e).setForward("consultaSaldoCTS");
			}
			else if(codConsulta.equals("13")){
				((ArrayRuleException)e).setForward("consultaUtimosMovimientosCTS");
			}
		}
		return e;
	}
	
	
	
	
	private boolean Consulta(HttpServletRequest request, String valor){
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	    
		List listaCuentas = usuario.getCuentas();
		boolean vBoolean= false;
		
		for(int i=0;i<listaCuentas.size();i++){
			Cuenta cta= (Cuenta)listaCuentas.get(i);
//			System.out.println("Valor::::"+valor);
//			System.out.println("cta.getCuentaFormateada():::"+cta.getCuentaFormateada());
			
			if(valor.equals(cta.getCuentaFormateada())){
//				System.out.println("ES IGUAL");
				vBoolean=true;
			}
			
			
			
			
			
		}
		
		
		return vBoolean;	
	}
	
	
	
	
} 
