package com.example.day27ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username shouldn't be null")
//    @Size(min = 3)
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    @NotEmpty(message = "password shouldn't be null")
//    @Size(min = 5)
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    private Set<Blog> blogs;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(null));
    }

    @Override
    public boolean isAccountNonExpired() {///panned
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
