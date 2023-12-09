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
		// TODO
		String username = "";
		String pass = "";

		while (true) {
			System.out.print("Enter username: ");
			username = input.next();
			if (username.equals("")) {
				System.out.println("Username can not be empty!");
				continue;
			}

			System.out.print("\nEnter password: ");
			pass = input.next();
			if (pass.equals("")) {
				System.out.println("Password can not be empty!");
				continue;
			}
			break;
		}
		//for(int i =0 ; i < userDataHandler)

	}
	

	public static void startModule() {
		// TODO
	}

	public static void main(String[] args) {
		// TODO: start the project lol
		initializeData();

		// menu
		input = new Scanner(System.in);

		int choice;
		boolean exit = false;
		while (!exit) {
			System.out.println("Task Managment System");
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