package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PartController implements Initializable, ParentController {

    private Inventory inventory;
    Part part;


    @FXML
    private Label label;

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton houseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField maxPriceField;

    @FXML
    private TextField compNmField;

    @FXML
    private TextField minPriceField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label editableLb;


    //    @FXML
//    private void switchToPrimary() throws IOException {
//        App.setRoot("primary");
//    }
    private void close() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }


    public void init(Inventory myInvetory) {
        inventory = myInvetory;
    }

    public void init(Part item) {
        if (item != null) {
            part = item;
            idField.setText(String.valueOf(item.getId()));
            nameField.setText(item.getName());
            priceField.setText(String.valueOf(item.getPrice()));
            minPriceField.setText(String.valueOf(item.getMin()));
            maxPriceField.setText(String.valueOf(item.getMax()));
            invField.setText(String.valueOf(item.getStock()));
            if (item instanceof InHouse) {
                compNmField.setText(String.valueOf((((InHouse)item).getMachineId())));
            } else
                compNmField.setText(((OutSourced)item).getCompanyName());
            label.setText("Modify Part");
        }
    }

    public void save() {
        String name = nameField.getText();
        int inv = Integer.parseInt(invField.getText());
        double cost = Double.parseDouble(priceField.getText());
        int max = Integer.parseInt(maxPriceField.getText());
        int min = Integer.parseInt(minPriceField.getText());
        String nameCm = compNmField.getText();


        if (part == null) {
            int id = inventory.getPartId();
            if (houseRadioButton.isSelected()) {
                InHouse inHouse = new InHouse(id, name, cost, inv, max, min);
                inHouse.setMachineId(Integer.parseInt(nameCm));
                inventory.addPart(inHouse);
            } else {
                OutSourced outSourced = new OutSourced(id, name, cost, inv, max, min);
                outSourced.setCompanyName(nameCm);
                inventory.addPart(outSourced);
            }
        } else {
            part.setName(name);
            part.setPrice(cost);
            part.setStock(inv);
            part.setMax(max);
            part.setMin(min);
            if (part instanceof InHouse) {

            }

            inventory.updatePart(part);
        }
        close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        houseRadioButton.setSelected(true);

        cancelButton.setOnAction(actionEvent -> close());
        idField.setDisable(true);
        this.toggleGroup = new ToggleGroup();
        this.houseRadioButton.setToggleGroup(toggleGroup);
        this.outsourcedRadioButton.setToggleGroup(toggleGroup);
//        compNmField.setEditable(true);


        houseRadioButton.setOnAction(actionEvent -> {
            if (this.toggleGroup.getSelectedToggle().equals(houseRadioButton)) {
                compNmField.setPromptText("Mach ID");
                editableLb.setText("Machine ID");

            }
        });

        outsourcedRadioButton.setOnAction(actionEvent -> {
            if (this.toggleGroup.getSelectedToggle().equals(outsourcedRadioButton)) {
                compNmField.setPromptText("Comp nm");
                editableLb.setText("Company name");
            }
        });


    }


    @Override
    public void setLabel(String string) {
        label.setText(string);
    }
}