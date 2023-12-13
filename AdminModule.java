import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminModule extends Module {
    AdminModule(User currentUser) {
        this.currentUser= currentUser;
    }
    @Override
    public void startModule() throws IOException
    {
        int choice= 0;
        boolean exit= false;
        while(!exit)
        {
            System.out.println("\u001B[1m" + "\n ---------------------Admin Module---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
            // System.out.println("==========================================================");
            // System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
            //         "|| 1)\u001B[35m\tManage Users.\u001B[0m\t\t\t\t\t||\n" +
            //         "|| 2)\u001B[35m\tManage Employees.\u001B[0m\t\t\t\t||\n" +
            //         "|| 3)\u001B[35m\tManage Projects.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 4)\u001B[35m\tManage Employee Type.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 5)\u001B[35m\tModify Task Phases.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 6)\u001B[35m\tLogout.\u001B[0m\t\t\t\t\t\t||");
            // System.out.println("==========================================================");
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
    public void manageUsers() throws IOException
    {
        int choice= 0;
        boolean exit= false;
        menu:
        while(!exit)
        {
            System.out.println("\u001B[1m" + "\n ---------------------Manage Users---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
            // System.out.println("==========================================================");
            // System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
            //         "|| 1)\u001B[35m\tAdd Users.\u001B[0m\t\t\t\t\t||\n" +
            //         "|| 2)\u001B[35m\tUpdate Users.\u001B[0m\t\t\t\t||\n" +
            //         "|| 3)\u001B[35m\tDelete Users.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 4)\u001B[35m\tBack.\u001B[0m\t\t\t\t\t\t||");
            // System.out.println("==========================================================");

            System.out.print("\033[H\033[2J");
            System.out.flush();
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
                // try
                {
                    String uname, pword, utype_str;
                    User.utype utype= User.utype.employee;

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
                    System.out.println("|| \u001B[43m"+"Username: \u001B[0m\t\t\t\t||\n");
                    uname= Application.input.next();

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
                // catch(InputMismatchException e)
                // {
                //     System.out.println("\033[31mInvalid input!\033[0m please try again.");
                //     Application.input.next();//consume invalid input from Scanner buffer
                // }
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
                    // System.out.println("=====================================");
                    // System.out.print("|| \u001B[43m"+"Registered Users\u001B[0m\t\t\t\t||\n" +
                    //         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
                    //         "|| \u001B[43m"+"USERNAME\tTYPE\u001B[0m\t\t\t\t||\n" +
                    //         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n");
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
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
                        System.out.println("\033[33mPassword was successfully modified!\033[0m\nEnter any key to continue...");
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
                    // System.out.println("=====================================");
                    // System.out.print("|| \u001B[43m"+"Registered Users\u001B[0m\t\t\t\t||\n" +
                    //         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
                    //         "|| \u001B[43m"+"USERNAME\tTYPE\u001B[0m\t\t\t\t||\n" +
                    //         "||");
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
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
                        if(employee.getUsername()==uname)
                        {
                            Application.employeeDataHandler.delete(k);
                            break;
                        }
                    }
                    for(int k=0;k<len_tasks;++k)//nullify assigned tasks
                    {
                        Task task= Application.taskDataHandler.get(k);
                        if(task.getAssignedEmployee().getUsername()==uname)
                        {
                            task.setAssignedEmployee(null);
                            Application.taskDataHandler.update(k, task);
                        }
                    }
                    for(int k=0;k<len_projects;++k)//nullify any assigned projects 
                    {
                        Project project= Application.projectDataHandler.get(k);
                        if(project.getLeader().getUsername()==uname)
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
            System.out.println("\u001B[1m" + "\n ---------------------Manage Employees---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
            // System.out.println("==========================================================");
            // System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
            //         "|| 1)\u001B[35m\tAdd Employees.\u001B[0m\t\t\t\t||\n" +
            //         "|| 2)\u001B[35m\tUpdate Employees.\u001B[0m\t\t\t\t||\n" +
            //         "|| 3)\u001B[35m\tDelete Employees.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 4)\u001B[35m\tBack.\u001B[0m\t\t\t\t\t\t||");
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
                    int len= Application.employeeDataHandler.getLength();

                    if(len==0)
                    {
                        System.out.println("\033[33mNo Employees Yet!\033[0m\nEnter any key to continue...");
                        Application.input.next();
                        continue menu;
                    }
                    // System.out.println("=====================================");
                    // System.out.println("|| \u001B[43m"+"Registered Employees\u001B[0m\t\t\t\t||\n" +
                    //         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
                    //         "|| \u001B[43m"+"USERNAME\tTYPE\u001B[0m\t\t\t\t||\n" +
                    //         "||");
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
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
                    System.out.println("=====================================");
                    while(true)
                    {
                        System.out.println("|| \u001B[43m"+"Username: \u001B[0m\t\t\t\t||\n");
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
                    Application.employeeDataHandler.delete(employee_idx);//TODO: delete from users?
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
        while(!exit)
        {
            System.out.println("\u001B[1m" + "\n ---------------------Manage Projects---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
            // System.out.println("==========================================================");
            // System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
            //         "|| 1)\u001B[35m\tAdd Projects.\u001B[0m\t\t\t\t||\n" +
            //         "|| 2)\u001B[35m\tUpdate Projects.\u001B[0m\t\t\t\t||\n" +
            //         "|| 3)\u001B[35m\tDelete Projects.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 4)\u001B[35m\tBack.\u001B[0m\t\t\t\t\t\t||");
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
    public void manageEmpType() throws IOException
    {
        int choice= 0;
        boolean exit= false;
        menu:
        while(!exit)
        {
            System.out.println("\u001B[1m" + "\n ---------------------Manage Employee Type---------------------\n\u001B[8m" + "\t\t\t  D͓̽e͓̽d͓̽S͓̽e͓̽c͓̽\u001B[0m");
            // System.out.println("==========================================================");
            // System.out.println("|| \u001B[43m"+"Please choose one of the following options:\u001B[0m\t\t||\n" +
            //         "|| 1)\u001B[35m\tAdd Employee Type.\u001B[0m\t\t\t\t||\n" +
            //         "|| 2)\u001B[35m\tUpdate Employee Type.\u001B[0m\t\t\t\t||\n" +
            //         "|| 3)\u001B[35m\tDelete Employee Type.\u001B[0m\t\t\t\t||\n" + 
            //         "|| 4)\u001B[35m\tBack.\u001B[0m\t\t\t\t\t\t||");
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
                    System.out.print("|| \u001B[43m"+"New Type of Employee: \u001B[0m\t\t\t\t||\n");
                    String name= Application.input.next();
                    boolean isManager;
                    System.out.print("|| \u001B[43m"+"Categorize as manager? [true/false]: \u001B[0m\t\t\t||\n");
                    isManager= Application.input.nextBoolean();
                    EmpType empType= new EmpType(name, isManager);
                    Application.empTypeDataHandler.add(empType);
                }
                catch(InputMismatchException e)
                {
                    System.out.println("\033[31mInvalid input!\033[0m please try again.");
                    Application.input.next();//consume invalid input from Scanner buffer
                }
                break;
            case 2://Update Employee Type
                {
                    EmpType empType= null;
                    int idx= -1;
                    int len= Application.empTypeDataHandler.getLength();
                    if(len==0)
                    {
                        // System.out.println("|| \033[33m"+"No Employee Types Defined Yet\033[0m\t\t\t\t||\n");

                        System.out.println("\033[33mNo Employee Types Defined Yet!\033[0m\nEnter any key to continue...");
                        Application.input.next();
                        continue menu;
                    }
                    // System.out.print("=====================================\n");
                    // System.out.print("|| \u001B[43m"+"Defined Employee Types\u001B[0m\t\t\t\t||\n" +
                    //         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n" +
                    //         "|| \u001B[43m"+"TYPE\tMANAGER\u001B[0m\t\t\t\t||\n" +
                    //         "|| \u001B[43m"+"--------------------------------\u001B[0m\t\t\t||\n");
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.print(
                        "Defined Employee Types\n"+
                        "--------------------------------\n"+
                        "TYPE\tMANAGER\n"+
                        "--------------------------------\n"
                    );
                    for(int k=0;k<len;++k)
                    {
                        empType= Application.empTypeDataHandler.get(k);
                        System.out.println((k+1)+empType.getName()+"\t"+empType.isManager());
                    }
                    // System.out.println("=====================================");
                    while(true)
                    {
                        System.out.print("Number: ");
                        try
                        {
                            idx= Application.input.nextInt()-1;
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("\033[31mInvalid Input!\033[0m Please, enter valid type number!");
                            Application.input.next();//consume invalid input from Scanner buffer
                            continue;
                        }
                        if(idx<0||idx>=len)
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
                    empType= Application.empTypeDataHandler.get(idx);
                    menu_update:
                    while(true)
                    {
                        System.out.println("=====================================");
                        System.out.print("|| \u001B[43m"+"What to modify?\u001B[0m\t\t\t\t||\n" +
                                "|| \u001B[43m"+"1) Type\u001B[0m\t\t\t\t\t||\n" +
                                "|| \u001B[43m"+"2) Managerial Position\u001B[0m\t\t\t\t||\n" +
                                "|| \u001B[43m"+"3) Cancel\u001B[0m\t\t\t\t\t||\n");
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
                        switch(choice)
                        {
                        case 1://Modify Type
                            System.out.print("New Type: ");
                            String type= Application.input.next();
                            String oldtype= empType.getName();
                            empType.setName(type);
                            Application.empTypeDataHandler.update(idx, empType);//TODO: cascade update to all employees?
                            System.out.println("|| \033[33m"+"\""+oldtype+"\" \033[32m was successfully modified to \"\033[0m"+type+"\033[33m\"!\033[0m\t||\n");
                            break;
                        case 2://Modify Managerial Position
                            {
                                boolean isManager= false;
                                while(true)
                                {
                                    System.out.print("|| \u001B[43m"+"Categorize as manager? [true/false]: \u001B[0m\t\t\t||\n");
                                    try
                                    {
                                        isManager= Application.input.nextBoolean();
                                    }
                                    catch(InputMismatchException e)
                                    {
                                        Application.input.next();//consume invalid input from Scanner buffer
                                        System.out.println("\033[31mInvalid input!\033[0m please try again.");
                                        System.out.print("\033[33mTry again? [Y/N]: \033[0m");
                                        String retry= Application.input.next();
                                        if(retry.equals("Y")||retry.equals("y"))
                                            continue;
                                        continue menu_update;
                                    }
                                    break;
                                }
                                empType.setManager(isManager);
                                Application.empTypeDataHandler.update(idx, empType);
                                System.out.println("|| \033[33m"+"\""+empType.getName()+"\" \033[32m managerial position was successfully changed!\033[0m\t||\n");
                            }
                            break;
                        case 3:
                            continue menu;
                        default:
                            System.out.println("\033[31mInvalid Operation!\033[0m");
                            break;
                        }
                    }
                }
            case 3://Delete Employee Type
                {
                    EmpType empType= null;
                    int idx= -1;
                    int len= Application.empTypeDataHandler.getLength();

                    if(len==0)
                    {
                        System.out.println("\033[33mNo Employee Types Defined Yet!\033[0m\nEnter any key to continue...");
                        Application.input.next();
                        continue menu;
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.print(
                        "Different Employee Types\n"+
                        "--------------------------------\n"+
                        "NUMBER\tTYPE\tMANAGER?\n"+
                        "--------------------------------\n"
                    );
                    for(int k=0;k<len;++k)
                    {
                        empType= Application.empTypeDataHandler.get(k);
                        System.out.println((k+1)+"\t"+empType.getName()+"\t"+empType.isManager());
                    }
                    System.out.println("--------------------------------");
                    while(true)
                    {
                        System.out.print("Number: ");
                        try
                        {
                            idx= Application.input.nextInt()-1;
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("\033[31mInvalid Input!\033[0m Please, enter valid type number!");
                            Application.input.next();//consume invalid input from Scanner buffer
                            continue;
                        }
                        if(idx<0||idx>=len)
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
                    empType= Application.empTypeDataHandler.get(idx);
                    System.out.print("Are you sure you want to \033[31mDELETE\033[0m \""+empType.getName()+"\" Employee Type? [Y/N]: ");
                    String confirm= Application.input.next();
                    if(!confirm.equals("Y") && !confirm.equals("y"))//Don't Delete
                        continue menu;
                    Application.employeeDataHandler.delete(idx);//TODO: nullify all employees with this type
                    System.out.println("\033[33m\"\033[0m"+empType.getName()+"\" \033[32m was successfully undefined!\033[0m");
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
    public static void main(String[] args) throws IOException
    {
    	try
    	{
    		Application.initializeData();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	Application.input= new Scanner(System.in);
    	AdminModule adminModule= new AdminModule(null);
    	adminModule.startModule();
    }
}