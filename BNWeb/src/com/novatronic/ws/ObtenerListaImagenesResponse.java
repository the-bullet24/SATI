/**
 * ObtenerListaImagenesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaImagenesResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String[] listaImagenes;

    private java.lang.Integer numeroImagenes;

    public ObtenerListaImagenesResponse() {
    }

    public ObtenerListaImagenesResponse(
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
           java.lang.String[] listaImagenes,
           java.lang.Integer numeroImagenes) {
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
        this.listaImagenes = listaImagenes;
        this.numeroImagenes = numeroImagenes;
    }


    /**
     * Gets the listaImagenes value for this ObtenerListaImagenesResponse.
     * 
     * @return listaImagenes
     */
    public java.lang.String[] getListaImagenes() {
        return listaImagenes;
    }


    /**
     * Sets the listaImagenes value for this ObtenerListaImagenesResponse.
     * 
     * @param listaImagenes
     */
    public void setListaImagenes(java.lang.String[] listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public java.lang.String getListaImagenes(int i) {
        return this.listaImagenes[i];
    }

    public void setListaImagenes(int i, java.lang.String _value) {
        this.listaImagenes[i] = _value;
    }


    /**
     * Gets the numeroImagenes value for this ObtenerListaImagenesResponse.
     * 
     * @return numeroImagenes
     */
    public java.lang.Integer getNumeroImagenes() {
        return numeroImagenes;
    }


    /**
     * Sets the numeroImagenes value for this ObtenerListaImagenesResponse.
     * 
     * @param numeroImagenes
     */
    public void setNumeroImagenes(java.lang.Integer numeroImagenes) {
        this.numeroImagenes = numeroImagenes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaImagenesResponse)) return false;
        ObtenerListaImagenesResponse other = (ObtenerListaImagenesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listaImagenes==null && other.getListaImagenes()==null) || 
             (this.listaImagenes!=null &&
              java.util.Arrays.equals(this.listaImagenes, other.getListaImagenes()))) &&
            ((this.numeroImagenes==null && other.getNumeroImagenes()==null) || 
             (this.numeroImagenes!=null &&
              this.numeroImagenes.equals(other.getNumeroImagenes())));
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
        if (getListaImagenes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaImagenes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaImagenes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroImagenes() != null) {
            _hashCode += getNumeroImagenes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaImagenesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaImagenes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaImagenes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroImagenes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroImagenes"));
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
