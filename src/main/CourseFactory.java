package main;

import java.util.Vector;

public class CourseFactory {

	public CourseFactory() {
	}

	public boolean createCourse(String courseId, String name, int courseTypeId, int credits, Vector<String> preRequisites) {
		String preReqs = String.join("->", preRequisites);
		String courseInfo = courseId + '=' + name + '=' + credits + '=' + preReqs;
		return FileHandler.appendToFile("courses.txt", courseInfo);
	}
}
