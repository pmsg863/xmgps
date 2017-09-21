package com.xmgps.yfzx.scan;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangwb on 7/7/2016.
 */
public class FolderScan {
    public static String basepath = "C:\\Users\\huangwb\\deploy\\";

    List<String> fieldName = new ArrayList<>();

    public void init(){
        try {
            String info = FileUtils.readFileToString(new File(basepath + "TAB_PHOTOS.sql"));
            Pattern pattern = Pattern.compile("\".*\"");
            Matcher matcher = pattern.matcher(info);
            while(matcher.find()){
                fieldName.add(matcher.group(0).replaceAll("\"",""));
            }
            fieldName.remove(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> takeTasks(){
        List<Task> tasks = new CopyOnWriteArrayList<>();
        try {
            SimpleDateFormat srcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat destFormat = new SimpleDateFormat("yyMMddHHmmss");
            String info = FileUtils.readFileToString(new File(basepath + "TAB_PHOTOS_DATA_TABLE.ldr"));
            String[] list = info.split("\\{EOL\\}");
            for(String value:list){
                Task task = new Task();
                String[] fields = value.split("\\|");
                for(int i =0;i<fields.length;i++){
                    switch (fieldName.get(i)){
                        case "PHOTO":
                            task.fileName = fields[i].trim();
                            break;
                        case "GPSTIME":
                        case "TAKEBEGINTIME":
                        case "TAKEENDTIME":
                            try {
                                String format = destFormat.format(srcFormat.parse(fields[i].trim().replaceAll("\"","")));
                                task.meta.put(fieldName.get(i),format);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            task.meta.put(fieldName.get(i),fields[i].replaceAll("\"",""));
                    }
                }
                task.key = String.format("%12s_%11s_%1s",task.meta.get("GPSTIME"),task.meta.get("DEV_ID"),task.meta.get("CAMERANO"));
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }



    public static void main(String[] args) {
        FolderScan scan = new FolderScan();

        scan.init();
        scan.takeTasks();

        Collection<File> files = FileUtils.listFiles(new File(scan.basepath), null, false);
        files.stream().forEach(item->System.out.println(item.getName()));
    }
}
