import java.io.IOException;
import java.util.Scanner;

public class Application {
	public static DataHandler<User> userDataHandler;
	public static DataHandler<Employee> employeeDataHandler;
	public static DataHandler<Task> taskDataHandler;
	public static DataHandler<LeaveRequest> leaveRequestDataHandler;
	public static DataHandler<EmpType> empTypeDataHandler;
	public static DataHandler<Request> requestDataHandler;
	public static DataHandler<TimeCard> timeCardDataHandler;
	public static DataHandler<TaskLog> taskLogDataHandler;
	public static DataHandler<Project> projectDataHandler;
	public static Module currentModule;
	public static User user;
	public static Scanner input;

	public static void initializeData() throws IOException {

		empTypeDataHandler = new DataHandler<EmpType>("files/EmpType.txt", new EmpType(null, false));
		userDataHandler = new DataHandler<User>("files/User.txt", new User(null, null, null));
		requestDataHandler = new DataHandler<Request>("files/Request.txt", new Request(null, null, null));
		leaveRequestDataHandler = new DataHandler<LeaveRequest>("files/LeaveRequest.txt", new LeaveRequest(null, null, null, null, null));
		employeeDataHandler = new DataHandler<Employee>("files/Employee.txt", new Employee(null, null, null, null));
		timeCardDataHandler = new DataHandler<TimeCard>("files/TimeCard.txt", new TimeCard(null, null, null));
		projectDataHandler = new DataHandler<Project>("files/Project.txt", new Project(null, null, null, null, null));
		taskDataHandler = new DataHandler<Task>("files/Task.txt", new Task(null, null, null, null, null, null, null, null, null, null, 0));
		taskLogDataHandler = new DataHandler<TaskLog>("files/TaskLog.txt", new TaskLog(null, null, null, null));

	}

