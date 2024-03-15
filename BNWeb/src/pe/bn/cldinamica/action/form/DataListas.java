package pe.bn.cldinamica.action.form;

import java.util.Comparator;

public class DataListas {


	private String code;

	private String name;
	private String description;
	private String type;
	private String status;
	
	

	
	
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
//	public int compareTo(Object o) {
//		DataListas dataListas = (DataListas)o;
//		
//		if(this.name.compareToIgnoreCase(dataListas.getName()) == 0) {           
//            if(this.name.compareToIgnoreCase(persona.nombre) == 0) {
//                return this.dni.compareTo(persona.dni);
//            } else {
//            	return
//            	this.nombre.compareToIgnoreCase(persona.nombre);
//		    }
//		} else {
//		  return this.apellidos.compareToIgnoreCase(persona.apellidos);
//		}       
//	}
	
	
	
	

}
