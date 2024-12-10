public class User {
    private int id;
    public String name;
    private String password;
    private boolean logged;

    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
        logged = false;
    }

    public boolean login(int id, String password){
        if(this.id == id && this.password.equals(password)){
            logged = true;
        }
        return logged;
    }

    public void logout(){
        logged = false;
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

}
