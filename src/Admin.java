public class Admin extends Employee {

    public Admin(int id, String name, String password, double salary) {
        super(id, name, password, salary);
    }

    // Method to create a user using UserFactory
    public boolean createUser(int userType, int userId, String name, String password) {
        return UserFactory.createUser(userType, userId, name, password, 0);
    }
}