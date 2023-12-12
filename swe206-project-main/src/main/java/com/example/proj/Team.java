package com.example.proj;

import java.util.ArrayList;

public class Team {
    private int teamNumber;
    private String teamTitle;
    private ArrayList<Member> members;
    private Project project;

    public Team(int teamNumber, String teamTitle) {
        this.teamNumber = teamNumber;
        this.teamTitle = teamTitle;
        this.members = new ArrayList<>();

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void addMember(Member member) {
        members.add(member);

    }
    
    public void removeMember(Member member) {
        members.remove(member);

    }

    public boolean isAvailable() {
        return true; 
    }

    public Project showAssociatedproject() {
        return project;
    }

    public String showTeamInfo() {
        String s= this.teamTitle+ " : ";

        for(int i=0; i<members.size(); i++){
            if(i<members.size()-1)
                    s = s + members.get(i).getName()+", ";
            else
                s = s + members.get(i).getName();

        }
        return s;
    }
    public boolean verifyAvailability() {
        return true;
    }
}

