package com.liquidlab.view;

import javax.swing.JFrame;
import com.liquidlab.model.*;
import java.awt.*;

/**
 * UserInterface.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/19/2016
 * This class provides the View of the MVC relationship, providing and interactive user interface.
 */
public class UserInterface extends JFrame {
    private DatabaseInteraction dbInt;

    public UserInterface(DatabaseInteraction db) throws HeadlessException {
        super("LiquidLab");
        this.dbInt = db;
    }

}
