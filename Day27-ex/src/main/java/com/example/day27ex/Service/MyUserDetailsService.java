package com.example.day27ex.Service;

import com.example.day27ex.Model.MyUser;
import com.example.day27ex.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=authRepository.findMyUserByUsername(username);
        if(username==null){
            throw new UsernameNotFoundException("wrong username or password");
        }
        return myUser;
    }
}
