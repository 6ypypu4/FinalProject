package main;

import main.FileHandler;
import Research.ResearchPaper;
import Research.ResearchProject;
import Research.Researcher;

import java.util.ArrayList;
import java.util.List;

public class StudentResearcher extends Student implements Researcher {
	
	private List<ResearchProject> researchProjects;
    public StudentResearcher(int userId, String password, String name, String surname) {
        super(userId, password, name, surname);
        researchProjects = new ArrayList<>();
    }
    
    private boolean isNewProject(int projectID) {
    	List<String> allProjects = FileHandler.readFromFile(
        		"//FInalProject//src//Data//research_projects.txt");
        
        for (String line : allProjects) {
        	if (line.startsWith(Integer.toString(projectID))) {
        		return false;
        	}
        }
        return true;
    }
    
    private ResearchProject findProject(int projectID) {
        List<String> allProjects = FileHandler.readFromFile("research_projects.txt");
        
        for (String line : allProjects) {
            String[] parts = line.split("=");
            if (parts[0].equals(Integer.toString(projectID))) {
                // Create and return project from file data
                ResearchProject project = new ResearchProject(projectID, parts[1], this);
                researchProjects.add(project);
                return project;
            }
        }
        return null;
    }
    
    @Override
    public boolean startResearchProject(int projectID, String title) {
        if (!isNewProject(projectID)) {
        	return false;
        }
        
        ResearchProject project = new ResearchProject(projectID, title, this);
        researchProjects.add(project);
        FileHandler.appendToFile("research_projects.txt", project.toString());
        return true;
    }

    @Override 
    public boolean publishResearchProject(int projectID, int journalID) {
        ResearchProject project = findProject(projectID);
        if (project == null) {
            return false;
        }
        
        // Here you would typically:
        // 1. Validate the journal exists
        // 2. Update the project status
        // 3. Save changes to file
        
        return true;
    }

    @Override
    public boolean addResearchPaper(int projectID, ResearchPaper paper) {
        ResearchProject project = findProject(projectID);
        if (project == null) {
            return false;
        }
        
        project.publishPaper(paper);
        
        // Update the project in the file
        List<String> allProjects = FileHandler.readFromFile("research_projects.txt");
        for (int i = 0; i < allProjects.size(); i++) {
            if (allProjects.get(i).startsWith(Integer.toString(projectID))) {
                allProjects.set(i, project.toString());
                break;
            }
        }
        return FileHandler.overwriteFile("research_projects.txt", allProjects);
    }

}