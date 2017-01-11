package com.liquidlab.view;

import com.liquidlab.model.DatabaseInteraction;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * UserInterface.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 01/10/2017
 * This class provides the View of the MVC relationship, providing and interactive user interface.
 */
public class UserInterface extends Application {
    private DatabaseInteraction dbInt;
    private GridPane input;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Set initial window title
        primaryStage.setTitle("LiquidLab");
        BorderPane pane = new BorderPane();
        //Set Top Pane
        this.setTopPane(pane);
        //Set Left pane
        this.setLeftPane(pane);
        //Set Right Pane
        this.setRightPane(pane);
        //Initialize grid in a scene
        Scene scene = new Scene(pane, 1000, 500);
        //Set scene to initial window
        primaryStage.setScene(scene);
        //Link CSS stylesheet
        scene.getStylesheets().add(UserInterface.class.getResource("Test.css").toExternalForm());
        //Display window
        primaryStage.show();
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

    /**
     * Sends information to the DatabaseInteraction
     *
     * @param db The database to send information to.
     */
    private void setDB(DatabaseInteraction db) {
        dbInt = db;
    }

    public GridPane getInput() {
        return input;
    }

    //Setter methods for variables
    public void setInput(GridPane inputPane) {
        input = inputPane;
    }

    private void setTopPane(BorderPane parent) {
        //Declarations
        HBox title;
        Text titleLbl;
        Insets margin;

        //Initializations
        title = new HBox();
        titleLbl = new Text("LiquidLab");
        margin = new Insets(15, 0, 0, 30);

        //Format label
        titleLbl.setId("title-text");
        title.getChildren().add(titleLbl);
        BorderPane.setMargin(title, margin);

        //Assign to top pane
        parent.setTop(title);
    }

    private void setLeftPane(BorderPane parent) {
        //Declarations
        HBox lBox;
        Button saveBtn;
        Button calcBtn;
        AnchorPane lAnchor;
        Insets margin;

        //Initialization
        lBox = new HBox(8);
        lAnchor = new AnchorPane();
        saveBtn = new Button("Save");
        calcBtn = new Button("Calculate");
        margin = new Insets(20, 10, 30, 30);


        //Add buttons to lBox
        lBox.getChildren().addAll(saveBtn, calcBtn);
        //Create input grid
        this.setInput(new GridPane());
        this.getInput().setId("left-grid");
        //Create anchor for buttons
        lAnchor.setId("left-anchor");
        AnchorPane.setBottomAnchor(lBox, 5.0);
        AnchorPane.setRightAnchor(lBox, 8.0);
        AnchorPane.setTopAnchor(this.getInput(), 10.0);
        AnchorPane.setLeftAnchor(this.getInput(), 10.0);
        BorderPane.setMargin(lAnchor, margin);
        lAnchor.getChildren().addAll(this.getInput(), lBox);
        //Set anchor in pane
        parent.setLeft(lAnchor);

        //Left Pane complete, set controls
        this.setLeftControls(this.getInput());

        calcBtn.setOnMouseClicked((event) -> {
            this.calcButtonAction();
        });
    }

    private void calcButtonAction() {

    }