	public static void login() throws IOException {
		String username = "";
		String pass = "";

		// credentials input
		while (true) {    
			System.out.print(
				"╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
				"║                     🯇 Please Enter the Username and password🯈   	              ║\n" +
				"║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
				"║│🯅 🯆 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🮲🮳 🯇 🯉 🯉 🯉 🯉 🯉 🯉 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 │║\n" +
				"╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
				"║ Username: ");
			username = input.next();
			// delete the if statements (idk)
			if (username.equals("")) {System.out.println("\u001B[41m" + "╠═════════════════════════════════════════════════════════════════════════════════════╣\n\u001B[0m" +
			"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Usernames cannot be empty my bro! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
			"\n"+"\u001B[41m"+"╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
				continue;
			}

			System.out.print("\n║ Password: ");
			pass = input.next();
			// delete the if statements (idk)
			if (pass.equals("")) {	System.out.println("\u001B[41m" + "╠═════════════════════════════════════════════════════════════════════════════════════╣\n\u001B[0m" +
			"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Passwords cannot be empty my bro! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
			"\n"+"\u001B[41m"+"╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
				continue;
			}
			break;
		}

		// check user's credentials
		boolean foundUser = false;
		for (int i = 0; i < userDataHandler.getLength(); i++) {
			if (userDataHandler.get(i).canlogin(username, pass)) {
				user = userDataHandler.get(i);
				startModule();
				foundUser = true;
				break;
			}
		}
		if (!foundUser)
			System.out.println("\u001B[41m" + "╠═════════════════════════════════════════════════════════════════════════════════════╣\n\u001B[0m" +
			"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Username or password are wrong! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
			"\n"+"\u001B[41m"+"╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
	}

	public static void startModule() throws IOException {
		// admin module path
		if (user.getUserType() == User.utype.admin) {
			currentModule = new AdminModule(user);
			currentModule.startModule();
		} else {
			Employee employee = null;
			int index = -1;
			for (int i = 0; i < employeeDataHandler.getLength(); i++) {
				if (employeeDataHandler.get(i).canlogin(user.getUsername(), user.getPassword())) {
					employee = employeeDataHandler.get(i);
					user = employee;
					index = employeeDataHandler.getIndex(employee);
					break;
				}
			}
			if (employee != null) {
				int choice;
				boolean exit = false;
				while (!exit) {
					System.out.print("╠═════════════════════════════════════════════════════════════════════════════════════╣\n" +
									   "║                     🯇 Please choose one of the following options:🯈                  ║ \n" +
									   "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
									   "║│1)│Employee Module.                                                                │║ \n" +
									   "║│2)│Task Module.                                                                    │║ \n" +
									   "║│3)│Go back.                                                                        │║ \n" +
									   "╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣ \n" +
									   "║ Please enter your choice: ");
					choice = input.nextInt();

					switch (choice) {
						case 1:
							currentModule = new EmployeeModule(employee);
							currentModule.startModule();
							break;
						case 2:
							boolean projexit = false;
							while (!projexit) {
								Project[] projectArr = new Project[projectDataHandler.getLength()];
								
								System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
								 "║                     🯇 Please choose one of the following options:🯈                  ║ \n" +
								 "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
								 "║│1)│Choose Project.                                                                 │║ \n" +
								 "║│2)│Go back.                                                                        │║ \n" +
								 "╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣ \n" +
								 "║ Please enter your choice: ");
								int choicep = input.nextInt();
								switch (choicep) {
									case 1:
									for (int i = 0, j = 0; i < projectDataHandler.getLength(); i++)
										{
											
											
											if(projectDataHandler.get(i).getLeader().toString().equals(employee.toString()))
											{
												projectArr[j] = projectDataHandler.get(i);
												System.out.println((j + 1) + "-Project: " + projectArr[j].getName());
												j++;
											}
										}
								System.out.print("╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
								 "║                     🯇 Please choose one of the following options:🯈                  ║ \n" +
								 "║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n" +
								 "║│1)│Enter the project number.                                                       │║ \n" +
								 "║│2)│Go back.                                                                        │║ \n" +
								 "╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣ \n" +
								 "║ Please enter your choice: ");
								 		choice = input.nextInt();
										if (choice > projectArr.length || choice < 1)
											System.out.println("\u001B[41m" + "╠═════════════════════════════════════════════════════════════════════════════════════╣\n\u001B[0m" +
			"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Chosen project is not found! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
			"\n"+"\u001B[41m"+"╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
										currentModule = new TaskModule(employee, projectArr[choice - 1]);
										currentModule.startModule();
										projexit = true;
										break;
									case 2:
										projexit = true;
										break;
									default:
									System.out.println("\u001B[41m" + "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
									"\u001B[41m"+"║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"+"\u001B[0m" +
									"\n"+"\u001B[41m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
										break;
								}
							}
							break;
						case 3:
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

	}

	public static void main(String[] args) throws IOException {
		try {
			initializeData();
		} catch (IOException ex) {
			System.out.println("Exception:" + ex.getMessage());
		}

		// menu
		input = new Scanner(System.in);

		int choice;
		boolean exit = false;
		while (!exit) {
			System.out.println( "╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
								"║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
								"║▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░Task Management System░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n" +
								"║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n" +
								"║╒═════════╝                                                              ╚══════════╕║ █\n" +
								"║│ A task management system made in Java for the PL2 course at Helwan University.    │║ █\n" +
								"║│ This project made by: 3ab3z, Omar Atya, Omar Wageh, Ali, Mohammed, Tarek.         │║ █\n" +
								"║╘═══════════════════════════════════════════════════════════════════════════════════╛║ █\n" +
								"║                     🯇 Please choose one of the following options:🯈                  ║ █\n" +
								"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ █\n" +
								"║│1)│Sign in.                                                                        │║ █\n" +
								"║│2)│Exit.                                                                           │║ █\n" +
								"╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n" +
								" ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");
			System.out.print("Your choice:🮶 ");
			choice = input.nextInt();
			switch (choice) {
				case 1:
					login();
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