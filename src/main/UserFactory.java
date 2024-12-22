package main;

import java.io.Serializable;
import java.util.List;

class UserFactory implements Serializable {

    public static boolean createUser(int userType, int id, String name, String password, double salary) {
        String userRecord = id + "=" + userType + "=" + name + "=" + password + "=" + salary;
        String authRecord = id + "=" + userType + "=" + password;

        if (!FileHandler.appendToFile("users.txt", userRecord)) {
            return false;
        }

        return FileHandler.appendToFile("authentication.txt", authRecord);
    }

    public static boolean updateUser(int userType, int userId, String name) {
        List<String> users = FileHandler.readFromFile("users.txt");
        boolean updated = false;

        for (int i = 0; i < users.size(); i++) {
            String[] userDetails = users.get(i).split("=");
            if (Integer.parseInt(userDetails[0]) == userId) {
                users.set(i, userId + "=" + userType + "=" + name + "=" + userDetails[3] + "=" + userDetails[4]);
                updated = true;
                break;
            }
        }

        if (updated) {
            return FileHandler.overwriteFile("users.txt", users);
        }
        return false;
    }

    public static boolean deleteUser(int userId) {
        List<String> users = FileHandler.readFromFile("users.txt");
        users.removeIf(user -> {
            String[] userDetails = user.split("=");
            return Integer.parseInt(userDetails[0]) == userId;
        });
        return FileHandler.overwriteFile("users.txt", users);
    }

    public static List<String> getAllUsers() {
        return FileHandler.readFromFile("users.txt");
    }
}
