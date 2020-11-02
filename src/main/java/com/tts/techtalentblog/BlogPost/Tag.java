package com.tts.techtalentblog.BlogPost;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    private String phrase;

    @ManyToMany(mappedBy = "tags")
    private List<BlogPost> tweets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<BlogPost> getTweets() {
        return tweets;
    }

    public void setTweets(List<BlogPost> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", phrase=" + phrase + ", tweets=" + tweets + "]";
    }

    

}

