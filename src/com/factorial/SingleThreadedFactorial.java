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
public class SingleThreadedFactorial {

    public BigInteger solve() {

        BigInteger current = new BigInteger("1");

        for (int i = 2; i <= Max.NUMBER.getMax(); i++) {
            current = current.multiply(BigInteger.valueOf(i));
        }

        return current;
    }
}
