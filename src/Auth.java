public class Auth {
    public int id;
    public int userType;
    public String password;

    public Auth(int id, int userType, String password) {
        this.id = id;
        this.userType = userType;
        this.password = password;
    }

    public boolean login(int userType, int loginId, String password) {
        return this.id == loginId && this.password.equals(password) && this.userType == userType;
    }
}