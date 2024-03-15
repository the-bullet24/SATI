/*
 * Created on 29/03/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.cosapi.system;

/**
 * @author 2424012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.SeguridadUtil;

public class SelloSeguridadUtil {
	
	private String sello_1;
	private String sello_2;
	private String sello_3;
	private String sello_4;
	private String sello_5;
	private String sello_6;
	private String sello_7;
	private String sello_8;
	private String sello_9;
	
	private String alt_1;
	private String alt_2;
	private String alt_3;
	private String alt_4;
	private String alt_5;
	private String alt_6;
	private String alt_7;
	private String alt_8;
	private String alt_9;

	private String randomEncript;
	
	public void asignar(Map output,HttpServletRequest req){
		String ran = (String)SeguridadUtil.getObjectSession(ConstanteSesion.NRO_RANDOM,req);
		
		//if(Constante.VER_LOG)System.out.println("------------>ran="+ran+"<-----------");
		
		GeneratorKeys gen = new GeneratorKeys();
		String rdnEncript =  gen.getCodeRandomEncript();
		//if(Constante.VER_LOG)System.out.println("------------>rdnEncript="+rdnEncript+"<-----------");
		
		//if(Constante.VER_LOG)System.out.println("------------>DEn rdnEncript="+gen.procesarDencript(rdnEncript)+"<-----------");
		String rnDencript = gen.procesarDencript(rdnEncript);
		
		if (ran!=null){
			String ranDencript = gen.procesarDencript(ran);
			if (rnDencript.equals(ranDencript))
				rdnEncript = gen.getCodeRandomEncriptDif(ran);
		}else{
			rdnEncript = gen.procesarEncript("17");
		}
		//if(Constante.VER_LOG)System.out.println("------------>ult rdnEncript="+rdnEncript+"<-----------");
		
		
		String rdnDencript = gen.procesarDencript(rdnEncript);
		//Map output = gen.generateMap();
		String cadeEncript  =  (String)output.get(rdnDencript);
		String cadeDEncript = gen.procesarDencript(cadeEncript);
		
		String temp = "tv_b"; 
		
		this.sello_1 = temp+cadeDEncript.substring(0,1);
		this.alt_1   = cadeDEncript.substring(0,1);
		this.sello_2 = temp+cadeDEncript.substring(1,2);
		this.alt_2   = cadeDEncript.substring(1,2);
		this.sello_3 = temp+cadeDEncript.substring(2,3);
		this.alt_3   = cadeDEncript.substring(2,3);
		this.sello_4 = temp+cadeDEncript.substring(3,4);
		this.alt_4   = cadeDEncript.substring(3,4);
		this.sello_5 = temp+cadeDEncript.substring(4,5);
		this.alt_5   = cadeDEncript.substring(4,5);
		this.sello_6 = temp+cadeDEncript.substring(5,6);
		this.alt_6   = cadeDEncript.substring(5,6);
		this.sello_7 = temp+cadeDEncript.substring(6,7);
		this.alt_7   = cadeDEncript.substring(6,7);
		this.sello_8 = temp+cadeDEncript.substring(7,8);
		this.alt_8   = cadeDEncript.substring(7,8);
		this.sello_9 = temp+cadeDEncript.substring(8,9);
		this.alt_9   = cadeDEncript.substring(8,9);

		this.randomEncript = rdnEncript;
		SeguridadUtil.setearObjectSession(ConstanteSesion.NRO_RANDOM,rdnEncript,req);
	}
	
	
	public void asignar(String valor){
		
	}
	
	
	/**
	 * @return Devuelve randomEncript.
	 */
	public String getRandomEncript() {
		return randomEncript;
	}
	/**
	 * @param randomEncript El randomEncript a establecer.
	 */
	public void setRandomEncript(String randomEncript) {
		this.randomEncript = randomEncript;
	}
	/**
	 * @return Devuelve Sello_0.
	 */
	public String getSello_9() {
		return sello_9;
	}
	/**
	 * @param Sello_0 El Sello_0 a establecer.
	 */
	public void setSello_9(String sello_9) {
		this.sello_9 = sello_9;
	}
	/**
	 * @return Devuelve Sello_1.
	 */
	public String getSello_1() {
		return sello_1;
	}
	/**
	 * @param Sello_1 El Sello_1 a establecer.
	 */
	public void setSello_1(String sello_1) {
		this.sello_1 = sello_1;
	}
	/**
	 * @return Devuelve Sello_2.
	 */
	public String getSello_2() {
		return sello_2;
	}
	/**
	 * @param Sello_2 El Sello_2 a establecer.
	 */
	public void setSello_2(String sello_2) {
		this.sello_2 = sello_2;
	}
	/**
	 * @return Devuelve Sello_3.
	 */
	public String getSello_3() {
		return sello_3;
	}
	/**
	 * @param Sello_3 El Sello_3 a establecer.
	 */
	public void setSello_3(String sello_3) {
		this.sello_3 = sello_3;
	}
	/**
	 * @return Devuelve Sello_4.
	 */
	public String getSello_4() {
		return sello_4;
	}
	/**
	 * @param Sello_4 El Sello_4 a establecer.
	 */
	public void setSello_4(String sello_4) {
		this.sello_4 = sello_4;
	}
	/**
	 * @return Devuelve Sello_5.
	 */
	public String getSello_5() {
		return sello_5;
	}
	/**
	 * @param Sello_5 El Sello_5 a establecer.
	 */
	public void setSello_5(String sello_5) {
		this.sello_5 = sello_5;
	}
	/**
	 * @return Devuelve Sello_6.
	 */
	public String getSello_6() {
		return sello_6;
	}
	/**
	 * @param Sello_6 El Sello_6 a establecer.
	 */
	public void setSello_6(String sello_6) {
		this.sello_6 = sello_6;
	}
	/**
	 * @return Devuelve Sello_7.
	 */
	public String getSello_7() {
		return sello_7;
	}
	/**
	 * @param Sello_7 El Sello_7 a establecer.
	 */
	public void setSello_7(String Sello_7) {
		this.sello_7 = Sello_7;
	}
	/**
	 * @return Devuelve Sello_8.
	 */
	public String getSello_8() {
		return sello_8;
	}
	/**
	 * @param Sello_8 El Sello_8 a establecer.
	 */
	public void setSello_8(String Sello_8) {
		this.sello_8 = Sello_8;
	}
	
	
	/**
	 * @return Returns the alt_1.
	 */
	public String getAlt_1() {
		return alt_1;
	}
	/**
	 * @param alt_1 The alt_1 to set.
	 */
	public void setAlt_1(String alt_1) {
		this.alt_1 = alt_1;
	}
	/**
	 * @return Returns the alt_2.
	 */
	public String getAlt_2() {
		return alt_2;
	}
	/**
	 * @param alt_2 The alt_2 to set.
	 */
	public void setAlt_2(String alt_2) {
		this.alt_2 = alt_2;
	}
	/**
	 * @return Returns the alt_3.
	 */
	public String getAlt_3() {
		return alt_3;
	}
	/**
	 * @param alt_3 The alt_3 to set.
	 */
	public void setAlt_3(String alt_3) {
		this.alt_3 = alt_3;
	}
	/**
	 * @return Returns the alt_4.
	 */
	public String getAlt_4() {
		return alt_4;
	}
	/**
	 * @param alt_4 The alt_4 to set.
	 */
	public void setAlt_4(String alt_4) {
		this.alt_4 = alt_4;
	}
	/**
	 * @return Returns the alt_5.
	 */
	public String getAlt_5() {
		return alt_5;
	}
	/**
	 * @param alt_5 The alt_5 to set.
	 */
	public void setAlt_5(String alt_5) {
		this.alt_5 = alt_5;
	}
	/**
	 * @return Returns the alt_6.
	 */
	public String getAlt_6() {
		return alt_6;
	}
	/**
	 * @param alt_6 The alt_6 to set.
	 */
	public void setAlt_6(String alt_6) {
		this.alt_6 = alt_6;
	}
	/**
	 * @return Returns the alt_7.
	 */
	public String getAlt_7() {
		return alt_7;
	}
	/**
	 * @param alt_7 The alt_7 to set.
	 */
	public void setAlt_7(String alt_7) {
		this.alt_7 = alt_7;
	}
	/**
	 * @return Returns the alt_8.
	 */
	public String getAlt_8() {
		return alt_8;
	}
	/**
	 * @param alt_8 The alt_8 to set.
	 */
	public void setAlt_8(String alt_8) {
		this.alt_8 = alt_8;
	}
	/**
	 * @return Returns the alt_9.
	 */
	public String getAlt_9() {
		return alt_9;
	}
	/**
	 * @param alt_9 The alt_9 to set.
	 */
	public void setAlt_9(String alt_9) {
		this.alt_9 = alt_9;
	}
}
