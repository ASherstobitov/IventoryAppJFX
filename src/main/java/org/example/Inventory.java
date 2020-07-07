package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public int getPartId() {
        if (allParts.size() == 0) return 1;
      return  allParts.get(allParts.size() - 1).getId() + 1;
    }

    public int getProductId() {
        if (allProducts.size() == 0) return 1;
        return allProducts.get(allProducts.size() - 1).getId() + 1;
    }

    public void addPart(Part part) {
        allParts.add(part);
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public Part lookUpPart(int partId) {
        return allParts.stream().filter(part -> part.getId() == partId).findFirst().get();
    }

    public Product lookUpProduct(int partId) {
        return allProducts.stream().filter(part -> part.getId() == partId).findFirst().get();
    }

    public ObservableList<Part> lookupPart(String partName) {

        return  FXCollections.observableArrayList((Collection<? extends Part>) allParts.stream()
               .filter(part -> part.getName().equals(partName))
               .collect(Collectors.toCollection(ArrayList::new)));
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return FXCollections.observableArrayList((Collection<? extends Product>) allProducts.stream()
                .filter(product -> product.getName().equals(productName))
                .collect(Collectors.toCollection(ArrayList::new)));

    }

    public void updatePart(Part selectedPart) {
        int index = allParts.indexOf(selectedPart);
       allParts.set(index, selectedPart);
    }

    public void updateProduct(Product selectedProduct) {
        int index = allProducts.indexOf(selectedProduct);
        allProducts.set(index, selectedProduct);
    }

    public void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    public void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }




}
