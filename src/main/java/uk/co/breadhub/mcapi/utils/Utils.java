package uk.co.breadhub.mcapi.utils;

import me.kbrewster.exceptions.APIException;
import me.kbrewster.mojangapi.MojangAPI;
import me.kbrewster.mojangapi.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.breadhub.mcapi.Main;
import uk.co.breadhub.mcapi.model.User;
import uk.co.breadhub.mcapi.service.UserService;

import java.io.IOException;

@Component
public class Utils {

    @Autowired
    private UserService userService;

    public User newUser(String str) {
        User user = new User();
        try {
            Profile userProfile = MojangAPI.getProfile(str);
            user.setName(userProfile.getName());
            user.setUuid(userProfile.getId());
            System.out.println(String.format("Name: %s, UUID: %s", userProfile.getName(), userProfile.getId()));
            userService.save(user);
        } catch (APIException | IOException e) {
        }
        return user;
    }
}
