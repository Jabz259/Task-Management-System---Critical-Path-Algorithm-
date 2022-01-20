package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class ProjectInformation {


    //Label for confirming changes window
    //textField for confirmation input
    private Label changesInfo = new Label();
    private static TextField retypeProjectField = new TextField();

    public Label getChangesInfo() {
        this.changesInfo.setText("Please retype the 'Project Name' from list to confirm\n" +
                "the changes made.\n" +
                "Please note these changes cannot be undone." );
        this.changesInfo.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 17));
        return changesInfo;
    }

    public TextField getRetypeProjectField() {
        return retypeProjectField;
    }

    //nodes for displaying searched data
    private Label searchResults = new Label();
    private TextArea viewSearchResultsLabel = new TextArea();
    private static TextField searchField = new TextField();

    public TextArea getViewSearchResultsTextArea(String Text) {
        this.viewSearchResultsLabel.setText(Text);
        return viewSearchResultsLabel;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public Label getSearchResults(String Text) {
        this.searchResults.setText(Text);
        this.searchResults.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return searchResults;
    }


    //textfields to load data into fields
    private static TextField viewProjectNameField = new TextField ();
    private static TextField viewMainTeamField = new TextField ();
    private static Label projTeamNameLbl = new Label();
    private static Label projSupportNameLbl = new Label();
    private static TextField viewMainTaskField = new TextField ();
    private static TextField viewAdditionalTaskField = new TextField ();
    private static TextField viewCostField = new TextField();
    private static TextField viewDependencyField = new TextField();

    public TextField getViewCost(String Text) {
        this.viewCostField.setText(Text);
        return viewCostField;
    }

    public TextField getViewDependency(String Text) {
        this.viewDependencyField.setText(Text);
        return viewDependencyField;
    }

    public TextField getViewProjectName(String Text) {
        this.viewProjectNameField.setText(Text);
        return viewProjectNameField;
    }

    public TextField getViewMainTeam(String Text) {
        this.viewMainTeamField.setText(Text);
        return viewMainTeamField;
    }


    public TextField getViewMainTask(String Text) {
        this.viewMainTaskField.setText(Text);
        this.viewMainTaskField.setMinWidth(400);
        this.viewMainTaskField.setMinHeight(70);
        return viewMainTaskField;
    }

    public TextField getViewAdditionalTask(String Text) {
        this.viewAdditionalTaskField.setText(Text);
        return viewAdditionalTaskField;
    }


    public Label getProjTeamName(String Text) {
        this.projTeamNameLbl.setText(Text);
        this.projTeamNameLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return projTeamNameLbl;
    }

    public Label getProjSupportName(String Text) {
        this.projSupportNameLbl.setText(Text);
        this.projSupportNameLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return projSupportNameLbl;
    }

    //captions for loaded information
    private Label projectLbl = new Label();
    private Label teamNameLbl = new Label();
    private Label mainTeamLbl = new Label();
    private Label supportTeamLbl = new Label();
    private Label mainTaskLbl = new Label();
    private Label addTaskLbl = new Label();
    private Label dependencyLbl = new Label();
    private Label costLbl = new Label();

    public Label getProjectLbl(String Text) {
        this.projectLbl.setText(Text);
        this.projectLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return projectLbl;
    }

    public Label getTeamNameLbl(String Text) {
        this.teamNameLbl.setText(Text);
        this.teamNameLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return teamNameLbl;
    }

    public Label getMainTeamLbl(String Text) {
        this.mainTeamLbl.setText(Text);
        this.mainTeamLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return mainTeamLbl;
    }

    public Label getSupportTeamLbl(String Text) {
        this.supportTeamLbl.setText(Text);
        this.supportTeamLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return supportTeamLbl;
    }

    public Label getMainTaskLbl(String Text) {
        this.mainTaskLbl.setText(Text);
        this.mainTaskLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return mainTaskLbl;
    }

    public Label getAddTaskLbl(String Text) {
        this.addTaskLbl.setText(Text);
        this.addTaskLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return addTaskLbl;
    }


    public Label getDependencyLbl(String Text) {
        this.dependencyLbl.setText(Text);
        this.dependencyLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return dependencyLbl;
    }

    public Label getCostLbl(String Text) {
        this.costLbl.setText(Text);
        this.costLbl.setFont(Font.font("Mono Spaced Plain", FontWeight.BOLD, 15));
        return costLbl;
    }

    //Create a project fields for input
    private static TextField projectNameField = new TextField();
    private static TextField teamNameField = new TextField();
    private static TextField mainTaskField = new TextField();
    private static TextField additionalTaskField = new TextField();
    private static TextField countField = new TextField();
    private static ComboBox teamSelectionCb = new ComboBox();
    private static ComboBox supportSelectionCb = new ComboBox();
    private static ComboBox dependencySelectionCb = new ComboBox();

    public TextField getCountField() {
        return countField;
    }

    public TextField getProjectNameField() {
       return projectNameField;
    }

    public TextField getTeamNameField() {
        return teamNameField;
    }

    public ComboBox getTeamSelectionCb() {
        this.teamSelectionCb.setItems (FXCollections.observableArrayList("London", "None"));
        return teamSelectionCb;
    }

    //my workaround
    public String mainData (String text) {
        text = (String) getTeamSelectionCb().getValue();
        return text;
    }

    public String supportData (String text) {
        text = (String) getSupportSelectionCb().getValue();
        return text;
    }

    public ComboBox getSupportSelectionCb() {
        this.supportSelectionCb.setItems (FXCollections.observableArrayList("Germany", "Brazil", "China", "None"));
        return supportSelectionCb;
    }

    public TextField getMainTaskNameField() {
        this.mainTaskField.setMinWidth(250);
        this.mainTaskField.setMinHeight(70);
        return mainTaskField;
    }

    public TextField getAdditionalTaskField() {
        return additionalTaskField;
    }

    public ComboBox getDependencySelectionCb() {
        ObservableList<String> item = FXCollections.observableArrayList(ControllerKt.ManageData());
        item.add("none");
        //setting all data from database into comboBox table
        this.dependencySelectionCb.setItems (item);
        return dependencySelectionCb;
    }

    public String dependencyData (String text) {
        text = (String) getDependencySelectionCb().getValue();
        return text;
    }


}
