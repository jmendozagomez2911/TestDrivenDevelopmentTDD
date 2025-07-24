package io.github.josemanuel;

import io.github.josemanuel.core.*;
import io.github.josemanuel.core.validation.ValidateISBN;

/**
 * Tiny CLI to smoke‑test the two main features.
 * Run: mvn compile exec:java -Dexec.mainClass=io.github.josemanuel.App
 * <p>
 * NOTE: We do NOT use test doubles here (those live in src/test/java).
 * For the demo we supply an inline implementation.
 */
public class App {
    public static void main(String[] args) {

        // 1) Checksum validator demo (pure function)
        String isbn10 = "0140449116";
        boolean ok = new ValidateISBN().isValid10(isbn10);
        System.out.println(isbn10 + (ok ? " is VALID" : " is INVALID"));

        // 2) Locator‑code demo using an inline ExternalISBNDataService
        ExternalISBNDataService demoService =
                isbn -> new Book(isbn, "Design Patterns", "GoF");
        StockManager sm = new StockManager(demoService);
        System.out.println("Locator for 1111111111 -> "
                + sm.getLocatorCode("1111111111")); // prints 1315
    }
}
