import java.util.ArrayList;
import java.util.List;

public class Friend {
    private int id;
    private int friendId;
    private boolean friendRequestStatus;
    private String createdTime;
    private UserDAO userDAO;

    public Friend(int friendId) {
        this.friendId = friendId;
        this.friendRequestStatus = false;
        this.createdTime = "";
        this.userDAO = new UserDAO();
    }

    // Method to add a friend request to the database
    public void sendFriendRequest(User user, User friend) {
        userDAO.addFriend(user, friend);
    }

    // Method to accept a friend request and update the database
    public void acceptFriendRequest(User user, Friend friendRequest) {
        friendRequest.setFriendRequestStatus(true);
        friendRequest.setCreatedTime(java.time.LocalDateTime.now().toString());

        // add friend to user's friend list
        User friend = userDAO.getUserById(friendRequest.getFriendId());
        user.addFriend(friend);

        // update friend list for both users in the database
        userDAO.acceptFriendRequest(user, friend);
    }

    // Method to remove a friend from the user's friends list in the database
    public void removeFriend(User user, Friend friend) {
        User friendUser = userDAO.getUserById(friend.getFriendId());
        user.removeFriend(friendUser);
        userDAO.removeFriend(user, friendUser);
    }

    // Method to retrieve the user's list of friends from the database
    public List<Friend> getFriendList(User user) {
        List<Friend> friendList = new ArrayList<>();

        for (User friend : user.getFriends()) {
            Friend friendObj = new Friend(friend.getId());
            friendObj.setFriendRequestStatus(true);
            friendObj.setCreatedTime(friend.getCreatedAt().toString());
            friendList.add(friendObj);
        }

        return friendList;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public boolean isFriendRequestStatus() {
        return friendRequestStatus;
    }

    public void setFriendRequestStatus(boolean friendRequestStatus) {
        this.friendRequestStatus = friendRequestStatus;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
