package microbucks.orderservice.repository;

import microbucks.orderservice.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Rollback
    public void testSaveOrder() {
        Order order = Order.builder().customer("Foo").barista("bar").build();
        orderRepository.save(order);
        Long id = order.getId();
        assertNotNull(id);
        assertNotNull(orderRepository.findAll());
        assertEquals("Foo", orderRepository.findOne(id).getCustomer());
    }

    @Test
    public void testNoOrderFound() {
        assertEquals(0, orderRepository.findAll().size());
    }
}