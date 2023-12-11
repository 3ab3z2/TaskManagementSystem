package com.taskera;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeCard implements LoadSave {
    Employee employee;
    LocalDateTime attendance;
    LocalTime departure;
    
    public TimeCard(Employee employee, LocalDateTime attendance, LocalTime departure) {
        this.employee = employee;
        this.attendance = attendance;
        this.departure = departure;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public LocalDateTime getAttendance() {
        return attendance;
    }
    public void setAttendance(LocalDateTime attendance) {
        this.attendance = attendance;
    }
    public LocalTime getDeparture() {
        return departure;
    }
    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
