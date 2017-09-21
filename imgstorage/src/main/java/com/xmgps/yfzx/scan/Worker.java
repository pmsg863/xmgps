package com.xmgps.yfzx.scan;

import com.xmgps.yfzx.fastdfs.FastdfsClient;
import com.xmgps.yfzx.fastdfs.FastdfsClientFactory;
import com.xmgps.yfzx.redis.JedisPool;
import com.xmgps.yfzx.redis.RedisClient;
import com.xmgps.yfzx.redis.RedisClientFactory;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.io.FileUtils;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by huangwb on 7/8/2016.
 */
public class Worker implements Runnable {

    Queue<Task> tasks = new LinkedBlockingQueue<>();

    Queue<Task> faileds = new LinkedBlockingQueue<>();

    RedisClient rdsClient;
    FastdfsClient dfsClient;

    Map<String,String> filemap = new HashMap<>();

    public void init() throws ConfigurationException {
        dfsClient = FastdfsClientFactory.getFastdfsClient("FastdfsClient.properties");
        rdsClient = RedisClientFactory.getFastdfsClient("RedisClient.properties");

        Collection<File> files = FileUtils.listFiles(new File(FolderScan.basepath), null, false);
        files.stream().forEach(item->filemap.put(item.getName(),item.getName()));
    }

    public void takeTasks() {
        FolderScan scan = new FolderScan();
        scan.init();
        List<Task> lists = scan.takeTasks();
        tasks.addAll(lists);
    }

    @Override
    public void run() {
        Task poll = tasks.poll();
        if (poll != null && filemap.containsKey(poll.fileName)) {

            File file = new File(FolderScan.basepath + poll.fileName);
            JedisPool jedisPool = rdsClient.getJedisPool();
            Jedis resource = jedisPool.getResource();
            String fileId = null;
            try {
                fileId = dfsClient.upload(file);
                String url = dfsClient.getUrl(fileId);
                boolean result = dfsClient.setMeta(fileId, poll.meta);

                resource.hset(poll.key, "url", url);
                resource.hset(poll.key, "fileid", fileId);
                resource.hset(poll.key, "filename", poll.fileName);

            } catch (Exception e) {
                faileds.add(poll);
                resource.del(poll.key);
                try {
                    dfsClient.delete(fileId);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } finally {
                jedisPool.returnResource(resource);
            }
            //成功，删除文件 todo
            FileUtils.deleteQuietly(file);

        }

    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        try {
            worker.init();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        worker.takeTasks();

        long starttime = System.currentTimeMillis();
        while (worker.tasks.size() > 0) {
            System.out.println(worker.tasks.size());
            worker.run();
        }
        System.out.println("hours of use:"+ (System.currentTimeMillis()-starttime) );

    }
}
