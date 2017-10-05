package microbucks.customerservice.runner;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.model.Order;
import microbucks.orderservice.service.OrderServiceFacade;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderServiceGetRunner implements ApplicationRunner {
    @Reference
    private OrderServiceFacade orderServiceFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Order> orderList = orderServiceFacade.getAllOrders();
        log.info("get {} orders from remote", orderList.size());

        Order order = orderServiceFacade.getOrder(1L);
        log.info("getOrder(1): {}", order);
    }
}
