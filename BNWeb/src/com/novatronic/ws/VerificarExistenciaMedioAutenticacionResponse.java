/**
 * VerificarExistenciaMedioAutenticacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class VerificarExistenciaMedioAutenticacionResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacionVirtual;

    private java.lang.String codigoMedioAutenticacionVirtual;

    private java.lang.String estadoMedioAutenticacion;

    private java.lang.String estadoMedioAutenticacionVirtual;

    public VerificarExistenciaMedioAutenticacionResponse() {
    }

    public VerificarExistenciaMedioAutenticacionResponse(
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
           java.lang.String binMedioAutenticacionVirtual,
           java.lang.String codigoMedioAutenticacionVirtual,
           java.lang.String estadoMedioAutenticacion,
           java.lang.String estadoMedioAutenticacionVirtual) {
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
        this.binMedioAutenticacionVirtual = binMedioAutenticacionVirtual;
        this.codigoMedioAutenticacionVirtual = codigoMedioAutenticacionVirtual;
        this.estadoMedioAutenticacion = estadoMedioAutenticacion;
        this.estadoMedioAutenticacionVirtual = estadoMedioAutenticacionVirtual;
    }


    /**
     * Gets the binMedioAutenticacionVirtual value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @return binMedioAutenticacionVirtual
     */
    public java.lang.String getBinMedioAutenticacionVirtual() {
        return binMedioAutenticacionVirtual;
    }


    /**
     * Sets the binMedioAutenticacionVirtual value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @param binMedioAutenticacionVirtual
     */
    public void setBinMedioAutenticacionVirtual(java.lang.String binMedioAutenticacionVirtual) {
        this.binMedioAutenticacionVirtual = binMedioAutenticacionVirtual;
    }


    /**
     * Gets the codigoMedioAutenticacionVirtual value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @return codigoMedioAutenticacionVirtual
     */
    public java.lang.String getCodigoMedioAutenticacionVirtual() {
        return codigoMedioAutenticacionVirtual;
    }


    /**
     * Sets the codigoMedioAutenticacionVirtual value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @param codigoMedioAutenticacionVirtual
     */
    public void setCodigoMedioAutenticacionVirtual(java.lang.String codigoMedioAutenticacionVirtual) {
        this.codigoMedioAutenticacionVirtual = codigoMedioAutenticacionVirtual;
    }


    /**
     * Gets the estadoMedioAutenticacion value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @return estadoMedioAutenticacion
     */
    public java.lang.String getEstadoMedioAutenticacion() {
        return estadoMedioAutenticacion;
    }


    /**
     * Sets the estadoMedioAutenticacion value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @param estadoMedioAutenticacion
     */
    public void setEstadoMedioAutenticacion(java.lang.String estadoMedioAutenticacion) {
        this.estadoMedioAutenticacion = estadoMedioAutenticacion;
    }


    /**
     * Gets the estadoMedioAutenticacionVirtual value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @return estadoMedioAutenticacionVirtual
     */
    public java.lang.String getEstadoMedioAutenticacionVirtual() {
        return estadoMedioAutenticacionVirtual;
    }


    /**
     * Sets the estadoMedioAutenticacionVirtual value for this VerificarExistenciaMedioAutenticacionResponse.
     * 
     * @param estadoMedioAutenticacionVirtual
     */
    public void setEstadoMedioAutenticacionVirtual(java.lang.String estadoMedioAutenticacionVirtual) {
        this.estadoMedioAutenticacionVirtual = estadoMedioAutenticacionVirtual;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarExistenciaMedioAutenticacionResponse)) return false;
        VerificarExistenciaMedioAutenticacionResponse other = (VerificarExistenciaMedioAutenticacionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.binMedioAutenticacionVirtual==null && other.getBinMedioAutenticacionVirtual()==null) || 
             (this.binMedioAutenticacionVirtual!=null &&
              this.binMedioAutenticacionVirtual.equals(other.getBinMedioAutenticacionVirtual()))) &&
            ((this.codigoMedioAutenticacionVirtual==null && other.getCodigoMedioAutenticacionVirtual()==null) || 
             (this.codigoMedioAutenticacionVirtual!=null &&
              this.codigoMedioAutenticacionVirtual.equals(other.getCodigoMedioAutenticacionVirtual()))) &&
            ((this.estadoMedioAutenticacion==null && other.getEstadoMedioAutenticacion()==null) || 
             (this.estadoMedioAutenticacion!=null &&
              this.estadoMedioAutenticacion.equals(other.getEstadoMedioAutenticacion()))) &&
            ((this.estadoMedioAutenticacionVirtual==null && other.getEstadoMedioAutenticacionVirtual()==null) || 
             (this.estadoMedioAutenticacionVirtual!=null &&
              this.estadoMedioAutenticacionVirtual.equals(other.getEstadoMedioAutenticacionVirtual())));
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
        if (getBinMedioAutenticacionVirtual() != null) {
            _hashCode += getBinMedioAutenticacionVirtual().hashCode();
        }
        if (getCodigoMedioAutenticacionVirtual() != null) {
            _hashCode += getCodigoMedioAutenticacionVirtual().hashCode();
        }
        if (getEstadoMedioAutenticacion() != null) {
            _hashCode += getEstadoMedioAutenticacion().hashCode();
        }
        if (getEstadoMedioAutenticacionVirtual() != null) {
            _hashCode += getEstadoMedioAutenticacionVirtual().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarExistenciaMedioAutenticacionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binMedioAutenticacionVirtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binMedioAutenticacionVirtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMedioAutenticacionVirtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMedioAutenticacionVirtual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoMedioAutenticacionVirtual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoMedioAutenticacionVirtual"));
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
