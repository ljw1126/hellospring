package tobyspring.hellospring.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(String no, BigDecimal total) {
        Order order = new Order(no, total);

        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderedRequest> reqs) {
        return reqs.stream()
                .map(req -> create(req.no(), req.total()))
                .collect(Collectors.toList());
    }
}
