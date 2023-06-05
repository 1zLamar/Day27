package com.example.day27ex.Service;

import com.example.day27ex.Model.Blog;
import com.example.day27ex.Model.MyUser;
import com.example.day27ex.Repository.AuthRepository;
import com.example.day27ex.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;

    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        authRepository.save(myUser);
    }

    public void update(Integer userId, MyUser myUser) {
        MyUser myUser1 = authRepository.findMyUserById(userId);
        myUser1.setUsername(myUser.getUsername());
        String hash = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser1.setPassword(hash);
        authRepository.save(myUser1);
    }

    public void deleteUser(MyUser myUser) {

        MyUser myUser1 = authRepository.findMyUserById(myUser.getId());
        List<Blog> blogs = blogRepository.findBlogsByUser(myUser);
        for (int i = 0; i < blogs.size(); i++) {
            blogs.get(i).setUser(null);
        }
        authRepository.delete(myUser);
    }

}
