/**
 * CambiarValoresSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class CambiarValoresSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String codigoAutorizacion;

    private java.lang.String nuevoValorOffset;

    private java.lang.String nuevoValorPVV;

    private java.lang.String nuevoValorPassword;

    private java.lang.Integer respuestaCambioOffset;

    private java.lang.Integer respuestaCambioPVV;

    private java.lang.Integer respuestaCambioPassword;

    public CambiarValoresSeguridadResponse() {
    }

    public CambiarValoresSeguridadResponse(
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
           java.lang.String codigoAutorizacion,
           java.lang.String nuevoValorOffset,
           java.lang.String nuevoValorPVV,
           java.lang.String nuevoValorPassword,
           java.lang.Integer respuestaCambioOffset,
           java.lang.Integer respuestaCambioPVV,
           java.lang.Integer respuestaCambioPassword) {
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
        this.codigoAutorizacion = codigoAutorizacion;
        this.nuevoValorOffset = nuevoValorOffset;
        this.nuevoValorPVV = nuevoValorPVV;
        this.nuevoValorPassword = nuevoValorPassword;
        this.respuestaCambioOffset = respuestaCambioOffset;
        this.respuestaCambioPVV = respuestaCambioPVV;
        this.respuestaCambioPassword = respuestaCambioPassword;
    }


    /**
     * Gets the codigoAutorizacion value for this CambiarValoresSeguridadResponse.
     * 
     * @return codigoAutorizacion
     */
    public java.lang.String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }


    /**
     * Sets the codigoAutorizacion value for this CambiarValoresSeguridadResponse.
     * 
     * @param codigoAutorizacion
     */
    public void setCodigoAutorizacion(java.lang.String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }


    /**
     * Gets the nuevoValorOffset value for this CambiarValoresSeguridadResponse.
     * 
     * @return nuevoValorOffset
     */
    public java.lang.String getNuevoValorOffset() {
        return nuevoValorOffset;
    }


    /**
     * Sets the nuevoValorOffset value for this CambiarValoresSeguridadResponse.
     * 
     * @param nuevoValorOffset
     */
    public void setNuevoValorOffset(java.lang.String nuevoValorOffset) {
        this.nuevoValorOffset = nuevoValorOffset;
    }


    /**
     * Gets the nuevoValorPVV value for this CambiarValoresSeguridadResponse.
     * 
     * @return nuevoValorPVV
     */
    public java.lang.String getNuevoValorPVV() {
        return nuevoValorPVV;
    }


    /**
     * Sets the nuevoValorPVV value for this CambiarValoresSeguridadResponse.
     * 
     * @param nuevoValorPVV
     */
    public void setNuevoValorPVV(java.lang.String nuevoValorPVV) {
        this.nuevoValorPVV = nuevoValorPVV;
    }


    /**
     * Gets the nuevoValorPassword value for this CambiarValoresSeguridadResponse.
     * 
     * @return nuevoValorPassword
     */
    public java.lang.String getNuevoValorPassword() {
        return nuevoValorPassword;
    }


    /**
     * Sets the nuevoValorPassword value for this CambiarValoresSeguridadResponse.
     * 
     * @param nuevoValorPassword
     */
    public void setNuevoValorPassword(java.lang.String nuevoValorPassword) {
        this.nuevoValorPassword = nuevoValorPassword;
    }


    /**
     * Gets the respuestaCambioOffset value for this CambiarValoresSeguridadResponse.
     * 
     * @return respuestaCambioOffset
     */
    public java.lang.Integer getRespuestaCambioOffset() {
        return respuestaCambioOffset;
    }


    /**
     * Sets the respuestaCambioOffset value for this CambiarValoresSeguridadResponse.
     * 
     * @param respuestaCambioOffset
     */
    public void setRespuestaCambioOffset(java.lang.Integer respuestaCambioOffset) {
        this.respuestaCambioOffset = respuestaCambioOffset;
    }


    /**
     * Gets the respuestaCambioPVV value for this CambiarValoresSeguridadResponse.
     * 
     * @return respuestaCambioPVV
     */
    public java.lang.Integer getRespuestaCambioPVV() {
        return respuestaCambioPVV;
    }


    /**
     * Sets the respuestaCambioPVV value for this CambiarValoresSeguridadResponse.
     * 
     * @param respuestaCambioPVV
     */
    public void setRespuestaCambioPVV(java.lang.Integer respuestaCambioPVV) {
        this.respuestaCambioPVV = respuestaCambioPVV;
    }


    /**
     * Gets the respuestaCambioPassword value for this CambiarValoresSeguridadResponse.
     * 
     * @return respuestaCambioPassword
     */
    public java.lang.Integer getRespuestaCambioPassword() {
        return respuestaCambioPassword;
    }


    /**
     * Sets the respuestaCambioPassword value for this CambiarValoresSeguridadResponse.
     * 
     * @param respuestaCambioPassword
     */
    public void setRespuestaCambioPassword(java.lang.Integer respuestaCambioPassword) {
        this.respuestaCambioPassword = respuestaCambioPassword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CambiarValoresSeguridadResponse)) return false;
        CambiarValoresSeguridadResponse other = (CambiarValoresSeguridadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoAutorizacion==null && other.getCodigoAutorizacion()==null) || 
             (this.codigoAutorizacion!=null &&
              this.codigoAutorizacion.equals(other.getCodigoAutorizacion()))) &&
            ((this.nuevoValorOffset==null && other.getNuevoValorOffset()==null) || 
             (this.nuevoValorOffset!=null &&
              this.nuevoValorOffset.equals(other.getNuevoValorOffset()))) &&
            ((this.nuevoValorPVV==null && other.getNuevoValorPVV()==null) || 
             (this.nuevoValorPVV!=null &&
              this.nuevoValorPVV.equals(other.getNuevoValorPVV()))) &&
            ((this.nuevoValorPassword==null && other.getNuevoValorPassword()==null) || 
             (this.nuevoValorPassword!=null &&
              this.nuevoValorPassword.equals(other.getNuevoValorPassword()))) &&
            ((this.respuestaCambioOffset==null && other.getRespuestaCambioOffset()==null) || 
             (this.respuestaCambioOffset!=null &&
              this.respuestaCambioOffset.equals(other.getRespuestaCambioOffset()))) &&
            ((this.respuestaCambioPVV==null && other.getRespuestaCambioPVV()==null) || 
             (this.respuestaCambioPVV!=null &&
              this.respuestaCambioPVV.equals(other.getRespuestaCambioPVV()))) &&
            ((this.respuestaCambioPassword==null && other.getRespuestaCambioPassword()==null) || 
             (this.respuestaCambioPassword!=null &&
              this.respuestaCambioPassword.equals(other.getRespuestaCambioPassword())));
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
        if (getCodigoAutorizacion() != null) {
            _hashCode += getCodigoAutorizacion().hashCode();
        }
        if (getNuevoValorOffset() != null) {
            _hashCode += getNuevoValorOffset().hashCode();
        }
        if (getNuevoValorPVV() != null) {
            _hashCode += getNuevoValorPVV().hashCode();
        }
        if (getNuevoValorPassword() != null) {
            _hashCode += getNuevoValorPassword().hashCode();
        }
        if (getRespuestaCambioOffset() != null) {
            _hashCode += getRespuestaCambioOffset().hashCode();
        }
        if (getRespuestaCambioPVV() != null) {
            _hashCode += getRespuestaCambioPVV().hashCode();
        }
        if (getRespuestaCambioPassword() != null) {
            _hashCode += getRespuestaCambioPassword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CambiarValoresSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAutorizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAutorizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nuevoValorOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nuevoValorOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nuevoValorPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nuevoValorPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nuevoValorPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nuevoValorPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaCambioOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaCambioOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaCambioPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaCambioPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaCambioPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaCambioPassword"));
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
