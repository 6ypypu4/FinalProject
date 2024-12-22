import Enums.CourseType;

import java.util.Vector;

public class Manager extends Employee{
	
	private CourseFactory courseFactory;
	private LessonFactory lessonFactory;
	
	public Manager(int id, String name, String password, double salary) {
		super(id, name, password, salary);
		courseFactory = new CourseFactory();
		lessonFactory = new LessonFactory();
	}
	
	
	public boolean createCourse(String courseId, String name, CourseType courseType, int credits, Vector<String> preRequisites) {
		int courseTypeId;
		if(courseType == CourseType.MINOR){
			courseTypeId = 1;
		}else if( courseType == CourseType.MAJOR){
			courseTypeId = 2;
		}else {
			courseTypeId = 3;
		}
		return courseFactory.createCourse(courseId, name, courseTypeId, credits, preRequisites);
	}
	
	public boolean createLesson(Course course, String date, int lessonTypeId, Teacher teacher) {
		return lessonFactory.createLesson(course, date, lessonTypeId, teacher);
	}
	
	public boolean addStudent(Lesson lesson, Student student) {
		return lessonFactory.addStudent(lesson, student);
	}
	
	//to list all courses and lessons:
	public void listCourses(Vector<Course> courses) {
        System.out.println("Courses: ");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void listLessons(Vector<Lesson> lessons) {
        System.out.println("Lessons: ");
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }
}
