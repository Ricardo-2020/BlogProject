package com.tts.techtalentblog.BlogPost;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class BlogPost {
    
    // JPA will recognize it as the objects ID and primary key
    // sets id as the primary key
    @Id
    // allows the underlying database to set the value for the field
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    // Blog post fields
    private Long id;
    private String title;
    private String author;
    private String blogEntry;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "tweet_tag", joinColumns = @JoinColumn(name = "tweet_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    // default constructor (empty)
    public BlogPost(){

    }
    // constructor with arguments
    public BlogPost(Long id, String title, String author, String blogEntry, List<Tag> tags){
        this.id = id;
        this.title = title;
        this.author = author;
        this.blogEntry = blogEntry;
        this.tags = tags;
    }

    // Generate getters and setters
    public Long getId() {
        return id;
    }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlogEntry() {
        return blogEntry;
    }

    public void setBlogEntry(String blogEntry) {
        this.blogEntry = blogEntry;
    }


    
    // the auto generated to String
    @Override
    public String toString() {
        return "BlogPost [author=" + author + ", blogEntry=" + blogEntry + ", id=" + id + ", tags=" + tags + ", title="
                + title + "]";
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    
   

    
    
    







}
