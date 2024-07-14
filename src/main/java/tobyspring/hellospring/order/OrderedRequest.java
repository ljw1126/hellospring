package tobyspring.hellospring.order;

import java.math.BigDecimal;

public record OrderedRequest(String no, BigDecimal total) {
}
