/*
 * Creado el 07/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import pe.bn.afiliacion.dao.AfiliacionDAO;
import pe.bn.antiphishing.dao.ServiciosAntiphishingDAO;
import pe.bn.cldinamica.dao.ClaveDinamicaDAO;
import pe.bn.login.dao.LoginDAO;
import pe.bn.notificaciones.dao.ParametrosSiatDAO;
import pe.bn.notificaciones.dao.TransaccionDAO;
import pe.bn.pago.dao.PagoDAO;
import pe.bn.pago.dao.impl.PagoIbatis;
import pe.bn.tcredito.dao.TarjetaCreditoDAO;
import pe.cosapi.dao.AgenciaDAO;
import pe.cosapi.dao.DescripcionDAO;
import pe.cosapi.dao.EstadoCtaDAO;
import pe.cosapi.dao.GeneralDAO;
import pe.cosapi.dao.ItfDAO;
import pe.cosapi.dao.SabmDAO;
import pe.cosapi.dao.SaraWebLogDAO;
import pe.cosapi.dao.impl.SaraWebLogIbatis;


/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DAOFactory extends ObjectFactory{
	
	public static GeneralDAO getGeneraDAO(){
		return (GeneralDAO) getObject("generalDAO");
	}
	
	public static DescripcionDAO getDescripcionDAO(){
		return (DescripcionDAO) getObject("descripcionDAO");
	}
	
	public static LoginDAO getLoginImpl(){
		return (LoginDAO) getObject("loginDAO");
	}
	
	public static SaraWebLogDAO getSaraWebLogImpl(){
		return (SaraWebLogIbatis) getObject("saraWebLogDAO");
	}
	
	public static AfiliacionDAO getAfiliacionDAO(){
		return (AfiliacionDAO) getObject("afiliacionDAO");
	}
	
	public static TarjetaCreditoDAO getTarjetaCreditoDAO(){
		return (TarjetaCreditoDAO) getObject("tarjetaCreditoDAO");
	}
	
	public static ItfDAO getItfDAO(){
		return (ItfDAO) getObject("itfDAO");
	}
	
	public static EstadoCtaDAO getEstadoCtaDAO(){
		return (EstadoCtaDAO) getObject("estadoCtaDAO");
	}

	public static AgenciaDAO getAgenciaDAO(){
		return (AgenciaDAO) getObject("agenciaDAO");
	}

	public static PagoDAO getPagoDAO(){
		return new PagoIbatis();
	}
	
	public static TransaccionDAO getTransaccionDAO(){
		return (TransaccionDAO) getObject("transaccionDAO");
	}
	
	public static ParametrosSiatDAO getParametrosSiatDAO(){
		return (ParametrosSiatDAO) getObject("parametrosSiatDAO");
	}
	
	public static ServiciosAntiphishingDAO getServiciosAntiphishingDAO(){
		return (ServiciosAntiphishingDAO) getObject("serviciosAntiphishingDAO");
	}
	
    public static ClaveDinamicaDAO getClaveDinamicaDAO(){
        return (ClaveDinamicaDAO) getObject("claveDinamicaDAO");
    }
    
    public static SabmDAO getGeneracionLogDAO(){
        return (SabmDAO) getObject("sabmDAO");
    }
    
}