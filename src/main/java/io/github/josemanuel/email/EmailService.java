package io.github.josemanuel.email;

/** Wrapper so we can mock or fake e‑mails. */
public interface EmailService {
    void send(String message);
}
