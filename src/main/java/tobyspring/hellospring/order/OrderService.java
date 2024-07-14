package tobyspring.hellospring.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order create(String no, BigDecimal total) {
        Order order = new Order(no, total);

        orderRepository.save(order);
        return order;
    }

    public List<Order> createOrders(List<OrderedRequest> reqs) {
        return new TransactionTemplate(transactionManager).execute(status -> reqs.stream()
                .map(req -> create(req.no(), req.total()))
                .collect(Collectors.toList()));
    }
}
