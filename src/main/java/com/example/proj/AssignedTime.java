package com.example.proj;

import java.util.ArrayList;

public class AssignedTime {
    private ArrayList<Time> times;
    private String projectName;


    public AssignedTime(ArrayList<Time> times, String projectName) {
        this.times = times;
        this.projectName = projectName;
    }
    public ArrayList<Time> getTimes() {
        return times;
    }
    public void addTime(Time time){
        times.add(time);
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }
}
