package com.wrdao.springboot.configuration;

import com.wrdao.springboot.common.constant.RedisCacheTransfer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.getPoolConfig().setMaxIdle(8);
        jedisConnectionFactory.getPoolConfig().setMaxTotal(8);
        jedisConnectionFactory.getPoolConfig().setMaxWaitMillis(-1);
        jedisConnectionFactory.getPoolConfig().setTestOnBorrow(true);

        jedisConnectionFactory.getStandaloneConfiguration().setHostName("127.0.0.1");
        jedisConnectionFactory.getStandaloneConfiguration().setPort(6379);
        jedisConnectionFactory.getStandaloneConfiguration().setPassword(RedisPassword.of("123456"));

        /*jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setPassword("123456");*/

        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());

        return stringRedisTemplate;
    }

    @Bean
    public RedisCacheTransfer getRedisCacheTransfer() {
        RedisCacheTransfer redisCacheTransfer = new RedisCacheTransfer();

        redisCacheTransfer.setJedisConnectionFactory(jedisConnectionFactory());

        return redisCacheTransfer;
    }

}
