import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TaskModule extends Module {
    private Employee currentEmployee;
    private Project project;

    public TaskModule(Employee currentEmployee, Project project) {
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
        this.project = project;
    }

    @Override
    public void startModule() throws IOException {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println(
                    "╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n"
                            +
                            "║▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░Task Module░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n"
                            +
                            "║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n"
                            +
                            "║╒═════════╝                                                              ╚══════════╕║ █\n"
                            +
                            "║│                                  Hi " + currentEmployee.getUsername() + "\n" +
                            "║│                                                                                   │║ █\n"
                            +
                            "║╘═══════════════════════════════════════════════════════════════════════════════════╛║ █\n");

            if (project.getLeader().toString().equals(currentEmployee.toString())) {
                System.out.println(
                        "║╭╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╮║ █\n" +
                                "║│                                  🯅 Position: Manager                              │║ █\n"
                                +
                                "║╰╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╯║ █\n"
                                +
                                "║                     🯇 Please choose one of the following options:🯈                  ║ █\n"
                                +
                                "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ █\n"
                                +
                                "║│1)│View tasks.                                                                     │║ █\n"
                                +
                                "║│2)│Manage tasks.                                                                   │║ █\n"
                                +
                                "║│3)│Show calendar.                                                                  │║ █\n"
                                +
                                "║│0)│Go back.                                                                        │║ █\n"
                                +
                                "╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n"
                                +
                                " ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");

                choice = Application.inputInt("Your choice:🮶 ");
                switch (choice) {
                    case 1:
                        try {

                            viewTasksMenu();
                        } catch (IOException e) {
                            System.out.println("\u001B[41m"
                                    + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                    +
                                    "\u001B[41m"
                                    + "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 an error occured while viewing tasks 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
                                    + "\u001B[0m" +
                                    "\n" + "\u001B[41m" + e.getMessage()
                                    + "\n╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        }
                        break;

                    case 2:
                        try {
                            manageTasks();
                        } catch (IOException e) {
                            System.out.println("\u001B[41m"
                                    + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                    +
                                    "\u001B[41m"
                                    + "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 an error occured while managing tasks 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
                                    + "\u001B[0m" +
                                    "\n" + "\u001B[41m" + e.getMessage()
                                    + "\n╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        }
                        break;
                    case 3:
                        showCalender();
                        break;

                    case 0:
                        System.out.println("\u001B[42m" +
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                +
                                "\u001B[42m"
                                + "║                                 Returning to menu🮴                                  ║"
                                + "\u001B[0m" +
                                "\n" +
                                "\u001B[42m"
                                + "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        exit = true;
                        break;

                    default:
                        System.out.println("\u001B[41m"
                                + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                +
                                "\u001B[41m"
                                + "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
                                + "\u001B[0m" +
                                "\n" + "\u001B[41m"
                                + "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        break;
                }

            } else {

                System.out.println(
                        "║╭╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╮║ █\n"
                                +
                                "║│                                  🯅 Position: Employee                             │║ █\n"
                                +
                                "║╰╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╯║ █\n"
                                +
                                "║                     🯇 Please choose one of the following options:🯈                  ║ █\n"
                                +
                                "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ █\n"
                                +
                                "║│1)│View tasks.                                                                     │║ █\n"
                                +
                                "║│2)│Show calendar.                                                                  │║ █\n"
                                +
                                "║│0)│Go back.                                                                        │║ █\n"
                                +
                                "╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n"
                                +
                                " ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");

                choice = Application.inputInt("Your choice:🮶 ");
                switch (choice) {
                    case 1:
                        try {
                            viewTasksMenu();
                        } catch (IOException e) {
                            System.out.println("\u001B[41m"
                                    + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                    +
                                    "\u001B[41m"
                                    + "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 an error occured while viewing tasks 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
                                    + "\u001B[0m" +
                                    "\n" + "\u001B[41m" + e.getMessage()
                                    + "\n╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        }
                        break;

                    case 2:
                        showCalender();
                        break;

                    case 0:
                        System.out.println("\u001B[42m" +
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                +
                                "\u001B[42m"
                                + "║                                 Returning to menu🮴                                  ║"
                                + "\u001B[0m" +
                                "\n" +
                                "\u001B[42m"
                                + "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        exit = true;
                        break;

                    default:
                        System.out.println("\u001B[41m"
                                + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
                                +
                                "\u001B[41m"
                                + "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
                                + "\u001B[0m" +
                                "\n" + "\u001B[41m"
                                + "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        break;
                }

            }
        }

    }

    public void viewTasks() throws IOException {

        // display tasks
        System.out.println(
                "╔═════════════════════════════════════════════════════════════════════════════════════╗");
       
        // manager
        if (project.getLeader().toString().equals(currentEmployee.toString())) {
             System.out.println(
                "║                              These are the project's tasks                          ║\n" +
                        "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║");
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (task.getProject().toString().equals(project.toString())) {

                    System.out.println("║│ Code:" + task.getCode());
                    System.out.println("║│ Title: " + task.getTitle());
                    System.out.println("║│------------------------------");
                }
            }
        }
        // employee
        else {
             System.out.println(
                "║                              These are your assigned tasks                          ║\n" +
                        "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║");
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (task.getAssignedEmployee().toString().equals(currentEmployee.toString()))
                    System.out.println("║│ Code:" + task.getCode() + "│Title: " + task.getTitle());
            }
        }
        System.out.println("╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝");

    }

    public void viewTasksMenu() throws IOException {
        while (true) {
            viewTasks();
            System.out.println("0)Go back");
            String taskCode = Application.inputStringOneWord("Press 0 to go back or enter Code: ", "Input cannot be empty!\n");
            boolean found = false;
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (taskCode.equals(task.getCode())) {
                    found = true;
                    viewTaskMenu(task);
                }
            }
            if (taskCode.equals("0")) {
                System.out.println("Going to the previous page");
                break;
            }
            if (!found)
                System.out.println("task code doesn't exist");

        }
    }

    public void viewTask(Task task) throws IOException {

        System.out.println("\n-----------------");
        System.out.println("Title: " + task.getTitle());
        System.out.println("Code: " + task.getCode());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Task phase: " + task.getTaskPhase());
        System.out.println("Priority: " + task.getPriority().toString());
        System.out.println("Assigned to: " + task.getAssignedEmployee().getUsername());
        System.out.println("Start date: " + task.getStartDate().toString());
        System.out.println("Due date: " + task.getEndDate().toString());
        System.out.println("EST: " + task.getEST());
        System.out.println("-----------------\n");

    }

    public void viewTaskMenu(Task task) throws IOException {
        boolean exit = false;
        while (!exit) {
            viewTask(task);
            System.out.println("1)View tasklogs");
            System.out.println("2)Create tasklog");
            System.out.println("0)Go Back");
            int choice = Application.inputInt("Choice: ");
            switch (choice) {
                case 0:
                    System.out.println("Going to the previous page");
                    exit = true;
                    break;

                case 1:
                    ArrayList<TaskLog> taskLogslist = new ArrayList<>();
                    for (int i = 0; i < Application.taskLogDataHandler.getLength(); i++)
                        if (Application.taskLogDataHandler.get(i).getTask() == task) {
                           taskLogslist.add(Application.taskLogDataHandler.get(i));
                        }

                    viewTasklogsMenu(task, taskLogslist);
                    break;

                case 2:
                    while (true) {
                        // from time input
                        System.out.println("\n---From Time--");
                        int year = Application.inputInt("Enter the year: ");
                        int month = Application.inputInt("Enter the month: ");
                        int day = Application.inputInt("Enter the day: ");
                        int hour = Application.inputInt("Enter the hour: ");
                        int minute = Application.inputInt("Enter the minute: ");

                        // to time input
                        System.out.println("\n---To Time---");
                        int hourTo = Application.inputInt("Enter the hour: ");
                        int minuteTo = Application.inputInt("Enter the minute: ");

                        // try catch to handle DateTime Exceptions
                        try {
                            // Creating the tasklog
                            TaskLog log = createTaskLog(task, LocalDateTime.of(year, month, day, hour, minute),
                                    LocalDateTime.of(year, month, day, hourTo, minuteTo));
                            if (log != null) {
                                project.getListOfTaskLogs().add(log);
                                break;
                            }
                        } catch (DateTimeException err) {
                            System.err.println(err.getMessage());
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    public TaskLog createTaskLog(Task task, LocalDateTime fromTime, LocalDateTime toTime) throws IOException {
        if (toTime.compareTo(fromTime) > 0) {
            TaskLog newlog = new TaskLog(fromTime, toTime, currentEmployee, task);
            Application.taskLogDataHandler.add(newlog);
            return newlog;
        } else
            System.err.println("\nFrom time cant be bigger than the to time");
        return null;
    }

    public void viewTasklogs(ArrayList<TaskLog> tasklogs) {
        for (int i = 0; i < tasklogs.size(); i++)
            System.out.println(
                    (i + 1) + ")From time: " + tasklogs.get(i).getFromTime().toString()
                            + " To time: " +  tasklogs.get(i).getToTime().toString());

    }

    public void viewTasklogsMenu(Task task, ArrayList<TaskLog> tasklogs) {
        while (true) {
            viewTasklogs(tasklogs);
            System.out.println("0)Go Back");
            int choice = Application.inputInt("Tasklog number: ");
            if (choice == 0)
                break;
                
            else if (choice < 1 || choice > tasklogs.size())
                System.out.println("\033[31mChosen tasklog doesn't exist\033[0m\n");
            else
                viewTasklog(tasklogs.get(choice -1));
        }
    }

    public void viewTasklog(TaskLog taskLog) {
        System.out.println("\n-----------------");
        System.out.println("Task Title: " + taskLog.getTask().getTitle());
        System.out.println("From time: " + taskLog.getFromTime().toString());
        System.out.println("To time: " + taskLog.getToTime().toString());
        if (project.getLeader().getUsername().equals(currentEmployee.getUsername()))
            System.out.println("Assigned to: " + taskLog.getAssignedEmployee().getUsername());
        System.out.println("-----------------\n");
        System.out.print("Press Enter to continue\n");
        Application.input.nextLine();
    }

    public void manageTasks() throws IOException {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n---Manage Tasks---");
            System.out.println("0)Go back");
            System.out.println("1)Add Task");
            System.out.println("2)Delete Task");
            System.out.println("3)Update Task");
            choice = Application.inputInt("Choice: ");
            switch (choice) {
                case 0:
                    System.out.println("Going to the previous page");
                    exit = true;
                    break;
                case 1:
                    addTask();
                    break;
                case 2:
                    deleteTask();
                    break;
                case 3:
                    updateTask();
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    public void addTask() throws IOException {
        boolean addExit = false;
        while (!addExit) {

            // input task data
            System.out.println("\n---Task details---");

            // code
            boolean codeExist = false;
            String taskCode;
            while (true) {
                taskCode = Application.inputStringOneWord("\n---Code---\nCode: ", "Code cannot be empty!\n");
                for (int i = 0; i < Application.taskDataHandler.getLength(); i++)
                    if (Application.taskDataHandler.get(i).getCode().equals(taskCode)) {
                        System.err.println("Code already exists!");
                        codeExist = true;
                        break;
                    }
                if (!codeExist)
                    break;
            }

            // title
            String taskTitle = Application.inputString("\n---Title---\nTitle: ", "Title cannot be empty!\n");
            

            // desc
            String taskDescription = Application.inputString("\n---Description---\nDescription: ", "Description cannot be empty!\n");
            
            // task phase
            String taskPhase = Application.inputStringOneWord("\n---Task phase---\nTask phase: ", "Task phase cannot be empty!\n");
           
            // Priority
            int choice;
            Task.Priority priority = null;
            boolean exit = false;
            while (!exit) {
                choice = Application.inputInt("\n---Priority---\n1)easy\n2)normal\n3)high\nPriority: ");
                switch (choice) {
                    case 1:
                        priority = Task.Priority.easy;
                        exit = true;
                        break;
                    case 2:
                        priority = Task.Priority.normal;
                        exit = true;
                        break;
                    case 3:
                        priority = Task.Priority.high;
                        exit = true;
                        break;

                    default:
                        System.out.println("Unknown choice, please try again.");
                        break;
                }
            }

            // assigned employee
            Employee employee;
            while (true) {
                System.out.println("\n---Employee---");
                for (int i = 0; i < Application.employeeDataHandler.getLength(); i++) {
                    employee = Application.employeeDataHandler.get(i);
                    System.out.println((i + 1) + ") " + employee.getUsername());
                }
                choice = Application.inputInt("Assign to: ");
                if (choice > Application.employeeDataHandler.getLength() || choice < 1) {
                    System.out.println("Invalid choice, please try again.");
                    continue;
                } else {
                    employee = Application.employeeDataHandler.get(choice - 1);
                    break;
                }

            }

            // date & EST & task creation

            while (true) {
                try {

                    // Start date
                    int year, month, day, yearEnd, monthEnd, dayEnd;

                    System.out.println("\n---Start date---");
                    year = Application.inputInt("Enter the year: ");
                    month = Application.inputInt("Enter the month: ");
                    day = Application.inputInt("Enter the day: ");
                    LocalDate startDate = LocalDate.of(year, month, day);

                    // End date
                    System.out.println("\n---End date---");
                    yearEnd = Application.inputInt("Enter the year: ");
                    monthEnd = Application.inputInt("Enter the month: ");
                    dayEnd = Application.inputInt("Enter the day: ");
                    LocalDate endDate = LocalDate.of(yearEnd, monthEnd, dayEnd);

                    // EST
                    double taskEST;
                    taskEST = Math.ceil(startDate.until(endDate, ChronoUnit.DAYS) * 8.0 * (5.0 / 7.0));

                    // Task Creation
                    if (endDate.compareTo(startDate) > 0) {
                        Task task = new Task(taskCode, taskTitle, taskDescription, employee, taskPhase,
                                project, priority, currentEmployee, startDate, endDate, taskEST);
                        Application.taskDataHandler.add(task);
                        project.getListOfTasks().add(task);
                        addExit = true;
                        System.out.println("\nTask added successfully!");
                        break;
                    } else
                        System.out.println("begin date can't be bigger than the end date");

                } catch (DateTimeException err) {
                    System.err.println(err.getMessage());
                }

            }
        }
    }

    public void deleteTask() throws IOException {
        while (true) {
            viewTasks();
            System.out.println("0)Go Back");
            String taskCode = Application.inputStringOneWord("Press 0 to go back or enter Code: ", "Input cannot be empty!\n");
            boolean found = false;
            if (taskCode.equals("0"))
                break;

            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                if (taskCode.equals(Application.taskDataHandler.get(i).getCode())) {
                    Task task = Application.taskDataHandler.get(i);
                    found = true;
                    project.getListOfTasks().remove(task);
                    for (int j = 0; j < Application.taskLogDataHandler.getLength(); j++) {
                        TaskLog taskLog = Application.taskLogDataHandler.get(j);
                        if (taskLog.getTask() == task) {
                            project.getListOfTaskLogs().remove(taskLog);
                            Application.taskLogDataHandler.delete(j);
                            j--;
                        }
                    }
                    Application.taskDataHandler.delete(i);
                    System.out.println("\nTask deleted successfully!");
                    break;
                }
            }
            if (!found)
                System.out.println("Task Code doesn't exist!");
        }
    }

    public void updateTask() throws IOException {
        while (true) {
            viewTasks();
            System.out.println("0)Go Back");
            String taskCode = Application.inputString("Press 0 to go back or enter Code: ", "Code cannot be empty!\n");
            Task task = null;
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                if (taskCode.equals(Application.taskDataHandler.get(i).getCode())) {
                    task = Application.taskDataHandler.get(i);
                    break;
                }
            }

            if (taskCode.equals("0"))
                break;
            if (task == null)
                System.out.println("Task Code Doesn't exist!");

            else {

                // update task menu

                boolean exit = false;
                while (!exit) {
                    viewTask(task);
                    System.out.println("1)Title");
                    System.out.println("2)Description");
                    System.out.println("3)Assigned employee");
                    System.out.println("4)Task phase");
                    System.out.println("5)Priority");
                    System.out.println("6)Start date");
                    System.out.println("7)End date");
                    System.out.println("0)go back");
                    int choice = Application.inputInt("Choice: ");
                    switch (choice) {
                        case 0:
                            exit = true;
                            break;
                        case 1:
                            System.out.println("\ncurrent title: " + task.getTitle());
                            String newTitle = Application.inputString("Title: ", "Title cannot be empty!\n");
                            task.setTitle(newTitle);
                            System.out.println("\nTask updated successfully!");
                            break;
                        case 2:
                            System.out.println("\ncurrent descirption: " + task.getDescription());
                            String newDescription = Application.inputString("Description: ", "Description cannot be empty!\n");
                            task.setDescription(newDescription);
                            System.out.println("\nTask updated successfully!");
                            break;
                        case 3:
                            Employee employee;
                            boolean employeeExit = false;
                            while (!employeeExit) {
                                System.out.println(
                                        "current assigned employee: " + task.getAssignedEmployee().getUsername());
                                for (int i = 0; i < Application.employeeDataHandler.getLength(); i++) {
                                    employee = Application.employeeDataHandler.get(i);
                                    System.out.println((i + 1) + ") " + employee.getUsername());
                                }
                                choice = Application.inputInt("Assign to: ");
                                if (choice > Application.employeeDataHandler.getLength() || choice < 1) {
                                    System.out.println("Invalid choice, please try again.");
                                } else {
                                    employee = Application.employeeDataHandler.get(choice - 1);
                                    task.setAssignedEmployee(employee);
                                    System.out.println("\nTask updated successfully!");
                                    employeeExit = true;
                                    break;
                                }
                            }
                            break;
                        case 4:
                            System.out.println("\ncurrent task phase: " + task.getTaskPhase());
                            String newPhase = Application.inputString("Task phase: ", "Task phase cannot be empty!\n");
                            task.setTaskPhase(newPhase);
                            System.out.println("\nTask updated successfully!");
                            break;
                        case 5:
                            boolean priorityExit = false;
                            while (!priorityExit) {
                                System.out.println("\ncurrent priority: " + task.getPriority().toString());
                                choice = Application.inputInt("Priority: \n1- easy\n2- normal\n3- high\nPriority: ");
                                switch (choice) {
                                    case 1:
                                        task.setPriority(Task.Priority.easy);
                                        System.out.println("\nTask updated successfully!");
                                        priorityExit = true;
                                        break;
                                    case 2:
                                        task.setPriority(Task.Priority.normal);
                                        System.out.println("\nTask updated successfully!");
                                        priorityExit = true;
                                        break;
                                    case 3:
                                        task.setPriority(Task.Priority.high);
                                        System.out.println("\nTask updated successfully!");
                                        priorityExit = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice, please try again.");
                                        break;
                                }
                            }
                            break;

                        case 6:
                            int year, month, day;
                            while (true) {
                                System.out.println("\ncurrent start date: " + task.getStartDate().toString());
                                System.out.println("current end date: " + task.getEndDate().toString());
                                year = Application.inputInt("Enter the year: ");
                                month = Application.inputInt("Enter the month: ");
                                day = Application.inputInt("Enter the day: ");
                                LocalDate startDate = LocalDate.of(year, month, day);
                                if (task.getEndDate().compareTo(startDate) > 0) {
                                    double taskEstStart;
                                    taskEstStart = Math
                                            .ceil(startDate.until(task.getEndDate(), ChronoUnit.DAYS) * 8.0
                                                    * (5.0 / 7.0));
                                    task.setStartDate(startDate);
                                    task.setEST(taskEstStart);
                                    System.out.println("\nTask updated successfully!");
                                    break;
                                } else
                                    System.out.println("\nstart date can't be bigger than the end date\n");
                            }

                            break;
                        case 7:
                            int yearEnd, monthEnd, dayEnd;
                            while (true) {
                                System.out.println("\ncurrent start date: " + task.getStartDate().toString());
                                System.out.println("current end date: " + task.getEndDate().toString());
                                yearEnd = Application.inputInt("Enter the year: ");
                                monthEnd = Application.inputInt("Enter the month: ");
                                dayEnd = Application.inputInt("Enter the day: ");
                                LocalDate endDate = LocalDate.of(yearEnd, monthEnd, dayEnd);
                                if (endDate.compareTo(task.getStartDate()) > 0) {
                                    double taskEstEnd;
                                    taskEstEnd = Math
                                            .ceil(task.getStartDate().until(endDate, ChronoUnit.DAYS) * 8.0
                                                    * (5.0 / 7.0));
                                    task.setEndDate(endDate);
                                    task.setEST(taskEstEnd);
                                    System.out.println("\nTask updated successfully!");
                                    break;
                                } else
                                    System.out.println("\nstart date can't be bigger than the end date\n");

                            }
                            break;

                        default:
                            System.out.println("Invalid option please choose again");
                            break;
                    }
                }
            }
        }
    }

    public void showCalender() {
        boolean check = false;
        System.out.println("\n-----------------");
        for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
            Task task = Application.taskDataHandler.get(i);
            if (task.getProject().toString().equals(project.toString())) {
                check = true;
                System.out.println("Task: " + task.getTitle());
                System.out.println("Task phase: " + task.getTaskPhase());
                System.out.println("Assigned employee: " + task.getAssignedEmployee().getUsername());
                System.out.println("Start Date: " + task.getStartDate().toString());
                System.out.println("End Date: " + task.getEndDate().toString());
                System.out.println("-----------------\n");
            }
        }
        if (!check) {
            System.out.println("There are no tasks in this project");
        }
        System.out.print("Press Enter to continue\n");
        Application.input.nextLine();
    }

}
