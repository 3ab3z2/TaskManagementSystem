package com.taskera;

import java.time.LocalDateTime;

public class TaskLog implements LoadSave {
    //include datetime package
    LocalDateTime fromTime;
    LocalDateTime toTime;
    Employee assignedEmployee;
    Task task;
    public TaskLog(LocalDateTime fromTime, LocalDateTime toTime, Employee assignedEmployee, Task task) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.assignedEmployee = assignedEmployee;
        this.task = task;
    }
    public LocalDateTime getFromTime() {
        return fromTime;
    }
    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }
    public LocalDateTime getToTime() {
        return toTime;
    }
    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }
    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }
    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
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
    public double calculateHours() {
        // TODO
        return 0;
    }
}
