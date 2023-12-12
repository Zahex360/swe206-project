package com.example.proj;

public class Reservation {
    private int machineID;
    private Time time;
    private Member member;


    public Reservation(int machineID, Time time, Member member) {
        this.machineID = machineID;
        this.time = time;
        this.member = member;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
