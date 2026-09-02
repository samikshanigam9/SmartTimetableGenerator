package com.samiksha.timetable;

public class TimeSlot {

    private String day;
    private String startTime;
    private String endTime;

    public TimeSlot(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void displayTimeSlot() {
        System.out.println("Day: " + day);
        System.out.println("Time: " + startTime + " - " + endTime);
    }
}