package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order create(String no, BigDecimal total);

    List<Order> createOrders(List<OrderedRequest> reqs);
}
