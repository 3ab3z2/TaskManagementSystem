import java.io.IOException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import javax.swing.text.DateFormatter;

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
    public void startModule() throws IOException{
        int choice;

        System.out.println("\nTask Module\n");
        System.out.println("\t\t\tHi " +currentEmployee.getUsername()+ " !");
        
        if (currentEmployee.empType.isManager())
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
    public void viewTasks() throws IOException {
        
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
                    String taskCodeInput = "";
                    System.out.println("please pick the task code that you wish to refer to: ");
                    taskCodeInput = Application.input.next();

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
    public void viewTask(Task task) throws IOException {

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
                    case 2:
                    while(true)
                    {
                            //from time input
                            System.out.println("Enter the From Time: ");
                            System.out.print("Enter the year: ");
                            int year = Application.input.nextInt();
                            System.out.print("\nEnter the month: ");
                            int month = Application.input.nextInt();
                            System.out.print("\nEnter the day: ");
                            int day = Application.input.nextInt();
                            System.out.print("\nEnter the hour: ");
                            int hour = Application.input.nextInt();
                            System.out.print("\nEnter the minute: ");
                            int minute = Application.input.nextInt();  
        
                            //to time input
                            System.out.println("Enter the to Time: ");
                            System.out.print("\nEnter the hour: ");
                            int hourt = Application.input.nextInt();
                            System.out.print("\nEnter the minute: ");
                            int minutet = Application.input.nextInt();
        
                            
                            try
                            {
                                //Creating the tasklog
                                TaskLog log = createTaskLog(task, LocalDateTime.of(year, month, day, hour, minute), LocalDateTime.of(year, month, day, hourt, minutet)); 
                                if(log!=null){
                                    project.getListOfTaskLogs().add(log);
                                    break;
                                }
                            }
                            catch(DateTimeException err){
                                System.err.println(err.getMessage());
                            }

                        }
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
            if(Application.taskLogDataHandler.get(i).getTask() == task)
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
    public void manageTasks() throws IOException {
            int choice;
            System.out.println("\nManage Tasks:");
            System.out.println("\nPick a choice: \n\n");
            System.out.println("1-Add Task \n");
            System.out.println("2-Delete Task \n");
            System.out.println("3-Update Task \n");
            System.out.println("4-Go back \n");

            choice = Application.input.nextInt();
            boolean exit = false;
            while (!exit)
            {
                switch (choice)
                {
                    case 1:
                        while(true)
                        {
                            
                            //input task data
                            System.out.println("Task details");
                            
                            //code
                            String taskCode = Application.taskDataHandler.get(Application.taskDataHandler.getLength() - 1).getCode();
                            
                            //title
                            System.out.print("Title: ");
                            String taskTitle = Application.input.next();
                            
                            //desc
                            System.out.print("Description: ");
                            String taskDescription = Application.input.next();
                            
                            //task phase
                            System.out.print("Task phase: ");
                            String taskPhase = Application.input.next();
                            
                            //Priority
                            int priorityChoice;
                            Task.Priority priority = null;
                            boolean priorityExit = false;
                            while (!priorityExit) 
                            {                            
                                System.out.println("Priority: \n1- easy\n2- normal\n3- high");
                                priorityChoice = Application.input.nextInt();
                                switch (priorityChoice) 
                                {
                                    case 1:
                                        priority = Task.Priority.easy;
                                        priorityExit = true;
                                        break;
                                    case 2:
                                        priority = Task.Priority.normal;
                                        priorityExit = true;
                                        break;
                                    case 3:
                                        priority = Task.Priority.high;
                                        priorityExit = true;
                                        break;
                                
                                    default:
                                        System.out.println("Invalid choice, please try again.");
                                        break;
                                }
                            }

                            //assigned employee
                            Employee taskEmployee = null; 
                            boolean empExit = false;
                            while (!empExit)
                            {
                                Employee arr[] = new Employee[Application.employeeDataHandler.getLength()];
                                for(int i = 0 , j = 0; i < Application.employeeDataHandler.getLength(); i++)
                                {
                                    taskEmployee = Application.employeeDataHandler.get(i);
                                    if(!taskEmployee.empType.isManager())
                                    {
                                        System.out.println((i+1) + "- " + taskEmployee.getUsername());
                                        arr[j] = taskEmployee;
                                    }
                                }
                                
                                System.out.println("Assign to: ");
                                int empChoice = Application.input.nextInt();
                                if(empChoice >= arr.length || empChoice < 0)
                                {
                                    System.out.println("Invalid choice, please try again.");
                                    continue;
                                }
                                else
                                    taskEmployee = arr[choice - 1];
                            }
                            //try catch (date errors)
                            try
                            {
                                //Start date 
                                System.out.println("Enter the Start date: ");
                                System.out.print("Enter the year: ");
                                int year = Application.input.nextInt();
                                System.out.print("\nEnter the month: ");
                                int month = Application.input.nextInt();
                                System.out.print("\nEnter the day: ");
                                int day = Application.input.nextInt();
                                LocalDate startDate = LocalDate.of(year, month, day);
                                
                                
                                //End date 
                                System.out.println("Enter the End date: ");
                                System.out.print("Enter the year: ");
                                int yearEnd = Application.input.nextInt();
                                System.out.print("\nEnter the month: ");
                                int monthEnd = Application.input.nextInt();
                                System.out.print("\nEnter the day: ");
                                int dayEnd = Application.input.nextInt();
                                LocalDate endDate = LocalDate.of(yearEnd, monthEnd, dayEnd);
                                
                                //EST
                                System.out.println("EST: ");
                                double taskEST;
                                taskEST = Period.between(startDate, endDate).getDays()* 8;
                            
                            
                            
                                //Creating the task
                                if(endDate.compareTo(startDate) < 0)
                                {
                                    Task task = new Task(taskCode, taskTitle, taskDescription, taskEmployee, taskPhase, project, priority, currentEmployee, startDate, endDate, taskEST);
                                    Application.taskDataHandler.add(task);
                                    project.getListOfTasks().add(task);
                                    break;
                                }
                                else
                                    System.out.println("begin date cant be bigger than the end date");
                                    
                            }
                            catch(DateTimeException err)
                            {
                                System.err.println(err.getMessage());
                            }

                        }
                        break;
                    case 2:
                        System.out.println("Enter task code to delete: ");
                        String taskCo = Application.input.next();
                        Task taskDelete = null;
                        for (int i = 0; i < Application.taskDataHandler.getLength(); i++)
                        {
                            taskDelete = Application.taskDataHandler.get(i);
                            if (taskCo.equals(taskDelete.getCode()))
                            {
                                project.getListOfTasks().remove(taskDelete);

                                for(int j = 0; j < Application.taskLogDataHandler.getLength(); j++)
                                {
                                    TaskLog taskLogDelete = Application.taskLogDataHandler.get(i);
                                    if(taskLogDelete.getTask() == taskDelete)
                                    {
                                        project.getListOfTaskLogs().remove(taskLogDelete);
                                        Application.taskLogDataHandler.delete(j);
                                        j--;
                                    }

                                }
                                
                                Application.taskDataHandler.delete(i);
                                break;
                            }
                        }
                        
                        if(taskDelete == null)
                            System.out.println("task doesn't exist");                        
                        break;
                    case 3:
                        
                        System.out.println("Enter task code to modify: ");
                        String taskC = Application.input.next();
                        Task taskUpdate = null;
                        for (int i = 0; i < Application.taskDataHandler.getLength(); i++)
                        {
                            taskUpdate = Application.taskDataHandler.get(i);
                            if (taskC.equals(taskUpdate.getCode()))
                                break;
                        }
                        if(taskUpdate == null)
                            System.out.println("task doesn't exist");

                        else
                        {
                            //chosen task 
                            System.out.println("Code: " + taskUpdate.getCode());
                            System.out.println("Title: " + taskUpdate.getTitle());
                            System.out.println("Description: " + taskUpdate.getDescription());
                            System.out.println("Assigned Employee: " + taskUpdate.getAssignedEmployee());
                            System.out.println("Task phase: " + taskUpdate.getTaskPhase());
                            
                            //update task menu
                            System.out.println("Enter a field to modify");
                            System.out.println("1-title");
                            System.out.println("2-Description");
                            System.out.println("3-Assigned employee");
                            System.out.println("4-Task phase");
                            System.out.println("5-Priority");
                            System.out.println("6-exit");
                            
                            boolean updateExit = true;
                            int choiceUpdate = Application.input.nextInt();
                            while(updateExit)
                            {
                                switch (choiceUpdate)
                                {
                                    case 1:
                                        System.out.print("Enter the new title: ");
                                        String newTitle = Application.input.next();
                                        taskUpdate.setTitle(newTitle);
                                        break;
                                    case 2:
                                        System.out.print("Enter the new Description: ");
                                        String newDescription = Application.input.next();
                                        taskUpdate.setDescription(newDescription);
                                        break;
                                    case 3:
                                        Employee newEmployee = null; 
                                        boolean empExit = false;
                                        while (!empExit)
                                        {
                                            Employee arr[] = new Employee[Application.employeeDataHandler.getLength()];
                                            for(int i = 0 , j = 0; i < Application.employeeDataHandler.getLength(); i++)
                                            {
                                                newEmployee = Application.employeeDataHandler.get(i);
                                                if(!newEmployee.empType.isManager())
                                                {
                                                    System.out.println((i+1) + "- " + newEmployee.getUsername());
                                                    arr[j] = newEmployee;
                                                }
                                            }

                                            System.out.println("Assign to: ");
                                            int empChoice = Application.input.nextInt();
                                            if(empChoice >= arr.length || empChoice < 0)
                                            {
                                                System.out.println("Invalid choice, please try again.");
                                                continue;
                                            }
                                            else
                                            {
                                                newEmployee = arr[choice - 1];
                                                taskUpdate.setAssignedEmployee(newEmployee);
                                            }

                                        }
                                        break;
                                    case 4:
                                        System.out.print("Enter the new Task phase: ");
                                        String newPhase = Application.input.next();
                                        taskUpdate.setTaskPhase(newPhase);
                                        break;
                                    case 5:
                                        int priorityChoice;
                                        boolean priorityExit = false;
                                        while (!priorityExit) 
                                        {                            
                                            System.out.println("Priority: \n1- easy\n2- normal\n3- high");
                                            priorityChoice = Application.input.nextInt();
                                            switch (priorityChoice) 
                                            {
                                                case 1:
                                                    taskUpdate.setPriority(Task.Priority.easy);
                                                    priorityExit = true;
                                                    break;
                                                case 2:
                                                    taskUpdate.setPriority(Task.Priority.normal);
                                                    priorityExit = true;
                                                    break;
                                                case 3:
                                                    taskUpdate.setPriority(Task.Priority.high);
                                                    priorityExit = true;
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice, please try again.");
                                                    break;
                                            }
                                        }
                                                    break;
                                    case 6:
                                        updateExit = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option please choose again");
                                        break;
                                }

                            }
                        }
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
    public TaskLog createTaskLog(Task task, LocalDateTime fromTime, LocalDateTime toTime) throws IOException {
        if(toTime.compareTo(fromTime) < 0)
        {
            TaskLog newlog = new TaskLog(fromTime, toTime, currentEmployee, task);
            Application.taskLogDataHandler.add(newlog);
            return newlog;
        }
        else
            System.err.println("To time bigger than the from time");
            return null;
    }
    public void showCalender(){

        for(int i = 0; i < Application.employeeDataHandler.getLength(); i++)
        {
            Task task = Application.taskDataHandler.get(i);
            if(task.project == project)
            {
               System.out.println("Task: " + task.getTitle());
               System.out.println("Task phase: " + task.getTaskPhase());
               System.out.println("Assigned employee: " + task.getAssignedEmployee().toString());
            }
        }
    }

}
