package io.github.josemanuel.clock;

import java.time.LocalDate;

/**
 * Clock abstraction so production code does not call LocalDate.now() directly.
 * WHY: Tests can inject a fixed implementation (lambda or fake) to freeze time.
 */
public interface Clock {
    LocalDate today();
}
