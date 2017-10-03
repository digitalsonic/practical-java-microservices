package microbucks.orderservice.runner;

import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
public class DataInitializerRunner implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;
    @Value("${barista.name}")
    private String baristaName;

    @Override
    public void run(String... args) throws Exception {
        Arrays.asList("LiLei", "HanMeimei").forEach(x -> {
            Order order = Order.builder().customer(x).barista(baristaName)
                    .createTime(new Date()).modifyTime(new Date()).build();
            orderRepository.save(order);
            log.info("Saving Order[{}] to database.", order);
        });
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setBaristaName(String baristaName) {
        this.baristaName = baristaName;
    }
}
