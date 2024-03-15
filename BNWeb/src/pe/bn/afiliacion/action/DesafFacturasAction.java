/*
 * Created on 20/08/2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.afiliacion.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;

/**
 * @author 2424010
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DesafFacturasAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(DesafFacturasAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List listaDesafiliacion = null;
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"Nombre");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"Nro. de Referencia:");
		
		    ConstanteSesion.TITULO = Constante.DESAF_FACTURAS_TITULO;
			request.getSession().setAttribute("TITULO",Constante.DESAF_FACTURAS_TITULO);

			listaDesafiliacion =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(hidCodEntidad,usuario.getTarjeta().getNumero(), "09044");
		
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
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"Nro. de Referencia");
		
		    request.setAttribute("hidServicio", "3");
		    path = request.getContextPath()+"/pagoFacturas.do?hidServicio=11";
		    response.sendRedirect(path);
		
		return mapping.findForward("iniciar");
		
	}

	public ActionForward finDesafiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List listaDesafiliacion = new ArrayList();
		String[] idPrincipal = ObjectUtil.getArrayStrings(request.getParameter("listDesafilia"),"¬");
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


   	
   		
   		int nNum = idPrincipal.length;
   		
   		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
   		
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
