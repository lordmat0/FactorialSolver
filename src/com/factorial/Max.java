/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.factorial;

/**
 *
 * @author mat
 */
public enum Max {
    NUMBER(100_000);
    
    private int number;
    
    private Max(int number){
        this.number = number;
    }
    
    public int getMax(){
        return number;
    }
}
