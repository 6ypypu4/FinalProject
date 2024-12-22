package Research;
import java.util.*;
import java.text.SimpleDateFormat;
import Enums.Format;

public class ResearchPaper {
	
	private String DOI;
    private String title;
    private List<String> authors;
    private Journal journal;
    private List<Page> pages;
    private Date date;
    private int citations;

    public ResearchPaper(String title, List<String> authors, Journal journal, List<Page> pages, Date date, int citations, String DOI) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.journal = journal;
        this.pages = new ArrayList<>(pages);
        this.date = date;
        this.citations = citations;
        this.DOI = DOI;
    }

    // Method to print papers sorted by comparator
    public static void printPapers(List<Page> pages, Comparator<Page> comparator) {
        pages.sort(comparator);
        for (Page paper : pages) {
            System.out.println(paper);
        }
    }

    // Method to calculate h-index
    public static int calculateHIndex(List<ResearchPaper> papers) {
        List<Integer> citationCounts = new ArrayList<>();
        for (ResearchPaper paper : papers) {
            citationCounts.add(paper.citations);
        }
        Collections.sort(citationCounts, Collections.reverseOrder());
        
        int hIndex = 0;
        for (int i = 0; i < citationCounts.size(); i++) {
            if (citationCounts.get(i) >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }

    // Method to get citation in different formats
    public String getCitation(Format format) {
        if (format == Format.PLAIN_TEXT) {
            return String.format("%s, %s, %s, pp. %d, %s, DOI: %s",
                    String.join(", ", authors), title, journal, pages, formatDate(date), DOI);
        } else if (format == Format.BIBTEX) {
            return String.format("@article{%s,\n  title={%s},\n  author={%s},\n  journal={%s},\n  pages={%d},\n  year={%s},\n  doi={%s}\n}",
                    DOI.replaceAll("[^a-zA-Z0-9]", ""), title, String.join(" and ", authors), journal, pages, formatYear(date), DOI);
        }
        return "";
    }

    // Utility to format date
    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // Utility to format year
    private static String formatYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    // Override toString for printing
    @Override
    public String toString() {
        return String.format("Title: %s, Authors: %s, Journal: %s, Pages: %d, Date: %s, Citations: %d, DOI: %s",
                title, String.join(", ", authors), journal, pages, formatDate(date), citations, DOI);
    }

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String dOI) {
		DOI = dOI;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCitations() {
		return citations;
	}

	public void setCitations(int citations) {
		this.citations = citations;
	}
}


