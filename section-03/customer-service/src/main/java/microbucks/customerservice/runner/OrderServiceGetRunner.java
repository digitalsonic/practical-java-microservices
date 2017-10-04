package microbucks.customerservice.runner;

import lombok.extern.slf4j.Slf4j;
import microbucks.customerservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
@Slf4j
public class OrderServiceGetRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${orderservice.url}")
    private String orderServiceUrl;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String ordersStr = restTemplate.getForObject(orderServiceUrl + "/", String.class);
        log.info("Simply getForObject(): {}", ordersStr);

        Order order = restTemplate.getForObject(orderServiceUrl+ "/{id}", Order.class, "1");
        log.info("Simply get one order: {}", order);

        ParameterizedTypeReference<ArrayList<Order>> ptr =
                new ParameterizedTypeReference<ArrayList<Order>>() {};
        ArrayList<Order> response =
                restTemplate.exchange(orderServiceUrl + "/", HttpMethod.GET, null, ptr).getBody();
        for (Order o : response) {
            log.info("exchange with type, order: {}", o);
        }
    }
}
