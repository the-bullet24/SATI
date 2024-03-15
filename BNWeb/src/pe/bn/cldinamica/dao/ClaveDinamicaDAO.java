package pe.bn.cldinamica.dao;

import java.io.OutputStream;
import java.util.List;

import pe.bn.login.domain.Menu;
import pe.bn.tcredito.domain.Formato;

public interface ClaveDinamicaDAO {

	

	public List obtenerListaBinesMedioAutenticacion(String estado) throws Exception;
	public List verificarExistenciaMedioAutenticacion( String binVirtual, String codigoMA) throws Exception;
	public List obtenerListaElementoSeguridadRelacionados( String binVirtual, String codigoMA) throws Exception;
}