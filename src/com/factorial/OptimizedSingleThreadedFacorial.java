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
public class OptimizedSingleThreadedFacorial implements FactorialSolver {

    private final int slice = 5000;
    
    @Override
    public BigInteger solve() {
        BigInteger bigSum = BigInteger.ONE;
        BigInteger sum = BigInteger.ONE;

        for (int i = 2; i <= Util.getLimit(); i+= slice) {
            for(int k = i; k < i + slice && k <= Util.getLimit(); k++){
                sum = sum.multiply(BigInteger.valueOf(k));
            }
            bigSum = bigSum.multiply(sum);
            sum = BigInteger.ONE;
        }

        return bigSum;
    }

    @Override
    public String getName() {
        return OptimizedSingleThreadedFacorial.class.getName();
    }

}
