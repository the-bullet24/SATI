package pe.bn.cldinamica.action.form;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;

/**
 * Contiene las configuraciones para disposición de efectivo, se encuentra
 * anidado a {@link CardResponse}
 *
 * @author prov_destrada
 * @created 12/11/2020 - 04:04 p. m.
 * @project MSCardConfigurationManagement
 */

public class CashDispositionSettings implements Serializable
{
	private String status;
	private String available;
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
