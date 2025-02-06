- 一个项目中，什么数据适合使用Redis缓存呢？
	- 常用的
	- 一致性要求不高的
	- 不太修改

- 在苍穹外卖里做了缓存处理的数据
	- 店铺营业状态
		- 不经常修改
		- 很常用
	- 菜品的查询结果
		- 经常访问
		- 不常修改

- 我打算在云彩Web里加什么？
	- 云彩的know页面的查询结果，以及各个类别的detail查询结果


- 步骤
	- 准备好依赖和配置
	- 编码，使用工具观察结果
		- 查询时get/set
		- 更改时delete
- 需要作为value的数据，如果是自定义对象，需要实现implements Serializable
	- ![[Pasted image 20250117194608.png]]

### 依赖
```xml
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-data-redis</artifactId>  
</dependency>
```

### 配置类
```java
package com.example.java7_4.config;  
  
import lombok.extern.slf4j.Slf4j;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.data.redis.connection.RedisConnectionFactory;  
import org.springframework.data.redis.core.RedisTemplate;  
import org.springframework.data.redis.serializer.StringRedisSerializer;  
  
@Configuration  
@Slf4j  
public class RedisConfiguration {  
  
    @Bean  
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){  
        log.info("开始创建redis模板对象...");  
        RedisTemplate redisTemplate = new RedisTemplate();  
        //设置redis的连接工厂对象  
        redisTemplate.setConnectionFactory(redisConnectionFactory);  
        //设置redis key的序列化器  
        redisTemplate.setKeySerializer(new StringRedisSerializer());  
        return redisTemplate;  
    }  
}
```

### 配置文件
```yml
redis:  
    host: localhost  
    port: 6379  
#     password: 123456  
    database: 10
```


### get和set的使用
- 尝试去拿，拿不到再去数据库查，查完了set
	- ![[Pasted image 20250117193032.png]]

- 结果展示
	- ![[Pasted image 20250117192919.png]]


### delete保持数据一致性
- 数据发生了变化或者新增了数据->delete
	- ![[Pasted image 20250117194856.png]]
	- ![[Pasted image 20250117195032.png]]