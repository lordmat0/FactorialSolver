package com.factorial;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author mat
 */
public class MultiThreadedFactorial implements FactorialSolver{

    private final int availableProcessors;
    private int threadToSolve;

    public MultiThreadedFactorial() {
        availableProcessors = Runtime.getRuntime().availableProcessors();
        threadToSolve = Util.getLimit() / availableProcessors;
        
    }

    @Override
    public BigInteger solve() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<BigInteger>> list = new ArrayList<Future<BigInteger>>();

        BigInteger current = BigInteger.ONE;

        int i = 1;
        while (true) {
            Callable<BigInteger> solver;
            if (i + threadToSolve < Util.getLimit()) {
                solver = new CallableSolver(i, i + threadToSolve);

                list.add(executor.submit(solver));

                i += threadToSolve + 1;
            } else {
                solver = new CallableSolver(i, Util.getLimit());

                list.add(executor.submit(solver));

                break;
            }
        }

        for (Future<BigInteger> future : list) {
            try {

                // Bottleneck here
                current = current.multiply(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        return current;
    }

    @Override
    public String getName() {
        return MultiThreadedFactorial.class.getSimpleName();
    }

}

class CallableSolver implements Callable<BigInteger> {

    private final int min;
    private final int max;
    private final int slice = 5000;

    public CallableSolver(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger bigSum = BigInteger.ONE;
        BigInteger sum = BigInteger.ONE;
        
        for (int i = min; i <= max; i += slice) {
            for (int k = i; k < i + slice && k <= max; k++) {
                sum = sum.multiply(BigInteger.valueOf(k));
            }
            bigSum = bigSum.multiply(sum);
            sum = BigInteger.ONE;
        }

        System.out.println("CallableSolver completed " + min + " " + max);

        return bigSum;
    }

}
