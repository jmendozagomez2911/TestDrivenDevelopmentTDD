package io.github.josemanuel.core;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.josemanuel.doubles.DummyISBNService;
import org.junit.jupiter.api.Test;

/**
 * Tests exception path using a Dummy that always returns null.
 */
class StockManagerDummyTest {
    @Test
    void throwsWhenBookMissing() {
        StockManager sm = new StockManager(new DummyISBNService());
        assertThatThrownBy(() -> sm.getLocatorCode("000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ISBN not found");
    }
}
