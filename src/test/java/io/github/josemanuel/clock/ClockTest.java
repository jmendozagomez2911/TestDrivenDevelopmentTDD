package io.github.josemanuel.clock;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Uses a lambda as a Clock double (no separate class needed).
 */
class ClockTest {
    @Test
    void fixedClock() {
        Clock fixed = () -> LocalDate.of(2025, 7, 23);
        assertThat(fixed.today()).isEqualTo(LocalDate.of(2025, 7, 23));
    }
}
