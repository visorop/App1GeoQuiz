package com.visorop.app1geoquiz.helper;

import java.util.Random;

public class RandomIndexGenerator {

    private Random random;
    private int maxValue;

    public RandomIndexGenerator(){
        this.random = new Random();
        this.maxValue = 10;
    }

    public RandomIndexGenerator(int maxValue){
        this();
        this.maxValue = maxValue;
    }

    public int getNextValue(){
        return random.nextInt(maxValue);
    }

    public int getNextDifferentValue(int value){
        int newValue;

        do {
            newValue = random.nextInt(maxValue);
        } while (value == newValue);

        return newValue;
    }
}
