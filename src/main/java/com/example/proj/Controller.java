package com.example.proj;

// ###   BACKEND   ###

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public List<String> userList = new ArrayList<String>();
    public Admin admin;
    public Member member;

    public ArrayList<Team> Teams = new ArrayList<>();
    public ArrayList<Member> Members = new ArrayList<>();
    public ArrayList<Project> Projects = new ArrayList<>();
    public ArrayList<Machine> Machines = new ArrayList<>();

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
    private ListView<String> teamsListView;
    @FXML
    private TextField newTeamBox;
    @FXML
    private Button newTeamButton;
    @FXML
    private Button addToTeamButton;
    @FXML
    private ListView<String> membersListView;
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
    private ListView<String> projectListView;
    @FXML
    private TextField newprojectNameBox;
    @FXML
    private Button addNewProjectbutton;
    @FXML
    private TextField teamNameBox;
    @FXML
    private Button assignTeamButton;
    @FXML
    private ListView<String> machinesListView;
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


    @FXML
    public void assignTeam(ActionEvent actionEvent) {
        String name = teamNameBox.getText();
    }
    @FXML
    public void assginMachine(ActionEvent actionEvent) {
    }

    @FXML
    public void addMachine(ActionEvent actionEvent) {
    }
    @FXML
    public void addNewProject(ActionEvent actionEvent) {
        Project project = new Project(newprojectNameBox.getText());
        Projects.add(project);
        projectListView.getItems().add(project.getProjectInfo());
    }

    /* End Admin-projects Tab*/

    /* Member-teams Tab*/
    @FXML
    private Tab mTeamsTab;
    @FXML
    private ListView<String> myTeamsListView;

    /* End Member-Teams Tab*/

    /* Member-projects Tab*/
    @FXML
    private Tab mProjectsTab;
    @FXML
    private ListView<String> myProjectListView;
    @FXML
    private DatePicker pDatePicker;
    @FXML
    private TextField memberFromBox;
    @FXML
    private TextField memberToBox;
    @FXML
    private ListView<String> memberMachineListView;

    @FXML
    private Button reserveButton;

    /* End Member-projects Tab*/

    /* Admin-Vis Tab*/
    @FXML
    private Tab VisTab;

    /* End Admin-VisTab Tab*/

    public void userLogin(ActionEvent actionEvent) {
        admin = new Admin();
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
            admin = new Admin();
            for (Team t : Teams) {
                teamsListView.getItems().add(t.getTeamTitle());
            }


        } else if (userList.contains(username + ":" + pass + "@Member")) {
            StatusLabel.setText("Logged in, As a member");
            projectsTab.setDisable(true);
            teamsTab.setDisable(true);
            VisTab.setDisable(true);
            mProjectsTab.setDisable(false);
            mTeamsTab.setDisable(false);
            // initialize
            member = new Member(username);
            admin.assignMemberToTeam(member, Teams.get(0));
            admin.assignMemberToTeam(member, Teams.get(1));
            Teams.get(0).setProject(Projects.get(0));
            Teams.get(1).setProject(Projects.get(1));
            for (Team t : member.showAssociatedTeams()){
                myTeamsListView.getItems().add(t.getTeamTitle());
                myProjectListView.getItems().add(t.getProject().getProjectName());
            }
        }
    }
    @FXML
    private void initialize() throws Exception{
        userList = Utility.readFileAsArrayList(System.getProperty("user.dir") +"\\src\\main\\java\\com\\example\\proj\\users.txt");
        for (int i = 1; i < 4; i++){
            Teams.add(new Team(i, "Team" + i));
            Members.add(new Member("Pr" + i));
            Projects.add(new Project("Hello" + i));
            Machines.add(new Machine(i, "M" + i));
        }
    }


    @FXML
    private void createNewTeam(ActionEvent actionEvent){
        String tn = newTeamBox.getText();
        teamsListView.getItems().add(tn);

    }

    @FXML
    public void addNewMember(ActionEvent actionEvent) {
        membersListView.getItems().add(memberNameBox.getText());
    }

    @FXML
    public void addMemberToTeam(ActionEvent actionEvent) {
        String member = membersListView.getSelectionModel().getSelectedItem();
        String team = teamsListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void Reserve(ActionEvent actionEvent) {
    }


    public void ShowMessageBox(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(msg);
        alert.show();
    }
}
