package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class UserFactory implements Serializable {

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

    public static boolean updateUser(int userType, int userId, String name) {
        String userFilePath = "src\\Data\\users.txt";
        List<String> users = readFromFile(userFilePath);
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
            return overwriteFile(userFilePath, users);
        }
        return false;
    }

    public static boolean deleteUser(int userId) {
        String userFilePath = "src\\Data\\users.txt";
        List<String> users = readFromFile(userFilePath);

        users.removeIf(user -> {
            String[] userDetails = user.split("=");
            return Integer.parseInt(userDetails[0]) == userId;
        });

        return overwriteFile(userFilePath, users);
    }

    public static List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    private static boolean saveToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("\n" + data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to " + filePath + ": " + e.getMessage());
            return false;
        }
    }

    private static List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from " + filePath + ": " + e.getMessage());
        }
        return lines;
    }

    private static boolean overwriteFile(String filePath, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error overwriting " + filePath + ": " + e.getMessage());
            return false;
        }
    }
}
