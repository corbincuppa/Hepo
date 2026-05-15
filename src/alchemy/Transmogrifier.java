package alchemy;

import java.util.ArrayList;

public class Transmogrifier extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/

    public Transmogrifier(ArrayList<IngredientContainer> contents, State state) {
        super(contents);
        this.state = state;
    }


    /**********************************************************
     * Container
     **********************************************************/

    public AlchemicIngredient getAlchemicIngredient() {
        int length = this.getAlchemicIngredients().size();
        if (length != 1){
            throw new IllegalArgumentException("You can only put one thing in the transmogrifier at a time. Current number of items : " + length);
        }
        return this.getAlchemicIngredients().get(0);
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
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
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
        AlchemicIngredient ingredient = this.getAlchemicIngredient();
        IngredientContainer oldContainer = this.getContents().get(0);
        UnitOfQuantity capacity = oldContainer.getCapacity();
        if (this.canItBeTransmogrified()){
            ingredient.addPrefixState(this.getState());
            ingredient.changeState(this.getState());
        }
        new IngredientContainer(capacity, ingredient);
    }


}
