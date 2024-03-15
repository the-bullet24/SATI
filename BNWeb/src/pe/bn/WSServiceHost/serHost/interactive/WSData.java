package pe.bn.WSServiceHost.serHost.interactive;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.log4j.Logger;

import pe.bn.WSServiceHost.serHost.util.TextFormat;
import pe.com.bn.common.Constante;
import pe.com.bn.common.Funciones;

public class WSData{

	public  Campo[] campos;
	public static final int SIN_ERROR = 100;
	public static final int ERROR_FORMATO = -200;
	public static final int ERROR_GENERICO = -999;
	public static final int ERROR_EXCEPCION = -998;
	public static final int ERROR_TAMANIO_TEXT = -210;
	public static final int ERROR_VALOR_NO_NUMERICO = -220;
	private static Logger logger = Logger.getLogger(WSData.class.getName());
	public static final int TAMNO_DOC_IDENTIDAD = 15;
	
	public static final class Campo implements Serializable{
		
		
		private static final int CHARACTER = 0;
		public static final int CHAR_NULL = 1;
		public static final int CHAR_RIGTH = 2;
		public static final int CHAR_LEFT = 3;
		public static final int NUM_CODIGO = 4;
		public static final int MTO_17 = 5;
		public static final int MTO_9 = 6;
		public static final int MTO_6 = 7;
		public static final int RETURN_INIT_BLNK = 8;
		public static final int NUM_CODIGO_CERO = 9;
		public static final int NUM_CODIGO_NULL = 10;		
		
		public int Codigo;
		public String Descripcion;
		public String Valor;
		public int TipoDato;
		public int Tamano;
		public int Inicio;
		public int Decimales = 0;
		
		public Campo(String Descripcion, int TipoDato, int Tamano) throws Exception {
			this.Descripcion = Descripcion;
			this.Valor = "";
			this.TipoDato = TipoDato;
			this.Tamano = Tamano;
			ajustarLeft(CHARACTER);
		}
		
		public int setDouble(double valor) throws Exception{
			
			 int NumError = ERROR_GENERICO;
			 String msgError = "";
			try{
				switch(TipoDato){
				case MTO_17:
					this.Valor = formatearMonto17(new BigDecimal(valor));
					if(this.Valor.indexOf("E") > 0){
						msgError = "ERROR: El numero supero el tipo de dato del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
						logger.info(msgError);
						throw new Exception(msgError);
						//this.Valor = formatearMonto17(new BigDecimal(0.0));
						//return ERROR_FORMATO;
					}
					if(this.Valor.length()>this.Tamano){
						msgError = "ERROR: Tamaño del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
						logger.info(msgError);
						throw new Exception(msgError);
						//this.Valor = formatearMonto17(new BigDecimal(0.0));
						//return ERROR_FORMATO;
					}
					break;	
				case MTO_9:
					this.Valor = formatearMonto9(new BigDecimal(valor));
					if(this.Valor.indexOf("E") > 0){
						msgError = "ERROR: El numero supero el tipo de dato del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
						logger.info(msgError);
						throw new Exception(msgError);

						//this.Valor = formatearMonto9(new BigDecimal(0.0));
						//return ERROR_FORMATO;
					}					
					if(this.Valor.length()>this.Tamano){
						msgError = "ERROR: Tamaño del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
						logger.info(msgError);
						throw new Exception(msgError);
						//this.Valor = formatearMonto9(new BigDecimal(0.0));
						//return ERROR_FORMATO;
					}
					break;		
				case MTO_6:
					this.Valor = formatearMonto6(new BigDecimal(valor));
					if(this.Valor.indexOf("E") > 0){
						msgError = "ERROR: El numero supero el tipo de dato del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
						logger.info(msgError);
						throw new Exception(msgError);
						//this.Valor = formatearMonto6(new BigDecimal(0.0));
						//return ERROR_FORMATO;
					}					
					if(this.Valor.length()>this.Tamano){
						msgError = "ERROR: Tamaño del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
						logger.info(msgError);
						throw new Exception(msgError);
						//this.Valor = formatearMonto6(new BigDecimal(0.0));
						//return ERROR_FORMATO;
					}
					break;		
				}
				NumError = SIN_ERROR;
			}catch(Exception e){
				e.printStackTrace();
				NumError = ERROR_EXCEPCION;
				throw e;
			}
			return NumError;
		}
		
