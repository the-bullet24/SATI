
package pe.com.bn.bnws.ws.interfaz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import pe.com.bn.bnws.ws.bean.RequestGateway;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="request" type="{http://bean.ws.bnws.bn.com.pe}RequestGateway"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "clave",
    "request"
})
@XmlRootElement(name = "enviarTramaConsulta")
public class EnviarTramaConsulta {

    @XmlElement(required = true)
    protected byte[] clave;
    @XmlElement(required = true, nillable = true)
    protected RequestGateway request;

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setClave(byte[] value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad request.
     * 
     * @return
     *     possible object is
     *     {@link RequestGateway }
     *     
     */
    public RequestGateway getRequest() {
        return request;
    }

    /**
     * Define el valor de la propiedad request.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestGateway }
     *     
     */
    public void setRequest(RequestGateway value) {
        this.request = value;
    }

}
