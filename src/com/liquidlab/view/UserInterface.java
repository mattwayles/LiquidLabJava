package com.liquidlab.view;

import com.liquidlab.controller.BusinessLogic;
import com.liquidlab.model.DatabaseInteraction;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.Collator;
import java.util.ArrayList;

/**
 * UserInterface.java
 * Author: Matt Wayles
 * Created: 19 DEC 2016
 * Modified: 26 JAN 2017
 *
 * This class creates and initializes the LiquidLab User Interface. It uses a BorderPane base with a menu populating the top pane and
 * two GridPanes populating the left and right border panes. The left GridPane provides a ComboBox displaying saved flavors retrieved
 * from the database, as well as TextFields for user inputs on a new recipe. The right GridPane displays calculation results in ML,
 * grams, and percentages.
 */

public class UserInterface extends Application {
    //region Static Class Variables
    private static Double ourPgG;
    private static Double ourVgG;
    private static Double ourNicG;
    private static Double ourPgMl;
    private static Double ourVgMl;
    private static Double ourNicMl;
    private static Double ourPgPer;
    private static Double ourVgPer;
    private static Double ourNicPer;
    private static Double ourMlLeft;
    private static Integer ourRows;
    private static Text ourProduct;
    private static TextField ourPg;
    private static TextField ourVg;
    private static TextField ourNic;
    private static TextField ourMlToMake;
    private static TextField ourRecipeName;
    private static TextArea ourNotes;
    private static GridPane ourInput;
    private static GridPane ourOutput;
    private static BorderPane ourPane;
    private static Button ourAddFlav;
    private static FlavorView[] ourFlavors;
    private static ObservableList<String> cboxItems;
    private static ArrayList<String[]> flavsNotInInv;
    private static ComboBox<String> ourFlList;
    //endregion

    //region Initiation Methods

    /**
     * Main method to launch the User Interface.
     *
     * @param args Arguments sent to the GUI launcher
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method for the User Interface
     *
     * @param primaryStage The main GUI window that opens on execution
     */
    public void start(Stage primaryStage) {
        //Declare scene
        Scene scene;

        //Initialize variables
        scene = varInit();

        //Populate scene with controls
        primaryStage.setTitle("LiquidLab");
        setTopPane(primaryStage, getPane());    //BorderPane Top Pane - Menu and Title
        setLeftPane(getPane()); //BorderPane Left Pane - Recipe Controls
        setRightPane(getPane());    //Borderpane Right Pane - Calculation Results

        //Format stage and show
        primaryStage.setScene(scene); //Assign scene to stage
        scene.getStylesheets().add(UserInterface.class.getResource("Test.css").toExternalForm()); //Apply CSS styling
        primaryStage.show();
        getMlToMake().requestFocus(); //Focus on the top-left TextField
    }

    /**
     * Initialize variables on launch
     *
     * @return the new Scene to assign to the Stage
     */
    private Scene varInit() {
        Scene scene;
        setPane(new BorderPane());
        setFlavors(new FlavorView[11]);
        scene = new Scene(getPane(), 1010.0, 530.0);

        //Initialize variables to avoid NullpointerExceptions
        setPgMl(0.0);
        setVgMl(0.0);
        setNicMl(0.0);
        setPgG(0.0);
        setVgG(0.0);
        setNicG(0.0);
        setPgPer(0.0);
        setVgPer(0.0);
        setNicPer(0.0);
        setRowCount(5); //Row count begins at 5 because of Recipe Label, Recipe name, and flavor controls
        setProduct(new Text("Results"));
        setComboBoxItems(FXCollections.observableArrayList());

        //Initialize first 3 left FlavorViews
        for (int i = 0; i < 3; ++i) {
            getFlavors()[i] = new FlavorView();
        }

        return scene;
    }
    //endregion

    //region Control Set Methods

    /**
     * Method to create and instantiate the menubar at the top-left corner of the stage.
     *
     * @param stage The initial GUI window the opens on execution.
     * @return The completed menu control.
     */
    private static MenuBar setMenu(Stage stage) {
        //Declare local variables
        MenuBar menu;
        Menu menuFile;
        Menu menuTools;
        Menu menuAbout;
        MenuItem quit;
        MenuItem newWindow;
        MenuItem setWtTool;
        MenuItem inventory;
        MenuItem shoppingList;
        MenuItem saveSettings;
        MenuItem aboutProduct;
        EventHandler<ActionEvent> setValAction;
        EventHandler<ActionEvent> setInventory;
        EventHandler<ActionEvent> setShoppingList;
        EventHandler<ActionEvent> setQuitAction;
        EventHandler<ActionEvent> setAboutLlAction;

        //Initialize local variables
        menu = new MenuBar();
        menuFile = new Menu("File");
        menuTools = new Menu("Tools");
        menuAbout = new Menu("About");
        quit = new MenuItem("Quit");
        newWindow = new MenuItem("New");
        saveSettings = new MenuItem("Save");
        aboutProduct = new MenuItem("About LiquidLab");
        setWtTool = new MenuItem("Set Weight/Nic Values");
        inventory = new MenuItem("Inventory");
        shoppingList = new MenuItem("Shopping List");
        setValAction = selectSetValue();
        setInventory = selectSetInventory();
        setShoppingList = selectSetShoppingList();
        setQuitAction = selectQuit();
        setAboutLlAction = selectAboutLl();

        //Add all menu items to menu
        menuFile.getItems().addAll(newWindow, saveSettings, quit);
        menuTools.getItems().addAll(setWtTool, inventory, shoppingList);
        menuAbout.getItems().addAll(aboutProduct);
        menu.getMenus().addAll(menuFile, menuTools, menuAbout);

        //Set event handlers for selecting items
        newWindow.setOnAction((event) -> selectNewWindow(stage));
        saveSettings.setOnAction((event) -> saveButtonAction());
        quit.setOnAction(setQuitAction);
        setWtTool.setOnAction(setValAction);
        inventory.setOnAction(setInventory);
        shoppingList.setOnAction(setShoppingList);
        aboutProduct.setOnAction(setAboutLlAction);

        return menu;
    }

