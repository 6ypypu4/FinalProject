package main;

import Research.Researcher;

public class StudentResearcher implements Researcher {

    @Override
    public boolean startResearchProject() {
        return false;
    }

    @Override
    public boolean publishResearchProject() {
        return false;
    }

    @Override
    public boolean addResearchPaper() {
        return false;
    }
}
