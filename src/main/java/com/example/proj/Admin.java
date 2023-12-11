package com.example.proj;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin {

    private ArrayList<Project> projects = new ArrayList();
    private ArrayList<Team> teams = new ArrayList();
    private ArrayList<Member> members = new ArrayList();
    public void addMachine() {
        
    }

    public void addProject() {
        // This method:
        // 1. Identifies the last project number
        // 2. Add 1 to the project number
        // 3. Assign the new project with new name
        int newProjNum = 1;

        if (!projects.isEmpty()){
            int lastIndex = projects.size()-1;
            String projectName = projects.get(lastIndex).getProjectName();
            int projNum = Integer.parseInt(projectName.substring(projectName.length()-1));
            newProjNum = projNum + 1;
        }

        projects.add(new Project("Project-"+newProjNum));

    }

    public void assignMachineTimeToProject(Project project, Time time) {

    }

    public void assignMemberToTeam(Member member, Team team) {
    }

    public void notifyAcceptance() {
        
    }

    public void notifyFullness() {
        
    }

    public void notifyRejection() {
        
    }

    public void showFailure() {
        
    }

    public void showPerformance() {
        
    }

    public void updateMachineTime(Time time) {
        
    }
}

