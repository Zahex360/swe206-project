package com.example.proj;
import java.util.HashMap;

public class Record {
    private HashMap<Member, Integer> machinesReservedTime;

    public Record() {
        machinesReservedTime = new HashMap<>();
    }

    public HashMap<Member, Integer> getMachinesReservedTime() {
        return this.machinesReservedTime;
    }

    public void setMachinesReservedTime(HashMap<Member, Integer> machinesReservedTime) {
        this.machinesReservedTime = machinesReservedTime;
    }

    public void updateRecord(Member member, Machine machine, Time time) {

    }
}