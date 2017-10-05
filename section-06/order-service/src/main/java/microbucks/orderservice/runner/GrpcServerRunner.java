package microbucks.orderservice.runner;

import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Slf4j
public class GrpcServerRunner implements CommandLineRunner{
    @Autowired
    private OrderServiceImpl orderService;
    @Value("${grpc.port:8888}")
    private int grpcPort;

    @Override
    public void run(String... args) throws Exception {
        ServerBuilder.forPort(grpcPort).addService(orderService).build().start();
        log.info("start grpc orderService on {}", grpcPort);
    }
}
