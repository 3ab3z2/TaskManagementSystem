public class AdminModule extends Module {
    AdminModule(User currentUser) {
        this.currentUser= currentUser;
    }
    @Override
    public void startModule(){
        int choice= 0;
        boolean exit= false;
        while(!exit){
            System.out.println("Admin Module");
            System.out.println("\t1.Manage Users");
            System.out.println("\t2.Manage Employees");
            System.out.println("\t3.Manage Projects");
            System.out.println("\t4.Manage Employee Type");
            System.out.println("\t5.Manage Task Phases");
            System.out.println("\t6.Logout");
            choice= Application.input.nextInt();
            switch(choice) {
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
                System.out.println("Illegal Operation!\n");
            }
        }
    }
    public void manageUsers(){
        int choice= 0;
        boolean exit= false;
        while(!exit){
            System.out.println("Managing Users..");
            System.out.println("\t1.Add");
            System.out.println("\t2.Update");
            System.out.println("\t3.Delete");
            System.out.println("\t4.Back");
            choice= Application.input.nextInt();
            switch(choice) {
            case 1:
                //TODO
                break;
            case 2:
                //TODO
                break;
            case 3:
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("Illegal Operation!\n");
            }
        }
    }
    public void manageEmployees() {
        int choice= 0;
        boolean exit= false;
        while(!exit){
            System.out.println("Managing Employees..");
            System.out.println("\t1.Add");
            System.out.println("\t2.Update");
            System.out.println("\t3.Delete");
            System.out.println("\t4.Back");
            choice= Application.input.nextInt();
            switch(choice) {
            case 1:
                //TODO
                break;
            case 2:
                //TODO
                break;
            case 3:
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("Illegal Operation!\n");
            }
        }
    }
    public void manageProjects() {
        int choice= 0;
        boolean exit= false;
        while(!exit){
            System.out.println("Managing Projects..");
            System.out.println("\t1.Add");
            System.out.println("\t2.Update");
            System.out.println("\t3.Delete");
            System.out.println("\t4.Back");
            choice= Application.input.nextInt();
            switch(choice) {
            case 1:
                //TODO
                break;
            case 2:
                //TODO
                break;
            case 3:
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("Illegal Operation!\n");
            }
        }
    }
    public void manageEmpType() {
        int choice= 0;
        boolean exit= false;
        while(!exit){
            System.out.println("Managing Types of Employees..");
            System.out.println("\t1.Add");
            System.out.println("\t2.Update");
            System.out.println("\t3.Delete");
            System.out.println("\t4.Back");
            choice= Application.input.nextInt();
            switch(choice) {
            case 1:
                //TODO
                break;
            case 2:
                //TODO
                break;
            case 3:
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("Illegal Operation!\n");
            }
        }
    }
    public void manageTaskPhases() {
        int choice= 0;
        boolean exit= false;
        while(!exit){
            System.out.println("Managing Task Phases..");
            System.out.println("\t1.Add");
            System.out.println("\t2.Update");
            System.out.println("\t3.Delete");
            System.out.println("\t4.Back");
            choice= Application.input.nextInt();
            switch(choice) {
            case 1:
                //TODO
                break;
            case 2:
                //TODO
                break;
            case 3:
                //TODO
                break;
            case 4:
                exit= true;
                break;
            default:
                System.out.println("Illegal Operation!\n");
            }
        }
    }
}
