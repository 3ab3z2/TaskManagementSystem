public class Request implements LoadSave {
    Employee employee;
    String reason;
    public enum Approval{pending, rejected, approved};
    Approval approval;
    public Request(Employee employee, String reason, Request.Approval approval) {
        this.employee = employee;
        this.reason = reason;
        this.approval = approval;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Approval getApproval() {
        return approval;
    }
    public void setApproval(Approval approval) {
        this.approval = approval;
    }
    @Override
    public String toString() {
        return reason+"\t"+approval;
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=2){
            Approval cApproval;
            switch (parts[1]) {
                case "approved":
                    cApproval=Approval.approved;
                    break;
                case "pending":
                    cApproval=Approval.pending;
                    break;
                case "rejected":
                    cApproval=Approval.rejected;
                    break;
                case "null":
                    cApproval=null;
                    break;
                default:
                    throw new IllegalArgumentException("unknown value "+parts[1]);
            }
            return new Request(null, parts[0], cApproval);
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    
}
