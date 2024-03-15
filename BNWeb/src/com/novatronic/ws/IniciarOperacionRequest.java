/**
 * IniciarOperacionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class IniciarOperacionRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.Integer canal;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.Integer transaccion;

    public IniciarOperacionRequest() {
    }

    public IniciarOperacionRequest(
           java.lang.String codigoAplicacionOrigen,
           java.lang.String codigoComercio,
           java.lang.String codigoEmpresa,
           java.lang.String codigoZonaIntercambio,
           java.lang.String numeroTerminal,
           java.lang.String tipoAplicacionOrigen,
           java.lang.Integer tipoTerminal,
           java.lang.String usuario,
           java.util.Calendar fechaDate,
           java.lang.Integer numeroSecuencia,
           java.lang.String binMedioAutenticacion,
           java.lang.Integer canal,
           java.lang.String codigoMedioAutenticacion,
           java.lang.Integer transaccion) {
        super(
            codigoAplicacionOrigen,
            codigoComercio,
            codigoEmpresa,
            codigoZonaIntercambio,
            numeroTerminal,
            tipoAplicacionOrigen,
            tipoTerminal,
            usuario,
            fechaDate,
            numeroSecuencia);
        this.binMedioAutenticacion = binMedioAutenticacion;
        this.canal = canal;
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
        this.transaccion = transaccion;
    }


    /**
     * Gets the binMedioAutenticacion value for this IniciarOperacionRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this IniciarOperacionRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the canal value for this IniciarOperacionRequest.
     * 
     * @return canal
     */
    public java.lang.Integer getCanal() {
        return canal;
    }


    /**
     * Sets the canal value for this IniciarOperacionRequest.
     * 
     * @param canal
     */
    public void setCanal(java.lang.Integer canal) {
        this.canal = canal;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this IniciarOperacionRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this IniciarOperacionRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the transaccion value for this IniciarOperacionRequest.
     * 
     * @return transaccion
     */
    public java.lang.Integer getTransaccion() {
        return transaccion;
    }


    /**
     * Sets the transaccion value for this IniciarOperacionRequest.
     * 
     * @param transaccion
     */
    public void setTransaccion(java.lang.Integer transaccion) {
        this.transaccion = transaccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IniciarOperacionRequest)) return false;
        IniciarOperacionRequest other = (IniciarOperacionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.binMedioAutenticacion==null && other.getBinMedioAutenticacion()==null) || 
             (this.binMedioAutenticacion!=null &&
              this.binMedioAutenticacion.equals(other.getBinMedioAutenticacion()))) &&
            ((this.canal==null && other.getCanal()==null) || 
             (this.canal!=null &&
              this.canal.equals(other.getCanal()))) &&
            ((this.codigoMedioAutenticacion==null && other.getCodigoMedioAutenticacion()==null) || 
             (this.codigoMedioAutenticacion!=null &&
              this.codigoMedioAutenticacion.equals(other.getCodigoMedioAutenticacion()))) &&
            ((this.transaccion==null && other.getTransaccion()==null) || 
             (this.transaccion!=null &&
              this.transaccion.equals(other.getTransaccion())));
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
        if (getBinMedioAutenticacion() != null) {
            _hashCode += getBinMedioAutenticacion().hashCode();
        }
        if (getCanal() != null) {
            _hashCode += getCanal().hashCode();
        }
        if (getCodigoMedioAutenticacion() != null) {
            _hashCode += getCodigoMedioAutenticacion().hashCode();
        }
        if (getTransaccion() != null) {
            _hashCode += getTransaccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IniciarOperacionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "canal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transaccion"));
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
