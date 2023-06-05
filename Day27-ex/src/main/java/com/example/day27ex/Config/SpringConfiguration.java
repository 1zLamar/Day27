package com.example.day27ex.Config;

import com.example.day27ex.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

    private final MyUserDetailsService myUserDetailsService;
    @Bean
    public DaoAuthenticationProvider authenticationProvide(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvide())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/reg").permitAll()
                .requestMatchers("/api/v1/auth/login","/api/v1/auth/logout").permitAll()
                .requestMatchers("/api/v1/auth/update","/api/v1/auth/delete").permitAll()
                .requestMatchers("/api/v1/blog/get-all","/api/v1/blog/get-id/{id}").permitAll()
                .requestMatchers("/api/v1/blog/get-title/{id}").permitAll()
                .requestMatchers("/api/v1/blog/get").permitAll()
                .requestMatchers("/api/v1/blog/add").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }

}
