import java.util.ArrayList;

public class Project implements LoadSave {
    String name;
    String description;
    ArrayList<Task> listOfTasks;
    ArrayList<TaskLog> listOfTaskLogs;
    Employee Leader;

    public Project(String name, String description, ArrayList<Task> listOfTasks, ArrayList<TaskLog> listOfTaskLogs,
            Employee leader) {
        this.name = name;
        this.description = description;
        this.listOfTasks = listOfTasks;
        this.listOfTaskLogs = listOfTaskLogs;
        Leader = leader;
    }

    public Project(String name, String description,Employee leader) {
        this(name, description, new ArrayList<>(), new ArrayList<>(), leader);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    public void setListOfTasks(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<TaskLog> getListOfTaskLogs() {
        return listOfTaskLogs;
    }

    public void setListOfTaskLogs(ArrayList<TaskLog> listOfTaskLogs) {
        this.listOfTaskLogs = listOfTaskLogs;
    }

    public Employee getLeader() {
        return Leader;
    }

    public void setLeader(Employee leader) {
        Leader = leader;
    }

    @Override
    public String toString() {
        return name+"\t"+description+"\t"+Application.employeeDataHandler.getIndex(Leader);
    }

    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=3){
            return new Project(parts[0], parts[1], Application.employeeDataHandler.get(Integer.parseInt(parts[2])));
        }
        else throw new IllegalArgumentException("not enough arguments");
    }

}
