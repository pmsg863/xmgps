package com.xmgps.yfzx.redis;

import com.xmgps.yfzx.fastdfs.FastdfsClient;
import com.xmgps.yfzx.fastdfs.FastdfsClientConfig;
import com.xmgps.yfzx.fastdfs.FastdfsClientImpl;
import com.xmgps.yfzx.fastdfs.client.StorageClient;
import com.xmgps.yfzx.fastdfs.client.StorageClientFactory;
import com.xmgps.yfzx.fastdfs.client.TrackerClient;
import com.xmgps.yfzx.fastdfs.client.TrackerClientFactory;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

import java.util.List;

public class RedisClientFactory {

    private static volatile RedisClient redisClient;


    public static RedisClient getFastdfsClient(String configFileName) throws ConfigurationException {
        Configuration config = new PropertiesConfiguration(configFileName);

        return getFastdfsClient(config);
    }

    public static RedisClient getFastdfsClient(Configuration config) {
        if (redisClient == null) {
            synchronized (RedisClient.class) {
                if (redisClient == null) {
                    redisClient = new RedisClient();
                    redisClient.setRedisServer(config.getString("redis_ip"));
                    redisClient.init();
                }
            }
        }
        return redisClient;
    }

}
