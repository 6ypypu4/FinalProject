import Enums.LessonType;
import Enums.CourseType;
import java.io.*;
import java.util.*;
import java.util.Vector;

public class viewManager extends viewEmployee {
    private int managerId;
    private Manager manager;
    private int languageChoice;

    private HashMap<String, String> messages = new HashMap<>();
    private MessageLoader messageLoader = new MessageLoader();

    private Vector<Course> courses = new Vector<>();
    private Vector<Lesson> lessons = new Vector<>();

    public viewManager(int managerId, int languageChoice) {
        this.managerId = managerId;
        this.languageChoice = languageChoice;
    }

    @Override
    protected void loadMessages() {
        if (languageChoice == 1) {
            messageLoader.loadMessages("src\\Translations\\viewManager\\english.txt", messages);
        } else if (languageChoice == 2) {
            messageLoader.loadMessages("src\\Translations\\viewManager\\russian.txt", messages);
        } else if (languageChoice == 3) {
            messageLoader.loadMessages("src\\Translations\\viewManager\\kazakh.txt", messages);
        }
    }

    @Override
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
            System.out.println(messages.get("manager_not_found"));
        } catch (IOException e) {
            System.out.println("Error loading managers file: " + filename);
        }
    }

    @Override
    protected void displayInfo() {
        if (manager != null) {
            System.out.println(messages.get("manager_details") + ": " + manager.getName()
                    + ", " + messages.get("salary") + ": " + manager.getSalary());
        } else {
            System.out.println(messages.get("manager_not_loaded"));
        }
    }

    public void start() {
        loadMessages();
        createUserFromFile("src\\Data\\users.txt");
        boolean running = true;

        while (running) {
            System.out.println("1. " + messages.get("create_course"));
            System.out.println("2. " + messages.get("create_lesson"));
            System.out.println("3. " + messages.get("list_courses"));
            System.out.println("4. " + messages.get("list_lessons"));
            System.out.println("5. " + messages.get("exit"));

            System.out.print(messages.get("enter_choice") + " ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                createCourse();
            } else if (choice == 2) {
                createLesson();
            } else if (choice == 3) {
                manager.listCourses(courses);
            } else if (choice == 4) {
                manager.listLessons(lessons);
            } else if (choice == 5) {
                running = false;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private void createCourse() {
        System.out.print(messages.get("enter_course_id") + " ");
        String courseId = scanner.nextLine();

        System.out.print(messages.get("enter_course_name") + " ");
        String name = scanner.nextLine();

        System.out.println(messages.get("select_course_type"));
        int courseTypeId = selectCourseType();

        System.out.print(messages.get("enter_credits") + " ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Clear input buffer

        System.out.print(messages.get("enter_pre_requisites") + " ");
        String prerequisitesInput = scanner.nextLine();
        Vector<String> preRequisites = new Vector<>(Arrays.asList(prerequisitesInput.split(",")));

        boolean success = manager.createCourse(courseId, name, courseTypeId, credits, preRequisites);
        if (success) {
            courses.add(new Course(courseId, CourseType.values()[courseTypeId-1].toString(), name, credits, preRequisites));
            System.out.println(messages.get("course_created"));
        } else {
            System.out.println(messages.get("course_creation_failed"));
        }
    }

    private void createLesson() {
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        Course course = findCourseByName(courseName);

        System.out.println("Enter date (format: DD/MM/YYYY):");
        String date = scanner.nextLine();

        System.out.println("Enter lesson type (1-Lecture, 2-Practice, 3-Lab):");
        int lessonTypeId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Enter teacher name:");
        String teacherName = scanner.nextLine();
        Teacher teacher = findTeacherByName(teacherName);

        if (manager.createLesson(course, date, lessonTypeId, teacher)) {
            System.out.println("Lesson created successfully!");
        } else {
            System.out.println("Failed to create lesson. Please check your inputs.");
        }
    }

    private LessonType selectLessonType() {
        while (true) {
            System.out.println("1. LECTURE");
            System.out.println("2. PRACTICE");
            System.out.println("3. LAB");
            System.out.print(messages.get("enter_choice") + " ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка ввода

            if (choice == 1) {
                return LessonType.LECTURE;
            } else if (choice == 2) {
                return LessonType.PRACTICE;
            } else if (choice == 3) {
                return LessonType.LAB;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private int selectCourseType() {
        while (true) {
            System.out.println("1. MAJOR");
            System.out.println("2. MINOR");
            System.out.println("3. FREE_ELECTIVE");
            System.out.print(messages.get("enter_choice") + " ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            if (choice >= 1 && choice <= 3) {
                return choice;
            } else {
                System.out.println(messages.get("invalid_choice"));
            }
        }
    }

    private Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    private Teacher findTeacherByName(String teacherName) {
        // Implement this method to find a teacher by name from your data source
        // For now, we'll return null
        return null;
    }
}
