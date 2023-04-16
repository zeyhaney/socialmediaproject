import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String profileImage;
    private ArrayList<User> friends;
    private ArrayList<Post> posts;
    private UserDAO userDAO;

    public User(String username, String password, String email, String firstName, String lastName, String profileImage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.userDAO = new UserDAO();
    }

    // Method to create a new user account
    public static User createUser(String username, String password, String email, String firstName, String lastName, String profileImage) {
        User user = new User(username, password, email, firstName, lastName, profileImage);
        user.userDAO.createUser(user);
        return user;
    }

    // Method to update user profile information
    public void updateProfile(String firstName, String lastName, String email, String profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
        userDAO.updateProfile(this);
    }

    // Method to send a friend request to another user
    public void addFriend(User friend) {
        this.friends.add(friend);
        userDAO.addFriend(this, friend);
    }

    // Method to accept a friend request from another user
    public void acceptFriend(User friend) {
        friend.addFriend(this);
        this.friends.add(friend);
        userDAO.acceptFriendRequest(this, friend);
    }

    // Method to remove a friend from the user's friends list
    public void removeFriend(User friend) {
        this.friends.remove(friend);
        userDAO.removeFriend(this, friend);
    }

    // Method to create a new post
    public void createPost(String content, String image) {
        Post post = new Post(content, this, image);
        this.posts.add(post);
        PostDAO postDAO = new PostDAO();
        postDAO.createPost(post);
    }

    // Method to delete a post
    public void deletePost(Post post) {
        this.posts.remove(post);
        PostDAO postDAO = new PostDAO();
        postDAO.deletePost(post);
    }

    // Method to retrieve the user's feed (i.e., all posts made by the user and the user's friends)
    public ArrayList<Post> getFeed() {
        ArrayList<Post> feed = new ArrayList<>(this.posts);
        for (User friend : this.friends) {
            feed.addAll(friend.posts);
        }
        return feed;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setpublic

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

}
