package pe.bn.cldinamica.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.cosapi.domain.Usuario;


	/**
	 * @author Mily Dolores Bustamante
	 *
	 * TODO Para cambiar la plantilla de este comentario generado, vaya a
	 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
	 */
	public interface afiliacionDAO {
		
	
		public String mostrarIdBNWSF02() throws Exception;
		public Integer insertAfiliacion(Afiliacion afiliacion, Usuario usuario, Integer idAfil, String ip, String claveValid) throws Exception;
		public List consultarBNWSF02(String numTarjeta) throws SQLException;
	
}
