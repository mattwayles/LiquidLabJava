package com.liquidlab.controller;

import com.liquidlab.Flavor;
import com.liquidlab.model.DatabaseInteraction;
import com.liquidlab.view.UserInterface;

import java.util.ArrayList;

/**
 * BusinessLogic.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/22/2016
 * This class provides the Controller of the MVC relationship, dealing with logical calculations. It handles
 * parsing of data extracted from the DatabaseInteraction class as well as commands sent from the User Interface.
 * Finally, all mathematical calculations used to produce liquid gram measurements are achieved here as well.
 */


///////******Once buttons are created, could these all pass as parameters instead of variables?? ***********//

public class BusinessLogic {
    private static double flavorWeight;
    private static double nicWeight;
    private static double pgWeight;
    private static double vgWeight;
    //Handles to other classes
    private UserInterface myUI;
    private DatabaseInteraction myDB;
    //Total volume
    private double mlToMake;
    //Flavor variables
    private double flavorMl;
    private double flavorGrams;
    private double flavorPercent;
    private double flavorMlTotal;
    private ArrayList<Flavor> flavors;
    //NIC Variables
    private double nicMl;
    private double nicAmt;
    private double nicGrams;
    private double nicTarget;
    private double nicBasePg;
    private double nicBaseVg;
    private double nicStrength;
    //PG Variables
    private double pgMl;
    private double pgGrams;
    private double pgTarget;
    private double pgPercent;
    //VG Variables
    private double vgMl;
    private double vgGrams;
    private double vgTarget;
    private double vgPercent;

    /**
     * Creates a BusinessLogic calculator to interface with the database and user interface.
     *
     * @param ui The user interface that is sending commands to the BusinessLogic calculator.
     * @param db The database that holds recipes information utilized by the BusinessLogic calculator.
     */
    public BusinessLogic(UserInterface ui, DatabaseInteraction db) {
        this.setUI(ui);
        this.setDB(db);
        this.setFlavors(new ArrayList<>());
    }

    //Get Class handles

    /**
     * Retrieves flavorWeight class variable
     *
     * @return flavorWeight The user-defined weight of the flavors used (ml/g).
     */
    private static double getFlavorWeight() {
        return flavorWeight;
    }

    /**
     * Setss flavorWeight class variable
     *
     * @param flwt The user-defined weight of the flavors used (ml/g).
     */
    private static void setFlavorWeight(double flwt) {
        flavorWeight = flwt;
    }
    //Get Volume

    /**
     * Retrieves nicWeight class variable
     *
     * @return nicWeight The user-defined weight of the nicotine being used in the recipe.
     */
    private static double getNicWeight() {
        return nicWeight;
    }
    //Getters - Flavors

    /**
     * Sets nicWeight class variable
     *
     * @param ncwt The user-defined weight of the nicotine being used in the recipe.
     */
    private static void setNicWeight(double ncwt) {
        nicWeight = ncwt;
    }

    /**
     * Retrieves pgWeight class variable
     *
     * @return pgWeight The user-defined weight of the PG being used in the recipe.
     */
    private static double getPgWeight() {
        return pgWeight;
    }

    /**
     * Sets pgWeight class variable
     *
     * @param pgwt The user-defined weight of the PG being used in the recipe.
     */
    private static void setPgWeight(double pgwt) {
        pgWeight = pgwt;
    }

    /**
     * Retrieves vgWeight class variable
     *
     * @return vgWeight The user-defined weight of the vG being used in the recipe.
     */
    private static double getVgWeight() {
        return vgWeight;
    }

    /**
     * Sets vgWeight class variable
     *
     * @param vgwt The user-defined weight of the VG being used in the recipe.
     */
    private static void setVgWeight(double vgwt) {
        vgWeight = vgwt;
    }

    /**
     * Allows communication with the UserInterface class
     *
     * @return myUI    The UserInterface instance communicating with this BusinessLogic calculator.
     */
    private UserInterface getUI() {
        return myUI;
    }
    //Getters - Nic

    /**
     * Sends communications to the UserInterface class
     *
     * @param userInt The UserInterface instance communicating with this BusinessLogic calculator.
     */
    private void setUI(UserInterface userInt) {
        myUI = userInt;
    }

    /**
     * Allows communication with the DatabaseInteraction class
     *
     * @return myDB The DatabaseInteraction class communicating with this BusinessLogic calculator.
     */
    private DatabaseInteraction getDB() {
        return myDB;
    }

    /**
     * Sends communication to the DatabaseInteraction class
     *
     * @param dbInt The DatabaseInteraction class communicating with this BusinessLogic calculator.
     */
    private void setDB(DatabaseInteraction dbInt) {
        myDB = dbInt;
    }

