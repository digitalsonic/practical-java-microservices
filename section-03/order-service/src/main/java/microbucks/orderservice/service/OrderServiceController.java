package microbucks.orderservice.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
@Api("the services related to Order")
public class OrderServiceController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/")
    @ApiOperation(value = "get the list of orders", response = List.class)
    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(o -> log.info("Order[{}] loaded.", o));
        return orders;
    }

    @RequestMapping("/{id}")
    @ApiOperation(value = "get one detail order", response = Order.class)
    @ApiImplicitParam(value = "orderId", name = "id", required = true)
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
