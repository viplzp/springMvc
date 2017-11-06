/**
 *@Copyright:Copyright (c) 2008 - 2100
 *@Company:
 *
 *@Title:后台返回xml头文件
 *@Description:
 *@Author:zq
 *@Since:2014-3-26
 *@Version:1.1.0
 */

package com.zbd.test;

public class ResponseMsg {
    private String TxnCode; // 交易代码 必填
    private String Version; // 版本号 必填 V1.0
    private String TraceNo; // 发起方流水号 必填
    private String SysTraceNo; // 应答方流水号 必填
    private String ContractNo; // 合约号 可选
    private String SysID9;// 应答方系统ID 必填
    private String MsgTyp;// 消息类型 必填 "N-正常E-错误W-警告"
    private String RetCode;// 返回码 必填
    private String RetMsg;// 返回信息 必填
    private String MPkgIdx;// 多包标志 必填 0-单包 1-多包
    private String CPkgIdx;// 后续包标志 可选 0-无后续包 1-有后续包
    private String RspTranDate;// 平台返回交易日期 必填
    private String RspTranTime;// 平台返回交易时间 必填
    private String AcctDate;// 会计日期 可选
    private String SubTrCode;// 后继交易码 可选
    private String MacFlag;// 是否用校验码 必填 1-要校验 0-不要校验
    private String Mac;// 校验码 可选
    private String TxnResvFld1;// 保留字段1 可选
    private String TxnResvFld2;// 保留字段1 可选
    private String TxnResvFld3;// 保留字段1 可选

    /**
     * @return the txnCode
     */
    public String getTxnCode() {
        return TxnCode;
    }

    /**
     * @param txnCode
     *            the txnCode to set
     */
    public void setTxnCode(String txnCode) {
        TxnCode = txnCode;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return Version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        Version = version;
    }

    /**
     * @return the traceNo
     */
    public String getTraceNo() {
        return TraceNo;
    }

    /**
     * @param traceNo
     *            the traceNo to set
     */
    public void setTraceNo(String traceNo) {
        TraceNo = traceNo;
    }

    /**
     * @return the sysTraceNo
     */
    public String getSysTraceNo() {
        return SysTraceNo;
    }

    /**
     * @param sysTraceNo
     *            the sysTraceNo to set
     */
    public void setSysTraceNo(String sysTraceNo) {
        SysTraceNo = sysTraceNo;
    }

    /**
     * @return the contractNo
     */
    public String getContractNo() {
        return ContractNo;
    }

    /**
     * @param contractNo
     *            the contractNo to set
     */
    public void setContractNo(String contractNo) {
        ContractNo = contractNo;
    }

    /**
     * @return the sysID9
     */
    public String getSysID9() {
        return SysID9;
    }

    /**
     * @param sysID9
     *            the sysID9 to set
     */
    public void setSysID9(String sysID9) {
        SysID9 = sysID9;
    }

    /**
     * @return the msgTyp
     */
    public String getMsgTyp() {
        return MsgTyp;
    }

    /**
     * @param msgTyp
     *            the msgTyp to set
     */
    public void setMsgTyp(String msgTyp) {
        MsgTyp = msgTyp;
    }

    /**
     * @return the retCode
     */
    public String getRetCode() {
        return RetCode;
    }

    /**
     * @param retCode
     *            the retCode to set
     */
    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    /**
     * @return the retMsg
     */
    public String getRetMsg() {
        return RetMsg;
    }

    /**
     * @param retMsg
     *            the retMsg to set
     */
    public void setRetMsg(String retMsg) {
        RetMsg = retMsg;
    }

    /**
     * @return the mPkgIdx
     */
    public String getMPkgIdx() {
        return MPkgIdx;
    }

    /**
     * @param mPkgIdx
     *            the mPkgIdx to set
     */
    public void setMPkgIdx(String mPkgIdx) {
        MPkgIdx = mPkgIdx;
    }

    /**
     * @return the cPkgIdx
     */
    public String getCPkgIdx() {
        return CPkgIdx;
    }

    /**
     * @param cPkgIdx
     *            the cPkgIdx to set
     */
    public void setCPkgIdx(String cPkgIdx) {
        CPkgIdx = cPkgIdx;
    }

    /**
     * @return the rspTranDate
     */
    public String getRspTranDate() {
        return RspTranDate;
    }

    /**
     * @param rspTranDate
     *            the rspTranDate to set
     */
    public void setRspTranDate(String rspTranDate) {
        RspTranDate = rspTranDate;
    }

    /**
     * @return the rspTranTime
     */
    public String getRspTranTime() {
        return RspTranTime;
    }

    /**
     * @param rspTranTime
     *            the rspTranTime to set
     */
    public void setRspTranTime(String rspTranTime) {
        RspTranTime = rspTranTime;
    }

    /**
     * @return the acctDate
     */
    public String getAcctDate() {
        return AcctDate;
    }

    /**
     * @param acctDate
     *            the acctDate to set
     */
    public void setAcctDate(String acctDate) {
        AcctDate = acctDate;
    }

    /**
     * @return the subTrCode
     */
    public String getSubTrCode() {
        return SubTrCode;
    }

    /**
     * @param subTrCode
     *            the subTrCode to set
     */
    public void setSubTrCode(String subTrCode) {
        SubTrCode = subTrCode;
    }

    /**
     * @return the macFlag
     */
    public String getMacFlag() {
        return MacFlag;
    }

    /**
     * @param macFlag
     *            the macFlag to set
     */
    public void setMacFlag(String macFlag) {
        MacFlag = macFlag;
    }

    /**
     * @return the mac
     */
    public String getMac() {
        return Mac;
    }

    /**
     * @param mac
     *            the mac to set
     */
    public void setMac(String mac) {
        Mac = mac;
    }

    /**
     * @return the txnResvFld1
     */
    public String getTxnResvFld1() {
        return TxnResvFld1;
    }

    /**
     * @param txnResvFld1
     *            the txnResvFld1 to set
     */
    public void setTxnResvFld1(String txnResvFld1) {
        TxnResvFld1 = txnResvFld1;
    }

    /**
     * @return the txnResvFld2
     */
    public String getTxnResvFld2() {
        return TxnResvFld2;
    }

    /**
     * @param txnResvFld2
     *            the txnResvFld2 to set
     */
    public void setTxnResvFld2(String txnResvFld2) {
        TxnResvFld2 = txnResvFld2;
    }

    /**
     * @return the txnResvFld3
     */
    public String getTxnResvFld3() {
        return TxnResvFld3;
    }

    /**
     * @param txnResvFld3
     *            the txnResvFld3 to set
     */
    public void setTxnResvFld3(String txnResvFld3) {
        TxnResvFld3 = txnResvFld3;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseMsg [TxnCode=" + TxnCode + ", Version=" + Version
                + ", TraceNo=" + TraceNo + ", SysTraceNo=" + SysTraceNo
                + ", ContractNo=" + ContractNo + ", SysID9=" + SysID9
                + ", MsgTyp=" + MsgTyp + ", RetCode=" + RetCode + ", RetMsg="
                + RetMsg + ", MPkgIdx=" + MPkgIdx + ", CPkgIdx=" + CPkgIdx
                + ", RspTranDate=" + RspTranDate + ", RspTranTime="
                + RspTranTime + ", AcctDate=" + AcctDate + ", SubTrCode="
                + SubTrCode + ", MacFlag=" + MacFlag + ", Mac=" + Mac
                + ", TxnResvFld1=" + TxnResvFld1 + ", TxnResvFld2="
                + TxnResvFld2 + ", TxnResvFld3=" + TxnResvFld3 + "]";
    }

}
