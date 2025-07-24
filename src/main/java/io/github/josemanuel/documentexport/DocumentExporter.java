package io.github.josemanuel.documentexport;

/**
 * Abstraction for exporting an invoice.
 * Tests replace heavy implementation with a FakeExporter.
 */
public interface DocumentExporter {
    void export(String filename);
}
