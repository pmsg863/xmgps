package com.xmgps.framework.restapi;

import java.net.URI;
import java.net.URL;

/**
 * Created by gps_hwb on 2015/8/25.
 */
public class TestApi {

    public static void main(String[] args) {
        URI uri = URI.create("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "freemarker");
        System.out.println(uri.getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("freemarker").getPath());
        System.out.println(Thread.currentThread().getClass().getResource("/").getPath());
    }
}
