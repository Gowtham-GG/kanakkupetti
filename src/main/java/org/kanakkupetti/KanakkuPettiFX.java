package org.kanakkupetti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class KanakkuPettiFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("base.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setScene(scene);
        stage.setTitle("Kanakkupetti - Billing & Inventory");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/org/kanakkupetti/icon.png")));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
