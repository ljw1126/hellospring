package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.db.OrderRepository;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DBClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DBConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        PlatformTransactionManager transactionManager = beanFactory.getBean(PlatformTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.TEN); // no: 주문 번호가 유니크 하지 않아 예외
                repository.save(order2);

                return null;
            });
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            System.out.println("주문번호 중복 복구 작업");
        }
    }
}
