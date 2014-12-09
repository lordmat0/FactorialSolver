package com.factorial;

import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class FactorialTimer implements FactorialSolver {

    private FactorialSolver solver;

    public FactorialTimer(FactorialSolver solver) {
        this.solver = solver;
    }

    @Override
    public BigInteger solve() {
        long now = System.currentTimeMillis();
        BigInteger result = solver.solve();

        System.out.println(solver.getName() + " Seconds " + ((double) System.currentTimeMillis() - now) / 1000);

        return result;
    }

    @Override
    public String getName() {
        return solver.getName();
    }

}
