package microbucks.orderservice.service;

import microbucks.orderservice.model.Order;

import java.util.List;

public interface OrderServiceFacade {
    List<Order> getAllOrders();
    Order getOrder(Long id);
}
