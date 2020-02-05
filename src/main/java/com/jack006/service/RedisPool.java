package com.jack006.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/4 14:45
 * */
@Service
@Slf4j
public class RedisPool {

    @Resource
    private ShardedJedisPool shardedJedisPool;


    public ShardedJedis instance() {
        return shardedJedisPool.getResource();
    }

    public void safeClose(ShardedJedis shardedJedis) {
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        } catch (Exception e) {
            log.error("retrun redis resource exception", e);
        }
    }
}
