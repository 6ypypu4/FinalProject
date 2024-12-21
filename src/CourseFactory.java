import Enums.CourseType;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class CourseFactory {

	public CourseFactory() {
	}

	public boolean createCourse(String courseId, String name, int courseTypeId, int credits, Vector<String> preRequisites) {
		String preReqs = String.join("->", preRequisites);
		// если preRequisites содержит ["Math", "Physics"], результат будет "Math->Physics"
	    String courseInfo = courseId + '=' + name + '=' + credits + '=' + preReqs;
		return saveToFile("src\\Data\\courses.txt", courseInfo);
	}

	
	private static boolean saveToFile(String filePath, String data) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write(data);
			writer.newLine();
			return true;
		} catch(IOException e) {
			System.err.println("Error writing" + filePath + ":" + e.getMessage());
			return false;
		}
	
	}
}
