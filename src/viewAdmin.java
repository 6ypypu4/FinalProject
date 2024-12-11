import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class viewAdmin extends viewEmployee{
    private static int    adminId;
    private static Admin  admin;
    private static int    languageChoice;

    private HashMap<String, String> messages = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private MessageLoader messageLoader = new MessageLoader();

    public viewAdmin(int adminId, int languageChoice) {
        viewAdmin.adminId = adminId;
        viewAdmin.languageChoice = languageChoice;
    }

    private void loadMessages() {
        if (languageChoice == 1) {
            messageLoader.loadMessages("src\\Translations\\viewAdmin\\english.txt", messages);
        } else if (languageChoice == 2) {
            messageLoader.loadMessages("src\\Translations\\viewAdmin\\russian.txt", messages);
        } else if (languageChoice == 3) {
            messageLoader.loadMessages("src\\Translations\\viewAdmin\\kazakh.txt", messages);
        } else {
            System.out.println("Invalid language choice. Defaulting to English.");
            messageLoader.loadMessages("src\\Translations\\viewAdmin\\english.txt", messages);
        }
    }

    public void start() {
        loadMessages();
        createAdminFromFile("src\\Data\\users.txt");
        System.out.println(messages.get("admin_view"));
        System.out.println(messages.get("admin_details") + ": " + admin.getName() + ", " + messages.get("salary") + ": " + admin.getSalary());
        boolean running = true;

        while (running) {
            System.out.println("1. " + messages.get("create_user"));
            System.out.println("2. " + messages.get("exit"));
            System.out.print(messages.get("enter_choice") + " ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            if (choice == 1) {
                createUser();
            } else if (choice == 2) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private void createUser() {
        System.out.print(messages.get("enter_user_id") + " ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        System.out.print(messages.get("enter_user_type") + " ");
        int userType = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        System.out.print(messages.get("enter_password") + " ");
        String name = scanner.nextLine();

        System.out.print(messages.get("enter_password") + " ");
        String password = scanner.nextLine();

        admin.createUser(userType, userId, name, password);


    }

    public static void createAdminFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("=");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String password = parts[2].trim();
                    double salary = Double.parseDouble(parts[3].trim());
                    System.out.println(name);

                   // if(id == adminId){
                        admin = new Admin(id, name, password ,salary);
                        return;
                    //}
                    //}
                } else {
                    System.out.println("Invalid admin data format.");
                }
            } else {
                System.out.println("Admin data file is empty.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading admin data file: " + e.getMessage());
        }
    }
}