    /**
     * Retrieves mlToMake instance variable
     *
     * @return mlToMake The total amount of liquid to be produced.
     */
    private double getMlToMake() {
        return mlToMake;
    }

    /**
     * Sets flavorMl instance variable
     *
     * @param mltm The amount of flavor required (in ML) for the recipe.
     */
    private void setMlToMake(double mltm) {
        mlToMake = mltm;
    }

    /**
     * Retrieves flavorMl instance variable
     *
     * @return flavorMl The amount of flavor required (in ML) for the recipe.
     */
    private double getFlavorMl() {
        return flavorMl;
    }

    /**
     * Sets flavorPercent instance variable
     *
     * @param flml The desired percentage of a flavor in the recipe.
     */
    private void setFlavorMl(double flml) {
        flavorMl = flml;
    }

    /**
     * Retrieves flavorPercent instance variable
     *
     * @return flavorPercent The desired percentage of a flavor in the recipe.
     */
    private double getFlavorPercent() {
        return flavorPercent;
    }
    //Getters - PG

    /**
     * Sets flavorPercent instance variable
     *
     * @param flpc The desired percentage of a flavor in the recipe.
     */
    private void setFlavorPercent(double flpc) {
        flavorPercent = flpc;
    }

    /**
     * Retrieves flavorMlTotal instance variable
     *
     * @return flavorMlTotal The total amount of all flavors in the recipe
     */
    private double getFlavorMlTotal() {
        return flavorMlTotal;
    }

    /**
     * Sets flavorMlTotal instance variable
     *
     * @param fltl The total amount of all flavors in the recipe
     */
    private void setFlavorMlTotal(double fltl) {
        flavorMlTotal = fltl;
    }

    /**
     * Retrieves flavorGrams instance variable
     *
     * @return flavorGrams The amount of individual flavor needed, in grams.
     */
    private double getFlavorGrams() {
        return flavorGrams;
    }

    /**
     * Sets flavorGrams instance variable
     *
     * @param flgr The amount of individual flavor needed, in grams.
     */
    private void setFlavorGrams(double flgr) {
        flavorGrams = flgr;
    }
    //Getters - VG

    /**
     * Retrieves flavors instance ArrayList
     *
     * @return flavors  A list of all flavors retrieved from a DataBaseInteraction query
     */
    private ArrayList<Flavor> getFlavors() {
        return flavors;
    }

    /**
     * Creates a new flavors instance ArrayList
     *
     * @param flal A list of all flavors retrieved from a DataBaseInteraction query
     */
    private void setFlavors(ArrayList<Flavor> flal) {
        flavors = flal;
    }

    /**
     * Retrieves nicAmt instance variable
     *
     * @return nicAmt The user-defined amount of nicotine to be used in the recipe, in mg/ml
     */
    private double getNicAmt() {
        return nicAmt;
    }

    /**
     * Sets nicAmt instance variable
     *
     * @param ncamt The user-defined amount of nicotine to be used in the recipe, in mg/ml
     */
    private void setNicAmt(double ncamt) {
        nicAmt = ncamt;
    }

    /**
     * Retrieves nicTarget instance variable
     *
     * @return nicTarget The amount of nicotine required to reach the desired percentage for total volume selected.
     */
    private double getNicTarget() {
        return nicTarget;
    }


    //Setters - Class Handles

    /**
     * Sets nicTarget instance variable
     *
     * @param nctg The amount of nicotine required to reach the desired percentage for total volume selected.
     */
    private void setNicTarget(double nctg) {
        nicTarget = nctg;
    }

    /**
     * Retrieves nicMl instance variable
     *
     * @return nicMl The amount of nicotine contained in the recipe, in ML.
     */
    private double getNicMl() {
        return nicMl;
    }
    //Setters - Volume

    /**
     * Sets nicMl instance variable
     *
     * @param ncml The amount of nicotine contained in the recipe, in ML.
     */
    private void setNicMl(double ncml) {
        nicMl = ncml;
    }
    //Setters - Flavors

    /**
     * Retrieves nicStrength instance variable
     *
     * @return nicStrength The strength of the nicotine being used in the recipe, in mg/ml
     */
    private double getNicStrength() {
        return nicStrength;
    }

    /**
     * Sets nicStrength instance variable
     *
     * @param ncst The strength of the nicotine being used in the recipe, in mg/ml
     */
    private void setNicStrength(double ncst) {
        nicStrength = ncst;
    }

    /**
     * Retrieves nicGrams instance variable
     *
     * @return nicGrams The amount of nicotine being used in the recipe, in grams.
     */
    private double getNicGrams() {
        return nicGrams;
    }

