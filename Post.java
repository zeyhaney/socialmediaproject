import java.sql.Timestamp;

public class Post {
    private int id;
    private String content;
    private User user;
    private String image;
    private Timestamp createdAt;
    private PostDAO postDAO;

    public Post(String content, User user, String image) {
        this.content = content;
        this.user = user;
        this.image = image;
        this.postDAO = new PostDAO();
    }

    // Method to save the post to the database
    public void save() {
        postDAO.createPost(this);
    }

    // Method to delete the post from the database
    public void delete() {
        postDAO.deletePost(this);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
