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
        for (Project p : Projects){
            if (p.getProjectName().equals(projectListView.getSelectionModel().getSelectedItem())){
                for (Team t : Teams){
                    if (t.getTeamTitle().equals(name)){
                        t.setProject(p);
                        return;
                    }
                }
            }
        }

        ShowMessageBox("Make sure that your selection is valid.");
    }
    @FXML
    public void assginMachine(ActionEvent actionEvent) {
        Time time = new Time(mDatePicker.getValue(), Integer.parseInt(fromHourBox.getText()), Integer.parseInt(toHourBox.getText()));
        for (Project p : Projects){
            if (p.getProjectName().equals(projectListView.getSelectionModel().getSelectedItem())){
                for (Machine m : Machines){
                    if (m.getSpecialization().equals(membersListView.getSelectionModel().getSelectedItem())){
                        admin.assignMachineTimeToProject(m, p, time);
                    }
                }
            }
        }
    }

    @FXML
    public void addMachine(ActionEvent actionEvent) {

        int id = Machines.size()+1;
        Machine machine = new Machine(id,newMachineBox.getText());
        Machines.add(machine);
        machinesListView.getItems().add(machine.getSpecialization());
    }
    @FXML
    public void addNewProject(ActionEvent actionEvent) {
        Project project = new Project(newprojectNameBox.getText());
        Projects.add(project);
        projectListView.getItems().add(project.getProjectName());
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
                teamsListView.getItems().add(t.showTeamInfo());
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
            Projects.get(0).addAvailableMachine(Machines.get(0));
            Time ti = new Time(java.time.LocalDate.of(2023, 12,12), 1, 24);
            admin.assignMachineTimeToProject(Machines.get(0), Projects.get(0), ti);
            for (Team t : member.showAssociatedTeams()){

                myTeamsListView.getItems().add(t.showTeamInfo());
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
        String name = newTeamBox.getText();
        Team newTeam = new Team(Teams.size()+1, name);
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

        for (Member m : Members){
            if (m.getName().equals(memberN)){
                for(Team t: Teams){
                   if (t.showTeamInfo().equals(teamN)){
                       if (!m.showAssociatedTeams().contains(t)){
                           t.addMember(m);
                           m.addTeam(t);
                           teamsListView.getItems().set(teamsListView.getSelectionModel().getSelectedIndex(), t.showTeamInfo());


                           return;

                       }
                   }
                }}
        }
    }


    @FXML
    public void Reserve(ActionEvent actionEvent) {

        Time time = new Time(pDatePicker.getValue(), Integer.parseInt(memberFromBox.getText()), Integer.parseInt(memberToBox.getText()));
        for (Project p : Projects){
            if (p.getProjectName().equals(myProjectListView.getSelectionModel().getSelectedItem())){
                for (Machine m : Machines){
                    if (m.getSpecialization().equals(memberMachineListView.getSelectionModel().getSelectedItem())){
                        ShowMessageBox(member.reserveMachine(m, time, p));
                    }
                }
            }
        }
    }


    public void ShowMessageBox(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(msg);
        alert.show();
    }

    @FXML
    public void visActivity(ActionEvent actionEvent) {
        admin.setMembers(this.Members);
        if(admin.getMostActiveMember() == null){
            MAMLabel.setText("No Active Member Yet!");
            return;
        }
        else{
            MAMLabel.setText(admin.getMostActiveMember().getName());
        }

        if(admin.getMostUtilizedMachine() == null){
            MUMLabel.setText("No Activie Machine Yet!");
            return;
        }
        else{
            MUMLabel.setText(admin.getMostUtilizedMachine().getSpecialization());
        }

        if(admin.getMostActiveProject() == null){
            MAPLabel.setText("No Active Project Yet!");
            return;
        }
        else{
            MAPLabel.setText(admin.getMostActiveProject().getProjectInfo());
        }
        // String mostActiveMember = admin.getMostActiveMember().getName();
        // String mostUtilizedMachine = admin.getMostUtilizedMachine().getSpecialization();
        // String mostActiveProject = admin.getMostActiveProject().getProjectInfo();


        // MAMLabel.setText(mostActiveMember);
        // MUMLabel.setText(mostUtilizedMachine);
        // MAPLabel.setText(mostActiveProject);

    }
}
