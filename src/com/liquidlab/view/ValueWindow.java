//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab.view;

import com.liquidlab.controller.BusinessLogic;
import com.liquidlab.model.DatabaseInteraction;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

class ValueWindow {
    private static GridPane ourPane;
    private static Scene ourScene;
    private static TextField ourNicStrengthField;
    private static TextField ourNicBasePgField;
    private static TextField ourNicBaseVgField;
    private static TextField ourFlavWtField;
    private static TextField ourNicWtField;
    private static TextField ourPgWtField;
    private static TextField ourVgWtField;

    private static GridPane getPane() {
        return ourPane;
    }

    private static void setPane(GridPane gp) {
        ourPane = gp;
    }

    private static Scene getScene() {
        return ourScene;
    }

    private static void setScene(Scene newScene) {
        ourScene = newScene;
    }

    private static TextField getNicStrengthField() {
        return ourNicStrengthField;
    }

    private static void setNicStrengthField(TextField newField) {
        ourNicStrengthField = newField;
    }

    private static TextField getNicBasePgField() {
        return ourNicBasePgField;
    }

    private static void setNicBasePgField(TextField newField) {
        ourNicBasePgField = newField;
    }

    private static TextField getNicBaseVgField() {
        return ourNicBaseVgField;
    }

    private static void setNicBaseVgField(TextField newField) {
        ourNicBaseVgField = newField;
    }

    private static TextField getFlavWtField() {
        return ourFlavWtField;
    }

    private static void setFlavWtField(TextField newField) {
        ourFlavWtField = newField;
    }

    private static TextField getNicWtField() {
        return ourNicWtField;
    }

    private static void setNicWtField(TextField newField) {
        ourNicWtField = newField;
    }

    private static TextField getPgWtField() {
        return ourPgWtField;
    }

    private static void setPgWtField(TextField newField) {
        ourPgWtField = newField;
    }

    private static TextField getVgWtField() {
        return ourVgWtField;
    }

    private static void setVgWtField(TextField newField) {
        ourVgWtField = newField;
    }

    ValueWindow() {
        setPane(new GridPane());
        start(new Stage());
    }

