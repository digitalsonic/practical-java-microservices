package microbucks.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.model.Status;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderServiceController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        Order newOrder = Order.builder()
                .customer(order.getCustomer()).content(order.getContent())
                .price("30")
                .createTime(new Date()).modifyTime(new Date())
                .state(Status.INIT).build();
        return orderRepository.save(newOrder);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(o -> log.info("Order[{}] loaded.", o));
        return orders;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Order modifyOrder(@PathVariable("id") Long id, @RequestBody Order order,
                             HttpServletResponse response) {
        Integer state = order.getState();
        Order savedOrder = orderRepository.findOne(id);

        if (savedOrder == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        switch(state) {
            case Status.PAYED :
                if (modifyToPayed(order, response, savedOrder)) {
                    return savedOrder;
                }
                break;
            case Status.TAKEN :
                if (modifyToTaken(response, savedOrder)) {
                    return savedOrder;
                }
                break;
            case Status.READY :
                savedOrder.setState(Status.READY);
                savedOrder.setBarista(order.getBarista());
                break;
            default:
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return null;
        }
        savedOrder.setModifyTime(new Date());
        return orderRepository.save(savedOrder);
    }

    private boolean modifyToTaken(HttpServletResponse response, Order savedOrder) {
        if (Status.READY == savedOrder.getState()) {
            savedOrder.setState(Status.TAKEN);
        } else {
            response.setStatus(HttpStatus.PROCESSING.value());
            return true;
        }
        return false;
    }

    private boolean modifyToPayed(Order order, HttpServletResponse response, Order savedOrder) {
        if (savedOrder.getPrice().equals(order.getPrice())) {
            savedOrder.setState(Status.PAYED);
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return true;
        }
        return false;
    }
}
