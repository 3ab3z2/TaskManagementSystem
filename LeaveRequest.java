import java.time.Period;

public class LeaveRequest extends Request {
    Period duration;
    //check enum if correct
    public enum LeaveType{sickleave, vacation, urgent};
    LeaveType leaveType;
    public LeaveRequest(Employee employee, String reason, Request.Approval approval, Period duration,
            LeaveRequest.LeaveType leaveType) {
        super(employee, reason, approval);
        this.duration = duration;
        this.leaveType = leaveType;
    }
    public Period getDuration() {
        return duration;
    }
    public void setDuration(Period duration) {
        this.duration = duration;
    }
    public LeaveType getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
    @Override
    public String toString() {
        return reason+"\t"+approval+"\t"+duration+"\t"+leaveType;
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=4){
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
            LeaveType cLeaveType;
            switch (parts[3]) {
                case "sickleave":
                    cLeaveType=LeaveType.sickleave;
                    break;
                case "vacation":
                    cLeaveType=LeaveType.vacation;
                    break;
                case "urgent":
                    cLeaveType=LeaveType.urgent;
                    break;
                case "null":
                    cLeaveType=null;
                    break;
                default:
                    throw new IllegalArgumentException("unknown value "+parts[3]);
            }
            return new LeaveRequest(null, parts[0], cApproval, (parts[2].equals("null")?null:Period.parse(parts[2])), cLeaveType);
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    
}
