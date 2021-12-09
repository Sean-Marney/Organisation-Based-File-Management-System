package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    //@Query(value = "SELECT u FROM User u WHERE u.username = ?!")
    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
