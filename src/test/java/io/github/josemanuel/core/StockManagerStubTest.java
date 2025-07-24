package io.github.josemanuel.core;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.josemanuel.doubles.StubISBNService;
import org.junit.jupiter.api.Test;

/**
 * Tests exact locator output using a Stub with hard-coded book.
 */
class StockManagerStubTest {
    @Test
    void exactLocator() {
        String code = new StockManager(new StubISBNService())
                .getLocatorCode("1111111111");
        assertThat(code).isEqualTo("1315"); // 1|3|15
    }
}
