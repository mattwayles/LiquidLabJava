package com.liquidlab.controller;
import com.liquidlab.model.*;
import com.liquidlab.view.*;

/**
 * BusinessLogic.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/19/2016
 * This class provides the Controller of the MVC relationship, dealing with logical calculations.
 */
public class BusinessLogic {
    private UserInterface ui;
    private DatabaseInteraction dbInt;

    public BusinessLogic(UserInterface ui, DatabaseInteraction db) {
        this.ui = ui;
        this.dbInt = db;
    }
}
