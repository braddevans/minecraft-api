package uk.co.breadhub.mcapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.breadhub.mcapi.database.UserRepository;

@SpringBootApplication
public class Main {

    public static Main instance;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        instance = new Main();
        SpringApplication.run(Main.class, args);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