		public int setString(String valor) throws Exception{
			 
			String msgError = "";
			valor = Funciones.enmascararTramaEnvio(valor);
			int NumError = ERROR_GENERICO;
			try{
				if(valor.trim().length()>this.Tamano){
					msgError = "ERROR: Tamaño del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
					logger.info(msgError);
					throw new Exception(msgError);
					//return ERROR_TAMANIO_TEXT;
				}else{
					switch(TipoDato){
					case CHAR_NULL:
						this.Valor = valor;
						ajustar(CHARACTER);
						break;
					case CHAR_RIGTH:
						this.Valor = valor;
						ajustarRight(CHARACTER);
						break;	
					case CHAR_LEFT:
						this.Valor = valor;
						ajustarLeft(CHARACTER);
						break;
						
					case NUM_CODIGO_NULL:
						if(valor.trim().equals("")){
							this.Valor = "";
							ajustarLeft(CHARACTER);
						}else{
							if(isNumero(valor)){
								this.Valor = String.valueOf(Long.parseLong(valor.trim()));
								ajustarRight(NUM_CODIGO);
							}else{
								this.Valor = "";
								ajustarLeft(CHARACTER);
								return ERROR_VALOR_NO_NUMERICO;
							}
						}
						break;						
					case NUM_CODIGO:
						if(valor.trim().equals("")){
							this.Valor = "0";
							ajustarLeft(NUM_CODIGO_CERO);
						}else{
							if(isNumero(valor)){
								this.Valor = String.valueOf(Long.parseLong(valor.trim()));
								ajustarRight(NUM_CODIGO);
							}else{
								this.Valor = "";
								ajustarLeft(CHARACTER);
								return ERROR_VALOR_NO_NUMERICO;
							}
						}
						break;
					case NUM_CODIGO_CERO:
						if(valor.trim().equals("")){
							this.Valor = "0";
							ajustarRight(NUM_CODIGO_CERO);
						}else{
							if(isNumero(valor)){
								this.Valor = String.valueOf(Long.parseLong(valor.trim()));
								ajustarRight(NUM_CODIGO_CERO);
							}else{
								this.Valor = "";
								ajustarLeft(CHARACTER);
								return ERROR_VALOR_NO_NUMERICO;
							}
						}
						break;
					case RETURN_INIT_BLNK:
						this.Valor = "";
						ajustar(CHARACTER);
						break;		
					}					
					NumError = SIN_ERROR;
				}
			}catch(Exception e){
				e.printStackTrace();
				NumError = ERROR_EXCEPCION;
				throw e;
			}
			return NumError;
		}


		private void ajustarRight(int tipoDato){
			String result = "";
			String valor = this.Valor.trim().toUpperCase();
			for(int i=0;i<(Tamano-valor.length());i++){
				char c = (tipoDato == CHARACTER)?' ':'0';
				result = ((tipoDato != CHARACTER)?(String.valueOf(c)):"") + result + ((tipoDato == CHARACTER)?(String.valueOf(c)):"");					
			}
			result = result+ valor;
			this.Valor = result;
		}

		private void ajustarLeft(int tipoDato){
			String result = "";
			String valor = this.Valor.trim().toUpperCase();
			for(int i=0;i<Tamano;i++){
				char c = (tipoDato == CHARACTER)?' ':'0';
				if(valor.length()>i){
					c = valor.charAt(i);
					result += c;
				}else{
					result = ((tipoDato != CHARACTER)?(String.valueOf(c)):"") + result + ((tipoDato == CHARACTER)?(String.valueOf(c)):"");
				}
			}			
			this.Valor = result;			
		}
		
		private void ajustar(int tipoDato){
			String result = "";
			String valor = this.Valor.toUpperCase();
			for(int i=0;i<Tamano;i++){
				char c = (tipoDato == CHARACTER)?' ':'0';
				if(valor.length()>i){
					c = valor.charAt(i);
					result += c;
				}else{
					result = ((tipoDato != CHARACTER)?(String.valueOf(c)):"") + result + ((tipoDato == CHARACTER)?(String.valueOf(c)):"");
				}
			}			
			this.Valor = result;
		}
		
		private boolean isNumero(String valor) throws Exception {
			boolean si = false;
			String msgError = "";
			try {
				Long.parseLong(valor.trim());
				si = true;
			} catch (Exception e) {
				msgError = "ERROR: En el tipo de Dato del campo [" + this.Descripcion + "] =" + this.Tamano + " Valor Insertado:" + valor;
				logger.info(msgError);
				throw new Exception(msgError);
			}
			return si;
		}
		
		
		public static String formatearMonto17(BigDecimal monto){		
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("+00000000000.0000;-00000000000.0000");
			formateador.setDecimalFormatSymbols(simbolos);
			return formateador.format(monto);		
		}
		
		public static String formatearMonto9(BigDecimal monto){		
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("0000.0000");
			formateador.setDecimalFormatSymbols(simbolos);
			return formateador.format(monto);		
		}

		public static String formatearMonto6(BigDecimal monto){		
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("000.00");
			formateador.setDecimalFormatSymbols(simbolos);
			return formateador.format(monto);		
		}
		
		public String get(){
			return this.Valor;
		}
		
		public String toString() {
			return Valor;
		}
		
		public static String lpad(String valueToPad, String filler, int size) {   
	        while (valueToPad.length() < size) {   
	            valueToPad = filler + valueToPad;   
	        }   
	        return valueToPad;   
	    }   
	       
