package com.liquidlab;

/**
 * Flavor.java
 * Author: Matthew Blough-Wayles
 * Created: 12/22/2016
 * Edited: 12/22/2016
 * A flavor class containing a Name and a usage Percentage.
 */
public class Flavor {
    //Instance Variables
    private String myName;
    private double myPercentage;

    /**
     * Creates a default flavor option with no name and no percentage (will be assigned later)
     */
    public Flavor() {
        this.setName("No Name");
        this.setPercentage(0);
    }

    /**
     * Created a flavor option with supplied name and percentage
     *
     * @param name       The name of the flavor
     * @param percentage The percentage to be used in the recipe
     */
    public Flavor(String name, double percentage) {
        this.setName(name);
        this.setPercentage(percentage);
    }

    //Getters

    /**
     * Retrieves the flavor name
     *
     * @return The flavor name
     */
    public String getName() {
        return myName;
    }

    /**
     * Sets the flavor name
     *
     * @param str The flavor name to be set
     */
    public void setName(String str) {
        myName = str;
    }

    /**
     * Retrieves the percentage of the flavor to be used in the recipe
     *
     * @return The percentage of the flavor supplied by the user
     */
    public double getPercentage() {
        return myPercentage;
    }
    //Setters

    /**
     * Set the percentage to be used in the recipe
     *
     * @param num The percentage of a flavor to be used in the recipe
     */
    public void setPercentage(double num) {
        myPercentage = num;
    }

    /**
     * Retrieves the fractional equivalent of the percentage for use in calculations
     *
     * @return The fractional equivalent of the percentage supplied
     */
    public double getFraction() {
        return (myPercentage / 100);
    }
}
