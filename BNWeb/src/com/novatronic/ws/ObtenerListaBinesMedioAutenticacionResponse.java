/**
 * ObtenerListaBinesMedioAutenticacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaBinesMedioAutenticacionResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String[][] listaBinesMedioAutenticacion;

    private java.lang.Integer numeroBinesMedioAutenticacion;

    public ObtenerListaBinesMedioAutenticacionResponse() {
    }

    public ObtenerListaBinesMedioAutenticacionResponse(
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
           java.lang.String[][] listaBinesMedioAutenticacion,
           java.lang.Integer numeroBinesMedioAutenticacion) {
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
        this.listaBinesMedioAutenticacion = listaBinesMedioAutenticacion;
        this.numeroBinesMedioAutenticacion = numeroBinesMedioAutenticacion;
    }


    /**
     * Gets the listaBinesMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionResponse.
     * 
     * @return listaBinesMedioAutenticacion
     */
    public java.lang.String[][] getListaBinesMedioAutenticacion() {
        return listaBinesMedioAutenticacion;
    }


    /**
     * Sets the listaBinesMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionResponse.
     * 
     * @param listaBinesMedioAutenticacion
     */
    public void setListaBinesMedioAutenticacion(java.lang.String[][] listaBinesMedioAutenticacion) {
        this.listaBinesMedioAutenticacion = listaBinesMedioAutenticacion;
    }

    public java.lang.String[] getListaBinesMedioAutenticacion(int i) {
        return this.listaBinesMedioAutenticacion[i];
    }

    public void setListaBinesMedioAutenticacion(int i, java.lang.String[] _value) {
        this.listaBinesMedioAutenticacion[i] = _value;
    }


    /**
     * Gets the numeroBinesMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionResponse.
     * 
     * @return numeroBinesMedioAutenticacion
     */
    public java.lang.Integer getNumeroBinesMedioAutenticacion() {
        return numeroBinesMedioAutenticacion;
    }


    /**
     * Sets the numeroBinesMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionResponse.
     * 
     * @param numeroBinesMedioAutenticacion
     */
    public void setNumeroBinesMedioAutenticacion(java.lang.Integer numeroBinesMedioAutenticacion) {
        this.numeroBinesMedioAutenticacion = numeroBinesMedioAutenticacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaBinesMedioAutenticacionResponse)) return false;
        ObtenerListaBinesMedioAutenticacionResponse other = (ObtenerListaBinesMedioAutenticacionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaBinesMedioAutenticacion==null && other.getListaBinesMedioAutenticacion()==null) || 
             (this.listaBinesMedioAutenticacion!=null &&
              java.util.Arrays.equals(this.listaBinesMedioAutenticacion, other.getListaBinesMedioAutenticacion()))) &&
            ((this.numeroBinesMedioAutenticacion==null && other.getNumeroBinesMedioAutenticacion()==null) || 
             (this.numeroBinesMedioAutenticacion!=null &&
              this.numeroBinesMedioAutenticacion.equals(other.getNumeroBinesMedioAutenticacion())));
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
        if (getListaBinesMedioAutenticacion() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaBinesMedioAutenticacion());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaBinesMedioAutenticacion(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroBinesMedioAutenticacion() != null) {
            _hashCode += getNumeroBinesMedioAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaBinesMedioAutenticacionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaBinesMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaBinesMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroBinesMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroBinesMedioAutenticacion"));
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
