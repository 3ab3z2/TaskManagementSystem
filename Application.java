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

	public static void initializeData() {
		// TODO
		try {
			// TODO: what to put in the generic part paramater in the following methods
			userDataHandler = new DataHandler<User>("files/User.txt", new User(null, null, null));
			employeeDataHandler = new DataHandler<Employee>("files/Employee.txt", new Employee(null, null, null, null));
			taskDataHandler = new DataHandler<Task>("files/Task.txt",
					new Task(null, null, null, null, null, null, null, null, null, null, 0));
			leaveRequestDataHandler = new DataHandler<LeaveRequest>("files/LeaveRequest.txt",
					new LeaveRequest(null, null, null, 0, null));
			empTypeDataHandler = new DataHandler<EmpType>("files/EmpType.txt", new EmpType(null, false));
			requestDataHandler = new DataHandler<Request>("files/Request.txt", new Request(null, null, null));
			timeCardDataHandler = new DataHandler<TimeCard>("files/TimeCard.txt", new TimeCard(null, null, null));
			taskLogDataHandler = new DataHandler<TaskLog>("files/TaskLog.txt", new TaskLog(null, null, null, null));
			projectDataHandler = new DataHandler<Project>("files/Project.txt",
					new Project(null, null, null, null, null));
		} catch (Exception ex) {
			System.out.println("Exception:" + ex.getMessage());
		}
	}

	public static void login() {
		String username = "";
		String pass = "";

		// credentials input
		while (true) {
			System.out.print("Enter username: ");
			username = input.next();
			// delete the if statements (idk)
			if (username.equals("")) {
				System.out.println("Username can not be empty!");
				continue;
			}

			System.out.print("\nEnter password: ");
			pass = input.next();
			// delete the if statements (idk)
			if (pass.equals("")) {
				System.out.println("Password can not be empty!");
				continue;
			}
			break;
		}
		
		//check user's credentials
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
			System.out.println("Username or password is incorrect!\n");
	}

	public static void startModule() {
		//admin module path
		if (user.getUserType() == User.utype.admin) {
			currentModule = new AdminModule(user);
			currentModule.startModule();
		}else {
			Employee employee = null;
			for (int i = 0; i < employeeDataHandler.getLength(); i++) {
				if (employeeDataHandler.get(i).canlogin(user.getUsername(), user.getPassword())) {
					employee = employeeDataHandler.get(i);
					user = employee;
					break;
				}
			}
			if (employee != null) {
				int choice;
				boolean exit = false;
				while (!exit) {
					System.out.println("Please choose the module\n1-Employee Moudle\n2-Tasks Module\n3-Go back");
					System.out.print("Choice: ");
					choice = input.nextInt();

					switch (choice) {
						case 1:
							// abdelaziz
							currentModule = new EmployeeModule(employee);
							currentModule.startModule();
							break;
						case 2:
							
							int projchoice;
							boolean projexit = false;
							while (!projexit) {
								System.out.println("Please choose the project\n0-Go back");
								for (int i = 0; i < projectDataHandler.getLength(); i++) {
									System.out.println((i + 1) + "-Project: " + projectDataHandler.get(i).getName());
								}
								System.out.print("Choice: ");
								projchoice = input.nextInt();
								if (projchoice == 0)
									projexit = true;
								else if (projchoice > projectDataHandler.getLength() || projchoice < 0) 
									System.out.println("\nUnknown Choice!\nTry again!\n");
								else {
									// ali
									currentModule = new TaskModule(employee, projectDataHandler.get(projchoice - 1));
									currentModule.startModule();
									projexit = true;
								}
							}
							break;
						case 3:
							exit = true;
							break;
						default:
							System.out.println("\nUnknown Choice!\nTry again!\n");
							break;

					}
				}
			}

		}
	}
	public static void main(String[] args) {
	
		initializeData();

		// menu
		input = new Scanner(System.in);

		System.out.println("Task Managment System");
		int choice;
		boolean exit = false;
		while (!exit) {
			System.out.println("Please Choose");
			System.out.println("1-login\n2-exit");
			choice = input.nextInt();
			switch (choice) {
				case 1:
					login();
					break;
				case 2:
					exit = true;
					break;
				default:
					System.out.println("\nUnknown Choice!\nTry again!\n");
					break;
			}
		}
	}
}