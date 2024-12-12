import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class CourseFactory {
	
	private CourseType courseType;
	
	public CourseFactory(CourseType courseType) {
		this.courseType = courseType;
	}
	
	public boolean createCourse(String name, int credits, Vector<String> preRequisites) {
		
		String preReq = String.join("->", preRequisites);
	    String courseInfo = name + '=' + credits + '=' + preReq;
		
	    return saveToFile("src\\Data\\courses.txt", courseInfo);
	    
	}
	
	
	private static boolean saveToFile(String filePath, String data) {
		try(BufferedWriter writer new BufferedWriter(new FileWriter(filePath, true))){
			writer.writer(data);
			writer.newLine();
			return true;
		} catch(IOException e) {
			System.err.println("Error writing" + filePath + ":" + e.getMessage());
			return false;
		}
		
	}
}