    /**
     * Sets nicGrams instance variable
     *
     * @param ncgr The amount of nicotine being used in the recipe, in grams.
     */
    private void setNicGrams(double ncgr) {
        nicGrams = ncgr;
    }

    /**
     * Retrieves nicBasePg instance variable
     *
     * @return nicBasePg The Base PG in the nicotine being used in the recipe.
     */
    private double getNicBasePg() {
        return nicBasePg;
    }

    /**
     * Sets nicBasePg instance variable
     *
     * @param ncbpg The Base PG in the nicotine being used in the recipe.
     */
    private void setNicBasePg(double ncbpg) {
        nicBasePg = ncbpg;
    }
    //Setters - Nic

    /**
     * Retrieves nicBaseVg instance variable
     *
     * @return nicBaseVg The Base VG in the nicotine being used in the recipe.
     */
    private double getNicBaseVg() {
        return nicBaseVg;
    }

    /**
     * Sets nicBaseVg instance variable
     *
     * @param ncbvg The Base VG in the nicotine being used in the recipe.
     */
    private void setNicBaseVg(double ncbvg) {
        nicBaseVg = ncbvg;
    }

    /**
     * Retrieves pgTarget instance variable
     *
     * @return pgTarget The amount of PG required to reach the desired percentage for total volume selected.
     */
    private double getPgTarget() {
        return pgTarget;
    }

    /**
     * Sets pgTarget instance variable
     *
     * @param pgtg The amount of PG required to reach the desired percentage for total volume selected.
     */
    private void setPgTarget(double pgtg) {
        pgTarget = pgtg;
    }

    /**
     * Retrieves pgPercent instance variable
     *
     * @return pgPercent The desired percentage of PG in the recipe.
     */
    private double getPgPercent() {
        return pgPercent;
    }

    /**
     * Sets pgPercent instance variable
     *
     * @param pgpc The desired percentage of PG in the recipe.
     */
    private void setPgPercent(double pgpc) {
        pgPercent = pgpc;
    }

    /**
     * Retrieves pgMl instance variable
     *
     * @return pgMl The amount of PG required (in ML) for the recipe.
     */
    private double getPgMl() {
        return pgMl;
    }

    /**
     * Sets pgMl instance variable
     *
     * @param pgml The amount of PG required (in ML) for the recipe.
     */
    private void setPgMl(double pgml) {
        pgMl = pgml;
    }
    //Setters - PG

    /**
     * Retrieves pgGrams instance variable
     *
     * @return pgGrams The amount of PG being used in the recipe, in grams.
     */
    private double getPgGrams() {
        return pgGrams;
    }

    /**
     * Sets pgGrams instance variable
     *
     * @param pggr The amount of PG being used in the recipe, in grams.
     */
    private void setPgGrams(double pggr) {
        pgGrams = pggr;
    }

    /**
     * Retrieves vgTarget instance variable
     *
     * @return vgTarget The amount of VG required to reach the desired percentage for total volume selected.
     */
    private double getVgTarget() {
        return vgTarget;
    }

    /**
     * Sets vgTarget instance variable
     *
     * @param vgtg The amount of VG required to reach the desired percentage for total volume selected.
     */
    private void setVgTarget(double vgtg) {
        vgTarget = vgtg;
    }

    /**
     * Retrieves vgPercent instance variable
     *
     * @return vgPercent The desired percentage of VG in the recipe.
     */
    private double getVgPercent() {
        return vgPercent;
    }
    //Setters - VG

    /**
     * Sets vgPercent instance variable
     *
     * @param vgpc The desired percentage of VG in the recipe.
     */
    private void setVgPercent(double vgpc) {
        vgPercent = vgpc;
    }

    /**
     * Retrieves vgMl instance variable
     *
     * @return vgMl The amount of VG required (in ML) for the recipe.
     */
    private double getVgMl() {
        return vgMl;
    }

    /**
     * Sets vgMl instance variable
     *
     * @param vgml The amount of VG required (in ML) for the recipe.
     */
    private void setVgMl(double vgml) {
        vgMl = vgml;
    }

    /**
     * Retrieves vgGrams instance variable
     *
     * @return vgGrams The amount of VG being used in the recipe, in grams.
     */
    private double getVgGrams() {
        return vgGrams;
    }

    /**
     * Sets vgGrams instance variable
     *
     * @param vggr The amount of VG being used in the recipe, in grams.
     */
    private void setVgGrams(double vggr) {
        vgGrams = vggr;
    }

