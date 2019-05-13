/**
 * MaMbrinfoRequestHead.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.kuaiqian.schema.ma.mbrinfo;

import com.mod.loan.kuaiqian.schema.commons.Version;

public class MaMbrinfoRequestHead  implements java.io.Serializable {
    private Version version;

    private String requestId;

    private String appId;

    public MaMbrinfoRequestHead() {
    }

    public MaMbrinfoRequestHead(
            Version version,
           String requestId,
           String appId) {
           this.version = version;
           this.requestId = requestId;
           this.appId = appId;
    }


    /**
     * Gets the version value for this MaMbrinfoRequestHead.
     *
     * @return version
     */
    public Version getVersion() {
        return version;
    }


    /**
     * Sets the version value for this MaMbrinfoRequestHead.
     *
     * @param version
     */
    public void setVersion(Version version) {
        this.version = version;
    }


    /**
     * Gets the requestId value for this MaMbrinfoRequestHead.
     *
     * @return requestId
     */
    public String getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this MaMbrinfoRequestHead.
     *
     * @param requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the appId value for this MaMbrinfoRequestHead.
     *
     * @return appId
     */
    public String getAppId() {
        return appId;
    }


    /**
     * Sets the appId value for this MaMbrinfoRequestHead.
     *
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof MaMbrinfoRequestHead)) return false;
        MaMbrinfoRequestHead other = (MaMbrinfoRequestHead) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.version==null && other.getVersion()==null) ||
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.requestId==null && other.getRequestId()==null) ||
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId()))) &&
            ((this.appId==null && other.getAppId()==null) ||
             (this.appId!=null &&
              this.appId.equals(other.getAppId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        if (getAppId() != null) {
            _hashCode += getAppId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MaMbrinfoRequestHead.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "ma-mbrinfo-request-head"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/commons", "version"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "appId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
