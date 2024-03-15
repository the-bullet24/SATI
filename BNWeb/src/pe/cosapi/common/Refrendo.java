/*
 * Creado el 02/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.ui.velocity.VelocityEngineUtils;

import pe.bn.pago.domain.impl.PagoTasasImpl;
import pe.cosapi.facade.impl.GeneralFacadeImpl;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Refrendo implements Serializable{

	private VelocityEngine velocityEngine;

	private String plantilla;
	
	private String resultado;

	/**
	 * 
	 */
	public Refrendo() {

	
	}
	
	public Refrendo(String id) {
		Refrendo ref = RefrendoFactory.getRefrendoInstance(id); 
		this.velocityEngine = ref.velocityEngine;
		this.plantilla = ObjectUtil.replaceIlegalCharacterToSendMail(ref.plantilla);		
	
	}

	/**
	 * @return Devuelve plantilla.
	 */
	public String getPlantilla() {
		return ObjectUtil.replaceIlegalCharacterToSendMail(plantilla);
	
	}
	/**
	 * @param plantilla El plantilla a establecer.
	 */
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
	/**
	 * @return Devuelve velocityEngine.
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	/**
	 * @param velocityEngine El velocityEngine a establecer.
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	/**
	 * @return Devuelve texto.
	 */
	public String getResultado() {
		return ObjectUtil.replaceIlegalCharacterToSendMail(resultado);
	}
	/**
	 * @param texto El texto a establecer.
	 */
	public void setTexto(String resultado) {
		this.resultado = resultado;
	}
	
	public void procesar(Object o) throws Exception{
		Map model = new HashMap();
		Map map = new HashMap(); 
		map.get(o);
		//System.out.println("entro al refrendo objeto");
		
		//model.put("param", o);
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();){ 
		    Object object = iter.next();
		    model.put(object,map.get(object));
		}
		model.put("date",new DateTool());
		model.put("number", new NumberTool());		
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, 
				getPlantilla(),
				model);
		this.resultado = text;
		
	}

	public void procesar(Map map) throws Exception{
	    Map model = new HashMap();
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();){ 
		    Object object = iter.next();
		    model.put(object,map.get(object));	    
		}
		model.put("date",new DateTool());
		model.put("number", new NumberTool());		
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, 
				getPlantilla(),
				model);
		this.resultado = text;
	}

}