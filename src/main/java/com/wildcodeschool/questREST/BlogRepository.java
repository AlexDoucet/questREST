package com.wildcodeschool.questREST;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    // custom query to search to blog post by title or content
   List<Blog> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
    
}
