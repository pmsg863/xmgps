package com.xmgps.framework.restapi.parser;

import com.sun.net.httpserver.HttpServer;
import com.xmgps.framework.restapi.template.ClassAttribute;
import com.xmgps.framework.restapi.template.Freemarker;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by gps_hwb on 2015/8/19.
 */
public class SQLModelManager {
    private static ConfigBean config;

    static JsonGenerator jsonGenerator = null;
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        config = readConfig();
        init();
    }


    public static ConfigBean readConfig() {
        try {
            return objectMapper.readValue(new File(URI.create(Freemarker.getPath() + "system.config")), ConfigBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConfigBean getConfig() {
        return config;
    }

    public static boolean writeConfig() {
        try {
            if (jsonGenerator == null)
                jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(new File(URI.create(Freemarker.getPath() + "system.config")), JsonEncoding.UTF8);
            jsonGenerator.writeObject(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void init() {
        try {
            Class.forName(config.getDirverClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean deploy() {
        InetSocketAddress addr = new InetSocketAddress(config.getHostname(), config.getPort());
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(addr, 0);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        List<String> resources = config.getAttrModel().values().stream().map(clsAttr->clsAttr.getPackageName()+"."
                +clsAttr.getClassName().replaceFirst(clsAttr.getClassName().substring(0, 1), clsAttr.getClassName().substring(0, 1).toUpperCase())+"Query").collect(Collectors.toList());
        HttpContextBuilder contextBuilder = new HttpContextBuilder();
        contextBuilder.setPath(config.getBasePath());
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

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public SQLModel buildSQL(String sql) throws SQLException {
        SQLModel sqlModel = new SQLModel().setFormateSQL(sql);
        sqlModel.parserInputParams();
        sqlModel.parserOutPutParams(getConnection());
        return sqlModel;
    }

    public static List<Map<String, Object>> runSQL(String sql, List<String> input) {
        Connection conn = getConnection();
        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        try {
            return qRunner.query(conn, sql, new MapListHandler(), input.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return new ArrayList<Map<String, Object>>();

    }

    public static List<Object[]> runSQLByList(String sql, List<String> input) {
        Connection conn = getConnection();
        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        try {
            return qRunner.query(conn, sql, new ArrayListHandler(), input.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return new ArrayList<>();

    }

    public static List<Object[]> updateSQLByList(String sql, List<String> input) {
        Connection conn = getConnection();
        List<Object[]> result = new ArrayList<>();
        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        try {
            result.add(new Object[]{qRunner.update(conn, sql, input.toArray())});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return result;
    }

    public static boolean registerModel(String path, SQLModel model, ClassAttribute attr) {
        config.getRegisterModel().put(path, model);
        config.getAttrModel().put(path, attr);
        return true;
    }

    public static SQLModel getRegisterModel(String path) {
        return config.getRegisterModel().get(path);
    }

    public static List<Object[]> getRegisterModels() {
        return config.getRegisterModel().entrySet().stream().map(entry -> {
            Object[] item = new Object[3];
            item[0] = entry.getKey();
            item[1] = entry.getValue().type;
            item[2] = entry.getValue().generateSQL();
            return item;
        }).collect(Collectors.toList());
    }

    public static Object[] getFieldArray(Object input) {
        List result = new ArrayList<>();
        Class<?> aClass = input.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(input);
                result.add(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                result.add(null);
            }
            field.setAccessible(false);
        }
        return result.toArray();
    }
}
