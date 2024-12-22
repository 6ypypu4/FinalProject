package Research;

public interface Researcher {
	public abstract boolean startResearchProject(int projectID, String title);
	public abstract boolean publishResearchProject(int projectID, int journalID);
	public abstract boolean addResearchPaper(int projectID, ResearchPaper paper);
}

