package io.github.josemanuel.core;

/**
 * Boundary interface that hides *how* a Book is fetched.
 * <p>
 * Production implementation:
 * - RealExternalISBNDataService (REST/DB placeholder)
 * <p>
 * Test implementations (in src/test/java):
 * - StubISBNService (stub)
 * - FakeInMemoryISBNService (fake)
 * - DummyISBNService (dummy)
 * - Mockito mock / spy
 */
public interface ExternalISBNDataService {
    Book lookup(String isbn);
}
