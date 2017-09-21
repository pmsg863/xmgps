package com.xmgps.yfzx.api;

import com.xmgps.yfzx.util.Converter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * 应答消息基类，包含流水号、生成时间、是否加密、应答结果等信息，另外还包含应答结果。
 *
 * @author YYH
 */
public class ResponseBase {
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
     * 应答结果，0 - 表示正确返回，其他参见应答消息返回码定义
     */
    private int result;
    /**
     * 原因
     */
    private String msg;

    /**
     * 构建应答消息，消息时间为当前时间，默认不加密
     */
    public ResponseBase() {
        Date dt = new Date();
        this.date = Converter.getDateString(dt);
        this.time = Converter.getTimeString(dt);
        this.isEncrypted = false;
        this.encryptKey = "";
    }

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

    /**
     * 获取应答结果，0 - 表示正确返回，其他参见应答消息返回码定义
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置应答结果，0 - 表示正确返回，其他参见应答消息返回码定义
     */
    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 生成应答消息
     * @param serial 请求消息流水号
     * @param result 应答消息结果
     * @param type   应答消息的HTTP数据类型
     * @return 应答消息
     */
    public static Response buildResponse(String serial, int result, MediaType type) {
        int s = 0;
        if (serial != null && serial.length() > 0) {
            try {
                s = Integer.parseInt(serial);
            } catch (NumberFormatException e) {
            }
        }
        ResponseBase responseBase = new ResponseBase();
        responseBase.setSerial(s);
        responseBase.setResult(result);
        return Response.ok(responseBase, type).build();
    }
}
