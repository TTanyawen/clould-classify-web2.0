- 第一次尝试(出现问题)
	- 导入依赖
		- knife4j对swagger进行封装，使用比较简单
	- 在配置类中加入knife4j相关配置
	- 设置静态资源映射
	- 使用


```xml
<dependency>  
    <groupId>com.github.xiaoymin</groupId>  
    <artifactId>knife4j-spring-boot-starter</artifactId>  
    <version>3.0.2</version>  
</dependency>
```



```java
@Bean  
public Docket docket1() {  
    ApiInfo apiInfo = new ApiInfoBuilder()  
            .title("云彩WEB项目接口文档")  
            .version("2.0")  
            .description("云彩WEB项目接口文档")  
            .build();  
    Docket docket = new Docket(DocumentationType.SWAGGER_2)  
            .groupName("接口")  
            .apiInfo(apiInfo)  
            .select()  
            .apis(RequestHandlerSelectors.basePackage("com.example.java7_4.controller"))  
            .paths(PathSelectors.any())  
            .build();  
    return docket;  
}
```



```java
/**  
 * 设置静态资源映射  
 *  
 * @param registry  
 */  
protected void addResourceHandlers(ResourceHandlerRegistry registry) {  
    registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");  
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");  
}
```

- 上面这个的不能乱写类名
- 静态资源映射也是在配置类里的
	- public class WebMvcConfig extends **WebMvcConfigurationSupport** {


### 出现问题->解决问题
- 出现问题
	- ![[Pasted image 20250118102251.png]]

- 排查
	- ![[Pasted image 20250118104200.png]]
	- 发现是swagger-resources 404了
	- 然后去看网上说knife4j和springboot存在兼容问题，版本需要对应
		- ![[Pasted image 20250118110603.png]]
- 最后我换成knife4j 4了
	- 解决参考
		- https://blog.csdn.net/weixin_64369714/article/details/137246250

- 1. 加入依赖
```
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
    <version>4.4.0</version>
</dependency>
```



- 2. 加入配置(packages-to-scan换成你模块下controller包的路径)

```yml
# springdoc-openapi项目配置
springdoc:
  swagger-ui:
    path: /swagger-ui.html
    tags-sorter: alpha
    operations-sorter: alpha
  api-docs:
    path: /v3/api-docs
  group-configs:
    - group: 'default'
      paths-to-match: '/**'
      packages-to-scan: com.xiaominfo.knife4j.demo.web    
 
# knife4j的增强配置，不需要增强可以不配（建议配置一下）
knife4j:
  enable: true
  setting:
    language: zh_cn
```


![[Pasted image 20250118120948.png]]

- 3.使用注解
	- ![[Pasted image 20250118121035.png]]
- 结果展示
	- ![[Pasted image 20250118121156.png]]
	- ![[Pasted image 20250118121146.png]]

- 感悟：其实去看官方的文档是最好的