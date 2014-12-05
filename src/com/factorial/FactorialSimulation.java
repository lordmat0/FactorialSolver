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
    
    public static void main(String []args){
        
        System.out.println("Solving " + Util.getLimit() + " factorial");
        
        long now = System.currentTimeMillis();
        BigInteger singleThread = new SingleThreadedFactorial().solve();
        
        System.out.println("Single thread Seconds " + ((double) System.currentTimeMillis() - now) / 1000);
        
        
        now = System.currentTimeMillis();        
        
        BigInteger multiThreaded = new MultiThreadedFactorial().solve();
        
        System.out.println("Multi thread Seconds " + ((double) System.currentTimeMillis() - now) / 1000);
        
        
        if(singleThread.equals(multiThreaded)){
            System.out.println("They are equal");
        }
    }
    
}
