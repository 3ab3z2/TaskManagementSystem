import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
                    boolean goback= false;
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
                                goback= true;
                        }
                        break;
                    }
                    if(goback)
                        continue;//back to menu

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
                                goback= true;
                        }
                        break;
                    }
                    if(goback)
                        continue;//back to menu

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
                                goback= true;
                        }
                        break;
                    }
                    if(goback)
                        continue;//back to menu

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
                //TODO
                break;
            case 3://Delete Users
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
    public void manageEmployees() {
        int choice= 0;
        boolean exit= false;
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
        //         //TODO
        //         break;
        //     case 2:
        //         //TODO
        //         break;
        //     case 3:
        //         //TODO
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
    //     try
    //     {
    //         Application.initializeData();
    //     }
    //     catch(IOException e)
    //     {
    //         e.printStackTrace();
    //     }
    //     Application.input= new Scanner(System.in);
    //     AdminModule adminModule= new AdminModule(null);
    //     adminModule.startModule();
    // }
}
