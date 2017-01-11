package com.liquidlab;
//Package imports

/**
 * Main.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/22/2016
 * This class runs the application and creates all the necessary interactive objects.


 public class Main {
    //Create handles to all classes in MVC
    private static DatabaseInteraction dbInt = new DatabaseInteraction();
 private static UserInterface ui = new UserInterface(getDB());
    private static BusinessLogic bl = new BusinessLogic(getUI(), getDB());

    //Greate methods to retrieve class handles
    private static DatabaseInteraction getDB() {
        return dbInt;
    }

    private static UserInterface getUI() {
        return ui;
    }

    private static BusinessLogic getBL() {
        return bl;
    }


    //Main - run calculation (currently testing)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::runApp);
    }

    private static void runApp() {
        dbManipulations(getDB());
        getBL().calculate();

    }

    //Method to place data into datebase before UI is constructed.
    private static void dbManipulations(DatabaseInteraction dbInt) {
        dbInt.delete("*");
        //Inserts
        dbInt.insert("Cherry Coke", "5% Cola, 5% Maraschino Cherry", "A delightfully " +
                "carbonated Cherry Cola flavor.");
        dbInt.insert("Vanilla Coke", "5% Cola, 1% Vanilla Bean Ice Cream", "A creamy " +
                "Coke with a splash of Vanilla.");
        dbInt.insert("Placeholder", "50% Place, 50% Holder", "A place " +
                "for a nice holder.");
        dbInt.insert("Placeholder2", "50% Place, 50% Holder", "A place " +
                "for a nice secondary holder.");
        //Updates
        dbInt.update("Cherry Coke", "5% Cola, 3% Maraschino Cherry", "A delightfully carbonated Cherry " +
                "Cola flavor.");
        dbInt.update("Vanilla Coke", "5% Cola, 5% Vanilla Bean Ice Cream", "A creamy " +
                "Coke with a splash of Vanilla.");
        dbInt.update("Placeholder", "5% Place, 5% Holder", "A place " +
                "for a nice holder.");
        dbInt.update("Placeholder2", "5% Place, 5% Holder", "A place " +
                "for a nice secondary holder.");
        //Removals
        dbInt.delete("Placeholder");
        dbInt.delete("Placeholder2");
        dbInt.selectFlavor("*");
        dbInt.selectFlavor("Vanilla Coke");
        dbInt.selectFlavor("Cherry Coke");
    }
}
 */
