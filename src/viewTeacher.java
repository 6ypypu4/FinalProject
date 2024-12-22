import Enums.UrgencyLevel;

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
        loadTeacherData();
        boolean running = true;

        while (running) {
            System.out.println("1. " + messages.get("view_courses"));
            System.out.println("2. " + messages.get("manage_course"));
            System.out.println("3. " + messages.get("view_students"));
            System.out.println("4. " + messages.get("manage_student_info"));
            System.out.println("5. " + messages.get("put_marks"));
            System.out.println("6. " + messages.get("send_message"));
            System.out.println("7. " + messages.get("view_messages"));
            System.out.println("8. " + messages.get("send_complaint"));
            System.out.println("9. " + messages.get("teacher_view"));
            System.out.println("10. " + messages.get("exit"));

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
                sendMessage();
            } else if (choice == 7) {
            	viewMessages();
            } else if (choice == 8) {
                sendComplaint();
            } else if (choice == 9) {
                displayInfo();
            } else if (choice == 10) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private void sendComplaint() {
        System.out.println(messages.get("insert_student_id"));
        String studentId = scanner.nextLine();
        scanner.nextLine();
        System.out.println(messages.get("insert_complain"));
        String complain = scanner.nextLine();
        teacher.sendComplaint(studentId, UrgencyLevel.MEDIUM, complain);
    }

    private void sendMessage() {
        System.out.println(messages.get("insert_employee_id"));
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(messages.get("insert_message"));
        String message = scanner.nextLine();
        teacher.sendWorkMessage(employeeId, message);
    }
    
    private void viewMessages() {
    	teacher.getWorkMessages();
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

    protected void loadTeacherData() {
        loadTeacherCourses();
        loadTeacherLessons();
        loadTeacherStudents();
    }

    private void loadTeacherCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data\\teacher_courses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && Integer.parseInt(parts[0]) == teacherId) {
                    String[] courses = parts[1].split(",");
                    for (String course : courses) {
                        teacher.manageCourse(course, true);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading teacher courses: " + e.getMessage());
        }
    }

    private void loadTeacherLessons() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data\\teacher_lessons.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && Integer.parseInt(parts[0]) == teacherId) {
                    String[] lessons = parts[1].split(",");
                    for (String lesson : lessons) {
                        teacher.getLessons().add(lesson);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading teacher lessons: " + e.getMessage());
        }
    }

    private void loadTeacherStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data\\teacher_students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && Integer.parseInt(parts[0]) == teacherId) {
                    String[] studentEntries = parts[1].split(",");
                    HashMap<Integer, Integer> students = new HashMap<>();
                    for (String entry : studentEntries) {
                        String[] studentInfo = entry.split(":");
                        students.put(Integer.parseInt(studentInfo[0]), Integer.parseInt(studentInfo[1]));
                    }
                    teacher.setStudents(students);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading teacher students: " + e.getMessage());
        }
    }
}
