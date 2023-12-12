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
    public Admin admin = new Admin();
    public Member member = new Member("");

    public ArrayList<Team> Teams = new ArrayList<>();
    public ArrayList<Member> Members = new ArrayList<>();
    public ArrayList<Project> Projects = new ArrayList<>();
    public ArrayList<Machine> Machines = new ArrayList<>();

    @FXML
    private TabPane mainTabPane;

    //region Sign-in Tab controllers and actions.
    /* Sign-in Tab*/
    @FXML
    private Tab loginTab;
    @FXML
    private TextField EmailBox;
    @FXML
    private PasswordField PasswordBox;
    @FXML
    private Label StatusLabel;

    public void userLogin(ActionEvent actionEvent) throws Exception {

        String username = EmailBox.getText();
        String pass = PasswordBox.getText();
        if (userList.contains(username + ":" + pass + "@Admin")) {
            StatusLabel.setText("Logged in, As an admin");
            mProjectsTab.setDisable(true);
            mTeamsTab.setDisable(true);
            projectsTab.setDisable(false);
            teamsTab.setDisable(false);
            VisTab.setDisable(false);
            mainTabPane.getTabs().clear();
            mainTabPane.getTabs().add(loginTab);
            mainTabPane.getTabs().add(teamsTab);
            mainTabPane.getTabs().add(projectsTab);
            mainTabPane.getTabs().add(VisTab);

        } else if (userList.contains(username + ":" + pass + "@Member")) {
            StatusLabel.setText("Logged in, As a member");
            projectsTab.setDisable(true);
            teamsTab.setDisable(true);
            VisTab.setDisable(true);
            mProjectsTab.setDisable(false);
            mTeamsTab.setDisable(false);
            mainTabPane.getTabs().clear();
            mainTabPane.getTabs().add(loginTab);
            mainTabPane.getTabs().add(mTeamsTab);
            mainTabPane.getTabs().add(mProjectsTab);
            // initialize
            if (Members.size() > 0)
                member = Members.get(0);

            try {
                myTeamsListView.getItems().clear();
                myProjectListView.getItems().clear();
                memberMachineListView.getItems().clear();
                for (Team t : member.showAssociatedTeams()) {
                    myTeamsListView.getItems().add(t.showTeamInfo());
                    myProjectListView.getItems().add(t.getProject().getProjectInfo());
                    for (Machine m : t.getProject().getavailableMachines()) {
                        memberMachineListView.getItems().add(m.getInfo());
                    }
                }
            } catch (Exception ex){
            }
        } else {
            StatusLabel.setText("Incorrect username or password.");
        }
    }

    /* End Sign-in Tab*/
    //endregion

    //region Admin-Teams controllers and actions.
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


    @FXML
    private void createNewTeam(ActionEvent actionEvent) {
        String name = newTeamBox.getText();
        Team newTeam = new Team(Teams.size() + 1, name);
        Teams.add(newTeam);
        teamsListView.getItems().add(newTeam.showTeamInfo());
    }

    @FXML
    public void addNewMember(ActionEvent actionEvent) {
        Member newMember = new Member(memberNameBox.getText(), memberEmailBox.getText(), memberRIBox.getText());
        Members.add(newMember);
        membersListView.getItems().add(newMember.getName());
    }

    @FXML
    public void addMemberToTeam(ActionEvent actionEvent) {
        String memberN = membersListView.getSelectionModel().getSelectedItem();
        String teamN = teamsListView.getSelectionModel().getSelectedItem();

        for (Member m : Members) {
            if (m.getName().equals(memberN)) {
                for (Team t : Teams) {
                    if (t.showTeamInfo().equals(teamN)) {
                        if (!m.showAssociatedTeams().contains(t)) {
                            ShowMessageBox(admin.assignMemberToTeam(m, t));
                            teamsListView.getItems().set(teamsListView.getSelectionModel().getSelectedIndex(), t.showTeamInfo());
                            return;

                        }
                    }
                }
            }
        }
    }

    /* End Admin-Teams Tab*/
    //endregion

    //region Projects controllers and actions.
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
        for (Project p : Projects) {
            if (p.getProjectInfo().equals(projectListView.getSelectionModel().getSelectedItem())) {
                for (Team t : Teams) {
                    if (t.getTeamTitle().equals(name)) {
                        t.setProject(p);
                        p.setAssociatedTeam(t);
                        projectListView.getItems().set(projectListView.getSelectionModel().getSelectedIndex(), p.getProjectInfo());
                        return;
                    }
                }
            }
        }

        ShowMessageBox("Make sure that your selection is valid.");
    }

    @FXML
    public void assginMachine(ActionEvent actionEvent) {
        if(fromHourBox.getText().equals("") || fromHourBox.getText().equals("") || mDatePicker.getValue() == null){
            ShowMessageBox("Please, Complete the Needed Information (Machine & Project & Date & Hours) !");
            return;
        }

        Time time = new Time(mDatePicker.getValue(), Integer.parseInt(fromHourBox.getText()), Integer.parseInt(toHourBox.getText()));
        for (Project p : Projects) {
            if (p.getProjectInfo().equals(projectListView.getSelectionModel().getSelectedItem())) {
                for (Machine m : Machines) {
                    if (m.getInfo().equals(machinesListView.getSelectionModel().getSelectedItem())) {
                        admin.assignMachineTimeToProject(m, p, time);
                        projectListView.getItems().set(projectListView.getSelectionModel().getSelectedIndex(), p.getProjectInfo());
                    }
                }
            }
        }
    }

    @FXML
    public void addMachine(ActionEvent actionEvent) {

        int id = Machines.size() + 1;
        Machine machine = new Machine(id, newMachineBox.getText());
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
    //endregion

    //region member teams tab
    /* Member-teams Tab*/
    @FXML
    private Tab mTeamsTab;
    @FXML
    private ListView<String> myTeamsListView;

    /* End Member-Teams Tab*/
    //endregion

    //region M_Projects tab
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

    @FXML
    public void Reserve(ActionEvent actionEvent) {

        if(memberFromBox.getText().equals("") || memberToBox.getText().equals("") || pDatePicker.getValue() == null){
            ShowMessageBox("Please, Complete the Needed Information (Machine & Project & Date & Hours) !");
            return;
        }
        Time time = new Time(pDatePicker.getValue(), Integer.parseInt(memberFromBox.getText()), Integer.parseInt(memberToBox.getText()));
        for (Project p : Projects) {
            if (p.getProjectInfo().equals(myProjectListView.getSelectionModel().getSelectedItem())) {
                for (Machine m : Machines) {
                    if (m.getInfo().equals(memberMachineListView.getSelectionModel().getSelectedItem())) {
                        ShowMessageBox(member.reserveMachine(m, time, p));
                        return;
                    }
                }
            }
        }
        ShowMessageBox("Please Select a project and its machine.");
    }

    /* End Member-projects Tab*/
    //endregion

    //region Vis tab
    /* Admin-Vis Tab*/
    @FXML
    private Tab VisTab;
    @FXML
    private Label MAMLabel; // Most active member
    @FXML
    private Label MUMLabel; // most utilized machine
    @FXML
    private Label MAPLabel; // most active project

    @FXML
    public void visActivity(ActionEvent actionEvent) {
        admin.setMembers(this.Members);

        if (admin.getMostActiveMember() == null) {
            MAMLabel.setText("No Active Member Yet!");
        } else {
            MAMLabel.setText(admin.getMostActiveMember().getName());
        }

        if (admin.getMostUtilizedMachine() == null) {
            MUMLabel.setText("No Activie Machine Yet!");
        } else {
            MUMLabel.setText(admin.getMostUtilizedMachine().getSpecialization());
        }

        if (admin.getMostActiveProject() == null) {
            MAPLabel.setText("No Active Project Yet!");
        } else {
            MAPLabel.setText(admin.getMostActiveProject().getProjectInfo());
        }

    }

    /* End Admin-VisTab Tab*/
    //endregion

    @FXML
    private void initialize() throws Exception {
        userList = Utility.readFileAsArrayList(System.getProperty("user.dir") + "\\src\\main\\java\\com\\example\\proj\\users.txt");
    }

    public void ShowMessageBox(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(msg);
        alert.show();
    }


}
