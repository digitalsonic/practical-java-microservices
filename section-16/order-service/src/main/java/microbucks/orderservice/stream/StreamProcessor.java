package microbucks.orderservice.stream;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.model.Status;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class StreamProcessor {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Qualifier("barista")
    private MessageChannel barista;

    @StreamListener("order")
    public void handle(Order order) {
        if (order == null) {
            return;
        }

        Order savedOrder = orderRepository.findOne(order.getId());
        savedOrder.setBarista(order.getBarista());
        savedOrder.setState(Status.READY);
        savedOrder.setModifyTime(new Date());
        orderRepository.save(savedOrder);
        log.info("Order is READY! {}", savedOrder);
    }

    public void sendOrder(Order order) {
        log.info("send order {} to barista", order.getId());
        barista.send(MessageBuilder.withPayload(order).build());
    }
}
