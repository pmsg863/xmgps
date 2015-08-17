package com.xmgps.framework.restapi.template;

import java.util.Map;

/**
 * Created by gps_hwb on 2015/8/19.
 */
public class BeanAttribute {
    String fieldName;
    String fieldClassName;

    public BeanAttribute(String fieldName, String fieldClassName) {
        this.fieldName = fieldName;
        this.fieldClassName = fieldClassName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public BeanAttribute setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldClassName() {
        return fieldClassName;
    }

    public BeanAttribute setFieldClassName(String fieldClassName) {
        this.fieldClassName = fieldClassName;
        return this;
    }
}
