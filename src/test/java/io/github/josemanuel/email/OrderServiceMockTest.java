package io.github.josemanuel.email;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import io.github.josemanuel.email.EmailService;
import io.github.josemanuel.email.OrderService;
import org.junit.jupiter.api.Test;

class OrderServiceMockTest {
    @Test void emailSentOnce(){
        EmailService mail = mock(EmailService.class);
        new OrderService(mail).placeOrder();
        verify(mail).send("Order placed");
        verifyNoMoreInteractions(mail);
    }
}