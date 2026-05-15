package alchemy;

import java.util.ArrayList;

public class Transmogrifier extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/

    public Transmogrifier(ArrayList<IngredientContainer> contents, State state) throws IllegalArgumentException {
        super(contents);
        this.state = state;
    }


    /**********************************************************
     * Container
     **********************************************************/

    @Override
    public void add(ArrayList<IngredientContainer> containers) throws IllegalArgumentException {
        int length = containers.size();
        if (length != 1){
            throw new IllegalArgumentException("You can only put one thing in the transmogrifier at a time. Current number of items : " + length);
        }
        for (int i = 0; i < length; i++){
            this.getContents().add(containers.get(i).getIngredient());
            containers.get(i).terminate();
        }
    }


    /**********************************************************
     * State
     **********************************************************/

    private State state;

    public State getState() {
        return state;
    }

    private boolean canItBeTransmogrified(){
        State stateTransmogrifier = this.getState();
        AlchemicIngredient ingredient = this.getContents().get(0);
        State stateIng = ingredient.getState();
        if (stateTransmogrifier != stateIng){
            return true;
        }
        return false;
    }


    /**********************************************************
     * Methods
     **********************************************************/

    @Override
    public void use(){
        AlchemicIngredient ingredient = this.getContents().get(0);
        if (this.canItBeTransmogrified()){
            ingredient.addPrefixState(this.getState());
            ingredient.changeState(this.getState());
        }
    }
}
