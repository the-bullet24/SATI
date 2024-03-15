package com.cosapisoft.sarawebbanking.admin;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class JobInicioDia extends Thread{
	private boolean flag=true;
	
	public void run() {
//		while(flag){
//			if(EjecucionDeProcesoServlet.esEjecutable()){		
//				System.out.println("ENTRO A LA VALIDACION DE EJECUCION DE JOB INICIO DE DIA");
//				System.out.println("ENTRO A LA VALIDACION DE EJECUCION DE JOB INICIO DE DIA");
//				//EjecucionDeProcesoServlet.ejecutarInicioDeDia();	
//			}	
//			
//			Date dHoraActual = new Date();
//			Date dHoraVerifica = new Date();
//			dHoraVerifica.setHours(23);
//			dHoraVerifica.setMinutes(0);
//			dHoraVerifica.setSeconds(0);
//			
//			if (dHoraActual == dHoraVerifica){
//			    EjecucionDeProcesoServlet.ejecutarReInicioDeDia();
//			    System.out.println("ENTRO A LA VALIDACION DE EJECUCION DE JOB RE-INICIO DE DIA");
//			}
//			try {
//				sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	public void detener(){
		this.flag=false;
	}
}
