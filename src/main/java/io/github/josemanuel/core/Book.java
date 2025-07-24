package io.github.josemanuel.core;

/**
 * Immutable value‑object that represents a book.
 * WHY: Avoids accidental mutation both in prod and tests.
 */
public final class Book {
    private final String isbn;
    private final String title;
    private final String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
