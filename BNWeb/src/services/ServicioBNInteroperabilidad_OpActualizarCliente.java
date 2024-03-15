	package services;

	import pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest;
	import pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse;
	import pe.com.bn.wscn.ws.ServicioBNInteroperabilidadProxy;

	@SuppressWarnings("unchecked")
	public class ServicioBNInteroperabilidad_OpActualizarCliente extends
			AbstractServiceBean {
		public static class ParamBean {
			private ActualizacionRequest request;

			public ActualizacionRequest getRequest() {
				if (request == null) {
					request = new ActualizacionRequest();
					//TODO This object has properties which may not have been initialized.  Add initialization code here if needed.
				}
				return request;
			}

			public void setRequest(ActualizacionRequest request) {
				this.request = request;
			}

		}

		private ParamBean paramBean;
		private OperacionResponse resultBean;
		private ServicioBNInteroperabilidadProxy service;

		public ParamBean getParamBean() {
			if (paramBean == null) {
				paramBean = new ParamBean();
			}
			return paramBean;
		}

		public OperacionResponse getResultBean() {
			if (resultBean == null) {
				resultBean = new OperacionResponse();
			}
			return resultBean;
		}

		private ServicioBNInteroperabilidadProxy getService() {
			if (service == null) {
				service = new ServicioBNInteroperabilidadProxy();
			}
			return service;
		}

		public String doAction() {
			getParamBean();
			if (paramBean != null) {
				try {
					resultBean = getService().opActualizarCliente(
							paramBean.getRequest());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "";
		}

	}

