package com.example.proj;
public class Machine {
    private int machineID;
    private String specialization;
    private Schedule timeTable;
    private int numberOfUsages;

    public Machine(int machineID, String specialization) {
        this.machineID = machineID;
        this.specialization = specialization;
        this.timeTable = new Schedule(); 
    }
    
    public int getMachineID() {
        return machineID;
    }


    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }


    public String getSpecialization() {
        return specialization;
    }


    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public Schedule getTimeTable() {
        return timeTable;
    }


    public void setTimeTable(Schedule timeTable) {
        this.timeTable = timeTable;
    }

    // public boolean isAvailable() {
    //     return true; 
    // }

    public boolean validateMachineTime(Reservation reservation, Project project) {
        if (this.timeTable.verifyTime(reservation, project)){
            // Reserve.
            System.out.println("REeached");
            this.timeTable.updateSchedule(reservation.getTime(), project.getProjectName());
            // Update record. Here
            // Reserved
            return true;
        }
        return false;
    }

    public int getNumberOfUsages() {
        return numberOfUsages;
    }

    public void increaseNumberOfUsages() {
        this.numberOfUsages++;
    }

    public String getInfo(){
        return this.getSpecialization() + " " + this.getMachineID();
    }
}