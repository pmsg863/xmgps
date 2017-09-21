package com.xmgps.yfzx.api;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 请求消息基类，包含流水号、生成时间、是否加密等信息
 *
 * @author YYH
 */
public class RequestBase {
    /**
     * 消息流水号
     */
    protected Integer serial;
    /**
     * 消息生成日期，格式YYYY-MM-DD
     */
    protected String date;
    /**
     * 消息生成时间，格式hh:mm:ss
     */
    protected String time;
    /**
     * 消息内容是否加密，true – 加密；false – 不加密；
     */
    protected boolean isEncrypted;
    /**
     * 加密密钥，不加密值时为空
     */
    protected String encryptKey;

    /**
     * 获取消息流水号
     */
    public Integer getSerial() {
        return serial;
    }

    /**
     * 设置消息流水号
     */
    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    /**
     * 获取消息生成日期，格式YYYY-MM-DD
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置消息生成日期，格式YYYY-MM-DD
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取消息生成时间，格式hh:mm:ss
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置消息生成时间，格式hh:mm:ss
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取消息内容是否加密，true – 加密；false – 不加密；
     */
    public Boolean getIsEncrypted() {
        return isEncrypted;
    }

    /**
     * 设置消息内容是否加密，true – 加密；false – 不加密；
     */
    public void setIsEncrypted(Boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    /**
     * 获取加密密钥，不加密值时为空
     */
    public String getEncryptKey() {
        return encryptKey;
    }

    /**
     * 设置加密密钥，不加密值时为空
     */
    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

}
