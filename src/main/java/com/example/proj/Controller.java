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
    /* End Sign-in Tab*/

    /* Admin-Teams Tab*/
    @FXML
    private Tab teamsTab;
    @FXML
    private ListView teamsListView;
    @FXML
    private TextField newTeamBox;
    @FXML
    private Button newTeamButton;
    @FXML
    private Button addToTeamButton;
    @FXML
    private ListView membersListView;
    @FXML
    private TextField memberNameBox;
    @FXML
    private TextField memberEmailBox;
    @FXML
    private TextField memberRIBox;
    @FXML
    private Button addMemberButton;
    /* End Admin-Teams Tab*/

    /* Admin-projects Tab*/
    @FXML
    private Tab projectsTab;
    @FXML
    private ListView projectListView;
    @FXML
    private TextField newprojectNameBox;
    @FXML
    private Button addNewProjectbutton;
    @FXML
    private TextField teamNameBox;
    @FXML
    private Button assignTeamButton;
    @FXML
    private ListView machinesListView;
    @FXML
    private TextField newMachineBox;
    @FXML
    private Button addNewMachineButton;
    @FXML
    private DatePicker mDatePicker;
    @FXML
    private TextField fromHourBox;
    @FXML
    private TextField toHourBox;
    @FXML
    private Button assignMToteamButton;

    /* End Admin-projects Tab*/

    /* Member-teams Tab*/
    @FXML
    private Tab mTeamsTab;
    @FXML
    private ListView myTeamsListView;

    /* End Member-Teams Tab*/

    /* Member-projects Tab*/
    @FXML
    private Tab mProjectsTab;
    @FXML
    private ListView myProjectListView;
    @FXML
    private DatePicker pDatePicker;
    @FXML
    private TextField memberFromBox;
    @FXML
    private TextField memberToBox;
    @FXML
    private ListView memberMachineListView;

    @FXML
    private Button reserveButton;

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
        userList = Utility.readFileAsArrayList("C:\\Users\\ccm-stu\\Desktop\\ggg\\swe206-project\\src\\main\\java\\com\\example\\users.txt");
    }
}
