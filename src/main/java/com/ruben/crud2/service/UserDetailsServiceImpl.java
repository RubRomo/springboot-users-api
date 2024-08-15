package com.ruben.crud2.service;

import com.ruben.crud2.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            throw new UsernameNotFoundException(username);
        }

        /*
        Usuario u = new Usuario();
        u.setUsename("rubenromo");
        //"secreto" as encrypted password
        //ruben.setPassword("$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq");
        u.setRole("ADMIN");
        */

        //Using explicitly path since User class already exists from UserDetails
        com.ruben.crud2.model.User response = repo.findByUsername(username);

        return User
                .withUsername(response.getUsername())
                .password(response.getPassword())
                .roles(response.getRole())
                .build();
    }
}