/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factorial;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class FileSaver {

    public void saveFactorial(String methodOfSolve, int solvedNumber, BigInteger number) {
        try {
            
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(methodOfSolve + "-" + solvedNumber + ".dat")));
            
            objectOutputStream.writeObject(number);
            
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
