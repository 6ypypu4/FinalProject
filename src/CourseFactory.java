import Enums.CourseType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class CourseFactory {
	
	private CourseType courseType;
	private Course course;
	
	public CourseFactory(CourseType courseType) {
		this.courseType = courseType;
	}
	
	public CourseFactory(Course course) {
		this.course = course;
	}
	
	public boolean createCourse(String courseId, String name, int credits, Vector<String> preRequisites) {
		
		String preReq = String.join("->", preRequisites);
	    String courseInfo = courseId + '=' + name + '=' + credits + '=' + preReq;
		return saveToFile("src\\Data\\courses.txt", courseInfo);
	    
		//если preRequisites содержит ["Math", "Physics"], результат будет "Math->Physics".
		
	}

	
	private static boolean saveToFile(String filePath, String data) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(data);
			writer.newLine();
			return true;
		} catch(IOException e) {
			System.err.println("Error writing" + filePath + ":" + e.getMessage());
			return false;
		}
	
	}
}
