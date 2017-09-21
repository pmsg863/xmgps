package com.xmgps.yfzx.api;

import com.sun.net.httpserver.HttpServer;
import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by huangwb on 2015/7/13.
 */
public class RestServer {

    String hostname = "127.0.0.1";
    int port = 8080;
    String basePath = "cob";

    List<String> resources = new ArrayList<>();

    private static RestServer _instance = null;
    static{
        _instance = new RestServer();
    }
    /**
     * 获取系统配置单例
     * @return 系统配置单例
     */
    public static RestServer getInstance(){
        return _instance;
    }
    /**
     * 私有构造函数（单例模式）
     */
    private RestServer() {}

    public boolean initialize() {
        InetSocketAddress addr = new InetSocketAddress(hostname, port);
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(addr, 0);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        HttpContextBuilder contextBuilder = new HttpContextBuilder();
        contextBuilder.setPath(basePath);
        contextBuilder.getDeployment().getActualResourceClasses().addAll(
                resources.stream().map(className -> {
                    Class item = null;
                    try {
                        item = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return item;
                }).collect(Collectors.toList()));
        contextBuilder.bind(httpServer);
        httpServer.start();
        return true;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
