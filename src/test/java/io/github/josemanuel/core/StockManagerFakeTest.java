package io.github.josemanuel.core;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.josemanuel.doubles.FakeInMemoryISBNService;
import org.junit.jupiter.api.*;

/**
 * Uses FakeInMemoryISBNService to exercise multiple rows without I/O.
 */
class StockManagerFakeTest {
    FakeInMemoryISBNService fake;

    @BeforeEach
    void init() {
        fake = new FakeInMemoryISBNService();
        fake.add(new Book("0140449116", "The Divine Comedy", "Dante"));    // last=6 author=5 title=17 -> 6517
        fake.add(new Book("0140177396", "Of Mice And Men", "Steinbeck")); // last=6 author=9 title=15 -> 6915
    }

    @Test
    void danteLocator() {
        assertThat(new StockManager(fake).getLocatorCode("0140449116"))
                .isEqualTo("6517");
    }

    @Test
    void steinbeckLocator() {
        assertThat(new StockManager(fake).getLocatorCode("0140177396"))
                .isEqualTo("6915");
    }
}
