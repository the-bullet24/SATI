
package pe.com.bn.bnws.ws.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResponseGateway complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseGateway">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filler" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msgno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseGateway", propOrder = {
    "datos",
    "filler",
    "mensaje",
    "msgno"
})
public class ResponseGateway {

    @XmlElement(required = true, nillable = true)
    protected String datos;
    @XmlElement(required = true, nillable = true)
    protected String filler;
    @XmlElement(required = true, nillable = true)
    protected String mensaje;
    @XmlElement(required = true, nillable = true)
    protected String msgno;

    /**
     * Obtiene el valor de la propiedad datos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Define el valor de la propiedad datos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatos(String value) {
        this.datos = value;
    }

    /**
     * Obtiene el valor de la propiedad filler.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiller() {
        return filler;
    }

    /**
     * Define el valor de la propiedad filler.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiller(String value) {
        this.filler = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad msgno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgno() {
        return msgno;
    }

    /**
     * Define el valor de la propiedad msgno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgno(String value) {
        this.msgno = value;
    }

}
