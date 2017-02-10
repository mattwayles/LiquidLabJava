package com.liquidlab.view;

import com.liquidlab.Flavor;
import com.liquidlab.model.DatabaseInteraction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


class ShoppingList {

    private static BorderPane ourPane;
    private static Scene ourScene;
    private static TableView<Flavor> ourTbl;
    private static ObservableList<Flavor> ourData;

    private static BorderPane getPane() {
        return ourPane;
    }

    private static void setPane(BorderPane gp) {
        ourPane = gp;
    }

    private static Scene getScene() {
        return ourScene;
    }

    private static void setScene(Scene newScene) {
        ourScene = newScene;
    }

    private static TableView<Flavor> getTable() { return ourTbl; }

    private static void setTable(TableView<Flavor> tbl) { ourTbl = tbl; }

    private static ObservableList<Flavor> getData() { return ourData; }

    private static void setData(ObservableList<Flavor> ol) { ourData = ol; }

    ShoppingList() {
        InventoryWindow iw = new InventoryWindow();
        InventoryWindow.close();
        setPane(new BorderPane());
        start(new Stage());
    }

    private static void start(Stage primaryStage) {
        primaryStage.setTitle("Shopping List");
        setScene(new Scene(getPane(), 330.0, 480.0));
        initGrid();
        getPane().requestFocus();
        primaryStage.setScene(getScene());
        getScene().getStylesheets().add(UserInterface.class.getResource("Inventory.css").toExternalForm());
        primaryStage.show();
    }

    private static void initGrid()
    {
        setTitle(new Text("Shopping List"));
        setTable(new TableView<>());
        populateTable();
        setBottom();
    }

    private static void setTitle(Text txt)
    {
        Insets margin = new Insets(20.0D, 15.0D, 15.0D, 15.0D);
        BorderPane.setAlignment(txt, Pos.CENTER);
        BorderPane.setMargin(txt, margin);
        getPane().setTop(txt);
        txt.setId("grid-header");
    }

    private static void populateTable()
    {

        TableColumn venCol;
        TableColumn flCol;

        getTable().setEditable(true);
        setData(FXCollections.observableArrayList());
        venCol = new TableColumn("Ven");
        venCol.setPrefWidth(45);
        venCol.setCellFactory(TextFieldTableCell.<Flavor>forTableColumn());
        venCol.setCellValueFactory(
                new PropertyValueFactory<Flavor, String>("ven"));
        venCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flavor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flavor, String> t) {
                t.getRowValue().setVen(t.getNewValue());
            }
        });
        flCol = new TableColumn("Flavor");
        flCol.setPrefWidth(233);
        flCol.setCellFactory(TextFieldTableCell.<Flavor>forTableColumn());
        flCol.setCellValueFactory(
                new PropertyValueFactory<Flavor, String>("name"));
        flCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flavor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flavor, String> t) {
                t.getRowValue().setName(t.getNewValue());
            }
        });

        BorderPane.setMargin(getTable(), new Insets(0, 25, 25, 25));
        getPane().setCenter(getTable());


        DatabaseInteraction.selectShoppingList();
        if(!DatabaseInteraction.getResults().isEmpty()) {
            for(int i = 0; i < DatabaseInteraction.getResults().size(); i = i + 2) {
                String ven = DatabaseInteraction.getResults().get(i);
                String fl = DatabaseInteraction.getResults().get(i + 1);
                getData().add(new Flavor(ven, fl, "10"));
            }
            getTable().setItems(getData());
            getTable().getColumns().addAll(venCol, flCol);
        }
    }

    private static void setBottom()
    {
        HBox bottom;
        Button saveBtn;
        Button addBtn;
        Button remBtn;
        Button invBtn;

        bottom = new HBox(8);
        saveBtn = new Button("Save");
        addBtn = new Button("Add");
        remBtn = new Button("Remove");
        invBtn = new Button("Inventory");

        bottom.setId("left-grid");
        bottom.getChildren().addAll(invBtn, remBtn, addBtn, saveBtn);
        bottom.setAlignment(Pos.BASELINE_RIGHT);
        BorderPane.setMargin(bottom, new Insets(10));
        getPane().setBottom(bottom);

        invBtn.setOnMouseClicked((event) -> invButtonAction());
        saveBtn.setOnMouseClicked((event) -> saveButtonAction());
        remBtn.setOnMouseClicked((event) -> removeButtonAction());
        addBtn.setOnMouseClicked((event) -> addButtonAction());
    }

    private static void invButtonAction()
    {
        Stage ex = (Stage)getScene().getWindow();
        ex.close();
        new InventoryWindow();
    }
    private static void saveButtonAction() {
        for(int i = 0; i < getData().size(); i++)
        {
            String ven = getData().get(i).getVen();
            String name = getData().get(i).getName();
            DatabaseInteraction.queryShoppingList(ven, name);
            if (DatabaseInteraction.getResults().size() == 0) {
                DatabaseInteraction.insertShoppingList(ven, name);
            }
        }
        UserInterface.msgBox("INFO", 0);
        Stage ex = (Stage)getScene().getWindow();
        ex.close();
        new ShoppingList();
    }

    private static void addButtonAction() {
        getTable().getItems().addListener((ListChangeListener<Flavor>) c -> Platform.runLater( () ->
                getTable().scrollTo(c.getList().size()-1) ));

        if (getTable().getItems().size() == 0)
        {
            DatabaseInteraction.insertShoppingList("", "New Flavor");
            populateTable();
            DatabaseInteraction.deleteShoppingList("", "New Flavor");
        }
        else {
            getTable().getItems().add(new Flavor("", "New Flavor", "10"));
        }
        getTable().getSelectionModel().select(getTable().getItems().size() -1 );

    }

    private static void removeButtonAction() {
        if (!getTable().getSelectionModel().getSelectedCells().isEmpty()) {
            Integer sRow = getTable().getSelectionModel().getSelectedCells().get(0).getRow();
            String ven = getData().get(sRow).getVen();
            String name = getData().get(sRow).getName();
            DatabaseInteraction.deleteShoppingList(ven, name);
            UserInterface.msgBox("INFO", 6);
            Stage ex = (Stage)getScene().getWindow();
            ex.close();
            new ShoppingList();
        }
        else {
            UserInterface.msgBox("ERROR", 10);
        }

    }
}
