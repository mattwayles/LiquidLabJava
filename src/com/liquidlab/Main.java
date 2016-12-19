package com.liquidlab;

import javax.swing.SwingUtilities;
import com.liquidlab.model.*;
import com.liquidlab.view.*;
import com.liquidlab.controller.*;

/**
 * DatabaseInteraction.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/19/2016
 * This class runs the application and creates all the necessary interactive objects.
 */
public class Main {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Main::runApp);
    }

    private static void runApp() {
        DatabaseInteraction dbInt = new DatabaseInteraction();
        //UserInterface ui = new UserInterface(dbInt);
        //BusinessLogic bl = new BusinessLogic(ui, dbInt);

       // dbInt.selectFlavor("Cherry Coke");
    }
}
