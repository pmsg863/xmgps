package com.xmgps.yfzx.lab;

import com.xmgps.yun.basetype.HexStringBinary;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangwb on 10/26/2016.
 */
public class HttpAreaClient {

    public static Logger logger = LoggerFactory.getLogger(HttpAreaClient.class);

    public static List<String> getAiXiaoqu() throws IOException, JSONException, DocumentException {


        HttpClient client = new HttpClient();
        Set<String> result = new HashSet<>();

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        for (int i = 1; i <= 20; i++) {
            String uri = "http://www.aibang.com/xiamen/louyuxiaoqu/p"+i+"/";
            HttpMethod method = new GetMethod(uri);
            //使用POST方法
            client.executeMethod(method);

            //打印服务器返回的状态
            System.out.println(method.getStatusLine());
            //打印返回的信息

            byte[] responseBody = method.getResponseBody();
            String utf8 = new String(responseBody, "utf8");
            Pattern pattern = Pattern.compile("1\\|\\|bid:.+</a>");
            Matcher matcher = pattern.matcher(utf8);
            while (matcher.find()) {
                result.add(matcher.group().split("<")[0].split(">")[1]);
            }
        }


        return new ArrayList<>(result);
    }

    public static List<String> getXiaoqu() throws IOException, JSONException, DocumentException {


        HttpClient client = new HttpClient();
        List<String> result = new ArrayList<>();

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        for (int i = 1; i <= 20; i++) {
            String uri = "http://www.chachaba.com/xiamen/type/xiaoqu1_" + i + ".html";
            HttpMethod method = new GetMethod(uri);
            //使用POST方法
            client.executeMethod(method);

            //打印服务器返回的状态
            System.out.println(method.getStatusLine());
            //打印返回的信息

            byte[] responseBody = method.getResponseBody();
            String utf8 = new String(responseBody, "utf8");
            Pattern pattern = Pattern.compile("screendingwei\\(.+\"\\);'><div");
            Matcher matcher = pattern.matcher(utf8.replaceAll("screendingwei", "\r\nscreendingwei"));
            while (matcher.find()) {
                result.add(matcher.group().split("\"")[5]);
            }
        }


        return result;
    }

    public static String getUID(String name) throws IOException, JSONException {
        String uri = "http://map.baidu.com/su?wd=%" + HexStringBinary.ByteArrayToHexString(name.getBytes()).replace(" ", "%") + "&cid=289&type=0&pc_ver=2";

        HttpClient client = new HttpClient();

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method = new GetMethod(uri);
        //使用POST方法
        client.executeMethod(method);

        //打印服务器返回的状态
        System.out.println(method.getStatusLine());
        //打印返回的信息

        byte[] responseBody = method.getResponseBody();
        JSONObject json = new JSONObject(new String(responseBody, "utf8"));
        //释放连接
        method.releaseConnection();
        if (json.has("s") && json.getJSONArray("s").length() > 0) {
            String[] split = json.getJSONArray("s").getString(0).replace('$', ' ').split(" ");
            if (split[0].equals("厦门市"))
                return split[split.length - 1];
            else
                return "";
        } else
            return "";
    }

    public static String getPceva(String uid) throws IOException, JSONException {
        if (uid == null || uid.length() == 0)
            return "";

        String uri = "http://map.baidu.com/?pcevaname=pc4.1&qt=ext&uid=" + uid + "&ext_ver=new&l=12";

        HttpClient client = new HttpClient();

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method = new GetMethod(uri);
        //使用POST方法
        client.executeMethod(method);

        //打印服务器返回的状态
        System.out.println(method.getStatusLine());
        //打印返回的信息
        byte[] responseBody = method.getResponseBody();
        method.releaseConnection();
        JSONObject json = new JSONObject(new String(responseBody, "utf8"));
        String geoString = json.getJSONObject("content").getString("geo");
        String[] split = geoString.split("1-");
        if (split.length > 1) {
            String[] split1 = split[1].substring(0, split[1].length() - 2).split(",");
            StringBuilder format = new StringBuilder();
            for (int i = 0; i < split1.length; i++) {
                format.append(split1[i]);
                if (i % 2 == 0)
                    format.append(",");
                else
                    format.append(";");
            }
            return getCoords(format.toString().substring(0, format.length() - 2));
        } else
            return "";

    }

    public static String getCoords(String latlons) throws IOException {
        String uri = "http://api.map.baidu.com/geoconv/v1/?coords=" + latlons + "&from=6&to=5&ak=bKMgAxSS2dCRi1WcHXCv5Q1T";

        HttpClient client = new HttpClient();

        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method = new GetMethod(uri);
        //使用POST方法
        client.executeMethod(method);

        //打印返回的信息
        byte[] responseBody = method.getResponseBody();
        //释放连接
        method.releaseConnection();
        return new String(responseBody, "utf8");

    }


    public static void main(String[] args) throws IOException, JSONException, DocumentException {
        List<String> xiaoqu = getAiXiaoqu();
        int i = 1;
        Set<String> area = new HashSet<>();

        logger.info(xiaoqu.size()+" "+xiaoqu.toString());
        try {
            for (String xa : xiaoqu) {
                logger.debug(xa + "  " + (i++) + "/" + xiaoqu.size());
                String pceva = "";
                try {
                    pceva = getPceva(getUID(xa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (pceva.length() > 0) {
                    area.add(xa + pceva);
                    logger.info(String.format("%20s        ", xa) + pceva);
                }
            }
        } finally {
            logger.info(area.size()+" "+area.toString());
        }

        /*getUID("软件园二期");

        getPceva("d1469ee75507241f8433041c");

        System.out.println(getPceva(getUID("海韵园")));
*/
    }

}
