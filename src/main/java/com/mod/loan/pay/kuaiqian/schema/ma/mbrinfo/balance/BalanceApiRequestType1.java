/**
 * BalanceApiRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.balance;

public class BalanceApiRequestType1  implements java.io.Serializable {
    private String memberCode;

    private int acctType;

    private String memberAcctCode;

    private String merchantMemberCode;

    public BalanceApiRequestType1() {
    }

    public BalanceApiRequestType1(
           String memberCode,
           int acctType,
           String memberAcctCode,
           String merchantMemberCode) {
           this.memberCode = memberCode;
           this.acctType = acctType;
           this.memberAcctCode = memberAcctCode;
           this.merchantMemberCode = merchantMemberCode;
    }


    /**
     * Gets the memberCode value for this BalanceApiRequestType.
     *
     * @return memberCode
     */
    public String getMemberCode() {
        return memberCode;
    }


    /**
     * Sets the memberCode value for this BalanceApiRequestType.
     *
     * @param memberCode
     */
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }


    /**
     * Gets the acctType value for this BalanceApiRequestType.
     *
     * @return acctType
     */
    public int getAcctType() {
        return acctType;
    }


    /**
     * Sets the acctType value for this BalanceApiRequestType.
     *
     * @param acctType
     */
    public void setAcctType(int acctType) {
        this.acctType = acctType;
    }


    /**
     * Gets the memberAcctCode value for this BalanceApiRequestType.
     *
     * @return memberAcctCode
     */
    public String getMemberAcctCode() {
        return memberAcctCode;
    }


    /**
     * Sets the memberAcctCode value for this BalanceApiRequestType.
     *
     * @param memberAcctCode
     */
    public void setMemberAcctCode(String memberAcctCode) {
        this.memberAcctCode = memberAcctCode;
    }


    /**
     * Gets the merchantMemberCode value for this BalanceApiRequestType.
     *
     * @return merchantMemberCode
     */
    public String getMerchantMemberCode() {
        return merchantMemberCode;
    }


    /**
     * Sets the merchantMemberCode value for this BalanceApiRequestType.
     *
     * @param merchantMemberCode
     */
    public void setMerchantMemberCode(String merchantMemberCode) {
        this.merchantMemberCode = merchantMemberCode;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BalanceApiRequestType1)) return false;
        BalanceApiRequestType1 other = (BalanceApiRequestType1) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.memberCode==null && other.getMemberCode()==null) ||
             (this.memberCode!=null &&
              this.memberCode.equals(other.getMemberCode()))) &&
            this.acctType == other.getAcctType() &&
            ((this.memberAcctCode==null && other.getMemberAcctCode()==null) ||
             (this.memberAcctCode!=null &&
              this.memberAcctCode.equals(other.getMemberAcctCode()))) &&
            ((this.merchantMemberCode==null && other.getMerchantMemberCode()==null) ||
             (this.merchantMemberCode!=null &&
              this.merchantMemberCode.equals(other.getMerchantMemberCode())));
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
        if (getMemberCode() != null) {
            _hashCode += getMemberCode().hashCode();
        }
        _hashCode += getAcctType();
        if (getMemberAcctCode() != null) {
            _hashCode += getMemberAcctCode().hashCode();
        }
        if (getMerchantMemberCode() != null) {
            _hashCode += getMerchantMemberCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalanceApiRequestType1.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceApiRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "memberCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acctType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "acctType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberAcctCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "memberAcctCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantMemberCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "merchantMemberCode"));
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
