
package pe.bn.consulta.facade;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.cosapi.domain.Usuario;


public interface PrestamoFacade{
	
	public PrestamoImpl simularPrestamo(PrestamoImpl datos, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	public PrestamoImpl confirmarPrestamo(PrestamoImpl datos, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	public void descargarDocumento(InputStream documentoStream,OutputStream contentStream) throws Exception;
	
	
}