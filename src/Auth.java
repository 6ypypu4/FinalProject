import Enums.UserType;

public class Auth {
    public int id;
    public UserType userType;
    public String password;

    public Auth(int id, UserType userType, String password) {
        this.id = id;
        this.userType = userType;
        this.password = password;
    }

    public boolean login(UserType userType, int loginId, String password) {
        return this.id == loginId && this.password.equals(password) && this.userType == userType;
    }
}