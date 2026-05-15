package alchemy;

import java.util.*;

import static java.lang.Long.sum;
import static java.lang.Math.abs;
import static java.util.Collections.min;

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
     * Use
     **********************************************************/

    @Override
    public void use(){
        // New ingredient with (which just stays in the Kettle) :
        // Name: ing[0] mixed with ing[1], ing[2], ..., and ing[n]
        // CHECK IF DIFF INGS, if not: not mixed!
        // first check if contents is of the same TYPE, else if all different -> put into string and mixedNames(String[])
        ArrayList<String> list = makeIntoList();
        String newName= AlchemicIngredient.mixedNames(list);
        if (newName.length() == 1) {
            // NEED TO ADD THE MULTIPLE INSTANCES TOGETHER
            alchemy.IngredientContainer container = new IngredientContainer(getIngredientWithName(newName));
            contents.clear();
            // Add container, which is then deleted
            add(container);
        }
        else {
            // State: state of ing with stdTemp closest to [0, 20], if multiple -> LIQUID>POWDER
            ArrayList<AlchemicIngredient> closest = getClosestToRoomTemp();
            ArrayList<State> states = new ArrayList<>();
            ArrayList<int[]> closestTemps = new ArrayList<>();
            ArrayList<Integer> closestSpoons = new ArrayList<>();
            for (AlchemicIngredient ing : closest) {
                State state = ing.getState();
                states.add(state);
                int[] stdTemp = ing.getIngredientType().getStdTemp();
                closestTemps.add(stdTemp);
                double spoons = ing.getQuantityAmount() * ing.getQuantityUnit().getAmountSpoons();
                closestSpoons.add((int)spoons);
            }
            State newState;
            if (states.size() == 1) {
                newState = states.get(0);
            }
            for (State state:states) {
                if (state instanceof State.LIQUID) {
                    newState = State.LIQUID;
                }
            }
            newState = State.POWDER;

            // StdState: State
            State newStdState = newState;

            // Quantity: amount is total amount (with conversion)
            int tempAmount = 0;
            for (AlchemicIngredient ing: contents) {
                double amount = ing.getQuantityAmount() * ing.getQuantityUnit().getAmountSpoons();
                tempAmount += amount;
            }
            int newAmount = UnitOfQuantity.getBestFitAmount(tempAmount, newState);
            UnitOfQuantity newUnit = UnitOfQuantity.getBestFitUnit(tempAmount, newState);

            // Temp: (amountSpoons1 * temp1 + .. + amountSpoonsN * tempN) / (amountSpoons1 + ... + amountSpoonsN)
            int sumSpoons = 0;
            for (int num:closestSpoons) {
                sumSpoons += num;
            }
            ArrayList<int[]> newTemps = new ArrayList<>();
            for (int i=0 ; i<closest.size() ; i++) {
                int[] t = closestTemps.get(i);
                int a = (int) closestSpoons.get(i);
                int tCold =(a*t[0])/sumSpoons;
                int tHot = (a*t[1])/sumSpoons;
                int[] newT = new int[]{tCold, tHot};
                newTemps.add(newT);
            }
            int sumCold = 0;
            int sumHot = 0;
            for (int i=0; i<newTemps.size()-1;i++){
                int[] cur = newTemps.get(i);
                int[] next = newTemps.get(i+1);
                sumCold += cur[0] + next[0];
                sumHot += cur[1] + next[1];
            }
            int[] newTemp = new int[]{sumCold-sumHot, 0};


            // StdTemp: stdTemp of ing with temp closest to [0, 20], multiple -> warmest
            int[] newStdTemp = null;
            for (int i=0 ; i<closestTemps.size()-1 ; i++) {
                int[] stdTemp = closestTemps.get(i);
                int hotness = stdTemp[1];
                int[] nextStdTemp = closestTemps.get(i+1);
                int nextHotness = nextStdTemp[1];
                if (hotness > nextHotness) {
                    newStdTemp = stdTemp;
                }
                newStdTemp = nextStdTemp;
            }

            IngredientTypeMixed newIngType = new IngredientTypeMixed(newName, newStdState, newStdTemp);
            AlchemicIngredient newIngredient = new AlchemicIngredient(newIngType, newAmount, newUnit);
        }
    }

    /**
     * Check if there are alchemic ingredient of the same type inside this kettle.
     *
     * @return  True if
     */
    protected boolean areSameIngType() {
        for (int i=0; i< contents.size(); i++) {
            IngredientType firstType = contents.get(i).getIngredientType();
            for (int j=0; j< contents.size(); j++) {
                IngredientType secondType = contents.get(j).getIngredientType();
                if (firstType == secondType) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Make a list of the simple names of the alchemic ingredients in this kettle.
     *
     * @return  The list of simple names of the given alchemic ingredients.
     * @effect  If the amount of alchemic ingredients inside this kettle is greater than or equal to 2, then
     *          for each alchemic ingredient in the contents of this kettle, if the simple name is not already
     *          inside the list, the simple name is added to the list.
     *          | if (contents.size() >= 2) then
     *          |   for ing in contents
     *          |       if !names.contains(ing.getSimpleName()) then
     *          |           names.add(ing.getSimpleName())
     */
    protected ArrayList<String> makeIntoList(){
        if(contents.size() >= 2) {
            ArrayList<String> names = new ArrayList<>();
            for (AlchemicIngredient ing : contents) {
                String simpleName = ing.getSimpleName();
                if (!names.contains(simpleName)) {
                    names.add(simpleName);
                }
            }
            return names;
        }
    }

    /**
     * Check if an ingredient with the given name is inside this kettle.
     *
     * @param   name
     *          The given name of the alchemic ingredient to be checked
     * @return  True if, checking the name of each ingredient in the kettle, the given name matches the name of one of
     *          the ingredients in the kettle, false otherwise.
     *          | for each ingredient in the contents of this kettle
     *          |   result == ingredient.getName().equals(name)
     */
    public boolean isIngredientInKettle(String name) {
        for (AlchemicIngredient ingredient: contents) {
            String ingName = ingredient.getSimpleName();
            // Look for the given name
            if (ingName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the ingredient with the given name.
     *
     * @pre     The given name must be the name of an ingredient which is indeed in this kettle
     *          | isIngredientInKettle()
     * @param   name
     *          The given name of the ingredient to be found
     * @return  If the given name is a name for an ingredient which is in this kettle, then
     *          the ingredient with that name is returned.
     *          | if isIngredientInStorage(name)
     *          |   for each ingredient in contents
     *          |       if ingredient.getSimpleName().equals(name)
     *          |           result = ingredient
     * @throws NoSuchElementException
     *          | ! isIngredientInKettle()
     */
    public AlchemicIngredient getIngredientWithName(String name) throws NoSuchElementException {
        if (isIngredientInKettle(name)) {
            for (AlchemicIngredient ingredient:contents) {
                String ingName = ingredient.getSimpleName();
                // Look for the given name
                if (ingName.equals(name)) {
                    return ingredient;
                }
            }
        }
        throw new NoSuchElementException(name);
    }

    /**
     * Get all the temperatures of all alchemic ingredients in the contents of this kettle.
     */
    protected ArrayList<int[]> getTemps() {
        ArrayList<int[]> list = new ArrayList<>();
        for (AlchemicIngredient ing: contents) {
            int[] temp = ing.getIngredientType().getStdTemp();
            list.add(temp);
        }
        return list;
    }

    protected ArrayList<AlchemicIngredient> getClosestToRoomTemp() {
        ArrayList<AlchemicIngredient> list = new ArrayList<>();
        List<Integer> listErrors = new ArrayList<>();
        int[] roomTemp = {0, 20};
        int roomTempColdness = 0;
        int roomTempHotness = 20;
        for (AlchemicIngredient ing:contents) {
            int[] temp = ing.getIngredientType().getStdTemp();
            int error = abs((temp[0] - roomTempColdness) + (temp[1] - roomTempHotness));
            listErrors.add(error);
        }
        Set<Integer> set = new HashSet<Integer>(listErrors);
        if(set.size() < listErrors.size()){
            // There are duplicates
            int min = min(listErrors);
            int range = set.size() - listErrors.size();
            for (int i=0; i<range; i++) {
                int min1 = min(listErrors);
                while(min == min1) {
                    int index = listErrors.indexOf(min1);
                    AlchemicIngredient closest = contents.get(index);
                    listErrors.add(index, 10000);
                    list.add(closest);
                }
            }
        }
        return list;
    }

}
