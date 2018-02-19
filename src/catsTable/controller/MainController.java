package catsTable.controller;

import catsTable.model.domain.Cat;
import catsTable.model.domain.CatColor;
import catsTable.model.service.CatService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainController {

    private CatService catService;
    @FXML
    private VBox catsVBox;

    @FXML
    private Button addButton;
    @FXML
    private TextField addCatNameTextField;
    @FXML
    private TextField addCatWeighTextField;
    @FXML
    private ComboBox addCatColorComboBox;
    @FXML
    private TextField addCatAgeTextField;

    @FXML
    private VBox editVBox;
    @FXML
    private TextField editNameTextField;
    @FXML
    private TextField editWeighTextField;
    @FXML
    private ComboBox editColorComboBox;
    @FXML
    private TextField editAgeTextField;
    @FXML
    private Label editIdLabel;


    @FXML
    public void initialize() {
        catService = CatService.getInstance();

        populateColorComboBox(addCatColorComboBox);
        populateColorComboBox(editColorComboBox);
        showCats();
        hideEditVBox();
        hideEditIdLabel();
    }

    private void hideEditIdLabel() {
        editIdLabel.setVisible(false);
    }

    private void hideEditVBox() {
        editVBox.setVisible(false);
    }

    private void showEditVBox() {
        editVBox.setVisible(true);
    }


    private void populateColorComboBox(ComboBox comboBox) {
        List<String> colors = new ArrayList<>();
        for (CatColor color : CatColor.values()) {
            colors.add(color.getName());
        }

        ObservableList<String> oList = FXCollections.observableList(colors);
        comboBox.setItems(oList);
    }

    public MainController() {
    }

    @FXML
    public void pressAdd(ActionEvent event) throws IOException {
        Cat cat = new Cat();

        cat.setName(addCatNameTextField.getText());
        cat.setAge(Integer.parseInt(addCatAgeTextField.getText()));
        cat.setWeigh(Integer.parseInt(addCatWeighTextField.getText()));

        String color = addCatColorComboBox.getValue().toString();
        cat.setColor(CatColor.findColor(color));


        catService.createCat(cat);
        showCats();
    }

    @FXML
    public void pressEdit(ActionEvent event) throws IOException {
        Cat cat = new Cat();

        cat.setId(Integer.parseInt(editIdLabel.getText()));

        cat.setName(editNameTextField.getText());
        cat.setAge(Integer.parseInt(editAgeTextField.getText()));
        cat.setWeigh(Integer.parseInt(editWeighTextField.getText()));

        String color = editColorComboBox.getValue().toString();
        cat.setColor(CatColor.findColor(color));

        catService.update(cat);
        showCats();
    }

    public void showCats() {
        catsVBox.getChildren().clear();

        List<Cat> cats = catService.getCats();
        for (Cat cat : cats) {
            HBox catHBox = populateHBox(cat);
            catsVBox.getChildren().add(catHBox);
        }

    }

    private HBox populateHBox(final Cat cat) {
        HBox catHBox = new HBox();
        catHBox.setSpacing(55);
        catHBox.setPadding(new Insets(10));

        Label catNameLabel = new Label();
        catNameLabel.setText(cat.getName());

        Label catWeighLabel = new Label();
        catWeighLabel.setText(String.valueOf(cat.getWeigh()));

        Label catColorLabel = new Label();
        catColorLabel.setText(cat.getColor().getName());

        Label catAgeLabel = new Label();
        catAgeLabel.setText(String.valueOf(cat.getAge()));

        Button delButton = new Button();
        delButton.setText("x");

        Button editButton = new Button();
        editButton.setText("edit");


        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("delete " + cat.getId());
                catService.delete(cat.getId());
                showCats();
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showEditVBox();
                populateEditVBoxWithCat(cat);
            }
        });
        catHBox.getChildren().add(catNameLabel);
        catHBox.getChildren().add(catWeighLabel);
        catHBox.getChildren().add(catColorLabel);
        catHBox.getChildren().add(catAgeLabel);
        catHBox.getChildren().add(delButton);
        catHBox.getChildren().add(editButton);

        return catHBox;
    }

    private void populateEditVBoxWithCat(Cat cat) {
        editNameTextField.setText(cat.getName());
        editAgeTextField.setText(String.valueOf(cat.getAge()));
        editWeighTextField.setText(String.valueOf(cat.getWeigh()));
        editColorComboBox.setValue(cat.getColor().getName());
        editIdLabel.setText(String.valueOf(cat.getId()));
    }
}

