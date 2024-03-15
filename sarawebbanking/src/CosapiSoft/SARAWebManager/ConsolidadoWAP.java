/*
 * Creado el 22/05/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package CosapiSoft.SARAWebManager;


public class ConsolidadoWAP {
	
	private String totOkClaro1 = "0";
	private String totFaClaro1 = "0";
	
	private String totOkMovis2 = "0";
	private String totFaMovis2 = "0";
	
	private String totOkClaro3 = "0";
	private String totFaClaro3 = "0";
	
	private String totOkMovis4 = "0";
	private String totFaMovis4 = "0";
	
	private Resultado[] arr1 ;
	private Resultado[] arr2 ;
	private Resultado[] arr3 ;
	private Resultado[] arr4 ;
	
	
	public ConsolidadoWAP(){
		
	}
	
	public ConsolidadoWAP(Resultado[] obj1,Resultado[] obj2,Resultado[] obj3,Resultado[] obj4){
		this.arr1 = obj1;
		this.arr2 = obj2;
		this.arr3 = obj3;
		this.arr4 = obj4;
		
		
		if (this.arr1!=null){
			for (int i = 0; i < this.arr1.length; i++) {
				Resultado resultado = this.arr1[i];
				if ("0".equals(resultado.getValor1())){
					this.totOkClaro1 = resultado.getValor2(); 
				}else if ("1".equals(resultado.getValor1())){
					this.totFaClaro1 = resultado.getValor2();
					//log.warn("totFaClaro1:"+totFaClaro1);
				}
			}
			
		}
		
		if (this.arr2!=null){
			for (int i = 0; i < this.arr2.length; i++) {
				Resultado resultado = this.arr2[i];
				if ("0".equals(resultado.getValor1())){
					this.totOkMovis2 = resultado.getValor2(); 
					//log.warn("totOkMovis2:"+totOkMovis2);
				}else if ("1".equals(resultado.getValor1())){
					this.totFaMovis2 = resultado.getValor2();
				//	log.warn("totOkMovis2:"+totOkMovis2);
				}
			}
		}
		
		if (this.arr3!=null){	
			for (int i = 0; i < this.arr3.length; i++) {
				Resultado resultado = this.arr3[i];
				if ("0".equals(resultado.getValor1())){
					this.totOkClaro3 = resultado.getValor2(); 
					//log.warn("totOkClaro3:"+totOkClaro3);
				}else if ("1".equals(resultado.getValor1())){
					this.totFaClaro1 = resultado.getValor2();
					//log.warn("totFaClaro1:"+totFaClaro1);
				}
			}
		}
		
		if (this.arr4!=null){	
			for (int i = 0; i < this.arr4.length; i++) {
				Resultado resultado = this.arr4[i];
				if ("0".equals(resultado.getValor1())){
					this.totOkMovis4 = resultado.getValor2();
				//	log.warn("totOkMovis4:"+totOkMovis4);
				}else if ("1".equals(resultado.getValor1())){
					this.totFaMovis4 = resultado.getValor2();
			//		log.warn("totOkMovis4:"+totOkMovis4);
				}
			}
		}
		
	}
	
	public String getTotal1(){
		int t1 = Integer.parseInt(this.totFaClaro1);
		int t2 = Integer.parseInt(this.totOkClaro1);
		
		int t3 = Integer.parseInt(this.totFaMovis2);
		int t4 = Integer.parseInt(this.totOkMovis2);
		
		int total = t1+t2+t3+t4;
		//log.warn("getTotal1 total:"+total);
		return String.valueOf(total);
	}
	
	public String getTotal2(){
		int t1 = Integer.parseInt(this.totFaClaro3);
		int t2 = Integer.parseInt(this.totOkClaro3);
		
		int t3 = Integer.parseInt(this.totFaMovis4);
		int t4 = Integer.parseInt(this.totOkMovis4);
		
		int total = t1+t2+t3+t4;
		//log.warn("getTotal2 total:"+total);
		return String.valueOf(total);
	}
	
	public String getTotalOkClaro1(){
		int t1 = Integer.parseInt(this.totOkClaro1);
		int t2 = Integer.parseInt(this.totOkClaro3);
		int total = t1+t2;
		//log.warn("getTotalOkClaro1 total:"+total);
		return String.valueOf(total);
	}
	
	public String getTotalFaClaro2(){
		int t1 = Integer.parseInt(this.totFaClaro1);
		int t2 = Integer.parseInt(this.totFaClaro3);
		int total = t1+t2;
		return String.valueOf(total);
	}
	
	public String getTotalOKMovis1(){
		int t1 = Integer.parseInt(this.totOkMovis2);
		int t2 = Integer.parseInt(this.totOkMovis4);
		int total = t1+t2;
		//log.warn("getTotalOKMovis1 total:"+total);
		return String.valueOf(total);
	}
	public String getTotalFaMovis2(){
		int t1 = Integer.parseInt(this.totFaMovis2);
		int t2 = Integer.parseInt(this.totFaMovis4);
		int total = t1+t2;
	//	log.warn("getTotalFaMovis2 total:"+total);
		return String.valueOf(total);
	}
	public String getTotal(){
		int t1 = Integer.parseInt(this.getTotal1());
		int t2 = Integer.parseInt(this.getTotal2());
		int total = t1+t2;
		//log.warn("getTotal total:"+total);
		return String.valueOf(total);
	}
	
	/**
	 * @return Devuelve totFaClaro1.
	 */
	public String getTotFaClaro1() {
		return totFaClaro1;
	}
	/**
	 * @param totFaClaro1 El totFaClaro1 a establecer.
	 */
	public void setTotFaClaro1(String totFaClaro1) {
		this.totFaClaro1 = totFaClaro1;
	}
	/**
	 * @return Devuelve totFaMovis2.
	 */
	public String getTotFaMovis2() {
		return totFaMovis2;
	}
	/**
	 * @param totFaMovis2 El totFaMovis2 a establecer.
	 */
	public void setTotFaMovis2(String totFaMovis2) {
		this.totFaMovis2 = totFaMovis2;
	}
	/**
	 * @return Devuelve totFaMovis4.
	 */
	public String getTotFaMovis4() {
		return totFaMovis4;
	}
	/**
	 * @param totFaMovis4 El totFaMovis4 a establecer.
	 */
	public void setTotFaMovis4(String totFaMovis4) {
		this.totFaMovis4 = totFaMovis4;
	}
	/**
	 * @return Devuelve totOkClaro1.
	 */
	public String getTotOkClaro1() {
		return totOkClaro1;
	}
	/**
	 * @param totOkClaro1 El totOkClaro1 a establecer.
	 */
	public void setTotOkClaro1(String totOkClaro1) {
		this.totOkClaro1 = totOkClaro1;
	}
	/**
	 * @return Devuelve totOkMovis2.
	 */
	public String getTotOkMovis2() {
		return totOkMovis2;
	}
	/**
	 * @param totOkMovis2 El totOkMovis2 a establecer.
	 */
	public void setTotOkMovis2(String totOkMovis2) {
		this.totOkMovis2 = totOkMovis2;
	}
	
	/**
	 * @return Devuelve totFaClaro3.
	 */
	public String getTotFaClaro3() {
		return totFaClaro3;
	}
	/**
	 * @param totFaClaro3 El totFaClaro3 a establecer.
	 */
	public void setTotFaClaro3(String totFaClaro3) {
		this.totFaClaro3 = totFaClaro3;
	}
	/**
	 * @return Devuelve totOkClaro3.
	 */
	public String getTotOkClaro3() {
		return totOkClaro3;
	}
	/**
	 * @param totOkClaro3 El totOkClaro3 a establecer.
	 */
	public void setTotOkClaro3(String totOkClaro3) {
		this.totOkClaro3 = totOkClaro3;
	}
	/**
	 * @return Devuelve totOkMovis4.
	 */
	public String getTotOkMovis4() {
		return totOkMovis4;
	}
	/**
	 * @param totOkMovis4 El totOkMovis4 a establecer.
	 */
	public void setTotOkMovis4(String totOkMovis4) {
		this.totOkMovis4 = totOkMovis4;
	}
}
