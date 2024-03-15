package pe.bn.trasferenciacontacto.facade;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;

public interface TransferenciaContactoFacade {
	
	public TransferenciaContacto enviaSmsCelularContacto( Usuario usuario ,TransferenciaContactoImpl transf,String remoteAddress) throws Exception;
	public TransferenciaContacto validaCelularContacto( Usuario usuario ,String codeSMS,TransferenciaContactoImpl transf, String remoteAddress) throws Exception;
	public TransferenciaContacto registroContacto( Usuario usuario , Cuenta cuenta, TransferenciaContactoImpl transf,  String remoteAddress, HttpServletRequest request) throws Exception;
	public TransferenciaContacto eliminarContacto( Usuario usuario , TransferenciaContactoImpl transf,String remoteAddress, HttpServletRequest request) throws Exception;
	public TransferenciaContacto actualizarContacto( Usuario usuario , TransferenciaContactoImpl transf,  String remoteAddress, HttpServletRequest request) throws Exception;
	public TransferenciaContacto consultarContacto( Usuario usuario ,  String remoteAddress) throws Exception;
	public java.util.List obtenerMotivoDes( Usuario usuario ,  String remoteAddress) throws Exception;
	public java.util.List barridoClientes( Usuario usuario , String celular, String remoteAddress) throws Exception;
	public TransferenciaContacto actualizaDatosCliente(String transaccion,  String remoteAddress, Afiliacion afil,Usuario usuario,HttpServletRequest request) throws Exception;

	

}
