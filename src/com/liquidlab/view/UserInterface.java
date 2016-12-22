package com.liquidlab.view;

import com.liquidlab.model.DatabaseInteraction;

import javax.swing.*;
import java.awt.*;

/**
 * UserInterface.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/19/2016
 * This class provides the View of the MVC relationship, providing and interactive user interface.
 */
public class UserInterface extends JFrame {
    //Database Variables
    private DatabaseInteraction dbInt;

    /**
     * Creates a user interface with a connection to the DatabaseInteraction class
     *
     * @param db Connection to DatabaseInteraction class, allowing for database querying
     * @throws HeadlessException Thrown when database cannot be accessed or manipulated
     */
    public UserInterface(DatabaseInteraction db) throws HeadlessException {
        super("LiquidLab");
        this.setDB(dbInt);

    }
    //Getter methods for variables

    /**
     * Allows communication with the DatabaseInteraction class
     *
     * @return The database containing relevant data for this operation
     */
    public DatabaseInteraction getDB() {
        return dbInt;
    }

    //Setter methods for variables

    /**
     * Sends information to the DatabaseInteraction
     *
     * @param db The database to send information to.
     */
    private void setDB(DatabaseInteraction db) {
        dbInt = db;
    }

}
