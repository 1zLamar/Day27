package com.example.day27ex.Service;

import com.example.day27ex.ApiException.ApiException;
import com.example.day27ex.Model.Blog;
import com.example.day27ex.Model.MyUser;
import com.example.day27ex.Repository.AuthRepository;
import com.example.day27ex.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs() {

        return blogRepository.findAll();
    }
    public List<Blog> getBlogs(MyUser myUser){
        return  blogRepository.findBlogsByUser(myUser);
    }

    public void addBlog(Integer userId,Blog blog){
        blog.setUser(authRepository.findMyUserById(userId));
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Blog blog, Integer blogId) {
        Blog blog1=blogRepository.findBlogById(blogId);
        if(blog1==null){
            throw new ApiException("not found");
        }
        if(userId!=blog1.getUser().getId()){
            throw new ApiException("unauthorized");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
    }

    public void deleteBlog(Integer userId, Integer id) {
        Blog blog1=blogRepository.findBlogById(id);
        if(blog1==null){
            throw new ApiException("not found");
        }
        if(userId!=blog1.getUser().getId()){
            throw new ApiException("unauthorized");
        }
        blogRepository.delete(blog1);
    }

    public Blog getBlogByIdBlog(Integer id) {
        Blog blog = blogRepository.findBlogById(id);
        if (blog == null)
            throw new ApiException("not found");
        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null)
            throw new ApiException("not found");
        return blog;
    }
}
