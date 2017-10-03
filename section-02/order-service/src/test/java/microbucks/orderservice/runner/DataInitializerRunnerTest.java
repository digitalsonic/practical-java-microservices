package microbucks.orderservice.runner;

import microbucks.orderservice.model.Order;
import microbucks.orderservice.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DataInitializerRunnerTest {
    @Mock
    private OrderRepository orderRepository;
    private DataInitializerRunner dataInitializerRunner;

    @Before
    public void setUp() throws Exception {
        dataInitializerRunner = new DataInitializerRunner();
        dataInitializerRunner.setOrderRepository(orderRepository);
        dataInitializerRunner.setBaristaName("M");
    }

    @After
    public void tearDown() throws Exception {
        dataInitializerRunner = null;
    }

    @Test
    public void testRun() throws Exception {
        assertNotNull(orderRepository);
        assertNotNull(dataInitializerRunner);

        dataInitializerRunner.run();
        verify(orderRepository, times(2)).save(any(Order.class));
        verify(orderRepository).save(argThat((Order o) -> o.getCustomer().equals("LiLei")));
    }
}