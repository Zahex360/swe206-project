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

    public void assignMachineTimeToProject(Machine machine,  Project project, Time time) {
        Machine[] assignedMachines = project.getReservedMachines();

        for (int i = 0; i < assignedMachines.length; i++){
            if(machine.getMachineID() == assignedMachines[i].getMachineID()){
                assignedMachines[i].getTimeTable().setAvailableTime(time, project.getProjectName());
            }
        }
    }

    public void assignMemberToTeam(Member member, Team team) {
        if(team.isAvailable() && member.isEligible())
            team.addMember(member);
        else
            member.displayErrorMessage();
    }

    public String notifyAcceptance() {
        return "Your Operation Have been done Successfully";
    }

    public String notifyFullness() {
        return "Sorry! The Number of Machines/Members Has Reached the Limit!, You Cannot Add More!";
    }

    public void showPerformance() {
        
    }
    
}

