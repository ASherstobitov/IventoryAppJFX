package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PrimaryController  implements Initializable {
    private Inventory inventory = new Inventory();


    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TableColumn<Product, Integer> idPrd;

    @FXML
    private TableColumn<Product, String> nmPrd;

    @FXML
    private TableColumn<Product, Integer> labelInv;

    @FXML
    private TableColumn<Product, Double> priceClmn;




    @FXML
    private TextField querySecond;

    @FXML
    private TableView<Part> tblPart;

    @FXML
    private TableColumn<Part, Integer> idPart;

    @FXML
    private TableColumn<Part, String> nmPart;

    @FXML
    private TableColumn<Part, Integer> invLabelPart;

    @FXML
    private TableColumn<Part, Double> pricePart;


    @FXML
    private Button addToFirstTable;

    @FXML
    private Button modifyInFirstTable;

    @FXML
    private Button deleteFromFirstTable;

    @FXML
    private TextField queryFirst;

    @FXML
    private Button searchInFirstTable;

    @FXML
    private Button addToSecondTable;

    @FXML
    private Button modifyInSecondTable;

    @FXML
    private Button deleteFromSecondTable;

    @FXML
    private Button searchInSecondTable;

    @FXML
    private Button leaveMainPanel;

    public void deleteProduct() {
        Product product = tblProduct.getSelectionModel().getSelectedItem();
        inventory.deleteProduct(product);
    }
    public void deletePart() {
        Part part = tblPart.getSelectionModel().getSelectedItem();
        inventory.deletePart(part);
    }


    public void searchPart() {
        ObservableList<Part> parts = inventory.lookupPart(queryFirst.getText());
        tblPart.setItems(parts);
    }

    public void searchProduct() {
        ObservableList<Product> products = inventory.lookupProduct(querySecond.getText());
        tblProduct.setItems(products);
    }


    public void functionPart(Part part) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary" + ".fxml"));
        try {
            Parent root = loader.load();
            PartController controller = loader.getController();
            controller.init(inventory);
            controller.init(part);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void functionProduct(Product product) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("productAdd" + ".fxml"));
        try {
            Parent root = loader.load();
            ProductController controller = loader.getController();
            controller.init(inventory);
            controller.init(product);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPart() {
        functionPart(null);
    }

    public void modifyPart() {
       Part part = tblPart.getSelectionModel().getSelectedItem();
       if (part != null) functionPart(part);
//       else
    }

    public void addProduct() {
        functionProduct(null);
    }
    public void modifyProduct() {
        Product product = tblProduct.getSelectionModel().getSelectedItem();
        if (product != null) functionProduct(product);
//       else
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idPrd.setCellValueFactory(new PropertyValueFactory<>("id"));
        nmPrd.setCellValueFactory(new PropertyValueFactory<>("name"));
        labelInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceClmn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblProduct.setItems(inventory.getAllProducts());


        idPart.setCellValueFactory(new PropertyValueFactory<>("id"));
        nmPart.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLabelPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePart.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPart.setItems(inventory.getAllParts());







        leaveMainPanel.setOnAction(actionEvent -> {
            Stage stage = (Stage) leaveMainPanel.getScene().getWindow();
            stage.close();
        });




    }
}