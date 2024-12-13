import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class viewManager extends viewEmployee{
	
	private static int managerId;
    private static int languageChoice;
    private static Manager manager;
    
    private HashMap<String, String> messages = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private MessageLoader messageLoader = new MessageLoader();

    public viewManager(int managerId, int languageChoice) {
        viewManager.managerId = managerId;
        viewManager.languageChoice = languageChoice;
    } 
    
    private void loadMessages() {
        if (languageChoice == 1) {
            messageLoader.loadMessages("src\\Translations\\viewManager\\english.txt", messages);
        } else if (languageChoice == 2) {
            messageLoader.loadMessages("src\\Translations\\viewManager\\russian.txt", messages);
        } else if (languageChoice == 3) {
            messageLoader.loadMessages("src\\Translations\\viewManager\\kazakh.txt", messages);
        }
    }
    
    
    
    public void start() {
        loadMessages();
        createManagerFromFile("src\\Data\\users.txt");
        boolean running = true;
        while (running) {
            System.out.println("1. " + messages.get("create_course"));
            System.out.println("2. " + messages.get("exit"));
            System.out.print(messages.get("enter_choice") + " ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                createCourse();
            } else if (choice == 2) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }
    
    private void createCourse() {
        System.out.print(messages.get("enter_course_name") + " ");
        String name = scanner.nextLine();

        System.out.print(messages.get("enter_course_credits") + " ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        System.out.print(messages.get("enter_course_type") + " ");
        String courseTypeName = scanner.nextLine();
        CourseType courseType = new CourseType(courseTypeName);

        manager.createCourse(name, credits, courseType, new Vector<>());
    }
    
    private void createManagerFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("");
                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[2].trim();
                        String password = parts[3].trim();
                        double salary = Double.parseDouble(parts[4].trim());
                        if (id == managerId) {
                            manager = new Manager(id, name, password, salary);
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