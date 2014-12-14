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

        //testSingleThreaded();
        //testSolver(new OptimizedSingleThreadedFacorial());
        
        //testSolver(new FactorialSaver(new FactorialLoader(new FactorialTimer(new SingleThreadedFactorial()))));
        testSolver(new FactorialSaver(new FactorialLoader(new FactorialTimer(new MultiThreadedFactorial()))));
        
        //testSolver(new MultiThreadedFactorial());
    }
    

    public void testSolver(FactorialSolver solver) {
        BigInteger result = solver.solve();
       
        testCorrectness(result);
    }

    public void testCorrectness(BigInteger testNumber) {
        if (singleThread != null) {
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
        }else{
            System.out.println("Unable to test as single thread is null");
        }

    }

    public static void main(String[] args) {
        new FactorialSimulation().startSimulation();
    }

}
