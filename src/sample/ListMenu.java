package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class ListMenu {
    private FileReader fileR = new FileReader("WeaponList");

    private ArrayList<String[]> stats = new ArrayList<>();

    // private ObservableList<String> weapons;

    private ListView listView = new ListView();
    private TextField tfield1 = new TextField();
    private TextField tfield2 = new TextField();
    private TextField tfield3 = new TextField();
    private TextField tfield4 = new TextField();

    private Button bAdd = new Button("Add");
    private Button bClear = new Button("Clear");
    private Button bDelete = new Button("Delete");
    private Button bSave = new Button("Save");

    public Scene makeScene() {
        makeList();

        //make parent pane
        Pane pane = new Pane();

        //make child panes
        VBox vbox = new VBox(tfield1, tfield2, tfield3, tfield4);
        HBox hbox = new HBox(listView);
        VBox vbox2 = new VBox(bAdd, bDelete, bClear, bSave);

        //resize TextField
        tfield1.setPrefSize(175, 30);
        tfield2.setPrefHeight(30);
        tfield3.setPrefHeight(30);
        tfield4.setPrefHeight(30);

        //move VBox
        vbox.setLayoutX(275);
        vbox.setLayoutY(25);
        vbox.setSpacing(1);

        vbox2.setLayoutX(275);
        vbox2.setLayoutY(175);
        vbox2.setSpacing(3);

        pane.getChildren().add(hbox);
        pane.getChildren().add(vbox);
        pane.getChildren().add(vbox2);

        return new Scene(pane, 500, 500);
    }

    private void makeList() {
        stats = fileR.getStats();

        //adds all the weapons
        ObservableList<String> weapons = FXCollections.observableArrayList(fileR.readFile());
        listView.setItems(weapons);
    }

    public void initActions() {

        //Detecting mouse clicked
        listView.setOnMouseClicked(e -> {
            String[] temp;

            //prints stats of selected weapon to text fields
            temp = stats.get(listView.getSelectionModel().getSelectedIndex());
            tfield1.setText(temp[0]);
            tfield2.setText(temp[1]);
            tfield3.setText(temp[2]);
            tfield4.setText(temp[3]);
        });

        //Button Actions
        bAdd.setOnMouseClicked(e -> {
            addWeapon(tfield1.getText(), tfield2.getText(), tfield3.getText(), tfield4.getText());
        });

        bDelete.setOnMouseClicked(e -> {
            deleteweapon();
        });

        bClear.setOnMouseClicked(e -> {
            clearTFields();
        });

        bSave.setOnMouseClicked(e -> {
            saveList();
        });
    }

    private void addWeapon(String name, String damage, String type, String speed) {

        //adds new weapon to list view
        //weapons.add(name);

        //adds new weapon to file
        fileR.addWeapon(stats);
    }

    private void clearTFields() {
        tfield1.clear();
        tfield2.clear();
        tfield3.clear();
        tfield4.clear();
    }

    private void deleteweapon() {
        stats.remove(listView.getSelectionModel().getSelectedIndex());
    }

    private void saveList() {
        fileR.addWeapon(stats);
    }

}
