import java.util.Date;
import java.util.Vector;

public class Lesson {
	
	 private LessonType type;    
	    private Date date;      
	    private Teacher teacher;   
	    private Vector<Student> students; 
	    private Course course;


	    public Lesson(LessonType type, Date date, Teacher teacher, Vector<Student> students, Course course) {
	        this.type = type;
	        this.date = date;
	        this.teacher = teacher;
	        this.students = students;
	        this.course = course;
	    }


		public LessonType getType() {
			return type;
		}


		public void setType(LessonType type) {
			this.type = type;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public Teacher getTeacher() {
			return teacher;
		}


		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}


		public Vector<Student> getStudents() {
			return students;
		}


		public void setStudents(Vector<Student> students) {
			this.students = students;
		}


		public Course getCourse() {
			return course;
		}


		public void setCourse(Course course) {
			this.course = course;
		}
	    
	    
		public String toString() {
	        return "Type: " + type + " Date: " + date + " Teacher: " + teacher.getName() + " Course: " + course.getName() + " Students: " + students.size();
	    }

}
