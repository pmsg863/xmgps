package com.xmgps.yfzx;

import com.xmgps.yfzx.redis.JedisPool;
import com.xmgps.yfzx.redis.RedisClient;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.logging.Level;

/**
 * Created by huangwb on 7/7/2016.
 */
public class RedisClientTest {
    public void testRedisClientTest(){
        RedisClient redisClient = new RedisClient();
        redisClient.setRedisServer("10.50.1.41");
        redisClient.init();

        JedisPool jedisPool = redisClient.getJedisPool();
        Jedis resource = jedisPool.getResource();
        try {
            Set<String> keys = resource.keys("*");
            System.out.println(keys);
        } finally {
            jedisPool.returnResource(resource);
        }

    }

    public static void main(String[] args) {
        RedisClientTest client = new RedisClientTest();
        client.testRedisClientTest();
    }
}
