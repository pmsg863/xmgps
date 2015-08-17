package com.xmgps.framework.restapi.parser;

import com.xmgps.framework.restapi.template.ClassAttribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gps_hwb on 2015/8/25.
 */
public class ConfigBean {
    private  String dirverClassName = "oracle.jdbc.driver.OracleDriver";
    private  String url = "jdbc:oracle:thin:@10.50.1.111:1521:JTYDB";
    private  String user = "sjgtest";
    private  String password = "gpsserver";

    private  String hostname = "127.0.0.1";
    private  int port = 8080;
    private  String basePath = "cob";

    private  Map<String, SQLModel> registerModel = new ConcurrentHashMap<>();

    private  Map<String, ClassAttribute> attrModel = new ConcurrentHashMap<>();

    public String getDirverClassName() {
        return dirverClassName;
    }

    public ConfigBean setDirverClassName(String dirverClassName) {
        this.dirverClassName = dirverClassName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ConfigBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ConfigBean setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ConfigBean setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public ConfigBean setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public int getPort() {
        return port;
    }

    public ConfigBean setPort(int port) {
        this.port = port;
        return this;
    }

    public String getBasePath() {
        return basePath;
    }

    public ConfigBean setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public Map<String, SQLModel> getRegisterModel() {
        return registerModel;
    }

    public ConfigBean setRegisterModel(Map<String, SQLModel> registerModel) {
        this.registerModel = registerModel;
        return this;
    }

    public Map<String, ClassAttribute> getAttrModel() {
        return attrModel;
    }

    public ConfigBean setAttrModel(Map<String, ClassAttribute> attrModel) {
        this.attrModel = attrModel;
        return this;
    }
}