    private static void start(Stage primaryStage) {
        primaryStage.setTitle("Set Values");
        setScene(new Scene(getPane(), 300.0D, 380.0D));
        initGrid(getScene());
        getPane().requestFocus();
        primaryStage.setScene(getScene());
        getScene().getStylesheets().add(UserInterface.class.getResource("Test.css").toExternalForm());
        primaryStage.show();
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, ValueWindow::closeRequest);
    }

    private static <T extends Event> void closeRequest(T t) {
        if(getFlavWtField().getText().isEmpty() || getNicStrengthField().getText().isEmpty() || getNicWtField().getText().isEmpty() || getNicBasePgField().getText().isEmpty() || getPgWtField().getText().isEmpty() || getNicBaseVgField().getText().isEmpty() || getVgWtField().getText().isEmpty()) {
            t.consume();
            UserInterface.msgBox("ERROR", 6);
        }

    }

    private static void initGrid(Scene scene) {
        ColumnConstraints col1 = new ColumnConstraints(scene.getWidth() / 2.0D - 20.0D);
        ColumnConstraints col2 = new ColumnConstraints(40.0D);
        ColumnConstraints col3 = new ColumnConstraints(scene.getWidth() / 2.0D - 20.0D);
        Label nicStrength = new Label("Nic Strength:");
        setNicStrengthField(new TextField());
        Text nicTitle = new Text("Set Nic Values");
        Text wtTitle = new Text("Set Weight Values");
        Label nicBasePg = new Label("Nic Base (PG):");
        setNicBasePgField(new TextField());
        setNicBaseVgField(new TextField());
        setFlavWtField(new TextField());
        setNicWtField(new TextField());
        setPgWtField(new TextField());
        setVgWtField(new TextField());
        Label nicBaseVg = new Label("Nic Base (VG):");
        Label flavWt = new Label("Flavor Weight:");
        Label nicWt = new Label("Nic Weight:");
        Label pgWt = new Label("PG Weight:");
        Label vgWt = new Label("VG Weight:");
        getPane().setId("left-grid");
        getPane().getColumnConstraints().addAll(col1, col2, col3);
        setTitle(nicTitle, 0);
        setTitle(wtTitle, 4);
        setControls(nicStrength, getNicStrengthField(), "100", "mg", 1);
        setControls(nicBasePg, getNicBasePgField(), "50", "%", 2);
        setControls(nicBaseVg, getNicBaseVgField(), "50", "%", 3);
        setControls(flavWt, getFlavWtField(), "1", "g = 1 ml", 5);
        setControls(nicWt, getNicWtField(), "1.24", "g = 1 ml", 6);
        setControls(pgWt, getPgWtField(), "1.04", "g = 1 ml", 7);
        setControls(vgWt, getVgWtField(), "1.26", "g = 1 ml", 8);
        retrieveFromDb();
        createSaveButton();
        createHelpButton();
        getNicBasePgField().addEventFilter(KeyEvent.KEY_TYPED, ValueWindow::pgBaseKeyEntered);
        getNicBaseVgField().addEventFilter(KeyEvent.KEY_TYPED, ValueWindow::vgBaseKeyEntered);
    }

    private static void retrieveFromDb() {
        DatabaseInteraction.selectValues();
        if(!DatabaseInteraction.getResults().isEmpty()) {
            getNicStrengthField().setText(DatabaseInteraction.getResults().get(0));
            getNicBasePgField().setText(DatabaseInteraction.getResults().get(1));
            getNicBaseVgField().setText(DatabaseInteraction.getResults().get(2));
            getFlavWtField().setText(DatabaseInteraction.getResults().get(3));
            getNicWtField().setText(DatabaseInteraction.getResults().get(4));
            getPgWtField().setText(DatabaseInteraction.getResults().get(5));
            getVgWtField().setText(DatabaseInteraction.getResults().get(6));
        }

    }

    private static void setTitle(Text txt, int row) {
        Insets margin = new Insets(20.0D, 15.0D, 15.0D, 15.0D);
        GridPane.setHalignment(txt, HPos.CENTER);
        GridPane.setMargin(txt, margin);
        getPane().add(txt, 0, row, 3, 1);
        txt.setId("grid-header");
    }

    private static void setControls(Label lbl, TextField node, String prompt, String unit, int row) {
        Label unitLbl = new Label(unit);
        getPane().add(lbl, 0, row, 1, 1);
        GridPane.setHalignment(lbl, HPos.RIGHT);
        node.setPromptText(prompt);
        getPane().add(node, 1, row, 1, 1);
        GridPane.setHalignment(node, HPos.CENTER);
        getPane().add(unitLbl, 2, row, 1, 1);
        GridPane.setHalignment(unitLbl, HPos.LEFT);
    }

    private static void createSaveButton() {
        Button saveBtn = new Button("Save");
        getPane().add(saveBtn, 2, 11, 1, 1);
        GridPane.setHalignment(saveBtn, HPos.CENTER);
        saveBtn.setOnMouseClicked((event) -> saveButtonAction());
    }

    private static void createHelpButton() {
        Button helpBtn = new Button("?");
        getPane().add(helpBtn, 0, 11, 1, 1);
        GridPane.setHalignment(helpBtn, HPos.LEFT);
        GridPane.setMargin(helpBtn, new Insets(0.0D, 0.0D, 0.0D, 15.0D));
        helpBtn.setOnMouseClicked((event) -> helpButtonAction());
    }

    private static void saveButtonAction() {
        try {
            BusinessLogic.getUserValues();
            DatabaseInteraction.insertValues(getNicStrengthField().getText(), getNicBasePgField().getText(), getNicBaseVgField().getText(), getFlavWtField().getText(), getNicWtField().getText(), getPgWtField().getText(), getVgWtField().getText());
            Stage ex = (Stage)getScene().getWindow();
            ex.close();
        } catch (Exception var1) {
            UserInterface.msgBox("ERROR", 6);
        }

    }

    private static void helpButtonAction() {
        System.out.println("I want this to display a helpfile of how to choose values.");
    }

    private static void pgBaseKeyEntered(KeyEvent inputEvent) {
        if(!inputEvent.isControlDown() && (inputEvent.getCharacter().equals("\b") || inputEvent.getCharacter().matches("\\d"))) {
            String newText;
            if(!getNicBasePgField().getText().isEmpty()) {
                if(!inputEvent.getCharacter().equals("\b")) {
                    if(!getNicBasePgField().getSelectedText().isEmpty()) {
                        getNicBasePgField().setText(getNicBasePgField().getText().replace(getNicBasePgField().getSelectedText(), ""));
                    }

                    newText = getNicBasePgField().getText() + inputEvent.getCharacter();
                } else {
                    newText = getNicBasePgField().getText();
                }
            } else if(inputEvent.getCharacter().equals("\b")) {
                newText = "0";
            } else {
                newText = inputEvent.getCharacter();
            }

            Integer diff = 100 - Integer.parseInt(newText);
            if(diff >= 0) {
                getNicBaseVgField().setText(diff.toString());
            } else {
                inputEvent.consume();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You must enter a number from 0-100");
                alert.showAndWait().ifPresent((rs) -> {
                    if(rs == ButtonType.OK) {
                        alert.close();
                    }

                });
            }
        } else {
            inputEvent.consume();
        }

    }

    private static void vgBaseKeyEntered(KeyEvent inputEvent) {
        if(!inputEvent.isControlDown() && (inputEvent.getCharacter().equals("\b") || inputEvent.getCharacter().matches("\\d"))) {
            String newText;
            if(!getNicBaseVgField().getText().isEmpty()) {
                if(!inputEvent.getCharacter().equals("\b")) {
                    if(!getNicBaseVgField().getSelectedText().isEmpty()) {
                        getNicBaseVgField().setText(getNicBaseVgField().getText().replace(getNicBaseVgField().getSelectedText(), ""));
                    }

                    newText = getNicBaseVgField().getText() + inputEvent.getCharacter();
                } else {
                    newText = getNicBaseVgField().getText();
                }
            } else if(inputEvent.getCharacter().equals("\b")) {
                newText = "0";
            } else {
                newText = inputEvent.getCharacter();
            }

            Integer diff = 100 - Integer.parseInt(newText);
            if(diff >= 0) {
                getNicBasePgField().setText(diff.toString());
            } else {
                inputEvent.consume();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You must enter a number from 0-100");
                alert.showAndWait().ifPresent((rs) -> {
                    if(rs == ButtonType.OK) {
                        alert.close();
                    }

                });
            }
        } else {
            inputEvent.consume();
        }

    }
}
