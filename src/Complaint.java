import Enums.UrgencyLevel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

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
			FileWriter fw = new FileWriter("src/data/complaints.txt", true);
			// true means that new line will be added at the end of the file
			BufferedWriter bw = new BufferedWriter(fw);
			
			String complaintData = studentId + "=" + urgencyLevel + "=" + comment;
			
			bw.write(complaintData);
			bw.newLine();
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error saving complaint: " + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Complaint [studentId=" + studentId 
				+ ", urgencyLevel=" + urgencyLevel 
				+ ", comment=" + comment + "]";
	}
	
	
	
}
