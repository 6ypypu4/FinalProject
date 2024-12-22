package Research;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PageFactory {
	
	public PageFactory() {
		
	}
	
	public boolean createPage(
			String ResearchPaperDOI, int pageNumber, String content,
			List<String> figures, List<String> tables, List<String> references) {
		return savePageToFile(new Page(ResearchPaperDOI, pageNumber, content,
				figures, tables, references));
	};
	
	private boolean savePageToFile(Page page) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("//FInalProject//src//Data//pages.txt", true))) {
			String data = page.toString();
			writer.write(data);
			writer.newLine();
			return true;
		} catch(IOException e) {
			System.err.println("Error writing <//FInalProject//src//Data//pages.txt> :" + e.getMessage());
			return false;
		}
		
	}
	
}
