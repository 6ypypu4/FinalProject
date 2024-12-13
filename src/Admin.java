import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends Employee {

    public Admin(int id, String name, String password, double salary) {
        super(id, name, password, salary);
    }


    //id=usertype=name=password=salary
    public boolean createUser(int userType, int userId, String name, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Data\\users.txt", true))) {
            writer.write(userId + "=" + userType + "=" + name + "=" + password + "=" + "0"); // "0" likely represents initial status/points
            writer.newLine();

            try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("src\\Data\\authentication.txt", true))) {
                writer1.write(userId + "=" + userType + "=" + password); // Note: writer1 used here
                writer1.newLine();
                return true;
            } catch (IOException _) {
            }
        } catch (IOException e) {
            // Handle IOException more specifically
            System.err.println("Error writing to users.txt: " + e.getMessage());
        }
        return false;
    }

}
