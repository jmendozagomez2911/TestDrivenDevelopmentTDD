package io.github.josemanuel.documentexport;

/**
 * FAKE exporter flips a flag so tests can assert the call.
 */
public class FakeExporter implements DocumentExporter {
    public boolean exported = false;

    @Override
    public void export(String filename) {
        exported = true;
    }
}
