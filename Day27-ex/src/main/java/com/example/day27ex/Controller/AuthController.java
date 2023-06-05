package com.example.day27ex.Controller;

import com.example.day27ex.Model.MyUser;
import com.example.day27ex.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/reg")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("User register");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser user, @RequestBody MyUser myUser) {
        authService.update(user.getId(), myUser);
        return ResponseEntity.status(200).body("User Updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal MyUser user) {
        authService.deleteUser(user);

        return ResponseEntity.status(200).body("User deleted");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }


}
