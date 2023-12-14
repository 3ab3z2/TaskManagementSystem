import java.io.IOException;
import java.util.InputMismatchException;

public class AdminModule extends Module {
	AdminModule(User currentUser) {
		this.currentUser= currentUser;
	}
	@Override
	public void startModule() throws IOException
	{
		int choice= -1;
		do
		{
			System.out.print("\033[H\033[2J"); System.out.flush();
			System.out.print(
				"Admin Module\n"+
				"\t1.Manage Users\n"+
				"\t2.Manage Employees\n"+
				"\t3.Manage Projects\n"+
				"\t4.Manage Employee Type\n"+
				"\t5.Modify Task Phases\n"+
				"\t0.Logout\n"+
				"input>> ");
			try
			{
				choice= Integer.parseInt(Application.input.nextLine());
			}
			catch(NumberFormatException e)
			{
				System.out.println("\033[31mInvalid Operation!\033[0m");
				continue;
			}
			switch(choice)
			{
			case 0://exit module
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
				System.out.println("\033[31mInvalid Operation!\033[0m");
			}
		} while(choice!=0);
		System.out.print("\033[H\033[2J"); System.out.flush();
	}
	public void manageUsers() throws IOException
	{
		int choice= 0;
		boolean exit= false;
	menu:
		while(!exit)
		{
			System.out.print("\033[H\033[2J"); System.out.flush();
			System.out.print(
				"Managing Users..\n"+
				"1.Add 2.Update 3.Delete 4.Back\n"+
				"input>> ");
			try
			{
				choice= Application.input.nextInt();    
			}
			catch(InputMismatchException e)
			{
				System.out.println("\033[31mInvalid Operation!\033[0m");
				Application.input.next();//consume invalid input from Scanner buffer
				continue;
			}
			switch(choice)
			{
			case 1://Add Users
				{
					String uname, pword, utype_str;
					User.utype utype= null;
					int count_users= Application.userDataHandler.getLength();

					while(true)
					{
						System.out.print("User Type [(A)dmin/(E)mployee]: ");
						utype_str= Application.input.next();
						if(utype_str.equals("Admin")||utype_str.equals("admin")||utype_str.equals("A")||utype_str.equals("a"))
							utype= User.utype.admin;
						else if(utype_str.equals("Employee")||utype_str.equals("employee")||utype_str.equals("E")||utype_str.equals("e"))
							utype= User.utype.employee;
						else
						{
							System.out.println("\033[31mInvalid User Type!\033[0m");
							System.out.print("\033[33mTry again? [Y/N]: \033[0m");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}
					System.out.println("=====================================");
					while(true)
					{
						boolean duplicate= false;

						System.out.println("|| \u001B[43m"+"Username: \u001B[0m\t\t\t\t||\n");
						uname= Application.input.next();
						for(int k=0;k<count_users;++k)
						{
							User user= Application.userDataHandler.get(k);
							if(user.getUsername().equals(uname))
							{
								duplicate= true;
								break;
							}
						}
						if(duplicate)
						{
							System.out.println("|| \033[31m"+"User by that name already exists!\033[0m\t||\n");
							System.out.println("|| \033[31m"+"Try again? [Y/N]: \033[0m\t\t\t||\n");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}
					while(true)
					{
						System.out.println("|| \u001B[43m"+"Password: \u001B[0m\t\t\t\t||\n");
						pword= Application.input.next();
						if(pword.length()<8)
						{
							System.out.println("|| \033[31m"+"Password must be at least 8 character long!\033[0m\t||\n");
							System.out.println("|| \033[31m"+"Try again? [Y/N]: \033[0m\t\t\t||\n");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}
					while(true)
					{
						System.out.println("|| \u001B[43m"+"Confirm Password: \u001B[0m\t\t\t||\n");
						String cpword= Application.input.next();
						if(!pword.equals(cpword))
						{
							System.out.println("|| \033[31m"+"Password Mismatch!\033[0m\t\t\t||\n");
							System.out.println("|| \033[31m"+"Try again? [Y/N]: \033[0m\t\t\t||\n");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}

					User user= new User(uname, pword, utype);
					Application.userDataHandler.add(user);

					// System.out.println("|| \033[33m"+"\""+uname+"\" \033[32mwas successfully added!\033[0m\t||\n");
					System.out.println(uname+" \033[32mwas successfully added!\033[0m\nEnter any key to continue...");
					Application.input.next();
				}
				break;
			case 2://Update Users
				{
					User user= null;
					int user_idx= -1;
					int len= Application.userDataHandler.getLength();
					if(len==0)
					{
						System.out.println("\033[33mNo Registered Users Found!\033[0m\nEnter any key to continue...");
						Application.input.next();
						continue menu;
					}
					System.out.print("\033[H\033[2J");
					System.out.flush();
					// System.out.println("=====================================");
					// System.out.print("|| \u001B[43m"+"Registered Users\u001B[0m\t\t\t\t||\n" +
					//         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
					//         "|| \u001B[43m"+"USERNAME\tTYPE\u001B[0m\t\t\t\t||\n" +
					//         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n");
					System.out.print(
						"Registered Users\n"+
						"--------------------------------\n"+
						"USERNAME\tTYPE\n"+
						"--------------------------------\n"
					);
					for(int k=0;k<len;++k)
					{
						user= Application.userDataHandler.get(k);
						System.out.println(user.getUsername()+"\t"+user.getUserType());
					}
					// System.out.println("=====================================");
					while(true)
					{
						System.out.print("Username: ");
						String uname= Application.input.next();
						boolean found= false;
						for(int k=0;k<len;++k)
						{
							user= Application.userDataHandler.get(k);
							if(user.getUsername().equals(uname))
							{
								user_idx= k;
								found= true;
								break;
							}
						}
						if(!found)
						{
							System.out.println("\033[31mUser not found!\033[0m");
							System.out.print("\033[33mTry again? [Y/N]:\033[0m ");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}
					while(true)
					{
						// System.out.println("=====================================");
						// System.out.println("|| \u001B[43m"+"What to modify?\u001B[0m\t\t\t\t||\n" +
						//         "|| \u001B[43m"+"1) Username\u001B[0m\t\t\t\t\t||\n" +
						//         "|| \u001B[43m"+"2) Type\u001B[0m\t\t\t\t\t\t||\n" +
						//         "|| \u001B[43m"+"3) Password\u001B[0m\t\t\t\t\t||\n" +
						//         "|| \u001B[43m"+"4) Cancel\u001B[0m\t\t\t\t\t||\n");
						System.out.print(
							"What to modify?\n"+
							"1. Username\n"+
							"2. Type\n"+
							"3. Password\n"+
							"4. Cancel\n"+
							"input>> "
						);
						try
						{
							choice= Application.input.nextInt();
						}
						catch(InputMismatchException e)
						{
							System.out.println("|| \033[31m"+"Invalid input!\033[0m\t\t\t\t\t||\n");
							Application.input.next();//consume invalid input from Scanner buffer
							continue;
						}
						break;
					}
					switch(choice)
					{
					case 1://Modify username  
						// System.out.println("=====================================");
						System.out.println("|| \u001B[43m"+"New Username: \u001B[0m\t\t\t\t||\n");
						String uname= Application.input.next();
						String oldname= user.getUsername();
						user.setUsername(uname);
						Application.userDataHandler.update(user_idx, user);

						// System.out.println("|| \033[33m"+"\""+oldname+"\" \033[32m was successfully modified to \"\033[0m"+uname+"\033[33m\"!\033[0m\t||\n");
						// System.out.println("=====================================");
						System.out.println("\033[33m\"\033[0m"+oldname+"\" \033[32m was successfully modified to \"\033[0m"+uname+"\033[33m\"!\033[0m\nEnter any key to continue...");
						Application.input.next();
						break;
					case 2://Modify usertype
						String utype_str;
						while(true)
						{
							System.out.println("=====================================");
							System.out.println("|| \u001B[43m"+"User Type [Admin/Employee]: \u001B[0m\t\t\t||\n");
							utype_str= Application.input.next();
							if(utype_str.equals("Admin")||utype_str.equals("admin")||utype_str.equals("A")||utype_str.equals("a"))
								user.setUserType(User.utype.admin);
							else if(utype_str.equals("Employee")||utype_str.equals("employee")||utype_str.equals("E")||utype_str.equals("e"))
								user.setUserType(User.utype.employee);
							else
							{
								System.out.println("\033[31mInvalid User Type!\033[0m");
								System.out.print("\033[33mTry again? [Y/N]: \033[0m");
								String retry= Application.input.next();
								if(retry.equals("Y")||retry.equals("y"))
									continue;
								else
									continue menu;
							}
							break;
						}
						Application.userDataHandler.update(user_idx, user);
						// System.out.println("|| \033[33m"+"\""+user.getUsername()+"\" \033[32m was successfully modified to \"\033[0m"+utype_str+"\033[33m\"!\033[0m\t||\n");
						System.out.println("\033[33m\"\033[0m"+user.getUsername()+"\" \033[32m was successfully promoted to \"\033[0m"+utype_str+"\033[33m\"!\033[0m\nEnter any key to continue...");
						Application.input.next();
						break;
					case 3://Modify password
						String newpword;
						while(true)
						{
							System.out.print("Old Password: ");
							String oldpword= Application.input.next();
							if(!oldpword.equals(user.getPassword()))
							{
								System.out.println("\033[31mIncorrect Password!\033[0m");
								System.out.print("\033[33mTry again? [Y/N]: \033[0m");
								String retry= Application.input.next();
								if(retry.equals("Y")||retry.equals("y"))
									continue;
								else
									continue menu;
							}
							break;
						}
						while(true)
						{
							System.out.print("New Password: ");
							newpword= Application.input.next();
							if(newpword.length()<8)
							{
								System.out.println("\033[31mPassword must be at least 8 character long!\033[0m");
								System.out.print("\033[33mTry again? [Y/N]: \033[0m");
								String retry= Application.input.next();
								if(retry.equals("Y")||retry.equals("y"))
									continue;
								else
									continue menu;
							}
							break;
						}
						while(true)
						{
							System.out.print("Confirm Password: ");
							String cpword= Application.input.next();
							if(!newpword.equals(cpword))
							{
								System.out.println("\033[31mPassword Mismatch!\033[0m");
								System.out.print("\033[33mTry again? [Y/N]: \033[0m");
								String retry= Application.input.next();
								if(retry.equals("Y")||retry.equals("y"))
									continue;
								else
									continue menu;
							}
							break;
						}
						user.setPassword(newpword);
						Application.userDataHandler.update(user_idx, user);
						System.out.println("\033[32mPassword was successfully modified!\033[0m\nEnter any key to continue...");
						Application.input.next();
					case 4:
						continue menu;
					default:
						System.out.println("\033[31mInvalid Operation!\033[0m");
						break;
					}
				}
				break;
			case 3://Delete Users
				{
					User user= null;
					int user_idx= -1;
					int len= Application.userDataHandler.getLength();

					if(len==0)
					{
						System.out.println("\033[33mNo Registered Users Found!\033[0m\nEnter any key to continue...");
						Application.input.next();
						continue menu;
					}
					System.out.print("\033[H\033[2J");
					System.out.flush();
					// System.out.println("=====================================");
					// System.out.print("|| \u001B[43m"+"Registered Users\u001B[0m\t\t\t\t||\n" +
					//         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
					//         "|| \u001B[43m"+"USERNAME\tTYPE\u001B[0m\t\t\t\t||\n" +
					//         "||");
					System.out.print(
						"Registered Users\n"+
						"--------------------------------\n"+
						"USERNAME\tTYPE\n"+
						"--------------------------------\n"
					);
					for(int k=0;k<len;++k)
					{
						user= Application.userDataHandler.get(k);
						System.out.println(user.getUsername()+"\t"+user.getUserType());
					}
					// System.out.println("=====================================");
					while(true)
					{
						System.out.println("|| \u001B[43m"+"Username: \u001B[0m\t\t\t\t||\n");
						String uname= Application.input.next();
						boolean found= false;
						for(int k=0;k<len;++k)
						{
							user= Application.userDataHandler.get(k);
							if(user.getUsername().equals(uname))
							{
								user_idx= k;
								found= true;
								break;
							}
						}
						if(!found)
						{
							// System.out.println("|| \033[31m"+"User not found!\033[0m\t\t\t\t||\n");
							// System.out.println("|| \033[31m"+"Try again? [Y/N]: \033[0m\t\t\t\t||\n");
							System.out.println("\033[31mUser not found!\033[0m");
							System.out.print("\033[33mTry again? [Y/N]: \033[0m");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}
					// System.out.println("|| \u001B[43m"+"Are you sure you want to \033[31mDELETE\033[0m \""+user.getUsername()+"\"? [Y/N]\u001B[0m\t||\n");
					System.out.print("Are you sure you want to \033[31mDELETE\033[0m \""+user.getUsername()+"\"? [Y/N]: ");
					String confirm= Application.input.next();
					if(!confirm.equals("Y") && !confirm.equals("y"))//Don't Delete
						continue menu;
					Application.userDataHandler.delete(user_idx);//delete user
					String uname= user.getUsername();
					int
						len_employees= Application.employeeDataHandler.getLength(),
						len_tasks= Application.taskDataHandler.getLength(),
						len_projects= Application.projectDataHandler.getLength();

					for(int k=0;k<len_employees;++k)//delete from employees
					{
						Employee employee= Application.employeeDataHandler.get(k);
						if(employee.getUsername().equals(uname))
						{
							Application.employeeDataHandler.delete(k);
							break;
						}
					}
					for(int k=0;k<len_tasks;++k)//nullify assigned tasks
					{
						Task task= Application.taskDataHandler.get(k);
						if(task.getAssignedEmployee().getUsername().equals(uname))
						{
							task.setAssignedEmployee(null);
							Application.taskDataHandler.update(k, task);
						}
					}
					for(int k=0;k<len_projects;++k)//nullify any assigned projects 
					{
						Project project= Application.projectDataHandler.get(k);
						if(project.getLeader().getUsername().equals(uname))
						{
							project.setLeader(null);
							Application.projectDataHandler.update(k, project);
						}
					}
					// System.out.println("|| \033[33m"+"\""+user.getUsername()+"\" \033[32m was successfully removed!\033[0m\t||\n");
					System.out.println("\033[33m\"\033[0m"+user.getUsername()+"\" \033[32m was successfully removed!\033[0mEnter any key to continue...");
					Application.input.next();
				}
				break;
			case 4:
				exit= true;
				break;
			default:
				System.out.println("\033[31mInvalid Operation!\033[0m");
			}
		}
	}
	public void manageEmployees() throws IOException
	{
		int choice= 0;
		boolean exit= false;
		menu:
		while(!exit)
		{
			System.out.print("\033[H\033[2J");
			System.out.flush();
			// System.out.println("\u001B[1m" + "\n ---------------------Manage Employees---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
			// System.out.println("==========================================================");
			// System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
			//         "|| 1)\u001B[35m\tAdd Employees.\u001B[0m\t\t\t\t||\n" +
			//         "|| 2)\u001B[35m\tUpdate Employees.\u001B[0m\t\t\t\t||\n" +
			//         "|| 3)\u001B[35m\tDelete Employees.\u001B[0m\t\t\t\t||\n" + 
			//         "|| 4)\u001B[35m\tBack.\u001B[0m\t\t\t\t\t\t||");
			System.out.print(
				"Managing Employees..\n"+
				"1.Add  2.Update  3.Delete  4.Back\n"+
				"input>> ");
			try
			{
				choice= Application.input.nextInt();    
			}
			catch(InputMismatchException e)
			{
				System.out.println("\033[31mInvalid Operation!\033[0m");
				Application.input.next();//consume invalid input from Scanner buffer
				continue;
			}
			switch(choice) {
			case 1://Add Employees
				{
					User user= null;
					int
						count_users= Application.userDataHandler.getLength(),
						count_employees= Application.employeeDataHandler.getLength();
					if(count_users==0)
					{
						System.out.println("\033[33mNo Registered Users Found!\033[0m\nEnter any key to continue...");
						Application.input.next();
						continue menu;
					}
					boolean unregistered= false;
					for(int i=0;i<count_users;i++)
					{
						boolean found= false;
						User user_registered= Application.userDataHandler.get(i);
						if(user_registered.getUserType()!=User.utype.employee)
							continue;
						for(int j=0;j<count_employees;j++)
						{
							Employee employee_registered= Application.employeeDataHandler.get(j);
							if(employee_registered.getUsername().equals(user_registered.getUsername()))
							{
								found= true;
								break;
							}
						}
						if(!found)
						{
							unregistered= true;
							break;
						}
					}
					if(!unregistered)
					{
						System.out.println("\033[33mAll employee user accounts have already been approved!\033[0m\nEnter any key to continue...");
						Application.input.next();
						continue menu;
					}
					System.out.print("\033[H\033[2J");
					System.out.flush();
					// System.out.println("=====================================");
					// System.out.print("|| \u001B[43m"+"Registered Users\u001B[0m\t\t\t\t||\n" +
					//         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
					//         "|| \u001B[43m"+"USERNAME\tTYPE\u001B[0m\t\t\t\t||\n" +
					//         "||");
					System.out.print(
						"Registered Accounts\n"+
						"--------------------------------\n"+
						"USERNAME\tTYPE\n"+
						"--------------------------------\n"
					);
					for(int k=0;k<count_users;++k)
					{
						user= Application.userDataHandler.get(k);
						if(user.getUserType()!=User.utype.employee)
							continue;

						boolean duplicate= false;
						for(int i=0;i<count_employees;++i)
						{
							if(Application.employeeDataHandler.get(i).getUsername().equals(user.getUsername()))
							{
								duplicate= true;
								break;
							}
						}
						if(!duplicate)
							System.out.println(user.getUsername()+"\t"+user.getUserType());
					}
					// System.out.println("=====================================");
					while(true)
					{
						// System.out.println("|| \u001B[43m"+"Username: \u001B[0m\t\t\t\t||\n");
						System.out.print("Username: ");
						String uname= Application.input.next();
						boolean found= false;
						for(int k=0;k<count_users;++k)
						{
							user= Application.userDataHandler.get(k);
							if(user.getUsername().equals(uname))
							{
								found= true;
								break;
							}
						}
						if(!found)
						{
							// System.out.println("|| \033[31m"+"User not found!\033[0m\t\t\t\t||\n");
							// System.out.println("|| \033[31m"+"Try again? [Y/N]: \033[0m\t\t\t\t||\n");
							System.out.println("\033[31mUser not found!\033[0m");
							System.out.print("\033[33mTry again? [Y/N]: \033[0m");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}

					int empType_idx= -1;
					int count_emptypes= Application.empTypeDataHandler.getLength();
					if(count_emptypes!=0)
					{
						System.out.print(
							"Defined Employee Types\n"+
							"--------------------------------\n"+
							"NUM\tTYPE\tMANAGER\n"+
							"--------------------------------\n"
						);
						for(int k=0;k<count_emptypes;++k)
						{
							EmpType empType= Application.empTypeDataHandler.get(k);
							System.out.println((k+1)+"\t"+empType.getName()+"\t"+empType.isManager());
						}
						// System.out.println("=====================================");
						while(true)
						{
							System.out.print("Number: ");
							try
							{
								empType_idx= Application.input.nextInt()-1;
							}
							catch(InputMismatchException e)
							{
								System.out.println("\033[31mInvalid Input!\033[0m Please, enter valid type number!");
								Application.input.next();//consume invalid input from Scanner buffer
								continue;
							}
							if(empType_idx<0||empType_idx>=count_emptypes)
							{
								System.out.println("\033[31mNumber Out of Bounds!\033[0m");
								System.out.print("\033[33mTry again? [Y/N]:\033[0m ");
								String retry= Application.input.next();
								if(retry.equals("Y")||retry.equals("y"))
									continue;
								else
									continue menu;
							}
							break;
						}
					}
					EmpType empType= null;
					if(empType_idx!=-1)
						empType= Application.empTypeDataHandler.get(empType_idx);
					Employee employee= new Employee(user.getUsername(), user.getPassword(), User.utype.employee, empType);
					Application.employeeDataHandler.add(employee);
				}
				break;
			case 2://Update Employees
				{
					int count_employees= Application.employeeDataHandler.getLength();
				}
				//TODO: Update Employees
				break;
			case 3://Delete Employees
				{
					Employee employee= null;
					String uname;
					int employee_idx= -1;
					int len= Application.employeeDataHandler.getLength();

					if(len==0)
					{
						System.out.println("\033[33mNo Employees Yet!\033[0m\nEnter any key to continue...");
						Application.input.next();
						continue menu;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					System.out.print(
						"--------------------------------\n"+
						"\tRegistered Employees\n"+
						"USERNAME\tTYPE\n"
					);
					for(int k=0;k<len;++k)
					{
						employee= Application.employeeDataHandler.get(k);
						System.out.println(employee.getUsername()+"\t"+employee.getEmpType());
					}
					// System.out.println("=====================================");
					while(true)
					{
						System.out.println("|| \u001B[43m"+"Username: \u001B[0m\t\t\t\t||\n");
						uname= Application.input.next();
						boolean found= false;
						for(int k=0;k<len;++k)
						{
							employee= Application.employeeDataHandler.get(k);
							if(employee.getUsername().equals(uname))
							{
								employee_idx= k;
								found= true;
								break;
							}
						}
						if(!found)
						{
							System.out.println("|| \033[31m"+"User not found!\033[0m\t\t\t\t||\n");
							System.out.println("|| \033[31m"+"Try again? [Y/N]: \033[0m\t\t\t\t||\n");
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu;
						}
						break;
					}
					System.out.println("|| \u001B[43m"+"Are you sure you want to \033[31mDELETE\033[0m \""+employee.getUsername()+"\"? [Y/N]\u001B[0m\t||\n");
					String confirm= Application.input.next();
					if(!confirm.equals("Y") && !confirm.equals("y"))//Don't Delete
						continue menu;
					Application.employeeDataHandler.delete(employee_idx);//delete employee
					int
						len_users= Application.userDataHandler.getLength(),
						len_tasks= Application.taskDataHandler.getLength(),
						len_projects= Application.projectDataHandler.getLength();
					for(int k=0;k<len_users;++k)
					{
						User user= Application.userDataHandler.get(k);
						if(user.getUsername().equals(uname))//delete user account
						{
							Application.userDataHandler.delete(k);
							break;
						}
					}
					for(int k=0;k<len_tasks;++k)//nullify assigned tasks
					{
						Task task= Application.taskDataHandler.get(k);
						if(task.getAssignedEmployee().getUsername().equals(uname))
						{
							task.setAssignedEmployee(null);
							Application.taskDataHandler.update(k, task);
						}
					}
					for(int k=0;k<len_projects;++k)//nullify any assigned projects 
					{
						Project project= Application.projectDataHandler.get(k);
						if(project.getLeader().getUsername().equals(uname))
						{
							project.setLeader(null);
							Application.projectDataHandler.update(k, project);
						}
					}
					System.out.println("|| \033[33m"+"\""+employee.getUsername()+"\" \033[32m was successfully removed!\033[0m\t||\n");
				}
				break;
			case 4:
				exit= true;
				break;
			default:
				System.out.println("\033[31mInvalid Operation!\033[0m");
			}
		}
	}
	public void manageProjects() throws IOException
	{
		int choice= 0;
		boolean exit= false;
	menu_manageProjects:
		while(!exit)
		{
			System.out.print("\033[H\033[2J"); System.out.flush();
			System.out.print(
				"Managing Projects..\n"+
				"1.Add 2.Update 3.Delete 0.Back\n"+
				"input>> ");
			try
			{
				choice= Integer.parseInt(Application.input.nextLine());
			}
			catch(NumberFormatException e)
			{
				System.out.println("\033[31mIllegal Character!\033[0m");
				continue;
			}
			switch(choice)
			{
			case 0://Back
				exit= true;
				break;
			case 1://Add Projects
				{
					String project_name, project_description;
					int count_employees= Application.employeeDataHandler.getLength();
					Employee leader= null;

					while(true)//project name
					{
						boolean duplicate= false;
						int count_projects= Application.projectDataHandler.getLength();

						System.out.print("Project Name: ");
						project_name= Application.input.nextLine().trim();
						if(project_name.isBlank())
						{
							System.out.print(
								"\033[31mThe project must have a name!\033[0m\n"+
								"\033[33mTry Again? [Y/N]: \033[0m"
							);
							String retry= Application.input.nextLine();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							continue menu_manageProjects;
						}
						for(int k=0;k<count_projects;++k)
						{
							Project project= Application.projectDataHandler.get(k);
							if(project.getName().equals(project_name))
							{
								duplicate= true;
								break;
							}
						}
						if(duplicate)
						{
							System.out.print(
								"\033[31mProject with the same name already in progress!\033[0m\n"+
								"\033[33mTry again? [Y/N]: \033[0m"
							);
							String retry= Application.input.nextLine();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							continue menu_manageProjects;
						}
						break;
					}
					while(true)//project description
					{
						System.out.print("Project Description: ");
						project_description= Application.input.nextLine();
						if(project_description.isBlank())
						{
							System.out.print(
								"\033[31mThe project must have a description!\033[0m\n"+
								"\033[33mTry again? [Y/N]: \033[0m"
							);
							String retry= Application.input.nextLine();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							continue menu_manageProjects;
						}
						break;
					}
					if(count_employees!=0)//project leader
					{
						int leader_idx;
						System.out.print(
							"Available employees to assign a leader:\n"+
							"IDX\tNAME\tPOSITION\tMANAGER?\n"
						);
						for(int k=0;k<count_employees;++k)
						{
							Employee employee= Application.employeeDataHandler.get(k);
							System.out.printf("%d.\t%s\t%s\n", k+1, employee.getUsername(), employee.getEmpType());
						}
						System.out.println("0. Don\'t assign anyone yet");
						while(true)
						{
							System.out.print("Input>> ");
							try
							{
								leader_idx= Integer.parseInt(Application.input.nextLine())-1;
							}
							catch(NumberFormatException e)
							{
								System.out.println("\033[31mPlease select a valid number from the employees list!\033[0m");
								continue;
							}
							if(leader_idx==-1)
								break;
							if(leader_idx<0||leader_idx>=count_employees)
							{
								System.out.println("\033[31mPlease select a valid number from the employees list!\033[0m");
								continue;
							}
							leader= Application.employeeDataHandler.get(leader_idx);
							break;
						}
					}
					Project project= new Project(project_name, project_description, leader);
					Application.projectDataHandler.add(project);
					System.out.print(
						"\033[32mSuccessfully added project \""+project_name+"\"!\033[0m\n"+
						"Press any key to continue...\n"
					);
					Application.input.nextLine();
				}
				break;
			case 2://Update Projects
			menu_manageProjects_update:
				while(true)
				{
					Project project;
					int
						count_projects= Application.projectDataHandler.getLength(),
						project_idx= -1;

					if(count_projects==0)
					{
						System.out.print(
							"\033[33mNo Projects Found!\033[0m\n"+
							"Press any key to continue..."
						);
						Application.input.nextLine();
						break;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					System.out.print(
						"Projects in progress:\n"+
						"IDX\tNAME\tLEADER\tDESCRIPTION\n"
					);
					for(int k=0;k<count_projects;++k)
					{
						project= Application.projectDataHandler.get(k);
						System.out.printf("%d.\t%s\t%s\t%s\n", k+1, project.getName(), project.getLeader()==null?"\033[33mTBD\033[0m":project.getLeader().getUsername(), project.getDescription());
					}
					System.out.println("0. Back");
					while(true)
					{
						System.out.print("Update>> ");
						try
						{
							project_idx= Integer.parseInt(Application.input.nextLine())-1;
						}
						catch(NumberFormatException e)
						{
							System.out.println("\033[31mPlease select a valid number from the projects list!\033[0m");
							continue;
						}
						if(project_idx==-1)
							continue menu_manageProjects;
						if(project_idx<0||project_idx>=count_projects)
						{
							System.out.println("\033[31mPlease select a valid number from the projects list!\033[0m");
							continue;
						}
						project= Application.projectDataHandler.get(project_idx);
						while(true)
						{
							System.out.print("\033[H\033[2J"); System.out.flush();
							System.out.printf(
								"\t1. Name:        %s\n"+
								"\t2. Description: %s\n"+
								"\t3. Leader:      %s\n"+
								"\t0. Cancel\n"+
								"Modify>> ",
								project.getName(),
								project.getDescription(),
								project.getLeader()==null?"\033[33mTBD\033[0m":project.getLeader().getUsername()
							);
							try
							{
								choice= Integer.parseInt(Application.input.nextLine());
							}
							catch(NumberFormatException e)
							{
								System.out.println("\033[31mInvalid Operation!\033[0m");
								continue;
							}
							switch(choice)
							{
							case 0://Cancel
								continue menu_manageProjects_update;
							case 1://Modify Project Name
								while(true)
								{
									boolean duplicate= false;
									String newname;

									System.out.print("\tNew Name: ");
									newname= Application.input.nextLine().trim();
									if(newname.isBlank())
									{
										System.out.print(
											"\033[31mThe project must have a name!\033[0m\n"+
											"\033[33mTry Again? [Y/N]: \033[0m"
										);
										String retry= Application.input.nextLine();
										if(retry.equals("Y")||retry.equals("y"))
											continue;
										break;
									}
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
										System.out.print(
											"\033[31mProject with the same name already in progress!\033[0m\n"+
											"\033[33mTry again? [Y/N]: \033[0m"
										);
										String retry= Application.input.nextLine();
										if(retry.equals("Y")||retry.equals("y"))
											continue;
										break;
									}
									project.setName(newname);
									Application.projectDataHandler.update(project_idx, project);
									break;
								}
								break;
							case 2://Modify Project Description
								while(true)
								{
									System.out.print("New Description: ");
									String newdescription= Application.input.nextLine().trim();
									if(newdescription.isBlank())
									{
										System.out.print(
											"\033[31mThe project must have a brief description!\033[0m\n"+
											"\033[33mTry Again? [Y/N]: \033[0m"
										);
										String retry= Application.input.nextLine();
										if(retry.equals("Y")||retry.equals("y"))
											continue;
										break;
									}
									project.setDescription(newdescription);
									Application.projectDataHandler.update(project_idx, project);
									break;
								}
								break;
							case 3://Modify Project Leader
								{
									int
										leader_idx= -1,
										count_employees= Application.employeeDataHandler.getLength();
									
									if(count_employees==0)
									{
										System.out.print(
											"\033[33mNo employees available\033[0m\n"+
											"Press any key to continue...\n"
										);
										Application.input.nextLine();
										break;
									}
									System.out.print(
										"Available employees to assign a leader:\n"+
										"IDX\tNAME\tPOSITION\tMANAGER?\n"
									);
									for(int k=0;k<count_employees;++k)
									{
										Employee employee= Application.employeeDataHandler.get(k);
										System.out.printf("%d.\t%s\t%s\n", k+1, employee.getUsername(), employee.getEmpType());
									}
									System.out.println("0. Don\'t assign anyone");
									while(true)
									{
										System.out.print("Input>> ");
										try
										{
											leader_idx= Integer.parseInt(Application.input.nextLine())-1;
										}
										catch(NumberFormatException e)
										{
											System.out.println("\033[31mPlease select a valid number from the employees list!\033[0m");
											continue;
										}
										if(leader_idx==-1)
										{
											project.setLeader(null);
											Application.projectDataHandler.update(project_idx, project);
											break;
										}
										if(leader_idx<0||leader_idx>=count_employees)
										{
											System.out.println("\033[31mPlease select a valid number from the employees list!\033[0m");
											continue;
										}
										project.setLeader(Application.employeeDataHandler.get(leader_idx));
										Application.projectDataHandler.update(project_idx, project);
										break;
									}
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
			case 3://Delete Projects
				{
					Project project;
					int
						count_projects= Application.projectDataHandler.getLength(),
						count_tasks= Application.taskDataHandler.getLength(),//for cascade delete
						project_idx= -1;

					if(count_projects==0)
					{
						System.out.println("\033[33mNo Projects Found!\033[0m\nEnter any key to continue...");
						Application.input.nextLine();
						continue menu_manageProjects;
					}
					System.out.print("\033[H\033[2J");
					System.out.flush();
					System.out.print(
						"Projects in progress:\n"+
						"IDX\tNAME\tLEADER\tDESCRIPTION\n"
					);
					for(int k=0;k<count_projects;++k)
					{
						project= Application.projectDataHandler.get(k);
						System.out.println((k+1)+".\t"+project.getName()+"\t"+project.getLeader().getUsername()+"\t"+project.getDescription());
					}
					System.out.println("0. Cancel");
					while(true)
					{
						System.out.print("Delete>> ");
						try
						{
							project_idx= Integer.parseInt(Application.input.nextLine())-1;
						}
						catch(NumberFormatException e)
						{
							System.out.println("\033[31mPlease select a valid number from the projects list!\033[0m");
							continue;
						}
						if(project_idx==-1)
							break;
						if(project_idx<0||project_idx>=count_projects)
						{
							System.out.println("\033[31mPlease select a valid number from the projects list!\033[0m");
							continue;
						}
						project= Application.projectDataHandler.get(project_idx);
						System.out.print("Are you sure you want to \033[31mDELETE\033[0m \""+project.getName()+"\"? [Y/N]: ");
						String confirm= Application.input.nextLine();
						if(!confirm.equals("Y") && !confirm.equals("y"))//don't delete
							continue;
						Application.projectDataHandler.delete(project_idx);//delete project
						for(int k=0;k<count_tasks;++k)//delete all tasks associated with the project
						{
							Task task= Application.taskDataHandler.get(k);
							if(task.getProject()==project)
								Application.taskDataHandler.delete(k);
						}
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
		boolean exit= false;
	menu_manageEmptype:
		while(!exit)
		{
			System.out.print("\033[H\033[2J"); System.out.flush();
			System.out.print(
				"Managing Types of Employees..\n"+
				"1.Add  2.Update  3.Delete  0.Back\n"+
				"input>> "
			);
			try
			{
				choice= Integer.parseInt(Application.input.nextLine());
			}
			catch(NumberFormatException e)
			{
				System.out.println("\033[31mInvalid Operation!\033[0m");
				continue;
			}
			switch(choice)
			{
			case 0:
				exit= true;
				break;
			case 1://Add Employee Type
				{
					EmpType empType= null;
					String empString= null;
					
					while(true)
					{
						boolean duplicate= false;
						int count_emptypes= Application.empTypeDataHandler.getLength();
						System.out.print("New Type of Employee: ");
						empString= Application.input.nextLine();
						for(int k=0;k<count_emptypes;++k)
						{
							EmpType compare= Application.empTypeDataHandler.get(k);
							if(compare.getName().equals(empString))
							{
								duplicate= true;
								break;
							}
						}
						if(duplicate)
						{
							System.out.print(
								"\033[31mType with the same name already exists!\033[0m\n"+
								"\033[33mTry again? [Y/N]: \033[0m"
							);
							String retry= Application.input.next();
							if(retry.equals("Y")||retry.equals("y"))
								continue;
							else
								continue menu_manageEmptype;
						}
						break;
					}
					System.out.print("Categorize as manager? [y/N]: ");
					String isManager= Application.input.nextLine();
					if(isManager.equals("Y")||isManager.equals("y"))
						empType= new EmpType(empString, true);
					else
						empType= new EmpType(empString, false);
					Application.empTypeDataHandler.add(empType);
					System.out.print(
						"\033[32mSuccessfully added employee type \""+empString+"\"!\033[0m\n"+
						"Press any key to continue...\n"
					);
					Application.input.nextLine();
				}
				break;
			case 2://Update Employee Type
			menu_manageEmptype_update:
				while(true)
				{
					EmpType emptype= null;
					int emptype_idx= -1;
					int count_emptype= Application.empTypeDataHandler.getLength();

					if(count_emptype==0)
					{
						System.out.print(
							"\033[33mNo Employee Types Defined Yet!\033[0m\n"+
							"Press any key to continue...\n"
						);
						Application.input.nextLine();
						break;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					System.out.print(
						"Defined Employee Types:\n"+
						"IDX\tTYPE\tMANAGER?\n"
					);
					for(int k=0;k<count_emptype;++k)
					{
						emptype= Application.empTypeDataHandler.get(k);
						System.out.printf("%d.\t%s\t%s\n", k+1, emptype.getName(), emptype.isManager()?"Yes":"No");
					}
					System.out.println("0. Cancel");
					while(true)
					{
						System.out.print("Update>> ");
						try
						{
							emptype_idx= Integer.parseInt(Application.input.nextLine())-1;
						}
						catch(NumberFormatException e)
						{
							System.out.println("\033[31mPlease select a valid number from the type list!\033[0m");
							continue;
						}
						if(emptype_idx==-1)
							continue menu_manageEmptype;
						if(emptype_idx<0||emptype_idx>=count_emptype)
						{
							System.out.println("\033[31mPlease select a valid number from the type list!\033[0m");
							continue;
						}
						emptype= Application.empTypeDataHandler.get(emptype_idx);
						while(true)
						{
							System.out.print("\033[H\033[2J"); System.out.flush();
							System.out.printf(
								"1. Type    : %s\n"+
								"2. Manager?: %s\n"+
								"0. Cancel\n"+
								"Modify>> ",
								emptype.getName(),
								emptype.isManager()?"Yes":"No"
							);
							try
							{
								choice= Integer.parseInt(Application.input.nextLine());
							}
							catch(NumberFormatException e)
							{
								System.out.println("\033[31mInvalid Operation!\033[0m");
								continue;
							}
							switch(choice)
							{
							case 0:
								continue menu_manageEmptype_update;
							case 1://Modify Type
								while(true)
								{
									String newtype, oldtype= emptype.getName();
									int count_employees= Application.employeeDataHandler.getLength();
									boolean duplicate= false;

									System.out.print("New Type: ");
									newtype= Application.input.nextLine();
									
									for(int k=0;k<count_emptype;++k)
									{
										EmpType compare= Application.empTypeDataHandler.get(k);
										if(compare.getName().equals(newtype))
										{
											duplicate= true;
											break;
										}
									}
									if(duplicate)
									{
										System.out.print(
											"\033[31mType with the same name already in exists!\033[0m\n"+
											"\033[33mTry again? [Y/N]: \033[0m"
										);
										String retry= Application.input.nextLine();
										if(retry.equals("Y")||retry.equals("y"))
											continue;
										break;
									}
									emptype.setName(newtype);
									Application.empTypeDataHandler.update(emptype_idx, emptype);
									for(int k=0;k<count_employees;++k)//update all employees with this type
									{
										Employee employee= Application.employeeDataHandler.get(k);
										if(employee.getEmpType().getName().equals(oldtype))
										{
											employee.setEmpType(emptype);
											Application.employeeDataHandler.update(k, employee);
											System.out.printf("\033[33m\"%s\" has been promoted to \"%s\"!\033[0m\n", employee.getUsername(), newtype);
										}
									}
									System.out.print(
										"\033[33mSuccessfully updated employee type!\033[0m\n"+
										"Press any key to continue...\n"
									);
									Application.input.nextLine();
									break;
								}
								break;
							case 2://Modify Managerial Position
								{
									String isManager= null;
									System.out.print("Categorize as manager? [y/N]: ");
									isManager= Application.input.nextLine();
									if(isManager.equals("Y")||isManager.equals("y"))
										emptype.setManager(true);
									else
										emptype.setManager(false);
									Application.empTypeDataHandler.update(emptype_idx, emptype);
									System.out.print(
										"\033[32mSuccessfully modified managerial position!\033[0m\n"+
										"Press any key to continue...\n"
									);
									Application.input.nextLine();
								}
								break;
							default:
								System.out.println("\033[31mInvalid Operation!\033[0m");
								break;
							}
							break;
						}
						break;
					}
				}
			case 3://Delete Employee Type
				{
					EmpType emptype= null;
					int
						emptype_idx= -1,
						count_emptypes= Application.empTypeDataHandler.getLength(),
						count_employees= Application.employeeDataHandler.getLength();//for nullifying

					if(count_emptypes==0)
					{
						System.out.print(
							"\033[33mNo Employee Types Defined Yet!\033[0m\n"+
							"Enter any key to continue...\n"
						);
						Application.input.nextLine();
						break;
					}
					System.out.print("\033[H\033[2J"); System.out.flush();
					System.out.print(
						"Defined Employee Types:\n"+
						"IDX\tTYPE\tMANAGER?\n"
					);
					for(int k=0;k<count_emptypes;++k)
					{
						emptype= Application.empTypeDataHandler.get(k);
						System.out.printf("%d.\t%s\t%s\n", k+1, emptype.getName(), emptype.isManager()?"Yes":"No");
					}
					System.out.println("0. Cancel");
					while(true)
					{
						System.out.print("Delete>> ");
						try
						{
							emptype_idx= Integer.parseInt(Application.input.nextLine())-1;
						}
						catch(NumberFormatException e)
						{
							System.out.println("\033[31mPlease select a valid number from the type list!\033[0m");
							continue;
						}
						if(emptype_idx==-1)
							break;
						if(emptype_idx<0||emptype_idx>=count_emptypes)
						{
							System.out.println("\033[31mPlease select a valid number from the type list!\033[0m");
							continue;
						}
						emptype= Application.empTypeDataHandler.get(emptype_idx);
						System.out.printf("Are you sure you want to \033[31mDELETE\033[0m \"%s\" type? [y/N]: ", emptype.getName());
						String confirm= Application.input.nextLine();
						if(!confirm.equals("Y") && !confirm.equals("y"))//Don't Delete
							break;
						Application.empTypeDataHandler.delete(emptype_idx);//delete type
						for(int k=0;k<count_employees;++k)//nullify all employees with this type
						{
							Employee employee= Application.employeeDataHandler.get(k);
							if(employee.getEmpType().getName().equals(emptype.getName()));
							{
								employee.setEmpType(null);
								Application.employeeDataHandler.update(k, employee);
								System.out.printf("\033[33m\"%s\" type has been nullified!\033[0m\n", employee.getUsername());
							}
						}
						System.out.println("Press any key to continue...");
						Application.input.nextLine();
					}
				}
				break;
			default:
				System.out.println("\033[31mInvalid Operation!\033[0m");
			}
		}
	}
	public void manageTaskPhases() throws IOException
	{
		Task task= null;
		int task_idx= -1;
		int len= Application.taskDataHandler.getLength();

		if(len==0)
		{
			System.out.println("\033[33mNo Tasks Yet!\033[0m\nEnter any key to continue...");
			Application.input.next();
			return;//to menu
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.print(
			"Current Tasks\n"+
			"----------------------------------------------------------------\n"+
			"CODE\tTITLE\tPROJECT\tPRIORITY\tPHASE\tDESCRIPTION\tSTARTS\tENDS\tEST\tCREATOR\tASSIGNED\n"+
			"----------------------------------------------------------------\n"
		);
		for(int k=0;k<len;++k)
		{
			task= Application.taskDataHandler.get(k);
			System.out.println(
				task.getCode()+"\t"+
				task.getTitle()+"\t"+
				task.getProject()+"\t"+
				task.getPriority()+"\t"+
				task.getTaskPhase()+"\t"+
				task.getDescription()+"\t"+
				task.getStartDate()+"\t"+
				task.getEndDate()+"\t"+
				task.getEST()+"\t"+
				task.getCreator()+"\t"+
				task.getAssignedEmployee()+"\t"
			);
		}
		System.out.println("----------------------------------------------------------------");
		while(true)
		{
			System.out.print("Code: ");
			String code= Application.input.next();
			boolean found= false;
			for(int k=0;k<len;++k)
			{
				task= Application.taskDataHandler.get(k);
				if(task.getCode().equals(code))
				{
					task_idx= k;
					found= true;
					break;
				}
			}
			if(!found)
			{
				System.out.println("\033[31mTask not found!\033[0m");
				System.out.print("\033[33mTry again? [Y/N]:\033[0m ");
				String retry= Application.input.next();
				if(retry.equals("Y")||retry.equals("y"))
					continue;
				else
					return;//to main menu
			}
			break;
		}
		System.out.print("New Task Phase: ");
		String phase= Application.input.next();
		task.setTaskPhase(phase);
		Application.taskDataHandler.update(task_idx, task);
		System.out.println("\033[33m\""+task.getTitle()+"\"\'s phase was successfully modified to \""+task.getTaskPhase()+"\" in project \""+task.getProject()+"\"!\033[0m");
	}
}