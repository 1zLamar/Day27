package com.example.day27ex.Controller;

import com.example.day27ex.Model.Blog;
import com.example.day27ex.Model.MyUser;
import com.example.day27ex.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private  final BlogService blogService;

    @GetMapping("/get/all")
    public ResponseEntity getBlogs(){
        List<Blog> blogs=blogService.getAllBlogs();
        return ResponseEntity.status(200).body(blogs);
    }

    @GetMapping("/get")
    public ResponseEntity getUserBlogs(@AuthenticationPrincipal MyUser myUser){
        List<Blog> blogs = blogService.getBlogs(myUser);
        return ResponseEntity.status(200).body(blogs);
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog){
        blogService.addBlog(myUser.getId(),blog);
        return ResponseEntity.status(200).body("bolg added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog, @PathVariable Integer id){
        blogService.updateBlog(myUser.getId(),blog,id);
        return ResponseEntity.status(200).body("todo updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        blogService.deleteBlog(myUser.getId(),id);
        return ResponseEntity.status(200).body("todo deleted");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id) {
        Blog blog=blogService.getBlogByIdBlog(id);
        return ResponseEntity.status(200).body(blog);

    }

    @GetMapping("/get-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title) {
        Blog blog=blogService.getBlogByTitle(title);
        return ResponseEntity.status(200).body(blog);
    }

}
