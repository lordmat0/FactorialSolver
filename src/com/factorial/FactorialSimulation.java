package com.factorial;

import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class FactorialSimulation {

    private BigInteger singleThread;

    public FactorialSimulation() {
    }

    public void startSimulation() {
        System.out.println("Number of cores: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Solving " + Util.getLimit() + " factorial");
        
        testSingleThreaded();
        testSolver(new OptimizedSingleThreadedFacorial());
        testSolver(new MultiThreadedFactorial());
    }

    public void testSingleThreaded() {
        long now = System.currentTimeMillis();

        FactorialSolver singleThreadedFactorial = new SingleThreadedFactorial();

        FileLoader fileLoader = new FileLoader();

        if (fileLoader.factorialExists(singleThreadedFactorial.getName(), Util.getLimit())) {
            singleThread = fileLoader.getFactorial(singleThreadedFactorial.getName(), Util.getLimit());
            System.out.println("Reading from file");
        } else {
            singleThread = singleThreadedFactorial.solve();

            FileSaver fileSaver = new FileSaver();
            fileSaver.saveFactorial(singleThreadedFactorial.getName(), Util.getLimit(), singleThread);
        }

        System.out.println("Single thread Seconds " + ((double) System.currentTimeMillis() - now) / 1000);
    }

    public void testSolver(FactorialSolver solver) {
        long now = System.currentTimeMillis();
        
        BigInteger result = solver.solve();

        System.out.println(solver.getName() + " Seconds " + ((double) System.currentTimeMillis() - now) / 1000);
        
        testCorrectness(result);
    }


    public void testCorrectness(BigInteger testNumber) {

        switch (singleThread.compareTo(testNumber)) {
            case -1:
                System.err.println("Is less than");
                break;
            case 0:
                System.out.println("Is equal (correct result)");
                break;
            case 1:
                System.err.println("Is bigger");
        }
    }

    public static void main(String[] args) {
        new FactorialSimulation().startSimulation();
    }

}
