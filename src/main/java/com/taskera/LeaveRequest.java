package com.taskera;

public class LeaveRequest extends Request {
    int duration;
    //check enum if correct
    public enum LeaveType{sickleave, vacation, urgent};
    LeaveType leaveType;
    public LeaveRequest(Employee employee, String reason, Request.Approval approval, int duration,
            LeaveRequest.LeaveType leaveType) {
        super(employee, reason, approval);
        this.duration = duration;
        this.leaveType = leaveType;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public LeaveType getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
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
