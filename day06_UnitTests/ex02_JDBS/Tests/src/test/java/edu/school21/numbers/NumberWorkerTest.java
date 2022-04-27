package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 11, 83, 97})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 12, 21, 63, 111})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -114, -55555})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> {
            new NumberWorker().isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void checkDigitSum(int number, int expected) {
        Assertions.assertEquals(expected, new NumberWorker().digitsSum(number));
    }

}
