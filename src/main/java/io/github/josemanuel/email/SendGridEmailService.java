package io.github.josemanuel.email;

/**
 * Placeholder – throws to avoid hitting real SMTP until wired.
 * TODO: Implement actual SendGrid integration.
 */
public class SendGridEmailService implements EmailService {
    @Override
    public void send(String message) {
        throw new UnsupportedOperationException("SendGrid not wired");
    }
}
