package com.factorial;

import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class SingleThreadedFactorial implements FactorialSolver {

    @Override
    public BigInteger solve() {

        BigInteger current = BigInteger.ONE;

        for (int i = 2; i <= Util.getLimit(); i++) {
            current = current.multiply(BigInteger.valueOf(i));
        }

        return current;
    }

    @Override
    public String getName() {
        return SingleThreadedFactorial.class.getSimpleName();
    }
}
