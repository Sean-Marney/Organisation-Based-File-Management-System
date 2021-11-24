package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
