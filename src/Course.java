import java.util.Vector;

public class Course {
	
	public String typeName;
	public String name;
	public int credits;
	public Vector<String> preRequisites;
	
	public Course(String typeName, String name, int credits, Vector<String> preRequisites) {
		this.typeName = typeName;
		this.name = name;
		this.credits = credits;
		this.preRequisites = preRequisites;
	}

	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	public Vector<String> getPreRequisites() {
		return preRequisites;
	}


	public void setPreRequisites(Vector<String> preRequisites) {
		this.preRequisites = preRequisites;
	}


	public String toString() {
		return "Course Type: " + typeName + " Name: " + name + " Credits: " + credits + " Pre-requisites: " + String.join(" ", preRequisites);	
		}
}
