import Enums.LessonType;

import java.io.*;
import java.util.*;
import java.util.Vector;

public class viewManager extends viewEmployee {
    private static int managerId;
    private static Manager manager;
    private static int languageChoice;

    private HashMap<String, String> messages = new HashMap<>();
    private MessageLoader messageLoader = new MessageLoader();

    private Vector<Course> courses = new Vector<>();
    private Vector<Lesson> lessons = new Vector<>();

    public viewManager(int managerId, int languageChoice) {
        viewManager.managerId = managerId;
        viewManager.languageChoice = languageChoice;
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

        System.out.print(messages.get("enter_credits") + " ");
        int credits = scanner.nextInt();
        scanner.nextLine();

        System.out.print(messages.get("enter_pre_requisites") + " ");
        String prerequisitesInput = scanner.nextLine();
        Vector<String> preRequisites = new Vector<>(Arrays.asList(prerequisitesInput.split(",")));

        boolean success = manager.createCourse(courseId, name, credits, preRequisites);
        if (success) {
            courses.add(new Course(courseId, "General", name, credits, preRequisites));
            System.out.println(messages.get("course_created"));
        } else {
            System.out.println(messages.get("course_creation_failed"));
        }
    }

    private void createLesson() {
        System.out.println(messages.get("select_lesson_type"));
        LessonType lessonType = selectLessonType();

        System.out.print(messages.get("enter_lesson_date") + " ");
        String dateInput = scanner.nextLine();
        Date date = new Date(dateInput); // Можно использовать SimpleDateFormat для корректного парсинга даты.

        System.out.print(messages.get("enter_teacher_id") + " ");
        String teacherId = scanner.nextLine();

        System.out.print(messages.get("enter_course_name") + " ");
        String courseName = scanner.nextLine();
        Course course = findCourseByName(courseName);

        if (course == null) {
            System.out.println(messages.get("course_not_found"));
            return;
        }

        System.out.print(messages.get("enter_student_ids") + " ");
        String studentIdsInput = scanner.nextLine(); // Ввод ID студентов через запятую
        Vector<String> studentIds = new Vector<>(Arrays.asList(studentIdsInput.split(",")));

        // Создание урока с Enums.LessonType, ID учителя и студентов
        Lesson lesson = new Lesson(lessonType, date, teacherId, studentIds, course);
        lessons.add(lesson);

        System.out.println(messages.get("lesson_created"));
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


    private Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }
}
