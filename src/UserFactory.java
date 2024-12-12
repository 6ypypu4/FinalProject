import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserFactory {

    public static boolean createUser(int userType, int id, String name, String password, double salary) {
        String userRecord = id + "=" + userType + "=" + name + "=" + password + "=" + salary;
        String authRecord = id + "=" + userType + "=" + password;

        // Save user data to users.txt
        if (!saveToFile("src\\Data\\users.txt", userRecord)) {
            return false;
        }

        // Save authentication data to authentication.txt
        return saveToFile("src\\Data\\authentication.txt", authRecord);
    }

    private static boolean saveToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to " + filePath + ": " + e.getMessage());
            return false;
        }
    }
}