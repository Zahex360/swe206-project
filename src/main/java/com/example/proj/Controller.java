package com.example.proj;

// ###   BACKEND   ###

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public List<String> userList = new ArrayList<String>();

    @FXML
    private TabPane mainTabPane;

    /* Sign-in Tab*/
    @FXML
    private Tab loginTab;
    @FXML
    private TextField EmailBox;
    @FXML
    private PasswordField PasswordBox;
    @FXML
    private Label StatusLabel;
    @FXML
    private ListView<String> teamsListView;
    /* End Sign-in Tab*/

    /* Admin-Teams Tab*/
    @FXML
    private Tab teamsTab;

    /* End Admin-Teams Tab*/

    /* Admin-projects Tab*/
    @FXML
    private Tab projectsTab;

    /* End Admin-projects Tab*/

    /* Member-teams Tab*/
    @FXML
    private Tab mTeamsTab;

    /* End Member-Teams Tab*/

    /* Member-projects Tab*/
    @FXML
    private Tab mProjectsTab;

    /* End Member-projects Tab*/

    /* Admin-Vis Tab*/
    @FXML
    private Tab VisTab;

    /* End Admin-VisTab Tab*/

    public void userLogin(ActionEvent actionEvent) {
        String username = EmailBox.getText();
        String pass = PasswordBox.getText();
        if (userList.contains(username + ":" + pass + "@Admin")){
            StatusLabel.setText("Logged in, As an admin");
            mProjectsTab.setDisable(true);
            mTeamsTab.setDisable(true);
            projectsTab.setDisable(false);
            teamsTab.setDisable(false);
            VisTab.setDisable(false);
            // initialize

        } else if (userList.contains(username + ":" + pass + "@Member")) {
            StatusLabel.setText("Logged in, As a member");
            projectsTab.setDisable(true);
            teamsTab.setDisable(true);
            VisTab.setDisable(true);
            mProjectsTab.setDisable(false);
            mTeamsTab.setDisable(false);
            // initialize
        }
    }
    @FXML
    private void initialize() throws Exception{
        userList = Utility.readFileAsArrayList("C:\\Users\\Turki\\Documents\\SWE206\\Proj\\Phase 3\\Proj\\src\\main\\java\\com\\example\\users.txt");
    }
}
