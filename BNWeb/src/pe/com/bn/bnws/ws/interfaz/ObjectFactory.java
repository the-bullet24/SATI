
package pe.com.bn.bnws.ws.interfaz;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pe.com.bn.bnws.ws.interfaz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.com.bn.bnws.ws.interfaz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviarTramaConsulta }
     * 
     */
    public EnviarTramaConsulta createEnviarTramaConsulta() {
        return new EnviarTramaConsulta();
    }

    /**
     * Create an instance of {@link RecargaParametroResponse }
     * 
     */
    public RecargaParametroResponse createRecargaParametroResponse() {
        return new RecargaParametroResponse();
    }

    /**
     * Create an instance of {@link EnviarTramaConsultaResponse }
     * 
     */
    public EnviarTramaConsultaResponse createEnviarTramaConsultaResponse() {
        return new EnviarTramaConsultaResponse();
    }

    /**
     * Create an instance of {@link RecargaParametro }
     * 
     */
    public RecargaParametro createRecargaParametro() {
        return new RecargaParametro();
    }

}
