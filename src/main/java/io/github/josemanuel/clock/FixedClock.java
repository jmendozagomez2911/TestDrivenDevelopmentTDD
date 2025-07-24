package io.github.josemanuel.clock;

import java.time.LocalDate;

/**
 * Fixed clock returning the same date every call.
 * Usually used in tests or rare deterministic prod scenarios.
 */
public class FixedClock implements Clock {
    private final LocalDate fixed;

    public FixedClock(LocalDate fixed) {
        this.fixed = fixed;
    }

    @Override
    public LocalDate today() {
        return fixed;
    }
}
