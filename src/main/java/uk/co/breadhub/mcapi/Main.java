package uk.co.breadhub.mcapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.co.breadhub.mcapi.database.UserRepository;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories("uk.co.breadhub.mcapi.database")
@EntityScan("uk.co.breadhub.mcapi.model")
public class Main {

    @Autowired
    static
    DataSource dataSource;
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        startup();
        SpringApplication.run(Main.class, args);
    }

    public static void startup() {
        System.out.println("Our DataSource is = " + dataSource);
    }

}
