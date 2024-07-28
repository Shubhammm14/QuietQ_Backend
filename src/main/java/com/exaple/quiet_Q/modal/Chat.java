package com.exaple.quiet_Q.modal;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatName;
    private String chatImage;
    @Column(name = "is_group")
    private boolean isGroup;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    @ManyToMany
    private Set<User> admins = new HashSet<>();
    @JoinColumn(name = "created_by")
    @ManyToOne
    private User createdBy;
    @OneToMany
    private List<Message> messages = new ArrayList<>();
    @ManyToMany
    private Set<User> users = new HashSet<>();
    private LocalDateTime timestamp;

    // Getters and setters for the new fields
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatImage() {
        return chatImage;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public Chat() {}

    public Chat(Long id, String chatName, String chatImage, boolean isGroup, Set<User> admins, User createdBy, List<Message> messages, Set<User> users, LocalDateTime timestamp, List<String> tags) {
        this.id = id;
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.isGroup = isGroup;
        this.admins = admins;
        this.createdBy = createdBy;
        this.messages = messages;
        this.users = users;
        this.timestamp = timestamp;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", chatName='" + chatName + '\'' +
                ", chatImage='" + chatImage + '\'' +
                ", isGroup=" + isGroup +
                ", admins=" + admins +
                ", createdBy=" + createdBy +
                ", messages=" + messages +
                ", users=" + users +
                ", timestamp=" + timestamp +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chat chat = (Chat) obj;
        if (id != null ? !id.equals(chat.id) : chat.id != null) return false;
        return chatName != null ? chatName.equals(chat.chatName) : chat.chatName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (chatName != null ? chatName.hashCode() : 0);
        return result;
    }
}
