import java.time.LocalDateTime;

public class TaskModule extends Module {
    Employee currentEmployee;
    Project project;

    @Override
    public void startModule(){} 
    
    public void viewTasks() {
        //TODO
    }
    public void viewTask(Task task) {
        //TODO
    }
    public void viewTasklogs(Task task) {
        //TODO
    }
    public void viewTasklog(TaskLog taskLog) {
        //TODO
    }
    public void manageTasks() {
        //TODO
    }
    public TaskLog createTaskLog(Task task, LocalDateTime fromTime, LocalDateTime toTime) {
        // TODO
        return null;
    }

}
