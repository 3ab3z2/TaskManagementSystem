import java.time.LocalDateTime;

public class TaskModule extends Module {
    Employee currentEmployee;
    Project project;

    public void viewTasks() {}

    public void viewTask(Task task) {}

    public void viewTasklogs(Task task) {}

    public void viewTasklog(TaskLog taskLog) {}

    public void manageTasks() {}

    public TaskLog createTaskLog(Task task, LocalDateTime fromTime, LocalDateTime toTime) {
        // TODO
        return null;
    }

}
