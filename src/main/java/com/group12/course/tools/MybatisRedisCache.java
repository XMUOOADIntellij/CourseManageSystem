package com.group12.course.tools;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 工具类
 * 用于实现 mybatis cache接口
 *
 * @author Xu Gang
 * @date 2018年12月14日
 * */
public class MybatisRedisCache implements Cache {

    private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    private Jedis redisClient = createRedis();

    /**
     * 默认两个小时后会在 redis 中被删除
     * 最终上线后可以去除这个限制
     */
    private final int expireTime = 60*60*2;

    /**
     * The ReadWriteLock.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {

        return Integer.valueOf(redisClient.dbSize().toString());
    }

    @Override
    public void putObject(Object key, Object value) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
        redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));

        // 设置过期时间
        redisClient.expire(SerializeUtil.serialize(key.toString()),expireTime);
    }

    @Override
    public Object getObject(Object key) {
        Object value = SerializeUtil.unserialize(redisClient.get(SerializeUtil.serialize(key.toString())));
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>getObject:" + key + "=" + value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);
    }

    @Override
    public void clear() {
        redisClient.flushDB();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    protected static Jedis createRedis() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "47.107.81.51",6379,2000,"123456");
        return pool.getResource();
    }
}

