package pe.bn.afiliacion.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.form.DesafiliacionForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.pago.action.PagoTelefonoAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;

public class DesafiliacionAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(DesafiliacionAction.class.getName());
		
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List listaDesafiliacion = null;
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombre");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"Nro. de Servicio");
		
		
		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_TELEFONO)){
		    ConstanteSesion.TITULO = Constante.DESAF_TELEFONO_TITULO;
			request.getSession().setAttribute("TITULO",Constante.DESAF_TELEFONO_TITULO);
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
		}
		

		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_MOVISTAR)){
		    ConstanteSesion.TITULO = Constante.DESAF_MOVISTAR_TITULO;
			request.getSession().setAttribute("TITULO",Constante.DESAF_MOVISTAR_TITULO);
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
		}
		

		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_TERRA)){
		    ConstanteSesion.TITULO = Constante.DESAF_TERRA_TITULO;
			request.getSession().setAttribute("TITULO",Constante.DESAF_TERRA_TITULO);
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_TERRA);
		}
		

		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_CMAGICO)){
		    ConstanteSesion.TITULO = Constante.DESAF_CMAGICO_TITULO;
			request.getSession().setAttribute("TITULO",Constante.DESAF_CMAGICO_TITULO);
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_CMAGICO);
		}
		

		if (Constante.TIPO_SERVICIO.equals("00441")){
		    ConstanteSesion.TITULO = Constante.DESAF_SEDAPAL_TITULO;
			request.getSession().setAttribute("TITULO",Constante.DESAF_SEDAPAL_TITULO);
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombre");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"Número de Suministro");
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_SED,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_TERRA);
		}
		

		if (Constante.TIPO_SERVICIO.equals("00420")){
		    ConstanteSesion.TITULO = "DESAFILIACION TARJETAS DE CREDITO DE OTROS BANCOS";
			request.getSession().setAttribute("TITULO","DESAFILIACION TARJETAS DE CREDITO DE OTROS BANCOS");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombre de Tarjeta");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"No. de Tarjeta");
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TARJETA,usuario.getTarjeta().getNumero(), "00420");
		}
		

		if (Constante.TIPO_SERVICIO.equals(Constante.TB_MISMO_BANCO)){
			Constante.TIPO_SERVICIO = "00004";
			ConstanteSesion.TITULO = "DESAFILIACION TRANSFERENCIAS MISMO BANCO";
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombre del Beneficiario");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"No.Cuenta Destino");
			request.getSession().setAttribute("TITULO","DESAFILIACION TRANSFERENCIAS MISMO BANCO");
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_MISMO_BANCO,usuario.getTarjeta().getNumero(), "00004");
			for(int i=0;i<listaDesafiliacion.size();i++){
			AfiliacionImpl af= (AfiliacionImpl)listaDesafiliacion.get(i);	
			af.setNumSerFormat(ObjectUtil.formatearCuenta(af.getNumeroServicio(),Constante.FORMATO_CUENTA));
			listaDesafiliacion.set(i,af);
			}
			
		} 
		

		if (Constante.TIPO_SERVICIO.equals(Constante.TB_INTERBANCO)){
			Constante.TIPO_SERVICIO = "00004";
			ConstanteSesion.TITULO = "DESAFILIACION TRANSFERENCIAS INTERBANCARIAS";
			request.getSession().setAttribute("TITULO","DESAFILIACION TRANSFERENCIAS INTERBANCARIAS");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombres del Beneficiario");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"No. CCI");
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(), "00004");
		}
		

		if (Constante.TIPO_SERVICIO.equals(Constante.TELEGIRO_NACIONAL)){
			Constante.TIPO_SERVICIO = "00004";
			ConstanteSesion.TITULO = "DESAFILIACION DE GIROS";
			request.getSession().setAttribute("TITULO","DESAFILIACION DE GIROS");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Beneficiario");
			request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"No. de documento");
			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
		}
		
		request.getSession().setAttribute("listaDesafiliacion",listaDesafiliacion);
		return mapping.findForward("iniciarDesafiliacion");
	}

	public ActionForward regresar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List listaDesafiliacion = null;
		String path = ""; 
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombre");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"Nro. de Servicio");
		
		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_TELEFONO)){
		    request.setAttribute("hidServicio", "1");
		    path = request.getContextPath()+"/pagoTelefono.do";
		    response.sendRedirect(path);
		}
		
		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_MOVISTAR)){
		    request.setAttribute("hidServicio", "3");
		    path = request.getContextPath()+"/pagoTelefono.do";
		    response.sendRedirect(path);
		}
		
		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_TERRA)){
		    request.setAttribute("hidServicio", "4");
		    path = request.getContextPath()+"/pagoTelefono.do";
		    response.sendRedirect(path);
		}
		
		if (Constante.TIPO_SERVICIO.equals(Constante.COD_HLP_PGO_CMAGICO)){
		    request.setAttribute("hidServicio", "7");
		    path = request.getContextPath()+"/pagoTelefono.do";
		    response.sendRedirect(path);
		}
		
		if (Constante.TIPO_SERVICIO.equals("00441")){
		    path = request.getContextPath()+"/pagoSedapal.do";
		    response.sendRedirect(path);
		}
		
		if (Constante.TIPO_SERVICIO.equals("00420")){
		    path = request.getContextPath()+"/pagoTarjeta.do";
		    response.sendRedirect(path);
		}
		
		if (ConstanteSesion.TITULO.equals("DESAFILIACION TRANSFERENCIAS MISMO BANCO")){
		    path = request.getContextPath()+"/transferenciaMismoBanco.do";
		    response.sendRedirect(path);
		} 
		
		if (ConstanteSesion.TITULO.equals("DESAFILIACION TRANSFERENCIAS INTERBANCARIAS")){
		    path = request.getContextPath()+"/transferenciaInterbancaria.do";
		    response.sendRedirect(path);
		}
		
		if (ConstanteSesion.TITULO.equals("DESAFILIACION DE GIROS")){
		    path = request.getContextPath()+"/telegiros.do";
		    response.sendRedirect(path);
		    return mapping.findForward("iniciarTelegiro");
		}
		
		return mapping.findForward("iniciar");
		
	}

	public ActionForward confirmarDesafiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DesafiliacionForm frm  = (DesafiliacionForm)form;
		Afiliacion af = new AfiliacionImpl();
		return mapping.findForward("confirmacionDesafiliacion");
	}
	
	public ActionForward finDesafiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List listaDesafiliacion = new ArrayList();
		String[] idPrincipal = ObjectUtil.getArrayStrings(request.getParameter("listDesafilia"),"¬");
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


   	
   		
   		int nNum = idPrincipal.length;
   		
   	
   		Afiliacion afFinal = null;
   		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajecabeceraconfdesaf",((Vector)aplicacion.getMensajePorCodigo("DS01","00001")).elementAt(2).toString());
		try {
   		for(int i =0; i<nNum; i++)
   		{
   			String[] id = ObjectUtil.getArrayStrings(idPrincipal[i],"-");
   			Afiliacion af = FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0],id[1],new Long(id[2]), Constante.TIPO_SERVICIO);
   			
   			af.setTipoAfiliacion(id[0]);
   			
   	   		af.setNroTarjeta(id[1]);
   	   		 	   		af.setSecuencia(new Long(id[2]));
   	   		List arr= (List)request.getSession().getAttribute("listaDesafiliacion");
	   	   	for (Iterator iter = arr.iterator(); iter.hasNext();) {	
				AfiliacionImpl element = (AfiliacionImpl) iter.next();
				if(element.getSecuencia().toString().trim().equals(af.getSecuencia().toString().trim())){
					af.setDescripcion(element.getDescripcion());
					if(af.getTipoAfiliacion().equals("TRAM")){
						af.setNumSerFormat(ObjectUtil.formatearCuenta(af.getNumeroServicio(),Constante.FORMATO_CUENTA));	    
					}
				}
			}
	   	
   	   		FacadeFactory.getAfiliacionFacade().desafiliar(af);
   	   		listaDesafiliacion.add(af);
   	   		afFinal = af;
   		}
   		
			request.getSession().setAttribute(ConstanteSesion.DESAFILIAR, afFinal);
		} catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("iniciarDesafiliacion");
			throw e;
		}
		request.getSession().setAttribute(ConstanteSesion.DESAFILIADOS,listaDesafiliacion);
		return mapping.findForward("finDesafiliar");
	}
	   
}
