/**
 *@Copyright:Copyright (c) 2008 - 2100
 *@Company:
 *
 *@Title:返回服务端信息类
 *@Description:
 *@Author:zq
 *@Since:2014-3-19
 *@Version:1.1.0
 */

package com.zbd.test;


public class Message {
    private String msgCode = "310005";// 错误代码
    private Object message;// 消息

    /**
     * @return the msgCode
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode
     *            the msgCode to set
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(Object message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Message [msgCode=" + msgCode + ", message=" + message + "]";
    }

}
