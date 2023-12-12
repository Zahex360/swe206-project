package com.example.proj;

import java.util.ArrayList;
import java.util.HashMap;


public class Schedule {
    private HashMap<String, ArrayList<Time>> availableTimes = new HashMap<>();
    private HashMap<String, ArrayList<Time>> reservedTimes = new HashMap<>();

    public void updateSchedule(Time time, String projectName) {
        if (reservedTimes.containsKey(projectName)) {
            reservedTimes.get(projectName).add(time);
        } else {
            var tt = new ArrayList<Time>();
            tt.add(time);
            reservedTimes.put(projectName, tt);
        }
    }

    public void setAvailableTime(Time time, String projectName) {
        if (availableTimes.containsKey(projectName)) {
            availableTimes.get(projectName).clear();
            availableTimes.get(projectName).add(time);
        } else {
            var tt = new ArrayList<Time>();
            tt.add(time);
            availableTimes.put(projectName, tt);
        }
    }

    public boolean verifyTime(Reservation reservation, Project project) {
        String projectName = project.getProjectName();
        if (!availableTimes.containsKey(projectName))
            return false;
        // First Check validate of the wanted time:
        for (Time time : availableTimes.get(projectName)) {
            if (time.inBetween(reservation.getTime())) {
                // Found Valid Time ---
                // now check for conflicts

                for (Map.Entry<String, ArrayList<Time>> entry : reservedTimes.entrySet()) {
                    ArrayList<Time> rTime = entry.getValue();
                    for (Time time1 : rTime) {
                        if (time1.checkConflict(reservation.getTime())) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
