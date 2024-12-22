package Research;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import main.User;

public class ResearchProjectFactory {
	private static final AtomicInteger counter = new AtomicInteger();
	
	public ResearchProjectFactory() {}
	
	public ResearchProject createResearchProject(String title, List<ResearchPaper> publishedPapers, User creator) throws Exception {
		int projectId = counter.incrementAndGet();
		ResearchProject project = new ResearchProject(projectId, title, creator);
		if (saveProjectToFile(project)) {
			return new ResearchProject(projectId, title, creator);			
		} else {
			throw new Exception();
		}
	}
	
	private boolean saveProjectToFile(ResearchProject project) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("//FInalProject//src//Data//projects.txt", true))) {
			writer.write("\n" + project.toString());
			writer.newLine();
			return true;
		} catch(IOException e) {
			System.err.println("Error writing <//FInalProject//src//Data//projects.txt>:" + e.getMessage());
			return false;
		}
	}
}
