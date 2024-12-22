package Research;
import java.util.Vector;
import main.User;


public class Journal {
	private int journalID;
	private String title;
    private Vector<User> subs;
    private String theme;
	private Vector<ResearchProject> projects;

	public Journal(int journalID, String title, String theme) {
		this.journalID = journalID;
		this.title = title;
		this.theme = theme;
		this.subs = new Vector<User>();
		this.projects = new Vector<ResearchProject>();
	}

	public int getJournalID() {
		return journalID;
	}
	public String getTitle() {
		return title;
	}
	public String getTheme() {
		return theme;
	}
	public Vector<User> getSubs() {
		return subs;
	}
	public Vector<ResearchProject> getProjects() {
		return projects;
	}
	public void newSub(User user){
        subs.add(user);
    }

	public void newProject(ResearchProject project){
        projects.add(project);
    }

	public void removeSub(User user){
        subs.remove(user);
    }

	public void removeProject(ResearchProject project){
        projects.remove(project);
    }

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
