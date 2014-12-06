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
public class FileLoader {
    
    
    public boolean factorialExists(String methodOfSolve, int solvedNumber){
        File file = new File(methodOfSolve + "-" + solvedNumber + ".dat");
        
        return file.isFile();
    }

    public BigInteger getFactorial(String methodOfSolve, int solvedNumber) {
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
