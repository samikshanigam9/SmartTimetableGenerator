package com.samiksha.timetable;

public class Classroom {

    private String roomNumber;
    private int capacity;

    public Classroom(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void displayClassroom() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Capacity: " + capacity);
    }
}