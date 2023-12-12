public class EmpType implements LoadSave {
    String name;
    boolean isManager;
    public EmpType(String name, boolean isManager) {
        this.name = name;
        this.isManager = isManager;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }
    @Override
    public String toString() {
        return name+"\t"+isManager;
    }
    @Override
    public LoadSave fromString(String s) throws IllegalArgumentException {
        String[] parts=s.split("\t");
        if(parts.length>=2){
            return new EmpType(parts[0], Boolean.parseBoolean(parts[1]));
        }
        else throw new IllegalArgumentException("not enough arguments");
    }
    
}
