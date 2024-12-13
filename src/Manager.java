import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Manager extends Employee {

	public Manager(int id, String name, String password, double salary) {
		
		super(id, name, password, salary);
	}
	
	public boolean createCourse(String name, int credits, CourseType courseType, Vector<String> preRequisites) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Data\\courses.txt", true))){
			String preReq = String.join("->", preRequisites);
			writer.write(name + "=" + credits + "=" + courseType.getTypeName() + "=" + preReq);
			writer.newLine();
			return true;
		} catch (IOException e) {
			System.err.println("Error writing to file" + e.getMessage());
		} 
		 
		return false;

	}
}
