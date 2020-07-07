package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable, ParentController {

    private Inventory inventory;
    private Product product;


    @FXML
    private TextField invFd;

    @FXML
    private TextField priceFd;

    @FXML
    private TextField maxFd;

    @FXML
    private Label label;

    @FXML
    private Button saveBt;

    @FXML
    private Button cancelBt;

    @FXML
    private Label MinLb;

    @FXML
    private TextField minFd;

    @FXML
    private TextField nmFd;

    @FXML
    private TextField idFd;

    @FXML
    private TableView<?> firstTable;

    @FXML
    private TableColumn<?, ?> columnIDFirst;

    @FXML
    private TableColumn<?, ?> columnPrtNmFirst;

    @FXML
    private TableColumn<?, ?> columnInvLFirst;

    @FXML
    private TableColumn<?, ?> columnPricePUnFirst;

    @FXML
    private Button deleteBt;

    @FXML
    private Button searchBt;

    @FXML
    private Button addBt;

    @FXML
    private TableView<?> secondTable;

    @FXML
    private TableColumn<?, ?> columnIDSec;

    @FXML
    private TableColumn<?, ?> columnPrtNmSec;

    @FXML
    private TableColumn<?, ?> columnInvLSec;

    @FXML
    private TableColumn<?, ?> columnPricePUSec;



    public void init(Inventory myInventory) {
        inventory = myInventory;
    }

    public void init(Product item) {
        if (item != null) {
            product = item;
            idFd.setText(String.valueOf(item.getId()));
            nmFd.setText(item.getName());
            priceFd.setText(String.valueOf(item.getPrice()));
            minFd.setText(String.valueOf(item.getMin()));
            maxFd.setText(String.valueOf(item.getMax()));
            invFd.setText(String.valueOf(item.getStock()));
            label.setText("Modify Product");
        }
    }

    public void save() {
        String name = nmFd.getText();
        int inv = Integer.parseInt(invFd.getText());
        double cost = Double.parseDouble(priceFd.getText());
        int max = Integer.parseInt(maxFd.getText());
        int min = Integer.parseInt(minFd.getText());
        if (product == null) {
            int id = inventory.getProductId();
            Product item = new Product(id, name, cost, inv, max, min);
            inventory.addProduct(item);
        } else {
            product.setName(name);
            product.setPrice(cost);
            product.setStock(inv);
            product.setMax(max);
            product.setMin(min);
            inventory.updateProduct(product);
        }
        close();
    }

    public void close() {
        Stage stage = (Stage) cancelBt.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idFd.setDisable(true);

        cancelBt.setOnAction(actionEvent -> {
            close();
        });

    }

    @Override
    public void setLabel(String string) {
        label.setText(string);
    }
}
