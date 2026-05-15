package alchemy;

import be.kuleuven.cs.som.annotate.Model;

import java.util.ArrayList;

public class Transmogrifier extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/
    /**
     * Initialize a new transmogrifier with given contents and state.
     *
     * @param  	contents
     *         	The contents of the new device.
     * @param  	state
     *         	The state of the new transmogrifier.
     * @effect 	The transmogrifier is initialized as a devices
     * 			(contents is set)
     * 			| super(contents)
     * @post	The state of this new transmogrifier is set to the given state.
     * 			| new.getState == state
     */
    public Transmogrifier(ArrayList<IngredientContainer> contents, State state) throws IllegalArgumentException {
        super(contents);
        this.state = state;
    }


    /**********************************************************
     * Container
     **********************************************************/

    /**
     * Set the contents of this device to the alchemic ingredients in the given containers.
     *
     * @param   containers
     *          The given containers
     * @effect  The contents of this device is expanded with the alchemic ingredients in the given containers.
     *          | for each container in containers
     *          |    contents.add(containers.get(i).getIngredient())
     * @thows   IllegalArgumentException
     *          | containers.size() != 1
     */
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

    /**
     * Variable referencing the state of this transmogrifier.
     */
    private State state;

    /**
     * Return the state of the transmogrifier.
     */
    @Model
    public State getState() {
        return state;
    }

    /**
     * Check whether the state of the transmogrifier
     * is not equal the state of the contents
     *
     * @return  False if the state of the transmogrifier equals the state of the contents
     *          True otherwise.
     *          | if getState() == getContents().get(0).getState()
     *          |   return false
     *          | else return true
     */
    protected boolean canItBeTransmogrified(){
        State stateTransmogrifier = this.getState();
        AlchemicIngredient ingredient = this.getContents().getLast();
        State stateIng = ingredient.getState();
        if (stateTransmogrifier != stateIng){
            return true;
        }
        return false;
    }


    /**********************************************************
     * Methods
     **********************************************************/

    /**
     * Transmogrify the contents to the given state if the state of the transmogrifier does not equal the state of the contents.
     *
     * @effect  The full name of the alchemic ingredient is the prefix "Liquid" added if the given state is liquid.
     *          | if canItBeTransmogrified() && getState == State.LIQUID
     *          | then getContents.get(0).getFullName.equals("Liquid"+getContents.get(0).getSimpleName())
     * @effect  The full name of the alchemic ingredient is the prefix "Powdered" added if the given state is powder.
     *          | if canItBeTransmogrified() && getState == State.POWDER
     *          | then getContents.get(0).getFullName.equals("Powdered"+getContents.get(0).getSimpleName())
     * @effect  The state of the alchemic ingredient is set to the state of the transmogrifier.
     *          | if canItBeTransmogrified()
     *          | then getState().equals(getContents.get(0).getState())
     */
    @Override
    public void use(){
        AlchemicIngredient ingredient = this.getContents().get(0);
        if (this.canItBeTransmogrified()){
            ingredient.addPrefixState(this.getState());
            ingredient.changeState(this.getState());
        }
    }
}
