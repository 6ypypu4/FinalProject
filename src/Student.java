import java.util.Vector;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Student {
    private String name;
    private int studentID;
    private Vector<Integer> grades;
    private List<Course> registeredCourses;
    private HashMap<Course, Integer> courseGrades;
    private List<StudentOrganization> organizations;
    private HashMap<Teacher, Integer> teacherRatings;
    private boolean isOrgHead;

    // Конструктор
    public Student(String name, int studentID) {
        this.name = name;
        this.studentID = studentID;
        this.grades = new Vector<>();
        this.registeredCourses = new ArrayList<>();
        this.courseGrades = new HashMap<>();
        this.organizations = new ArrayList<>();
        this.teacherRatings = new HashMap<>();
        this.isOrgHead = false;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    // Метод для добавления оценки
    public void addGrade(int grade) {
        this.grades.add(grade);
    }

    // Метод для просмотра всех оценок
    public void viewGrades() {
        System.out.println("Оценки студента " + name + ": " + grades);
    }

    // Метод для вывода полной информации о студенте
    public void viewStudentInfo() {
        System.out.println("ID: " + studentID + ", Имя: " + name);
        viewGrades();
    }

    // Course Registration Methods
    public void registerForCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for: " + course.getName());
        }
    }

    public void viewAvailableCourses(List<Course> availableCourses) {
        System.out.println("Available Courses:");
        for (Course course : availableCourses) {
            System.out.println(course.toString());
        }
    }

    // Marks and Transcript Methods
    public void viewTranscript() {
        System.out.println("Transcript for " + name + " (ID: " + studentID + ")");
        for (Course course : courseGrades.keySet()) {
            System.out.println(course.getName() + ": " + courseGrades.get(course));
        }
    }

    public void generateTranscript() {
        // Logic to generate and possibly export transcript
        double gpa = calculateGPA();
        System.out.println("Generated Transcript");
        viewTranscript();
        System.out.println("GPA: " + gpa);
    }

    private double calculateGPA() {
        // GPA calculation logic
        if (courseGrades.isEmpty()) return 0.0;
        double total = 0;
        for (Integer grade : courseGrades.values()) {
            total += grade;
        }
        return total / courseGrades.size();
    }

    // Teacher Feedback Methods
    public void rateTeacher(Teacher teacher, int rating) {
        if (rating >= 1 && rating <= 5) {
            teacherRatings.put(teacher, rating);
            System.out.println("Rating submitted successfully");
        } else {
            System.out.println("Invalid rating. Please rate from 1 to 5");
        }
    }

    // Student Organization Methods
    public void joinOrganization(StudentOrganization org) {
        if (!organizations.contains(org)) {
            organizations.add(org);
            System.out.println("Successfully joined: " + org.getName());
        }
    }

    public void becomeOrgHead(StudentOrganization org) {
        if (organizations.contains(org) && !isOrgHead) {
            isOrgHead = true;
            org.setHead(this);
            System.out.println("Successfully became head of: " + org.getName());
        }
    }

    public void viewOrganizations() {
        System.out.println("Organizations:");
        for (StudentOrganization org : organizations) {
            System.out.println(org.getName() + (isOrgHead ? " (Head)" : " (Member)"));
        }
    }
}
