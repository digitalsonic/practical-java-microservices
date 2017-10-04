package microbucks.customerservice.runner;

import lombok.extern.slf4j.Slf4j;
import microbucks.customerservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Slf4j
public class OrderServiceGetRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${orderservice.url}")
    private String orderServiceUrl;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        URI uri = UriComponentsBuilder.fromUriString(orderServiceUrl + "/").build().toUri();
        PagedResources<Order> resources =
                restTemplate.exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<PagedResources<Order>>() {}).getBody();
        log.info("get orders from Link: {}", resources.getLink(Link.REL_SELF));
        resources.getContent().forEach(o -> log.info("get Order[{}]", o));
    }
}
