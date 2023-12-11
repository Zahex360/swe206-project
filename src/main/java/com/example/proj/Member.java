package com.example.proj;
import java.util.ArrayList;
public class Member {

    private String name;
    private String Email;
    private String researchInterrest;
    private ArrayList<Team> AssociatedTeams;

    public Member(String name, String Email, String researchInterrest ) {
        this.name = name;
        this.Email=Email;
        this.researchInterrest=researchInterrest;
        this.AssociatedTeams = new ArrayList<Team>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getResearchInterrest(){return researchInterrest;}
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Team> getNumberOfAssociatedTeams() {
        return AssociatedTeams;
    }

    public void setNumberOfAssociatedTeams(ArrayList<Team> AssociatedTeams) {
        this.AssociatedTeams = AssociatedTeams;
    }


    // public void assignSelectedMember(Message message) {

    // }

    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("Associated Teams: " + AssociatedTeams);
    }

    public boolean isEligible() {
        return AssociatedTeams.size() >= 3;
    }

    public String reserveMachine(Machine machine, Time time, Project project, Record record) {
        Reservation reservation = new Reservation(11, time, this);
        if (machine.validateMachineTime(reservation, project)){
            // Has Been Reserved
            machine.increaseNumberOfUsages();
            return "Successfully reserved.";
        }else {
            return "Can not be reserved.";
        }
    }

     public void addTeam(Team team) {
        AssociatedTeams.add(team);
     }

    public ArrayList<Team> showAssociatedTeams() {
        return AssociatedTeams; 
    }
    
    public void assessActivity() {
        if(AssociatedTeams.size() == 3){
            System.out.println("Member is highly Active");
        }
        else if(AssociatedTeams.size() == 2){
            System.out.println("Member is Active");
        }
        else if(AssociatedTeams.size() == 1){
            System.out.println("Member is less Active");
        }
        else{
            System.out.println("Member is not Active");
        }
    }
}