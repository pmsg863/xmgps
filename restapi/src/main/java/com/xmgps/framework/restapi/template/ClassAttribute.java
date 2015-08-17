package com.xmgps.framework.restapi.template;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by gps_hwb on 2015/8/20.
 */
public class ClassAttribute {
    String packageName = "com.xmgps.framework.restapi";
    String className ="test";

    String apiType ="GET";
    String path = "test";
    String subPath ="query";
    String apiName = "testQuery";

    Map<String,String> input;

    public ClassAttribute() {
    }

    public ClassAttribute(Map<String, String> input) {
        this.input = input;

        for(Field field:ClassAttribute.class.getDeclaredFields()){
            String name = field.getName();
            if(input.containsKey(name)){
                try {
                    field.setAccessible(true);
                    field.set(this, input.get(name));
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String getPackageName() {
        return packageName;
    }

    public ClassAttribute setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ClassAttribute setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getApiType() {
        return apiType;
    }

    public ClassAttribute setApiType(String apiType) {
        this.apiType = apiType;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ClassAttribute setPath(String path) {
        this.path = path;
        return this;
    }

    public String getSubPath() {
        return subPath;
    }

    public ClassAttribute setSubPath(String subPath) {
        this.subPath = subPath;
        return this;
    }

    public String getApiName() {
        return apiName;
    }

    public ClassAttribute setApiName(String apiName) {
        this.apiName = apiName;
        return this;
    }

    public Map<String, String> getInput() {
        return input;
    }

    public ClassAttribute setInput(Map<String, String> input) {
        this.input = input;
        return this;
    }
}
