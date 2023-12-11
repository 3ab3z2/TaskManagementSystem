package com.taskera;

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
        // TODO Auto-generated method stub
        return super.toString();
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
