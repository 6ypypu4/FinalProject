import java.util.List;
import java.util.ArrayList;

public class StudentOrganization {
    private String name;
    private Student head;
    private List<Student> members;

    public StudentOrganization(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setHead(Student student) {
        this.head = student;
    }

    public void addMember(Student student) {
        members.add(student);
    }
} 