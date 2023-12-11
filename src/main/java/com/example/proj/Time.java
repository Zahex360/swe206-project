package com.example.proj;
import java.time.LocalDate;
public class Time {
    private int startTime;
    private int endTime;

    public Time(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean checkConflict(Time other){
            return !(this.getStartTime() > other.getEndTime() || other.getStartTime() > this.getEndTime());
    }

    public boolean inBetween(Time other){
        if (other != null){
            return (this.getStartTime() > other.getEndTime() && other.getEndTime() > this.getEndTime());
        }
        return false;
    }
}
