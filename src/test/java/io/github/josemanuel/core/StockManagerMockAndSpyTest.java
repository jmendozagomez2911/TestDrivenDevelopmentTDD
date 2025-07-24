package io.github.josemanuel.core;

import static org.mockito.Mockito.*;

import io.github.josemanuel.doubles.FakeInMemoryISBNService;
import org.junit.jupiter.api.Test;

/**
 * Interaction tests:
 * - Mock: verify lookup called exactly once.
 * - Spy: run real fake logic and still verify interaction.
 */
class StockManagerMockAndSpyTest {

    @Test
    void mockSingleLookup() {
        ExternalISBNDataService mock = mock(ExternalISBNDataService.class);
        when(mock.lookup("999")).thenReturn(new Book("999", "X", "Y"));
        new StockManager(mock).getLocatorCode("999");
        verify(mock).lookup("999");
        verifyNoMoreInteractions(mock);
    }

    @Test
    void spyOnFake() {
        FakeInMemoryISBNService fake = new FakeInMemoryISBNService();
        fake.add(new Book("1", "A", "B"));
        ExternalISBNDataService spy = spy(fake);
        new StockManager(spy).getLocatorCode("1");
        verify(spy).lookup("1");
    }
}
