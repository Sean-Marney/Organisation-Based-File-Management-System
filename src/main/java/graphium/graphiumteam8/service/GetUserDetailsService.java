package graphium.graphiumteam8.service;

import graphium.graphiumteam8.controller.RoleAuth.AppUserDetails;
import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//public class GetUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//

//    @Autowired
//    UserRepository userRepository;
/*
    @Autowired
    PasswordEncoder passwordEncoder;*/


    // Logic for returning a user by username
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Couldn't find username: " + username));
//
//        return user.map(AppUserDetails::new).get();
//    }
//}
