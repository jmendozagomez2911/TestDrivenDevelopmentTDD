package io.github.josemanuel.email;

/**
 * Business use‑case that fires an e‑mail on order placement.
 * Tests would mock EmailService to verify interaction.
 */
public class OrderService {
    private final EmailService email;

    public OrderService(EmailService email) {
        this.email = email;
    }

    public void placeOrder() {
        email.send("Order placed");
    }
}
