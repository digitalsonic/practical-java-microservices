package microbucks.customerservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.RetryLoadBalancerInterceptor;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableOAuth2Client
public class CustomerServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomerServiceApplication.class).web(true).run(args);
    }

    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    @LoadBalanced
    public RestTemplate oauth2RestTemplate(ClientCredentialsResourceDetails resource,
                                           OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(resource, oauth2ClientContext);
    }

    /*
     * 为了能通过服务发现找到AuthorizationServer，需要自己定义一个AccessTokenProvider
     * Trace也是同样的道理，需要在这里设置一下
     * 为了避免RestTemplate创建时的循环依赖，在这里设置
     */
    @Bean
    public ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider(
            RetryLoadBalancerInterceptor retryLoadBalancerInterceptor,
            TraceRestTemplateInterceptor traceRestTemplateInterceptor,
            OAuth2RestTemplate restTemplate) {
        ClientCredentialsAccessTokenProvider accessTokenProvider = new ClientCredentialsAccessTokenProvider();
        accessTokenProvider.setInterceptors(Arrays.asList(traceRestTemplateInterceptor, retryLoadBalancerInterceptor));
        restTemplate.setAccessTokenProvider(accessTokenProvider);
        return accessTokenProvider;
    }
}
