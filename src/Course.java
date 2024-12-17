import java.util.Vector;

public class Course {
	
	public String courseId;
	public String courseType;
	public String name;
	public int credits;
	public Vector<String> preRequisites;
	
	public Course(String courseId, String courseType, String name, int credits, Vector<String> preRequisites) {
		this.courseId = courseId;
		this.courseType = courseType;
		this.name = name;
		this.credits = credits;
		this.preRequisites = preRequisites;
	}

	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
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
		return "Course ID: " + courseId + " Course Type: " + courseType + " Name: " + name + " Credits: " + credits + " Pre-requisites: " + String.join(" ", preRequisites);
		}
}
