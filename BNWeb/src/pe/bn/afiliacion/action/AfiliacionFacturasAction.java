/*
 * Created on 20/08/2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.afiliacion.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.form.AfiliacionPgServiciosForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;

/**
 * @author 2424010
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AfiliacionFacturasAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionFacturasAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		frm.reset(mapping, request);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("AS01","00003")).elementAt(2).toString());
		request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_FACTURAS);
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: FACTURAS");
		request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_FACTURAS));
		request.getSession().setAttribute("nomafil","Nro. Referencia:");
		request.getSession().setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lista0= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_LOCAL);
		List temp= new ArrayList();
		

		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		//request.getSession().setAttribute("tipoElemento",null );	
	
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			if(elementos.getTipoElementoSeguridad().equals("5")){
				System.out.println("Token Fisico");
				usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
				usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
				
			}else if(elementos.getTipoElementoSeguridad().equals("6")){
				System.out.println("Clave Dinamica Digital");
				usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
				usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicioAfFacturas");
			throw ar;
		}
		
		//REALIZAR CONSULTA DE COORDENADAS
		

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
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioAfFacturas");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		
		
		
		//--------------------------------------------------------
		
		//saco el ultimo y lo almaceno en un temporal
		temp.add(0,lista0.get(lista0.size()-1));
		temp.add(0,lista0.get(0));
		//elimino el ultimo alemento q ya alamcene antes en otra lista
		lista0.remove(0);
		lista0.remove(lista0.size()-1);
		temp.addAll(lista0);
		lista0=temp;		
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());


		return mapping.findForward("inicioAfFacturas");
	}
	
	
	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, hidCodEntidad);
		List listaAfiliaciones = null;
	
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(hidCodEntidad,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_FACTURAS, afiliacion.getNumeroServicio());
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: FACTURAS");
			request.setAttribute("nomafil","Nro. Referencia:");
		
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		
		if (listaAfiliaciones.size() > 0){
			request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_FACTURAS);
			loadObject(request);
			frm.setTxtClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de servicio: " + afiliacion.getNumeroServicio() +" ya se encuentra afiliado.");
		}
				
		//---
		String transaccion = request.getParameter("transaccion");
		//afiliacion.setClave6Digitos(SeguridadUtil.getClaveDesencriptada(frm.getTxtClave(),request));
		java.util.Vector valores = new java.util.Vector();
		java.util.Vector ctas=new java.util.Vector();
		java.util.Vector val = new java.util.Vector();
		val = new Vector();
		val.addElement("id");
		val.addElement(request.getSession(false).getId());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cmbTipoDoc",frm.getCmbTipoDoc());
		valores.addElement(val);	
		val = ObjectUtil.getVectorComponent("txtNumDoc",frm.getTxtNumDoc());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("txtDia",frm.getTxtDia());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("cmbMes",frm.getCmbMes());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("txtAnio",frm.getTxtAnio());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("rdnSexo",frm.getRdnSexo());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("txtMail",frm.getTxtMail());
		valores.addElement(val);
		
		Vector v = ObjectUtil.getVectorBlankCuentas(request);
		ctas.addElement(v);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
	
		try {
			
			
			ElemenSeguridad resultCoord = null;
		
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
						
			String pinblock = request.getParameter("txtCoordenada");
		
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			ParametrosElemSegTDC elementos = null;
			try{
				
				
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioAfFacturas");
				throw ar;
			}
			
			
			
			// Código Nuevo para TDC
		
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
				
				
			
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
				
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
						{
						
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
					
					request.getSession().setAttribute(ConstanteSesion.AFILIAR_SERVICIOS,FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion));
									
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
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
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("inicioAfFacturas");
					throw e;
				}
			}
		
	
			
			
			
		} catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			
				request.setAttribute("nomafil","Nro. Referencia:");
					
			loadObject(request);
			frm.setTxtClave("");
			throw e;
		}
		
			
		return mapping.findForward("finAfFacturas");
		
	}
	
	private void loadObject(HttpServletRequest request)throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.setAttribute("codServicioDet",Constante.COD_HLP_PGO_FACTURAS);
		request.setAttribute("lstDocumento",lista);
		
			request.setAttribute("nomafil","Nro. Referencia:");
						
		request.setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_FACTURAS));
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
	}
	
	private Afiliacion setearAfiliacion(AfiliacionPgServiciosForm frm,Tarjeta tarjeta, String codEntidad) throws Exception {
		Afiliacion afiliacion = new AfiliacionImpl();
		
		afiliacion.setTipoAfiliacion(codEntidad);
		afiliacion.setCodigoLocalidad("00");
		afiliacion.setNumeroServicio(frm.getTxtNumServicio());
		
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setTipoDocumento(frm.getCmbTipoDoc());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setSexo(frm.getRdnSexo());
		afiliacion.setEmail(frm.getTxtMail());
		afiliacion.setCodigoServicio(frm.getCmbServicio());
		
		afiliacion.setDescripcion(frm.getTxtNombreServicio());
		return afiliacion;
	}
}
