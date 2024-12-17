import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Enums.UrgencyLevel;

public class Teacher extends Employee {

    private ArrayList<String> courses = new ArrayList<>();
    private HashMap<Integer, String> students = new HashMap<>(); // studentId -> info
    private Scanner scanner = new Scanner(System.in);

    public Teacher(int id, String name, String password, double salary) {
        super(id, name, password, salary);
    }

    // View courses assigned to the teacher
    public void viewCourses() {
        System.out.println("Courses assigned to " + getName() + ":");
        for (String course : courses) {
            System.out.println("- " + course);
        }
    }

    // Add or manage a course
    public void manageCourse(String courseId, boolean addCourse) {
        if (addCourse) {
            courses.add(courseId);
            System.out.println("Course added: " + courseId);
        } else {
            courses.remove(courseId);
            System.out.println("Course removed: " + courseId);
        }
    }

    // View information about students
    public void viewStudents() {
        System.out.println("Students under teacher " + getName() + ":");
        for (var entry : students.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Info: " + entry.getValue());
        }
    }

    // Add or update student info // Нужен реворк
    public void manageStudentInfo(int studentId, String info) {
        students.put(studentId, info);
        System.out.println("Student information updated for ID " + studentId);
    }

    // Put marks for a student // нужен реворк
    public void putMarks(int studentId, int mark) {
        if (students.containsKey(studentId)) {
            System.out.println("Mark " + mark + " assigned to student ID " + studentId);
        } else {
            System.out.println("Student ID " + studentId + " not found.");
        }
    }

    // Send a complaint
    public void sendComplaint(int studentId, UrgencyLevel level, String complaintText) {
    	Complaint complaint = new Complaint(studentId, level, complaintText);
    	
        System.out.println("Complaint filed: " + complaintText);
    }

}
