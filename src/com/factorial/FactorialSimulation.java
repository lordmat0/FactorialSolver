/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factorial;

import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class FactorialSimulation {

    public static void main(String[] args) {

        System.out.println("Number of cores: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Solving " + Util.getLimit() + " factorial");

        long now = System.currentTimeMillis();
        
        FactorialSolver singleThreadedFactorial = new SingleThreadedFactorial();
        BigInteger singleThread = null;

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

        now = System.currentTimeMillis();

        BigInteger multiThreaded = new MultiThreadedFactorial().solve();

        System.out.println("Multi thread Seconds " + ((double) System.currentTimeMillis() - now) / 1000);

        switch(singleThread.compareTo(multiThreaded)){
            case -1:
                System.err.println("Is less than");
                break;
            case 0:
                System.out.println("Is equal");
                break;
            case 1:
                System.err.println("Is bigger");
        }
    }

}
