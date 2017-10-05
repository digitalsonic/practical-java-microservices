package microbucks.customerservice.runner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.service.OrderProtoService;
import microbucks.orderservice.service.OrderServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceGetRunner implements ApplicationRunner {
    @Value("${grpc.server}")
    private String server;
    @Value("${grpc.port}")
    private int port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(server, port)
                .usePlaintext(true) // turn off secure
                .build();
        OrderServiceGrpc.OrderServiceBlockingStub stub =
                OrderServiceGrpc.newBlockingStub(channel);

        OrderProtoService.OrderListResponse response =
                stub.getAll(OrderProtoService.GetAllRequest.newBuilder().build());
        response.getOrdersList().forEach(o -> log.info("getAll(): {}", o));
    }
}
