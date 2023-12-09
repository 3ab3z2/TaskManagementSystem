import java.util.ArrayList;

public class Employee extends User {
    EmpType empType;
    ArrayList<Request> missionRequests;
    ArrayList<Request> permissionRequests;
    ArrayList<LeaveRequest> leaveRequests;
    public Employee(String username, String password, User.utype userType, EmpType empType,
            ArrayList<Request> missionRequests, ArrayList<Request> permissionRequests,
            ArrayList<LeaveRequest> leaveRequests) {
        super(username, password, userType);
        this.empType = empType;
        this.missionRequests = missionRequests;
        this.permissionRequests = permissionRequests;
        this.leaveRequests = leaveRequests;
    }
    public Employee(String username, String password, User.utype userType, EmpType empType) {
        this(username,password,userType,empType,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }
    public EmpType getEmpType() {
        return empType;
    }
    public void setEmpType(EmpType empType) {
        this.empType = empType;
    }
    public ArrayList<Request> getMissionRequests() {
        return missionRequests;
    }
    public void setMissionRequests(ArrayList<Request> missionRequests) {
        this.missionRequests = missionRequests;
    }
    public ArrayList<Request> getPermissionRequests() {
        return permissionRequests;
    }
    public void setPermissionRequests(ArrayList<Request> permissionRequests) {
        this.permissionRequests = permissionRequests;
    }
    public ArrayList<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }
    public void setLeaveRequests(ArrayList<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return super.fromString(s);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
