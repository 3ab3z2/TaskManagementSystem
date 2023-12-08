public class User implements LoadSave {
    private String username;
    private String password;
    public enum utype{admin,employee};
    private utype userType;
    public User(String username, String password, User.utype userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public utype getUserType() {
        return userType;
    }
    public void setUserType(utype userType) {
        this.userType = userType;
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
    public boolean canlogin(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
}
