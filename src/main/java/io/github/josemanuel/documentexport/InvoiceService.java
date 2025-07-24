package io.github.josemanuel.documentexport;

/**
 * Business service that delegates to a DocumentExporter.
 * The real exporter would create a PDF; the test fake flips a boolean.
 */
public class InvoiceService {
    private final DocumentExporter exporter;

    public InvoiceService(DocumentExporter exporter) {
        this.exporter = exporter;
    }

    public void exportInvoice() {
        exporter.export("invoice.pdf");
    }
}
