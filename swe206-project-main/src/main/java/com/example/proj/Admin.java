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

        if (!projects.isEmpty()) {
            int lastIndex = projects.size() - 1;
            String projectName = projects.get(lastIndex).getProjectName();
            int projNum = Integer.parseInt(projectName.substring(projectName.length() - 1));
            newProjNum = projNum + 1;
        }
        projects.add(new Project("Project-" + newProjNum));
    }

    public void setMembers(ArrayList<Member> m) {
        members = m;
    }

    public void setProjects(ArrayList<Project> m) {
        projects = m;
    }

    public void setTeams(ArrayList<Team> m) {
        teams = m;
    }

    public void assignMachineTimeToProject(Machine machine, Project project, Time time) {
       machine.getTimeTable().setAvailableTime(time, project.getProjectName());
       project.addAvailableMachine(machine);
    }

    public String assignMemberToTeam(Member member, Team team) {
        if (member.isEligible() && team.isAvailable()) {
            team.addMember(member);
            member.addTeam(team);
            return notifyAcceptance();
        }
        return notifyFullness();
    }

    public String notifyAcceptance() {
        return "Your Operation Have been done Successfully";
    }

    public String notifyFullness() {
        return "Sorry! The Number of Machines/Members Has Reached the Limit!, You Cannot Add More!";
    }


    public Project getMostActiveProject() {

        Project mostActiveProject = null;

        for (Project project : projects) {
            mostActiveProject = projects.get(0);
            if (mostActiveProject.getReservedMachines().size() < project.getReservedMachines().size()) {
                mostActiveProject = project;
            }
        }

        return mostActiveProject;
    }


    public Member getMostActiveMember() {
        Member mostActiveMember = new Member("");
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).showAssociatedTeams().size() > mostActiveMember.showAssociatedTeams().size()) {
                mostActiveMember = members.get(i);
            }
        }
        return mostActiveMember;
    }

    public Machine getMostUtilizedMachine() {

        Machine mostUtilizedMachine = null;

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getReservedMachines().get(i).getNumberOfUsages() > projects.get(i + 1).getReservedMachines().get(i + 1).getNumberOfUsages()) {
                mostUtilizedMachine = projects.get(i).getReservedMachines().get(i);
            }
        }
        return mostUtilizedMachine;
    }

}

