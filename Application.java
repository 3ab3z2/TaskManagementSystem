import java.io.IOException;
import java.util.Scanner;

public class Application
{
	public static DataHandler<User> userDataHandler;
	public static DataHandler<Employee> employeeDataHandler;
	public static DataHandler<Task> taskDataHandler;
	public static DataHandler<LeaveRequest> leaveRequestDataHandler;
	public static DataHandler<EmpType> empTypeDataHandler;
	//DataHandler<TaskPhase> taskPhaseDataHandler;
	public static DataHandler<Request> requestDataHandler;
	public static DataHandler<TimeCard> timeCardDataHandler;
	public static DataHandler<TaskLog> taskLogDataHandler;
	public static DataHandler<Project> projectDataHandler;
	public static Module currentModule;
	public static User user;
	static Scanner input = new Scanner(System.in);

	public static void initializeData()
	{
		//TODO
		try
		{
			//TODO: what to put in the generic part paramater in the following methods
			userDataHandler = new DataHandler<User>("files/User.txt", null);
			employeeDataHandler = new DataHandler<Employee>("files/Employee.txt", null);
			taskDataHandler = new DataHandler<Task>("files/Task.txt", null);
			leaveRequestDataHandler = new DataHandler<LeaveRequest>("files/LeaveRequest.txt", null);
			empTypeDataHandler = new DataHandler<EmpType>("files/EmpType.txt", null);
			requestDataHandler = new DataHandler<Request>("files/Request.txt", null);
			timeCardDataHandler = new DataHandler<TimeCard>("files/TimeCard.txt", null);
			taskLogDataHandler = new DataHandler<TaskLog>("files/TaskLog.txt", null);
			projectDataHandler = new DataHandler<Project>("files/Project.txt", null);
		}
		catch(Exception ex)
		{
			System.out.println("Exception:" + ex.getMessage());
		}
	}
	public static void menu()
	{
		int choice;
		do
		{
			System.out.println("Task Managment System");
			System.out.println("1-login\n2-exit");
			choice = input.nextInt();
			switch(choice) 
			{
			case 1:
				login();
				break;
			case 2:
				break;
			default:
				System.out.println("\nWrong Choice!\nChoose again!\n");
				break;
			}
			
		} while(choice!=2);
	}

	public static void login()
	{
		//TODO
		System.out.print("Enter username: ");
		String uname = input.nextLine();
		System.out.println("\nEnter password: ");
		String upass = input.nextLine();
		System.out.println("");


	}
	public static void startModule()
	{
		//TODO
	}
	public static void main(String[] args)
	{
		//TODO: start the project lol
		menu();
	}
}