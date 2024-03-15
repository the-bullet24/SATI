/**
 * ExtornarCambioValorSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ExtornarCambioValorSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String codigoAutorizacion;

    private java.lang.Integer respuestaExtornoOffset;

    private java.lang.Integer respuestaExtornoPVV;

    private java.lang.Integer respuestaExtornoPassword;

    public ExtornarCambioValorSeguridadResponse() {
    }

    public ExtornarCambioValorSeguridadResponse(
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
           java.lang.Integer respuestaExtornoOffset,
           java.lang.Integer respuestaExtornoPVV,
           java.lang.Integer respuestaExtornoPassword) {
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
        this.respuestaExtornoOffset = respuestaExtornoOffset;
        this.respuestaExtornoPVV = respuestaExtornoPVV;
        this.respuestaExtornoPassword = respuestaExtornoPassword;
    }


    /**
     * Gets the codigoAutorizacion value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @return codigoAutorizacion
     */
    public java.lang.String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }


    /**
     * Sets the codigoAutorizacion value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @param codigoAutorizacion
     */
    public void setCodigoAutorizacion(java.lang.String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }


    /**
     * Gets the respuestaExtornoOffset value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @return respuestaExtornoOffset
     */
    public java.lang.Integer getRespuestaExtornoOffset() {
        return respuestaExtornoOffset;
    }


    /**
     * Sets the respuestaExtornoOffset value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @param respuestaExtornoOffset
     */
    public void setRespuestaExtornoOffset(java.lang.Integer respuestaExtornoOffset) {
        this.respuestaExtornoOffset = respuestaExtornoOffset;
    }


    /**
     * Gets the respuestaExtornoPVV value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @return respuestaExtornoPVV
     */
    public java.lang.Integer getRespuestaExtornoPVV() {
        return respuestaExtornoPVV;
    }


    /**
     * Sets the respuestaExtornoPVV value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @param respuestaExtornoPVV
     */
    public void setRespuestaExtornoPVV(java.lang.Integer respuestaExtornoPVV) {
        this.respuestaExtornoPVV = respuestaExtornoPVV;
    }


    /**
     * Gets the respuestaExtornoPassword value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @return respuestaExtornoPassword
     */
    public java.lang.Integer getRespuestaExtornoPassword() {
        return respuestaExtornoPassword;
    }


    /**
     * Sets the respuestaExtornoPassword value for this ExtornarCambioValorSeguridadResponse.
     * 
     * @param respuestaExtornoPassword
     */
    public void setRespuestaExtornoPassword(java.lang.Integer respuestaExtornoPassword) {
        this.respuestaExtornoPassword = respuestaExtornoPassword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtornarCambioValorSeguridadResponse)) return false;
        ExtornarCambioValorSeguridadResponse other = (ExtornarCambioValorSeguridadResponse) obj;
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
            ((this.respuestaExtornoOffset==null && other.getRespuestaExtornoOffset()==null) || 
             (this.respuestaExtornoOffset!=null &&
              this.respuestaExtornoOffset.equals(other.getRespuestaExtornoOffset()))) &&
            ((this.respuestaExtornoPVV==null && other.getRespuestaExtornoPVV()==null) || 
             (this.respuestaExtornoPVV!=null &&
              this.respuestaExtornoPVV.equals(other.getRespuestaExtornoPVV()))) &&
            ((this.respuestaExtornoPassword==null && other.getRespuestaExtornoPassword()==null) || 
             (this.respuestaExtornoPassword!=null &&
              this.respuestaExtornoPassword.equals(other.getRespuestaExtornoPassword())));
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
        if (getRespuestaExtornoOffset() != null) {
            _hashCode += getRespuestaExtornoOffset().hashCode();
        }
        if (getRespuestaExtornoPVV() != null) {
            _hashCode += getRespuestaExtornoPVV().hashCode();
        }
        if (getRespuestaExtornoPassword() != null) {
            _hashCode += getRespuestaExtornoPassword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtornarCambioValorSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAutorizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAutorizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaExtornoOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaExtornoOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaExtornoPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaExtornoPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaExtornoPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuestaExtornoPassword"));
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
