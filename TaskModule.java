import java.time.LocalDateTime;

public class TaskModule extends Module {
    Employee currentEmployee;
    Project project;

    public TaskModule(Employee currentEmployee, Project project)
    {
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
        this.project = project;
    }

    @Override
    public void startModule(){
        int choice;

        System.out.println("\nTask Module\n");
        System.out.println("\t\t\tHi " +currentEmployee.getUsername()+ " !");
        
        if (currentEmployee.empType.isManager)
        {
            System.out.println("Manager\n");
            System.out.println("\nPick a choice: \n\n");
            System.out.println("1-View tasks \n");
            System.out.println("2-Manage tasks \n");
            System.out.println("3-Show calender \n");
            System.out.println("3-Go back \n");

            choice = Application.input.nextInt();
            boolean exit = false;
            while (!exit)
            {
                switch (choice)
                {
                    case 1:
                        viewTasks();
                        break;

                    case 2:
                        manageTasks();
                        break;
                    case 3:
                        showCalender();
                        break;
                    
                    case 4:
                        System.out.println("Going to the previous page");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }

        }
        else
        {
            System.out.println("\nEmpolyee\n");
            System.out.println("\nPick a choice: \n\n");
            System.out.println("1-View tasks \n");
            System.out.println("2-Show Calender \n");
            System.out.println("3-Go back \n");

            choice = Application.input.nextInt();
            boolean exit = false;
            while (!exit)
            {
                switch (choice)
                {
                    case 1:
                        viewTasks();
                        break;

                    case 2:
                        showCalender();
                        break;
                    
                    case 3:
                        System.out.println("Going to the previous page");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }
        }
        

    } 
    
    public void viewTasks() {
        
        int len = Application.taskDataHandler.getLength();
        System.out.println("Code\tTitle\n");
        if(currentEmployee.empType.isManager)
        {
            for(int i = 0; i <len; i++){
                Task task = Application.taskDataHandler.get(i);
                if(task.project == project)
                System.out.println(task.code + "\t" + task.title + "\n");
            }
        }
        else
        {
            for(int i = 0; i <len; i++){
                Task task = Application.taskDataHandler.get(i);
                if(task.assignedEmployee == currentEmployee)
                System.out.println(task.code + "\t" + task.title + "\n");
            }
        }
        
        boolean exit = false;
        while (!exit)
            {
                System.out.println("\nPick a choice: \n\n");
                System.out.println("1-View tasks \n");
                System.out.println("2-Go Back \n");
                int choice = Application.input.nextInt();
                switch (choice)
                {
                    case 1:
                        //TODO
                        break;
                    case 2:
                        System.out.println("Going to the previous page");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }

        
    }
    public void viewTask(Task task) {

        //TODO

        boolean exit = false;
        while (!exit)
            {
                System.out.println("\nPick a choice: \n\n");
                System.out.println("1-View tasklogs \n");
                System.out.println("2-Create tasklog \n");
                System.out.println("3-Go Back \n");
                int choice = Application.input.nextInt();
                switch (choice)
                {
                    case 1:
                        viewTasklogs(task);
                        break;
                    case 2: //TODO
                        createTaskLog(task, null, null); 
                        break;
                    case 3:
                        System.out.println("Going to the previous page");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }
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
    public void showCalender(){
        // TODO
    }

}
