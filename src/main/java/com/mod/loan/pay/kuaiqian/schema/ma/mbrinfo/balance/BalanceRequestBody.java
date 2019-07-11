/**
 * BalanceRequestBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.balance;

import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.MaSealPkiDataType;

public class BalanceRequestBody  implements java.io.Serializable {
    private BalanceApiRequestType balanceApiRequestType;

    private MaSealPkiDataType maSealPkiDataType;

    public BalanceRequestBody() {
    }

    public BalanceRequestBody(
           BalanceApiRequestType balanceApiRequestType,
           MaSealPkiDataType maSealPkiDataType) {
           this.balanceApiRequestType = balanceApiRequestType;
           this.maSealPkiDataType = maSealPkiDataType;
    }


    /**
     * Gets the balanceApiRequestType value for this BalanceRequestBody.
     * 
     * @return balanceApiRequestType
     */
    public BalanceApiRequestType getBalanceApiRequestType() {
        return balanceApiRequestType;
    }


    /**
     * Sets the balanceApiRequestType value for this BalanceRequestBody.
     * 
     * @param balanceApiRequestType
     */
    public void setBalanceApiRequestType(BalanceApiRequestType balanceApiRequestType) {
        this.balanceApiRequestType = balanceApiRequestType;
    }


    /**
     * Gets the maSealPkiDataType value for this BalanceRequestBody.
     * 
     * @return maSealPkiDataType
     */
    public MaSealPkiDataType getMaSealPkiDataType() {
        return maSealPkiDataType;
    }


    /**
     * Sets the maSealPkiDataType value for this BalanceRequestBody.
     * 
     * @param maSealPkiDataType
     */
    public void setMaSealPkiDataType(MaSealPkiDataType maSealPkiDataType) {
        this.maSealPkiDataType = maSealPkiDataType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BalanceRequestBody)) return false;
        BalanceRequestBody other = (BalanceRequestBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.balanceApiRequestType==null && other.getBalanceApiRequestType()==null) ||
             (this.balanceApiRequestType!=null &&
              this.balanceApiRequestType.equals(other.getBalanceApiRequestType()))) &&
            ((this.maSealPkiDataType==null && other.getMaSealPkiDataType()==null) ||
             (this.maSealPkiDataType!=null &&
              this.maSealPkiDataType.equals(other.getMaSealPkiDataType())));
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
        if (getBalanceApiRequestType() != null) {
            _hashCode += getBalanceApiRequestType().hashCode();
        }
        if (getMaSealPkiDataType() != null) {
            _hashCode += getMaSealPkiDataType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalanceRequestBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balance-request-body"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balanceApiRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceApiRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceApiRequestType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maSealPkiDataType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "maSealPkiDataType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "maSealPkiDataType"));
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
