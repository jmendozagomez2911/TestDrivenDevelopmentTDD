package io.github.josemanuel;

public class ValidateISBN {

    public static void main(String[] args) {
        ValidateISBN validator = new ValidateISBN();

        String isbn = "9780134685991";
        boolean valid = validator.isValid(isbn);

        if (valid) {
            System.out.println("The ISBN " + isbn + " is valid.");
        } else {
            System.out.println("The ISBN " + isbn + " is invalid.");
        }
    }

    public boolean isValid(String isbn) {
        return isbn.length() == 13 && isbn.matches("\\d+");
    }
}
