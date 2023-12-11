package com.example.proj;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private Team associatedTeam;
    private ArrayList<Machine> reservedMachines = new ArrayList<>();
    private ArrayList<Machine> availableMachines =  new ArrayList<>();

    public Project(String projectName) {
        this.projectName = projectName;
        associatedTeam = new Team(-1, "");
        
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Team getAssociatedTeam() {
        return associatedTeam;
    }

    public void setAssociatedTeam(Team associatedTeam) {
        this.associatedTeam = associatedTeam;
    }

    public ArrayList<Machine> getReservedMachines() {
        return reservedMachines;
    }

    public void setReservedMachines(ArrayList<Machine> reservedMachines) {
        this.reservedMachines = reservedMachines;
    }

    public Team showAssociatedTeam() {

        return associatedTeam;
    }

    public ArrayList<Machine> showReservedMachines() {
 
        return reservedMachines;
    }
    public void addAvailableMachine(Machine machine){
        availableMachines.add(machine);
    }
    public ArrayList<Machine> getavailableMachines(){
        return availableMachines;
    }
    
    public Machine showMostUtilizedMachine() {
        Machine mostUtilizedMachine = null;
        for(int i=0; i<reservedMachines.size(); i++) {
            //Compare between all and return the highest most utilized machine
            if(reservedMachines.get(i).getNumberOfUsages() >= reservedMachines.get(i+1).getNumberOfUsages()) {
                mostUtilizedMachine = reservedMachines.get(i);
            }
        }
        return mostUtilizedMachine;
    }

    public String getProjectInfo(){
        return this.projectName + " : " + this.associatedTeam.getTeamTitle();
    }
}