package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class DashboardInterface {

    //text area which will be used to print critical path data
    private TextArea kotlinCriticalPath = new TextArea();

    public TextArea getKotlinCriticalPath() {
        return kotlinCriticalPath;
    }

    //labels for the dashboard screen
    private Label title = new Label("Critical Path");
    private Label current_projects = new Label("Current Projects");
    private Label progress = new Label("Progress");

    public Label getProgress() {
        return progress;
    }

    public Label getCurrent_projects() {
        current_projects.setText("CRITICAL PATH GOES HERE");
        return current_projects;
    }

    public Label getTitle() {
        return title;
    }

}
