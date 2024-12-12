public class Course {
	
	public String typeName;
	
	public Course(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String toString() {
		return typeName;
	}
}
