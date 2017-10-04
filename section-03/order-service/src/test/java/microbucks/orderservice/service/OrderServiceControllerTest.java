package microbucks.orderservice.service;

import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceControllerTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private OrderRepository orderRepository;
    private OrderServiceController orderServiceController;

    @Before
    public void setUp() throws Exception {
        orderServiceController = new OrderServiceController();
        orderServiceController.setOrderRepository(orderRepository);
    }

    @After
    public void tearDown() throws Exception {
        orderServiceController = null;
        reset(orderRepository);
    }

    @Test
    public void testGetOrders() throws Exception {
        orderServiceController.getOrders();
        verify(orderRepository, only()).findAll();
        verify(orderRepository, never()).findOne(anyLong());
    }

    @Test
    public void getGetOrderById() throws Exception {
        Order order = Order.builder().id(1L).barista("M").customer("LiLei").build();
        when(orderRepository.findOne(1L)).thenReturn(order);
        Order retrieved = orderServiceController.getOrderById(1L);
        assertEquals(order, retrieved);
        assertNull(orderServiceController.getOrderById(2L));
    }
}