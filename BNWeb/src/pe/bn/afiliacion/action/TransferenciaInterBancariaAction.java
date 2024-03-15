
package pe.bn.afiliacion.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.form.AfiliacionTransferenciaInterBancariaForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.dto.PersonaDTO;

public class TransferenciaInterBancariaAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaInterBancariaAction.class.getName());
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		loadObject(request);
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		AfiliacionTransferenciaInterBancariaForm frm = (AfiliacionTransferenciaInterBancariaForm)form;
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajepieafiltransfint",((Vector)aplicacion.getMensajePorCodigo("TB05","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeafiliacion",((Vector)aplicacion.getMensajePorCodigo("TB05","AF002")).elementAt(2).toString());
		frm.reset(mapping, request);
		
		//REALIZAR CONSULTA DE COORDENADAS
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosElemSegTDC elementos = null;
		ElemenSeguridad resultCoord = null;
		//request.getSession().setAttribute("tipoElemento",null );	
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicio");
			throw ar;
		}
		
		// REALIZAR CONSULTA DE COORDENADAS
		
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
				ar.setForward("inicio");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		return mapping.findForward("inicio");
	}
	
	public ActionForward ayuda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("ayuda");
	}
	
	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = SeguridadUtil.getUsuarioSession(request);
		
		AfiliacionTransferenciaInterBancariaForm frm = (AfiliacionTransferenciaInterBancariaForm)form;
		Afiliacion afiliacion = getAfiliacion(frm);
		//afiliacion.setClave6Digitos(SeguridadUtil.getClaveDesencriptada(frm.getTxtNumClave(),request));
		
		
		String codCuentaCCI = afiliacion.getNumeroServicio();
		String codBancoCCI = codCuentaCCI.substring(0, 3);
		String codCCIFormateada  = codCuentaCCI.substring(0, 3)+"-"+codCuentaCCI.substring(3, 6)+"-"+codCuentaCCI.substring(6, 18)+"-"+codCuentaCCI.substring(18, 20);
		
		ComboUtil lista = new ComboUtil();
		lista = DAOFactory.getGeneraDAO().getObjDetalleHlp("00460",codBancoCCI.trim());
		
		
		if (lista == null){
			loadObject(request);
			frm.setTxtNumClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El Nro. CCI no es valido para la Transferencia Interbancaria.");
		} else if (lista.getCodigo().trim() == ""){
			loadObject(request);
			frm.setTxtNumClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El Nro. CCI no es valido para la Transferencia Interbancaria.");
		}else{
		afiliacion.getObjBenef().setObjBanco(lista);
		}
		
		List listaAfiliaciones = null;
		listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(), "00460", afiliacion.getNumeroServicio());
		
		if (listaAfiliaciones.size() > 0){
			loadObject(request);
			frm.setTxtNumClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de CCI: " + afiliacion.getNumeroServicio() +" ya se encuentra afiliado.");
		}
		
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		try {
			// Nuevo Código para TDC
			ElemenSeguridad resultCoord = null;
			
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			
			String pinblock = request.getParameter("txtCoordenada");
						
			/**
			 * NUEVO CODIGO CLAVE DINAMICA
			 */
			
			// REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("inicio");
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
					
					AfiliacionImpl afiliacionX =(AfiliacionImpl) FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
					afiliacionX.getObjBenef().setObjBanco(lista);
					request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacionX);
					
					request.getSession().setAttribute("codCCIFormateada",codCCIFormateada);
					
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
					e.setForward("inicio");
					throw e;
				}
			}

		} catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			frm.setTxtNumClave("");
			throw e;
		}
		
		return mapping.findForward("afiliar");
	}
	
	private void loadObject(HttpServletRequest request)throws Exception {
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);	

		request.setAttribute("lstDocumento", lista);
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_MES));
		
	}
	
	private Afiliacion getAfiliacion(AfiliacionTransferenciaInterBancariaForm frm) throws Exception{
		
		PersonaDTO persona 		= new PersonaDTO();
		ComboUtil cBanco 		= new ComboUtil();
		
		persona.setNombre(frm.getTxtNombreBenef());
		persona.setObjBanco(cBanco);
		
		Afiliacion afiliacion = new AfiliacionImpl();
		afiliacion.setObjBenef(persona);
		afiliacion.setTipoAfiliacion(Constante.AFI_TRA_INTER_BANCO);
		
		afiliacion.setNumeroServicio(frm.getTxtCuentaCCI());
		afiliacion.setCodigoServicio(frm.getTxtCuentaCCI().substring(0,3));
		
		
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setSexo(frm.getRdnSexo());
		afiliacion.setEmail(frm.getTxtMail());
		
		afiliacion.setBeneficiario(frm.getTxtNombreBenef());
		afiliacion.setCuentaPropia(frm.getRdnCuentaPropia());
		return afiliacion;
		
	}

}
