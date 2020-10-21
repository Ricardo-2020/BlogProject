package com.tts.techtalentblog;

public class BlogPost {
    
    // Blog post fields
    private Long id;
    private String title;
    private String author;
    private String blogEntry;

    // default constructor
    public BlogPost(){

    }

    public BlogPost(String title, String author, String blogEntry){
        this.title = title;
        this.author = author;
        this.blogEntry = blogEntry;
    }

    // Generate getters and setters
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
        return "BlogPost [author=" + author + ", blogEntry=" + blogEntry + ", id=" + id + ", title=" + title + "]";
    }
    
    







}
