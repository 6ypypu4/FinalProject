import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Enums.UrgencyLevel;

public class Teacher extends Employee {

    private ArrayList<String> courses = new ArrayList<>();
    private ArrayList<String> lessons = new ArrayList<>();
    private HashMap<Integer, Integer> students = new HashMap<>(); // studentId -> grade

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
        for (Map.Entry<Integer, Integer> entry : students.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
    }

    // Add or update student info // Нужен реворк
    public void manageStudentInfo(int studentId, String info) {
        System.out.println("Student info updated for ID " + studentId + ": " + info);
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
    public void sendComplaint(String studentId, UrgencyLevel level, String complaintText) {
    	Complaint complaint = new Complaint(studentId, level, complaintText);
    	complaint.saveComplaint();
    	
        System.out.println("Complaint filed: " + complaintText);
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    } 

    public void setStudents(HashMap<Integer, Integer> students) {
        this.students = students;
    }

    // Add this getter method
    public ArrayList<String> getLessons() {
        return lessons;
    }
}
