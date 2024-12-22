package main;

import Enums.LessonType;
import java.util.Date;
import java.util.Vector;

public class Lesson {

    private LessonType type;
    private Date date;      
    private Teacher teacher;
    private Vector<String> students;
    private Course course;


    public Lesson(LessonType type, Date date, Teacher teacher, Course course) {
        this.type = type;
        this.date = date;
        this.teacher = teacher;
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

	
	public Vector<String> getStudents() {
		return students;
	}
	public void setStudents(Vector<String> students) {
		this.students = students;
	}

	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}


	@Override
	public String toString() {
		return "Lesson [type=" + type 
				+ ", date=" + date 
				+ ", teacher=" + teacher
				+ ", students=" + students
				+ ", course=" + course + "]";
	}
    
   

}
