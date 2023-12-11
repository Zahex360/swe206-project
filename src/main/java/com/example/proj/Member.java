package com.example.proj;
import java.util.ArrayList;
public class Member {
    private String name;
    private ArrayList<Team> AssociatedTeams;

    public Member(String name) {
        this.name = name;
        this.AssociatedTeams = new ArrayList<Team>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Team> getNumberOfAssociatedTeams() {
        return AssociatedTeams;
    }

    public void setNumberOfAssociatedTeams(ArrayList<Team> AssociatedTeams) {
        this.AssociatedTeams = AssociatedTeams;
    }


    public void assignSelectedMember(Message message) {

    }

    public void getInfo() {
    }

    public boolean isEligible() {
        return AssociatedTeams.size() >= 3;
    }

    public String reserveMachine(Machine machine, Time time, Project project, Record record) {
        Reservation reservation = new Reservation(11, time, this);
        if (machine.validateMachineTime(reservation, project)){
            // Has Been Reserved
            return "Successfully reserved.";
        }else {
            return "Can not be reserved.";
        }
    }

    public void selectMember() {

    }

    public Team[] showAssociatedTeams() {
        return new Team[0]; 
    }

    public Machine[] showAvailableMachines() {
        return new Machine[0]; 
    }

}