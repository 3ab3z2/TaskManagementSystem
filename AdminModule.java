import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class AdminModule extends Module {
    AdminModule(User currentUser) {
        this.currentUser= currentUser;
    }
    @Override
    public void startModule(){
        int choice= 0;
        boolean exit= false;
        while(!exit)
        {
            System.out.print(
                "Admin Module\n"+
                "\t1.Manage Users\n"+
                "\t2.Manage Employees\n"+
                "\t3.Manage Projects\n"+
                "\t4.Manage Employee Type\n"+
                "\t5.Modify Task Phases\n"+
                "\t6.Logout\n"+
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
            case 6:
                exit= true;
                break;
            default:
                System.out.println("\033[31mInvalid Operation!\033[0m");
            }
        }
    }
    public void manageUsers(){
        int choice= 0;
        boolean exit= false;
        menu:
        while(!exit)
        {
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
                try
                {
                    String uname, pword, utype_str;
                    User.utype utype= User.utype.employee;

                    while(true)
                    {
                        System.out.print("User Type [Admin/Employee]: ");
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

                    System.out.print("Username: ");
                    uname= Application.input.next();

                    while(true)
                    {
                        System.out.print("Password: ");
                        pword= Application.input.next();
                        if(pword.length()<8)
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
                        if(!pword.equals(cpword))
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

                    User user= new User(uname, pword, utype);
                    Application.userDataHandler.add(user);
                    System.out.println(uname+" \033[32mwas successfully added!\033[0m");
                }
                catch(InputMismatchException e)
                {
                    System.out.println("\033[31mInvalid input!\033[0m please try again.");
                    Application.input.next();//consume invalid input from Scanner buffer
                }
                catch(NoSuchElementException e)
                {
                    //TODO: this block can be removed
                }
                catch(IllegalStateException e)
                {
                    //TODO: handle closed scanner
                }
                catch(IOException e)
                {
                    System.out.println(
                        "\033[31mFATAL ERROR\033[0m: something went wrong with the Employee Type DataHandler!\n"+
                        e.getMessage()
                    );
                }
                break;
            case 2://Update Users
                {
                    User user= null;
                    int user_idx= -1;

                    System.out.print(
                        "--------------------------------\n"+
                        "\tRegistered Users\n"+
                        "Username\tType\n"
                    );
                    int len= Application.userDataHandler.getLength();
                    for(int k=0;k<len;++k)
                    {
                        user= Application.userDataHandler.get(k);
                        System.out.println(user.getUsername()+"\t"+user.getUserType());
                    }
                    System.out.println("--------------------------------");
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
                        System.out.print(
                            "What to modify?\n"+
                            "1. Username\n"+
                            "2. Type\n"+
                            "3. Password\n"+//?
                            "4. Cancel\n"+
                            "input>> "
                        );
                        try
                        {
                            choice= Application.input.nextInt();
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("\033[31mInvalid input!\033[0m please try again.");
                            Application.input.next();//consume invalid input from Scanner buffer
                            continue;
                        }
                        break;
                    }
                    switch(choice)
                    {
                    case 1://Modify username
                        System.out.print("New Username: ");
                        String uname= Application.input.next();
                        String oldname= user.getUsername();
                        user.setUsername(uname);
                        try
                        {
                            Application.userDataHandler.delete(user_idx);
                            Application.userDataHandler.add(user);
                            System.out.println("\033[33m\"\033[0m"+oldname+"\" \033[32m was successfully modified to \"\033[0m"+uname+"\033[33m\"!\033[0m");
                        }
                        catch(IOException e)
                        {
                            System.out.println(
                                "\033[31mFATAL ERROR\033[0m: something went wrong with the User DataHandler!\n"+
                                e.getMessage()
                            );
                        }
                        break;
                    case 2://Modify usertype
                        String utype_str;
                        while(true)
                        {
                            System.out.print("User Type [Admin/Employee]: ");
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
                        try
                        {
                            Application.userDataHandler.delete(user_idx);
                            Application.userDataHandler.add(user);
                            System.out.println("\033[33m\"\033[0m"+user.getUsername()+"\" \033[32m was successfully promoted to \"\033[0m"+utype_str+"\033[33m\"!\033[0m");
                        }
                        catch(IOException e)
                        {
                            System.out.println(
                                "\033[31mFATAL ERROR\033[0m: something went wrong with the User DataHandler!\n"+
                                e.getMessage()
                            );
                        }
                        break;
                    case 3://Modify password?
                        //TODO
                        break;
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

                    System.out.print(
                        "--------------------------------\n"+
                        "\tRegistered Users\n"+
                        "Username\tType\n"
                    );
                    int len= Application.userDataHandler.getLength();
                    for(int k=0;k<len;++k)
                    {
                        user= Application.userDataHandler.get(k);
                        System.out.println(user.getUsername()+"\t"+user.getUserType());
                    }
                    System.out.println("--------------------------------");
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
                    System.out.print("Are you sure you want to \033[31mDELETE\033[0m \""+user.getUsername()+"\"? [Y/N]");
                    String confirm= Application.input.next();
                    if(!confirm.equals("Y") && !confirm.equals("y"))//Don't Delete
                        continue menu;
                    try
                    {
                        Application.userDataHandler.delete(user_idx);
                        System.out.println("\033[33m\"\033[0m"+user.getUsername()+"\" \033[32m was successfully removed!\033[0m");
                    }
                    catch(IOException e)
                    {
                        System.out.println(
                            "\033[31mFATAL ERROR\033[0m: something went wrong with the User DataHandler!\n"+
                            e.getMessage()
                        );
                    }
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
    public void manageEmployees() {
        int choice= 0;
        boolean exit= false;
        menu:
        while(!exit)
        {
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
                //TODO
                break;
            case 2://Update Employees
                //TODO
                break;
            case 3://Delete Employees
                {
                    Employee employee= null;
                    int employee_idx= -1;

                    System.out.print(
                        "--------------------------------\n"+
                        "\tRegistered Employees\n"+
                        "Username\tType\n"
                    );
                    int len= Application.userDataHandler.getLength();
                    for(int k=0;k<len;++k)
                    {
                        employee= Application.employeeDataHandler.get(k);
                        System.out.println(employee.getUsername()+"\t"+employee.getEmpType());
                    }
                    System.out.println("--------------------------------");
                    while(true)
                    {
                        System.out.print("Username: ");
                        String uname= Application.input.next();
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
                            System.out.println("\033[31mEmployee not found!\033[0m");
                            System.out.print("\033[33mTry again? [Y/N]:\033[0m ");
                            String retry= Application.input.next();
                            if(retry.equals("Y")||retry.equals("y"))
                                continue;
                            else
                                continue menu;
                        }
                        break;
                    }
                    System.out.print("Are you sure you want to \033[31mDELETE\033[0m \""+employee.getUsername()+"\"? [Y/N]");
                    String confirm= Application.input.next();
                    if(!confirm.equals("Y") && !confirm.equals("y"))//Don't Delete
                        continue menu;
                    try
                    {
                        Application.employeeDataHandler.delete(employee_idx);
                        System.out.println("\033[33m\"\033[0m"+employee.getUsername()+"\" \033[32m was successfully removed!\033[0m");
                    }
                    catch(IOException e)
                    {
                        System.out.println(
                            "\033[31mFATAL ERROR\033[0m: something went wrong with the Employee DataHandler!\n"+
                            e.getMessage()
                        );
                    }
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
    public void manageProjects() {
        int choice= 0;
        boolean exit= false;
        while(!exit)
        {
            System.out.print(
                "Managing Projects..\n"+
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
            switch(choice) {
            case 1://Add Projects
                //TODO
                break;
            case 2://Update Projects
                //TODO
                break;
            case 3://Delete Projects
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("\033[31mInvalid Operation!\033[0m");
            }
        }
    }
    public void manageEmpType() {
        int choice= 0;
        boolean exit= false;
        while(!exit)
        {
            System.out.print(
                "Managing Types of Employees..\n"+
                "1.Add  2.Update  3.Delete  4.Back\n"+
                "input>> "
            );
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
            case 1://Add Employee Type
                try
                {
                    System.out.print("New Type of Employee: ");
                    String name= Application.input.next();
                    boolean isManager;
                    System.out.print("Manager? [true/false]: ");
                    isManager= Application.input.nextBoolean();
                    EmpType empType= new EmpType(name, isManager);
                    Application.empTypeDataHandler.add(empType);
                }
                catch(InputMismatchException e)
                {
                    System.out.println("\033[31mInvalid input!\033[0m please try again.");
                    Application.input.next();//consume invalid input from Scanner buffer
                }
                catch(NoSuchElementException e)
                {
                    //TODO: this block can be removed
                }
                catch(IllegalStateException e)
                {
                    //TODO: handle closed scanner
                }
                catch(IOException e)
                {
                    System.out.println(
                        "\033[31mFATAL ERROR\033[0m: something went wrong with the Employee Type DataHandler!\n"+
                        e.getMessage()
                    );
                }
                break;
            case 2://Update Employee Type
                //TODO
                break;
            case 3://Delete Employee Type
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("\033[31mInvalid Operation!\033[0m");
            }
        }
    }
    public void manageTaskPhases() {
        //TODO

        // int choice= 0;
        // boolean exit= false;
        // while(!exit){
        //     System.out.print(
        //         "Managing Task Phases..\n"+
        //         "1. Add  2.Update  3.Delete  4.Back\n"+
        //         "input>> ");
        //     try
        //     {
        //         choice= Application.input.nextInt();   
        //     }
        //     catch(InputMismatchException e)
        //     {
        //         System.out.println("\033[31mInvalid Operation!\n\033[0m");
        //         Application.input.next();//consume invalid input from Scanner buffer
        //         continue;
        //     }
        //     switch(choice) {
        //     case 1:
        //         break;
        //     case 2:
        //         break;
        //     case 3:
        //         break;
        //     case 4:
        //         exit= true;
        //         break;
        //     default:
        //         System.out.println("\033[31mInvalid Operation!\n\033[0m");
        //     }
        // }
    }
    // public static void main(String[] args) {
    // 	try
    // 	{
    // 		Application.initializeData();
    // 	}
    // 	catch(IOException e)
    // 	{
    // 		e.printStackTrace();
    // 	}
    // 	Application.input= new Scanner(System.in);
    // 	AdminModule adminModule= new AdminModule(null);
    // 	adminModule.startModule();
    // }
}
