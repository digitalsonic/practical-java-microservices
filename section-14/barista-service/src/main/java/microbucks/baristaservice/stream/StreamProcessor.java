package microbucks.baristaservice.stream;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class StreamProcessor {
    @Value("${barista.name}")
    private String baristaName;

    @StreamListener("barista")
    @SendTo("order")
    public Order receiveOrder(Order order) {
        log.info("receive order: {}", order);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        log.info("coffee for order {} is ready", order.getId());
        order.setBarista(baristaName);
        order.setState(3);
        order.setModifyTime(new Date());
        return order;
    }
}
