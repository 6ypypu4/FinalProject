package Research;
import java.util.Vector;

import main.User;

public class Journal {
	private String title;
    private Vector<User> subs;
    private String directory;

    

    public Journal(String title, Vector<User> subs, String directory) {
		super();
		this.title = title;
		this.subs = subs;
		this.directory = directory;
	}



	public void newSub(User user){
        subs.add(user);
    }
}
