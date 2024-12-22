package Views;
public abstract class viewUser {
    public abstract void start();
    protected abstract void loadMessages();
    protected abstract void createUserFromFile(String filename);
    protected abstract void displayInfo();
}
