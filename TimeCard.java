import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeCard implements LoadSave {
    private Employee employee;
    private LocalDateTime attendance;
    private LocalTime departure;
    
    public TimeCard(Employee employee, LocalDateTime attendance, LocalTime departure) {
        this.employee = employee;
        this.attendance = attendance;
        this.departure = departure;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public LocalDateTime getAttendance() {
        return attendance;
    }
    public void setAttendance(LocalDateTime attendance) {
        this.attendance = attendance;
    }
    public LocalTime getDeparture() {
        return departure;
    }
    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }
    @Override
    public String toString() {
        return Application.employeeDataHandler.getIndex(employee)+"\t"+attendance+"\t"+departure;
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=3){
            return new TimeCard(((Integer.parseInt(parts[0])!=-1)?Application.employeeDataHandler.get(Integer.parseInt(parts[0])):null),(parts[1].equals("null")?null:LocalDateTime.parse(parts[1])), (parts[2].equals("null")?null:LocalTime.parse(parts[2])));
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    
}
