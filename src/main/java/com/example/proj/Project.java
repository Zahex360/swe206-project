package com.example.proj;
public class Project {
    private String projectName;
    private Team associatedTeam;
    private Machine[] reservedMachines;

    public Project(String projectName) {
        this.projectName = projectName;
        
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

    public Machine[] getReservedMachines() {
        return reservedMachines;
    }

    public void setReservedMachines(Machine[] reservedMachines) {
        this.reservedMachines = reservedMachines;
    }

    public Team showAssociatedTeam() {

        return associatedTeam;
    }

    public Machine[] showReservedMachines() {
 
        return reservedMachines;
    }
}