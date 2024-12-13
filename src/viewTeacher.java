import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class viewTeacher extends viewEmployee {
    private static int     teacherId;
    private static Teacher teacher;
    private static int     languageChoice;

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
            System.out.println("1. " + messages.get("view_courses"));
            System.out.println("2. " + messages.get("manage_course"));
            System.out.println("3. " + messages.get("view_students"));
            System.out.println("4. " + messages.get("manage_student_info"));
            System.out.println("5. " + messages.get("put_marks"));
            System.out.println("6. " + messages.get("send_message"));
            System.out.println("7. " + messages.get("send_complaint"));
            System.out.println("8. " + messages.get("teacher_view"));
            System.out.println("9. " + messages.get("exit"));

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            if (choice == 1) {
                viewCourses();
            } else if (choice == 2) {
                manageCourse();
            } else if (choice == 3) {
                viewStudents();
            } else if (choice == 4) {
                manageStudentInfo();
            } else if (choice == 5) {
                putMarks();
            } else if (choice == 6) {
                sendMessage1();
            } else if (choice == 7) {
                sendComplaint();
            } else if (choice == 8) {
                displayInfo();
            } else if (choice == 9) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private void sendComplaint() {
        System.out.println(messages.get("insert_complain"));
        String complain = scanner.nextLine();
        teacher.sendComplaint(complain);
    }

    private void sendMessage1() {
        sendMessage(teacherId);
    }

    private void putMarks() {
        System.out.println(messages.get("enter_student_id"));
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println(messages.get("insert_mark"));
        int mark = Integer.parseInt(scanner.nextLine());

        teacher.putMarks(studentId, mark);


    }

    private void manageStudentInfo() {
        System.out.println(messages.get("enter_student_id"));
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println(messages.get("insert_data"));
        String info = scanner.nextLine();

        teacher.manageStudentInfo(studentId, info);
    }

    private void viewStudents() {
        teacher.viewStudents();
    }

    private void manageCourse() {
        System.out.println(messages.get("enter_course_id"));
        String courseId = scanner.nextLine();
        System.out.println(messages.get("add_or_delete"));

        if(scanner.nextLine().equals(messages.get("add"))){
            teacher.manageCourse(courseId, true);
        }else {
            teacher.manageCourse(courseId, false);

        }
    }

    private void viewCourses() {
        teacher.viewCourses();
    }

    protected void displayInfo() {
        System.out.println(messages.get("teacher_details") + ": " + teacher.getName() + ", " + messages.get("salary") + ": " + teacher.getSalary());
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
