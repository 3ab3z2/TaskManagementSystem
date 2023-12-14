import java.time.Duration;
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
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=4){
            LocalDateTime cFromTime=parts[0].equals("null")?null:LocalDateTime.parse(parts[0]);
            LocalDateTime cToTime=parts[1].equals("null")?null:LocalDateTime.parse(parts[1]);
            Employee cAssignedEmployee=(Integer.parseInt(parts[2])!=-1)?Application.employeeDataHandler.get(Integer.parseInt(parts[2])):null;
            Task cTask=(Integer.parseInt(parts[3])!=-1)?Application.taskDataHandler.get(Integer.parseInt(parts[3])):null;
            TaskLog tasklog =  new TaskLog(cFromTime, cToTime, cAssignedEmployee, cTask);
            cTask.getProject().getListOfTaskLogs().add(tasklog);
            return tasklog;
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    @Override
    public String toString() {
        return fromTime+"\t"+toTime+"\t"+Application.employeeDataHandler.getIndex(assignedEmployee)+"\t"+Application.taskDataHandler.getIndex(task);
    }
    public double calculateHours() {
        return Duration.between(fromTime,toTime).toHours();
    }
}
