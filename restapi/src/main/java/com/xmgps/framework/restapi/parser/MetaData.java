package com.xmgps.framework.restapi.parser;

/**
 * Created by gps_hwb on 2015/8/19.
 */
public class MetaData {

    private String name;
    private String metaClass;
    private String defaultValue;

    public MetaData setName(String name) {
        this.name = name;
        return this;
    }

    public MetaData setMetaClass(String className) {
        this.metaClass = className;
        return this;
    }

    public MetaData setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getMetaClass() {
        return metaClass;
    }
}
