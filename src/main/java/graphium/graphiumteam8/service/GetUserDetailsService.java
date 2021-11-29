package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import graphium.graphiumteam8.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Couldn't find username: " + username));

        return user.map(AppUserDetails::new).get();
    }
}
