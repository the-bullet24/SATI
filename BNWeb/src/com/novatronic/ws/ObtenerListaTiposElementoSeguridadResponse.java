/**
 * ObtenerListaTiposElementoSeguridadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaTiposElementoSeguridadResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String[][] listaTiposElementoSeguridad;

    private java.lang.Integer numeroTiposElementoSeguridad;

    public ObtenerListaTiposElementoSeguridadResponse() {
    }

    public ObtenerListaTiposElementoSeguridadResponse(
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
           java.lang.String[][] listaTiposElementoSeguridad,
           java.lang.Integer numeroTiposElementoSeguridad) {
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
        this.listaTiposElementoSeguridad = listaTiposElementoSeguridad;
        this.numeroTiposElementoSeguridad = numeroTiposElementoSeguridad;
    }


    /**
     * Gets the listaTiposElementoSeguridad value for this ObtenerListaTiposElementoSeguridadResponse.
     * 
     * @return listaTiposElementoSeguridad
     */
    public java.lang.String[][] getListaTiposElementoSeguridad() {
        return listaTiposElementoSeguridad;
    }


    /**
     * Sets the listaTiposElementoSeguridad value for this ObtenerListaTiposElementoSeguridadResponse.
     * 
     * @param listaTiposElementoSeguridad
     */
    public void setListaTiposElementoSeguridad(java.lang.String[][] listaTiposElementoSeguridad) {
        this.listaTiposElementoSeguridad = listaTiposElementoSeguridad;
    }

    public java.lang.String[] getListaTiposElementoSeguridad(int i) {
        return this.listaTiposElementoSeguridad[i];
    }

    public void setListaTiposElementoSeguridad(int i, java.lang.String[] _value) {
        this.listaTiposElementoSeguridad[i] = _value;
    }


    /**
     * Gets the numeroTiposElementoSeguridad value for this ObtenerListaTiposElementoSeguridadResponse.
     * 
     * @return numeroTiposElementoSeguridad
     */
    public java.lang.Integer getNumeroTiposElementoSeguridad() {
        return numeroTiposElementoSeguridad;
    }


    /**
     * Sets the numeroTiposElementoSeguridad value for this ObtenerListaTiposElementoSeguridadResponse.
     * 
     * @param numeroTiposElementoSeguridad
     */
    public void setNumeroTiposElementoSeguridad(java.lang.Integer numeroTiposElementoSeguridad) {
        this.numeroTiposElementoSeguridad = numeroTiposElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaTiposElementoSeguridadResponse)) return false;
        ObtenerListaTiposElementoSeguridadResponse other = (ObtenerListaTiposElementoSeguridadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaTiposElementoSeguridad==null && other.getListaTiposElementoSeguridad()==null) || 
             (this.listaTiposElementoSeguridad!=null &&
              java.util.Arrays.equals(this.listaTiposElementoSeguridad, other.getListaTiposElementoSeguridad()))) &&
            ((this.numeroTiposElementoSeguridad==null && other.getNumeroTiposElementoSeguridad()==null) || 
             (this.numeroTiposElementoSeguridad!=null &&
              this.numeroTiposElementoSeguridad.equals(other.getNumeroTiposElementoSeguridad())));
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
        if (getListaTiposElementoSeguridad() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTiposElementoSeguridad());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTiposElementoSeguridad(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroTiposElementoSeguridad() != null) {
            _hashCode += getNumeroTiposElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaTiposElementoSeguridadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTiposElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaTiposElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTiposElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroTiposElementoSeguridad"));
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
