package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) throws IllegalNumberException {
        if (number <= 1)
            throw new IllegalNumberException();
        for (int i = 2; i*i <= number ; i++)
            if (number % i == 0)
                return false;
        return true;
    }

    public int digitsSum(int number) {
        if (number == 0)
            return 0;
        return (number % 10 + digitsSum(number / 10));
    }
}

