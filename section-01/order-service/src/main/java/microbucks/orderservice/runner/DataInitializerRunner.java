package microbucks.orderservice.runner;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
public class DataInitializerRunner implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.asList("LiLei", "HanMeimei").forEach(x -> {
            Order order = Order.builder().customer(x).barista("Master")
                    .createTime(new Date()).modifyTime(new Date()).build();
            orderRepository.save(order);
            log.info("Saving Order[{}] to database.", order);
        });
    }
}
