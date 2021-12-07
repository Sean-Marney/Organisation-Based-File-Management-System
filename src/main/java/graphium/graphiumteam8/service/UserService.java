package graphium.graphiumteam8.service;

import graphium.graphiumteam8.entity.User;
import graphium.graphiumteam8.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listUsers(){

        return userRepository.findAll();
    }

    public List<String> listUsernames(){

        List<String> listOfUsernames = new ArrayList<>();

        for(User user : listUsers()){
            listOfUsernames.add(user.getUsername());
        }

        return listOfUsernames;
    }

    public void saveUser(User user){

        userRepository.save(user);
    }
}
