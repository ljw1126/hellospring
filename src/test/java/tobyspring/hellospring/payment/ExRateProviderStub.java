package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;

public class ExRateProviderStub implements ExRateProvider {

    private final BigDecimal exRate;

    public ExRateProviderStub(BigDecimal exRate) {
        this.exRate = exRate;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        return exRate;
    }
}
