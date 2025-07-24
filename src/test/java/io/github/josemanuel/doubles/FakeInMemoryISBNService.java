package io.github.josemanuel.doubles;

import io.github.josemanuel.core.Book;
import io.github.josemanuel.core.ExternalISBNDataService;

import java.util.HashMap;
import java.util.Map;

/**
 * FAKE – lightweight, in‑memory “database”.
 * Lets tests add/query books without external I/O.
 */
public class FakeInMemoryISBNService implements ExternalISBNDataService {
    private final Map<String, Book> storage = new HashMap<>();

    /**
     * Seed the fake with a book entry.
     */
    public void add(Book b) {
        storage.put(b.getIsbn(), b);
    }

    /**
     * Lookup behaves like a real DB: returns null if not found.
     */
    @Override
    public Book lookup(String isbn) {
        return storage.get(isbn);
    }
}
