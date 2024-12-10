
public class News {
    public String title;
    public String description;
    public String author;

    public News(String title, String description){
        this.title = title;
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void postNew(String author){
        setAuthor(author);
        // постит новость в news

    }

}
