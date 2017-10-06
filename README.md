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

## Section 04

在Section 02的基础上，引入了HAL，使用Spring Data REST演示了简单的HATEOAS。

## Section 05

基于Section 01的代码，将服务提供方改为通过Dubbo发布服务。调用方同样使用Dubbo。

需要在本机启动一个Zookeeper，监听2181端口。

## Section 06

与Section 05类似，但不使用Dubbo，改用gRPC来提供服务。

## Section 07

完善Section 01的服务端和Section 03的客户端例子。丰富了Order实体的内容与状态。

## Section 08

在Section 07的基础上，使用Spring Cloud的服务发现和注册机制，使用Eureka作为注册中心，客户端使用Ribbon来做负载均衡。

## Section 09

将Section 08的Eureka替换成ZooKeeper和Consul。需要在本地启动ZooKeeper和Consul的服务。