    /**
     * Formats and populates the top pane of the BorderPane
     *
     * @param stage  The GUI window
     * @param parent The base BorderPane
     */
    private static void setTopPane(Stage stage, BorderPane parent) {
        //Declare top controls
        VBox title;
        Text titleLbl;
        Insets margin;
        MenuBar menuBar;

        //Initialize top controls
        menuBar = setMenu(stage);
        title = new VBox(10.0);
        titleLbl = new Text("LiquidLab");
        margin = new Insets(0.0, 0.0, 0.0, 30.0);

        //Add top controls
        VBox.setMargin(titleLbl, margin);
        title.getChildren().addAll(menuBar, titleLbl);
        parent.setTop(title);

        //Set CSS Styling
        titleLbl.setId("title-text");
    }

    /**
     * Formats and populates the left pane of the BorderPane
     *
     * @param parent The base BorderPane
     */
    private static void setLeftPane(BorderPane parent) {
        //Declare left pane members
        HBox lBox;
        AnchorPane lAnchor;
        Button saveBtn;
        Button calcBtn;
        Button removeBtn;
        Insets margin;

        //Initialize left pane members
        setInput(new GridPane());
        lBox = new HBox(8.0);
        lAnchor = new AnchorPane();
        saveBtn = new Button("Save");
        calcBtn = new Button("Calculate");
        removeBtn = new Button("Remove");
        margin = new Insets(20.0, 10.0, 30.0, 30.0);

        //Add buttons to horizontal box
        lBox.getChildren().addAll(removeBtn, saveBtn, calcBtn);
        removeBtn.setDisable(true);

        //Set CSS Styling
        getInput().setId("left-grid");
        lAnchor.setId("left-anchor");

        //Format and populate the AnchorPane
        AnchorPane.setBottomAnchor(lBox, 5.0);
        AnchorPane.setRightAnchor(lBox, 8.0);
        AnchorPane.setTopAnchor(getInput(), 10.0);
        AnchorPane.setLeftAnchor(getInput(), 10.0);
        BorderPane.setMargin(lAnchor, margin);
        lAnchor.getChildren().addAll(getInput(), lBox);
        parent.setLeft(lAnchor);

        //Set textfields, textareas, and labels
        setLeftControls();

        //Set event handlers for ComboBox and buttons
        removeBtn.setOnMouseClicked((event) -> removeButtonAction());
        saveBtn.setOnMouseClicked((event) -> saveButtonAction());
        calcBtn.setOnMouseClicked((event) -> calcButtonAction());
        getComboBox().setOnAction((event) -> dbFlavorSelected(removeBtn));
    }

