import Enums.UrgencyLevel;

public class Complaint {
	private String studentId;
	private UrgencyLevel urgencyLevel;
	private String comment;
	
	public Complaint(String studentId, UrgencyLevel urgencyLevel, String comment) {
		this.studentId = studentId;
		this.urgencyLevel = urgencyLevel;
		this.comment = comment;
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
