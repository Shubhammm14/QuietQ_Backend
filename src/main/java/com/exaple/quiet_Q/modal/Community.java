package com.exaple.quiet_Q.modal;

import com.exaple.quiet_Q.modal.Post;
import com.exaple.quiet_Q.modal.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String communityName;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    public Community() {
    }

    public Community(Long id, String communityName, List<User> users, List<Post> posts) {
        this.id = id;
        this.communityName = communityName;
        this.users = users;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
