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
    private Vector<Teacher> teachers = new Vector<>();
    private Vector<Student> students = new Vector<>();

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
                if (parts.length >= 5) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String userType = parts[1].trim();
                        String name = parts[2].trim();
                        String password = parts[3].trim();
                        double salary = Double.parseDouble(parts[4].trim());
                        
                        switch (userType) {
                            case "2":
                                if (id == managerId) {
                                    manager = new Manager(id, name, password, salary);
                                }
                                break;
                            case "3":
                                teachers.add(new Teacher(id, name, password, salary));
                                break;
                            case "4":
                                String isOrg = parts.length > 5 ? parts[5].trim() : "0";
                                students.add(new Student(id, name, password, isOrg));
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
            if (manager == null) {
                System.out.println(messages.get("manager_not_found"));
            }
        } catch (IOException e) {
            System.out.println("Error loading users file: " + filename);
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

    protected void loadCoursesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("=");
                    if (parts.length >= 3) {
                        String courseId = parts[0].trim();
                        String name = parts[1].trim();
                        int credits = Integer.parseInt(parts[2].trim());
                        
                        // Parse prerequisites
                        Vector<String> preRequisites = new Vector<>();
                        if (parts.length > 3 && parts[3].contains("->")) {
                            String[] prereqs = parts[3].split("->");
                            preRequisites.addAll(Arrays.asList(prereqs));
                        }
                        
                        // Default to MAJOR if course type is not specified
                        CourseType courseType = CourseType.MAJOR;
                        courses.add(new Course(courseId, courseType, name, credits, preRequisites));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(messages.get("error_loading_courses") + ": " + filename);
        }
    }

    protected void loadLessonsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("=");
                    if (parts.length >= 4) {
                        Course course = findCourseByName(parts[0].trim());
                        String date = parts[1].trim();
                        LessonType lessonType = LessonType.valueOf(parts[2].trim().toUpperCase());
                        Teacher teacher = findTeacherByName(parts[3].trim());
                        
                        if (course != null && teacher != null) {
                            lessons.add(new Lesson(course, date, lessonType, teacher));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(messages.get("error_loading_lessons") + ": " + filename);
        }
    }

    public void start() {
        loadMessages();
        createUserFromFile("src\\Data\\users.txt");
        loadCoursesFromFile("src\\Data\\courses.txt");
        loadLessonsFromFile("src\\Data\\lessons.txt");
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
        CourseType courseType = selectCourseType();

        System.out.print(messages.get("enter_credits") + " ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Clear input buffer

        System.out.print(messages.get("enter_pre_requisites") + " ");
        String prerequisitesInput = scanner.nextLine();
        Vector<String> preRequisites = new Vector<>(Arrays.asList(prerequisitesInput.split(",")));

        boolean success = manager.createCourse(courseId, name, courseType, credits, preRequisites);
        if (success) {
            courses.add(new Course(courseId, courseType, name, credits, preRequisites));
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

        System.out.println("Enter teacher id:");
        String teacherName = scanner.nextLine();
        Teacher selectedTeacher = findTeacherByName(teacherName);

        if (manager.createLesson(course, date, lessonTypeId, selectedTeacher)) {
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

    private CourseType selectCourseType() {
        while (true) {
            System.out.println("1. MAJOR");
            System.out.println("2. MINOR");
            System.out.println("3. FREE_ELECTIVE");
            System.out.print(messages.get("enter_choice") + " ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            if (choice == 1) {
                return CourseType.MAJOR;
            }else if(choice == 2) {
                return CourseType.MINOR;
            }else if (choice == 3){
                return CourseType.FREE_ELECTIVE;
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
        for (Teacher teacher : teachers) {
            if (teacher.getName().equalsIgnoreCase(teacherName)) {
                return teacher;
            }
        }
        return null;
    }
}
