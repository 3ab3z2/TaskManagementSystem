import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.security.cert.CertPathValidatorException.Reason;
import java.time.LocalDateTime;
import java.time.LocalTime;


///ANSI COLOR CODES:
///Reset: \u001B[0m
///Red: \u001B[31m
///Green: \u001B[32m
///Yellow: \u001B[33m
///Blue: \u001B[34m
///Purple: \u001B[35m
///Cyan: \u001B[36m
///White: \u001B[37m
///ANSI BACKGROUND COLOR CODES:
///Reset: \u001B[0m
///Red: \u001B[41m
///Green: \u001B[42m
///Yellow: \u001B[43m
///Blue: \u001B[44m
///Purple: \u001B[45m
///Cyan: \u001B[46m
///White: \u001B[47m
///ANSI TEXT STYLE CODES:
///Reset All: \u001B[0m
///Bold: \u001B[1m
///Dim: \u001B[2m   
///Underline: \u001B[4m
///Blink: \u001B[5m
///Reverse: \u001B[7m
///Hidden: \u001B[8m

public class EmployeeModule extends Module {
    Employee currentEmployee;

    public EmployeeModule(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
    }

    @Override
    public void startModule() {
        int choice;
        Scanner input = new Scanner(System.in);

        System.out.println("\u001B[1m" + "\n ---------------------Employee Module---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
        System.out.println("\t\t\tHi \u001B[1m"+currentEmployee.getUsername()+" !");

        do {
            System.out.println("==========================================================");
            System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
                    "|| 1)\u001B[35m\tView time cards.\u001B[0m\t\t\t\t||\n" +
                    "|| 2)\u001B[35m\tCreate a time card.\u001B[0m\t\t\t\t||\n" +
                    "|| 3)\u001B[35m\tView requests.\u001B[0m\t\t\t\t\t||\n" +
                    "|| 4)\u001B[35m\tMake a request.\u001B[0m\t\t\t\t\t||\n" +
                    "|| 5)\u001B[35m\tMake a leave request.\u001B[0m\t\t\t\t||\n" +
                    "|| 6)\u001B[35m\tManage requests.\u001B[0m\t\t\t\t||\n" +
                    "|| 7)\u001B[35m\tManage a request.\u001B[0m\t\t\t\t||\n" +
                    "|| \u001B[41m8)\tLogout.\u001B[0m\t\t\t\t\t\t||");
            System.out.println("==========================================================");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    viewTimeCards();
                    break;

                case 2:
                    createTimeCard();
                    break;

                case 3:
                    viewRequests();
                    break;

                case 4:
                    makeRequest(null);
                    break;

                case 5:
                    makeLeaveRequest(null);
                    break;

                case 6:
                    manageRequests();
                    break;

                case 7:
                    manageRequest(null);
                    break;

                case 8:
                    System.out.println("\u001B[41m Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 8);
    }

    public void viewTimeCards() {
        //try {
        //  DataHandler<TimeCard> timeCardDataHandler = new DataHandler<>("/files/TimeCard.txt", new TimeCard());
        //  List<TimeCard> timeCards = timeCardDataHandler.getAll();

        //  for (TimeCard timeCard : timeCards) {
        //      if (timeCard.getEmployee().equals(currentEmployee.getUsername())) {
        //          System.out.println(timeCard);
        //          System.out.println("----------------------------------------------------------");
        //      }
        //  }
        //} catch (IOException e) {
        //    System.out.println("An error occurred while reading TimeCard.txt");
        //    e.printStackTrace();
        //}
    }

    public void createTimeCard() {
        Scanner input = new Scanner(System.in);
        LocalTime departure = null;
        LocalDateTime attendance = LocalDateTime.now();

        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"Please enter the date and time of your attendance\u001B[0m\t||\n|| \u001B[43m(yyyy-MM-dd HH:mm:ss): \u001B[0m\t\t\t\t||\n" +"||  \t\t\t\t\t\t\t||");
        System.out.print("|| \u001B[43m"+"Date: \u001B[0m");
        String date = input.nextLine();
        System.out.print("|| \u001B[43m"+"Time: \u001B[0m");
        String time = input.nextLine();
        String dateTime = date + " " + time;
        attendance = LocalDateTime.parse(dateTime);

        System.out.println("==========================================================");

        input.close();
        

        System.out.println("");
        try {
            DataHandler<TimeCard> timeCardDataHandler = new DataHandler<>("TimeCard.txt", new TimeCard(currentEmployee, attendance, departure));
            TimeCard newTimeCard = new TimeCard(currentEmployee, attendance, departure);
            timeCardDataHandler.add(newTimeCard);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to TimeCard.txt");
            e.printStackTrace();
        }
    }

    public void viewRequests() {
        //try {
        //  DataHandler<Request> requestDataHandler = new DataHandler<>("/files/Request.txt", new Request());
        //  List<Request> request = requestDataHandler.getAll();

        //  for (Request request : request) {
        //      if (Request.getEmployee().equals(currentEmployee.getUsername())) {
        //          System.out.println(Request);
        //          System.out.println("----------------------------------------------------------");
        //      }
        //  }
        //} catch (IOException e) {
        //    System.out.println("An error occurred while reading request.txt");
        //    e.printStackTrace();
        //}
    }

    public void makeRequest(Request request) {

        Request.Approval approval = null;
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"Please enter the reason for your request:\u001B[0m\t\t||\n" +"||  \t\t\t\t\t\t\t||");
        String reason = input.nextLine();
        System.out.println("==========================================================");

        System.out.println("");
        try {
            DataHandler<Request> requestDataHandler = new DataHandler<>("/files/Request.txt", new Request(currentEmployee, reason, approval));
            Request newrequest = new Request(currentEmployee, reason, approval);
            requestDataHandler.add(newrequest);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to request.txt");
            e.printStackTrace();
        }

        input.close();
    }

    public void makeLeaveRequest(LeaveRequest leaveRequest) {
        Request.Approval approval = null;
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"Please enter the reason for your leave request:\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        String LeaveReason = input.nextLine();
        System.out.println("|| \u001B[43m"+"Please enter the duration of your leave request:\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        int duration = input.nextInt();
        System.out.println("|| \u001B[43m"+"Please enter the type of your leave request:\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        String leaveTypeString = input.nextLine();
        LeaveRequest.LeaveType leaveType = LeaveRequest.LeaveType.valueOf(leaveTypeString);    
        System.out.println("==========================================================");

        System.out.println("");
        
        try {
            DataHandler<LeaveRequest> leaveRequestDataHandler = new DataHandler<>("/files/LeaveRequest.txt", new LeaveRequest(currentEmployee, LeaveReason, approval, duration, leaveType));
            LeaveRequest newleaverequest = new LeaveRequest(currentEmployee, LeaveReason, approval, duration, leaveType);
            leaveRequestDataHandler.add(newleaverequest);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to LeaveRequest.txt");
            e.printStackTrace();
        }

        input.close();
    }

    public void manageRequests() {
        //TODO
    }

    public void manageRequest(Request request) {
        //TODO
    }
}
