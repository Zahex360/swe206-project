package com.example.proj;

import java.util.ArrayList;

public class Team {
    private int teamNumber;
    private String teamTitle;
    private ArrayList<Member> members;
    private ArrayList<Project> projects;

    public Team(int teamNumber, String teamTitle) {
        this.teamNumber = teamNumber;
        this.teamTitle = teamTitle;
        this.members = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    
    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void addMember(Member member) {
        members.add(member);
        member.increaseNumberOfAssociatedTeams(); 
    }
    
    public void removeMember(Member member) {
        members.remove(member);
        member.decreaseNumberOfAssociatedTeams(); 
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void assessSkills() { 
    }

    public boolean isAvailable() {
        return true; 
    }

    public Project[] showAssociatedProjects() {
        return projects.toArray(new Project[0]);
    }

    public Machine[] showAvailableMachines() {
        return new Machine[0]; 
    }

    public void showPerformance() {
    }

    public boolean verifyAvailability() {
        return true;     
    }
}

