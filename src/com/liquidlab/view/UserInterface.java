package com.liquidlab.view;

import com.liquidlab.controller.BusinessLogic;
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
    private static Text myProduct;
    private BorderPane myPane;
    private DatabaseInteraction dbInt;
    private GridPane myInput, myOutput;
    private TextField myMlToMake, myNic, myPg, myVg;
    private Double myPgMl, myVgMl, myNicMl, myFlavMl, myPgG, myVgG, myNicG;
    private TextArea myNotes;
    private Integer myPgPer, myVgPer, myNicPer;
    private Integer myRows;
    private FlavorView[] flavors;

    public static Text getProduct() {
        return myProduct;
    }

    private static void setProduct(Text newProd) {
        myProduct = newProd;
    }

    public static void main(String[] args) {
        launch(args);
    }

    //Getters
    public TextField getMlToMake() {
        return myMlToMake;
    }

    //Setters
    private void setMlToMake(TextField txtFld) {
        myMlToMake = txtFld;
    }

    public TextField getNic() {
        return myNic;
    }

    private void setNic(TextField txtFld) {
        myNic = txtFld;
    }

    public TextField getPg() {
        return myPg;
    }

    private void setPg(TextField txtFld) {
        myPg = txtFld;
    }

    public TextField getVg() {
        return myVg;
    }

    private void setVg(TextField txtFld) {
        myVg = txtFld;
    }

    public TextArea getNotes() {
        return myNotes;
    }

    private void setNotes(TextArea txtArea) {
        myNotes = txtArea;
    }

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
        return myInput;
    }

    public void setInput(GridPane inputPane) {
        myInput = inputPane;
    }

    public GridPane getOutput() {
        return myOutput;
    }

    public void setOutput(GridPane outputPane) {
        myOutput = outputPane;
    }

    public Double getPgMl() {
        return myPgMl;
    }

    public void setPgMl(Double txt) {
        myPgMl = txt;
    }

    public Double getVgMl() {
        return myVgMl;
    }

    public void setVgMl(Double txt) {
        myVgMl = txt;
    }

    public Double getNicMl() {
        return myNicMl;
    }

    public void setNicMl(Double txt) {
        myNicMl = txt;
    }

    public Double getPgG() {
        return myPgG;
    }

    public void setPgG(Double txt) {
        myPgG = txt;
    }

    public Double getVgG() {
        return myVgG;
    }

    public void setVgG(Double txt) {
        myVgG = txt;
    }

    public Double getNicG() {
        return myNicG;
    }

    public void setNicG(Double txt) {
        myNicG = txt;
    }

    public Integer getPgPer() {
        return myPgPer;
    }

    public void setPgPer(Integer txt) {
        myPgPer = txt;
    }

    public Integer getVgPer() {
        return myVgPer;
    }

    public void setVgPer(Integer txt) {
        myVgPer = txt;
    }

    public Integer getNicPer() {
        return myNicPer;
    }

    public void setNicPer(Integer txt) {
        myNicPer = txt;
    }

    public FlavorView[] getFlavors() {
        return flavors;
    }

    public void setFlavors(FlavorView[] flavs) {
        flavors = flavs;
    }

    private BorderPane getPane() {
        return myPane;
    }

    private void setPane(BorderPane newPane) {
        myPane = newPane;
    }

    private int getRowCount() {
        return myRows;
    }

    private void setRowCount(int count) {
        myRows = count;
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
        this.setLeftControls();

        calcBtn.setOnMouseClicked((event) -> {
            this.calcButtonAction();
        });

    }

    private void setLeftControls() {
        //Declarations
        ComboBox<String> flList;
        final Label mlToMakeText;
        final Label mlToMakeUnit;
        final Label nicText;
        final Label nicUnit;
        final Label pgText;
        final Label pgUnit;
        final Label vgText;
        final Label vgUnit;
        final Text newFlav;
        final Text buffer;
        Button addFlav;


        //Initializations
        flList = new ComboBox<String>();
        mlToMakeText = new Label("ML To Make:");
        mlToMakeUnit = new Label("ml");
        nicText = new Label("Target Nicotine:");
        nicUnit = new Label("mg");
        pgText = new Label("Target PG:");
        pgUnit = new Label("%");
        vgText = new Label("Target VG:");
        vgUnit = new Label("%");
        newFlav = new Text("New Recipe");
        addFlav = new Button("+");
        buffer = new Text();
        this.getFlavors()[0].addToLeftGrid(this.getInput());
        this.getFlavors()[1].addToLeftGrid(this.getInput());
        this.getFlavors()[2].addToLeftGrid(this.getInput());
        this.setMlToMake(new TextField());
        this.setNic(new TextField());
        this.setPg(new TextField());
        this.setVg(new TextField());
        this.setNotes(new TextArea());

        //Set Prompt Text
        this.getNotes().setPromptText("Notes:");

        //Format Drop-Down Menu
        flList.setPromptText("Select Flavor...");
        flList.getItems().add("Child's Play");
        flList.getItems().add("Whirlmelon");
        flList.getItems().add("The Simple Lifeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        //Add to GridPane
        this.getInput().add(flList, 0, 0, 3, 1);

        //Ml to Make
        this.formatTextField(this.getMlToMake(), 4);
        this.getInput().add(mlToMakeText, 0, 1);
        this.getInput().add(this.getMlToMake(), 1, 1);
        this.getInput().add(mlToMakeUnit, 2, 1);
        //Nic
        this.formatTextField(this.getNic(), 3);
        this.getInput().add(nicText, 0, 2);
        this.getInput().add(this.getNic(), 1, 2);
        this.getInput().add(nicUnit, 2, 2);
        //PG
        this.formatTextField(this.getPg(), 3);
        this.getInput().add(pgText, 0, 3);
        this.getInput().add(this.getPg(), 1, 3);
        this.getInput().add(pgUnit, 2, 3);
        //VG
        this.formatTextField(this.getVg(), 3);
        this.getInput().add(vgText, 0, 4);
        this.getInput().add(this.getVg(), 1, 4);
        this.getInput().add(vgUnit, 2, 4);
        //Buffer
        this.getInput().add(buffer, 0, 5);
        buffer.setId("buffer-text");

        //Set control CSS IDs
        //notes.setId("text-area");
        newFlav.setId("grid-header");
        //this.getVen1().setId("text-field-vendor");
        //this.getVen2().setId("text-field-vendor");
        //this.getVen3().setId("text-field-vendor");
        //this.getFlav1().setId("text-field-flavor");
        //this.getFlav2().setId("text-field-flavor");
        //this.getFlav3().setId("text-field-flavor");
        //this.getFlav4().setId("text-field-flavor");
        GridPane.setHalignment(newFlav, HPos.CENTER);
        GridPane.setValignment(newFlav, VPos.TOP);
        this.getInput().add(this.getNotes(), 0, 6, 3, 5);   //notes
        this.getInput().add(newFlav, 6, 0);    //new flavor label
        //this.formatTextField(this.getVen1(), 3);
        //this.getInput().add(this.getVen1(), 5, 1);    //vendors
        //this.formatTextField(this.getVen2(), 3);
        //this.getInput().add(this.getVen2(), 5, 2);
        //this.formatTextField(this.getVen3(), 3);
        //this.getInput().add(this.getVen3(), 5, 3);
        //this.getInput().add(this.getFlav1(), 6, 1);   //flavors
        //this.getInput().add(this.getFlav2(), 6, 2);
        //this.getInput().add(this.getFlav3(), 6, 3);
        //this.formatTextField(this.getFlavPer(), 3);
        //this.getInput().add(this.getFlavPer(), 7, 1);    //flavor percentages
        //this.formatTextField(this.getFlavPer2(), 3);
        //this.getInput().add(this.getFlavPer2(), 7, 2);
        //this.formatTextField(this.getFlavPer3(), 3);
        //this.getInput().add(this.getFlavPer3(), 7, 3);
        //this.getInput().add(flavUnit, 8, 1);   //Percent labels
        //this.getInput().add(flavUnit2, 8, 2);
        //this.getInput().add(flavUnit3, 8, 3);
        //Add flavor button
        this.getInput().add(addFlav, 5, 4);

        //Event Handler
        addFlav.setOnMouseClicked((event) -> {
            this.addFlavButtonAction(addFlav);
        });
    }

    private void setRightPane(BorderPane parent) {
        //Declarations
        AnchorPane rAnchor;
        Insets margin;
        ColumnConstraints col1, col2, col3, col4;
        //Initializations
        rAnchor = new AnchorPane();
        this.setOutput(new GridPane());
        margin = new Insets(20, 30, 30, 0);
        col1 = new ColumnConstraints(120);
        col2 = new ColumnConstraints(30);
        col3 = new ColumnConstraints(40);
        col4 = new ColumnConstraints(60);

        //Set CSS Styling
        this.getOutput().setId("right-grid");
        rAnchor.setId("right-anchor");
        this.getOutput().getColumnConstraints().addAll(col1, col2, col3, col4);
        //Create box to hold total values
        //Anchor components
        this.getFlavors()[0].addToRightGrid(this.getOutput());
        this.getFlavors()[1].addToRightGrid(this.getOutput());
        this.getFlavors()[2].addToRightGrid(this.getOutput());
        rAnchor.getChildren().add(this.getOutput());
        BorderPane.setMargin(rAnchor, margin);
        AnchorPane.setTopAnchor(this.getOutput(), 10.0);
        AnchorPane.setLeftAnchor(this.getOutput(), 10.0);
        //Apply to borderpane
        parent.setRight(rAnchor);

        //set controls
        this.setRightControls(this.getOutput());
    }

    public void setRightControls(GridPane thisPane) {
        //PLACEHOLDER VALUES FOR RESULTS


        //Declarations
        final Label mlLabel;
        final Label gLabel;
        final Label perLabel;
        final Label pgLabel;
        final Label vgLabel;
        final Label nicLabel;
        Text pgMlText;
        Text vgMlText;
        Text nicMlText;
        Text pgGText;
        Text vgGText;
        Text nicGText;
        Text pgPercentText;
        Text vgPercentText;
        Text nicPercentText;


        //Initializations
        setProduct(new Text("Results"));
        mlLabel = new Label("ml");
        gLabel = new Label("grams");
        perLabel = new Label("% of Total");
        pgLabel = new Label("PG");
        vgLabel = new Label("VG");
        nicLabel = new Label("Nicotine");
        pgMlText = new Text(this.getPgMl().toString());
        vgMlText = new Text(this.getVgMl().toString());
        nicMlText = new Text(this.getNicMl().toString());
        pgGText = new Text(this.getPgG().toString());
        vgGText = new Text(this.getVgG().toString());
        nicGText = new Text(this.getNicG().toString());
        pgPercentText = new Text(this.getPgPer().toString() + "%");
        vgPercentText = new Text(this.getVgPer().toString() + "%");
        nicPercentText = new Text(this.getNicPer().toString() + "%");



        //CSS Styling
        getProduct().setId("grid-header");

        //Add controls
        thisPane.add(getProduct(), 0, 0, 5, 1);
        GridPane.setHalignment(getProduct(), HPos.CENTER);
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


        //Placeholders - CSS Styling
        pgMlText.setId("output");
        vgMlText.setId("output");
        nicMlText.setId("output");
        pgGText.setId("output-grams");
        vgGText.setId("output-grams");
        nicGText.setId("output-grams");
        pgPercentText.setId("output");
        vgPercentText.setId("output");
        nicPercentText.setId("output");


        //Add placeholders
        thisPane.add(pgMlText, 1, 2);
        GridPane.setHalignment(pgMlText, HPos.CENTER);
        thisPane.add(vgMlText, 1, 3);
        GridPane.setHalignment(vgMlText, HPos.CENTER);
        thisPane.add(nicMlText, 1, 4);
        GridPane.setHalignment(nicMlText, HPos.CENTER);
        thisPane.add(pgGText, 2, 2);
        GridPane.setHalignment(pgGText, HPos.CENTER);
        thisPane.add(vgGText, 2, 3);
        GridPane.setHalignment(vgGText, HPos.CENTER);
        thisPane.add(nicGText, 2, 4);
        GridPane.setHalignment(nicGText, HPos.CENTER);
        thisPane.add(pgPercentText, 3, 2);
        GridPane.setHalignment(pgPercentText, HPos.CENTER);
        thisPane.add(vgPercentText, 3, 3);
        GridPane.setHalignment(vgPercentText, HPos.CENTER);
        thisPane.add(nicPercentText, 3, 4);
        GridPane.setHalignment(nicPercentText, HPos.CENTER);
    }

    private void varInit() {
        this.setPgMl(0.0);
        this.setVgMl(0.0);
        this.setNicMl(0.0);
        this.setPgG(0.0);
        this.setVgG(0.0);
        this.setNicG(0.0);
        this.setPgPer(0);
        this.setVgPer(0);
        this.setNicPer(0);
        this.setRowCount(4);
    }

    private void flavorInit() {
        this.setFlavors(new FlavorView[10]);
        for (int i = 0; i < 3; i++) {
            this.getFlavors()[i] = new FlavorView(this);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        //Set initial window title
        primaryStage.setTitle("LiquidLab");
        this.setPane(new BorderPane());
        this.varInit();
        this.flavorInit();
        //Set Top Pane
        this.setTopPane(this.getPane());
        //Set Left pane
        this.setLeftPane(this.getPane());
        //Set Right Pane
        this.setRightPane(this.getPane());
        //Initialize grid in a scene
        Scene scene = new Scene(this.getPane(), 1000, 530);
        //Set scene to initial window
        primaryStage.setScene(scene);
        //Link CSS stylesheet
        scene.getStylesheets().add(UserInterface.class.getResource("Test.css").toExternalForm());
        //Display window
        primaryStage.show();
    }

    public void formatTextField(TextField field, int maxLen) {
        field.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > maxLen) {
                return null;
            } else {
                return change;
            }
        }));
    }

    public void update() {
        this.setRightPane(this.getPane());
    }

    private void calcButtonAction() {
        this.getFlavors()[0].reset();
        BusinessLogic bl = new BusinessLogic(this, getDB());
        bl.calculate();
    }

    private void addFlavButtonAction(Button btn) {
        this.getInput().getChildren().remove(btn);
        this.getFlavors()[this.getRowCount() - 1] = new FlavorView(this);
        this.getFlavors()[this.getRowCount() - 1].addToLeftGrid(this.getInput());
        this.getFlavors()[this.getRowCount() - 1].addToRightGrid(this.getOutput());
        if ((this.getRowCount()) < 10) {
            this.getInput().add(btn, 5, this.getRowCount() + 1);
            this.setRowCount(this.getRowCount() + 1);
        }

    }
}
