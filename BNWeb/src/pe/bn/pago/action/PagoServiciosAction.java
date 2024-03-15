
package pe.bn.pago.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;


public class PagoServiciosAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(PagoServiciosAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
        
        String flagActdatos = usuario.getFlagActualizaDatoHost();
        if(flagActdatos.equals("1"))
        {
            return mapping.findForward("actualizarDatosPersona");
        }
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	    List list= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_ENTIDADES_PAGOS_SERVICIOS);
	    
	    List listGrpEstado = new ArrayList();
	    List listGrpTelefono = new ArrayList();
	    List listGrpCelular = new ArrayList();
	    List listGrpCable = new ArrayList();
	    List listGrpAgua = new ArrayList();
	    List listGrpLuz = new ArrayList();
	    List listGrpBancosyFin = new ArrayList();
	    List listGrpSeguros = new ArrayList();
	    List listGrpUniversidades = new ArrayList();
	    List listGrpTasasEducativas = new ArrayList();
	    List listGrpUniversidadesBD = new ArrayList();
	    List listGrpColegios = new ArrayList();
	    List listGrpEmpresas = new ArrayList(); 
	    List listGrpInternet = new ArrayList();
	    List listGrpEmpPubli = new ArrayList();
	    	    
	    Iterator it = list.iterator();
	    while (it.hasNext()){
	        DetalleAyudaDatosImpl dHelp = (DetalleAyudaDatosImpl)it.next();
	        String codGrupo = dHelp.getCodigoDetalleAyuda1().substring(0,2);
	        String tipoTrx = dHelp.getCodigoDetalleAyuda1().substring(2,4);
	       
	        dHelp.setCodigoDetalleAyuda(dHelp.getCodigoDetalleAyuda()+tipoTrx);
	        
	        if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_ESTADO)){ //ESTADO    
	            listGrpEstado.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_TELEFONO)){ //TELEFONO
	            listGrpTelefono.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_CELULAR)){ //CELULAR
	            listGrpCelular.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_CABLE)){ //CABLE
	            listGrpCable.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_AGUA)){ //AGUA
	            listGrpAgua.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_LUZ)){ //LUZ
	            listGrpLuz.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_BANCOSYFIN)){ //BANCOS Y FINANCIERAS
	            listGrpBancosyFin.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_SEGUROS)){ //SEGUROS
	            listGrpSeguros.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_UNIVERSIDADES)){ //UNIVERSIDADES
	            listGrpUniversidades.add(dHelp);
	   
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_COLEGIOS)){ //COLEGIOS
	            listGrpColegios.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_EMPRESAS)){ //EMPRESAS/OTROS
	            listGrpEmpresas.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_INTERNET)){ //INTERNET
	            listGrpInternet.add(dHelp);
	        }else if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_EMP_PUB)){ //INTERNET
	        	listGrpEmpPubli.add(dHelp);
	        }
	    }
	    DetalleAyudaDatosImpl detalleAyuda = new DetalleAyudaDatosImpl();
	    List listUniversidades= detalleAyuda.getListDetalleAyuda(Constante.COD_HLP_DET_ENTIDADES_PAGOS_UNI);
	    Iterator ite = listUniversidades.iterator();
	    while (ite.hasNext()){
	    	 DetalleAyudaDatosImpl dHelp = (DetalleAyudaDatosImpl)ite.next();
	    	 
		     String codGrupo = Funciones.lpad(dHelp.getCodigoDetalleAyuda1(),"0",4) .substring(0,2);
		     String tipoTrx =  Funciones.lpad(dHelp.getCodigoDetalleAyuda1(),"0",4).substring(2,4);
		     dHelp.setCodigoDetalleAyuda(dHelp.getCodigoDetalleAyuda()+tipoTrx);
		     if (codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_UNIVERSIDADES) || codGrupo.equals(Constante.PAGO_SERVICIOS_GRP_TASAS_EDUCATIVAS)){ 
		    	 listGrpUniversidadesBD.add(dHelp);
		     }
	    }
	    
	    for (int i = 0; i < listGrpUniversidadesBD.size(); i++) {
	    	 DetalleAyudaDatosImpl universidadesBD = (DetalleAyudaDatosImpl)listGrpUniversidadesBD.get(i);
	    	 listGrpUniversidades.add(universidadesBD);
		}
	    
		   
	    
	    //listGrpUniversidadesBD.add(listGrpUniversidades);
		 
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_ESTADO,listGrpEstado);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_TELEFONO,listGrpTelefono);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_CELULAR,listGrpCelular);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_CABLE,listGrpCable);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_AGUA,listGrpAgua);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_LUZ,listGrpLuz);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_BANCOSYFIN,listGrpBancosyFin);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_SEGUROS,listGrpSeguros);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_UNIVERSIDADES,listGrpUniversidades);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_COLEGIOS,listGrpColegios);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_EMPRESAS,listGrpEmpresas);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_INTERNET,listGrpInternet);
	    request.getSession().setAttribute(ConstanteSesion.PAGO_SERVICIOS_GRP_EMP_PUB,listGrpEmpPubli);
	    
		return mapping.findForward("iniciaServicios");
	}

	
	public ActionForward PagoServicios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String numeroCuenta = request.getParameter("hidCuenta");		
		String codConsulta = request.getParameter("hidConsulta");
		String codMoneda = request.getParameter("hidMoneda");
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getConsultaFacade().consultar(codConsulta,numeroCuenta,codMoneda, "", request.getRemoteAddr(),usuario,request));
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		return getMappingConsulta(mapping,codConsulta);			
	}
	
	private ActionForward getMappingConsulta(ActionMapping mapping,String codConsulta){
		if(codConsulta.equals("01"))
			return mapping.findForward("consultaSaldoAhorro");
		if(codConsulta.equals("02"))
			return mapping.findForward("consultaUltimosMovimientosAhorros");
		if(codConsulta.equals("03"))
			return mapping.findForward("consultaCCI");
		if(codConsulta.equals("05"))
			return mapping.findForward("consultaSaldoCtaCte");
		if(codConsulta.equals("07"))
			return mapping.findForward("consultaCtaCteCCI");
		if(codConsulta.equals("09"))
			return mapping.findForward("consultaSaldoPrestamo");
		if(codConsulta.equals("10"))
			return mapping.findForward("consultaCalendarioPago");
		if(codConsulta.equals("12"))
			return mapping.findForward("consultaSaldoCTS");
		return null;
	}
	
} 
