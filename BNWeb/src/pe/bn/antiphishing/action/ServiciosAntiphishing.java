package pe.bn.antiphishing.action;

import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;

public class ServiciosAntiphishing {

	
	public String validaIP(String ip){		
		
		String flag = "";
		String estado = "";
		
		try {
			
			//validar estado de la opción
			estado = FacadeFactory.getServiciosAntiphishingFacade().getOpcionAntiphishing(Constante.OPCION_ANTIPHISHING_VALIDA_IP);
			
			if(estado.equals(Constante.OPCION_ANTIPHISHING_ESTADO_ACTIVO)){
				
				flag = FacadeFactory.getServiciosAntiphishingFacade().getValidaIP(ip);
				
				if(flag.equals("")) flag = ""+Constante.FLAG_ANTIPHISHING_ERROR_VALIDA_IP;
				

			}else if(estado.equals(Constante.OPCION_ANTIPHISHING_ESTADO_INACTIVO)){
				flag = ""+Constante.FLAG_ANTIPHISHING_NO_VALIDA_IP;
			}
			
//			if(Constante.VER_LOG) {
//				System.out.println("FLAG_VALIDA_IP:"+flag);
//			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
