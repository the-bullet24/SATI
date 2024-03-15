package pe.cosapi.facade;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.domain.Banner;
import pe.cosapi.domain.Branch;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.MasterTransaction;
import pe.cosapi.domain.MsgComunication;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;

public interface GeneralFacade {
	public abstract MsgComunication[] getMsgComunication(String trx) throws Exception;
	
	public abstract Branch getBranch() throws Exception;
	
	public abstract Banner getBannerByDate(String tipopersona,Timestamp fecha) throws Exception;
	
	public abstract Map getMapEstilos() throws Exception;
	
	public abstract String sendHost(String trx, String canal,String tramaDictionary ,Vector data, Vector cuentas) throws Exception;
	
	public abstract MasterTransaction getTransactionById(String codTrx) throws Exception;
	
	public abstract boolean validateLimits(String dicCuenta,String dicTipoPersona, String dicCodMoneda,String dicTrx,String dicMonto ,java.util.Vector data, java.util.Vector cuentas)throws Exception;
	
	public boolean validarHorarioAtencion(String trx)throws Exception;
	
	public abstract List getComboDetalleAyuda(String codAyuda)throws Exception;
	
	public abstract List getComboDetalleAyuda1(String codAyuda)throws Exception;
	
	public abstract List getComboDetalleAyudaOrden(String codAyuda)throws Exception;
	
	public abstract List getComboPais()throws Exception;
	
	public abstract List getComboDepartamento()throws Exception;
		
	public abstract List getComboProvincia(String codigo)throws Exception;
	
	public abstract List getComboDistrito(String codigo)throws Exception;
	
	public abstract List getComboDepartamentoCore()throws Exception;
	
	public abstract List getComboProvinciaCore(String codigo)throws Exception;
	
	public abstract List getComboDistritoCore(String codigo)throws Exception;
	
	public abstract String getDescripcionUbigeo(String codigo)throws Exception;
	
	public abstract List getComboLocalidad(String codigo)throws Exception;
	
	public abstract List getComboDetalleAyudaDiscado(String codAyuda)throws Exception;
	
	public abstract List getComboDetHlp(String codAyuda)throws Exception;
	
	public abstract List getComboDetHlpOrden(String codAyuda)throws Exception;
	
	public abstract List getComboDetHlpTasa(String codAyuda)throws Exception;
	
	public abstract List getAgenciasSoles()throws Exception;
	
	public abstract List getAgenciasDolares()throws Exception;
	
	public abstract List getDepartamentos()throws Exception;
	
	public abstract List getComboDetalleHlp(String codAyuda)throws Exception;
	
	public abstract List getComboDetalleHlpOrden(String codAyuda)throws Exception;
	
	public abstract List getComboDetalleHlpHijo(String codAyuda,String codHijo)throws Exception;
	
	public abstract List getComboDetalleHlpHijoMod(String codAyuda,String codHijo)throws Exception;
	
	public abstract List getComboDetalleHlpHijoCodhlp(String codAyuda,String codHijo)throws Exception;
	
	public abstract List getFlagTransaccion(String codAyuda)throws Exception;
	
	public abstract TipoCambio getTipoCambio() throws Exception;
	
	public abstract List getComboAyudaHijo(String codAyuda)throws Exception;
	
	public abstract List getComboAyudaHijoOrden(String codAyuda)throws Exception;
	
	public abstract String getDescripcionLocalidad(String codAyuda, String codCiudad) throws Exception;
	
	public abstract String getDiasMigracion(String codhlp) throws Exception;
	
	public abstract String getUrlPrestamos(String codhlp, String numseq) throws Exception;

