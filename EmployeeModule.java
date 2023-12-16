import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.InputMismatchException;

public class EmployeeModule extends Module {
    private Employee currentEmployee;

    public EmployeeModule(Employee currentEmployee) throws IOException{
        this.currentEmployee = currentEmployee;
        this.currentUser = currentEmployee;
    }

    @Override
    public void startModule() throws IOException{
        int choice=0;
        boolean exit = false;

        System.out.print("\033[H\033[2J"); System.out.flush();
        
        while(!exit){
            do {
				if (currentEmployee.getEmpType().isManager()) {
					System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
							"║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
							"║▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░Employee Module░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
							"║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n" +
							"║╒═════════╝                                                              ╚══════════╕║ █\n" +
							"║│                                  Hi " + currentEmployee.getUsername());
					for(int i = 0; i < 46 - currentEmployee.getUsername().length(); i++){
						System.out.print(" ");
					};
					System.out.print("│║ █\n");
					System.out.println( 
							"║│                                                                                   │║ █\n" +
							"║╘═══════════════════════════════════════════════════════════════════════════════════╛║ █\n" +
							"║                     🯇 Please choose one of the following options:🯈                  ║ █\n" +
							"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ █\n" +
							"║│1)│View time cards.                                                                │║ █\n" +
							"║│2)│Create a time card.                                                             │║ █\n" +
							"║│3)│Manage requests.                                                                │║ █\n" +
							"║│4)│Logout.                                                                         │║ █\n" +
							"╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n" +
							" ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");
					try{
						choice = Application.inputInt("Your choice:🮶 ");
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
							manageRequests();
							break;

						case 4:
							System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
							"\u001B[41m"+"║🮲🮳 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Logging Out 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
							"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
							exit = true;
							break;

						default:
						System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
						"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
						"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
							break;
					}
				} else {
					System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
							"║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
							"║▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░Employee Module░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
							"║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n" +
							"║╒═════════╝                                                              ╚══════════╕║ █\n" +
							"║│                                  Hi " + currentEmployee.getUsername());
					for(int i = 0; i < 46 - currentEmployee.getUsername().length(); i++){
						System.out.print(" ");
					};
					System.out.print("│║ █\n");
					System.out.println( 
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
							"║│7)│Logout.                                                                         │║ █\n" +
							"╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n" +
							" ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");
					try{
						choice = Application.inputInt("Your choice:🮶 ");
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
							System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
							"\u001B[41m"+"║🮲🮳 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Logging Out 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
							"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
							exit = true;
							break;

						default:
						System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
						"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
						"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
							break;
					}
				}
            } while (choice != 7);
        }
    }

    public void viewTimeCards() throws IOException{
        boolean exit = false;
        System.out.print("\033[H\033[2J"); System.out.flush();
        
        while(!exit){
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                           These are your time cards                                 ║\n" +
                            "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                            "║│                                                                                   │║");
            for(int i = 0; i < Application.timeCardDataHandler.getLength(); i++){
                Employee employee = Application.timeCardDataHandler.get(i).getEmployee();
                if(currentEmployee.getUsername().equals(employee.getUsername())){
                    System.out.print("║│ "+Application.timeCardDataHandler.get(i));
                    for(int j = 0; j < 75 - Application.timeCardDataHandler.get(i).toString().length(); j++){
                        System.out.print(" ");
                    };
                    System.out.println("│║");
                    System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
                }
            }
            System.out.println("╚╧═══════════════════════════════════════════════════════════════════════════════════╧╝");
            
            System.out.println("\u001B[42m" +       
                        "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
            "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
            "\n"+
            "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            try{
                String choice = Application.input.next();
                if(choice.equals("y")){
                    exit = true;
                    break;
                }
                else if(choice.equals("n")){
                    System.out.println("\u001B[42m" +       
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
                    "\n"+
                    "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    continue;
                }
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
    }

    public void createTimeCard() throws IOException{
        LocalTime departure = LocalTime.now();
        LocalDateTime attendance = LocalDateTime.now();
        boolean exit = false;

        System.out.print("\033[H\033[2J"); System.out.flush();

        while(!exit){
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
                continue;
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
                continue;
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
                exit = true;
                break;
            }
            else if(choice.equals("n")){
            System.out.println("\u001B[42m" +       
                        "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
            "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
            "\n"+
            "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                continue;
            }
            else{
                    System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                    "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            }
        }
    }

    public void viewRequests() throws IOException{
        boolean exit = false;

        System.out.print("\033[H\033[2J"); System.out.flush();

        while(!exit){
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                           These are your opened requests                            ║\n" +
                            "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                            "║│                                                                                   │║");
            for(int i = 0 , j = 0; i < currentEmployee.getPermissionRequests().size(); i++, j++){
                    System.out.println("|| "+(j+1) +") " + currentEmployee.getPermissionRequests().get(i).getReason()+ "  " + "Approval: " + currentEmployee.getPermissionRequests().get(i).getApproval());
                    j++;
                    System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
            };
            System.out.println("╚╧═══════════════════════════════════════════════════════════════════════════════════╧╝");

            System.out.println("\u001B[42m" +       
                        "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
            "\u001B[42m"+"║                                 Return to menu?🮴(y/n)                               ║"+"\u001B[0m" +
            "\n"+
            "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
            try{
                String choice = Application.input.next();
                if(choice.equals("y")){
                    exit = true;
                    break;
                }
                else if(choice.equals("n")){
                    System.out.println("\u001B[42m" +       
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
                    "\n"+
                    "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    continue;
                }
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
    }

    public void makeRequest(Request request) throws IOException{
        boolean exit = false;

        System.out.print("\033[H\033[2J"); System.out.flush();

        while(!exit){
            Request.Approval approval = Request.Approval.pending;
                    System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                          🯇 Please Enter the reason for the request                  ║\n" +
                            "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                            "║│A string. Example: 'Can I Hang DedSec's logo on my wall?'                          │║\n" +
                            "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                            "║ Reason: ");
                            Application.input.nextLine();
            String reason = Application.input.nextLine();
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");

            System.out.println("");
            try {
                Request newrequest = new Request(currentEmployee, reason, approval);
                currentEmployee.getPermissionRequests().add(newrequest);
                Application.requestDataHandler.add(newrequest);
                Application.employeeDataHandler.update(Application.employeeDataHandler.getIndex(currentEmployee), currentEmployee);
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
                    exit = true;
                    break;
                }
                else if(choice.equals("n")){
                    System.out.println("\u001B[42m" +       
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
                    "\n"+
                    "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    continue;
                }
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
    }

    public void makeLeaveRequest(LeaveRequest leaveRequest) throws IOException{
        Request.Approval approval = null;
        LocalDate lastDay=null;
        LocalDate firstDay=null;
        LeaveRequest.LeaveType leaveType=null;
        boolean exit = false;

        System.out.print("\033[H\033[2J"); System.out.flush();

        while(!exit){
            System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                          🯇 Please Enter a reason for the leave request              ║\n" +
                            "║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
                            "║│A string. Example: 'Forgot the baby in the microwave'                              │║\n" +
                            "╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
                            "║ Reason: ");
                            Application.input.nextLine();
            String LeaveReason = Application.input.nextLine();
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
                continue;
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
                continue;
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
                continue;
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
                    exit = true;
                    break;
                }
                else if(choice.equals("n")){
                    System.out.println("\u001B[42m" +       
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[42m"+"║                                 Returning to menu🮴                                  ║"+"\u001B[0m" +
                    "\n"+
                    "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    continue;
                }
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
    }

    public void manageRequests() throws IOException{
        Request request = null;
        boolean exit = false;


        while(!exit){
			if(currentEmployee.getEmpType().isManager()){
				System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
								"║                            These are the opened requests                            ║\n" +
								"║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
								"║│                                                                                   │║");
				for(int i = 0; i < Application.requestDataHandler.getLength(); i++){
						System.out.println("║│ "+(i+1) +") " + Application.requestDataHandler.get(i).toString());
						System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
					}
				System.out.println("╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
				System.out.println("║ Please enter the ID of the request you want to manage: (0 to exit)                  ║");
				try{
					int ID = Application.inputInt("║ ID: ")-1;
					if(ID == -1){
						exit = true;
						break;
					}
					else{
						request = Application.requestDataHandler.get(ID);
						System.out.println("╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
						System.out.print(	 "║ Please enter the approval of the request: (pending, rejected, approved)             ║\n" +
											 "╠═════════════════════════════════════════════════════════════════════════════════════╣" +
											 "║ Approval: ");
						Request.Approval approval = Request.Approval.valueOf(Application.input.next());
						for(int i = 0; i < 74 - approval.toString().length(); i++){
							System.out.print(" ");

						}
						System.out.println("║");
						request.setApproval(approval);
						Application.requestDataHandler.update(Application.requestDataHandler.getIndex(request), request);
						System.out.println("║ Request updated successfully!             (enter to exit)                           ║");
						System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");
						Application.input.nextLine();
					}
				}
				catch(Exception e){
						System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
						"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
						"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
					continue;
				}
			}
			else{
				System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
								"║                           These are your opened requests                            ║\n" +
								"║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
								"║│                                                                                   │║");
				for(int i = 0 , j = 0; i < Application.requestDataHandler.getLength(); i++, j++){
					Employee employee = Application.requestDataHandler.get(i).getEmployee();
					if (currentEmployee.getUsername().equals(employee.getUsername())) {
						System.out.println("║│ "+(j+1) +") " + Application.requestDataHandler.get(i).toString());
						j++;
						System.out.println("║╞═══════════════════════════════════════════════════════════════════════════════════╡║");
					}
				};
				System.out.println("╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣");
				System.out.println("║ Please enter the ID of the request you want to manage: (0 to exit)                  ║");
				try{
					System.out.print("║ ID: ");
					int ID = Application.input.nextInt()-1;
					if(ID == -1){
						exit = true;
						break;
					}
					else{
						request = Application.requestDataHandler.get(ID);
						manageRequest(request);
					}
				}
				catch(Exception e){
						System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
						"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
						"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
					continue;
				}
			}
        }       
    }

    public void manageRequest(Request request) throws IOException{
        boolean exit = false;


        while(!exit){
            System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                     🯇 Please choose one of the following options:🯈                  ║ \n" +
                            "║╭╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╮║ \n" +
                            "║│                         " + request.toString() + "                                │║ \n" +
                            "║╰╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╴╯║ \n" +
                            "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
                            "║│1)│Change the reason of the request.                                               │║ \n" +
                            "║│2)│Return to menu.                                                                 │║ \n" +
                            "╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣ \n");
            int choice = Application.inputInt("║ Please enter your choice: ");
			for(int i = 0; i < 59 - String.valueOf(choice).length(); i++){
				System.out.print(" ");
			}
			System.out.println("║");
            switch (choice) {
                case 1:
                    System.out.print("║ Please enter the new reason for your request: ");
                    try{
                        Application.input.nextLine();
                        String reason = Application.input.nextLine();
                        request.setReason(reason);
                    System.out.println("\u001B[42m" +       
                                "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[42m"+"║                                 Reason changed successfully!                        ║"+"\u001B[0m" +
                    "\n"+
                    "\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    }
                    catch(Exception e){
                    System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                    "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                        Application.input.next();
                        continue;
                    }
                    break;

                case 2:
                    exit = true;
                    break;

                default:
                    System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
                    "\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
                    "\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
                    break;
            }
        }
    }
}
