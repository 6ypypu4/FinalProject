import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class viewTeacher extends viewEmployee {
    private static int    teacherId;
    private static Teacher teacher;
    private static int    languageChoice;

    private HashMap<String, String> messages = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private MessageLoader messageLoader = new MessageLoader();

    public viewTeacher(int teacherId, int languageChoice) {
        viewTeacher.teacherId = teacherId;
        viewTeacher.languageChoice = languageChoice;
    }

    protected void loadMessages() {
        if (languageChoice == 1) {
            messageLoader.loadMessages("src\\Translations\\viewTeacher\\english.txt", messages);
        } else if (languageChoice == 2) {
            messageLoader.loadMessages("src\\Translations\\viewTeacher\\russian.txt", messages);
        } else if (languageChoice == 3) {
            messageLoader.loadMessages("src\\Translations\\viewTeacher\\kazakh.txt", messages);
        }
    }

    public void start() {
        loadMessages();
        createUserFromFile("src\\Data\\users.txt");
        boolean running = true;

        while (running) {
            System.out.println("1. " + messages.get("view_teacher_schedule"));
            System.out.println("2. " + messages.get("teacher_view"));
            System.out.println("3. " + messages.get("exit"));

            System.out.print(messages.get("enter_choice") + " ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            if (choice == 1) {
                viewSchedule();
            } else if (choice == 2) {
                displayInfo();
            } else if (choice == 3) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    protected void displayInfo() {
        System.out.println(messages.get("teacher_details") + ": " + teacher.getName() + ", " + messages.get("salary") + ": " + teacher.getSalary());
    }

    private void viewSchedule() {
        System.out.println(messages.get("schedule_placeholder"));
        // Add logic to display teacher's schedule
    }

    protected void createUserFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[2].trim();
                        String password = parts[3].trim();
                        double salary = Double.parseDouble(parts[4].trim());
                        if (id == teacherId) {
                            teacher = new Teacher(id, name, password, salary);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users file: " + filename);
        }
    }
}
