package microbucks.customerservice;

import microbucks.customerservice.model.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-service")
public interface OrderService {
    @RequestMapping(value = "/orders/", method = RequestMethod.POST)
    Order createOrder(@RequestBody Order order);

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    Order getOrderById(@PathVariable("id") Long id);

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    ResponseEntity<Order> modifyOrder(@PathVariable("id") Long id, @RequestBody Order order);
}
