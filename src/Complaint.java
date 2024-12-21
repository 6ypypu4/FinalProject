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

	public void saveComplaint() {
		try {
			java.io.FileWriter fw = new java.io.FileWriter("src/data/complaints.txt", true);
			java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
			
			String complaintData = studentId + "=" + urgencyLevel + "=" + comment;
			bw.write(complaintData);
			bw.newLine();
			
			bw.close();
			fw.close();
		} catch (java.io.IOException e) {
			System.out.println("Error saving complaint: " + e.getMessage());
		}
	}

	
}
