package org.kanakkupetti.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class KanakkuController {

    @FXML private StackPane mainContent; // area where other pages will load

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

    /** -----------------------
     *  Navigation Handlers
     * ----------------------- */

    @FXML
    private void showInventory() {
        loadPage("/org/kanakkupetti/view/Inventory.fxml");
    }

    @FXML
    private void showUsers() {
        loadPage("/org/kanakkupetti/view/Users.fxml");
    }

    @FXML
    private void showBilling() {
        loadPage("/org/kanakkupetti/view/Billing.fxml");
    }

    @FXML
    private void showReports() {
        loadPage("/org/kanakkupetti/view/Reports.fxml");
    }

    /** -----------------------
     *  Button Handlers
     * ----------------------- */

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
        lblTotal.setText("₹100.00"); // demo
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

    /** -----------------------
     *  Helper Method
     * ----------------------- */

    private void loadPage(String fxmlPath) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainContent.getChildren().clear();
            mainContent.getChildren().add(page);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML: " + fxmlPath);
        }
    }
}
