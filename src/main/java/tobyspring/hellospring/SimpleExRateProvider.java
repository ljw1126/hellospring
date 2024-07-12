package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if ("USD".equals(currency)) return BigDecimal.valueOf(100);

        throw new IllegalArgumentException("지원되지 않는 통화입니다");
    }
}