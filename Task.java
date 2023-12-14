import java.time.LocalDate;

public class Task implements LoadSave {
    String code;
    String title;
    String description;
    Employee assignedEmployee;
    String taskPhase;
    Project project;
    public enum Priority{easy,normal,high};
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
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=11){
            Priority cPriority;
            switch (parts[6]) {
                case "easy":
                    cPriority=Priority.easy;
                    break;
                case "normal":
                    cPriority=Priority.normal;
                    break;
                case "high":
                    cPriority=Priority.high;
                    break;
                case "null":
                    cPriority=null;
                    break;
                default:
                    throw new IllegalArgumentException("unknown value "+parts[6]);
            }
            Employee cAssignedEmployee=(Integer.parseInt(parts[3])!=-1)?Application.employeeDataHandler.get(Integer.parseInt(parts[3])):null;
            Project cProject=(Integer.parseInt(parts[3])!=-1)?Application.projectDataHandler.get(Integer.parseInt(parts[5])):null;
            Employee cCreator=(Integer.parseInt(parts[7])!=-1)?Application.employeeDataHandler.get(Integer.parseInt(parts[7])):null;
            LocalDate cStartDate=parts[8].equals("null")?null:LocalDateTime.parse(parts[8]);
            LocalDate cEndDate=parts[9].equals("null")?null:LocalDateTime.parse(parts[9]);
            
            Task task =  new Task(parts[0], parts[1], parts[2], cAssignedEmployee, parts[4], cProject, cPriority, cCreator, cStartDate, cEndDate, Double.parseDouble(parts[10]));
            cProject.getListOfTasks().add(task);
            return task;
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    @Override
    public String toString() {
        return code+"\t"+title+"\t"+description+"\t"+Application.employeeDataHandler.getIndex(assignedEmployee)+"\t"+taskPhase+"\t"+Application.projectDataHandler.getIndex(project)+"\t"+priority+"\t"+Application.employeeDataHandler.getIndex(creator)+"\t"+startDate+"\t"+endDate+"\t"+EST;
    }
}
