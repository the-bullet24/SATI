package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;

public class BuscadorComboImpl implements Serializable {
	
	public List getComboDetalleHlp(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetalleHlp(codAyuda);
		ComboUtil combo = new ComboUtil();
		combo.setCodigo("");
		combo.setDescripcion(Constante.SELECCIONE);		
		lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	
	public List getFlagTransaccion(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getFlagTransaccion(codAyuda);
//		System.out.println(lstDetalleHlp.size());
		return lstDetalleHlp;
	}
	
	
	public List getComboDetalleHlpOrden(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetalleHlpOrden(codAyuda);
		ComboUtil combo = new ComboUtil();
		combo.setCodigo("");
		combo.setDescripcion(Constante.SELECCIONE);		
		lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	public List getComboDetHlp(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetHlp(codAyuda);
		return lstDetalleHlp;
	}
	
	public List getComboDetHlpOrden(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetHlpOrden(codAyuda);
		return lstDetalleHlp;
	}
	
	public List getComboAyudaHijo(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboAyudaHijo(codAyuda);
		ComboUtil combo = new ComboUtil();
		combo.setCodigo("");
		combo.setDescripcion(Constante.SELECCIONE);		
		lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	
	public List getComboAyudaHijoOrden(String codAyuda)throws Exception{
	
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboAyudaHijoOrden(codAyuda);
		ComboUtil combo = new ComboUtil();
		combo.setCodigo("");
		combo.setDescripcion(Constante.SELECCIONE);		
		lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	
	public List getComboDetHlpTasa(String codAyuda)throws Exception{
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetHlpTasa(codAyuda);
		return lstDetalleHlp;
	}
	
	
	public List getComboDetalleHlpHijo(String codAyuda, String codHijo) {
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo(codAyuda,codHijo);
		ComboUtil combo = new ComboUtil();
		combo.setCodigo("");
		combo.setDescripcion(Constante.SELECCIONE);		
		lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	
	public List getComboDetalleHlpHijoCodhlp(String codAyuda, String codHijo) {
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoCodhlp(codAyuda,codHijo);
		ComboUtil combo = new ComboUtil();
		//combo.setCodigo("");
		//combo.setDescripcion(Constante.SELECCIONE);		
		//lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	
	public List getComboDetalleHlpHijoMod(String codAyuda, String codHijo) {
		List lstDetalleHlp = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(codAyuda,codHijo);
//		ComboUtil combo = new ComboUtil();
//		combo.setCodigo("");
//		combo.setDescripcion(Constante.SELECCIONE);		
//		lstDetalleHlp.add(0, combo);
		return lstDetalleHlp;
	}
	
	public ComboUtil getObjDetalleHlp(String codAyuda, String codHijo) throws Exception{
	    return DAOFactory.getGeneraDAO().getObjDetalleHlp(codAyuda,codHijo);
	}
	
	public List getComboTipoCuenta() throws Exception{
	    List lstTipoCuenta = new ArrayList();
	    lstTipoCuenta.add(getComboUtil("","Seleccione..."));
	    lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.COD_CUENTA_AHORROS_MN,"Ahorros MN"));
	    lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.COD_CUENTA_AHORROS_ME,"Ahorros ME"));
	    lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.COD_CUENTA_CORRIENTE_MN,"Cta.Cte. MN"));
	    lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.COD_CUENTA_CORRIENTE_ME,"Cta.Cte. ME"));
	    //lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.COD_CUENTA_CTS_MN,"CTS MN"));
	   // lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.COD_CUENTA_CTS_ME,"CTS ME"));
	    return lstTipoCuenta;
	}
	
	public List getComboTipoCuentaBancaCel() throws Exception{
	    List lstTipoCuenta = new ArrayList();
	    lstTipoCuenta.add(getComboUtil("","Seleccione..."));
	    lstTipoCuenta.add(getComboUtil(pe.cosapi.common.Constante.BAN_CELULAR_COD_CUENTA_AHORROS_MN,"Ahorros MN"));
	   
	   
	    return lstTipoCuenta;
	}
	
	public ComboUtil getComboUtil(String codigo, String descripcion){
	    ComboUtil bean = new ComboUtil();
	    bean.setCodigo(codigo);
	    bean.setDescripcion(descripcion);
	    return bean;
	}
}
