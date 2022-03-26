package graphium.graphiumteam8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GraphiumTeam8Application {


    public static void main(String[] args) {
        SpringApplication.run(GraphiumTeam8Application.class, args);

//        String rawPassword = "pass";
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(rawPassword);

//        System.out.println(encodedPassword);
    }


}
