package io.github.josemanuel.core;

/**
 * Computes a warehouse locator code for a book.
 * <p>
 * Business rule:
 * locator = last ISBN digit + author length + title length
 * <p>
 * All external data access goes via ExternalISBNDataService
 * (constructor‑injected for maximum testability).
 */
public class StockManager {

    private final ExternalISBNDataService dataService;

    public StockManager(ExternalISBNDataService dataService) {
        this.dataService = dataService;
    }

    /**
     * @throws IllegalArgumentException if ISBN not found.
     */
    public String getLocatorCode(String isbn) {
        Book book = dataService.lookup(isbn);        // dependency boundary
        if (book == null)
            throw new IllegalArgumentException("ISBN not found");

        String last = isbn.substring(isbn.length() - 1);
        return last + book.getAuthor().length() + book.getTitle().length();
    }
}
