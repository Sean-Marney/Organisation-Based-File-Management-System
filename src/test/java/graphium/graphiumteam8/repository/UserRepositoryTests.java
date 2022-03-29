package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired private UserRepository userRepository;

    @Autowired private TestEntityManager testEntityManager;

    // Testing creation of new user
    @Test
    public void createNewUserTest(){

        User user = new User();
        user.setUsername("testUsername123");
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        user.setPassword("password123");

        User saveUser = userRepository.save(user);

        User newUser = testEntityManager.find(User.class, saveUser.getUser_id());

        Assertions.assertThat(newUser.getUsername()).isEqualTo(user.getUsername());
    }
}
