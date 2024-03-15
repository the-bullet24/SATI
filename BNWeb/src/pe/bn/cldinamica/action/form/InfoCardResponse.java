package pe.bn.cldinamica.action.form;

import java.io.Serializable;
import java.util.List;

/**
 * @author prov_destrada
 * @created 12/11/2020 - 02:44 p. m.
 * @project MSCardConfigurationManagement
 */

public class InfoCardResponse implements Serializable
{
	private List<InfoCard> cards;

	public List<InfoCard> getCards() {
		return cards;
	}

	public void setCards(List<InfoCard> cards) {
		this.cards = cards;
	}

	

    
        
    
}
