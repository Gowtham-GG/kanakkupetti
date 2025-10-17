package org.kanakkupetti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KanakkuPettiFX extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {

        // Initialize the database
        org.kanakkupetti.util.DatabaseInitializer.initialize();


        stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/org/kanakkupetti/icon.png")));

        // Load the main content
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("base.fxml"));
        BorderPane root = fxmlLoader.load();

        // Create a custom title bar
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #1976d2; -fx-padding: 5;");
        titleBar.setPrefHeight(30);

        Text title = new Text("Kanakkupetti - Billing & Inventory");
        title.setFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Minimize Button
        Text minimizeButton = new Text("_");
        minimizeButton.setFill(Color.WHITE);
        minimizeButton.setStyle("-fx-cursor: hand; -fx-font-size: 18px; -fx-padding: 0 10px;");
        minimizeButton.setOnMouseEntered(event -> minimizeButton.setFill(Color.GREY));
        minimizeButton.setOnMouseExited(event -> minimizeButton.setFill(Color.WHITE));
        minimizeButton.setOnMouseClicked(event -> stage.setIconified(true));

        // Close Button
        Text closeButton = new Text("X");
        closeButton.setFill(Color.WHITE);
        closeButton.setStyle("-fx-cursor: hand; -fx-font-size: 18px; -fx-padding: 0 10px;");
        closeButton.setOnMouseEntered(event -> closeButton.setFill(Color.RED));
        closeButton.setOnMouseExited(event -> closeButton.setFill(Color.WHITE));
        closeButton.setOnMouseClicked(event -> stage.close());

        // Add buttons to the right corner
        HBox buttonBox = new HBox(10, minimizeButton, closeButton);
        buttonBox.setStyle("-fx-alignment: center-right;");

        titleBar.getChildren().addAll(title, spacer, buttonBox);

        // Enable dragging the window
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Add the title bar to the root
        root.setTop(titleBar);

        // Set the stage style to undecorated
        stage.initStyle(StageStyle.UNDECORATED);

        // Set the scene
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}