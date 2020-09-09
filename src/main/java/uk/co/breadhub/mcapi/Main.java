package uk.co.breadhub.mcapi;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class Main {

    public static Main instance;
    public static Path WorkingDir = Paths.get(".");

    public static void main(String[] args) {
        instance = new Main();
        if (!(new File(WorkingDir + "/config.yml").exists())) {
            downloadDefaultConfig();
        }
        runLoop();
        SpringApplication.run(Main.class, args);
    }

    private static void runLoop() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                // run garbage collection
                System.gc();
                //System.out.println("gc");
            }
        }, 0, 5 * 1000);
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
}
