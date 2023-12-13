import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.InputMismatchException;


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

    public EmployeeModule(Employee currentEmployee) throws IOException{
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
    }

    @Override
    public void startModule() throws IOException{
        int choice=0;

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
            try{
                choice = Application.input.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("|| \u001B[41m"+"Invalid choice, please try again.\u001B[0m\t\t\t\t||");
                Application.input.nextLine();
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
                    System.out.println("\u001B[41m Logging out...\u001B[0m");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 8);
    }

    public void viewTimeCards() throws IOException{
        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"These are your time cards:\u001B[0m\t\t\t||\n");
        for(int i = 0; i < Application.timeCardDataHandler.getLength(); i++){
            Employee employee = Application.requestDataHandler.get(i).getEmployee();
            if(currentEmployee.getUsername().equals(employee.getUsername())){
                System.out.println(Application.timeCardDataHandler.get(i));
                System.out.println("----------------------------------------------------------");
            }
        }
        System.out.println("==========================================================");
        System.out.println("Return to menu? (y/n)");
        try{
            String choice = Application.input.nextLine();
            if(choice.equals("y")){
                try{
                    startModule();
                }
                catch(IOException e){
                    System.out.println("An error occurred while writing to TimeCard.txt");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
                System.out.println("Returning to menu...");
                viewTimeCards();}
            else{
                System.out.println("Invalid choice, please try again.");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
        }
    }

    public void createTimeCard() throws IOException{
        LocalTime departure = LocalTime.now();
        LocalDateTime attendance = LocalDateTime.now();

        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"Please enter the date and time of your attendance\u001B[0m\t||\n|| \u001B[43m(yyyy-MM-dd HH:mm:ss): \u001B[0m\t\t\t\t||\n" +"||  \t\t\t\t\t\t\t||");
        
        try{
            System.out.print("|| \u001B[43m"+"Date: \u001B[0m");
            String date = Application.input.next();
            System.out.print("|| \u001B[43m"+"Time: \u001B[0m");
            String time = Application.input.next();
            String dateTime = date + "T" + time;
            attendance = LocalDateTime.parse(dateTime);
        }
        catch(Exception e){
            System.out.println("Invalid choice, please try again.");
            createTimeCard();
        }

        System.out.println("==========================================================");

        System.out.println("|| \u001B[43m"+"Please enter the time of your departure\u001B[0m\t\t||\n|| \u001B[43m(HH:mm:ss): \u001B[0m\t\t\t\t\t\t||\n" +"||  \t\t\t\t\t\t\t||");
        
        try{
            System.out.print("|| \u001B[43m"+"Time: \u001B[0m");
            String timedep = Application.input.next();
            departure = LocalTime.parse(timedep);
        }
        catch(Exception e){
            System.out.println("Invalid choice, please try again.");
            createTimeCard();
        }

        System.out.println("==========================================================");

       
        System.out.println("");
        try {
            TimeCard newTimeCard = new TimeCard(currentEmployee, attendance, departure);
            Application.timeCardDataHandler.add(newTimeCard);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to TimeCard.txt");
            e.printStackTrace();
        }

        System.out.println("Return to menu? (y/n)");
        String choice = Application.input.next();
        if(choice.equals("y")){
            try{
                startModule();
            }
            catch(IOException e){
                System.out.println("An error occurred while writing to TimeCard.txt");
                e.printStackTrace();
            }
        }
        else if(choice.equals("n")){
            System.out.println("Returning to menu...");
            createTimeCard();}
        else{
            System.out.println("Invalid choice, please try again.");
        }
    }

    public void viewRequests() throws IOException{
        System.out.println("==========================================================");
        for(int i = 0 , j = 0; i < Application.requestDataHandler.getLength(); i++, j++){
            Employee employee = Application.requestDataHandler.get(i).getEmployee();
            if (currentEmployee.getUsername().equals(employee.getUsername())) {
                  System.out.println("|| "+(j+1) +") " + Application.requestDataHandler.get(i).toString());
                  j++;
                  System.out.println("----------------------------------------------------------");
              }
            };
        System.out.println("==========================================================");System.out.println("Return to menu? (y/n)");
        try{
            String choice = Application.input.nextLine();
            if(choice.equals("y")){
                try{
                    startModule();
                }
                catch(IOException e){
                    System.out.println("An error occurred while writing to request.txt");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
                System.out.println("Returning to menu...");
                viewRequests();}
            else{
                System.out.println("Invalid choice, please try again.");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
        }
        }

    public void makeRequest(Request request) throws IOException{

        Request.Approval approval = null;
        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"Please enter the reason for your request:\u001B[0m\t\t||\n" +"||  \t\t\t\t\t\t\t||");
        //putting a try and catch here makes it impossible that "reason" is readable by Request, so not writing it here
        String reason = Application.input.nextLine();
        System.out.println("==========================================================");

        System.out.println("");
        try {
            Request newrequest = new Request(currentEmployee, reason, approval);
            Application.requestDataHandler.add(newrequest);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to request.txt");
            e.printStackTrace();
        }

        System.out.println("Return to menu? (y/n)");
        try{
            String choice = Application.input.nextLine();
            if(choice.equals("y")){
                try{
                    startModule();
                }
                catch(IOException e){
                    System.out.println("An error occurred while writing to request.txt");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
                System.out.println("Returning to menu...");
                makeRequest(request);}
            else{
                System.out.println("Invalid choice, please try again.");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
        }
    }

    public void makeLeaveRequest(LeaveRequest leaveRequest) throws IOException{
        Request.Approval approval = null;
        LocalDate lastDay=null;
        LocalDate firstDay=null;
        LeaveRequest.LeaveType leaveType=null;
        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"Please enter the reason for your leave request:\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        String LeaveReason = Application.input.nextLine();
        System.out.println("|| \u001B[43m"+"Please enter the first day of your leave request: (ex:2010-01-15)\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        try{
            firstDay = LocalDate.parse(Application.input.nextLine());
        }
        catch(Exception e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
            makeLeaveRequest(leaveRequest);
        }
        System.out.println("|| \u001B[43m"+"Please enter the last day of your leave request: (ex:2010-01-15)\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        try{
            lastDay = LocalDate.parse(Application.input.nextLine());
        }
        catch(Exception e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
            makeLeaveRequest(leaveRequest);
        }
        System.out.println("|| \u001B[43m"+"Please enter the type of your leave request:\u001B[0m\t||\n" +"||  \t\t\t\t\t\t\t||");
        try{
            leaveType = LeaveRequest.LeaveType.valueOf(Application.input.nextLine());
        }
        catch(Exception e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
            makeLeaveRequest(leaveRequest);
        }
        System.out.println("==========================================================");

        System.out.println("");
        
        try {
            LeaveRequest newleaverequest = new LeaveRequest(currentEmployee, LeaveReason, approval, Period.between(firstDay, lastDay), leaveType);
            Application.leaveRequestDataHandler.add(newleaverequest);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to LeaveRequest.txt");
            e.printStackTrace();
        }
        System.out.println("Return to menu? (y/n)");
        try{
            String choice = Application.input.nextLine();
            if(choice.equals("y")){
                try{
                    startModule();
                }
                catch(IOException e){
                    System.out.println("An error occurred while writing to LeaveRequest.txt");
                    e.printStackTrace();
                }
            }
            else if(choice.equals("n")){
                System.out.println("Returning to menu...");
                makeLeaveRequest(leaveRequest);}
            else{
                System.out.println("Invalid choice, please try again.");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
        }
    }

    public void manageRequests() throws IOException{
        Request request = null;
        System.out.println("==========================================================");
        System.out.println("|| \u001B[43m"+"These are your open requests:\u001B[0m\t\t\t||\n");
        viewRequests();
        System.out.println("|| \u001B[43m"+"Please enter the number of the request you want to manage:\u001B[0m||\n");
        try{
            int ID = Application.input.nextInt();
            request = Application.requestDataHandler.get(ID);
            System.out.println("==========================================================");
            manageRequest(request);
        }
        catch(Exception e){
            System.out.println("Invalid choice, please try again.");
            Application.input.nextLine();
            manageRequests();
        }
        System.out.println("==========================================================");
        manageRequest(request);
        
    }

    public void manageRequest(Request request) throws IOException{
        System.out.println("|| \u001B[43m"+"Please Choose what you want to do with the request:\u001B[0m\t||\n|| 1)\u001B[35m\tChange the reason.\u001B[0m\t\t\t\t||\n" +"|| 2)\u001B[35m\tReturn to menu.\u001B[0m\t\t\t\t||\n");
        int choice = Application.input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("|| \u001B[43m"+"Please enter the new reason for your request:\u001B[0m\t\t||\n" +"||  \t\t\t\t\t\t\t||");
                try{
                    String reason = Application.input.nextLine();
                    request.setReason(reason);
                    System.out.println("==========================================================");
                    System.out.println("");
                    System.out.println("|| \u001B[43m"+"Your request has been updated: (enter \"ok\" to return to home page)\u001B[0m\t\t\t||\n");
                    System.out.println(request.toString());
                    System.out.println("----------------------------------------------------------");
                    System.out.println("==========================================================");
                    String ok = Application.input.nextLine();
                    if(ok.equals("ok")){
                        startModule();
                    }
                    else{
                        System.out.println("Invalid choice, please try again.");
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid choice, please try again.");
                    Application.input.nextLine();
                    manageRequest(request);
                }
                break;

            case 2:
            try{
                startModule();
            }
            catch(IOException e){
                System.out.println("An error occurred while writing to request.txt");
                e.printStackTrace();
            }
                break;

            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }
    }
}
