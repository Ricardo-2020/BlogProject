package com.tts.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogPostController {
    
    @Autowired
    private BlogPostRepository blogPostRepository;

    private static List<BlogPost> posts = new ArrayList<>();

    //index Page
    @GetMapping(value = "/")
    public String index (BlogPost blogPost, Model model) {
        // removes all current posts inside of the arrayList called posts
        // this for each loop goes over the entire repository all blog posts, 
        // and for each one it adds them to the posts array

        
        posts.removeAll(posts);
        // .findAll() is an iterable - which allow a group of objects
        // This for each loop goes over the entire repository(all blog posts), 
        // and for
		// each one it adds them
		// to the posts array list
        for (BlogPost postFromDB : blogPostRepository.findAll()) {
            posts.add(postFromDB);
        }
        // this line makes the posts array list available to the webpage to be viewed
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

    @PostMapping(value = "/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        // We do not want to create a new instance everytime,
		// instead we can pass in the blogPost as is.
		// Springboot is doing the hard work for us in the background
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
	    model.addAttribute("author", blogPost.getAuthor());
	    model.addAttribute("blogEntry", blogPost.getBlogEntry());
	    return "blogpost/result";
    }

    // This is the mapping to edit a specific post or url
    @PostMapping(value="/blogpost/update/{id}")
    public String updatePostWithId(@PathVariable Long id, BlogPost blogPost,Model model) {
        // Use blogPostRepo to find
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();

            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());

            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
        }
        else {
            return "Error";
        }

        return "blogpost/result";
    }

    
    @RequestMapping(value="/blogpost/delete/{id}")
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        blogPostRepository.deleteById(id);
        return "blogpost/delete";
    }

    // This is the mapping to edit a specific post
    @RequestMapping(value="/blogpost/edit/{id}")
    public String editPostWithId(@PathVariable Long id,Model model) {
        // Use blogPostRepo to find post by id
        // Initalize a variable to be filled by the post if it exists
        Optional<BlogPost> editPost = blogPostRepository.findById(id);
        
		BlogPost result;

		// use Optional method, to check if the post came through
		if (editPost.isPresent()) {
			// if the post came through, store it in result
			result = editPost.get();
			// add attribute to page, accessible through model
			model.addAttribute("blogPost", result);
		} else {
			// Need to handle error here, you could use a html error page
			return "Error";
		}

		// Show browser the blogpost/edit page
		return "blogpost/edit";
    }

    

    

    
    



}
