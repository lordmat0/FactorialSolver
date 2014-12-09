package com.factorial;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class FactorialLoader implements FactorialSolver {

    private FactorialSolver solver;

    public FactorialLoader(FactorialSolver solver) {
        this.solver = solver;
    }

    @Override
    public BigInteger solve() {
        BigInteger result;
        if (factorialExists(solver.getName(), Util.getLimit())) {
            result = getFactorial(solver.getName(), Util.getLimit());
        } else {
            result = solver.solve();
        }

        return result;
    }

    @Override
    public String getName() {
        return solver.getName();
    }

    private boolean factorialExists(String methodOfSolve, int solvedNumber) {
        File file = new File(methodOfSolve + "-" + solvedNumber + ".dat");

        return file.isFile();
    }

    private BigInteger getFactorial(String methodOfSolve, int solvedNumber) {
        BigInteger result = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(methodOfSolve + "-" + solvedNumber + ".dat")));

            result = (BigInteger) objectInputStream.readObject();

            objectInputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
