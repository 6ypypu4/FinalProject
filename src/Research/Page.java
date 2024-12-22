package Research;
import java.util.Comparator;
import java.util.List;
import main.Serializable;

public class Page implements Serializable, Comparable<Page> {
	
	private String ResearchPaperDOI;
    private int pageNumber;
    private String content;
    private List<String> figures; // List of figures
    private List<String> tables;  // List of tables
    private List<String> references; // References cited on this page

    public Page(String ResearchPaperDOI, int pageNumber, String content, List<String> figures, List<String> tables, List<String> references) {
        this.ResearchPaperDOI = ResearchPaperDOI;
    	this.pageNumber = pageNumber;
        this.content = content;
        this.figures = figures;
        this.tables = tables;
        this.references = references;
    }
    
    public String getResearchPaperDOI() {
		return ResearchPaperDOI;
	}


	public void setResearchPaperDOI(String researchPaperDOI) {
		ResearchPaperDOI = researchPaperDOI;
	}


	public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getFigures() {
        return figures;
    }

    public void setFigures(List<String> figures) {
        this.figures = figures;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public void printPageDetails() {
        System.out.println("Page Number: " + pageNumber);
        System.out.println("Content: " + content);
        if (!figures.isEmpty()) {
            System.out.println("Figures: " + String.join(", ", figures));
        }
        if (!tables.isEmpty()) {
            System.out.println("Tables: " + String.join(", ", tables));
        }
        if (!references.isEmpty()) {
            System.out.println("References: " + String.join(", ", references));
        }
    }

	@Override
	public String toString() {
		return ResearchPaperDOI + "=" + pageNumber + "=" + content;
	}
    
	@Override
	public int compareTo(Page other) {
		if (this.pageNumber > other.getPageNumber()) {
			return 1;
		} else if (this.pageNumber < other.getPageNumber()) {
			return -1;
		}
		
		return 0;
	}
}
