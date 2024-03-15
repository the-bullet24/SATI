/**
 * GenerarValoresSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class GenerarValoresSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.Integer respuestaGeneracionCVV;

    private java.lang.Integer respuestaGeneracionCVV2;

    private java.lang.Integer respuestaGeneracionOffset;

    private java.lang.Integer respuestaGeneracionPVV;

    private java.lang.Integer respuestaGeneracionPassword;

    private java.lang.String valorCVV;

    private java.lang.String valorCVV2;

    private java.lang.String valorOffset;

    private java.lang.String valorPVV;

    private java.lang.String valorPassword;

    public GenerarValoresSeguridadResponse() {
    }

    public GenerarValoresSeguridadResponse(
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
           java.lang.Integer respuestaGeneracionCVV,
           java.lang.Integer respuestaGeneracionCVV2,
           java.lang.Integer respuestaGeneracionOffset,
           java.lang.Integer respuestaGeneracionPVV,
           java.lang.Integer respuestaGeneracionPassword,
           java.lang.String valorCVV,
           java.lang.String valorCVV2,
           java.lang.String valorOffset,
           java.lang.String valorPVV,
           java.lang.String valorPassword) {
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
        this.respuestaGeneracionCVV = respuestaGeneracionCVV;
        this.respuestaGeneracionCVV2 = respuestaGeneracionCVV2;
        this.respuestaGeneracionOffset = respuestaGeneracionOffset;
        this.respuestaGeneracionPVV = respuestaGeneracionPVV;
        this.respuestaGeneracionPassword = respuestaGeneracionPassword;
        this.valorCVV = valorCVV;
        this.valorCVV2 = valorCVV2;
        this.valorOffset = valorOffset;
        this.valorPVV = valorPVV;
        this.valorPassword = valorPassword;
    }


    /**
     * Gets the respuestaGeneracionCVV value for this GenerarValoresSeguridadResponse.
     * 
     * @return respuestaGeneracionCVV
     */
    public java.lang.Integer getRespuestaGeneracionCVV() {
        return respuestaGeneracionCVV;
    }


    /**
     * Sets the respuestaGeneracionCVV value for this GenerarValoresSeguridadResponse.
     * 
     * @param respuestaGeneracionCVV
     */
    public void setRespuestaGeneracionCVV(java.lang.Integer respuestaGeneracionCVV) {
        this.respuestaGeneracionCVV = respuestaGeneracionCVV;
    }


    /**
     * Gets the respuestaGeneracionCVV2 value for this GenerarValoresSeguridadResponse.
     * 
     * @return respuestaGeneracionCVV2
     */
    public java.lang.Integer getRespuestaGeneracionCVV2() {
        return respuestaGeneracionCVV2;
    }


    /**
     * Sets the respuestaGeneracionCVV2 value for this GenerarValoresSeguridadResponse.
     * 
     * @param respuestaGeneracionCVV2
     */
    public void setRespuestaGeneracionCVV2(java.lang.Integer respuestaGeneracionCVV2) {
        this.respuestaGeneracionCVV2 = respuestaGeneracionCVV2;
    }


    /**
     * Gets the respuestaGeneracionOffset value for this GenerarValoresSeguridadResponse.
     * 
     * @return respuestaGeneracionOffset
     */
    public java.lang.Integer getRespuestaGeneracionOffset() {
        return respuestaGeneracionOffset;
    }


    /**
     * Sets the respuestaGeneracionOffset value for this GenerarValoresSeguridadResponse.
     * 
     * @param respuestaGeneracionOffset
     */
    public void setRespuestaGeneracionOffset(java.lang.Integer respuestaGeneracionOffset) {
        this.respuestaGeneracionOffset = respuestaGeneracionOffset;
    }


    /**
     * Gets the respuestaGeneracionPVV value for this GenerarValoresSeguridadResponse.
     * 
     * @return respuestaGeneracionPVV
     */
    public java.lang.Integer getRespuestaGeneracionPVV() {
        return respuestaGeneracionPVV;
    }


    /**
     * Sets the respuestaGeneracionPVV value for this GenerarValoresSeguridadResponse.
     * 
     * @param respuestaGeneracionPVV
     */
    public void setRespuestaGeneracionPVV(java.lang.Integer respuestaGeneracionPVV) {
        this.respuestaGeneracionPVV = respuestaGeneracionPVV;
    }


    /**
     * Gets the respuestaGeneracionPassword value for this GenerarValoresSeguridadResponse.
     * 
     * @return respuestaGeneracionPassword
     */
    public java.lang.Integer getRespuestaGeneracionPassword() {
        return respuestaGeneracionPassword;
    }


    /**
     * Sets the respuestaGeneracionPassword value for this GenerarValoresSeguridadResponse.
     * 
     * @param respuestaGeneracionPassword
     */
    public void setRespuestaGeneracionPassword(java.lang.Integer respuestaGeneracionPassword) {
        this.respuestaGeneracionPassword = respuestaGeneracionPassword;
    }


    /**
     * Gets the valorCVV value for this GenerarValoresSeguridadResponse.
     * 
     * @return valorCVV
     */
    public java.lang.String getValorCVV() {
        return valorCVV;
    }


    /**
     * Sets the valorCVV value for this GenerarValoresSeguridadResponse.
     * 
     * @param valorCVV
     */
    public void setValorCVV(java.lang.String valorCVV) {
        this.valorCVV = valorCVV;
    }


    /**
     * Gets the valorCVV2 value for this GenerarValoresSeguridadResponse.
     * 
     * @return valorCVV2
     */
    public java.lang.String getValorCVV2() {
        return valorCVV2;
    }


    /**
     * Sets the valorCVV2 value for this GenerarValoresSeguridadResponse.
     * 
     * @param valorCVV2
     */
    public void setValorCVV2(java.lang.String valorCVV2) {
        this.valorCVV2 = valorCVV2;
    }


    /**
     * Gets the valorOffset value for this GenerarValoresSeguridadResponse.
     * 
     * @return valorOffset
     */
    public java.lang.String getValorOffset() {
        return valorOffset;
    }


    /**
     * Sets the valorOffset value for this GenerarValoresSeguridadResponse.
     * 
     * @param valorOffset
     */
    public void setValorOffset(java.lang.String valorOffset) {
        this.valorOffset = valorOffset;
    }


    /**
     * Gets the valorPVV value for this GenerarValoresSeguridadResponse.
     * 
     * @return valorPVV
     */
    public java.lang.String getValorPVV() {
        return valorPVV;
    }


    /**
     * Sets the valorPVV value for this GenerarValoresSeguridadResponse.
     * 
     * @param valorPVV
     */
    public void setValorPVV(java.lang.String valorPVV) {
        this.valorPVV = valorPVV;
    }


    /**
     * Gets the valorPassword value for this GenerarValoresSeguridadResponse.
     * 
     * @return valorPassword
     */
    public java.lang.String getValorPassword() {
        return valorPassword;
    }


    /**
     * Sets the valorPassword value for this GenerarValoresSeguridadResponse.
     * 
     * @param valorPassword
     */
    public void setValorPassword(java.lang.String valorPassword) {
        this.valorPassword = valorPassword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerarValoresSeguridadResponse)) return false;
        GenerarValoresSeguridadResponse other = (GenerarValoresSeguridadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.respuestaGeneracionCVV==null && other.getRespuestaGeneracionCVV()==null) || 
             (this.respuestaGeneracionCVV!=null &&
              this.respuestaGeneracionCVV.equals(other.getRespuestaGeneracionCVV()))) &&
            ((this.respuestaGeneracionCVV2==null && other.getRespuestaGeneracionCVV2()==null) || 
             (this.respuestaGeneracionCVV2!=null &&
              this.respuestaGeneracionCVV2.equals(other.getRespuestaGeneracionCVV2()))) &&
            ((this.respuestaGeneracionOffset==null && other.getRespuestaGeneracionOffset()==null) || 
             (this.respuestaGeneracionOffset!=null &&
              this.respuestaGeneracionOffset.equals(other.getRespuestaGeneracionOffset()))) &&
            ((this.respuestaGeneracionPVV==null && other.getRespuestaGeneracionPVV()==null) || 
             (this.respuestaGeneracionPVV!=null &&
              this.respuestaGeneracionPVV.equals(other.getRespuestaGeneracionPVV()))) &&
            ((this.respuestaGeneracionPassword==null && other.getRespuestaGeneracionPassword()==null) || 
             (this.respuestaGeneracionPassword!=null &&
              this.respuestaGeneracionPassword.equals(other.getRespuestaGeneracionPassword()))) &&
            ((this.valorCVV==null && other.getValorCVV()==null) || 
             (this.valorCVV!=null &&
              this.valorCVV.equals(other.getValorCVV()))) &&
            ((this.valorCVV2==null && other.getValorCVV2()==null) || 
             (this.valorCVV2!=null &&
              this.valorCVV2.equals(other.getValorCVV2()))) &&
            ((this.valorOffset==null && other.getValorOffset()==null) || 
             (this.valorOffset!=null &&
              this.valorOffset.equals(other.getValorOffset()))) &&
            ((this.valorPVV==null && other.getValorPVV()==null) || 
             (this.valorPVV!=null &&
              this.valorPVV.equals(other.getValorPVV()))) &&
            ((this.valorPassword==null && other.getValorPassword()==null) || 
             (this.valorPassword!=null &&
              this.valorPassword.equals(other.getValorPassword())));
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
        if (getRespuestaGeneracionCVV() != null) {
            _hashCode += getRespuestaGeneracionCVV().hashCode();
        }
        if (getRespuestaGeneracionCVV2() != null) {
            _hashCode += getRespuestaGeneracionCVV2().hashCode();
        }
        if (getRespuestaGeneracionOffset() != null) {
            _hashCode += getRespuestaGeneracionOffset().hashCode();
        }
        if (getRespuestaGeneracionPVV() != null) {
            _hashCode += getRespuestaGeneracionPVV().hashCode();
        }
        if (getRespuestaGeneracionPassword() != null) {
            _hashCode += getRespuestaGeneracionPassword().hashCode();
        }
        if (getValorCVV() != null) {
            _hashCode += getValorCVV().hashCode();
        }
        if (getValorCVV2() != null) {
            _hashCode += getValorCVV2().hashCode();
        }
        if (getValorOffset() != null) {
            _hashCode += getValorOffset().hashCode();
        }
        if (getValorPVV() != null) {
            _hashCode += getValorPVV().hashCode();
        }
        if (getValorPassword() != null) {
            _hashCode += getValorPassword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerarValoresSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaGeneracionCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaGeneracionCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaGeneracionCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaGeneracionCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaGeneracionOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaGeneracionOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaGeneracionPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaGeneracionPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaGeneracionPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaGeneracionPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
