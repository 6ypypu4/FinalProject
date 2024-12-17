import Enums.UrgencyLevel;

public class Complaint {
	private int studentId;
	private UrgencyLevel urgencyLevel;
	private String comment;
	
	public Complaint(int studentId, UrgencyLevel urgencyLevel, String comment) {
		super();
		this.studentId = studentId;
		this.urgencyLevel = urgencyLevel;
		this.comment = comment;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public UrgencyLevel getUrgencyLevel() {
		return urgencyLevel;
	}

	public void setUrgencyLevel(UrgencyLevel urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
