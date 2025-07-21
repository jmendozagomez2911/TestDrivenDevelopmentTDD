package io.github.josemanuel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {


    @Test
    public void shouldReturnTrueForValidISBN() {
        ValidateISBN validator = new ValidateISBN(); // create object of the real class
        assertTrue(validator.isValid("9780134685991"));       // valid ISBN
        assertFalse(validator.isValid("1234567"));            // too short


        assertFalse(validator.isValid("97801X4685991"));      // invalid character
    }
}
