package com.liquidlab.view;

import javafx.geometry.HPos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by root on 1/13/17.
 */
public class FlavorView extends Control {
    private static UserInterface ui;
    private static int lRow = 1;
    private static int rRow = 5;
    private final Label ourFlavUnit = new Label("%");
    //Left Grid Vars
    private TextField myVenField;
    private TextField myFlavField;
    private TextField myFlavPerField;
    //Right Field Vars
    private Label myFlavLabel;
    private Text myFlavMlText;
    private Text myFlavGText;
    private Text myFlavPercentText;

    public FlavorView(UserInterface ui) {
        this.setUi(ui);
        //Left Grid
        this.setVenField(new TextField());
        this.setFlavField(new TextField());
        this.setFlavPerField(new TextField());
        //Right Grid
        this.setFlavLabel(new Label("New Flavor"));
        this.setFlavMlText(new Text("0.0"));
        this.setFlavGText(new Text("0.0"));
        this.setFlavPercentText(new Text("0.0"));
        this.addToLeftGrid(this.getUi().getInput());
        this.addToRightGrid(this.getUi().getOutput());
    }

    private static int getLrow() {
        return lRow;
    }

    private static void setLrow(int newRow) {
        lRow = newRow;
    }

    private static int getRrow() {
        return rRow;
    }

    private static void setRrow(int newRow) {
        rRow = newRow;
    }

    //Getters
    public UserInterface getUi() {
        return ui;
    }

    //Setters
    public void setUi(UserInterface userInt) {
        ui = userInt;
    }

    //Left Grid
    public TextField getVenField() {
        return myVenField;
    }

    //Left Grid
    public void setVenField(TextField ven) {
        myVenField = ven;
    }

    public TextField getFlavField() {
        return myFlavField;
    }

    public void setFlavField(TextField flav) {
        myFlavField = flav;
    }

    public TextField getFlavPerField() {
        return myFlavPerField;
    }

    public void setFlavPerField(TextField per) {
        myFlavPerField = per;
    }

    public Label getFlavUnit() {
        return ourFlavUnit;
    }

    //Right Grid
    public Label getFlavLabel() {
        return myFlavLabel;
    }

    //Right Grid
    public void setFlavLabel(Label fl) {
        myFlavLabel = fl;
    }

    public Text getFlavMlText() {
        return myFlavMlText;
    }

    public void setFlavMlText(Text fl) {
        myFlavMlText = fl;
    }

    public Text getFlavGText() {
        return myFlavGText;
    }

    public void setFlavGText(Text fl) {
        myFlavGText = fl;
    }

    public Text getFlavPercentText() {
        return myFlavPercentText;
    }

    public void setFlavPercentText(Text fl) {
        myFlavPercentText = fl;
    }

    private void formatControls() {
        String str = "Flavor " + (getLrow() - 1);
        //Left Grid
        this.getVenField().setPromptText("Ven");
        this.getVenField().setId("text-field-vendor");
        getUi().formatTextField(this.getVenField(), 3);
        this.getFlavField().setPromptText(str);
        this.getFlavField().setId("text-field-flavor");
        getUi().formatTextField(this.getFlavPerField(), 3);
        //Right Grid
        GridPane.setHalignment(this.getFlavLabel(), HPos.RIGHT);
        this.getFlavMlText().setId("output");
        this.getFlavGText().setId("output-grams");
        this.getFlavPercentText().setId("output");
        GridPane.setHalignment(this.getFlavMlText(), HPos.CENTER);
        GridPane.setHalignment(this.getFlavGText(), HPos.CENTER);
        GridPane.setHalignment(this.getFlavPercentText(), HPos.CENTER);

    }

    public void addToLeftGrid(GridPane pane) {
        this.formatControls();
        //Left Grid
        pane.add(this.getVenField(), 5, getLrow());
        pane.add(this.getFlavField(), 6, getLrow());   //flavors
        pane.add(this.getFlavPerField(), 7, getLrow());    //flavor percentages
        pane.add(this.getFlavUnit(), 8, getLrow());   //Percent labels
        setLrow(getLrow() + 1);
    }

    public void addToRightGrid(GridPane pane) {
        this.formatControls();
        //Right Grid
        pane.add(this.getFlavLabel(), 0, getRrow());
        pane.add(this.getFlavMlText(), 1, getRrow());
        pane.add(this.getFlavGText(), 2, getRrow());
        pane.add(this.getFlavPercentText(), 3, getRrow());
        setRrow(getRrow() + 1);
    }
}
