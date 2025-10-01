package org.kanakkupetti.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class KanakkuController {

    @FXML private TableView<?> itemsTable;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colName;
    @FXML private TableColumn<?, ?> colPrice;

    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtAddress;

    @FXML private TableView<?> billTable;
    @FXML private TableColumn<?, ?> billColName;
    @FXML private TableColumn<?, ?> billColQty;
    @FXML private TableColumn<?, ?> billColPrice;

    @FXML private Label lblTotal;

    // Button handlers
    @FXML
    private void handleAddItem() {
        System.out.println("Add Item clicked");
    }

    @FXML
    private void handleRemoveItem() {
        System.out.println("Remove Item clicked");
    }

    @FXML
    private void handleGetBill() {
        System.out.println("Get Bill clicked");
        lblTotal.setText("100.00"); // demo
    }

    @FXML
    private void handlePrint() {
        System.out.println("Print clicked");
    }

    @FXML
    private void handleSendMessage() {
        System.out.println("Send SMS/WhatsApp clicked");
    }

    @FXML
    private void handleReminder() {
        System.out.println("Reminder clicked");
    }
}
