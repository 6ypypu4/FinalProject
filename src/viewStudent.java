import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class viewStudent extends viewUser {
    private static int studentId;
    private static Student student;
    private static int languageChoice;

    private HashMap<String, String> messages = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private MessageLoader messageLoader = new MessageLoader();

    public viewStudent(int studentId, int languageChoice) {
        viewStudent.studentId = studentId;
        viewStudent.languageChoice = languageChoice;
    }

    @Override
    protected void loadMessages() {
        if (languageChoice == 1) {
            messageLoader.loadMessages("src\\Translations\\viewStudent\\english.txt", messages);
        } else if (languageChoice == 2) {
            messageLoader.loadMessages("src\\Translations\\viewStudent\\russian.txt", messages);
        } else if (languageChoice == 3) {
            messageLoader.loadMessages("src\\Translations\\viewStudent\\kazakh.txt", messages);
        }
    }

    @Override
    public void start() {
        loadMessages();
        createUserFromFile("src\\Data\\users.txt");
        boolean running = true;

        while (running) {
            System.out.println("2. " + messages.get("view_transcript"));
            System.out.println("3. " + messages.get("register_course"));
            System.out.println("4. " + messages.get("view_available_courses"));
            System.out.println("5. " + messages.get("rate_teacher"));
            System.out.println("6. " + messages.get("join_organization"));
            System.out.println("7. " + messages.get("view_organizations"));
            System.out.println("8. " + messages.get("student_info"));
            System.out.println("9. " + messages.get("exit"));

            System.out.print(messages.get("enter_choice") + " ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            if (choice == 1) {
                //notjing
            } else if (choice == 2) {
                student.generateTranscript();
            } else if (choice == 3) {
                registerForCourse();
            } else if (choice == 4) {
                // This would need to be implemented with actual course data
                System.out.println(messages.get("feature_not_available")); 
            } else if (choice == 5) {
                rateTeacher();
            } else if (choice == 6) {
                joinOrganization();
            } else if (choice == 7) {
                student.viewOrganizations();
            } else if (choice == 8) {
                displayInfo();
            } else if (choice == 9) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private void rateTeacher() {
        System.out.print(messages.get("enter_teacher_id") + " ");
        int teacherId = scanner.nextInt();
        System.out.print(messages.get("enter_rating") + " (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        
        // This would need actual Teacher object
        // student.rateTeacher(teacher, rating);
        System.out.println(messages.get("rating_submitted"));
    }

    private void registerForCourse() {
        System.out.print(messages.get("enter_course_id") + " ");
        String courseId = scanner.nextLine();
        
        // This would need actual Course object
        // student.registerForCourse(course);
        System.out.println(messages.get("registration_submitted"));
    }

    private void joinOrganization() {
        System.out.print(messages.get("enter_org_name") + " ");
        String orgName = scanner.nextLine();
        
        // This would need actual StudentOrganization object
        // student.joinOrganization(org);
        System.out.println(messages.get("org_joined"));
    }

    @Override
    protected void displayInfo() {
        //rework
    }

    @Override
    protected void createUserFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length >= 4) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[2].trim();
                        String password = parts[3].trim();
                        String isOrgHead = parts[4].trim();
                        if (id == studentId) {
                            student = new Student(id, name, password, isOrgHead);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                }
            }
            System.out.println(messages.get("student_not_found"));
        } catch (IOException e) {
            System.out.println("Error loading students file: " + filename);
        }
    }
}