package com.example.pollingapplication.security;

import com.example.pollingapplication.models.User;
import com.example.pollingapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
   private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       // let people login with their username or email

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()->
                new UsernameNotFoundException("user not found with username or password"));
        return UserPrincipal.create(user);
    }

    // method used by JWTAuthenticationFilter

    @Transactional

    public UserDetails loadUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->
                new UsernameNotFoundException("user not found by id :"+ id));
        return UserPrincipal.create(user); 
    }


}
