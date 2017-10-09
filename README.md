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

## Section 10

在Section 09的基础上，为customer-service增加了断路器，调整了客户端的实现，通过POST调用触发操作，并演示了DashBoard功能。

## Section 11

在Section 09的基础上，将客户端从Ribbon替换为Feign。

注意，服务端的响应码需要>=200。

## Section 12

将Section 09的order-service放到Zuul代理背后，简单演示了ZuulProxy。

## Section 13

演示了Spring Cloud Config的功能，包含如下内容：

1. 基于Git的配置管理
2. 通过服务发现找到配置中心
3. 配置加解密

## Section 14

基于Section 08的order-service，增加了Spring Cloud Stream的消息机制。

新增了barista-service，作为咖啡师，通过消息推进订单状态。

需要在本地启动一个RabbitMQ，监听5672端口，默认用户名密码guest/guest。

## Section 15

通过Spring Cloud Sleuth将Section 14的order-service和barista-service，以及Section 09的customer-service串联起来。

这里用到了：

1. Spring Cloud Sleuth
2. Spring Cloud Stream
3. Zipkin Server
4. Zipkin Web
5. Eureka

需要在本地5672启动一个RabbitMQ。

## Section 16

在Section 15的基础上，增加了OAuth 2.0的客户端模式认证。
