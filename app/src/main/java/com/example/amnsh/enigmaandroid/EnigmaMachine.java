package com.example.amnsh.enigmaandroid;



/*
This is the main class that is going to be the EnigmaMachine
The enigma machine has in input keyboard that hasn't been initialized here
The machine has an output that also hasn't been initialized here

Components initialized here
Rotors:
There are three rotors in the enigma machine. From the Rotor class, there are three rotors initialized here
Each rotor is going to encrypt a character into a different character
Instead of working with the characters directly, the position of the characters is going to be used in calculation
For example, A is 0, B is 1 and so on. All characters are converted to capital and all punctuation and spaces are omitted for now
(We can change this later)
Please look at the Rotor class for further clarification


Static array
This is not necessarily an object
It's just a part that gives us the corresponding number.
Again, we are working with numbers and the numbers will be converted to respective characters
This will be referenced multiple times
Was considering using hashmap but we need it both ways, so, I'm suggesting an array


Reflector
Haven't figured out the functionality of it in complete detail but am using another array that just swaps two letters

 */

public class EnigmaMachine {

    //Rotors
    Rotor firstRotor;
    Rotor secondRotor;
    Rotor thirdRotor;

    String staticRotor;

    String reflector;

    String plugBoardValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //HashMap<Character, Character> reflector;

    public EnigmaMachine(Rotor firstRotor, Rotor secondRotor, Rotor thirdRotor, String plugBoardValues){
        this.firstRotor = firstRotor;
        this.secondRotor = secondRotor;
        this.thirdRotor = thirdRotor;

        this.staticRotor = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//This is just to initialize the staticRotor array

        this.reflector = "NPRTVXZOQSUWYAHBICJDKELFMG";

        this.plugBoardValues = plugBoardValues;
    }


    /*
    This is going to be the main encryption process
    Here, we loop through each character in the input
    for each character, the following is done
    1)rotor one is rotated
    2)
     */

    //Counter goes down??
    public String encryption(String input){
        String output = "";
        for(int i =0 ; i < input.length() ; i++){
            if(this.firstRotor.increamentRotor()) {
                if(this.secondRotor.increamentRotor()) {
                    this.thirdRotor.increamentRotor();
                }
            }
            int fromstaticRotor = staticRotor.indexOf(input.charAt(i));
            char inputFromPlugBoard = plugBoardValues.charAt(fromstaticRotor);
            //int fromFirst = firstRotor.letters.indexOf(staticRotor.charAt((firstRotor.getCounter()+staticRotor.indexOf(input.charAt(i)))%firstRotor.maxValue));
            int fromFirst = firstRotor.letters.indexOf(staticRotor.charAt((firstRotor.getCounter()+staticRotor.indexOf(inputFromPlugBoard))%firstRotor.maxValue));
            int fromSecond = secondRotor.letters.indexOf(staticRotor.charAt((fromFirst + secondRotor.getCounter())%secondRotor.maxValue));
            int fromThird = thirdRotor.letters.indexOf(staticRotor.charAt((fromSecond + thirdRotor.getCounter())%thirdRotor.maxValue));

            char charFromReflector = this.staticRotor.charAt(this.reflector.indexOf(thirdRotor.letters.charAt(fromThird)));
            //char charFromReflector = this.reflector.get(thirdRotor.letters.charAt(fromThird));
            //char charFromReflector = this.staticRotor.charAt(this.reflector.indexOf(thirdRotor.letters.charAt((fromThird + thirdRotor.getCounter())%thirdRotor.maxValue)));

            fromThird = staticRotor.indexOf(charFromReflector);
            fromSecond = staticRotor.indexOf(secondRotor.letters.charAt(((((fromThird - thirdRotor.getCounter()) % thirdRotor.maxValue) + thirdRotor.maxValue ) % thirdRotor.maxValue)));
            fromFirst = staticRotor.indexOf(firstRotor.letters.charAt(((((fromSecond - secondRotor.getCounter()) % secondRotor.maxValue) + secondRotor.maxValue ) % secondRotor.maxValue)));

            char finalResult = staticRotor.charAt(((((fromFirst - firstRotor.getCounter()) % firstRotor.maxValue) + firstRotor.maxValue ) % firstRotor.maxValue));

            //Might need to remove the following ONE line
            finalResult = plugBoardValues.charAt(staticRotor.indexOf(finalResult));

            output = output+ finalResult;
        }

        return output;
    }

}



