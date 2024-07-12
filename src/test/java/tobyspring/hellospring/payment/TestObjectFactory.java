package tobyspring.hellospring.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.math.BigDecimal.valueOf;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(valueOf(1_000));
    }
}
