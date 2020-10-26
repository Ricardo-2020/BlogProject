package com.tts.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogPostController {
    
    @Autowired
    private BlogPostRepository blogPostRepository;

    private static List<BlogPost> posts = new ArrayList<>();

    //index Page
    @GetMapping(value = "/")
    public String index (BlogPost blogPost, Model model) {
        posts.removeAll(posts);
        for (BlogPost postFromDB : blogPostRepository.findAll()) {
            posts.add(postFromDB);
        }
        
        model.addAttribute("posts", posts);

        // the return needs to be same as the folder name 
        // in the templates folder
        return "blogpost/index";
    }

    // new Page
    @GetMapping(value = "/blogpost/new")
    public String index (BlogPost blogPost) {

        // the return needs to be same as the folder name 
        // in the templates folder
        return "blogpost/new";
    }

    private BlogPost blogPost;

    @PostMapping(value = "/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(blogPost);
        //posts.add(blogPost);
        model.addAttribute("title", blogPost.getTitle());
	    model.addAttribute("author", blogPost.getAuthor());
	    model.addAttribute("blogEntry", blogPost.getBlogEntry());
	    return "blogpost/result";
    }

    @RequestMapping(value="/blogpost/{id}")
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        blogPostRepository.deleteById(id);
        return "redirect:/";
    }
    



}
