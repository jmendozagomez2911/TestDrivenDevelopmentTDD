package io.github.josemanuel.infraestructure.isbn;

import io.github.josemanuel.core.*;

/**
 * Real implementation placeholder.
 * TODO: Replace with remote REST call / DB lookup.
 */
public class RealExternalISBNDataService implements ExternalISBNDataService {
    @Override
    public Book lookup(String isbn) {
        throw new UnsupportedOperationException("Real call not implemented");
    }
}
