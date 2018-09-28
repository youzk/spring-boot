package com.example.common;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	

	@Autowired 
	private RedisTemplate redisTemplate;
	
	
	
	/**
	 * 设置缓存
	 * com.jianyan.config 
	 * 方法名：set
	 * 创建人：何雪平 
	 * 时间：2017年7月25日-下午5:53:11 
	 * @param key
	 * @param value
	 * @param time void
	 * @exception 
	 * @since  1.0.0
	 */
	public void set(String key, String value,long time) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		if (!this.stringRedisTemplate.hasKey(key)) {
			ops.set(key, value, time,TimeUnit.SECONDS);//time 为失效时间
			System.out.println("set key success");
		} else {
			// 存在则打印之前的value值
			System.out.println("this key = " + ops.get(key));
		}
	}

	/**
	 * 当redis存储的值是hash类型时  设值
	 * com.example.common 
	 * 方法名：setHashValue
	 * 创建人：yzk 
	 * 时间：2018年8月22日-上午9:48:01 
	 * @param key1
	 * @param key2
	 * @param value void
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public void setHashValue(String key1,String key2, Object value) {
		HashOperations<String, String, Object>  ops= redisTemplate.opsForHash();
		ops.put(key1, key2, value);
	}
	
	/**
	 * 当redis存储的值是Set类型时  设值
	 * com.example.common 
	 * 方法名：setSetValue
	 * 创建人：yzk 
	 * 时间：2018年9月26日-上午11:10:15 
	 * @param key
	 * @param value void
	 * @exception 
	 * @since  1.0.0
	 */

	@SuppressWarnings("unchecked")
	public void setSetValue(String key,  Set<String> value) {
		SetOperations<String, Set<String>> ops = redisTemplate.opsForSet();
		if(!redisTemplate.hasKey(key)) {
		ops.add(key, value);
		}
		else {
			//当已存在key时，就把原来的数据删除，再把新的值存进去，这样只会保留一个最新的set集合
			redisTemplate.delete(key);
			ops.add(key, value);
		}
	}
	
	
	public String get(String key) {
		return this.stringRedisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 当redis存储的值是hash类型时  取值
	 * com.example.common 
	 * 方法名：getHashValue
	 * 创建人：yzk 
	 * 时间：2018年8月22日-上午9:42:07 
	 * @param key1
	 * @param key2
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Object getHashValue(String key1,String key2) {
		return  this.redisTemplate.opsForHash().get(key1,key2);
	}
	
	/**
	 * 当redis存储的值是set类型时  取值
	 * com.example.common 
	 * 方法名：getSetValue
	 * 创建人：yzk 
	 * 时间：2018年9月26日-下午1:17:10 
	 * @param key
	 * @return Set<String>
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	//某个键里可能存有多个set集合
	public Set<Set<String>> getSetValue(String key) {
		return  this.redisTemplate.opsForSet().members(key);
	}
	
	
	/**
	 * 是否存在此key
	 * com.example.common 
	 * 方法名：hasKey
	 * 创建人：yzk 
	 * 时间：2018年9月26日-下午3:44:27 
	 * @param key
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}
	
	
	/**
	 * 获取失效时间
	 * com.jianyan.config 
	 * 方法名：getExpire
	 * 创建人：何雪平 
	 * 时间：2017年7月25日-下午5:41:49 
	 * @param key
	 * @return long
	 * @exception 
	 * @since  1.0.0
	 */
	public long getExpire(String key) {
		return stringRedisTemplate.getExpire(key,TimeUnit.SECONDS);
	}
	
	/**
	 * 删除缓存
	 * com.jianyan.config 
	 * 方法名：del
	 * 创建人：何雪平 
	 * 时间：2017年7月25日-下午5:50:44 
	 * @param key void
	 * @exception 
	 * @since  1.0.0
	 */
	public void del(String key) {
		this.stringRedisTemplate.delete(key);
	}
}