    /**
     * Method to set the smaller controls in the left pane - TextFields, TextAreas, Text, Labels, ComboBox
     */
    private static void setLeftControls() {
        //Declare controls
        Text buffer;
        Text newFlav;
        Label vgUnit;
        Label vgText;
        Label pgUnit;
        Label pgText;
        Label nicText;
        Label nicUnit;
        Label mlToMakeUnit;
        Label mlToMakeText;
        FlavorView[] flavors;

        //Initialize controls
        buffer = new Text();
        newFlav = new Text("Recipe");
        pgUnit = new Label("%");
        vgUnit = new Label("%");
        pgText = new Label("Target PG:");
        vgText = new Label("Target VG:");
        nicUnit = new Label("mg");
        nicText = new Label("Target Nicotine:");
        mlToMakeUnit = new Label("ml");
        mlToMakeText = new Label("ML To Make:");
        flavors = getFlavors();
        setAddFlavBtn(new Button("+"));
        setComboBox(new ComboBox<>());
        setMlToMake(new TextField());
        setNic(new TextField());
        setPg(new TextField());
        setVg(new TextField());
        setNotes(new TextArea());
        setRecipeName(new TextField());

        //Insert controls into the left pane
        int i;
        for (i = 0; i < getFlavors().length && getFlavors()[i] != null; ++i) {
            getFlavors()[i].addToLeftGrid(getInput());
        }
        getInput().add(getComboBox(), 0, 0, 3, 1);
        getInput().add(mlToMakeText, 0, 1);
        getInput().add(getMlToMake(), 1, 1);
        getInput().add(mlToMakeUnit, 2, 1);
        getInput().add(nicText, 0, 2);
        getInput().add(getNic(), 1, 2);
        getInput().add(nicUnit, 2, 2);
        getInput().add(pgText, 0, 3);
        getInput().add(getPg(), 1, 3);
        getInput().add(pgUnit, 2, 3);
        getInput().add(vgText, 0, 4);
        getInput().add(getVg(), 1, 4);
        getInput().add(vgUnit, 2, 4);
        getInput().add(buffer, 0, 5);
        getInput().add(getNotes(), 0, 6, 3, 6);
        getInput().add(newFlav, 6, 0);
        getInput().add(getRecipeName(), 5, 1, 3, 1);
        getInput().add(getAddFlavBtn(), 5, 5);

        //Format certain controls for max string length
        formatTextField(getVg(), 3);
        formatTextField(getPg(), 3);
        formatTextField(getNic(), 3);
        formatTextField(getMlToMake(), 4);

        //Apply CSS Styling and alignment
        buffer.setId("buffer-text");
        getAddFlavBtn().setId("add-button");
        newFlav.setId("grid-header");
        getRecipeName().setId("text-field-recipe");
        GridPane.setHalignment(newFlav, HPos.CENTER);
        GridPane.setValignment(newFlav, VPos.TOP);

        //Set control prompt text
        getNotes().setPromptText("Notes:");
        getRecipeName().setPromptText("Recipe Name");
        getComboBox().setPromptText("Select Flavor...");


        //Populate ComboBox from Database
        DatabaseInteraction.selectFlavor("*");
        for (i = 0; i < DatabaseInteraction.getResults().size(); i += 3) {
            getComboBoxItems().add(DatabaseInteraction.getResults().get(i));
        }

        //Add event listeners to certain controls
        getComboBox().setOnMouseClicked((event) -> checkRecipeAvailability());
        getComboBox().setOnMouseEntered((event) -> dbFlavorHover());
        getComboBox().setCellFactory(param -> dbFlavorHover());
        getAddFlavBtn().setOnMouseClicked((event) -> addFlavButtonAction());
        getMlToMake().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::textFieldIntEntered);
        getNic().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::textFieldDoubleEntered);
        getPg().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::pgKeyEntered);
        getVg().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::vgKeyEntered);
        getRecipeName().addEventFilter(KeyEvent.KEY_PRESSED, UserInterface::recipeKeyPressed);
        getNotes().addEventFilter(KeyEvent.KEY_PRESSED, UserInterface::notesKeyPressed);
        for (FlavorView flvw : flavors) {
            if (flvw != null) {
                flvw.getFlavPerField().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::textFieldDoubleEntered);
            }
        }
    }

    /**
     * Method to format and populate the left pane of the BorderPane
     *
     * @param parent The base BorderPane
     */
    private static void setRightPane(BorderPane parent) {
        //Declare right pane members
        Insets margin;
        Button made;
        AnchorPane rAnchor;
        ColumnConstraints col1;
        ColumnConstraints col2;
        ColumnConstraints col3;
        ColumnConstraints col4;

        //Initialize right pane members
        rAnchor = new AnchorPane();
        setOutput(new GridPane());
        made = new Button("Made");
        margin = new Insets(20.0, 30.0, 30.0, 0.0);
        col1 = new ColumnConstraints(120.0);
        col2 = new ColumnConstraints(30.0);
        col3 = new ColumnConstraints(40.0);
        col4 = new ColumnConstraints(60.0);

        //Populate pane
        BorderPane.setMargin(rAnchor, margin);
        AnchorPane.setTopAnchor(getOutput(), 10.0);
        AnchorPane.setLeftAnchor(getOutput(), 10.0);
        AnchorPane.setTopAnchor(made, 0.0);
        AnchorPane.setRightAnchor(made, 0.0);
        getOutput().getColumnConstraints().addAll(col1, col2, col3, col4);
        rAnchor.getChildren().addAll(getOutput(), made);
        parent.setRight(rAnchor);
        setRightControls(getOutput());

        //Set CSS Styling
        getOutput().setId("right-grid");
        rAnchor.setId("right-anchor");

        //If any flavor fields are populated, display their calculations in this pane
        for (int i = 0; i < getFlavors().length && getFlavors()[i] != null; ++i) {
            if (!getFlavors()[i].getFlavPerField().getText().isEmpty()) {
                getFlavors()[i].setFlavPercent(Double.parseDouble(getFlavors()[i].getFlavPerField().getText()));
                getFlavors()[i].addToRightGrid(getOutput());
            }
        }
        //Check to see if Nic/Weight data has been assigned by the user
        checkUserInputsTable();

        //Button event handler
        made.setOnMousePressed((event) -> madeBtnPressed());
    }

    /**
     * Method to set the smaller controls in the right pane - TextFields, Text, Labels
     *
     * @param thisPane The GridPane to add controls on
     */
    private static void setRightControls(GridPane thisPane) {
        //Declare local controls
        Label mlLabel;
        Label gLabel;
        Label perLabel;
        Label pgLabel;
        Label vgLabel;
        Label nicLabel;
        Text pgMlText;
        Text vgMlText;
        Text nicMlText;
        Text pgGText;
        Text vgGText;
        Text nicGText;
        Text pgPercentText;
        Text vgPercentText;
        Text nicPercentText;

        //Initialize local controls
        mlLabel = new Label("ml");
        gLabel = new Label("grams");
        perLabel = new Label("% of Total");
        pgLabel = new Label("PG");
        vgLabel = new Label("VG");
        nicLabel = new Label("Nicotine");
        pgMlText = new Text(getPgMl().toString());
        vgMlText = new Text(getVgMl().toString());
        nicMlText = new Text(getNicMl().toString());
        pgGText = new Text(getPgG().toString());
        vgGText = new Text(getVgG().toString());
        nicGText = new Text(getNicG().toString());
        pgPercentText = new Text(getPgPer().toString() + "%");
        vgPercentText = new Text(getVgPer().toString() + "%");
        nicPercentText = new Text(getNicPer().toString() + "%");

        //Add local controls
        thisPane.add(getProduct(), 0, 0, 5, 1);
        thisPane.add(mlLabel, 1, 1);
        thisPane.add(gLabel, 2, 1);
        thisPane.add(perLabel, 3, 1);
        thisPane.add(pgLabel, 0, 2);
        thisPane.add(vgLabel, 0, 3);
        thisPane.add(nicLabel, 0, 4);
        thisPane.add(pgMlText, 1, 2);
        thisPane.add(vgMlText, 1, 3);
        thisPane.add(nicMlText, 1, 4);
        thisPane.add(pgGText, 2, 2);
        thisPane.add(vgGText, 2, 3);
        thisPane.add(nicGText, 2, 4);
        thisPane.add(pgPercentText, 3, 2);
        thisPane.add(vgPercentText, 3, 3);
        thisPane.add(nicPercentText, 3, 4);

        //Set control alignment
        GridPane.setHalignment(getProduct(), HPos.CENTER);
        GridPane.setHalignment(mlLabel, HPos.CENTER);
        GridPane.setHalignment(gLabel, HPos.CENTER);
        GridPane.setHalignment(perLabel, HPos.CENTER);
        GridPane.setHalignment(pgLabel, HPos.RIGHT);
        GridPane.setHalignment(vgLabel, HPos.RIGHT);
        GridPane.setHalignment(nicLabel, HPos.RIGHT);
        GridPane.setHalignment(pgMlText, HPos.CENTER);
        GridPane.setHalignment(vgMlText, HPos.CENTER);
        GridPane.setHalignment(nicMlText, HPos.CENTER);
        GridPane.setHalignment(pgGText, HPos.CENTER);
        GridPane.setHalignment(vgGText, HPos.CENTER);
        GridPane.setHalignment(nicGText, HPos.CENTER);
        GridPane.setHalignment(pgPercentText, HPos.CENTER);
        GridPane.setHalignment(vgPercentText, HPos.CENTER);
        GridPane.setHalignment(nicPercentText, HPos.CENTER);

        //Set CSS Styling
        getProduct().setId("grid-header");
        pgMlText.setId("output");
        vgMlText.setId("output");
        nicMlText.setId("output");
        pgGText.setId("output-grams");
        vgGText.setId("output-grams");
        nicGText.setId("output-grams");
        pgPercentText.setId("output");
        vgPercentText.setId("output");
        nicPercentText.setId("output");


    }
    //endregion

    //region Functional Methods

    /**
     * Method to create a new window and discard the old one when user selects File > New menu item.
     *
     * @param stage New GUI window with fresh controls.
     */
    private static void selectNewWindow(Stage stage) {
        //Declare new window
        Stage primaryStage;

        //Initialize new window
        primaryStage = new Stage();

        //Reset class variables
        FlavorView.setLrow(2);
        FlavorView.setRrow(5);

        //Close old window and open new one
        stage.close();
        new UserInterface().start(primaryStage);
    }

    /**
     * Checks for Nic/Weight data supplied by the user. If it is not supplied, a window is summoned
     * that will not close until the values are provided.
     */
    private static void checkUserInputsTable() {
        //Check if the values exist
        DatabaseInteraction.queryForValues();
        if (DatabaseInteraction.getResults().isEmpty()) { //if they don't
            new ValueWindow();  //open new ValueWindow to request user input
        } else { //if they do
            BusinessLogic.getUserValues(); //retrieve them
        }

    }

    /**
     * Method to restrict TextField input values
     *
     * @param field  The TextField to restrict
     * @param maxLen The maximum number of inputs allowed
     */
    static void formatTextField(TextField field, int maxLen) {
        field.setTextFormatter(new TextFormatter<>((change) -> {
            String newText = change.getControlNewText();
            return newText.length() > maxLen ? null : change;
        }));
    }

    /**
     * Public method to update the right pane after calculation
     */
    public static void update() {
        setRightPane(getPane());
    }

    /**
     * Error-checking method with pre-set error messages
     */
    public static void msgBox(String type, int num) {
        Alert alert;
        String str;
        switch (type) {
            case "ERROR":
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Invalid Input");
                str = "An error has been encountered.";
                switch (num) {
                    case 0:
                        str = "Please enter a valid amount of liquid to make.";
                        break;
                    case 1:
                        str = "Please enter valid PG/VG amounts (0-100).";
                        break;
                    case 2:
                        str = "This recipe does not contain enough PG.";
                        break;
                    case 3:
                        str = "This recipe does not contain enough VG.";
                        break;
                    case 4:
                        str = "Ths ratio of Nic to PG/VG is incorrect.";
                        break;
                    case 5:
                        str = "An error was encountered accessing the Database.";
                        break;
                    case 6:
                        str = "Please assign all values before continuing.";
                        break;
                    case 7:
                        str = "A percentage has not been supplied for \n one or more flavors.";
                        break;
                    case 8:
                        str = "All flavors must contain a name in order to \n complete this action.";
                        break;
                    case 9:
                        str = "You must provide a recipe name to save \nto the database.";
                        break;
                    case 10:
                        str = "You must select a flavor to remove.";
                        break;
                }

                alert.setContentText(str);
                alert.showAndWait().ifPresent((rs) -> {
                    if (rs == ButtonType.OK) {
                        alert.close();
                    }

                });
                break;
            case "INFO":
                alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Success");
                str = "The operation was successful.";
                switch (num) {
                    case 0:
                        str = "Database has been updated successfully.";
                        break;
                    case 1:
                        str = "This recipe was successfully added \nto the database.";
                        break;
                    case 2:
                        str = "This recipe was successfully removed \nfrom the database.";
                        break;
                    case 3:
                        str = "This flavor was successfully removed \nfrom the inventory.";
                        break;
                    case 4:
                        str = "Flavor usage has been subtracted from \nthe inventory.";
                        if (!getRecipeName().getText().isEmpty()) {
                            str = str + "\n\n Enjoy your " + getMlToMake().getText() + " ml of " + getRecipeName().getText() + "!";
                        }
                        break;
                    case 5:
                        str = "Flavors added to inventory at default 10ml" +
                                "\n\nPlease use \"Tools > Inventory\" to modify.";
                        break;
                    case 6:
                        str = "This flavor was successfully removed \nfrom the Shopping List.";
                        break;
                }

                alert.setContentText(str);
                alert.showAndWait().ifPresent((rs) -> {
                    if (rs == ButtonType.OK) {
                        alert.close();
                    }

                });
                break;
            case "CONFIRM":
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("");
                str = "Do you Wish to perform this operation?.";
                switch (num) {
                    case 0:
                        str = "Are you sure you want to remove this recipe?";

                        break;
                    case 1:
                        str = "The following flavors are not contained \n" +
                                " in your inventory. Would you like to add them? \n \n";
                        for (String[] flav : getFlavsNotInInv()) {
                            if (flav[0].isEmpty()) {
                                str = str + flav[1] + "\n";
                            } else {
                                str = str + "[" + flav[0] + "] " + flav[1] + "\n";
                            }
                        }
                }
                alert.setContentText(str);
                String finalStr = str;
                alert.showAndWait().ifPresent((rs) -> {
                    if (rs == ButtonType.OK) {
                        if (finalStr.contains("remove this recipe")) {
                            DatabaseInteraction.deleteFlavor(getRecipeName().getText());
                            getComboBoxItems().remove(getRecipeName().getText());
                            //getComboBox().getItems().remove(getRecipeName().getText());
                            msgBox("INFO", 2);
                        } else if (finalStr.contains("add them")) {
                            for (String[] flav : getFlavsNotInInv()) {
                                DatabaseInteraction.insertInventory(flav[0], flav[1], "10");
                            }
                            msgBox("INFO", 5);
                        }
                    }
                    alert.close();

                });
                break;
        }

    }
    //endregion

    //region Event Handlers

    /**
     * Method to handle the user pressing the "Calculate" button
     */
    private static void calcButtonAction() {
        FlavorView.reset();
        if (!getRecipeName().getText().isEmpty()) { //If a recipe name was supplied,
            setProduct(new Text(getRecipeName().getText())); //Update the right-pane label
        }
        BusinessLogic.calculate();
    }

    /**
     * Method to handle the user pressing the "Save" button
     */
    private static void saveButtonAction() {
        checkInventory();
        checkRecipeAvailability();
        BusinessLogic.save();
        for (String str : getComboBoxItems()) {
            if (str.equals(getRecipeName().getText())) {
                getComboBox().getSelectionModel().select(str);
            }
        }
    }

    /**
     * Method to handle the user pressing the "+" button to add another flavor
     */
    public static void addFlavButtonAction() {
        getInput().getChildren().remove(getAddFlavBtn()); //remove the button
        getFlavors()[getRowCount() - 2] = new FlavorView(); //replace it with new flavor controls
        getFlavors()[getRowCount() - 2].addToLeftGrid(getInput());

        //Equip new flavor controls with appropriate event handlers
        getFlavors()[2].getFlavPerField().addEventFilter(KeyEvent.KEY_PRESSED, UserInterface::flavPerKeyEntered);
        getFlavors()[getRowCount() - 2].getFlavPerField().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::flavPerKeyEntered);

        //Replace the button on the next row
        if (getRowCount() < 11) {
            getInput().add(getAddFlavBtn(), 5, getRowCount() + 1);
            setRowCount(getRowCount() + 1);
        }

    }

    /**
     * Method to ask the user to confirm after pressing the "Remove" button
     */
    private static void removeButtonAction() {
        msgBox("CONFIRM", 0);
    }

    /**
     * Method to create ToolTip when hovering mouse over ComboBox item
     *
     * @return The new cell with updated ToolTip
     */
    public static ListCell dbFlavorHover() {
        Tooltip tt;

        tt = new Tooltip();

        return new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) { //Return the updated ListCell item
                String notes = "";
                super.updateItem(item, empty); //Update the ListCell
                if (item != null) {
                    setText(item);
                    //Grab Notes column from DB
                    DatabaseInteraction.selectNotes(item);
                    if (!DatabaseInteraction.getResults().isEmpty()) {
                        notes = DatabaseInteraction.getResults().get(0);
                    }
                    //Create tooltip containing Notes info
                    if (!notes.isEmpty()) {
                        tt.setText(notes);
                    } else {
                        tt.setText(item);    //Populate tooltip with flavor name
                    }
                    setTooltip(tt);
                } else {
                    setText(null);
                    setTooltip(null);
                }
                getComboBox().setItems(new SortedList<>(getComboBoxItems(), Collator.getInstance()));
            }
        };
    }

    private static void checkRecipeAvailability() {
        getComboBox().setCellFactory(list -> new ListCellClass());

    }



    static class ListCellClass extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Tooltip tt;
            String notes;


            tt = new Tooltip();
            notes = "";
            setGraphic(null);
            Circle rect = new Circle(3);
            if (item != null) {
                if (!getMlToMake().getText().isEmpty()) {
                    String[] flavArr = BusinessLogic.setFlavArr(item);    //Set an array of flavors [%,Ven,Fl]
                    for (int j = 0; j < flavArr.length; j = j + 3)   //For each element in this new array
                    {
                        setMlLeft(0.0);
                        String percentNeeded = flavArr[j];
                        String ven = flavArr[j + 1];
                        String name = flavArr[j + 2];
                        BusinessLogic.setFlavorMl((Double.parseDouble(getMlToMake().getText()) *        //Set Total ML for this flavor
                                Double.parseDouble(percentNeeded)) / 100);
                        DatabaseInteraction.queryInventory(ven, name);  //Query inventory for this fl
                        if (DatabaseInteraction.getResults().size() > 1) {
                            setMlLeft(Double.parseDouble(DatabaseInteraction.getResults().get(2)));
                        }
                        if (BusinessLogic.getFlavorMl() > getMlLeft()) {
                            rect.setFill(Color.RED);
                            setGraphic(rect);
                            break;
                        }
                    }
                }

                    //Set tooltip
                    DatabaseInteraction.selectNotes(item);
                    if (!DatabaseInteraction.getResults().isEmpty()) {
                        notes = DatabaseInteraction.getResults().get(0);
                    }
                    //Create tooltip containing Notes info
                    if (!notes.isEmpty()) {
                        tt.setText(notes);
                    } else {
                        tt.setText(item);    //Populate tooltip with flavor name
                    }
                    setTooltip(tt);
                } else {
                    setText(null);
                    setTooltip(null);
                    }
                    BusinessLogic.setFlavorMl(0.0);
                    setText(item);
        }
    }

    /**
     * Method to handle event when user selects a flavor from the ComboBox
     */
    private static void dbFlavorSelected(Button removeBtn) {
        //Clear any potentially populated textfield
        for(int selectedFlav = 0; selectedFlav < getFlavors().length; ++selectedFlav) {
            if(getFlavors()[selectedFlav] != null) {
                getFlavors()[selectedFlav].getVenField().setText("");
                getFlavors()[selectedFlav].getFlavField().setText("");
                getFlavors()[selectedFlav].getFlavPerField().setText("");
            }
        }
        //Populate textfields with new flavor information based on selection
        String selectedFlavor = getComboBox().getSelectionModel().getSelectedItem();
        getRecipeName().setText(selectedFlavor);
        BusinessLogic.parseData(selectedFlavor);
        removeBtn.setDisable(false);
    }

    /**
     * Method to create new ValueWindow when user selects NIC/Weight Tool from the Tools menu.
     * @return  The user's menu selection
     */
    private static EventHandler<ActionEvent> selectSetValue() {
        return (actionEvent) -> new ValueWindow();
    }

    private static void madeBtnPressed() {
        checkInventory();
        String ven = "";
        String name;
        String amt;
        for (int i = 0; i < getFlavors().length; i++)
        {
            if (getFlavors()[i] != null) {
                if(!getFlavors()[i].getVenField().getText().isEmpty()) {
                    ven = getFlavors()[i].getVenField().getText();
                }
                name = getFlavors()[i].getFlavField().getText();
                DatabaseInteraction.queryInventory(ven, name);
                if(DatabaseInteraction.getResults().size() > 0) {
                    amt = DatabaseInteraction.getResults().get(2);
                    Double newAmt = Double.parseDouble(amt) - getFlavors()[i].getFlavMl();
                    DatabaseInteraction.updateInventory(ven, name, BusinessLogic.round(newAmt).toString());
                }
            }
        }
        msgBox("INFO", 4);
    }

    private static void checkInventory()
    {

        setFlavsNotInInv(new ArrayList<>());
        for (int i = 0; i < getFlavors().length; i++) {
            if (getFlavors()[i] != null) {
                String ven = getFlavors()[i].getVenField().getText();
                String name = getFlavors()[i].getFlavField().getText();
                DatabaseInteraction.queryInventory(ven, name);
                if (DatabaseInteraction.getResults().isEmpty()) //found exact match
                {
                    if(!name.isEmpty()) {
                        String[] flav = new String[] {ven, name};
                        getFlavsNotInInv().add(flav);
                    }
                }
            }
        }
        if (!getFlavsNotInInv().isEmpty()) //there are listed flavors that are not in the inventory
        {
            msgBox("CONFIRM", 1);
        }
    }


    /**
     * Method to create new InventoryWindow when user selects Inventory from the Tools menu.
     * @return  The user's menu selection
     */
    private static EventHandler<ActionEvent> selectSetInventory() {
        return (actionEvent) -> new InventoryWindow();
    }

    private static EventHandler<ActionEvent> selectSetShoppingList() {
        return (actionEvent) -> new ShoppingList();
    }

    /**
     * Method to close the program when the File > Quit option is chosen.
     * @return The user's menu selection
     */
    private static EventHandler<ActionEvent> selectQuit()
    {
        return (actionEvent) -> Platform.exit();
    }

    /**
     * Method to open "About LiquidLab" window when About > About LiquidLab menu item is chosen.
     * @return The user's menu selection
     */
    private static EventHandler<ActionEvent> selectAboutLl() {return (actionEvent) -> new About(); }

    /**
     * Event handler for entering an Integer in a textfield (consumes all non-integers)
     * @param inputEvent    The key being added
     */
    private static void textFieldIntEntered(KeyEvent inputEvent) {
        if(!inputEvent.getCharacter().matches("\\d")) {
            inputEvent.consume();
        }

    }

    /**
     * Event handler for entering a Double in a textfield (consumes all non-integers except ".")
     * @param inputEvent    The key being entered
     */
    private static void textFieldDoubleEntered(KeyEvent inputEvent) {
        if(!inputEvent.getCharacter().equals(".") && !inputEvent.getCharacter().matches("\\d")) {
            inputEvent.consume();
        }

    }

    /**
     * Event handler for pressing a key within the PG % control. Contains conditions to consume a non-integer and
     * non-period, accept an integer or period, tab to the appropriate control, accept the delete key, and set
     * the VG % field to the 100 minus the supplied value.
     * @param inputEvent    The key being entered
     */
    private static void pgKeyEntered(KeyEvent inputEvent) {
        //Key must be a non-letter and the control key cannot be depressed
        if((inputEvent.getCharacter().equals("\b") || inputEvent.getCharacter().matches("\\d"))
                && !inputEvent.isControlDown()) {
            String newText;
            if(!getPg().getText().isEmpty()) { //Added to avoid NullPtr when 'delete' is used in empty box
                if(!inputEvent.getCharacter().equals("\b")) { //If non-backspace was entered
                    if(!getPg().getSelectedText().isEmpty()) { //If any text is selected
                        getPg().setText(getPg().getText().replace(getPg().getSelectedText(), "")); //delete selected text
                    }
                    newText = getPg().getText() + inputEvent.getCharacter(); //Append the new integer
                } else { //if delete was used in a populated box
                    newText = getPg().getText(); //delete the value and set the new text
                }
            }
            else if(inputEvent.getCharacter().equals("\b")) { //if delete is used on empty box
                newText = "0"; //set text to 0
            }
            else { //if non-delete is entered in empty box
                newText = inputEvent.getCharacter(); //set the new text
            }

            //PG box value successfully set, now set corresponding VG value
            Integer diff = 100 - Integer.parseInt(newText);
            if(diff >= 0) { //if valid value was entered
                getVg().setText(diff.toString()); //set VG textfield to difference value
            } else { //if invalid value is entered, pop an error
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
        } else { //if a non-integer, non-period, non-backspace is entered, or the control key is depressed
            inputEvent.consume();
        }

    }

    /**
     * Event handler for pressing a key within the VG % control. Contains conditions to consume a non-integer and
     * non-period, accept an integer or period, tab to the appropriate control, accept the delete key, and set
     * the PG % field to the 100 minus the supplied value.
     * @param inputEvent    The key being entered
     */
    private static void vgKeyEntered(KeyEvent inputEvent) {
        //Key must be a non-letter and the control key cannot be depressed
        if(!inputEvent.isControlDown() && (inputEvent.getCharacter().equals("\b") || inputEvent.getCharacter().matches("\\d"))) {
            String newText;
            if(!getVg().getText().isEmpty()) { //Added to avoid NullPtr when 'delete' is used in empty box
                if(!inputEvent.getCharacter().equals("\b")) { //If non-backspace was entered
                    if(!getVg().getSelectedText().isEmpty()) { //If any text is selected
                        getVg().setText(getVg().getText().replace(getVg().getSelectedText(), "")); //delete selected text
                    }
                    newText = getVg().getText() + inputEvent.getCharacter(); //Append the new integer
                } else { //if delete was used in a populated box
                    newText = getVg().getText();  //delete the value and set the new text
                }
            } else if(inputEvent.getCharacter().equals("\b")) { //if delete is used on empty box
                newText = "0";  //set text to 0
            } else {  //if non-delete is entered in empty box
                newText = inputEvent.getCharacter();  //set the new text
            }

            //VG box value successfully set, now set corresponding PG value
            Integer diff = 100 - Integer.parseInt(newText);
            if(diff >= 0) {  //if valid value was entered
                getPg().setText(diff.toString());  //set VG textfield to difference value
            } else {  //if invalid value is entered, pop an error
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
        } else {  //if a non-integer, non-period, non-backspace is entered, or the control key is depressed
            inputEvent.consume();
        }

    }

    /**
     * Event handler for pressing tab within the Notes textarea.
     * @param inputEvent    The key being entered
     */
    private static void notesKeyPressed(KeyEvent inputEvent) {
        //Define control to focus when Tab key is pressed in Notes TextArea
        if(inputEvent.getCode() == KeyCode.TAB) {
            getRecipeName().requestFocus();
            inputEvent.consume();
        }

    }

    /**
     * Event handler for pressing tab within the Recipe TextField
     * @param inputEvent    The key being entered.
     */
    private static void recipeKeyPressed(KeyEvent inputEvent) {
        //Define control to focus when Tab key is pressed in Recipe TextArea
        if(inputEvent.getCode() == KeyCode.TAB) {
            getFlavors()[0].getVenField().requestFocus();
            inputEvent.consume();
        }

    }

    /**
     * Event handler for pressing tab within the third flavor percent textfield
     * @param inputEvent    The key being entered.
     */
    private static void flavPerKeyEntered(KeyEvent inputEvent) {
        //Define control to focus when Tab key is pressed in Recipe TextArea
        if(inputEvent.getCode() == KeyCode.TAB) {
            if(getFlavors()[3] != null) {
                getFlavors()[3].getVenField().requestFocus();
            }

            inputEvent.consume();
        }
        //Throw out any input that is non-numerical, not a period, not Tab, and not backspace
        if(!inputEvent.getCharacter().equals(".") && !inputEvent.getText().equals("\b") && !inputEvent.getCharacter().matches("\\d")) {
            inputEvent.consume();
        }
    }
    //endregion

    //region Getters & Setters
    /**
     * Get the calculated Grams of PG required for the recipe
     * @return  Calculated grams of PG required for the recipe
     */
    private static Double getPgG() {
        return ourPgG;
    }

    /**
     * Sets the grams of PG required for the recipe after calculation
     * @param txt   The grams of PG required for the recipe
     */
    public static void setPgG(Double txt) {
        ourPgG = txt;
    }

    /**
     * Get the calculated Grams of VG required for the recipe
     * @return  Calculated grams of VG required for the recipe
     */
    private static Double getVgG() {
        return ourVgG;
    }

    /**
     * Sets the grams of VG required for the recipe after calculation
     * @param txt   The grams of VG required for the recipe
     */
    public static void setVgG(Double txt) {
        ourVgG = txt;
    }

    /**
     * Get the calculated Grams of Nicotine required for the recipe
     * @return  Calculated grams of Nicotine required for the recipe
     */
    private static Double getNicG() {
        return ourNicG;
    }

    /**
     * Sets the grams of Nicotine required for the recipe after calculation
     * @param txt   The grams of Nicotine required for the recipe
     */
    public static void setNicG(Double txt) {
        ourNicG = txt;
    }

    /**
     * Get the calculated Milliliters of PG required for the recipe
     * @return  Calculated Milliliters of PG required for the recipe
     */
    private static Double getPgMl() {
        return ourPgMl;
    }

    /**
     * Sets the milliliters of PG required for the recipe after calculation
     * @param txt   The milliliters of PG required for the recipe
     */
    public static void setPgMl(Double txt) {
        ourPgMl = txt;
    }

    /**
     * Get the calculated Milliliters of VG required for the recipe
     * @return  Calculated Milliliters of VG required for the recipe
     */
    private static Double getVgMl() {
        return ourVgMl;
    }

    /**
     * Sets the milliliters of VG required for the recipe after calculation
     * @param txt   The milliliters of VG required for the recipe
     */
    public static void setVgMl(Double txt) {
        ourVgMl = txt;
    }

    /**
     * Get the calculated Milliliters of Nicotine required for the recipe
     * @return  Calculated Milliliters of Nicotine required for the recipe
     */
    private static Double getNicMl() {
        return ourNicMl;
    }

    /**
     * Sets the milliliters of VG required for the recipe after calculation
     * @param txt   The milliliters of VG required for the recipel
     */
    public static void setNicMl(Double txt) {
        ourNicMl = txt;
    }

    /**
     * Get the calculated percentage of PG required for the recipe
     * @return  Calculated percentage of PG required for the recipe
     */
    private static Double getPgPer() {
        return ourPgPer;
    }

    /**
     * Sets the percentage of PG required for the recipe after calculation
     * @param txt   The percentage of PG required for the recipel
     */
    public static void setPgPer(Double txt) {
        ourPgPer = txt;
    }

    /**
     * Get the calculated percentage of VG required for the recipe
     * @return  Calculated percentage of VG required for the recipe
     */
    private static Double getVgPer() {
        return ourVgPer;
    }

    /**
     * Sets the percentage of VG required for the recipe after calculation
     * @param txt   The percentage of VG required for the recipel
     */
    public static void setVgPer(Double txt) {
        ourVgPer = txt;
    }

    /**
     * Get the calculated percentage of Nicotine required for the recipe
     * @return  Calculated percentage of Nicotine required for the recipe
     */
    private static Double getNicPer() {
        return ourNicPer;
    }

    /**
     * Sets the percentage of Nicotine required for the recipe after calculation
     * @param txt   The percentage of Nicotine required for the recipel
     */
    public static void setNicPer(Double txt) {
        ourNicPer = txt;
    }

    private static Double getMlLeft() { return ourMlLeft; }

    private static void setMlLeft(Double mlleft) { ourMlLeft = mlleft; }

    /**
     * Get the GridPane row value to insert controls into the left GridPane
     * @return  The left GridPane row available for control insertion
     */
    public static int getRowCount() {
        return ourRows;
    }

    /**
     * Set the GridPane row value to insert controls into the left GridPane
     * @param count  The left GridPane row available for control insertion
     */
    private static void setRowCount(int count) {
        ourRows = count;
    }

    /**
     * Get the name of the product resulting from the calculation, based on what is supplied
     * in the "Recipe Name" textbox.
     * @return  The name of the final product
     */
    private static Text getProduct() {
        return ourProduct;
    }

    /**
     * Set the name of the product resulting from the calculation by extracting the value
     * from the "Recipe Name" textbox.
     * @param newProd The name of the final product
     */
    private static void setProduct(Text newProd) {
        ourProduct = newProd;
    }

    /**
     * Get the user-supplied amount of PG to add to the recipe as a percentage.
     * @return  The amount of PG to add, as a percentage
     */
    public static TextField getPg() {
        return ourPg;
    }

    /**
     * Set the amount of PG to add to the recipe as a percentage.
     * @param txtFld    The TextField containing the amount of PG to add, as a percentage
     */
    private static void setPg(TextField txtFld) {
        ourPg = txtFld;
    }

    /**
     * Get the user-supplied amount of VG to add to the recipe as a percentage.
     * @return  The amount of VG to add, as a percentage
     */
    public static TextField getVg() {
        return ourVg;
    }

    /**
     * Set the amount of VG to add to the recipe as a percentage.
     * @param txtFld    The TextField containing the amount of VG to add, as a percentage
     */
    private static void setVg(TextField txtFld) {
        ourVg = txtFld;
    }

    /**
     * Get the user-supplied amount of nicotine to add to the recipe, in milligrams
     * @return  The amount of nicotine to add, in milligrams
     */
    public static TextField getNic() {
        return ourNic;
    }

    /**
     * Set the amount of nicotine to add to the recipe with user-supplied data
     * @param txtFld    The TextField containing the amount of nicotine to add, in milligrams
     */
    private static void setNic(TextField txtFld) {
        ourNic = txtFld;
    }
    /**
     * Get the user-supplied volume of liquid to produce
     * @return  The volume of liquid to make, in millileters
     */
    public static TextField getMlToMake() {
        return ourMlToMake;
    }

    /**
     * Set the amount of liquid to make with user-supplied data
     * @param txtFld    The TextField containing the volume to produce, in millimeters
     */
    private static void setMlToMake(TextField txtFld) {
        ourMlToMake = txtFld;
    }

    /**
     * Get the name of the current recipe
     * @return  The name of the current recipe
     */
    public static TextField getRecipeName() {
        return ourRecipeName;
    }

    /**
     * Set the name of the current recipe
     * @param recName  The name of the current recipe
     */
    private static void setRecipeName(TextField recName) {
        ourRecipeName = recName;
    }

    /**
     * Get the Notes associated with a recipe
     * @return The notes associated with a recipe
     */
    public static TextArea getNotes() {
        return ourNotes;
    }

    /**
     * Associate the text in the Notes TextArea with a recipe
     * @param txtArea The TextArea containing the notes to be associated with the recipe
     */
    private static void setNotes(TextArea txtArea) {
        ourNotes = txtArea;
    }

    private static ArrayList<String[]> getFlavsNotInInv() { return flavsNotInInv; }

    private static void setFlavsNotInInv(ArrayList<String[]> flavs) { flavsNotInInv = flavs; }

    /**
     * Gets the left (input) GridPane
     * @return The left (input) GridPane
     */
    private static GridPane getInput() {
        return ourInput;
    }

    /**
     * Sets the left (input) GridPane
     * @param inputPane A GridPane object to be set in the left BorderPane field
     */
    private static void setInput(GridPane inputPane) {
        ourInput = inputPane;
    }

    /**
     * Gets the right (output) GridPane
     * @return The right (output) GridPane
     */
    private static GridPane getOutput() {
        return ourOutput;
    }

    /**
     * Sets the right (output) GridPane
     * @param outputPane A GridPane object to be set in the right BorderPane field
     */
    private static void setOutput(GridPane outputPane) {
        ourOutput = outputPane;
    }

    /**
     * Get the base BorderPane that contains GridPane items
     * @return The base BorderPane
     */
    private static BorderPane getPane() {
        return ourPane;
    }

    /**
     * Set the base BorderPane within the scene
     * @param newPane The base BorderPane
     */
    private static void setPane(BorderPane newPane) {
        ourPane = newPane;
    }

    /**
     * Retrieve the button that is used to add flavors to the left grid
     * @return  The Add Flavors (+) button
     */
    private static Button getAddFlavBtn() { return ourAddFlav; }

    /**
     * Set the button that is used to add flavors to the left grid
     * @param btn The Add Flavors (+) button
     */
    private static void setAddFlavBtn(Button btn) { ourAddFlav = btn; }

    /**
     * Gets the list of flavors retrieved from the database or supplied by the user
     * @return A list of Flavor objects
     */
    public static FlavorView[] getFlavors() {
        return ourFlavors;
    }

    /**
     * Sets the list of flavors frm either a database query or user input
     * @param flavs An ArrayList of type Flavor
     */
    private static void setFlavors(FlavorView[] flavs) {
        ourFlavors = flavs;
    }

    /**
     * Gets the ComboBox used to select saved flavors
     * @return The flavor ComboBox
     */
    public static ComboBox<String> getComboBox() {
        return ourFlList;
    }

    public static ObservableList<String> getComboBoxItems() { return cboxItems; }

    private static void setComboBoxItems(ObservableList<String> items) { cboxItems = items; }
    /**
     * Sets a ComboBox in the GridPane
     * @param newBox A ComboBox of type String
     */
    private static void setComboBox(ComboBox<String> newBox) {
        ourFlList = newBox;
    }
    //endregion
}
