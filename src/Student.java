import java.util.Vector;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Student extends User {
    private List<Course> registeredCourses;
    private HashMap<Course, Integer> courseGrades;
    private List<StudentOrganization> organizations;
    private boolean isOrgHead = false;

    // Конструктор
    public Student(int id, String name, String password, String isOrg) {
        super(id, name, password);
        if(isOrg.equals("1")){
            this.isOrgHead= true;
        }
        this.registeredCourses = new ArrayList<>();
        this.courseGrades = new HashMap<>();
        this.organizations = new ArrayList<>();
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
        System.out.println("Transcript for " + name + " (ID: " + id + ")");
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
        double GPA = 0;
        int totalCredits = 0;
        for (Map.Entry<Course, Integer> entry : courseGrades.entrySet()) {
        	double gpaEquivalent = 0.00;
        	totalCredits += entry.getKey().getCredits();
        	if (95 <= entry.getValue()) {
        		gpaEquivalent = 4.00;
        	} else if (90 <= entry.getValue()) {
        		gpaEquivalent = 3.67;
        	} else if (85 <= entry.getValue()) {
        		gpaEquivalent = 3.33;
        	} else if (80 <= entry.getValue()) {
        		gpaEquivalent = 3.00;
        	} else if (75 <= entry.getValue()) {
        		gpaEquivalent = 2.67;
        	} else if (70 <= entry.getValue()) {
        		gpaEquivalent = 2.33;
        	} else if (65 <= entry.getValue()) {
        		gpaEquivalent = 2.00;
        	} else if (60 <= entry.getValue()) {
        		gpaEquivalent = 1.67;
        	} else if (55 <= entry.getValue()) {
        		gpaEquivalent = 1.33;
        	} else if (50 <= entry.getValue()) {
        		gpaEquivalent = 1;
        	} 
            GPA += entry.getKey().getCredits() * gpaEquivalent;
        }
        return GPA / totalCredits;
    }

    // Teacher Feedback Methods
    public void rateTeacher(Teacher teacher, int rating) {
        if (rating >= 1 && rating <= 5) {
            RateFactory.rateTeacher(id, teacher.id, rating);
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
