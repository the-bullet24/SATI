
package pe.bn.afiliacion.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.ws.http.HttpRequest;

import pe.bn.afiliacion.action.form.AfiliacionOtroBancoForm;
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
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.dto.PersonaDTO;


public class AfiliacionOtroBancoAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionFacturasAction.class.getName());
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		loadObject(request);
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		AfiliacionOtroBancoForm frm = (AfiliacionOtroBancoForm)form;
		frm.reset(mapping, request);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajepieafiliaciontarjetasotrosbancos",((Vector)aplicacion.getMensajePorCodigo("PG01","AF002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecaracteresafiliaciontarjetasotrosbancos",((Vector)aplicacion.getMensajePorCodigo("PG01","AF003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecabeceraafiliaciontarjetasotrosbancos",((Vector)aplicacion.getMensajePorCodigo("PG01","AF004")).elementAt(2).toString());
		
		
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA
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
			ar.setForward("inicio");
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
				ar.setForward("inicio");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);



		return mapping.findForward("inicio");
	}
	
	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = SeguridadUtil.getUsuarioSession(request);
		AfiliacionOtroBancoForm frm = (AfiliacionOtroBancoForm)form;
		Afiliacion afiliacion = getAfiliacion(frm,request);
		
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		
	
		List listaAfiliaciones = null;
		listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_TARJETA,usuario.getTarjeta().getNumero(), "", afiliacion.getNumeroServicio());
		
		if (listaAfiliaciones.size() > 0){
			loadObject(request);
			frm.setTxtNumClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de tarjeta: " + afiliacion.getNumeroServicio() +" ya se encuentra afiliada.");
		}

		
		//afiliacion.setClave6Digitos(SeguridadUtil.getClaveDesencriptada(frm.getTxtNumClave(),request));
		
		
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA
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
		try {
			
		MensajesTDC resultado = new MensajesTDC();
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		ElemenSeguridad resultCoord = null;
		String pinblock = request.getParameter("txtCoordenada");
		
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
				
				request.getSession().setAttribute(ConstanteSesion.AFILIAR_OTRO_BANCO,FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion));
				
								
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
		request.setAttribute("lstBancoDestino", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_BANCO));
		request.setAttribute("lstTipoTarjeta", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_TIP_TARJETA));
		
	}
	
	private Afiliacion getAfiliacion(AfiliacionOtroBancoForm frm,HttpServletRequest request) throws Exception{
		
		PersonaDTO persona = new PersonaDTO();
		ComboUtil cBanco 		= new ComboUtil();
		ComboUtil cTipoTarjeta 	= new ComboUtil();
		UsuarioImpl usuario =(UsuarioImpl)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		persona.setNombre(ObjectUtil.cortarCadena(frm.getTxtNombreBenef().trim(), 18));
		persona.setApellidoPaterno(ObjectUtil.cortarCadena(frm.getTxtApellidoPaternoBenef().trim(), 20));
		persona.setApellidoMaterno(ObjectUtil.cortarCadena(frm.getTxtApellidoMaternoBenef().trim(), 20));
		cBanco.setCodigo(frm.getCmbBancoDestino());
		cTipoTarjeta.setCodigo(frm.getCmbTipoTarjeta());
		persona.setObjBanco(cBanco);
		persona.setObjTipoTarjeta(cTipoTarjeta);
		
		Afiliacion afiliacion = new AfiliacionImpl();
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setObjBenef(persona);
		afiliacion.setTipoAfiliacion(Constante.AFI_OTRO_BANCO);
		afiliacion.setTipoTarjeta(frm.getCmbTipoTarjeta());
		afiliacion.setNumeroServicio(frm.getTxtNroTarjeta());
		afiliacion.setCodigoServicio(frm.getCmbBancoDestino());
		afiliacion.setDescripcion(frm.getTxtNombreTransferencia());
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setSexo(frm.getRdnSexo());
		afiliacion.setEmail(frm.getTxtMail());
		StringBuffer sb = new StringBuffer(frm.getTxtNombreBenef().trim()).append(" ");
		sb.append(frm.getTxtApellidoPaternoBenef().trim()).append(" ");
		sb.append(frm.getTxtApellidoMaternoBenef().trim());
		afiliacion.setBeneficiario(ObjectUtil.cortarCadena(sb.toString().trim(), 60));
		return afiliacion;
	}

	
}
