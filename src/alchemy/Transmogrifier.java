package alchemy;

public class Transmogrifier extends Device {

    /**********************************************************
     * Constructors
     **********************************************************/

    public Transmogrifier(IngredientContainer container, State state) {
        this.container = container;
        this.state = state;
    }


    /**********************************************************
     * Container
     **********************************************************/

    private IngredientContainer container;

    public IngredientContainer getContainer() {
        return container;
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
        AlchemicIngredient ingredient = this.getContainer().getIngredient();
        State stateIng = ingredient.getState();
        if (stateTransmogrifier != stateIng){
            return true;
        }
        return false;
    }


    /**********************************************************
     * Methods
     **********************************************************/

    protected void transmogrify(){
        AlchemicIngredient ingredient = this.getContainer().getIngredient();
        //oude container moet vernietigd worden
        if (this.canItBeTransmogrified()){
            ingredient.addPrefixState(this.getState());
            ingredient.changeState(this.getState());
        }
        //nieuwe container moet gemaakt worden
    }


}
