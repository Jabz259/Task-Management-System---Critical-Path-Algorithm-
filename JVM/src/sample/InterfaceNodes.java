package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InterfaceNodes {

    private static Button findButton = new Button();
    private static Button findProjectsButton = new Button();
    private static Button deleteSaveChangesButton =  new Button();
    private static Button deleteDataButton = new Button();
    private static Button confirmSaveChangesButton =  new Button();
    private static Button saveChangesButton =  new Button();
    private static Button createButton = new Button();
    private static Button dashboardButton = new Button();
    private static Button confirmButton = new Button();
    private static Button currentProjectButton = new Button();

    public Button findBtn () {
        findButton.setMinWidth(150);
        findButton.setMinHeight(70);
        findButton.setText("Find/View All Projects");
        findButton.setTextFill(Color.WHITE);
        findButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        findButton.setStyle ("-fx-background-color: #283B4E;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 200px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 200px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return findButton;
    }

    public Button findProjectBtn () {
        findProjectsButton.setMinWidth(150);
        findProjectsButton.setMinHeight(70);
        findProjectsButton.setText("Find a Project");
        findProjectsButton.setTextFill(Color.WHITE);
        findProjectsButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        findProjectsButton.setStyle ("-fx-background-color: #283B4E;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return findProjectsButton;
    }

    //Delete button function
    public Button deleteDataBtn () {
        deleteDataButton.setMinWidth(150);
        deleteDataButton.setMinHeight(70);
        deleteDataButton.setText("Delete Data");
        deleteDataButton.setTextFill(Color.WHITE);
        deleteDataButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        deleteDataButton.setStyle ("-fx-background-color: #630024;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return deleteDataButton;
    }

    //Submit data to database button function
    public  Button createSubmit () {

        confirmButton.setMinWidth(150);
        confirmButton.setMinHeight(70);
        confirmButton.setText("Create a New Project");
        confirmButton.setTextFill(Color.WHITE);
        confirmButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        confirmButton.setStyle ("-fx-background-color: #283B4E;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 200px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 200px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return confirmButton;
    }

    //save changes button function
    public Button confirmSubmitChangesBtn () {
        confirmSaveChangesButton.setMinWidth(150);
        confirmSaveChangesButton.setMinHeight(70);
        confirmSaveChangesButton.setText("Confirm Changes");
        confirmSaveChangesButton.setTextFill(Color.WHITE);
        confirmSaveChangesButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        confirmSaveChangesButton.setStyle ("-fx-background-color: #630024;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return confirmSaveChangesButton;
    }
    //save changes button function
    public Button submitChangesBtn () {
        saveChangesButton.setMinWidth(150);
        saveChangesButton.setMinHeight(70);
        saveChangesButton.setText("EDIT A PROJECT");
        saveChangesButton.setTextFill(Color.WHITE);
        saveChangesButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        saveChangesButton.setStyle ("-fx-background-color: #5a7896;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return saveChangesButton;
    }

    //View current project button function, now known as testing button
    public static Button currentProjBtn (String text) {

        currentProjectButton.setMinWidth(150);
        currentProjectButton.setMinHeight(70);
        currentProjectButton.setText(text);
        currentProjectButton.setTextFill(Color.BLACK);
        currentProjectButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        currentProjectButton.setStyle ("-fx-background-color: #ffffff;" + "-fx-background-radius: 1em; " +
                "-fx-min-width: 100px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 100px; " +
                "-fx-max-height: 50px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return currentProjectButton;
    }

    ////Create a new project button function
    public static Button createBtn() {

        createButton.setMinWidth(150);
        createButton.setMinHeight(70);
        createButton.setText("Create/Edit a Project");
        createButton.setTextFill(Color.WHITE);
        createButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        createButton.setStyle ("-fx-background-color: #283B4E;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 200px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 200px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return createButton;
    }

    //dashboard button function
    public static Button dashboardBtn() {

        dashboardButton.setMinWidth(150);
        dashboardButton.setMinHeight(70);
        dashboardButton.setText("Dashboard");
        dashboardButton.setTextFill(Color.WHITE);
        dashboardButton.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        dashboardButton.setStyle ("-fx-background-color: #283B4E;" + "-fx-background-radius: 8em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 60px;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 15, 0.5, 0.5, 0);");

        return dashboardButton;
    }




//    @Override
//    public void handle(ActionEvent actionEvent) {
//
//
//
//    }
}
