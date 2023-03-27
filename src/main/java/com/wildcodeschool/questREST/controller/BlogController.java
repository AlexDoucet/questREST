package com.wildcodeschool.questREST.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.questREST.Blog;
import com.wildcodeschool.questREST.BlogRepository;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/blogs")
    public List<Blog> index(){
        return blogRepository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Blog show(@PathVariable Long id){
        return blogRepository.findById(id).get();
    }

    @PostMapping("/blogs/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blogs")
    public Blog create(@RequestBody Blog blog){
        return blogRepository.save(blog);
    }

    @PutMapping("/blogs/{id}")
    public Blog update(@PathVariable Long id, @RequestBody Blog blog){
        // getting blog
        Blog blogToUpdate = blogRepository.findById(id).get();
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setDescription(blog.getDescription());
        return blogRepository.save(blogToUpdate);
    }

    @DeleteMapping("blogs/{id}")
    public boolean delete(@PathVariable Long id){
        blogRepository.deleteById(id);
        return true;
    }
}