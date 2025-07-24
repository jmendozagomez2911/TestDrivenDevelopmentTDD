package io.github.josemanuel.core.validation;

/**
 * ISBN‑10 checksum validator (pure function).
 * WHY: Pure logic is trivial to unit test.
 */
public class ValidateISBN {
    public boolean isValid10(String isbn) {
        int sum = 0;
        for (int i = 0; i < 10; i++)
            sum += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        return sum % 11 == 0;
    }
}
