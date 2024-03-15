/**
 * ValidarValoresSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ValidarValoresSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.Integer respuestaValidacionCVV;

    private java.lang.Integer respuestaValidacionCVV2;

    private java.lang.Integer respuestaValidacionOffset;

    private java.lang.Integer respuestaValidacionPVV;

    private java.lang.Integer respuestaValidacionPassword;

    public ValidarValoresSeguridadResponse() {
    }

    public ValidarValoresSeguridadResponse(
           java.lang.Integer tipoFormato,
           java.lang.String codigoComando,
           java.lang.String codigoEmpresa,
           java.lang.String tipoAplicacionOrigen,
           java.lang.String codigoAplicacionOrigen,
           java.util.Calendar fechaDate,
           java.lang.Integer fecha,
           java.lang.Integer hora,
           java.lang.String usuario,
           java.lang.Integer numeroSecuencia,
           java.lang.String codigoComercio,
           java.lang.Integer tipoTerminal,
           java.lang.String numeroTerminal,
           java.lang.String codigoZonaIntercambio,
           java.lang.String filler,
           java.lang.String origen,
           java.lang.Integer codigoRespuestaPrincipal,
           java.lang.String mensajeError,
           java.lang.String codigoRespuestaExtendido,
           java.lang.Integer respuestaValidacionCVV,
           java.lang.Integer respuestaValidacionCVV2,
           java.lang.Integer respuestaValidacionOffset,
           java.lang.Integer respuestaValidacionPVV,
           java.lang.Integer respuestaValidacionPassword) {
        super(
            tipoFormato,
            codigoComando,
            codigoEmpresa,
            tipoAplicacionOrigen,
            codigoAplicacionOrigen,
            fechaDate,
            fecha,
            hora,
            usuario,
            numeroSecuencia,
            codigoComercio,
            tipoTerminal,
            numeroTerminal,
            codigoZonaIntercambio,
            filler,
            origen,
            codigoRespuestaPrincipal,
            mensajeError,
            codigoRespuestaExtendido);
        this.respuestaValidacionCVV = respuestaValidacionCVV;
        this.respuestaValidacionCVV2 = respuestaValidacionCVV2;
        this.respuestaValidacionOffset = respuestaValidacionOffset;
        this.respuestaValidacionPVV = respuestaValidacionPVV;
        this.respuestaValidacionPassword = respuestaValidacionPassword;
    }


    /**
     * Gets the respuestaValidacionCVV value for this ValidarValoresSeguridadResponse.
     * 
     * @return respuestaValidacionCVV
     */
    public java.lang.Integer getRespuestaValidacionCVV() {
        return respuestaValidacionCVV;
    }


    /**
     * Sets the respuestaValidacionCVV value for this ValidarValoresSeguridadResponse.
     * 
     * @param respuestaValidacionCVV
     */
    public void setRespuestaValidacionCVV(java.lang.Integer respuestaValidacionCVV) {
        this.respuestaValidacionCVV = respuestaValidacionCVV;
    }


    /**
     * Gets the respuestaValidacionCVV2 value for this ValidarValoresSeguridadResponse.
     * 
     * @return respuestaValidacionCVV2
     */
    public java.lang.Integer getRespuestaValidacionCVV2() {
        return respuestaValidacionCVV2;
    }


    /**
     * Sets the respuestaValidacionCVV2 value for this ValidarValoresSeguridadResponse.
     * 
     * @param respuestaValidacionCVV2
     */
    public void setRespuestaValidacionCVV2(java.lang.Integer respuestaValidacionCVV2) {
        this.respuestaValidacionCVV2 = respuestaValidacionCVV2;
    }


    /**
     * Gets the respuestaValidacionOffset value for this ValidarValoresSeguridadResponse.
     * 
     * @return respuestaValidacionOffset
     */
    public java.lang.Integer getRespuestaValidacionOffset() {
        return respuestaValidacionOffset;
    }


    /**
     * Sets the respuestaValidacionOffset value for this ValidarValoresSeguridadResponse.
     * 
     * @param respuestaValidacionOffset
     */
    public void setRespuestaValidacionOffset(java.lang.Integer respuestaValidacionOffset) {
        this.respuestaValidacionOffset = respuestaValidacionOffset;
    }


    /**
     * Gets the respuestaValidacionPVV value for this ValidarValoresSeguridadResponse.
     * 
     * @return respuestaValidacionPVV
     */
    public java.lang.Integer getRespuestaValidacionPVV() {
        return respuestaValidacionPVV;
    }


    /**
     * Sets the respuestaValidacionPVV value for this ValidarValoresSeguridadResponse.
     * 
     * @param respuestaValidacionPVV
     */
    public void setRespuestaValidacionPVV(java.lang.Integer respuestaValidacionPVV) {
        this.respuestaValidacionPVV = respuestaValidacionPVV;
    }


    /**
     * Gets the respuestaValidacionPassword value for this ValidarValoresSeguridadResponse.
     * 
     * @return respuestaValidacionPassword
     */
    public java.lang.Integer getRespuestaValidacionPassword() {
        return respuestaValidacionPassword;
    }


    /**
     * Sets the respuestaValidacionPassword value for this ValidarValoresSeguridadResponse.
     * 
     * @param respuestaValidacionPassword
     */
    public void setRespuestaValidacionPassword(java.lang.Integer respuestaValidacionPassword) {
        this.respuestaValidacionPassword = respuestaValidacionPassword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarValoresSeguridadResponse)) return false;
        ValidarValoresSeguridadResponse other = (ValidarValoresSeguridadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.respuestaValidacionCVV==null && other.getRespuestaValidacionCVV()==null) || 
             (this.respuestaValidacionCVV!=null &&
              this.respuestaValidacionCVV.equals(other.getRespuestaValidacionCVV()))) &&
            ((this.respuestaValidacionCVV2==null && other.getRespuestaValidacionCVV2()==null) || 
             (this.respuestaValidacionCVV2!=null &&
              this.respuestaValidacionCVV2.equals(other.getRespuestaValidacionCVV2()))) &&
            ((this.respuestaValidacionOffset==null && other.getRespuestaValidacionOffset()==null) || 
             (this.respuestaValidacionOffset!=null &&
              this.respuestaValidacionOffset.equals(other.getRespuestaValidacionOffset()))) &&
            ((this.respuestaValidacionPVV==null && other.getRespuestaValidacionPVV()==null) || 
             (this.respuestaValidacionPVV!=null &&
              this.respuestaValidacionPVV.equals(other.getRespuestaValidacionPVV()))) &&
            ((this.respuestaValidacionPassword==null && other.getRespuestaValidacionPassword()==null) || 
             (this.respuestaValidacionPassword!=null &&
              this.respuestaValidacionPassword.equals(other.getRespuestaValidacionPassword())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getRespuestaValidacionCVV() != null) {
            _hashCode += getRespuestaValidacionCVV().hashCode();
        }
        if (getRespuestaValidacionCVV2() != null) {
            _hashCode += getRespuestaValidacionCVV2().hashCode();
        }
        if (getRespuestaValidacionOffset() != null) {
            _hashCode += getRespuestaValidacionOffset().hashCode();
        }
        if (getRespuestaValidacionPVV() != null) {
            _hashCode += getRespuestaValidacionPVV().hashCode();
        }
        if (getRespuestaValidacionPassword() != null) {
            _hashCode += getRespuestaValidacionPassword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidarValoresSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaValidacionCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaValidacionCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaValidacionCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaValidacionCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaValidacionOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaValidacionOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaValidacionPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaValidacionPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaValidacionPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaValidacionPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
