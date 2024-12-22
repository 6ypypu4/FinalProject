package Research;

import java.util.Comparator;

public class PageComparator implements Comparator<Page> {

	
	// pageNumber comparator
	@Override
	public int compare(Page o1, Page o2) {
		return o1.compareTo(o2);
	}

}
