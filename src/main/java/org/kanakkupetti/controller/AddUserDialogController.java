package org.kanakkupetti.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kanakkupetti.model.User;
import org.kanakkupetti.util.DatabaseHelper;

import java.time.LocalDateTime;

public class AddUserDialogController {

    @FXML private TextField txtUserName;
    @FXML private ComboBox<String> cmbRole;

    private Stage dialogStage;
    private boolean saved = false;
    private User user;

    @FXML
    public void initialize() {
        cmbRole.getItems().addAll("ADMIN", "CASHIER");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isSaved() {
        return saved;
    }

    public User getNewUser() {
        return new User(0, txtUserName.getText(), cmbRole.getValue(), LocalDateTime.now());
    }

    public void setUserData(User user) {
        this.user = user;
        txtUserName.setText(user.getUserName());
        cmbRole.setValue(user.getRole());
    }

    @FXML
    private void handleSave() {
        if (user != null) {
            user.setUserName(txtUserName.getText());
            user.setRole(cmbRole.getValue());
            DatabaseHelper.editUser(txtUserName.getText(),cmbRole.getValue(), user.getUserId());
        }
        saved = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
