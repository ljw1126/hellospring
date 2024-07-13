package tobyspring.hellospring.payment;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void of() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.of(1L, "USD", TEN, valueOf(1_000), LocalDateTime.now(clock));

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.of(1L, "USD", TEN, valueOf(1_000), LocalDateTime.now(clock));

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES))))
                .isFalse(); // 30분전으로 돌림
    }
}
