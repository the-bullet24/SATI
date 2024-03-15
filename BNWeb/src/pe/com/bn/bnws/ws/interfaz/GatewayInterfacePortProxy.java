package pe.com.bn.bnws.ws.interfaz;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import pe.com.bn.bnws.ws.bean.RequestGateway;
import pe.com.bn.bnws.ws.bean.ResponseGateway;

public class GatewayInterfacePortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private pe.com.bn.bnws.ws.interfaz.BasicGatewayInterfaceService _service = null;
        private pe.com.bn.bnws.ws.interfaz.GatewayInterface _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new pe.com.bn.bnws.ws.interfaz.BasicGatewayInterfaceService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (pe.com.bn.bnws.ws.interfaz.BasicGatewayInterfaceService)ctx.lookup("java:comp/env/service/BasicGatewayInterfaceService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new pe.com.bn.bnws.ws.interfaz.BasicGatewayInterfaceService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getGatewayInterfacePort();
        }

        public pe.com.bn.bnws.ws.interfaz.GatewayInterface getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://interfaz.ws.bnws.bn.com.pe", "GatewayInterfacePort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                System.out.println("dispatchEndpointUrl"+dispatchEndpointUrl);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            System.out.println("<<<<<<>>>>>>"+(String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
    }

    public GatewayInterfacePortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public GatewayInterfacePortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public void recargaParametro() {
        _getDescriptor().getProxy().recargaParametro();
    }

    public ResponseGateway enviarTramaConsulta(byte[] clave, RequestGateway request) {
        return _getDescriptor().getProxy().enviarTramaConsulta(clave,request);
    }

}