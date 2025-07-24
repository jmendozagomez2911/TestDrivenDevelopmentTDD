package io.github.josemanuel.doubles;

import io.github.josemanuel.core.Book;
import io.github.josemanuel.core.ExternalISBNDataService;

/**
 * STUB – always returns the same hard‑coded Book,
 * allowing us to predict the locator result exactly.
 */
public class StubISBNService implements ExternalISBNDataService {
    @Override
    public Book lookup(String isbn) {
        return new Book(isbn, "Design Patterns", "GoF");
    }
}
