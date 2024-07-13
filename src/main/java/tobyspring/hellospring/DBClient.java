package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.db.OrderRepository;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DBClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DBConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        System.out.println(order);
    }
}
