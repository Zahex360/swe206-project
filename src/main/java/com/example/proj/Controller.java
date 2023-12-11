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
        Project project = new Project("-1@DEV_USE");
        for (Project p : Projects){
            if (p.getProjectName().equals(projectsTab.getText())){
                project = p;
                break;
            }
        }
       if (project.getProjectName().equals("-1@DEV_USE")){
           ShowMessageBox("Make sure that you are selecting a project.");
       }

        for (Team t : Teams){
            if (t.getTeamTitle().equals(name)){
                t.setProject(project);
            }
        }
        ShowMessageBox("Not a valid team name.");
    }
    @FXML
    public void assginMachine(ActionEvent actionEvent) {
        Time time = new Time(mDatePicker.getValue(), Integer.parseInt(fromHourBox.getText()), Integer.parseInt(toHourBox.getText()));
        for (Project p : Projects){
            if (p.getProjectName().equals(projectsTab.getText())){
                for (Machine m : Machines){
                    if (m.getSpecialization().equals(projectsTab.getText())){}
                }
            }
        }
    }

    @FXML
    public void addMachine(ActionEvent actionEvent) {

        int id = Machines.size()+1;
        Machine machine = new Machine(id,newMachineBox.getText());
        Machines.add(machine);
        machinesListView.getItems().add(machine.getInfo());
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
    @FXML
    private Label MAMLabel; // Most active member
    @FXML
    private Label MUMLabel; // most utilized machine
    @FXML
    private Label MAPLabel; // most active project

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

            for (Machine machine : Machines){
                machinesListView.getItems().add(machine.getSpecialization());
            }

            for (Member member : Members){
                membersListView.getItems().add(member.getName());
            }

            for (Project project : Projects){
                projectListView.getItems().add(project.getProjectName());
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
                for (Machine m : t.getProject().getavailableMachines()){
                    memberMachineListView.getItems().add(m.getSpecialization());
                }
            }
        }
    }
    @FXML
    private void initialize() throws Exception{
        userList = Utility.readFileAsArrayList(System.getProperty("user.dir") +"\\src\\main\\java\\com\\example\\proj\\users.txt");
        for (int i = 1; i < 4; i++){
            Teams.add(new Team(i, "Team" + i));
            Members.add(new Member("Hello" + i,"user"+ i+"@hotmail.com", "RI"+i));
            Projects.add(new Project("Hello" + i));
            Machines.add(new Machine(i, "M" + i));
        }
    }


    @FXML
    private void createNewTeam(ActionEvent actionEvent){
        String name = teamNameBox.getText();
        Team newTeam = new Team(Teams.size()+1, name);
        Teams.add(newTeam);
        teamsListView.getItems().add(newTeam.showTeamInfo());



    }

    @FXML
    public void addNewMember(ActionEvent actionEvent) {
        Member newMember = new Member(memberNameBox.getText(), memberEmailBox.getText(), memberRIBox.getText());
        Members.add(newMember);
        membersListView.getItems().add((Members.size()+1)+". "+newMember.getName());
    }

    @FXML
    public void addMemberToTeam(ActionEvent actionEvent) {
        String member = membersListView.getSelectionModel().getSelectedItem();
        String team = teamsListView.getSelectionModel().getSelectedItem();
//        teamsListView.getItems().set(teamsListView.getSelectionModel().getSelectedIndex(), )
    }


    @FXML
    public void Reserve(ActionEvent actionEvent) {

        String currentMachineName = memberMachineListView.getSelectionModel().getSelectedItem().split(" ")[0];

        String currentProjectName = myProjectListView.getSelectionModel().getSelectedItem();

//        admin.assignMachineTimeToProject(member, );
    }


    public void ShowMessageBox(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(msg);
        alert.show();
    }

    @FXML
    public void visActivity(ActionEvent actionEvent) {

        String mostActiveMember = admin.getMostActiveMember().getName();
        String mostUtilizedMachine = admin.getMostUtilizedMachine().getSpecialization();
        String mostActiveProject = admin.getMostActiveProject().getProjectInfo();

        MAMLabel.setText(mostActiveMember);
        MUMLabel.setText(mostUtilizedMachine);
        MAPLabel.setText(mostActiveProject);

    }
}
