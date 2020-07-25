package uk.co.breadhub.mcapi;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.breadhub.mcapi.database.UserRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class Main {

    public static Main instance;
    public static Path WorkingDir = Paths.get(".");

    public static void main(String[] args) {
        instance = new Main();
        if (!(new File(WorkingDir + "/config.yml").exists())) {
            downloadDefaultConfig();
        }
        SpringApplication.run(Main.class, args);
    }

    private static void downloadDefaultConfig() {
        try {
            FileUtils.copyURLToFile(
                    new URL("https://raw.githubusercontent.com/braddevans/minecraft-api/default/application.yml"),
                    new File(WorkingDir + "/config.yml"),
                    999,
                    999
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ========================
    // if !getUser(Uuid).getName().equals(name)
    //      Update Prev Names and update name
    //
    //
    //
    // ========================

}
