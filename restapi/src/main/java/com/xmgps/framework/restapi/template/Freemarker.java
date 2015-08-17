package com.xmgps.framework.restapi.template;

import com.xmgps.framework.restapi.parser.MetaData;
import com.xmgps.framework.restapi.parser.SQLModel;
import com.xmgps.framework.restapi.parser.SQLModelManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URI;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by gps_hwb on 2015/8/19.
 */
public class Freemarker {

    private Configuration config;            //模版配置对象

    private String descPath = "com/xmgps/framework/restapi";

    public void init() throws Exception {
        //初始化FreeMarker配置
        //创建一个Configuration实例
        config = new Configuration();
        //设置FreeMarker的模版文件夹位置
        config.setDirectoryForTemplateLoading(new File(URI.create(getPath() + "freemarker")));
    }

    public static String getPath(){
        //old URI.create("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/"
        return "file://"+Thread.currentThread().getClass().getResource("/").getPath();
    }

    public boolean generateSrc(SQLModel model,Map<String,String> input){
        ClassAttribute attr = new ClassAttribute(input);
        boolean a = generateRequest(model.inputParams, attr);
        boolean b = generateResponse(model.outputParams, attr);
        boolean c = generateQuery(attr,model);
        boolean d = generateClass(attr);
        return a&&b&&c&&d;
    }

    private boolean generateQuery(ClassAttribute attr, SQLModel model){

        Map<String,Object> root = new HashMap<String, Object>();
        root.put("attrs", attr);
        root.put("sqlmodel", model);
        try {
            Template template = config.getTemplate("/Rest_Api_Query.ftl");
            File file = new File(URI.create(getPath() + attr.packageName.replaceAll("\\.","/")+"/"+
                    attr.getClassName().replaceFirst(attr.getClassName().substring(0, 1), attr.getClassName().substring(0, 1).toUpperCase())+"Query.java"));
            if(!file.exists() || !file.getParentFile().exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter out = new FileWriter(file);
            template.process(root, out);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return false;

    }

    private boolean generateResponse(List<MetaData> params,ClassAttribute attr){
        List<BeanAttribute> beanAttr = params.stream().map(metaData -> new BeanAttribute(metaData.getName(), metaData.getMetaClass())).collect(Collectors.toList());

        Map<String,Object> root = new HashMap<String, Object>();
        root.put("attrs", attr);
        root.put("bean", beanAttr);
        try {
            Template template = config.getTemplate("/Rest_Bean_Response.ftl");
            File file = new File(URI.create(getPath() + attr.packageName.replaceAll("\\.","/")+"/"+
                    attr.getClassName().replaceFirst(attr.getClassName().substring(0, 1), attr.getClassName().substring(0, 1).toUpperCase())+"Response.java"));
            if(!file.exists() || !file.getParentFile().exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter out = new FileWriter(file);
            template.process(root, out);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return false;

    }

    private boolean generateRequest(List<MetaData> params,ClassAttribute attr){
        List<BeanAttribute> beanAttr = params.stream().map(metaData -> new BeanAttribute(metaData.getName(), metaData.getMetaClass())).collect(Collectors.toList());

        Map<String,Object> root = new HashMap<String, Object>();
        root.put("attrs", attr);
        root.put("bean", beanAttr);
        try {
            Template template = config.getTemplate("/Rest_Bean_Request.ftl");
            File file = new File(URI.create(getPath() + attr.packageName.replaceAll("\\.", "/")+"/"+
                    attr.getClassName().replaceFirst(attr.getClassName().substring(0, 1), attr.getClassName().substring(0, 1).toUpperCase())+"Request.java"));
            if(!file.exists() || !file.getParentFile().exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter out = new FileWriter(file);
            template.process(root, out);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return false;

    }

    private boolean generateClass(ClassAttribute attr){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, URI.create(getPath() + attr.packageName.replaceAll("\\.", "/")+"/"+
                attr.getClassName().replaceFirst(attr.getClassName().substring(0, 1), attr.getClassName().substring(0, 1).toUpperCase())+"Request.java").getPath());
        compiler.run(null, null, null, URI.create(getPath() + attr.packageName.replaceAll("\\.", "/")+"/"+
                attr.getClassName().replaceFirst(attr.getClassName().substring(0, 1), attr.getClassName().substring(0, 1).toUpperCase())+"Response.java").getPath());
        compiler.run(null, null, null, URI.create(getPath() + attr.packageName.replaceAll("\\.","/")+"/"+
                attr.getClassName().replaceFirst(attr.getClassName().substring(0, 1), attr.getClassName().substring(0, 1).toUpperCase())+"Query.java").getPath());
        return true;
    }

    public static void main(String[] args) throws Exception{

        SQLModel sqlModel = new SQLModel();
        sqlModel.setFormateSQL("select * from TAB_CAR_INFO t WHERE t.VEHICLE_COLOR={#VEHICLE_COLOR,type=String,test=2} and t.VEHICLE_NO!={#VEHICLE_NO,type=String,test='123'}");
        sqlModel.parserInputParams();
        sqlModel.parserOutPutParams(SQLModelManager.getConnection());

        Map<String,String> input = new HashMap<>();

        Freemarker freemarker = new Freemarker();
        freemarker.init();
        freemarker.generateSrc(sqlModel, input);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "D:/Herrfe/Myjob/Code/MySVN/36.厦门市交通信息共享服务平台/源代码/trunk/restapi/TestQuery.java");
        System.out.println(result);

    }

}
