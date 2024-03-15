package pe.bn.tcredito.dao;

import java.io.OutputStream;
import java.util.List;

import pe.bn.login.domain.Menu;
import pe.bn.tcredito.domain.Formato;

public interface TarjetaCreditoDAO {

	
	
	public abstract void  encontrarFormato2( final String numDocumento,final String periodo,final OutputStream contentStream) throws Exception;
	public abstract void  encontrarEstadoFtp(final String usuario,final String clave,final String servidor,final String remotePath, final OutputStream contentStream) throws Exception;
	
	public List buscarEstadoCuenta(String numDocumento) throws Exception;
}