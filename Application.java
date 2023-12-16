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
						"║                     🯇 Please Enter the Username and password🯈   	                 ║\n" +
						"║╒═══════════════════════════════════════════════════════════════════════════════════╕║\n" +
						"║│🯅 🯆 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🮲🮳 🯇 🯉 🯉 🯉 🯉 🯉 🯉 🯈 🯇 🯈 🯇 🯈 🯇 🯈 🯇 │║\n"
						+
						"╠╧═══════════════════════════════════════════════════════════════════════════════════╧╣\n" +
						"║ Username: ",
				// err message
				"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n"
						+
						"\u001B[41m║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Username cannot be empty my bro! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║\u001B[0m\n"
						+
						"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
		if (username.equals("exit"))
			return;

		pass = inputString(
				// prompt
				"║ Password: ",
				// err message
				"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n"
						+
						"\u001B[41m║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Passwords cannot be empty my bro! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀║\u001B[0m\n"
						+
						"\u001B[41m╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
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
			System.out.println("\u001B[41m"
					+ "╠═════════════════════════════════════════════════════════════════════════════════════╣\n\u001B[0m"
					+
					"\u001B[41m"
					+ "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Username or password are wrong! 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀  ║"
					+ "\u001B[0m" +
					"\n" + "\u001B[41m"
					+ "╠═════════════════════════════════════════════════════════════════════════════════════╣\u001B[0m\n");
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
					choice = inputInt(
							"╠═════════════════════════════════════════════════════════════════════════════════════╣\n"
									+
									"║                     🯇 Please choose one of the following options:🯈                  ║\n"
									+
									"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║\n"
									+
									"║│1)│Employee Module.                                                                │║\n"
									+
									"║│2)│Task Module.                                                                    │║\n"
									+
									"║│0)│Sign Out.                                                                       │║\n"
									+
									"╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣\n"
									+
									"║ Please enter your choice: ");
					switch (choice) {
						case 1:
							currentModule = new EmployeeModule(employee);
							currentModule.startModule();
							break;
						case 2:
							boolean projexit = false;
							while (!projexit) {

								choice = inputInt(
										"╔═════════════════════════════════════════════════════════════════════════════════════╗\n"
												+
												"║                     🯇 Please choose one of the following options:🯈                  ║\n"
												+
												"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║\n"
												+
												"║│1)│Choose Project.                                                                 │║\n"
												+
												"║│2)│Go back.                                                                        │║\n"
												+
												"╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣\n"
												+
												"║ Please enter your choice: ");
								switch (choice) {
									case 1:
										System.out.print(
												"╔═════════════════════════════════════════════════════════════════════════════════════╗\n"
														+
														"║                     🯇 Please choose one of the following options:🯈                  ║ \n"
														+
														"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ \n"
														+
														"║│0)│Go back.                                                                        │║ \n");
										for (int i = 0; i < projectDataHandler.getLength(); i++) {

											System.out.print("║│" + (i + 1) + ")│Project:"
													+ projectDataHandler.get(i).getName()
													+ "                                               │║ \n");

										}
										choice = inputInt(
												"╠╧══╧════════════════════════════════════════════════════════════════════════════════╧╣\n"
														+
														"║ Please enter your choice: ");
										if (choice == 0)
											System.out.println("going back....");
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
											projexit = true;
										}
										break;
									case 2:
										projexit = true;
										break;
									default:
										System.out.println("\u001B[41m"
												+ "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
												+
												"\u001B[41m"
												+ "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
												+ "\u001B[0m" +
												"\n" + "\u001B[41m"
												+ "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
										break;
								}
							}
							break;
						case 0:
							exit = true;
							break;
						default:
							System.out.println("\u001B[41m"
									+ "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
									+
									"\u001B[41m"
									+ "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
									+ "\u001B[0m" +
									"\n" + "\u001B[41m"
									+ "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
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

		System.out.print("\033[H\033[2J");
		System.out.flush();

			while (!exit) {
				System.out.println(
						"╔═════════════════════════════════════════════════════════════════════════════════════╗\n" +
								"║▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓║ █\n"
								+
								"║▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░Task Management System░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓║ █\n"
								+
								"║▓▓▓▓▓▓▓▓▓🮜╔══════════════════════════════════════════════════════════════╗🮝▓▓▓▓▓▓▓▓▓▓║ █\n"
								+
								"║╒═════════╝                                                              ╚══════════╕║ █\n"
								+
								"║│ A task management system made in Java for the PL2 course at Helwan University.    │║ █\n"
								+
								"║│ This project made by: 3ab3z, Omar Atya, Omar Wagih, Ali, Mohammed, Tarek.         │║ █\n"
								+
								"║╘═══════════════════════════════════════════════════════════════════════════════════╛║ █\n"
								+
								"║                     🯇 Please choose one of the following options:🯈                  ║ █\n"
								+
								"║╒══╤════════════════════════════════════════════════════════════════════════════════╕║ █\n"
								+
								"║│1)│Sign in.                                                                        │║ █\n"
								+
								"║│2)│Exit.                                                                           │║ █\n"
								+
								"╚╧══╧════════════════════════════════════════════════════════════════════════════════╧╝ █\n"
								+
								" ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇█\n");
				choice = inputInt(">>🮶  ");
				switch (choice) {
					case 1:
						login();
						break;
					case 2:
						exit = true;
						break;
					default:
						System.out.println("\u001B[41m"
								+ "╔═════════════════════════════════════════════════════════════════════════════════════╗\n\u001B[0m"
								+
								"\u001B[41m"
								+ "║🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 Invalid Choice, please try again 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 🯀 ║"
								+ "\u001B[0m" +
								"\n" + "\u001B[41m"
								+ "╚═════════════════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
						break;
				}
			}
		} catch (IOException ex) {
			System.out.println("Fatal Error:" + ex.getMessage());
		}
	}

	public static int inputInt(String print) {
		int value;
		while (true) {
			try {
				System.out.print(print);
				value = Integer.parseInt(input.nextLine());
				break;
			} catch (NumberFormatException ex) {
				System.err.println("\033[31mInput must be an integer!\033[0m");
				continue;
			}
		}
		return value;
	}

	public static String inputString(String prompt) {
		String value;
		while (true) {
			System.out.print(prompt);
			value = input.nextLine();
			if (value.isBlank()) {
				System.err.println("\033[31mInput must not be empty!\033[0m");
				continue;
			}
			break;
		}
		return value;
	}
	//Waits for the user to acknowledge message
	public static String inputString(String prompt, String isBlank_message)
	{
		String value;
		while (true) {
			System.out.print(prompt);
			value = input.nextLine();
			if (value.isBlank()) {
				System.err.print(isBlank_message);
				input.nextLine();
				continue;
			}
			break;
		}
		return value;
	}
	public static String inputStringln(String prompt)
	{
		return inputString(prompt+"\n");
	}
	public static String inputStringln(String prompt, String isBlank_message)
	{
		return inputString(prompt+"\n", isBlank_message+"\n");
	}
	public static String inputStringOneWord(String prompt){
		return inputString(prompt).split(" ")[0];
	}
	public static String inputStringOneWord(String prompt, String isBlank_message){
		return inputString(prompt, isBlank_message).split(" ")[0];
	}
	public static String inputStringOneWordln(String prompt){
		return inputStringln(prompt).split(" ")[0];
	}
	public static String inputStringOneWordln(String prompt, String isBlank_message){
		return inputStringln(prompt, isBlank_message).split(" ")[0];
	}

	// public static int inputInt(String print) {
	// int value;
	// while (true) {
	// try {
	// System.out.print(print);
	// value = input.nextInt();
	// break;
	// } catch (InputMismatchException ex) {
	// System.err.print("Input must be an integer!");
	// input.next();
	// }
	// }
	// return value;
	// }

	public static int inputIntln(String print) {
		return inputInt(print + "\n");
	}

	public static double inputDouble(String print) {
		double value;
		while (true) {
			try {
				System.out.print(print);
				value = Double.parseDouble(input.nextLine());
				break;
			} catch (NumberFormatException ex) {
				System.err.print("\033[31mInput must be an number!\033[0m");
				continue;
			}
		}
		return value;
	}

	// public static double inputDouble(String print) {
	// double value;
	// while (true) {
	// try {
	// System.out.println(print);
	// value = input.nextDouble();
	// break;
	// } catch (InputMismatchException ex) {
	// System.err.print("Input must be an number!");
	// input.next();
	// }
	// }
	// return value;
	// }

	public static double inputDoubleln(String print) {
		return inputDouble(print + "\n");
	}

}