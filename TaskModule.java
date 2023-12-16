import java.io.IOException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.InputMismatchException;

public class TaskModule extends Module {
    Employee currentEmployee;
    Project project;

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

            if (project.getLeader() == currentEmployee) {
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
                                "║│4)│Go back.                                                                        │║ █\n"
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

                    case 4:
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
                exit = false;
                while (!exit) {
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
                                    "║│3)│Go back.                                                                        │║ █\n"
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

                        case 3:
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

    }

    public void viewTasks() throws IOException {

        // display tasks
        System.out.println(
                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                        "║                              These are your tasks                                   ║\n" +
                        "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║");
        // manager
        if (project.getLeader() == currentEmployee) {
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (task.project == project)
                    System.out.println("║│ Code:" + task.getCode() + "│Title: " + task.getTitle());
            }
        }
        // employee
        else {
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

            System.out.print("Enter Task Code: ");
            String taskCode = Application.input.next();
            Application.input.nextLine();
            boolean found = false;
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (taskCode.equals(task.getCode())) {
                    found = true;
                    viewTaskMenu(task);
                }
            }
            if (!found)
                System.out.println("task code doesn't exist");

            if (taskCode.equals("0")) {
                System.out.println("Going to the previous page");
                break;
            }

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
            System.out.println("0)Go Back");
            System.out.println("1)View tasklogs");
            System.out.println("2)Create tasklog");
            int choice = Application.inputInt("Choice: ");
            switch (choice) {
                case 0:
                    System.out.println("Going to the previous page");
                    exit = true;
                    break;

                case 1:
                    TaskLog[] tasklogArr = new TaskLog[Application.taskLogDataHandler.getLength()];
                    for (int i = 0, j = 0; i < Application.taskLogDataHandler.getLength(); i++)
                        if (Application.taskLogDataHandler.get(i).getTask() == task)
                            tasklogArr[j] = Application.taskLogDataHandler.get(i);

                    viewTasklogsMenu(task, tasklogArr);
                    break;

                case 2:
                    while (true) {
                        // from time input
                        System.out.println("---From Time--");
                        int year = Application.inputInt("Enter the year: ");
                        int month = Application.inputInt("Enter the month: ");
                        int day = Application.inputInt("Enter the day: ");
                        int hour = Application.inputInt("Enter the hour: ");
                        int minute = Application.inputInt("Enter the minute: ");

                        // to time input
                        System.out.println("---To Time---");
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
            System.err.println("To time smaller than the from time");
        return null;
    }

    public void viewTasklogs(TaskLog[] tasklogs) {

        for (int i = 0; i < tasklogs.length; i++)
            System.out.println(
                    (i + 1) + ") From time: " + Application.taskLogDataHandler.get(i).getFromTime().toString()
                            + " To time: " + Application.taskLogDataHandler.get(i).getToTime().toString());

    }

    public void viewTasklogsMenu(Task task, TaskLog[] tasklogs) {
        while (true) {
            viewTasklogs(tasklogs);
            System.out.println("0)Go Back");
            int choice = Application.inputInt("Tasklog number: ");
            if (choice == 0)
                break;
            else if (choice < 1 || choice > tasklogs.length)
                System.out.println("Chosen tasklog doesn't exist");
            else
                viewTasklog(tasklogs[choice - 1]);
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
    }

    public void manageTasks() throws IOException {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println("---Manage Tasks---");
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
        while (true) {

            // input task data
            System.out.println("---Task details---");

            // code
            boolean codeExist = false;
            String taskCode;
            while (true) {
                System.out.print("Code: ");
                taskCode = Application.input.next().trim();
                Application.input.nextLine();
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
            System.out.print("Title: ");
            String taskTitle = Application.input.next().trim();
            Application.input.nextLine();

            // desc
            System.out.print("Description: ");
            String taskDescription = Application.input.next().trim();
            Application.input.nextLine();

            // task phase
            System.out.print("Task phase: ");
            String taskPhase = Application.input.next();
            Application.input.nextLine();

            // Priority
            int choice;
            Task.Priority priority = null;
            boolean exit = false;
            while (!exit) {
                choice = Application.inputInt("1-easy\n2-normal\n3-high\nPriority: ");
                Application.input.nextLine();
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
                    System.out.println("---End date---");
                    yearEnd = Application.inputInt("Enter the year: ");
                    monthEnd = Application.inputInt("Enter the month: ");
                    dayEnd = Application.inputInt("Enter the day: ");
                    LocalDate endDate = LocalDate.of(yearEnd, monthEnd, dayEnd);

                    // TODO: EXECPTION ERROR
                    double taskEST;
                    // taskEST = Duration.between(startDate, endDate).toDays() * 8 * (5 / 7);
                    taskEST = 0;
                    // Task Creation
                    if (endDate.compareTo(startDate) > 0) {
                        Task task = new Task(taskCode, taskTitle, taskDescription, employee, taskPhase,
                                project, priority, currentEmployee, startDate, endDate, taskEST);
                        Application.taskDataHandler.add(task);
                        project.getListOfTasks().add(task);
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
            System.out.println("Choice: ");
            String taskCode = Application.input.next();
            Application.input.nextLine();
            boolean found = false;
            if (taskCode.equals("0"))
                break;

            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                if (taskCode.equals(Application.taskDataHandler.get(i).getCode())) {
                    Task task = Application.taskDataHandler.get(i);
                    found = true;
                    project.getListOfTasks().remove(task);
                    for (int j = 0; j < Application.taskLogDataHandler.getLength(); j++) {
                        TaskLog taskLog = Application.taskLogDataHandler.get(i);
                        if (taskLog.getTask() == task) {
                            project.getListOfTaskLogs().remove(taskLog);
                            Application.taskLogDataHandler.delete(i);
                            j--;
                        }
                    }
                    Application.taskDataHandler.delete(i);
                    break;
                }
            }
            if (found)
                System.out.println("Task Code doesn't exist!");
        }
    }

    public void updateTask() throws IOException {
        while (true) {
            viewTasks();
            System.out.println("0)Go Back");
            System.out.print("Choice: ");
            String taskCode = Application.input.next();
            Application.input.nextLine();
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
                    System.out.println("0)exit");
                    System.out.println("1)Title");
                    System.out.println("2)Description");
                    System.out.println("3)Assigned employee");
                    System.out.println("4)Task phase");
                    System.out.println("5)Priority");
                    int choice = Application.inputInt("Choice: ");
                    switch (choice) {
                        case 0:
                            exit = true;
                            break;
                        case 1:
                            System.out.print("Enter the new title: ");
                            String newTitle = Application.input.next();
                            Application.input.nextLine();
                            task.setTitle(newTitle);
                            break;
                        case 2:
                            System.out.print("Enter the new Description: ");
                            String newDescription = Application.input.next();
                            Application.input.nextLine();
                            task.setDescription(newDescription);
                            break;
                        case 3:
                            Employee employee;
                            boolean employeeExit = false;
                            while (!employeeExit) {
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
                                    employeeExit = true;
                                    break;
                                }
                            }
                            break;
                        case 4:
                            System.out.print("Enter the new Task phase: ");
                            String newPhase = Application.input.next();
                            Application.input.nextLine();
                            task.setTaskPhase(newPhase);
                            break;
                        case 5:
                            boolean priorityExit = false;
                            while (!priorityExit) {
                                choice = Application.inputInt("Priority: \n1- easy\n2- normal\n3- high\nPriority: ");
                                switch (choice) {
                                    case 1:
                                        task.setPriority(Task.Priority.easy);
                                        priorityExit = true;
                                        break;
                                    case 2:
                                        task.setPriority(Task.Priority.normal);
                                        priorityExit = true;
                                        break;
                                    case 3:
                                        task.setPriority(Task.Priority.high);
                                        priorityExit = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice, please try again.");
                                        break;
                                }
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
            if (task.project.toString().equals(project.toString())) {
                check = true;
                System.out.println("Task: " + task.getTitle());
                System.out.println("Task phase: " + task.getTaskPhase());
                System.out.println("Assigned employee: " + task.getAssignedEmployee().getUsername());
                System.out.println("-----------------\n");
            }
        }
        if (!check) {
            System.out.println("There are no tasks in this project");
        }
        System.out.print("Press Enter to continue: ");
        Application.input.nextLine();
    }

}
