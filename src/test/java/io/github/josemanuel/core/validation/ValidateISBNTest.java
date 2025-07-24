package io.github.josemanuel.core.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Pure function test: no doubles required.
 */
class ValidateISBNTest {
    @ParameterizedTest
    @CsvSource({"0140449116,true", "0140449117,false", "0134685997,true"})
    void validates10(String isbn, boolean expected) {
        assertThat(new ValidateISBN().isValid10(isbn)).isEqualTo(expected);
    }
}