    /////////////////////////////TRASH METHOD, throw out!//////////////////////////
    private void tests() {
        setUI(this.getUI());

        //Test1
        this.setNicBasePg(.60);
        this.setNicBaseVg(.40);
        this.setNicStrength(.013);
        this.setNicAmt(.012);
        this.setMlToMake(30);
        this.setFlavorPercent(.14);
        this.setPgPercent(.40);
        this.setVgPercent(.60);
        setFlavorWeight(.015);
        setNicWeight(1.239);
        setPgWeight(1.036);
        setVgWeight(1.21);


        //Test2

        this.setNicBasePg(.88);
        this.setNicBaseVg(.12);
        this.setNicStrength(.004);
        this.setNicAmt(.003);
        this.setMlToMake(250);
        this.setFlavorPercent(.07);
        this.setPgPercent(.82);
        this.setVgPercent(.18);
        setFlavorWeight(1);
        setNicWeight(1.235);
        setPgWeight(1.038);
        setVgWeight(1.26);

    }

    /**
     * Communicates with DatabaseInteraction class to pull a user-defined flavor from the database. Splits database
     * information into an array and extracts relevant name, flavor, and description information. Once data is
     * formatted correctly, it is sent off for calculation.
     */
    private void parseData() {
        //Local variables
        String flavors;
        String[] results;
        Flavor flav;

        //Declarations
        flav = null;

        //Retrieve requested information from database
        this.getDB().selectFlavor("Vanilla Coke"); //this will eventually be a string selected by user
        flavors = this.getDB().getResults().get(1); //Right now, we're only concerned with flavor list
        //Format data for calculation
        flavors = flavors.replaceAll(",", "%"); //single delimiter to deal with
        results = flavors.split("%"); //This gives us Name, Flavor List, Desc - for later GUI interaction
        for (int i = 0; i < results.length; i++) {
            String res = results[i].trim(); //remove whitespace
            if ((i & 1) == 0) //even iterations hold flavor percentages
            {
                double val = Double.parseDouble(res); //convert string to double
                flav = new Flavor(); //create new flavor instance
                flav.setPercentage(val);
            } else //odd iterations hold flavor names
            {
                assert flav != null;
                flav.setName(res);  //flavor was created by last iteration, assign the name
                this.getFlavors().add(flav); //add flavor to calculation ArrayList
            }
        }
    }

    /**
     * Calculates the grams needed for base liquid elements; NIC, VG, and PG
     */
    private void calcBaseGrams() {
        //Calculate target NIC for recipe
        this.setNicTarget(this.getMlToMake() * this.getNicAmt());
        //Calculate ML of NIC needed
        this.setNicMl((this.getMlToMake() / this.getNicStrength()) * this.getNicAmt());
        //Calculate grams of NIC needed
        this.setNicGrams(this.getNicMl() * getNicWeight());

        //Calculate target PG for recipe
        this.setPgTarget((this.getMlToMake() * this.getPgPercent()) - (this.getNicTarget() * this.getNicBasePg()));
        //Calculate ML of PG needed
        this.setPgMl(this.getPgTarget() - ((this.getNicMl() - this.getNicTarget()) *
                this.getNicBasePg()) - this.getFlavorMlTotal());
        //Calculate grams of PG needed
        this.setPgGrams(this.getPgMl() * getPgWeight());

        //Calculate target VG for recipe
        this.setVgTarget((this.getMlToMake() * this.getVgPercent()) - (this.getNicTarget() * this.getNicBaseVg()));
        //Calculate ML of VG needed
        this.setVgMl(this.getVgTarget() - ((this.getNicMl() - this.getNicTarget()) *
                this.getNicBaseVg()));
        //Calculate grams of VG needed
        this.setVgGrams(this.getVgMl() * getVgWeight());
    }

    /**
     * Calculates the grams needed for each flavor individually. Requires a separate method because it is called
     * in an iterative pattern and this minimizes resources used.
     */
    private void calcFlavorGrams() {
        this.setFlavorMl(this.getMlToMake() * this.getFlavorPercent());
        this.setFlavorMlTotal(this.getFlavorMlTotal() + getFlavorMl());
        this.setFlavorGrams(this.getFlavorMl() * getFlavorWeight());
    }


    /**
     * This method puts together all the pieces. It takes the formatted data from the <code>parseData()</code> method
     * and calculates the grams needed for each liquid component before displaying finished data to the user.
     */
    public void calculate() {

        this.tests();


        //Get data from database and format it
        this.parseData();

        //Calculate flavor information first
        for (Flavor fl : getFlavors()) {
            this.setFlavorPercent(fl.getFraction());
            this.calcFlavorGrams();
            System.out.println(fl.getName() + ": " + this.getFlavorGrams());
        }
        //Calculate base information
        this.calcBaseGrams();

        //Display to user
        System.out.println("Nic = " + this.getNicGrams());
        System.out.println("PG = " + this.getPgGrams());
        System.out.println("VG = " + this.getVgGrams());
    }
}
