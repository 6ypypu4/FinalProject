public class User {
    private int id;
    public String name;
    private String password;

    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void postNews(News news){
        news.postNew(name);
    }

    public void subscribeToJournal(Journal journal){
        journal.newSub(this);
    }
 
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }
}
