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
			System.out.println("Username or password is incorrect!\n");
	}

	public static void startModule() throws IOException {
		// admin module path
		if (user.getUserType() == User.utype.admin) {
			currentModule = new AdminModule(user);
			currentModule.startModule();
		} else {
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
							currentModule = new EmployeeModule(employee);
							currentModule.startModule();
							break;
						case 2:
							boolean projexit = false;
							while (!projexit) {
								Project[] projectArr = new Project[projectDataHandler.getLength()];
								for (int i = 0, j = 0; i < projectDataHandler.getLength(); i++)
								{
									if(projectDataHandler.get(i).getLeader() == employee)
									{
										projectArr[j] = projectDataHandler.get(i);
										System.out.println((j + 1) + "-Project: " + projectArr[j].getName());
										j++;
									}
								}
								System.out.println("1- choose project\n2-Go back");
								switch (choice) {
									case 1:
										System.out.print("Choice: ");
										choice = input.nextInt();
										if (choice > projectArr.length || choice < 1)
											System.out.println("chosen project doesn't exist");
										currentModule = new TaskModule(employee, projectArr[choice - 1]);
										currentModule.startModule();
										projexit = true;
										break;
									case 2:
										projexit = true;
										break;
									default:
										System.out.println("invalid option please choose again");
										break;
								}
							}
							break;
						case 3:
							exit = true;
							break;
						default:
							System.out.println("invalid option please choose again");
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

		System.out.println("Task Managment System");
		int choice;
		boolean exit = false;
		while (!exit) {
			System.out.println("Please Choose");
			System.out.println("1-login\n2-exit");
			System.out.print("Choice: ");
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