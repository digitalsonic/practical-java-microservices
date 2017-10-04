# Practical Java Microservices

一些基于Spring Boot的微服务实例。

## Section 01

一个简单的微服务，查询一些简单的订单。

基于如下技术：

1. Spring Boot
2. Spring Data JPA访问数据
3. H2内存数据库作为数据存储
4. Spring MVC实现REST接口
5. Lombok简化代码

## Section 02

在Section 01的基础上，演示了Spring Boot的一些特性：

1. 修改一些常用参数
2. 替换日志框架spring-boot-starter-log4j2
3. 用Jetty替换Tomcat
4. Actuator

## Section 03

在Section 02的基础上，增加了一个客户端，使用`RestTemplate`进行服务调用。

为服务增加了文档说明，使用了以下两种技术：

1. Spring REST Docs
2. Swagger(SpringFox)

