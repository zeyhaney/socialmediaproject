import java.util.Date;

public class Message {
    private int id;
    private User sender;
    private User receiver;
    private String content;
    private Date sentTime;
    private boolean isEncrypted;
    private String encryptionKey;
    private MessageDAO messageDAO;

    public Message(User sender, User receiver, String content, boolean isEncrypted, String encryptionKey) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sentTime = new Date();
        this.isEncrypted = isEncrypted;
        this.encryptionKey = encryptionKey;
        this.messageDAO = new MessageDAO();
    }

    // Method to send a message to another user
    public void sendMessage() {
        if (isEncrypted) {
            content = Encryption.encrypt(content, encryptionKey);
        }

        messageDAO.createMessage(this);
        receiver.receiveMessage(this);
    }

    // Method to delete a message
    public void deleteMessage() {
        messageDAO.deleteMessage(this);
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        if (isEncrypted) {
            return Encryption.decrypt(content, encryptionKey);
        } else {
            return content;
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
