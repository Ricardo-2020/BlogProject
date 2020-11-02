package com.tts.techtalentblog.BlogPost;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{

    // List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);

    // List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);
    
    List<BlogPost> findAllByOrderByCreatedAtDesc();

    List<BlogPost> findByTags_PhraseOrderByCreatedAtDesc(String tag);
    
    

} 