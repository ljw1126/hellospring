package tobyspring.hellospring.exrate;

import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        if ("USD".equals(currency)) return BigDecimal.valueOf(100);

        throw new IllegalArgumentException("지원되지 않는 통화입니다");
    }
}
