package com.liquidlab.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


 class About {
    private static GridPane ourPane;

    private static GridPane getPane() { return ourPane; }
    private static void setPane(GridPane pane) { ourPane = pane; }

    About() {
        setPane(new GridPane());
        start(new Stage());
    }

    private static void start(Stage primaryStage) {
        primaryStage.setTitle("About LiquidLab");
        Scene scene = new Scene(getPane(), 300.0, 200.0);
        initGrid();
        getPane().requestFocus();
        primaryStage.setScene(scene);
        scene.getStylesheets().add(UserInterface.class.getResource("Test.css").toExternalForm());
        primaryStage.show();
    }

    private static void initGrid()
    {
        Text title = new Text("LiquidLab Beta 0.7");
        title.setId("grid-header");
        getPane().setId("left-pane");
        getPane().setPadding(new Insets(20));
        getPane().add(title, 0, 0, 1,  1);

        Label author = new Label("Author: Matthew Wayles");
        getPane().add(author, 0, 1, 1, 1);

        Label release = new Label("Released January 2017");
        getPane().add(release, 0, 2, 1, 1);

        Label buffer = new Label("");
        getPane().add(buffer, 0, 3, 1, 1);

        Label info = new Label("LiquidLab is a client application used for \n" +
                " DIY mixing of e-Liquid juice by weight. \n " +
                "It provides the ability to store \n " +
                "recipes and batches in a database for \n " +
                "easy saving and retrieval. LiquidLab Beta \n " +
                "0.7 is the first release of a project under \n " +
                "active progression.");
        getPane().add(info, 0, 5, 1, 1);


    }

}
