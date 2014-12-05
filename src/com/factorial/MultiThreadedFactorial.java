/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class MultiThreadedFactorial {

    private int threadToSolve;

    public MultiThreadedFactorial(){
        this(1000);
    }
    
    public MultiThreadedFactorial(int threadToSolve){
        this.threadToSolve = threadToSolve;
    }
    
    
    
    public BigInteger solve() {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        List<Future<BigInteger>> list = new ArrayList<Future<BigInteger>>();

        BigInteger current = BigInteger.ONE;

        int i = 1;
        while (true) {
            Callable<BigInteger> solver;
            if (i + threadToSolve < Max.NUMBER.getMax()) {
                solver = new Solver(i, i + threadToSolve);

                list.add(executor.submit(solver));

                i += threadToSolve + 1;
            } else {
                solver = new Solver(i, Max.NUMBER.getMax());

                list.add(executor.submit(solver));

                break;
            }
        }

        for (Future<BigInteger> future : list) {
            try {
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

}

class Solver implements Callable<BigInteger> {

    private final int min;
    private final int max;

    public Solver(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger sum = BigInteger.ONE;

        for (int i = min; i <= max; i++) {
            sum = sum.multiply(BigInteger.valueOf(i));
        }

        return sum;
    }

}
