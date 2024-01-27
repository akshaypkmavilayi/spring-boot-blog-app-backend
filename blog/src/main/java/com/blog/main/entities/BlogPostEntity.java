package com.blog.main.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
public class BlogPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column
    private String blogTitle;

    @Lob
    @Column
    private String blogContent;

    @Column
    private Instant blogCreatedOn;

    @Column
    private  Instant blogUpdatedOn;

    @Column
    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Instant getBlogCreatedOn() {
        return blogCreatedOn;
    }

    public void setBlogCreatedOn(Instant blogCreatedOn) {
        this.blogCreatedOn = blogCreatedOn;
    }

    public Instant getBlogUpdatedOn() {
        return blogUpdatedOn;
    }

    public void setBlogUpdatedOn(Instant blogUpdatedOn) {
        this.blogUpdatedOn = blogUpdatedOn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
