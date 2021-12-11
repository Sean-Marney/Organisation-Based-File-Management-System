package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

//    void deletebyTitle(String username);
//    void deleteByUsername(String username);

}
