package com.example.proj;

import java.util.ArrayList;

public class Schedule{
    private ArrayList<AssignedTime> availableTimes = new ArrayList<AssignedTime>();
    private ArrayList<AssignedTime> reservedTimes = new ArrayList<AssignedTime>();



    public void updateSchedule(Time time, String projectName) {
        var rTime = reservedTimes.stream().filter(assignedTime1 -> assignedTime1.getProjectName().equals(projectName)).toList();
        if (rTime.size() == 1){
            rTime.get(0).addTime(time);
        }else{
            var tt = new ArrayList<Time>();
            tt.add(time);
            AssignedTime assignedTime = new AssignedTime(tt, projectName);
            reservedTimes.add(assignedTime);
        }
    }
    public void setAvailableTime(Time time, String projectName){
        var rTime = availableTimes.stream().filter(assignedTime1 -> assignedTime1.getProjectName().equals(projectName)).toList();
        if (rTime.size() == 1){
            rTime.get(0).addTime(time);
        }else{
            var tt = new ArrayList<Time>();
            tt.add(time);
            AssignedTime assignedTime = new AssignedTime(tt, projectName);
            availableTimes.remove(0);
            availableTimes.add(assignedTime);
        }
    }
    public boolean verifyTime(Reservation reservation, Project project) {

        var aTimes = availableTimes.stream().filter(assignedTime -> assignedTime.getProjectName().equals(project.getProjectName())).toList();
        if (aTimes.size() < 1)
            return false;
        // First Check validate of the wanted time:
        for (Time time : aTimes.get(0).getTimes()) {
            if (time.inBetween(reservation.getTime())) {
                // Found Valid Time ---
                // now check for conflicts
                var rTimes =  availableTimes.stream().filter(assignedTime1 -> assignedTime1.getProjectName().equals(project.getProjectName())).toList();
                if (rTimes.size() < 1)
                    return true;
                for (Time time1 : rTimes.get(0).getTimes()) {
                    if (time1.checkConflict(reservation.getTime())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
