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
        String[] parts=s.split("\t");
        if(parts.length>=4){
            utype cUserType;
            switch (parts[2]) {
                case "admin":
                    cUserType=utype.admin;
                    break;
                case "employee":
                    cUserType=utype.employee;
                    break;
                default:
                    throw new IllegalArgumentException("unknown value "+parts[2]);
            }
            Employee employee = new Employee(parts[0], parts[1], cUserType, Application.empTypeDataHandler.get(Integer.parseInt(parts[3])));
            if(parts.length>=5){
                for(String index : parts[4].split(",")){
                    if(!index.isEmpty()) {
                        Request request=Application.requestDataHandler.get(Integer.parseInt(index));
                        employee.getMissionRequests().add(request);
                        request.setEmployee(employee);
                    }
                }
            }
            if(parts.length>=6){
                for(String index : parts[5].split(",")){
                    if(!index.isEmpty()) {
                        Request request=Application.requestDataHandler.get(Integer.parseInt(index));
                        employee.getPermissionRequests().add(request);
                        request.setEmployee(employee);
                    }
                }
            }
            if(parts.length>=7){
                for(String index : parts[6].split(",")){
                    if(!index.isEmpty()) {
                        LeaveRequest request=Application.leaveRequestDataHandler.get(Integer.parseInt(index));
                        employee.getLeaveRequests().add(request);
                        request.setEmployee(employee);
                    }
                }
            }
            return employee;
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    @Override
    public String toString() {
        String s = username+"\t"+password+"\t"+userType+"\t"+Application.empTypeDataHandler.getIndex(empType)+"\t";
        for (Request missionRequest : missionRequests) {
            int missionRequestIndex=Application.requestDataHandler.getIndex(missionRequest);
            s+=missionRequestIndex+",";
        }
        s+="\t";
        for (Request permissionRequest : permissionRequests) {
            int permissionRequestIndex=Application.requestDataHandler.getIndex(permissionRequest);
            s+=permissionRequestIndex+",";
        }
        s+="\t";
        for (LeaveRequest leaveRequest : leaveRequests) {
            int leaveRequestIndex=Application.leaveRequestDataHandler.getIndex(leaveRequest);
            s+=leaveRequestIndex+",";
        }
        return s;
    }
}
