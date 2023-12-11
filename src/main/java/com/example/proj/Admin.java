package com.example.proj;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin {
    public void addMachine() {
        
    }

    public void addProject() {
        
    }

    public void assignMachineTimeToProject(Machine machine,  Project project, Time time) {
         
    }

    public void assignMemberToTeam(Member member, Team team) {
        if(team.isAvailable() && member.isEligible())
            team.addMember(member);
        else
            member.displayErrorMessage();   
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

    public void updateMachineTime(Machine machine, Time time) {
        
    }
}

