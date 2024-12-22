package Research;

public class NotResearcherError extends Exception {
	
	      // Parameterless Constructor
	      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotResearcherError() {
		
	}
	
	public NotResearcherError(String message) {
		super(message);
	}
}
