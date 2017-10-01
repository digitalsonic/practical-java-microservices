package microbucks.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderServiceController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/")
    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(o -> log.info("Order[{}] loaded.", o));
        return orders;
    }

    @RequestMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }
}
