package com.mod.loan.config.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class RedisMapper {

	private final static String REDIS_PRE = "mod:merchant:";
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	}
	@Autowired
	StringRedisTemplate redisTemplate;

	/**
	 * 获取锁
	 * 
	 * @param key
	 * @param exp
	 * @return
	 */
	public Boolean getLock(String key, long exp) {
		Boolean lock = redisTemplate.opsForValue().setIfAbsent(REDIS_PRE + key, key);
		if (lock) {
			redisTemplate.expire(REDIS_PRE + key, exp, TimeUnit.SECONDS);
		}
		return lock;
	}

	public long increment(String key, long delta, long exp) {
		Long current = redisTemplate.opsForValue().increment(REDIS_PRE + key, delta);
		redisTemplate.expire(REDIS_PRE + key, exp, TimeUnit.SECONDS);
		return current;
	}

	public void expire(String key, long exp) {
		redisTemplate.expire(REDIS_PRE + key, exp, TimeUnit.SECONDS);
	}

	public void remove(String key) {
		redisTemplate.delete(REDIS_PRE + key);
	}

	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(REDIS_PRE + key, serializer(value));
	}

	public void set(String key, Object value, long exp) {
		redisTemplate.opsForValue().set(REDIS_PRE + key, serializer(value), exp, TimeUnit.SECONDS);
	}

	public String get(String key) {
		return redisTemplate.opsForValue().get(REDIS_PRE + key);
	}

	public void lpush(String key, Object value) {
		redisTemplate.opsForList().leftPush(REDIS_PRE + key, serializer(value));
	}

	public String lpop(String key) {
		return redisTemplate.opsForList().leftPop(REDIS_PRE + key);
	}

	public <T> T lpop(String key, TypeReference<T> t) {
		return deserializer(redisTemplate.opsForList().leftPop(REDIS_PRE + key), t);
	}

	public <T> T get(String key, TypeReference<T> t) {
		return deserializer(redisTemplate.opsForValue().get(REDIS_PRE + key), t);
	}

	private String serializer(Object value) {
		try {
			if (value instanceof String) {
				return (String) value;
			}
			return mapper.writeValueAsString(value);
		} catch (Exception ex) {
			throw new RuntimeException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}

	private <T> T deserializer(String value, TypeReference<T> t) {
		try {
			return value == null ? null : mapper.readValue(value, t);
		} catch (Exception e) {
			throw new RuntimeException("Could not write JSON: " + e.getMessage(), e);
		}
	}

}
