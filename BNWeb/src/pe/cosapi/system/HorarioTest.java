/*
 * Creado el 15/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.system;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import pe.cosapi.common.DAOFactory;


/**
 * @author cosapi_ati04
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class HorarioTest {

	public static void main(String[] args) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		System.out.println("HORA actual: " + cal.getTime());
		/*System.out.println("HORA getTimeInMillis: " + cal.getTimeInMillis());
		System.out.println("HORA getTimeInMillis: " + new Date(cal.getTimeInMillis()));
		System.out.println("HORA getTimeInMillis +120000 : " + (cal.getTimeInMillis()+120000));
		System.out.println("HORA getTimeInMillis +120000 : " + new Date(cal.getTimeInMillis()+120000));*/
		cal.add(Calendar.MINUTE,2);
		System.out.println("HORA luego de aplicar el metodo sumados 02 minutos: "+ cal.getTime());
		System.out.println("hora: "+ getHora(cal));
		System.out.println("minuto: "+ getMinuto(cal));
		System.out.println("segundo: "+ getSegundo(cal));
		/*
		try {
			DAOFactory.getGeneraDAO().setHorarioInicioDia(getHora(cal)+":"+getMinuto(cal)+":"+getSegundo(cal));
		} catch (Exception e) {
			System.out.println("ERROR!!!");
			e.printStackTrace();
		}*/
		
	}
	 public static String getHora(Calendar cal){
        //Calendar cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        String temp = String.valueOf(hora);
        if (temp.length()==1)
            temp = "0"+temp;
        return temp;
    }
    
    public static String getMinuto(Calendar cal){
        //Calendar cal = Calendar.getInstance();
        int minuto = cal.get(Calendar.MINUTE);
        String temp = String.valueOf(minuto);
        if (temp.length()==1)
            temp = "0"+temp;
        return temp;
    }
    
    public static String getSegundo(Calendar cal){
        //Calendar cal = Calendar.getInstance();
        int segundo = cal.get(Calendar.SECOND);
        String temp = String.valueOf(segundo);
        if (temp.length()==1)
            temp = "0"+temp;
        return temp;
    }
}