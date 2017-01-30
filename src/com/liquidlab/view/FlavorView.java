//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab.view;

import javafx.geometry.HPos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FlavorView extends Control {
    private static int lRow = 2;
    private static int rRow = 5;
    private final Label ourFlavUnit = new Label("%");
    private TextField myVenField;
    private TextField myFlavField;
    private TextField myFlavPerField;
    private Label myFlavLabel;
    private Text myFlavMlText;
    private Text myFlavGText;
    private Text myFlavPercentText;
    private String myFlav;
    private Double myFlavMl;
    private Double myFlavG;
    private Double myFlavPercent;

    FlavorView() {
        this.setVenField(new TextField());
        this.setFlavField(new TextField());
        this.setFlavPerField(new TextField());
        this.setFlavLabel(new Label("New Flavor"));
        this.setFlavMlText(new Text("0.0"));
        this.setFlavGText(new Text("0.0"));
        this.setFlavPercentText(new Text("0"));
        this.setFlav("New Flavor");
        this.setFlavMl(0.0);
        this.setFlavG(0.0);
        this.setFlavPercent(0.0);
        this.formatControls();
    }

    private static int getLrow() {
        return lRow;
    }

    static void setLrow(int newRow) {
        lRow = newRow;
    }

    private static int getRrow() {
        return rRow;
    }

    static void setRrow(int newRow) {
        rRow = newRow;
    }

    public TextField getVenField() {
        return this.myVenField;
    }

    private void setVenField(TextField ven) {
        this.myVenField = ven;
    }

    public TextField getFlavField() {
        return this.myFlavField;
    }

    private void setFlavField(TextField flav) {
        this.myFlavField = flav;
    }

    public TextField getFlavPerField() {
        return this.myFlavPerField;
    }

    private void setFlavPerField(TextField per) {
        this.myFlavPerField = per;
    }

    private Label getFlavUnit() {
        return this.ourFlavUnit;
    }

    private Label getFlavLabel() {
        return this.myFlavLabel;
    }

    private void setFlavLabel(Label fl) {
        this.myFlavLabel = fl;
    }

    private Text getFlavMlText() {
        return this.myFlavMlText;
    }

    private void setFlavMlText(Text fl) {
        this.myFlavMlText = fl;
    }

    private Text getFlavGText() {
        return this.myFlavGText;
    }

    private void setFlavGText(Text fl) {
        this.myFlavGText = fl;
    }

    private Text getFlavPercentText() {
        return this.myFlavPercentText;
    }

    private void setFlavPercentText(Text fl) {
        this.myFlavPercentText = fl;
    }

    private String getFlav() {
        return this.myFlav;
    }

    public void setFlav(String fl) {
        this.myFlav = fl;
    }

    private Double getFlavMl() {
        return this.myFlavMl;
    }

    public void setFlavMl(Double fl) {
        this.myFlavMl = fl;
    }

    private Double getFlavG() {
        return this.myFlavG;
    }

    public void setFlavG(Double fl) {
        this.myFlavG = fl;
    }

    private Double getFlavPercent() {
        return this.myFlavPercent;
    }

    public void setFlavPercent(Double fl) {
        this.myFlavPercent = fl;
    }

    private void formatControls() {
        String str = "New Flavor";
        this.getVenField().setPromptText("Ven");
        this.getVenField().setId("text-field-vendor");
        UserInterface.formatTextField(this.getVenField(), 3);
        this.getFlavField().setPromptText(str);
        this.getFlavField().setId("text-field-flavor");
        UserInterface.formatTextField(this.getFlavPerField(), 5);
        GridPane.setHalignment(this.getFlavLabel(), HPos.RIGHT);
        this.getFlavMlText().setId("output");
        this.getFlavGText().setId("output-grams");
        this.getFlavPercentText().setId("output");
        GridPane.setHalignment(this.getFlavMlText(), HPos.CENTER);
        GridPane.setHalignment(this.getFlavGText(), HPos.CENTER);
        GridPane.setHalignment(this.getFlavPercentText(), HPos.CENTER);
    }

    void addToLeftGrid(GridPane pane) {
        pane.add(this.getVenField(), 5, getLrow());
        pane.add(this.getFlavField(), 6, getLrow());
        pane.add(this.getFlavPerField(), 7, getLrow());
        pane.add(this.getFlavUnit(), 8, getLrow());
        setLrow(getLrow() + 1);
    }

    void addToRightGrid(GridPane pane) {
        this.translate();
        this.formatControls();
        pane.add(this.getFlavLabel(), 0, getRrow());
        pane.add(this.getFlavMlText(), 1, getRrow());
        pane.add(this.getFlavGText(), 2, getRrow());
        pane.add(this.getFlavPercentText(), 3, getRrow());
        setRrow(getRrow() + 1);
    }

    private void translate() {
        this.setFlavLabel(new Label(this.getFlav()));
        this.setFlavMlText(new Text(this.getFlavMl().toString()));
        this.setFlavGText(new Text(this.getFlavG().toString()));
        this.setFlavPercentText(new Text(this.getFlavPercent().toString() + "%"));
    }

    static void reset() {
        setRrow(5);
    }
}
