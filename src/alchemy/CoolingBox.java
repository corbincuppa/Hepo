package alchemy;

public class CoolingBox extends Device{

    /**********************************************************
     * Constructors
     **********************************************************/
    public CoolingBox(int temperature) {
        setTemperature(temperature);
    }


    /**********************************************************
     * Contents
     **********************************************************/
    // super(contents)


    /**********************************************************
     * Temperature
     **********************************************************/
    public final int temperature = 0;

    protected void setTemperature(int newTemp) {
        this.temperature += temperature;
    }



    /**********************************************************
     * Methods
     **********************************************************/



}
