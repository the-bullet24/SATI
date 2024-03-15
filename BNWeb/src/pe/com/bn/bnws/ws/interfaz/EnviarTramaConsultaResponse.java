
package pe.com.bn.bnws.ws.interfaz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import pe.com.bn.bnws.ws.bean.ResponseGateway;


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
 *         &lt;element name="enviarTramaConsultaReturn" type="{http://bean.ws.bnws.bn.com.pe}ResponseGateway"/>
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
    "enviarTramaConsultaReturn"
})
@XmlRootElement(name = "enviarTramaConsultaResponse")
public class EnviarTramaConsultaResponse {

    @XmlElement(required = true, nillable = true)
    protected ResponseGateway enviarTramaConsultaReturn;

    /**
     * Obtiene el valor de la propiedad enviarTramaConsultaReturn.
     * 
     * @return
     *     possible object is
     *     {@link ResponseGateway }
     *     
     */
    public ResponseGateway getEnviarTramaConsultaReturn() {
        return enviarTramaConsultaReturn;
    }

    /**
     * Define el valor de la propiedad enviarTramaConsultaReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseGateway }
     *     
     */
    public void setEnviarTramaConsultaReturn(ResponseGateway value) {
        this.enviarTramaConsultaReturn = value;
    }

}
