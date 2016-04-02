/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.utils;

import gatebass.myControl.tableView.MyColumnTable;
import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Companies;
import gatebass.dataBase.tables.Users;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author reza
 */
public class MenuTableInit {

    public static void actionsInit(String query, TextField textField, TableView<Users> tableView) {
        MyColumnTable<Users> removeButtonCellFactory = new MyColumnTable<Users>(null, Cursor.DEFAULT);
        removeButtonCellFactory.setOnAddToMenu((Users s) -> {
            textField.setText(s.getName_fa());
        });
        FilteredList<Users> filteredlist = removeButtonCellFactory.init(
                tableView, textField, databaseHelper.usersDao.rawResults(query));

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0) ? true : s.getName_fa().contains(newValue));
            tableView.setPrefHeight(
                    tableView.getFixedCellSize() * (tableView.getItems().size() > 5 ? 5 : tableView.getItems().size()) + 4
            );
        });
        tableView.getSelectionModel().select(0);

    }

    public static void companiesInit(String query, TextField textField, TableView<Companies> tableView) {
        MyColumnTable<Companies> removeButtonCellFactory = new MyColumnTable<>(null, Cursor.DEFAULT);
        removeButtonCellFactory.setOnAddToMenu((Companies s) -> {
            textField.setText(s.getCompany_fa());
        });
        FilteredList<Companies> filteredlist = removeButtonCellFactory.init(
                tableView, textField, databaseHelper.companiesDao.rawResults(query));

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0) ? true : s.getCompany_fa().contains(newValue));
            if (!filteredlist.isEmpty()) {
                textField.setStyle("-fx-text-fill: " + (filteredlist.get(0).getCompany_fa().equals(textField.getText()) ? "black;" : "red;"));
            } else {
                textField.setStyle("-fx-text-fill: red;");
            }
            tableView.setPrefHeight(
                    tableView.getFixedCellSize() * (tableView.getItems().size() > 5 ? 5 : tableView.getItems().size()) + 4
            );
        });
        tableView.getSelectionModel().select(0);
    }

    public static void companiesInit(String query, TextField textField, TableView<Companies> tableView, Button button, Button clear) {
        MyColumnTable<Companies> removeButtonCellFactory = new MyColumnTable<>(null, Cursor.DEFAULT);
        removeButtonCellFactory.setOnAddToMenu((Companies s) -> {
            textField.setText(s.getCompany_fa());
        });
        FilteredList<Companies> filteredlist = removeButtonCellFactory.init(
                tableView, textField, databaseHelper.companiesDao.rawResults(query), button, clear);

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0) ? true : s.getCompany_fa().contains(newValue));
            if (!filteredlist.isEmpty()) {
                textField.setStyle("-fx-text-fill: " + (filteredlist.get(0).getCompany_fa().equals(textField.getText()) ? "black;" : "red;"));
            } else {
                textField.setStyle("-fx-text-fill: red;");
            }
            tableView.setPrefHeight(
                    tableView.getFixedCellSize() * (tableView.getItems().size() > 5 ? 5 : tableView.getItems().size()) + 4
            );
        });
        tableView.getSelectionModel().select(0);
    }

    public static void companiesInit(String query, TextField textField, TableView<Companies> tableView, boolean is_EN) {
        MyColumnTable<Companies> removeButtonCellFactory = new MyColumnTable<>(null, Cursor.DEFAULT);
        removeButtonCellFactory.setOnAddToMenu((Companies s) -> {
            textField.setText(is_EN ? s.getCompany_en() : s.getCompany_fa());

        });
        FilteredList<Companies> filteredlist = removeButtonCellFactory.init(
                tableView, textField, databaseHelper.companiesDao.rawResults(query));

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0)
                    ? true : is_EN ? s.getCompany_en().toLowerCase().contains(newValue.toLowerCase()) : s.getCompany_fa().toLowerCase().contains(newValue.toLowerCase()));

            tableView.setPrefHeight(
                    tableView.getFixedCellSize() * (tableView.getItems().size() > 5 ? 5 : tableView.getItems().size()) + 4
            );
        });
        tableView.getSelectionModel().select(0);
    }

    public static void disciplineInit(String query, TextField textField, TableView<Users> tableView, boolean is_EN) {
        MyColumnTable<Users> removeButtonCellFactory = new MyColumnTable<>(null, Cursor.DEFAULT);
        removeButtonCellFactory.is_EN = is_EN;
        removeButtonCellFactory.setOnAddToMenu((Users s) -> {
            textField.setText(is_EN ? s.getName_en() : s.getName_fa());
        });
        FilteredList<Users> filteredlist = removeButtonCellFactory.init(
                //                tableView, textField, databaseHelper.groupsDao.rawResults(query));
                tableView, textField, databaseHelper.usersDao.rawResults(query));

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0)
                    ? true : is_EN ? s.getName_en().toLowerCase().contains(newValue.toLowerCase()) : s.getName_fa().toLowerCase().contains(newValue.toLowerCase()));
            tableView.setPrefHeight(
                    tableView.getFixedCellSize() * (tableView.getItems().size() > 5 ? 5 : tableView.getItems().size()) + 4
            );
        });
        tableView.getSelectionModel().select(0);
    }

}
