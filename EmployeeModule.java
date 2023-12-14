import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.InputMismatchException;


///Reset: \u001B[0m
///Red: \u001B[41m

public class EmployeeModule extends Module {
    Employee currentEmployee;

    public EmployeeModule(Employee currentEmployee) throws IOException{
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
    }

    @Override
    public void startModule() throws IOException{
        int choice=0;

        
        do {System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                           "║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
                           "║▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░Employee Module░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
                           "║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n" +
                           "║╒═════════╝                                                              ╚══════════╕║ █\n" +
                           "║│                                  Hi " + currentEmployee.getUsername() + "\n" + 
                           "║│                                                                                   │║ █\n" +
                           "║╘═══════════════════════════════════════════════════════════════════════════════════╛║ █\n" +
                           "║                     🯇 Please choose one of the following options:🯈                  ║ █\n" +
                           "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ █\n" +
                           "║│1)│View time cards.                                                                │║ █\n" +
                           "║│2)│Create a time card.                                                             │║ █\n" +
                           "║│3)│View requests.                                                                  │║ █\n" +
                           "║│4)│Make a request.                                                                 │║ █\n" +
                           "║│5)│Make a leave request.                                                           │║ █\n" +
                           "║│6)│Manage requests.                                                                │║ █\n" +
                           "║│7)│Manage a request.                                                               │║ █\n" +
                           "║│8)│Logout.                                                                         │║ █\n" +
                           "╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n" +
                           " ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");
        System.out.print("Your choice:🮶 ");
            try{
                choice = Application.input.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                Application.input.next();
                continue;
            }
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
                    System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[41m"+"║🮲🮳 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Logging Out 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                    "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    break;

                default:
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    break;
            }
        } while (choice != 8);
    }

    public void viewTimeCards() throws IOException{
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                           "║                           These are your time cards                                 ║\n" +
                           "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                           "║│                                                                                   │║");
        for(int i = 0; i < Application.timeCardDataHandler.getLength(); i++){
            Employee employee = Application.timeCardDataHandler.get(i).getEmployee();
            if(currentEmployee.getUsername().equals(employee.getUsername())){
                System.out.println("║│ "+Application.timeCardDataHandler.get(i));
                System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
            }
        }
        System.out.println("╚╧╧══════════════════════════════════════════════════════════════════════════════════╧╝");
        
        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        try{
            String choice = Application.input.next();
            if(choice.equals("y")){
                try{
                    startModule();
					return;
                }
                catch(IOException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to timecard 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                viewTimeCards();}
            else{
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            }
        }
        catch(InputMismatchException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            Application.input.next();
        }
    }

    public void createTimeCard() throws IOException{
        LocalTime departure = LocalTime.now();
        LocalDateTime attendance = LocalDateTime.now();

    System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                       "║                     🯇 Please Enter the date and time of attendace🯈                  ║\n" +
                       "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                       "║│Example-> (yyyy-MM-dd)  and   (HH:mm:ss)                                           │║\n" +
                       "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
        
        try{
            System.out.print("║ Date: ");
            String date = Application.input.next();
            System.out.print("║ Time: ");
            String time = Application.input.next();
            String dateTime = date + "T" + time;
            attendance = LocalDateTime.parse(dateTime);
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");
        }
        catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            createTimeCard();
        }

        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                        "║                          🯇 Please Enter the time of departure                       ║\n" +
                        "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                        "║│Example->  (HH:mm:ss)                                                              │║\n" +
                        "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
        
        try{
            System.out.print("║ Time: ");
            String timedep = Application.input.next();
            departure = LocalTime.parse(timedep);
        }
        catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            createTimeCard();
        }

        try {
            TimeCard newTimeCard = new TimeCard(currentEmployee, attendance, departure);
            Application.timeCardDataHandler.add(newTimeCard);
        } catch (IOException e) {
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to timecard 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            e.printStackTrace();
        }

        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        String choice = Application.input.next();
        if(choice.equals("y")){
            try{
                startModule();
				return;
            }
            catch(IOException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to timecard 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                e.printStackTrace();
            }
        }
        else if(choice.equals("n")){
        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            createTimeCard();}
        else{
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        }
    }

    public void viewRequests() throws IOException{
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                           "║                           These are your opened requests                            ║\n" +
                           "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                           "║│                                                                                   │║");
        for(int i = 0 , j = 0; i < Application.requestDataHandler.getLength(); i++, j++){
            Employee employee = Application.requestDataHandler.get(i).getEmployee();
            if (currentEmployee.getUsername().equals(employee.getUsername())) {
                  System.out.println("|| "+(j+1) +") " + Application.requestDataHandler.get(i).toString());
                  j++;
                  System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
            }
        };
        System.out.println("╚╧╧══════════════════════════════════════════════════════════════════════════════════╧╝");

        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        try{
            String choice = Application.input.next();
            if(choice.equals("y")){
                try{
                    startModule();
					return;
                }
                catch(IOException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to request 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                viewRequests();}
            else{
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            }
        }
        catch(InputMismatchException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            Application.input.next();
        }
        }

    public void makeRequest(Request request) throws IOException{

        Request.Approval approval = null;
                System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                        "║                          🯇 Please Enter the reason for the request                  ║\n" +
                        "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                        "║│A string. Example: 'Can I Hang DedSec's logo on my wall?'                          │║\n" +
                        "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                        "║ Reason: ");
        String reason = Application.input.next();
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");

        System.out.println("");
        try {
            Request newrequest = new Request(currentEmployee, reason, approval);
            Application.requestDataHandler.add(newrequest);
        } catch (IOException e) {
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to request 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            e.printStackTrace();
        }

        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        try{
            String choice = Application.input.next();
            if(choice.equals("y")){
                try{
                    startModule();
					return;
                }
                catch(IOException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to request 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                makeRequest(request);}
            else{
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            }
        }
        catch(InputMismatchException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            Application.input.next();
        }
    }

    public void makeLeaveRequest(LeaveRequest leaveRequest) throws IOException{
        Request.Approval approval = null;
        LocalDate lastDay=null;
        LocalDate firstDay=null;
        LeaveRequest.LeaveType leaveType=null;
        System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                        "║                          🯇 Please Enter a reason for the leave request              ║\n" +
                        "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                        "║│A string. Example: 'Forgot the baby in the microwave'                              │║\n" +
                        "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                        "║ Reason: ");
        String LeaveReason = Application.input.next();
        System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.print("║                          🯇 Please Enter first day of the leave request              ║\n" +
                           "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                           "║│Example-> (yyyy-MM-dd)                                                             │║\n" +
                           "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                           "║ First day: ");
        try{
            firstDay = LocalDate.parse(Application.input.next());
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════╣");
        }
        catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            makeLeaveRequest(leaveRequest);
        }
        System.out.print("║                          🯇 Please Enter last day of the leave request              ║\n" +
                           "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                           "║│Example-> (yyyy-MM-dd)                                                             │║\n" +
                           "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                           "║ Last day: ");
        try{
            lastDay = LocalDate.parse(Application.input.next());
        }
        catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            makeLeaveRequest(leaveRequest);
        }
        System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.print("║                          🯇 Please Enter the type of the leave request              ║\n" +
                           "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                           "║│Example-> (urgent, vacation, sickleave)     -->copy values                         │║\n" +
                           "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                           "║ Type: ");
        try{
            leaveType = LeaveRequest.LeaveType.valueOf(Application.input.next());
        }
        catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            makeLeaveRequest(leaveRequest);
        }
        
        try {
            LeaveRequest newleaverequest = new LeaveRequest(currentEmployee, LeaveReason, approval, Period.between(firstDay, lastDay), leaveType);
            Application.leaveRequestDataHandler.add(newleaverequest);
        } catch (IOException e) {
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to leaverequest 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            e.printStackTrace();
        }

        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
        try{
            String choice = Application.input.next();
            if(choice.equals("y")){
                try{
                    startModule();
					return;
                }
                catch(IOException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to leaverequest 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
        System.out.println("\u001B[42m" +       
                     "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
        "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
        "\n"+
        "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                makeLeaveRequest(leaveRequest);}
            else{
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            }
        }
        catch(InputMismatchException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            Application.input.next();
        }
    }

    public void manageRequests() throws IOException{
        Request request = null;
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                           "║                           These are your opened requests                            ║\n" +
                           "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                           "║│                                                                                   │║");
        for(int i = 0 , j = 0; i < Application.requestDataHandler.getLength(); i++, j++){
            Employee employee = Application.requestDataHandler.get(i).getEmployee();
            if (currentEmployee.getUsername().equals(employee.getUsername())) {
                  System.out.println("|| "+(j+1) +") " + Application.requestDataHandler.get(i).toString());
                  j++;
                  System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
            }
        };
        System.out.println("╠╧╧══════════════════════════════════════════════════════════════════════════════════╧╣");
        System.out.println("║ Please enter the ID of the request you want to manage:                              ║");
        try{
            System.out.print("║ ID: ");
            int ID = Application.input.nextInt()-1;
            request = Application.requestDataHandler.get(ID);
            manageRequest(request);
        }
        catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            manageRequests();
        }        
    }

    public void manageRequest(Request request) throws IOException{
        System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                           "║                     🯇 Please choose one of the following options:🯈                  ║ \n" +
                           "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
                           "║│1)│Change the reason of the request.                                               │║ \n" +
                           "║│2)│Return to menu.                                                                 │║ \n" +
                           "╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣ \n" +
                           "║ Please enter your choice: ");
        int choice = Application.input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("║ Please enter the new reason for your request: ");
                try{
                    String reason = Application.input.next();
                    request.setReason(reason);
                    System.out.println(request.toString());
                    String ok = Application.input.next();
                    if(ok.equals("ok")){
                        startModule();
						return;
                    }
                    else{
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    }
                }
                catch(Exception e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    Application.input.next();
                    manageRequest(request);
                }
                break;

            case 2:
            try{
                startModule();
				return;
            }
            catch(IOException e){
                System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Error occured while writing to request 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                e.printStackTrace();
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
