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


class InventoryWindow {

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

    InventoryWindow() {
        setPane(new BorderPane());
        start(new Stage());
    }

    private static void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory");
        setScene(new Scene(getPane(), 330.0, 480.0));
        //initGrid(getScene());
        initGrid();
        getPane().requestFocus();
        primaryStage.setScene(getScene());
        getScene().getStylesheets().add(UserInterface.class.getResource("Inventory.css").toExternalForm());
        primaryStage.show();
    }

    private static void initGrid()
    {
        setTitle(new Text("Flavor Inventory"));
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
        //getPane().add(txt, 0, row, 3, 1);
        txt.setId("grid-header");
    }

    private static void populateTable()
    {

        TableColumn venCol;
        TableColumn flCol;
        TableColumn amtCol;
        TableColumn bufCol;

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
        flCol.setPrefWidth(165);
        flCol.setCellFactory(TextFieldTableCell.<Flavor>forTableColumn());
        flCol.setCellValueFactory(
                new PropertyValueFactory<Flavor, String>("name"));
        flCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flavor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flavor, String> t) {
                t.getRowValue().setName(t.getNewValue());
            }
        });
        amtCol = new TableColumn("ML Left");
        amtCol.setPrefWidth(65);
        amtCol.setCellValueFactory(new PropertyValueFactory<Flavor, String>("amt"));
        amtCol.setCellFactory(TextFieldTableCell.<Flavor>forTableColumn());
        amtCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flavor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flavor, String> t) {

                t.getRowValue().setAmt(t.getNewValue());
            }
        });
        bufCol = new TableColumn();
        bufCol.setPrefWidth(0);
        bufCol.setCellValueFactory(new PropertyValueFactory<Flavor, String>("amt"));
        bufCol.setCellFactory((Object column) -> {
            return new TableCell<Flavor, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);
                    TableRow<Flavor> currentRow = getTableRow();

                    if (!isEmpty()) {
                        if(Double.parseDouble(item) < 3.0)
                        {
                            currentRow.setId("table-row-low");
                            //currentRow.setStyle("-fx-background-color: red");
                            if(currentRow.getItem() != null) {
                                DatabaseInteraction.queryShoppingList(currentRow.getItem().getVen(),
                                        currentRow.getItem().getName());
                                if (DatabaseInteraction.getResults().size() == 0) {
                                    DatabaseInteraction.insertShoppingList(currentRow.getItem().getVen(),
                                            currentRow.getItem().getName());
                                }
                            }

                        }
                        else
                        {
                            currentRow.setId("table-row-cell");
                            //currentRow.setStyle("-fx-background-color: linear-gradient(#61a2b1, #2a5058)");
                            if(currentRow.getItem() != null) {
                               // DatabaseInteraction.queryShoppingList(currentRow.getItem().getVen(),
                                //        currentRow.getItem().getName());
                                //if (DatabaseInteraction.getResults().size() == 0) {
                                    DatabaseInteraction.deleteShoppingList(currentRow.getItem().getVen(),
                                            currentRow.getItem().getName());
                                //}
                            }
                        }
                    }
                }
            };
        });

        BorderPane.setMargin(getTable(), new Insets(0, 25, 25, 25));
        getPane().setCenter(getTable());
        //getPane().add(tbl, 0, 1);


        DatabaseInteraction.selectInventory();
        if(!DatabaseInteraction.getResults().isEmpty()) {
            for(int i = 0; i < DatabaseInteraction.getResults().size(); i = i + 3) {
                String ven = DatabaseInteraction.getResults().get(i);
                String fl = DatabaseInteraction.getResults().get(i + 1);
                String amt = DatabaseInteraction.getResults().get(i + 2);
                getData().add(new Flavor(ven, fl, amt));
            }
            (getData()).sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
            //SortedList<Flavor> sortFlav = new SortedList<>(getData());
            //getTable().setItems(sortFlav);
            //sortFlav.comparatorProperty().bind(getTable().comparatorProperty());
            getTable().setItems(getData());
            getTable().getColumns().addAll(venCol, flCol, amtCol, bufCol);
        }
    }

    private static void setBottom()
    {
        HBox bottom;
        Button saveBtn;
        Button addBtn;
        Button remBtn;
        Button shopListBtn;

        bottom = new HBox(8);
        saveBtn = new Button("Save");
        addBtn = new Button("Add");
        remBtn = new Button("Remove");
        shopListBtn = new Button("Shopping List");

        bottom.setId("left-grid");
        bottom.getChildren().addAll(shopListBtn, remBtn, addBtn, saveBtn);
        bottom.setAlignment(Pos.BASELINE_RIGHT);
        BorderPane.setMargin(bottom, new Insets(10));
        getPane().setBottom(bottom);
        //getPane().add(updateBtn, 0, 2);
        //BorderPane().setHalignment(updateBtn, HPos.CENTER);

        shopListBtn.setOnMouseClicked((event) -> shopListButtonAction());
        remBtn.setOnMouseClicked((event) -> removeButtonAction());
        addBtn.setOnMouseClicked((event) -> addButtonAction());
        saveBtn.setOnMouseClicked((event) -> saveButtonAction());
    }

    private static void shopListButtonAction()
    {
        Stage ex = (Stage)getScene().getWindow();
        ex.close();
        new ShoppingList();
    }
    private static void saveButtonAction() {
        for(int i = 0; i < getData().size(); i++)
        {
            String ven = getData().get(i).getVen();
            String name = getData().get(i).getName();
            String amt = getData().get(i).getAmt();
            DatabaseInteraction.queryInventory(ven, name);
            if (DatabaseInteraction.getResults().size() == 0)
            {
                DatabaseInteraction.insertInventory(ven, name, amt);
            }
            else
            {
                DatabaseInteraction.updateInventory(ven, name, amt);
            }
        }
        UserInterface.msgBox("INFO", 0);
        Stage ex = (Stage)getScene().getWindow();
        ex.close();
        new InventoryWindow();
    }
    private static void addButtonAction() {
        getTable().getItems().addListener((ListChangeListener<Flavor>) c -> Platform.runLater( () ->
                getTable().scrollTo(c.getList().size()-1) ));
        getTable().getItems().add(new Flavor("", "New Flavor", "10"));
        getTable().getSelectionModel().select(getTable().getItems().size() -1 );

    }

    private static void removeButtonAction() {
        if (!getTable().getSelectionModel().getSelectedCells().isEmpty()) {
                Integer sRow = getTable().getSelectionModel().getSelectedCells().get(0).getRow();
                String ven = getData().get(sRow).getVen();
                String name = getData().get(sRow).getName();
                DatabaseInteraction.deleteInventory(ven, name);
                UserInterface.msgBox("INFO", 3);
            }
            else
            {
                UserInterface.msgBox("ERROR", 10);
            }
        Stage ex = (Stage)getScene().getWindow();
        ex.close();
        new InventoryWindow();

    }

 static void close()
    {
        Stage ex = (Stage)getScene().getWindow();
        ex.close();
    }
}
