package pe.cosapi.common;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

public class ArrayRuleException extends RuntimeException{
	
	private ActionError[] arrAE;
	
	private ArrayList arr = null;
	
	private String forward;
	public boolean hasExceptions(){
		if (arr == null)
			return false;
		return true;
	}
	
	public ActionError[] getActionErrorsArray(){
		arrAE = new ActionError[arr.size()];
		for (int i = 0; i < arr.size(); i++){
			arrAE[i] = (ActionError) arr.get(i);
		}
		return arrAE;
	}

	public ActionErrors getActionErrors(){
		ActionErrors msgs = new ActionErrors();
		for (int i = 0; i < arr.size(); i++)
		{
			msgs.add(ActionErrors.GLOBAL_MESSAGE, (ActionError) arr.get(i));
		}
		return msgs;
	}

	public ArrayRuleException(){
		super();
	}
	
	public void addRuleException(RuleException re){
		if (arr == null)
			arr = new ArrayList();
		arr.add(re.getActionError());
	}
	
	public void setArrayRuleExceptions(ActionErrors msgs){
		if (arr == null)
			arr = new ArrayList();
		Iterator i=msgs.get(ActionErrors.GLOBAL_MESSAGE);
		while(i.hasNext()){
			ActionError obj = (ActionError) i.next();
			arr.add(obj);
		}
	}

	public ArrayRuleException(String key){
		super();
		arr = new ArrayList();
		arr.add(new ActionError(key));		
		/*super();
		String key = ConstanteError.GENERICO; 
		arr = new ArrayList();
		String mensaje;
		try{
			mensaje = (String)BNAplicacion.getInstance().getMensajePorCodigo(codigoMensaje).elementAt(2);	
		}
		catch (Exception e) {
			mensaje = "Error Inesperado";
		}		
		arr.add(new ActionError(key,mensaje));
		*/
	}
	

	public ArrayRuleException(String key, String param0){
		super();
		arr = new ArrayList();
		arr.add(new ActionError(key, param0));
	}

	public ArrayRuleException(String key, String param0, String param1){
		super();
		arr = new ArrayList();
		arr.add(new ActionError(key, param0, param1));
	}
	
	public ArrayRuleException(String key, String param0, String param1, String param2){
		super();
		arr = new ArrayList();
		arr.add(new ActionError(key, param0, param1, param2));
	}

	public ArrayRuleException(String key, String param0, String param1, String param2, String param3){
		super();
		arr = new ArrayList();
		arr.add(new ActionError(key, param0, param1, param2, param3));
	}

	public ArrayRuleException(String key, String[] params){
		super();
		arr = new ArrayList();
		arr.add(new ActionError(key, params));
	}
	
	/**
	 * @return Devuelve forward.
	 */
	public String getForward() {
		return forward;
	}
	/**
	 * @param forward El forward a establecer.
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}
}