	    public static String rpad(String valueToPad, String filler, int size) {   
	        while (valueToPad.length() < size) {   
	            valueToPad = valueToPad+filler;   
	        }   
	        return valueToPad;   
	    }  		
		
	}//Fin Clase

	 
	protected String separador = "";
	protected String printFillBody = Constante.gBN_CONST_IMPRIMIR_TRAMA;
    
	protected void init(){
		
	}
	
	protected WSData() {
		init();
	}

	public String getByIndex(int index){
		if(campos!=null){
			if(index<campos.length){
				return campos[index].get();
			}
		}
		return null;
	}
	
//	public String toString(){
//		StringBuffer camposstring = new StringBuffer("");
//		
//		if(campos!=null){
//			for(int c=0; c<campos.length; c++){
//				camposstring.append(campos[c].get() + separador);
//			}
//		}
//		
//		return camposstring.toString();
//	}
	
	
	public String toString(){
		
		if (printFillBody.equals("1")){
			logger.info("*********** [ENVIO TRAMA] - " + this.getClass() + " ***********");
			 
		}			
			
		
		StringBuffer camposstring = new StringBuffer("");
		if(campos!=null){ 
			
				for(int c=0; c<campos.length; c++){
					String temp = "";
					if (printFillBody.equals("1")){
						for(int f=0;f<30;f++){
							if(campos[c].Descripcion.length()-1<f){
								temp += " "; 
							}else{
								temp += campos[c].Descripcion.charAt(f);
							}
						}
					}
				
					if (printFillBody.equals("1")){						
						 
						logger.info(temp + ":\t" + campos[c].Tamano + "\t" + "[" + campos[c].Valor  + "]\t");
						}
						
					
				camposstring.append(campos[c].get() + separador);
			}
		}
		return camposstring.toString();
	}
	
	
	public int LongitudTrama(){
	
		int num = 0;
		if(campos!=null){
			for(int c=0; c<campos.length; c++){
				num = num + campos[c].Tamano ;
			}
		}
		return num;
	}
	
	public Campo getCampoByIndex(int index){
		if(campos!=null){
			if(index<campos.length){
				return campos[index];
			}
		}
		return null;
	}

	public String getByTag(String desc){
		if(campos!=null){
			for(int i=0;i<campos.length;i++){
				if(campos[i].Descripcion.equals(TextFormat.getValue(desc).trim())){
					return TextFormat.getValue(campos[i].get());
				}
			}
		}
		return null;
	}
	
	
	public String getSeparador() {
		return separador;
	}

	public int getCampoLength(){
		if(campos!=null){
				return campos.length;
		}
		return 0;
	}
	
	public void showPositions(){

		if(campos!=null){
			for(int c=0; c<campos.length; c++){
				
				String desc = campos[c].Descripcion;
				String val = campos[c].Valor;
				for (int f=0;f<30-campos[c].Descripcion.length();f++){
					desc += " ";
				}
				for (int f=0;f<100-campos[c].Valor.length();f++){
					val += " ";
				}
				
			}
		}
		
	}
	

	public void FillBoby(String bodyOutPut) throws Exception {

	    if (printFillBody.equals("1")){
	    	
	    	//logger.info("*********** [RETORNO TRAMA] - " + this.getClass() + " ***********");
	    	//System.out.println("*********** [RETORNO TRAMA] - " + this.getClass() + " ***********");
	    	 
	    }
			
	    
		if(this.campos!=null){
			int inicio = 1;
			for(int i=0;i<campos.length;i++){
				String temp = "";
				for(int f=0;f<20;f++){
					if(campos[i].Descripcion.length()-1<f){
						temp += " "; 
					}else{
						temp += campos[i].Descripcion.charAt(f);
					}
				}
				
				campos[i].Valor = Funciones.enmascararTramaRecepcion( bodyOutPut.substring((inicio-1), (inicio-1) + campos[i].Tamano) );
				
				if (printFillBody.equals("1")){
					logger.info(temp + ":\t" + campos[i].Tamano + "\t" + "[" + campos[i].Valor  + "]\t");
					//System.out.println(temp + ":\t" + campos[i].Tamano + "\t" + "[" + campos[i].Valor  + "]\t");
					 
				}
				    
				
				inicio+= campos[i].Tamano;
				
			}
		}
	}
	
	public static boolean ValidarCampo(boolean campo1, int campo2){
	
		boolean result = false;
		if (campo1 && (campo2 == SIN_ERROR)){
			result = true;
		}
		
		return result;
	
	}
	
	public void setByTag(String desc, String val) throws Exception{
		if(campos!=null){
			for(int i=0;i<campos.length;i++){
				if(campos[i].Descripcion.equals(desc.trim())){
					campos[i].setString(val);
				}
			}
		}
	}
	
}
