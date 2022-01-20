package sample;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main extends Application {

    private static BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ProjectInformation pi = new ProjectInformation();
        InterfaceNodes ui = new InterfaceNodes();

        ui.findBtn().setOnAction(e -> {
           //converting values to string
           String text = String.valueOf(ControllerKt.filter());
           pi.getViewSearchResultsTextArea(text);
           searchProjects(text);
        });

        //find project screen
        ui.findProjectBtn().setOnAction(e -> {
            searchProjects("");
        });

        //Delete data onscreen
        ui.deleteDataBtn().setOnAction(e -> {

        });

        ui.confirmSubmitChangesBtn().setOnAction(e -> {
           ControllerKt.updateData();
            //System.out.println(ControllerKt.updateDependencyInfo());

        });

        //Button to save changes
        ui.submitChangesBtn().setOnAction(e -> {
            saveChangesConfirmWindow();
        });


        //Button redirects user to create a group form
        ui.createBtn().setOnAction(e -> {
        createGroup();
        });

//        String criticalData = Arrays.toString(ControllerKt.criticalPath(DbConnection.testingData()));
//        String criticalMessage = criticalData + "\n" + "Critical Path " ;

        dashboard(ControllerKt.criticalMessage());
        //dashboard critical path page
        ui.dashboardBtn().setOnAction(e -> {
            dashboard(ControllerKt.criticalMessage());
        });


        //insert data using the Kotlin function
        ui.createSubmit().setOnAction(e -> {
                ControllerKt.insertData();
        });

        //TESTING BUTTON! WILL NOT BE IN THE FINAL BUILD!
        ui.currentProjBtn("").setOnAction(e -> {

            HashSet<Task> test = DbConnection.testingData();
            System.out.println(Arrays.toString(ControllerKt.criticalPathAlgorithm(test)));

            //DbConnection.testingData();
           // DbConnection.updateDatabase("application functionalityChanged2", "java programmersChanged2","application functionalityChanged");
        });

        //Hbox placement for main buttons
        HBox topHb = new HBox(ui.createBtn(),ui.dashboardBtn(), ui.findProjectBtn());
        topHb.setSpacing(10);
        root.setTop(topHb);
        topHb.setStyle("-fx-background-color: #34475A;" +
                "-fx-set-spacing: 15;" +
                "-fx-padding: 15;" +
                "-fx-effect: dropShadow(gaussian, rgba(0, 0, 0, 0.3), 30, 0.5, 0.4, 0.5);");

        //creating a VBox layout instead of HBox for vertical placement
        VBox leftHb = new VBox(currentProjectList(ControllerKt.ManageData()));
        leftHb.setSpacing(50);
        root.setLeft(leftHb);
        leftHb.setStyle("-fx-background-color: #34475A; -fx-set-spacing: 10; -fx-padding: 10;");

        //Hbox for right pane
        HBox rightHb = new HBox();
        rightHb.setSpacing(10);
        root.setRight(rightHb);
        rightHb.setStyle("-fx-background-color: #34475A; -fx-set-spacing: 10; -fx-padding: 10;");

        //HBox for bottom pane
        HBox bottomHb = new HBox();
        bottomHb.setSpacing(10);
        root.setBottom(bottomHb);
        bottomHb.setStyle("-fx-background-color: #34475A; -fx-set-spacing: 10; -fx-padding: 10;");

        //main window screen
        primaryStage.setTitle("Project Management");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

//        Label projectName = new Label("Hello World");
//        TextField enterName = new TextField();
//        projectName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
//
//        HBox centerHb = new HBox(projectName, enterName);
//        centerHb.setSpacing(10);
//        root.setCenter(centerHb);
//        centerHb.setStyle("-fx-background-color: #34475A;" +
//                "-fx-set-spacing: 10;" +
//                "-fx-padding: 10;");
        //ui.createBtn().setOnAction(e -> form );
        //btn.addEventHandler(ActionEvent.ACTION, (e) -> );

    }

    public ListView currentProjectList (List<String> dataStream) {
        ListView <String> list = new ListView();
        ObservableList<String> item = FXCollections.observableArrayList(dataStream);
        //setting all data from database into listView table
        list.setItems(item);

        list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = list.getSelectionModel().getSelectedItem();
            int index = list.getSelectionModel().getSelectedIndex();
            //DbConnection.listSelectionData(selectedItem);

            //TESTING DATABASE WITHIN MAIN, REMOVE ONCE TESTS ARE DONE!
            Connection con = DbConnection.connect();
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                String sql = "Select projectName,teamName,teamLocation,supportTeamLocation,mainTaskInfo,additionalTaskInfo,dependency,cost from ProjectInfo where projectName = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,selectedItem);
                rs = ps.executeQuery();

                //CHANGE TEST VARIABLE NAMES!
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                String f = rs.getString(6);
                String g = rs.getString(7);
                String h = rs.getString(8);

                viewProject(a,b,c,d,e,f,g,h);
                System.out.println("Project Name: " +  a + " Team Name: " + b + " teamLocation: " + c + " Support Location: " + d);

            } catch (SQLException e) {
                System.out.println(e.toString());

            } finally {

                try {
                    rs.close();
                    ps.close();
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }

            System.out.println("Item selected : " + selectedItem + ", Item index : " + index);
        });

        list.setStyle ("-fx-background-color: #283B4E;" +
                "-fx-min-width: 200px; " +
                "-fx-min-height: 450px; " +
                "-fx-max-width: 200px; " +
                "-fx-max-height: 450px;");

        return list;
    }

    public void saveChangesConfirmWindow () {
        InterfaceNodes ui = new InterfaceNodes();
        ProjectInformation fields = new ProjectInformation();

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 5, 0, 10));

        Scene secondScene = new Scene(grid, 450, 200);

        grid.add(fields.getChangesInfo(),1,2);
        grid.add(fields.getRetypeProjectField(),1,3);
        grid.add(ui.confirmSubmitChangesBtn(),1,4);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Submit Changes");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.show();

    }

    public GridPane searchProjects (String searchResults) {

        InterfaceNodes ui = new InterfaceNodes();
        ProjectInformation fields = new ProjectInformation();

        //creating a new GridPane
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        root.setCenter(grid);

        //width and height of the spacing between nodes
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 10, 0, 20));

        grid.add(fields.getSearchResults("Search for a project"),2,3);
        grid.add(fields.getSearchField(),2,4);
        grid.add(ui.findBtn(), 2,5);
        grid.add(fields.getViewSearchResultsTextArea(searchResults),2,6);

        return grid;
    }

    public GridPane viewProject (String projectText, String teamText, String mainTeam, String supportTeamText, String mainTaskText, String additionalTaskText,String dependencyText, String costText) {
        InterfaceNodes ui = new InterfaceNodes();
        ProjectInformation fields = new ProjectInformation();

        //creating a new GridPane
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        root.setCenter(grid);

        //width and height of the spacing between nodes
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 10, 0, 20));

        //adding all nodes onto the grid
        grid.add(fields.getProjectLbl("Project Name: "), 1,1);
        grid.add(fields.getViewProjectName(projectText), 2, 1);

        grid.add(fields.getTeamNameLbl("Departments: "), 1,2);
        grid.add(fields.getViewMainTeam(teamText),2,2);

        grid.add(fields.getMainTeamLbl("Main Team: "), 1,3);
        grid.add(fields.getProjTeamName(mainTeam),2,3);

        grid.add(fields.getSupportTeamLbl("Supporting Team: "), 1,4);
        grid.add(fields.getProjSupportName(supportTeamText),2,4);

        grid.add(fields.getMainTaskLbl("Main Tasks: "),1,5);
        grid.add(fields.getViewMainTask(mainTaskText),2,5);

        grid.add(fields.getAddTaskLbl("Additional Tasks: "),1,6);
        grid.add(fields.getViewAdditionalTask(additionalTaskText),2,6);

        grid.add(fields.getCostLbl("Days Required for this Project: "),1,7);
        grid.add(fields.getViewCost(costText),2,7);

        grid.add(fields.getDependencyLbl("This Project is Depended on: "),1,8);
        grid.add(fields.getViewDependency(dependencyText),2,8);

        grid.add(ui.deleteDataBtn(),1,9);


        return grid;
    }

    public GridPane createGroup() {
        InterfaceNodes ui = new InterfaceNodes();
        ProjectInformation fields = new ProjectInformation();

        //creating a new GridPane
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        root.setCenter(grid);

        //width and height of the spacing between nodes
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 10, 0, 20));

        //adding all nodes onto the grid
        grid.add(fields.getProjectLbl("Enter Project Name: "), 1,1);
        grid.add(fields.getProjectNameField(), 2, 1);

        grid.add(fields.getTeamNameLbl("Enter Team Departments: "), 1,2);
        grid.add(fields.getTeamNameField(),2,2);

        grid.add(fields.getMainTeamLbl("Main Location Team: "), 1,3);
        grid.add(fields.getTeamSelectionCb(),2,3);

        grid.add(fields.getSupportTeamLbl("Supporting Team: "), 1,4);
        grid.add(fields.getSupportSelectionCb(),2,4);

        grid.add(fields.getMainTaskLbl("Enter Main Tasks: "),1,5);
        grid.add(fields.getMainTaskNameField(),2,5);

        grid.add(fields.getAddTaskLbl("Enter Any Additional Tasks: "),1,6);
        grid.add(fields.getAdditionalTaskField(),2,6);

        grid.add(fields.getDependencyLbl("Is this project dependant: "), 1,7);
        grid.add(fields.getDependencySelectionCb(),2,7);

        grid.add(fields.getCostLbl("Total days required of this project: "), 1,8);
        grid.add(fields.getCountField(),2,8);

        grid.add(ui.createSubmit(), 2,9);
        grid.add(ui.submitChangesBtn(), 1,9);

        return grid;
    }


    public GridPane dashboard (String Text) {
        DashboardInterface di = new DashboardInterface();


        //creating a new GridPane
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        root.setCenter(grid);

        //width and height of the spacing between nodes
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(0, 10, 0, 20));

        //Title Dashboard
        di.getTitle().setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 35));
        grid.add(di.getTitle(), 1,0);

        //adding textarea
        di.getKotlinCriticalPath().setText(Text);
        di.getKotlinCriticalPath().setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 13));
        di.getKotlinCriticalPath().setStyle(
                "-fx-min-width: 720px; " +
                "-fx-min-height: 400px; " +
                "-fx-max-width: 720px; " +
                "-fx-max-height: 400px;");
        grid.add(di.getKotlinCriticalPath(),1,2);

        //Current Projects
//        di.getCurrent_projects().setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
//        di.getCurrent_projects().setStyle("-fx-effect: dropShadow(gaussian, rgba(128, 128, 128, 0.3), 15, 0.5, 0.5, 0);");
//        grid.add(di.getCurrent_projects(),1,3);
//
//        di.getProgress().setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 20));
//        di.getProgress().setStyle("-fx-effect: dropShadow(gaussian, rgba(128, 128, 128, 0.3), 15, 0.5, 0.5, 0);");
//        grid.add(di.getProgress(),1,9);

        return grid;

//        HBox centerHb = new HBox(di.getTitle());
//        di.getTitle().setFont(Font.font("Times New Roman", FontWeight.BOLD, 35));
//        root.setCenter(centerHb);
//        centerHb.setAlignment(Pos.TOP_CENTER);
//        centerHb.setSpacing(15);

        //centerHb.setStyle("-fx-background-color: white; -fx-set-spacing: 10; -fx-padding: 10;");

    }

    public void layout () {

        BorderPane secondaryLayout = new BorderPane();
        Label secondLabel = new Label("I'm a Label on new Window");
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 300, 550);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.show();

    }

    public static void main(String[] args) {
        //DbConnection.readSpecificRow();
        launch(args);
    }
}
