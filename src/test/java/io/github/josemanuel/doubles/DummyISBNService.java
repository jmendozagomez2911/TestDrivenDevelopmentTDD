package io.github.josemanuel.doubles;

import io.github.josemanuel.core.Book;
import io.github.josemanuel.core.ExternalISBNDataService;

/**
 * DUMMY – returns null unconditionally.
 * Used when we expect an exception path (ISBN not found).
 */
public class DummyISBNService implements ExternalISBNDataService {
    @Override
    public Book lookup(String isbn) {
        return null;
    }
}
