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
        
        //display tasks
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

        //view tasks menu
        boolean exit = false;
        while (!exit)
            {
                System.out.println("\nPick a choice: \n\n");
                System.out.println("1-View task \n");
                System.out.println("2-Go Back \n");
                int choice = Application.input.nextInt();
                switch (choice)
                {
                    case 1:
                    //TODO: error checking 
                    String taskCodeInput = "";
                    int taskCodeInputInt;
                    System.out.println("please pick the task code that you wish to refer to: ");
                    taskCodeInputInt = Application.input.nextInt();
                    taskCodeInput = taskCodeInputInt + "";
                    for(int i = 0; i < len; i++)
                    {
                        Task task = Application.taskDataHandler.get(i);
                        if(taskCodeInput.equals(task.getCode()))
                            viewTask(task);
                    }
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

        //display task details
        System.out.println("Title: " + task.getTitle());
        System.out.println("Code: " + task.getCode());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Task phase: " + task.getTaskPhase());
        System.out.println("Priority: " + task.getPriority().toString());
        System.out.println("Assigned to: " + task.getAssignedEmployee().toString());
        System.out.println("Start date: " + task.getStartDate().toString());
        System.out.println("Due date: " + task.getEndDate().toString());
        System.out.println("EST: " + task.getEST());

        //display view task menu
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
        //display all task logs and store all relative tasklogs in an array for usage
        TaskLog[] arr = new TaskLog[Application.taskLogDataHandler.getLength()];
        for(int i = 0, j =0; i < Application.taskLogDataHandler.getLength(); i++)
        {
            //TODO: check if == or equals
            
            //manager ver.
            if(Application.taskLogDataHandler.get(i).getTask() == task && currentEmployee.empType.isManager)
            {
                System.out.println((i+1) + ".\nfromtime:" + Application.taskLogDataHandler.get(i).getFromTime().toString());
                System.out.println("totime:" + Application.taskLogDataHandler.get(i).getToTime().toString());
                arr[j] = Application.taskLogDataHandler.get(i);

            }
            //emp ver.
            if(Application.taskLogDataHandler.get(i).getAssignedEmployee() == currentEmployee)
            {
                System.out.println((i+1) + ".\nfromtime:" + Application.taskLogDataHandler.get(i).getFromTime().toString());
                System.out.println("totime:" + Application.taskLogDataHandler.get(i).getToTime().toString());
                arr[j] = Application.taskLogDataHandler.get(i);

            }
        }

            //display view task logs menu
            boolean exit = false;
            while (!exit)
            {
                System.out.println("\nPick a choice: \n\n");
                System.out.println("0-Go Back \n");
                for (int i = 0; i < arr.length; i++) 
                    System.out.println((i + 1) + "-\tFrom: " + arr[i].getFromTime().toString() + "\tTo:" + arr[i].getToTime().toString());
                int choice = Application.input.nextInt();
                
                    if( choice == 0)
                    {
                        System.out.println("Going to the previous page");
                        exit = true;
                    }
                    else if(choice >= arr.length || choice < 0)
                        System.out.println("Invalid choice, please try again.");
                    else
                        for(int i = 0; i < arr.length; i++)
                            if(i == choice - 1 )
                                viewTasklog(arr[i]);
            }
        }    
    public void viewTasklog(TaskLog taskLog) {
        //display tasklog
        System.out.println("Task: "+ taskLog.getTask().toString());
        System.out.println("From time:" + taskLog.getFromTime().toString());
        System.out.println("To time:" + taskLog.getToTime().toString());
        if(currentEmployee.empType.isManager())
            System.out.println("Assigned to:" + taskLog.getAssignedEmployee().toString());
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
