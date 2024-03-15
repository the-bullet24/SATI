/*
 * Creado el 07/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de código
 */
package pe.cosapi.common;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SpringWebApplicationContext {
	
		
	private static SpringWebApplicationContext unico = null;
	
	private SpringWebApplicationContext(){
	}

	public static SpringWebApplicationContext getInstance()	{		
		if (unico==null)
			unico = new SpringWebApplicationContext();
		return unico;

	}

	/** @modelguid {A0AD3C3A-C785-48DC-B503-E7A6E0B96BDB} */
	private WebApplicationContext webApplicationContext=null;

	/** @modelguid {7CCD0445-03DB-4C41-91B3-3C58BBA29D18} */
	public WebApplicationContext getWebApplicationContext()
	{
		return webApplicationContext;
	}

	/** @modelguid {26A5FDD2-A526-465A-B48F-5691DD129120} */
	public void setWebApplicationContext(WebApplicationContext context)
	{
		webApplicationContext = context;
	}
	/** @modelguid {E4AC08F6-4AEE-40C9-88AF-DEB0C85C37B8} */
	public Object getBean(String beanId)
	{
		/*  Este metodo busca el objeto pedido dentro de los contextos de Spring.
		 *  Spring puede guardar sus objetos en dos contextos.
		 *  El contexto principal es el WebApplicationContext; pero generalmente
		 *  en ambientes de prueba se usa el XMLContext
		 */
		 
		Object respuesta = null;
		
		if (webApplicationContext!=null){
			respuesta = webApplicationContext.getBean(beanId);
			if (respuesta==null)
				throw new RuntimeException("Nombre de Bean incorrecto de objeto Spring");
			return respuesta;
		}
			
		if (respuesta==null)
			throw new RuntimeException("Nombre de Bean incorrecto de objeto Spring");			
		return respuesta;
	}	
	
}
