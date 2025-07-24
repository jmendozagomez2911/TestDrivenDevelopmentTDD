package io.github.josemanuel.clock;

import java.time.LocalDate;

/**
 * Production clock using the real system date.
 */
public class SystemClock implements Clock {
    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}