package microbucks.orderservice.health;

import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderHealthIndicator implements HealthIndicator {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Health health() {
        List<Order> orders = orderRepository.findAll();
        if (orders == null || orders.isEmpty()) {
            return Health.unknown().withDetail("order.size", "none").build();
        }

        return Health.up().withDetail("order.size", orders.size()).build();
    }
}
