package pe.cosapi.system;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.SeguridadUtil;

public class TecladoUtil {
	
	
	private String jpg_0;
	private String jpg_1;
	private String jpg_2;
	private String jpg_3;
	private String jpg_4;
	private String jpg_5;
	private String jpg_6;
	private String jpg_7;
	private String jpg_8;
	private String jpg_9;
	
	private String alt_0;
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
		
		this.jpg_0 = temp+cadeDEncript.substring(0,1);
		this.alt_0 = cadeDEncript.substring(0,1);
		//System.out.println(this.getAlt_0());
		this.jpg_1 = temp+cadeDEncript.substring(1,2);
		this.alt_1 = cadeDEncript.substring(1,2);
		//System.out.println(this.getAlt_1());
		this.jpg_2 = temp+cadeDEncript.substring(2,3);
		this.alt_2 = cadeDEncript.substring(2,3);
		this.jpg_3 = temp+cadeDEncript.substring(3,4);
		this.alt_3 = cadeDEncript.substring(3,4);
		this.jpg_4 = temp+cadeDEncript.substring(4,5);
		this.alt_4 = cadeDEncript.substring(4,5);
		this.jpg_5 = temp+cadeDEncript.substring(5,6);
		this.alt_5 = cadeDEncript.substring(5,6);
		this.jpg_6 = temp+cadeDEncript.substring(6,7);
		this.alt_6 = cadeDEncript.substring(6,7);
		this.jpg_7 = temp+cadeDEncript.substring(7,8);
		this.alt_7 = cadeDEncript.substring(7,8);
		this.jpg_8 = temp+cadeDEncript.substring(8,9);
		this.alt_8 = cadeDEncript.substring(8,9);
		this.jpg_9 = temp+cadeDEncript.substring(9,cadeDEncript.length());
		this.alt_9 = cadeDEncript.substring(9,cadeDEncript.length());
		this.randomEncript = rdnEncript;
		SeguridadUtil.setearObjectSession(ConstanteSesion.NRO_RANDOM,rdnEncript,req);
	}
	
	public void asignarSello(Map output,HttpServletRequest req){
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
		
		String temp = "Tarjeta"; 
		String temp1 = ".jpg";
		
		this.jpg_1 = temp+cadeDEncript.substring(0,1)+temp1;
		this.alt_1 = cadeDEncript.substring(0,1);
		this.jpg_2 = temp+cadeDEncript.substring(1,2)+temp1;
		this.alt_2 = cadeDEncript.substring(1,2);
		this.jpg_3 = temp+cadeDEncript.substring(2,3)+temp1;
		this.alt_3 = cadeDEncript.substring(2,3);
		this.jpg_4 = temp+cadeDEncript.substring(3,4)+temp1;
		this.alt_4 = cadeDEncript.substring(3,4);
		this.jpg_5 = temp+cadeDEncript.substring(4,5)+temp1;
		this.alt_5 = cadeDEncript.substring(4,5);
		this.jpg_6 = temp+cadeDEncript.substring(5,6)+temp1;
		this.alt_6 = cadeDEncript.substring(5,6);
		this.jpg_7 = temp+cadeDEncript.substring(6,7)+temp1;
		this.alt_7 = cadeDEncript.substring(6,7);
		this.jpg_8 = temp+cadeDEncript.substring(7,8)+temp1;
		this.alt_8 = cadeDEncript.substring(7,8);
		this.jpg_9 = temp+cadeDEncript.substring(8,9)+temp1;
		this.alt_9 = cadeDEncript.substring(8,9);
		this.randomEncript = rdnEncript;
		SeguridadUtil.setearObjectSession(ConstanteSesion.NRO_RANDOM,rdnEncript,req);
	}
	
	public int[] generar_array_numeros(){
		
		int n = 10;
		int k = n;
		int[] resultado = new int[n];
		int[] numeros=new int[n];       
		Random rnd = new Random();
		int res;
		       
		for(int i=0;i<n;i++){
		    numeros[i]=i+1;
		}
		       
		for(int i=0;i<n;i++){
		    res = rnd.nextInt(k);           
		        resultado[i]=numeros[res];
		        numeros[res]=numeros[k-1];
		        k--;           
		}
				
		return resultado;
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
	 * @return Devuelve jpg_0.
	 */
	public String getJpg_0() {
		return jpg_0;
	}
	/**
	 * @param jpg_0 El jpg_0 a establecer.
	 */
	public void setJpg_0(String jpg_0) {
		this.jpg_0 = jpg_0;
	}
	/**
	 * @return Devuelve jpg_1.
	 */
	public String getJpg_1() {
		return jpg_1;
	}
	/**
	 * @param jpg_1 El jpg_1 a establecer.
	 */
	public void setJpg_1(String jpg_1) {
		this.jpg_1 = jpg_1;
	}
	/**
	 * @return Devuelve jpg_2.
	 */
	public String getJpg_2() {
		return jpg_2;
	}
	/**
	 * @param jpg_2 El jpg_2 a establecer.
	 */
	public void setJpg_2(String jpg_2) {
		this.jpg_2 = jpg_2;
	}
	/**
	 * @return Devuelve jpg_3.
	 */
	public String getJpg_3() {
		return jpg_3;
	}
	/**
	 * @param jpg_3 El jpg_3 a establecer.
	 */
	public void setJpg_3(String jpg_3) {
		this.jpg_3 = jpg_3;
	}
	/**
	 * @return Devuelve jpg_4.
	 */
	public String getJpg_4() {
		return jpg_4;
	}
	/**
	 * @param jpg_4 El jpg_4 a establecer.
	 */
	public void setJpg_4(String jpg_4) {
		this.jpg_4 = jpg_4;
	}
	/**
	 * @return Devuelve jpg_5.
	 */
	public String getJpg_5() {
		return jpg_5;
	}
	/**
	 * @param jpg_5 El jpg_5 a establecer.
	 */
	public void setJpg_5(String jpg_5) {
		this.jpg_5 = jpg_5;
	}
	/**
	 * @return Devuelve jpg_6.
	 */
	public String getJpg_6() {
		return jpg_6;
	}
	/**
	 * @param jpg_6 El jpg_6 a establecer.
	 */
	public void setJpg_6(String jpg_6) {
		this.jpg_6 = jpg_6;
	}
	/**
	 * @return Devuelve jpg_7.
	 */
	public String getJpg_7() {
		return jpg_7;
	}
	/**
	 * @param jpg_7 El jpg_7 a establecer.
	 */
	public void setJpg_7(String jpg_7) {
		this.jpg_7 = jpg_7;
	}
	/**
	 * @return Devuelve jpg_8.
	 */
	public String getJpg_8() {
		return jpg_8;
	}
	/**
	 * @param jpg_8 El jpg_8 a establecer.
	 */
	public void setJpg_8(String jpg_8) {
		this.jpg_8 = jpg_8;
	}
	/**
	 * @return Devuelve jpg_9.
	 */
	public String getJpg_9() {
		return jpg_9;
	}
	/**
	 * @param jpg_9 El jpg_9 a establecer.
	 */
	public void setJpg_9(String jpg_9) {
		this.jpg_9 = jpg_9;
	}
    public String getAlt_0() {
        return alt_0;
    }
    public void setAlt_0(String alt_0) {
        this.alt_0 = alt_0;
    }
    public String getAlt_1() {
        return alt_1;
    }
    public void setAlt_1(String alt_1) {
        this.alt_1 = alt_1;
    }
    public String getAlt_2() {
        return alt_2;
    }
    public void setAlt_2(String alt_2) {
        this.alt_2 = alt_2;
    }
    public String getAlt_3() {
        return alt_3;
    }
    public void setAlt_3(String alt_3) {
        this.alt_3 = alt_3;
    }
    public String getAlt_4() {
        return alt_4;
    }
    public void setAlt_4(String alt_4) {
        this.alt_4 = alt_4;
    }
    public String getAlt_5() {
        return alt_5;
    }
    public void setAlt_5(String alt_5) {
        this.alt_5 = alt_5;
    }
    public String getAlt_6() {
        return alt_6;
    }
    public void setAlt_6(String alt_6) {
        this.alt_6 = alt_6;
    }
    public String getAlt_7() {
        return alt_7;
    }
    public void setAlt_7(String alt_7) {
        this.alt_7 = alt_7;
    }
    public String getAlt_8() {
        return alt_8;
    }
    public void setAlt_8(String alt_8) {
        this.alt_8 = alt_8;
    }
    public String getAlt_9() {
        return alt_9;
    }
    public void setAlt_9(String alt_9) {
        this.alt_9 = alt_9;
    }
}
