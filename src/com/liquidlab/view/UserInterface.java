//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab.view;

import com.liquidlab.controller.BusinessLogic;
import com.liquidlab.model.DatabaseInteraction;
import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application {
    private static Text ourProduct;
    private static BorderPane ourPane;
    private static GridPane ourInput;
    private static GridPane ourOutput;
    private static TextField ourMlToMake;
    private static TextField ourNic;
    private static TextField ourPg;
    private static TextField ourVg;
    private static TextField ourRecipeName;
    private static Double ourPgMl;
    private static Double ourVgMl;
    private static Double ourNicMl;
    private static Double ourPgG;
    private static Double ourVgG;
    private static Double ourNicG;
    private static TextArea ourNotes;
    private static Double ourPgPer;
    private static Double ourVgPer;
    private static Double ourNicPer;
    private static Integer ourRows;
    private static FlavorView[] ourFlavors;
    private static ComboBox<String> ourFlList;

    public UserInterface() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("LiquidLab");
        setPane(new BorderPane());
        this.varInit();
        this.flavorInit();
        setTopPane(getPane());
        this.setLeftPane(getPane());
        setRightPane(getPane());
        Scene scene = new Scene(getPane(), 1010.0, 530.0);
        getMlToMake().requestFocus();
        primaryStage.setScene(scene);
        scene.getStylesheets().add(UserInterface.class.getResource("Test.css").toExternalForm());
        primaryStage.show();
        checkUserInputsTable();
    }

    private static MenuBar setMenu() {
        MenuBar menu = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuTools = new Menu("Tools");
        Menu menuAbout = new Menu("About");
        EventHandler<ActionEvent> setValAction = selectSetValue();
        MenuItem newWindow = new MenuItem("New");
        MenuItem saveSettings = new MenuItem("Save");
        MenuItem importFlav = new MenuItem("Import");
        MenuItem exportFlav = new MenuItem("Export");
        MenuItem quit = new MenuItem("Quit");
        menuFile.getItems().addAll(newWindow, saveSettings, importFlav, exportFlav, quit);
        MenuItem setWtTool = new MenuItem("Set Weight/Nic Values");
        setWtTool.setOnAction(setValAction);
        menuTools.getItems().addAll(setWtTool);
        MenuItem aboutProduct = new MenuItem("About LiquidLab");
        MenuItem aboutDiy = new MenuItem("About DIY Mixing");
        menuAbout.getItems().addAll(aboutProduct, aboutDiy);
        menu.getMenus().addAll(menuFile, menuTools, menuAbout);
        return menu;
    }

    private static EventHandler<ActionEvent> selectSetValue() {
        return (actionEvent) -> new ValueWindow();
    }

    private static Text getProduct() {
        return ourProduct;
    }

    private static void setProduct(Text newProd) {
        ourProduct = newProd;
    }

    public static TextField getMlToMake() {
        return ourMlToMake;
    }

    private static void setMlToMake(TextField txtFld) {
        ourMlToMake = txtFld;
    }

    public static TextField getNic() {
        return ourNic;
    }

    private static void setNic(TextField txtFld) {
        ourNic = txtFld;
    }

    public static TextField getPg() {
        return ourPg;
    }

    private static void setPg(TextField txtFld) {
        ourPg = txtFld;
    }

    public static TextField getVg() {
        return ourVg;
    }

    private static void setVg(TextField txtFld) {
        ourVg = txtFld;
    }

    public static TextArea getNotes() {
        return ourNotes;
    }

    private static void setNotes(TextArea txtArea) {
        ourNotes = txtArea;
    }

    private static GridPane getInput() {
        return ourInput;
    }

    private static void setInput(GridPane inputPane) {
        ourInput = inputPane;
    }

    private static GridPane getOutput() {
        return ourOutput;
    }

    private static void setOutput(GridPane outputPane) {
        ourOutput = outputPane;
    }

    private static Double getPgMl() {
        return ourPgMl;
    }

    public static void setPgMl(Double txt) {
        ourPgMl = txt;
    }

    private static Double getVgMl() {
        return ourVgMl;
    }

    public static void setVgMl(Double txt) {
        ourVgMl = txt;
    }

    private static Double getNicMl() {
        return ourNicMl;
    }

    public static void setNicMl(Double txt) {
        ourNicMl = txt;
    }

    private static Double getPgG() {
        return ourPgG;
    }

    public static void setPgG(Double txt) {
        ourPgG = txt;
    }

    private static Double getVgG() {
        return ourVgG;
    }

    public static void setVgG(Double txt) {
        ourVgG = txt;
    }

    private static Double getNicG() {
        return ourNicG;
    }

    public static void setNicG(Double txt) {
        ourNicG = txt;
    }

    private static Double getPgPer() {
        return ourPgPer;
    }

    public static void setPgPer(Double txt) {
        ourPgPer = txt;
    }

    private static Double getVgPer() {
        return ourVgPer;
    }

    public static void setVgPer(Double txt) {
        ourVgPer = txt;
    }

    private static Double getNicPer() {
        return ourNicPer;
    }

    public static void setNicPer(Double txt) {
        ourNicPer = txt;
    }

    public static FlavorView[] getFlavors() {
        return ourFlavors;
    }

    private static ComboBox<String> getComboBox() {
        return ourFlList;
    }

    private static void setComboBox(ComboBox<String> newBox) {
        ourFlList = newBox;
    }

    private static void setFlavors(FlavorView[] flavs) {
        ourFlavors = flavs;
    }

    private static BorderPane getPane() {
        return ourPane;
    }

    private static void setPane(BorderPane newPane) {
        ourPane = newPane;
    }

    public static int getRowCount() {
        return ourRows;
    }

    private static void setRowCount(int count) {
        ourRows = count;
    }

    private static TextField getRecipeName() {
        return ourRecipeName;
    }

    private static void setRecipeName(TextField recName) {
        ourRecipeName = recName;
    }

    private static void setTopPane(BorderPane parent) {
        MenuBar menuBar = setMenu();
        VBox title = new VBox(10.0);
        Text titleLbl = new Text("LiquidLab");
        Insets margin = new Insets(0.0, 0.0, 0.0, 30.0);
        VBox.setMargin(titleLbl, margin);
        titleLbl.setId("title-text");
        title.getChildren().addAll(menuBar, titleLbl);
        parent.setTop(title);
    }

    private void setLeftPane(BorderPane parent) {
        HBox lBox = new HBox(8.0);
        AnchorPane lAnchor = new AnchorPane();
        Button saveBtn = new Button("Save");
        Button calcBtn = new Button("Calculate");
        Button removeBtn = new Button("Remove");
        Insets margin = new Insets(20.0, 10.0, 30.0, 30.0);
        removeBtn.setDisable(true);
        lBox.getChildren().addAll(removeBtn, saveBtn, calcBtn);
        setInput(new GridPane());
        getInput().setId("left-grid");
        lAnchor.setId("left-anchor");
        AnchorPane.setBottomAnchor(lBox, 5.0);
        AnchorPane.setRightAnchor(lBox, 8.0);
        AnchorPane.setTopAnchor(getInput(), 10.0);
        AnchorPane.setLeftAnchor(getInput(), 10.0);
        BorderPane.setMargin(lAnchor, margin);
        lAnchor.getChildren().addAll(getInput(), lBox);
        parent.setLeft(lAnchor);
        this.setLeftControls();
        getComboBox().setOnAction((event) -> this.dbFlavorSelected(removeBtn));
        removeBtn.setOnMouseClicked((event) -> this.removeButtonAction());
        saveBtn.setOnMouseClicked((event) -> this.saveButtonAction());
        calcBtn.setOnMouseClicked((event) -> this.calcButtonAction());
        getMlToMake().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::textFieldIntEntered);
        getNic().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::textFieldDoubleEntered);
        getPg().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::pgKeyEntered);
        getVg().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::vgKeyEntered);
        FlavorView[] flavors = getFlavors();

        for (FlavorView flvw : flavors) {
            if (flvw != null) {
                flvw.getFlavPerField().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::textFieldDoubleEntered);
            }
        }

    }

    private void setLeftControls() {
        Label mlToMakeText = new Label("ML To Make:");
        Label mlToMakeUnit = new Label("ml");
        Label nicText = new Label("Target Nicotine:");
        Label nicUnit = new Label("mg");
        Label pgText = new Label("Target PG:");
        Label pgUnit = new Label("%");
        Label vgText = new Label("Target VG:");
        Label vgUnit = new Label("%");
        Text newFlav = new Text("Recipe");
        Button addFlav = new Button("+");
        Text buffer = new Text();

        int i;
        for(i = 0; i < getFlavors().length && getFlavors()[i] != null; ++i) {
            getFlavors()[i].addToLeftGrid(getInput());
        }

        setComboBox(new ComboBox<>());
        setMlToMake(new TextField());
        setNic(new TextField());
        setPg(new TextField());
        setVg(new TextField());
        setNotes(new TextArea());
        setRecipeName(new TextField());
        getRecipeName().setPromptText("Recipe Name");
        getNotes().setPromptText("Notes:");
        getRecipeName().addEventFilter(KeyEvent.KEY_PRESSED, UserInterface::recipeKeyPressed);
        getNotes().addEventFilter(KeyEvent.KEY_PRESSED, UserInterface::notesKeyPressed);
        getComboBox().setPromptText("Select Flavor...");
        DatabaseInteraction.selectFlavor("*");

        for(i = 0; i < DatabaseInteraction.getResults().size(); i += 3) {
            getComboBox().getItems().add(DatabaseInteraction.getResults().get(i));
        }

        getInput().add(getComboBox(), 0, 0, 3, 1);
        formatTextField(getMlToMake(), 4);
        getInput().add(mlToMakeText, 0, 1);
        getInput().add(getMlToMake(), 1, 1);
        getInput().add(mlToMakeUnit, 2, 1);
        formatTextField(getNic(), 3);
        getInput().add(nicText, 0, 2);
        getInput().add(getNic(), 1, 2);
        getInput().add(nicUnit, 2, 2);
        formatTextField(getPg(), 3);
        getInput().add(pgText, 0, 3);
        getInput().add(getPg(), 1, 3);
        getInput().add(pgUnit, 2, 3);
        formatTextField(getVg(), 3);
        getInput().add(vgText, 0, 4);
        getInput().add(getVg(), 1, 4);
        getInput().add(vgUnit, 2, 4);
        getInput().add(buffer, 0, 5);
        buffer.setId("buffer-text");
        addFlav.setId("add-button");
        newFlav.setId("grid-header");
        getRecipeName().setId("text-field-recipe");
        GridPane.setHalignment(newFlav, HPos.CENTER);
        GridPane.setValignment(newFlav, VPos.TOP);
        getInput().add(getNotes(), 0, 6, 3, 6);
        getInput().add(newFlav, 6, 0);
        getInput().add(getRecipeName(), 5, 1, 3, 1);
        getInput().add(addFlav, 5, 5);
        addFlav.setOnMouseClicked((event) -> addFlavButtonAction(addFlav));
    }

    private void dbFlavorSelected(Button removeBtn) {
        for(int selectedFlav = 0; selectedFlav < getFlavors().length; ++selectedFlav) {
            if(getFlavors()[selectedFlav] != null) {
                getFlavors()[selectedFlav].getVenField().setText("");
                getFlavors()[selectedFlav].getFlavField().setText("");
                getFlavors()[selectedFlav].getFlavPerField().setText("");
            }
        }

        String selectedFlavor = getComboBox().getSelectionModel().getSelectedItem();
        getRecipeName().setText(selectedFlavor);
        BusinessLogic.parseData(selectedFlavor);
        removeBtn.setDisable(false);
    }

    private static void setRightPane(BorderPane parent) {
        AnchorPane rAnchor = new AnchorPane();
        setOutput(new GridPane());
        Insets margin = new Insets(20.0, 30.0, 30.0, 0.0);
        ColumnConstraints col1 = new ColumnConstraints(120.0);
        ColumnConstraints col2 = new ColumnConstraints(30.0);
        ColumnConstraints col3 = new ColumnConstraints(40.0);
        ColumnConstraints col4 = new ColumnConstraints(60.0);
        getOutput().setId("right-grid");
        rAnchor.setId("right-anchor");
        getOutput().getColumnConstraints().addAll(col1, col2, col3, col4);

        for(int i = 0; i < getFlavors().length && getFlavors()[i] != null; ++i) {
            if(!getFlavors()[i].getFlavPerField().getText().isEmpty() && !getFlavors()[i].getFlavField().getText().isEmpty()) {
                getFlavors()[i].setFlavPercent(Double.parseDouble(getFlavors()[i].getFlavPerField().getText()));
                getFlavors()[i].addToRightGrid(getOutput());
            }
        }

        rAnchor.getChildren().add(getOutput());
        BorderPane.setMargin(rAnchor, margin);
        AnchorPane.setTopAnchor(getOutput(), 10.0);
        AnchorPane.setLeftAnchor(getOutput(), 10.0);
        parent.setRight(rAnchor);
        setRightControls(getOutput());
    }

    private static void setRightControls(GridPane thisPane) {
        Label mlLabel = new Label("ml");
        Label gLabel = new Label("grams");
        Label perLabel = new Label("% of Total");
        Label pgLabel = new Label("PG");
        Label vgLabel = new Label("VG");
        Label nicLabel = new Label("Nicotine");
        Text pgMlText = new Text(getPgMl().toString());
        Text vgMlText = new Text(getVgMl().toString());
        Text nicMlText = new Text(getNicMl().toString());
        Text pgGText = new Text(getPgG().toString());
        Text vgGText = new Text(getVgG().toString());
        Text nicGText = new Text(getNicG().toString());
        Text pgPercentText = new Text(getPgPer().toString() + "%");
        Text vgPercentText = new Text(getVgPer().toString() + "%");
        Text nicPercentText = new Text(getNicPer().toString() + "%");
        getProduct().setId("grid-header");
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
        pgMlText.setId("output");
        vgMlText.setId("output");
        nicMlText.setId("output");
        pgGText.setId("output-grams");
        vgGText.setId("output-grams");
        nicGText.setId("output-grams");
        pgPercentText.setId("output");
        vgPercentText.setId("output");
        nicPercentText.setId("output");
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
        setPgMl(0.0);
        setVgMl(0.0);
        setNicMl(0.0);
        setPgG(0.0);
        setVgG(0.0);
        setNicG(0.0);
        setPgPer(0.0);
        setVgPer(0.0);
        setNicPer(0.0);
        setRowCount(5);
        setProduct(new Text("Results"));
    }

    private void flavorInit() {
        setFlavors(new FlavorView[11]);

        for(int i = 0; i < 3; ++i) {
            getFlavors()[i] = new FlavorView();
        }

    }

    private static void checkUserInputsTable() {
        DatabaseInteraction.queryForValues();
        if(DatabaseInteraction.getResults().isEmpty()) {
            new ValueWindow();
        } else {
            BusinessLogic.getUserValues();
        }

    }

    static void formatTextField(TextField field, int maxLen) {
        field.setTextFormatter(new TextFormatter<>((change) -> {
            String newText = change.getControlNewText();
            return newText.length() > maxLen?null:change;
        }));
    }

    public static void update() {
        setRightPane(getPane());
    }

    private void calcButtonAction() {
        FlavorView.reset();
        if(!getRecipeName().getText().isEmpty()) {
            setProduct(new Text(getRecipeName().getText()));
        }

        BusinessLogic.calculate();
    }

    private void saveButtonAction() {
        boolean alreadyInDb = false;
        DatabaseInteraction.selectFlavor("*");
        String flavorData = BusinessLogic.combineFlavorData();
        if(!flavorData.equals("error")) {
            for(int i = 0; i < DatabaseInteraction.getResults().size(); i += 3) {
                if(DatabaseInteraction.getResults().get(i).equals(getRecipeName().getText())) {
                    DatabaseInteraction.updateFlavor(getRecipeName().getText(), flavorData, getNotes().getText());
                    alreadyInDb = true;
                    msgBox("INFO", 0);
                }
            }

            if(!alreadyInDb) {
                DatabaseInteraction.insertFlavor(getRecipeName().getText(), flavorData, getNotes().getText());
                msgBox("INFO", 1);
                getComboBox().getItems().add(getRecipeName().getText());
            }
        }

    }

    private static void addFlavButtonAction(Button btn) {
        getInput().getChildren().remove(btn);
        getFlavors()[2].getFlavPerField().addEventFilter(KeyEvent.KEY_PRESSED, UserInterface::flavPerKeyEntered);
        getFlavors()[getRowCount() - 2] = new FlavorView();
        getFlavors()[getRowCount() - 2].getFlavPerField().addEventFilter(KeyEvent.KEY_TYPED, UserInterface::flavPerKeyEntered);
        getFlavors()[getRowCount() - 2].addToLeftGrid(getInput());
        if(getRowCount() < 11) {
            getInput().add(btn, 5, getRowCount() + 1);
            setRowCount(getRowCount() + 1);
        }

    }

    private void removeButtonAction() {
        msgBox("CONFIRM", 0);
    }

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
                        str = "Cannot insert into database unless all \nflavors contain a name.";
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
                        str = "This recipe was successfully updated \nin the database.";
                        break;
                    case 1:
                        str = "This recipe was successfully added \nto the database.";
                        break;
                    case 2:
                        str = "This recipe was successfully removed \nfrom the database";
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
                    default:
                        alert.setContentText(str);
                        alert.showAndWait().ifPresent((rs) -> {
                            if (rs == ButtonType.OK) {
                                DatabaseInteraction.deleteFlavor(getRecipeName().getText());
                                getComboBox().getItems().remove(getRecipeName().getText());
                                msgBox("INFO", 2);
                                alert.close();
                            }

                        });
                }
                break;
        }

    }

    private static void textFieldIntEntered(KeyEvent inputEvent) {
        if(!inputEvent.getCharacter().matches("\\d")) {
            inputEvent.consume();
        }

    }

    private static void textFieldDoubleEntered(KeyEvent inputEvent) {
        if(!inputEvent.getCharacter().equals(".") && !inputEvent.getCharacter().matches("\\d")) {
            inputEvent.consume();
        }

    }

    private static void pgKeyEntered(KeyEvent inputEvent) {
        if(!inputEvent.isControlDown() && (inputEvent.getCharacter().equals("\b") || inputEvent.getCharacter().matches("\\d"))) {
            String newText;
            if(!getPg().getText().isEmpty()) {
                if(!inputEvent.getCharacter().equals("\b")) {
                    if(!getPg().getSelectedText().isEmpty()) {
                        getPg().setText(getPg().getText().replace(getPg().getSelectedText(), ""));
                    }

                    newText = getPg().getText() + inputEvent.getCharacter();
                } else {
                    newText = getPg().getText();
                }
            } else if(inputEvent.getCharacter().equals("\b")) {
                newText = "0";
            } else {
                newText = inputEvent.getCharacter();
            }

            Integer diff = 100 - Integer.parseInt(newText);
            if(diff >= 0) {
                getVg().setText(diff.toString());
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

    private static void vgKeyEntered(KeyEvent inputEvent) {
        if(!inputEvent.isControlDown() && (inputEvent.getCharacter().equals("\b") || inputEvent.getCharacter().matches("\\d"))) {
            String newText;
            if(!getVg().getText().isEmpty()) {
                if(!inputEvent.getCharacter().equals("\b")) {
                    if(!getVg().getSelectedText().isEmpty()) {
                        getVg().setText(getVg().getText().replace(getVg().getSelectedText(), ""));
                    }

                    newText = getVg().getText() + inputEvent.getCharacter();
                } else {
                    newText = getVg().getText();
                }
            } else if(inputEvent.getCharacter().equals("\b")) {
                newText = "0";
            } else {
                newText = inputEvent.getCharacter();
            }

            Integer diff = 100 - Integer.parseInt(newText);
            if(diff >= 0) {
                getPg().setText(diff.toString());
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

    private static void notesKeyPressed(KeyEvent inputEvent) {
        if(inputEvent.getCode() == KeyCode.TAB) {
            getRecipeName().requestFocus();
            inputEvent.consume();
        }

    }

    private static void recipeKeyPressed(KeyEvent inputEvent) {
        if(inputEvent.getCode() == KeyCode.TAB) {
            getFlavors()[0].getVenField().requestFocus();
            inputEvent.consume();
        }

    }

    private static void flavPerKeyEntered(KeyEvent inputEvent) {
        if(inputEvent.getCode() == KeyCode.TAB) {
            if(getFlavors()[3] != null) {
                getFlavors()[3].getVenField().requestFocus();
            }

            inputEvent.consume();
        }

        if(!inputEvent.getCharacter().equals(".") && !inputEvent.getText().equals("\b") && !inputEvent.getCharacter().matches("\\d")) {
            inputEvent.consume();
        }

    }
}
