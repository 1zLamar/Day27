package com.example.day27ex.Repository;

import com.example.day27ex.Model.Blog;
import com.example.day27ex.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);

    Blog findBlogByTitle(String title);

    List<Blog> findBlogsByUser(MyUser myUser);
}