    private void setLeftControls(GridPane thisPane) {
        //Declarations
        ComboBox<String> flList;
        Label mlToMakeText;
        TextField mlToMakeField;
        Label mlToMakeUnit;
        Label nicText;
        TextField nicField;
        Label nicUnit;
        Label pgText;
        pgText = new Label("Target PG:");
        TextField pgField;
        Label pgUnit;
        Label vgText;
        TextField vgField;
        Label vgUnit;
        TextArea notes;
        Text newFlav;
        TextField ven;
        TextField ven2;
        TextField ven3;
        TextField flav;
        TextField flavPer;
        Label flavUnit;
        TextField flav2;
        TextField flavPer2;
        Label flavUnit2;
        TextField flav3;
        TextField flavPer3;
        Label flavUnit3;
        Button addFlav;

        //Initializations
        flList = new ComboBox<String>();
        mlToMakeText = new Label("ML To Make:");
        mlToMakeField = new TextField();
        mlToMakeUnit = new Label("ml");
        nicText = new Label("Target Nicotine:");
        nicField = new TextField();
        nicUnit = new Label("mg");
        pgField = new TextField();
        pgUnit = new Label("%");
        vgText = new Label("Target VG:");
        vgField = new TextField();
        vgUnit = new Label("%");
        notes = new TextArea();
        newFlav = new Text("New Recipe");
        ven = new TextField();
        ven2 = new TextField();
        ven3 = new TextField();
        flav = new TextField();
        flavPer = new TextField();
        flavUnit = new Label("%");
        flav2 = new TextField();
        flavPer2 = new TextField();
        flavUnit2 = new Label("%");
        flav3 = new TextField();
        flavPer3 = new TextField();
        flavUnit3 = new Label("%");
        addFlav = new Button("+");
        //Set Prompt Text
        notes.setPromptText("Notes:");
        ven.setPromptText("Ven");
        ven2.setPromptText("Ven");
        ven3.setPromptText("Ven");
        flav.setPromptText("Flavor 1");
        flav2.setPromptText("Flavor 2");
        flav3.setPromptText("Flavor 3");

        //Format Drop-Down Menu
        flList.setPromptText("Select Flavor...");
        flList.getItems().add("Child's Play");
        flList.getItems().add("Whirlmelon");
        flList.getItems().add("The Simple Lifeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        //Add to GridPane
        thisPane.add(flList, 0, 0, 3, 1);

        //Ml to Make
        thisPane.add(mlToMakeText, 0, 1);
        thisPane.add(mlToMakeField, 1, 1);
        thisPane.add(mlToMakeUnit, 2, 1);
        //Nic
        thisPane.add(nicText, 0, 2);
        thisPane.add(nicField, 1, 2);
        thisPane.add(nicUnit, 2, 2);
        //PG
        thisPane.add(pgText, 0, 3);
        thisPane.add(pgField, 1, 3);
        thisPane.add(pgUnit, 2, 3);
        //VG
        thisPane.add(vgText, 0, 4);
        thisPane.add(vgField, 1, 4);
        thisPane.add(vgUnit, 2, 4);

        //Set control CSS IDs
        //notes.setId("text-area");
        newFlav.setId("grid-header");
        ven.setId("text-field-vendor");
        ven2.setId("text-field-vendor");
        ven3.setId("text-field-vendor");
        flav.setId("text-field-flavor");
        flav2.setId("text-field-flavor");
        flav3.setId("text-field-flavor");
        GridPane.setHalignment(newFlav, HPos.CENTER);
        GridPane.setValignment(newFlav, VPos.TOP);
        thisPane.add(notes, 0, 5, 3, 10);   //notes
        thisPane.add(newFlav, 6, 0);    //new flavor label
        thisPane.add(ven, 5, 1);    //vendors
        thisPane.add(ven2, 5, 2);
        thisPane.add(ven3, 5, 3);
        thisPane.add(flav, 6, 1);   //flavors
        thisPane.add(flav2, 6, 2);
        thisPane.add(flav3, 6, 3);
        thisPane.add(flavPer, 7, 1);    //flavor percentages
        thisPane.add(flavPer2, 7, 2);
        thisPane.add(flavPer3, 7, 3);
        thisPane.add(flavUnit, 8, 1);   //Percent labels
        thisPane.add(flavUnit2, 8, 2);
        thisPane.add(flavUnit3, 8, 3);
        //Add flavor button
        thisPane.add(addFlav, 5, 4);
    }

    private void textFieldEntered(TextInputControl node) {
        if (node.getText().equals(node.getPromptText())) {
            node.clear();
        }
        System.out.println("Click!");
    }

    private void setRightPane(BorderPane parent) {
        //PLACEHOLDER VALUES!
        Text totalMl = new Text("30.35");
        Text totalG = new Text("34.03");
        Text totalPer = new Text("100.00%");

        //Declarations
        HBox rBox;
        Label totalLabel;
        AnchorPane rAnchor;
        GridPane output;
        Insets margin;
        ColumnConstraints col1;
        //Initializations
        rBox = new HBox(35);
        totalLabel = new Label("Total");
        rAnchor = new AnchorPane();
        output = new GridPane();
        margin = new Insets(20, 30, 30, 0);
        col1 = new ColumnConstraints(120);

        //Set CSS Styling
        totalMl.setId("output");
        totalG.setId("output-grams");
        totalPer.setId("output");
        rBox.setId("right-box");
        output.setId("right-grid");
        rAnchor.setId("right-anchor");
        output.getColumnConstraints().addAll(col1);
        //Create box to hold total values
        rBox.getChildren().addAll(totalLabel, totalMl, totalG, totalPer);
        //Anchor components
        rAnchor.getChildren().addAll(output, rBox);
        AnchorPane.setBottomAnchor(rBox, 5.0);
        AnchorPane.setLeftAnchor(rBox, 8.0);
        BorderPane.setMargin(rAnchor, margin);
        AnchorPane.setTopAnchor(output, 10.0);
        AnchorPane.setLeftAnchor(output, 10.0);
        //Apply to borderpane
        parent.setRight(rAnchor);

        //set controls
        this.setRightControls(output);
    }

    private void setRightControls(GridPane thisPane) {
        //PLACEHOLDER VALUES FOR RESULTS
        Text pgMl = new Text("12.81");
        Text vgMl = new Text("29.84");
        Text nicMl = new Text("12.54");
        Text flavMl1 = new Text("3.28");
        Text flavMl2 = new Text("4.92");
        Text flavMl3 = new Text("8.71");
        Text pgG = new Text("14.37");
        Text vgG = new Text("38.99");
        Text nicG = new Text("14.31");
        Text flavG1 = new Text("12.16");
        Text flavG2 = new Text("8.21");
        Text flavG3 = new Text("1.11");
        Text pgPer = new Text("15.17");
        Text vgPer = new Text("20.00");
        Text nicPer = new Text("37.12");
        Text flavPercent = new Text("18.13");
        Text flavPercent2 = new Text("19.32");
        Text flavPercent3 = new Text("47.32");

        //Declarations
        Text product;
        Label mlLabel;
        Label gLabel;
        Label perLabel;
        Label pgLabel;
        Label vgLabel;
        Label nicLabel;
        Label flavLabel1;
        Label flavLabel2;
        Label flavLabel3;

        //Initializations
        product = new Text("Results");
        mlLabel = new Label("ml");
        gLabel = new Label("grams");
        perLabel = new Label("% of Total");
        pgLabel = new Label("PG");
        vgLabel = new Label("VG");
        nicLabel = new Label("Nicotine");
        flavLabel1 = new Label("TFA Blueberry");
        flavLabel2 = new Label("FA Strawberry (Ripe)");
        flavLabel3 = new Label("RF Bread Pudding");

        //CSS Styling
        product.setId("grid-header");

        //Add controls
        thisPane.add(product, 0, 0, 5, 1);
        GridPane.setHalignment(product, HPos.CENTER);
        thisPane.add(mlLabel, 1, 1);
        GridPane.setHalignment(mlLabel, HPos.CENTER);
        thisPane.add(gLabel, 2, 1);
        GridPane.setHalignment(gLabel, HPos.CENTER);
        thisPane.add(perLabel, 3, 1);
        GridPane.setHalignment(perLabel, HPos.CENTER);
        thisPane.add(pgLabel, 0, 2);
        GridPane.setHalignment(pgLabel, HPos.RIGHT);
        thisPane.add(vgLabel, 0, 3);
        GridPane.setHalignment(vgLabel, HPos.RIGHT);
        thisPane.add(nicLabel, 0, 4);
        GridPane.setHalignment(nicLabel, HPos.RIGHT);
        thisPane.add(flavLabel1, 0, 5);
        GridPane.setHalignment(flavLabel1, HPos.RIGHT);
        thisPane.add(flavLabel2, 0, 6);
        GridPane.setHalignment(flavLabel2, HPos.RIGHT);
        thisPane.add(flavLabel3, 0, 7);
        GridPane.setHalignment(flavLabel3, HPos.RIGHT);


        //Placeholders - CSS Styling
        pgMl.setId("output");
        vgMl.setId("output");
        nicMl.setId("output");
        flavMl1.setId("output");
        flavMl2.setId("output");
        flavMl3.setId("output");
        pgG.setId("output-grams");
        vgG.setId("output-grams");
        nicG.setId("output-grams");
        flavG1.setId("output-grams");
        flavG2.setId("output-grams");
        flavG3.setId("output-grams");
        pgPer.setId("output");
        vgPer.setId("output");
        nicPer.setId("output");
        flavPercent.setId("output");
        flavPercent2.setId("output");
        flavPercent3.setId("output");


        //Add placeholders
        thisPane.add(pgMl, 1, 2);
        GridPane.setHalignment(pgMl, HPos.CENTER);
        thisPane.add(vgMl, 1, 3);
        GridPane.setHalignment(vgMl, HPos.CENTER);
        thisPane.add(nicMl, 1, 4);
        GridPane.setHalignment(nicMl, HPos.CENTER);
        thisPane.add(flavMl1, 1, 5);
        GridPane.setHalignment(flavMl1, HPos.CENTER);
        thisPane.add(flavMl2, 1, 6);
        GridPane.setHalignment(flavMl2, HPos.CENTER);
        thisPane.add(flavMl3, 1, 7);
        GridPane.setHalignment(flavMl3, HPos.CENTER);
        thisPane.add(pgG, 2, 2);
        GridPane.setHalignment(pgG, HPos.CENTER);
        thisPane.add(vgG, 2, 3);
        GridPane.setHalignment(vgG, HPos.CENTER);
        thisPane.add(nicG, 2, 4);
        GridPane.setHalignment(nicG, HPos.CENTER);
        thisPane.add(flavG1, 2, 5);
        GridPane.setHalignment(flavG1, HPos.CENTER);
        thisPane.add(flavG2, 2, 6);
        GridPane.setHalignment(flavG2, HPos.CENTER);
        thisPane.add(flavG3, 2, 7);
        GridPane.setHalignment(flavG3, HPos.CENTER);
        thisPane.add(pgPer, 3, 2);
        GridPane.setHalignment(pgPer, HPos.CENTER);
        thisPane.add(vgPer, 3, 3);
        GridPane.setHalignment(vgPer, HPos.CENTER);
        thisPane.add(nicPer, 3, 4);
        GridPane.setHalignment(nicPer, HPos.CENTER);
        thisPane.add(flavPercent, 3, 5);
        GridPane.setHalignment(flavPercent, HPos.CENTER);
        thisPane.add(flavPercent2, 3, 6);
        GridPane.setHalignment(flavPercent2, HPos.CENTER);
        thisPane.add(flavPercent3, 3, 7);
        GridPane.setHalignment(flavPercent3, HPos.CENTER);
    }
}
