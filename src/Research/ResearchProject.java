package Research;

import java.util.ArrayList;
import java.util.List;
import main.User;
import main.Serializable;

public class ResearchProject implements Serializable {
	
	private int projectId;
	private String title;
	private List<ResearchPaper> publishedPapers;
	private List<User> participants;
	
	{
		publishedPapers = new ArrayList<>();
		participants = new ArrayList<>();
	}
	
	public ResearchProject(int projectId, String title, User user) {
		this.projectId = projectId;
		this.title = title;
		this.participants.add(user);
	}
	
	public ResearchProject(String title, List<ResearchPaper> publishedPapers, List<User> participants) {
		this.title = title;
		this.publishedPapers = publishedPapers;
		this.participants = participants;
	}
	
	@Override
	public String toString() {
		List<String> publishedPapersFormat = new ArrayList<>();
		for (ResearchPaper paper : publishedPapers) {
			publishedPapersFormat.add(paper.getDOI());
		}
		List<String> participantsFormat = new ArrayList<>();
		for (User user : participants) {
			publishedPapersFormat.add(Integer.toString(user.getId()));
		}
		return Integer.toString(projectId) + "=" + title + 
				"=" + String.join("->", publishedPapersFormat) +
				"=" + String.join("->", participantsFormat);
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ResearchPaper> getPublishedPapers() {
		return publishedPapers;
	}

	public void setPublishedPapers(List<ResearchPaper> publishedPapers) {
		this.publishedPapers = publishedPapers;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public void publishPaper(ResearchPaper paper) {
		publishedPapers.add(paper);
	}
	
	public void addParticipants(List<User> newParticipants) throws NotResearcherError {
		for (User user : newParticipants) {
			if (user instanceof Researcher) {
				this.participants.add(user);
			} else {
				throw new NotResearcherError(user.getName() + " is not a Resarcher");
			}
		}
	}
	
	public void deleteParticipant(User user) {
		this.participants.remove(user);
	}
}
