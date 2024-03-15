package pe.bn.cldinamica.action.form;

import java.io.Serializable;

/**
 * @author prov_destrada
 * @created 12/11/2020 - 02:44 p. m.
 * @project MSCardConfigurationManagement
 */

public class CardConfSaveResponse implements Serializable
{
	
	private String message;
//	private String numberOperation;
    private String dateOperation;
    
	public String getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}
//	public String getNumberOperation() {
//		return numberOperation;
//	}
//	public void setNumberOperation(String numberOperation) {
//		this.numberOperation = numberOperation;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    



    
    
    
}
