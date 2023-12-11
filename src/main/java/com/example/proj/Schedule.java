package com.example.proj;

import java.util.ArrayList;

public class Schedule{
    private ArrayList<AssignedTime> availableTimes = new ArrayList<AssignedTime>();
    private ArrayList<AssignedTime> reservedTimes = new ArrayList<AssignedTime>();



    public void updateSchedule(AssignedTime time) {
        reservedTimes.add(time);
    }
    public void addAvailableTime(AssignedTime time){
        availableTimes.add(time);
    }
    public boolean verifyTime(Reservation reservation, Project project) {
        var aTimes = availableTimes.stream().filter(assignedTime -> assignedTime.getProjectName().equals(project.getProjectName())).toList();
        for (AssignedTime assignedTime: aTimes){
            // First Check validate of the wanted time:
            for (Time time : assignedTime.getTimes()){
                if (time.inBetween(reservation.getTime())){
                    // Found Valid Time ---
                    // now check for conflicts
                    var rTimes = availableTimes.stream().filter(assignedTime1 -> assignedTime1.getProjectName().equals(project.getProjectName())).toList();
                    for (AssignedTime assignedTime1: rTimes){
                        for (Time time1 : assignedTime1.getTimes()){
                            if (!time1.checkConflict(reservation.getTime())){
                                // No conflict
                                reservedTimes.get(reservedTimes.indexOf(assignedTime1)).addTime(time1);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
