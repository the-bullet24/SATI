
package pe.bn.afiliacion.facade;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;


public interface AfiliacionFacade {
	
	public List getAfiliaciones(String tipAfiliacion, String nroTarjeta, String campoAyuda) throws Exception;
	
	public List getAfiliaExiste(String tipAfiliacion, String nroTarjeta, String campoAyuda, String numeroServicio) throws Exception;
	
	public List getAfiliaExisteDebito(String tipAfiliacion, String nroTarjeta, String campoAyuda, String numeroServicio, String codServicio) throws Exception;
	
	public Afiliacion getAfiliacion(String tipAfiliacion, String nroTarjeta, Long nroSecuencia, String campoAyuda) throws Exception;

	public void desafiliar(Afiliacion af) throws Exception;
	
	public void desafiliarDebito(Afiliacion af) throws Exception;
	
	public Afiliacion getAfiliacionDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public List validacionRestric(String transaccion, String cuenta, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
		
	public List getConsultaDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress) throws Exception;
	
	public Afiliacion getDesafiliacionDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getModificacionDA(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception;

	public Afiliacion afiliar(Usuario usuario, Afiliacion af)throws Exception;

	public List getAfiliacionByValues(String codigoAyuda,String tipoAfiliacion,String numTarjeta) throws Exception;

	public Afiliacion getAfiliacionTelegiro(Usuario usuario, Afiliacion afiliacion)throws Exception;
	
	public List getListaAfiliaciones(String tipoAfiliacion,String nroTarjeta,String nroCuenta) throws Exception;
	
	//Banca Celular
	
	public List consultaAliasBancaCel(String transaccion,Afiliacion  afiliacion, String telefono,String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public List validacionBancaCel(String transaccion, String cuenta, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion consultaCelularBancaCel(String transaccion, String cuenta, String celular,String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getAfiliacionBancaCel(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getDesafiliacionBancaCel(String transaccion,Afiliacion afiliacion, String tipoOperacion, String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getVinculacionBancaCel(String transaccion,Afiliacion afiliacion, String clave,String remoteAddress,Usuario usuario,HttpServletRequest request) throws Exception;
	
	
	//Datos del Cliente
	
	
	public Afiliacion getConsultaDatosCliente(String transaccion,  String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getConsultaDatosClienteLogin(String transaccion,  String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getActualizaDatosCliente(String transaccion,  String remoteAddress, Afiliacion afil,Usuario usuario,HttpServletRequest request) throws Exception;
	
	public Afiliacion getActualizaCelularCliente(String transaccion,  Usuario usuario, String remoteAddress, String celular, String nombreOperador,Afiliacion afil,TransferenciaContactoImpl transferencia,HttpServletRequest request) throws Exception;

	//Elección de Medio de Envío
	
	public Afiliacion getConsultaMedioEnvio(String transaccion,  Afiliacion afiliacion,String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	public Afiliacion getGuardarMedioEnvio(String transaccion,  Afiliacion afiliacion,String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	public Afiliacion getGuardarDirCorresp(String transaccion,  Afiliacion afiliacion,String remoteAddress, Usuario usuario,HttpServletRequest request) throws Exception;
	
	//Claves Dinámicas - MDB

	public String mostrarIdBNWSF02() throws  Exception;

	public Integer insertAfiliacion(Afiliacion afiliacion, Usuario usuario, Integer idAfil, String ip, String claveValid) throws Exception;
	
	public List consultarBNWSF02(String numTarjeta) throws SQLException;
	
	
	//NCB
	//public void guardarLogDomicilio(String transaccion,  String remoteAddress, Usuario usuario,DomicilioCliente domicilio,HttpServletRequest request) throws Exception;
	//public void guardarLogTelefono(String transaccion,  String remoteAddress, Usuario usuario,TelefonoCliente telefono,String tipoTelefono,HttpServletRequest request) throws Exception;
	//public void guardarLogEmail(String transaccion,  String remoteAddress, Usuario usuario,CorreoCliente correo,HttpServletRequest request) throws Exception;
	//public void guardarLogIndicador(String transaccion,  String remoteAddress, Usuario usuario,Afiliacion indicador,HttpServletRequest request) throws Exception;
	
}
