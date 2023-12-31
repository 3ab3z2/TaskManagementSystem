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
            System.out.print("\033[H\033[2J"); System.out.flush();
            System.out.print(
                    "╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n"
                            +
                            "║▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░Task Module░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n"
                            +
                            "║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n"
                            +
                            "║╒═════════╝                                                              ╚══════════╕║ █\n"
                            +
                            "║│                                  Hi " + currentEmployee.getUsername());
            for(int i = 0; i < 46 - currentEmployee.getUsername().length(); i++){
                System.out.print(" ");
            };
            System.out.print("│║ █\n");
            System.out.print(
                            "║│                                                                                   │║ █\n"
                            +
                            "║╘═══════════════════════════════════════════════════════════════════════════════════╛║ █\n");

            if (project.getLeader()==currentEmployee) {
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
                            System.out.print("\033[H\033[2J"); System.out.flush();
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
                        System.out.print("\033[H\033[2J"); System.out.flush();
                        showCalender();
                        break;

                    case 0:
                        System.out.print("\033[H\033[2J"); System.out.flush();
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
        System.out.print("\033[H\033[2J"); System.out.flush();

        // display tasks
        System.out.println(
                "╔═════════════════════════════════════════════════════════════════════════════════════╗");
       
        // manager
        if (project.getLeader()==currentEmployee) {
             System.out.println(
                "║                              These are the project's tasks                          ║\n" +
                        "║╒════════╤══════════════════════════════════════════════════════════════════════════╕║");
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (task.getProject()==project) {

                    System.out.print("║│ Code:  " + task.getCode());
                    for(int j = 0; j < 75 - task.getCode().length(); j++){
                        System.out.print(" ");
                    };
                    System.out.println("│║");
                    System.out.print("║│ Title: " + task.getTitle());
                    for(int j = 0; j < 75 - task.getTitle().length(); j++){
                        System.out.print(" ");
                    };
                    System.out.println("│║");
                    System.out.println("║├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┤║");
                }
            }
        }
        // employee
        else {
             System.out.println(
                "║                              These are your assigned tasks                          ║\n" +
                        "║╒════════╤══════════════════════════════════════════════════════════════════════════╕║");
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (task.getProject()==project && task.getAssignedEmployee()==currentEmployee)
                {
                    System.out.print("║│ Code:  │" + task.getCode());
                    for(int j = 0; j < 73 - task.getCode().length(); j++){
                        System.out.print(" ");
                    };
                    System.out.print("│║\n");
                    System.out.print("║│ Title: │" + task.getTitle());
                    for(int j = 0; j < 74 - task.getTitle().length(); j++){
                        System.out.print(" ");
                    };
                    System.out.println("│║");

                }
            }
        }
        System.out.println("║│0)      │Go back.                                                                  │║");
        System.out.println("╚╧════════╧══════════════════════════════════════════════════════════════════════════╧╝");

    }

    public void viewTasksMenu() throws IOException {
        while (true) {
            viewTasks();
            String taskCode = Application.inputStringOneWord("Your choice:🮶", "\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
            "\u001B[41m"+"║🮲🮳 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Empty Input 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
            "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            boolean found = false;
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                Task task = Application.taskDataHandler.get(i);
                if (taskCode.equals(task.getCode())) {
                    found = true;
                    viewTaskMenu(task);
                }
            }
            if (taskCode.equals("0")) {
                System.out.println("\u001B[42m" +       
                            "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[42m"+"║                                 Returning back🮴                                    ║"+"\u001B[0m" +
                "\n"+
                "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                break;
            }
            if (!found)
            System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
            "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
            "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");

        }
    }

    public void viewTask(Task task) throws IOException {

        System.out.print("\n"+
                         "╔══════════════╤══════════════════════════════════════════════════════════════════════╗"+
                         "║ Title        │" + task.getTitle());
        for(int i = 0; i < 69 - task.getTitle().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.print("║ Code         │" + task.getCode());
        for(int i = 0; i < 69 - task.getCode().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.print("║ Description  │" + task.getDescription());
        for(int i = 0; i < 69 - task.getDescription().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.print("║ Task phase   │" + task.getTaskPhase());
        for(int i = 0; i < 69 - task.getTaskPhase().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.print("║ Priority     │" + task.getPriority().toString());
        for(int i = 0; i < 69 - task.getPriority().toString().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.print("║ Assigned to  │" + task.getAssignedEmployee().getUsername());
        for(int i = 0; i < 69 - task.getAssignedEmployee().getUsername().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.print("║ Start date   │" + task.getStartDate().toString());
        for(int i = 0; i < 69 - task.getStartDate().toString().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");

        System.out.print("║ Due date     │" + task.getEndDate().toString());
        for(int i = 0; i < 69 - task.getEndDate().toString().length(); i++){
            System.out.print(" ");
        };
        System.out.println("║");
        System.out.println("║ EST          │" + task.getEST());
        System.out.println("╚══════════════╧══════════════════════════════════════════════════════════════════════╝");

    }

    public void viewTaskMenu(Task task) throws IOException {
        LocalDateTime startdate = LocalDateTime.now();
        LocalDateTime end_date_time = LocalDateTime.now();
        boolean exit = false;
        while (!exit) {
            System.out.print("\033[H\033[2J"); System.out.flush();
            viewTask(task);
            System.out.print(   "╔═════════════════════════════════════════════════════════════════════════════════════╗ \n"+
                                "║                 🯇 Please choose one of the following options:🯈                     ║ \n"+
                                "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
                                "║│1)│View tasklogs.                                                                  │║ \n" +
                                "║│2)│Create tasklog.                                                                 │║ \n" +
                                "║│0)│Logout.                                                                         │║ \n" +
                                "╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ \n");
            int choice = Application.inputInt("Your choice:🮶 ");
            switch (choice) {
                case 0:
                System.out.println("\u001B[42m" +       
                            "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[42m"+"║                                 Returning back🮴                                    ║"+"\u001B[0m" +
                "\n"+
                "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
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
                        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                                    "║                     🯇 Please Enter the start date and time 🯈                        ║\n" +
                                    "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                                    "║│Example-> (yyyy-MM-dd)  and   (HH:mm:ss)                                           │║\n" +
                                    "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
                        
                        try{
                            System.out.print("║ Date: ");
                            String date = Application.input.next();
                            System.out.print("║ Time: ");
                            String time = Application.input.next();
                            String dateTime = date + "T" + time;
                            startdate = LocalDateTime.parse(dateTime);
                            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");
                            
                        }    catch(Exception e){
                                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                            continue;
                        }
                        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                                    "║                     🯇 Please Enter the end date and time 🯈                          ║\n" +
                                    "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                                    "║│Example-> (yyyy-MM-dd)  and   (HH:mm:ss)                                           │║\n" +
                                    "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
                        
                        try{
                            System.out.print("║ Date: ");
                            String end_date = Application.input.next();
                            System.out.print("║ Time: ");
                            String end_time = Application.input.next();
                            String dateTime = end_date + "T" + end_time;
                            end_date_time = LocalDateTime.parse(dateTime);
                            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");
                            
                        }    catch(Exception e){
                                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                            continue;
                        }

                        // try catch to handle DateTime Exceptions
                        try {
                            // Creating the tasklog
                            TaskLog log = createTaskLog(task, startdate,
                                    end_date_time);
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
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
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
            System.err.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 End date is later than start date 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        return null;
    }

    public void viewTasklogs(ArrayList<TaskLog> tasklogs) {
        System.out.print("\033[H\033[2J"); System.out.flush();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗ \n"+
                                "║                             These are the tasklogs                                  ║ \n"+
                                "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n");
        for (int i = 0; i < tasklogs.size(); i++){
            System.out.print("║│"+(i+1)+"│From time: " + tasklogs.get(i).getFromTime().toString());
            for(int j = 0; j < 76 - tasklogs.get(i).getFromTime().toString().length(); j++){
                System.out.print(" ");
            };
            System.out.println("║");
            System.out.print("║│  │To time: " + tasklogs.get(i).getToTime().toString());
            for(int j = 0; j < 76 - tasklogs.get(i).getToTime().toString().length(); j++){
                System.out.print(" ");
            };
            System.out.println("║");
            System.out.println("║├┄┄┼┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┤║");
        }
        System.out.println("║│0)│Go back.                                                                        │║");
        System.out.println("╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝");

    }

    public void viewTasklogsMenu(Task task, ArrayList<TaskLog> tasklogs) {
        while (true) {
            viewTasklogs(tasklogs);
            int choice = Application.inputInt("Your choice:🮶");
            if (choice == 0)
            {
                System.out.println("\u001B[42m" +       
                            "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[42m"+"║                                 Returning back🮴                                    ║"+"\u001B[0m" +
                "\n"+
                "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                break;
            }
                
            else if (choice < 1 || choice > tasklogs.size())
                                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            else
                viewTasklog(tasklogs.get(choice -1));
        }
    }

    public void viewTasklog(TaskLog taskLog) {
        System.out.print("\033[H\033[2J"); System.out.flush();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗ \n"+
                           "║                             Title:" + taskLog.getTask().getTitle());
        for(int i = 0; i < 50 - taskLog.getTask().getTitle().length(); i++){
            System.out.print(" ");
        }
        System.out.print("║ \n");
        System.out.print("║╒═══════════════════════════════════════════════════════════════════════════════════╕║ \n");
        System.out.println("║│From time: " + taskLog.getFromTime().toString());
        for(int i = 0; i < 72 - taskLog.getFromTime().toString().length(); i++){
            System.out.print(" ");
        }
        System.out.print("║ \n");
        System.out.println("║│To time:   " + taskLog.getToTime().toString());
        for(int i = 0; i < 72 - taskLog.getToTime().toString().length(); i++){
            System.out.print(" ");
        }
        System.out.print("║ \n");
        if (project.getLeader()==currentEmployee){
            System.out.println("║│Assigned to: " + taskLog.getAssignedEmployee().getUsername());
            for(int i = 0; i < 69 - taskLog.getToTime().toString().length(); i++){
            System.out.print(" ");
            }
            System.out.print("║ \n");
        }
        System.out.println("╚╧═══════════════════════════════════════════════════════════════════════════════════╧╝");
        System.out.print("Press Enter to continue\n");
        Application.input.nextLine();
    }

    public void manageTasks() throws IOException {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.print(   "╔═════════════════════════════════════════════════════════════════════════════════════╗ \n"+
                                "║                 🯇 Please choose one of the following options:🯈                     ║ \n"+
                                "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
                                "║│1)│Add task.                                                                       │║ \n" +
                                "║│2)│Delete task.                                                                    │║ \n" +
                                "║│3)│Update task.                                                                    │║ \n" +
                                "║│0)│back.                                                                           │║ \n" +
                                "╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ \n");
            choice = Application.inputInt("Your choice:🮶 ");
            switch (choice) {
                case 0:
                    System.out.println("\u001B[42m" +       
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
                    "\n"+
                    "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
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
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    break;
            }
        }
    }

    public void addTask() throws IOException {
        boolean addExit = false;
        while (!addExit) {

            boolean codeExist = false;
            String taskCode;
            while (true) {
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");
                taskCode = Application.inputStringOneWord("\n╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                                    🯇 Task Data                                      ║\n" +
                            "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                            "║│Here you can add a task                                                            │║\n" +
                            "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                            "║ Code: ", "\033[31mCode cannot be empty!\033[0m\n");
                for (int i = 0; i < Application.taskDataHandler.getLength(); i++)
                    if (Application.taskDataHandler.get(i).getCode().equals(taskCode)) {
                        System.err.println("\033[31mCode already exists!\033[0m");
                        codeExist = true;
                        break;
                    }
                if (!codeExist)
                    break;
            }

            // title
            String taskTitle = Application.inputString("\n---Title---\nTitle: ", "\033[31mTitle cannot be empty!\033[0m\n");
            

            // desc
            String taskDescription = Application.inputString("\n---Description---\nDescription: ", "\033[31mDescription cannot be empty!\033[0m\n");
            
            // task phase
            String taskPhase = Application.inputStringOneWord("\n---Task phase---\nTask phase: ", "\033[31mTask phase cannot be empty!\033[0m\n");
           
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
                        System.out.println("\033[31mUnknown choice, please try again.\033[0m");
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
                    System.out.println("\033[31mInvalid choice, please try again.\033[0m");
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
                        System.out.println("\n\033[32mTask added successfully!\033[0m");
                        break;
                    } else
                        System.out.println("\033[31mStart date can't be bigger than the end date.\033[0m");

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
            String taskCode = Application.inputStringOneWord("Press 0 to go back or enter Code: ", "\033[31mInput cannot be empty!\033[0m\n");
            boolean found = false;
            if (taskCode.equals("0"))
            {
                System.out.println("Going to the previous page");
                break;
            }

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
                    System.out.println("\n\033[32mTask deleted successfully!\033[0m");
                    break;
                }
            }
            if (!found)
                System.out.println("\033[31mTask Code doesn't exist!\033[0m");
        }
    }

    public void updateTask() throws IOException {
        while (true) {
            viewTasks();
            System.out.println("0)Go Back");
            String taskCode = Application.inputString("Press 0 to go back or enter Code: ", "\033[31mCode cannot be empty!\033[0m\n");
            Task task = null;
            for (int i = 0; i < Application.taskDataHandler.getLength(); i++) {
                if (taskCode.equals(Application.taskDataHandler.get(i).getCode())) {
                    task = Application.taskDataHandler.get(i);
                    break;
                }
            }

            if (taskCode.equals("0"))
            {
                System.out.println("Going to the previous page");
                break;
            }
            if (task == null)
                System.out.println("\033[31mTask Code Doesn't exist!\033[0m");

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
                            String newTitle = Application.inputString("Title: ", "\033[31mTitle cannot be empty!\033[0m\n");
                            task.setTitle(newTitle);
                            System.out.println("\n\033[32mTask updated successfully!\033[0m");
                            break;
                        case 2:
                            System.out.println("\ncurrent descirption: " + task.getDescription());
                            String newDescription = Application.inputString("Description: ", "\033[31mDescription cannot be empty!\033[0m\n");
                            task.setDescription(newDescription);
                             System.out.println("\n\033[32mTask updated successfully!\033[0m");
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
                                    System.out.println("\033[31mInvalid choice, please try again.\033[0m");
                                } else {
                                    employee = Application.employeeDataHandler.get(choice - 1);
                                    task.setAssignedEmployee(employee);
                                    System.out.println("\n\033[32mTask updated successfully!\033[0m");
                                    employeeExit = true;
                                    break;
                                }
                            }
                            break;
                        case 4:
                            System.out.println("\ncurrent task phase: " + task.getTaskPhase());
                            String newPhase = Application.inputString("Task phase: ", "\033[31mTask phase cannot be empty!\033[0m\n");
                            task.setTaskPhase(newPhase);
                            System.out.println("\n\033[32mTask updated successfully!\033[0m");
                            break;
                        case 5:
                            boolean priorityExit = false;
                            while (!priorityExit) {
                                System.out.println("\ncurrent priority: " + task.getPriority().toString());
                                choice = Application.inputInt("Priority: \n1- easy\n2- normal\n3- high\nPriority: ");
                                switch (choice) {
                                    case 1:
                                        task.setPriority(Task.Priority.easy);
                                        System.out.println("\n\033[32mTask updated successfully!\033[0m");
                                        priorityExit = true;
                                        break;
                                    case 2:
                                        task.setPriority(Task.Priority.normal);
                                        System.out.println("\n\033[32mTask updated successfully!\033[0m");
                                        priorityExit = true;
                                        break;
                                    case 3:
                                        task.setPriority(Task.Priority.high);
                                        System.out.println("\n\033[32mTask updated successfully!\033[0m");
                                        priorityExit = true;
                                        break;
                                    default:
                                        System.out.println("\033[31mInvalid choice, please try again.\033[0m");
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
                                    System.out.println("\n\033[32mTask updated successfully!\033[0m");
                                    break;
                                } else
                                    System.out.println("\n\033[31mStart date can't be bigger than the end date\033[0m\n");
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
                                    System.out.println("\n\033[32mTask updated successfully!\033[0m");
                                    break;
                                } else
                                    System.out.println("\n\033[31mStart date can't be bigger than the end date\033[0m\n");

                            }
                            break;

                        default:
                            System.out.println("\033[31mInvalid option please choose again\033[0m");
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
            if (task.getProject()==project) {
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
