package alchemy;

import java.util.ArrayList;

public class Kettle extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/



    /**********************************************************
     * Contents
     **********************************************************/

    /**
     * Variable referencing the contents of this kettle.
     */
    private final ArrayList<AlchemicIngredient> contents = new ArrayList<>();


    /**********************************************************
     * Temperature
     **********************************************************/
    // geen temp


    /**********************************************************
     * Use
     **********************************************************/

    @Override
    public void use(){
        // New ingredient with (which just stays in the Kettle) :
        // Name: ing[0] mixed with ing[1], ing[2], ..., and ing[n]
        // CHECK IF DIFF INGS, if not: not mixed!



        if (contents.size() == 2) {
            AlchemicIngredient firstIng = contents.get(0);
            String firstName = firstIng.getName();
            AlchemicIngredient secondIng = contents.get(1);
            String secondName = secondIng.getName();
            // ing[0] mixed with ing[1]
            String name = firstName + " mixed with " + secondName;
        }
        if (contents.size() == 3) {

        }
        if (contents.size() > 3) {

        }

        AlchemicIngredient.mixedNames();

        // State: state of ing with stdTemp closest to [0, 20], if multiple -> LIQUID>POWDER
        // StdState: State
        // Quantity: amount is total amount (with conversion)
        // Temp: (amountSpoons1 * temp1 + .. + amountSpoonsN * tempN) / (amountSpoons1 + ... + amountSpoonsN)
        // StdTemp: stdTemp of ing with temp closest to [0, 20], multiple -> warmest
    }



}
