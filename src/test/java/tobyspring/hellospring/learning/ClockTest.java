package tobyspring.hellospring.learning;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {

    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone(); // 시간대

        LocalDateTime dateTime1 = LocalDateTime.now(clock);
        LocalDateTime dateTime2 = LocalDateTime.now(clock);

        assertThat(dateTime2).isAfter(dateTime1);
    }

    @Test
    void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime dateTime1 = LocalDateTime.now(clock);
        LocalDateTime dateTime2 = LocalDateTime.now(clock);

        assertThat(dateTime2).isEqualTo(dateTime1);
    }
}
