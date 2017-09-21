package com.xmgps.yfzx.api;

/**
 * 应答消息返回码
 *
 * @author YYH
 */
public enum ResponseResult {
    OK(0, "正确"),
    UPDATE_FAILED_WRONG(1005, "密码错误"),
    ADD_FAILED_REPETITION(1006, "登录ID重复"),
    LOGIN_FAILED_NOT_EXIST(1007, "用户名不存在"),
    LOGIN_FAILED_ERR_PASSWORD(1008, "密钥不正确"),
    LOGIN_FAILED_STOPPED(1009, "该用户被禁用"),
    LOGIN_FAILED(1010, "接入码或接入密钥不正确"),
    TOKEN_INVALID(1011, "没有提供访问令牌或者访问令牌不正确"),
    TOKEN_EXPIRED(1012, "访问令牌过期，需要重新获取"),
    IP_INVALID(1013, "请求方IP地址未授权"),
    FORBIDDEN(1014, "无API访问权限"),
    NOT_AUTHORIZED(1014, "无API访问权限"),
    NOT_FOUND(1020, "API接口不存在"),
    INTERNAL_SERVER_ERROR(1021, "服务器发生错误"),
    BAD_REQUEST(1030, "数据格式不合法，数据未遵循接口指定的协议或格式"),
    NOT_SUPPORTED(1031, "HTTP消息的多媒体类型不被支持");

    /**
     * 应答结果
     */
    private int result;
    /**
     * 应答结果描述
     */
    private String reason;

    /**
     * 构造应答结果
     */
    ResponseResult(int result, String reason) {
        this.result = result;
        this.reason = reason;
    }

    /**
     * 获取应答结果
     */
    public int getResult() {
        return result;
    }

    /**
     * 设置应答结果
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * 获取应答结果描述
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置应答结果描述
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