	public abstract void processValidationCardAhorro(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception;
	
	public abstract String processValidationCardAhorroClave(String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception;
	
	public abstract void processValidationCardCuentaCorriente(String tipDocumento, String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception;
	
	/**
	 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros.
	 * @param wPINEncript   : Clave de 4 digitos Encriptada.
	 * @param claveAnterior : Clave anterior de 6 digitos desencriptada.
	 * @param nuevaClave 	: Nueva clave de 6 digitos desencriptada.
	 * @throws Exception    
	 */
	public abstract void processChangeKey6DigitsAhorro(String tarjeta,String wPINEncript,String claveAnterior,String nuevaClave)throws Exception;
	
	/**
	 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros.
	 * @param wPINEncript   : Clave de 4 digitos Encriptada.
	 * @param claveAnterior : Clave anterior de 6 digitos desencriptada.
	 * @param nuevaClave 	: Nueva clave de 6 digitos desencriptada.
	 * @throws Exception    
	 */
	public abstract void processDesafiliaKey6DigitsAhorro(String tarjeta,String wPINEncript,String claveAnterior,String nuevaClave)throws Exception;
	/**
	 * @param wTipDoc		: Valores que puede tomar 1 = DNI, 2 = Carnet de FFAA, 3 = Carnet de FFPP
	 * @param nroDocumento  : Numero de Documento
	 * @param wPINEncript   : Clave de 4 digitos sin Encriptar
	 * @param nuevaClave  	: Nueva clave de 6 digitos desencriptada
	 * @param claveActual 	: clave actual de 6 Digitos.(clave que sera modificada) desencriptada
	 * @throws Exception    
	 */
	public abstract void processChangeKey6DigitsCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String nuevaClave,String claveActual)throws Exception;
	
	/**
	 * @param nroTarjeta	: Numero de Tarjeta
	 * @param wPINEncript   : clave Encriptada de 4 digitos.
	 * @param wPINBLOCKNew  : nueva clave de 6 digitos desencriptada
	 * @param wPINBLOCKConf : confirmacion de clave de 6 digitos desencriptada
	 * @throws Exception
	 */
	public abstract void processGenerationKey6DigitsAhorro(String nroTarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception;
	
	/**
	 * @param wTipDoc		: Valores que puede tomar 1 = DNI, 2 = Carnet de FFAA, 3 = Carnet de FFPP
	 * @param nroDocumento  : Numero de Documento
	 * @param wPINEncript	: clave de 6 digitos del login.
	 * @param wPINBLOCKNew	: nueva clave de 6 digitos de internet
	 * @param wPINBLOCKConf	: conf nueva clave de 6 digitos de internet
	 * @throws Exception
	 */
	
	public abstract void processGenerationOlvidoKey6DigitsAhorro(String nroTarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception;
	
	/**
	 * @param wTipDoc		: Valores que puede tomar 1 = DNI, 2 = Carnet de FFAA, 3 = Carnet de FFPP
	 * @param nroDocumento  : Numero de Documento
	 * @param wPINEncript	: clave de 6 digitos del login.
	 * @param wPINBLOCKNew	: nueva clave de 6 digitos de internet
	 * @param wPINBLOCKConf	: conf nueva clave de 6 digitos de internet
	 * @throws Exception
	 */
	
	public abstract void processGenerationKey6DigitsCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception;
	
	/**
	 * @param numeroCuenta : Numero de Cuenta.
	 * @return [0]= Codigo de Proceso
	 * 
	 * si codigo Proceso[0] =0 
	 * [1]=Nombre Completo,[2]=Codigo de documento
	 * (Valores que puede tomar 1 = DNI, 2 = Carnet de FFAA, 3 = Carnet de FFPP)
	 * [3]= Numero de Documento,[4]= fecha de Nacimiento(yyyymmdd)
	 * 
	 * si Codigo de Proceso[0] != 0, [1] Descripcion del Error.
	 * @throws Exception
	 */
	//public abstract String[] callOperacionesDatosCuenta(String numeroCuenta)throws Exception;
	

	/**
	 * 
	 * @param afiliacion: Interfaz Afiliacion. se usa para extraer los datos de l jsp para validarlo con los resultados 
	 * @param usuario : objeto usuario se encarga de entrgar las cuentas asociadas a el. 
	 * @throws Exception : exeption
	 */
	public abstract void validarDatosPersonalesAfiliacion(Afiliacion afiliacion,Usuario usuario)throws Exception;
	
	public abstract boolean getEstadoAplicacion() throws Exception;
	
	public abstract ComboUtil getObjDetalleHlp(String codAyuda, String codHijo) throws Exception;
	
	public abstract boolean getVerificarHorario(String transaccion) throws Exception;
	
	
	public abstract void actualizarCuentas(String transaccion,Usuario usuario,String remoteAddress) throws Exception;
	
	public abstract void validarLimiteImporte(Cuenta cuenta,BigDecimal monto) throws Exception ;
	
	public abstract void validarLimiteImporteHost(Usuario usuario,Cuenta cuenta,BigDecimal monto) throws Exception ;
	
	public abstract void validarLimiteImporteConTope(Cuenta cuenta,BigDecimal monto) throws Exception ;
	
	
	public void validacionCuenta(AfiliacionImpl afil) throws Exception;
	
	public void validacionCuentaCCI(AfiliacionImpl afil);
	
	public List getComboTipoCuenta() throws Exception;
	
	public List getComboTipoCuentaBancaCel() throws Exception;
	
	//Obtener Datos personales - Adicionar en frecuentes
	
	//public abstract Afiliacion obtenerDatosPersonalesAfiliacion(Usuario usuario)throws Exception;
	
	public abstract List getComboDetalleConcepto(String codAyuda,String codHijo)throws Exception;
}