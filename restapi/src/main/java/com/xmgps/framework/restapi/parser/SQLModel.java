package com.xmgps.framework.restapi.parser;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gps_hwb on 2015/8/19.
 */
public class SQLModel {
    public String type;
    public String formateSQL;
    public List<MetaData> inputParams = new ArrayList<>();
    public List<MetaData> outputParams = new ArrayList<>();

    public SQLModel setFormateSQL(String sql) {
        this.formateSQL = sql;
        if( sql.indexOf("INSERT")>=0||sql.indexOf("INSERT".toLowerCase())>=0 )
            type = "INSERT";
        else if( sql.indexOf("UPDATE")>=0||sql.indexOf("UPDATE".toLowerCase())>=0 )
            type = "UPDATE";
        else if( sql.indexOf("DELETE")>=0||sql.indexOf("DELETE".toLowerCase())>=0 )
            type = "DELETE";
        else if( sql.indexOf("CALL")>=0||sql.indexOf("CALL".toLowerCase())>=0 )
            type = "PROC";
        else
            type = "SELECT";
        return this;
    }

    public boolean parserInputParams() {
        Pattern pattern = Pattern.compile("\\{#(.*?)\\}");
        Matcher matcher = pattern.matcher(formateSQL);

        while (matcher.find()) {
            String param = String.valueOf(matcher.group(1));
            String[] split = param.split(",");
            inputParams.add(new MetaData().setName(split[0]).setMetaClass(split[1].substring(5)).setDefaultValue(split[2].substring(5)));
        }
        return true;
    }

    public boolean parserOutPutParams(Connection connection) throws SQLException {
        PreparedStatement statament = connection
                .prepareStatement(generateTestSQL());
        statament.execute();
        ResultSetMetaData meta = statament.getMetaData();
        if (meta != null) {//update insert Ã»ÓÐMetaData
            int cols = meta.getColumnCount();
            for (int i = 1; i <= cols; i++) {
                String name = meta.getColumnName(i);
                String columnClass = meta.getColumnClassName(i);
                outputParams.add(new MetaData().setName(name).setMetaClass(columnClass));
            }
        }
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(statament);
        return true;
    }

    public String generateTestSQL() {
        String testSQL = formateSQL;

        Pattern pattern = Pattern.compile("\\{#(.*?)\\}");
        Matcher matcher = pattern.matcher(formateSQL);

        while (matcher.find()) {
            String param = String.valueOf(matcher.group(1));
            testSQL = testSQL.replaceAll("\\{#" + param + "\\}", param.split(",")[2].substring(5));
        }
        return testSQL;
    }

    public String generateSQL() {
        String testSQL = formateSQL;

        Pattern pattern = Pattern.compile("\\{#(.*?)\\}");
        Matcher matcher = pattern.matcher(formateSQL);

        while (matcher.find()) {
            String param = String.valueOf(matcher.group(1));
            testSQL = testSQL.replaceAll("\\{#" + param + "\\}", "?");
        }
        return testSQL;
    }

    public List<MetaData> getInputParams() {
        return inputParams;
    }

    public List<MetaData> getOutputParams() {
        return outputParams;
    }

    public String getType() {
        return type;
    }

    public SQLModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getFormateSQL() {
        return formateSQL;
    }

    public static void main(String[] args) {

        SQLModel sqlModel = new SQLModel();
        sqlModel.setFormateSQL("select * from TAB_CAR_INFO t WHERE t.VEHICLE_COLOR={#VEHICLE_COLOR,type=string,test=2} and t.NAME={#NAME,type=string,test=2};");

        System.out.println(sqlModel.generateSQL());
        System.out.println(sqlModel.generateTestSQL());
        System.out.println(sqlModel.formateSQL);
        sqlModel.parserInputParams();

    }
}
