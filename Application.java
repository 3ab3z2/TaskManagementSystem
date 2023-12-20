import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		leaveRequestDataHandler = new DataHandler<LeaveRequest>("files/LeaveRequest.txt",
				new LeaveRequest(null, null, null, null, null));
		employeeDataHandler = new DataHandler<Employee>("files/Employee.txt", new Employee(null, null, null, null));
		timeCardDataHandler = new DataHandler<TimeCard>("files/TimeCard.txt", new TimeCard(null, null, null));
		projectDataHandler = new DataHandler<Project>("files/Project.txt", new Project(null, null, null, null, null));
		taskDataHandler = new DataHandler<Task>("files/Task.txt",
				new Task(null, null, null, null, null, null, null, null, null, null, 0));
		taskLogDataHandler = new DataHandler<TaskLog>("files/TaskLog.txt", new TaskLog(null, null, null, null));

	}

	public static void login() throws IOException {
		String username = "";
		String pass = "";

		System.out.print("\033[H\033[2J");
		System.out.flush();

		// credentials input
		username = inputString(
			// prompt
			"╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
			"║                     🯇 Please Enter the Username and password🯈   	              ║\n" +
			"║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
			"║│🯅 🯆 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🮲🮳 🯇 🯉 🯉 🯉 🯉 🯉 🯉 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 │║\n"+
			"╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
			"║ Username: ",
			// err message
			"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n"+
			"\u001B[41m║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Username cannot be empty 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀         ║\u001B[0m\n"+
			"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n"
		);
		for(int i = 0; i < 74 - username.length(); i++){
			System.out.print(" ");
		}
		System.out.print("║\n");
		if (username.equals("exit"))
			return;

		pass = inputString(
			// prompt
			"║ Password: ",
			// err message
			"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n"+
			"\u001B[41m║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Passwords cannot be empty 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀        ║\u001B[0m\n"+
			"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n"
		);
		for(int i = 0; i < 74 - pass.length(); i++){
			System.out.print(" ");
		}
		System.out.print("║\n");
		System.out.print("╚═════════════════════════════════════════════════════════════════════════════════════╝\n");
		if (pass.equals("exit"))
			return;

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
		{
			System.out.println(printInABoxError("Username or password are wrong!",85));
			input.nextLine();
		}
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
					System.out.print("\033[H\033[2J"); System.out.flush();
					choice = inputInt(printInABox("🯇 Please choose one of the following options:🯈","1)│Employee Module.\n2)│Task Module.\n0)│Sign Out.",3,85,true)+
									"║ Please enter your choice: ");
					for(int i = 0; i < 58 - String.valueOf(choice).length(); i++){
						System.out.print(" ");
					}
					System.out.print("║\n");
					switch (choice) {
						case 1:
							currentModule = new EmployeeModule(employee);
							currentModule.startModule();
							break;
						case 2:

							System.out.print(
											"╔═════════════════════════════════════════════════════════════════════════════════════╗\n"+
											"║                     🯇 Please choose one of the following options:🯈                  ║ \n"+
											"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n"+
											"║│0)│Go back.                                                                        │║ \n");
							for (int i = 0; i < projectDataHandler.getLength(); i++) {
								System.out.print("║│" + (i + 1) + ")│Project:"
										+ projectDataHandler.get(i).getName());
								for(int j = 0; j < 72 - projectDataHandler.get(i).getName().length(); j++){
									System.out.print(" ");
								}
								System.out.print("│║\n");
							}
							choice = inputInt(
									"╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣\n"
											+
											"║ Please enter your choice: ");
							for(int i = 0; i < 58 - String.valueOf(choice).length(); i++){
								System.out.print(" ");
							}
							System.out.print("║\n");
							if (choice == 0)
							{
								System.out.println("\u001B[42m" +       
											"╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m" +
								"\u001B[42m"+"║                                 Returning back🮴                                    ║"+"\u001B[0m" +
								"\n"+
								"\u001B[42m"+"╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
								break;
							}
							else if (choice > projectDataHandler.getLength() || choice < 1)
								System.out.println("\u001B[41m"
										+ "╠═════════════════════════════════════════════════════════════════════════════════════╣\n\u001B[0m"
										+
										"\u001B[41m"
										+ "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Chosen project is not found! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
										+ "\u001B[0m" +
										"\n" + "\u001B[41m"
										+ "╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
							else {
								currentModule = new TaskModule(employee, projectDataHandler.get(choice - 1));
								currentModule.startModule();
							}
							break;
						case 0:
							exit = true;
							break;
						default:
							System.out.println(printInABoxError("Invalid Choice, please try again", 85));
							break;

					}
				}
			}

		}

	}

	public static void main(String[] args) {
		try {
			initializeData();

			// menu
			input = new Scanner(System.in);

			int choice;
			boolean exit = false;

			System.out.print("\033[H\033[2J");System.out.flush();
			
			while (!exit) {
				System.out.println(printInABoxMain("Task Management System","A task management system made in Java for the PL2 course at Helwan University.\nThis project made by: 3ab3z, Omar Atya, Omar Wagih, Ali, Mohammed, Tarek.","🯇 Please choose one of the following options:🯈","1)│Sign in.\n2)│Exit.",3,85,false,10,20));

				choice = inputInt("Input:🮶  ");
				switch (choice) {
					case 1:
						login();
						break;
					case 2:
						System.out.print("\033[H\033[2J");System.out.flush();
						System.out.print(printInABoxGreen("Thank you for using this program!",85));
						exit = true;
						break;
					default:
						System.out.println(printInABoxError("Invalid Choice, please try again",85));
						break;
				}
			}
		} catch (IOException ex) {
			System.out.println("Fatal Error:" + ex.getMessage());
		}
	}

	public static int inputInt(String print){
		return inputInt(print, printInABoxError("Input must be an integer!",85));
	}

	public static int inputIntln(String print) {
		return inputInt(print + "\n");
	}

	public static int inputIntln(String print, String error) {
		return inputInt(print + "\n", error + "\n");
	}

	public static int inputInt(String print, String error) {
		int value;
		while (true) {
			try {
				System.out.print(print);
				value = Integer.parseInt(input.nextLine().trim());
				break;
			} catch (NumberFormatException ex) {
				System.err.print(error);
				continue;
			}
		}
		return value;
	}

	public static String inputString(String prompt) {
		return inputString(prompt, printInABoxError("Input must not be empty!",85));
	}

	// Waits for the user to acknowledge message
	public static String inputString(String prompt, String isBlank_message) {
		String value;
		while (true) {
			System.out.print(prompt);
			value = input.nextLine().trim();
			if (value.isBlank()) {
				System.err.print(isBlank_message);
				continue;
			}
			break;
		}
		return value;
	}

	public static String inputStringln(String prompt) {
		return inputString(prompt + "\n");
	}

	public static String inputStringln(String prompt, String isBlank_message) {
		return inputString(prompt + "\n", isBlank_message + "\n");
	}

	public static String inputStringOneWord(String prompt) {
		return inputString(prompt).split(" ")[0];
	}

	public static String inputStringOneWord(String prompt, String isBlank_message) {
		return inputString(prompt, isBlank_message).split(" ")[0];
	}

	public static String inputStringOneWordln(String prompt) {
		return inputStringln(prompt).split(" ")[0];
	}

	public static String inputStringOneWordln(String prompt, String isBlank_message){
		return inputStringln(prompt, isBlank_message).split(" ")[0];
	}

	public static double inputDouble(String print, String error) {
		double value;
		while (true) {
			try {
				System.out.print(print);
				value = Double.parseDouble(input.nextLine().trim());
				break;
			} catch (NumberFormatException ex) {
				System.err.print(error);
				continue;
			}
		}
		return value;
	}

	public static double inputDouble(String print){
		return inputDouble(print, printInABoxError("Input must be an number!",85));
	}

	public static double inputDoubleln(String print) {
		return inputDouble(print + "\n");
	}

	public static double inputDoubleln(String print, String error) {
		return inputDouble(print + "\n", error + "\n");
	}

	public static String printInABox(String title,String content,int gutterOffset,int width,boolean tailed){
		StringBuilder value=new StringBuilder();
		value.append("╔");
		for(int i = 0;i<width;i++){
			value.append("═");
		}
		value.append("╗\n");
		value.append("║");
		int space = width-title.length();
		for(int i = 0;i<space/2;i++){
			value.append(" ");
		}
		value.append(title);
		for(int i = 0;i<(space/2)+(space%2);i++){
			value.append(" ");
		}
		value.append("║\n");
		value.append("║╒");
		for(int i = 1;i<width-1;i++){
			value.append(i==gutterOffset?"╤":"═");
		}
		value.append("╕║\n");
		for(String ln : content.split("\n")){
			value.append("║│");
			int spaceIn = width-ln.length()-2;
			value.append(ln);
			for(int i = 0;i<spaceIn;i++){
				value.append(" ");
			}
			value.append("│║\n");
		}
		value.append(tailed?"╠╧":"╚╧");
		for(int i = 1;i<width-1;i++){
			value.append(i==gutterOffset?"╧":"═");
		}
		value.append(tailed?"╧╣\n":"╧╝\n");
		return value.toString();
	}
	public static String printInABox(String title,ArrayList<String> content,int gutterOffset,int width,boolean tailed){
		StringBuilder value=new StringBuilder();
		for(int i=0;i<content.size();i++){
			value.append(content.get(i)+"\n");
		}
		return printInABox(title, value.toString(), gutterOffset, width, tailed);
	}
	public static String printInABox(String title,HashMap<Integer,String> content,int gutterOffset,int width,boolean tailed){
		StringBuilder value=new StringBuilder();
		for(Integer key:content.keySet()){
			if(key==0) continue;
			String keystr=key.toString();
			for(int i=keystr.length();i<gutterOffset-2;i++){
				keystr=" "+keystr;
			}
			value.append(keystr+")│"+content.get(key)+"\n");
		}
		if(content.containsKey(0)){
			Integer key=0;
			String keystr=key.toString();
			for(int i=keystr.length();i<gutterOffset-2;i++){
				keystr=" "+keystr;
			}
			value.append(keystr+")│"+content.get(key)+"\n");
		}
		return printInABox(title, value.toString(), gutterOffset, width, tailed);
	}
	public static String printInABoxError(String title,int width,boolean tailed){
		StringBuilder value=new StringBuilder();
		value.append("\23341m"+(tailed?"╠":"╔"));
		for(int i = 0;i<width;i++){
			value.append("═");
		}
		value.append((tailed?"╣":"╗")+"\2330m\n");
		value.append("\23341m║");
		int space = width-title.length()-2;
		value.append(space%2==1?" ":"");
		for(int i = 0;i<space/4;i++){
			value.append("🯀 ");
		}
		value.append(" "+title+" ");
		for(int i = 0;i<(space/4)+(space%4)/2;i++){
			value.append("🯀 ");
		}
		value.append("║\2330m\n");
		value.append("\23341m"+(tailed?"╠":"╚"));
		for(int i = 0;i<width;i++){
			value.append("═");
		}
		value.append((tailed?"╣":"╝")+"\2330m\n");
		return value.toString();
	}
	public static String printInABoxError(String title,int width){
		return printInABoxError(title,width,false);
	}
	public static String printInABoxGreen(String title,int width){
		StringBuilder value=new StringBuilder();
		value.append("\23342m╔");
		for(int i = 0;i<width;i++){
			value.append("═");
		}
		value.append("╗\2330m\n");
		value.append("\23342m║\23330m");
		int space = width-title.length();
		for(int i = 0;i<space/2;i++){
			value.append(" ");
		}
		value.append(title);
		for(int i = 0;i<(space/2)+(space%2);i++){
			value.append(" ");
		}
		value.append("\2330m\23342m║\2330m\n");
		value.append("\23342m╚");
		for(int i = 0;i<width;i++){
			value.append("═");
		}
		value.append("╝\2330m\n");
		return value.toString();
	}
	public static String printInABoxMain(String title,String description,String subtitle,String content,int gutterOffset,int width,boolean tailed,int firstMargin,int secondMargin){
		StringBuilder value=new StringBuilder();
		value.append("╔");
		for(int i = 0;i<width;i++){
			value.append("═");
		}
		value.append("╗\n");
		value.append("║");
		for(int i = 0;i<width;i++){
			value.append("▓");
		}
		value.append("║ █\n");

		value.append("║");
		int space_title = width-title.length();
		for(int i = 0;i<space_title/2;i++){
			if(i<firstMargin)
				value.append("▓");
			else if(i<secondMargin)
				value.append("▒");
			else
				value.append("░");
		}
		value.append(title);
		for(int i = 0;i<(space_title/2)+(space_title%2);i++){
			if(((space_title/2)+(space_title%2))-i<firstMargin)
				value.append("▓");
			else if(((space_title/2)+(space_title%2))-i<secondMargin)
				value.append("▒");
			else
				value.append("░");
		}
		value.append("║ █\n");

		value.append("║");
		for(int i = 0;i<width;i++){
			if(i<firstMargin-1)
				value.append("▓");
			else if(i==(firstMargin-1))
				value.append("🮜");
			else if(i==firstMargin)
				value.append("╔");
			else if(width-i<firstMargin-1)
				value.append("▓");
			else if(width-i>firstMargin&&i>firstMargin)
				value.append("═");
			else if(width-i==firstMargin-1)
				value.append("🮝");
			else if(width-i==firstMargin)
				value.append("╗");
			else
				value.append(" ");
		}
		value.append("║ █\n");

		value.append("║╒");
		for(int i = 1;i<width-1;i++){
			if(i<firstMargin)
				value.append("═");
			else if(i==firstMargin)
				value.append("╝");
			else if(width-i<firstMargin)
				value.append("═");
			else if(width-i==firstMargin)
				value.append("╚");
			else
				value.append(" ");
		}
		value.append("╕║ █\n");
		for(String ln : description.split("\n")){
			value.append("║│ ");
			int spaceIn = width-ln.length()-3;
			value.append(ln);
			for(int i = 0;i<spaceIn;i++){
				value.append(" ");
			}
			value.append("│║ █\n");
		}
		value.append("║╘");
		for(int i = 1;i<width-1;i++){
			value.append("═");
		}
		value.append("╛║ █\n");

		value.append("║  ");
		int space_sub = width-subtitle.length();
		for(int i = 0;i<space_sub/2;i++){
			value.append(" ");
		}
		value.append(subtitle);
		for(int i = 0;i<(space_sub/2)+(space_sub%2);i++){
			value.append(" ");
		}
		value.append("║ █\n");
		value.append("║╒");
		for(int i = 1;i<width-1;i++){
			value.append(i==gutterOffset?"╤":"═");
		}
		value.append("╕║ █\n");
		for(String ln : content.split("\n")){
			value.append("║│");
			int spaceIn = width-ln.length()-2;
			value.append(ln);
			for(int i = 0;i<spaceIn;i++){
				value.append(" ");
			}
			value.append("│║ █\n");
		}
		value.append(tailed?"╠╧":"╚╧");
		for(int i = 1;i<width-1;i++){
			value.append(i==gutterOffset?"╧":"═");
		}
		value.append(tailed?"╧╣ █\n":"╧╝ █\n");
		value.append(" █");
		for(int i = 0;i<width;i++){
			value.append("█");
		}
		value.append("██\n");
		return value.toString();
	}
}