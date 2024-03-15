	package services;

	import java.util.Map;
	import javax.faces.context.FacesContext;

	@SuppressWarnings("unchecked")
	public abstract class AbstractServiceBean {
		public Map getSessionScope() {
			return FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap();
		}

	}

