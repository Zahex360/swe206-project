package com.example.proj;
import java.time.LocalDate;
public class Time {
    private int startTime;
    private int endTime;
    private LocalDate date;

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
            return !(other.date.equals(this.date) && (this.getStartTime() > other.getEndTime() || other.getStartTime() > this.getEndTime()));
    }

    public boolean inBetween(Time other){
        if (other.date.equals(this.date)){
            return (this.getStartTime() > other.getEndTime() && other.getEndTime() > this.getEndTime());
        }
        return false;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
