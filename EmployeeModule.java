import java.util.List;
import java.util.Scanner;
import java.io.*;


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
    
    public EmployeeModule(Employee currentEmployee)
    {
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
    }   

    @Override
    public void startModule(){

        int choice;

        Scanner input = new Scanner(System.in);

        System.out.println("\u001B[41m" +"\n ---------------------Employee Module---------------------\n");
        System.out.println("\t\t\tHi " +currentEmployee.getUsername()+ " !");
        do{
            System.out.println("==========================================================");
            System.out.println("|| Please choose one of the following options:\t\t||\n|| 1)\tView time cards.\t\t\t\t||\n|| 2)\tCreate a time card.\t\t\t\t||\n|| 3)\tView requests.\t\t\t\t\t||\n|| 4)\tMake a request.\t\t\t\t\t||\n|| 5)\tMake a leave request.\t\t\t\t||\n|| 6)\tManage requests.\t\t\t\t||\n|| 7)\tManage a request.\t\t\t\t||\n|| 8)\tLogout.\t\t\t\t\t\t||");
            System.out.println("==========================================================");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    viewTimeCards();
                    break;

                case 2:
                    createTimeCard(null);
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
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while(choice!=8);

    }

    public void viewTimeCards() {
    //try {
    //  DataHandler<TimeCard> timeCardDataHandler = new DataHandler<>("/files/TimeCard.txt", new TimeCard());
    //  List<TimeCard> timeCards = timeCardDataHandler.getAll();

    //  for (TimeCard timeCard : timeCards) {
    //      if (timeCard.getEmployee().equals(currentEmployee.getUsername())) {
    //          System.out.println(timeCard);
    //      }
    //  }
    //} catch (IOException e) {
    //    System.out.println("An error occurred while reading TimeCard.txt");
    //    e.printStackTrace();
    //}
}
    public void createTimeCard(TimeCard timeCard) {
        //TODO

        
    }
    public void viewRequests() {
        //TODO
    }
    public void makeRequest(Request request) {
        //TODO
    }
    public void makeLeaveRequest(LeaveRequest leaveRequest) {
        //TODO
    }
    public void manageRequests() {
        //TODO
    }
    public void manageRequest(Request request) {
        //TODO
    }
}
