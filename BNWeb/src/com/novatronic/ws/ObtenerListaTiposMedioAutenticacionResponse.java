/**
 * ObtenerListaTiposMedioAutenticacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaTiposMedioAutenticacionResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String[][] listaTiposMedioAutenticacion;

    private java.lang.Integer numeroTiposMedioAutenticacion;

    public ObtenerListaTiposMedioAutenticacionResponse() {
    }

    public ObtenerListaTiposMedioAutenticacionResponse(
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
           java.lang.String[][] listaTiposMedioAutenticacion,
           java.lang.Integer numeroTiposMedioAutenticacion) {
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
        this.listaTiposMedioAutenticacion = listaTiposMedioAutenticacion;
        this.numeroTiposMedioAutenticacion = numeroTiposMedioAutenticacion;
    }


    /**
     * Gets the listaTiposMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionResponse.
     * 
     * @return listaTiposMedioAutenticacion
     */
    public java.lang.String[][] getListaTiposMedioAutenticacion() {
        return listaTiposMedioAutenticacion;
    }


    /**
     * Sets the listaTiposMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionResponse.
     * 
     * @param listaTiposMedioAutenticacion
     */
    public void setListaTiposMedioAutenticacion(java.lang.String[][] listaTiposMedioAutenticacion) {
        this.listaTiposMedioAutenticacion = listaTiposMedioAutenticacion;
    }

    public java.lang.String[] getListaTiposMedioAutenticacion(int i) {
        return this.listaTiposMedioAutenticacion[i];
    }

    public void setListaTiposMedioAutenticacion(int i, java.lang.String[] _value) {
        this.listaTiposMedioAutenticacion[i] = _value;
    }


    /**
     * Gets the numeroTiposMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionResponse.
     * 
     * @return numeroTiposMedioAutenticacion
     */
    public java.lang.Integer getNumeroTiposMedioAutenticacion() {
        return numeroTiposMedioAutenticacion;
    }


    /**
     * Sets the numeroTiposMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionResponse.
     * 
     * @param numeroTiposMedioAutenticacion
     */
    public void setNumeroTiposMedioAutenticacion(java.lang.Integer numeroTiposMedioAutenticacion) {
        this.numeroTiposMedioAutenticacion = numeroTiposMedioAutenticacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaTiposMedioAutenticacionResponse)) return false;
        ObtenerListaTiposMedioAutenticacionResponse other = (ObtenerListaTiposMedioAutenticacionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaTiposMedioAutenticacion==null && other.getListaTiposMedioAutenticacion()==null) || 
             (this.listaTiposMedioAutenticacion!=null &&
              java.util.Arrays.equals(this.listaTiposMedioAutenticacion, other.getListaTiposMedioAutenticacion()))) &&
            ((this.numeroTiposMedioAutenticacion==null && other.getNumeroTiposMedioAutenticacion()==null) || 
             (this.numeroTiposMedioAutenticacion!=null &&
              this.numeroTiposMedioAutenticacion.equals(other.getNumeroTiposMedioAutenticacion())));
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
        if (getListaTiposMedioAutenticacion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTiposMedioAutenticacion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTiposMedioAutenticacion(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroTiposMedioAutenticacion() != null) {
            _hashCode += getNumeroTiposMedioAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaTiposMedioAutenticacionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTiposMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaTiposMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTiposMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroTiposMedioAutenticacion"));
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
