/*
 * Creado el 12/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package com.cosapisoft.sarawebbanking.admin;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TestInicioDia {
	public static void main(String[] args) throws Exception{
		 ProcessBean processBean = new ProcessBean();
			ArrayList listaSubProcesos = new ArrayList();
			
			processBean.setProcessID(NavigationConstant.ID_INICIO_DIA);
			processBean.setTitleProcess("Inicio de Día");
			
			//listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_SISTEMA);
			//listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_FECHA);
			listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_AGENCIA);
			listaSubProcesos.add(ProcessBean.PROC_PASE_DIARIO);
			listaSubProcesos.add(ProcessBean.PROC_DEPURACION_HISTORICO);
			listaSubProcesos.add(ProcessBean.PROC_DESBLOQUEO_AGENCIA);
			
			processBean.setSubProcessList(listaSubProcesos);
			
	
	}
	

	
}
