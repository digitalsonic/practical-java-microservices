package microbucks.orderservice.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import microbucks.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void getAll(OrderProtoService.GetAllRequest request,
                       StreamObserver<OrderProtoService.OrderListResponse> responseObserver) {
        OrderProtoService.OrderListResponse.Builder builder =
                OrderProtoService.OrderListResponse.newBuilder();
        orderRepository.findAll().forEach(
                (microbucks.orderservice.model.Order o) -> builder.addOrders(convert(o)));
        OrderProtoService.OrderListResponse response = builder.build();
        log.info("getAll() returns {} order(s).", response.getOrdersCount());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrderById(OrderProtoService.GetOrderByIdRequest request,
                             StreamObserver<OrderModel.Order> responseObserver) {
        Long id = request.getId();
        OrderModel.Order order = convert(orderRepository.findOne(id));
        log.info("getOrderById({}) return {}", id, order);
        responseObserver.onNext(order);
        responseObserver.onCompleted();
    }

    private OrderModel.Order convert(microbucks.orderservice.model.Order o) {
        return OrderModel.Order.newBuilder()
                .setId(o.getId())
                .setBarista(o.getBarista())
                .setCustomer(o.getCustomer())
                .setCreateTime(Timestamp.newBuilder().setSeconds(o.getCreateTime().getTime()).build())
                .setCreateTime(Timestamp.newBuilder().setSeconds(o.getModifyTime().getTime()).build())
                .build();
    }
}
