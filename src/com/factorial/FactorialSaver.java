package com.factorial;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

/**
 *
 * @author mat
 */
public class FactorialSaver implements FactorialSolver{

    private final FactorialSolver factorialSolver;
    
    public FactorialSaver(FactorialSolver factorialSolver){
        this.factorialSolver = factorialSolver;
    }
    
    private void saveFactorial(String methodOfSolve, int solvedNumber, BigInteger number) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(methodOfSolve + "-" + solvedNumber + ".dat")));
            
            objectOutputStream.writeObject(number);
            
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public BigInteger solve() {
        BigInteger result = factorialSolver.solve();
        
        saveFactorial(factorialSolver.getName(), Util.getLimit(), result);
        
        return result;                
    }

    @Override
    public String getName() {
        return factorialSolver.getName();
    }
}
