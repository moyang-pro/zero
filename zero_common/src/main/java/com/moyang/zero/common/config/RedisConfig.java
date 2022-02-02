package com.moyang.zero.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author moyang
 */
@Configuration
@EnableCaching
@PropertySource("classpath:/redis/redis.properties") //指定自定义配置文件位置和名称
//@EnableConfigurationProperties(RedisConfig.class) //开启对应配置类的属性注入功能
//@ConfigurationProperties(prefix = "test") //指定配置文件注入属性前缀
public class RedisConfig{

	private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

	/**
	 * redis配置属性读取
	 */
	@Value("${moyang.redis.host}")
	private  String host;
	@Value("${moyang.redis.port}")
	private  int port;
	@Value("${moyang.redis.database}")
	private  int database;
	@Value("${moyang.redis.jedis.pool.max-idle}")
	private int maxIdle;
	@Value("${moyang.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;
	@Value("${moyang.redis.jedis.pool.max-active}")
	private int maxActive;


	/**
	 * JedisPoolConfig配置
	 * @return JedisPoolConfig
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		log.info("初始化JedisPoolConfig");
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxActive);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setMaxIdle(maxIdle);
		return jedisPoolConfig;
	}

	/**
	 * 注入RedisConnectionFactory
	 * @return RedisConnectionFactory
	 */
	@Bean
	public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		log.info("初始化JedisConnectionFactory");
		// JedisConnectionFactory配置host、database、password等参数
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setDatabase(database);
		// JedisConnectionFactory配置jedisPoolConfig
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolConfigBuilder =
				(JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
		jedisPoolConfigBuilder.poolConfig(jedisPoolConfig);
		return new JedisConnectionFactory(redisStandaloneConfiguration);

	}

	/**
	 * 采用RedisCacheManager作为缓存管理器
	 * @param connectionFactory 缓存管理器
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		return RedisCacheManager.create(connectionFactory);
	}

	/**
	 * 重新实现RedisTemplate：解决序列化问题
	 * @param redisConnectionFactory redis 连接
	 * @return 重新实现的RedisTemplate
	 */
	@Bean
	@SuppressWarnings({"rawtype"})
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		// 设置任何字段可见
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// 设置不是final的属性可以转换
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
				ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		log.info("objectMapper: {}", om);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson序列化方式
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson序列化方式
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		template.setEnableTransactionSupport(true);
		return template;
	}

	/**
	 * 重新实现StringRedisTemplate：键值都是String的的数据
	 * @param redisConnectionFactory redis 连接
	 * @return StringRedisTemplate
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		template.setConnectionFactory(redisConnectionFactory);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson序列化方式
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson序列化方式
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		return template;
	}

}

