package io.github.josemanuel.documentexport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Verifies side-effect using a FakeExporter (no heavy PDF generation).
 */
class InvoiceServiceFakeTest {
    @Test
    void exporterCalled() {
        FakeExporter fake = new FakeExporter();
        new InvoiceService(fake).exportInvoice();
        assertThat(fake.exported).isTrue();
    }
}
