package com.example.amnsh.enigmaandroid;


/*
This is the rotor class
the rotor is going to be an array
As of now, haven't looked at any source for this, so the rotor is going to be random.
Might not need a class for this. so, might change it later.
The rotor has an array and a counter
The counter is for efficiency so that we don't have to move the rotor but instead make it seem like it's rotating
 */
public class Rotor {

    int maxValue = 26;

    //Properties
    String letters;//This is the array of letters that will be used
    private int counter;//this is the counter mentioned above

    public Rotor(String letters){//this is constructing the rotor and initializing the array
        this.letters = letters;
        this.counter = 0;
    }

    public boolean increamentRotor(){//This increases the counter taking into account the upperlimit of 26 letters
        boolean toReturn = false;
        if(this.getCounter() + 1 >= maxValue) {
            toReturn = true;
        }
        this.setCounter((this.getCounter() + 1) % maxValue) ;
        return toReturn;
    }


    //Getters and Setters
    public int getCounter(){
        return this.counter;
    }

    public void setCounter(int counter){
        this.counter = counter % this.maxValue;
    }


}

