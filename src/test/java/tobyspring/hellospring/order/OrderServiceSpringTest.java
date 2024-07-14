package tobyspring.hellospring.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DataSource dataSource;

    @Test
    void create() {
        var order = orderService.create("100", BigDecimal.ONE);

        assertThat(order.getId()).isGreaterThan(0L);
    }

    @Test
    void createOrders() {
        List<OrderedRequest> orderedRequests = List.of(new OrderedRequest("200", BigDecimal.TEN),
                new OrderedRequest("300", BigDecimal.ONE));

        var orders = orderService.createOrders(orderedRequests);

        assertThat(orders).hasSize(2);
        for (Order order : orders) {
            assertThat(order.getId()).isGreaterThan(0L);
        }
    }

    @Test
    void 주문번호가_동일한경우_예외가발생한다() {
        List<OrderedRequest> orderedRequests = List.of(
                new OrderedRequest("300", BigDecimal.TEN),
                new OrderedRequest("300", BigDecimal.ONE)
        );

        assertThatThrownBy(() -> orderService.createOrders(orderedRequests))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no = '300'").query(Long.class).single();
        assertThat(count).isZero();
    }
}
