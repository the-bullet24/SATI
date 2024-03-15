package pe.bn.login.dao;

import java.util.List;

import pe.bn.login.domain.Menu;

public interface LoginDAO {
	public abstract Menu[] getMenuByCode(String codigoPersona,String codigoTarjeta) 	throws Exception;
	public abstract Menu[] getMenuByCode1(String codigoPersona,String codigoTarjeta) 	throws Exception;
	public abstract Menu[] getSubMenuByCode(String codigoPersona,String codigoPadre) 	throws Exception;
	public abstract Menu[] getSubMenuByCode1(String codigoPersona,String codigoPadre, String ip) 	throws Exception;
	public abstract Menu[] getSubMenuByCode2(String codigoPersona,String codigoPadre, String codigoHijo) 	throws Exception;
	public abstract Menu[] getMenuByCodeOtros(String codigoPersona,String codigoTarjeta) 	throws Exception;
	public abstract Menu[] getSubMenuByCodeOtros(String codigoPersona,String codigoPadre) 	throws Exception;
	public abstract List getValidaImagen(String numTarjeta) throws Exception;
	public abstract void getAfiliaImagen(String numTarjeta, String cNomImagen, String cNumSec, String cNumCat, String ip, String estado) throws Exception;
	public abstract void getIntentosImagen(String numTarjeta, int numIntDia, String ip) throws Exception;
	public abstract void getUltimoIngreso(String numTarjeta,String ip) throws Exception;
	public abstract void getCambioEstado(String numeroSec, String estado) throws Exception;
	public abstract List getValidaExiste  (String numTarjeta) throws Exception;
	public abstract void getFechaIngreso(String cNumTar, String cNumSec, String ip) throws Exception ;
	public abstract void getUltimoIngresoFecha(String cNumTar,String ip) throws Exception ;
	
	public abstract String getNextSec() throws Exception;
	public abstract String getNextSecFech() throws Exception;
}