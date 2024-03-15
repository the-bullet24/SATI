package pe.bn.WSServiceHost.serHost.util;

public class TextFormat {
	
	public static String getValue(String text){
		return (text!=null)?text.trim():"";
	}

	public static int getEntero(String text){
		try {
			
			return (text!=null)?Integer.parseInt(text.trim()):0;
			
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
		
	}
	
	public static double getDouble(String text){
		try {
			
			return (text!=null)?Double.parseDouble(text.trim()):0;
			
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
		
	}
	
	public static long getLong(String text){
		try {
			
			return (text!=null)?Long.parseLong(text.trim()):0;
			
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
		
	}
	
}
