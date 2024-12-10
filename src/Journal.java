import java.util.Vector;

public class Journal {
    private Vector<User> subs;
    private String directory;

    public Journal(String directory){
        this.directory = directory;
    }

    public void newSub(User user){
        subs.add(user);
    }
}
