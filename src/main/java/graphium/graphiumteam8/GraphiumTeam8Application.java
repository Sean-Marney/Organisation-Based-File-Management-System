package graphium.graphiumteam8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GraphiumTeam8Application {

    public static void main(String[] args) {
        SpringApplication.run(GraphiumTeam8Application.class, args);
    }
}
