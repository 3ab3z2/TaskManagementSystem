import java.time.LocalDate;

public class Task implements LoadSave {
    String code;
    String title;
    String description;
    Employee assignedEmployee;
    String taskPhase;
    Project project;
    public enum Priority{high,normal,easy};
    Priority priority;
    Employee creator;
    LocalDate startDate;
    LocalDate endDate;
    double EST;
    public Task(String code, String title, String description, Employee assignedEmployee, String taskPhase,
            Project project, Task.Priority priority, Employee creator, LocalDate startDate, LocalDate endDate,
            double eST) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.assignedEmployee = assignedEmployee;
        this.taskPhase = taskPhase;
        this.project = project;
        this.priority = priority;
        this.creator = creator;
        this.startDate = startDate;
        this.endDate = endDate;
        EST = eST;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }
    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
    public String getTaskPhase() {
        return taskPhase;
    }
    public void setTaskPhase(String taskPhase) {
        this.taskPhase = taskPhase;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Employee getCreator() {
        return creator;
    }
    public void setCreator(Employee creator) {
        this.creator = creator;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public double getEST() {
        return EST;
    }
    public void setEST(double eST) {
        EST = eST;
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
