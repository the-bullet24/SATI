
package pe.bn.afiliacion.action;

import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.form.AfiliacionTransferenciaBancariaForm;
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
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;


public class TransferenciaBancariaAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaBancariaAction.class.getName());

	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		AfiliacionTransferenciaBancariaForm frm = (AfiliacionTransferenciaBancariaForm)form;
		frm.reset(mapping, request);
		request.getSession().setAttribute("mensajeafiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecabeceraafmb",((Vector)aplicacion.getMensajePorCodigo("TB01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajepieafmb",((Vector)aplicacion.getMensajePorCodigo("TB01","00006")).elementAt(2).toString());
		
		// REALIZAR CONSULTA DE COORDENADAS
		
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

	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AfiliacionTransferenciaBancariaForm frm = (AfiliacionTransferenciaBancariaForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, usuario, request);
		//afiliacion.setClave6Digitos(SeguridadUtil.getClaveDesencriptada(frm.getTxtNumClave(),request));
		
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		
		
		List listaAfiliaciones = null;
		listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.TB_MISMO_BANCO,usuario.getTarjeta().getNumero(), "", afiliacion.getNumeroServicio());
		
		if (listaAfiliaciones.size() > 0){
			loadObject(request);
			frm.setTxtNumClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de Cuenta: " + afiliacion.getNumeroServicio() +" ya se encuentra afiliado.");
		}
		
		/**
		 * GU
		 * 18/10/2011
		 * Valida si es una cuenta UOB
		 */
		FacadeFactory.getTransferenciaFacade().getTransferenciaCtaUob("TB00",afiliacion,request.getRemoteAddr());
		
		if (afiliacion.getCtaUob().equals("9999")){
			loadObject(request);
			frm.setTxtNumClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de Cuenta: " + afiliacion.getNumeroServicio() +" es una cuenta no válida para Transferencias.");
		}

		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		try {
			ElemenSeguridad resultCoord = null;
		
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			
			String pinblock = request.getParameter("txtCoordenada");
						
			/**
			 * NUEVO CODIGO CLAVE DINAMICA
			 */
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
					
					Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
					af.getCuenta().setCuentaformateada(ObjectUtil.formatearCuenta(af.getNumeroServicio(),Constante.FORMATO_CUENTA));
					request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					loadObject(request);
					
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
			
		}
		catch (ArrayRuleException e) {
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
		request.setAttribute("lstDocumento",lista);
		request.setAttribute("lstTipoCuenta", FacadeFactory.getGeneralFacade().getComboTipoCuenta());
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
	}
	
	private Afiliacion setearAfiliacion(AfiliacionTransferenciaBancariaForm frm,Tarjeta tarjeta, Usuario usuario, HttpServletRequest request) throws Exception{
		Afiliacion afiliacion = new AfiliacionImpl();
		afiliacion.setTipoAfiliacion(Constante.TB_MISMO_BANCO);
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setSexo(frm.getRdnSexo());
		afiliacion.setEmail(frm.getTxtMail());
		afiliacion.setCodigoServicio(frm.getCmbTipoCuentaDestino());
		if(validaCuentaPropia(frm.getTxtNumeroCuentaDestino(),frm.getRdnCuentaPropia(), usuario, request)){
			afiliacion.setNumeroServicio(frm.getTxtNumeroCuentaDestino());	
		}
		
		afiliacion.setCuentaPropia(frm.getRdnCuentaPropia());
		afiliacion.setBeneficiario(frm.getTxtNombreBeneficiario());
		return afiliacion;
	}
	private boolean validaCuentaPropia(String cuenta, String flgCtapropia, Usuario user, HttpServletRequest request) throws Exception{
		List listaCuentas = user.getCuentas();
		boolean flag= false;
		if(flgCtapropia.equals("S")){
		    //System.out.println("Cuentaa: "+cuenta);
			for(int i=0;i<listaCuentas.size();i++){
				Cuenta cta= (Cuenta)listaCuentas.get(i);
				//System.out.println("Cta "+i+": "+cta.getNumeroProducto());
				if(cuenta.equals(cta.getNumeroProducto())){
					
				    //System.out.println("La cuenta " + cuenta + " es cuenta propia.");
					flag=true;
				}	
			}
			if(!flag){
				loadObject(request);
				throw new ArrayRuleException(ConstanteError.GENERICO,"CUENTA NO ASOCIADA CON TARJETA MULTIRED");
			}
		}else{
			for(int i=0;i<listaCuentas.size();i++){
				Cuenta cta= (Cuenta)listaCuentas.get(i);
				//System.out.println("Cta "+i+": "+cta.getNumeroProducto());
				if(cuenta.equals(cta.getNumeroProducto())){
					loadObject(request);
					//flag=false;
					throw new ArrayRuleException(ConstanteError.GENERICO,"CUENTA ESTA ASOCIADA CON TARJETA MULTIRED" );
				}
			}
			flag=true;
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}