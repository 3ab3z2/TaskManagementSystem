public class Task extends LoadSave {
    String code;
    String title;
    String description;
    Employee assignedEmployee;
    TaskPhase taskPhase;
    Project project;
    //check enum if correct
    enum priority{high,normal,easy};
    Employee creator;
    //include date package
    Date startDate;
    Date endDate;
    double EST;
    
}
