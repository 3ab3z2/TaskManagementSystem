import java.io.IOException;
import java.util.HashMap;

public class AdminModule extends Module {
	AdminModule(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public void startModule() throws IOException {
		int choice = -1;
		do {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			choice = Application.inputInt(Application.printInABoxMain("Admin Module","Hi "+currentUser.getUsername(),"🯇 Please choose one of the following options:🯈","1)│Manage Users\n" +
							"2)│Manage Employees\n" +
							"3)│Manage Projects\n" +
							"4)│Manage Employee Type\n" +
							"5)│Modify Task Phases\n" +
							"0)│Logout",3,85,true,10,20)+
							"║choose🮶 ");
			switch (choice) {
				case 0:// exit module
					break;
				case 1:
					manageUsers();
					break;
				case 2:
					manageEmployees();
					break;
				case 3:
					manageProjects();
					break;
				case 4:
					manageEmpType();
					break;
				case 5:
					manageTaskPhases();
					break;
				default:
					System.err.println(Application.printInABoxError("Invalid Operation!",85,true)+"Press Enter to continue...");
					Application.input.nextLine();
					break;
			}
		} while (choice != 0);
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void manageUsers() throws IOException {
		int choice = -1;
		menu_manageUsers: do {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			choice= Application.inputInt(Application.printInABox("Managing Users","1)│Add\n2)│Update\n3)│Delete\n0)│Back",3,85,true)+"║Choose🮶 ",Application.printInABoxError("Input must not be empty!",85,true));
			
			switch (choice) {
				case 0:// Back
					break;
				case 1:// Add Users
				{
					String uname, pword, utype_str;
					User.utype utype = null;
					int count_users = Application.userDataHandler.getLength();

					while (true)// employee/admin
					{
						utype_str = Application.inputString("User Type [(A)dmin/(E)mployee]: ");
						if (utype_str.equals("exit"))
							continue menu_manageUsers;
						if (utype_str.equals("Admin") || utype_str.equals("admin") || utype_str.equals("A")
								|| utype_str.equals("a"))
							utype = User.utype.admin;
						else if (utype_str.equals("Employee") || utype_str.equals("employee") || utype_str.equals("E")
								|| utype_str.equals("e"))
							utype = User.utype.employee;
						else {
							System.err.println("\033[31mInvalid Type! try again or type exit to go back\033[0m\n");
							continue;
						}
						break;
					}
					while (true)// username
					{
						boolean duplicate = false;

						uname = Application.inputString("Username: ");
						if (uname.equals("exit"))
							continue menu_manageUsers;
						for (int k = 0; k < count_users; ++k) {
							User user = Application.userDataHandler.get(k);
							if (user.getUsername().equals(uname)) {
								duplicate = true;
								break;
							}
						}
						if (duplicate) {
							System.err.println(
									"\033[31mUser by that name already exists! try again or type exit to go back\033[0m");
							continue;
						}
						break;
					}
					while (true)// password
					{
						pword = Application.inputString("Password: ");
						if (pword.equals("exit"))
							continue menu_manageUsers;
						if (pword.length() < 8) {
							System.err.println(
									"\033[31mPassword must be at least 8 character long! try again or type exit to go back\033[0m");
							continue;
						}
						break;
					}
					while (true)// confirm password
					{
						String cpword = Application.inputString("Confirm Password: ");
						if (cpword.equals("exit"))
							continue menu_manageUsers;
						if (!pword.equals(cpword)) {
							System.err.println("\033[31mPassword Mismatch! try again or type exit to go back\033[0m");
							continue;
						}
						break;
					}
					User user = new User(uname, pword, utype);
					Application.userDataHandler.add(user);
					System.out.print(
							"\033[32mSuccessfully added \"" + uname + "\"!\033[0m\n" +
									"Press enter to continue...\n");
					Application.input.nextLine();
				}
					break;
				case 2:// Update Users
				{
					User user = null;
					int user_idx = -1;
					int count_users = Application.userDataHandler.getLength();

					if (count_users == 0) {
						System.out.print(
								"\033[33mNo Registered Users Found!\033[0m\n" +
										"Press Enter to continue...\n");
						Application.input.nextLine();
						break;
					}
					do {
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.print(
								"Registered Users:\n" +
										"IDX\tUSERNAME\tTYPE\n");
						for (int k = 0; k < count_users; ++k) {
							user = Application.userDataHandler.get(k);
							System.out.printf("%d.\t%s\t%s\n", k + 1, user.getUsername(), user.getUserType());
						}
						System.out.println("0. Back");
						user_idx = Application.inputInt("Update>> ") - 1;
						if (user_idx == -1)
							break;
						if (user_idx < 0 || user_idx >= count_users) {
							System.err.println("\033[31mPlease select a valid number from the users list!\033[0m");
							continue;
						}
						choice = -1;
						while (user_idx != -1 && choice != 0) {
							System.out.print("\033[H\033[2J");
							System.out.flush();
							choice = Application.inputInt(
									"1. Username: " + user.getUsername() + "\n" +
											"2. Type:     " + user.getUserType() + "\n" +
											"3. Password\n" +
											"0. Cancel\n" +
											"Modify>> ");
							switch (choice) {
								case 0:// Cancel
									break;
								case 1:// modify username
									while (true) {
										String newname;
										Boolean duplicate = false;

										newname = Application.inputString("New Username: ");
										if (newname.equals("exit"))
											break;
										for (int k = 0; k < count_users; ++k) {
											User compare = Application.userDataHandler.get(k);
											if (compare.getUsername().equals(newname)) {
												duplicate = true;
												break;
											}
										}
										if (duplicate) {
											System.err.println(
													"\033[31mUser Already Exists! try again or enter exit to go back\033[0m");
											continue;
										}
										user.setUsername(newname);
										Application.userDataHandler.update(user_idx, user);
										break;
									}
									break;
								case 2:// modify type
									while (true) {
										String newtype;

										newtype = Application.inputString("New Type [(A)dmin/(E)mployee]: ");
										if (newtype.equals("exit"))
											break;
										if (newtype.equals("Admin") || newtype.equals("admin") || newtype.equals("A")
												|| newtype.equals("a"))
											user.setUserType(User.utype.admin);
										else if (newtype.equals("Employee") || newtype.equals("employee")
												|| newtype.equals("E") || newtype.equals("e"))
											user.setUserType(User.utype.employee);
										else {
											System.out.println(
													"\033[31mInvalid User Type! try again or type \"exit\" to go back\033[0m");
											continue;
										}
										Application.userDataHandler.update(user_idx, user);
										break;
									}
									break;
								case 3:// modify password
									while (true) {
										String oldpword, newpword;

										oldpword = Application.inputString("Old Password: ");
										if (oldpword.equals("exit"))
											break;
										if (!oldpword.equals(user.getPassword())) {
											System.out.println(
													"\033[31mIncorrect Password! try again or type \"exit\" to go back\033[0m");
											continue;
										}
										newpword = Application.inputString("New Password: ");
										if (newpword.equals("exit"))
											break;
										user.setPassword(newpword);
										Application.userDataHandler.update(user_idx, user);
										break;
									}
									break;
								default:
									System.err.println("\033[31mInvalid Operation!\033[0m");
									break;
							}
						}
					} while (user_idx != -1);
				}
					break;
				case 3:// Delete Users
					while (true) {
						User user = null;
						int user_idx = -1;
						String uname;
						int count_users = Application.userDataHandler.getLength(),
								count_employees = Application.employeeDataHandler.getLength(), // cascade delete
								count_tasks = Application.taskDataHandler.getLength(), // nullify
								count_projects = Application.projectDataHandler.getLength();// nullify

						if (count_users == 0) {
							System.out.print(
									"\033[33mNo Registered Users Found!\033[0m\n" +
											"Press enter to continue...");
							Application.input.nextLine();
							break;
						}
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.print(
								"Registered Users:\n" +
										"IDX\tUSERNAME\tTYPE\n");
						for (int k = 0; k < count_users; ++k) {
							user = Application.userDataHandler.get(k);
							System.out.printf("%d.\t%s\t%s\n", k + 1, user.getUsername(), user.getUserType());
						}
						System.out.println("0. Cancel");
						user_idx = Application.inputInt("Delete>> ") - 1;
						if (user_idx == -1)
							break;
						if (user_idx < 0 || user_idx >= count_users) {
							System.err.println("\033[31mPlease select a valid number from the users list!\033[0m");
							continue;
						}
						uname = user.getUsername();
						String confirm = Application.inputString(
								"Are you sure you want to \033[31mDELETE\033[0m \"" + uname + "\"? [Y/N]: ");
						if (!confirm.equals("Y") && !confirm.equals("y"))// don't delete
							continue;
						Application.userDataHandler.delete(user_idx);// delete user

						for (int k = 0; k < count_employees; ++k)// delete from employees
						{
							Employee employee = Application.employeeDataHandler.get(k);
							if (employee.getUsername().equals(uname)) {
								Application.employeeDataHandler.delete(k);
								break;
							}
						}
						for (int k = 0; k < count_tasks; ++k)// nullify assigned tasks
						{
							Task task = Application.taskDataHandler.get(k);
							if (task.getAssignedEmployee() == null)
								continue;
							if (task.getAssignedEmployee().getUsername().equals(uname)) {
								task.setAssignedEmployee(null);
								Application.taskDataHandler.update(k, task);
							}
						}
						for (int k = 0; k < count_projects; ++k)// nullify any assigned projects
						{
							Project project = Application.projectDataHandler.get(k);
							if (project.getLeader() == null)
								continue;
							if (project.getLeader().getUsername().equals(uname)) {
								project.setLeader(null);
								Application.projectDataHandler.update(k, project);
							}
						}
					}
					break;
				default:
					System.err.println("\033[31mInvalid Operation!\033[0m");
			}
		} while (choice != 0);
	}

	public void manageEmployees() throws IOException {
		int choice = 0;
		do {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			choice= Application.inputInt(Application.printInABox("Managing Employees","1)│Add\n2)│Update\n3)│Delete\n0)│Back",3,85,true)+"║Choose🮶 ",Application.printInABoxError("Input must not be empty!",85,true));
			
			switch (choice) {
				case 1:// Add Employees
				{
					Employee employee = null;
					User user = null;
					String uname = null;
					int empType_idx = -1,
							count_users = Application.userDataHandler.getLength(),
							count_emptypes = Application.empTypeDataHandler.getLength();
					boolean unregistered = false;

					if (count_users == 0)
					{
						System.out.print(
								"\033[33mNo Registered Users Found!\033[0m\n" +
										"Press enter to continue...\n");
						Application.input.nextLine();
						break;// back to menu
					}
					do
					{
						int count_employees = Application.employeeDataHandler.getLength();
						for (int i = 0; i < count_users; i++) {
							boolean found = false;
							User user_registered = Application.userDataHandler.get(i);

							if (user_registered.getUserType() != User.utype.employee)
								continue;
							for (int j = 0; j < count_employees; j++) {
								Employee employee_registered = Application.employeeDataHandler.get(j);
								if (employee_registered.getUsername().equals(user_registered.getUsername())) {
									found = true;
									break;
								}
							}
							if (!found) {
								unregistered = true;
								break;
							}
						}
						if (!unregistered)
						{
							System.out.print(
									"\033[33mAll employee user accounts have already been registered & approved!\033[0m\n"
											+
											"Press Enter to continue...\n");
							Application.input.nextLine();
							break;// back to manageEmployees;
						}
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.print(
								"Registered Accounts:\n" +
										"USERNAME\tTYPE\n");
						for (int k = 0; k < count_users; ++k) {
							user = Application.userDataHandler.get(k);
							if (user.getUserType() != User.utype.employee)
								continue;

							unregistered = false;
							for (int i = 0; i < count_employees; ++i) {
								if (Application.employeeDataHandler.get(i).getUsername().equals(user.getUsername())) {
									unregistered = true;
									break;
								}
							}
							if (!unregistered)
								System.out.printf("%s\t%s\n", user.getUsername(), user.getUserType());
						}
						System.out.println("\"exit\" to go back");
						uname = Application.inputString("Choose>> ");
						if (uname.equals("exit"))
							break;
						Boolean found = false;
						for (int k = 0; k < count_users; ++k) {
							user = Application.userDataHandler.get(k);
							if (user.getUsername().equals(uname)) {
								found = true;
								break;
							}
						}
						if (!found) {
							System.err.println("\033[31mPlease select a valid user account from the list!\033[0m");
							continue;
						}
						if (count_emptypes == 0) {
							employee = new Employee(user.getUsername(), user.getPassword(), User.utype.employee, null);
							Application.employeeDataHandler.add(employee);
						}
						else {
							do {
								EmpType empType = null;

								System.out.print("\033[H\033[2J");
								System.out.flush();
								System.out.print(
									"Defined Employee Types:\n" +
									"IDX\tTYPE\tMANAGER?\n");
								for (int k = 0; k < count_emptypes; ++k) {
									empType = Application.empTypeDataHandler.get(k);
									System.out.printf("%d.\t%s\t%s\n", k + 1, empType.getName(),
											empType.isManager() ? "Yes" : "No");
								}
								System.out.print(
									"0. Cancel\n" +
									"-1. To Be Determined\n");
								empType_idx = Application.inputInt("Choose>> ") - 1;
								if (empType_idx == -1)
									break;
								if (empType_idx == -2) {
									employee = new Employee(user.getUsername(), user.getPassword(), User.utype.employee,
											null);
									Application.employeeDataHandler.add(employee);
									break;
								}
								if (empType_idx < 0 || empType_idx >= count_emptypes) {
									System.err.println(
										"\033[31mPlease select a valid number from the employee type list!\033[0m");
									continue;
								}
								empType = Application.empTypeDataHandler.get(empType_idx);
								employee = new Employee(user.getUsername(), user.getPassword(), User.utype.employee,
										empType);
								Application.employeeDataHandler.add(employee);
								break;
							} while (empType_idx != -1);
						}
					} while (!uname.equals("exit"));
				}
					break;
				case 2:// Update Employees
				{
					Employee employee = null;
					int employee_idx = -1,
							empType_idx = -1,
							count_employees = Application.employeeDataHandler.getLength();

					if (count_employees == 0) {
						System.out.print(
								"\033[33mNo Employees Registered!\033[0m\n" +
										"Press Enter to continue...\n");
						Application.input.nextLine();
						break;
					}
					do {
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.print(
								"Registered Employees:\n" +
										"IDX\tNAME\tPOSITION\n");
						for (int k = 0; k < count_employees; ++k) {
							employee = Application.employeeDataHandler.get(k);
							System.out.printf(
									"%d.\t%s\t%s\n",
									k + 1,
									employee.getUsername(),
									employee.getEmpType() == null ? "TBD" : employee.getEmpType().getName());
						}
						System.out.println("0. Back");
						employee_idx = Application.inputInt("Update>> ") - 1;
						if (employee_idx == -1)
							break;
						if (employee_idx < 0 || employee_idx >= count_employees) {
							System.err.println("\033[31mPlease select a valid number from the employees list!\033[0m");
							continue;
						}
						employee = Application.employeeDataHandler.get(employee_idx);
						do {
							EmpType empType = null;
							int count_emptypes = Application.empTypeDataHandler.getLength();

							System.out.print("\033[H\033[2J");
							System.out.flush();
							System.out.print(
									"Defined Employee Types:\n" +
											"IDX\tTYPE\tMANAGER?\n");
							for (int k = 0; k < count_emptypes; ++k) {
								empType = Application.empTypeDataHandler.get(k);
								System.out.printf("%d.\t%s\t%s\n", k + 1, empType.getName(),
										empType.isManager() ? "Yes" : "No");
							}
							System.out.print(
									"0. Cancel\n" +
											"-1. TBD\n");
							empType_idx = Application.inputInt("Choose>> ") - 1;
							if (empType_idx == -1)
								break;
							if (empType_idx == -2) {
								employee.setEmpType(null);
								Application.employeeDataHandler.update(employee_idx, employee);
								break;
							}
							if (empType_idx < 0 || empType_idx >= count_emptypes) {
								System.err.println(
										"\033[31mPlease select a valid number from the employee type list!\033[0m");
								continue;
							}
							empType = Application.empTypeDataHandler.get(empType_idx);
							employee.setEmpType(empType);
							Application.employeeDataHandler.update(employee_idx, employee);
							break;
						} while (empType_idx != -1);
					} while (employee_idx != -1);
				}
					break;
				case 3:// Delete Employees
					while (true) {
						Employee employee = null;
						int employee_idx = -1,
								count_employees = Application.employeeDataHandler.getLength(),
								count_users = Application.userDataHandler.getLength(), // for cascade deletion
								count_tasks = Application.taskDataHandler.getLength(), // for nullifying
								count_projects = Application.projectDataHandler.getLength();// for nullifying

						if (count_employees == 0) {
							System.out.print(
									"\033[33mNo Employees Yet!\033[0m\n" +
											"Press enter to continue...\n");
							Application.input.nextLine();
							break;// back to menu
						}
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.print(
								"Registered Employees:\n" +
										"IDX\tNAME\tTYPE\n");
						for (int k = 0; k < count_employees; ++k) {
							employee = Application.employeeDataHandler.get(k);
							System.out.printf("%d.\t%s\t%s\n", k + 1, employee.getUsername(),
									employee.getEmpType() == null ? "TBD" : employee.getEmpType().getName());
						}
						System.out.println("0. Cancel");
						employee_idx = Application.inputInt("Delete>> ") - 1;
						if (employee_idx == -1)
							break;
						if (employee_idx < 0 || employee_idx >= count_employees) {
							System.err.println("\033[31mPlease select a valid number from the users list!\033[0m");
							continue;
						}
						employee = Application.employeeDataHandler.get(employee_idx);
						String confirm = Application.inputString("Are you sure you want to \033[31mDELETE\033[0m \""
								+ employee.getUsername() + "\"? [y/N]: ");
						if (!confirm.equals("Y") && !confirm.equals("y"))// don't delete
							continue;
						Application.employeeDataHandler.delete(employee_idx);// delete employee
						for (int k = 0; k < count_users; ++k) {
							User user = Application.userDataHandler.get(k);
							if (user.getUsername().equals(employee.getUsername()))// delete user account
							{
								Application.userDataHandler.delete(k);
								break;
							}
						}
						for (int k = 0; k < count_tasks; ++k)// nullify assigned tasks
						{
							Task task = Application.taskDataHandler.get(k);
							if (task.getAssignedEmployee() == null)
								continue;
							if (task.getAssignedEmployee().getUsername().equals(employee.getUsername())) {
								task.setAssignedEmployee(null);
								Application.taskDataHandler.update(k, task);
							}
						}
						for (int k = 0; k < count_projects; ++k)// nullify any assigned projects
						{
							Project project = Application.projectDataHandler.get(k);
							if (project.getLeader() == null)
								continue;
							if (project.getLeader().getUsername().equals(employee.getUsername())) {
								project.setLeader(null);
								Application.projectDataHandler.update(k, project);
							}
						}
					}
					break;
				case 4:
					break;
				default:
					System.out.println("\033[31mInvalid Operation!\033[0m");
			}
		} while (choice != 0);
	}
	public void manageProjects() throws IOException
	{
		int choice= 0;
		boolean exit_manageProjects= false;
		while(!exit_manageProjects)
		{
			System.out.print("\033[H\033[2J");
			System.out.flush();
			choice= Application.inputInt(Application.printInABox("Managing Projects","1)│Add\n2)│Update\n3)│Delete\n0)│Back",3,85,true)+"║Choose🮶 ",Application.printInABoxError("Input must not be empty!",85,true));
			
			switch(choice)
			{
			case 0://Back
				exit_manageProjects= true;
				break;
			case 1://Add Projects
				{
					Project project= null;
					Employee leader= null;
					String project_name= "", project_description = "", leader_name= "";
					int count_employees= Application.employeeDataHandler.getLength();
					boolean exit_name= false, exit_leader= false;

					while(!exit_name)//project name
					{
						boolean duplicate= false;
						int count_projects= Application.projectDataHandler.getLength();

						project_name= Application.inputString("Project Name: ", "\033[31mThe project must have a name!\033[0m\n");
						if(project_name.equals("exit"))
						{
							exit_name= true;
							break;
						}
						for(int k=0;k<count_projects;++k)
						{
							Project compare= Application.projectDataHandler.get(k);
							if(compare.getName().equals(project_name))
							{
								duplicate= true;
								break;
							}
						}
						if(duplicate)
						{
							System.err.print("\033[31mProject with the same name already in progress! try again or type \"exit\" to go back\033[0m\n");
							continue;
						}
						break;
					}
					if(exit_name)
						break;
					//project description
					project_description= Application.inputString("Project Description: ");
					if(project_description.equals("exit"))
						break;
					if(count_employees != 0)//project leader
					{
						while(!exit_leader)
						{
							System.out.print(
								"Available employees to assign a leader:\n"+
								"NAME\tPOSITION\tMANAGER?\n"
							);
							for(int k=0;k<count_employees;++k)
							{
								Employee employee= Application.employeeDataHandler.get(k);
								if(employee.getEmpType()==null)
									continue;
								if(employee.getEmpType().isManager())
									System.out.printf("%s\t%s\t%s\n", employee.getUsername(), employee.getEmpType().getName(), employee.getEmpType().isManager()?"Yes":"No");
							}
							leader_name= Application.inputString("Choose>> ");
							if(leader_name.equals("exit"))
							{
								exit_leader= true;
								break;
							}
							for(int k=0;k<count_employees;++k)
							{
								Employee employee= Application.employeeDataHandler.get(k);
								if(employee.getEmpType()==null)
									continue;
								if(employee.getEmpType().isManager() && employee.getUsername().equals(leader_name))
								{
									leader= employee;
									break;
								}
							}
							if(leader==null)
							{
								System.err.println("\033[31mManager with that name not found! try again or type \"exit\" to go back\033[0m\n");
								continue;
							}
							break;
						}
					}
					if(exit_leader==true)
						break;
					project= new Project(project_name, project_description, leader);
					Application.projectDataHandler.add(project);
					System.out.print(
						"\033[32mSuccessfully added project \""+project_name+"\"!\033[0m\n"+
						"Press enter to continue...\n"
					);
					Application.input.nextLine();
				}
				break;
			case 2:// Update Projects
				{
					boolean exit_update= false;
					while(!exit_update)
					{
						Project project;
						int
							count_projects= Application.projectDataHandler.getLength(),
							project_idx= -1;
						boolean exit_update_selected= false;

						if(count_projects==0)
						{
							System.err.print(
								"\033[33mNo Projects Found!\033[0m\n" +
								"Press enter to continue...");
							Application.input.nextLine();
							break;
						}
						System.out.print("\033[H\033[2J"); System.out.flush();
						System.out.print(
							"Projects in progress:\n" +
							"IDX\tNAME\tLEADER\tDESCRIPTION\n"
						);
						for(int k=0;k<count_projects;++k)
						{
							project= Application.projectDataHandler.get(k);
							System.out.printf(
								"%d.\t%s\t%s\t%s\n",
								k+1,
								project.getName(),
								project.getLeader()==null?"\033[33mTBD\033[0m":project.getLeader().getUsername(),
								project.getDescription()
							);
						}
						System.out.println("0. Back");
						project_idx= Application.inputInt("Update>> ")-1;
						if(project_idx==-1)
						{
							exit_update= true;
							continue;
						}
						if(project_idx<0||project_idx>=count_projects)
						{
							System.err.println("\033[31mPlease select a valid number from the projects list!\033[0m");
							Application.input.nextLine();
							continue;
						}
						project= Application.projectDataHandler.get(project_idx);
						while(!exit_update_selected)
						{
							System.out.print("\033[H\033[2J"); System.out.flush();
							System.out.print(
								"1. Name:        "+project.getName()+"\n"+
								"2. Description: "+project.getDescription()+"\n"+
								"3. Leader:      "+(project.getLeader()==null?"\033[33mTBD\033[0m":project.getLeader().getUsername())+"\n"+
								"0. Cancel\n"
							);
							choice = Application.inputInt("Modify>> ");
							switch(choice)
							{
							case 0://Cancel
								exit_update_selected= true;
								continue;
							case 1://Modify Project Name
								while(true)
								{
									boolean duplicate= false;
									String newname= Application.inputString("New Name: ");
									
									if(newname.equals("exit"))
										break;
									for(int k=0;k<count_projects;++k)
									{
										Project compare= Application.projectDataHandler.get(k);
										if(compare.getName().equals(newname))
										{
											duplicate= true;
											break;
										}
									}
									if(duplicate)
									{
										System.out.print("\033[31mProject with the same name already in progress! Try again or type \"exit\" to go back\033[0m\n");
										continue;
									}
									project.setName(newname);
									Application.projectDataHandler.update(project_idx, project);
									break;
								}
								break;
							case 2://Modify Project Description
								while(true)
								{
									String newdescription= Application.inputString("New Description: ");
									if(newdescription.equals("exit"))
										break;
									project.setDescription(newdescription);
									Application.projectDataHandler.update(project_idx, project);
									break;
								}
								break;
							case 3://Modify Project Leader
								while(true)
								{
									Employee leader= null;
									String leader_name= "";
									int count_employees= Application.employeeDataHandler.getLength();

									if(count_employees==0)
									{
										System.out.print(
											"\033[33mNo employees available to assign a leader!\033[0m\n"+
											"Press enter to continue...\n"
										);
										Application.input.nextLine();
										continue;
									}
									System.out.print(
										"Available employees to assign a leader:\n"+
										"NAME\tPOSITION\n"
									);
									for(int k=0;k<count_employees;++k)
									{
										Employee employee= Application.employeeDataHandler.get(k);
										if(employee.getEmpType()==null)
											continue;
										if(!employee.getEmpType().isManager())
											continue;
										System.out.printf("%s\t%s\n", employee.getUsername(), employee.getEmpType().getName());
									}
									leader_name= Application.inputString("Choose>> ");
									if(leader_name.equals("exit"))
										break;
									for(int k=0;k<count_employees;++k)
									{
										Employee employee= Application.employeeDataHandler.get(k);
										if(employee.getEmpType()==null)
											continue;
										if(!employee.getEmpType().isManager())
											continue;
										if(employee.getUsername().equals(leader_name))
											leader= employee;
									}
									if(leader==null)
									{
										System.out.println("\033[31mManager with that name not found! try again or type \"exit\" to go back\033[0m\n");
										continue;
									}
									project.setLeader(leader);
									Application.projectDataHandler.update(project_idx, project);
									break;
								}
								break;
							default:
								System.out.println("\033[31mInvalid Operation!\033[0m");
								break;
							}
						}
					}
				}
				break;
			case 3:// Delete Projects
				while(true)
				{
					Project project;
					int
						count_projects= Application.projectDataHandler.getLength(),
						count_tasks= Application.taskDataHandler.getLength(), // for cascade deletion
						project_idx= -1;

					if(count_projects==0)
					{
						System.err.print(
							"\033[33mNo Projects Found!\033[0m"+
							"Press enter to continue...\n"
						);
						Application.input.nextLine();
						break;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					System.out.print(
						"Projects in progress:\n" +
						"IDX\tNAME\tLEADER\tDESCRIPTION\n"
					);
					for(int k=0;k<count_projects;++k)
					{
						project= Application.projectDataHandler.get(k);
						System.out.printf(
							"%d.\t%s\t%s\t%s\n",
							k+1,
							project.getName(),
							project.getLeader()==null?"\033[33mTBD\033[0m":project.getLeader().getUsername(),
							project.getDescription()
						);
					}
					System.out.println("0. Back");
					project_idx= Application.inputInt("Delete>> ")-1;
					if(project_idx==-1)
						break;
					if(project_idx<0||project_idx>=count_projects)
					{
						System.out.println("\033[31mPlease select a valid number from the projects list!\033[0m");
						continue;
					}
					project= Application.projectDataHandler.get(project_idx);
					String confirm= Application.inputString("Are you sure you want to \033[31mDELETE\033[0m \""+project.getName()+"\"? [y/N]: ");
					if(!confirm.equals("Y") && !confirm.equals("y"))// don't delete
						continue;
					Application.projectDataHandler.delete(project_idx);// delete project
					for(int k=0;k<count_tasks;++k)// delete all tasks associated with the project
					{
						Task task= Application.taskDataHandler.get(k);
						if(task.getProject()==project)
							Application.taskDataHandler.delete(k);
					}
				}
				break;
			default:
				System.out.println("\033[31mInvalid Operation!\033[0m");
			}
		}
	}
	public void manageEmpType() throws IOException
	{
		int choice= 0;
		boolean exit_manageEmptype= false;
		while(!exit_manageEmptype)
		{
			System.out.print("\033[H\033[2J");
			System.out.flush();
			choice= Application.inputInt(Application.printInABox("Managing Types of Employees","1)│Add\n2)│Update\n3)│Delete\n0)│Back",3,85,true)+"║Choose🮶 ",Application.printInABoxError("Input must not be empty!",85,true));
			switch(choice)
			{
			case 0://Back
				exit_manageEmptype= true;
				break;
			case 1://Add Employee Type
				while(true)
				{
					EmpType empType= null;
					String name= "", manager= "";
					int count_emptypes= Application.empTypeDataHandler.getLength();
					boolean duplicate= false;

					name= Application.inputString("║Enter New Type of Employee or type \"exit\" to go back: ");
					if(name.equals("exit"))
						break;
					for(int k=0;k<count_emptypes;++k)
					{
						EmpType compare= Application.empTypeDataHandler.get(k);
						if(compare.getName().equals(name))
						{
							duplicate = true;
							break;
						}
					}
					if(duplicate)
					{
						System.out.println(Application.printInABoxError("Type with the same name already exists! Try again or type \"exit\" to go back",85,true));
						continue;
					}
					manager= Application.inputString("║Categorize as manager? [y/N]: ");
					if(manager.equals("exit"))
						break;
					if(manager.equalsIgnoreCase("Y"))
						empType= new EmpType(name, true);
					else
						empType= new EmpType(name, false);
					Application.empTypeDataHandler.add(empType);
					System.out.print(
						Application.printInABoxGreen("Successfully added employee type \""+name+"\"!",85)+
						"Press enter to continue..."
					);
					Application.input.nextLine();
					break;
				}
				break;
			case 2://Update Employee Type
				while(true)
				{
					EmpType emptype= null;
					int
						emptype_idx= -1,
						count_emptype= Application.empTypeDataHandler.getLength();
					boolean exit_update_selected= false;

					if(count_emptype==0)
					{
						System.out.print(
							Application.printInABoxError("No Employee Types Defined Yet!",85)+
							"Press enter to continue..."
						);
						Application.input.nextLine();
						break;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					HashMap<Integer,String> tp=new HashMap<>();
					for (int k = 0; k < count_emptype; ++k) {
						String ln="";
						emptype = Application.empTypeDataHandler.get(k);
						ln+="Name:"+emptype.getName()+", Manager:"+(emptype.isManager()?"Yes":"No");
						tp.put(k+1, ln);
					}
					tp.put(0,"Cancel");
					System.out.print(Application.printInABox("Defined Employee Types:", tp, 3, 85, true));
					emptype_idx= Application.inputInt("║Update🮶 ")-1;
					if(emptype_idx==-1)
						break;
					if(emptype_idx<0||emptype_idx>=count_emptype)
					{
						System.out.println(Application.printInABoxError("Please select a valid number from the type list!",85,true));
						continue;
					}
					emptype= Application.empTypeDataHandler.get(emptype_idx);
					while(!exit_update_selected)
					{
						System.out.print("\033[H\033[2J"); System.out.flush();
						System.out.print(Application.printInABox("Choose Attribute:",
							"1)│Type    : "+emptype.getName()+"\n"+
							"2)│Manager?: "+(emptype.isManager()?"Yes":"No")+"\n"+
							"0)│Cancel",3,85,true)
						);
						choice= Application.inputInt("║Modify🮶 ");
						switch(choice)
						{
						case 0://Cancel
							exit_update_selected= true;
							break;
						case 1://Modify Type
							while(true)
							{
								String newtype, oldtype= emptype.getName();
								int count_employees= Application.employeeDataHandler.getLength();
								boolean duplicate = false;

								newtype= Application.inputString("║New Type: ");
								for(int k=0;k<count_emptype;++k)
								{
									EmpType compare= Application.empTypeDataHandler.get(k);
									if(compare.getName().equals(newtype))
									{
										duplicate = true;
										break;
									}
								}
								if(duplicate)
								{
									System.out.print(Application.printInABoxError("Type with the same name already in exists! Try again",85,true));
									continue;
								}
								emptype.setName(newtype);
								Application.empTypeDataHandler.update(emptype_idx, emptype);
								for(int k=0;k<count_employees;++k)//update all employees with this type
								{
									Employee employee= Application.employeeDataHandler.get(k);
									if(employee.getEmpType()==null)
										continue;
									if(employee.getEmpType().getName().equals(oldtype))
									{
										employee.setEmpType(emptype);
										Application.employeeDataHandler.update(k, employee);
										System.out.print(Application.printInABoxGreen("\""+employee.getUsername()+"\" has been promoted to \""+newtype+"\"!",85));
									}
								}
								System.out.print(
									Application.printInABoxGreen("Successfully updated employee type!",85)+
									"Press enter to continue..."
								);
								Application.input.nextLine();
								break;
							}
							break;
						case 2://Modify Managerial Position
							{
								String manager= "";
								int count_projects= Application.projectDataHandler.getLength();

								manager= Application.inputString("║Categorize as manager? [y/N]: ");
								if(manager.equals("exit"))
									break;
								if(manager.equalsIgnoreCase("Y"))
									emptype.setManager(true);
								else
								{
									emptype.setManager(false);
									for(int k=0;k<count_projects;++k)//nullify project leaders
									{
										Project project= Application.projectDataHandler.get(k);
										if(project.getLeader()==null)
											continue;
										if(project.getLeader().getEmpType()==emptype)
										{
											System.out.println("\033[33m\""+project.getLeader().getUsername()+"\" is no longer leading in \""+project.getName()+"\"\033[0m");
											project.setLeader(null);
											Application.projectDataHandler.update(k, project);
										}
									}
								}
								Application.empTypeDataHandler.update(emptype_idx, emptype);
								System.out.print(
									Application.printInABoxGreen("Successfully modified managerial position!",85)+
									"Press enter key to continue...\n"
								);
								Application.input.nextLine();
							}
							break;
						default:
							System.out.println(Application.printInABoxError("Invalid Operation!",85));
						}
					}
				}
				break;
			case 3://Delete Employee Type
				while(true)
				{
					EmpType emptype= null;
					int
						emptype_idx= -1,
						count_emptypes= Application.empTypeDataHandler.getLength(),
						count_employees= Application.employeeDataHandler.getLength();//for nullifying
	
					if(count_emptypes==0)
					{
						System.out.print(
							Application.printInABoxError("No Employee Types Defined!",85)+
							"Press enter to continue..."
						);
						Application.input.nextLine();
						break;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					HashMap<Integer,String> tp=new HashMap<>();
					for (int k = 0; k < count_emptypes; ++k) {
						String ln="";
						emptype= Application.empTypeDataHandler.get(k);
						ln+="Name:"+emptype.getName()+", Manager:"+(emptype.isManager()?"Yes":"No");
						tp.put(k+1, ln);
					}
					tp.put(0,"Cancel");
					System.out.print(Application.printInABox("Defined Employee Types:", tp, 3, 85, true));
					emptype_idx= Application.inputInt("║Delete🮶 ")-1;
					if(emptype_idx==-1)
						break;
					if(emptype_idx<0||emptype_idx>=count_emptypes)
					{
						System.out.println(Application.printInABoxError("Please select a valid number from the type list!",85));
						continue;
					}
					emptype= Application.empTypeDataHandler.get(emptype_idx);
					String confirm= Application.inputString("Are you sure you want to \033[31mDELETE\033[0m \""+emptype.getName()+"\" type? [y/N]: ");
					if(!confirm.equals("Y") && !confirm.equals("y"))// Don't Delete
						continue;
					for(int k=0;k<count_employees;++k)// nullify all employees with this type
					{
						Employee employee= Application.employeeDataHandler.get(k);
						if(employee.getEmpType()==emptype)
						{
							employee.setEmpType(null);
							Application.employeeDataHandler.update(k, employee);
							System.out.printf("\033[33m\'%s\' position has been nullified\033[0m\n", employee.getUsername());
						}
					}
					Application.empTypeDataHandler.delete(emptype_idx);//delete type
					System.out.println("Press enter to continue...");
					Application.input.nextLine();
				}
				break;
			default:
				System.out.println(Application.printInABoxError("Invalid Operation!",85));
			}
		}
	}
	public void manageTaskPhases() throws IOException
	{
		Task task = null;
		int
			task_idx = -1,
			count_tasks = Application.taskDataHandler.getLength();

		if (count_tasks == 0) {
			System.out.print(
					Application.printInABoxError("No Tasks Yet!",85) +
							"Press enter to continue...");
			Application.input.nextLine();
			return;// to menu
		}
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			HashMap<Integer,String> tp=new HashMap<>();
			for (int k = 0; k < count_tasks; ++k) {
				String ln="";
				task = Application.taskDataHandler.get(k);
				ln+="Code:"+task.getCode()+", Title:"+task.getTitle()+", Project:"+task.getProject().getName()+", Phase:"+task.getTaskPhase();
				tp.put(k+1, ln);
			}
			tp.put(0,"Cancel");
			System.out.print(Application.printInABox("Current Tasks:",tp,3,85,true));
			task_idx = Application.inputInt("║Modify🮶 ") - 1;
			if (task_idx == -1)
				break;
			if (task_idx < 0 || task_idx >= count_tasks) {
				System.err.print(Application.printInABoxError("Please select a valid number from the tasks list!",85));
				System.out.print("Press enter to continue...");
				Application.input.nextLine();
				continue;
			}
			task = Application.taskDataHandler.get(task_idx);
			String phase = Application.inputString("║New Task Phase: ");
			task.setTaskPhase(phase);
			Application.taskDataHandler.update(task_idx, task);
		}
	}
}