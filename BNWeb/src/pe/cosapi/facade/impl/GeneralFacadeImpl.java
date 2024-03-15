package pe.cosapi.facade.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.listener.Util;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.RuleException;
import pe.cosapi.domain.Banner;
import pe.cosapi.domain.Branch;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Departamento;
import pe.cosapi.domain.DetalleAyudaDatos;
import pe.cosapi.domain.Estilo;
import pe.cosapi.domain.Journal;
import pe.cosapi.domain.Limit;
import pe.cosapi.domain.MasterTransaction;
import pe.cosapi.domain.MsgComunication;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AgenciaImpl;
import pe.cosapi.domain.impl.BannerImpl;
import pe.cosapi.domain.impl.BranchImpl;
import pe.cosapi.domain.impl.BuscadorComboImpl;
import pe.cosapi.domain.impl.DepartamentoImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.JournalImpl;
import pe.cosapi.domain.impl.LimitImpl;
import pe.cosapi.domain.impl.MasterLimitsImpl;
import pe.cosapi.domain.impl.MasterTransactionImpl;
import pe.cosapi.domain.impl.MsgComunicationImpl;
import pe.cosapi.domain.impl.TipoCambioImpl;
import pe.cosapi.facade.GeneralFacade;
import pe.cosapi.sarabank.bean.Host;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;
import pe.cosapi.system.Key6Digits;
import pe.cosapi.system.ValidacionCuenta;
import pe.cosapi.system.services.UtilWebServices;



public class GeneralFacadeImpl implements GeneralFacade {
	
	/* (non-Javadoc)
	 * @see pe.cosapi.facade.impl.GeneralFacade#getMsgComunication(java.lang.String)
	 */
	public MsgComunication[] getMsgComunication(String trx) throws Exception{
		MsgComunicationImpl msgComunication = new MsgComunicationImpl();
		return msgComunication.getMsgComunication(trx);
	} 
	
	public String getDescripcionLocalidad(String codAyuda, String codCiudad) throws Exception{
	     return DAOFactory.getGeneraDAO().getDescripcionLocalidad(codAyuda,codCiudad);
	}
	
	public String getDiasMigracion(String codhlp) throws Exception{
	     return DAOFactory.getGeneraDAO().getDiasMigracion(codhlp);
	}
	
	public String getUrlPrestamos(String codhlp, String numseq) throws Exception{
	     return DAOFactory.getGeneraDAO().getUrlPrestamos(codhlp, numseq);
	}

	/**
	 * @param trx : codigo de transaccion
	 * @param canal : canal
	 * @param tramaDictionary : cadena de diccionarios
	 * @param data : vector mask
	 * @param cuentas : vector cuentas
	 */
	public String sendHost(String trx, String canal,String tramaDictionary ,Vector data, Vector cuentas) throws Exception{
		//System.out.println("LOGIN SEG GeneralFacadeImpl- trx: " + trx);
		//System.out.println("LOGIN SEG GeneralFacadeImpl- canal: "+canal);
		//System.out.println("LOGIN SEG GeneralFacadeImpl- tramaDictionary: "+tramaDictionary);
		//System.out.println("LOGIN SEG GeneralFacadeImpl- data: "+data);
		//System.out.println("LOGIN SEG GeneralFacadeImpl - cuentas: "+cuentas);
		Host host = new Host();
		String cadena = host.sendHost(trx,canal,tramaDictionary,data,cuentas);
		return cadena;
		
	}
	
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, String par10, String par11, String par12, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, String par10, String par11, String par12, String par13, String par14, String par15, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, String par10, String par11, String par12, String par13, String par14, String par15, String par16, String par17, String par18, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, String par10, String par11, String par12, String par13, String par14, String par15, String par16, String par17, String par18, String par19, String par20, String par21, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, String par10, String par11, String par12, String par13, String par14, String par15, String par16, String par17, String par18, String par19, String par20, String par21, String par22, String par23, String par24, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public String sendHost(String trx, String canal,String tramaDictionary , String par4, String par5, String par6, String par7, String par8, String par9, String par10, String par11, String par12, String par13, String par14, String par15, String par16, String par17, String par18, String par19, String par20, String par21, String par22, String par23, String par24, String par25, String par26, String par27, Vector data, Vector cuentas) throws Exception{
		return this.sendHost(trx,canal,tramaDictionary,data,cuentas);
	}
	public Branch getBranch() throws Exception{
		BranchImpl branchImpl = new BranchImpl();
		Branch branch = branchImpl.getBranch(Constante.COD_BRANCH);
		return branch;
	}

