package org.kanakkupetti.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kanakkupetti.model.User;
import org.kanakkupetti.util.DatabaseHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class UsersController {

    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, Integer> colUserId;
    @FXML private TableColumn<User, String> colUserName;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, LocalDateTime> colLastLoggedIn;
    @FXML private TableColumn<User, Void> colEdit;
    @FXML private TableColumn<User, Void> colDelete;

    private ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colLastLoggedIn.setCellValueFactory(new PropertyValueFactory<>("lastLoggedIn"));

        addEditButtonToTable();
        addDeleteButtonToTable();

        loadUsersFromDB();

//        tblUsers.setItems(users);
    }

    @FXML
    private void handleAddUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/kanakkupetti/view/AddUserDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add User");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(loader.load()));

            AddUserDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isSaved()) {
                User newUser = controller.getNewUser();
                newUser.setUserId(users.size() + 1); // temporary until DB integration
                users.add(newUser);


                DatabaseHelper.addUser(newUser.getUserName(), newUser.getRole()     );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEditButtonToTable() {
        colEdit.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Edit");

            {
                btn.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white;");
                btn.setOnAction(event -> {
                    User selected = getTableView().getItems().get(getIndex());
                    showEditDialog(selected);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);

            }



        });


    }

    private void addDeleteButtonToTable() {
        colDelete.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Delete");

            {
                btn.setStyle("-fx-background-color: #e53935; -fx-text-fill: white;");
                btn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                            "Delete user: " + user.getUserName() + "?",
                            ButtonType.YES, ButtonType.NO);
                    confirm.setHeaderText(null);
                    Optional<ButtonType> result = confirm.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        users.remove(user);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void showEditDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/kanakkupetti/view/AddUserDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(loader.load()));

            AddUserDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUserData(user);

            dialogStage.showAndWait();

            if (controller.isSaved()) {
                tblUsers.refresh();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromDB() {
//        DatabaseHelper userService = new DatabaseHelper();
        users.clear();
        users.addAll(DatabaseHelper.getUsers());
        tblUsers.setItems(users);
    }
}
