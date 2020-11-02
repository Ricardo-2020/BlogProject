package com.tts.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<BlogPost> findAll() {
        List<BlogPost> blogPosts = blogPostRepository.findAllByOrderByCreatedAtDesc();
        return formatBlogPosts(blogPosts);
    }

    public List<BlogPost> findAllWithTag(String tag) {
        List<BlogPost> blogPost = BlogPostRepository.findByTags_PhraseOrderByCreatedAtDesc(tag);
        return formatBlogPosts(blogPost);
    }

    public void save(BlogPost blogPost) {
        handleTags(blogPost);
        blogPostRepository.save(blogPost);
    }

    private void handleTags(BlogPost blogPost) {
        List<Tag> tags = new ArrayList<Tag>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(blogPost.getBlogEntry());
        while (matcher.find()) {
            String phrase = matcher.group().substring(1).toLowerCase();
            Tag tag = tagRepository.findByPhrase(phrase);
            if (tag == null) {
                tag = new Tag();
                tag.setPhrase(phrase);
                tagRepository.save(tag);
            }
            tags.add(tag);
        }
        blogPost.setTags(tags);
    }

    private List<BlogPost> formatBlogPosts(List<BlogPost> blogPosts) {
        addTagLinks(blogPosts);
        return blogPosts;
    }

    private void addTagLinks(List<BlogPost> blogPosts) {
        Pattern pattern = Pattern.compile("#\\w+");
        for (BlogPost BlogPost : blogPosts) {
            String message = BlogPost.getBlogEntry();
            Matcher matcher = pattern.matcher(message);
            Set<String> tags = new HashSet<String>();
            while (matcher.find()) {
                tags.add(matcher.group());
            }
            for (String tag : tags) {
                message = message.replaceAll(tag,
                        "<a class=\"tag\" href=\"/blogPosts/" + tag.substring(1).toLowerCase() + "\">" + tag + "</a>");
            }
            BlogPost.setBlogEntry(message);
        }
    }

    

}