	public Banner getBannerByDate(String tipopersona,Timestamp fecha) throws Exception{
		BannerImpl banner = new BannerImpl();
		return banner.getBannerByDate(tipopersona, fecha);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getMapEstilos()
	 */
	public Map getMapEstilos() throws Exception {
		//System.out.println("LOGIN SEG - Entro al metodo getMapEstilos() Clase GEneralfaceimpl");
		List lista = DAOFactory.getGeneraDAO().getEstilo();
		//System.out.println("LOGIN SEG - Metodo getMapEstilos() - Lista: " + lista);
		Map map = new HashMap();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			Estilo element = (Estilo) iter.next();
			map.put(element.getId().trim(),element);
		}
		return map;
	}
	
	public MasterTransaction getTransactionById(String codTrx) throws Exception{
		MasterTransactionImpl mTransaction = new MasterTransactionImpl();
		return mTransaction.getTransactionById(codTrx);
	}
	
	public void validarBloqueo(){
		
	}
	
	public boolean validarHorarioAtencion(String trx)throws Exception{
		boolean flag = false;
		MasterTransaction mTransaction =  getTransactionById(trx);
		if (mTransaction.isHoraAtencion()){
			flag = true;
		}else{
			throw new ArrayRuleException(ConstanteError.GENERICO,"La transacción seleccionada se encuentra fuera de horario de atención");
		}
		return flag;
	}
	/**
	 * Método encargado de Validar el monto ingresado.
	 * @param dicCuenta : diccionario de la Cuenta
	 * @param dicTipoPersona : diccionario del tipo Persona
	 * @param dicCodMoneda : diccionario de Moneda
	 * @param dicTrx : dicionario de codigo Transaccion
	 * @param dicMonto : diccioanrio del monto
	 * @return
	 */
	public boolean validateLimits(String dicCuenta,String dicTipoPersona, String dicCodMoneda,String dicTrx,String dicMonto
								 ,java.util.Vector data, java.util.Vector cuentas)throws Exception{
								
		boolean flag = false;
		
		String cuenta 		= (String)Transaction.searchValuebyDicc(dicCuenta,data);
		String tipoPersona  = (String)Transaction.searchValuebyDicc(dicTipoPersona,data);
		String codMoneda 	= (String)Transaction.searchValuebyDicc(dicCodMoneda,data);
		String trx 			= (String)Transaction.searchValuebyDicc(dicTrx,data);
		String txtMonto 	= (String)Transaction.searchValuebyDicc(dicMonto,data);
		
		//if(Constante.VER_LOG) System.out.println("begin method validateLimits");
		/*
		String cuenta 		= "0001";String tipoPersona  = "NAT";String codMoneda 	= "SOL";String trx 			= "1630";String txtMonto 	= "113.84";
		*/
		BigDecimal monto = new BigDecimal(txtMonto); 
		BigDecimal bTemp = new BigDecimal("0.00");
		
		MasterLimitsImpl  mLimits_ = new MasterLimitsImpl(tipoPersona, trx);
		
		if (mLimits_.getLimInfOperacion().doubleValue() <=  monto.doubleValue() && monto.doubleValue() <= mLimits_.getLimSupOperacion().doubleValue()){
			JournalImpl  journal_ = new JournalImpl ();
			Journal[] journal =  journal_.getArrayJournal(cuenta, trx);
			for (int i = 0; i < journal.length; i++) {
				bTemp = bTemp.add(journal[i].getAmotra());
			}
			bTemp = bTemp.add(monto);
			
			if ((mLimits_.getLimInfDiario().doubleValue() <= bTemp.doubleValue())&&  bTemp.doubleValue() <= mLimits_.getLimSupDiario().doubleValue() ){
				flag = true;
			}else{
				//Lanzar un exception
				//if(Constante.VER_LOG) System.out.println("lanzar exception 1");
				//TODO EXTRAER DESDE ALGUNA FUNCION DE HENRY POR EL MOMENTO EN DURO
				throw new ArrayRuleException(ConstanteError.COD_VAL_LIMITS_1,"No se Pudo Procesar la Transaccion debido a su Limite Diario.");
			}
		}else{
			//lanzar una exception
			//if(Constante.VER_LOG) System.out.println("lanzar exception 2");
			//TODO EXTRAER DESDE ALGUNA FUNCION DE HENRY POR EL MOMENTO EN DURO
			throw new ArrayRuleException(ConstanteError.COD_VAL_LIMITS_2,"No se Pudo Procesar la Transaccion debido a su Limite de Operacion.");
		}
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see pe.cosapi.facade.impl.GeneralFacade#getComboDetalleAyuda(java.lang.String)
	 */
	public List getComboDetalleAyuda(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleAyuda(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	
	public List getComboDetalleAyuda1(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleAyuda(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}

public List getComboDetalleAyudaOrden(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleAyudaOrden(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}

	public List getComboPais()throws Exception{
	
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetallePais();
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
}

	public List getComboDepartamento()throws Exception{
		
	
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		//List lstHelp = dHelp_.getListDetalleDepartamento();
		List lstHelp = DAOFactory.getGeneraDAO().getListDetalleDepartamento();
				
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	


	public List getComboProvincia(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		//List lstHelp = dHelp_.getListDetalleProvincia(codAyuda);
		List lstHelp = DAOFactory.getGeneraDAO().getListDetalleProvincia(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	public List getComboDistrito(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		//List lstHelp = dHelp_.getListDetalleDistrito(codAyuda);
		List lstHelp = DAOFactory.getGeneraDAO().getListDetalleDistrito(codAyuda);
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	
	public List getComboDepartamentoCore()throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleDepartamentoCore();
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	


	public List getComboProvinciaCore(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleProvinciaCore(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	public List getComboDistritoCore(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleDistritoCore(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	
	public String getDescripcionUbigeo(String codAyuda)throws Exception{
		
	
		return DAOFactory.getGeneraDAO().getDescripcionUbigeo(codAyuda);
		

		
		
	}
	
	public List getComboLocalidad(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleLocalidad(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}
	
public List getComboDetalleAyudaDiscado(String codAyuda)throws Exception{
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lstHelp = dHelp_.getListDetalleAyudaDiscado(codAyuda);
		
		DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		bean.setCodigoDetalleAyuda("");
		bean.setDescripcionDetalle("Seleccione...");
		
		if ( lstHelp.size()== 0)
			lstHelp.add(bean);
		else
			lstHelp.add(0, bean);
		
		return lstHelp;
	}


	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getAgencias(java.lang.String)
	 */
	public List getAgenciasSoles() throws Exception {
		//List lista = DAOFactory.getAgenciaDAO().getAgenciasSoles();
		List lista = DAOFactory.getGeneraDAO().getAgenciasSoles();
		List departamentos = new ArrayList();
		List agencias = new ArrayList();
		String anterior = "";
		DepartamentoImpl departamento = new DepartamentoImpl();
		
		
		for (int i=0;i<lista.size();i++) {
			AgenciaImpl agencia = (AgenciaImpl)lista.get(i);
			departamento= agencia.getDepartamento();
			agencia.setDepartamento(departamento);
			agencias.add(agencia);			
		}
		return agencias;
	}
	public List getAgenciasDolares() throws Exception {
		//List lista = DAOFactory.getAgenciaDAO().getAgenciasDolares();
		List lista = DAOFactory.getGeneraDAO().getAgenciasDolares();
		List departamentos = new ArrayList();
		List agencias = new ArrayList();
		String anterior = "";
		DepartamentoImpl departamento = new DepartamentoImpl();
		
		
		for (int i=0;i<lista.size();i++) {
			AgenciaImpl agencia = (AgenciaImpl)lista.get(i);
			departamento= agencia.getDepartamento();
			agencia.setDepartamento(departamento);
			agencias.add(agencia);			
		}
		return agencias;
	}
	public List getDepartamentos() throws Exception {
		List lista = DAOFactory.getGeneraDAO().getDepartamentos1();
		List departamentos = new ArrayList();
		for (int i=0; i<lista.size(); i++) {
			DepartamentoImpl dep = ((AgenciaImpl)lista.get(i)).getDepartamento();
			departamentos.add(dep);
		}
		return departamentos;
	}
	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getComboDetalleHlp(java.lang.String)
	 */
	public List getComboDetalleHlp(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetalleHlp = buscador.getComboDetalleHlp(codAyuda);
		return lstDetalleHlp; 
	}
	
	public List getComboDetalleHlpOrden(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetalleHlp = buscador.getComboDetalleHlpOrden(codAyuda);
		return lstDetalleHlp; 
	}
	
	public List getComboDetHlp(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetHlp = buscador.getComboDetHlp(codAyuda);
		if (lstDetHlp==null ||lstDetHlp.size()==0)
			return null;
		
		return lstDetHlp;
	}
	
	public List getComboDetHlpOrden(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetHlp = buscador.getComboDetHlpOrden(codAyuda);
		if (lstDetHlp==null ||lstDetHlp.size()==0)
			return null;
		
		return lstDetHlp;
	}
	
	public List getComboDetHlpTasa(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetHlp = buscador.getComboDetHlpTasa(codAyuda);
		if (lstDetHlp==null ||lstDetHlp.size()==0)
			return null;
		
		return lstDetHlp;
	}
	
	public List getFlagTransaccion(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetalleHlp = buscador.getFlagTransaccion(codAyuda);
		return lstDetalleHlp; 
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getComboAyudaHijo(java.lang.String)
	 */
	public List getComboAyudaHijo(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstAyudaHijo = buscador.getComboAyudaHijo(codAyuda);
		return lstAyudaHijo; 
	}
	
	public List getComboAyudaHijoOrden(String codAyuda)throws Exception{
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstAyudaHijo = buscador.getComboAyudaHijoOrden(codAyuda);
		return lstAyudaHijo; 
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getComboDetalleHlpHijo(java.lang.String, java.lang.String)
	 */
	public List getComboDetalleHlpHijo(String codAyuda, String codHijo) throws Exception {
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetalleHlp = buscador.getComboDetalleHlpHijo(codAyuda,codHijo);
		return lstDetalleHlp; 
	}
	
	public List getComboDetalleHlpHijoMod(String codAyuda, String codHijo) throws Exception {
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetalleHlp = buscador.getComboDetalleHlpHijoMod(codAyuda,codHijo);
		return lstDetalleHlp; 
	}

	public List getComboDetalleHlpHijoCodhlp(String codAyuda, String codHijo) throws Exception {
		BuscadorComboImpl buscador = new BuscadorComboImpl();
		List lstDetalleHlp = buscador.getComboDetalleHlpHijoCodhlp(codAyuda,codHijo);
		return lstDetalleHlp; 
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getTipoCambio()
	 */
	public TipoCambio getTipoCambio() throws Exception {
		
		//throw new ArrayRuleException(ConstanteError.MODULO_LOGIN_LONGITUD_REQUERIDA,"número de tarjeta","4");
		return new TipoCambioImpl(null,null);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processValidationCardAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processValidationCardAhorro(String nroDocumento, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		key6_.processValidationCardAhorro(nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processValidationCardAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String processValidationCardAhorroClave(String nroDocumento, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		String cValorRetorno = key6_.processValidationCardAhorroClave(nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		return cValorRetorno;
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processValidationCardCuentaCorriente(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processValidationCardCuentaCorriente(String tipDocumento, String nroDocumento, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		key6_.processValidationCardCuentaCorriente(tipDocumento, nroDocumento, wPINEncript, wPINBLOCKNew, wPINBLOCKConf);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processChangeKey6DigitsAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processChangeKey6DigitsAhorro(String tarjeta, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_  = new Key6Digits();
		String claveAnterior 	=  wPINBLOCKNew;
		String claveNueva 		=  wPINBLOCKConf;
		key6_.processChangeKey6DigitsAhorro(tarjeta, wPINEncript, claveAnterior, claveNueva);
	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processDesafiliaKey6DigitsAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processDesafiliaKey6DigitsAhorro(String tarjeta, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_  = new Key6Digits();
		String claveAnterior 	=  wPINBLOCKNew;
		String claveNueva 		=  wPINBLOCKConf;
		key6_.processDesafiliaKey6DigitsAhorro(tarjeta, wPINEncript, claveAnterior, claveNueva);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processChangeKey6DigitsCorriente(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processChangeKey6DigitsCorriente(String wTipDoc, String nroDocumento, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		/* Para el caso de cuenta corriente viene invertido*/
		String claveNueva 		=  wPINBLOCKConf;
		String claveAnterior 	=  wPINBLOCKNew;
		
		key6_.processChangeKey6DigitsCorriente(wTipDoc, nroDocumento, wPINEncript, wPINBLOCKNew, wPINBLOCKConf);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processGenerationKey6DigitsAhorro(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processGenerationKey6DigitsAhorro(String nroTarjeta, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		key6_.processGenerationKey6DigitsAhorro(nroTarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}
	
	public void processGenerationOlvidoKey6DigitsAhorro(String nroTarjeta, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		key6_.processGenerationOlvidoKey6DigitsAhorro(nroTarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#processGenerationKey6DigitsCorriente(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void processGenerationKey6DigitsCorriente(String wTipDoc, String nroDocumento, String wPINEncript, String wPINBLOCKNew, String wPINBLOCKConf) throws Exception {
		Key6Digits key6_ = new Key6Digits();
		key6_.processGenerationKey6DigitsCorriente(wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#callOperacionesDatosCuenta(java.lang.String)
	 */
//	public String[] callOperacionesDatosCuenta(String numeroCuenta) throws Exception {
//		return UtilWebServices.getInstance().callOperacionesDatosCuenta(numeroCuenta);
//	}
	
	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#validarDatosPersonalesAfiliacion(java.lang.String, pe.bn.afiliacion.domain.Afiliacion, pe.cosapi.domain.Usuario)
	 */
	public void validarDatosPersonalesAfiliacion(Afiliacion afiliacion, Usuario usuario) throws Exception {
	    
	    String numeroCuenta = "";
	    boolean flagCorriente = false;   
	    
	    if (usuario.getCuentaAhorro() != null){
	        numeroCuenta = usuario.getCuentaAhorro().getNumeroProducto();
	    }
	    else{
	        numeroCuenta = usuario.getCuentaCorriente().getNumeroProducto();
	        flagCorriente = true;
	    }
	    
	   // if(Constante.VER_LOG) System.out.println("---- numeroCuenta:"+numeroCuenta);
	    
	    //String[] rest = callOperacionesDatosCuenta(numeroCuenta);   
	    String tipoDoc="";
	    String numDoc="";
	    			
	    			
	    			if(usuario.getCodigoCLDI() != null){
	    				tipoDoc=usuario.getCodigoCLDI().substring(12,13);
	    				numDoc=usuario.getCodigoCLDI().substring(13);	
	    				if(tipoDoc != "1"){
	    					numDoc=Util.removeZero(numDoc);
	    				}else{
	    					numDoc= numDoc.substring(4);
	    				}
	    		
	    			
	    			}
       
		ArrayRuleException exception = new ArrayRuleException();
		boolean flag=false;
		
		if (!tipoDoc.equals(afiliacion.getTipoDocumento().substring(2,3))){
			//if(Constante.VER_LOG) System.out.println("El Tipo de Documento es Diferente");
			exception.addRuleException(new RuleException(ConstanteError.GENERICO,"El Tipo de Documento es Incorrecto"));
			flag = true;
		}
		//if(Constante.VER_LOG) System.out.println("rest[3]:"+rest[3]+"|afiliacion.getNroDocumento()="+afiliacion.getNroDocumento());
		
		String cNumDoc = "";
		
		if (afiliacion.getNroDocumento().trim().length() == 8){
		    cNumDoc = "00"+afiliacion.getNroDocumento();
		} else {
		    cNumDoc = afiliacion.getNroDocumento();
		}
		
		if (!numDoc.equals(cNumDoc)){
			//if(Constante.VER_LOG) System.out.println("N° de Documento Incorrecto");
			exception.addRuleException(new RuleException(ConstanteError.GENERICO,"N° de Documento Incorrecto"));
			flag = true;
		}
//		
//		if (!flagCorriente){
//			Timestamp tm = ObjectUtil.stringTramaToTimestamp(rest[4]);
//			//if(Constante.VER_LOG) System.out.println("tm:"+tm+"|afiliacion.getFechaNacimiento()="+afiliacion.getFechaNacimiento());
//			
//			if (!tm.equals(afiliacion.getFechaNacimiento())){
//				//if(Constante.VER_LOG) System.out.println("La Fecha de Nacimiento es Diferente");
//				exception.addRuleException(new RuleException(ConstanteError.GENERICO,"La Fecha de Nacimiento es Incorrecta"));
//				flag = true;
//			}
//		}
		
		if (flag){
			//if(Constante.VER_LOG) System.out.println("Lanzando Exception, El valor del flag:"+flag);
			throw exception;
		}
		
	}

	/* (sin Javadoc);
	 * @see pe.cosapi.facade.GeneralFacade#getEstadoAplicacion()
	 */
	public boolean getEstadoAplicacion() throws Exception {
		return DAOFactory.getGeneraDAO().getEstadoAplicacion();
	}
	
	public ComboUtil getObjDetalleHlp(String codAyuda, String codHijo) throws Exception{
	    BuscadorComboImpl b = new BuscadorComboImpl();
	    return b.getObjDetalleHlp(codAyuda,codHijo);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#getVerificarHorario()
	 */
	public boolean getVerificarHorario(String transaccion) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return DAOFactory.getGeneraDAO().getVerificarHorario(transaccion);
	}

	/* (sin Javadoc)
	 * @see pe.cosapi.facade.GeneralFacade#actualizarCuentas(pe.cosapi.domain.Usuario, java.lang.String)
	 */
	public void actualizarCuentas(String transaccion,Usuario usuario, String remoteAddress) throws Exception {
		
		Transaction t = new Transaction(transaccion);
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement(transaccion);
		valores.addElement(val);		

		val = new Vector();
		val.addElement("nroTarjeta");
		System.out.println("usuario.getTarjeta().getNumero():"+usuario.getTarjeta().getNumero());
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

		usuario.actualizarCuentas(t);
		GeneralTest gt = new GeneralTest();
		gt.generarLog(t,usuario,null,usuario);
	}

	public void validarLimiteImporte(Cuenta cuenta,BigDecimal monto) throws Exception {
	    BigDecimal totalDiario = new BigDecimal("0.00");
	  	    
	    JournalImpl[] array_ =   arrJournalByNroCuenta(cuenta.getNumeroProducto());
	  
	    for (int i = 0; i < array_.length; i++) {
	        JournalImpl journal = array_[i];
            //if(Constante.VER_LOG) System.out.println("journal.getAmotra():"+journal.getAmotra());
	        /**
	         * Comentado por solicitud de Canales Virtuales (LP)
	         * FECHA:10/04/2012
	         * Se validara el importa máximo por día solo las transacciones sin Comisión ni ITF 
	         */
            //totalDiario = totalDiario.add(journal.getAmotra());
            totalDiario = totalDiario.add(journal.getAmotxn());
        }
	  
	    totalDiario = totalDiario.add(monto);
	    //if(Constante.VER_LOG) System.out.println("totalDiario + monto:"+totalDiario);
	    
	    Limit limit = new LimitImpl();
	    limit.setTipoCuenta( cuenta.getTipoProducto());
	    LimitImpl lim = getLimiteByLimt(limit);
	    
	
	    if (totalDiario.compareTo(lim.getImporteMaximo())==1)
	        throw new ArrayRuleException(ConstanteError.GENERICO,"No se Pudo Procesar la Transaccion debido a su Limite de Importe Máximo.");
	    
	}
	
	public void validarLimiteImporteHost(Usuario usuario,Cuenta cuenta,BigDecimal monto) throws Exception {
		Transaction t = new Transaction("VM00");
		
		Vector valores = new Vector();
		Vector val = new Vector();
		
		val = new Vector();
		val.addElement("transaccion");
		val.addElement("VM00");
		valores.addElement(val);		

		val = new Vector();
		val.addElement("codCuenta");
		val.addElement(cuenta.getNumeroProducto());
		valores.addElement(val);
		
		val = new Vector();
		val.addElement("montoLimite");
		val.addElement(usuario.getMontoLimite());
		valores.addElement(val);	
		
		val = new Vector();
		val.addElement("montoOperacion");
		val.addElement(""+ObjectUtil.formatearMontoTrama(monto));
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
		v.addElement("");
		//v.addElement(remoteAddress);
		ctas.addElement(v);
		
		t.setValues(valores);
		t.setCuentas(ctas);		

		usuario.actualizarCuentas(t);
		GeneralTest gt = new GeneralTest();
		gt.generarLog(t,usuario,null,usuario);
	    
	}
	
	public void validarLimiteImporteConTope(Cuenta cuenta,BigDecimal monto) throws Exception {
	    BigDecimal totalDiario = new BigDecimal("0.00");
	  	    
	    JournalImpl[] array_ =   arrJournalByNroCuenta(cuenta.getNumeroProducto());
	  
	    for (int i = 0; i < array_.length; i++) {
	        JournalImpl journal = array_[i];
                 
            totalDiario = totalDiario.add(journal.getAmotxn());
        }
	
	    totalDiario = totalDiario.add(monto);

	    
	    Limit limit = new LimitImpl();
	  
	    limit.setImporteMaximo(new BigDecimal(Constante.TOPE_MONTO_MAXIMO));
	    
	   
	    if (totalDiario.compareTo(limit.getImporteMaximo())==1)
	        throw new ArrayRuleException(ConstanteError.GENERICO,"No se Pudo Procesar la Transaccion debido a su Limite de Importe Máximo.");
	    
	}
	
	
	public LimitImpl getLimiteByLimt(Limit limit) throws Exception {
	    LimitImpl limit_ = new LimitImpl(limit);
	    limit_.cargar();
	    //if(Constante.VER_LOG) System.out.println("getTipoCuenta():"+limit_.getTipoCuenta());
	    //if(Constante.VER_LOG) System.out.println("getImporteMaximo():"+limit_.getImporteMaximo());
	    return limit_;
	}
	
	public JournalImpl[] arrJournalByNroCuenta(String numeroCuenta) throws Exception {
	    JournalImpl j = new JournalImpl();
	    return j.getArrayJournalByNroCuenta(numeroCuenta);
	}

	public void validacionCuenta(AfiliacionImpl afil) throws Exception{
	    ValidacionCuenta valid_ = new ValidacionCuenta();
	    boolean flag = valid_.validateAccountMod11(afil.getNumeroServicio(),afil.getNumeroServicio().length());
	    if (!flag){
	        throw new ArrayRuleException(ConstanteError.GENERICO,"El No. de Cuenta ingresado es Inválido");
	    }
	}
	
	public void validacionCuentaCCI(AfiliacionImpl afil){
	    ValidacionCuenta valid_ = new ValidacionCuenta();
	    boolean flag = valid_.validateCCI(afil.getNumeroServicio());
	    if (!flag){
	        throw new ArrayRuleException(ConstanteError.GENERICO,"El No. CCI ingresado es Inválido");
	    }
	}
	
	public List getComboTipoCuenta() throws Exception{
	    BuscadorComboImpl buscador = new BuscadorComboImpl();
	    return buscador.getComboTipoCuenta();
	}
	
	public List getComboTipoCuentaBancaCel() throws Exception{
	    BuscadorComboImpl buscador = new BuscadorComboImpl();
	    return buscador.getComboTipoCuentaBancaCel();
	}
	
	//Obtener informacion de datos para frecuentes
	
//public Afiliacion obtenerDatosPersonalesAfiliacion(Usuario usuario) throws Exception {
//	    
//	    String numeroCuenta = "";
//	    boolean flagCorriente = false;   
//	    
//	    if (usuario.getCuentaAhorro() != null){
//	        numeroCuenta = usuario.getCuentaAhorro().getNumeroProducto();
//	    }
//	    else{
//	        numeroCuenta = usuario.getCuentaCorriente().getNumeroProducto();
//	        flagCorriente = true;
//	    }
//	    Afiliacion resultado = new AfiliacionImpl();
//	
//	    
//	    String[] rest = callOperacionesDatosCuenta(numeroCuenta);   
//       
//		ArrayRuleException exception = new ArrayRuleException();
//		boolean flag=false;
//		
//		resultado.setTipoDocumento(rest[2]);
//		
//				
//		if(Integer.parseInt(resultado.getTipoDocumento()) == 1 ){
//			
//			resultado.setNroDocumento(rest[3].substring(2));
//		}else{
//			
//			resultado.setNroDocumento(rest[3]);
//		}
//		
//		
//		
//	
//		return resultado;
//
//	
//	}

public List getComboDetalleConcepto(String codAyuda,String codHijo)throws Exception{
	
	DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	List lstHelp = dHelp_.getListDetalleConcepto(codAyuda,codHijo);
	
	
	DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
	bean.setCodigoDetalleAyuda("");
	bean.setDescripcionDetalle("Seleccione...");
	
	if ( lstHelp.size()== 0)
		lstHelp.add(bean);
	else
		lstHelp.add(0, bean);
	
	return lstHelp;
}

}
