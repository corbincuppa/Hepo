package alchemy;

import alchemy.State;
import java.util.*;
import static java.lang.Math.abs;
import static java.util.Collections.min;

public class Kettle extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/
    public Kettle(ArrayList<IngredientContainer> contents) {
        super(contents);
    }


    /**********************************************************
     * Use
     **********************************************************/

    /**
     * Use this kettle.
     *
     * @effect  The name of the new ingredient is set to the mixed name of all
     *          ingredients: thefirst ingredient's name followed by
     *          'mixed with' and the remaining names separated by commas and 'and'.
     *          If all ingredients share the same simple name, the name remains unchanged.
     *
     * @effect  The state of the new alchemic ingredient is set to the standard state
     *          of the base ingredient whose standard temperature lies closest to [0, 20].
     *          If multiple ingredients are equally close, LIQUID takes priority over POWDER.
     *
     * @effect  The standard state of the new ingredient type is set to the state
     *          the new ingredient assumes after mixing.
     *
     * @effect  The temperature of the new alchemic ingredient is set to the weighted
     *          average of the temperatures of all mixed ingredients, weighted by their
     *          quantity in spoons.
     *
     * @effect  The standard temperature of the new ingredient type is set to the
     *          standard temperature of the ingredient type whose standard temperature
     *          lies closest to [0, 20]. If multiple candidates are equally close,
     *          the warmest standard temperature is chosen.
     *
     * @effect  The quantity of the new alchemic ingredient is set to the total combined
     *          quantity of all mixed ingredients in spoons, converted to the best fitting
     *          unit. If the resulting state differs from one or more base ingredients,
     *          fractional spoons from those ingredients are summed and rounded down.
     */
    @Override
    public void use(){
        // New ingredient with (which just stays in the Kettle) :
        // Name: ing[0] mixed with ing[1], ing[2], ..., and ing[n]
        ArrayList<String> list = makeIntoList();
        String newName= AlchemicIngredient.mixedNames(list);

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
            if (state == State.LIQUID) {
                newState = State.LIQUID;
            }
        }
        newState = State.POWDER;

        // StdState: State
        State newStdState = newState;

        // Quantity: amount is total amount (with conversion)
        int tempAmount = 0;
        for (AlchemicIngredient ing: this.getContents()) {
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

        // Check if the ingredients in the contents are the same ingredient type, then the mixedName will just be one name.
        if (newName.length() == 1) {
            AlchemicIngredient oldIngredient = getIngredientWithName(newName);
            IngredientType newIngType = oldIngredient.getIngredientType();

            AlchemicIngredient newIngredient = new AlchemicIngredient(newIngType, newAmount, newUnit);

            alchemy.IngredientContainer container = new IngredientContainer(newIngredient);
            ArrayList<IngredientContainer> listContainer = new ArrayList<IngredientContainer> (Arrays.asList(container));
            this.getContents().clear();
            // Add container, which is then deleted
            add(listContainer);
        }

        IngredientTypeMixed newIngType = new IngredientTypeMixed(newName, newStdState, newStdTemp);
        AlchemicIngredient newIngredient = new AlchemicIngredient(newIngType, newAmount, newUnit);
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
     * @throws  IllegalArgumentException
     *          | contents.size() < 2
     */
    protected ArrayList<String> makeIntoList(){
        if(canHaveAsContentsToMix()) {
            ArrayList<String> names = new ArrayList<>();
            for (AlchemicIngredient ing : this.getContents()) {
                String simpleName = ing.getSimpleName();
                if (!names.contains(simpleName)) {
                    names.add(simpleName);
                }
            }
            return names;
        }
        throw new IllegalArgumentException("A kettle must have more than one ingredient present to mix.");
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
        for (AlchemicIngredient ingredient: this.getContents()) {
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
            for (AlchemicIngredient ingredient:this.getContents()) {
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
        for (AlchemicIngredient ing: this.getContents()) {
            int[] temp = ing.getIngredientType().getStdTemp();
            list.add(temp);
        }
        return list;
    }

    /**
     * Get the alchemic ingredients inside the contents of this kettle with
     * the standard temperature that is the closest to [0, 20] (room temperature).
     *
     * @return  For each ingredient in contents, the difference between the temperature and the room temperature is stored
     *          in a list. The minimum of that list is taken and the ingredients whose temperature is closer to the
     *          room temperature is added to the list of ingredients which is returned.
     */
    protected ArrayList<AlchemicIngredient> getClosestToRoomTemp() {
        ArrayList<AlchemicIngredient> list = new ArrayList<>();
        List<Integer> listErrors = new ArrayList<>();
        int[] roomTemp = {0, 20};
        int roomTempColdness = 0;
        int roomTempHotness = 20;
        for (AlchemicIngredient ing:this.getContents()) {
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
                    AlchemicIngredient closest = this.getContents().get(index);
                    listErrors.add(index, 10000);
                    list.add(closest);
                }
            }
        }
        return list;
    }

    /**
     * Check if this kettle has enough ingredients inside it to mix.
     *
     * @return  True is there are two or more ingredients inside this kettle, false otherwise.
     *          | result == ( this.contents.size() >= 2 )
     */
    protected boolean canHaveAsContentsToMix(){
        if (this.getContents().size()<2){
            return false;
        }
        return true;
    }

}
